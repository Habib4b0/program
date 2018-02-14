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

import com.stpl.app.exception.NoSuchUsergroupBusinessroleException;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.impl.UsergroupBusinessroleImpl;
import com.stpl.app.model.impl.UsergroupBusinessroleModelImpl;
import com.stpl.app.service.persistence.UsergroupBusinessrolePersistence;

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
 * The persistence implementation for the usergroup businessrole service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupBusinessrolePersistence
 * @see com.stpl.app.service.persistence.UsergroupBusinessroleUtil
 * @generated
 */
@ProviderType
public class UsergroupBusinessrolePersistenceImpl extends BasePersistenceImpl<UsergroupBusinessrole>
	implements UsergroupBusinessrolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UsergroupBusinessroleUtil} to access the usergroup businessrole persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UsergroupBusinessroleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED,
			UsergroupBusinessroleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED,
			UsergroupBusinessroleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public UsergroupBusinessrolePersistenceImpl() {
		setModelClass(UsergroupBusinessrole.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("businessroleMasterSid", "BUSINESSROLE_MASTER_SID");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("processed", "PROCESSED");
			dbColumnNames.put("usergroupId", "USERGROUP_ID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("usergroupBusinessroleSid",
				"USERGROUP_BUSINESSROLE_SID");
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
	 * Caches the usergroup businessrole in the entity cache if it is enabled.
	 *
	 * @param usergroupBusinessrole the usergroup businessrole
	 */
	@Override
	public void cacheResult(UsergroupBusinessrole usergroupBusinessrole) {
		entityCache.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleImpl.class,
			usergroupBusinessrole.getPrimaryKey(), usergroupBusinessrole);

		usergroupBusinessrole.resetOriginalValues();
	}

	/**
	 * Caches the usergroup businessroles in the entity cache if it is enabled.
	 *
	 * @param usergroupBusinessroles the usergroup businessroles
	 */
	@Override
	public void cacheResult(List<UsergroupBusinessrole> usergroupBusinessroles) {
		for (UsergroupBusinessrole usergroupBusinessrole : usergroupBusinessroles) {
			if (entityCache.getResult(
						UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
						UsergroupBusinessroleImpl.class,
						usergroupBusinessrole.getPrimaryKey()) == null) {
				cacheResult(usergroupBusinessrole);
			}
			else {
				usergroupBusinessrole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all usergroup businessroles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UsergroupBusinessroleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the usergroup businessrole.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UsergroupBusinessrole usergroupBusinessrole) {
		entityCache.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleImpl.class,
			usergroupBusinessrole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UsergroupBusinessrole> usergroupBusinessroles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UsergroupBusinessrole usergroupBusinessrole : usergroupBusinessroles) {
			entityCache.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
				UsergroupBusinessroleImpl.class,
				usergroupBusinessrole.getPrimaryKey());
		}
	}

	/**
	 * Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
	 *
	 * @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
	 * @return the new usergroup businessrole
	 */
	@Override
	public UsergroupBusinessrole create(int usergroupBusinessroleSid) {
		UsergroupBusinessrole usergroupBusinessrole = new UsergroupBusinessroleImpl();

		usergroupBusinessrole.setNew(true);
		usergroupBusinessrole.setPrimaryKey(usergroupBusinessroleSid);

		return usergroupBusinessrole;
	}

	/**
	 * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	 * @return the usergroup businessrole that was removed
	 * @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole remove(int usergroupBusinessroleSid)
		throws NoSuchUsergroupBusinessroleException {
		return remove((Serializable)usergroupBusinessroleSid);
	}

	/**
	 * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the usergroup businessrole
	 * @return the usergroup businessrole that was removed
	 * @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole remove(Serializable primaryKey)
		throws NoSuchUsergroupBusinessroleException {
		Session session = null;

		try {
			session = openSession();

			UsergroupBusinessrole usergroupBusinessrole = (UsergroupBusinessrole)session.get(UsergroupBusinessroleImpl.class,
					primaryKey);

			if (usergroupBusinessrole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUsergroupBusinessroleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(usergroupBusinessrole);
		}
		catch (NoSuchUsergroupBusinessroleException nsee) {
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
	protected UsergroupBusinessrole removeImpl(
		UsergroupBusinessrole usergroupBusinessrole) {
		usergroupBusinessrole = toUnwrappedModel(usergroupBusinessrole);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(usergroupBusinessrole)) {
				usergroupBusinessrole = (UsergroupBusinessrole)session.get(UsergroupBusinessroleImpl.class,
						usergroupBusinessrole.getPrimaryKeyObj());
			}

			if (usergroupBusinessrole != null) {
				session.delete(usergroupBusinessrole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (usergroupBusinessrole != null) {
			clearCache(usergroupBusinessrole);
		}

		return usergroupBusinessrole;
	}

	@Override
	public UsergroupBusinessrole updateImpl(
		UsergroupBusinessrole usergroupBusinessrole) {
		usergroupBusinessrole = toUnwrappedModel(usergroupBusinessrole);

		boolean isNew = usergroupBusinessrole.isNew();

		Session session = null;

		try {
			session = openSession();

			if (usergroupBusinessrole.isNew()) {
				session.save(usergroupBusinessrole);

				usergroupBusinessrole.setNew(false);
			}
			else {
				usergroupBusinessrole = (UsergroupBusinessrole)session.merge(usergroupBusinessrole);
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

		entityCache.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
			UsergroupBusinessroleImpl.class,
			usergroupBusinessrole.getPrimaryKey(), usergroupBusinessrole, false);

		usergroupBusinessrole.resetOriginalValues();

		return usergroupBusinessrole;
	}

	protected UsergroupBusinessrole toUnwrappedModel(
		UsergroupBusinessrole usergroupBusinessrole) {
		if (usergroupBusinessrole instanceof UsergroupBusinessroleImpl) {
			return usergroupBusinessrole;
		}

		UsergroupBusinessroleImpl usergroupBusinessroleImpl = new UsergroupBusinessroleImpl();

		usergroupBusinessroleImpl.setNew(usergroupBusinessrole.isNew());
		usergroupBusinessroleImpl.setPrimaryKey(usergroupBusinessrole.getPrimaryKey());

		usergroupBusinessroleImpl.setCreatedBy(usergroupBusinessrole.getCreatedBy());
		usergroupBusinessroleImpl.setBusinessroleMasterSid(usergroupBusinessrole.getBusinessroleMasterSid());
		usergroupBusinessroleImpl.setUsersSid(usergroupBusinessrole.getUsersSid());
		usergroupBusinessroleImpl.setModifiedBy(usergroupBusinessrole.getModifiedBy());
		usergroupBusinessroleImpl.setCreatedDate(usergroupBusinessrole.getCreatedDate());
		usergroupBusinessroleImpl.setProcessed(usergroupBusinessrole.getProcessed());
		usergroupBusinessroleImpl.setUsergroupId(usergroupBusinessrole.getUsergroupId());
		usergroupBusinessroleImpl.setVersionNo(usergroupBusinessrole.getVersionNo());
		usergroupBusinessroleImpl.setIsActive(usergroupBusinessrole.getIsActive());
		usergroupBusinessroleImpl.setUsergroupBusinessroleSid(usergroupBusinessrole.getUsergroupBusinessroleSid());
		usergroupBusinessroleImpl.setModifiedDate(usergroupBusinessrole.getModifiedDate());

		return usergroupBusinessroleImpl;
	}

	/**
	 * Returns the usergroup businessrole with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the usergroup businessrole
	 * @return the usergroup businessrole
	 * @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUsergroupBusinessroleException {
		UsergroupBusinessrole usergroupBusinessrole = fetchByPrimaryKey(primaryKey);

		if (usergroupBusinessrole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUsergroupBusinessroleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return usergroupBusinessrole;
	}

	/**
	 * Returns the usergroup businessrole with the primary key or throws a {@link NoSuchUsergroupBusinessroleException} if it could not be found.
	 *
	 * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	 * @return the usergroup businessrole
	 * @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole findByPrimaryKey(int usergroupBusinessroleSid)
		throws NoSuchUsergroupBusinessroleException {
		return findByPrimaryKey((Serializable)usergroupBusinessroleSid);
	}

	/**
	 * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the usergroup businessrole
	 * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
				UsergroupBusinessroleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UsergroupBusinessrole usergroupBusinessrole = (UsergroupBusinessrole)serializable;

		if (usergroupBusinessrole == null) {
			Session session = null;

			try {
				session = openSession();

				usergroupBusinessrole = (UsergroupBusinessrole)session.get(UsergroupBusinessroleImpl.class,
						primaryKey);

				if (usergroupBusinessrole != null) {
					cacheResult(usergroupBusinessrole);
				}
				else {
					entityCache.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
						UsergroupBusinessroleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupBusinessroleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return usergroupBusinessrole;
	}

	/**
	 * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	 * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
	 */
	@Override
	public UsergroupBusinessrole fetchByPrimaryKey(int usergroupBusinessroleSid) {
		return fetchByPrimaryKey((Serializable)usergroupBusinessroleSid);
	}

	@Override
	public Map<Serializable, UsergroupBusinessrole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UsergroupBusinessrole> map = new HashMap<Serializable, UsergroupBusinessrole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UsergroupBusinessrole usergroupBusinessrole = fetchByPrimaryKey(primaryKey);

			if (usergroupBusinessrole != null) {
				map.put(primaryKey, usergroupBusinessrole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupBusinessroleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UsergroupBusinessrole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERGROUPBUSINESSROLE_WHERE_PKS_IN);

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

			for (UsergroupBusinessrole usergroupBusinessrole : (List<UsergroupBusinessrole>)q.list()) {
				map.put(usergroupBusinessrole.getPrimaryKeyObj(),
					usergroupBusinessrole);

				cacheResult(usergroupBusinessrole);

				uncachedPrimaryKeys.remove(usergroupBusinessrole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
					UsergroupBusinessroleImpl.class, primaryKey, nullModel);
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
	 * Returns all the usergroup businessroles.
	 *
	 * @return the usergroup businessroles
	 */
	@Override
	public List<UsergroupBusinessrole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the usergroup businessroles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup businessroles
	 * @param end the upper bound of the range of usergroup businessroles (not inclusive)
	 * @return the range of usergroup businessroles
	 */
	@Override
	public List<UsergroupBusinessrole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the usergroup businessroles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup businessroles
	 * @param end the upper bound of the range of usergroup businessroles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of usergroup businessroles
	 */
	@Override
	public List<UsergroupBusinessrole> findAll(int start, int end,
		OrderByComparator<UsergroupBusinessrole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the usergroup businessroles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of usergroup businessroles
	 * @param end the upper bound of the range of usergroup businessroles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of usergroup businessroles
	 */
	@Override
	public List<UsergroupBusinessrole> findAll(int start, int end,
		OrderByComparator<UsergroupBusinessrole> orderByComparator,
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

		List<UsergroupBusinessrole> list = null;

		if (retrieveFromCache) {
			list = (List<UsergroupBusinessrole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERGROUPBUSINESSROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERGROUPBUSINESSROLE;

				if (pagination) {
					sql = sql.concat(UsergroupBusinessroleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UsergroupBusinessrole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UsergroupBusinessrole>)QueryUtil.list(q,
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
	 * Removes all the usergroup businessroles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UsergroupBusinessrole usergroupBusinessrole : findAll()) {
			remove(usergroupBusinessrole);
		}
	}

	/**
	 * Returns the number of usergroup businessroles.
	 *
	 * @return the number of usergroup businessroles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERGROUPBUSINESSROLE);

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
		return UsergroupBusinessroleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the usergroup businessrole persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UsergroupBusinessroleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERGROUPBUSINESSROLE = "SELECT usergroupBusinessrole FROM UsergroupBusinessrole usergroupBusinessrole";
	private static final String _SQL_SELECT_USERGROUPBUSINESSROLE_WHERE_PKS_IN = "SELECT usergroupBusinessrole FROM UsergroupBusinessrole usergroupBusinessrole WHERE USERGROUP_BUSINESSROLE_SID IN (";
	private static final String _SQL_COUNT_USERGROUPBUSINESSROLE = "SELECT COUNT(usergroupBusinessrole) FROM UsergroupBusinessrole usergroupBusinessrole";
	private static final String _ORDER_BY_ENTITY_ALIAS = "usergroupBusinessrole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UsergroupBusinessrole exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(UsergroupBusinessrolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "businessroleMasterSid", "usersSid", "modifiedBy",
				"createdDate", "processed", "usergroupId", "versionNo",
				"isActive", "usergroupBusinessroleSid", "modifiedDate"
			});
}