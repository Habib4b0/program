package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchTransactionModuleMasterException;
import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.model.impl.TransactionModuleMasterImpl;
import com.stpl.app.model.impl.TransactionModuleMasterModelImpl;
import com.stpl.app.service.persistence.TransactionModuleMasterPersistence;

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
 * The persistence implementation for the transaction module master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleMasterPersistence
 * @see TransactionModuleMasterUtil
 * @generated
 */
public class TransactionModuleMasterPersistenceImpl extends BasePersistenceImpl<TransactionModuleMaster>
    implements TransactionModuleMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link TransactionModuleMasterUtil} to access the transaction module master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = TransactionModuleMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED,
            TransactionModuleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED,
            TransactionModuleMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_TRANSACTIONMODULEMASTER = "SELECT transactionModuleMaster FROM TransactionModuleMaster transactionModuleMaster";
    private static final String _SQL_COUNT_TRANSACTIONMODULEMASTER = "SELECT COUNT(transactionModuleMaster) FROM TransactionModuleMaster transactionModuleMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "transactionModuleMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionModuleMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(TransactionModuleMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "transactionModuleMasterSid", "invalidTableName", "tableName",
                "moduleName", "tabName"
            });
    private static TransactionModuleMaster _nullTransactionModuleMaster = new TransactionModuleMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<TransactionModuleMaster> toCacheModel() {
                return _nullTransactionModuleMasterCacheModel;
            }
        };

    private static CacheModel<TransactionModuleMaster> _nullTransactionModuleMasterCacheModel =
        new CacheModel<TransactionModuleMaster>() {
            @Override
            public TransactionModuleMaster toEntityModel() {
                return _nullTransactionModuleMaster;
            }
        };

    public TransactionModuleMasterPersistenceImpl() {
        setModelClass(TransactionModuleMaster.class);
    }

    /**
     * Caches the transaction module master in the entity cache if it is enabled.
     *
     * @param transactionModuleMaster the transaction module master
     */
    @Override
    public void cacheResult(TransactionModuleMaster transactionModuleMaster) {
        EntityCacheUtil.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterImpl.class,
            transactionModuleMaster.getPrimaryKey(), transactionModuleMaster);

        transactionModuleMaster.resetOriginalValues();
    }

    /**
     * Caches the transaction module masters in the entity cache if it is enabled.
     *
     * @param transactionModuleMasters the transaction module masters
     */
    @Override
    public void cacheResult(
        List<TransactionModuleMaster> transactionModuleMasters) {
        for (TransactionModuleMaster transactionModuleMaster : transactionModuleMasters) {
            if (EntityCacheUtil.getResult(
                        TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        TransactionModuleMasterImpl.class,
                        transactionModuleMaster.getPrimaryKey()) == null) {
                cacheResult(transactionModuleMaster);
            } else {
                transactionModuleMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all transaction module masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(TransactionModuleMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(TransactionModuleMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the transaction module master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(TransactionModuleMaster transactionModuleMaster) {
        EntityCacheUtil.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterImpl.class,
            transactionModuleMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<TransactionModuleMaster> transactionModuleMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (TransactionModuleMaster transactionModuleMaster : transactionModuleMasters) {
            EntityCacheUtil.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
                TransactionModuleMasterImpl.class,
                transactionModuleMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
     *
     * @param transactionModuleMasterSid the primary key for the new transaction module master
     * @return the new transaction module master
     */
    @Override
    public TransactionModuleMaster create(int transactionModuleMasterSid) {
        TransactionModuleMaster transactionModuleMaster = new TransactionModuleMasterImpl();

        transactionModuleMaster.setNew(true);
        transactionModuleMaster.setPrimaryKey(transactionModuleMasterSid);

        return transactionModuleMaster;
    }

    /**
     * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param transactionModuleMasterSid the primary key of the transaction module master
     * @return the transaction module master that was removed
     * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster remove(int transactionModuleMasterSid)
        throws NoSuchTransactionModuleMasterException, SystemException {
        return remove((Serializable) transactionModuleMasterSid);
    }

    /**
     * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the transaction module master
     * @return the transaction module master that was removed
     * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster remove(Serializable primaryKey)
        throws NoSuchTransactionModuleMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            TransactionModuleMaster transactionModuleMaster = (TransactionModuleMaster) session.get(TransactionModuleMasterImpl.class,
                    primaryKey);

            if (transactionModuleMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchTransactionModuleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(transactionModuleMaster);
        } catch (NoSuchTransactionModuleMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected TransactionModuleMaster removeImpl(
        TransactionModuleMaster transactionModuleMaster)
        throws SystemException {
        transactionModuleMaster = toUnwrappedModel(transactionModuleMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(transactionModuleMaster)) {
                transactionModuleMaster = (TransactionModuleMaster) session.get(TransactionModuleMasterImpl.class,
                        transactionModuleMaster.getPrimaryKeyObj());
            }

            if (transactionModuleMaster != null) {
                session.delete(transactionModuleMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (transactionModuleMaster != null) {
            clearCache(transactionModuleMaster);
        }

        return transactionModuleMaster;
    }

    @Override
    public TransactionModuleMaster updateImpl(
        com.stpl.app.model.TransactionModuleMaster transactionModuleMaster)
        throws SystemException {
        transactionModuleMaster = toUnwrappedModel(transactionModuleMaster);

        boolean isNew = transactionModuleMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (transactionModuleMaster.isNew()) {
                session.save(transactionModuleMaster);

                transactionModuleMaster.setNew(false);
            } else {
                session.merge(transactionModuleMaster);
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

        EntityCacheUtil.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleMasterImpl.class,
            transactionModuleMaster.getPrimaryKey(), transactionModuleMaster);

        return transactionModuleMaster;
    }

    protected TransactionModuleMaster toUnwrappedModel(
        TransactionModuleMaster transactionModuleMaster) {
        if (transactionModuleMaster instanceof TransactionModuleMasterImpl) {
            return transactionModuleMaster;
        }

        TransactionModuleMasterImpl transactionModuleMasterImpl = new TransactionModuleMasterImpl();

        transactionModuleMasterImpl.setNew(transactionModuleMaster.isNew());
        transactionModuleMasterImpl.setPrimaryKey(transactionModuleMaster.getPrimaryKey());

        transactionModuleMasterImpl.setTransactionModuleMasterSid(transactionModuleMaster.getTransactionModuleMasterSid());
        transactionModuleMasterImpl.setInvalidTableName(transactionModuleMaster.getInvalidTableName());
        transactionModuleMasterImpl.setTableName(transactionModuleMaster.getTableName());
        transactionModuleMasterImpl.setModuleName(transactionModuleMaster.getModuleName());
        transactionModuleMasterImpl.setTabName(transactionModuleMaster.getTabName());

        return transactionModuleMasterImpl;
    }

    /**
     * Returns the transaction module master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the transaction module master
     * @return the transaction module master
     * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchTransactionModuleMasterException, SystemException {
        TransactionModuleMaster transactionModuleMaster = fetchByPrimaryKey(primaryKey);

        if (transactionModuleMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchTransactionModuleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return transactionModuleMaster;
    }

    /**
     * Returns the transaction module master with the primary key or throws a {@link com.stpl.app.NoSuchTransactionModuleMasterException} if it could not be found.
     *
     * @param transactionModuleMasterSid the primary key of the transaction module master
     * @return the transaction module master
     * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster findByPrimaryKey(
        int transactionModuleMasterSid)
        throws NoSuchTransactionModuleMasterException, SystemException {
        return findByPrimaryKey((Serializable) transactionModuleMasterSid);
    }

    /**
     * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the transaction module master
     * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        TransactionModuleMaster transactionModuleMaster = (TransactionModuleMaster) EntityCacheUtil.getResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
                TransactionModuleMasterImpl.class, primaryKey);

        if (transactionModuleMaster == _nullTransactionModuleMaster) {
            return null;
        }

        if (transactionModuleMaster == null) {
            Session session = null;

            try {
                session = openSession();

                transactionModuleMaster = (TransactionModuleMaster) session.get(TransactionModuleMasterImpl.class,
                        primaryKey);

                if (transactionModuleMaster != null) {
                    cacheResult(transactionModuleMaster);
                } else {
                    EntityCacheUtil.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
                        TransactionModuleMasterImpl.class, primaryKey,
                        _nullTransactionModuleMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
                    TransactionModuleMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return transactionModuleMaster;
    }

    /**
     * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param transactionModuleMasterSid the primary key of the transaction module master
     * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleMaster fetchByPrimaryKey(
        int transactionModuleMasterSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) transactionModuleMasterSid);
    }

    /**
     * Returns all the transaction module masters.
     *
     * @return the transaction module masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the transaction module masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of transaction module masters
     * @param end the upper bound of the range of transaction module masters (not inclusive)
     * @return the range of transaction module masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the transaction module masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of transaction module masters
     * @param end the upper bound of the range of transaction module masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of transaction module masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleMaster> findAll(int start, int end,
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

        List<TransactionModuleMaster> list = (List<TransactionModuleMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_TRANSACTIONMODULEMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_TRANSACTIONMODULEMASTER;

                if (pagination) {
                    sql = sql.concat(TransactionModuleMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<TransactionModuleMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<TransactionModuleMaster>(list);
                } else {
                    list = (List<TransactionModuleMaster>) QueryUtil.list(q,
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
     * Removes all the transaction module masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (TransactionModuleMaster transactionModuleMaster : findAll()) {
            remove(transactionModuleMaster);
        }
    }

    /**
     * Returns the number of transaction module masters.
     *
     * @return the number of transaction module masters
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

                Query q = session.createQuery(_SQL_COUNT_TRANSACTIONMODULEMASTER);

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
     * Initializes the transaction module master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.TransactionModuleMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<TransactionModuleMaster>> listenersList = new ArrayList<ModelListener<TransactionModuleMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<TransactionModuleMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(TransactionModuleMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
