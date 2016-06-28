package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchPsContractException;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.impl.PsContractImpl;
import com.stpl.app.model.impl.PsContractModelImpl;
import com.stpl.app.service.persistence.PsContractPersistence;

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
 * The persistence implementation for the ps contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractPersistence
 * @see PsContractUtil
 * @generated
 */
public class PsContractPersistenceImpl extends BasePersistenceImpl<PsContract>
    implements PsContractPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PsContractUtil} to access the ps contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PsContractImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractModelImpl.FINDER_CACHE_ENABLED, PsContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractModelImpl.FINDER_CACHE_ENABLED, PsContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PSCONTRACT = "SELECT psContract FROM PsContract psContract";
    private static final String _SQL_COUNT_PSCONTRACT = "SELECT COUNT(psContract) FROM PsContract psContract";
    private static final String _ORDER_BY_ENTITY_ALIAS = "psContract.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsContract exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PsContractPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "psName", "cfpContractSid", "psContractSid", "psType",
                "psContractAttachedStatus", "modifiedDate", "psCategory",
                "recordLockStatus", "psStatus", "createdDate", "createdBy",
                "source", "parentPsId", "psDesignation", "batchId",
                "contractMasterSid", "psModelSid", "psContractAttachedDate",
                "psEndDate", "modifiedBy", "inboundStatus", "parentPsName",
                "psStartDate", "ifpContractSid", "psTradeClass"
            });
    private static PsContract _nullPsContract = new PsContractImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PsContract> toCacheModel() {
                return _nullPsContractCacheModel;
            }
        };

    private static CacheModel<PsContract> _nullPsContractCacheModel = new CacheModel<PsContract>() {
            @Override
            public PsContract toEntityModel() {
                return _nullPsContract;
            }
        };

    public PsContractPersistenceImpl() {
        setModelClass(PsContract.class);
    }

    /**
     * Caches the ps contract in the entity cache if it is enabled.
     *
     * @param psContract the ps contract
     */
    @Override
    public void cacheResult(PsContract psContract) {
        EntityCacheUtil.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractImpl.class, psContract.getPrimaryKey(), psContract);

        psContract.resetOriginalValues();
    }

    /**
     * Caches the ps contracts in the entity cache if it is enabled.
     *
     * @param psContracts the ps contracts
     */
    @Override
    public void cacheResult(List<PsContract> psContracts) {
        for (PsContract psContract : psContracts) {
            if (EntityCacheUtil.getResult(
                        PsContractModelImpl.ENTITY_CACHE_ENABLED,
                        PsContractImpl.class, psContract.getPrimaryKey()) == null) {
                cacheResult(psContract);
            } else {
                psContract.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ps contracts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PsContractImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PsContractImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ps contract.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PsContract psContract) {
        EntityCacheUtil.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractImpl.class, psContract.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PsContract> psContracts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PsContract psContract : psContracts) {
            EntityCacheUtil.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
                PsContractImpl.class, psContract.getPrimaryKey());
        }
    }

    /**
     * Creates a new ps contract with the primary key. Does not add the ps contract to the database.
     *
     * @param psContractSid the primary key for the new ps contract
     * @return the new ps contract
     */
    @Override
    public PsContract create(int psContractSid) {
        PsContract psContract = new PsContractImpl();

        psContract.setNew(true);
        psContract.setPrimaryKey(psContractSid);

        return psContract;
    }

    /**
     * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param psContractSid the primary key of the ps contract
     * @return the ps contract that was removed
     * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract remove(int psContractSid)
        throws NoSuchPsContractException, SystemException {
        return remove((Serializable) psContractSid);
    }

    /**
     * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ps contract
     * @return the ps contract that was removed
     * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract remove(Serializable primaryKey)
        throws NoSuchPsContractException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PsContract psContract = (PsContract) session.get(PsContractImpl.class,
                    primaryKey);

            if (psContract == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(psContract);
        } catch (NoSuchPsContractException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PsContract removeImpl(PsContract psContract)
        throws SystemException {
        psContract = toUnwrappedModel(psContract);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(psContract)) {
                psContract = (PsContract) session.get(PsContractImpl.class,
                        psContract.getPrimaryKeyObj());
            }

            if (psContract != null) {
                session.delete(psContract);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (psContract != null) {
            clearCache(psContract);
        }

        return psContract;
    }

    @Override
    public PsContract updateImpl(com.stpl.app.model.PsContract psContract)
        throws SystemException {
        psContract = toUnwrappedModel(psContract);

        boolean isNew = psContract.isNew();

        Session session = null;

        try {
            session = openSession();

            if (psContract.isNew()) {
                session.save(psContract);

                psContract.setNew(false);
            } else {
                session.merge(psContract);
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

        EntityCacheUtil.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
            PsContractImpl.class, psContract.getPrimaryKey(), psContract);

        return psContract;
    }

    protected PsContract toUnwrappedModel(PsContract psContract) {
        if (psContract instanceof PsContractImpl) {
            return psContract;
        }

        PsContractImpl psContractImpl = new PsContractImpl();

        psContractImpl.setNew(psContract.isNew());
        psContractImpl.setPrimaryKey(psContract.getPrimaryKey());

        psContractImpl.setPsName(psContract.getPsName());
        psContractImpl.setCfpContractSid(psContract.getCfpContractSid());
        psContractImpl.setPsContractSid(psContract.getPsContractSid());
        psContractImpl.setPsType(psContract.getPsType());
        psContractImpl.setPsContractAttachedStatus(psContract.getPsContractAttachedStatus());
        psContractImpl.setModifiedDate(psContract.getModifiedDate());
        psContractImpl.setPsCategory(psContract.getPsCategory());
        psContractImpl.setRecordLockStatus(psContract.isRecordLockStatus());
        psContractImpl.setPsStatus(psContract.getPsStatus());
        psContractImpl.setCreatedDate(psContract.getCreatedDate());
        psContractImpl.setCreatedBy(psContract.getCreatedBy());
        psContractImpl.setSource(psContract.getSource());
        psContractImpl.setParentPsId(psContract.getParentPsId());
        psContractImpl.setPsDesignation(psContract.getPsDesignation());
        psContractImpl.setBatchId(psContract.getBatchId());
        psContractImpl.setContractMasterSid(psContract.getContractMasterSid());
        psContractImpl.setPsModelSid(psContract.getPsModelSid());
        psContractImpl.setPsContractAttachedDate(psContract.getPsContractAttachedDate());
        psContractImpl.setPsEndDate(psContract.getPsEndDate());
        psContractImpl.setModifiedBy(psContract.getModifiedBy());
        psContractImpl.setInboundStatus(psContract.getInboundStatus());
        psContractImpl.setParentPsName(psContract.getParentPsName());
        psContractImpl.setPsStartDate(psContract.getPsStartDate());
        psContractImpl.setIfpContractSid(psContract.getIfpContractSid());
        psContractImpl.setPsTradeClass(psContract.getPsTradeClass());

        return psContractImpl;
    }

    /**
     * Returns the ps contract with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ps contract
     * @return the ps contract
     * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPsContractException, SystemException {
        PsContract psContract = fetchByPrimaryKey(primaryKey);

        if (psContract == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return psContract;
    }

    /**
     * Returns the ps contract with the primary key or throws a {@link com.stpl.app.NoSuchPsContractException} if it could not be found.
     *
     * @param psContractSid the primary key of the ps contract
     * @return the ps contract
     * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract findByPrimaryKey(int psContractSid)
        throws NoSuchPsContractException, SystemException {
        return findByPrimaryKey((Serializable) psContractSid);
    }

    /**
     * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ps contract
     * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PsContract psContract = (PsContract) EntityCacheUtil.getResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
                PsContractImpl.class, primaryKey);

        if (psContract == _nullPsContract) {
            return null;
        }

        if (psContract == null) {
            Session session = null;

            try {
                session = openSession();

                psContract = (PsContract) session.get(PsContractImpl.class,
                        primaryKey);

                if (psContract != null) {
                    cacheResult(psContract);
                } else {
                    EntityCacheUtil.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
                        PsContractImpl.class, primaryKey, _nullPsContract);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
                    PsContractImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return psContract;
    }

    /**
     * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param psContractSid the primary key of the ps contract
     * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContract fetchByPrimaryKey(int psContractSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) psContractSid);
    }

    /**
     * Returns all the ps contracts.
     *
     * @return the ps contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContract> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ps contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps contracts
     * @param end the upper bound of the range of ps contracts (not inclusive)
     * @return the range of ps contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContract> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ps contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps contracts
     * @param end the upper bound of the range of ps contracts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ps contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContract> findAll(int start, int end,
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

        List<PsContract> list = (List<PsContract>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PSCONTRACT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PSCONTRACT;

                if (pagination) {
                    sql = sql.concat(PsContractModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PsContract>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PsContract>(list);
                } else {
                    list = (List<PsContract>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ps contracts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PsContract psContract : findAll()) {
            remove(psContract);
        }
    }

    /**
     * Returns the number of ps contracts.
     *
     * @return the number of ps contracts
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

                Query q = session.createQuery(_SQL_COUNT_PSCONTRACT);

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
     * Initializes the ps contract persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.PsContract")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PsContract>> listenersList = new ArrayList<ModelListener<PsContract>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PsContract>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PsContractImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
