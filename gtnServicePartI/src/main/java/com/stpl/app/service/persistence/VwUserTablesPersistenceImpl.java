package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwUserTablesException;
import com.stpl.app.model.VwUserTables;
import com.stpl.app.model.impl.VwUserTablesImpl;
import com.stpl.app.model.impl.VwUserTablesModelImpl;
import com.stpl.app.service.persistence.VwUserTablesPersistence;

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
 * The persistence implementation for the vw user tables service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwUserTablesPersistence
 * @see VwUserTablesUtil
 * @generated
 */
public class VwUserTablesPersistenceImpl extends BasePersistenceImpl<VwUserTables>
    implements VwUserTablesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwUserTablesUtil} to access the vw user tables persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwUserTablesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesModelImpl.FINDER_CACHE_ENABLED, VwUserTablesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesModelImpl.FINDER_CACHE_ENABLED, VwUserTablesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWUSERTABLES = "SELECT vwUserTables FROM VwUserTables vwUserTables";
    private static final String _SQL_COUNT_VWUSERTABLES = "SELECT COUNT(vwUserTables) FROM VwUserTables vwUserTables";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwUserTables.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwUserTables exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwUserTablesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "uniqueId", "tableName", "columnName"
            });
    private static VwUserTables _nullVwUserTables = new VwUserTablesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwUserTables> toCacheModel() {
                return _nullVwUserTablesCacheModel;
            }
        };

    private static CacheModel<VwUserTables> _nullVwUserTablesCacheModel = new CacheModel<VwUserTables>() {
            @Override
            public VwUserTables toEntityModel() {
                return _nullVwUserTables;
            }
        };

    public VwUserTablesPersistenceImpl() {
        setModelClass(VwUserTables.class);
    }

    /**
     * Caches the vw user tables in the entity cache if it is enabled.
     *
     * @param vwUserTables the vw user tables
     */
    @Override
    public void cacheResult(VwUserTables vwUserTables) {
        EntityCacheUtil.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesImpl.class, vwUserTables.getPrimaryKey(), vwUserTables);

        vwUserTables.resetOriginalValues();
    }

    /**
     * Caches the vw user tableses in the entity cache if it is enabled.
     *
     * @param vwUserTableses the vw user tableses
     */
    @Override
    public void cacheResult(List<VwUserTables> vwUserTableses) {
        for (VwUserTables vwUserTables : vwUserTableses) {
            if (EntityCacheUtil.getResult(
                        VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
                        VwUserTablesImpl.class, vwUserTables.getPrimaryKey()) == null) {
                cacheResult(vwUserTables);
            } else {
                vwUserTables.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw user tableses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwUserTablesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwUserTablesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw user tables.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwUserTables vwUserTables) {
        EntityCacheUtil.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesImpl.class, vwUserTables.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwUserTables> vwUserTableses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwUserTables vwUserTables : vwUserTableses) {
            EntityCacheUtil.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
                VwUserTablesImpl.class, vwUserTables.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
     *
     * @param uniqueId the primary key for the new vw user tables
     * @return the new vw user tables
     */
    @Override
    public VwUserTables create(int uniqueId) {
        VwUserTables vwUserTables = new VwUserTablesImpl();

        vwUserTables.setNew(true);
        vwUserTables.setPrimaryKey(uniqueId);

        return vwUserTables;
    }

    /**
     * Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param uniqueId the primary key of the vw user tables
     * @return the vw user tables that was removed
     * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables remove(int uniqueId)
        throws NoSuchVwUserTablesException, SystemException {
        return remove((Serializable) uniqueId);
    }

    /**
     * Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw user tables
     * @return the vw user tables that was removed
     * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables remove(Serializable primaryKey)
        throws NoSuchVwUserTablesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwUserTables vwUserTables = (VwUserTables) session.get(VwUserTablesImpl.class,
                    primaryKey);

            if (vwUserTables == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwUserTablesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwUserTables);
        } catch (NoSuchVwUserTablesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwUserTables removeImpl(VwUserTables vwUserTables)
        throws SystemException {
        vwUserTables = toUnwrappedModel(vwUserTables);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwUserTables)) {
                vwUserTables = (VwUserTables) session.get(VwUserTablesImpl.class,
                        vwUserTables.getPrimaryKeyObj());
            }

            if (vwUserTables != null) {
                session.delete(vwUserTables);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwUserTables != null) {
            clearCache(vwUserTables);
        }

        return vwUserTables;
    }

    @Override
    public VwUserTables updateImpl(com.stpl.app.model.VwUserTables vwUserTables)
        throws SystemException {
        vwUserTables = toUnwrappedModel(vwUserTables);

        boolean isNew = vwUserTables.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwUserTables.isNew()) {
                session.save(vwUserTables);

                vwUserTables.setNew(false);
            } else {
                session.merge(vwUserTables);
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

        EntityCacheUtil.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
            VwUserTablesImpl.class, vwUserTables.getPrimaryKey(), vwUserTables);

        return vwUserTables;
    }

    protected VwUserTables toUnwrappedModel(VwUserTables vwUserTables) {
        if (vwUserTables instanceof VwUserTablesImpl) {
            return vwUserTables;
        }

        VwUserTablesImpl vwUserTablesImpl = new VwUserTablesImpl();

        vwUserTablesImpl.setNew(vwUserTables.isNew());
        vwUserTablesImpl.setPrimaryKey(vwUserTables.getPrimaryKey());

        vwUserTablesImpl.setUniqueId(vwUserTables.getUniqueId());
        vwUserTablesImpl.setTableName(vwUserTables.getTableName());
        vwUserTablesImpl.setColumnName(vwUserTables.getColumnName());

        return vwUserTablesImpl;
    }

    /**
     * Returns the vw user tables with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw user tables
     * @return the vw user tables
     * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwUserTablesException, SystemException {
        VwUserTables vwUserTables = fetchByPrimaryKey(primaryKey);

        if (vwUserTables == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwUserTablesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwUserTables;
    }

    /**
     * Returns the vw user tables with the primary key or throws a {@link com.stpl.app.NoSuchVwUserTablesException} if it could not be found.
     *
     * @param uniqueId the primary key of the vw user tables
     * @return the vw user tables
     * @throws com.stpl.app.NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables findByPrimaryKey(int uniqueId)
        throws NoSuchVwUserTablesException, SystemException {
        return findByPrimaryKey((Serializable) uniqueId);
    }

    /**
     * Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw user tables
     * @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwUserTables vwUserTables = (VwUserTables) EntityCacheUtil.getResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
                VwUserTablesImpl.class, primaryKey);

        if (vwUserTables == _nullVwUserTables) {
            return null;
        }

        if (vwUserTables == null) {
            Session session = null;

            try {
                session = openSession();

                vwUserTables = (VwUserTables) session.get(VwUserTablesImpl.class,
                        primaryKey);

                if (vwUserTables != null) {
                    cacheResult(vwUserTables);
                } else {
                    EntityCacheUtil.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
                        VwUserTablesImpl.class, primaryKey, _nullVwUserTables);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
                    VwUserTablesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwUserTables;
    }

    /**
     * Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param uniqueId the primary key of the vw user tables
     * @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwUserTables fetchByPrimaryKey(int uniqueId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) uniqueId);
    }

    /**
     * Returns all the vw user tableses.
     *
     * @return the vw user tableses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwUserTables> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw user tableses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw user tableses
     * @param end the upper bound of the range of vw user tableses (not inclusive)
     * @return the range of vw user tableses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwUserTables> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw user tableses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw user tableses
     * @param end the upper bound of the range of vw user tableses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw user tableses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwUserTables> findAll(int start, int end,
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

        List<VwUserTables> list = (List<VwUserTables>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWUSERTABLES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWUSERTABLES;

                if (pagination) {
                    sql = sql.concat(VwUserTablesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwUserTables>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwUserTables>(list);
                } else {
                    list = (List<VwUserTables>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the vw user tableses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwUserTables vwUserTables : findAll()) {
            remove(vwUserTables);
        }
    }

    /**
     * Returns the number of vw user tableses.
     *
     * @return the number of vw user tableses
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

                Query q = session.createQuery(_SQL_COUNT_VWUSERTABLES);

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
     * Initializes the vw user tables persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwUserTables")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwUserTables>> listenersList = new ArrayList<ModelListener<VwUserTables>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwUserTables>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwUserTablesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
