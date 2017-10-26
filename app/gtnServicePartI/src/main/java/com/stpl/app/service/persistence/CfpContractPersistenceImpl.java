package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCfpContractException;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.impl.CfpContractImpl;
import com.stpl.app.model.impl.CfpContractModelImpl;
import com.stpl.app.service.persistence.CfpContractPersistence;

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
 * The persistence implementation for the cfp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractPersistence
 * @see CfpContractUtil
 * @generated
 */
public class CfpContractPersistenceImpl extends BasePersistenceImpl<CfpContract>
    implements CfpContractPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CfpContractUtil} to access the cfp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CfpContractImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractModelImpl.FINDER_CACHE_ENABLED, CfpContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractModelImpl.FINDER_CACHE_ENABLED, CfpContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFPCONTRACT = "SELECT cfpContract FROM CfpContract cfpContract";
    private static final String _SQL_COUNT_CFPCONTRACT = "SELECT COUNT(cfpContract) FROM CfpContract cfpContract";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cfpContract.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpContract exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CfpContractPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "cfpContractSid", "cfpType", "cfpTradeClass",
                "modifiedBy", "createdDate", "contractMasterSid",
                "cfpContractAttachedDate", "cfpModelSid", "batchId",
                "modifiedDate", "recordLockStatus", "cfpDesignation", "cfpName",
                "cfpNo", "cfpCategory", "source", "cfpStatus", "parentCfpId",
                "cfpContractAttachedStatus", "cfpStartDate", "cfpEndDate",
                "parentCfpName", "inboundStatus", "salesInclusion"
            });
    private static CfpContract _nullCfpContract = new CfpContractImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CfpContract> toCacheModel() {
                return _nullCfpContractCacheModel;
            }
        };

    private static CacheModel<CfpContract> _nullCfpContractCacheModel = new CacheModel<CfpContract>() {
            @Override
            public CfpContract toEntityModel() {
                return _nullCfpContract;
            }
        };

    public CfpContractPersistenceImpl() {
        setModelClass(CfpContract.class);
    }

    /**
     * Caches the cfp contract in the entity cache if it is enabled.
     *
     * @param cfpContract the cfp contract
     */
    @Override
    public void cacheResult(CfpContract cfpContract) {
        EntityCacheUtil.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractImpl.class, cfpContract.getPrimaryKey(), cfpContract);

        cfpContract.resetOriginalValues();
    }

    /**
     * Caches the cfp contracts in the entity cache if it is enabled.
     *
     * @param cfpContracts the cfp contracts
     */
    @Override
    public void cacheResult(List<CfpContract> cfpContracts) {
        for (CfpContract cfpContract : cfpContracts) {
            if (EntityCacheUtil.getResult(
                        CfpContractModelImpl.ENTITY_CACHE_ENABLED,
                        CfpContractImpl.class, cfpContract.getPrimaryKey()) == null) {
                cacheResult(cfpContract);
            } else {
                cfpContract.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cfp contracts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CfpContractImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CfpContractImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cfp contract.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CfpContract cfpContract) {
        EntityCacheUtil.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractImpl.class, cfpContract.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CfpContract> cfpContracts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CfpContract cfpContract : cfpContracts) {
            EntityCacheUtil.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
                CfpContractImpl.class, cfpContract.getPrimaryKey());
        }
    }

    /**
     * Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
     *
     * @param cfpContractSid the primary key for the new cfp contract
     * @return the new cfp contract
     */
    @Override
    public CfpContract create(int cfpContractSid) {
        CfpContract cfpContract = new CfpContractImpl();

        cfpContract.setNew(true);
        cfpContract.setPrimaryKey(cfpContractSid);

        return cfpContract;
    }

    /**
     * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cfpContractSid the primary key of the cfp contract
     * @return the cfp contract that was removed
     * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract remove(int cfpContractSid)
        throws NoSuchCfpContractException, SystemException {
        return remove((Serializable) cfpContractSid);
    }

    /**
     * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cfp contract
     * @return the cfp contract that was removed
     * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract remove(Serializable primaryKey)
        throws NoSuchCfpContractException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CfpContract cfpContract = (CfpContract) session.get(CfpContractImpl.class,
                    primaryKey);

            if (cfpContract == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cfpContract);
        } catch (NoSuchCfpContractException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CfpContract removeImpl(CfpContract cfpContract)
        throws SystemException {
        cfpContract = toUnwrappedModel(cfpContract);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cfpContract)) {
                cfpContract = (CfpContract) session.get(CfpContractImpl.class,
                        cfpContract.getPrimaryKeyObj());
            }

            if (cfpContract != null) {
                session.delete(cfpContract);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cfpContract != null) {
            clearCache(cfpContract);
        }

        return cfpContract;
    }

    @Override
    public CfpContract updateImpl(com.stpl.app.model.CfpContract cfpContract)
        throws SystemException {
        cfpContract = toUnwrappedModel(cfpContract);

        boolean isNew = cfpContract.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cfpContract.isNew()) {
                session.save(cfpContract);

                cfpContract.setNew(false);
            } else {
                session.merge(cfpContract);
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

        EntityCacheUtil.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
            CfpContractImpl.class, cfpContract.getPrimaryKey(), cfpContract);

        return cfpContract;
    }

    protected CfpContract toUnwrappedModel(CfpContract cfpContract) {
        if (cfpContract instanceof CfpContractImpl) {
            return cfpContract;
        }

        CfpContractImpl cfpContractImpl = new CfpContractImpl();

        cfpContractImpl.setNew(cfpContract.isNew());
        cfpContractImpl.setPrimaryKey(cfpContract.getPrimaryKey());

        cfpContractImpl.setCreatedBy(cfpContract.getCreatedBy());
        cfpContractImpl.setCfpContractSid(cfpContract.getCfpContractSid());
        cfpContractImpl.setCfpType(cfpContract.getCfpType());
        cfpContractImpl.setCfpTradeClass(cfpContract.getCfpTradeClass());
        cfpContractImpl.setModifiedBy(cfpContract.getModifiedBy());
        cfpContractImpl.setCreatedDate(cfpContract.getCreatedDate());
        cfpContractImpl.setContractMasterSid(cfpContract.getContractMasterSid());
        cfpContractImpl.setCfpContractAttachedDate(cfpContract.getCfpContractAttachedDate());
        cfpContractImpl.setCfpModelSid(cfpContract.getCfpModelSid());
        cfpContractImpl.setBatchId(cfpContract.getBatchId());
        cfpContractImpl.setModifiedDate(cfpContract.getModifiedDate());
        cfpContractImpl.setRecordLockStatus(cfpContract.isRecordLockStatus());
        cfpContractImpl.setCfpDesignation(cfpContract.getCfpDesignation());
        cfpContractImpl.setCfpName(cfpContract.getCfpName());
        cfpContractImpl.setCfpNo(cfpContract.getCfpNo());
        cfpContractImpl.setCfpCategory(cfpContract.getCfpCategory());
        cfpContractImpl.setSource(cfpContract.getSource());
        cfpContractImpl.setCfpStatus(cfpContract.getCfpStatus());
        cfpContractImpl.setParentCfpId(cfpContract.getParentCfpId());
        cfpContractImpl.setCfpContractAttachedStatus(cfpContract.getCfpContractAttachedStatus());
        cfpContractImpl.setCfpStartDate(cfpContract.getCfpStartDate());
        cfpContractImpl.setCfpEndDate(cfpContract.getCfpEndDate());
        cfpContractImpl.setParentCfpName(cfpContract.getParentCfpName());
        cfpContractImpl.setInboundStatus(cfpContract.getInboundStatus());
        cfpContractImpl.setSalesInclusion(cfpContract.getSalesInclusion());

        return cfpContractImpl;
    }

    /**
     * Returns the cfp contract with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cfp contract
     * @return the cfp contract
     * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCfpContractException, SystemException {
        CfpContract cfpContract = fetchByPrimaryKey(primaryKey);

        if (cfpContract == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cfpContract;
    }

    /**
     * Returns the cfp contract with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractException} if it could not be found.
     *
     * @param cfpContractSid the primary key of the cfp contract
     * @return the cfp contract
     * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract findByPrimaryKey(int cfpContractSid)
        throws NoSuchCfpContractException, SystemException {
        return findByPrimaryKey((Serializable) cfpContractSid);
    }

    /**
     * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cfp contract
     * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CfpContract cfpContract = (CfpContract) EntityCacheUtil.getResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
                CfpContractImpl.class, primaryKey);

        if (cfpContract == _nullCfpContract) {
            return null;
        }

        if (cfpContract == null) {
            Session session = null;

            try {
                session = openSession();

                cfpContract = (CfpContract) session.get(CfpContractImpl.class,
                        primaryKey);

                if (cfpContract != null) {
                    cacheResult(cfpContract);
                } else {
                    EntityCacheUtil.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
                        CfpContractImpl.class, primaryKey, _nullCfpContract);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
                    CfpContractImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cfpContract;
    }

    /**
     * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cfpContractSid the primary key of the cfp contract
     * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CfpContract fetchByPrimaryKey(int cfpContractSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cfpContractSid);
    }

    /**
     * Returns all the cfp contracts.
     *
     * @return the cfp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContract> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cfp contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp contracts
     * @param end the upper bound of the range of cfp contracts (not inclusive)
     * @return the range of cfp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContract> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cfp contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cfp contracts
     * @param end the upper bound of the range of cfp contracts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cfp contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CfpContract> findAll(int start, int end,
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

        List<CfpContract> list = (List<CfpContract>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFPCONTRACT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFPCONTRACT;

                if (pagination) {
                    sql = sql.concat(CfpContractModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CfpContract>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CfpContract>(list);
                } else {
                    list = (List<CfpContract>) QueryUtil.list(q, getDialect(),
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
     * Removes all the cfp contracts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CfpContract cfpContract : findAll()) {
            remove(cfpContract);
        }
    }

    /**
     * Returns the number of cfp contracts.
     *
     * @return the number of cfp contracts
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

                Query q = session.createQuery(_SQL_COUNT_CFPCONTRACT);

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
     * Initializes the cfp contract persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CfpContract")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CfpContract>> listenersList = new ArrayList<ModelListener<CfpContract>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CfpContract>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CfpContractImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
