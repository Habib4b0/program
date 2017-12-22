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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyMasterException;
import com.stpl.app.parttwo.model.IvldCompanyMaster;
import com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyMasterPersistence;

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
 * The persistence implementation for the ivld company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCompanyMasterUtil
 * @generated
 */
@ProviderType
public class IvldCompanyMasterPersistenceImpl extends BasePersistenceImpl<IvldCompanyMaster>
	implements IvldCompanyMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCompanyMasterUtil} to access the ivld company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCompanyMasterPersistenceImpl() {
		setModelClass(IvldCompanyMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("state", "STATE");
			dbColumnNames.put("financialSystem", "FINANCIAL_SYSTEM");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("companyGroup", "COMPANY_GROUP");
			dbColumnNames.put("companyCategory", "COMPANY_CATEGORY");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("ivldCompanyMasterSid", "IVLD_COMPANY_MASTER_SID");
			dbColumnNames.put("lives", "LIVES");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("companyMasterIntfid", "COMPANY_MASTER_INTFID");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("zipCode", "ZIP_CODE");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("address1", "ADDRESS_1");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("address2", "ADDRESS_2");
			dbColumnNames.put("companyType", "COMPANY_TYPE");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyStatus", "COMPANY_STATUS");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("city", "CITY");
			dbColumnNames.put("regionCode", "REGION_CODE");
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
	 * Caches the ivld company master in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyMaster the ivld company master
	 */
	@Override
	public void cacheResult(IvldCompanyMaster ivldCompanyMaster) {
		entityCache.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey(),
			ivldCompanyMaster);

		ivldCompanyMaster.resetOriginalValues();
	}

	/**
	 * Caches the ivld company masters in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyMasters the ivld company masters
	 */
	@Override
	public void cacheResult(List<IvldCompanyMaster> ivldCompanyMasters) {
		for (IvldCompanyMaster ivldCompanyMaster : ivldCompanyMasters) {
			if (entityCache.getResult(
						IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyMasterImpl.class,
						ivldCompanyMaster.getPrimaryKey()) == null) {
				cacheResult(ivldCompanyMaster);
			}
			else {
				ivldCompanyMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld company masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCompanyMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld company master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCompanyMaster ivldCompanyMaster) {
		entityCache.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCompanyMaster> ivldCompanyMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCompanyMaster ivldCompanyMaster : ivldCompanyMasters) {
			entityCache.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
	 *
	 * @param ivldCompanyMasterSid the primary key for the new ivld company master
	 * @return the new ivld company master
	 */
	@Override
	public IvldCompanyMaster create(int ivldCompanyMasterSid) {
		IvldCompanyMaster ivldCompanyMaster = new IvldCompanyMasterImpl();

		ivldCompanyMaster.setNew(true);
		ivldCompanyMaster.setPrimaryKey(ivldCompanyMasterSid);

		ivldCompanyMaster.setCompanyId(companyProvider.getCompanyId());

		return ivldCompanyMaster;
	}

	/**
	 * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCompanyMasterSid the primary key of the ivld company master
	 * @return the ivld company master that was removed
	 * @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster remove(int ivldCompanyMasterSid)
		throws NoSuchIvldCompanyMasterException {
		return remove((Serializable)ivldCompanyMasterSid);
	}

	/**
	 * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld company master
	 * @return the ivld company master that was removed
	 * @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster remove(Serializable primaryKey)
		throws NoSuchIvldCompanyMasterException {
		Session session = null;

		try {
			session = openSession();

			IvldCompanyMaster ivldCompanyMaster = (IvldCompanyMaster)session.get(IvldCompanyMasterImpl.class,
					primaryKey);

			if (ivldCompanyMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCompanyMaster);
		}
		catch (NoSuchIvldCompanyMasterException nsee) {
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
	protected IvldCompanyMaster removeImpl(IvldCompanyMaster ivldCompanyMaster) {
		ivldCompanyMaster = toUnwrappedModel(ivldCompanyMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCompanyMaster)) {
				ivldCompanyMaster = (IvldCompanyMaster)session.get(IvldCompanyMasterImpl.class,
						ivldCompanyMaster.getPrimaryKeyObj());
			}

			if (ivldCompanyMaster != null) {
				session.delete(ivldCompanyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCompanyMaster != null) {
			clearCache(ivldCompanyMaster);
		}

		return ivldCompanyMaster;
	}

	@Override
	public IvldCompanyMaster updateImpl(IvldCompanyMaster ivldCompanyMaster) {
		ivldCompanyMaster = toUnwrappedModel(ivldCompanyMaster);

		boolean isNew = ivldCompanyMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCompanyMaster.isNew()) {
				session.save(ivldCompanyMaster);

				ivldCompanyMaster.setNew(false);
			}
			else {
				ivldCompanyMaster = (IvldCompanyMaster)session.merge(ivldCompanyMaster);
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

		entityCache.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey(),
			ivldCompanyMaster, false);

		ivldCompanyMaster.resetOriginalValues();

		return ivldCompanyMaster;
	}

	protected IvldCompanyMaster toUnwrappedModel(
		IvldCompanyMaster ivldCompanyMaster) {
		if (ivldCompanyMaster instanceof IvldCompanyMasterImpl) {
			return ivldCompanyMaster;
		}

		IvldCompanyMasterImpl ivldCompanyMasterImpl = new IvldCompanyMasterImpl();

		ivldCompanyMasterImpl.setNew(ivldCompanyMaster.isNew());
		ivldCompanyMasterImpl.setPrimaryKey(ivldCompanyMaster.getPrimaryKey());

		ivldCompanyMasterImpl.setState(ivldCompanyMaster.getState());
		ivldCompanyMasterImpl.setFinancialSystem(ivldCompanyMaster.getFinancialSystem());
		ivldCompanyMasterImpl.setCompanyName(ivldCompanyMaster.getCompanyName());
		ivldCompanyMasterImpl.setCompanyGroup(ivldCompanyMaster.getCompanyGroup());
		ivldCompanyMasterImpl.setCompanyCategory(ivldCompanyMaster.getCompanyCategory());
		ivldCompanyMasterImpl.setLastUpdatedDate(ivldCompanyMaster.getLastUpdatedDate());
		ivldCompanyMasterImpl.setModifiedDate(ivldCompanyMaster.getModifiedDate());
		ivldCompanyMasterImpl.setStatus(ivldCompanyMaster.getStatus());
		ivldCompanyMasterImpl.setIvldCompanyMasterSid(ivldCompanyMaster.getIvldCompanyMasterSid());
		ivldCompanyMasterImpl.setLives(ivldCompanyMaster.getLives());
		ivldCompanyMasterImpl.setOrganizationKey(ivldCompanyMaster.getOrganizationKey());
		ivldCompanyMasterImpl.setSource(ivldCompanyMaster.getSource());
		ivldCompanyMasterImpl.setCreatedDate(ivldCompanyMaster.getCreatedDate());
		ivldCompanyMasterImpl.setCreatedBy(ivldCompanyMaster.getCreatedBy());
		ivldCompanyMasterImpl.setAddChgDelIndicator(ivldCompanyMaster.getAddChgDelIndicator());
		ivldCompanyMasterImpl.setErrorCode(ivldCompanyMaster.getErrorCode());
		ivldCompanyMasterImpl.setIntfInsertedDate(ivldCompanyMaster.getIntfInsertedDate());
		ivldCompanyMasterImpl.setModifiedBy(ivldCompanyMaster.getModifiedBy());
		ivldCompanyMasterImpl.setCompanyMasterIntfid(ivldCompanyMaster.getCompanyMasterIntfid());
		ivldCompanyMasterImpl.setReprocessedFlag(ivldCompanyMaster.getReprocessedFlag());
		ivldCompanyMasterImpl.setUdc6(ivldCompanyMaster.getUdc6());
		ivldCompanyMasterImpl.setUdc5(ivldCompanyMaster.getUdc5());
		ivldCompanyMasterImpl.setUdc4(ivldCompanyMaster.getUdc4());
		ivldCompanyMasterImpl.setUdc1(ivldCompanyMaster.getUdc1());
		ivldCompanyMasterImpl.setUdc2(ivldCompanyMaster.getUdc2());
		ivldCompanyMasterImpl.setZipCode(ivldCompanyMaster.getZipCode());
		ivldCompanyMasterImpl.setUdc3(ivldCompanyMaster.getUdc3());
		ivldCompanyMasterImpl.setReasonForFailure(ivldCompanyMaster.getReasonForFailure());
		ivldCompanyMasterImpl.setCompanyId(ivldCompanyMaster.getCompanyId());
		ivldCompanyMasterImpl.setAddress1(ivldCompanyMaster.getAddress1());
		ivldCompanyMasterImpl.setCountry(ivldCompanyMaster.getCountry());
		ivldCompanyMasterImpl.setAddress2(ivldCompanyMaster.getAddress2());
		ivldCompanyMasterImpl.setCompanyType(ivldCompanyMaster.getCompanyType());
		ivldCompanyMasterImpl.setCompanyStartDate(ivldCompanyMaster.getCompanyStartDate());
		ivldCompanyMasterImpl.setCompanyNo(ivldCompanyMaster.getCompanyNo());
		ivldCompanyMasterImpl.setBatchId(ivldCompanyMaster.getBatchId());
		ivldCompanyMasterImpl.setCompanyStatus(ivldCompanyMaster.getCompanyStatus());
		ivldCompanyMasterImpl.setCompanyEndDate(ivldCompanyMaster.getCompanyEndDate());
		ivldCompanyMasterImpl.setErrorField(ivldCompanyMaster.getErrorField());
		ivldCompanyMasterImpl.setCity(ivldCompanyMaster.getCity());
		ivldCompanyMasterImpl.setRegionCode(ivldCompanyMaster.getRegionCode());
		ivldCompanyMasterImpl.setCheckRecord(ivldCompanyMaster.isCheckRecord());

		return ivldCompanyMasterImpl;
	}

	/**
	 * Returns the ivld company master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company master
	 * @return the ivld company master
	 * @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCompanyMasterException {
		IvldCompanyMaster ivldCompanyMaster = fetchByPrimaryKey(primaryKey);

		if (ivldCompanyMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCompanyMaster;
	}

	/**
	 * Returns the ivld company master with the primary key or throws a {@link NoSuchIvldCompanyMasterException} if it could not be found.
	 *
	 * @param ivldCompanyMasterSid the primary key of the ivld company master
	 * @return the ivld company master
	 * @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster findByPrimaryKey(int ivldCompanyMasterSid)
		throws NoSuchIvldCompanyMasterException {
		return findByPrimaryKey((Serializable)ivldCompanyMasterSid);
	}

	/**
	 * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company master
	 * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCompanyMaster ivldCompanyMaster = (IvldCompanyMaster)serializable;

		if (ivldCompanyMaster == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCompanyMaster = (IvldCompanyMaster)session.get(IvldCompanyMasterImpl.class,
						primaryKey);

				if (ivldCompanyMaster != null) {
					cacheResult(ivldCompanyMaster);
				}
				else {
					entityCache.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCompanyMaster;
	}

	/**
	 * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCompanyMasterSid the primary key of the ivld company master
	 * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
	 */
	@Override
	public IvldCompanyMaster fetchByPrimaryKey(int ivldCompanyMasterSid) {
		return fetchByPrimaryKey((Serializable)ivldCompanyMasterSid);
	}

	@Override
	public Map<Serializable, IvldCompanyMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCompanyMaster> map = new HashMap<Serializable, IvldCompanyMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCompanyMaster ivldCompanyMaster = fetchByPrimaryKey(primaryKey);

			if (ivldCompanyMaster != null) {
				map.put(primaryKey, ivldCompanyMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCompanyMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCOMPANYMASTER_WHERE_PKS_IN);

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

			for (IvldCompanyMaster ivldCompanyMaster : (List<IvldCompanyMaster>)q.list()) {
				map.put(ivldCompanyMaster.getPrimaryKeyObj(), ivldCompanyMaster);

				cacheResult(ivldCompanyMaster);

				uncachedPrimaryKeys.remove(ivldCompanyMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld company masters.
	 *
	 * @return the ivld company masters
	 */
	@Override
	public List<IvldCompanyMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company masters
	 * @param end the upper bound of the range of ivld company masters (not inclusive)
	 * @return the range of ivld company masters
	 */
	@Override
	public List<IvldCompanyMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company masters
	 * @param end the upper bound of the range of ivld company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld company masters
	 */
	@Override
	public List<IvldCompanyMaster> findAll(int start, int end,
		OrderByComparator<IvldCompanyMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company masters
	 * @param end the upper bound of the range of ivld company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld company masters
	 */
	@Override
	public List<IvldCompanyMaster> findAll(int start, int end,
		OrderByComparator<IvldCompanyMaster> orderByComparator,
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

		List<IvldCompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCOMPANYMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCOMPANYMASTER;

				if (pagination) {
					sql = sql.concat(IvldCompanyMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCompanyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCompanyMaster>)QueryUtil.list(q,
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
	 * Removes all the ivld company masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCompanyMaster ivldCompanyMaster : findAll()) {
			remove(ivldCompanyMaster);
		}
	}

	/**
	 * Returns the number of ivld company masters.
	 *
	 * @return the number of ivld company masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYMASTER);

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
		return IvldCompanyMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld company master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCompanyMasterImpl.class.getName());
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
	private static final String _SQL_SELECT_IVLDCOMPANYMASTER = "SELECT ivldCompanyMaster FROM IvldCompanyMaster ivldCompanyMaster";
	private static final String _SQL_SELECT_IVLDCOMPANYMASTER_WHERE_PKS_IN = "SELECT ivldCompanyMaster FROM IvldCompanyMaster ivldCompanyMaster WHERE IVLD_COMPANY_MASTER_SID IN (";
	private static final String _SQL_COUNT_IVLDCOMPANYMASTER = "SELECT COUNT(ivldCompanyMaster) FROM IvldCompanyMaster ivldCompanyMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCompanyMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"state", "financialSystem", "companyName", "companyGroup",
				"companyCategory", "lastUpdatedDate", "modifiedDate", "status",
				"ivldCompanyMasterSid", "lives", "organizationKey", "source",
				"createdDate", "createdBy", "addChgDelIndicator", "errorCode",
				"intfInsertedDate", "modifiedBy", "companyMasterIntfid",
				"reprocessedFlag", "udc6", "udc5", "udc4", "udc1", "udc2",
				"zipCode", "udc3", "reasonForFailure", "companyId", "address1",
				"country", "address2", "companyType", "companyStartDate",
				"companyNo", "batchId", "companyStatus", "companyEndDate",
				"errorField", "city", "regionCode", "checkRecord"
			});
}