package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchWorkflowProfileException;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.model.impl.WorkflowProfileImpl;
import com.stpl.app.model.impl.WorkflowProfileModelImpl;
import com.stpl.app.service.persistence.WorkflowProfilePersistence;

import com.stpl.portal.kernel.cache.CacheRegistryUtil;
import com.stpl.portal.kernel.dao.orm.EntityCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderCacheUtil;
import com.stpl.portal.kernel.dao.orm.FinderPath;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.QueryUtil;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.log.Log;
import com.stpl.portal.kernel.log.LogFactoryUtil;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.InstanceFactory;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.PropsKeys;
import com.stpl.portal.kernel.util.PropsUtil;
import com.stpl.portal.kernel.util.SetUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringUtil;
import com.stpl.portal.kernel.util.UnmodifiableList;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.ModelListener;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * @see WorkflowProfileUtil
 * @generated
 */
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
    private static final String _SQL_SELECT_WORKFLOWPROFILE = "SELECT workflowProfile FROM WorkflowProfile workflowProfile";
    private static final String _SQL_COUNT_WORKFLOWPROFILE = "SELECT COUNT(workflowProfile) FROM WorkflowProfile workflowProfile";
    private static final String _ORDER_BY_ENTITY_ALIAS = "workflowProfile.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowProfile exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(WorkflowProfilePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
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
    private static WorkflowProfile _nullWorkflowProfile = new WorkflowProfileImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<WorkflowProfile> toCacheModel() {
                return _nullWorkflowProfileCacheModel;
            }
        };

    private static CacheModel<WorkflowProfile> _nullWorkflowProfileCacheModel = new CacheModel<WorkflowProfile>() {
            @Override
            public WorkflowProfile toEntityModel() {
                return _nullWorkflowProfile;
            }
        };

    public WorkflowProfilePersistenceImpl() {
        setModelClass(WorkflowProfile.class);
    }

    /**
     * Caches the workflow profile in the entity cache if it is enabled.
     *
     * @param workflowProfile the workflow profile
     */
    @Override
    public void cacheResult(WorkflowProfile workflowProfile) {
        EntityCacheUtil.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
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
            if (EntityCacheUtil.getResult(
                        WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
                        WorkflowProfileImpl.class,
                        workflowProfile.getPrimaryKey()) == null) {
                cacheResult(workflowProfile);
            } else {
                workflowProfile.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all workflow profiles.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(WorkflowProfileImpl.class.getName());
        }

        EntityCacheUtil.clearCache(WorkflowProfileImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the workflow profile.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(WorkflowProfile workflowProfile) {
        EntityCacheUtil.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
            WorkflowProfileImpl.class, workflowProfile.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<WorkflowProfile> workflowProfiles) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (WorkflowProfile workflowProfile : workflowProfiles) {
            EntityCacheUtil.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
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
     * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile remove(int processSid)
        throws NoSuchWorkflowProfileException, SystemException {
        return remove((Serializable) processSid);
    }

    /**
     * Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the workflow profile
     * @return the workflow profile that was removed
     * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile remove(Serializable primaryKey)
        throws NoSuchWorkflowProfileException, SystemException {
        Session session = null;

        try {
            session = openSession();

            WorkflowProfile workflowProfile = (WorkflowProfile) session.get(WorkflowProfileImpl.class,
                    primaryKey);

            if (workflowProfile == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchWorkflowProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(workflowProfile);
        } catch (NoSuchWorkflowProfileException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected WorkflowProfile removeImpl(WorkflowProfile workflowProfile)
        throws SystemException {
        workflowProfile = toUnwrappedModel(workflowProfile);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(workflowProfile)) {
                workflowProfile = (WorkflowProfile) session.get(WorkflowProfileImpl.class,
                        workflowProfile.getPrimaryKeyObj());
            }

            if (workflowProfile != null) {
                session.delete(workflowProfile);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (workflowProfile != null) {
            clearCache(workflowProfile);
        }

        return workflowProfile;
    }

    @Override
    public WorkflowProfile updateImpl(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws SystemException {
        workflowProfile = toUnwrappedModel(workflowProfile);

        boolean isNew = workflowProfile.isNew();

        Session session = null;

        try {
            session = openSession();

            if (workflowProfile.isNew()) {
                session.save(workflowProfile);

                workflowProfile.setNew(false);
            } else {
                session.merge(workflowProfile);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
            WorkflowProfileImpl.class, workflowProfile.getPrimaryKey(),
            workflowProfile);

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
     * Returns the workflow profile with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the workflow profile
     * @return the workflow profile
     * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile findByPrimaryKey(Serializable primaryKey)
        throws NoSuchWorkflowProfileException, SystemException {
        WorkflowProfile workflowProfile = fetchByPrimaryKey(primaryKey);

        if (workflowProfile == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchWorkflowProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return workflowProfile;
    }

    /**
     * Returns the workflow profile with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowProfileException} if it could not be found.
     *
     * @param processSid the primary key of the workflow profile
     * @return the workflow profile
     * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile findByPrimaryKey(int processSid)
        throws NoSuchWorkflowProfileException, SystemException {
        return findByPrimaryKey((Serializable) processSid);
    }

    /**
     * Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the workflow profile
     * @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        WorkflowProfile workflowProfile = (WorkflowProfile) EntityCacheUtil.getResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
                WorkflowProfileImpl.class, primaryKey);

        if (workflowProfile == _nullWorkflowProfile) {
            return null;
        }

        if (workflowProfile == null) {
            Session session = null;

            try {
                session = openSession();

                workflowProfile = (WorkflowProfile) session.get(WorkflowProfileImpl.class,
                        primaryKey);

                if (workflowProfile != null) {
                    cacheResult(workflowProfile);
                } else {
                    EntityCacheUtil.putResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
                        WorkflowProfileImpl.class, primaryKey,
                        _nullWorkflowProfile);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(WorkflowProfileModelImpl.ENTITY_CACHE_ENABLED,
                    WorkflowProfileImpl.class, primaryKey);

                throw processException(e);
            } finally {
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
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowProfile fetchByPrimaryKey(int processSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) processSid);
    }

    /**
     * Returns all the workflow profiles.
     *
     * @return the workflow profiles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowProfile> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the workflow profiles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of workflow profiles
     * @param end the upper bound of the range of workflow profiles (not inclusive)
     * @return the range of workflow profiles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowProfile> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the workflow profiles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of workflow profiles
     * @param end the upper bound of the range of workflow profiles (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of workflow profiles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowProfile> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<WorkflowProfile> list = (List<WorkflowProfile>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_WORKFLOWPROFILE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
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
                    list = (List<WorkflowProfile>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<WorkflowProfile>(list);
                } else {
                    list = (List<WorkflowProfile>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the workflow profiles from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (WorkflowProfile workflowProfile : findAll()) {
            remove(workflowProfile);
        }
    }

    /**
     * Returns the number of workflow profiles.
     *
     * @return the number of workflow profiles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_WORKFLOWPROFILE);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the workflow profile persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.WorkflowProfile")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<WorkflowProfile>> listenersList = new ArrayList<ModelListener<WorkflowProfile>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<WorkflowProfile>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(WorkflowProfileImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
