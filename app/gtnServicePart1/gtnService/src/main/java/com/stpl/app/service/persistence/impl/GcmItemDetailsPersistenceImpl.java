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

import com.stpl.app.exception.NoSuchGcmItemDetailsException;
import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.model.impl.GcmItemDetailsImpl;
import com.stpl.app.model.impl.GcmItemDetailsModelImpl;
import com.stpl.app.service.persistence.GcmItemDetailsPersistence;

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
 * The persistence implementation for the gcm item details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmItemDetailsPersistence
 * @see com.stpl.app.service.persistence.GcmItemDetailsUtil
 * @generated
 */
@ProviderType
public class GcmItemDetailsPersistenceImpl extends BasePersistenceImpl<GcmItemDetails>
	implements GcmItemDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GcmItemDetailsUtil} to access the gcm item details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GcmItemDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmItemDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmItemDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GcmItemDetailsPersistenceImpl() {
		setModelClass(GcmItemDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ifpDetailsEndDate", "IFP_DETAILS_END_DATE");
			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("ifpDetailsStartDate", "IFP_DETAILS_START_DATE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemEndDate", "ITEM_END_DATE");
			dbColumnNames.put("gcmItemDetailsSid", "GCM_ITEM_DETAILS_SID");
			dbColumnNames.put("itemIfpDetailsSid", "ITEM_IFP_DETAILS_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemStartDate", "ITEM_START_DATE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("itemStatusSid", "ITEM_STATUS_SID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("theraputicClass", "THERAPUTIC_CLASS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the gcm item details in the entity cache if it is enabled.
	 *
	 * @param gcmItemDetails the gcm item details
	 */
	@Override
	public void cacheResult(GcmItemDetails gcmItemDetails) {
		entityCache.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey(),
			gcmItemDetails);

		gcmItemDetails.resetOriginalValues();
	}

	/**
	 * Caches the gcm item detailses in the entity cache if it is enabled.
	 *
	 * @param gcmItemDetailses the gcm item detailses
	 */
	@Override
	public void cacheResult(List<GcmItemDetails> gcmItemDetailses) {
		for (GcmItemDetails gcmItemDetails : gcmItemDetailses) {
			if (entityCache.getResult(
						GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey()) == null) {
				cacheResult(gcmItemDetails);
			}
			else {
				gcmItemDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gcm item detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GcmItemDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gcm item details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GcmItemDetails gcmItemDetails) {
		entityCache.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GcmItemDetails> gcmItemDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GcmItemDetails gcmItemDetails : gcmItemDetailses) {
			entityCache.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
	 *
	 * @param gcmItemDetailsSid the primary key for the new gcm item details
	 * @return the new gcm item details
	 */
	@Override
	public GcmItemDetails create(int gcmItemDetailsSid) {
		GcmItemDetails gcmItemDetails = new GcmItemDetailsImpl();

		gcmItemDetails.setNew(true);
		gcmItemDetails.setPrimaryKey(gcmItemDetailsSid);

		return gcmItemDetails;
	}

	/**
	 * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gcmItemDetailsSid the primary key of the gcm item details
	 * @return the gcm item details that was removed
	 * @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails remove(int gcmItemDetailsSid)
		throws NoSuchGcmItemDetailsException {
		return remove((Serializable)gcmItemDetailsSid);
	}

	/**
	 * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gcm item details
	 * @return the gcm item details that was removed
	 * @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails remove(Serializable primaryKey)
		throws NoSuchGcmItemDetailsException {
		Session session = null;

		try {
			session = openSession();

			GcmItemDetails gcmItemDetails = (GcmItemDetails)session.get(GcmItemDetailsImpl.class,
					primaryKey);

			if (gcmItemDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGcmItemDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gcmItemDetails);
		}
		catch (NoSuchGcmItemDetailsException nsee) {
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
	protected GcmItemDetails removeImpl(GcmItemDetails gcmItemDetails) {
		gcmItemDetails = toUnwrappedModel(gcmItemDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gcmItemDetails)) {
				gcmItemDetails = (GcmItemDetails)session.get(GcmItemDetailsImpl.class,
						gcmItemDetails.getPrimaryKeyObj());
			}

			if (gcmItemDetails != null) {
				session.delete(gcmItemDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gcmItemDetails != null) {
			clearCache(gcmItemDetails);
		}

		return gcmItemDetails;
	}

	@Override
	public GcmItemDetails updateImpl(GcmItemDetails gcmItemDetails) {
		gcmItemDetails = toUnwrappedModel(gcmItemDetails);

		boolean isNew = gcmItemDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (gcmItemDetails.isNew()) {
				session.save(gcmItemDetails);

				gcmItemDetails.setNew(false);
			}
			else {
				gcmItemDetails = (GcmItemDetails)session.merge(gcmItemDetails);
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

		entityCache.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey(),
			gcmItemDetails, false);

		gcmItemDetails.resetOriginalValues();

		return gcmItemDetails;
	}

	protected GcmItemDetails toUnwrappedModel(GcmItemDetails gcmItemDetails) {
		if (gcmItemDetails instanceof GcmItemDetailsImpl) {
			return gcmItemDetails;
		}

		GcmItemDetailsImpl gcmItemDetailsImpl = new GcmItemDetailsImpl();

		gcmItemDetailsImpl.setNew(gcmItemDetails.isNew());
		gcmItemDetailsImpl.setPrimaryKey(gcmItemDetails.getPrimaryKey());

		gcmItemDetailsImpl.setIfpDetailsEndDate(gcmItemDetails.getIfpDetailsEndDate());
		gcmItemDetailsImpl.setItemStatus(gcmItemDetails.getItemStatus());
		gcmItemDetailsImpl.setCheckRecord(gcmItemDetails.isCheckRecord());
		gcmItemDetailsImpl.setIfpDetailsStartDate(gcmItemDetails.getIfpDetailsStartDate());
		gcmItemDetailsImpl.setUserId(gcmItemDetails.getUserId());
		gcmItemDetailsImpl.setItemMasterSid(gcmItemDetails.getItemMasterSid());
		gcmItemDetailsImpl.setItemEndDate(gcmItemDetails.getItemEndDate());
		gcmItemDetailsImpl.setGcmItemDetailsSid(gcmItemDetails.getGcmItemDetailsSid());
		gcmItemDetailsImpl.setItemIfpDetailsSid(gcmItemDetails.getItemIfpDetailsSid());
		gcmItemDetailsImpl.setItemId(gcmItemDetails.getItemId());
		gcmItemDetailsImpl.setBrandName(gcmItemDetails.getBrandName());
		gcmItemDetailsImpl.setModifiedDate(gcmItemDetails.getModifiedDate());
		gcmItemDetailsImpl.setCreatedDate(gcmItemDetails.getCreatedDate());
		gcmItemDetailsImpl.setCreatedBy(gcmItemDetails.getCreatedBy());
		gcmItemDetailsImpl.setItemStartDate(gcmItemDetails.getItemStartDate());
		gcmItemDetailsImpl.setSessionId(gcmItemDetails.getSessionId());
		gcmItemDetailsImpl.setItemName(gcmItemDetails.getItemName());
		gcmItemDetailsImpl.setOperation(gcmItemDetails.getOperation());
		gcmItemDetailsImpl.setModifiedBy(gcmItemDetails.getModifiedBy());
		gcmItemDetailsImpl.setInboundStatus(gcmItemDetails.getInboundStatus());
		gcmItemDetailsImpl.setItemStatusSid(gcmItemDetails.getItemStatusSid());
		gcmItemDetailsImpl.setItemNo(gcmItemDetails.getItemNo());
		gcmItemDetailsImpl.setIfpModelSid(gcmItemDetails.getIfpModelSid());
		gcmItemDetailsImpl.setTheraputicClass(gcmItemDetails.getTheraputicClass());

		return gcmItemDetailsImpl;
	}

	/**
	 * Returns the gcm item details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm item details
	 * @return the gcm item details
	 * @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGcmItemDetailsException {
		GcmItemDetails gcmItemDetails = fetchByPrimaryKey(primaryKey);

		if (gcmItemDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGcmItemDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gcmItemDetails;
	}

	/**
	 * Returns the gcm item details with the primary key or throws a {@link NoSuchGcmItemDetailsException} if it could not be found.
	 *
	 * @param gcmItemDetailsSid the primary key of the gcm item details
	 * @return the gcm item details
	 * @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails findByPrimaryKey(int gcmItemDetailsSid)
		throws NoSuchGcmItemDetailsException {
		return findByPrimaryKey((Serializable)gcmItemDetailsSid);
	}

	/**
	 * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm item details
	 * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmItemDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GcmItemDetails gcmItemDetails = (GcmItemDetails)serializable;

		if (gcmItemDetails == null) {
			Session session = null;

			try {
				session = openSession();

				gcmItemDetails = (GcmItemDetails)session.get(GcmItemDetailsImpl.class,
						primaryKey);

				if (gcmItemDetails != null) {
					cacheResult(gcmItemDetails);
				}
				else {
					entityCache.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmItemDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmItemDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gcmItemDetails;
	}

	/**
	 * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gcmItemDetailsSid the primary key of the gcm item details
	 * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
	 */
	@Override
	public GcmItemDetails fetchByPrimaryKey(int gcmItemDetailsSid) {
		return fetchByPrimaryKey((Serializable)gcmItemDetailsSid);
	}

	@Override
	public Map<Serializable, GcmItemDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GcmItemDetails> map = new HashMap<Serializable, GcmItemDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GcmItemDetails gcmItemDetails = fetchByPrimaryKey(primaryKey);

			if (gcmItemDetails != null) {
				map.put(primaryKey, gcmItemDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmItemDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GcmItemDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GCMITEMDETAILS_WHERE_PKS_IN);

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

			for (GcmItemDetails gcmItemDetails : (List<GcmItemDetails>)q.list()) {
				map.put(gcmItemDetails.getPrimaryKeyObj(), gcmItemDetails);

				cacheResult(gcmItemDetails);

				uncachedPrimaryKeys.remove(gcmItemDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmItemDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the gcm item detailses.
	 *
	 * @return the gcm item detailses
	 */
	@Override
	public List<GcmItemDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gcm item detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm item detailses
	 * @param end the upper bound of the range of gcm item detailses (not inclusive)
	 * @return the range of gcm item detailses
	 */
	@Override
	public List<GcmItemDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gcm item detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm item detailses
	 * @param end the upper bound of the range of gcm item detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gcm item detailses
	 */
	@Override
	public List<GcmItemDetails> findAll(int start, int end,
		OrderByComparator<GcmItemDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gcm item detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm item detailses
	 * @param end the upper bound of the range of gcm item detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of gcm item detailses
	 */
	@Override
	public List<GcmItemDetails> findAll(int start, int end,
		OrderByComparator<GcmItemDetails> orderByComparator,
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

		List<GcmItemDetails> list = null;

		if (retrieveFromCache) {
			list = (List<GcmItemDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GCMITEMDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GCMITEMDETAILS;

				if (pagination) {
					sql = sql.concat(GcmItemDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GcmItemDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GcmItemDetails>)QueryUtil.list(q,
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
	 * Removes all the gcm item detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GcmItemDetails gcmItemDetails : findAll()) {
			remove(gcmItemDetails);
		}
	}

	/**
	 * Returns the number of gcm item detailses.
	 *
	 * @return the number of gcm item detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GCMITEMDETAILS);

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
		return GcmItemDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gcm item details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GcmItemDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GCMITEMDETAILS = "SELECT gcmItemDetails FROM GcmItemDetails gcmItemDetails";
	private static final String _SQL_SELECT_GCMITEMDETAILS_WHERE_PKS_IN = "SELECT gcmItemDetails FROM GcmItemDetails gcmItemDetails WHERE GCM_ITEM_DETAILS_SID IN (";
	private static final String _SQL_COUNT_GCMITEMDETAILS = "SELECT COUNT(gcmItemDetails) FROM GcmItemDetails gcmItemDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gcmItemDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmItemDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GcmItemDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ifpDetailsEndDate", "itemStatus", "checkRecord",
				"ifpDetailsStartDate", "userId", "itemMasterSid", "itemEndDate",
				"gcmItemDetailsSid", "itemIfpDetailsSid", "itemId", "brandName",
				"modifiedDate", "createdDate", "createdBy", "itemStartDate",
				"sessionId", "itemName", "operation", "modifiedBy",
				"inboundStatus", "itemStatusSid", "itemNo", "ifpModelSid",
				"theraputicClass"
			});
}