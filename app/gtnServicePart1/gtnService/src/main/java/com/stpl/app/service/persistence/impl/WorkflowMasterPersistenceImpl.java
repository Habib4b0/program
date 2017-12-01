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

import com.stpl.app.exception.NoSuchWorkflowMasterException;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.model.impl.WorkflowMasterImpl;
import com.stpl.app.model.impl.WorkflowMasterModelImpl;
import com.stpl.app.service.persistence.WorkflowMasterPersistence;

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
 * The persistence implementation for the workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowMasterPersistence
 * @see com.stpl.app.service.persistence.WorkflowMasterUtil
 * @generated
 */
@ProviderType
public class WorkflowMasterPersistenceImpl extends BasePersistenceImpl<WorkflowMaster>
	implements WorkflowMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowMasterUtil} to access the workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterModelImpl.FINDER_CACHE_ENABLED,
			WorkflowMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterModelImpl.FINDER_CACHE_ENABLED,
			WorkflowMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WorkflowMasterPersistenceImpl() {
		setModelClass(WorkflowMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("fileSize", "FILE_SIZE");
			dbColumnNames.put("workflowStatusId", "WORKFLOW_STATUS_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("approvalLevel", "APPROVAL_LEVEL");
			dbColumnNames.put("noOfApproval", "NO_OF_APPROVAL");
			dbColumnNames.put("fileName", "FILE_NAME");
			dbColumnNames.put("uploadedBy", "UPLOADED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("workflowMasterSid", "WORKFLOW_MASTER_SID");
			dbColumnNames.put("workflowId", "WORKFLOW_ID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("uploadedDate", "UPLOADED_DATE");
			dbColumnNames.put("fileType", "FILE_TYPE");
			dbColumnNames.put("approvedBy", "APPROVED_BY");
			dbColumnNames.put("workflowDescrption", "WORKFLOW_DESCRPTION");
			dbColumnNames.put("approvedDate", "APPROVED_DATE");
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
	 * Caches the workflow master in the entity cache if it is enabled.
	 *
	 * @param workflowMaster the workflow master
	 */
	@Override
	public void cacheResult(WorkflowMaster workflowMaster) {
		entityCache.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterImpl.class, workflowMaster.getPrimaryKey(),
			workflowMaster);

		workflowMaster.resetOriginalValues();
	}

	/**
	 * Caches the workflow masters in the entity cache if it is enabled.
	 *
	 * @param workflowMasters the workflow masters
	 */
	@Override
	public void cacheResult(List<WorkflowMaster> workflowMasters) {
		for (WorkflowMaster workflowMaster : workflowMasters) {
			if (entityCache.getResult(
						WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowMasterImpl.class, workflowMaster.getPrimaryKey()) == null) {
				cacheResult(workflowMaster);
			}
			else {
				workflowMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkflowMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowMaster workflowMaster) {
		entityCache.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterImpl.class, workflowMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowMaster> workflowMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowMaster workflowMaster : workflowMasters) {
			entityCache.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowMasterImpl.class, workflowMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow master with the primary key. Does not add the workflow master to the database.
	 *
	 * @param workflowMasterSid the primary key for the new workflow master
	 * @return the new workflow master
	 */
	@Override
	public WorkflowMaster create(int workflowMasterSid) {
		WorkflowMaster workflowMaster = new WorkflowMasterImpl();

		workflowMaster.setNew(true);
		workflowMaster.setPrimaryKey(workflowMasterSid);

		return workflowMaster;
	}

	/**
	 * Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowMasterSid the primary key of the workflow master
	 * @return the workflow master that was removed
	 * @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster remove(int workflowMasterSid)
		throws NoSuchWorkflowMasterException {
		return remove((Serializable)workflowMasterSid);
	}

	/**
	 * Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow master
	 * @return the workflow master that was removed
	 * @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster remove(Serializable primaryKey)
		throws NoSuchWorkflowMasterException {
		Session session = null;

		try {
			session = openSession();

			WorkflowMaster workflowMaster = (WorkflowMaster)session.get(WorkflowMasterImpl.class,
					primaryKey);

			if (workflowMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowMaster);
		}
		catch (NoSuchWorkflowMasterException nsee) {
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
	protected WorkflowMaster removeImpl(WorkflowMaster workflowMaster) {
		workflowMaster = toUnwrappedModel(workflowMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowMaster)) {
				workflowMaster = (WorkflowMaster)session.get(WorkflowMasterImpl.class,
						workflowMaster.getPrimaryKeyObj());
			}

			if (workflowMaster != null) {
				session.delete(workflowMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowMaster != null) {
			clearCache(workflowMaster);
		}

		return workflowMaster;
	}

	@Override
	public WorkflowMaster updateImpl(WorkflowMaster workflowMaster) {
		workflowMaster = toUnwrappedModel(workflowMaster);

		boolean isNew = workflowMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (workflowMaster.isNew()) {
				session.save(workflowMaster);

				workflowMaster.setNew(false);
			}
			else {
				workflowMaster = (WorkflowMaster)session.merge(workflowMaster);
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

		entityCache.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowMasterImpl.class, workflowMaster.getPrimaryKey(),
			workflowMaster, false);

		workflowMaster.resetOriginalValues();

		return workflowMaster;
	}

	protected WorkflowMaster toUnwrappedModel(WorkflowMaster workflowMaster) {
		if (workflowMaster instanceof WorkflowMasterImpl) {
			return workflowMaster;
		}

		WorkflowMasterImpl workflowMasterImpl = new WorkflowMasterImpl();

		workflowMasterImpl.setNew(workflowMaster.isNew());
		workflowMasterImpl.setPrimaryKey(workflowMaster.getPrimaryKey());

		workflowMasterImpl.setCreatedBy(workflowMaster.getCreatedBy());
		workflowMasterImpl.setFileSize(workflowMaster.getFileSize());
		workflowMasterImpl.setWorkflowStatusId(workflowMaster.getWorkflowStatusId());
		workflowMasterImpl.setModifiedBy(workflowMaster.getModifiedBy());
		workflowMasterImpl.setCreatedDate(workflowMaster.getCreatedDate());
		workflowMasterImpl.setApprovalLevel(workflowMaster.getApprovalLevel());
		workflowMasterImpl.setNoOfApproval(workflowMaster.getNoOfApproval());
		workflowMasterImpl.setFileName(workflowMaster.getFileName());
		workflowMasterImpl.setUploadedBy(workflowMaster.getUploadedBy());
		workflowMasterImpl.setModifiedDate(workflowMaster.getModifiedDate());
		workflowMasterImpl.setAccClosureMasterSid(workflowMaster.getAccClosureMasterSid());
		workflowMasterImpl.setNotes(workflowMaster.getNotes());
		workflowMasterImpl.setWorkflowMasterSid(workflowMaster.getWorkflowMasterSid());
		workflowMasterImpl.setWorkflowId(workflowMaster.getWorkflowId());
		workflowMasterImpl.setProjectionMasterSid(workflowMaster.getProjectionMasterSid());
		workflowMasterImpl.setUploadedDate(workflowMaster.getUploadedDate());
		workflowMasterImpl.setFileType(workflowMaster.getFileType());
		workflowMasterImpl.setApprovedBy(workflowMaster.getApprovedBy());
		workflowMasterImpl.setWorkflowDescrption(workflowMaster.getWorkflowDescrption());
		workflowMasterImpl.setApprovedDate(workflowMaster.getApprovedDate());
		workflowMasterImpl.setContractMasterSid(workflowMaster.getContractMasterSid());
		workflowMasterImpl.setContractStructure(workflowMaster.getContractStructure());

		return workflowMasterImpl;
	}

	/**
	 * Returns the workflow master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow master
	 * @return the workflow master
	 * @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowMasterException {
		WorkflowMaster workflowMaster = fetchByPrimaryKey(primaryKey);

		if (workflowMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowMaster;
	}

	/**
	 * Returns the workflow master with the primary key or throws a {@link NoSuchWorkflowMasterException} if it could not be found.
	 *
	 * @param workflowMasterSid the primary key of the workflow master
	 * @return the workflow master
	 * @throws NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws NoSuchWorkflowMasterException {
		return findByPrimaryKey((Serializable)workflowMasterSid);
	}

	/**
	 * Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow master
	 * @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkflowMaster workflowMaster = (WorkflowMaster)serializable;

		if (workflowMaster == null) {
			Session session = null;

			try {
				session = openSession();

				workflowMaster = (WorkflowMaster)session.get(WorkflowMasterImpl.class,
						primaryKey);

				if (workflowMaster != null) {
					cacheResult(workflowMaster);
				}
				else {
					entityCache.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowMaster;
	}

	/**
	 * Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowMasterSid the primary key of the workflow master
	 * @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
	 */
	@Override
	public WorkflowMaster fetchByPrimaryKey(int workflowMasterSid) {
		return fetchByPrimaryKey((Serializable)workflowMasterSid);
	}

	@Override
	public Map<Serializable, WorkflowMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkflowMaster> map = new HashMap<Serializable, WorkflowMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkflowMaster workflowMaster = fetchByPrimaryKey(primaryKey);

			if (workflowMaster != null) {
				map.put(primaryKey, workflowMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkflowMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKFLOWMASTER_WHERE_PKS_IN);

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

			for (WorkflowMaster workflowMaster : (List<WorkflowMaster>)q.list()) {
				map.put(workflowMaster.getPrimaryKeyObj(), workflowMaster);

				cacheResult(workflowMaster);

				uncachedPrimaryKeys.remove(workflowMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the workflow masters.
	 *
	 * @return the workflow masters
	 */
	@Override
	public List<WorkflowMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow masters
	 * @param end the upper bound of the range of workflow masters (not inclusive)
	 * @return the range of workflow masters
	 */
	@Override
	public List<WorkflowMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow masters
	 * @param end the upper bound of the range of workflow masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow masters
	 */
	@Override
	public List<WorkflowMaster> findAll(int start, int end,
		OrderByComparator<WorkflowMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow masters
	 * @param end the upper bound of the range of workflow masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of workflow masters
	 */
	@Override
	public List<WorkflowMaster> findAll(int start, int end,
		OrderByComparator<WorkflowMaster> orderByComparator,
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

		List<WorkflowMaster> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKFLOWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWMASTER;

				if (pagination) {
					sql = sql.concat(WorkflowMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowMaster>)QueryUtil.list(q,
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
	 * Removes all the workflow masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkflowMaster workflowMaster : findAll()) {
			remove(workflowMaster);
		}
	}

	/**
	 * Returns the number of workflow masters.
	 *
	 * @return the number of workflow masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWMASTER);

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
		return WorkflowMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the workflow master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkflowMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WORKFLOWMASTER = "SELECT workflowMaster FROM WorkflowMaster workflowMaster";
	private static final String _SQL_SELECT_WORKFLOWMASTER_WHERE_PKS_IN = "SELECT workflowMaster FROM WorkflowMaster workflowMaster WHERE WORKFLOW_MASTER_SID IN (";
	private static final String _SQL_COUNT_WORKFLOWMASTER = "SELECT COUNT(workflowMaster) FROM WorkflowMaster workflowMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WorkflowMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "fileSize", "workflowStatusId", "modifiedBy",
				"createdDate", "approvalLevel", "noOfApproval", "fileName",
				"uploadedBy", "modifiedDate", "accClosureMasterSid", "notes",
				"workflowMasterSid", "workflowId", "projectionMasterSid",
				"uploadedDate", "fileType", "approvedBy", "workflowDescrption",
				"approvedDate", "contractMasterSid", "contractStructure"
			});
}