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

import com.stpl.app.exception.NoSuchIvldLotMasterException;
import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.model.impl.IvldLotMasterImpl;
import com.stpl.app.model.impl.IvldLotMasterModelImpl;
import com.stpl.app.service.persistence.IvldLotMasterPersistence;

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
 * The persistence implementation for the ivld lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldLotMasterPersistence
 * @see com.stpl.app.service.persistence.IvldLotMasterUtil
 * @generated
 */
@ProviderType
public class IvldLotMasterPersistenceImpl extends BasePersistenceImpl<IvldLotMaster>
	implements IvldLotMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldLotMasterUtil} to access the ivld lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldLotMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldLotMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldLotMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldLotMasterPersistenceImpl() {
		setModelClass(IvldLotMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("ivldLotMasterSid", "IVLD_LOT_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("lastLotSoldDate", "LAST_LOT_SOLD_DATE");
			dbColumnNames.put("lotExpirationDate", "LOT_EXPIRATION_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("lotNo", "LOT_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("lotMasterIntfid", "LOT_MASTER_INTFID");
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
	 * Caches the ivld lot master in the entity cache if it is enabled.
	 *
	 * @param ivldLotMaster the ivld lot master
	 */
	@Override
	public void cacheResult(IvldLotMaster ivldLotMaster) {
		entityCache.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey(),
			ivldLotMaster);

		ivldLotMaster.resetOriginalValues();
	}

	/**
	 * Caches the ivld lot masters in the entity cache if it is enabled.
	 *
	 * @param ivldLotMasters the ivld lot masters
	 */
	@Override
	public void cacheResult(List<IvldLotMaster> ivldLotMasters) {
		for (IvldLotMaster ivldLotMaster : ivldLotMasters) {
			if (entityCache.getResult(
						IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey()) == null) {
				cacheResult(ivldLotMaster);
			}
			else {
				ivldLotMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld lot masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldLotMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld lot master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldLotMaster ivldLotMaster) {
		entityCache.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldLotMaster> ivldLotMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldLotMaster ivldLotMaster : ivldLotMasters) {
			entityCache.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
	 *
	 * @param ivldLotMasterSid the primary key for the new ivld lot master
	 * @return the new ivld lot master
	 */
	@Override
	public IvldLotMaster create(int ivldLotMasterSid) {
		IvldLotMaster ivldLotMaster = new IvldLotMasterImpl();

		ivldLotMaster.setNew(true);
		ivldLotMaster.setPrimaryKey(ivldLotMasterSid);

		return ivldLotMaster;
	}

	/**
	 * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldLotMasterSid the primary key of the ivld lot master
	 * @return the ivld lot master that was removed
	 * @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster remove(int ivldLotMasterSid)
		throws NoSuchIvldLotMasterException {
		return remove((Serializable)ivldLotMasterSid);
	}

	/**
	 * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld lot master
	 * @return the ivld lot master that was removed
	 * @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster remove(Serializable primaryKey)
		throws NoSuchIvldLotMasterException {
		Session session = null;

		try {
			session = openSession();

			IvldLotMaster ivldLotMaster = (IvldLotMaster)session.get(IvldLotMasterImpl.class,
					primaryKey);

			if (ivldLotMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldLotMaster);
		}
		catch (NoSuchIvldLotMasterException nsee) {
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
	protected IvldLotMaster removeImpl(IvldLotMaster ivldLotMaster) {
		ivldLotMaster = toUnwrappedModel(ivldLotMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldLotMaster)) {
				ivldLotMaster = (IvldLotMaster)session.get(IvldLotMasterImpl.class,
						ivldLotMaster.getPrimaryKeyObj());
			}

			if (ivldLotMaster != null) {
				session.delete(ivldLotMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldLotMaster != null) {
			clearCache(ivldLotMaster);
		}

		return ivldLotMaster;
	}

	@Override
	public IvldLotMaster updateImpl(IvldLotMaster ivldLotMaster) {
		ivldLotMaster = toUnwrappedModel(ivldLotMaster);

		boolean isNew = ivldLotMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldLotMaster.isNew()) {
				session.save(ivldLotMaster);

				ivldLotMaster.setNew(false);
			}
			else {
				ivldLotMaster = (IvldLotMaster)session.merge(ivldLotMaster);
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

		entityCache.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey(),
			ivldLotMaster, false);

		ivldLotMaster.resetOriginalValues();

		return ivldLotMaster;
	}

	protected IvldLotMaster toUnwrappedModel(IvldLotMaster ivldLotMaster) {
		if (ivldLotMaster instanceof IvldLotMasterImpl) {
			return ivldLotMaster;
		}

		IvldLotMasterImpl ivldLotMasterImpl = new IvldLotMasterImpl();

		ivldLotMasterImpl.setNew(ivldLotMaster.isNew());
		ivldLotMasterImpl.setPrimaryKey(ivldLotMaster.getPrimaryKey());

		ivldLotMasterImpl.setReasonForFailure(ivldLotMaster.getReasonForFailure());
		ivldLotMasterImpl.setItemId(ivldLotMaster.getItemId());
		ivldLotMasterImpl.setIvldLotMasterSid(ivldLotMaster.getIvldLotMasterSid());
		ivldLotMasterImpl.setModifiedDate(ivldLotMaster.getModifiedDate());
		ivldLotMasterImpl.setCreatedBy(ivldLotMaster.getCreatedBy());
		ivldLotMasterImpl.setCreatedDate(ivldLotMaster.getCreatedDate());
		ivldLotMasterImpl.setSource(ivldLotMaster.getSource());
		ivldLotMasterImpl.setLastLotSoldDate(ivldLotMaster.getLastLotSoldDate());
		ivldLotMasterImpl.setLotExpirationDate(ivldLotMaster.getLotExpirationDate());
		ivldLotMasterImpl.setBatchId(ivldLotMaster.getBatchId());
		ivldLotMasterImpl.setAddChgDelIndicator(ivldLotMaster.getAddChgDelIndicator());
		ivldLotMasterImpl.setErrorField(ivldLotMaster.getErrorField());
		ivldLotMasterImpl.setErrorCode(ivldLotMaster.getErrorCode());
		ivldLotMasterImpl.setIntfInsertedDate(ivldLotMaster.getIntfInsertedDate());
		ivldLotMasterImpl.setModifiedBy(ivldLotMaster.getModifiedBy());
		ivldLotMasterImpl.setLotNo(ivldLotMaster.getLotNo());
		ivldLotMasterImpl.setReprocessedFlag(ivldLotMaster.getReprocessedFlag());
		ivldLotMasterImpl.setLotMasterIntfid(ivldLotMaster.getLotMasterIntfid());
		ivldLotMasterImpl.setCheckRecord(ivldLotMaster.isCheckRecord());

		return ivldLotMasterImpl;
	}

	/**
	 * Returns the ivld lot master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld lot master
	 * @return the ivld lot master
	 * @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldLotMasterException {
		IvldLotMaster ivldLotMaster = fetchByPrimaryKey(primaryKey);

		if (ivldLotMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldLotMaster;
	}

	/**
	 * Returns the ivld lot master with the primary key or throws a {@link NoSuchIvldLotMasterException} if it could not be found.
	 *
	 * @param ivldLotMasterSid the primary key of the ivld lot master
	 * @return the ivld lot master
	 * @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster findByPrimaryKey(int ivldLotMasterSid)
		throws NoSuchIvldLotMasterException {
		return findByPrimaryKey((Serializable)ivldLotMasterSid);
	}

	/**
	 * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld lot master
	 * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldLotMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldLotMaster ivldLotMaster = (IvldLotMaster)serializable;

		if (ivldLotMaster == null) {
			Session session = null;

			try {
				session = openSession();

				ivldLotMaster = (IvldLotMaster)session.get(IvldLotMasterImpl.class,
						primaryKey);

				if (ivldLotMaster != null) {
					cacheResult(ivldLotMaster);
				}
				else {
					entityCache.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldLotMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldLotMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldLotMaster;
	}

	/**
	 * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldLotMasterSid the primary key of the ivld lot master
	 * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
	 */
	@Override
	public IvldLotMaster fetchByPrimaryKey(int ivldLotMasterSid) {
		return fetchByPrimaryKey((Serializable)ivldLotMasterSid);
	}

	@Override
	public Map<Serializable, IvldLotMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldLotMaster> map = new HashMap<Serializable, IvldLotMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldLotMaster ivldLotMaster = fetchByPrimaryKey(primaryKey);

			if (ivldLotMaster != null) {
				map.put(primaryKey, ivldLotMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldLotMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldLotMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDLOTMASTER_WHERE_PKS_IN);

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

			for (IvldLotMaster ivldLotMaster : (List<IvldLotMaster>)q.list()) {
				map.put(ivldLotMaster.getPrimaryKeyObj(), ivldLotMaster);

				cacheResult(ivldLotMaster);

				uncachedPrimaryKeys.remove(ivldLotMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldLotMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld lot masters.
	 *
	 * @return the ivld lot masters
	 */
	@Override
	public List<IvldLotMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld lot masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld lot masters
	 * @param end the upper bound of the range of ivld lot masters (not inclusive)
	 * @return the range of ivld lot masters
	 */
	@Override
	public List<IvldLotMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld lot masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld lot masters
	 * @param end the upper bound of the range of ivld lot masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld lot masters
	 */
	@Override
	public List<IvldLotMaster> findAll(int start, int end,
		OrderByComparator<IvldLotMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld lot masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld lot masters
	 * @param end the upper bound of the range of ivld lot masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld lot masters
	 */
	@Override
	public List<IvldLotMaster> findAll(int start, int end,
		OrderByComparator<IvldLotMaster> orderByComparator,
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

		List<IvldLotMaster> list = null;

		if (retrieveFromCache) {
			list = (List<IvldLotMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDLOTMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDLOTMASTER;

				if (pagination) {
					sql = sql.concat(IvldLotMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldLotMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldLotMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ivld lot masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldLotMaster ivldLotMaster : findAll()) {
			remove(ivldLotMaster);
		}
	}

	/**
	 * Returns the number of ivld lot masters.
	 *
	 * @return the number of ivld lot masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDLOTMASTER);

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
		return IvldLotMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld lot master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldLotMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDLOTMASTER = "SELECT ivldLotMaster FROM IvldLotMaster ivldLotMaster";
	private static final String _SQL_SELECT_IVLDLOTMASTER_WHERE_PKS_IN = "SELECT ivldLotMaster FROM IvldLotMaster ivldLotMaster WHERE IVLD_LOT_MASTER_SID IN (";
	private static final String _SQL_COUNT_IVLDLOTMASTER = "SELECT COUNT(ivldLotMaster) FROM IvldLotMaster ivldLotMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldLotMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldLotMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldLotMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"reasonForFailure", "itemId", "ivldLotMasterSid", "modifiedDate",
				"createdBy", "createdDate", "source", "lastLotSoldDate",
				"lotExpirationDate", "batchId", "addChgDelIndicator",
				"errorField", "errorCode", "intfInsertedDate", "modifiedBy",
				"lotNo", "reprocessedFlag", "lotMasterIntfid", "checkRecord"
			});
}