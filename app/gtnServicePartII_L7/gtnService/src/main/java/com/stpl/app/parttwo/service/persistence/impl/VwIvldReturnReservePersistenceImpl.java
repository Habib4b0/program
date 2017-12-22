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

import com.stpl.app.parttwo.exception.NoSuchVwIvldReturnReserveException;
import com.stpl.app.parttwo.model.VwIvldReturnReserve;
import com.stpl.app.parttwo.model.impl.VwIvldReturnReserveImpl;
import com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl;
import com.stpl.app.parttwo.service.persistence.VwIvldReturnReservePersistence;

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
 * The persistence implementation for the vw ivld return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldReturnReservePersistence
 * @see com.stpl.app.parttwo.service.persistence.VwIvldReturnReserveUtil
 * @generated
 */
@ProviderType
public class VwIvldReturnReservePersistenceImpl extends BasePersistenceImpl<VwIvldReturnReserve>
	implements VwIvldReturnReservePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwIvldReturnReserveUtil} to access the vw ivld return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwIvldReturnReserveImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED,
			VwIvldReturnReserveImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED,
			VwIvldReturnReserveImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwIvldReturnReservePersistenceImpl() {
		setModelClass(VwIvldReturnReserve.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ivldReturnReserveSid", "IVLD_RETURN_RESERVE_SID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("project", "PROJECT");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("returnReserveIntfId", "RETURN_RESERVE_INTF_ID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("businessUnit", "BUSINESS_UNIT");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("glCompany", "GL_COMPANY");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("future1", "FUTURE1");
			dbColumnNames.put("future2", "FUTURE2");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("division", "DIVISION");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorField", "ERROR_FIELD");
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
	 * Caches the vw ivld return reserve in the entity cache if it is enabled.
	 *
	 * @param vwIvldReturnReserve the vw ivld return reserve
	 */
	@Override
	public void cacheResult(VwIvldReturnReserve vwIvldReturnReserve) {
		entityCache.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey(),
			vwIvldReturnReserve);

		vwIvldReturnReserve.resetOriginalValues();
	}

	/**
	 * Caches the vw ivld return reserves in the entity cache if it is enabled.
	 *
	 * @param vwIvldReturnReserves the vw ivld return reserves
	 */
	@Override
	public void cacheResult(List<VwIvldReturnReserve> vwIvldReturnReserves) {
		for (VwIvldReturnReserve vwIvldReturnReserve : vwIvldReturnReserves) {
			if (entityCache.getResult(
						VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldReturnReserveImpl.class,
						vwIvldReturnReserve.getPrimaryKey()) == null) {
				cacheResult(vwIvldReturnReserve);
			}
			else {
				vwIvldReturnReserve.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw ivld return reserves.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwIvldReturnReserveImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw ivld return reserve.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwIvldReturnReserve vwIvldReturnReserve) {
		entityCache.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwIvldReturnReserve> vwIvldReturnReserves) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwIvldReturnReserve vwIvldReturnReserve : vwIvldReturnReserves) {
			entityCache.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldReturnReserveImpl.class,
				vwIvldReturnReserve.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
	 *
	 * @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
	 * @return the new vw ivld return reserve
	 */
	@Override
	public VwIvldReturnReserve create(int ivldReturnReserveSid) {
		VwIvldReturnReserve vwIvldReturnReserve = new VwIvldReturnReserveImpl();

		vwIvldReturnReserve.setNew(true);
		vwIvldReturnReserve.setPrimaryKey(ivldReturnReserveSid);

		return vwIvldReturnReserve;
	}

	/**
	 * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve that was removed
	 * @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve remove(int ivldReturnReserveSid)
		throws NoSuchVwIvldReturnReserveException {
		return remove((Serializable)ivldReturnReserveSid);
	}

	/**
	 * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve that was removed
	 * @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve remove(Serializable primaryKey)
		throws NoSuchVwIvldReturnReserveException {
		Session session = null;

		try {
			session = openSession();

			VwIvldReturnReserve vwIvldReturnReserve = (VwIvldReturnReserve)session.get(VwIvldReturnReserveImpl.class,
					primaryKey);

			if (vwIvldReturnReserve == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwIvldReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwIvldReturnReserve);
		}
		catch (NoSuchVwIvldReturnReserveException nsee) {
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
	protected VwIvldReturnReserve removeImpl(
		VwIvldReturnReserve vwIvldReturnReserve) {
		vwIvldReturnReserve = toUnwrappedModel(vwIvldReturnReserve);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwIvldReturnReserve)) {
				vwIvldReturnReserve = (VwIvldReturnReserve)session.get(VwIvldReturnReserveImpl.class,
						vwIvldReturnReserve.getPrimaryKeyObj());
			}

			if (vwIvldReturnReserve != null) {
				session.delete(vwIvldReturnReserve);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwIvldReturnReserve != null) {
			clearCache(vwIvldReturnReserve);
		}

		return vwIvldReturnReserve;
	}

	@Override
	public VwIvldReturnReserve updateImpl(
		VwIvldReturnReserve vwIvldReturnReserve) {
		vwIvldReturnReserve = toUnwrappedModel(vwIvldReturnReserve);

		boolean isNew = vwIvldReturnReserve.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwIvldReturnReserve.isNew()) {
				session.save(vwIvldReturnReserve);

				vwIvldReturnReserve.setNew(false);
			}
			else {
				vwIvldReturnReserve = (VwIvldReturnReserve)session.merge(vwIvldReturnReserve);
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

		entityCache.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey(),
			vwIvldReturnReserve, false);

		vwIvldReturnReserve.resetOriginalValues();

		return vwIvldReturnReserve;
	}

	protected VwIvldReturnReserve toUnwrappedModel(
		VwIvldReturnReserve vwIvldReturnReserve) {
		if (vwIvldReturnReserve instanceof VwIvldReturnReserveImpl) {
			return vwIvldReturnReserve;
		}

		VwIvldReturnReserveImpl vwIvldReturnReserveImpl = new VwIvldReturnReserveImpl();

		vwIvldReturnReserveImpl.setNew(vwIvldReturnReserve.isNew());
		vwIvldReturnReserveImpl.setPrimaryKey(vwIvldReturnReserve.getPrimaryKey());

		vwIvldReturnReserveImpl.setIvldReturnReserveSid(vwIvldReturnReserve.getIvldReturnReserveSid());
		vwIvldReturnReserveImpl.setCompanyName(vwIvldReturnReserve.getCompanyName());
		vwIvldReturnReserveImpl.setYear(vwIvldReturnReserve.getYear());
		vwIvldReturnReserveImpl.setProject(vwIvldReturnReserve.getProject());
		vwIvldReturnReserveImpl.setItemId(vwIvldReturnReserve.getItemId());
		vwIvldReturnReserveImpl.setBrandName(vwIvldReturnReserve.getBrandName());
		vwIvldReturnReserveImpl.setModifiedDate(vwIvldReturnReserve.getModifiedDate());
		vwIvldReturnReserveImpl.setAccount(vwIvldReturnReserve.getAccount());
		vwIvldReturnReserveImpl.setReturnReserveIntfId(vwIvldReturnReserve.getReturnReserveIntfId());
		vwIvldReturnReserveImpl.setSource(vwIvldReturnReserve.getSource());
		vwIvldReturnReserveImpl.setCreatedDate(vwIvldReturnReserve.getCreatedDate());
		vwIvldReturnReserveImpl.setCreatedBy(vwIvldReturnReserve.getCreatedBy());
		vwIvldReturnReserveImpl.setBusinessUnit(vwIvldReturnReserve.getBusinessUnit());
		vwIvldReturnReserveImpl.setBusinessUnitName(vwIvldReturnReserve.getBusinessUnitName());
		vwIvldReturnReserveImpl.setAddChgDelIndicator(vwIvldReturnReserve.getAddChgDelIndicator());
		vwIvldReturnReserveImpl.setErrorCode(vwIvldReturnReserve.getErrorCode());
		vwIvldReturnReserveImpl.setIntfInsertedDate(vwIvldReturnReserve.getIntfInsertedDate());
		vwIvldReturnReserveImpl.setModifiedBy(vwIvldReturnReserve.getModifiedBy());
		vwIvldReturnReserveImpl.setItemNo(vwIvldReturnReserve.getItemNo());
		vwIvldReturnReserveImpl.setMonth(vwIvldReturnReserve.getMonth());
		vwIvldReturnReserveImpl.setReprocessedFlag(vwIvldReturnReserve.getReprocessedFlag());
		vwIvldReturnReserveImpl.setUdc6(vwIvldReturnReserve.getUdc6());
		vwIvldReturnReserveImpl.setUdc5(vwIvldReturnReserve.getUdc5());
		vwIvldReturnReserveImpl.setUdc4(vwIvldReturnReserve.getUdc4());
		vwIvldReturnReserveImpl.setUdc1(vwIvldReturnReserve.getUdc1());
		vwIvldReturnReserveImpl.setUnits(vwIvldReturnReserve.getUnits());
		vwIvldReturnReserveImpl.setUdc2(vwIvldReturnReserve.getUdc2());
		vwIvldReturnReserveImpl.setUdc3(vwIvldReturnReserve.getUdc3());
		vwIvldReturnReserveImpl.setReasonForFailure(vwIvldReturnReserve.getReasonForFailure());
		vwIvldReturnReserveImpl.setCountry(vwIvldReturnReserve.getCountry());
		vwIvldReturnReserveImpl.setCompanyIdString(vwIvldReturnReserve.getCompanyIdString());
		vwIvldReturnReserveImpl.setCostCenter(vwIvldReturnReserve.getCostCenter());
		vwIvldReturnReserveImpl.setGlCompany(vwIvldReturnReserve.getGlCompany());
		vwIvldReturnReserveImpl.setBrandId(vwIvldReturnReserve.getBrandId());
		vwIvldReturnReserveImpl.setFuture1(vwIvldReturnReserve.getFuture1());
		vwIvldReturnReserveImpl.setFuture2(vwIvldReturnReserve.getFuture2());
		vwIvldReturnReserveImpl.setAmount(vwIvldReturnReserve.getAmount());
		vwIvldReturnReserveImpl.setDivision(vwIvldReturnReserve.getDivision());
		vwIvldReturnReserveImpl.setCompanyNo(vwIvldReturnReserve.getCompanyNo());
		vwIvldReturnReserveImpl.setBatchId(vwIvldReturnReserve.getBatchId());
		vwIvldReturnReserveImpl.setItemName(vwIvldReturnReserve.getItemName());
		vwIvldReturnReserveImpl.setErrorField(vwIvldReturnReserve.getErrorField());
		vwIvldReturnReserveImpl.setCheckRecord(vwIvldReturnReserve.isCheckRecord());

		return vwIvldReturnReserveImpl;
	}

	/**
	 * Returns the vw ivld return reserve with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve
	 * @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwIvldReturnReserveException {
		VwIvldReturnReserve vwIvldReturnReserve = fetchByPrimaryKey(primaryKey);

		if (vwIvldReturnReserve == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwIvldReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwIvldReturnReserve;
	}

	/**
	 * Returns the vw ivld return reserve with the primary key or throws a {@link NoSuchVwIvldReturnReserveException} if it could not be found.
	 *
	 * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve
	 * @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve findByPrimaryKey(int ivldReturnReserveSid)
		throws NoSuchVwIvldReturnReserveException {
		return findByPrimaryKey((Serializable)ivldReturnReserveSid);
	}

	/**
	 * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldReturnReserveImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwIvldReturnReserve vwIvldReturnReserve = (VwIvldReturnReserve)serializable;

		if (vwIvldReturnReserve == null) {
			Session session = null;

			try {
				session = openSession();

				vwIvldReturnReserve = (VwIvldReturnReserve)session.get(VwIvldReturnReserveImpl.class,
						primaryKey);

				if (vwIvldReturnReserve != null) {
					cacheResult(vwIvldReturnReserve);
				}
				else {
					entityCache.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldReturnReserveImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldReturnReserveImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwIvldReturnReserve;
	}

	/**
	 * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	 * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
	 */
	@Override
	public VwIvldReturnReserve fetchByPrimaryKey(int ivldReturnReserveSid) {
		return fetchByPrimaryKey((Serializable)ivldReturnReserveSid);
	}

	@Override
	public Map<Serializable, VwIvldReturnReserve> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwIvldReturnReserve> map = new HashMap<Serializable, VwIvldReturnReserve>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwIvldReturnReserve vwIvldReturnReserve = fetchByPrimaryKey(primaryKey);

			if (vwIvldReturnReserve != null) {
				map.put(primaryKey, vwIvldReturnReserve);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldReturnReserveImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwIvldReturnReserve)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWIVLDRETURNRESERVE_WHERE_PKS_IN);

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

			for (VwIvldReturnReserve vwIvldReturnReserve : (List<VwIvldReturnReserve>)q.list()) {
				map.put(vwIvldReturnReserve.getPrimaryKeyObj(),
					vwIvldReturnReserve);

				cacheResult(vwIvldReturnReserve);

				uncachedPrimaryKeys.remove(vwIvldReturnReserve.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldReturnReserveImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw ivld return reserves.
	 *
	 * @return the vw ivld return reserves
	 */
	@Override
	public List<VwIvldReturnReserve> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw ivld return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld return reserves
	 * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	 * @return the range of vw ivld return reserves
	 */
	@Override
	public List<VwIvldReturnReserve> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw ivld return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld return reserves
	 * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw ivld return reserves
	 */
	@Override
	public List<VwIvldReturnReserve> findAll(int start, int end,
		OrderByComparator<VwIvldReturnReserve> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw ivld return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld return reserves
	 * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw ivld return reserves
	 */
	@Override
	public List<VwIvldReturnReserve> findAll(int start, int end,
		OrderByComparator<VwIvldReturnReserve> orderByComparator,
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

		List<VwIvldReturnReserve> list = null;

		if (retrieveFromCache) {
			list = (List<VwIvldReturnReserve>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWIVLDRETURNRESERVE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWIVLDRETURNRESERVE;

				if (pagination) {
					sql = sql.concat(VwIvldReturnReserveModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwIvldReturnReserve>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwIvldReturnReserve>)QueryUtil.list(q,
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
	 * Removes all the vw ivld return reserves from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwIvldReturnReserve vwIvldReturnReserve : findAll()) {
			remove(vwIvldReturnReserve);
		}
	}

	/**
	 * Returns the number of vw ivld return reserves.
	 *
	 * @return the number of vw ivld return reserves
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWIVLDRETURNRESERVE);

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
		return VwIvldReturnReserveModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw ivld return reserve persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwIvldReturnReserveImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWIVLDRETURNRESERVE = "SELECT vwIvldReturnReserve FROM VwIvldReturnReserve vwIvldReturnReserve";
	private static final String _SQL_SELECT_VWIVLDRETURNRESERVE_WHERE_PKS_IN = "SELECT vwIvldReturnReserve FROM VwIvldReturnReserve vwIvldReturnReserve WHERE IVLD_RETURN_RESERVE_SID IN (";
	private static final String _SQL_COUNT_VWIVLDRETURNRESERVE = "SELECT COUNT(vwIvldReturnReserve) FROM VwIvldReturnReserve vwIvldReturnReserve";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldReturnReserve.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldReturnReserve exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwIvldReturnReservePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ivldReturnReserveSid", "companyName", "year", "project",
				"itemId", "brandName", "modifiedDate", "account",
				"returnReserveIntfId", "source", "createdDate", "createdBy",
				"businessUnit", "businessUnitName", "addChgDelIndicator",
				"errorCode", "intfInsertedDate", "modifiedBy", "itemNo", "month",
				"reprocessedFlag", "udc6", "udc5", "udc4", "udc1", "units",
				"udc2", "udc3", "reasonForFailure", "country", "companyIdString",
				"costCenter", "glCompany", "brandId", "future1", "future2",
				"amount", "division", "companyNo", "batchId", "itemName",
				"errorField", "checkRecord"
			});
}