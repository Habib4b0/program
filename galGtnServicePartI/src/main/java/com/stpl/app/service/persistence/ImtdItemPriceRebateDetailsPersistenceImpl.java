package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdItemPriceRebateDetailsException;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.model.impl.ImtdItemPriceRebateDetailsImpl;
import com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsPersistence;

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
 * The persistence implementation for the imtd item price rebate details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetailsPersistence
 * @see ImtdItemPriceRebateDetailsUtil
 * @generated
 */
public class ImtdItemPriceRebateDetailsPersistenceImpl
    extends BasePersistenceImpl<ImtdItemPriceRebateDetails>
    implements ImtdItemPriceRebateDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdItemPriceRebateDetailsUtil} to access the imtd item price rebate details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdItemPriceRebateDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_IMTDITEMPRICEREBATEDETAILS = "SELECT imtdItemPriceRebateDetails FROM ImtdItemPriceRebateDetails imtdItemPriceRebateDetails";
    private static final String _SQL_COUNT_IMTDITEMPRICEREBATEDETAILS = "SELECT COUNT(imtdItemPriceRebateDetails) FROM ImtdItemPriceRebateDetails imtdItemPriceRebateDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdItemPriceRebateDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdItemPriceRebateDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdItemPriceRebateDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "formulaMethodId", "endDate", "priceProtectionStartDate",
                "itemPriceRevisionDate", "modifiedDate", "rsCheckRecord",
                "rebateRevisionDate", "contractMasterSid", "imtdCreatedDate",
                "modifiedBy", "udc6", "udc5", "udc4", "checkRecord", "udc1",
                "udc2", "udc3", "contractPriceEndDate", "totalVolumeCommitment",
                "priceProtectionEndDate", "recordLockStatus", "startDate",
                "rebateProgramType", "sessionId", "itemName", "priceRevision",
                "rsModelSid", "price", "rsAttachedStatus", "itemMasterSid",
                "totalDollarCommitment", "itemType",
                "totalMarketShareCommitmnet", "itemId", "basePrice", "bundleNo",
                "formulaName", "psStatus", "priceTolerance", "createdDate",
                "createdBy", "usersSid", "psDetailsSid", "suggestedPrice",
                "psModelSid", "formulaId", "commitmentPeriod", "itemNo",
                "contractPrice", "ifpDetailsSid", "ifpModelSid",
                "priceToleranceType", "rebateAmount", "contractPriceStartDate",
                "rebateScheduleType", "priceToleranceFrequency",
                "imtdItemPriceRebateSid", "rebatePlanSystemId", "attachedDate",
                "pricePlanId", "itemRebateEndDate", "priceType",
                "priceToleranceInterval", "rsAttachedDate",
                "itemRebateStartDate", "operation", "cfpModelSid",
                "rsDetailsSid", "attachedStatus", "primaryUom", "packageSize",
                "deductionCalendarMasterSid",
                "rsContractDetailsDeductionCalendarNo",
                "rsContractDetailsDeductionCalendarName",
                "netSalesFormulaMasterSid", "rsContractDetailsNetSalesFormulaNo",
                "rsContractDetailsNetSalesFormulaName", "formulaType",
                "netSalesRule", "evaluationRule", "evaluationRuleBundle",
                "calculationRule", "calculationRuleBundle",
                "maxIncrementalChange", "resetEligible", "resetType",
                "resetDate", "resetInterval", "resetFrequency", "netPriceType",
                "netPriceTypeFormula", "priceProtectionPriceType", "nep",
                "nepFormula", "brandMasterSid", "priceProtectionStatus",
                "basePriceType", "basePriceEntry", "basePriceDate",
                "basePriceDdlb", "netBasePrice", "subsequentPeriodPriceType",
                "resetPriceType", "netResetPriceType", "netResetPriceFormulaId",
                "netBasePriceFormulaId", "netSubsequentPriceFormulaId",
                "netSubsequentPeriodPrice", "rsContractDetailsRebatePlanName",
                "rsContractDetailsFormulaNo", "source"
            });
    private static ImtdItemPriceRebateDetails _nullImtdItemPriceRebateDetails = new ImtdItemPriceRebateDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdItemPriceRebateDetails> toCacheModel() {
                return _nullImtdItemPriceRebateDetailsCacheModel;
            }
        };

    private static CacheModel<ImtdItemPriceRebateDetails> _nullImtdItemPriceRebateDetailsCacheModel =
        new CacheModel<ImtdItemPriceRebateDetails>() {
            @Override
            public ImtdItemPriceRebateDetails toEntityModel() {
                return _nullImtdItemPriceRebateDetails;
            }
        };

    public ImtdItemPriceRebateDetailsPersistenceImpl() {
        setModelClass(ImtdItemPriceRebateDetails.class);
    }

    /**
     * Caches the imtd item price rebate details in the entity cache if it is enabled.
     *
     * @param imtdItemPriceRebateDetails the imtd item price rebate details
     */
    @Override
    public void cacheResult(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        EntityCacheUtil.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsImpl.class,
            imtdItemPriceRebateDetails.getPrimaryKey(),
            imtdItemPriceRebateDetails);

        imtdItemPriceRebateDetails.resetOriginalValues();
    }

    /**
     * Caches the imtd item price rebate detailses in the entity cache if it is enabled.
     *
     * @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
     */
    @Override
    public void cacheResult(
        List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
        for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : imtdItemPriceRebateDetailses) {
            if (EntityCacheUtil.getResult(
                        ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdItemPriceRebateDetailsImpl.class,
                        imtdItemPriceRebateDetails.getPrimaryKey()) == null) {
                cacheResult(imtdItemPriceRebateDetails);
            } else {
                imtdItemPriceRebateDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd item price rebate detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdItemPriceRebateDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdItemPriceRebateDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd item price rebate details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        EntityCacheUtil.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsImpl.class,
            imtdItemPriceRebateDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : imtdItemPriceRebateDetailses) {
            EntityCacheUtil.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdItemPriceRebateDetailsImpl.class,
                imtdItemPriceRebateDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
     *
     * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
     * @return the new imtd item price rebate details
     */
    @Override
    public ImtdItemPriceRebateDetails create(int imtdItemPriceRebateSid) {
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = new ImtdItemPriceRebateDetailsImpl();

        imtdItemPriceRebateDetails.setNew(true);
        imtdItemPriceRebateDetails.setPrimaryKey(imtdItemPriceRebateSid);

        return imtdItemPriceRebateDetails;
    }

    /**
     * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details that was removed
     * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails remove(int imtdItemPriceRebateSid)
        throws NoSuchImtdItemPriceRebateDetailsException, SystemException {
        return remove((Serializable) imtdItemPriceRebateSid);
    }

    /**
     * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details that was removed
     * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails remove(Serializable primaryKey)
        throws NoSuchImtdItemPriceRebateDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails) session.get(ImtdItemPriceRebateDetailsImpl.class,
                    primaryKey);

            if (imtdItemPriceRebateDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdItemPriceRebateDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdItemPriceRebateDetails);
        } catch (NoSuchImtdItemPriceRebateDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdItemPriceRebateDetails removeImpl(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws SystemException {
        imtdItemPriceRebateDetails = toUnwrappedModel(imtdItemPriceRebateDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdItemPriceRebateDetails)) {
                imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails) session.get(ImtdItemPriceRebateDetailsImpl.class,
                        imtdItemPriceRebateDetails.getPrimaryKeyObj());
            }

            if (imtdItemPriceRebateDetails != null) {
                session.delete(imtdItemPriceRebateDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdItemPriceRebateDetails != null) {
            clearCache(imtdItemPriceRebateDetails);
        }

        return imtdItemPriceRebateDetails;
    }

    @Override
    public ImtdItemPriceRebateDetails updateImpl(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws SystemException {
        imtdItemPriceRebateDetails = toUnwrappedModel(imtdItemPriceRebateDetails);

        boolean isNew = imtdItemPriceRebateDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdItemPriceRebateDetails.isNew()) {
                session.save(imtdItemPriceRebateDetails);

                imtdItemPriceRebateDetails.setNew(false);
            } else {
                session.merge(imtdItemPriceRebateDetails);
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

        EntityCacheUtil.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdItemPriceRebateDetailsImpl.class,
            imtdItemPriceRebateDetails.getPrimaryKey(),
            imtdItemPriceRebateDetails);

        return imtdItemPriceRebateDetails;
    }

    protected ImtdItemPriceRebateDetails toUnwrappedModel(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        if (imtdItemPriceRebateDetails instanceof ImtdItemPriceRebateDetailsImpl) {
            return imtdItemPriceRebateDetails;
        }

        ImtdItemPriceRebateDetailsImpl imtdItemPriceRebateDetailsImpl = new ImtdItemPriceRebateDetailsImpl();

        imtdItemPriceRebateDetailsImpl.setNew(imtdItemPriceRebateDetails.isNew());
        imtdItemPriceRebateDetailsImpl.setPrimaryKey(imtdItemPriceRebateDetails.getPrimaryKey());

        imtdItemPriceRebateDetailsImpl.setFormulaMethodId(imtdItemPriceRebateDetails.getFormulaMethodId());
        imtdItemPriceRebateDetailsImpl.setEndDate(imtdItemPriceRebateDetails.getEndDate());
        imtdItemPriceRebateDetailsImpl.setPriceProtectionStartDate(imtdItemPriceRebateDetails.getPriceProtectionStartDate());
        imtdItemPriceRebateDetailsImpl.setItemPriceRevisionDate(imtdItemPriceRebateDetails.getItemPriceRevisionDate());
        imtdItemPriceRebateDetailsImpl.setModifiedDate(imtdItemPriceRebateDetails.getModifiedDate());
        imtdItemPriceRebateDetailsImpl.setRsCheckRecord(imtdItemPriceRebateDetails.isRsCheckRecord());
        imtdItemPriceRebateDetailsImpl.setRebateRevisionDate(imtdItemPriceRebateDetails.getRebateRevisionDate());
        imtdItemPriceRebateDetailsImpl.setContractMasterSid(imtdItemPriceRebateDetails.getContractMasterSid());
        imtdItemPriceRebateDetailsImpl.setImtdCreatedDate(imtdItemPriceRebateDetails.getImtdCreatedDate());
        imtdItemPriceRebateDetailsImpl.setModifiedBy(imtdItemPriceRebateDetails.getModifiedBy());
        imtdItemPriceRebateDetailsImpl.setUdc6(imtdItemPriceRebateDetails.getUdc6());
        imtdItemPriceRebateDetailsImpl.setUdc5(imtdItemPriceRebateDetails.getUdc5());
        imtdItemPriceRebateDetailsImpl.setUdc4(imtdItemPriceRebateDetails.getUdc4());
        imtdItemPriceRebateDetailsImpl.setCheckRecord(imtdItemPriceRebateDetails.isCheckRecord());
        imtdItemPriceRebateDetailsImpl.setUdc1(imtdItemPriceRebateDetails.getUdc1());
        imtdItemPriceRebateDetailsImpl.setUdc2(imtdItemPriceRebateDetails.getUdc2());
        imtdItemPriceRebateDetailsImpl.setUdc3(imtdItemPriceRebateDetails.getUdc3());
        imtdItemPriceRebateDetailsImpl.setContractPriceEndDate(imtdItemPriceRebateDetails.getContractPriceEndDate());
        imtdItemPriceRebateDetailsImpl.setTotalVolumeCommitment(imtdItemPriceRebateDetails.getTotalVolumeCommitment());
        imtdItemPriceRebateDetailsImpl.setPriceProtectionEndDate(imtdItemPriceRebateDetails.getPriceProtectionEndDate());
        imtdItemPriceRebateDetailsImpl.setRecordLockStatus(imtdItemPriceRebateDetails.getRecordLockStatus());
        imtdItemPriceRebateDetailsImpl.setStartDate(imtdItemPriceRebateDetails.getStartDate());
        imtdItemPriceRebateDetailsImpl.setRebateProgramType(imtdItemPriceRebateDetails.getRebateProgramType());
        imtdItemPriceRebateDetailsImpl.setSessionId(imtdItemPriceRebateDetails.getSessionId());
        imtdItemPriceRebateDetailsImpl.setItemName(imtdItemPriceRebateDetails.getItemName());
        imtdItemPriceRebateDetailsImpl.setPriceRevision(imtdItemPriceRebateDetails.getPriceRevision());
        imtdItemPriceRebateDetailsImpl.setRsModelSid(imtdItemPriceRebateDetails.getRsModelSid());
        imtdItemPriceRebateDetailsImpl.setPrice(imtdItemPriceRebateDetails.getPrice());
        imtdItemPriceRebateDetailsImpl.setRsAttachedStatus(imtdItemPriceRebateDetails.getRsAttachedStatus());
        imtdItemPriceRebateDetailsImpl.setItemMasterSid(imtdItemPriceRebateDetails.getItemMasterSid());
        imtdItemPriceRebateDetailsImpl.setTotalDollarCommitment(imtdItemPriceRebateDetails.getTotalDollarCommitment());
        imtdItemPriceRebateDetailsImpl.setItemType(imtdItemPriceRebateDetails.getItemType());
        imtdItemPriceRebateDetailsImpl.setTotalMarketShareCommitmnet(imtdItemPriceRebateDetails.getTotalMarketShareCommitmnet());
        imtdItemPriceRebateDetailsImpl.setItemId(imtdItemPriceRebateDetails.getItemId());
        imtdItemPriceRebateDetailsImpl.setBasePrice(imtdItemPriceRebateDetails.getBasePrice());
        imtdItemPriceRebateDetailsImpl.setBundleNo(imtdItemPriceRebateDetails.getBundleNo());
        imtdItemPriceRebateDetailsImpl.setFormulaName(imtdItemPriceRebateDetails.getFormulaName());
        imtdItemPriceRebateDetailsImpl.setPsStatus(imtdItemPriceRebateDetails.getPsStatus());
        imtdItemPriceRebateDetailsImpl.setPriceTolerance(imtdItemPriceRebateDetails.getPriceTolerance());
        imtdItemPriceRebateDetailsImpl.setCreatedDate(imtdItemPriceRebateDetails.getCreatedDate());
        imtdItemPriceRebateDetailsImpl.setCreatedBy(imtdItemPriceRebateDetails.getCreatedBy());
        imtdItemPriceRebateDetailsImpl.setUsersSid(imtdItemPriceRebateDetails.getUsersSid());
        imtdItemPriceRebateDetailsImpl.setPsDetailsSid(imtdItemPriceRebateDetails.getPsDetailsSid());
        imtdItemPriceRebateDetailsImpl.setSuggestedPrice(imtdItemPriceRebateDetails.getSuggestedPrice());
        imtdItemPriceRebateDetailsImpl.setPsModelSid(imtdItemPriceRebateDetails.getPsModelSid());
        imtdItemPriceRebateDetailsImpl.setFormulaId(imtdItemPriceRebateDetails.getFormulaId());
        imtdItemPriceRebateDetailsImpl.setCommitmentPeriod(imtdItemPriceRebateDetails.getCommitmentPeriod());
        imtdItemPriceRebateDetailsImpl.setItemNo(imtdItemPriceRebateDetails.getItemNo());
        imtdItemPriceRebateDetailsImpl.setContractPrice(imtdItemPriceRebateDetails.getContractPrice());
        imtdItemPriceRebateDetailsImpl.setIfpDetailsSid(imtdItemPriceRebateDetails.getIfpDetailsSid());
        imtdItemPriceRebateDetailsImpl.setIfpModelSid(imtdItemPriceRebateDetails.getIfpModelSid());
        imtdItemPriceRebateDetailsImpl.setPriceToleranceType(imtdItemPriceRebateDetails.getPriceToleranceType());
        imtdItemPriceRebateDetailsImpl.setRebateAmount(imtdItemPriceRebateDetails.getRebateAmount());
        imtdItemPriceRebateDetailsImpl.setContractPriceStartDate(imtdItemPriceRebateDetails.getContractPriceStartDate());
        imtdItemPriceRebateDetailsImpl.setRebateScheduleType(imtdItemPriceRebateDetails.getRebateScheduleType());
        imtdItemPriceRebateDetailsImpl.setPriceToleranceFrequency(imtdItemPriceRebateDetails.getPriceToleranceFrequency());
        imtdItemPriceRebateDetailsImpl.setImtdItemPriceRebateSid(imtdItemPriceRebateDetails.getImtdItemPriceRebateSid());
        imtdItemPriceRebateDetailsImpl.setRebatePlanSystemId(imtdItemPriceRebateDetails.getRebatePlanSystemId());
        imtdItemPriceRebateDetailsImpl.setAttachedDate(imtdItemPriceRebateDetails.getAttachedDate());
        imtdItemPriceRebateDetailsImpl.setPricePlanId(imtdItemPriceRebateDetails.getPricePlanId());
        imtdItemPriceRebateDetailsImpl.setItemRebateEndDate(imtdItemPriceRebateDetails.getItemRebateEndDate());
        imtdItemPriceRebateDetailsImpl.setPriceType(imtdItemPriceRebateDetails.getPriceType());
        imtdItemPriceRebateDetailsImpl.setPriceToleranceInterval(imtdItemPriceRebateDetails.getPriceToleranceInterval());
        imtdItemPriceRebateDetailsImpl.setRsAttachedDate(imtdItemPriceRebateDetails.getRsAttachedDate());
        imtdItemPriceRebateDetailsImpl.setItemRebateStartDate(imtdItemPriceRebateDetails.getItemRebateStartDate());
        imtdItemPriceRebateDetailsImpl.setOperation(imtdItemPriceRebateDetails.getOperation());
        imtdItemPriceRebateDetailsImpl.setCfpModelSid(imtdItemPriceRebateDetails.getCfpModelSid());
        imtdItemPriceRebateDetailsImpl.setRsDetailsSid(imtdItemPriceRebateDetails.getRsDetailsSid());
        imtdItemPriceRebateDetailsImpl.setAttachedStatus(imtdItemPriceRebateDetails.getAttachedStatus());
        imtdItemPriceRebateDetailsImpl.setPrimaryUom(imtdItemPriceRebateDetails.getPrimaryUom());
        imtdItemPriceRebateDetailsImpl.setPackageSize(imtdItemPriceRebateDetails.getPackageSize());
        imtdItemPriceRebateDetailsImpl.setDeductionCalendarMasterSid(imtdItemPriceRebateDetails.getDeductionCalendarMasterSid());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarNo(imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarNo());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarName(imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarName());
        imtdItemPriceRebateDetailsImpl.setNetSalesFormulaMasterSid(imtdItemPriceRebateDetails.getNetSalesFormulaMasterSid());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaNo(imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaNo());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaName(imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaName());
        imtdItemPriceRebateDetailsImpl.setFormulaType(imtdItemPriceRebateDetails.getFormulaType());
        imtdItemPriceRebateDetailsImpl.setNetSalesRule(imtdItemPriceRebateDetails.getNetSalesRule());
        imtdItemPriceRebateDetailsImpl.setEvaluationRule(imtdItemPriceRebateDetails.getEvaluationRule());
        imtdItemPriceRebateDetailsImpl.setEvaluationRuleBundle(imtdItemPriceRebateDetails.getEvaluationRuleBundle());
        imtdItemPriceRebateDetailsImpl.setCalculationRule(imtdItemPriceRebateDetails.getCalculationRule());
        imtdItemPriceRebateDetailsImpl.setCalculationRuleBundle(imtdItemPriceRebateDetails.getCalculationRuleBundle());
        imtdItemPriceRebateDetailsImpl.setMaxIncrementalChange(imtdItemPriceRebateDetails.getMaxIncrementalChange());
        imtdItemPriceRebateDetailsImpl.setResetEligible(imtdItemPriceRebateDetails.getResetEligible());
        imtdItemPriceRebateDetailsImpl.setResetType(imtdItemPriceRebateDetails.getResetType());
        imtdItemPriceRebateDetailsImpl.setResetDate(imtdItemPriceRebateDetails.getResetDate());
        imtdItemPriceRebateDetailsImpl.setResetInterval(imtdItemPriceRebateDetails.getResetInterval());
        imtdItemPriceRebateDetailsImpl.setResetFrequency(imtdItemPriceRebateDetails.getResetFrequency());
        imtdItemPriceRebateDetailsImpl.setNetPriceType(imtdItemPriceRebateDetails.getNetPriceType());
        imtdItemPriceRebateDetailsImpl.setNetPriceTypeFormula(imtdItemPriceRebateDetails.getNetPriceTypeFormula());
        imtdItemPriceRebateDetailsImpl.setPriceProtectionPriceType(imtdItemPriceRebateDetails.getPriceProtectionPriceType());
        imtdItemPriceRebateDetailsImpl.setNep(imtdItemPriceRebateDetails.getNep());
        imtdItemPriceRebateDetailsImpl.setNepFormula(imtdItemPriceRebateDetails.getNepFormula());
        imtdItemPriceRebateDetailsImpl.setBrandMasterSid(imtdItemPriceRebateDetails.getBrandMasterSid());
        imtdItemPriceRebateDetailsImpl.setPriceProtectionStatus(imtdItemPriceRebateDetails.getPriceProtectionStatus());
        imtdItemPriceRebateDetailsImpl.setBasePriceType(imtdItemPriceRebateDetails.getBasePriceType());
        imtdItemPriceRebateDetailsImpl.setBasePriceEntry(imtdItemPriceRebateDetails.getBasePriceEntry());
        imtdItemPriceRebateDetailsImpl.setBasePriceDate(imtdItemPriceRebateDetails.getBasePriceDate());
        imtdItemPriceRebateDetailsImpl.setBasePriceDdlb(imtdItemPriceRebateDetails.getBasePriceDdlb());
        imtdItemPriceRebateDetailsImpl.setNetBasePrice(imtdItemPriceRebateDetails.getNetBasePrice());
        imtdItemPriceRebateDetailsImpl.setSubsequentPeriodPriceType(imtdItemPriceRebateDetails.getSubsequentPeriodPriceType());
        imtdItemPriceRebateDetailsImpl.setResetPriceType(imtdItemPriceRebateDetails.getResetPriceType());
        imtdItemPriceRebateDetailsImpl.setNetResetPriceType(imtdItemPriceRebateDetails.getNetResetPriceType());
        imtdItemPriceRebateDetailsImpl.setNetResetPriceFormulaId(imtdItemPriceRebateDetails.getNetResetPriceFormulaId());
        imtdItemPriceRebateDetailsImpl.setNetBasePriceFormulaId(imtdItemPriceRebateDetails.getNetBasePriceFormulaId());
        imtdItemPriceRebateDetailsImpl.setNetSubsequentPriceFormulaId(imtdItemPriceRebateDetails.getNetSubsequentPriceFormulaId());
        imtdItemPriceRebateDetailsImpl.setNetSubsequentPeriodPrice(imtdItemPriceRebateDetails.getNetSubsequentPeriodPrice());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsRebatePlanName(imtdItemPriceRebateDetails.getRsContractDetailsRebatePlanName());
        imtdItemPriceRebateDetailsImpl.setRsContractDetailsFormulaNo(imtdItemPriceRebateDetails.getRsContractDetailsFormulaNo());
        imtdItemPriceRebateDetailsImpl.setSource(imtdItemPriceRebateDetails.getSource());

        return imtdItemPriceRebateDetailsImpl;
    }

    /**
     * Returns the imtd item price rebate details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details
     * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdItemPriceRebateDetailsException, SystemException {
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = fetchByPrimaryKey(primaryKey);

        if (imtdItemPriceRebateDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdItemPriceRebateDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdItemPriceRebateDetails;
    }

    /**
     * Returns the imtd item price rebate details with the primary key or throws a {@link com.stpl.app.NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
     *
     * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details
     * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails findByPrimaryKey(
        int imtdItemPriceRebateSid)
        throws NoSuchImtdItemPriceRebateDetailsException, SystemException {
        return findByPrimaryKey((Serializable) imtdItemPriceRebateSid);
    }

    /**
     * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails) EntityCacheUtil.getResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdItemPriceRebateDetailsImpl.class, primaryKey);

        if (imtdItemPriceRebateDetails == _nullImtdItemPriceRebateDetails) {
            return null;
        }

        if (imtdItemPriceRebateDetails == null) {
            Session session = null;

            try {
                session = openSession();

                imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails) session.get(ImtdItemPriceRebateDetailsImpl.class,
                        primaryKey);

                if (imtdItemPriceRebateDetails != null) {
                    cacheResult(imtdItemPriceRebateDetails);
                } else {
                    EntityCacheUtil.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdItemPriceRebateDetailsImpl.class, primaryKey,
                        _nullImtdItemPriceRebateDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdItemPriceRebateDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdItemPriceRebateDetails;
    }

    /**
     * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
     * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdItemPriceRebateDetails fetchByPrimaryKey(
        int imtdItemPriceRebateSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdItemPriceRebateSid);
    }

    /**
     * Returns all the imtd item price rebate detailses.
     *
     * @return the imtd item price rebate detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdItemPriceRebateDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd item price rebate detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd item price rebate detailses
     * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
     * @return the range of imtd item price rebate detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdItemPriceRebateDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd item price rebate detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd item price rebate detailses
     * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd item price rebate detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdItemPriceRebateDetails> findAll(int start, int end,
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

        List<ImtdItemPriceRebateDetails> list = (List<ImtdItemPriceRebateDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDITEMPRICEREBATEDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDITEMPRICEREBATEDETAILS;

                if (pagination) {
                    sql = sql.concat(ImtdItemPriceRebateDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdItemPriceRebateDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdItemPriceRebateDetails>(list);
                } else {
                    list = (List<ImtdItemPriceRebateDetails>) QueryUtil.list(q,
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
     * Removes all the imtd item price rebate detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : findAll()) {
            remove(imtdItemPriceRebateDetails);
        }
    }

    /**
     * Returns the number of imtd item price rebate detailses.
     *
     * @return the number of imtd item price rebate detailses
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

                Query q = session.createQuery(_SQL_COUNT_IMTDITEMPRICEREBATEDETAILS);

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
     * Initializes the imtd item price rebate details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdItemPriceRebateDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdItemPriceRebateDetails>> listenersList = new ArrayList<ModelListener<ImtdItemPriceRebateDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdItemPriceRebateDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdItemPriceRebateDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
