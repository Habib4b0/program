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

import com.stpl.app.exception.NoSuchIvldGlCostCenterException;
import com.stpl.app.model.IvldGlCostCenter;
import com.stpl.app.model.impl.IvldGlCostCenterImpl;
import com.stpl.app.model.impl.IvldGlCostCenterModelImpl;
import com.stpl.app.service.persistence.IvldGlCostCenterPersistence;

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
 * The persistence implementation for the ivld gl cost center service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlCostCenterPersistence
 * @see com.stpl.app.service.persistence.IvldGlCostCenterUtil
 * @generated
 */
@ProviderType
public class IvldGlCostCenterPersistenceImpl extends BasePersistenceImpl<IvldGlCostCenter>
	implements IvldGlCostCenterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldGlCostCenterUtil} to access the ivld gl cost center persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldGlCostCenterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED,
			IvldGlCostCenterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED,
			IvldGlCostCenterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldGlCostCenterPersistenceImpl() {
		setModelClass(IvldGlCostCenter.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("glCostCenterIntfid", "GL_COST_CENTER_INTFID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyCostCenter", "COMPANY_COST_CENTER");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("ivldGlCostCenterSid", "IVLD_GL_COST_CENTER_SID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("companyCode", "COMPANY_CODE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("ndc8", "NDC8");
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
	 * Caches the ivld gl cost center in the entity cache if it is enabled.
	 *
	 * @param ivldGlCostCenter the ivld gl cost center
	 */
	@Override
	public void cacheResult(IvldGlCostCenter ivldGlCostCenter) {
		entityCache.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey(),
			ivldGlCostCenter);

		ivldGlCostCenter.resetOriginalValues();
	}

	/**
	 * Caches the ivld gl cost centers in the entity cache if it is enabled.
	 *
	 * @param ivldGlCostCenters the ivld gl cost centers
	 */
	@Override
	public void cacheResult(List<IvldGlCostCenter> ivldGlCostCenters) {
		for (IvldGlCostCenter ivldGlCostCenter : ivldGlCostCenters) {
			if (entityCache.getResult(
						IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
						IvldGlCostCenterImpl.class,
						ivldGlCostCenter.getPrimaryKey()) == null) {
				cacheResult(ivldGlCostCenter);
			}
			else {
				ivldGlCostCenter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld gl cost centers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldGlCostCenterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld gl cost center.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldGlCostCenter ivldGlCostCenter) {
		entityCache.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldGlCostCenter> ivldGlCostCenters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldGlCostCenter ivldGlCostCenter : ivldGlCostCenters) {
			entityCache.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
				IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
	 *
	 * @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
	 * @return the new ivld gl cost center
	 */
	@Override
	public IvldGlCostCenter create(int ivldGlCostCenterSid) {
		IvldGlCostCenter ivldGlCostCenter = new IvldGlCostCenterImpl();

		ivldGlCostCenter.setNew(true);
		ivldGlCostCenter.setPrimaryKey(ivldGlCostCenterSid);

		return ivldGlCostCenter;
	}

	/**
	 * Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	 * @return the ivld gl cost center that was removed
	 * @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter remove(int ivldGlCostCenterSid)
		throws NoSuchIvldGlCostCenterException {
		return remove((Serializable)ivldGlCostCenterSid);
	}

	/**
	 * Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld gl cost center
	 * @return the ivld gl cost center that was removed
	 * @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter remove(Serializable primaryKey)
		throws NoSuchIvldGlCostCenterException {
		Session session = null;

		try {
			session = openSession();

			IvldGlCostCenter ivldGlCostCenter = (IvldGlCostCenter)session.get(IvldGlCostCenterImpl.class,
					primaryKey);

			if (ivldGlCostCenter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldGlCostCenterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldGlCostCenter);
		}
		catch (NoSuchIvldGlCostCenterException nsee) {
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
	protected IvldGlCostCenter removeImpl(IvldGlCostCenter ivldGlCostCenter) {
		ivldGlCostCenter = toUnwrappedModel(ivldGlCostCenter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldGlCostCenter)) {
				ivldGlCostCenter = (IvldGlCostCenter)session.get(IvldGlCostCenterImpl.class,
						ivldGlCostCenter.getPrimaryKeyObj());
			}

			if (ivldGlCostCenter != null) {
				session.delete(ivldGlCostCenter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldGlCostCenter != null) {
			clearCache(ivldGlCostCenter);
		}

		return ivldGlCostCenter;
	}

	@Override
	public IvldGlCostCenter updateImpl(IvldGlCostCenter ivldGlCostCenter) {
		ivldGlCostCenter = toUnwrappedModel(ivldGlCostCenter);

		boolean isNew = ivldGlCostCenter.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldGlCostCenter.isNew()) {
				session.save(ivldGlCostCenter);

				ivldGlCostCenter.setNew(false);
			}
			else {
				ivldGlCostCenter = (IvldGlCostCenter)session.merge(ivldGlCostCenter);
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

		entityCache.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey(),
			ivldGlCostCenter, false);

		ivldGlCostCenter.resetOriginalValues();

		return ivldGlCostCenter;
	}

	protected IvldGlCostCenter toUnwrappedModel(
		IvldGlCostCenter ivldGlCostCenter) {
		if (ivldGlCostCenter instanceof IvldGlCostCenterImpl) {
			return ivldGlCostCenter;
		}

		IvldGlCostCenterImpl ivldGlCostCenterImpl = new IvldGlCostCenterImpl();

		ivldGlCostCenterImpl.setNew(ivldGlCostCenter.isNew());
		ivldGlCostCenterImpl.setPrimaryKey(ivldGlCostCenter.getPrimaryKey());

		ivldGlCostCenterImpl.setReasonForFailure(ivldGlCostCenter.getReasonForFailure());
		ivldGlCostCenterImpl.setGlCostCenterIntfid(ivldGlCostCenter.getGlCostCenterIntfid());
		ivldGlCostCenterImpl.setModifiedDate(ivldGlCostCenter.getModifiedDate());
		ivldGlCostCenterImpl.setCompanyCostCenter(ivldGlCostCenter.getCompanyCostCenter());
		ivldGlCostCenterImpl.setUploadDate(ivldGlCostCenter.getUploadDate());
		ivldGlCostCenterImpl.setCreatedBy(ivldGlCostCenter.getCreatedBy());
		ivldGlCostCenterImpl.setCreatedDate(ivldGlCostCenter.getCreatedDate());
		ivldGlCostCenterImpl.setSource(ivldGlCostCenter.getSource());
		ivldGlCostCenterImpl.setBatchId(ivldGlCostCenter.getBatchId());
		ivldGlCostCenterImpl.setAddChgDelIndicator(ivldGlCostCenter.getAddChgDelIndicator());
		ivldGlCostCenterImpl.setIvldGlCostCenterSid(ivldGlCostCenter.getIvldGlCostCenterSid());
		ivldGlCostCenterImpl.setErrorField(ivldGlCostCenter.getErrorField());
		ivldGlCostCenterImpl.setErrorCode(ivldGlCostCenter.getErrorCode());
		ivldGlCostCenterImpl.setIntfInsertedDate(ivldGlCostCenter.getIntfInsertedDate());
		ivldGlCostCenterImpl.setCompanyCode(ivldGlCostCenter.getCompanyCode());
		ivldGlCostCenterImpl.setModifiedBy(ivldGlCostCenter.getModifiedBy());
		ivldGlCostCenterImpl.setReprocessedFlag(ivldGlCostCenter.getReprocessedFlag());
		ivldGlCostCenterImpl.setNdc8(ivldGlCostCenter.getNdc8());
		ivldGlCostCenterImpl.setCheckRecord(ivldGlCostCenter.isCheckRecord());

		return ivldGlCostCenterImpl;
	}

	/**
	 * Returns the ivld gl cost center with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld gl cost center
	 * @return the ivld gl cost center
	 * @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldGlCostCenterException {
		IvldGlCostCenter ivldGlCostCenter = fetchByPrimaryKey(primaryKey);

		if (ivldGlCostCenter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldGlCostCenterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldGlCostCenter;
	}

	/**
	 * Returns the ivld gl cost center with the primary key or throws a {@link NoSuchIvldGlCostCenterException} if it could not be found.
	 *
	 * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	 * @return the ivld gl cost center
	 * @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter findByPrimaryKey(int ivldGlCostCenterSid)
		throws NoSuchIvldGlCostCenterException {
		return findByPrimaryKey((Serializable)ivldGlCostCenterSid);
	}

	/**
	 * Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld gl cost center
	 * @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
				IvldGlCostCenterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldGlCostCenter ivldGlCostCenter = (IvldGlCostCenter)serializable;

		if (ivldGlCostCenter == null) {
			Session session = null;

			try {
				session = openSession();

				ivldGlCostCenter = (IvldGlCostCenter)session.get(IvldGlCostCenterImpl.class,
						primaryKey);

				if (ivldGlCostCenter != null) {
					cacheResult(ivldGlCostCenter);
				}
				else {
					entityCache.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
						IvldGlCostCenterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlCostCenterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldGlCostCenter;
	}

	/**
	 * Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	 * @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
	 */
	@Override
	public IvldGlCostCenter fetchByPrimaryKey(int ivldGlCostCenterSid) {
		return fetchByPrimaryKey((Serializable)ivldGlCostCenterSid);
	}

	@Override
	public Map<Serializable, IvldGlCostCenter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldGlCostCenter> map = new HashMap<Serializable, IvldGlCostCenter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldGlCostCenter ivldGlCostCenter = fetchByPrimaryKey(primaryKey);

			if (ivldGlCostCenter != null) {
				map.put(primaryKey, ivldGlCostCenter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlCostCenterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldGlCostCenter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDGLCOSTCENTER_WHERE_PKS_IN);

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

			for (IvldGlCostCenter ivldGlCostCenter : (List<IvldGlCostCenter>)q.list()) {
				map.put(ivldGlCostCenter.getPrimaryKeyObj(), ivldGlCostCenter);

				cacheResult(ivldGlCostCenter);

				uncachedPrimaryKeys.remove(ivldGlCostCenter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlCostCenterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld gl cost centers.
	 *
	 * @return the ivld gl cost centers
	 */
	@Override
	public List<IvldGlCostCenter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld gl cost centers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl cost centers
	 * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	 * @return the range of ivld gl cost centers
	 */
	@Override
	public List<IvldGlCostCenter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld gl cost centers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl cost centers
	 * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld gl cost centers
	 */
	@Override
	public List<IvldGlCostCenter> findAll(int start, int end,
		OrderByComparator<IvldGlCostCenter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld gl cost centers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl cost centers
	 * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld gl cost centers
	 */
	@Override
	public List<IvldGlCostCenter> findAll(int start, int end,
		OrderByComparator<IvldGlCostCenter> orderByComparator,
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

		List<IvldGlCostCenter> list = null;

		if (retrieveFromCache) {
			list = (List<IvldGlCostCenter>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDGLCOSTCENTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDGLCOSTCENTER;

				if (pagination) {
					sql = sql.concat(IvldGlCostCenterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldGlCostCenter>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldGlCostCenter>)QueryUtil.list(q,
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
	 * Removes all the ivld gl cost centers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldGlCostCenter ivldGlCostCenter : findAll()) {
			remove(ivldGlCostCenter);
		}
	}

	/**
	 * Returns the number of ivld gl cost centers.
	 *
	 * @return the number of ivld gl cost centers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDGLCOSTCENTER);

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
		return IvldGlCostCenterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld gl cost center persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldGlCostCenterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDGLCOSTCENTER = "SELECT ivldGlCostCenter FROM IvldGlCostCenter ivldGlCostCenter";
	private static final String _SQL_SELECT_IVLDGLCOSTCENTER_WHERE_PKS_IN = "SELECT ivldGlCostCenter FROM IvldGlCostCenter ivldGlCostCenter WHERE IVLD_GL_COST_CENTER_SID IN (";
	private static final String _SQL_COUNT_IVLDGLCOSTCENTER = "SELECT COUNT(ivldGlCostCenter) FROM IvldGlCostCenter ivldGlCostCenter";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldGlCostCenter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldGlCostCenter exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldGlCostCenterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"reasonForFailure", "glCostCenterIntfid", "modifiedDate",
				"companyCostCenter", "uploadDate", "createdBy", "createdDate",
				"source", "batchId", "addChgDelIndicator", "ivldGlCostCenterSid",
				"errorField", "errorCode", "intfInsertedDate", "companyCode",
				"modifiedBy", "reprocessedFlag", "ndc8", "checkRecord"
			});
}