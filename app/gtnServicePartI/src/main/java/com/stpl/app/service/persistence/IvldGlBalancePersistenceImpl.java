package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldGlBalanceException;
import com.stpl.app.model.IvldGlBalance;
import com.stpl.app.model.impl.IvldGlBalanceImpl;
import com.stpl.app.model.impl.IvldGlBalanceModelImpl;
import com.stpl.app.service.persistence.IvldGlBalancePersistence;

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
 * The persistence implementation for the ivld gl balance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlBalancePersistence
 * @see IvldGlBalanceUtil
 * @generated
 */
public class IvldGlBalancePersistenceImpl extends BasePersistenceImpl<IvldGlBalance>
    implements IvldGlBalancePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldGlBalanceUtil} to access the ivld gl balance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldGlBalanceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED,
            IvldGlBalanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED,
            IvldGlBalanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDGLBALANCE = "SELECT ivldGlBalance FROM IvldGlBalance ivldGlBalance";
    private static final String _SQL_COUNT_IVLDGLBALANCE = "SELECT COUNT(ivldGlBalance) FROM IvldGlBalance ivldGlBalance";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldGlBalance.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldGlBalance exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldGlBalancePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "balance", "accountNo", "reasonForFailure", "accountId", "year",
                "period", "modifiedDate", "isActive", "source", "uploadDate",
                "createdBy", "createdDate", "addChgDelIndicator", "batchId",
                "closeIndicator", "insertedDate", "errorField",
                "ivldGlBalanceSid", "errorCode", "glBalanceIntfid",
                "intfInsertedDate", "modifiedBy", "reprocessedFlag",
                "checkRecord"
            });
    private static IvldGlBalance _nullIvldGlBalance = new IvldGlBalanceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldGlBalance> toCacheModel() {
                return _nullIvldGlBalanceCacheModel;
            }
        };

    private static CacheModel<IvldGlBalance> _nullIvldGlBalanceCacheModel = new CacheModel<IvldGlBalance>() {
            @Override
            public IvldGlBalance toEntityModel() {
                return _nullIvldGlBalance;
            }
        };

    public IvldGlBalancePersistenceImpl() {
        setModelClass(IvldGlBalance.class);
    }

    /**
     * Caches the ivld gl balance in the entity cache if it is enabled.
     *
     * @param ivldGlBalance the ivld gl balance
     */
    @Override
    public void cacheResult(IvldGlBalance ivldGlBalance) {
        EntityCacheUtil.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey(),
            ivldGlBalance);

        ivldGlBalance.resetOriginalValues();
    }

    /**
     * Caches the ivld gl balances in the entity cache if it is enabled.
     *
     * @param ivldGlBalances the ivld gl balances
     */
    @Override
    public void cacheResult(List<IvldGlBalance> ivldGlBalances) {
        for (IvldGlBalance ivldGlBalance : ivldGlBalances) {
            if (EntityCacheUtil.getResult(
                        IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
                        IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey()) == null) {
                cacheResult(ivldGlBalance);
            } else {
                ivldGlBalance.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld gl balances.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldGlBalanceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldGlBalanceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld gl balance.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldGlBalance ivldGlBalance) {
        EntityCacheUtil.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldGlBalance> ivldGlBalances) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldGlBalance ivldGlBalance : ivldGlBalances) {
            EntityCacheUtil.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
                IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
     *
     * @param ivldGlBalanceSid the primary key for the new ivld gl balance
     * @return the new ivld gl balance
     */
    @Override
    public IvldGlBalance create(int ivldGlBalanceSid) {
        IvldGlBalance ivldGlBalance = new IvldGlBalanceImpl();

        ivldGlBalance.setNew(true);
        ivldGlBalance.setPrimaryKey(ivldGlBalanceSid);

        return ivldGlBalance;
    }

    /**
     * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldGlBalanceSid the primary key of the ivld gl balance
     * @return the ivld gl balance that was removed
     * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance remove(int ivldGlBalanceSid)
        throws NoSuchIvldGlBalanceException, SystemException {
        return remove((Serializable) ivldGlBalanceSid);
    }

    /**
     * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld gl balance
     * @return the ivld gl balance that was removed
     * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance remove(Serializable primaryKey)
        throws NoSuchIvldGlBalanceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldGlBalance ivldGlBalance = (IvldGlBalance) session.get(IvldGlBalanceImpl.class,
                    primaryKey);

            if (ivldGlBalance == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldGlBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldGlBalance);
        } catch (NoSuchIvldGlBalanceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldGlBalance removeImpl(IvldGlBalance ivldGlBalance)
        throws SystemException {
        ivldGlBalance = toUnwrappedModel(ivldGlBalance);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldGlBalance)) {
                ivldGlBalance = (IvldGlBalance) session.get(IvldGlBalanceImpl.class,
                        ivldGlBalance.getPrimaryKeyObj());
            }

            if (ivldGlBalance != null) {
                session.delete(ivldGlBalance);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldGlBalance != null) {
            clearCache(ivldGlBalance);
        }

        return ivldGlBalance;
    }

    @Override
    public IvldGlBalance updateImpl(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws SystemException {
        ivldGlBalance = toUnwrappedModel(ivldGlBalance);

        boolean isNew = ivldGlBalance.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldGlBalance.isNew()) {
                session.save(ivldGlBalance);

                ivldGlBalance.setNew(false);
            } else {
                session.merge(ivldGlBalance);
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

        EntityCacheUtil.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey(),
            ivldGlBalance);

        return ivldGlBalance;
    }

    protected IvldGlBalance toUnwrappedModel(IvldGlBalance ivldGlBalance) {
        if (ivldGlBalance instanceof IvldGlBalanceImpl) {
            return ivldGlBalance;
        }

        IvldGlBalanceImpl ivldGlBalanceImpl = new IvldGlBalanceImpl();

        ivldGlBalanceImpl.setNew(ivldGlBalance.isNew());
        ivldGlBalanceImpl.setPrimaryKey(ivldGlBalance.getPrimaryKey());

        ivldGlBalanceImpl.setBalance(ivldGlBalance.getBalance());
        ivldGlBalanceImpl.setAccountNo(ivldGlBalance.getAccountNo());
        ivldGlBalanceImpl.setReasonForFailure(ivldGlBalance.getReasonForFailure());
        ivldGlBalanceImpl.setAccountId(ivldGlBalance.getAccountId());
        ivldGlBalanceImpl.setYear(ivldGlBalance.getYear());
        ivldGlBalanceImpl.setPeriod(ivldGlBalance.getPeriod());
        ivldGlBalanceImpl.setModifiedDate(ivldGlBalance.getModifiedDate());
        ivldGlBalanceImpl.setIsActive(ivldGlBalance.getIsActive());
        ivldGlBalanceImpl.setSource(ivldGlBalance.getSource());
        ivldGlBalanceImpl.setUploadDate(ivldGlBalance.getUploadDate());
        ivldGlBalanceImpl.setCreatedBy(ivldGlBalance.getCreatedBy());
        ivldGlBalanceImpl.setCreatedDate(ivldGlBalance.getCreatedDate());
        ivldGlBalanceImpl.setAddChgDelIndicator(ivldGlBalance.getAddChgDelIndicator());
        ivldGlBalanceImpl.setBatchId(ivldGlBalance.getBatchId());
        ivldGlBalanceImpl.setCloseIndicator(ivldGlBalance.getCloseIndicator());
        ivldGlBalanceImpl.setInsertedDate(ivldGlBalance.getInsertedDate());
        ivldGlBalanceImpl.setErrorField(ivldGlBalance.getErrorField());
        ivldGlBalanceImpl.setIvldGlBalanceSid(ivldGlBalance.getIvldGlBalanceSid());
        ivldGlBalanceImpl.setErrorCode(ivldGlBalance.getErrorCode());
        ivldGlBalanceImpl.setGlBalanceIntfid(ivldGlBalance.getGlBalanceIntfid());
        ivldGlBalanceImpl.setIntfInsertedDate(ivldGlBalance.getIntfInsertedDate());
        ivldGlBalanceImpl.setModifiedBy(ivldGlBalance.getModifiedBy());
        ivldGlBalanceImpl.setReprocessedFlag(ivldGlBalance.getReprocessedFlag());
        ivldGlBalanceImpl.setCheckRecord(ivldGlBalance.isCheckRecord());

        return ivldGlBalanceImpl;
    }

    /**
     * Returns the ivld gl balance with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld gl balance
     * @return the ivld gl balance
     * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldGlBalanceException, SystemException {
        IvldGlBalance ivldGlBalance = fetchByPrimaryKey(primaryKey);

        if (ivldGlBalance == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldGlBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldGlBalance;
    }

    /**
     * Returns the ivld gl balance with the primary key or throws a {@link com.stpl.app.NoSuchIvldGlBalanceException} if it could not be found.
     *
     * @param ivldGlBalanceSid the primary key of the ivld gl balance
     * @return the ivld gl balance
     * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance findByPrimaryKey(int ivldGlBalanceSid)
        throws NoSuchIvldGlBalanceException, SystemException {
        return findByPrimaryKey((Serializable) ivldGlBalanceSid);
    }

    /**
     * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld gl balance
     * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldGlBalance ivldGlBalance = (IvldGlBalance) EntityCacheUtil.getResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
                IvldGlBalanceImpl.class, primaryKey);

        if (ivldGlBalance == _nullIvldGlBalance) {
            return null;
        }

        if (ivldGlBalance == null) {
            Session session = null;

            try {
                session = openSession();

                ivldGlBalance = (IvldGlBalance) session.get(IvldGlBalanceImpl.class,
                        primaryKey);

                if (ivldGlBalance != null) {
                    cacheResult(ivldGlBalance);
                } else {
                    EntityCacheUtil.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
                        IvldGlBalanceImpl.class, primaryKey, _nullIvldGlBalance);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
                    IvldGlBalanceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldGlBalance;
    }

    /**
     * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldGlBalanceSid the primary key of the ivld gl balance
     * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlBalance fetchByPrimaryKey(int ivldGlBalanceSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldGlBalanceSid);
    }

    /**
     * Returns all the ivld gl balances.
     *
     * @return the ivld gl balances
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlBalance> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld gl balances.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld gl balances
     * @param end the upper bound of the range of ivld gl balances (not inclusive)
     * @return the range of ivld gl balances
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlBalance> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld gl balances.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld gl balances
     * @param end the upper bound of the range of ivld gl balances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld gl balances
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlBalance> findAll(int start, int end,
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

        List<IvldGlBalance> list = (List<IvldGlBalance>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDGLBALANCE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDGLBALANCE;

                if (pagination) {
                    sql = sql.concat(IvldGlBalanceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldGlBalance>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldGlBalance>(list);
                } else {
                    list = (List<IvldGlBalance>) QueryUtil.list(q,
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
     * Removes all the ivld gl balances from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldGlBalance ivldGlBalance : findAll()) {
            remove(ivldGlBalance);
        }
    }

    /**
     * Returns the number of ivld gl balances.
     *
     * @return the number of ivld gl balances
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

                Query q = session.createQuery(_SQL_COUNT_IVLDGLBALANCE);

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
     * Initializes the ivld gl balance persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldGlBalance")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldGlBalance>> listenersList = new ArrayList<ModelListener<IvldGlBalance>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldGlBalance>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldGlBalanceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
