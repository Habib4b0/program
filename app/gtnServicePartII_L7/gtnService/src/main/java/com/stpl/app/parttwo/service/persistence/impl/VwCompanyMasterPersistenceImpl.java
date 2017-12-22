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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyMasterException;
import com.stpl.app.parttwo.model.VwCompanyMaster;
import com.stpl.app.parttwo.model.impl.VwCompanyMasterImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyMasterPersistence;

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
 * The persistence implementation for the vw company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwCompanyMasterUtil
 * @generated
 */
@ProviderType
public class VwCompanyMasterPersistenceImpl extends BasePersistenceImpl<VwCompanyMaster>
	implements VwCompanyMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwCompanyMasterUtil} to access the vw company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwCompanyMasterPersistenceImpl() {
		setModelClass(VwCompanyMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("state", "STATE");
			dbColumnNames.put("financialSystem", "FINANCIAL_SYSTEM");
			dbColumnNames.put("companyGroup", "COMPANY_GROUP");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("companyCategory", "COMPANY_CATEGORY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("lives", "LIVES");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("address2", "ADDRESS2");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("address1", "ADDRESS1");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("zipCode", "ZIP_CODE");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyType", "COMPANY_TYPE");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyStatus", "COMPANY_STATUS");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("city", "CITY");
			dbColumnNames.put("regionCode", "REGION_CODE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw company master in the entity cache if it is enabled.
	 *
	 * @param vwCompanyMaster the vw company master
	 */
	@Override
	public void cacheResult(VwCompanyMaster vwCompanyMaster) {
		entityCache.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey(),
			vwCompanyMaster);

		vwCompanyMaster.resetOriginalValues();
	}

	/**
	 * Caches the vw company masters in the entity cache if it is enabled.
	 *
	 * @param vwCompanyMasters the vw company masters
	 */
	@Override
	public void cacheResult(List<VwCompanyMaster> vwCompanyMasters) {
		for (VwCompanyMaster vwCompanyMaster : vwCompanyMasters) {
			if (entityCache.getResult(
						VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyMasterImpl.class,
						vwCompanyMaster.getPrimaryKey()) == null) {
				cacheResult(vwCompanyMaster);
			}
			else {
				vwCompanyMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw company masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwCompanyMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw company master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwCompanyMaster vwCompanyMaster) {
		entityCache.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwCompanyMaster> vwCompanyMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwCompanyMaster vwCompanyMaster : vwCompanyMasters) {
			entityCache.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw company master with the primary key. Does not add the vw company master to the database.
	 *
	 * @param companyMasterSid the primary key for the new vw company master
	 * @return the new vw company master
	 */
	@Override
	public VwCompanyMaster create(int companyMasterSid) {
		VwCompanyMaster vwCompanyMaster = new VwCompanyMasterImpl();

		vwCompanyMaster.setNew(true);
		vwCompanyMaster.setPrimaryKey(companyMasterSid);

		vwCompanyMaster.setCompanyId(companyProvider.getCompanyId());

		return vwCompanyMaster;
	}

	/**
	 * Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyMasterSid the primary key of the vw company master
	 * @return the vw company master that was removed
	 * @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster remove(int companyMasterSid)
		throws NoSuchVwCompanyMasterException {
		return remove((Serializable)companyMasterSid);
	}

	/**
	 * Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw company master
	 * @return the vw company master that was removed
	 * @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster remove(Serializable primaryKey)
		throws NoSuchVwCompanyMasterException {
		Session session = null;

		try {
			session = openSession();

			VwCompanyMaster vwCompanyMaster = (VwCompanyMaster)session.get(VwCompanyMasterImpl.class,
					primaryKey);

			if (vwCompanyMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwCompanyMaster);
		}
		catch (NoSuchVwCompanyMasterException nsee) {
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
	protected VwCompanyMaster removeImpl(VwCompanyMaster vwCompanyMaster) {
		vwCompanyMaster = toUnwrappedModel(vwCompanyMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwCompanyMaster)) {
				vwCompanyMaster = (VwCompanyMaster)session.get(VwCompanyMasterImpl.class,
						vwCompanyMaster.getPrimaryKeyObj());
			}

			if (vwCompanyMaster != null) {
				session.delete(vwCompanyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwCompanyMaster != null) {
			clearCache(vwCompanyMaster);
		}

		return vwCompanyMaster;
	}

	@Override
	public VwCompanyMaster updateImpl(VwCompanyMaster vwCompanyMaster) {
		vwCompanyMaster = toUnwrappedModel(vwCompanyMaster);

		boolean isNew = vwCompanyMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwCompanyMaster.isNew()) {
				session.save(vwCompanyMaster);

				vwCompanyMaster.setNew(false);
			}
			else {
				vwCompanyMaster = (VwCompanyMaster)session.merge(vwCompanyMaster);
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

		entityCache.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey(),
			vwCompanyMaster, false);

		vwCompanyMaster.resetOriginalValues();

		return vwCompanyMaster;
	}

	protected VwCompanyMaster toUnwrappedModel(VwCompanyMaster vwCompanyMaster) {
		if (vwCompanyMaster instanceof VwCompanyMasterImpl) {
			return vwCompanyMaster;
		}

		VwCompanyMasterImpl vwCompanyMasterImpl = new VwCompanyMasterImpl();

		vwCompanyMasterImpl.setNew(vwCompanyMaster.isNew());
		vwCompanyMasterImpl.setPrimaryKey(vwCompanyMaster.getPrimaryKey());

		vwCompanyMasterImpl.setState(vwCompanyMaster.getState());
		vwCompanyMasterImpl.setFinancialSystem(vwCompanyMaster.getFinancialSystem());
		vwCompanyMasterImpl.setCompanyGroup(vwCompanyMaster.getCompanyGroup());
		vwCompanyMasterImpl.setCompanyName(vwCompanyMaster.getCompanyName());
		vwCompanyMasterImpl.setLastUpdatedDate(vwCompanyMaster.getLastUpdatedDate());
		vwCompanyMasterImpl.setCompanyCategory(vwCompanyMaster.getCompanyCategory());
		vwCompanyMasterImpl.setModifiedDate(vwCompanyMaster.getModifiedDate());
		vwCompanyMasterImpl.setLives(vwCompanyMaster.getLives());
		vwCompanyMasterImpl.setOrganizationKey(vwCompanyMaster.getOrganizationKey());
		vwCompanyMasterImpl.setAddress2(vwCompanyMaster.getAddress2());
		vwCompanyMasterImpl.setCreatedDate(vwCompanyMaster.getCreatedDate());
		vwCompanyMasterImpl.setCreatedBy(vwCompanyMaster.getCreatedBy());
		vwCompanyMasterImpl.setSource(vwCompanyMaster.getSource());
		vwCompanyMasterImpl.setAddress1(vwCompanyMaster.getAddress1());
		vwCompanyMasterImpl.setAddChgDelIndicator(vwCompanyMaster.getAddChgDelIndicator());
		vwCompanyMasterImpl.setModifiedBy(vwCompanyMaster.getModifiedBy());
		vwCompanyMasterImpl.setUdc6(vwCompanyMaster.getUdc6());
		vwCompanyMasterImpl.setUdc5(vwCompanyMaster.getUdc5());
		vwCompanyMasterImpl.setCompanyMasterSid(vwCompanyMaster.getCompanyMasterSid());
		vwCompanyMasterImpl.setUdc4(vwCompanyMaster.getUdc4());
		vwCompanyMasterImpl.setUdc1(vwCompanyMaster.getUdc1());
		vwCompanyMasterImpl.setZipCode(vwCompanyMaster.getZipCode());
		vwCompanyMasterImpl.setUdc2(vwCompanyMaster.getUdc2());
		vwCompanyMasterImpl.setUdc3(vwCompanyMaster.getUdc3());
		vwCompanyMasterImpl.setCompanyId(vwCompanyMaster.getCompanyId());
		vwCompanyMasterImpl.setCountry(vwCompanyMaster.getCountry());
		vwCompanyMasterImpl.setCompanyType(vwCompanyMaster.getCompanyType());
		vwCompanyMasterImpl.setCompanyStartDate(vwCompanyMaster.getCompanyStartDate());
		vwCompanyMasterImpl.setCompanyNo(vwCompanyMaster.getCompanyNo());
		vwCompanyMasterImpl.setBatchId(vwCompanyMaster.getBatchId());
		vwCompanyMasterImpl.setCompanyStatus(vwCompanyMaster.getCompanyStatus());
		vwCompanyMasterImpl.setCompanyEndDate(vwCompanyMaster.getCompanyEndDate());
		vwCompanyMasterImpl.setCity(vwCompanyMaster.getCity());
		vwCompanyMasterImpl.setRegionCode(vwCompanyMaster.getRegionCode());

		return vwCompanyMasterImpl;
	}

	/**
	 * Returns the vw company master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company master
	 * @return the vw company master
	 * @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwCompanyMasterException {
		VwCompanyMaster vwCompanyMaster = fetchByPrimaryKey(primaryKey);

		if (vwCompanyMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwCompanyMaster;
	}

	/**
	 * Returns the vw company master with the primary key or throws a {@link NoSuchVwCompanyMasterException} if it could not be found.
	 *
	 * @param companyMasterSid the primary key of the vw company master
	 * @return the vw company master
	 * @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster findByPrimaryKey(int companyMasterSid)
		throws NoSuchVwCompanyMasterException {
		return findByPrimaryKey((Serializable)companyMasterSid);
	}

	/**
	 * Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company master
	 * @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwCompanyMaster vwCompanyMaster = (VwCompanyMaster)serializable;

		if (vwCompanyMaster == null) {
			Session session = null;

			try {
				session = openSession();

				vwCompanyMaster = (VwCompanyMaster)session.get(VwCompanyMasterImpl.class,
						primaryKey);

				if (vwCompanyMaster != null) {
					cacheResult(vwCompanyMaster);
				}
				else {
					entityCache.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwCompanyMaster;
	}

	/**
	 * Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyMasterSid the primary key of the vw company master
	 * @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
	 */
	@Override
	public VwCompanyMaster fetchByPrimaryKey(int companyMasterSid) {
		return fetchByPrimaryKey((Serializable)companyMasterSid);
	}

	@Override
	public Map<Serializable, VwCompanyMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwCompanyMaster> map = new HashMap<Serializable, VwCompanyMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwCompanyMaster vwCompanyMaster = fetchByPrimaryKey(primaryKey);

			if (vwCompanyMaster != null) {
				map.put(primaryKey, vwCompanyMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwCompanyMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWCOMPANYMASTER_WHERE_PKS_IN);

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

			for (VwCompanyMaster vwCompanyMaster : (List<VwCompanyMaster>)q.list()) {
				map.put(vwCompanyMaster.getPrimaryKeyObj(), vwCompanyMaster);

				cacheResult(vwCompanyMaster);

				uncachedPrimaryKeys.remove(vwCompanyMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw company masters.
	 *
	 * @return the vw company masters
	 */
	@Override
	public List<VwCompanyMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company masters
	 * @param end the upper bound of the range of vw company masters (not inclusive)
	 * @return the range of vw company masters
	 */
	@Override
	public List<VwCompanyMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company masters
	 * @param end the upper bound of the range of vw company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw company masters
	 */
	@Override
	public List<VwCompanyMaster> findAll(int start, int end,
		OrderByComparator<VwCompanyMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company masters
	 * @param end the upper bound of the range of vw company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw company masters
	 */
	@Override
	public List<VwCompanyMaster> findAll(int start, int end,
		OrderByComparator<VwCompanyMaster> orderByComparator,
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

		List<VwCompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<VwCompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWCOMPANYMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWCOMPANYMASTER;

				if (pagination) {
					sql = sql.concat(VwCompanyMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwCompanyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwCompanyMaster>)QueryUtil.list(q,
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
	 * Removes all the vw company masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwCompanyMaster vwCompanyMaster : findAll()) {
			remove(vwCompanyMaster);
		}
	}

	/**
	 * Returns the number of vw company masters.
	 *
	 * @return the number of vw company masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWCOMPANYMASTER);

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
		return VwCompanyMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw company master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwCompanyMasterImpl.class.getName());
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
	private static final String _SQL_SELECT_VWCOMPANYMASTER = "SELECT vwCompanyMaster FROM VwCompanyMaster vwCompanyMaster";
	private static final String _SQL_SELECT_VWCOMPANYMASTER_WHERE_PKS_IN = "SELECT vwCompanyMaster FROM VwCompanyMaster vwCompanyMaster WHERE COMPANY_MASTER_SID IN (";
	private static final String _SQL_COUNT_VWCOMPANYMASTER = "SELECT COUNT(vwCompanyMaster) FROM VwCompanyMaster vwCompanyMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwCompanyMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"state", "financialSystem", "companyGroup", "companyName",
				"lastUpdatedDate", "companyCategory", "modifiedDate", "lives",
				"organizationKey", "address2", "createdDate", "createdBy",
				"source", "address1", "addChgDelIndicator", "modifiedBy", "udc6",
				"udc5", "companyMasterSid", "udc4", "udc1", "zipCode", "udc2",
				"udc3", "companyId", "country", "companyType",
				"companyStartDate", "companyNo", "batchId", "companyStatus",
				"companyEndDate", "city", "regionCode"
			});
}