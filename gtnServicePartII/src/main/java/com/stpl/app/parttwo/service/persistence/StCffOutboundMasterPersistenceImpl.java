package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchStCffOutboundMasterException;
import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl;
import com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPersistence;

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
 * The persistence implementation for the st cff outbound master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StCffOutboundMasterPersistence
 * @see StCffOutboundMasterUtil
 * @generated
 */
public class StCffOutboundMasterPersistenceImpl extends BasePersistenceImpl<StCffOutboundMaster>
    implements StCffOutboundMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StCffOutboundMasterUtil} to access the st cff outbound master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StCffOutboundMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED,
            StCffOutboundMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED,
            StCffOutboundMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STCFFOUTBOUNDMASTER = "SELECT stCffOutboundMaster FROM StCffOutboundMaster stCffOutboundMaster";
    private static final String _SQL_COUNT_STCFFOUTBOUNDMASTER = "SELECT COUNT(stCffOutboundMaster) FROM StCffOutboundMaster stCffOutboundMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stCffOutboundMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StCffOutboundMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StCffOutboundMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "etlCheckRecord", "customerName", "contractHolderId",
                "businessUnitNo", "year", "financialForecastApprovalDate",
                "deductionId", "modifiedDate", "deductionPerUnit", "cogsPerUnit",
                "contractType", "source", "businessUnitName",
                "contractMasterSid", "financialForecastId", "projectId",
                "customerNo", "modifiedBy", "salesDollars", "month",
                "cffDetailsSid", "type", "deductionType", "companyMasterSid",
                "checkRecord", "contractName", "deductionRate",
                "deductionCategory", "cogsAmount", "deductionNo",
                "financialForecastCreationDate", "companyNo", "salesUnits",
                "sessionId", "itemName", "deductionInclusion", "rsModelSid",
                "contractHolderName", "itemMasterSid", "companyName",
                "customerId", "itemId", "netProfitDollars", "glCompanyMasterSid",
                "createdDate", "createdBy", "deductionCategory1",
                "deductionCategory2", "contractHolderNo", "deductionCategory3",
                "itemNo", "deductionCategory4", "deductionCategory5",
                "deductionCategory6", "contractId", "deductionProgram",
                "businessUnitId", "projectionName", "userId", "companyId",
                "outboundStatus", "originalBatchId", "deductionName",
                "netProfitPerUnit", "periodSid", "salesInclusion", "batchId",
                "financialForecastName", "netSalesDollar", "deductionDollars",
                "contractNo"
            });
    private static StCffOutboundMaster _nullStCffOutboundMaster = new StCffOutboundMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StCffOutboundMaster> toCacheModel() {
                return _nullStCffOutboundMasterCacheModel;
            }
        };

    private static CacheModel<StCffOutboundMaster> _nullStCffOutboundMasterCacheModel =
        new CacheModel<StCffOutboundMaster>() {
            @Override
            public StCffOutboundMaster toEntityModel() {
                return _nullStCffOutboundMaster;
            }
        };

    public StCffOutboundMasterPersistenceImpl() {
        setModelClass(StCffOutboundMaster.class);
    }

    /**
     * Caches the st cff outbound master in the entity cache if it is enabled.
     *
     * @param stCffOutboundMaster the st cff outbound master
     */
    @Override
    public void cacheResult(StCffOutboundMaster stCffOutboundMaster) {
        EntityCacheUtil.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey(),
            stCffOutboundMaster);

        stCffOutboundMaster.resetOriginalValues();
    }

    /**
     * Caches the st cff outbound masters in the entity cache if it is enabled.
     *
     * @param stCffOutboundMasters the st cff outbound masters
     */
    @Override
    public void cacheResult(List<StCffOutboundMaster> stCffOutboundMasters) {
        for (StCffOutboundMaster stCffOutboundMaster : stCffOutboundMasters) {
            if (EntityCacheUtil.getResult(
                        StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StCffOutboundMasterImpl.class,
                        stCffOutboundMaster.getPrimaryKey()) == null) {
                cacheResult(stCffOutboundMaster);
            } else {
                stCffOutboundMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st cff outbound masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StCffOutboundMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StCffOutboundMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st cff outbound master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StCffOutboundMaster stCffOutboundMaster) {
        EntityCacheUtil.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StCffOutboundMaster> stCffOutboundMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StCffOutboundMaster stCffOutboundMaster : stCffOutboundMasters) {
            EntityCacheUtil.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
                StCffOutboundMasterImpl.class,
                stCffOutboundMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
     *
     * @param stCffOutboundMasterPK the primary key for the new st cff outbound master
     * @return the new st cff outbound master
     */
    @Override
    public StCffOutboundMaster create(
        StCffOutboundMasterPK stCffOutboundMasterPK) {
        StCffOutboundMaster stCffOutboundMaster = new StCffOutboundMasterImpl();

        stCffOutboundMaster.setNew(true);
        stCffOutboundMaster.setPrimaryKey(stCffOutboundMasterPK);

        return stCffOutboundMaster;
    }

    /**
     * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stCffOutboundMasterPK the primary key of the st cff outbound master
     * @return the st cff outbound master that was removed
     * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster remove(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws NoSuchStCffOutboundMasterException, SystemException {
        return remove((Serializable) stCffOutboundMasterPK);
    }

    /**
     * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st cff outbound master
     * @return the st cff outbound master that was removed
     * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster remove(Serializable primaryKey)
        throws NoSuchStCffOutboundMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StCffOutboundMaster stCffOutboundMaster = (StCffOutboundMaster) session.get(StCffOutboundMasterImpl.class,
                    primaryKey);

            if (stCffOutboundMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStCffOutboundMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stCffOutboundMaster);
        } catch (NoSuchStCffOutboundMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StCffOutboundMaster removeImpl(
        StCffOutboundMaster stCffOutboundMaster) throws SystemException {
        stCffOutboundMaster = toUnwrappedModel(stCffOutboundMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stCffOutboundMaster)) {
                stCffOutboundMaster = (StCffOutboundMaster) session.get(StCffOutboundMasterImpl.class,
                        stCffOutboundMaster.getPrimaryKeyObj());
            }

            if (stCffOutboundMaster != null) {
                session.delete(stCffOutboundMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stCffOutboundMaster != null) {
            clearCache(stCffOutboundMaster);
        }

        return stCffOutboundMaster;
    }

    @Override
    public StCffOutboundMaster updateImpl(
        com.stpl.app.parttwo.model.StCffOutboundMaster stCffOutboundMaster)
        throws SystemException {
        stCffOutboundMaster = toUnwrappedModel(stCffOutboundMaster);

        boolean isNew = stCffOutboundMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stCffOutboundMaster.isNew()) {
                session.save(stCffOutboundMaster);

                stCffOutboundMaster.setNew(false);
            } else {
                session.merge(stCffOutboundMaster);
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

        EntityCacheUtil.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
            StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey(),
            stCffOutboundMaster);

        return stCffOutboundMaster;
    }

    protected StCffOutboundMaster toUnwrappedModel(
        StCffOutboundMaster stCffOutboundMaster) {
        if (stCffOutboundMaster instanceof StCffOutboundMasterImpl) {
            return stCffOutboundMaster;
        }

        StCffOutboundMasterImpl stCffOutboundMasterImpl = new StCffOutboundMasterImpl();

        stCffOutboundMasterImpl.setNew(stCffOutboundMaster.isNew());
        stCffOutboundMasterImpl.setPrimaryKey(stCffOutboundMaster.getPrimaryKey());

        stCffOutboundMasterImpl.setEtlCheckRecord(stCffOutboundMaster.isEtlCheckRecord());
        stCffOutboundMasterImpl.setCustomerName(stCffOutboundMaster.getCustomerName());
        stCffOutboundMasterImpl.setContractHolderId(stCffOutboundMaster.getContractHolderId());
        stCffOutboundMasterImpl.setBusinessUnitNo(stCffOutboundMaster.getBusinessUnitNo());
        stCffOutboundMasterImpl.setYear(stCffOutboundMaster.getYear());
        stCffOutboundMasterImpl.setFinancialForecastApprovalDate(stCffOutboundMaster.getFinancialForecastApprovalDate());
        stCffOutboundMasterImpl.setDeductionId(stCffOutboundMaster.getDeductionId());
        stCffOutboundMasterImpl.setModifiedDate(stCffOutboundMaster.getModifiedDate());
        stCffOutboundMasterImpl.setDeductionPerUnit(stCffOutboundMaster.getDeductionPerUnit());
        stCffOutboundMasterImpl.setCogsPerUnit(stCffOutboundMaster.getCogsPerUnit());
        stCffOutboundMasterImpl.setContractType(stCffOutboundMaster.getContractType());
        stCffOutboundMasterImpl.setSource(stCffOutboundMaster.getSource());
        stCffOutboundMasterImpl.setBusinessUnitName(stCffOutboundMaster.getBusinessUnitName());
        stCffOutboundMasterImpl.setContractMasterSid(stCffOutboundMaster.getContractMasterSid());
        stCffOutboundMasterImpl.setFinancialForecastId(stCffOutboundMaster.getFinancialForecastId());
        stCffOutboundMasterImpl.setProjectId(stCffOutboundMaster.getProjectId());
        stCffOutboundMasterImpl.setCustomerNo(stCffOutboundMaster.getCustomerNo());
        stCffOutboundMasterImpl.setModifiedBy(stCffOutboundMaster.getModifiedBy());
        stCffOutboundMasterImpl.setSalesDollars(stCffOutboundMaster.getSalesDollars());
        stCffOutboundMasterImpl.setMonth(stCffOutboundMaster.getMonth());
        stCffOutboundMasterImpl.setCffDetailsSid(stCffOutboundMaster.getCffDetailsSid());
        stCffOutboundMasterImpl.setType(stCffOutboundMaster.getType());
        stCffOutboundMasterImpl.setDeductionType(stCffOutboundMaster.getDeductionType());
        stCffOutboundMasterImpl.setCompanyMasterSid(stCffOutboundMaster.getCompanyMasterSid());
        stCffOutboundMasterImpl.setCheckRecord(stCffOutboundMaster.isCheckRecord());
        stCffOutboundMasterImpl.setContractName(stCffOutboundMaster.getContractName());
        stCffOutboundMasterImpl.setDeductionRate(stCffOutboundMaster.getDeductionRate());
        stCffOutboundMasterImpl.setDeductionCategory(stCffOutboundMaster.getDeductionCategory());
        stCffOutboundMasterImpl.setCogsAmount(stCffOutboundMaster.getCogsAmount());
        stCffOutboundMasterImpl.setDeductionNo(stCffOutboundMaster.getDeductionNo());
        stCffOutboundMasterImpl.setFinancialForecastCreationDate(stCffOutboundMaster.getFinancialForecastCreationDate());
        stCffOutboundMasterImpl.setCompanyNo(stCffOutboundMaster.getCompanyNo());
        stCffOutboundMasterImpl.setSalesUnits(stCffOutboundMaster.getSalesUnits());
        stCffOutboundMasterImpl.setSessionId(stCffOutboundMaster.getSessionId());
        stCffOutboundMasterImpl.setItemName(stCffOutboundMaster.getItemName());
        stCffOutboundMasterImpl.setDeductionInclusion(stCffOutboundMaster.getDeductionInclusion());
        stCffOutboundMasterImpl.setRsModelSid(stCffOutboundMaster.getRsModelSid());
        stCffOutboundMasterImpl.setContractHolderName(stCffOutboundMaster.getContractHolderName());
        stCffOutboundMasterImpl.setItemMasterSid(stCffOutboundMaster.getItemMasterSid());
        stCffOutboundMasterImpl.setCompanyName(stCffOutboundMaster.getCompanyName());
        stCffOutboundMasterImpl.setCustomerId(stCffOutboundMaster.getCustomerId());
        stCffOutboundMasterImpl.setItemId(stCffOutboundMaster.getItemId());
        stCffOutboundMasterImpl.setNetProfitDollars(stCffOutboundMaster.getNetProfitDollars());
        stCffOutboundMasterImpl.setGlCompanyMasterSid(stCffOutboundMaster.getGlCompanyMasterSid());
        stCffOutboundMasterImpl.setCreatedDate(stCffOutboundMaster.getCreatedDate());
        stCffOutboundMasterImpl.setCreatedBy(stCffOutboundMaster.getCreatedBy());
        stCffOutboundMasterImpl.setDeductionCategory1(stCffOutboundMaster.getDeductionCategory1());
        stCffOutboundMasterImpl.setDeductionCategory2(stCffOutboundMaster.getDeductionCategory2());
        stCffOutboundMasterImpl.setContractHolderNo(stCffOutboundMaster.getContractHolderNo());
        stCffOutboundMasterImpl.setDeductionCategory3(stCffOutboundMaster.getDeductionCategory3());
        stCffOutboundMasterImpl.setItemNo(stCffOutboundMaster.getItemNo());
        stCffOutboundMasterImpl.setDeductionCategory4(stCffOutboundMaster.getDeductionCategory4());
        stCffOutboundMasterImpl.setDeductionCategory5(stCffOutboundMaster.getDeductionCategory5());
        stCffOutboundMasterImpl.setDeductionCategory6(stCffOutboundMaster.getDeductionCategory6());
        stCffOutboundMasterImpl.setContractId(stCffOutboundMaster.getContractId());
        stCffOutboundMasterImpl.setDeductionProgram(stCffOutboundMaster.getDeductionProgram());
        stCffOutboundMasterImpl.setBusinessUnitId(stCffOutboundMaster.getBusinessUnitId());
        stCffOutboundMasterImpl.setProjectionName(stCffOutboundMaster.getProjectionName());
        stCffOutboundMasterImpl.setUserId(stCffOutboundMaster.getUserId());
        stCffOutboundMasterImpl.setCompanyId(stCffOutboundMaster.getCompanyId());
        stCffOutboundMasterImpl.setOutboundStatus(stCffOutboundMaster.getOutboundStatus());
        stCffOutboundMasterImpl.setOriginalBatchId(stCffOutboundMaster.getOriginalBatchId());
        stCffOutboundMasterImpl.setDeductionName(stCffOutboundMaster.getDeductionName());
        stCffOutboundMasterImpl.setNetProfitPerUnit(stCffOutboundMaster.getNetProfitPerUnit());
        stCffOutboundMasterImpl.setPeriodSid(stCffOutboundMaster.getPeriodSid());
        stCffOutboundMasterImpl.setSalesInclusion(stCffOutboundMaster.getSalesInclusion());
        stCffOutboundMasterImpl.setBatchId(stCffOutboundMaster.getBatchId());
        stCffOutboundMasterImpl.setFinancialForecastName(stCffOutboundMaster.getFinancialForecastName());
        stCffOutboundMasterImpl.setNetSalesDollar(stCffOutboundMaster.getNetSalesDollar());
        stCffOutboundMasterImpl.setDeductionDollars(stCffOutboundMaster.getDeductionDollars());
        stCffOutboundMasterImpl.setContractNo(stCffOutboundMaster.getContractNo());

        return stCffOutboundMasterImpl;
    }

    /**
     * Returns the st cff outbound master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st cff outbound master
     * @return the st cff outbound master
     * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStCffOutboundMasterException, SystemException {
        StCffOutboundMaster stCffOutboundMaster = fetchByPrimaryKey(primaryKey);

        if (stCffOutboundMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStCffOutboundMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stCffOutboundMaster;
    }

    /**
     * Returns the st cff outbound master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStCffOutboundMasterException} if it could not be found.
     *
     * @param stCffOutboundMasterPK the primary key of the st cff outbound master
     * @return the st cff outbound master
     * @throws com.stpl.app.parttwo.NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster findByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK)
        throws NoSuchStCffOutboundMasterException, SystemException {
        return findByPrimaryKey((Serializable) stCffOutboundMasterPK);
    }

    /**
     * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st cff outbound master
     * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StCffOutboundMaster stCffOutboundMaster = (StCffOutboundMaster) EntityCacheUtil.getResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
                StCffOutboundMasterImpl.class, primaryKey);

        if (stCffOutboundMaster == _nullStCffOutboundMaster) {
            return null;
        }

        if (stCffOutboundMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stCffOutboundMaster = (StCffOutboundMaster) session.get(StCffOutboundMasterImpl.class,
                        primaryKey);

                if (stCffOutboundMaster != null) {
                    cacheResult(stCffOutboundMaster);
                } else {
                    EntityCacheUtil.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StCffOutboundMasterImpl.class, primaryKey,
                        _nullStCffOutboundMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StCffOutboundMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stCffOutboundMaster;
    }

    /**
     * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stCffOutboundMasterPK the primary key of the st cff outbound master
     * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StCffOutboundMaster fetchByPrimaryKey(
        StCffOutboundMasterPK stCffOutboundMasterPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stCffOutboundMasterPK);
    }

    /**
     * Returns all the st cff outbound masters.
     *
     * @return the st cff outbound masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StCffOutboundMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st cff outbound masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st cff outbound masters
     * @param end the upper bound of the range of st cff outbound masters (not inclusive)
     * @return the range of st cff outbound masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StCffOutboundMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st cff outbound masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st cff outbound masters
     * @param end the upper bound of the range of st cff outbound masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st cff outbound masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StCffOutboundMaster> findAll(int start, int end,
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

        List<StCffOutboundMaster> list = (List<StCffOutboundMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCFFOUTBOUNDMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCFFOUTBOUNDMASTER;

                if (pagination) {
                    sql = sql.concat(StCffOutboundMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StCffOutboundMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StCffOutboundMaster>(list);
                } else {
                    list = (List<StCffOutboundMaster>) QueryUtil.list(q,
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
     * Removes all the st cff outbound masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StCffOutboundMaster stCffOutboundMaster : findAll()) {
            remove(stCffOutboundMaster);
        }
    }

    /**
     * Returns the number of st cff outbound masters.
     *
     * @return the number of st cff outbound masters
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

                Query q = session.createQuery(_SQL_COUNT_STCFFOUTBOUNDMASTER);

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
     * Initializes the st cff outbound master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.StCffOutboundMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StCffOutboundMaster>> listenersList = new ArrayList<ModelListener<StCffOutboundMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StCffOutboundMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StCffOutboundMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
