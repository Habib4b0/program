package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMasterDataFilesException;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.impl.MasterDataFilesImpl;
import com.stpl.app.model.impl.MasterDataFilesModelImpl;
import com.stpl.app.service.persistence.MasterDataFilesPersistence;

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
 * The persistence implementation for the master data files service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataFilesPersistence
 * @see MasterDataFilesUtil
 * @generated
 */
public class MasterDataFilesPersistenceImpl extends BasePersistenceImpl<MasterDataFiles>
    implements MasterDataFilesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MasterDataFilesUtil} to access the master data files persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MasterDataFilesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesModelImpl.FINDER_CACHE_ENABLED,
            MasterDataFilesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesModelImpl.FINDER_CACHE_ENABLED,
            MasterDataFilesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MASTERDATAFILES = "SELECT masterDataFiles FROM MasterDataFiles masterDataFiles";
    private static final String _SQL_COUNT_MASTERDATAFILES = "SELECT COUNT(masterDataFiles) FROM MasterDataFiles masterDataFiles";
    private static final String _ORDER_BY_ENTITY_ALIAS = "masterDataFiles.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MasterDataFiles exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MasterDataFilesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "masterTableSid", "masterDataFilesSid", "masterTableName",
                "filePath", "createdDate", "createdBy"
            });
    private static MasterDataFiles _nullMasterDataFiles = new MasterDataFilesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MasterDataFiles> toCacheModel() {
                return _nullMasterDataFilesCacheModel;
            }
        };

    private static CacheModel<MasterDataFiles> _nullMasterDataFilesCacheModel = new CacheModel<MasterDataFiles>() {
            @Override
            public MasterDataFiles toEntityModel() {
                return _nullMasterDataFiles;
            }
        };

    public MasterDataFilesPersistenceImpl() {
        setModelClass(MasterDataFiles.class);
    }

    /**
     * Caches the master data files in the entity cache if it is enabled.
     *
     * @param masterDataFiles the master data files
     */
    @Override
    public void cacheResult(MasterDataFiles masterDataFiles) {
        EntityCacheUtil.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey(),
            masterDataFiles);

        masterDataFiles.resetOriginalValues();
    }

    /**
     * Caches the master data fileses in the entity cache if it is enabled.
     *
     * @param masterDataFileses the master data fileses
     */
    @Override
    public void cacheResult(List<MasterDataFiles> masterDataFileses) {
        for (MasterDataFiles masterDataFiles : masterDataFileses) {
            if (EntityCacheUtil.getResult(
                        MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
                        MasterDataFilesImpl.class,
                        masterDataFiles.getPrimaryKey()) == null) {
                cacheResult(masterDataFiles);
            } else {
                masterDataFiles.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all master data fileses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MasterDataFilesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MasterDataFilesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the master data files.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MasterDataFiles masterDataFiles) {
        EntityCacheUtil.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MasterDataFiles> masterDataFileses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MasterDataFiles masterDataFiles : masterDataFileses) {
            EntityCacheUtil.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
                MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey());
        }
    }

    /**
     * Creates a new master data files with the primary key. Does not add the master data files to the database.
     *
     * @param masterDataFilesSid the primary key for the new master data files
     * @return the new master data files
     */
    @Override
    public MasterDataFiles create(int masterDataFilesSid) {
        MasterDataFiles masterDataFiles = new MasterDataFilesImpl();

        masterDataFiles.setNew(true);
        masterDataFiles.setPrimaryKey(masterDataFilesSid);

        return masterDataFiles;
    }

    /**
     * Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param masterDataFilesSid the primary key of the master data files
     * @return the master data files that was removed
     * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles remove(int masterDataFilesSid)
        throws NoSuchMasterDataFilesException, SystemException {
        return remove((Serializable) masterDataFilesSid);
    }

    /**
     * Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the master data files
     * @return the master data files that was removed
     * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles remove(Serializable primaryKey)
        throws NoSuchMasterDataFilesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MasterDataFiles masterDataFiles = (MasterDataFiles) session.get(MasterDataFilesImpl.class,
                    primaryKey);

            if (masterDataFiles == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMasterDataFilesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(masterDataFiles);
        } catch (NoSuchMasterDataFilesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MasterDataFiles removeImpl(MasterDataFiles masterDataFiles)
        throws SystemException {
        masterDataFiles = toUnwrappedModel(masterDataFiles);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(masterDataFiles)) {
                masterDataFiles = (MasterDataFiles) session.get(MasterDataFilesImpl.class,
                        masterDataFiles.getPrimaryKeyObj());
            }

            if (masterDataFiles != null) {
                session.delete(masterDataFiles);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (masterDataFiles != null) {
            clearCache(masterDataFiles);
        }

        return masterDataFiles;
    }

    @Override
    public MasterDataFiles updateImpl(
        com.stpl.app.model.MasterDataFiles masterDataFiles)
        throws SystemException {
        masterDataFiles = toUnwrappedModel(masterDataFiles);

        boolean isNew = masterDataFiles.isNew();

        Session session = null;

        try {
            session = openSession();

            if (masterDataFiles.isNew()) {
                session.save(masterDataFiles);

                masterDataFiles.setNew(false);
            } else {
                session.merge(masterDataFiles);
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

        EntityCacheUtil.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
            MasterDataFilesImpl.class, masterDataFiles.getPrimaryKey(),
            masterDataFiles);

        return masterDataFiles;
    }

    protected MasterDataFiles toUnwrappedModel(MasterDataFiles masterDataFiles) {
        if (masterDataFiles instanceof MasterDataFilesImpl) {
            return masterDataFiles;
        }

        MasterDataFilesImpl masterDataFilesImpl = new MasterDataFilesImpl();

        masterDataFilesImpl.setNew(masterDataFiles.isNew());
        masterDataFilesImpl.setPrimaryKey(masterDataFiles.getPrimaryKey());

        masterDataFilesImpl.setMasterTableSid(masterDataFiles.getMasterTableSid());
        masterDataFilesImpl.setMasterDataFilesSid(masterDataFiles.getMasterDataFilesSid());
        masterDataFilesImpl.setMasterTableName(masterDataFiles.getMasterTableName());
        masterDataFilesImpl.setFilePath(masterDataFiles.getFilePath());
        masterDataFilesImpl.setCreatedDate(masterDataFiles.getCreatedDate());
        masterDataFilesImpl.setCreatedBy(masterDataFiles.getCreatedBy());

        return masterDataFilesImpl;
    }

    /**
     * Returns the master data files with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the master data files
     * @return the master data files
     * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMasterDataFilesException, SystemException {
        MasterDataFiles masterDataFiles = fetchByPrimaryKey(primaryKey);

        if (masterDataFiles == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMasterDataFilesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return masterDataFiles;
    }

    /**
     * Returns the master data files with the primary key or throws a {@link com.stpl.app.NoSuchMasterDataFilesException} if it could not be found.
     *
     * @param masterDataFilesSid the primary key of the master data files
     * @return the master data files
     * @throws com.stpl.app.NoSuchMasterDataFilesException if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles findByPrimaryKey(int masterDataFilesSid)
        throws NoSuchMasterDataFilesException, SystemException {
        return findByPrimaryKey((Serializable) masterDataFilesSid);
    }

    /**
     * Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the master data files
     * @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MasterDataFiles masterDataFiles = (MasterDataFiles) EntityCacheUtil.getResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
                MasterDataFilesImpl.class, primaryKey);

        if (masterDataFiles == _nullMasterDataFiles) {
            return null;
        }

        if (masterDataFiles == null) {
            Session session = null;

            try {
                session = openSession();

                masterDataFiles = (MasterDataFiles) session.get(MasterDataFilesImpl.class,
                        primaryKey);

                if (masterDataFiles != null) {
                    cacheResult(masterDataFiles);
                } else {
                    EntityCacheUtil.putResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
                        MasterDataFilesImpl.class, primaryKey,
                        _nullMasterDataFiles);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MasterDataFilesModelImpl.ENTITY_CACHE_ENABLED,
                    MasterDataFilesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return masterDataFiles;
    }

    /**
     * Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param masterDataFilesSid the primary key of the master data files
     * @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MasterDataFiles fetchByPrimaryKey(int masterDataFilesSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) masterDataFilesSid);
    }

    /**
     * Returns all the master data fileses.
     *
     * @return the master data fileses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataFiles> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the master data fileses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of master data fileses
     * @param end the upper bound of the range of master data fileses (not inclusive)
     * @return the range of master data fileses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataFiles> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the master data fileses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of master data fileses
     * @param end the upper bound of the range of master data fileses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of master data fileses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MasterDataFiles> findAll(int start, int end,
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

        List<MasterDataFiles> list = (List<MasterDataFiles>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MASTERDATAFILES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MASTERDATAFILES;

                if (pagination) {
                    sql = sql.concat(MasterDataFilesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MasterDataFiles>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MasterDataFiles>(list);
                } else {
                    list = (List<MasterDataFiles>) QueryUtil.list(q,
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
     * Removes all the master data fileses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MasterDataFiles masterDataFiles : findAll()) {
            remove(masterDataFiles);
        }
    }

    /**
     * Returns the number of master data fileses.
     *
     * @return the number of master data fileses
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

                Query q = session.createQuery(_SQL_COUNT_MASTERDATAFILES);

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
     * Initializes the master data files persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MasterDataFiles")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MasterDataFiles>> listenersList = new ArrayList<ModelListener<MasterDataFiles>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MasterDataFiles>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MasterDataFilesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
