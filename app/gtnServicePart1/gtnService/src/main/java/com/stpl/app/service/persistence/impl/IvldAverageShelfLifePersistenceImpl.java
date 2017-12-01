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

import com.stpl.app.exception.NoSuchIvldAverageShelfLifeException;
import com.stpl.app.model.IvldAverageShelfLife;
import com.stpl.app.model.impl.IvldAverageShelfLifeImpl;
import com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl;
import com.stpl.app.service.persistence.IvldAverageShelfLifePersistence;

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
 * The persistence implementation for the ivld average shelf life service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAverageShelfLifePersistence
 * @see com.stpl.app.service.persistence.IvldAverageShelfLifeUtil
 * @generated
 */
@ProviderType
public class IvldAverageShelfLifePersistenceImpl extends BasePersistenceImpl<IvldAverageShelfLife>
	implements IvldAverageShelfLifePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldAverageShelfLifeUtil} to access the ivld average shelf life persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldAverageShelfLifeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED,
			IvldAverageShelfLifeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED,
			IvldAverageShelfLifeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldAverageShelfLifePersistenceImpl() {
		setModelClass(IvldAverageShelfLife.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("ivldAverageShelfLifeSid",
				"IVLD_AVERAGE_SHELF_LIFE_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("avgShelfLife", "AVG_SHELF_LIFE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("itemIdType", "ITEM_ID_TYPE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("averageShelfLifeIntfid",
				"AVERAGE_SHELF_LIFE_INTFID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
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
	 * Caches the ivld average shelf life in the entity cache if it is enabled.
	 *
	 * @param ivldAverageShelfLife the ivld average shelf life
	 */
	@Override
	public void cacheResult(IvldAverageShelfLife ivldAverageShelfLife) {
		entityCache.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeImpl.class,
			ivldAverageShelfLife.getPrimaryKey(), ivldAverageShelfLife);

		ivldAverageShelfLife.resetOriginalValues();
	}

	/**
	 * Caches the ivld average shelf lifes in the entity cache if it is enabled.
	 *
	 * @param ivldAverageShelfLifes the ivld average shelf lifes
	 */
	@Override
	public void cacheResult(List<IvldAverageShelfLife> ivldAverageShelfLifes) {
		for (IvldAverageShelfLife ivldAverageShelfLife : ivldAverageShelfLifes) {
			if (entityCache.getResult(
						IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
						IvldAverageShelfLifeImpl.class,
						ivldAverageShelfLife.getPrimaryKey()) == null) {
				cacheResult(ivldAverageShelfLife);
			}
			else {
				ivldAverageShelfLife.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld average shelf lifes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldAverageShelfLifeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld average shelf life.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldAverageShelfLife ivldAverageShelfLife) {
		entityCache.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeImpl.class, ivldAverageShelfLife.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldAverageShelfLife> ivldAverageShelfLifes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldAverageShelfLife ivldAverageShelfLife : ivldAverageShelfLifes) {
			entityCache.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
				IvldAverageShelfLifeImpl.class,
				ivldAverageShelfLife.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
	 *
	 * @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
	 * @return the new ivld average shelf life
	 */
	@Override
	public IvldAverageShelfLife create(int ivldAverageShelfLifeSid) {
		IvldAverageShelfLife ivldAverageShelfLife = new IvldAverageShelfLifeImpl();

		ivldAverageShelfLife.setNew(true);
		ivldAverageShelfLife.setPrimaryKey(ivldAverageShelfLifeSid);

		return ivldAverageShelfLife;
	}

	/**
	 * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	 * @return the ivld average shelf life that was removed
	 * @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife remove(int ivldAverageShelfLifeSid)
		throws NoSuchIvldAverageShelfLifeException {
		return remove((Serializable)ivldAverageShelfLifeSid);
	}

	/**
	 * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld average shelf life
	 * @return the ivld average shelf life that was removed
	 * @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife remove(Serializable primaryKey)
		throws NoSuchIvldAverageShelfLifeException {
		Session session = null;

		try {
			session = openSession();

			IvldAverageShelfLife ivldAverageShelfLife = (IvldAverageShelfLife)session.get(IvldAverageShelfLifeImpl.class,
					primaryKey);

			if (ivldAverageShelfLife == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldAverageShelfLifeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldAverageShelfLife);
		}
		catch (NoSuchIvldAverageShelfLifeException nsee) {
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
	protected IvldAverageShelfLife removeImpl(
		IvldAverageShelfLife ivldAverageShelfLife) {
		ivldAverageShelfLife = toUnwrappedModel(ivldAverageShelfLife);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldAverageShelfLife)) {
				ivldAverageShelfLife = (IvldAverageShelfLife)session.get(IvldAverageShelfLifeImpl.class,
						ivldAverageShelfLife.getPrimaryKeyObj());
			}

			if (ivldAverageShelfLife != null) {
				session.delete(ivldAverageShelfLife);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldAverageShelfLife != null) {
			clearCache(ivldAverageShelfLife);
		}

		return ivldAverageShelfLife;
	}

	@Override
	public IvldAverageShelfLife updateImpl(
		IvldAverageShelfLife ivldAverageShelfLife) {
		ivldAverageShelfLife = toUnwrappedModel(ivldAverageShelfLife);

		boolean isNew = ivldAverageShelfLife.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldAverageShelfLife.isNew()) {
				session.save(ivldAverageShelfLife);

				ivldAverageShelfLife.setNew(false);
			}
			else {
				ivldAverageShelfLife = (IvldAverageShelfLife)session.merge(ivldAverageShelfLife);
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

		entityCache.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
			IvldAverageShelfLifeImpl.class,
			ivldAverageShelfLife.getPrimaryKey(), ivldAverageShelfLife, false);

		ivldAverageShelfLife.resetOriginalValues();

		return ivldAverageShelfLife;
	}

	protected IvldAverageShelfLife toUnwrappedModel(
		IvldAverageShelfLife ivldAverageShelfLife) {
		if (ivldAverageShelfLife instanceof IvldAverageShelfLifeImpl) {
			return ivldAverageShelfLife;
		}

		IvldAverageShelfLifeImpl ivldAverageShelfLifeImpl = new IvldAverageShelfLifeImpl();

		ivldAverageShelfLifeImpl.setNew(ivldAverageShelfLife.isNew());
		ivldAverageShelfLifeImpl.setPrimaryKey(ivldAverageShelfLife.getPrimaryKey());

		ivldAverageShelfLifeImpl.setReasonForFailure(ivldAverageShelfLife.getReasonForFailure());
		ivldAverageShelfLifeImpl.setIvldAverageShelfLifeSid(ivldAverageShelfLife.getIvldAverageShelfLifeSid());
		ivldAverageShelfLifeImpl.setItemId(ivldAverageShelfLife.getItemId());
		ivldAverageShelfLifeImpl.setAvgShelfLife(ivldAverageShelfLife.getAvgShelfLife());
		ivldAverageShelfLifeImpl.setModifiedDate(ivldAverageShelfLife.getModifiedDate());
		ivldAverageShelfLifeImpl.setCreatedBy(ivldAverageShelfLife.getCreatedBy());
		ivldAverageShelfLifeImpl.setCreatedDate(ivldAverageShelfLife.getCreatedDate());
		ivldAverageShelfLifeImpl.setSource(ivldAverageShelfLife.getSource());
		ivldAverageShelfLifeImpl.setItemIdType(ivldAverageShelfLife.getItemIdType());
		ivldAverageShelfLifeImpl.setBatchId(ivldAverageShelfLife.getBatchId());
		ivldAverageShelfLifeImpl.setAddChgDelIndicator(ivldAverageShelfLife.getAddChgDelIndicator());
		ivldAverageShelfLifeImpl.setAverageShelfLifeIntfid(ivldAverageShelfLife.getAverageShelfLifeIntfid());
		ivldAverageShelfLifeImpl.setErrorField(ivldAverageShelfLife.getErrorField());
		ivldAverageShelfLifeImpl.setErrorCode(ivldAverageShelfLife.getErrorCode());
		ivldAverageShelfLifeImpl.setIntfInsertedDate(ivldAverageShelfLife.getIntfInsertedDate());
		ivldAverageShelfLifeImpl.setModifiedBy(ivldAverageShelfLife.getModifiedBy());
		ivldAverageShelfLifeImpl.setReprocessedFlag(ivldAverageShelfLife.getReprocessedFlag());
		ivldAverageShelfLifeImpl.setCheckRecord(ivldAverageShelfLife.isCheckRecord());

		return ivldAverageShelfLifeImpl;
	}

	/**
	 * Returns the ivld average shelf life with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld average shelf life
	 * @return the ivld average shelf life
	 * @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldAverageShelfLifeException {
		IvldAverageShelfLife ivldAverageShelfLife = fetchByPrimaryKey(primaryKey);

		if (ivldAverageShelfLife == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldAverageShelfLifeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldAverageShelfLife;
	}

	/**
	 * Returns the ivld average shelf life with the primary key or throws a {@link NoSuchIvldAverageShelfLifeException} if it could not be found.
	 *
	 * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	 * @return the ivld average shelf life
	 * @throws NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife findByPrimaryKey(int ivldAverageShelfLifeSid)
		throws NoSuchIvldAverageShelfLifeException {
		return findByPrimaryKey((Serializable)ivldAverageShelfLifeSid);
	}

	/**
	 * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld average shelf life
	 * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
				IvldAverageShelfLifeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldAverageShelfLife ivldAverageShelfLife = (IvldAverageShelfLife)serializable;

		if (ivldAverageShelfLife == null) {
			Session session = null;

			try {
				session = openSession();

				ivldAverageShelfLife = (IvldAverageShelfLife)session.get(IvldAverageShelfLifeImpl.class,
						primaryKey);

				if (ivldAverageShelfLife != null) {
					cacheResult(ivldAverageShelfLife);
				}
				else {
					entityCache.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
						IvldAverageShelfLifeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
					IvldAverageShelfLifeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldAverageShelfLife;
	}

	/**
	 * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	 * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
	 */
	@Override
	public IvldAverageShelfLife fetchByPrimaryKey(int ivldAverageShelfLifeSid) {
		return fetchByPrimaryKey((Serializable)ivldAverageShelfLifeSid);
	}

	@Override
	public Map<Serializable, IvldAverageShelfLife> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldAverageShelfLife> map = new HashMap<Serializable, IvldAverageShelfLife>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldAverageShelfLife ivldAverageShelfLife = fetchByPrimaryKey(primaryKey);

			if (ivldAverageShelfLife != null) {
				map.put(primaryKey, ivldAverageShelfLife);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
					IvldAverageShelfLifeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldAverageShelfLife)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDAVERAGESHELFLIFE_WHERE_PKS_IN);

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

			for (IvldAverageShelfLife ivldAverageShelfLife : (List<IvldAverageShelfLife>)q.list()) {
				map.put(ivldAverageShelfLife.getPrimaryKeyObj(),
					ivldAverageShelfLife);

				cacheResult(ivldAverageShelfLife);

				uncachedPrimaryKeys.remove(ivldAverageShelfLife.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
					IvldAverageShelfLifeImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld average shelf lifes.
	 *
	 * @return the ivld average shelf lifes
	 */
	@Override
	public List<IvldAverageShelfLife> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld average shelf lifes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld average shelf lifes
	 * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	 * @return the range of ivld average shelf lifes
	 */
	@Override
	public List<IvldAverageShelfLife> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld average shelf lifes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld average shelf lifes
	 * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld average shelf lifes
	 */
	@Override
	public List<IvldAverageShelfLife> findAll(int start, int end,
		OrderByComparator<IvldAverageShelfLife> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld average shelf lifes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld average shelf lifes
	 * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld average shelf lifes
	 */
	@Override
	public List<IvldAverageShelfLife> findAll(int start, int end,
		OrderByComparator<IvldAverageShelfLife> orderByComparator,
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

		List<IvldAverageShelfLife> list = null;

		if (retrieveFromCache) {
			list = (List<IvldAverageShelfLife>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDAVERAGESHELFLIFE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDAVERAGESHELFLIFE;

				if (pagination) {
					sql = sql.concat(IvldAverageShelfLifeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldAverageShelfLife>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldAverageShelfLife>)QueryUtil.list(q,
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
	 * Removes all the ivld average shelf lifes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldAverageShelfLife ivldAverageShelfLife : findAll()) {
			remove(ivldAverageShelfLife);
		}
	}

	/**
	 * Returns the number of ivld average shelf lifes.
	 *
	 * @return the number of ivld average shelf lifes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDAVERAGESHELFLIFE);

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
		return IvldAverageShelfLifeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld average shelf life persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldAverageShelfLifeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDAVERAGESHELFLIFE = "SELECT ivldAverageShelfLife FROM IvldAverageShelfLife ivldAverageShelfLife";
	private static final String _SQL_SELECT_IVLDAVERAGESHELFLIFE_WHERE_PKS_IN = "SELECT ivldAverageShelfLife FROM IvldAverageShelfLife ivldAverageShelfLife WHERE IVLD_AVERAGE_SHELF_LIFE_SID IN (";
	private static final String _SQL_COUNT_IVLDAVERAGESHELFLIFE = "SELECT COUNT(ivldAverageShelfLife) FROM IvldAverageShelfLife ivldAverageShelfLife";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldAverageShelfLife.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldAverageShelfLife exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldAverageShelfLifePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"reasonForFailure", "ivldAverageShelfLifeSid", "itemId",
				"avgShelfLife", "modifiedDate", "createdBy", "createdDate",
				"source", "itemIdType", "batchId", "addChgDelIndicator",
				"averageShelfLifeIntfid", "errorField", "errorCode",
				"intfInsertedDate", "modifiedBy", "reprocessedFlag",
				"checkRecord"
			});
}