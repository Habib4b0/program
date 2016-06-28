package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRsContractException;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.impl.RsContractImpl;
import com.stpl.app.model.impl.RsContractModelImpl;
import com.stpl.app.service.persistence.RsContractPersistence;

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
 * The persistence implementation for the rs contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractPersistence
 * @see RsContractUtil
 * @generated
 */
public class RsContractPersistenceImpl extends BasePersistenceImpl<RsContract>
    implements RsContractPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RsContractUtil} to access the rs contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RsContractImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractModelImpl.FINDER_CACHE_ENABLED, RsContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractModelImpl.FINDER_CACHE_ENABLED, RsContractImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RSCONTRACT = "SELECT rsContract FROM RsContract rsContract";
    private static final String _SQL_COUNT_RSCONTRACT = "SELECT COUNT(rsContract) FROM RsContract rsContract";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rsContract.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsContract exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RsContractPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "cfpContractSid", "createdDate", "psContractSid", "rsName",
                "rsStatus", "rsStartDate", "rsTransRefId", "makePayableTo",
                "createdBy", "rsCategory", "rsTradeClass", "contractMasterSid",
                "rebateRuleType", "paymentMethod", "rsContractAttachedDate",
                "rsAlias", "formulaMethodId", "rebateProcessingType",
                "rsContractAttachedStatus", "interestBearingBasis",
                "modifiedDate", "rsTransRefName", "rebateProgramType",
                "rebatePlanLevel", "source", "rsCalendar", "rsType", "address1",
                "address2", "rsEndDate", "modifiedBy", "rsTransRefNo", "zipCode",
                "rebateRuleAssociation", "state", "rebateFrequency",
                "rsDesignation", "batchId", "ifpContractSid", "rsContractSid",
                "paymentTerms", "rsNo", "rsModelSid", "rsValidationProfile",
                "paymentGracePeriod", "paymentFrequency", "recordLockStatus",
                "rsId", "city", "parentRsName", "interestBearingIndicator",
                "parentRsId", "inboundStatus", "calculationType",
                "calculationLevel", "calculationRule", "calculationRuleLevel",
                "evaluationRuleType", "evaluationRuleLevel",
                "evaluationRuleOrAssociation", "deductionInclusion"
            });
    private static RsContract _nullRsContract = new RsContractImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RsContract> toCacheModel() {
                return _nullRsContractCacheModel;
            }
        };

    private static CacheModel<RsContract> _nullRsContractCacheModel = new CacheModel<RsContract>() {
            @Override
            public RsContract toEntityModel() {
                return _nullRsContract;
            }
        };

    public RsContractPersistenceImpl() {
        setModelClass(RsContract.class);
    }

    /**
     * Caches the rs contract in the entity cache if it is enabled.
     *
     * @param rsContract the rs contract
     */
    @Override
    public void cacheResult(RsContract rsContract) {
        EntityCacheUtil.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractImpl.class, rsContract.getPrimaryKey(), rsContract);

        rsContract.resetOriginalValues();
    }

    /**
     * Caches the rs contracts in the entity cache if it is enabled.
     *
     * @param rsContracts the rs contracts
     */
    @Override
    public void cacheResult(List<RsContract> rsContracts) {
        for (RsContract rsContract : rsContracts) {
            if (EntityCacheUtil.getResult(
                        RsContractModelImpl.ENTITY_CACHE_ENABLED,
                        RsContractImpl.class, rsContract.getPrimaryKey()) == null) {
                cacheResult(rsContract);
            } else {
                rsContract.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rs contracts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RsContractImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RsContractImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rs contract.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RsContract rsContract) {
        EntityCacheUtil.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractImpl.class, rsContract.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RsContract> rsContracts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RsContract rsContract : rsContracts) {
            EntityCacheUtil.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
                RsContractImpl.class, rsContract.getPrimaryKey());
        }
    }

    /**
     * Creates a new rs contract with the primary key. Does not add the rs contract to the database.
     *
     * @param rsContractSid the primary key for the new rs contract
     * @return the new rs contract
     */
    @Override
    public RsContract create(int rsContractSid) {
        RsContract rsContract = new RsContractImpl();

        rsContract.setNew(true);
        rsContract.setPrimaryKey(rsContractSid);

        return rsContract;
    }

    /**
     * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rsContractSid the primary key of the rs contract
     * @return the rs contract that was removed
     * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract remove(int rsContractSid)
        throws NoSuchRsContractException, SystemException {
        return remove((Serializable) rsContractSid);
    }

    /**
     * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rs contract
     * @return the rs contract that was removed
     * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract remove(Serializable primaryKey)
        throws NoSuchRsContractException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RsContract rsContract = (RsContract) session.get(RsContractImpl.class,
                    primaryKey);

            if (rsContract == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rsContract);
        } catch (NoSuchRsContractException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RsContract removeImpl(RsContract rsContract)
        throws SystemException {
        rsContract = toUnwrappedModel(rsContract);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rsContract)) {
                rsContract = (RsContract) session.get(RsContractImpl.class,
                        rsContract.getPrimaryKeyObj());
            }

            if (rsContract != null) {
                session.delete(rsContract);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rsContract != null) {
            clearCache(rsContract);
        }

        return rsContract;
    }

    @Override
    public RsContract updateImpl(com.stpl.app.model.RsContract rsContract)
        throws SystemException {
        rsContract = toUnwrappedModel(rsContract);

        boolean isNew = rsContract.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rsContract.isNew()) {
                session.save(rsContract);

                rsContract.setNew(false);
            } else {
                session.merge(rsContract);
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

        EntityCacheUtil.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
            RsContractImpl.class, rsContract.getPrimaryKey(), rsContract);

        return rsContract;
    }

    protected RsContract toUnwrappedModel(RsContract rsContract) {
        if (rsContract instanceof RsContractImpl) {
            return rsContract;
        }

        RsContractImpl rsContractImpl = new RsContractImpl();

        rsContractImpl.setNew(rsContract.isNew());
        rsContractImpl.setPrimaryKey(rsContract.getPrimaryKey());

        rsContractImpl.setCfpContractSid(rsContract.getCfpContractSid());
        rsContractImpl.setCreatedDate(rsContract.getCreatedDate());
        rsContractImpl.setPsContractSid(rsContract.getPsContractSid());
        rsContractImpl.setRsName(rsContract.getRsName());
        rsContractImpl.setRsStatus(rsContract.getRsStatus());
        rsContractImpl.setRsStartDate(rsContract.getRsStartDate());
        rsContractImpl.setRsTransRefId(rsContract.getRsTransRefId());
        rsContractImpl.setMakePayableTo(rsContract.getMakePayableTo());
        rsContractImpl.setCreatedBy(rsContract.getCreatedBy());
        rsContractImpl.setRsCategory(rsContract.getRsCategory());
        rsContractImpl.setRsTradeClass(rsContract.getRsTradeClass());
        rsContractImpl.setContractMasterSid(rsContract.getContractMasterSid());
        rsContractImpl.setRebateRuleType(rsContract.getRebateRuleType());
        rsContractImpl.setPaymentMethod(rsContract.getPaymentMethod());
        rsContractImpl.setRsContractAttachedDate(rsContract.getRsContractAttachedDate());
        rsContractImpl.setRsAlias(rsContract.getRsAlias());
        rsContractImpl.setFormulaMethodId(rsContract.getFormulaMethodId());
        rsContractImpl.setRebateProcessingType(rsContract.getRebateProcessingType());
        rsContractImpl.setRsContractAttachedStatus(rsContract.getRsContractAttachedStatus());
        rsContractImpl.setInterestBearingBasis(rsContract.getInterestBearingBasis());
        rsContractImpl.setModifiedDate(rsContract.getModifiedDate());
        rsContractImpl.setRsTransRefName(rsContract.getRsTransRefName());
        rsContractImpl.setRebateProgramType(rsContract.getRebateProgramType());
        rsContractImpl.setRebatePlanLevel(rsContract.getRebatePlanLevel());
        rsContractImpl.setSource(rsContract.getSource());
        rsContractImpl.setRsCalendar(rsContract.getRsCalendar());
        rsContractImpl.setRsType(rsContract.getRsType());
        rsContractImpl.setAddress1(rsContract.getAddress1());
        rsContractImpl.setAddress2(rsContract.getAddress2());
        rsContractImpl.setRsEndDate(rsContract.getRsEndDate());
        rsContractImpl.setModifiedBy(rsContract.getModifiedBy());
        rsContractImpl.setRsTransRefNo(rsContract.getRsTransRefNo());
        rsContractImpl.setZipCode(rsContract.getZipCode());
        rsContractImpl.setRebateRuleAssociation(rsContract.getRebateRuleAssociation());
        rsContractImpl.setState(rsContract.getState());
        rsContractImpl.setRebateFrequency(rsContract.getRebateFrequency());
        rsContractImpl.setRsDesignation(rsContract.getRsDesignation());
        rsContractImpl.setBatchId(rsContract.getBatchId());
        rsContractImpl.setIfpContractSid(rsContract.getIfpContractSid());
        rsContractImpl.setRsContractSid(rsContract.getRsContractSid());
        rsContractImpl.setPaymentTerms(rsContract.getPaymentTerms());
        rsContractImpl.setRsNo(rsContract.getRsNo());
        rsContractImpl.setRsModelSid(rsContract.getRsModelSid());
        rsContractImpl.setRsValidationProfile(rsContract.getRsValidationProfile());
        rsContractImpl.setPaymentGracePeriod(rsContract.getPaymentGracePeriod());
        rsContractImpl.setPaymentFrequency(rsContract.getPaymentFrequency());
        rsContractImpl.setRecordLockStatus(rsContract.isRecordLockStatus());
        rsContractImpl.setRsId(rsContract.getRsId());
        rsContractImpl.setCity(rsContract.getCity());
        rsContractImpl.setParentRsName(rsContract.getParentRsName());
        rsContractImpl.setInterestBearingIndicator(rsContract.getInterestBearingIndicator());
        rsContractImpl.setParentRsId(rsContract.getParentRsId());
        rsContractImpl.setInboundStatus(rsContract.getInboundStatus());
        rsContractImpl.setCalculationType(rsContract.getCalculationType());
        rsContractImpl.setCalculationLevel(rsContract.getCalculationLevel());
        rsContractImpl.setCalculationRule(rsContract.getCalculationRule());
        rsContractImpl.setCalculationRuleLevel(rsContract.getCalculationRuleLevel());
        rsContractImpl.setEvaluationRuleType(rsContract.getEvaluationRuleType());
        rsContractImpl.setEvaluationRuleLevel(rsContract.getEvaluationRuleLevel());
        rsContractImpl.setEvaluationRuleOrAssociation(rsContract.getEvaluationRuleOrAssociation());
        rsContractImpl.setDeductionInclusion(rsContract.getDeductionInclusion());

        return rsContractImpl;
    }

    /**
     * Returns the rs contract with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rs contract
     * @return the rs contract
     * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRsContractException, SystemException {
        RsContract rsContract = fetchByPrimaryKey(primaryKey);

        if (rsContract == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rsContract;
    }

    /**
     * Returns the rs contract with the primary key or throws a {@link com.stpl.app.NoSuchRsContractException} if it could not be found.
     *
     * @param rsContractSid the primary key of the rs contract
     * @return the rs contract
     * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract findByPrimaryKey(int rsContractSid)
        throws NoSuchRsContractException, SystemException {
        return findByPrimaryKey((Serializable) rsContractSid);
    }

    /**
     * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rs contract
     * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RsContract rsContract = (RsContract) EntityCacheUtil.getResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
                RsContractImpl.class, primaryKey);

        if (rsContract == _nullRsContract) {
            return null;
        }

        if (rsContract == null) {
            Session session = null;

            try {
                session = openSession();

                rsContract = (RsContract) session.get(RsContractImpl.class,
                        primaryKey);

                if (rsContract != null) {
                    cacheResult(rsContract);
                } else {
                    EntityCacheUtil.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
                        RsContractImpl.class, primaryKey, _nullRsContract);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
                    RsContractImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rsContract;
    }

    /**
     * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rsContractSid the primary key of the rs contract
     * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContract fetchByPrimaryKey(int rsContractSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rsContractSid);
    }

    /**
     * Returns all the rs contracts.
     *
     * @return the rs contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContract> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rs contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs contracts
     * @param end the upper bound of the range of rs contracts (not inclusive)
     * @return the range of rs contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContract> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rs contracts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs contracts
     * @param end the upper bound of the range of rs contracts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rs contracts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContract> findAll(int start, int end,
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

        List<RsContract> list = (List<RsContract>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RSCONTRACT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RSCONTRACT;

                if (pagination) {
                    sql = sql.concat(RsContractModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RsContract>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsContract>(list);
                } else {
                    list = (List<RsContract>) QueryUtil.list(q, getDialect(),
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
     * Removes all the rs contracts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RsContract rsContract : findAll()) {
            remove(rsContract);
        }
    }

    /**
     * Returns the number of rs contracts.
     *
     * @return the number of rs contracts
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

                Query q = session.createQuery(_SQL_COUNT_RSCONTRACT);

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
     * Initializes the rs contract persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RsContract")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RsContract>> listenersList = new ArrayList<ModelListener<RsContract>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RsContract>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RsContractImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
