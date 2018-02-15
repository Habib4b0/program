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

import com.stpl.app.exception.NoSuchWorkflowProfileException;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.model.impl.WorkflowProfileImpl;
import com.stpl.app.model.impl.WorkflowProfileModelImpl;
import com.stpl.app.service.persistence.WorkflowProfilePersistence;

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
 * The persistence implementation for the workflow profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProfilePersistence
 * @see com.stpl.app.service.persistence.WorkflowProfileUtil
 * @generated
 */
@ProviderType
public class WorkflowProfilePersistenceImpl extends BasePersistenceImpl<WorkflowProfile>
	implements WorkflowProfilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowProfileUtil} to access the workflow profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowProfileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileModelImpl.FINDER_CACHE_ENABLED,
			WorkflowProfileImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileModelImpl.FINDER_CACHE_ENABLED,
			WorkflowProfileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WorkflowProfilePersistenceImpl() {
		setModelClass(WorkflowProfile.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("startHour", "START_HOUR");
			dbColumnNames.put("frequency", "FREQUENCY");
			dbColumnNames.put("processName", "PROCESS_NAME");
			dbColumnNames.put("startMinutes1", "START_MINUTES1");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("emailNotificationFailureCc",
				"EMAIL_NOTIFICATION_FAILURE_CC");
			dbColumnNames.put("failureMailSubject", "FAILURE_MAIL_SUBJECT");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("schemaName", "SCHEMA_NAME");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("scheduleLastRun", "SCHEDULE_LAST_RUN");
			dbColumnNames.put("emailNotificationSuccessTo",
				"EMAIL_NOTIFICATION_SUCCESS_TO");
			dbColumnNames.put("startMinutes3", "START_MINUTES3");
			dbColumnNames.put("startMinutes2", "START_MINUTES2");
			dbColumnNames.put("processSid", "PROCESS_SID");
			dbColumnNames.put("successMailBody", "SUCCESS_MAIL_BODY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("emailNotificationSuccessCc",
				"EMAIL_NOTIFICATION_SUCCESS_CC");
			dbColumnNames.put("emailNotificationFailureTo",
				"EMAIL_NOTIFICATION_FAILURE_TO");
			dbColumnNames.put("failureMailBody", "FAILURE_MAIL_BODY");
			dbColumnNames.put("activeFlag", "ACTIVE_FLAG");
			dbColumnNames.put("processDisplayName", "PROCESS_DISPLAY_NAME");
			dbColumnNames.put("startMinutes", "START_MINUTES");
			dbColumnNames.put("manualLastRun", "MANUAL_LAST_RUN");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("slaCalendarMasterSid", "SLA_CALENDAR_MASTER_SID");
			dbColumnNames.put("successMailSubject", "SUCCESS_MAIL_SUBJECT");
			dbColumnNames.put("startHour3", "START_HOUR3");
			dbColumnNames.put("startHour2", "START_HOUR2");
			dbColumnNames.put("userSid", "USER_SID");
			dbColumnNames.put("startHour1", "START_HOUR1");
			dbColumnNames.put("processType", "PROCESS_TYPE");
			dbColumnNames.put("scriptName", "SCRIPT_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the workflow profile in the entity cache if it is enabled.
	 *
	 * @param workflowProfile the workflow profile
	 */
	@Override
	public void cacheResult(WorkflowProfile workflowProfile) {
		entityCache.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileImpl.class, workflowProfile.getPrimaryKey(),
			workflowProfile);

		workflowProfile.resetOriginalValues();
	}

	/**
	 * Caches the workflow profiles in the entity cache if it is enabled.
	 *
	 * @param workflowProfiles the workflow profiles
	 */
	@Override
	public void cacheResult(List<WorkflowProfile> workflowProfiles) {
		for (WorkflowProfile workflowProfile : workflowProfiles) {
			if (entityCache.getResult(
						WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowProfileImpl.class,
						workflowProfile.getPrimaryKey()) == null) {
				cacheResult(workflowProfile);
			}
			else {
				workflowProfile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow profiles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkflowProfileImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow profile.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowProfile workflowProfile) {
		entityCache.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileImpl.class, workflowProfile.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorkflowProfile> workflowProfiles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowProfile workflowProfile : workflowProfiles) {
			entityCache.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowProfileImpl.class, workflowProfile.getPrimaryKey());
		}
	}

	/**
	 * Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
	 *
	 * @param processSid the primary key for the new workflow profile
	 * @return the new workflow profile
	 */
	@Override
	public WorkflowProfile create(int processSid) {
		WorkflowProfile workflowProfile = new WorkflowProfileImpl();

		workflowProfile.setNew(true);
		workflowProfile.setPrimaryKey(processSid);

		return workflowProfile;
	}

	/**
	 * Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param processSid the primary key of the workflow profile
	 * @return the workflow profile that was removed
	 * @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile remove(int processSid)
		throws NoSuchWorkflowProfileException {
		return remove((Serializable)processSid);
	}

	/**
	 * Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow profile
	 * @return the workflow profile that was removed
	 * @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile remove(Serializable primaryKey)
		throws NoSuchWorkflowProfileException {
		Session session = null;

		try {
			session = openSession();

			WorkflowProfile workflowProfile = (WorkflowProfile)session.get(WorkflowProfileImpl.class,
					primaryKey);

			if (workflowProfile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowProfile);
		}
		catch (NoSuchWorkflowProfileException nsee) {
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
	protected WorkflowProfile removeImpl(WorkflowProfile workflowProfile) {
		workflowProfile = toUnwrappedModel(workflowProfile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workflowProfile)) {
				workflowProfile = (WorkflowProfile)session.get(WorkflowProfileImpl.class,
						workflowProfile.getPrimaryKeyObj());
			}

			if (workflowProfile != null) {
				session.delete(workflowProfile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workflowProfile != null) {
			clearCache(workflowProfile);
		}

		return workflowProfile;
	}

	@Override
	public WorkflowProfile updateImpl(WorkflowProfile workflowProfile) {
		workflowProfile = toUnwrappedModel(workflowProfile);

		boolean isNew = workflowProfile.isNew();

		Session session = null;

		try {
			session = openSession();

			if (workflowProfile.isNew()) {
				session.save(workflowProfile);

				workflowProfile.setNew(false);
			}
			else {
				workflowProfile = (WorkflowProfile)session.merge(workflowProfile);
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

		entityCache.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowProfileImpl.class, workflowProfile.getPrimaryKey(),
			workflowProfile, false);

		workflowProfile.resetOriginalValues();

		return workflowProfile;
	}

	protected WorkflowProfile toUnwrappedModel(WorkflowProfile workflowProfile) {
		if (workflowProfile instanceof WorkflowProfileImpl) {
			return workflowProfile;
		}

		WorkflowProfileImpl workflowProfileImpl = new WorkflowProfileImpl();

		workflowProfileImpl.setNew(workflowProfile.isNew());
		workflowProfileImpl.setPrimaryKey(workflowProfile.getPrimaryKey());

		workflowProfileImpl.setStartHour(workflowProfile.getStartHour());
		workflowProfileImpl.setFrequency(workflowProfile.getFrequency());
		workflowProfileImpl.setProcessName(workflowProfile.getProcessName());
		workflowProfileImpl.setStartMinutes1(workflowProfile.getStartMinutes1());
		workflowProfileImpl.setEndDate(workflowProfile.getEndDate());
		workflowProfileImpl.setEmailNotificationFailureCc(workflowProfile.getEmailNotificationFailureCc());
		workflowProfileImpl.setFailureMailSubject(workflowProfile.getFailureMailSubject());
		workflowProfileImpl.setModifiedDate(workflowProfile.getModifiedDate());
		workflowProfileImpl.setSchemaName(workflowProfile.getSchemaName());
		workflowProfileImpl.setCreatedBy(workflowProfile.getCreatedBy());
		workflowProfileImpl.setCreatedDate(workflowProfile.getCreatedDate());
		workflowProfileImpl.setScheduleLastRun(workflowProfile.getScheduleLastRun());
		workflowProfileImpl.setEmailNotificationSuccessTo(workflowProfile.getEmailNotificationSuccessTo());
		workflowProfileImpl.setStartMinutes3(workflowProfile.getStartMinutes3());
		workflowProfileImpl.setStartMinutes2(workflowProfile.getStartMinutes2());
		workflowProfileImpl.setProcessSid(workflowProfile.getProcessSid());
		workflowProfileImpl.setSuccessMailBody(workflowProfile.getSuccessMailBody());
		workflowProfileImpl.setInboundStatus(workflowProfile.getInboundStatus());
		workflowProfileImpl.setModifiedBy(workflowProfile.getModifiedBy());
		workflowProfileImpl.setEmailNotificationSuccessCc(workflowProfile.getEmailNotificationSuccessCc());
		workflowProfileImpl.setEmailNotificationFailureTo(workflowProfile.getEmailNotificationFailureTo());
		workflowProfileImpl.setFailureMailBody(workflowProfile.getFailureMailBody());
		workflowProfileImpl.setActiveFlag(workflowProfile.getActiveFlag());
		workflowProfileImpl.setProcessDisplayName(workflowProfile.getProcessDisplayName());
		workflowProfileImpl.setStartMinutes(workflowProfile.getStartMinutes());
		workflowProfileImpl.setManualLastRun(workflowProfile.getManualLastRun());
		workflowProfileImpl.setStartDate(workflowProfile.getStartDate());
		workflowProfileImpl.setSlaCalendarMasterSid(workflowProfile.getSlaCalendarMasterSid());
		workflowProfileImpl.setSuccessMailSubject(workflowProfile.getSuccessMailSubject());
		workflowProfileImpl.setStartHour3(workflowProfile.getStartHour3());
		workflowProfileImpl.setStartHour2(workflowProfile.getStartHour2());
		workflowProfileImpl.setUserSid(workflowProfile.getUserSid());
		workflowProfileImpl.setStartHour1(workflowProfile.getStartHour1());
		workflowProfileImpl.setProcessType(workflowProfile.getProcessType());
		workflowProfileImpl.setScriptName(workflowProfile.getScriptName());

		return workflowProfileImpl;
	}

	/**
	 * Returns the workflow profile with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow profile
	 * @return the workflow profile
	 * @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkflowProfileException {
		WorkflowProfile workflowProfile = fetchByPrimaryKey(primaryKey);

		if (workflowProfile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkflowProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workflowProfile;
	}

	/**
	 * Returns the workflow profile with the primary key or throws a {@link NoSuchWorkflowProfileException} if it could not be found.
	 *
	 * @param processSid the primary key of the workflow profile
	 * @return the workflow profile
	 * @throws NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile findByPrimaryKey(int processSid)
		throws NoSuchWorkflowProfileException {
		return findByPrimaryKey((Serializable)processSid);
	}

	/**
	 * Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow profile
	 * @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowProfileImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkflowProfile workflowProfile = (WorkflowProfile)serializable;

		if (workflowProfile == null) {
			Session session = null;

			try {
				session = openSession();

				workflowProfile = (WorkflowProfile)session.get(WorkflowProfileImpl.class,
						primaryKey);

				if (workflowProfile != null) {
					cacheResult(workflowProfile);
				}
				else {
					entityCache.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowProfileImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProfileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workflowProfile;
	}

	/**
	 * Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param processSid the primary key of the workflow profile
	 * @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
	 */
	@Override
	public WorkflowProfile fetchByPrimaryKey(int processSid) {
		return fetchByPrimaryKey((Serializable)processSid);
	}

	@Override
	public Map<Serializable, WorkflowProfile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkflowProfile> map = new HashMap<Serializable, WorkflowProfile>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkflowProfile workflowProfile = fetchByPrimaryKey(primaryKey);

			if (workflowProfile != null) {
				map.put(primaryKey, workflowProfile);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProfileImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkflowProfile)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKFLOWPROFILE_WHERE_PKS_IN);

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

			for (WorkflowProfile workflowProfile : (List<WorkflowProfile>)q.list()) {
				map.put(workflowProfile.getPrimaryKeyObj(), workflowProfile);

				cacheResult(workflowProfile);

				uncachedPrimaryKeys.remove(workflowProfile.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
					WorkflowProfileImpl.class, primaryKey, nullModel);
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
	 * Returns all the workflow profiles.
	 *
	 * @return the workflow profiles
	 */
	@Override
	public List<WorkflowProfile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow profiles
	 * @param end the upper bound of the range of workflow profiles (not inclusive)
	 * @return the range of workflow profiles
	 */
	@Override
	public List<WorkflowProfile> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow profiles
	 * @param end the upper bound of the range of workflow profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow profiles
	 */
	@Override
	public List<WorkflowProfile> findAll(int start, int end,
		OrderByComparator<WorkflowProfile> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workflow profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow profiles
	 * @param end the upper bound of the range of workflow profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of workflow profiles
	 */
	@Override
	public List<WorkflowProfile> findAll(int start, int end,
		OrderByComparator<WorkflowProfile> orderByComparator,
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

		List<WorkflowProfile> list = null;

		if (retrieveFromCache) {
			list = (List<WorkflowProfile>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKFLOWPROFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWPROFILE;

				if (pagination) {
					sql = sql.concat(WorkflowProfileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkflowProfile>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkflowProfile>)QueryUtil.list(q,
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
	 * Removes all the workflow profiles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkflowProfile workflowProfile : findAll()) {
			remove(workflowProfile);
		}
	}

	/**
	 * Returns the number of workflow profiles.
	 *
	 * @return the number of workflow profiles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWPROFILE);

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
		return WorkflowProfileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the workflow profile persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkflowProfileImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WORKFLOWPROFILE = "SELECT workflowProfile FROM WorkflowProfile workflowProfile";
	private static final String _SQL_SELECT_WORKFLOWPROFILE_WHERE_PKS_IN = "SELECT workflowProfile FROM WorkflowProfile workflowProfile WHERE PROCESS_SID IN (";
	private static final String _SQL_COUNT_WORKFLOWPROFILE = "SELECT COUNT(workflowProfile) FROM WorkflowProfile workflowProfile";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowProfile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowProfile exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WorkflowProfilePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"startHour", "frequency", "processName", "startMinutes1",
				"endDate", "emailNotificationFailureCc", "failureMailSubject",
				"modifiedDate", "schemaName", "createdBy", "createdDate",
				"scheduleLastRun", "emailNotificationSuccessTo", "startMinutes3",
				"startMinutes2", "processSid", "successMailBody",
				"inboundStatus", "modifiedBy", "emailNotificationSuccessCc",
				"emailNotificationFailureTo", "failureMailBody", "activeFlag",
				"processDisplayName", "startMinutes", "manualLastRun",
				"startDate", "slaCalendarMasterSid", "successMailSubject",
				"startHour3", "startHour2", "userSid", "startHour1",
				"processType", "scriptName"
			});
}