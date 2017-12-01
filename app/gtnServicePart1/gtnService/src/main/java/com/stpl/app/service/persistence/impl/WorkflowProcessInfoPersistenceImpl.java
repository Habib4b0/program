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

import com.stpl.app.exception.NoSuchWorkflowProcessInfoException;
import com.stpl.app.model.WorkflowProcessInfo;
import com.stpl.app.model.impl.WorkflowProcessInfoImpl;
import com.stpl.app.model.impl.WorkflowProcessInfoModelImpl;
import com.stpl.app.service.persistence.WorkflowProcessInfoPersistence;

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
 * The persistence implementation for the workflow process info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProcessInfoPersistence
 * @see com.stpl.app.service.persistence.WorkflowProcessInfoUtil
 * @generated
 */
@ProviderType
public class WorkflowProcessInfoPersistenceImpl extends BasePersistenceImpl<WorkflowProcessInfo>
	implements WorkflowProcessInfoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowProcessInfoUtil} to access the workflow process info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowProcessInfoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoModelImpl.FINDER_CACHE_ENABLED,
			WorkflowProcessInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoModelImpl.FINDER_CACHE_ENABLED,
			WorkflowProcessInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WorkflowProcessInfoPersistenceImpl() {
		setModelClass(WorkflowProcessInfo.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("processInstanceId", "PROCESS_INSTANCE_ID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("workflowProcessInfoSid",
				"WORKFLOW_PROCESS_INFO_SID");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("contractStructure", "CONTRACT_STRUCTURE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the workflow process info in the entity cache if it is enabled.
	 *
	 * @param workflowProcessInfo the workflow process info
	 */
	@Override
	public void cacheResult(WorkflowProcessInfo workflowProcessInfo) {
		entityCache.putResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoImpl.class, workflowProcessInfo.getPrimaryKey(),
			workflowProcessInfo);

		workflowProcessInfo.resetOriginalValues();
	}

	/**
	 * Caches the workflow process infos in the entity cache if it is enabled.
	 *
	 * @param workflowProcessInfos the workflow process infos
	 */
	@Override
	public void cacheResult(List<WorkflowProcessInfo> workflowProcessInfos) {
		for (WorkflowProcessInfo workflowProcessInfo : workflowProcessInfos) {
			if (entityCache.getResult(
						WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowProcessInfoImpl.class,
						workflowProcessInfo.getPrimaryKey()) == null) {
				cacheResult(workflowProcessInfo);
			}
			else {
				workflowProcessInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow process infos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkflowProcessInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow process info.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowProcessInfo workflowProcessInfo) {
		entityCache.removeResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoImpl.class, workflowProcessInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowProcessInfo> workflowProcessInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowProcessInfo workflowProcessInfo : workflowProcessInfos) {
			entityCache.removeResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowProcessInfoImpl.class,
				workflowProcessInfo.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
	 *
	 * @param workflowProcessInfoSid the primary key for the new workflow process info
	 * @return the new workflow process info
	 */
	@Override
	public WorkflowProcessInfo create(int workflowProcessInfoSid) {
		WorkflowProcessInfo workflowProcessInfo = new WorkflowProcessInfoImpl();

		workflowProcessInfo.setNew(true);
		workflowProcessInfo.setPrimaryKey(workflowProcessInfoSid);

		return workflowProcessInfo;
	}

	/**
	 * Removes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowProcessInfoSid the primary key of the workflow process info
	 * @return the workflow process info that was removed
	 * @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo remove(int workflowProcessInfoSid)
		throws NoSuchWorkflowProcessInfoException {
		return remove((Serializable)workflowProcessInfoSid);
	}

	/**
	 * Removes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow process info
	 * @return the workflow process info that was removed
	 * @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo remove(Serializable primaryKey)
		throws NoSuchWorkflowProcessInfoException {
		Session session = null;

		try {
			session = openSession();

			WorkflowProcessInfo workflowProcessInfo = (WorkflowProcessInfo)session.get(WorkflowProcessInfoImpl.class,
					primaryKey);

			if (workflowProcessInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowProcessInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowProcessInfo);
		}
		catch (NoSuchWorkflowProcessInfoException nsee) {
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
	protected WorkflowProcessInfo removeImpl(
		WorkflowProcessInfo workflowProcessInfo) {
		workflowProcessInfo = toUnwrappedModel(workflowProcessInfo);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowProcessInfo)) {
				workflowProcessInfo = (WorkflowProcessInfo)session.get(WorkflowProcessInfoImpl.class,
						workflowProcessInfo.getPrimaryKeyObj());
			}

			if (workflowProcessInfo != null) {
				session.delete(workflowProcessInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowProcessInfo != null) {
			clearCache(workflowProcessInfo);
		}

		return workflowProcessInfo;
	}

	@Override
	public WorkflowProcessInfo updateImpl(
		WorkflowProcessInfo workflowProcessInfo) {
		workflowProcessInfo = toUnwrappedModel(workflowProcessInfo);

		boolean isNew = workflowProcessInfo.isNew();

		Session session = null;

		try {
			session = openSession();

			if (workflowProcessInfo.isNew()) {
				session.save(workflowProcessInfo);

				workflowProcessInfo.setNew(false);
			}
			else {
				workflowProcessInfo = (WorkflowProcessInfo)session.merge(workflowProcessInfo);
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

		entityCache.putResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProcessInfoImpl.class, workflowProcessInfo.getPrimaryKey(),
			workflowProcessInfo, false);

		workflowProcessInfo.resetOriginalValues();

		return workflowProcessInfo;
	}

	protected WorkflowProcessInfo toUnwrappedModel(
		WorkflowProcessInfo workflowProcessInfo) {
		if (workflowProcessInfo instanceof WorkflowProcessInfoImpl) {
			return workflowProcessInfo;
		}

		WorkflowProcessInfoImpl workflowProcessInfoImpl = new WorkflowProcessInfoImpl();

		workflowProcessInfoImpl.setNew(workflowProcessInfo.isNew());
		workflowProcessInfoImpl.setPrimaryKey(workflowProcessInfo.getPrimaryKey());

		workflowProcessInfoImpl.setProcessInstanceId(workflowProcessInfo.getProcessInstanceId());
		workflowProcessInfoImpl.setProjectionMasterSid(workflowProcessInfo.getProjectionMasterSid());
		workflowProcessInfoImpl.setWorkflowProcessInfoSid(workflowProcessInfo.getWorkflowProcessInfoSid());
		workflowProcessInfoImpl.setAccClosureMasterSid(workflowProcessInfo.getAccClosureMasterSid());
		workflowProcessInfoImpl.setContractMasterSid(workflowProcessInfo.getContractMasterSid());
		workflowProcessInfoImpl.setContractStructure(workflowProcessInfo.getContractStructure());

		return workflowProcessInfoImpl;
	}

	/**
	 * Returns the workflow process info with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow process info
	 * @return the workflow process info
	 * @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowProcessInfoException {
		WorkflowProcessInfo workflowProcessInfo = fetchByPrimaryKey(primaryKey);

		if (workflowProcessInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowProcessInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowProcessInfo;
	}

	/**
	 * Returns the workflow process info with the primary key or throws a {@link NoSuchWorkflowProcessInfoException} if it could not be found.
	 *
	 * @param workflowProcessInfoSid the primary key of the workflow process info
	 * @return the workflow process info
	 * @throws NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo findByPrimaryKey(int workflowProcessInfoSid)
		throws NoSuchWorkflowProcessInfoException {
		return findByPrimaryKey((Serializable)workflowProcessInfoSid);
	}

	/**
	 * Returns the workflow process info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow process info
	 * @return the workflow process info, or <code>null</code> if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowProcessInfoImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkflowProcessInfo workflowProcessInfo = (WorkflowProcessInfo)serializable;

		if (workflowProcessInfo == null) {
			Session session = null;

			try {
				session = openSession();

				workflowProcessInfo = (WorkflowProcessInfo)session.get(WorkflowProcessInfoImpl.class,
						primaryKey);

				if (workflowProcessInfo != null) {
					cacheResult(workflowProcessInfo);
				}
				else {
					entityCache.putResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowProcessInfoImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProcessInfoImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowProcessInfo;
	}

	/**
	 * Returns the workflow process info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowProcessInfoSid the primary key of the workflow process info
	 * @return the workflow process info, or <code>null</code> if a workflow process info with the primary key could not be found
	 */
	@Override
	public WorkflowProcessInfo fetchByPrimaryKey(int workflowProcessInfoSid) {
		return fetchByPrimaryKey((Serializable)workflowProcessInfoSid);
	}

	@Override
	public Map<Serializable, WorkflowProcessInfo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkflowProcessInfo> map = new HashMap<Serializable, WorkflowProcessInfo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkflowProcessInfo workflowProcessInfo = fetchByPrimaryKey(primaryKey);

			if (workflowProcessInfo != null) {
				map.put(primaryKey, workflowProcessInfo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProcessInfoImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkflowProcessInfo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKFLOWPROCESSINFO_WHERE_PKS_IN);

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

			for (WorkflowProcessInfo workflowProcessInfo : (List<WorkflowProcessInfo>)q.list()) {
				map.put(workflowProcessInfo.getPrimaryKeyObj(),
					workflowProcessInfo);

				cacheResult(workflowProcessInfo);

				uncachedPrimaryKeys.remove(workflowProcessInfo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkflowProcessInfoModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProcessInfoImpl.class, primaryKey, nullModel);
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
	 * Returns all the workflow process infos.
	 *
	 * @return the workflow process infos
	 */
	@Override
	public List<WorkflowProcessInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow process infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow process infos
	 * @param end the upper bound of the range of workflow process infos (not inclusive)
	 * @return the range of workflow process infos
	 */
	@Override
	public List<WorkflowProcessInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow process infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow process infos
	 * @param end the upper bound of the range of workflow process infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow process infos
	 */
	@Override
	public List<WorkflowProcessInfo> findAll(int start, int end,
		OrderByComparator<WorkflowProcessInfo> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow process infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow process infos
	 * @param end the upper bound of the range of workflow process infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of workflow process infos
	 */
	@Override
	public List<WorkflowProcessInfo> findAll(int start, int end,
		OrderByComparator<WorkflowProcessInfo> orderByComparator,
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

		List<WorkflowProcessInfo> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowProcessInfo>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKFLOWPROCESSINFO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWPROCESSINFO;

				if (pagination) {
					sql = sql.concat(WorkflowProcessInfoModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowProcessInfo>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowProcessInfo>)QueryUtil.list(q,
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
	 * Removes all the workflow process infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkflowProcessInfo workflowProcessInfo : findAll()) {
			remove(workflowProcessInfo);
		}
	}

	/**
	 * Returns the number of workflow process infos.
	 *
	 * @return the number of workflow process infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWPROCESSINFO);

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
		return WorkflowProcessInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the workflow process info persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkflowProcessInfoImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WORKFLOWPROCESSINFO = "SELECT workflowProcessInfo FROM WorkflowProcessInfo workflowProcessInfo";
	private static final String _SQL_SELECT_WORKFLOWPROCESSINFO_WHERE_PKS_IN = "SELECT workflowProcessInfo FROM WorkflowProcessInfo workflowProcessInfo WHERE WORKFLOW_PROCESS_INFO_SID IN (";
	private static final String _SQL_COUNT_WORKFLOWPROCESSINFO = "SELECT COUNT(workflowProcessInfo) FROM WorkflowProcessInfo workflowProcessInfo";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowProcessInfo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowProcessInfo exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WorkflowProcessInfoPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"processInstanceId", "projectionMasterSid",
				"workflowProcessInfoSid", "accClosureMasterSid",
				"contractMasterSid", "contractStructure"
			});
}