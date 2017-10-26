package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchContractMasterException;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.impl.ContractMasterImpl;
import com.stpl.app.model.impl.ContractMasterModelImpl;
import com.stpl.app.service.persistence.ContractMasterPersistence;

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
 * The persistence implementation for the contract master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractMasterPersistence
 * @see ContractMasterUtil
 * @generated
 */
public class ContractMasterPersistenceImpl extends BasePersistenceImpl<ContractMaster>
    implements ContractMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContractMasterUtil} to access the contract master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContractMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterModelImpl.FINDER_CACHE_ENABLED,
            ContractMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTRACTMASTER = "SELECT contractMaster FROM ContractMaster contractMaster";
    private static final String _SQL_COUNT_CONTRACTMASTER = "SELECT COUNT(contractMaster) FROM ContractMaster contractMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contractMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContractMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "proposalEndDate", "createdDate", "renegotiationEndDate",
                "outsideAdditionalName", "endDate", "manfCompanyMasterSid",
                "renegotiationStartDate", "insideAuthor", "advanceNoticeDays",
                "outsideOwner", "mostFavoredNation", "insideAdditionalPhone",
                "originalStartDate", "createdBy", "proposalStartDate",
                "contractTradeClass", "outsideAdditional", "processStatus",
                "insideAdditionalName", "contractMasterSid", "contractStatus",
                "contractId", "modifiedDate", "contractType", "awardStatus",
                "insideOwner", "source", "shippingTerms",
                "priceEscalationClause", "modifiedBy", "outsideAdditionalPhone",
                "term", "contractNo", "batchId", "documentClass",
                "originalEndDate", "paymentTerms", "insideAdditional",
                "affiliatedContractInfo", "category", "outsidePhone",
                "priceprotectionStartDate", "priceprotectionEndDate",
                "documentType", "exemptFromLowPrice", "organizationKey",
                "currency", "insidePhone", "bunitCompanyMasterSid",
                "outsideAuthor", "contHoldCompanyMasterSid", "startDate",
                "contractName", "lastUpdatedDate", "recordLockStatus",
                "priceResetIndicator", "minimumOrder", "cancellationClause",
                "inboundStatus", "internalNotes"
            });
    private static ContractMaster _nullContractMaster = new ContractMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContractMaster> toCacheModel() {
                return _nullContractMasterCacheModel;
            }
        };

    private static CacheModel<ContractMaster> _nullContractMasterCacheModel = new CacheModel<ContractMaster>() {
            @Override
            public ContractMaster toEntityModel() {
                return _nullContractMaster;
            }
        };

    public ContractMasterPersistenceImpl() {
        setModelClass(ContractMaster.class);
    }

    /**
     * Caches the contract master in the entity cache if it is enabled.
     *
     * @param contractMaster the contract master
     */
    @Override
    public void cacheResult(ContractMaster contractMaster) {
        EntityCacheUtil.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterImpl.class, contractMaster.getPrimaryKey(),
            contractMaster);

        contractMaster.resetOriginalValues();
    }

    /**
     * Caches the contract masters in the entity cache if it is enabled.
     *
     * @param contractMasters the contract masters
     */
    @Override
    public void cacheResult(List<ContractMaster> contractMasters) {
        for (ContractMaster contractMaster : contractMasters) {
            if (EntityCacheUtil.getResult(
                        ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ContractMasterImpl.class, contractMaster.getPrimaryKey()) == null) {
                cacheResult(contractMaster);
            } else {
                contractMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contract masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContractMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContractMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contract master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContractMaster contractMaster) {
        EntityCacheUtil.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterImpl.class, contractMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContractMaster> contractMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContractMaster contractMaster : contractMasters) {
            EntityCacheUtil.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
                ContractMasterImpl.class, contractMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new contract master with the primary key. Does not add the contract master to the database.
     *
     * @param contractMasterSid the primary key for the new contract master
     * @return the new contract master
     */
    @Override
    public ContractMaster create(int contractMasterSid) {
        ContractMaster contractMaster = new ContractMasterImpl();

        contractMaster.setNew(true);
        contractMaster.setPrimaryKey(contractMasterSid);

        return contractMaster;
    }

    /**
     * Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param contractMasterSid the primary key of the contract master
     * @return the contract master that was removed
     * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster remove(int contractMasterSid)
        throws NoSuchContractMasterException, SystemException {
        return remove((Serializable) contractMasterSid);
    }

    /**
     * Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contract master
     * @return the contract master that was removed
     * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster remove(Serializable primaryKey)
        throws NoSuchContractMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContractMaster contractMaster = (ContractMaster) session.get(ContractMasterImpl.class,
                    primaryKey);

            if (contractMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContractMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contractMaster);
        } catch (NoSuchContractMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContractMaster removeImpl(ContractMaster contractMaster)
        throws SystemException {
        contractMaster = toUnwrappedModel(contractMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contractMaster)) {
                contractMaster = (ContractMaster) session.get(ContractMasterImpl.class,
                        contractMaster.getPrimaryKeyObj());
            }

            if (contractMaster != null) {
                session.delete(contractMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contractMaster != null) {
            clearCache(contractMaster);
        }

        return contractMaster;
    }

    @Override
    public ContractMaster updateImpl(
        com.stpl.app.model.ContractMaster contractMaster)
        throws SystemException {
        contractMaster = toUnwrappedModel(contractMaster);

        boolean isNew = contractMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contractMaster.isNew()) {
                session.save(contractMaster);

                contractMaster.setNew(false);
            } else {
                session.merge(contractMaster);
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

        EntityCacheUtil.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
            ContractMasterImpl.class, contractMaster.getPrimaryKey(),
            contractMaster);

        return contractMaster;
    }

    protected ContractMaster toUnwrappedModel(ContractMaster contractMaster) {
        if (contractMaster instanceof ContractMasterImpl) {
            return contractMaster;
        }

        ContractMasterImpl contractMasterImpl = new ContractMasterImpl();

        contractMasterImpl.setNew(contractMaster.isNew());
        contractMasterImpl.setPrimaryKey(contractMaster.getPrimaryKey());

        contractMasterImpl.setProposalEndDate(contractMaster.getProposalEndDate());
        contractMasterImpl.setCreatedDate(contractMaster.getCreatedDate());
        contractMasterImpl.setRenegotiationEndDate(contractMaster.getRenegotiationEndDate());
        contractMasterImpl.setOutsideAdditionalName(contractMaster.getOutsideAdditionalName());
        contractMasterImpl.setEndDate(contractMaster.getEndDate());
        contractMasterImpl.setManfCompanyMasterSid(contractMaster.getManfCompanyMasterSid());
        contractMasterImpl.setRenegotiationStartDate(contractMaster.getRenegotiationStartDate());
        contractMasterImpl.setInsideAuthor(contractMaster.getInsideAuthor());
        contractMasterImpl.setAdvanceNoticeDays(contractMaster.getAdvanceNoticeDays());
        contractMasterImpl.setOutsideOwner(contractMaster.getOutsideOwner());
        contractMasterImpl.setMostFavoredNation(contractMaster.getMostFavoredNation());
        contractMasterImpl.setInsideAdditionalPhone(contractMaster.getInsideAdditionalPhone());
        contractMasterImpl.setOriginalStartDate(contractMaster.getOriginalStartDate());
        contractMasterImpl.setCreatedBy(contractMaster.getCreatedBy());
        contractMasterImpl.setProposalStartDate(contractMaster.getProposalStartDate());
        contractMasterImpl.setContractTradeClass(contractMaster.getContractTradeClass());
        contractMasterImpl.setOutsideAdditional(contractMaster.getOutsideAdditional());
        contractMasterImpl.setProcessStatus(contractMaster.isProcessStatus());
        contractMasterImpl.setInsideAdditionalName(contractMaster.getInsideAdditionalName());
        contractMasterImpl.setContractMasterSid(contractMaster.getContractMasterSid());
        contractMasterImpl.setContractStatus(contractMaster.getContractStatus());
        contractMasterImpl.setContractId(contractMaster.getContractId());
        contractMasterImpl.setModifiedDate(contractMaster.getModifiedDate());
        contractMasterImpl.setContractType(contractMaster.getContractType());
        contractMasterImpl.setAwardStatus(contractMaster.getAwardStatus());
        contractMasterImpl.setInsideOwner(contractMaster.getInsideOwner());
        contractMasterImpl.setSource(contractMaster.getSource());
        contractMasterImpl.setShippingTerms(contractMaster.getShippingTerms());
        contractMasterImpl.setPriceEscalationClause(contractMaster.getPriceEscalationClause());
        contractMasterImpl.setModifiedBy(contractMaster.getModifiedBy());
        contractMasterImpl.setOutsideAdditionalPhone(contractMaster.getOutsideAdditionalPhone());
        contractMasterImpl.setTerm(contractMaster.getTerm());
        contractMasterImpl.setContractNo(contractMaster.getContractNo());
        contractMasterImpl.setBatchId(contractMaster.getBatchId());
        contractMasterImpl.setDocumentClass(contractMaster.getDocumentClass());
        contractMasterImpl.setOriginalEndDate(contractMaster.getOriginalEndDate());
        contractMasterImpl.setPaymentTerms(contractMaster.getPaymentTerms());
        contractMasterImpl.setInsideAdditional(contractMaster.getInsideAdditional());
        contractMasterImpl.setAffiliatedContractInfo(contractMaster.getAffiliatedContractInfo());
        contractMasterImpl.setCategory(contractMaster.getCategory());
        contractMasterImpl.setOutsidePhone(contractMaster.getOutsidePhone());
        contractMasterImpl.setPriceprotectionStartDate(contractMaster.getPriceprotectionStartDate());
        contractMasterImpl.setPriceprotectionEndDate(contractMaster.getPriceprotectionEndDate());
        contractMasterImpl.setDocumentType(contractMaster.getDocumentType());
        contractMasterImpl.setExemptFromLowPrice(contractMaster.getExemptFromLowPrice());
        contractMasterImpl.setOrganizationKey(contractMaster.getOrganizationKey());
        contractMasterImpl.setCurrency(contractMaster.getCurrency());
        contractMasterImpl.setInsidePhone(contractMaster.getInsidePhone());
        contractMasterImpl.setBunitCompanyMasterSid(contractMaster.getBunitCompanyMasterSid());
        contractMasterImpl.setOutsideAuthor(contractMaster.getOutsideAuthor());
        contractMasterImpl.setContHoldCompanyMasterSid(contractMaster.getContHoldCompanyMasterSid());
        contractMasterImpl.setStartDate(contractMaster.getStartDate());
        contractMasterImpl.setContractName(contractMaster.getContractName());
        contractMasterImpl.setLastUpdatedDate(contractMaster.getLastUpdatedDate());
        contractMasterImpl.setRecordLockStatus(contractMaster.isRecordLockStatus());
        contractMasterImpl.setPriceResetIndicator(contractMaster.getPriceResetIndicator());
        contractMasterImpl.setMinimumOrder(contractMaster.getMinimumOrder());
        contractMasterImpl.setCancellationClause(contractMaster.getCancellationClause());
        contractMasterImpl.setInboundStatus(contractMaster.getInboundStatus());
        contractMasterImpl.setInternalNotes(contractMaster.getInternalNotes());

        return contractMasterImpl;
    }

    /**
     * Returns the contract master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contract master
     * @return the contract master
     * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContractMasterException, SystemException {
        ContractMaster contractMaster = fetchByPrimaryKey(primaryKey);

        if (contractMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContractMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contractMaster;
    }

    /**
     * Returns the contract master with the primary key or throws a {@link com.stpl.app.NoSuchContractMasterException} if it could not be found.
     *
     * @param contractMasterSid the primary key of the contract master
     * @return the contract master
     * @throws com.stpl.app.NoSuchContractMasterException if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster findByPrimaryKey(int contractMasterSid)
        throws NoSuchContractMasterException, SystemException {
        return findByPrimaryKey((Serializable) contractMasterSid);
    }

    /**
     * Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contract master
     * @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContractMaster contractMaster = (ContractMaster) EntityCacheUtil.getResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
                ContractMasterImpl.class, primaryKey);

        if (contractMaster == _nullContractMaster) {
            return null;
        }

        if (contractMaster == null) {
            Session session = null;

            try {
                session = openSession();

                contractMaster = (ContractMaster) session.get(ContractMasterImpl.class,
                        primaryKey);

                if (contractMaster != null) {
                    cacheResult(contractMaster);
                } else {
                    EntityCacheUtil.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ContractMasterImpl.class, primaryKey,
                        _nullContractMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ContractMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contractMaster;
    }

    /**
     * Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param contractMasterSid the primary key of the contract master
     * @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContractMaster fetchByPrimaryKey(int contractMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) contractMasterSid);
    }

    /**
     * Returns all the contract masters.
     *
     * @return the contract masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contract masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contract masters
     * @param end the upper bound of the range of contract masters (not inclusive)
     * @return the range of contract masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contract masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contract masters
     * @param end the upper bound of the range of contract masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contract masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContractMaster> findAll(int start, int end,
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

        List<ContractMaster> list = (List<ContractMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTRACTMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTRACTMASTER;

                if (pagination) {
                    sql = sql.concat(ContractMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContractMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContractMaster>(list);
                } else {
                    list = (List<ContractMaster>) QueryUtil.list(q,
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
     * Removes all the contract masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContractMaster contractMaster : findAll()) {
            remove(contractMaster);
        }
    }

    /**
     * Returns the number of contract masters.
     *
     * @return the number of contract masters
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

                Query q = session.createQuery(_SQL_COUNT_CONTRACTMASTER);

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
     * Initializes the contract master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ContractMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContractMaster>> listenersList = new ArrayList<ModelListener<ContractMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContractMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContractMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
