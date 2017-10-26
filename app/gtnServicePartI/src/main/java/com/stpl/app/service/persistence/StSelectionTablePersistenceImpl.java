package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStSelectionTableException;
import com.stpl.app.model.StSelectionTable;
import com.stpl.app.model.impl.StSelectionTableImpl;
import com.stpl.app.model.impl.StSelectionTableModelImpl;
import com.stpl.app.service.persistence.StSelectionTablePersistence;

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
 * The persistence implementation for the st selection table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StSelectionTablePersistence
 * @see StSelectionTableUtil
 * @generated
 */
public class StSelectionTablePersistenceImpl extends BasePersistenceImpl<StSelectionTable>
    implements StSelectionTablePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StSelectionTableUtil} to access the st selection table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StSelectionTableImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableModelImpl.FINDER_CACHE_ENABLED,
            StSelectionTableImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableModelImpl.FINDER_CACHE_ENABLED,
            StSelectionTableImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STSELECTIONTABLE = "SELECT stSelectionTable FROM StSelectionTable stSelectionTable";
    private static final String _SQL_COUNT_STSELECTIONTABLE = "SELECT COUNT(stSelectionTable) FROM StSelectionTable stSelectionTable";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stSelectionTable.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StSelectionTable exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StSelectionTablePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "selectionType", "userId", "sessionId", "companyItemSid",
                "checkRecord"
            });
    private static StSelectionTable _nullStSelectionTable = new StSelectionTableImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StSelectionTable> toCacheModel() {
                return _nullStSelectionTableCacheModel;
            }
        };

    private static CacheModel<StSelectionTable> _nullStSelectionTableCacheModel = new CacheModel<StSelectionTable>() {
            @Override
            public StSelectionTable toEntityModel() {
                return _nullStSelectionTable;
            }
        };

    public StSelectionTablePersistenceImpl() {
        setModelClass(StSelectionTable.class);
    }

    /**
     * Caches the st selection table in the entity cache if it is enabled.
     *
     * @param stSelectionTable the st selection table
     */
    @Override
    public void cacheResult(StSelectionTable stSelectionTable) {
        EntityCacheUtil.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableImpl.class, stSelectionTable.getPrimaryKey(),
            stSelectionTable);

        stSelectionTable.resetOriginalValues();
    }

    /**
     * Caches the st selection tables in the entity cache if it is enabled.
     *
     * @param stSelectionTables the st selection tables
     */
    @Override
    public void cacheResult(List<StSelectionTable> stSelectionTables) {
        for (StSelectionTable stSelectionTable : stSelectionTables) {
            if (EntityCacheUtil.getResult(
                        StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
                        StSelectionTableImpl.class,
                        stSelectionTable.getPrimaryKey()) == null) {
                cacheResult(stSelectionTable);
            } else {
                stSelectionTable.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st selection tables.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StSelectionTableImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StSelectionTableImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st selection table.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StSelectionTable stSelectionTable) {
        EntityCacheUtil.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableImpl.class, stSelectionTable.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StSelectionTable> stSelectionTables) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StSelectionTable stSelectionTable : stSelectionTables) {
            EntityCacheUtil.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
                StSelectionTableImpl.class, stSelectionTable.getPrimaryKey());
        }
    }

    /**
     * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
     *
     * @param stSelectionTablePK the primary key for the new st selection table
     * @return the new st selection table
     */
    @Override
    public StSelectionTable create(StSelectionTablePK stSelectionTablePK) {
        StSelectionTable stSelectionTable = new StSelectionTableImpl();

        stSelectionTable.setNew(true);
        stSelectionTable.setPrimaryKey(stSelectionTablePK);

        return stSelectionTable;
    }

    /**
     * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stSelectionTablePK the primary key of the st selection table
     * @return the st selection table that was removed
     * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable remove(StSelectionTablePK stSelectionTablePK)
        throws NoSuchStSelectionTableException, SystemException {
        return remove((Serializable) stSelectionTablePK);
    }

    /**
     * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st selection table
     * @return the st selection table that was removed
     * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable remove(Serializable primaryKey)
        throws NoSuchStSelectionTableException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StSelectionTable stSelectionTable = (StSelectionTable) session.get(StSelectionTableImpl.class,
                    primaryKey);

            if (stSelectionTable == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStSelectionTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stSelectionTable);
        } catch (NoSuchStSelectionTableException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StSelectionTable removeImpl(StSelectionTable stSelectionTable)
        throws SystemException {
        stSelectionTable = toUnwrappedModel(stSelectionTable);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stSelectionTable)) {
                stSelectionTable = (StSelectionTable) session.get(StSelectionTableImpl.class,
                        stSelectionTable.getPrimaryKeyObj());
            }

            if (stSelectionTable != null) {
                session.delete(stSelectionTable);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stSelectionTable != null) {
            clearCache(stSelectionTable);
        }

        return stSelectionTable;
    }

    @Override
    public StSelectionTable updateImpl(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws SystemException {
        stSelectionTable = toUnwrappedModel(stSelectionTable);

        boolean isNew = stSelectionTable.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stSelectionTable.isNew()) {
                session.save(stSelectionTable);

                stSelectionTable.setNew(false);
            } else {
                session.merge(stSelectionTable);
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

        EntityCacheUtil.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
            StSelectionTableImpl.class, stSelectionTable.getPrimaryKey(),
            stSelectionTable);

        return stSelectionTable;
    }

    protected StSelectionTable toUnwrappedModel(
        StSelectionTable stSelectionTable) {
        if (stSelectionTable instanceof StSelectionTableImpl) {
            return stSelectionTable;
        }

        StSelectionTableImpl stSelectionTableImpl = new StSelectionTableImpl();

        stSelectionTableImpl.setNew(stSelectionTable.isNew());
        stSelectionTableImpl.setPrimaryKey(stSelectionTable.getPrimaryKey());

        stSelectionTableImpl.setSelectionType(stSelectionTable.getSelectionType());
        stSelectionTableImpl.setUserId(stSelectionTable.getUserId());
        stSelectionTableImpl.setSessionId(stSelectionTable.getSessionId());
        stSelectionTableImpl.setCompanyItemSid(stSelectionTable.getCompanyItemSid());
        stSelectionTableImpl.setCheckRecord(stSelectionTable.isCheckRecord());

        return stSelectionTableImpl;
    }

    /**
     * Returns the st selection table with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st selection table
     * @return the st selection table
     * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStSelectionTableException, SystemException {
        StSelectionTable stSelectionTable = fetchByPrimaryKey(primaryKey);

        if (stSelectionTable == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStSelectionTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stSelectionTable;
    }

    /**
     * Returns the st selection table with the primary key or throws a {@link com.stpl.app.NoSuchStSelectionTableException} if it could not be found.
     *
     * @param stSelectionTablePK the primary key of the st selection table
     * @return the st selection table
     * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable findByPrimaryKey(
        StSelectionTablePK stSelectionTablePK)
        throws NoSuchStSelectionTableException, SystemException {
        return findByPrimaryKey((Serializable) stSelectionTablePK);
    }

    /**
     * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st selection table
     * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StSelectionTable stSelectionTable = (StSelectionTable) EntityCacheUtil.getResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
                StSelectionTableImpl.class, primaryKey);

        if (stSelectionTable == _nullStSelectionTable) {
            return null;
        }

        if (stSelectionTable == null) {
            Session session = null;

            try {
                session = openSession();

                stSelectionTable = (StSelectionTable) session.get(StSelectionTableImpl.class,
                        primaryKey);

                if (stSelectionTable != null) {
                    cacheResult(stSelectionTable);
                } else {
                    EntityCacheUtil.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
                        StSelectionTableImpl.class, primaryKey,
                        _nullStSelectionTable);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
                    StSelectionTableImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stSelectionTable;
    }

    /**
     * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stSelectionTablePK the primary key of the st selection table
     * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StSelectionTable fetchByPrimaryKey(
        StSelectionTablePK stSelectionTablePK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stSelectionTablePK);
    }

    /**
     * Returns all the st selection tables.
     *
     * @return the st selection tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StSelectionTable> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st selection tables.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st selection tables
     * @param end the upper bound of the range of st selection tables (not inclusive)
     * @return the range of st selection tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StSelectionTable> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st selection tables.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st selection tables
     * @param end the upper bound of the range of st selection tables (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st selection tables
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StSelectionTable> findAll(int start, int end,
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

        List<StSelectionTable> list = (List<StSelectionTable>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STSELECTIONTABLE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STSELECTIONTABLE;

                if (pagination) {
                    sql = sql.concat(StSelectionTableModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StSelectionTable>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StSelectionTable>(list);
                } else {
                    list = (List<StSelectionTable>) QueryUtil.list(q,
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
     * Removes all the st selection tables from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StSelectionTable stSelectionTable : findAll()) {
            remove(stSelectionTable);
        }
    }

    /**
     * Returns the number of st selection tables.
     *
     * @return the number of st selection tables
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

                Query q = session.createQuery(_SQL_COUNT_STSELECTIONTABLE);

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
     * Initializes the st selection table persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StSelectionTable")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StSelectionTable>> listenersList = new ArrayList<ModelListener<StSelectionTable>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StSelectionTable>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StSelectionTableImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
