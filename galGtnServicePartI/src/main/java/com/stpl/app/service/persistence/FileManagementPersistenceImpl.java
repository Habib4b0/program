package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchFileManagementException;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.impl.FileManagementImpl;
import com.stpl.app.model.impl.FileManagementModelImpl;
import com.stpl.app.service.persistence.FileManagementPersistence;

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
 * The persistence implementation for the file management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FileManagementPersistence
 * @see FileManagementUtil
 * @generated
 */
public class FileManagementPersistenceImpl extends BasePersistenceImpl<FileManagement>
    implements FileManagementPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FileManagementUtil} to access the file management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FileManagementImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementModelImpl.FINDER_CACHE_ENABLED,
            FileManagementImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementModelImpl.FINDER_CACHE_ENABLED,
            FileManagementImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FILEMANAGEMENT = "SELECT fileManagement FROM FileManagement fileManagement";
    private static final String _SQL_COUNT_FILEMANAGEMENT = "SELECT COUNT(fileManagement) FROM FileManagement fileManagement";
    private static final String _ORDER_BY_ENTITY_ALIAS = "fileManagement.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FileManagement exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FileManagementPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "country", "fromPeriod", "versionNo", "forecastSource",
                "modifiedDate", "createdBy", "createdDate", "version",
                "fileSource", "toPeriod", "modifiedBy", "fileManagementSid",
                "forecastName", "fileType"
            });
    private static FileManagement _nullFileManagement = new FileManagementImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FileManagement> toCacheModel() {
                return _nullFileManagementCacheModel;
            }
        };

    private static CacheModel<FileManagement> _nullFileManagementCacheModel = new CacheModel<FileManagement>() {
            @Override
            public FileManagement toEntityModel() {
                return _nullFileManagement;
            }
        };

    public FileManagementPersistenceImpl() {
        setModelClass(FileManagement.class);
    }

    /**
     * Caches the file management in the entity cache if it is enabled.
     *
     * @param fileManagement the file management
     */
    @Override
    public void cacheResult(FileManagement fileManagement) {
        EntityCacheUtil.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementImpl.class, fileManagement.getPrimaryKey(),
            fileManagement);

        fileManagement.resetOriginalValues();
    }

    /**
     * Caches the file managements in the entity cache if it is enabled.
     *
     * @param fileManagements the file managements
     */
    @Override
    public void cacheResult(List<FileManagement> fileManagements) {
        for (FileManagement fileManagement : fileManagements) {
            if (EntityCacheUtil.getResult(
                        FileManagementModelImpl.ENTITY_CACHE_ENABLED,
                        FileManagementImpl.class, fileManagement.getPrimaryKey()) == null) {
                cacheResult(fileManagement);
            } else {
                fileManagement.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all file managements.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FileManagementImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FileManagementImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the file management.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FileManagement fileManagement) {
        EntityCacheUtil.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementImpl.class, fileManagement.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FileManagement> fileManagements) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FileManagement fileManagement : fileManagements) {
            EntityCacheUtil.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
                FileManagementImpl.class, fileManagement.getPrimaryKey());
        }
    }

    /**
     * Creates a new file management with the primary key. Does not add the file management to the database.
     *
     * @param fileManagementSid the primary key for the new file management
     * @return the new file management
     */
    @Override
    public FileManagement create(int fileManagementSid) {
        FileManagement fileManagement = new FileManagementImpl();

        fileManagement.setNew(true);
        fileManagement.setPrimaryKey(fileManagementSid);

        return fileManagement;
    }

    /**
     * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param fileManagementSid the primary key of the file management
     * @return the file management that was removed
     * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement remove(int fileManagementSid)
        throws NoSuchFileManagementException, SystemException {
        return remove((Serializable) fileManagementSid);
    }

    /**
     * Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the file management
     * @return the file management that was removed
     * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement remove(Serializable primaryKey)
        throws NoSuchFileManagementException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FileManagement fileManagement = (FileManagement) session.get(FileManagementImpl.class,
                    primaryKey);

            if (fileManagement == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFileManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(fileManagement);
        } catch (NoSuchFileManagementException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FileManagement removeImpl(FileManagement fileManagement)
        throws SystemException {
        fileManagement = toUnwrappedModel(fileManagement);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(fileManagement)) {
                fileManagement = (FileManagement) session.get(FileManagementImpl.class,
                        fileManagement.getPrimaryKeyObj());
            }

            if (fileManagement != null) {
                session.delete(fileManagement);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (fileManagement != null) {
            clearCache(fileManagement);
        }

        return fileManagement;
    }

    @Override
    public FileManagement updateImpl(
        com.stpl.app.model.FileManagement fileManagement)
        throws SystemException {
        fileManagement = toUnwrappedModel(fileManagement);

        boolean isNew = fileManagement.isNew();

        Session session = null;

        try {
            session = openSession();

            if (fileManagement.isNew()) {
                session.save(fileManagement);

                fileManagement.setNew(false);
            } else {
                session.merge(fileManagement);
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

        EntityCacheUtil.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
            FileManagementImpl.class, fileManagement.getPrimaryKey(),
            fileManagement);

        return fileManagement;
    }

    protected FileManagement toUnwrappedModel(FileManagement fileManagement) {
        if (fileManagement instanceof FileManagementImpl) {
            return fileManagement;
        }

        FileManagementImpl fileManagementImpl = new FileManagementImpl();

        fileManagementImpl.setNew(fileManagement.isNew());
        fileManagementImpl.setPrimaryKey(fileManagement.getPrimaryKey());

        fileManagementImpl.setCountry(fileManagement.getCountry());
        fileManagementImpl.setFromPeriod(fileManagement.getFromPeriod());
        fileManagementImpl.setVersionNo(fileManagement.getVersionNo());
        fileManagementImpl.setForecastSource(fileManagement.getForecastSource());
        fileManagementImpl.setModifiedDate(fileManagement.getModifiedDate());
        fileManagementImpl.setCreatedBy(fileManagement.getCreatedBy());
        fileManagementImpl.setCreatedDate(fileManagement.getCreatedDate());
        fileManagementImpl.setVersion(fileManagement.getVersion());
        fileManagementImpl.setFileSource(fileManagement.getFileSource());
        fileManagementImpl.setToPeriod(fileManagement.getToPeriod());
        fileManagementImpl.setModifiedBy(fileManagement.getModifiedBy());
        fileManagementImpl.setFileManagementSid(fileManagement.getFileManagementSid());
        fileManagementImpl.setForecastName(fileManagement.getForecastName());
        fileManagementImpl.setFileType(fileManagement.getFileType());

        return fileManagementImpl;
    }

    /**
     * Returns the file management with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the file management
     * @return the file management
     * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement findByPrimaryKey(Serializable primaryKey)
        throws NoSuchFileManagementException, SystemException {
        FileManagement fileManagement = fetchByPrimaryKey(primaryKey);

        if (fileManagement == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchFileManagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return fileManagement;
    }

    /**
     * Returns the file management with the primary key or throws a {@link com.stpl.app.NoSuchFileManagementException} if it could not be found.
     *
     * @param fileManagementSid the primary key of the file management
     * @return the file management
     * @throws com.stpl.app.NoSuchFileManagementException if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement findByPrimaryKey(int fileManagementSid)
        throws NoSuchFileManagementException, SystemException {
        return findByPrimaryKey((Serializable) fileManagementSid);
    }

    /**
     * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the file management
     * @return the file management, or <code>null</code> if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        FileManagement fileManagement = (FileManagement) EntityCacheUtil.getResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
                FileManagementImpl.class, primaryKey);

        if (fileManagement == _nullFileManagement) {
            return null;
        }

        if (fileManagement == null) {
            Session session = null;

            try {
                session = openSession();

                fileManagement = (FileManagement) session.get(FileManagementImpl.class,
                        primaryKey);

                if (fileManagement != null) {
                    cacheResult(fileManagement);
                } else {
                    EntityCacheUtil.putResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
                        FileManagementImpl.class, primaryKey,
                        _nullFileManagement);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(FileManagementModelImpl.ENTITY_CACHE_ENABLED,
                    FileManagementImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return fileManagement;
    }

    /**
     * Returns the file management with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param fileManagementSid the primary key of the file management
     * @return the file management, or <code>null</code> if a file management with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FileManagement fetchByPrimaryKey(int fileManagementSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) fileManagementSid);
    }

    /**
     * Returns all the file managements.
     *
     * @return the file managements
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FileManagement> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the file managements.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of file managements
     * @param end the upper bound of the range of file managements (not inclusive)
     * @return the range of file managements
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FileManagement> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the file managements.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of file managements
     * @param end the upper bound of the range of file managements (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of file managements
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FileManagement> findAll(int start, int end,
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

        List<FileManagement> list = (List<FileManagement>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FILEMANAGEMENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FILEMANAGEMENT;

                if (pagination) {
                    sql = sql.concat(FileManagementModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<FileManagement>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<FileManagement>(list);
                } else {
                    list = (List<FileManagement>) QueryUtil.list(q,
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
     * Removes all the file managements from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (FileManagement fileManagement : findAll()) {
            remove(fileManagement);
        }
    }

    /**
     * Returns the number of file managements.
     *
     * @return the number of file managements
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

                Query q = session.createQuery(_SQL_COUNT_FILEMANAGEMENT);

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
     * Initializes the file management persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.FileManagement")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FileManagement>> listenersList = new ArrayList<ModelListener<FileManagement>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FileManagement>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FileManagementImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
