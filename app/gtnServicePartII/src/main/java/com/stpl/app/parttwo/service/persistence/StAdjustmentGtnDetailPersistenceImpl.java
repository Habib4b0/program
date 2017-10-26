package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;
import com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl;
import com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPersistence;

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
 * The persistence implementation for the st adjustment gtn detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailPersistence
 * @see StAdjustmentGtnDetailUtil
 * @generated
 */
public class StAdjustmentGtnDetailPersistenceImpl extends BasePersistenceImpl<StAdjustmentGtnDetail>
    implements StAdjustmentGtnDetailPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StAdjustmentGtnDetailUtil} to access the st adjustment gtn detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StAdjustmentGtnDetailImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED,
            StAdjustmentGtnDetailImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED,
            StAdjustmentGtnDetailImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STADJUSTMENTGTNDETAIL = "SELECT stAdjustmentGtnDetail FROM StAdjustmentGtnDetail stAdjustmentGtnDetail";
    private static final String _SQL_COUNT_STADJUSTMENTGTNDETAIL = "SELECT COUNT(stAdjustmentGtnDetail) FROM StAdjustmentGtnDetail stAdjustmentGtnDetail";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stAdjustmentGtnDetail.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAdjustmentGtnDetail exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StAdjustmentGtnDetailPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentCreatedDate", "etlCheckRecord", "businessUnitNo",
                "redemptionPeriod", "deductionId", "glYear", "brandName",
                "modifiedDate", "account", "source", "workflowApprovedDate",
                "udc6", "businessUnitName", "udc5", "workflowCreatedDate",
                "udc4", "udc3", "udc2", "udc1", "adjustmentType", "modifiedBy",
                "deductionType", "checkRecord", "contractName", "deductionRate",
                "deductionCategory", "deductionNo", "companyNo", "sessionId",
                "glCompanyId", "itemName", "deductionInclusion",
                "deductionAmount", "companyName", "project", "deductionUdc3",
                "deductionUdc4", "deductionUdc1", "itemId", "deductionUdc2",
                "accountType", "glString", "createdDate", "createdBy",
                "deductionUdc6", "deductionUdc5", "glCompanyName", "workflowId",
                "itemNo", "contractId", "deductionProgram", "businessUnitId",
                "userId", "costCenter", "companyId", "outboundStatus", "future1",
                "brandId", "deductionName", "future2", "workflowName", "glDate",
                "workflowCreatedBy", "glMonth", "batchId", "accountCategory",
                "glCompanyNo", "workflowApprovedBy", "contractNo",
                "originalBatchId", "adjustmentLevel"
            });
    private static StAdjustmentGtnDetail _nullStAdjustmentGtnDetail = new StAdjustmentGtnDetailImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StAdjustmentGtnDetail> toCacheModel() {
                return _nullStAdjustmentGtnDetailCacheModel;
            }
        };

    private static CacheModel<StAdjustmentGtnDetail> _nullStAdjustmentGtnDetailCacheModel =
        new CacheModel<StAdjustmentGtnDetail>() {
            @Override
            public StAdjustmentGtnDetail toEntityModel() {
                return _nullStAdjustmentGtnDetail;
            }
        };

    public StAdjustmentGtnDetailPersistenceImpl() {
        setModelClass(StAdjustmentGtnDetail.class);
    }

    /**
     * Caches the st adjustment gtn detail in the entity cache if it is enabled.
     *
     * @param stAdjustmentGtnDetail the st adjustment gtn detail
     */
    @Override
    public void cacheResult(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        EntityCacheUtil.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailImpl.class,
            stAdjustmentGtnDetail.getPrimaryKey(), stAdjustmentGtnDetail);

        stAdjustmentGtnDetail.resetOriginalValues();
    }

    /**
     * Caches the st adjustment gtn details in the entity cache if it is enabled.
     *
     * @param stAdjustmentGtnDetails the st adjustment gtn details
     */
    @Override
    public void cacheResult(List<StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
        for (StAdjustmentGtnDetail stAdjustmentGtnDetail : stAdjustmentGtnDetails) {
            if (EntityCacheUtil.getResult(
                        StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
                        StAdjustmentGtnDetailImpl.class,
                        stAdjustmentGtnDetail.getPrimaryKey()) == null) {
                cacheResult(stAdjustmentGtnDetail);
            } else {
                stAdjustmentGtnDetail.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st adjustment gtn details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StAdjustmentGtnDetailImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StAdjustmentGtnDetailImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st adjustment gtn detail.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        EntityCacheUtil.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailImpl.class,
            stAdjustmentGtnDetail.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StAdjustmentGtnDetail stAdjustmentGtnDetail : stAdjustmentGtnDetails) {
            EntityCacheUtil.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
                StAdjustmentGtnDetailImpl.class,
                stAdjustmentGtnDetail.getPrimaryKey());
        }
    }

    /**
     * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
     *
     * @param stAdjustmentGtnDetailPK the primary key for the new st adjustment gtn detail
     * @return the new st adjustment gtn detail
     */
    @Override
    public StAdjustmentGtnDetail create(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK) {
        StAdjustmentGtnDetail stAdjustmentGtnDetail = new StAdjustmentGtnDetailImpl();

        stAdjustmentGtnDetail.setNew(true);
        stAdjustmentGtnDetail.setPrimaryKey(stAdjustmentGtnDetailPK);

        return stAdjustmentGtnDetail;
    }

    /**
     * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail remove(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws NoSuchStAdjustmentGtnDetailException, SystemException {
        return remove((Serializable) stAdjustmentGtnDetailPK);
    }

    /**
     * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail remove(Serializable primaryKey)
        throws NoSuchStAdjustmentGtnDetailException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StAdjustmentGtnDetail stAdjustmentGtnDetail = (StAdjustmentGtnDetail) session.get(StAdjustmentGtnDetailImpl.class,
                    primaryKey);

            if (stAdjustmentGtnDetail == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStAdjustmentGtnDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stAdjustmentGtnDetail);
        } catch (NoSuchStAdjustmentGtnDetailException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StAdjustmentGtnDetail removeImpl(
        StAdjustmentGtnDetail stAdjustmentGtnDetail) throws SystemException {
        stAdjustmentGtnDetail = toUnwrappedModel(stAdjustmentGtnDetail);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stAdjustmentGtnDetail)) {
                stAdjustmentGtnDetail = (StAdjustmentGtnDetail) session.get(StAdjustmentGtnDetailImpl.class,
                        stAdjustmentGtnDetail.getPrimaryKeyObj());
            }

            if (stAdjustmentGtnDetail != null) {
                session.delete(stAdjustmentGtnDetail);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stAdjustmentGtnDetail != null) {
            clearCache(stAdjustmentGtnDetail);
        }

        return stAdjustmentGtnDetail;
    }

    @Override
    public StAdjustmentGtnDetail updateImpl(
        com.stpl.app.parttwo.model.StAdjustmentGtnDetail stAdjustmentGtnDetail)
        throws SystemException {
        stAdjustmentGtnDetail = toUnwrappedModel(stAdjustmentGtnDetail);

        boolean isNew = stAdjustmentGtnDetail.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stAdjustmentGtnDetail.isNew()) {
                session.save(stAdjustmentGtnDetail);

                stAdjustmentGtnDetail.setNew(false);
            } else {
                session.merge(stAdjustmentGtnDetail);
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

        EntityCacheUtil.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
            StAdjustmentGtnDetailImpl.class,
            stAdjustmentGtnDetail.getPrimaryKey(), stAdjustmentGtnDetail);

        return stAdjustmentGtnDetail;
    }

    protected StAdjustmentGtnDetail toUnwrappedModel(
        StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        if (stAdjustmentGtnDetail instanceof StAdjustmentGtnDetailImpl) {
            return stAdjustmentGtnDetail;
        }

        StAdjustmentGtnDetailImpl stAdjustmentGtnDetailImpl = new StAdjustmentGtnDetailImpl();

        stAdjustmentGtnDetailImpl.setNew(stAdjustmentGtnDetail.isNew());
        stAdjustmentGtnDetailImpl.setPrimaryKey(stAdjustmentGtnDetail.getPrimaryKey());

        stAdjustmentGtnDetailImpl.setAdjustmentCreatedDate(stAdjustmentGtnDetail.getAdjustmentCreatedDate());
        stAdjustmentGtnDetailImpl.setEtlCheckRecord(stAdjustmentGtnDetail.isEtlCheckRecord());
        stAdjustmentGtnDetailImpl.setBusinessUnitNo(stAdjustmentGtnDetail.getBusinessUnitNo());
        stAdjustmentGtnDetailImpl.setRedemptionPeriod(stAdjustmentGtnDetail.getRedemptionPeriod());
        stAdjustmentGtnDetailImpl.setDeductionId(stAdjustmentGtnDetail.getDeductionId());
        stAdjustmentGtnDetailImpl.setGlYear(stAdjustmentGtnDetail.getGlYear());
        stAdjustmentGtnDetailImpl.setBrandName(stAdjustmentGtnDetail.getBrandName());
        stAdjustmentGtnDetailImpl.setModifiedDate(stAdjustmentGtnDetail.getModifiedDate());
        stAdjustmentGtnDetailImpl.setAccount(stAdjustmentGtnDetail.getAccount());
        stAdjustmentGtnDetailImpl.setSource(stAdjustmentGtnDetail.getSource());
        stAdjustmentGtnDetailImpl.setWorkflowApprovedDate(stAdjustmentGtnDetail.getWorkflowApprovedDate());
        stAdjustmentGtnDetailImpl.setUdc6(stAdjustmentGtnDetail.getUdc6());
        stAdjustmentGtnDetailImpl.setBusinessUnitName(stAdjustmentGtnDetail.getBusinessUnitName());
        stAdjustmentGtnDetailImpl.setUdc5(stAdjustmentGtnDetail.getUdc5());
        stAdjustmentGtnDetailImpl.setWorkflowCreatedDate(stAdjustmentGtnDetail.getWorkflowCreatedDate());
        stAdjustmentGtnDetailImpl.setUdc4(stAdjustmentGtnDetail.getUdc4());
        stAdjustmentGtnDetailImpl.setUdc3(stAdjustmentGtnDetail.getUdc3());
        stAdjustmentGtnDetailImpl.setUdc2(stAdjustmentGtnDetail.getUdc2());
        stAdjustmentGtnDetailImpl.setUdc1(stAdjustmentGtnDetail.getUdc1());
        stAdjustmentGtnDetailImpl.setAdjustmentType(stAdjustmentGtnDetail.getAdjustmentType());
        stAdjustmentGtnDetailImpl.setModifiedBy(stAdjustmentGtnDetail.getModifiedBy());
        stAdjustmentGtnDetailImpl.setDeductionType(stAdjustmentGtnDetail.getDeductionType());
        stAdjustmentGtnDetailImpl.setCheckRecord(stAdjustmentGtnDetail.isCheckRecord());
        stAdjustmentGtnDetailImpl.setContractName(stAdjustmentGtnDetail.getContractName());
        stAdjustmentGtnDetailImpl.setDeductionRate(stAdjustmentGtnDetail.getDeductionRate());
        stAdjustmentGtnDetailImpl.setDeductionCategory(stAdjustmentGtnDetail.getDeductionCategory());
        stAdjustmentGtnDetailImpl.setDeductionNo(stAdjustmentGtnDetail.getDeductionNo());
        stAdjustmentGtnDetailImpl.setCompanyNo(stAdjustmentGtnDetail.getCompanyNo());
        stAdjustmentGtnDetailImpl.setSessionId(stAdjustmentGtnDetail.getSessionId());
        stAdjustmentGtnDetailImpl.setGlCompanyId(stAdjustmentGtnDetail.getGlCompanyId());
        stAdjustmentGtnDetailImpl.setItemName(stAdjustmentGtnDetail.getItemName());
        stAdjustmentGtnDetailImpl.setDeductionInclusion(stAdjustmentGtnDetail.getDeductionInclusion());
        stAdjustmentGtnDetailImpl.setDeductionAmount(stAdjustmentGtnDetail.getDeductionAmount());
        stAdjustmentGtnDetailImpl.setCompanyName(stAdjustmentGtnDetail.getCompanyName());
        stAdjustmentGtnDetailImpl.setProject(stAdjustmentGtnDetail.getProject());
        stAdjustmentGtnDetailImpl.setDeductionUdc3(stAdjustmentGtnDetail.getDeductionUdc3());
        stAdjustmentGtnDetailImpl.setDeductionUdc4(stAdjustmentGtnDetail.getDeductionUdc4());
        stAdjustmentGtnDetailImpl.setDeductionUdc1(stAdjustmentGtnDetail.getDeductionUdc1());
        stAdjustmentGtnDetailImpl.setItemId(stAdjustmentGtnDetail.getItemId());
        stAdjustmentGtnDetailImpl.setDeductionUdc2(stAdjustmentGtnDetail.getDeductionUdc2());
        stAdjustmentGtnDetailImpl.setAccountType(stAdjustmentGtnDetail.getAccountType());
        stAdjustmentGtnDetailImpl.setGlString(stAdjustmentGtnDetail.getGlString());
        stAdjustmentGtnDetailImpl.setCreatedDate(stAdjustmentGtnDetail.getCreatedDate());
        stAdjustmentGtnDetailImpl.setCreatedBy(stAdjustmentGtnDetail.getCreatedBy());
        stAdjustmentGtnDetailImpl.setDeductionUdc6(stAdjustmentGtnDetail.getDeductionUdc6());
        stAdjustmentGtnDetailImpl.setDeductionUdc5(stAdjustmentGtnDetail.getDeductionUdc5());
        stAdjustmentGtnDetailImpl.setGlCompanyName(stAdjustmentGtnDetail.getGlCompanyName());
        stAdjustmentGtnDetailImpl.setWorkflowId(stAdjustmentGtnDetail.getWorkflowId());
        stAdjustmentGtnDetailImpl.setItemNo(stAdjustmentGtnDetail.getItemNo());
        stAdjustmentGtnDetailImpl.setContractId(stAdjustmentGtnDetail.getContractId());
        stAdjustmentGtnDetailImpl.setDeductionProgram(stAdjustmentGtnDetail.getDeductionProgram());
        stAdjustmentGtnDetailImpl.setBusinessUnitId(stAdjustmentGtnDetail.getBusinessUnitId());
        stAdjustmentGtnDetailImpl.setUserId(stAdjustmentGtnDetail.getUserId());
        stAdjustmentGtnDetailImpl.setCostCenter(stAdjustmentGtnDetail.getCostCenter());
        stAdjustmentGtnDetailImpl.setCompanyId(stAdjustmentGtnDetail.getCompanyId());
        stAdjustmentGtnDetailImpl.setOutboundStatus(stAdjustmentGtnDetail.getOutboundStatus());
        stAdjustmentGtnDetailImpl.setFuture1(stAdjustmentGtnDetail.getFuture1());
        stAdjustmentGtnDetailImpl.setBrandId(stAdjustmentGtnDetail.getBrandId());
        stAdjustmentGtnDetailImpl.setDeductionName(stAdjustmentGtnDetail.getDeductionName());
        stAdjustmentGtnDetailImpl.setFuture2(stAdjustmentGtnDetail.getFuture2());
        stAdjustmentGtnDetailImpl.setWorkflowName(stAdjustmentGtnDetail.getWorkflowName());
        stAdjustmentGtnDetailImpl.setGlDate(stAdjustmentGtnDetail.getGlDate());
        stAdjustmentGtnDetailImpl.setWorkflowCreatedBy(stAdjustmentGtnDetail.getWorkflowCreatedBy());
        stAdjustmentGtnDetailImpl.setGlMonth(stAdjustmentGtnDetail.getGlMonth());
        stAdjustmentGtnDetailImpl.setBatchId(stAdjustmentGtnDetail.getBatchId());
        stAdjustmentGtnDetailImpl.setAccountCategory(stAdjustmentGtnDetail.getAccountCategory());
        stAdjustmentGtnDetailImpl.setGlCompanyNo(stAdjustmentGtnDetail.getGlCompanyNo());
        stAdjustmentGtnDetailImpl.setWorkflowApprovedBy(stAdjustmentGtnDetail.getWorkflowApprovedBy());
        stAdjustmentGtnDetailImpl.setContractNo(stAdjustmentGtnDetail.getContractNo());
        stAdjustmentGtnDetailImpl.setOriginalBatchId(stAdjustmentGtnDetail.getOriginalBatchId());
        stAdjustmentGtnDetailImpl.setAdjustmentLevel(stAdjustmentGtnDetail.getAdjustmentLevel());

        return stAdjustmentGtnDetailImpl;
    }

    /**
     * Returns the st adjustment gtn detail with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStAdjustmentGtnDetailException, SystemException {
        StAdjustmentGtnDetail stAdjustmentGtnDetail = fetchByPrimaryKey(primaryKey);

        if (stAdjustmentGtnDetail == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStAdjustmentGtnDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stAdjustmentGtnDetail;
    }

    /**
     * Returns the st adjustment gtn detail with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException} if it could not be found.
     *
     * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail
     * @throws com.stpl.app.parttwo.NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail findByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws NoSuchStAdjustmentGtnDetailException, SystemException {
        return findByPrimaryKey((Serializable) stAdjustmentGtnDetailPK);
    }

    /**
     * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StAdjustmentGtnDetail stAdjustmentGtnDetail = (StAdjustmentGtnDetail) EntityCacheUtil.getResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
                StAdjustmentGtnDetailImpl.class, primaryKey);

        if (stAdjustmentGtnDetail == _nullStAdjustmentGtnDetail) {
            return null;
        }

        if (stAdjustmentGtnDetail == null) {
            Session session = null;

            try {
                session = openSession();

                stAdjustmentGtnDetail = (StAdjustmentGtnDetail) session.get(StAdjustmentGtnDetailImpl.class,
                        primaryKey);

                if (stAdjustmentGtnDetail != null) {
                    cacheResult(stAdjustmentGtnDetail);
                } else {
                    EntityCacheUtil.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
                        StAdjustmentGtnDetailImpl.class, primaryKey,
                        _nullStAdjustmentGtnDetail);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
                    StAdjustmentGtnDetailImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stAdjustmentGtnDetail;
    }

    /**
     * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stAdjustmentGtnDetailPK the primary key of the st adjustment gtn detail
     * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAdjustmentGtnDetail fetchByPrimaryKey(
        StAdjustmentGtnDetailPK stAdjustmentGtnDetailPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stAdjustmentGtnDetailPK);
    }

    /**
     * Returns all the st adjustment gtn details.
     *
     * @return the st adjustment gtn details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentGtnDetail> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st adjustment gtn details.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st adjustment gtn details
     * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
     * @return the range of st adjustment gtn details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentGtnDetail> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st adjustment gtn details.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st adjustment gtn details
     * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st adjustment gtn details
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAdjustmentGtnDetail> findAll(int start, int end,
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

        List<StAdjustmentGtnDetail> list = (List<StAdjustmentGtnDetail>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STADJUSTMENTGTNDETAIL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STADJUSTMENTGTNDETAIL;

                if (pagination) {
                    sql = sql.concat(StAdjustmentGtnDetailModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StAdjustmentGtnDetail>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StAdjustmentGtnDetail>(list);
                } else {
                    list = (List<StAdjustmentGtnDetail>) QueryUtil.list(q,
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
     * Removes all the st adjustment gtn details from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StAdjustmentGtnDetail stAdjustmentGtnDetail : findAll()) {
            remove(stAdjustmentGtnDetail);
        }
    }

    /**
     * Returns the number of st adjustment gtn details.
     *
     * @return the number of st adjustment gtn details
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

                Query q = session.createQuery(_SQL_COUNT_STADJUSTMENTGTNDETAIL);

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
     * Initializes the st adjustment gtn detail persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.StAdjustmentGtnDetail")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StAdjustmentGtnDetail>> listenersList = new ArrayList<ModelListener<StAdjustmentGtnDetail>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StAdjustmentGtnDetail>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StAdjustmentGtnDetailImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
