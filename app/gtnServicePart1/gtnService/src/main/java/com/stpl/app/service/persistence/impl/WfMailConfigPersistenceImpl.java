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

import com.stpl.app.exception.NoSuchWfMailConfigException;
import com.stpl.app.model.WfMailConfig;
import com.stpl.app.model.impl.WfMailConfigImpl;
import com.stpl.app.model.impl.WfMailConfigModelImpl;
import com.stpl.app.service.persistence.WfMailConfigPersistence;

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
 * The persistence implementation for the wf mail config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WfMailConfigPersistence
 * @see com.stpl.app.service.persistence.WfMailConfigUtil
 * @generated
 */
@ProviderType
public class WfMailConfigPersistenceImpl extends BasePersistenceImpl<WfMailConfig>
	implements WfMailConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WfMailConfigUtil} to access the wf mail config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WfMailConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigModelImpl.FINDER_CACHE_ENABLED, WfMailConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigModelImpl.FINDER_CACHE_ENABLED, WfMailConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WfMailConfigPersistenceImpl() {
		setModelClass(WfMailConfig.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("smtpFlag", "SMTP_FLAG");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("emailAddress", "EMAIL_ADDRESS");
			dbColumnNames.put("password", "PASSWORD");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("wfMailConfigSid", "WF_MAIL_CONFIG_SID");
			dbColumnNames.put("hostName", "HOST_NAME");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("portNumber", "PORT_NUMBER");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("testMailAddress", "TEST_MAIL_ADDRESS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the wf mail config in the entity cache if it is enabled.
	 *
	 * @param wfMailConfig the wf mail config
	 */
	@Override
	public void cacheResult(WfMailConfig wfMailConfig) {
		entityCache.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigImpl.class, wfMailConfig.getPrimaryKey(), wfMailConfig);

		wfMailConfig.resetOriginalValues();
	}

	/**
	 * Caches the wf mail configs in the entity cache if it is enabled.
	 *
	 * @param wfMailConfigs the wf mail configs
	 */
	@Override
	public void cacheResult(List<WfMailConfig> wfMailConfigs) {
		for (WfMailConfig wfMailConfig : wfMailConfigs) {
			if (entityCache.getResult(
						WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
						WfMailConfigImpl.class, wfMailConfig.getPrimaryKey()) == null) {
				cacheResult(wfMailConfig);
			}
			else {
				wfMailConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all wf mail configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WfMailConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the wf mail config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WfMailConfig wfMailConfig) {
		entityCache.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigImpl.class, wfMailConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WfMailConfig> wfMailConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WfMailConfig wfMailConfig : wfMailConfigs) {
			entityCache.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
				WfMailConfigImpl.class, wfMailConfig.getPrimaryKey());
		}
	}

	/**
	 * Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
	 *
	 * @param wfMailConfigSid the primary key for the new wf mail config
	 * @return the new wf mail config
	 */
	@Override
	public WfMailConfig create(int wfMailConfigSid) {
		WfMailConfig wfMailConfig = new WfMailConfigImpl();

		wfMailConfig.setNew(true);
		wfMailConfig.setPrimaryKey(wfMailConfigSid);

		return wfMailConfig;
	}

	/**
	 * Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wfMailConfigSid the primary key of the wf mail config
	 * @return the wf mail config that was removed
	 * @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig remove(int wfMailConfigSid)
		throws NoSuchWfMailConfigException {
		return remove((Serializable)wfMailConfigSid);
	}

	/**
	 * Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the wf mail config
	 * @return the wf mail config that was removed
	 * @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig remove(Serializable primaryKey)
		throws NoSuchWfMailConfigException {
		Session session = null;

		try {
			session = openSession();

			WfMailConfig wfMailConfig = (WfMailConfig)session.get(WfMailConfigImpl.class,
					primaryKey);

			if (wfMailConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWfMailConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wfMailConfig);
		}
		catch (NoSuchWfMailConfigException nsee) {
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
	protected WfMailConfig removeImpl(WfMailConfig wfMailConfig) {
		wfMailConfig = toUnwrappedModel(wfMailConfig);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wfMailConfig)) {
				wfMailConfig = (WfMailConfig)session.get(WfMailConfigImpl.class,
						wfMailConfig.getPrimaryKeyObj());
			}

			if (wfMailConfig != null) {
				session.delete(wfMailConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (wfMailConfig != null) {
			clearCache(wfMailConfig);
		}

		return wfMailConfig;
	}

	@Override
	public WfMailConfig updateImpl(WfMailConfig wfMailConfig) {
		wfMailConfig = toUnwrappedModel(wfMailConfig);

		boolean isNew = wfMailConfig.isNew();

		Session session = null;

		try {
			session = openSession();

			if (wfMailConfig.isNew()) {
				session.save(wfMailConfig);

				wfMailConfig.setNew(false);
			}
			else {
				wfMailConfig = (WfMailConfig)session.merge(wfMailConfig);
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

		entityCache.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
			WfMailConfigImpl.class, wfMailConfig.getPrimaryKey(), wfMailConfig,
			false);

		wfMailConfig.resetOriginalValues();

		return wfMailConfig;
	}

	protected WfMailConfig toUnwrappedModel(WfMailConfig wfMailConfig) {
		if (wfMailConfig instanceof WfMailConfigImpl) {
			return wfMailConfig;
		}

		WfMailConfigImpl wfMailConfigImpl = new WfMailConfigImpl();

		wfMailConfigImpl.setNew(wfMailConfig.isNew());
		wfMailConfigImpl.setPrimaryKey(wfMailConfig.getPrimaryKey());

		wfMailConfigImpl.setSmtpFlag(wfMailConfig.getSmtpFlag());
		wfMailConfigImpl.setCreatedBy(wfMailConfig.getCreatedBy());
		wfMailConfigImpl.setEmailAddress(wfMailConfig.getEmailAddress());
		wfMailConfigImpl.setPassword(wfMailConfig.getPassword());
		wfMailConfigImpl.setModifiedBy(wfMailConfig.getModifiedBy());
		wfMailConfigImpl.setWfMailConfigSid(wfMailConfig.getWfMailConfigSid());
		wfMailConfigImpl.setHostName(wfMailConfig.getHostName());
		wfMailConfigImpl.setCreatedDate(wfMailConfig.getCreatedDate());
		wfMailConfigImpl.setPortNumber(wfMailConfig.getPortNumber());
		wfMailConfigImpl.setModifiedDate(wfMailConfig.getModifiedDate());
		wfMailConfigImpl.setInboundStatus(wfMailConfig.getInboundStatus());
		wfMailConfigImpl.setTestMailAddress(wfMailConfig.getTestMailAddress());

		return wfMailConfigImpl;
	}

	/**
	 * Returns the wf mail config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the wf mail config
	 * @return the wf mail config
	 * @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWfMailConfigException {
		WfMailConfig wfMailConfig = fetchByPrimaryKey(primaryKey);

		if (wfMailConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWfMailConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return wfMailConfig;
	}

	/**
	 * Returns the wf mail config with the primary key or throws a {@link NoSuchWfMailConfigException} if it could not be found.
	 *
	 * @param wfMailConfigSid the primary key of the wf mail config
	 * @return the wf mail config
	 * @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig findByPrimaryKey(int wfMailConfigSid)
		throws NoSuchWfMailConfigException {
		return findByPrimaryKey((Serializable)wfMailConfigSid);
	}

	/**
	 * Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the wf mail config
	 * @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
				WfMailConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WfMailConfig wfMailConfig = (WfMailConfig)serializable;

		if (wfMailConfig == null) {
			Session session = null;

			try {
				session = openSession();

				wfMailConfig = (WfMailConfig)session.get(WfMailConfigImpl.class,
						primaryKey);

				if (wfMailConfig != null) {
					cacheResult(wfMailConfig);
				}
				else {
					entityCache.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
						WfMailConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
					WfMailConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return wfMailConfig;
	}

	/**
	 * Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wfMailConfigSid the primary key of the wf mail config
	 * @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
	 */
	@Override
	public WfMailConfig fetchByPrimaryKey(int wfMailConfigSid) {
		return fetchByPrimaryKey((Serializable)wfMailConfigSid);
	}

	@Override
	public Map<Serializable, WfMailConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WfMailConfig> map = new HashMap<Serializable, WfMailConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WfMailConfig wfMailConfig = fetchByPrimaryKey(primaryKey);

			if (wfMailConfig != null) {
				map.put(primaryKey, wfMailConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
					WfMailConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WfMailConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WFMAILCONFIG_WHERE_PKS_IN);

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

			for (WfMailConfig wfMailConfig : (List<WfMailConfig>)q.list()) {
				map.put(wfMailConfig.getPrimaryKeyObj(), wfMailConfig);

				cacheResult(wfMailConfig);

				uncachedPrimaryKeys.remove(wfMailConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
					WfMailConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the wf mail configs.
	 *
	 * @return the wf mail configs
	 */
	@Override
	public List<WfMailConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wf mail configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wf mail configs
	 * @param end the upper bound of the range of wf mail configs (not inclusive)
	 * @return the range of wf mail configs
	 */
	@Override
	public List<WfMailConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the wf mail configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wf mail configs
	 * @param end the upper bound of the range of wf mail configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wf mail configs
	 */
	@Override
	public List<WfMailConfig> findAll(int start, int end,
		OrderByComparator<WfMailConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wf mail configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wf mail configs
	 * @param end the upper bound of the range of wf mail configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of wf mail configs
	 */
	@Override
	public List<WfMailConfig> findAll(int start, int end,
		OrderByComparator<WfMailConfig> orderByComparator,
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

		List<WfMailConfig> list = null;

		if (retrieveFromCache) {
			list = (List<WfMailConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WFMAILCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WFMAILCONFIG;

				if (pagination) {
					sql = sql.concat(WfMailConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WfMailConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WfMailConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the wf mail configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WfMailConfig wfMailConfig : findAll()) {
			remove(wfMailConfig);
		}
	}

	/**
	 * Returns the number of wf mail configs.
	 *
	 * @return the number of wf mail configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WFMAILCONFIG);

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
		return WfMailConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the wf mail config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WfMailConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WFMAILCONFIG = "SELECT wfMailConfig FROM WfMailConfig wfMailConfig";
	private static final String _SQL_SELECT_WFMAILCONFIG_WHERE_PKS_IN = "SELECT wfMailConfig FROM WfMailConfig wfMailConfig WHERE WF_MAIL_CONFIG_SID IN (";
	private static final String _SQL_COUNT_WFMAILCONFIG = "SELECT COUNT(wfMailConfig) FROM WfMailConfig wfMailConfig";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wfMailConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WfMailConfig exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WfMailConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"smtpFlag", "createdBy", "emailAddress", "password",
				"modifiedBy", "wfMailConfigSid", "hostName", "createdDate",
				"portNumber", "modifiedDate", "inboundStatus", "testMailAddress"
			});
}