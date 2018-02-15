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

import com.stpl.app.exception.NoSuchUsergroupDomainMasterException;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.model.impl.UsergroupDomainMasterImpl;
import com.stpl.app.model.impl.UsergroupDomainMasterModelImpl;
import com.stpl.app.service.persistence.UsergroupDomainMasterPersistence;

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
 * The persistence implementation for the usergroup domain master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupDomainMasterPersistence
 * @see com.stpl.app.service.persistence.UsergroupDomainMasterUtil
 * @generated
 */
@ProviderType
public class UsergroupDomainMasterPersistenceImpl extends BasePersistenceImpl<UsergroupDomainMaster>
	implements UsergroupDomainMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UsergroupDomainMasterUtil} to access the usergroup domain master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UsergroupDomainMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED,
			UsergroupDomainMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED,
			UsergroupDomainMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public UsergroupDomainMasterPersistenceImpl() {
		setModelClass(UsergroupDomainMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usergroupDomainSid", "USERGROUP_DOMAIN_SID");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("domainId", "DOMAIN_ID");
			dbColumnNames.put("processed", "PROCESSED");
			dbColumnNames.put("usergroupId", "USERGROUP_ID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("isActive", "IS_ACTIVE");
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
	 * Caches the usergroup domain master in the entity cache if it is enabled.
	 *
	 * @param usergroupDomainMaster the usergroup domain master
	 */
	@Override
	public void cacheResult(UsergroupDomainMaster usergroupDomainMaster) {
		entityCache.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterImpl.class,
			usergroupDomainMaster.getPrimaryKey(), usergroupDomainMaster);

		usergroupDomainMaster.resetOriginalValues();
	}

	/**
	 * Caches the usergroup domain masters in the entity cache if it is enabled.
	 *
	 * @param usergroupDomainMasters the usergroup domain masters
	 */
	@Override
	public void cacheResult(List<UsergroupDomainMaster> usergroupDomainMasters) {
		for (UsergroupDomainMaster usergroupDomainMaster : usergroupDomainMasters) {
			if (entityCache.getResult(
						UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
						UsergroupDomainMasterImpl.class,
						usergroupDomainMaster.getPrimaryKey()) == null) {
				cacheResult(usergroupDomainMaster);
			}
			else {
				usergroupDomainMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all usergroup domain masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UsergroupDomainMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the usergroup domain master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UsergroupDomainMaster usergroupDomainMaster) {
		entityCache.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterImpl.class,
			usergroupDomainMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UsergroupDomainMaster> usergroupDomainMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UsergroupDomainMaster usergroupDomainMaster : usergroupDomainMasters) {
			entityCache.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
				UsergroupDomainMasterImpl.class,
				usergroupDomainMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
	 *
	 * @param usergroupDomainSid the primary key for the new usergroup domain master
	 * @return the new usergroup domain master
	 */
	@Override
	public UsergroupDomainMaster create(int usergroupDomainSid) {
		UsergroupDomainMaster usergroupDomainMaster = new UsergroupDomainMasterImpl();

		usergroupDomainMaster.setNew(true);
		usergroupDomainMaster.setPrimaryKey(usergroupDomainSid);

		return usergroupDomainMaster;
	}

	/**
	 * Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param usergroupDomainSid the primary key of the usergroup domain master
	 * @return the usergroup domain master that was removed
	 * @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster remove(int usergroupDomainSid)
		throws NoSuchUsergroupDomainMasterException {
		return remove((Serializable)usergroupDomainSid);
	}

	/**
	 * Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the usergroup domain master
	 * @return the usergroup domain master that was removed
	 * @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster remove(Serializable primaryKey)
		throws NoSuchUsergroupDomainMasterException {
		Session session = null;

		try {
			session = openSession();

			UsergroupDomainMaster usergroupDomainMaster = (UsergroupDomainMaster)session.get(UsergroupDomainMasterImpl.class,
					primaryKey);

			if (usergroupDomainMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUsergroupDomainMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(usergroupDomainMaster);
		}
		catch (NoSuchUsergroupDomainMasterException nsee) {
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
	protected UsergroupDomainMaster removeImpl(
		UsergroupDomainMaster usergroupDomainMaster) {
		usergroupDomainMaster = toUnwrappedModel(usergroupDomainMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(usergroupDomainMaster)) {
				usergroupDomainMaster = (UsergroupDomainMaster)session.get(UsergroupDomainMasterImpl.class,
						usergroupDomainMaster.getPrimaryKeyObj());
			}

			if (usergroupDomainMaster != null) {
				session.delete(usergroupDomainMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (usergroupDomainMaster != null) {
			clearCache(usergroupDomainMaster);
		}

		return usergroupDomainMaster;
	}

	@Override
	public UsergroupDomainMaster updateImpl(
		UsergroupDomainMaster usergroupDomainMaster) {
		usergroupDomainMaster = toUnwrappedModel(usergroupDomainMaster);

		boolean isNew = usergroupDomainMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (usergroupDomainMaster.isNew()) {
				session.save(usergroupDomainMaster);

				usergroupDomainMaster.setNew(false);
			}
			else {
				usergroupDomainMaster = (UsergroupDomainMaster)session.merge(usergroupDomainMaster);
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

		entityCache.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupDomainMasterImpl.class,
			usergroupDomainMaster.getPrimaryKey(), usergroupDomainMaster, false);

		usergroupDomainMaster.resetOriginalValues();

		return usergroupDomainMaster;
	}

	protected UsergroupDomainMaster toUnwrappedModel(
		UsergroupDomainMaster usergroupDomainMaster) {
		if (usergroupDomainMaster instanceof UsergroupDomainMasterImpl) {
			return usergroupDomainMaster;
		}

		UsergroupDomainMasterImpl usergroupDomainMasterImpl = new UsergroupDomainMasterImpl();

		usergroupDomainMasterImpl.setNew(usergroupDomainMaster.isNew());
		usergroupDomainMasterImpl.setPrimaryKey(usergroupDomainMaster.getPrimaryKey());

		usergroupDomainMasterImpl.setCreatedBy(usergroupDomainMaster.getCreatedBy());
		usergroupDomainMasterImpl.setUsergroupDomainSid(usergroupDomainMaster.getUsergroupDomainSid());
		usergroupDomainMasterImpl.setUsersSid(usergroupDomainMaster.getUsersSid());
		usergroupDomainMasterImpl.setModifiedBy(usergroupDomainMaster.getModifiedBy());
		usergroupDomainMasterImpl.setCreatedDate(usergroupDomainMaster.getCreatedDate());
		usergroupDomainMasterImpl.setDomainId(usergroupDomainMaster.getDomainId());
		usergroupDomainMasterImpl.setProcessed(usergroupDomainMaster.getProcessed());
		usergroupDomainMasterImpl.setUsergroupId(usergroupDomainMaster.getUsergroupId());
		usergroupDomainMasterImpl.setVersionNo(usergroupDomainMaster.getVersionNo());
		usergroupDomainMasterImpl.setIsActive(usergroupDomainMaster.getIsActive());
		usergroupDomainMasterImpl.setModifiedDate(usergroupDomainMaster.getModifiedDate());

		return usergroupDomainMasterImpl;
	}

	/**
	 * Returns the usergroup domain master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the usergroup domain master
	 * @return the usergroup domain master
	 * @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUsergroupDomainMasterException {
		UsergroupDomainMaster usergroupDomainMaster = fetchByPrimaryKey(primaryKey);

		if (usergroupDomainMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUsergroupDomainMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return usergroupDomainMaster;
	}

	/**
	 * Returns the usergroup domain master with the primary key or throws a {@link NoSuchUsergroupDomainMasterException} if it could not be found.
	 *
	 * @param usergroupDomainSid the primary key of the usergroup domain master
	 * @return the usergroup domain master
	 * @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster findByPrimaryKey(int usergroupDomainSid)
		throws NoSuchUsergroupDomainMasterException {
		return findByPrimaryKey((Serializable)usergroupDomainSid);
	}

	/**
	 * Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the usergroup domain master
	 * @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
				UsergroupDomainMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UsergroupDomainMaster usergroupDomainMaster = (UsergroupDomainMaster)serializable;

		if (usergroupDomainMaster == null) {
			Session session = null;

			try {
				session = openSession();

				usergroupDomainMaster = (UsergroupDomainMaster)session.get(UsergroupDomainMasterImpl.class,
						primaryKey);

				if (usergroupDomainMaster != null) {
					cacheResult(usergroupDomainMaster);
				}
				else {
					entityCache.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
						UsergroupDomainMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupDomainMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return usergroupDomainMaster;
	}

	/**
	 * Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param usergroupDomainSid the primary key of the usergroup domain master
	 * @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
	 */
	@Override
	public UsergroupDomainMaster fetchByPrimaryKey(int usergroupDomainSid) {
		return fetchByPrimaryKey((Serializable)usergroupDomainSid);
	}

	@Override
	public Map<Serializable, UsergroupDomainMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UsergroupDomainMaster> map = new HashMap<Serializable, UsergroupDomainMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UsergroupDomainMaster usergroupDomainMaster = fetchByPrimaryKey(primaryKey);

			if (usergroupDomainMaster != null) {
				map.put(primaryKey, usergroupDomainMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupDomainMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UsergroupDomainMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERGROUPDOMAINMASTER_WHERE_PKS_IN);

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

			for (UsergroupDomainMaster usergroupDomainMaster : (List<UsergroupDomainMaster>)q.list()) {
				map.put(usergroupDomainMaster.getPrimaryKeyObj(),
					usergroupDomainMaster);

				cacheResult(usergroupDomainMaster);

				uncachedPrimaryKeys.remove(usergroupDomainMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupDomainMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the usergroup domain masters.
	 *
	 * @return the usergroup domain masters
	 */
	@Override
	public List<UsergroupDomainMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the usergroup domain masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup domain masters
	 * @param end the upper bound of the range of usergroup domain masters (not inclusive)
	 * @return the range of usergroup domain masters
	 */
	@Override
	public List<UsergroupDomainMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the usergroup domain masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup domain masters
	 * @param end the upper bound of the range of usergroup domain masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of usergroup domain masters
	 */
	@Override
	public List<UsergroupDomainMaster> findAll(int start, int end,
		OrderByComparator<UsergroupDomainMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the usergroup domain masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup domain masters
	 * @param end the upper bound of the range of usergroup domain masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of usergroup domain masters
	 */
	@Override
	public List<UsergroupDomainMaster> findAll(int start, int end,
		OrderByComparator<UsergroupDomainMaster> orderByComparator,
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

		List<UsergroupDomainMaster> list = null;

		if (retrieveFromCache) {
			list = (List<UsergroupDomainMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERGROUPDOMAINMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERGROUPDOMAINMASTER;

				if (pagination) {
					sql = sql.concat(UsergroupDomainMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UsergroupDomainMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UsergroupDomainMaster>)QueryUtil.list(q,
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
	 * Removes all the usergroup domain masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UsergroupDomainMaster usergroupDomainMaster : findAll()) {
			remove(usergroupDomainMaster);
		}
	}

	/**
	 * Returns the number of usergroup domain masters.
	 *
	 * @return the number of usergroup domain masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERGROUPDOMAINMASTER);

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
		return UsergroupDomainMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the usergroup domain master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UsergroupDomainMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERGROUPDOMAINMASTER = "SELECT usergroupDomainMaster FROM UsergroupDomainMaster usergroupDomainMaster";
	private static final String _SQL_SELECT_USERGROUPDOMAINMASTER_WHERE_PKS_IN = "SELECT usergroupDomainMaster FROM UsergroupDomainMaster usergroupDomainMaster WHERE USERGROUP_DOMAIN_SID IN (";
	private static final String _SQL_COUNT_USERGROUPDOMAINMASTER = "SELECT COUNT(usergroupDomainMaster) FROM UsergroupDomainMaster usergroupDomainMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "usergroupDomainMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UsergroupDomainMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(UsergroupDomainMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "usergroupDomainSid", "usersSid", "modifiedBy",
				"createdDate", "domainId", "processed", "usergroupId",
				"versionNo", "isActive", "modifiedDate"
			});
}