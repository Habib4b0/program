package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIfpContractException;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.impl.IfpContractImpl;
import com.stpl.app.model.impl.IfpContractModelImpl;
import com.stpl.app.service.persistence.IfpContractPersistence;

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
 * The persistence implementation for the ifp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractPersistence
 * @see IfpContractUtil
 * @generated
 */
public class IfpContractPersistenceImpl extends BasePersistenceImpl<IfpContract>
    implements IfpContractPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IfpContractUtil} to access the ifp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IfpContractImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractModelImpl.FINDER_CACHE_ENABLED, IfpContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractModelImpl.FINDER_CACHE_ENABLED, IfpContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IFPCONTRACT = "SELECT ifpContract FROM IfpContract ifpContract";
    private static final String _SQL_COUNT_IFPCONTRACT = "SELECT COUNT(ifpContract) FROM IfpContract ifpContract";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ifpContract.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpContract exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IfpContractPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "cfpContractSid", "parentIfpName", "ifpContractAttachedDate",
                "ifpStatus", "ifpStartDate", "ifpContractAttachedStatus",
                "modifiedDate", "ifpCategory", "recordLockStatus", "ifpEndDate",
                "createdDate", "createdBy", "source", "ifpDesignation",
                "parentIfpId", "batchId", "contractMasterSid", "ifpType",
                "ifpName", "modifiedBy", "inboundStatus", "ifpContractSid",
                "ifpModelSid"
            });
    private static IfpContract _nullIfpContract = new IfpContractImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IfpContract> toCacheModel() {
                return _nullIfpContractCacheModel;
            }
        };

    private static CacheModel<IfpContract> _nullIfpContractCacheModel = new CacheModel<IfpContract>() {
            @Override
            public IfpContract toEntityModel() {
                return _nullIfpContract;
            }
        };

    public IfpContractPersistenceImpl() {
        setModelClass(IfpContract.class);
    }

    /**
     * Caches the ifp contract in the entity cache if it is enabled.
     *
     * @param ifpContract the ifp contract
     */
    @Override
    public void cacheResult(IfpContract ifpContract) {
        EntityCacheUtil.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractImpl.class, ifpContract.getPrimaryKey(), ifpContract);

        ifpContract.resetOriginalValues();
    }

    /**
     * Caches the ifp contracts in the entity cache if it is enabled.
     *
     * @param ifpContracts the ifp contracts
     */
    @Override
    public void cacheResult(List<IfpContract> ifpContracts) {
        for (IfpContract ifpContract : ifpContracts) {
            if (EntityCacheUtil.getResult(
                        IfpContractModelImpl.ENTITY_CACHE_ENABLED,
                        IfpContractImpl.class, ifpContract.getPrimaryKey()) == null) {
                cacheResult(ifpContract);
            } else {
                ifpContract.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ifp contracts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IfpContractImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IfpContractImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ifp contract.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IfpContract ifpContract) {
        EntityCacheUtil.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractImpl.class, ifpContract.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IfpContract> ifpContracts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IfpContract ifpContract : ifpContracts) {
            EntityCacheUtil.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
                IfpContractImpl.class, ifpContract.getPrimaryKey());
        }
    }

    /**
     * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
     *
     * @param ifpContractSid the primary key for the new ifp contract
     * @return the new ifp contract
     */
    @Override
    public IfpContract create(int ifpContractSid) {
        IfpContract ifpContract = new IfpContractImpl();

        ifpContract.setNew(true);
        ifpContract.setPrimaryKey(ifpContractSid);

        return ifpContract;
    }

    /**
     * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ifpContractSid the primary key of the ifp contract
     * @return the ifp contract that was removed
     * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract remove(int ifpContractSid)
        throws NoSuchIfpContractException, SystemException {
        return remove((Serializable) ifpContractSid);
    }

    /**
     * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ifp contract
     * @return the ifp contract that was removed
     * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract remove(Serializable primaryKey)
        throws NoSuchIfpContractException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IfpContract ifpContract = (IfpContract) session.get(IfpContractImpl.class,
                    primaryKey);

            if (ifpContract == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ifpContract);
        } catch (NoSuchIfpContractException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IfpContract removeImpl(IfpContract ifpContract)
        throws SystemException {
        ifpContract = toUnwrappedModel(ifpContract);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ifpContract)) {
                ifpContract = (IfpContract) session.get(IfpContractImpl.class,
                        ifpContract.getPrimaryKeyObj());
            }

            if (ifpContract != null) {
                session.delete(ifpContract);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ifpContract != null) {
            clearCache(ifpContract);
        }

        return ifpContract;
    }

    @Override
    public IfpContract updateImpl(com.stpl.app.model.IfpContract ifpContract)
        throws SystemException {
        ifpContract = toUnwrappedModel(ifpContract);

        boolean isNew = ifpContract.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ifpContract.isNew()) {
                session.save(ifpContract);

                ifpContract.setNew(false);
            } else {
                session.merge(ifpContract);
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

        EntityCacheUtil.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
            IfpContractImpl.class, ifpContract.getPrimaryKey(), ifpContract);

        return ifpContract;
    }

    protected IfpContract toUnwrappedModel(IfpContract ifpContract) {
        if (ifpContract instanceof IfpContractImpl) {
            return ifpContract;
        }

        IfpContractImpl ifpContractImpl = new IfpContractImpl();

        ifpContractImpl.setNew(ifpContract.isNew());
        ifpContractImpl.setPrimaryKey(ifpContract.getPrimaryKey());

        ifpContractImpl.setCfpContractSid(ifpContract.getCfpContractSid());
        ifpContractImpl.setParentIfpName(ifpContract.getParentIfpName());
        ifpContractImpl.setIfpContractAttachedDate(ifpContract.getIfpContractAttachedDate());
        ifpContractImpl.setIfpStatus(ifpContract.getIfpStatus());
        ifpContractImpl.setIfpStartDate(ifpContract.getIfpStartDate());
        ifpContractImpl.setIfpContractAttachedStatus(ifpContract.getIfpContractAttachedStatus());
        ifpContractImpl.setModifiedDate(ifpContract.getModifiedDate());
        ifpContractImpl.setIfpCategory(ifpContract.getIfpCategory());
        ifpContractImpl.setRecordLockStatus(ifpContract.isRecordLockStatus());
        ifpContractImpl.setIfpEndDate(ifpContract.getIfpEndDate());
        ifpContractImpl.setCreatedDate(ifpContract.getCreatedDate());
        ifpContractImpl.setCreatedBy(ifpContract.getCreatedBy());
        ifpContractImpl.setSource(ifpContract.getSource());
        ifpContractImpl.setIfpDesignation(ifpContract.getIfpDesignation());
        ifpContractImpl.setParentIfpId(ifpContract.getParentIfpId());
        ifpContractImpl.setBatchId(ifpContract.getBatchId());
        ifpContractImpl.setContractMasterSid(ifpContract.getContractMasterSid());
        ifpContractImpl.setIfpType(ifpContract.getIfpType());
        ifpContractImpl.setIfpName(ifpContract.getIfpName());
        ifpContractImpl.setModifiedBy(ifpContract.getModifiedBy());
        ifpContractImpl.setInboundStatus(ifpContract.getInboundStatus());
        ifpContractImpl.setIfpContractSid(ifpContract.getIfpContractSid());
        ifpContractImpl.setIfpModelSid(ifpContract.getIfpModelSid());

        return ifpContractImpl;
    }

    /**
     * Returns the ifp contract with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ifp contract
     * @return the ifp contract
     * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIfpContractException, SystemException {
        IfpContract ifpContract = fetchByPrimaryKey(primaryKey);

        if (ifpContract == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ifpContract;
    }

    /**
     * Returns the ifp contract with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractException} if it could not be found.
     *
     * @param ifpContractSid the primary key of the ifp contract
     * @return the ifp contract
     * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract findByPrimaryKey(int ifpContractSid)
        throws NoSuchIfpContractException, SystemException {
        return findByPrimaryKey((Serializable) ifpContractSid);
    }

    /**
     * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ifp contract
     * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IfpContract ifpContract = (IfpContract) EntityCacheUtil.getResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
                IfpContractImpl.class, primaryKey);

        if (ifpContract == _nullIfpContract) {
            return null;
        }

        if (ifpContract == null) {
            Session session = null;

            try {
                session = openSession();

                ifpContract = (IfpContract) session.get(IfpContractImpl.class,
                        primaryKey);

                if (ifpContract != null) {
                    cacheResult(ifpContract);
                } else {
                    EntityCacheUtil.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
                        IfpContractImpl.class, primaryKey, _nullIfpContract);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
                    IfpContractImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ifpContract;
    }

    /**
     * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ifpContractSid the primary key of the ifp contract
     * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IfpContract fetchByPrimaryKey(int ifpContractSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ifpContractSid);
    }

    /**
     * Returns all the ifp contracts.
     *
     * @return the ifp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContract> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ifp contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp contracts
     * @param end the upper bound of the range of ifp contracts (not inclusive)
     * @return the range of ifp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContract> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ifp contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ifp contracts
     * @param end the upper bound of the range of ifp contracts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ifp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IfpContract> findAll(int start, int end,
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

        List<IfpContract> list = (List<IfpContract>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IFPCONTRACT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IFPCONTRACT;

                if (pagination) {
                    sql = sql.concat(IfpContractModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IfpContract>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IfpContract>(list);
                } else {
                    list = (List<IfpContract>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ifp contracts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IfpContract ifpContract : findAll()) {
            remove(ifpContract);
        }
    }

    /**
     * Returns the number of ifp contracts.
     *
     * @return the number of ifp contracts
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

                Query q = session.createQuery(_SQL_COUNT_IFPCONTRACT);

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
     * Initializes the ifp contract persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IfpContract")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IfpContract>> listenersList = new ArrayList<ModelListener<IfpContract>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IfpContract>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IfpContractImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
