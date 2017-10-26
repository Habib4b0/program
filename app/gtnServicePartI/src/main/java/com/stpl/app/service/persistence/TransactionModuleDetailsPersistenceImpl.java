package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchTransactionModuleDetailsException;
import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.model.impl.TransactionModuleDetailsImpl;
import com.stpl.app.model.impl.TransactionModuleDetailsModelImpl;
import com.stpl.app.service.persistence.TransactionModuleDetailsPersistence;

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
 * The persistence implementation for the transaction module details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleDetailsPersistence
 * @see TransactionModuleDetailsUtil
 * @generated
 */
public class TransactionModuleDetailsPersistenceImpl extends BasePersistenceImpl<TransactionModuleDetails>
    implements TransactionModuleDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link TransactionModuleDetailsUtil} to access the transaction module details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = TransactionModuleDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED,
            TransactionModuleDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED,
            TransactionModuleDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_TRANSACTIONMODULEDETAILS = "SELECT transactionModuleDetails FROM TransactionModuleDetails transactionModuleDetails";
    private static final String _SQL_COUNT_TRANSACTIONMODULEDETAILS = "SELECT COUNT(transactionModuleDetails) FROM TransactionModuleDetails transactionModuleDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "transactionModuleDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionModuleDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(TransactionModuleDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "propertyIndex", "displayName", "transactionModuleMasterSid",
                "categoryName", "validation", "propertyName", "flag",
                "transactionModuleDetailsSid"
            });
    private static TransactionModuleDetails _nullTransactionModuleDetails = new TransactionModuleDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<TransactionModuleDetails> toCacheModel() {
                return _nullTransactionModuleDetailsCacheModel;
            }
        };

    private static CacheModel<TransactionModuleDetails> _nullTransactionModuleDetailsCacheModel =
        new CacheModel<TransactionModuleDetails>() {
            @Override
            public TransactionModuleDetails toEntityModel() {
                return _nullTransactionModuleDetails;
            }
        };

    public TransactionModuleDetailsPersistenceImpl() {
        setModelClass(TransactionModuleDetails.class);
    }

    /**
     * Caches the transaction module details in the entity cache if it is enabled.
     *
     * @param transactionModuleDetails the transaction module details
     */
    @Override
    public void cacheResult(TransactionModuleDetails transactionModuleDetails) {
        EntityCacheUtil.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsImpl.class,
            transactionModuleDetails.getPrimaryKey(), transactionModuleDetails);

        transactionModuleDetails.resetOriginalValues();
    }

    /**
     * Caches the transaction module detailses in the entity cache if it is enabled.
     *
     * @param transactionModuleDetailses the transaction module detailses
     */
    @Override
    public void cacheResult(
        List<TransactionModuleDetails> transactionModuleDetailses) {
        for (TransactionModuleDetails transactionModuleDetails : transactionModuleDetailses) {
            if (EntityCacheUtil.getResult(
                        TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        TransactionModuleDetailsImpl.class,
                        transactionModuleDetails.getPrimaryKey()) == null) {
                cacheResult(transactionModuleDetails);
            } else {
                transactionModuleDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all transaction module detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(TransactionModuleDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(TransactionModuleDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the transaction module details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(TransactionModuleDetails transactionModuleDetails) {
        EntityCacheUtil.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsImpl.class,
            transactionModuleDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<TransactionModuleDetails> transactionModuleDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (TransactionModuleDetails transactionModuleDetails : transactionModuleDetailses) {
            EntityCacheUtil.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
                TransactionModuleDetailsImpl.class,
                transactionModuleDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
     *
     * @param transactionModuleDetailsSid the primary key for the new transaction module details
     * @return the new transaction module details
     */
    @Override
    public TransactionModuleDetails create(int transactionModuleDetailsSid) {
        TransactionModuleDetails transactionModuleDetails = new TransactionModuleDetailsImpl();

        transactionModuleDetails.setNew(true);
        transactionModuleDetails.setPrimaryKey(transactionModuleDetailsSid);

        return transactionModuleDetails;
    }

    /**
     * Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param transactionModuleDetailsSid the primary key of the transaction module details
     * @return the transaction module details that was removed
     * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails remove(int transactionModuleDetailsSid)
        throws NoSuchTransactionModuleDetailsException, SystemException {
        return remove((Serializable) transactionModuleDetailsSid);
    }

    /**
     * Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the transaction module details
     * @return the transaction module details that was removed
     * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails remove(Serializable primaryKey)
        throws NoSuchTransactionModuleDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            TransactionModuleDetails transactionModuleDetails = (TransactionModuleDetails) session.get(TransactionModuleDetailsImpl.class,
                    primaryKey);

            if (transactionModuleDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchTransactionModuleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(transactionModuleDetails);
        } catch (NoSuchTransactionModuleDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected TransactionModuleDetails removeImpl(
        TransactionModuleDetails transactionModuleDetails)
        throws SystemException {
        transactionModuleDetails = toUnwrappedModel(transactionModuleDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(transactionModuleDetails)) {
                transactionModuleDetails = (TransactionModuleDetails) session.get(TransactionModuleDetailsImpl.class,
                        transactionModuleDetails.getPrimaryKeyObj());
            }

            if (transactionModuleDetails != null) {
                session.delete(transactionModuleDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (transactionModuleDetails != null) {
            clearCache(transactionModuleDetails);
        }

        return transactionModuleDetails;
    }

    @Override
    public TransactionModuleDetails updateImpl(
        com.stpl.app.model.TransactionModuleDetails transactionModuleDetails)
        throws SystemException {
        transactionModuleDetails = toUnwrappedModel(transactionModuleDetails);

        boolean isNew = transactionModuleDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (transactionModuleDetails.isNew()) {
                session.save(transactionModuleDetails);

                transactionModuleDetails.setNew(false);
            } else {
                session.merge(transactionModuleDetails);
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

        EntityCacheUtil.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
            TransactionModuleDetailsImpl.class,
            transactionModuleDetails.getPrimaryKey(), transactionModuleDetails);

        return transactionModuleDetails;
    }

    protected TransactionModuleDetails toUnwrappedModel(
        TransactionModuleDetails transactionModuleDetails) {
        if (transactionModuleDetails instanceof TransactionModuleDetailsImpl) {
            return transactionModuleDetails;
        }

        TransactionModuleDetailsImpl transactionModuleDetailsImpl = new TransactionModuleDetailsImpl();

        transactionModuleDetailsImpl.setNew(transactionModuleDetails.isNew());
        transactionModuleDetailsImpl.setPrimaryKey(transactionModuleDetails.getPrimaryKey());

        transactionModuleDetailsImpl.setPropertyIndex(transactionModuleDetails.getPropertyIndex());
        transactionModuleDetailsImpl.setDisplayName(transactionModuleDetails.getDisplayName());
        transactionModuleDetailsImpl.setTransactionModuleMasterSid(transactionModuleDetails.getTransactionModuleMasterSid());
        transactionModuleDetailsImpl.setCategoryName(transactionModuleDetails.getCategoryName());
        transactionModuleDetailsImpl.setValidation(transactionModuleDetails.getValidation());
        transactionModuleDetailsImpl.setPropertyName(transactionModuleDetails.getPropertyName());
        transactionModuleDetailsImpl.setFlag(transactionModuleDetails.getFlag());
        transactionModuleDetailsImpl.setTransactionModuleDetailsSid(transactionModuleDetails.getTransactionModuleDetailsSid());

        return transactionModuleDetailsImpl;
    }

    /**
     * Returns the transaction module details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the transaction module details
     * @return the transaction module details
     * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchTransactionModuleDetailsException, SystemException {
        TransactionModuleDetails transactionModuleDetails = fetchByPrimaryKey(primaryKey);

        if (transactionModuleDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchTransactionModuleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return transactionModuleDetails;
    }

    /**
     * Returns the transaction module details with the primary key or throws a {@link com.stpl.app.NoSuchTransactionModuleDetailsException} if it could not be found.
     *
     * @param transactionModuleDetailsSid the primary key of the transaction module details
     * @return the transaction module details
     * @throws com.stpl.app.NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails findByPrimaryKey(
        int transactionModuleDetailsSid)
        throws NoSuchTransactionModuleDetailsException, SystemException {
        return findByPrimaryKey((Serializable) transactionModuleDetailsSid);
    }

    /**
     * Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the transaction module details
     * @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        TransactionModuleDetails transactionModuleDetails = (TransactionModuleDetails) EntityCacheUtil.getResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
                TransactionModuleDetailsImpl.class, primaryKey);

        if (transactionModuleDetails == _nullTransactionModuleDetails) {
            return null;
        }

        if (transactionModuleDetails == null) {
            Session session = null;

            try {
                session = openSession();

                transactionModuleDetails = (TransactionModuleDetails) session.get(TransactionModuleDetailsImpl.class,
                        primaryKey);

                if (transactionModuleDetails != null) {
                    cacheResult(transactionModuleDetails);
                } else {
                    EntityCacheUtil.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        TransactionModuleDetailsImpl.class, primaryKey,
                        _nullTransactionModuleDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    TransactionModuleDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return transactionModuleDetails;
    }

    /**
     * Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param transactionModuleDetailsSid the primary key of the transaction module details
     * @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TransactionModuleDetails fetchByPrimaryKey(
        int transactionModuleDetailsSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) transactionModuleDetailsSid);
    }

    /**
     * Returns all the transaction module detailses.
     *
     * @return the transaction module detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the transaction module detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of transaction module detailses
     * @param end the upper bound of the range of transaction module detailses (not inclusive)
     * @return the range of transaction module detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the transaction module detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of transaction module detailses
     * @param end the upper bound of the range of transaction module detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of transaction module detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TransactionModuleDetails> findAll(int start, int end,
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

        List<TransactionModuleDetails> list = (List<TransactionModuleDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_TRANSACTIONMODULEDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_TRANSACTIONMODULEDETAILS;

                if (pagination) {
                    sql = sql.concat(TransactionModuleDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<TransactionModuleDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<TransactionModuleDetails>(list);
                } else {
                    list = (List<TransactionModuleDetails>) QueryUtil.list(q,
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
     * Removes all the transaction module detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (TransactionModuleDetails transactionModuleDetails : findAll()) {
            remove(transactionModuleDetails);
        }
    }

    /**
     * Returns the number of transaction module detailses.
     *
     * @return the number of transaction module detailses
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

                Query q = session.createQuery(_SQL_COUNT_TRANSACTIONMODULEDETAILS);

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
     * Initializes the transaction module details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.TransactionModuleDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<TransactionModuleDetails>> listenersList = new ArrayList<ModelListener<TransactionModuleDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<TransactionModuleDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(TransactionModuleDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
