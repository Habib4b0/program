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

import com.stpl.app.exception.NoSuchVwInventoryWdActualProjMasException;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.impl.VwInventoryWdActualProjMasImpl;
import com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl;
import com.stpl.app.service.persistence.VwInventoryWdActualProjMasPersistence;

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
 * The persistence implementation for the vw inventory wd actual proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMasPersistence
 * @see com.stpl.app.service.persistence.VwInventoryWdActualProjMasUtil
 * @generated
 */
@ProviderType
public class VwInventoryWdActualProjMasPersistenceImpl
	extends BasePersistenceImpl<VwInventoryWdActualProjMas>
	implements VwInventoryWdActualProjMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwInventoryWdActualProjMasUtil} to access the vw inventory wd actual proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwInventoryWdActualProjMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			VwInventoryWdActualProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			VwInventoryWdActualProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public VwInventoryWdActualProjMasPersistenceImpl() {
		setModelClass(VwInventoryWdActualProjMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

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
			dbColumnNames.put("inventoryWdActualProjMasSid",
				"INVENTORY_WD_ACTUAL_PROJ_MAS_SID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("unitsOnHand", "UNITS_ON_HAND");
			dbColumnNames.put("amountReceived", "AMOUNT_RECEIVED");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
			dbColumnNames.put("quantityReceived", "QUANTITY_RECEIVED");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("isForecast", "IS_FORECAST");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("amountOnOrder", "AMOUNT_ON_ORDER");
			dbColumnNames.put("forecastName", "FORECAST_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw inventory wd actual proj mas in the entity cache if it is enabled.
	 *
	 * @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
	 */
	@Override
	public void cacheResult(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		entityCache.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasImpl.class,
			vwInventoryWdActualProjMas.getPrimaryKey(),
			vwInventoryWdActualProjMas);

		vwInventoryWdActualProjMas.resetOriginalValues();
	}

	/**
	 * Caches the vw inventory wd actual proj mases in the entity cache if it is enabled.
	 *
	 * @param vwInventoryWdActualProjMases the vw inventory wd actual proj mases
	 */
	@Override
	public void cacheResult(
		List<VwInventoryWdActualProjMas> vwInventoryWdActualProjMases) {
		for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : vwInventoryWdActualProjMases) {
			if (entityCache.getResult(
						VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
						VwInventoryWdActualProjMasImpl.class,
						vwInventoryWdActualProjMas.getPrimaryKey()) == null) {
				cacheResult(vwInventoryWdActualProjMas);
			}
			else {
				vwInventoryWdActualProjMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw inventory wd actual proj mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwInventoryWdActualProjMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw inventory wd actual proj mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		entityCache.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasImpl.class,
			vwInventoryWdActualProjMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwInventoryWdActualProjMas> vwInventoryWdActualProjMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : vwInventoryWdActualProjMases) {
			entityCache.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
				VwInventoryWdActualProjMasImpl.class,
				vwInventoryWdActualProjMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw inventory wd actual proj mas with the primary key. Does not add the vw inventory wd actual proj mas to the database.
	 *
	 * @param inventoryWdActualProjMasSid the primary key for the new vw inventory wd actual proj mas
	 * @return the new vw inventory wd actual proj mas
	 */
	@Override
	public VwInventoryWdActualProjMas create(int inventoryWdActualProjMasSid) {
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = new VwInventoryWdActualProjMasImpl();

		vwInventoryWdActualProjMas.setNew(true);
		vwInventoryWdActualProjMas.setPrimaryKey(inventoryWdActualProjMasSid);

		return vwInventoryWdActualProjMas;
	}

	/**
	 * Removes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas that was removed
	 * @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas remove(int inventoryWdActualProjMasSid)
		throws NoSuchVwInventoryWdActualProjMasException {
		return remove((Serializable)inventoryWdActualProjMasSid);
	}

	/**
	 * Removes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas that was removed
	 * @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas remove(Serializable primaryKey)
		throws NoSuchVwInventoryWdActualProjMasException {
		Session session = null;

		try {
			session = openSession();

			VwInventoryWdActualProjMas vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)session.get(VwInventoryWdActualProjMasImpl.class,
					primaryKey);

			if (vwInventoryWdActualProjMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwInventoryWdActualProjMas);
		}
		catch (NoSuchVwInventoryWdActualProjMasException nsee) {
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
	protected VwInventoryWdActualProjMas removeImpl(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		vwInventoryWdActualProjMas = toUnwrappedModel(vwInventoryWdActualProjMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwInventoryWdActualProjMas)) {
				vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)session.get(VwInventoryWdActualProjMasImpl.class,
						vwInventoryWdActualProjMas.getPrimaryKeyObj());
			}

			if (vwInventoryWdActualProjMas != null) {
				session.delete(vwInventoryWdActualProjMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwInventoryWdActualProjMas != null) {
			clearCache(vwInventoryWdActualProjMas);
		}

		return vwInventoryWdActualProjMas;
	}

	@Override
	public VwInventoryWdActualProjMas updateImpl(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		vwInventoryWdActualProjMas = toUnwrappedModel(vwInventoryWdActualProjMas);

		boolean isNew = vwInventoryWdActualProjMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwInventoryWdActualProjMas.isNew()) {
				session.save(vwInventoryWdActualProjMas);

				vwInventoryWdActualProjMas.setNew(false);
			}
			else {
				vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)session.merge(vwInventoryWdActualProjMas);
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

		entityCache.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwInventoryWdActualProjMasImpl.class,
			vwInventoryWdActualProjMas.getPrimaryKey(),
			vwInventoryWdActualProjMas, false);

		vwInventoryWdActualProjMas.resetOriginalValues();

		return vwInventoryWdActualProjMas;
	}

	protected VwInventoryWdActualProjMas toUnwrappedModel(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		if (vwInventoryWdActualProjMas instanceof VwInventoryWdActualProjMasImpl) {
			return vwInventoryWdActualProjMas;
		}

		VwInventoryWdActualProjMasImpl vwInventoryWdActualProjMasImpl = new VwInventoryWdActualProjMasImpl();

		vwInventoryWdActualProjMasImpl.setNew(vwInventoryWdActualProjMas.isNew());
		vwInventoryWdActualProjMasImpl.setPrimaryKey(vwInventoryWdActualProjMas.getPrimaryKey());

		vwInventoryWdActualProjMasImpl.setQuantityOnOrder(vwInventoryWdActualProjMas.getQuantityOnOrder());
		vwInventoryWdActualProjMasImpl.setWeek(vwInventoryWdActualProjMas.getWeek());
		vwInventoryWdActualProjMasImpl.setPrice(vwInventoryWdActualProjMas.getPrice());
		vwInventoryWdActualProjMasImpl.setAmountOnHand(vwInventoryWdActualProjMas.getAmountOnHand());
		vwInventoryWdActualProjMasImpl.setIsMaster(vwInventoryWdActualProjMas.getIsMaster());
		vwInventoryWdActualProjMasImpl.setCompanyName(vwInventoryWdActualProjMas.getCompanyName());
		vwInventoryWdActualProjMasImpl.setYear(vwInventoryWdActualProjMas.getYear());
		vwInventoryWdActualProjMasImpl.setItemId(vwInventoryWdActualProjMas.getItemId());
		vwInventoryWdActualProjMasImpl.setModifiedDate(vwInventoryWdActualProjMas.getModifiedDate());
		vwInventoryWdActualProjMasImpl.setOrganizationKey(vwInventoryWdActualProjMas.getOrganizationKey());
		vwInventoryWdActualProjMasImpl.setInventoryWdActualProjMasSid(vwInventoryWdActualProjMas.getInventoryWdActualProjMasSid());
		vwInventoryWdActualProjMasImpl.setSource(vwInventoryWdActualProjMas.getSource());
		vwInventoryWdActualProjMasImpl.setCreatedBy(vwInventoryWdActualProjMas.getCreatedBy());
		vwInventoryWdActualProjMasImpl.setCreatedDate(vwInventoryWdActualProjMas.getCreatedDate());
		vwInventoryWdActualProjMasImpl.setDay(vwInventoryWdActualProjMas.getDay());
		vwInventoryWdActualProjMasImpl.setAddChgDelIndicator(vwInventoryWdActualProjMas.getAddChgDelIndicator());
		vwInventoryWdActualProjMasImpl.setUnitsOnHand(vwInventoryWdActualProjMas.getUnitsOnHand());
		vwInventoryWdActualProjMasImpl.setAmountReceived(vwInventoryWdActualProjMas.getAmountReceived());
		vwInventoryWdActualProjMasImpl.setModifiedBy(vwInventoryWdActualProjMas.getModifiedBy());
		vwInventoryWdActualProjMasImpl.setMonth(vwInventoryWdActualProjMas.getMonth());
		vwInventoryWdActualProjMasImpl.setAmountWithdrawn(vwInventoryWdActualProjMas.getAmountWithdrawn());
		vwInventoryWdActualProjMasImpl.setQuantityReceived(vwInventoryWdActualProjMas.getQuantityReceived());
		vwInventoryWdActualProjMasImpl.setUnitsWithdrawn(vwInventoryWdActualProjMas.getUnitsWithdrawn());
		vwInventoryWdActualProjMasImpl.setCountry(vwInventoryWdActualProjMas.getCountry());
		vwInventoryWdActualProjMasImpl.setCompanyStringId(vwInventoryWdActualProjMas.getCompanyStringId());
		vwInventoryWdActualProjMasImpl.setIsForecast(vwInventoryWdActualProjMas.getIsForecast());
		vwInventoryWdActualProjMasImpl.setForecastVer(vwInventoryWdActualProjMas.getForecastVer());
		vwInventoryWdActualProjMasImpl.setBatchId(vwInventoryWdActualProjMas.getBatchId());
		vwInventoryWdActualProjMasImpl.setItemName(vwInventoryWdActualProjMas.getItemName());
		vwInventoryWdActualProjMasImpl.setAmountOnOrder(vwInventoryWdActualProjMas.getAmountOnOrder());
		vwInventoryWdActualProjMasImpl.setForecastName(vwInventoryWdActualProjMas.getForecastName());

		return vwInventoryWdActualProjMasImpl;
	}

	/**
	 * Returns the vw inventory wd actual proj mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas
	 * @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwInventoryWdActualProjMasException {
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

		if (vwInventoryWdActualProjMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwInventoryWdActualProjMas;
	}

	/**
	 * Returns the vw inventory wd actual proj mas with the primary key or throws a {@link NoSuchVwInventoryWdActualProjMasException} if it could not be found.
	 *
	 * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas
	 * @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas findByPrimaryKey(
		int inventoryWdActualProjMasSid)
		throws NoSuchVwInventoryWdActualProjMasException {
		return findByPrimaryKey((Serializable)inventoryWdActualProjMasSid);
	}

	/**
	 * Returns the vw inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas, or <code>null</code> if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
				VwInventoryWdActualProjMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)serializable;

		if (vwInventoryWdActualProjMas == null) {
			Session session = null;

			try {
				session = openSession();

				vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)session.get(VwInventoryWdActualProjMasImpl.class,
						primaryKey);

				if (vwInventoryWdActualProjMas != null) {
					cacheResult(vwInventoryWdActualProjMas);
				}
				else {
					entityCache.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
						VwInventoryWdActualProjMasImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwInventoryWdActualProjMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwInventoryWdActualProjMas;
	}

	/**
	 * Returns the vw inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	 * @return the vw inventory wd actual proj mas, or <code>null</code> if a vw inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwInventoryWdActualProjMas fetchByPrimaryKey(
		int inventoryWdActualProjMasSid) {
		return fetchByPrimaryKey((Serializable)inventoryWdActualProjMasSid);
	}

	@Override
	public Map<Serializable, VwInventoryWdActualProjMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwInventoryWdActualProjMas> map = new HashMap<Serializable, VwInventoryWdActualProjMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwInventoryWdActualProjMas vwInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

			if (vwInventoryWdActualProjMas != null) {
				map.put(primaryKey, vwInventoryWdActualProjMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwInventoryWdActualProjMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwInventoryWdActualProjMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWINVENTORYWDACTUALPROJMAS_WHERE_PKS_IN);

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

			for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : (List<VwInventoryWdActualProjMas>)q.list()) {
				map.put(vwInventoryWdActualProjMas.getPrimaryKeyObj(),
					vwInventoryWdActualProjMas);

				cacheResult(vwInventoryWdActualProjMas);

				uncachedPrimaryKeys.remove(vwInventoryWdActualProjMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwInventoryWdActualProjMasImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw inventory wd actual proj mases.
	 *
	 * @return the vw inventory wd actual proj mases
	 */
	@Override
	public List<VwInventoryWdActualProjMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw inventory wd actual proj mases
	 * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	 * @return the range of vw inventory wd actual proj mases
	 */
	@Override
	public List<VwInventoryWdActualProjMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw inventory wd actual proj mases
	 * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw inventory wd actual proj mases
	 */
	@Override
	public List<VwInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwInventoryWdActualProjMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw inventory wd actual proj mases
	 * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw inventory wd actual proj mases
	 */
	@Override
	public List<VwInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwInventoryWdActualProjMas> orderByComparator,
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

		List<VwInventoryWdActualProjMas> list = null;

		if (retrieveFromCache) {
			list = (List<VwInventoryWdActualProjMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWINVENTORYWDACTUALPROJMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWINVENTORYWDACTUALPROJMAS;

				if (pagination) {
					sql = sql.concat(VwInventoryWdActualProjMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwInventoryWdActualProjMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwInventoryWdActualProjMas>)QueryUtil.list(q,
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
	 * Removes all the vw inventory wd actual proj mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : findAll()) {
			remove(vwInventoryWdActualProjMas);
		}
	}

	/**
	 * Returns the number of vw inventory wd actual proj mases.
	 *
	 * @return the number of vw inventory wd actual proj mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWINVENTORYWDACTUALPROJMAS);

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
		return VwInventoryWdActualProjMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw inventory wd actual proj mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwInventoryWdActualProjMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWINVENTORYWDACTUALPROJMAS = "SELECT vwInventoryWdActualProjMas FROM VwInventoryWdActualProjMas vwInventoryWdActualProjMas";
	private static final String _SQL_SELECT_VWINVENTORYWDACTUALPROJMAS_WHERE_PKS_IN =
		"SELECT vwInventoryWdActualProjMas FROM VwInventoryWdActualProjMas vwInventoryWdActualProjMas WHERE INVENTORY_WD_ACTUAL_PROJ_MAS_SID IN (";
	private static final String _SQL_COUNT_VWINVENTORYWDACTUALPROJMAS = "SELECT COUNT(vwInventoryWdActualProjMas) FROM VwInventoryWdActualProjMas vwInventoryWdActualProjMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwInventoryWdActualProjMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwInventoryWdActualProjMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwInventoryWdActualProjMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"quantityOnOrder", "week", "price", "amountOnHand", "isMaster",
				"companyName", "year", "itemId", "modifiedDate",
				"organizationKey", "inventoryWdActualProjMasSid", "source",
				"createdBy", "createdDate", "day", "addChgDelIndicator",
				"unitsOnHand", "amountReceived", "modifiedBy", "month",
				"amountWithdrawn", "quantityReceived", "unitsWithdrawn",
				"country", "companyStringId", "isForecast", "forecastVer",
				"batchId", "itemName", "amountOnOrder", "forecastName"
			});
}