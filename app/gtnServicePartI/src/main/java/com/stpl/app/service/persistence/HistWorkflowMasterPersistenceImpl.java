package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistWorkflowMasterException;
import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.app.model.impl.HistWorkflowMasterImpl;
import com.stpl.app.model.impl.HistWorkflowMasterModelImpl;
import com.stpl.app.service.persistence.HistWorkflowMasterPersistence;

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
 * The persistence implementation for the hist workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistWorkflowMasterPersistence
 * @see HistWorkflowMasterUtil
 * @generated
 */
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
    private static final String _SQL_SELECT_HISTWORKFLOWMASTER = "SELECT histWorkflowMaster FROM HistWorkflowMaster histWorkflowMaster";
    private static final String _SQL_COUNT_HISTWORKFLOWMASTER = "SELECT COUNT(histWorkflowMaster) FROM HistWorkflowMaster histWorkflowMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histWorkflowMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistWorkflowMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistWorkflowMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "fileSize", "actionDate", "workflowStatusId",
                "actionFlag", "modifiedBy", "createdDate", "approvalLevel",
                "noOfApproval", "fileName", "uploadedBy", "modifiedDate",
                "accClosureMasterSid", "notes", "workflowMasterSid",
                "workflowId", "projectionMasterSid", "uploadedDate", "fileType",
                "approvedBy", "workflowDescrption", "approvedDate"
            });
    private static HistWorkflowMaster _nullHistWorkflowMaster = new HistWorkflowMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistWorkflowMaster> toCacheModel() {
                return _nullHistWorkflowMasterCacheModel;
            }
        };

    private static CacheModel<HistWorkflowMaster> _nullHistWorkflowMasterCacheModel =
        new CacheModel<HistWorkflowMaster>() {
            @Override
            public HistWorkflowMaster toEntityModel() {
                return _nullHistWorkflowMaster;
            }
        };

    public HistWorkflowMasterPersistenceImpl() {
        setModelClass(HistWorkflowMaster.class);
    }

    /**
     * Caches the hist workflow master in the entity cache if it is enabled.
     *
     * @param histWorkflowMaster the hist workflow master
     */
    @Override
    public void cacheResult(HistWorkflowMaster histWorkflowMaster) {
        EntityCacheUtil.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
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
            if (EntityCacheUtil.getResult(
                        HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                        HistWorkflowMasterImpl.class,
                        histWorkflowMaster.getPrimaryKey()) == null) {
                cacheResult(histWorkflowMaster);
            } else {
                histWorkflowMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist workflow masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistWorkflowMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistWorkflowMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist workflow master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistWorkflowMaster histWorkflowMaster) {
        EntityCacheUtil.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
            HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HistWorkflowMaster> histWorkflowMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistWorkflowMaster histWorkflowMaster : histWorkflowMasters) {
            EntityCacheUtil.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
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
     * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster remove(int workflowMasterSid)
        throws NoSuchHistWorkflowMasterException, SystemException {
        return remove((Serializable) workflowMasterSid);
    }

    /**
     * Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist workflow master
     * @return the hist workflow master that was removed
     * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster remove(Serializable primaryKey)
        throws NoSuchHistWorkflowMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistWorkflowMaster histWorkflowMaster = (HistWorkflowMaster) session.get(HistWorkflowMasterImpl.class,
                    primaryKey);

            if (histWorkflowMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histWorkflowMaster);
        } catch (NoSuchHistWorkflowMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistWorkflowMaster removeImpl(
        HistWorkflowMaster histWorkflowMaster) throws SystemException {
        histWorkflowMaster = toUnwrappedModel(histWorkflowMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histWorkflowMaster)) {
                histWorkflowMaster = (HistWorkflowMaster) session.get(HistWorkflowMasterImpl.class,
                        histWorkflowMaster.getPrimaryKeyObj());
            }

            if (histWorkflowMaster != null) {
                session.delete(histWorkflowMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histWorkflowMaster != null) {
            clearCache(histWorkflowMaster);
        }

        return histWorkflowMaster;
    }

    @Override
    public HistWorkflowMaster updateImpl(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws SystemException {
        histWorkflowMaster = toUnwrappedModel(histWorkflowMaster);

        boolean isNew = histWorkflowMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histWorkflowMaster.isNew()) {
                session.save(histWorkflowMaster);

                histWorkflowMaster.setNew(false);
            } else {
                session.merge(histWorkflowMaster);
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

        EntityCacheUtil.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
            HistWorkflowMasterImpl.class, histWorkflowMaster.getPrimaryKey(),
            histWorkflowMaster);

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
     * Returns the hist workflow master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist workflow master
     * @return the hist workflow master
     * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistWorkflowMasterException, SystemException {
        HistWorkflowMaster histWorkflowMaster = fetchByPrimaryKey(primaryKey);

        if (histWorkflowMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistWorkflowMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histWorkflowMaster;
    }

    /**
     * Returns the hist workflow master with the primary key or throws a {@link com.stpl.app.NoSuchHistWorkflowMasterException} if it could not be found.
     *
     * @param workflowMasterSid the primary key of the hist workflow master
     * @return the hist workflow master
     * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster findByPrimaryKey(int workflowMasterSid)
        throws NoSuchHistWorkflowMasterException, SystemException {
        return findByPrimaryKey((Serializable) workflowMasterSid);
    }

    /**
     * Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist workflow master
     * @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistWorkflowMaster histWorkflowMaster = (HistWorkflowMaster) EntityCacheUtil.getResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                HistWorkflowMasterImpl.class, primaryKey);

        if (histWorkflowMaster == _nullHistWorkflowMaster) {
            return null;
        }

        if (histWorkflowMaster == null) {
            Session session = null;

            try {
                session = openSession();

                histWorkflowMaster = (HistWorkflowMaster) session.get(HistWorkflowMasterImpl.class,
                        primaryKey);

                if (histWorkflowMaster != null) {
                    cacheResult(histWorkflowMaster);
                } else {
                    EntityCacheUtil.putResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                        HistWorkflowMasterImpl.class, primaryKey,
                        _nullHistWorkflowMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistWorkflowMasterModelImpl.ENTITY_CACHE_ENABLED,
                    HistWorkflowMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
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
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistWorkflowMaster fetchByPrimaryKey(int workflowMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) workflowMasterSid);
    }

    /**
     * Returns all the hist workflow masters.
     *
     * @return the hist workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistWorkflowMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist workflow masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist workflow masters
     * @param end the upper bound of the range of hist workflow masters (not inclusive)
     * @return the range of hist workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistWorkflowMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist workflow masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist workflow masters
     * @param end the upper bound of the range of hist workflow masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist workflow masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistWorkflowMaster> findAll(int start, int end,
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

        List<HistWorkflowMaster> list = (List<HistWorkflowMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTWORKFLOWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
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
                    list = (List<HistWorkflowMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistWorkflowMaster>(list);
                } else {
                    list = (List<HistWorkflowMaster>) QueryUtil.list(q,
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
     * Removes all the hist workflow masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistWorkflowMaster histWorkflowMaster : findAll()) {
            remove(histWorkflowMaster);
        }
    }

    /**
     * Returns the number of hist workflow masters.
     *
     * @return the number of hist workflow masters
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

                Query q = session.createQuery(_SQL_COUNT_HISTWORKFLOWMASTER);

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
     * Initializes the hist workflow master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistWorkflowMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistWorkflowMaster>> listenersList = new ArrayList<ModelListener<HistWorkflowMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistWorkflowMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistWorkflowMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
