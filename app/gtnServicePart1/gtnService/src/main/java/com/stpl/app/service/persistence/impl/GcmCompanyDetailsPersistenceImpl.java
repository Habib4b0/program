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

import com.stpl.app.exception.NoSuchGcmCompanyDetailsException;
import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.model.impl.GcmCompanyDetailsImpl;
import com.stpl.app.model.impl.GcmCompanyDetailsModelImpl;
import com.stpl.app.service.persistence.GcmCompanyDetailsPersistence;

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
 * The persistence implementation for the gcm company details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyDetailsPersistence
 * @see com.stpl.app.service.persistence.GcmCompanyDetailsUtil
 * @generated
 */
@ProviderType
public class GcmCompanyDetailsPersistenceImpl extends BasePersistenceImpl<GcmCompanyDetails>
	implements GcmCompanyDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GcmCompanyDetailsUtil} to access the gcm company details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GcmCompanyDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmCompanyDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmCompanyDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GcmCompanyDetailsPersistenceImpl() {
		setModelClass(GcmCompanyDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("cfpDetailsTradeClass", "CFP_DETAILS_TRADE_CLASS");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("gcmCompanyDetailsSid", "GCM_COMPANY_DETAILS_SID");
			dbColumnNames.put("itemCfpDetailsSid", "ITEM_CFP_DETAILS_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("companyStatus", "COMPANY_STATUS");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("cfpDetailsStartDate", "CFP_DETAILS_START_DATE");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("subModuleName", "SUB_MODULE_NAME");
			dbColumnNames.put("cfpDetailsEndDate", "CFP_DETAILS_END_DATE");
			dbColumnNames.put("companyStatusSid", "COMPANY_STATUS_SID");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the gcm company details in the entity cache if it is enabled.
	 *
	 * @param gcmCompanyDetails the gcm company details
	 */
	@Override
	public void cacheResult(GcmCompanyDetails gcmCompanyDetails) {
		entityCache.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey(),
			gcmCompanyDetails);

		gcmCompanyDetails.resetOriginalValues();
	}

	/**
	 * Caches the gcm company detailses in the entity cache if it is enabled.
	 *
	 * @param gcmCompanyDetailses the gcm company detailses
	 */
	@Override
	public void cacheResult(List<GcmCompanyDetails> gcmCompanyDetailses) {
		for (GcmCompanyDetails gcmCompanyDetails : gcmCompanyDetailses) {
			if (entityCache.getResult(
						GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmCompanyDetailsImpl.class,
						gcmCompanyDetails.getPrimaryKey()) == null) {
				cacheResult(gcmCompanyDetails);
			}
			else {
				gcmCompanyDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gcm company detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GcmCompanyDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gcm company details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GcmCompanyDetails gcmCompanyDetails) {
		entityCache.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GcmCompanyDetails> gcmCompanyDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GcmCompanyDetails gcmCompanyDetails : gcmCompanyDetailses) {
			entityCache.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gcm company details with the primary key. Does not add the gcm company details to the database.
	 *
	 * @param gcmCompanyDetailsSid the primary key for the new gcm company details
	 * @return the new gcm company details
	 */
	@Override
	public GcmCompanyDetails create(int gcmCompanyDetailsSid) {
		GcmCompanyDetails gcmCompanyDetails = new GcmCompanyDetailsImpl();

		gcmCompanyDetails.setNew(true);
		gcmCompanyDetails.setPrimaryKey(gcmCompanyDetailsSid);

		return gcmCompanyDetails;
	}

	/**
	 * Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gcmCompanyDetailsSid the primary key of the gcm company details
	 * @return the gcm company details that was removed
	 * @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails remove(int gcmCompanyDetailsSid)
		throws NoSuchGcmCompanyDetailsException {
		return remove((Serializable)gcmCompanyDetailsSid);
	}

	/**
	 * Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gcm company details
	 * @return the gcm company details that was removed
	 * @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails remove(Serializable primaryKey)
		throws NoSuchGcmCompanyDetailsException {
		Session session = null;

		try {
			session = openSession();

			GcmCompanyDetails gcmCompanyDetails = (GcmCompanyDetails)session.get(GcmCompanyDetailsImpl.class,
					primaryKey);

			if (gcmCompanyDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGcmCompanyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gcmCompanyDetails);
		}
		catch (NoSuchGcmCompanyDetailsException nsee) {
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
	protected GcmCompanyDetails removeImpl(GcmCompanyDetails gcmCompanyDetails) {
		gcmCompanyDetails = toUnwrappedModel(gcmCompanyDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gcmCompanyDetails)) {
				gcmCompanyDetails = (GcmCompanyDetails)session.get(GcmCompanyDetailsImpl.class,
						gcmCompanyDetails.getPrimaryKeyObj());
			}

			if (gcmCompanyDetails != null) {
				session.delete(gcmCompanyDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gcmCompanyDetails != null) {
			clearCache(gcmCompanyDetails);
		}

		return gcmCompanyDetails;
	}

	@Override
	public GcmCompanyDetails updateImpl(GcmCompanyDetails gcmCompanyDetails) {
		gcmCompanyDetails = toUnwrappedModel(gcmCompanyDetails);

		boolean isNew = gcmCompanyDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (gcmCompanyDetails.isNew()) {
				session.save(gcmCompanyDetails);

				gcmCompanyDetails.setNew(false);
			}
			else {
				gcmCompanyDetails = (GcmCompanyDetails)session.merge(gcmCompanyDetails);
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

		entityCache.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey(),
			gcmCompanyDetails, false);

		gcmCompanyDetails.resetOriginalValues();

		return gcmCompanyDetails;
	}

	protected GcmCompanyDetails toUnwrappedModel(
		GcmCompanyDetails gcmCompanyDetails) {
		if (gcmCompanyDetails instanceof GcmCompanyDetailsImpl) {
			return gcmCompanyDetails;
		}

		GcmCompanyDetailsImpl gcmCompanyDetailsImpl = new GcmCompanyDetailsImpl();

		gcmCompanyDetailsImpl.setNew(gcmCompanyDetails.isNew());
		gcmCompanyDetailsImpl.setPrimaryKey(gcmCompanyDetails.getPrimaryKey());

		gcmCompanyDetailsImpl.setCheckRecord(gcmCompanyDetails.isCheckRecord());
		gcmCompanyDetailsImpl.setUserId(gcmCompanyDetails.getUserId());
		gcmCompanyDetailsImpl.setModuleName(gcmCompanyDetails.getModuleName());
		gcmCompanyDetailsImpl.setCompanyStringId(gcmCompanyDetails.getCompanyStringId());
		gcmCompanyDetailsImpl.setCfpDetailsTradeClass(gcmCompanyDetails.getCfpDetailsTradeClass());
		gcmCompanyDetailsImpl.setCompanyName(gcmCompanyDetails.getCompanyName());
		gcmCompanyDetailsImpl.setModifiedDate(gcmCompanyDetails.getModifiedDate());
		gcmCompanyDetailsImpl.setGcmCompanyDetailsSid(gcmCompanyDetails.getGcmCompanyDetailsSid());
		gcmCompanyDetailsImpl.setItemCfpDetailsSid(gcmCompanyDetails.getItemCfpDetailsSid());
		gcmCompanyDetailsImpl.setCreatedDate(gcmCompanyDetails.getCreatedDate());
		gcmCompanyDetailsImpl.setCreatedBy(gcmCompanyDetails.getCreatedBy());
		gcmCompanyDetailsImpl.setCompanyStartDate(gcmCompanyDetails.getCompanyStartDate());
		gcmCompanyDetailsImpl.setCompanyNo(gcmCompanyDetails.getCompanyNo());
		gcmCompanyDetailsImpl.setCompanyStatus(gcmCompanyDetails.getCompanyStatus());
		gcmCompanyDetailsImpl.setSessionId(gcmCompanyDetails.getSessionId());
		gcmCompanyDetailsImpl.setCompanyEndDate(gcmCompanyDetails.getCompanyEndDate());
		gcmCompanyDetailsImpl.setCfpDetailsStartDate(gcmCompanyDetails.getCfpDetailsStartDate());
		gcmCompanyDetailsImpl.setOperation(gcmCompanyDetails.getOperation());
		gcmCompanyDetailsImpl.setCfpModelSid(gcmCompanyDetails.getCfpModelSid());
		gcmCompanyDetailsImpl.setModifiedBy(gcmCompanyDetails.getModifiedBy());
		gcmCompanyDetailsImpl.setSubModuleName(gcmCompanyDetails.getSubModuleName());
		gcmCompanyDetailsImpl.setCfpDetailsEndDate(gcmCompanyDetails.getCfpDetailsEndDate());
		gcmCompanyDetailsImpl.setCompanyStatusSid(gcmCompanyDetails.getCompanyStatusSid());
		gcmCompanyDetailsImpl.setCompanyMasterSid(gcmCompanyDetails.getCompanyMasterSid());

		return gcmCompanyDetailsImpl;
	}

	/**
	 * Returns the gcm company details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm company details
	 * @return the gcm company details
	 * @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGcmCompanyDetailsException {
		GcmCompanyDetails gcmCompanyDetails = fetchByPrimaryKey(primaryKey);

		if (gcmCompanyDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGcmCompanyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gcmCompanyDetails;
	}

	/**
	 * Returns the gcm company details with the primary key or throws a {@link NoSuchGcmCompanyDetailsException} if it could not be found.
	 *
	 * @param gcmCompanyDetailsSid the primary key of the gcm company details
	 * @return the gcm company details
	 * @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails findByPrimaryKey(int gcmCompanyDetailsSid)
		throws NoSuchGcmCompanyDetailsException {
		return findByPrimaryKey((Serializable)gcmCompanyDetailsSid);
	}

	/**
	 * Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm company details
	 * @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmCompanyDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GcmCompanyDetails gcmCompanyDetails = (GcmCompanyDetails)serializable;

		if (gcmCompanyDetails == null) {
			Session session = null;

			try {
				session = openSession();

				gcmCompanyDetails = (GcmCompanyDetails)session.get(GcmCompanyDetailsImpl.class,
						primaryKey);

				if (gcmCompanyDetails != null) {
					cacheResult(gcmCompanyDetails);
				}
				else {
					entityCache.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmCompanyDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gcmCompanyDetails;
	}

	/**
	 * Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gcmCompanyDetailsSid the primary key of the gcm company details
	 * @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
	 */
	@Override
	public GcmCompanyDetails fetchByPrimaryKey(int gcmCompanyDetailsSid) {
		return fetchByPrimaryKey((Serializable)gcmCompanyDetailsSid);
	}

	@Override
	public Map<Serializable, GcmCompanyDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GcmCompanyDetails> map = new HashMap<Serializable, GcmCompanyDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GcmCompanyDetails gcmCompanyDetails = fetchByPrimaryKey(primaryKey);

			if (gcmCompanyDetails != null) {
				map.put(primaryKey, gcmCompanyDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GcmCompanyDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GCMCOMPANYDETAILS_WHERE_PKS_IN);

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

			for (GcmCompanyDetails gcmCompanyDetails : (List<GcmCompanyDetails>)q.list()) {
				map.put(gcmCompanyDetails.getPrimaryKeyObj(), gcmCompanyDetails);

				cacheResult(gcmCompanyDetails);

				uncachedPrimaryKeys.remove(gcmCompanyDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmCompanyDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the gcm company detailses.
	 *
	 * @return the gcm company detailses
	 */
	@Override
	public List<GcmCompanyDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gcm company detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company detailses
	 * @param end the upper bound of the range of gcm company detailses (not inclusive)
	 * @return the range of gcm company detailses
	 */
	@Override
	public List<GcmCompanyDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gcm company detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company detailses
	 * @param end the upper bound of the range of gcm company detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gcm company detailses
	 */
	@Override
	public List<GcmCompanyDetails> findAll(int start, int end,
		OrderByComparator<GcmCompanyDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gcm company detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm company detailses
	 * @param end the upper bound of the range of gcm company detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of gcm company detailses
	 */
	@Override
	public List<GcmCompanyDetails> findAll(int start, int end,
		OrderByComparator<GcmCompanyDetails> orderByComparator,
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

		List<GcmCompanyDetails> list = null;

		if (retrieveFromCache) {
			list = (List<GcmCompanyDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GCMCOMPANYDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GCMCOMPANYDETAILS;

				if (pagination) {
					sql = sql.concat(GcmCompanyDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GcmCompanyDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GcmCompanyDetails>)QueryUtil.list(q,
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
	 * Removes all the gcm company detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GcmCompanyDetails gcmCompanyDetails : findAll()) {
			remove(gcmCompanyDetails);
		}
	}

	/**
	 * Returns the number of gcm company detailses.
	 *
	 * @return the number of gcm company detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GCMCOMPANYDETAILS);

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
		return GcmCompanyDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gcm company details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GcmCompanyDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GCMCOMPANYDETAILS = "SELECT gcmCompanyDetails FROM GcmCompanyDetails gcmCompanyDetails";
	private static final String _SQL_SELECT_GCMCOMPANYDETAILS_WHERE_PKS_IN = "SELECT gcmCompanyDetails FROM GcmCompanyDetails gcmCompanyDetails WHERE GCM_COMPANY_DETAILS_SID IN (";
	private static final String _SQL_COUNT_GCMCOMPANYDETAILS = "SELECT COUNT(gcmCompanyDetails) FROM GcmCompanyDetails gcmCompanyDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gcmCompanyDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmCompanyDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GcmCompanyDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "userId", "moduleName", "companyStringId",
				"cfpDetailsTradeClass", "companyName", "modifiedDate",
				"gcmCompanyDetailsSid", "itemCfpDetailsSid", "createdDate",
				"createdBy", "companyStartDate", "companyNo", "companyStatus",
				"sessionId", "companyEndDate", "cfpDetailsStartDate",
				"operation", "cfpModelSid", "modifiedBy", "subModuleName",
				"cfpDetailsEndDate", "companyStatusSid", "companyMasterSid"
			});
}