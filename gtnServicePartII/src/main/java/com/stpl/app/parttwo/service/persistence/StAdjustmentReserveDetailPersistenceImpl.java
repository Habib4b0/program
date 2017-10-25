package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;
import com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl;
import com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPersistence;

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
 * The persistence implementation for the st adjustment reserve detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetailPersistence
 * @see StAdjustmentReserveDetailUtil
 * @generated
 */
public class StAdjustmentReserveDetailPersistenceImpl
    extends BasePersistenceImpl<StAdjustmentReserveDetail>
    implements StAdjustmentReserveDetailPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StAdjustmentReserveDetailUtil} to access the st adjustment reserve detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StAdjustmentReserveDetailImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
            StAdjustmentReserveDetailImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
            StAdjustmentReserveDetailImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_STADJUSTMENTRESERVEDETAIL = "SELECT stAdjustmentReserveDetail FROM StAdjustmentReserveDetail stAdjustmentReserveDetail";
    private static final String _SQL_COUNT_STADJUSTMENTRESERVEDETAIL = "SELECT COUNT(stAdjustmentReserveDetail) FROM StAdjustmentReserveDetail stAdjustmentReserveDetail";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stAdjustmentReserveDetail.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAdjustmentReserveDetail exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StAdjustmentReserveDetailPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentCreatedDate", "etlCheckRecord", "postingIndicator",
                "modifiedDate", "account", "credit", "workflowApprovedDate",
                "source", "lineDescription", "ledger", "udc6", "udc5", "udc4",
                "workflowCreatedDate", "udc3", "udc2", "udc1", "adjustmentType",
                "modifiedBy", "checkRecord", "glCompanyName", "division",
                "balanceType", "sessionId", "journalName", "project", "debit",
                "accountType", "journalDescription", "category", "createdBy",
                "createdDate", "businessUnitId", "reversalPeriodDate",
                "workflowId", "chartOfAccounts", "userId", "batchName",
                "database", "costCenter", "outboundStatus", "dataAccessSet",
                "future1", "future2", "workflowName", "workflowCreatedBy",
                "currency", "batchId", "accountCategory", "reverseJournal",
                "workflowApprovedBy", "brand", "accountingDate",
                "redemptionPeriod", "originalBatchId", "adjustmentLevel"
            });
    private static StAdjustmentReserveDetail _nullStAdjustmentReserveDetail = new StAdjustmentReserveDetailImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StAdjustmentReserveDetail> toCacheModel() {
                return _nullStAdjustmentReserveDetailCacheModel;
            }
        };

    private static CacheModel<StAdjustmentReserveDetail> _nullStAdjustmentReserveDetailCacheModel =
        new CacheModel<StAdjustmentReserveDetail>() {
            @Override
            public StAdjustmentReserveDetail toEntityModel() {
                return _nullStAdjustmentReserveDetail;
            }
        };

    public StAdjustmentReserveDetailPersistenceImpl() {
        setModelClass(StAdjustmentReserveDetail.class);
    }

    /**
     * Caches the st adjustment reserve detail in the entity cache if it is enabled.
     *
     * @param stAdjustmentReserveDetail the st adjustment reserve detail
     */
    @Override
    public void cacheResult(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        EntityCacheUtil.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailImpl.class,
            stAdjustmentReserveDetail.getPrimaryKey(), stAdjustmentReserveDetail);

        stAdjustmentReserveDetail.resetOriginalValues();
    }

    /**
     * Caches the st adjustment reserve details in the entity cache if it is enabled.
     *
     * @param stAdjustmentReserveDetails the st adjustment reserve details
     */
    @Override
    public void cacheResult(
        List<StAdjustmentReserveDetail> stAdjustmentReserveDetails) {
        for (StAdjustmentReserveDetail stAdjustmentReserveDetail : stAdjustmentReserveDetails) {
            if (EntityCacheUtil.getResult(
                        StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
                        StAdjustmentReserveDetailImpl.class,
                        stAdjustmentReserveDetail.getPrimaryKey()) == null) {
                cacheResult(stAdjustmentReserveDetail);
            } else {
                stAdjustmentReserveDetail.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st adjustment reserve details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StAdjustmentReserveDetailImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StAdjustmentReserveDetailImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st adjustment reserve detail.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        EntityCacheUtil.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailImpl.class,
            stAdjustmentReserveDetail.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StAdjustmentReserveDetail> stAdjustmentReserveDetails) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StAdjustmentReserveDetail stAdjustmentReserveDetail : stAdjustmentReserveDetails) {
            EntityCacheUtil.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
                StAdjustmentReserveDetailImpl.class,
                stAdjustmentReserveDetail.getPrimaryKey());
        }
    }

    /**
     * Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
     *
     * @param stAdjustmentReserveDetailPK the primary key for the new st adjustment reserve detail
     * @return the new st adjustment reserve detail
     */
    @Override
    public StAdjustmentReserveDetail create(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK) {
        StAdjustmentReserveDetail stAdjustmentReserveDetail = new StAdjustmentReserveDetailImpl();

        stAdjustmentReserveDetail.setNew(true);
        stAdjustmentReserveDetail.setPrimaryKey(stAdjustmentReserveDetailPK);

        return stAdjustmentReserveDetail;
    }

    /**
     * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail remove(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws NoSuchStAdjustmentReserveDetailException, SystemException {
        return remove((Serializable) stAdjustmentReserveDetailPK);
    }

    /**
     * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail remove(Serializable primaryKey)
        throws NoSuchStAdjustmentReserveDetailException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StAdjustmentReserveDetail stAdjustmentReserveDetail = (StAdjustmentReserveDetail) session.get(StAdjustmentReserveDetailImpl.class,
                    primaryKey);

            if (stAdjustmentReserveDetail == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStAdjustmentReserveDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stAdjustmentReserveDetail);
        } catch (NoSuchStAdjustmentReserveDetailException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StAdjustmentReserveDetail removeImpl(
        StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws SystemException {
        stAdjustmentReserveDetail = toUnwrappedModel(stAdjustmentReserveDetail);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stAdjustmentReserveDetail)) {
                stAdjustmentReserveDetail = (StAdjustmentReserveDetail) session.get(StAdjustmentReserveDetailImpl.class,
                        stAdjustmentReserveDetail.getPrimaryKeyObj());
            }

            if (stAdjustmentReserveDetail != null) {
                session.delete(stAdjustmentReserveDetail);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stAdjustmentReserveDetail != null) {
            clearCache(stAdjustmentReserveDetail);
        }

        return stAdjustmentReserveDetail;
    }

    @Override
    public StAdjustmentReserveDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentReserveDetail stAdjustmentReserveDetail)
        throws SystemException {
        stAdjustmentReserveDetail = toUnwrappedModel(stAdjustmentReserveDetail);

        boolean isNew = stAdjustmentReserveDetail.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stAdjustmentReserveDetail.isNew()) {
                session.save(stAdjustmentReserveDetail);

                stAdjustmentReserveDetail.setNew(false);
            } else {
                session.merge(stAdjustmentReserveDetail);
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

        EntityCacheUtil.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentReserveDetailImpl.class,
            stAdjustmentReserveDetail.getPrimaryKey(), stAdjustmentReserveDetail);

        return stAdjustmentReserveDetail;
    }

    protected StAdjustmentReserveDetail toUnwrappedModel(
        StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        if (stAdjustmentReserveDetail instanceof StAdjustmentReserveDetailImpl) {
            return stAdjustmentReserveDetail;
        }

        StAdjustmentReserveDetailImpl stAdjustmentReserveDetailImpl = new StAdjustmentReserveDetailImpl();

        stAdjustmentReserveDetailImpl.setNew(stAdjustmentReserveDetail.isNew());
        stAdjustmentReserveDetailImpl.setPrimaryKey(stAdjustmentReserveDetail.getPrimaryKey());

        stAdjustmentReserveDetailImpl.setAdjustmentCreatedDate(stAdjustmentReserveDetail.getAdjustmentCreatedDate());
        stAdjustmentReserveDetailImpl.setEtlCheckRecord(stAdjustmentReserveDetail.isEtlCheckRecord());
        stAdjustmentReserveDetailImpl.setPostingIndicator(stAdjustmentReserveDetail.getPostingIndicator());
        stAdjustmentReserveDetailImpl.setModifiedDate(stAdjustmentReserveDetail.getModifiedDate());
        stAdjustmentReserveDetailImpl.setAccount(stAdjustmentReserveDetail.getAccount());
        stAdjustmentReserveDetailImpl.setCredit(stAdjustmentReserveDetail.getCredit());
        stAdjustmentReserveDetailImpl.setWorkflowApprovedDate(stAdjustmentReserveDetail.getWorkflowApprovedDate());
        stAdjustmentReserveDetailImpl.setSource(stAdjustmentReserveDetail.getSource());
        stAdjustmentReserveDetailImpl.setLineDescription(stAdjustmentReserveDetail.getLineDescription());
        stAdjustmentReserveDetailImpl.setLedger(stAdjustmentReserveDetail.getLedger());
        stAdjustmentReserveDetailImpl.setUdc6(stAdjustmentReserveDetail.getUdc6());
        stAdjustmentReserveDetailImpl.setUdc5(stAdjustmentReserveDetail.getUdc5());
        stAdjustmentReserveDetailImpl.setUdc4(stAdjustmentReserveDetail.getUdc4());
        stAdjustmentReserveDetailImpl.setWorkflowCreatedDate(stAdjustmentReserveDetail.getWorkflowCreatedDate());
        stAdjustmentReserveDetailImpl.setUdc3(stAdjustmentReserveDetail.getUdc3());
        stAdjustmentReserveDetailImpl.setUdc2(stAdjustmentReserveDetail.getUdc2());
        stAdjustmentReserveDetailImpl.setUdc1(stAdjustmentReserveDetail.getUdc1());
        stAdjustmentReserveDetailImpl.setAdjustmentType(stAdjustmentReserveDetail.getAdjustmentType());
        stAdjustmentReserveDetailImpl.setModifiedBy(stAdjustmentReserveDetail.getModifiedBy());
        stAdjustmentReserveDetailImpl.setCheckRecord(stAdjustmentReserveDetail.isCheckRecord());
        stAdjustmentReserveDetailImpl.setGlCompanyName(stAdjustmentReserveDetail.getGlCompanyName());
        stAdjustmentReserveDetailImpl.setDivision(stAdjustmentReserveDetail.getDivision());
        stAdjustmentReserveDetailImpl.setBalanceType(stAdjustmentReserveDetail.getBalanceType());
        stAdjustmentReserveDetailImpl.setSessionId(stAdjustmentReserveDetail.getSessionId());
        stAdjustmentReserveDetailImpl.setJournalName(stAdjustmentReserveDetail.getJournalName());
        stAdjustmentReserveDetailImpl.setProject(stAdjustmentReserveDetail.getProject());
        stAdjustmentReserveDetailImpl.setDebit(stAdjustmentReserveDetail.getDebit());
        stAdjustmentReserveDetailImpl.setAccountType(stAdjustmentReserveDetail.getAccountType());
        stAdjustmentReserveDetailImpl.setJournalDescription(stAdjustmentReserveDetail.getJournalDescription());
        stAdjustmentReserveDetailImpl.setCategory(stAdjustmentReserveDetail.getCategory());
        stAdjustmentReserveDetailImpl.setCreatedBy(stAdjustmentReserveDetail.getCreatedBy());
        stAdjustmentReserveDetailImpl.setCreatedDate(stAdjustmentReserveDetail.getCreatedDate());
        stAdjustmentReserveDetailImpl.setBusinessUnitId(stAdjustmentReserveDetail.getBusinessUnitId());
        stAdjustmentReserveDetailImpl.setReversalPeriodDate(stAdjustmentReserveDetail.getReversalPeriodDate());
        stAdjustmentReserveDetailImpl.setWorkflowId(stAdjustmentReserveDetail.getWorkflowId());
        stAdjustmentReserveDetailImpl.setChartOfAccounts(stAdjustmentReserveDetail.getChartOfAccounts());
        stAdjustmentReserveDetailImpl.setUserId(stAdjustmentReserveDetail.getUserId());
        stAdjustmentReserveDetailImpl.setBatchName(stAdjustmentReserveDetail.getBatchName());
        stAdjustmentReserveDetailImpl.setDatabase(stAdjustmentReserveDetail.getDatabase());
        stAdjustmentReserveDetailImpl.setCostCenter(stAdjustmentReserveDetail.getCostCenter());
        stAdjustmentReserveDetailImpl.setOutboundStatus(stAdjustmentReserveDetail.getOutboundStatus());
        stAdjustmentReserveDetailImpl.setDataAccessSet(stAdjustmentReserveDetail.getDataAccessSet());
        stAdjustmentReserveDetailImpl.setFuture1(stAdjustmentReserveDetail.getFuture1());
        stAdjustmentReserveDetailImpl.setFuture2(stAdjustmentReserveDetail.getFuture2());
        stAdjustmentReserveDetailImpl.setWorkflowName(stAdjustmentReserveDetail.getWorkflowName());
        stAdjustmentReserveDetailImpl.setWorkflowCreatedBy(stAdjustmentReserveDetail.getWorkflowCreatedBy());
        stAdjustmentReserveDetailImpl.setCurrency(stAdjustmentReserveDetail.getCurrency());
        stAdjustmentReserveDetailImpl.setBatchId(stAdjustmentReserveDetail.getBatchId());
        stAdjustmentReserveDetailImpl.setAccountCategory(stAdjustmentReserveDetail.getAccountCategory());
        stAdjustmentReserveDetailImpl.setReverseJournal(stAdjustmentReserveDetail.getReverseJournal());
        stAdjustmentReserveDetailImpl.setWorkflowApprovedBy(stAdjustmentReserveDetail.getWorkflowApprovedBy());
        stAdjustmentReserveDetailImpl.setBrand(stAdjustmentReserveDetail.getBrand());
        stAdjustmentReserveDetailImpl.setAccountingDate(stAdjustmentReserveDetail.getAccountingDate());
        stAdjustmentReserveDetailImpl.setRedemptionPeriod(stAdjustmentReserveDetail.getRedemptionPeriod());
        stAdjustmentReserveDetailImpl.setOriginalBatchId(stAdjustmentReserveDetail.getOriginalBatchId());
        stAdjustmentReserveDetailImpl.setAdjustmentLevel(stAdjustmentReserveDetail.getAdjustmentLevel());

        return stAdjustmentReserveDetailImpl;
    }

    /**
     * Returns the st adjustment reserve detail with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStAdjustmentReserveDetailException, SystemException {
        StAdjustmentReserveDetail stAdjustmentReserveDetail = fetchByPrimaryKey(primaryKey);

        if (stAdjustmentReserveDetail == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStAdjustmentReserveDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stAdjustmentReserveDetail;
    }

    /**
     * Returns the st adjustment reserve detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException} if it could not be found.
     *
     * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail findByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws NoSuchStAdjustmentReserveDetailException, SystemException {
        return findByPrimaryKey((Serializable) stAdjustmentReserveDetailPK);
    }

    /**
     * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StAdjustmentReserveDetail stAdjustmentReserveDetail = (StAdjustmentReserveDetail) EntityCacheUtil.getResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
                StAdjustmentReserveDetailImpl.class, primaryKey);

        if (stAdjustmentReserveDetail == _nullStAdjustmentReserveDetail) {
            return null;
        }

        if (stAdjustmentReserveDetail == null) {
            Session session = null;

            try {
                session = openSession();

                stAdjustmentReserveDetail = (StAdjustmentReserveDetail) session.get(StAdjustmentReserveDetailImpl.class,
                        primaryKey);

                if (stAdjustmentReserveDetail != null) {
                    cacheResult(stAdjustmentReserveDetail);
                } else {
                    EntityCacheUtil.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
                        StAdjustmentReserveDetailImpl.class, primaryKey,
                        _nullStAdjustmentReserveDetail);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
                    StAdjustmentReserveDetailImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stAdjustmentReserveDetail;
    }

    /**
     * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stAdjustmentReserveDetailPK the primary key of the st adjustment reserve detail
     * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentReserveDetail fetchByPrimaryKey(
        StAdjustmentReserveDetailPK stAdjustmentReserveDetailPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stAdjustmentReserveDetailPK);
    }

    /**
     * Returns all the st adjustment reserve details.
     *
     * @return the st adjustment reserve details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentReserveDetail> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st adjustment reserve details.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st adjustment reserve details
     * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
     * @return the range of st adjustment reserve details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentReserveDetail> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st adjustment reserve details.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st adjustment reserve details
     * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st adjustment reserve details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentReserveDetail> findAll(int start, int end,
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

        List<StAdjustmentReserveDetail> list = (List<StAdjustmentReserveDetail>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STADJUSTMENTRESERVEDETAIL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STADJUSTMENTRESERVEDETAIL;

                if (pagination) {
                    sql = sql.concat(StAdjustmentReserveDetailModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StAdjustmentReserveDetail>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StAdjustmentReserveDetail>(list);
                } else {
                    list = (List<StAdjustmentReserveDetail>) QueryUtil.list(q,
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
     * Removes all the st adjustment reserve details from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StAdjustmentReserveDetail stAdjustmentReserveDetail : findAll()) {
            remove(stAdjustmentReserveDetail);
        }
    }

    /**
     * Returns the number of st adjustment reserve details.
     *
     * @return the number of st adjustment reserve details
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

                Query q = session.createQuery(_SQL_COUNT_STADJUSTMENTRESERVEDETAIL);

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
     * Initializes the st adjustment reserve detail persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.StAdjustmentReserveDetail")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StAdjustmentReserveDetail>> listenersList = new ArrayList<ModelListener<StAdjustmentReserveDetail>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StAdjustmentReserveDetail>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StAdjustmentReserveDetailImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
