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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyTradeClassException;
import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.model.impl.VwCompanyTradeClassImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassPersistence;

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
 * The persistence implementation for the vw company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassUtil
 * @generated
 */
@ProviderType
public class VwCompanyTradeClassPersistenceImpl extends BasePersistenceImpl<VwCompanyTradeClass>
	implements VwCompanyTradeClassPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwCompanyTradeClassUtil} to access the vw company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyTradeClassImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
			VwCompanyTradeClassImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwCompanyTradeClassPersistenceImpl() {
		setModelClass(VwCompanyTradeClass.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("priorTradeClass", "PRIOR_TRADE_CLASS");
			dbColumnNames.put("companyTradeClassSid", "COMPANY_TRADE_CLASS_SID");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("priorTradeClassStartDate",
				"PRIOR_TRADE_CLASS_START_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
			dbColumnNames.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("companyTradeClass", "COMPANY_TRADE_CLASS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw company trade class in the entity cache if it is enabled.
	 *
	 * @param vwCompanyTradeClass the vw company trade class
	 */
	@Override
	public void cacheResult(VwCompanyTradeClass vwCompanyTradeClass) {
		entityCache.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey(),
			vwCompanyTradeClass);

		vwCompanyTradeClass.resetOriginalValues();
	}

	/**
	 * Caches the vw company trade classes in the entity cache if it is enabled.
	 *
	 * @param vwCompanyTradeClasses the vw company trade classes
	 */
	@Override
	public void cacheResult(List<VwCompanyTradeClass> vwCompanyTradeClasses) {
		for (VwCompanyTradeClass vwCompanyTradeClass : vwCompanyTradeClasses) {
			if (entityCache.getResult(
						VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyTradeClassImpl.class,
						vwCompanyTradeClass.getPrimaryKey()) == null) {
				cacheResult(vwCompanyTradeClass);
			}
			else {
				vwCompanyTradeClass.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw company trade classes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwCompanyTradeClassImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw company trade class.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwCompanyTradeClass vwCompanyTradeClass) {
		entityCache.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwCompanyTradeClass> vwCompanyTradeClasses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwCompanyTradeClass vwCompanyTradeClass : vwCompanyTradeClasses) {
			entityCache.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyTradeClassImpl.class,
				vwCompanyTradeClass.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
	 *
	 * @param companyTradeClassSid the primary key for the new vw company trade class
	 * @return the new vw company trade class
	 */
	@Override
	public VwCompanyTradeClass create(int companyTradeClassSid) {
		VwCompanyTradeClass vwCompanyTradeClass = new VwCompanyTradeClassImpl();

		vwCompanyTradeClass.setNew(true);
		vwCompanyTradeClass.setPrimaryKey(companyTradeClassSid);

		vwCompanyTradeClass.setCompanyId(companyProvider.getCompanyId());

		return vwCompanyTradeClass;
	}

	/**
	 * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyTradeClassSid the primary key of the vw company trade class
	 * @return the vw company trade class that was removed
	 * @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass remove(int companyTradeClassSid)
		throws NoSuchVwCompanyTradeClassException {
		return remove((Serializable)companyTradeClassSid);
	}

	/**
	 * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw company trade class
	 * @return the vw company trade class that was removed
	 * @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass remove(Serializable primaryKey)
		throws NoSuchVwCompanyTradeClassException {
		Session session = null;

		try {
			session = openSession();

			VwCompanyTradeClass vwCompanyTradeClass = (VwCompanyTradeClass)session.get(VwCompanyTradeClassImpl.class,
					primaryKey);

			if (vwCompanyTradeClass == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwCompanyTradeClass);
		}
		catch (NoSuchVwCompanyTradeClassException nsee) {
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
	protected VwCompanyTradeClass removeImpl(
		VwCompanyTradeClass vwCompanyTradeClass) {
		vwCompanyTradeClass = toUnwrappedModel(vwCompanyTradeClass);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwCompanyTradeClass)) {
				vwCompanyTradeClass = (VwCompanyTradeClass)session.get(VwCompanyTradeClassImpl.class,
						vwCompanyTradeClass.getPrimaryKeyObj());
			}

			if (vwCompanyTradeClass != null) {
				session.delete(vwCompanyTradeClass);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwCompanyTradeClass != null) {
			clearCache(vwCompanyTradeClass);
		}

		return vwCompanyTradeClass;
	}

	@Override
	public VwCompanyTradeClass updateImpl(
		VwCompanyTradeClass vwCompanyTradeClass) {
		vwCompanyTradeClass = toUnwrappedModel(vwCompanyTradeClass);

		boolean isNew = vwCompanyTradeClass.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwCompanyTradeClass.isNew()) {
				session.save(vwCompanyTradeClass);

				vwCompanyTradeClass.setNew(false);
			}
			else {
				vwCompanyTradeClass = (VwCompanyTradeClass)session.merge(vwCompanyTradeClass);
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

		entityCache.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
			VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey(),
			vwCompanyTradeClass, false);

		vwCompanyTradeClass.resetOriginalValues();

		return vwCompanyTradeClass;
	}

	protected VwCompanyTradeClass toUnwrappedModel(
		VwCompanyTradeClass vwCompanyTradeClass) {
		if (vwCompanyTradeClass instanceof VwCompanyTradeClassImpl) {
			return vwCompanyTradeClass;
		}

		VwCompanyTradeClassImpl vwCompanyTradeClassImpl = new VwCompanyTradeClassImpl();

		vwCompanyTradeClassImpl.setNew(vwCompanyTradeClass.isNew());
		vwCompanyTradeClassImpl.setPrimaryKey(vwCompanyTradeClass.getPrimaryKey());

		vwCompanyTradeClassImpl.setPriorTradeClass(vwCompanyTradeClass.getPriorTradeClass());
		vwCompanyTradeClassImpl.setCompanyTradeClassSid(vwCompanyTradeClass.getCompanyTradeClassSid());
		vwCompanyTradeClassImpl.setCompanyId(vwCompanyTradeClass.getCompanyId());
		vwCompanyTradeClassImpl.setLastUpdatedDate(vwCompanyTradeClass.getLastUpdatedDate());
		vwCompanyTradeClassImpl.setPriorTradeClassStartDate(vwCompanyTradeClass.getPriorTradeClassStartDate());
		vwCompanyTradeClassImpl.setModifiedDate(vwCompanyTradeClass.getModifiedDate());
		vwCompanyTradeClassImpl.setTradeClassEndDate(vwCompanyTradeClass.getTradeClassEndDate());
		vwCompanyTradeClassImpl.setTradeClassStartDate(vwCompanyTradeClass.getTradeClassStartDate());
		vwCompanyTradeClassImpl.setSource(vwCompanyTradeClass.getSource());
		vwCompanyTradeClassImpl.setCreatedBy(vwCompanyTradeClass.getCreatedBy());
		vwCompanyTradeClassImpl.setCreatedDate(vwCompanyTradeClass.getCreatedDate());
		vwCompanyTradeClassImpl.setCompanyTradeClass(vwCompanyTradeClass.getCompanyTradeClass());
		vwCompanyTradeClassImpl.setBatchId(vwCompanyTradeClass.getBatchId());
		vwCompanyTradeClassImpl.setAddChgDelIndicator(vwCompanyTradeClass.getAddChgDelIndicator());
		vwCompanyTradeClassImpl.setModifiedBy(vwCompanyTradeClass.getModifiedBy());

		return vwCompanyTradeClassImpl;
	}

	/**
	 * Returns the vw company trade class with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company trade class
	 * @return the vw company trade class
	 * @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwCompanyTradeClassException {
		VwCompanyTradeClass vwCompanyTradeClass = fetchByPrimaryKey(primaryKey);

		if (vwCompanyTradeClass == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwCompanyTradeClass;
	}

	/**
	 * Returns the vw company trade class with the primary key or throws a {@link NoSuchVwCompanyTradeClassException} if it could not be found.
	 *
	 * @param companyTradeClassSid the primary key of the vw company trade class
	 * @return the vw company trade class
	 * @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
		throws NoSuchVwCompanyTradeClassException {
		return findByPrimaryKey((Serializable)companyTradeClassSid);
	}

	/**
	 * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw company trade class
	 * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
				VwCompanyTradeClassImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwCompanyTradeClass vwCompanyTradeClass = (VwCompanyTradeClass)serializable;

		if (vwCompanyTradeClass == null) {
			Session session = null;

			try {
				session = openSession();

				vwCompanyTradeClass = (VwCompanyTradeClass)session.get(VwCompanyTradeClassImpl.class,
						primaryKey);

				if (vwCompanyTradeClass != null) {
					cacheResult(vwCompanyTradeClass);
				}
				else {
					entityCache.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
						VwCompanyTradeClassImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyTradeClassImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwCompanyTradeClass;
	}

	/**
	 * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyTradeClassSid the primary key of the vw company trade class
	 * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
	 */
	@Override
	public VwCompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid) {
		return fetchByPrimaryKey((Serializable)companyTradeClassSid);
	}

	@Override
	public Map<Serializable, VwCompanyTradeClass> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwCompanyTradeClass> map = new HashMap<Serializable, VwCompanyTradeClass>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwCompanyTradeClass vwCompanyTradeClass = fetchByPrimaryKey(primaryKey);

			if (vwCompanyTradeClass != null) {
				map.put(primaryKey, vwCompanyTradeClass);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyTradeClassImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwCompanyTradeClass)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWCOMPANYTRADECLASS_WHERE_PKS_IN);

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

			for (VwCompanyTradeClass vwCompanyTradeClass : (List<VwCompanyTradeClass>)q.list()) {
				map.put(vwCompanyTradeClass.getPrimaryKeyObj(),
					vwCompanyTradeClass);

				cacheResult(vwCompanyTradeClass);

				uncachedPrimaryKeys.remove(vwCompanyTradeClass.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
					VwCompanyTradeClassImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw company trade classes.
	 *
	 * @return the vw company trade classes
	 */
	@Override
	public List<VwCompanyTradeClass> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company trade classes
	 * @param end the upper bound of the range of vw company trade classes (not inclusive)
	 * @return the range of vw company trade classes
	 */
	@Override
	public List<VwCompanyTradeClass> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company trade classes
	 * @param end the upper bound of the range of vw company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw company trade classes
	 */
	@Override
	public List<VwCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<VwCompanyTradeClass> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw company trade classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw company trade classes
	 * @param end the upper bound of the range of vw company trade classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw company trade classes
	 */
	@Override
	public List<VwCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<VwCompanyTradeClass> orderByComparator,
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

		List<VwCompanyTradeClass> list = null;

		if (retrieveFromCache) {
			list = (List<VwCompanyTradeClass>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWCOMPANYTRADECLASS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWCOMPANYTRADECLASS;

				if (pagination) {
					sql = sql.concat(VwCompanyTradeClassModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwCompanyTradeClass>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwCompanyTradeClass>)QueryUtil.list(q,
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
	 * Removes all the vw company trade classes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwCompanyTradeClass vwCompanyTradeClass : findAll()) {
			remove(vwCompanyTradeClass);
		}
	}

	/**
	 * Returns the number of vw company trade classes.
	 *
	 * @return the number of vw company trade classes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWCOMPANYTRADECLASS);

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
		return VwCompanyTradeClassModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw company trade class persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwCompanyTradeClassImpl.class.getName());
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
	private static final String _SQL_SELECT_VWCOMPANYTRADECLASS = "SELECT vwCompanyTradeClass FROM VwCompanyTradeClass vwCompanyTradeClass";
	private static final String _SQL_SELECT_VWCOMPANYTRADECLASS_WHERE_PKS_IN = "SELECT vwCompanyTradeClass FROM VwCompanyTradeClass vwCompanyTradeClass WHERE COMPANY_TRADE_CLASS_SID IN (";
	private static final String _SQL_COUNT_VWCOMPANYTRADECLASS = "SELECT COUNT(vwCompanyTradeClass) FROM VwCompanyTradeClass vwCompanyTradeClass";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyTradeClass.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyTradeClass exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwCompanyTradeClassPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"priorTradeClass", "companyTradeClassSid", "companyId",
				"lastUpdatedDate", "priorTradeClassStartDate", "modifiedDate",
				"tradeClassEndDate", "tradeClassStartDate", "source",
				"createdBy", "createdDate", "companyTradeClass", "batchId",
				"addChgDelIndicator", "modifiedBy"
			});
}