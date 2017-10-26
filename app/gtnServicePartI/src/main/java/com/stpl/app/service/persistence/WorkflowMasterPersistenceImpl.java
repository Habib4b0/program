package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchWorkflowMasterException;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.model.impl.WorkflowMasterImpl;
import com.stpl.app.model.impl.WorkflowMasterModelImpl;
import com.stpl.app.service.persistence.WorkflowMasterPersistence;

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
 * The persistence implementation for the workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowMasterPersistence
 * @see WorkflowMasterUtil
 * @generated
 */
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
    private static final String _SQL_SELECT_WORKFLOWMASTER = "SELECT workflowMaster FROM WorkflowMaster workflowMaster";
    private static final String _SQL_COUNT_WORKFLOWMASTER = "SELECT COUNT(workflowMaster) FROM WorkflowMaster workflowMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "workflowMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(WorkflowMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "fileSize", "workflowStatusId", "modifiedBy",
                "createdDate", "approvalLevel", "noOfApproval", "fileName",
                "uploadedBy", "modifiedDate", "accClosureMasterSid", "notes",
                "workflowMasterSid", "workflowId", "projectionMasterSid",
                "uploadedDate", "fileType", "approvedBy", "workflowDescrption",
                "approvedDate", "contractMasterSid", "contractStructure"
            });
    private static WorkflowMaster _nullWorkflowMaster = new WorkflowMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<WorkflowMaster> toCacheModel() {
                return _nullWorkflowMasterCacheModel;
            }
        };

    private static CacheModel<WorkflowMaster> _nullWorkflowMasterCacheModel = new CacheModel<WorkflowMaster>() {
            @Override
            public WorkflowMaster toEntityModel() {
                return _nullWorkflowMaster;
            }
        };

    public WorkflowMasterPersistenceImpl() {
        setModelClass(WorkflowMaster.class);
    }

    /**
     * Caches the workflow master in the entity cache if it is enabled.
     *
     * @param workflowMaster the workflow master
     */
    @Override
    public void cacheResult(WorkflowMaster workflowMaster) {
        EntityCacheUtil.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
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
            if (EntityCacheUtil.getResult(
                        WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                        WorkflowMasterImpl.class, workflowMaster.getPrimaryKey()) == null) {
                cacheResult(workflowMaster);
            } else {
                workflowMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all workflow masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(WorkflowMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(WorkflowMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the workflow master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(WorkflowMaster workflowMaster) {
        EntityCacheUtil.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
            WorkflowMasterImpl.class, workflowMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<WorkflowMaster> workflowMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (WorkflowMaster workflowMaster : workflowMasters) {
            EntityCacheUtil.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
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
     * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster remove(int workflowMasterSid)
        throws NoSuchWorkflowMasterException, SystemException {
        return remove((Serializable) workflowMasterSid);
    }

    /**
     * Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the workflow master
     * @return the workflow master that was removed
     * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster remove(Serializable primaryKey)
        throws NoSuchWorkflowMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            WorkflowMaster workflowMaster = (WorkflowMaster) session.get(WorkflowMasterImpl.class,
                    primaryKey);

            if (workflowMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(workflowMaster);
        } catch (NoSuchWorkflowMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected WorkflowMaster removeImpl(WorkflowMaster workflowMaster)
        throws SystemException {
        workflowMaster = toUnwrappedModel(workflowMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(workflowMaster)) {
                workflowMaster = (WorkflowMaster) session.get(WorkflowMasterImpl.class,
                        workflowMaster.getPrimaryKeyObj());
            }

            if (workflowMaster != null) {
                session.delete(workflowMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (workflowMaster != null) {
            clearCache(workflowMaster);
        }

        return workflowMaster;
    }

    @Override
    public WorkflowMaster updateImpl(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws SystemException {
        workflowMaster = toUnwrappedModel(workflowMaster);

        boolean isNew = workflowMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (workflowMaster.isNew()) {
                session.save(workflowMaster);

                workflowMaster.setNew(false);
            } else {
                session.merge(workflowMaster);
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

        EntityCacheUtil.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
            WorkflowMasterImpl.class, workflowMaster.getPrimaryKey(),
            workflowMaster);

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
     * Returns the workflow master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the workflow master
     * @return the workflow master
     * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchWorkflowMasterException, SystemException {
        WorkflowMaster workflowMaster = fetchByPrimaryKey(primaryKey);

        if (workflowMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return workflowMaster;
    }

    /**
     * Returns the workflow master with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowMasterException} if it could not be found.
     *
     * @param workflowMasterSid the primary key of the workflow master
     * @return the workflow master
     * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster findByPrimaryKey(int workflowMasterSid)
        throws NoSuchWorkflowMasterException, SystemException {
        return findByPrimaryKey((Serializable) workflowMasterSid);
    }

    /**
     * Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the workflow master
     * @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        WorkflowMaster workflowMaster = (WorkflowMaster) EntityCacheUtil.getResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                WorkflowMasterImpl.class, primaryKey);

        if (workflowMaster == _nullWorkflowMaster) {
            return null;
        }

        if (workflowMaster == null) {
            Session session = null;

            try {
                session = openSession();

                workflowMaster = (WorkflowMaster) session.get(WorkflowMasterImpl.class,
                        primaryKey);

                if (workflowMaster != null) {
                    cacheResult(workflowMaster);
                } else {
                    EntityCacheUtil.putResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                        WorkflowMasterImpl.class, primaryKey,
                        _nullWorkflowMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(WorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                    WorkflowMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
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
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WorkflowMaster fetchByPrimaryKey(int workflowMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) workflowMasterSid);
    }

    /**
     * Returns all the workflow masters.
     *
     * @return the workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the workflow masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of workflow masters
     * @param end the upper bound of the range of workflow masters (not inclusive)
     * @return the range of workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the workflow masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of workflow masters
     * @param end the upper bound of the range of workflow masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WorkflowMaster> findAll(int start, int end,
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

        List<WorkflowMaster> list = (List<WorkflowMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_WORKFLOWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
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
                    list = (List<WorkflowMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<WorkflowMaster>(list);
                } else {
                    list = (List<WorkflowMaster>) QueryUtil.list(q,
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
     * Removes all the workflow masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (WorkflowMaster workflowMaster : findAll()) {
            remove(workflowMaster);
        }
    }

    /**
     * Returns the number of workflow masters.
     *
     * @return the number of workflow masters
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

                Query q = session.createQuery(_SQL_COUNT_WORKFLOWMASTER);

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
     * Initializes the workflow master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.WorkflowMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<WorkflowMaster>> listenersList = new ArrayList<ModelListener<WorkflowMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<WorkflowMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(WorkflowMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
