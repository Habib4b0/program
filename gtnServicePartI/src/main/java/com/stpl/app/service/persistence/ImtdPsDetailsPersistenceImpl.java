package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdPsDetailsException;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.model.impl.ImtdPsDetailsImpl;
import com.stpl.app.model.impl.ImtdPsDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdPsDetailsPersistence;

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
 * The persistence implementation for the imtd ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdPsDetailsPersistence
 * @see ImtdPsDetailsUtil
 * @generated
 */
public class ImtdPsDetailsPersistenceImpl extends BasePersistenceImpl<ImtdPsDetails>
    implements ImtdPsDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdPsDetailsUtil} to access the imtd ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdPsDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdPsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdPsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDPSDETAILS = "SELECT imtdPsDetails FROM ImtdPsDetails imtdPsDetails";
    private static final String _SQL_COUNT_IMTDPSDETAILS = "SELECT COUNT(imtdPsDetails) FROM ImtdPsDetails imtdPsDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdPsDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdPsDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdPsDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "psDetailsModifiedDate", "psDetailsSuggestedPrice",
                "psDetailsContractPrice", "resetDate", "psDetailsAttachedStatus",
                "imtdPsDetailsSid", "modifiedDate", "psDetailsCreatedBy",
                "contractMasterSid", "psDtlsContPriceEnddate",
                "psDetailsPricPrtcnStdate", "imtdCreatedDate",
                "netPriceTypeFormula", "modifiedBy", "maxIncrementalChange",
                "psDetailsPricePlanId", "checkRecord",
                "psDtlsPriceToleranceFreq", "itemName", "sessionId",
                "resetFrequency", "psDtlsPriceToleranceType",
                "psDetailsPricetype", "psDetailsPriceRevision", "resetInterval",
                "ifpNo", "psDetailsAttachedDate", "nepFormula",
                "psDetailsModifiedBy", "psDtlsPriceToleranceIntrvl",
                "itemMasterSid", "resetType", "itemId", "status",
                "brandMasterSid", "psDetailsPrice", "psDetailsCreatedDate",
                "usersSid", "createdBy", "createdDate", "psDetailsSid",
                "psModelSid", "priceProtectionPriceType", "psDetailsBasePrice",
                "itemNo", "ifpModelSid", "psDetailsRevisionDate", "nep",
                "psDetailsPriceTolerance", "priceProtectionStatus",
                "psDtlsContPriceStartdate", "resetEligible", "netPriceType",
                "operation", "cfpModelSid", "psDetailsPricPrtcnEddate",
                "basePriceType", "basePriceEntry", "basePriceDate",
                "basePriceDdlb", "netBasePrice", "netBasePriceFormulaId",
                "netBasePriceFormulaNo", "netBasePriceFormulaName",
                "subsequentPeriodPriceType", "netSubsequentPeriodPrice",
                "netSubsequentPriceFormulaId", "netSubsequentPriceFormulaNo",
                "netSubsequentPriceFormulaName", "resetPriceType",
                "netResetPriceType", "netResetPriceFormulaId",
                "netResetPriceFormulaNo", "netResetPriceFormulaName"
            });
    private static ImtdPsDetails _nullImtdPsDetails = new ImtdPsDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdPsDetails> toCacheModel() {
                return _nullImtdPsDetailsCacheModel;
            }
        };

    private static CacheModel<ImtdPsDetails> _nullImtdPsDetailsCacheModel = new CacheModel<ImtdPsDetails>() {
            @Override
            public ImtdPsDetails toEntityModel() {
                return _nullImtdPsDetails;
            }
        };

    public ImtdPsDetailsPersistenceImpl() {
        setModelClass(ImtdPsDetails.class);
    }

    /**
     * Caches the imtd ps details in the entity cache if it is enabled.
     *
     * @param imtdPsDetails the imtd ps details
     */
    @Override
    public void cacheResult(ImtdPsDetails imtdPsDetails) {
        EntityCacheUtil.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey(),
            imtdPsDetails);

        imtdPsDetails.resetOriginalValues();
    }

    /**
     * Caches the imtd ps detailses in the entity cache if it is enabled.
     *
     * @param imtdPsDetailses the imtd ps detailses
     */
    @Override
    public void cacheResult(List<ImtdPsDetails> imtdPsDetailses) {
        for (ImtdPsDetails imtdPsDetails : imtdPsDetailses) {
            if (EntityCacheUtil.getResult(
                        ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey()) == null) {
                cacheResult(imtdPsDetails);
            } else {
                imtdPsDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd ps detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdPsDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdPsDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd ps details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdPsDetails imtdPsDetails) {
        EntityCacheUtil.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdPsDetails> imtdPsDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdPsDetails imtdPsDetails : imtdPsDetailses) {
            EntityCacheUtil.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
     *
     * @param imtdPsDetailsSid the primary key for the new imtd ps details
     * @return the new imtd ps details
     */
    @Override
    public ImtdPsDetails create(int imtdPsDetailsSid) {
        ImtdPsDetails imtdPsDetails = new ImtdPsDetailsImpl();

        imtdPsDetails.setNew(true);
        imtdPsDetails.setPrimaryKey(imtdPsDetailsSid);

        return imtdPsDetails;
    }

    /**
     * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdPsDetailsSid the primary key of the imtd ps details
     * @return the imtd ps details that was removed
     * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails remove(int imtdPsDetailsSid)
        throws NoSuchImtdPsDetailsException, SystemException {
        return remove((Serializable) imtdPsDetailsSid);
    }

    /**
     * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd ps details
     * @return the imtd ps details that was removed
     * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails remove(Serializable primaryKey)
        throws NoSuchImtdPsDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdPsDetails imtdPsDetails = (ImtdPsDetails) session.get(ImtdPsDetailsImpl.class,
                    primaryKey);

            if (imtdPsDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdPsDetails);
        } catch (NoSuchImtdPsDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdPsDetails removeImpl(ImtdPsDetails imtdPsDetails)
        throws SystemException {
        imtdPsDetails = toUnwrappedModel(imtdPsDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdPsDetails)) {
                imtdPsDetails = (ImtdPsDetails) session.get(ImtdPsDetailsImpl.class,
                        imtdPsDetails.getPrimaryKeyObj());
            }

            if (imtdPsDetails != null) {
                session.delete(imtdPsDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdPsDetails != null) {
            clearCache(imtdPsDetails);
        }

        return imtdPsDetails;
    }

    @Override
    public ImtdPsDetails updateImpl(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws SystemException {
        imtdPsDetails = toUnwrappedModel(imtdPsDetails);

        boolean isNew = imtdPsDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdPsDetails.isNew()) {
                session.save(imtdPsDetails);

                imtdPsDetails.setNew(false);
            } else {
                session.merge(imtdPsDetails);
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

        EntityCacheUtil.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey(),
            imtdPsDetails);

        return imtdPsDetails;
    }

    protected ImtdPsDetails toUnwrappedModel(ImtdPsDetails imtdPsDetails) {
        if (imtdPsDetails instanceof ImtdPsDetailsImpl) {
            return imtdPsDetails;
        }

        ImtdPsDetailsImpl imtdPsDetailsImpl = new ImtdPsDetailsImpl();

        imtdPsDetailsImpl.setNew(imtdPsDetails.isNew());
        imtdPsDetailsImpl.setPrimaryKey(imtdPsDetails.getPrimaryKey());

        imtdPsDetailsImpl.setPsDetailsModifiedDate(imtdPsDetails.getPsDetailsModifiedDate());
        imtdPsDetailsImpl.setPsDetailsSuggestedPrice(imtdPsDetails.getPsDetailsSuggestedPrice());
        imtdPsDetailsImpl.setPsDetailsContractPrice(imtdPsDetails.getPsDetailsContractPrice());
        imtdPsDetailsImpl.setResetDate(imtdPsDetails.getResetDate());
        imtdPsDetailsImpl.setPsDetailsAttachedStatus(imtdPsDetails.getPsDetailsAttachedStatus());
        imtdPsDetailsImpl.setImtdPsDetailsSid(imtdPsDetails.getImtdPsDetailsSid());
        imtdPsDetailsImpl.setModifiedDate(imtdPsDetails.getModifiedDate());
        imtdPsDetailsImpl.setPsDetailsCreatedBy(imtdPsDetails.getPsDetailsCreatedBy());
        imtdPsDetailsImpl.setContractMasterSid(imtdPsDetails.getContractMasterSid());
        imtdPsDetailsImpl.setPsDtlsContPriceEnddate(imtdPsDetails.getPsDtlsContPriceEnddate());
        imtdPsDetailsImpl.setPsDetailsPricPrtcnStdate(imtdPsDetails.getPsDetailsPricPrtcnStdate());
        imtdPsDetailsImpl.setImtdCreatedDate(imtdPsDetails.getImtdCreatedDate());
        imtdPsDetailsImpl.setNetPriceTypeFormula(imtdPsDetails.getNetPriceTypeFormula());
        imtdPsDetailsImpl.setModifiedBy(imtdPsDetails.getModifiedBy());
        imtdPsDetailsImpl.setMaxIncrementalChange(imtdPsDetails.getMaxIncrementalChange());
        imtdPsDetailsImpl.setPsDetailsPricePlanId(imtdPsDetails.getPsDetailsPricePlanId());
        imtdPsDetailsImpl.setCheckRecord(imtdPsDetails.isCheckRecord());
        imtdPsDetailsImpl.setPsDtlsPriceToleranceFreq(imtdPsDetails.getPsDtlsPriceToleranceFreq());
        imtdPsDetailsImpl.setItemName(imtdPsDetails.getItemName());
        imtdPsDetailsImpl.setSessionId(imtdPsDetails.getSessionId());
        imtdPsDetailsImpl.setResetFrequency(imtdPsDetails.getResetFrequency());
        imtdPsDetailsImpl.setPsDtlsPriceToleranceType(imtdPsDetails.getPsDtlsPriceToleranceType());
        imtdPsDetailsImpl.setPsDetailsPricetype(imtdPsDetails.getPsDetailsPricetype());
        imtdPsDetailsImpl.setPsDetailsPriceRevision(imtdPsDetails.getPsDetailsPriceRevision());
        imtdPsDetailsImpl.setResetInterval(imtdPsDetails.getResetInterval());
        imtdPsDetailsImpl.setIfpNo(imtdPsDetails.getIfpNo());
        imtdPsDetailsImpl.setPsDetailsAttachedDate(imtdPsDetails.getPsDetailsAttachedDate());
        imtdPsDetailsImpl.setNepFormula(imtdPsDetails.getNepFormula());
        imtdPsDetailsImpl.setPsDetailsModifiedBy(imtdPsDetails.getPsDetailsModifiedBy());
        imtdPsDetailsImpl.setPsDtlsPriceToleranceIntrvl(imtdPsDetails.getPsDtlsPriceToleranceIntrvl());
        imtdPsDetailsImpl.setItemMasterSid(imtdPsDetails.getItemMasterSid());
        imtdPsDetailsImpl.setResetType(imtdPsDetails.getResetType());
        imtdPsDetailsImpl.setItemId(imtdPsDetails.getItemId());
        imtdPsDetailsImpl.setStatus(imtdPsDetails.getStatus());
        imtdPsDetailsImpl.setBrandMasterSid(imtdPsDetails.getBrandMasterSid());
        imtdPsDetailsImpl.setPsDetailsPrice(imtdPsDetails.getPsDetailsPrice());
        imtdPsDetailsImpl.setPsDetailsCreatedDate(imtdPsDetails.getPsDetailsCreatedDate());
        imtdPsDetailsImpl.setUsersSid(imtdPsDetails.getUsersSid());
        imtdPsDetailsImpl.setCreatedBy(imtdPsDetails.getCreatedBy());
        imtdPsDetailsImpl.setCreatedDate(imtdPsDetails.getCreatedDate());
        imtdPsDetailsImpl.setPsDetailsSid(imtdPsDetails.getPsDetailsSid());
        imtdPsDetailsImpl.setPsModelSid(imtdPsDetails.getPsModelSid());
        imtdPsDetailsImpl.setPriceProtectionPriceType(imtdPsDetails.getPriceProtectionPriceType());
        imtdPsDetailsImpl.setPsDetailsBasePrice(imtdPsDetails.getPsDetailsBasePrice());
        imtdPsDetailsImpl.setItemNo(imtdPsDetails.getItemNo());
        imtdPsDetailsImpl.setIfpModelSid(imtdPsDetails.getIfpModelSid());
        imtdPsDetailsImpl.setPsDetailsRevisionDate(imtdPsDetails.getPsDetailsRevisionDate());
        imtdPsDetailsImpl.setNep(imtdPsDetails.getNep());
        imtdPsDetailsImpl.setPsDetailsPriceTolerance(imtdPsDetails.getPsDetailsPriceTolerance());
        imtdPsDetailsImpl.setPriceProtectionStatus(imtdPsDetails.getPriceProtectionStatus());
        imtdPsDetailsImpl.setPsDtlsContPriceStartdate(imtdPsDetails.getPsDtlsContPriceStartdate());
        imtdPsDetailsImpl.setResetEligible(imtdPsDetails.getResetEligible());
        imtdPsDetailsImpl.setNetPriceType(imtdPsDetails.getNetPriceType());
        imtdPsDetailsImpl.setOperation(imtdPsDetails.getOperation());
        imtdPsDetailsImpl.setCfpModelSid(imtdPsDetails.getCfpModelSid());
        imtdPsDetailsImpl.setPsDetailsPricPrtcnEddate(imtdPsDetails.getPsDetailsPricPrtcnEddate());
        imtdPsDetailsImpl.setBasePriceType(imtdPsDetails.getBasePriceType());
        imtdPsDetailsImpl.setBasePriceEntry(imtdPsDetails.getBasePriceEntry());
        imtdPsDetailsImpl.setBasePriceDate(imtdPsDetails.getBasePriceDate());
        imtdPsDetailsImpl.setBasePriceDdlb(imtdPsDetails.getBasePriceDdlb());
        imtdPsDetailsImpl.setNetBasePrice(imtdPsDetails.getNetBasePrice());
        imtdPsDetailsImpl.setNetBasePriceFormulaId(imtdPsDetails.getNetBasePriceFormulaId());
        imtdPsDetailsImpl.setNetBasePriceFormulaNo(imtdPsDetails.getNetBasePriceFormulaNo());
        imtdPsDetailsImpl.setNetBasePriceFormulaName(imtdPsDetails.getNetBasePriceFormulaName());
        imtdPsDetailsImpl.setSubsequentPeriodPriceType(imtdPsDetails.getSubsequentPeriodPriceType());
        imtdPsDetailsImpl.setNetSubsequentPeriodPrice(imtdPsDetails.getNetSubsequentPeriodPrice());
        imtdPsDetailsImpl.setNetSubsequentPriceFormulaId(imtdPsDetails.getNetSubsequentPriceFormulaId());
        imtdPsDetailsImpl.setNetSubsequentPriceFormulaNo(imtdPsDetails.getNetSubsequentPriceFormulaNo());
        imtdPsDetailsImpl.setNetSubsequentPriceFormulaName(imtdPsDetails.getNetSubsequentPriceFormulaName());
        imtdPsDetailsImpl.setResetPriceType(imtdPsDetails.getResetPriceType());
        imtdPsDetailsImpl.setNetResetPriceType(imtdPsDetails.getNetResetPriceType());
        imtdPsDetailsImpl.setNetResetPriceFormulaId(imtdPsDetails.getNetResetPriceFormulaId());
        imtdPsDetailsImpl.setNetResetPriceFormulaNo(imtdPsDetails.getNetResetPriceFormulaNo());
        imtdPsDetailsImpl.setNetResetPriceFormulaName(imtdPsDetails.getNetResetPriceFormulaName());

        return imtdPsDetailsImpl;
    }

    /**
     * Returns the imtd ps details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd ps details
     * @return the imtd ps details
     * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdPsDetailsException, SystemException {
        ImtdPsDetails imtdPsDetails = fetchByPrimaryKey(primaryKey);

        if (imtdPsDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdPsDetails;
    }

    /**
     * Returns the imtd ps details with the primary key or throws a {@link com.stpl.app.NoSuchImtdPsDetailsException} if it could not be found.
     *
     * @param imtdPsDetailsSid the primary key of the imtd ps details
     * @return the imtd ps details
     * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails findByPrimaryKey(int imtdPsDetailsSid)
        throws NoSuchImtdPsDetailsException, SystemException {
        return findByPrimaryKey((Serializable) imtdPsDetailsSid);
    }

    /**
     * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd ps details
     * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdPsDetails imtdPsDetails = (ImtdPsDetails) EntityCacheUtil.getResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdPsDetailsImpl.class, primaryKey);

        if (imtdPsDetails == _nullImtdPsDetails) {
            return null;
        }

        if (imtdPsDetails == null) {
            Session session = null;

            try {
                session = openSession();

                imtdPsDetails = (ImtdPsDetails) session.get(ImtdPsDetailsImpl.class,
                        primaryKey);

                if (imtdPsDetails != null) {
                    cacheResult(imtdPsDetails);
                } else {
                    EntityCacheUtil.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdPsDetailsImpl.class, primaryKey, _nullImtdPsDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdPsDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdPsDetails;
    }

    /**
     * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdPsDetailsSid the primary key of the imtd ps details
     * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdPsDetails fetchByPrimaryKey(int imtdPsDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdPsDetailsSid);
    }

    /**
     * Returns all the imtd ps detailses.
     *
     * @return the imtd ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdPsDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd ps detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd ps detailses
     * @param end the upper bound of the range of imtd ps detailses (not inclusive)
     * @return the range of imtd ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdPsDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd ps detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd ps detailses
     * @param end the upper bound of the range of imtd ps detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd ps detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdPsDetails> findAll(int start, int end,
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

        List<ImtdPsDetails> list = (List<ImtdPsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDPSDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDPSDETAILS;

                if (pagination) {
                    sql = sql.concat(ImtdPsDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdPsDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdPsDetails>(list);
                } else {
                    list = (List<ImtdPsDetails>) QueryUtil.list(q,
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
     * Removes all the imtd ps detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdPsDetails imtdPsDetails : findAll()) {
            remove(imtdPsDetails);
        }
    }

    /**
     * Returns the number of imtd ps detailses.
     *
     * @return the number of imtd ps detailses
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

                Query q = session.createQuery(_SQL_COUNT_IMTDPSDETAILS);

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
     * Initializes the imtd ps details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdPsDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdPsDetails>> listenersList = new ArrayList<ModelListener<ImtdPsDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdPsDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdPsDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
