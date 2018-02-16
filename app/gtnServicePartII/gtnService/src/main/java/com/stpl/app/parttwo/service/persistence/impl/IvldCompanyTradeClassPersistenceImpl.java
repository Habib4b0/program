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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyTradeClassException;
import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassPersistence;

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
 * The persistence implementation for the ivld company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassUtil
 * @generated
 */
@ProviderType
public class IvldCompanyTradeClassPersistenceImpl extends BasePersistenceImpl<IvldCompanyTradeClass>
	implements IvldCompanyTradeClassPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCompanyTradeClassUtil} to access the ivld company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyTradeClassImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCompanyTradeClassPersistenceImpl() {
		setModelClass(IvldCompanyTradeClass.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ivldCompanyTradeClassSid",
				"IVLD_COMPANY_TRADE_CLASS_SID");
			dbColumnNames.put("priorTradeClass", "PRIOR_TRADE_CLASS");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("priorTradeClassStartDate",
				"PRIOR_TRADE_CLASS_START_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
			dbColumnNames.put("tradeClassIntfid", "TRADE_CLASS_INTFID");
			dbColumnNames.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("tradeClass", "TRADE_CLASS");
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
	 * Caches the ivld company trade class in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyTradeClass the ivld company trade class
	 */
	@Override
	public void cacheResult(IvldCompanyTradeClass ivldCompanyTradeClass) {
		entityCache.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassImpl.class,
			ivldCompanyTradeClass.getPrimaryKey(), ivldCompanyTradeClass);

		ivldCompanyTradeClass.resetOriginalValues();
	}

	/**
	 * Caches the ivld company trade classes in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyTradeClasses the ivld company trade classes
	 */
	@Override
	public void cacheResult(List<IvldCompanyTradeClass> ivldCompanyTradeClasses) {
		for (IvldCompanyTradeClass ivldCompanyTradeClass : ivldCompanyTradeClasses) {
			if (entityCache.getResult(
						IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyTradeClassImpl.class,
						ivldCompanyTradeClass.getPrimaryKey()) == null) {
				cacheResult(ivldCompanyTradeClass);
			}
			else {
				ivldCompanyTradeClass.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld company trade classes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCompanyTradeClassImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld company trade class.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCompanyTradeClass ivldCompanyTradeClass) {
		entityCache.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassImpl.class,
			ivldCompanyTradeClass.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCompanyTradeClass> ivldCompanyTradeClasses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCompanyTradeClass ivldCompanyTradeClass : ivldCompanyTradeClasses) {
			entityCache.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyTradeClassImpl.class,
				ivldCompanyTradeClass.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
	 *
	 * @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
	 * @return the new ivld company trade class
	 */
	@Override
	public IvldCompanyTradeClass create(int ivldCompanyTradeClassSid) {
		IvldCompanyTradeClass ivldCompanyTradeClass = new IvldCompanyTradeClassImpl();

		ivldCompanyTradeClass.setNew(true);
		ivldCompanyTradeClass.setPrimaryKey(ivldCompanyTradeClassSid);

		return ivldCompanyTradeClass;
	}

	/**
	 * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	 * @return the ivld company trade class that was removed
	 * @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass remove(int ivldCompanyTradeClassSid)
		throws NoSuchIvldCompanyTradeClassException {
		return remove((Serializable)ivldCompanyTradeClassSid);
	}

	/**
	 * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld company trade class
	 * @return the ivld company trade class that was removed
	 * @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass remove(Serializable primaryKey)
		throws NoSuchIvldCompanyTradeClassException {
		Session session = null;

		try {
			session = openSession();

			IvldCompanyTradeClass ivldCompanyTradeClass = (IvldCompanyTradeClass)session.get(IvldCompanyTradeClassImpl.class,
					primaryKey);

			if (ivldCompanyTradeClass == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCompanyTradeClass);
		}
		catch (NoSuchIvldCompanyTradeClassException nsee) {
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
	protected IvldCompanyTradeClass removeImpl(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		ivldCompanyTradeClass = toUnwrappedModel(ivldCompanyTradeClass);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCompanyTradeClass)) {
				ivldCompanyTradeClass = (IvldCompanyTradeClass)session.get(IvldCompanyTradeClassImpl.class,
						ivldCompanyTradeClass.getPrimaryKeyObj());
			}

			if (ivldCompanyTradeClass != null) {
				session.delete(ivldCompanyTradeClass);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCompanyTradeClass != null) {
			clearCache(ivldCompanyTradeClass);
		}

		return ivldCompanyTradeClass;
	}

	@Override
	public IvldCompanyTradeClass updateImpl(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		ivldCompanyTradeClass = toUnwrappedModel(ivldCompanyTradeClass);

		boolean isNew = ivldCompanyTradeClass.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCompanyTradeClass.isNew()) {
				session.save(ivldCompanyTradeClass);

				ivldCompanyTradeClass.setNew(false);
			}
			else {
				ivldCompanyTradeClass = (IvldCompanyTradeClass)session.merge(ivldCompanyTradeClass);
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

		entityCache.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyTradeClassImpl.class,
			ivldCompanyTradeClass.getPrimaryKey(), ivldCompanyTradeClass, false);

		ivldCompanyTradeClass.resetOriginalValues();

		return ivldCompanyTradeClass;
	}

	protected IvldCompanyTradeClass toUnwrappedModel(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		if (ivldCompanyTradeClass instanceof IvldCompanyTradeClassImpl) {
			return ivldCompanyTradeClass;
		}

		IvldCompanyTradeClassImpl ivldCompanyTradeClassImpl = new IvldCompanyTradeClassImpl();

		ivldCompanyTradeClassImpl.setNew(ivldCompanyTradeClass.isNew());
		ivldCompanyTradeClassImpl.setPrimaryKey(ivldCompanyTradeClass.getPrimaryKey());

		ivldCompanyTradeClassImpl.setIvldCompanyTradeClassSid(ivldCompanyTradeClass.getIvldCompanyTradeClassSid());
		ivldCompanyTradeClassImpl.setPriorTradeClass(ivldCompanyTradeClass.getPriorTradeClass());
		ivldCompanyTradeClassImpl.setReasonForFailure(ivldCompanyTradeClass.getReasonForFailure());
		ivldCompanyTradeClassImpl.setCompanyIdString(ivldCompanyTradeClass.getCompanyIdString());
		ivldCompanyTradeClassImpl.setLastUpdatedDate(ivldCompanyTradeClass.getLastUpdatedDate());
		ivldCompanyTradeClassImpl.setPriorTradeClassStartDate(ivldCompanyTradeClass.getPriorTradeClassStartDate());
		ivldCompanyTradeClassImpl.setModifiedDate(ivldCompanyTradeClass.getModifiedDate());
		ivldCompanyTradeClassImpl.setTradeClassEndDate(ivldCompanyTradeClass.getTradeClassEndDate());
		ivldCompanyTradeClassImpl.setTradeClassIntfid(ivldCompanyTradeClass.getTradeClassIntfid());
		ivldCompanyTradeClassImpl.setTradeClassStartDate(ivldCompanyTradeClass.getTradeClassStartDate());
		ivldCompanyTradeClassImpl.setSource(ivldCompanyTradeClass.getSource());
		ivldCompanyTradeClassImpl.setCreatedBy(ivldCompanyTradeClass.getCreatedBy());
		ivldCompanyTradeClassImpl.setCreatedDate(ivldCompanyTradeClass.getCreatedDate());
		ivldCompanyTradeClassImpl.setAddChgDelIndicator(ivldCompanyTradeClass.getAddChgDelIndicator());
		ivldCompanyTradeClassImpl.setBatchId(ivldCompanyTradeClass.getBatchId());
		ivldCompanyTradeClassImpl.setErrorField(ivldCompanyTradeClass.getErrorField());
		ivldCompanyTradeClassImpl.setErrorCode(ivldCompanyTradeClass.getErrorCode());
		ivldCompanyTradeClassImpl.setTradeClass(ivldCompanyTradeClass.getTradeClass());
		ivldCompanyTradeClassImpl.setIntfInsertedDate(ivldCompanyTradeClass.getIntfInsertedDate());
		ivldCompanyTradeClassImpl.setModifiedBy(ivldCompanyTradeClass.getModifiedBy());
		ivldCompanyTradeClassImpl.setReprocessedFlag(ivldCompanyTradeClass.getReprocessedFlag());
		ivldCompanyTradeClassImpl.setCheckRecord(ivldCompanyTradeClass.isCheckRecord());

		return ivldCompanyTradeClassImpl;
	}

	/**
	 * Returns the ivld company trade class with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company trade class
	 * @return the ivld company trade class
	 * @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCompanyTradeClassException {
		IvldCompanyTradeClass ivldCompanyTradeClass = fetchByPrimaryKey(primaryKey);

		if (ivldCompanyTradeClass == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCompanyTradeClass;
	}

	/**
	 * Returns the ivld company trade class with the primary key or throws a {@link NoSuchIvldCompanyTradeClassException} if it could not be found.
	 *
	 * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	 * @return the ivld company trade class
	 * @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass findByPrimaryKey(int ivldCompanyTradeClassSid)
		throws NoSuchIvldCompanyTradeClassException {
		return findByPrimaryKey((Serializable)ivldCompanyTradeClassSid);
	}

	/**
	 * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company trade class
	 * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyTradeClassImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCompanyTradeClass ivldCompanyTradeClass = (IvldCompanyTradeClass)serializable;

		if (ivldCompanyTradeClass == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCompanyTradeClass = (IvldCompanyTradeClass)session.get(IvldCompanyTradeClassImpl.class,
						primaryKey);

				if (ivldCompanyTradeClass != null) {
					cacheResult(ivldCompanyTradeClass);
				}
				else {
					entityCache.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyTradeClassImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyTradeClassImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCompanyTradeClass;
	}

	/**
	 * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	 * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
	 */
	@Override
	public IvldCompanyTradeClass fetchByPrimaryKey(int ivldCompanyTradeClassSid) {
		return fetchByPrimaryKey((Serializable)ivldCompanyTradeClassSid);
	}

	@Override
	public Map<Serializable, IvldCompanyTradeClass> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCompanyTradeClass> map = new HashMap<Serializable, IvldCompanyTradeClass>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCompanyTradeClass ivldCompanyTradeClass = fetchByPrimaryKey(primaryKey);

			if (ivldCompanyTradeClass != null) {
				map.put(primaryKey, ivldCompanyTradeClass);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyTradeClassImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCompanyTradeClass)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCOMPANYTRADECLASS_WHERE_PKS_IN);

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

			for (IvldCompanyTradeClass ivldCompanyTradeClass : (List<IvldCompanyTradeClass>)q.list()) {
				map.put(ivldCompanyTradeClass.getPrimaryKeyObj(),
					ivldCompanyTradeClass);

				cacheResult(ivldCompanyTradeClass);

				uncachedPrimaryKeys.remove(ivldCompanyTradeClass.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyTradeClassImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld company trade classes.
	 *
	 * @return the ivld company trade classes
	 */
	@Override
	public List<IvldCompanyTradeClass> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company trade classes
	 * @param end the upper bound of the range of ivld company trade classes (not inclusive)
	 * @return the range of ivld company trade classes
	 */
	@Override
	public List<IvldCompanyTradeClass> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company trade classes
	 * @param end the upper bound of the range of ivld company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld company trade classes
	 */
	@Override
	public List<IvldCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<IvldCompanyTradeClass> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company trade classes
	 * @param end the upper bound of the range of ivld company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld company trade classes
	 */
	@Override
	public List<IvldCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<IvldCompanyTradeClass> orderByComparator,
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

		List<IvldCompanyTradeClass> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCompanyTradeClass>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCOMPANYTRADECLASS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCOMPANYTRADECLASS;

				if (pagination) {
					sql = sql.concat(IvldCompanyTradeClassModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCompanyTradeClass>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCompanyTradeClass>)QueryUtil.list(q,
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
	 * Removes all the ivld company trade classes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCompanyTradeClass ivldCompanyTradeClass : findAll()) {
			remove(ivldCompanyTradeClass);
		}
	}

	/**
	 * Returns the number of ivld company trade classes.
	 *
	 * @return the number of ivld company trade classes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYTRADECLASS);

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
		return IvldCompanyTradeClassModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld company trade class persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCompanyTradeClassImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCOMPANYTRADECLASS = "SELECT ivldCompanyTradeClass FROM IvldCompanyTradeClass ivldCompanyTradeClass";
	private static final String _SQL_SELECT_IVLDCOMPANYTRADECLASS_WHERE_PKS_IN = "SELECT ivldCompanyTradeClass FROM IvldCompanyTradeClass ivldCompanyTradeClass WHERE IVLD_COMPANY_TRADE_CLASS_SID IN (";
	private static final String _SQL_COUNT_IVLDCOMPANYTRADECLASS = "SELECT COUNT(ivldCompanyTradeClass) FROM IvldCompanyTradeClass ivldCompanyTradeClass";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyTradeClass.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyTradeClass exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCompanyTradeClassPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ivldCompanyTradeClassSid", "priorTradeClass",
				"reasonForFailure", "companyIdString", "lastUpdatedDate",
				"priorTradeClassStartDate", "modifiedDate", "tradeClassEndDate",
				"tradeClassIntfid", "tradeClassStartDate", "source", "createdBy",
				"createdDate", "addChgDelIndicator", "batchId", "errorField",
				"errorCode", "tradeClass", "intfInsertedDate", "modifiedBy",
				"reprocessedFlag", "checkRecord"
			});
}