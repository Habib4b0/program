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

import com.stpl.app.exception.NoSuchBusinessroleModuleException;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.model.impl.BusinessroleModuleImpl;
import com.stpl.app.model.impl.BusinessroleModuleModelImpl;
import com.stpl.app.service.persistence.BusinessroleModulePersistence;

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
 * The persistence implementation for the businessrole module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleModulePersistence
 * @see com.stpl.app.service.persistence.BusinessroleModuleUtil
 * @generated
 */
@ProviderType
public class BusinessroleModulePersistenceImpl extends BasePersistenceImpl<BusinessroleModule>
	implements BusinessroleModulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BusinessroleModuleUtil} to access the businessrole module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BusinessroleModuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public BusinessroleModulePersistenceImpl() {
		setModelClass(BusinessroleModule.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("businessroleModuleSid", "BUSINESSROLE_MODULE_SID");
			dbColumnNames.put("businessroleMasterSid", "BUSINESSROLE_MASTER_SID");
			dbColumnNames.put("addFlag", "ADD_FLAG");
			dbColumnNames.put("viewFlag", "VIEW_FLAG");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("submodulePropertyId", "SUBMODULE_PROPERTY_ID");
			dbColumnNames.put("editFlag", "EDIT_FLAG");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("accessModule", "ACCESS_MODULE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the businessrole module in the entity cache if it is enabled.
	 *
	 * @param businessroleModule the businessrole module
	 */
	@Override
	public void cacheResult(BusinessroleModule businessroleModule) {
		entityCache.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey(),
			businessroleModule);

		businessroleModule.resetOriginalValues();
	}

	/**
	 * Caches the businessrole modules in the entity cache if it is enabled.
	 *
	 * @param businessroleModules the businessrole modules
	 */
	@Override
	public void cacheResult(List<BusinessroleModule> businessroleModules) {
		for (BusinessroleModule businessroleModule : businessroleModules) {
			if (entityCache.getResult(
						BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
						BusinessroleModuleImpl.class,
						businessroleModule.getPrimaryKey()) == null) {
				cacheResult(businessroleModule);
			}
			else {
				businessroleModule.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all businessrole modules.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BusinessroleModuleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the businessrole module.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BusinessroleModule businessroleModule) {
		entityCache.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BusinessroleModule> businessroleModules) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BusinessroleModule businessroleModule : businessroleModules) {
			entityCache.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
				BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey());
		}
	}

	/**
	 * Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
	 *
	 * @param businessroleModuleSid the primary key for the new businessrole module
	 * @return the new businessrole module
	 */
	@Override
	public BusinessroleModule create(int businessroleModuleSid) {
		BusinessroleModule businessroleModule = new BusinessroleModuleImpl();

		businessroleModule.setNew(true);
		businessroleModule.setPrimaryKey(businessroleModuleSid);

		return businessroleModule;
	}

	/**
	 * Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param businessroleModuleSid the primary key of the businessrole module
	 * @return the businessrole module that was removed
	 * @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule remove(int businessroleModuleSid)
		throws NoSuchBusinessroleModuleException {
		return remove((Serializable)businessroleModuleSid);
	}

	/**
	 * Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the businessrole module
	 * @return the businessrole module that was removed
	 * @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule remove(Serializable primaryKey)
		throws NoSuchBusinessroleModuleException {
		Session session = null;

		try {
			session = openSession();

			BusinessroleModule businessroleModule = (BusinessroleModule)session.get(BusinessroleModuleImpl.class,
					primaryKey);

			if (businessroleModule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBusinessroleModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(businessroleModule);
		}
		catch (NoSuchBusinessroleModuleException nsee) {
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
	protected BusinessroleModule removeImpl(
		BusinessroleModule businessroleModule) {
		businessroleModule = toUnwrappedModel(businessroleModule);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(businessroleModule)) {
				businessroleModule = (BusinessroleModule)session.get(BusinessroleModuleImpl.class,
						businessroleModule.getPrimaryKeyObj());
			}

			if (businessroleModule != null) {
				session.delete(businessroleModule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (businessroleModule != null) {
			clearCache(businessroleModule);
		}

		return businessroleModule;
	}

	@Override
	public BusinessroleModule updateImpl(BusinessroleModule businessroleModule) {
		businessroleModule = toUnwrappedModel(businessroleModule);

		boolean isNew = businessroleModule.isNew();

		Session session = null;

		try {
			session = openSession();

			if (businessroleModule.isNew()) {
				session.save(businessroleModule);

				businessroleModule.setNew(false);
			}
			else {
				businessroleModule = (BusinessroleModule)session.merge(businessroleModule);
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

		entityCache.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey(),
			businessroleModule, false);

		businessroleModule.resetOriginalValues();

		return businessroleModule;
	}

	protected BusinessroleModule toUnwrappedModel(
		BusinessroleModule businessroleModule) {
		if (businessroleModule instanceof BusinessroleModuleImpl) {
			return businessroleModule;
		}

		BusinessroleModuleImpl businessroleModuleImpl = new BusinessroleModuleImpl();

		businessroleModuleImpl.setNew(businessroleModule.isNew());
		businessroleModuleImpl.setPrimaryKey(businessroleModule.getPrimaryKey());

		businessroleModuleImpl.setCreatedBy(businessroleModule.getCreatedBy());
		businessroleModuleImpl.setBusinessroleModuleSid(businessroleModule.getBusinessroleModuleSid());
		businessroleModuleImpl.setBusinessroleMasterSid(businessroleModule.getBusinessroleMasterSid());
		businessroleModuleImpl.setAddFlag(businessroleModule.getAddFlag());
		businessroleModuleImpl.setViewFlag(businessroleModule.getViewFlag());
		businessroleModuleImpl.setModifiedBy(businessroleModule.getModifiedBy());
		businessroleModuleImpl.setCreatedDate(businessroleModule.getCreatedDate());
		businessroleModuleImpl.setSubmodulePropertyId(businessroleModule.getSubmodulePropertyId());
		businessroleModuleImpl.setEditFlag(businessroleModule.getEditFlag());
		businessroleModuleImpl.setVersionNo(businessroleModule.getVersionNo());
		businessroleModuleImpl.setAccessModule(businessroleModule.getAccessModule());
		businessroleModuleImpl.setModifiedDate(businessroleModule.getModifiedDate());

		return businessroleModuleImpl;
	}

	/**
	 * Returns the businessrole module with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the businessrole module
	 * @return the businessrole module
	 * @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBusinessroleModuleException {
		BusinessroleModule businessroleModule = fetchByPrimaryKey(primaryKey);

		if (businessroleModule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBusinessroleModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return businessroleModule;
	}

	/**
	 * Returns the businessrole module with the primary key or throws a {@link NoSuchBusinessroleModuleException} if it could not be found.
	 *
	 * @param businessroleModuleSid the primary key of the businessrole module
	 * @return the businessrole module
	 * @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule findByPrimaryKey(int businessroleModuleSid)
		throws NoSuchBusinessroleModuleException {
		return findByPrimaryKey((Serializable)businessroleModuleSid);
	}

	/**
	 * Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the businessrole module
	 * @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
				BusinessroleModuleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BusinessroleModule businessroleModule = (BusinessroleModule)serializable;

		if (businessroleModule == null) {
			Session session = null;

			try {
				session = openSession();

				businessroleModule = (BusinessroleModule)session.get(BusinessroleModuleImpl.class,
						primaryKey);

				if (businessroleModule != null) {
					cacheResult(businessroleModule);
				}
				else {
					entityCache.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
						BusinessroleModuleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleModuleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return businessroleModule;
	}

	/**
	 * Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param businessroleModuleSid the primary key of the businessrole module
	 * @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
	 */
	@Override
	public BusinessroleModule fetchByPrimaryKey(int businessroleModuleSid) {
		return fetchByPrimaryKey((Serializable)businessroleModuleSid);
	}

	@Override
	public Map<Serializable, BusinessroleModule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BusinessroleModule> map = new HashMap<Serializable, BusinessroleModule>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BusinessroleModule businessroleModule = fetchByPrimaryKey(primaryKey);

			if (businessroleModule != null) {
				map.put(primaryKey, businessroleModule);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleModuleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BusinessroleModule)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BUSINESSROLEMODULE_WHERE_PKS_IN);

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

			for (BusinessroleModule businessroleModule : (List<BusinessroleModule>)q.list()) {
				map.put(businessroleModule.getPrimaryKeyObj(),
					businessroleModule);

				cacheResult(businessroleModule);

				uncachedPrimaryKeys.remove(businessroleModule.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleModuleImpl.class, primaryKey, nullModel);
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
	 * Returns all the businessrole modules.
	 *
	 * @return the businessrole modules
	 */
	@Override
	public List<BusinessroleModule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the businessrole modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole modules
	 * @param end the upper bound of the range of businessrole modules (not inclusive)
	 * @return the range of businessrole modules
	 */
	@Override
	public List<BusinessroleModule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the businessrole modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole modules
	 * @param end the upper bound of the range of businessrole modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of businessrole modules
	 */
	@Override
	public List<BusinessroleModule> findAll(int start, int end,
		OrderByComparator<BusinessroleModule> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the businessrole modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole modules
	 * @param end the upper bound of the range of businessrole modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of businessrole modules
	 */
	@Override
	public List<BusinessroleModule> findAll(int start, int end,
		OrderByComparator<BusinessroleModule> orderByComparator,
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

		List<BusinessroleModule> list = null;

		if (retrieveFromCache) {
			list = (List<BusinessroleModule>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BUSINESSROLEMODULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BUSINESSROLEMODULE;

				if (pagination) {
					sql = sql.concat(BusinessroleModuleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BusinessroleModule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BusinessroleModule>)QueryUtil.list(q,
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
	 * Removes all the businessrole modules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BusinessroleModule businessroleModule : findAll()) {
			remove(businessroleModule);
		}
	}

	/**
	 * Returns the number of businessrole modules.
	 *
	 * @return the number of businessrole modules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BUSINESSROLEMODULE);

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
		return BusinessroleModuleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the businessrole module persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BusinessroleModuleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_BUSINESSROLEMODULE = "SELECT businessroleModule FROM BusinessroleModule businessroleModule";
	private static final String _SQL_SELECT_BUSINESSROLEMODULE_WHERE_PKS_IN = "SELECT businessroleModule FROM BusinessroleModule businessroleModule WHERE BUSINESSROLE_MODULE_SID IN (";
	private static final String _SQL_COUNT_BUSINESSROLEMODULE = "SELECT COUNT(businessroleModule) FROM BusinessroleModule businessroleModule";
	private static final String _ORDER_BY_ENTITY_ALIAS = "businessroleModule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BusinessroleModule exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(BusinessroleModulePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "businessroleModuleSid", "businessroleMasterSid",
				"addFlag", "viewFlag", "modifiedBy", "createdDate",
				"submodulePropertyId", "editFlag", "versionNo", "accessModule",
				"modifiedDate"
			});
}