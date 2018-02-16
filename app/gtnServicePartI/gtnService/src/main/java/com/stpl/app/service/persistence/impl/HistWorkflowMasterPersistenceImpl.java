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

import com.stpl.app.exception.NoSuchHistWorkflowMasterException;
import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.app.model.impl.HistWorkflowMasterImpl;
import com.stpl.app.model.impl.HistWorkflowMasterModelImpl;
import com.stpl.app.service.persistence.HistWorkflowMasterPersistence;

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
 * The persistence implementation for the hist workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistWorkflowMasterPersistence
 * @see com.stpl.app.service.persistence.HistWorkflowMasterUtil
 * @generated
 */
@ProviderType
public class HistWorkflowMasterPersistenceImpl extends BasePersistenceImpl<HistWorkflowMaster>
	implements HistWorkflowMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistWorkflowMasterUtil} to access the hist workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistWorkflowMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterModelImpl.FINDER_CACHE_ENABLED,
			HistWorkflowMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterModelImpl.FINDER_CACHE_ENABLED,
			HistWorkflowMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistWorkflowMasterPersistenceImpl() {
		setModelClass(HistWorkflowMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("fileSize", "FILE_SIZE");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("workflowStatusId", "WORKFLOW_STATUS_ID");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
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

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hist workflow master in the entity cache if it is enabled.
	 *
	 * @param histWorkflowMaster the hist workflow master
	 */
	@Override
	public void cacheResult(HistWorkflowMaster histWorkflowMaster) {
		entityCache.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey(),
			histWorkflowMaster);

		histWorkflowMaster.resetOriginalValues();
	}

	/**
	 * Caches the hist workflow masters in the entity cache if it is enabled.
	 *
	 * @param histWorkflowMasters the hist workflow masters
	 */
	@Override
	public void cacheResult(List<HistWorkflowMaster> histWorkflowMasters) {
		for (HistWorkflowMaster histWorkflowMaster : histWorkflowMasters) {
			if (entityCache.getResult(
						HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
						HistWorkflowMasterImpl.class,
						histWorkflowMaster.getPrimaryKey()) == null) {
				cacheResult(histWorkflowMaster);
			}
			else {
				histWorkflowMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist workflow masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistWorkflowMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist workflow master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistWorkflowMaster histWorkflowMaster) {
		entityCache.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HistWorkflowMaster> histWorkflowMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistWorkflowMaster histWorkflowMaster : histWorkflowMasters) {
			entityCache.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
				HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
	 *
	 * @param workflowMasterSid the primary key for the new hist workflow master
	 * @return the new hist workflow master
	 */
	@Override
	public HistWorkflowMaster create(int workflowMasterSid) {
		HistWorkflowMaster histWorkflowMaster = new HistWorkflowMasterImpl();

		histWorkflowMaster.setNew(true);
		histWorkflowMaster.setPrimaryKey(workflowMasterSid);

		return histWorkflowMaster;
	}

	/**
	 * Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowMasterSid the primary key of the hist workflow master
	 * @return the hist workflow master that was removed
	 * @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster remove(int workflowMasterSid)
		throws NoSuchHistWorkflowMasterException {
		return remove((Serializable)workflowMasterSid);
	}

	/**
	 * Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist workflow master
	 * @return the hist workflow master that was removed
	 * @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster remove(Serializable primaryKey)
		throws NoSuchHistWorkflowMasterException {
		Session session = null;

		try {
			session = openSession();

			HistWorkflowMaster histWorkflowMaster = (HistWorkflowMaster)session.get(HistWorkflowMasterImpl.class,
					primaryKey);

			if (histWorkflowMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histWorkflowMaster);
		}
		catch (NoSuchHistWorkflowMasterException nsee) {
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
	protected HistWorkflowMaster removeImpl(
		HistWorkflowMaster histWorkflowMaster) {
		histWorkflowMaster = toUnwrappedModel(histWorkflowMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histWorkflowMaster)) {
				histWorkflowMaster = (HistWorkflowMaster)session.get(HistWorkflowMasterImpl.class,
						histWorkflowMaster.getPrimaryKeyObj());
			}

			if (histWorkflowMaster != null) {
				session.delete(histWorkflowMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histWorkflowMaster != null) {
			clearCache(histWorkflowMaster);
		}

		return histWorkflowMaster;
	}

	@Override
	public HistWorkflowMaster updateImpl(HistWorkflowMaster histWorkflowMaster) {
		histWorkflowMaster = toUnwrappedModel(histWorkflowMaster);

		boolean isNew = histWorkflowMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histWorkflowMaster.isNew()) {
				session.save(histWorkflowMaster);

				histWorkflowMaster.setNew(false);
			}
			else {
				histWorkflowMaster = (HistWorkflowMaster)session.merge(histWorkflowMaster);
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

		entityCache.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
			HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey(),
			histWorkflowMaster, false);

		histWorkflowMaster.resetOriginalValues();

		return histWorkflowMaster;
	}

	protected HistWorkflowMaster toUnwrappedModel(
		HistWorkflowMaster histWorkflowMaster) {
		if (histWorkflowMaster instanceof HistWorkflowMasterImpl) {
			return histWorkflowMaster;
		}

		HistWorkflowMasterImpl histWorkflowMasterImpl = new HistWorkflowMasterImpl();

		histWorkflowMasterImpl.setNew(histWorkflowMaster.isNew());
		histWorkflowMasterImpl.setPrimaryKey(histWorkflowMaster.getPrimaryKey());

		histWorkflowMasterImpl.setCreatedBy(histWorkflowMaster.getCreatedBy());
		histWorkflowMasterImpl.setFileSize(histWorkflowMaster.getFileSize());
		histWorkflowMasterImpl.setActionDate(histWorkflowMaster.getActionDate());
		histWorkflowMasterImpl.setWorkflowStatusId(histWorkflowMaster.getWorkflowStatusId());
		histWorkflowMasterImpl.setActionFlag(histWorkflowMaster.getActionFlag());
		histWorkflowMasterImpl.setModifiedBy(histWorkflowMaster.getModifiedBy());
		histWorkflowMasterImpl.setCreatedDate(histWorkflowMaster.getCreatedDate());
		histWorkflowMasterImpl.setApprovalLevel(histWorkflowMaster.getApprovalLevel());
		histWorkflowMasterImpl.setNoOfApproval(histWorkflowMaster.getNoOfApproval());
		histWorkflowMasterImpl.setFileName(histWorkflowMaster.getFileName());
		histWorkflowMasterImpl.setUploadedBy(histWorkflowMaster.getUploadedBy());
		histWorkflowMasterImpl.setModifiedDate(histWorkflowMaster.getModifiedDate());
		histWorkflowMasterImpl.setAccClosureMasterSid(histWorkflowMaster.getAccClosureMasterSid());
		histWorkflowMasterImpl.setNotes(histWorkflowMaster.getNotes());
		histWorkflowMasterImpl.setWorkflowMasterSid(histWorkflowMaster.getWorkflowMasterSid());
		histWorkflowMasterImpl.setWorkflowId(histWorkflowMaster.getWorkflowId());
		histWorkflowMasterImpl.setProjectionMasterSid(histWorkflowMaster.getProjectionMasterSid());
		histWorkflowMasterImpl.setUploadedDate(histWorkflowMaster.getUploadedDate());
		histWorkflowMasterImpl.setFileType(histWorkflowMaster.getFileType());
		histWorkflowMasterImpl.setApprovedBy(histWorkflowMaster.getApprovedBy());
		histWorkflowMasterImpl.setWorkflowDescrption(histWorkflowMaster.getWorkflowDescrption());
		histWorkflowMasterImpl.setApprovedDate(histWorkflowMaster.getApprovedDate());

		return histWorkflowMasterImpl;
	}

	/**
	 * Returns the hist workflow master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist workflow master
	 * @return the hist workflow master
	 * @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistWorkflowMasterException {
		HistWorkflowMaster histWorkflowMaster = fetchByPrimaryKey(primaryKey);

		if (histWorkflowMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histWorkflowMaster;
	}

	/**
	 * Returns the hist workflow master with the primary key or throws a {@link NoSuchHistWorkflowMasterException} if it could not be found.
	 *
	 * @param workflowMasterSid the primary key of the hist workflow master
	 * @return the hist workflow master
	 * @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws NoSuchHistWorkflowMasterException {
		return findByPrimaryKey((Serializable)workflowMasterSid);
	}

	/**
	 * Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist workflow master
	 * @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
				HistWorkflowMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistWorkflowMaster histWorkflowMaster = (HistWorkflowMaster)serializable;

		if (histWorkflowMaster == null) {
			Session session = null;

			try {
				session = openSession();

				histWorkflowMaster = (HistWorkflowMaster)session.get(HistWorkflowMasterImpl.class,
						primaryKey);

				if (histWorkflowMaster != null) {
					cacheResult(histWorkflowMaster);
				}
				else {
					entityCache.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
						HistWorkflowMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					HistWorkflowMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histWorkflowMaster;
	}

	/**
	 * Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowMasterSid the primary key of the hist workflow master
	 * @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
	 */
	@Override
	public HistWorkflowMaster fetchByPrimaryKey(int workflowMasterSid) {
		return fetchByPrimaryKey((Serializable)workflowMasterSid);
	}

	@Override
	public Map<Serializable, HistWorkflowMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistWorkflowMaster> map = new HashMap<Serializable, HistWorkflowMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HistWorkflowMaster histWorkflowMaster = fetchByPrimaryKey(primaryKey);

			if (histWorkflowMaster != null) {
				map.put(primaryKey, histWorkflowMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					HistWorkflowMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HistWorkflowMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HISTWORKFLOWMASTER_WHERE_PKS_IN);

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

			for (HistWorkflowMaster histWorkflowMaster : (List<HistWorkflowMaster>)q.list()) {
				map.put(histWorkflowMaster.getPrimaryKeyObj(),
					histWorkflowMaster);

				cacheResult(histWorkflowMaster);

				uncachedPrimaryKeys.remove(histWorkflowMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
					HistWorkflowMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the hist workflow masters.
	 *
	 * @return the hist workflow masters
	 */
	@Override
	public List<HistWorkflowMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist workflow masters
	 * @param end the upper bound of the range of hist workflow masters (not inclusive)
	 * @return the range of hist workflow masters
	 */
	@Override
	public List<HistWorkflowMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist workflow masters
	 * @param end the upper bound of the range of hist workflow masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist workflow masters
	 */
	@Override
	public List<HistWorkflowMaster> findAll(int start, int end,
		OrderByComparator<HistWorkflowMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist workflow masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist workflow masters
	 * @param end the upper bound of the range of hist workflow masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist workflow masters
	 */
	@Override
	public List<HistWorkflowMaster> findAll(int start, int end,
		OrderByComparator<HistWorkflowMaster> orderByComparator,
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

		List<HistWorkflowMaster> list = null;

		if (retrieveFromCache) {
			list = (List<HistWorkflowMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTWORKFLOWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTWORKFLOWMASTER;

				if (pagination) {
					sql = sql.concat(HistWorkflowMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistWorkflowMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistWorkflowMaster>)QueryUtil.list(q,
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
	 * Removes all the hist workflow masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistWorkflowMaster histWorkflowMaster : findAll()) {
			remove(histWorkflowMaster);
		}
	}

	/**
	 * Returns the number of hist workflow masters.
	 *
	 * @return the number of hist workflow masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTWORKFLOWMASTER);

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
		return HistWorkflowMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist workflow master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistWorkflowMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTWORKFLOWMASTER = "SELECT histWorkflowMaster FROM HistWorkflowMaster histWorkflowMaster";
	private static final String _SQL_SELECT_HISTWORKFLOWMASTER_WHERE_PKS_IN = "SELECT histWorkflowMaster FROM HistWorkflowMaster histWorkflowMaster WHERE WORKFLOW_MASTER_SID IN (";
	private static final String _SQL_COUNT_HISTWORKFLOWMASTER = "SELECT COUNT(histWorkflowMaster) FROM HistWorkflowMaster histWorkflowMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histWorkflowMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistWorkflowMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistWorkflowMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "fileSize", "actionDate", "workflowStatusId",
				"actionFlag", "modifiedBy", "createdDate", "approvalLevel",
				"noOfApproval", "fileName", "uploadedBy", "modifiedDate",
				"accClosureMasterSid", "notes", "workflowMasterSid",
				"workflowId", "projectionMasterSid", "uploadedDate", "fileType",
				"approvedBy", "workflowDescrption", "approvedDate"
			});
}