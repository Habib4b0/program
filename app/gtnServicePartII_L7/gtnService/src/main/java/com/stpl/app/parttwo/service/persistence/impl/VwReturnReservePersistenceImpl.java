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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchVwReturnReserveException;
import com.stpl.app.parttwo.model.VwReturnReserve;
import com.stpl.app.parttwo.model.impl.VwReturnReserveImpl;
import com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl;
import com.stpl.app.parttwo.service.persistence.VwReturnReservePersistence;

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
 * The persistence implementation for the vw return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwReturnReservePersistence
 * @see com.stpl.app.parttwo.service.persistence.VwReturnReserveUtil
 * @generated
 */
@ProviderType
public class VwReturnReservePersistenceImpl extends BasePersistenceImpl<VwReturnReserve>
	implements VwReturnReservePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwReturnReserveUtil} to access the vw return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwReturnReserveImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveModelImpl.FINDER_CACHE_ENABLED,
			VwReturnReserveImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveModelImpl.FINDER_CACHE_ENABLED,
			VwReturnReserveImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwReturnReservePersistenceImpl() {
		setModelClass(VwReturnReserve.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("project", "PROJECT");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("returnReserveIntfId", "RETURN_RESERVE_INTF_ID");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("businessUnit", "BUSINESS_UNIT");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("buCompanyMasterSid", "BU_COMPANY_MASTER_SID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("glCompany", "GL_COMPANY");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("future1", "FUTURE1");
			dbColumnNames.put("future2", "FUTURE2");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("division", "DIVISION");
			dbColumnNames.put("returnReserveSid", "RETURN_RESERVE_SID");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw return reserve in the entity cache if it is enabled.
	 *
	 * @param vwReturnReserve the vw return reserve
	 */
	@Override
	public void cacheResult(VwReturnReserve vwReturnReserve) {
		entityCache.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey(),
			vwReturnReserve);

		vwReturnReserve.resetOriginalValues();
	}

	/**
	 * Caches the vw return reserves in the entity cache if it is enabled.
	 *
	 * @param vwReturnReserves the vw return reserves
	 */
	@Override
	public void cacheResult(List<VwReturnReserve> vwReturnReserves) {
		for (VwReturnReserve vwReturnReserve : vwReturnReserves) {
			if (entityCache.getResult(
						VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
						VwReturnReserveImpl.class,
						vwReturnReserve.getPrimaryKey()) == null) {
				cacheResult(vwReturnReserve);
			}
			else {
				vwReturnReserve.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw return reserves.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwReturnReserveImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw return reserve.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwReturnReserve vwReturnReserve) {
		entityCache.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwReturnReserve> vwReturnReserves) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwReturnReserve vwReturnReserve : vwReturnReserves) {
			entityCache.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
				VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw return reserve with the primary key. Does not add the vw return reserve to the database.
	 *
	 * @param returnReserveSid the primary key for the new vw return reserve
	 * @return the new vw return reserve
	 */
	@Override
	public VwReturnReserve create(int returnReserveSid) {
		VwReturnReserve vwReturnReserve = new VwReturnReserveImpl();

		vwReturnReserve.setNew(true);
		vwReturnReserve.setPrimaryKey(returnReserveSid);

		vwReturnReserve.setCompanyId(companyProvider.getCompanyId());

		return vwReturnReserve;
	}

	/**
	 * Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param returnReserveSid the primary key of the vw return reserve
	 * @return the vw return reserve that was removed
	 * @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve remove(int returnReserveSid)
		throws NoSuchVwReturnReserveException {
		return remove((Serializable)returnReserveSid);
	}

	/**
	 * Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw return reserve
	 * @return the vw return reserve that was removed
	 * @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve remove(Serializable primaryKey)
		throws NoSuchVwReturnReserveException {
		Session session = null;

		try {
			session = openSession();

			VwReturnReserve vwReturnReserve = (VwReturnReserve)session.get(VwReturnReserveImpl.class,
					primaryKey);

			if (vwReturnReserve == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwReturnReserve);
		}
		catch (NoSuchVwReturnReserveException nsee) {
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
	protected VwReturnReserve removeImpl(VwReturnReserve vwReturnReserve) {
		vwReturnReserve = toUnwrappedModel(vwReturnReserve);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwReturnReserve)) {
				vwReturnReserve = (VwReturnReserve)session.get(VwReturnReserveImpl.class,
						vwReturnReserve.getPrimaryKeyObj());
			}

			if (vwReturnReserve != null) {
				session.delete(vwReturnReserve);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwReturnReserve != null) {
			clearCache(vwReturnReserve);
		}

		return vwReturnReserve;
	}

	@Override
	public VwReturnReserve updateImpl(VwReturnReserve vwReturnReserve) {
		vwReturnReserve = toUnwrappedModel(vwReturnReserve);

		boolean isNew = vwReturnReserve.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwReturnReserve.isNew()) {
				session.save(vwReturnReserve);

				vwReturnReserve.setNew(false);
			}
			else {
				vwReturnReserve = (VwReturnReserve)session.merge(vwReturnReserve);
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

		entityCache.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
			VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey(),
			vwReturnReserve, false);

		vwReturnReserve.resetOriginalValues();

		return vwReturnReserve;
	}

	protected VwReturnReserve toUnwrappedModel(VwReturnReserve vwReturnReserve) {
		if (vwReturnReserve instanceof VwReturnReserveImpl) {
			return vwReturnReserve;
		}

		VwReturnReserveImpl vwReturnReserveImpl = new VwReturnReserveImpl();

		vwReturnReserveImpl.setNew(vwReturnReserve.isNew());
		vwReturnReserveImpl.setPrimaryKey(vwReturnReserve.getPrimaryKey());

		vwReturnReserveImpl.setItemMasterSid(vwReturnReserve.getItemMasterSid());
		vwReturnReserveImpl.setCompanyName(vwReturnReserve.getCompanyName());
		vwReturnReserveImpl.setProject(vwReturnReserve.getProject());
		vwReturnReserveImpl.setYear(vwReturnReserve.getYear());
		vwReturnReserveImpl.setItemId(vwReturnReserve.getItemId());
		vwReturnReserveImpl.setBrandName(vwReturnReserve.getBrandName());
		vwReturnReserveImpl.setModifiedDate(vwReturnReserve.getModifiedDate());
		vwReturnReserveImpl.setBrandMasterSid(vwReturnReserve.getBrandMasterSid());
		vwReturnReserveImpl.setAccount(vwReturnReserve.getAccount());
		vwReturnReserveImpl.setReturnReserveIntfId(vwReturnReserve.getReturnReserveIntfId());
		vwReturnReserveImpl.setGlCompanyMasterSid(vwReturnReserve.getGlCompanyMasterSid());
		vwReturnReserveImpl.setSource(vwReturnReserve.getSource());
		vwReturnReserveImpl.setCreatedDate(vwReturnReserve.getCreatedDate());
		vwReturnReserveImpl.setCreatedBy(vwReturnReserve.getCreatedBy());
		vwReturnReserveImpl.setBusinessUnit(vwReturnReserve.getBusinessUnit());
		vwReturnReserveImpl.setBusinessUnitName(vwReturnReserve.getBusinessUnitName());
		vwReturnReserveImpl.setBuCompanyMasterSid(vwReturnReserve.getBuCompanyMasterSid());
		vwReturnReserveImpl.setInboundStatus(vwReturnReserve.getInboundStatus());
		vwReturnReserveImpl.setModifiedBy(vwReturnReserve.getModifiedBy());
		vwReturnReserveImpl.setItemNo(vwReturnReserve.getItemNo());
		vwReturnReserveImpl.setMonth(vwReturnReserve.getMonth());
		vwReturnReserveImpl.setUdc6(vwReturnReserve.getUdc6());
		vwReturnReserveImpl.setUdc5(vwReturnReserve.getUdc5());
		vwReturnReserveImpl.setUdc4(vwReturnReserve.getUdc4());
		vwReturnReserveImpl.setUdc1(vwReturnReserve.getUdc1());
		vwReturnReserveImpl.setUnits(vwReturnReserve.getUnits());
		vwReturnReserveImpl.setUdc2(vwReturnReserve.getUdc2());
		vwReturnReserveImpl.setUdc3(vwReturnReserve.getUdc3());
		vwReturnReserveImpl.setCountry(vwReturnReserve.getCountry());
		vwReturnReserveImpl.setCompanyId(vwReturnReserve.getCompanyId());
		vwReturnReserveImpl.setCostCenter(vwReturnReserve.getCostCenter());
		vwReturnReserveImpl.setGlCompany(vwReturnReserve.getGlCompany());
		vwReturnReserveImpl.setBrandId(vwReturnReserve.getBrandId());
		vwReturnReserveImpl.setFuture1(vwReturnReserve.getFuture1());
		vwReturnReserveImpl.setFuture2(vwReturnReserve.getFuture2());
		vwReturnReserveImpl.setAmount(vwReturnReserve.getAmount());
		vwReturnReserveImpl.setRecordLockStatus(vwReturnReserve.isRecordLockStatus());
		vwReturnReserveImpl.setDivision(vwReturnReserve.getDivision());
		vwReturnReserveImpl.setReturnReserveSid(vwReturnReserve.getReturnReserveSid());
		vwReturnReserveImpl.setCompanyNo(vwReturnReserve.getCompanyNo());
		vwReturnReserveImpl.setBatchId(vwReturnReserve.getBatchId());
		vwReturnReserveImpl.setItemName(vwReturnReserve.getItemName());

		return vwReturnReserveImpl;
	}

	/**
	 * Returns the vw return reserve with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw return reserve
	 * @return the vw return reserve
	 * @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwReturnReserveException {
		VwReturnReserve vwReturnReserve = fetchByPrimaryKey(primaryKey);

		if (vwReturnReserve == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwReturnReserve;
	}

	/**
	 * Returns the vw return reserve with the primary key or throws a {@link NoSuchVwReturnReserveException} if it could not be found.
	 *
	 * @param returnReserveSid the primary key of the vw return reserve
	 * @return the vw return reserve
	 * @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve findByPrimaryKey(int returnReserveSid)
		throws NoSuchVwReturnReserveException {
		return findByPrimaryKey((Serializable)returnReserveSid);
	}

	/**
	 * Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw return reserve
	 * @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
				VwReturnReserveImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwReturnReserve vwReturnReserve = (VwReturnReserve)serializable;

		if (vwReturnReserve == null) {
			Session session = null;

			try {
				session = openSession();

				vwReturnReserve = (VwReturnReserve)session.get(VwReturnReserveImpl.class,
						primaryKey);

				if (vwReturnReserve != null) {
					cacheResult(vwReturnReserve);
				}
				else {
					entityCache.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
						VwReturnReserveImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwReturnReserveImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwReturnReserve;
	}

	/**
	 * Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param returnReserveSid the primary key of the vw return reserve
	 * @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
	 */
	@Override
	public VwReturnReserve fetchByPrimaryKey(int returnReserveSid) {
		return fetchByPrimaryKey((Serializable)returnReserveSid);
	}

	@Override
	public Map<Serializable, VwReturnReserve> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwReturnReserve> map = new HashMap<Serializable, VwReturnReserve>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwReturnReserve vwReturnReserve = fetchByPrimaryKey(primaryKey);

			if (vwReturnReserve != null) {
				map.put(primaryKey, vwReturnReserve);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwReturnReserveImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwReturnReserve)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWRETURNRESERVE_WHERE_PKS_IN);

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

			for (VwReturnReserve vwReturnReserve : (List<VwReturnReserve>)q.list()) {
				map.put(vwReturnReserve.getPrimaryKeyObj(), vwReturnReserve);

				cacheResult(vwReturnReserve);

				uncachedPrimaryKeys.remove(vwReturnReserve.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
					VwReturnReserveImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw return reserves.
	 *
	 * @return the vw return reserves
	 */
	@Override
	public List<VwReturnReserve> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw return reserves
	 * @param end the upper bound of the range of vw return reserves (not inclusive)
	 * @return the range of vw return reserves
	 */
	@Override
	public List<VwReturnReserve> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw return reserves
	 * @param end the upper bound of the range of vw return reserves (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw return reserves
	 */
	@Override
	public List<VwReturnReserve> findAll(int start, int end,
		OrderByComparator<VwReturnReserve> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw return reserves.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw return reserves
	 * @param end the upper bound of the range of vw return reserves (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw return reserves
	 */
	@Override
	public List<VwReturnReserve> findAll(int start, int end,
		OrderByComparator<VwReturnReserve> orderByComparator,
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

		List<VwReturnReserve> list = null;

		if (retrieveFromCache) {
			list = (List<VwReturnReserve>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWRETURNRESERVE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWRETURNRESERVE;

				if (pagination) {
					sql = sql.concat(VwReturnReserveModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwReturnReserve>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwReturnReserve>)QueryUtil.list(q,
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
	 * Removes all the vw return reserves from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwReturnReserve vwReturnReserve : findAll()) {
			remove(vwReturnReserve);
		}
	}

	/**
	 * Returns the number of vw return reserves.
	 *
	 * @return the number of vw return reserves
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWRETURNRESERVE);

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
		return VwReturnReserveModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw return reserve persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwReturnReserveImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWRETURNRESERVE = "SELECT vwReturnReserve FROM VwReturnReserve vwReturnReserve";
	private static final String _SQL_SELECT_VWRETURNRESERVE_WHERE_PKS_IN = "SELECT vwReturnReserve FROM VwReturnReserve vwReturnReserve WHERE RETURN_RESERVE_SID IN (";
	private static final String _SQL_COUNT_VWRETURNRESERVE = "SELECT COUNT(vwReturnReserve) FROM VwReturnReserve vwReturnReserve";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwReturnReserve.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwReturnReserve exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwReturnReservePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "companyName", "project", "year", "itemId",
				"brandName", "modifiedDate", "brandMasterSid", "account",
				"returnReserveIntfId", "glCompanyMasterSid", "source",
				"createdDate", "createdBy", "businessUnit", "businessUnitName",
				"buCompanyMasterSid", "inboundStatus", "modifiedBy", "itemNo",
				"month", "udc6", "udc5", "udc4", "udc1", "units", "udc2", "udc3",
				"country", "companyId", "costCenter", "glCompany", "brandId",
				"future1", "future2", "amount", "recordLockStatus", "division",
				"returnReserveSid", "companyNo", "batchId", "itemName"
			});
}