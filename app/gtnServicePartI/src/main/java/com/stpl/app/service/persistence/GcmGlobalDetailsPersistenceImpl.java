package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGcmGlobalDetailsException;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.model.impl.GcmGlobalDetailsImpl;
import com.stpl.app.model.impl.GcmGlobalDetailsModelImpl;
import com.stpl.app.service.persistence.GcmGlobalDetailsPersistence;

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
 * The persistence implementation for the gcm global details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmGlobalDetailsPersistence
 * @see GcmGlobalDetailsUtil
 * @generated
 */
public class GcmGlobalDetailsPersistenceImpl extends BasePersistenceImpl<GcmGlobalDetails>
    implements GcmGlobalDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GcmGlobalDetailsUtil} to access the gcm global details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GcmGlobalDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmGlobalDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmGlobalDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GCMGLOBALDETAILS = "SELECT gcmGlobalDetails FROM GcmGlobalDetails gcmGlobalDetails";
    private static final String _SQL_COUNT_GCMGLOBALDETAILS = "SELECT COUNT(gcmGlobalDetails) FROM GcmGlobalDetails gcmGlobalDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "gcmGlobalDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmGlobalDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GcmGlobalDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemStatus", "formulaMethodId", "moduleName",
                "paymentFrequency", "endDate", "cfpStartDate",
                "priceProtectionStartDate", "tempItemMasterSid", "brandName",
                "modifiedDate", "contractMasterSid", "modifiedBy",
                "subModuleName", "theraputicClass", "gcmGlobalDetailsSid",
                "checkRecord", "paymentMethod", "contractPriceEndDate",
                "psContractSid", "priceProtectionEndDate", "startDate",
                "screenName", "rsContractSid", "itemName", "sessionId",
                "cfpStatus", "rsModelSid", "cfpContractSid", "price",
                "tempEndDate", "itemMasterSid", "itemType", "forecastingType",
                "itemId", "basePrice", "status", "formulaName",
                "workflowMasterSid", "priceTolerance", "createdBy",
                "createdDate", "tempStartDate", "cfpEndDate", "psModelSid",
                "formulaId", "itemNo", "contractPrice", "ifpModelSid",
                "priceToleranceType", "rebateAmount", "userId",
                "projectionMasterSid", "contractPriceStartDate",
                "priceToleranceFrequency", "ifpContractAttachedStatus",
                "rebatePlanSystemId", "rebatePlanName", "calendar",
                "pricingQualifierSid", "tempStatus", "itemRebateEndDate",
                "priceToleranceInterval", "itemRebateStartDate", "operation",
                "cfpModelSid", "itemStatusSid", "ifpContractSid"
            });
    private static GcmGlobalDetails _nullGcmGlobalDetails = new GcmGlobalDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GcmGlobalDetails> toCacheModel() {
                return _nullGcmGlobalDetailsCacheModel;
            }
        };

    private static CacheModel<GcmGlobalDetails> _nullGcmGlobalDetailsCacheModel = new CacheModel<GcmGlobalDetails>() {
            @Override
            public GcmGlobalDetails toEntityModel() {
                return _nullGcmGlobalDetails;
            }
        };

    public GcmGlobalDetailsPersistenceImpl() {
        setModelClass(GcmGlobalDetails.class);
    }

    /**
     * Caches the gcm global details in the entity cache if it is enabled.
     *
     * @param gcmGlobalDetails the gcm global details
     */
    @Override
    public void cacheResult(GcmGlobalDetails gcmGlobalDetails) {
        EntityCacheUtil.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey(),
            gcmGlobalDetails);

        gcmGlobalDetails.resetOriginalValues();
    }

    /**
     * Caches the gcm global detailses in the entity cache if it is enabled.
     *
     * @param gcmGlobalDetailses the gcm global detailses
     */
    @Override
    public void cacheResult(List<GcmGlobalDetails> gcmGlobalDetailses) {
        for (GcmGlobalDetails gcmGlobalDetails : gcmGlobalDetailses) {
            if (EntityCacheUtil.getResult(
                        GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmGlobalDetailsImpl.class,
                        gcmGlobalDetails.getPrimaryKey()) == null) {
                cacheResult(gcmGlobalDetails);
            } else {
                gcmGlobalDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gcm global detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GcmGlobalDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GcmGlobalDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gcm global details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GcmGlobalDetails gcmGlobalDetails) {
        EntityCacheUtil.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GcmGlobalDetails> gcmGlobalDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GcmGlobalDetails gcmGlobalDetails : gcmGlobalDetailses) {
            EntityCacheUtil.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new gcm global details with the primary key. Does not add the gcm global details to the database.
     *
     * @param gcmGlobalDetailsSid the primary key for the new gcm global details
     * @return the new gcm global details
     */
    @Override
    public GcmGlobalDetails create(int gcmGlobalDetailsSid) {
        GcmGlobalDetails gcmGlobalDetails = new GcmGlobalDetailsImpl();

        gcmGlobalDetails.setNew(true);
        gcmGlobalDetails.setPrimaryKey(gcmGlobalDetailsSid);

        return gcmGlobalDetails;
    }

    /**
     * Removes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param gcmGlobalDetailsSid the primary key of the gcm global details
     * @return the gcm global details that was removed
     * @throws com.stpl.app.NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails remove(int gcmGlobalDetailsSid)
        throws NoSuchGcmGlobalDetailsException, SystemException {
        return remove((Serializable) gcmGlobalDetailsSid);
    }

    /**
     * Removes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gcm global details
     * @return the gcm global details that was removed
     * @throws com.stpl.app.NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails remove(Serializable primaryKey)
        throws NoSuchGcmGlobalDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GcmGlobalDetails gcmGlobalDetails = (GcmGlobalDetails) session.get(GcmGlobalDetailsImpl.class,
                    primaryKey);

            if (gcmGlobalDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGcmGlobalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(gcmGlobalDetails);
        } catch (NoSuchGcmGlobalDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GcmGlobalDetails removeImpl(GcmGlobalDetails gcmGlobalDetails)
        throws SystemException {
        gcmGlobalDetails = toUnwrappedModel(gcmGlobalDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(gcmGlobalDetails)) {
                gcmGlobalDetails = (GcmGlobalDetails) session.get(GcmGlobalDetailsImpl.class,
                        gcmGlobalDetails.getPrimaryKeyObj());
            }

            if (gcmGlobalDetails != null) {
                session.delete(gcmGlobalDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (gcmGlobalDetails != null) {
            clearCache(gcmGlobalDetails);
        }

        return gcmGlobalDetails;
    }

    @Override
    public GcmGlobalDetails updateImpl(
        com.stpl.app.model.GcmGlobalDetails gcmGlobalDetails)
        throws SystemException {
        gcmGlobalDetails = toUnwrappedModel(gcmGlobalDetails);

        boolean isNew = gcmGlobalDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (gcmGlobalDetails.isNew()) {
                session.save(gcmGlobalDetails);

                gcmGlobalDetails.setNew(false);
            } else {
                session.merge(gcmGlobalDetails);
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

        EntityCacheUtil.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey(),
            gcmGlobalDetails);

        return gcmGlobalDetails;
    }

    protected GcmGlobalDetails toUnwrappedModel(
        GcmGlobalDetails gcmGlobalDetails) {
        if (gcmGlobalDetails instanceof GcmGlobalDetailsImpl) {
            return gcmGlobalDetails;
        }

        GcmGlobalDetailsImpl gcmGlobalDetailsImpl = new GcmGlobalDetailsImpl();

        gcmGlobalDetailsImpl.setNew(gcmGlobalDetails.isNew());
        gcmGlobalDetailsImpl.setPrimaryKey(gcmGlobalDetails.getPrimaryKey());

        gcmGlobalDetailsImpl.setItemStatus(gcmGlobalDetails.getItemStatus());
        gcmGlobalDetailsImpl.setFormulaMethodId(gcmGlobalDetails.getFormulaMethodId());
        gcmGlobalDetailsImpl.setModuleName(gcmGlobalDetails.getModuleName());
        gcmGlobalDetailsImpl.setPaymentFrequency(gcmGlobalDetails.getPaymentFrequency());
        gcmGlobalDetailsImpl.setEndDate(gcmGlobalDetails.getEndDate());
        gcmGlobalDetailsImpl.setCfpStartDate(gcmGlobalDetails.getCfpStartDate());
        gcmGlobalDetailsImpl.setPriceProtectionStartDate(gcmGlobalDetails.getPriceProtectionStartDate());
        gcmGlobalDetailsImpl.setTempItemMasterSid(gcmGlobalDetails.getTempItemMasterSid());
        gcmGlobalDetailsImpl.setBrandName(gcmGlobalDetails.getBrandName());
        gcmGlobalDetailsImpl.setModifiedDate(gcmGlobalDetails.getModifiedDate());
        gcmGlobalDetailsImpl.setContractMasterSid(gcmGlobalDetails.getContractMasterSid());
        gcmGlobalDetailsImpl.setModifiedBy(gcmGlobalDetails.getModifiedBy());
        gcmGlobalDetailsImpl.setSubModuleName(gcmGlobalDetails.getSubModuleName());
        gcmGlobalDetailsImpl.setTheraputicClass(gcmGlobalDetails.getTheraputicClass());
        gcmGlobalDetailsImpl.setGcmGlobalDetailsSid(gcmGlobalDetails.getGcmGlobalDetailsSid());
        gcmGlobalDetailsImpl.setCheckRecord(gcmGlobalDetails.isCheckRecord());
        gcmGlobalDetailsImpl.setPaymentMethod(gcmGlobalDetails.getPaymentMethod());
        gcmGlobalDetailsImpl.setContractPriceEndDate(gcmGlobalDetails.getContractPriceEndDate());
        gcmGlobalDetailsImpl.setPsContractSid(gcmGlobalDetails.getPsContractSid());
        gcmGlobalDetailsImpl.setPriceProtectionEndDate(gcmGlobalDetails.getPriceProtectionEndDate());
        gcmGlobalDetailsImpl.setStartDate(gcmGlobalDetails.getStartDate());
        gcmGlobalDetailsImpl.setScreenName(gcmGlobalDetails.getScreenName());
        gcmGlobalDetailsImpl.setRsContractSid(gcmGlobalDetails.getRsContractSid());
        gcmGlobalDetailsImpl.setItemName(gcmGlobalDetails.getItemName());
        gcmGlobalDetailsImpl.setSessionId(gcmGlobalDetails.getSessionId());
        gcmGlobalDetailsImpl.setCfpStatus(gcmGlobalDetails.getCfpStatus());
        gcmGlobalDetailsImpl.setRsModelSid(gcmGlobalDetails.getRsModelSid());
        gcmGlobalDetailsImpl.setCfpContractSid(gcmGlobalDetails.getCfpContractSid());
        gcmGlobalDetailsImpl.setPrice(gcmGlobalDetails.getPrice());
        gcmGlobalDetailsImpl.setTempEndDate(gcmGlobalDetails.getTempEndDate());
        gcmGlobalDetailsImpl.setItemMasterSid(gcmGlobalDetails.getItemMasterSid());
        gcmGlobalDetailsImpl.setItemType(gcmGlobalDetails.getItemType());
        gcmGlobalDetailsImpl.setForecastingType(gcmGlobalDetails.getForecastingType());
        gcmGlobalDetailsImpl.setItemId(gcmGlobalDetails.getItemId());
        gcmGlobalDetailsImpl.setBasePrice(gcmGlobalDetails.getBasePrice());
        gcmGlobalDetailsImpl.setStatus(gcmGlobalDetails.getStatus());
        gcmGlobalDetailsImpl.setFormulaName(gcmGlobalDetails.getFormulaName());
        gcmGlobalDetailsImpl.setWorkflowMasterSid(gcmGlobalDetails.getWorkflowMasterSid());
        gcmGlobalDetailsImpl.setPriceTolerance(gcmGlobalDetails.getPriceTolerance());
        gcmGlobalDetailsImpl.setCreatedBy(gcmGlobalDetails.getCreatedBy());
        gcmGlobalDetailsImpl.setCreatedDate(gcmGlobalDetails.getCreatedDate());
        gcmGlobalDetailsImpl.setTempStartDate(gcmGlobalDetails.getTempStartDate());
        gcmGlobalDetailsImpl.setCfpEndDate(gcmGlobalDetails.getCfpEndDate());
        gcmGlobalDetailsImpl.setPsModelSid(gcmGlobalDetails.getPsModelSid());
        gcmGlobalDetailsImpl.setFormulaId(gcmGlobalDetails.getFormulaId());
        gcmGlobalDetailsImpl.setItemNo(gcmGlobalDetails.getItemNo());
        gcmGlobalDetailsImpl.setContractPrice(gcmGlobalDetails.getContractPrice());
        gcmGlobalDetailsImpl.setIfpModelSid(gcmGlobalDetails.getIfpModelSid());
        gcmGlobalDetailsImpl.setPriceToleranceType(gcmGlobalDetails.getPriceToleranceType());
        gcmGlobalDetailsImpl.setRebateAmount(gcmGlobalDetails.getRebateAmount());
        gcmGlobalDetailsImpl.setUserId(gcmGlobalDetails.getUserId());
        gcmGlobalDetailsImpl.setProjectionMasterSid(gcmGlobalDetails.getProjectionMasterSid());
        gcmGlobalDetailsImpl.setContractPriceStartDate(gcmGlobalDetails.getContractPriceStartDate());
        gcmGlobalDetailsImpl.setPriceToleranceFrequency(gcmGlobalDetails.getPriceToleranceFrequency());
        gcmGlobalDetailsImpl.setIfpContractAttachedStatus(gcmGlobalDetails.getIfpContractAttachedStatus());
        gcmGlobalDetailsImpl.setRebatePlanSystemId(gcmGlobalDetails.getRebatePlanSystemId());
        gcmGlobalDetailsImpl.setRebatePlanName(gcmGlobalDetails.getRebatePlanName());
        gcmGlobalDetailsImpl.setCalendar(gcmGlobalDetails.getCalendar());
        gcmGlobalDetailsImpl.setPricingQualifierSid(gcmGlobalDetails.getPricingQualifierSid());
        gcmGlobalDetailsImpl.setTempStatus(gcmGlobalDetails.getTempStatus());
        gcmGlobalDetailsImpl.setItemRebateEndDate(gcmGlobalDetails.getItemRebateEndDate());
        gcmGlobalDetailsImpl.setPriceToleranceInterval(gcmGlobalDetails.getPriceToleranceInterval());
        gcmGlobalDetailsImpl.setItemRebateStartDate(gcmGlobalDetails.getItemRebateStartDate());
        gcmGlobalDetailsImpl.setOperation(gcmGlobalDetails.getOperation());
        gcmGlobalDetailsImpl.setCfpModelSid(gcmGlobalDetails.getCfpModelSid());
        gcmGlobalDetailsImpl.setItemStatusSid(gcmGlobalDetails.getItemStatusSid());
        gcmGlobalDetailsImpl.setIfpContractSid(gcmGlobalDetails.getIfpContractSid());

        return gcmGlobalDetailsImpl;
    }

    /**
     * Returns the gcm global details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gcm global details
     * @return the gcm global details
     * @throws com.stpl.app.NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGcmGlobalDetailsException, SystemException {
        GcmGlobalDetails gcmGlobalDetails = fetchByPrimaryKey(primaryKey);

        if (gcmGlobalDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGcmGlobalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return gcmGlobalDetails;
    }

    /**
     * Returns the gcm global details with the primary key or throws a {@link com.stpl.app.NoSuchGcmGlobalDetailsException} if it could not be found.
     *
     * @param gcmGlobalDetailsSid the primary key of the gcm global details
     * @return the gcm global details
     * @throws com.stpl.app.NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails findByPrimaryKey(int gcmGlobalDetailsSid)
        throws NoSuchGcmGlobalDetailsException, SystemException {
        return findByPrimaryKey((Serializable) gcmGlobalDetailsSid);
    }

    /**
     * Returns the gcm global details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gcm global details
     * @return the gcm global details, or <code>null</code> if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GcmGlobalDetails gcmGlobalDetails = (GcmGlobalDetails) EntityCacheUtil.getResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmGlobalDetailsImpl.class, primaryKey);

        if (gcmGlobalDetails == _nullGcmGlobalDetails) {
            return null;
        }

        if (gcmGlobalDetails == null) {
            Session session = null;

            try {
                session = openSession();

                gcmGlobalDetails = (GcmGlobalDetails) session.get(GcmGlobalDetailsImpl.class,
                        primaryKey);

                if (gcmGlobalDetails != null) {
                    cacheResult(gcmGlobalDetails);
                } else {
                    EntityCacheUtil.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmGlobalDetailsImpl.class, primaryKey,
                        _nullGcmGlobalDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    GcmGlobalDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return gcmGlobalDetails;
    }

    /**
     * Returns the gcm global details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param gcmGlobalDetailsSid the primary key of the gcm global details
     * @return the gcm global details, or <code>null</code> if a gcm global details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmGlobalDetails fetchByPrimaryKey(int gcmGlobalDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) gcmGlobalDetailsSid);
    }

    /**
     * Returns all the gcm global detailses.
     *
     * @return the gcm global detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmGlobalDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gcm global detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm global detailses
     * @param end the upper bound of the range of gcm global detailses (not inclusive)
     * @return the range of gcm global detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmGlobalDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gcm global detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm global detailses
     * @param end the upper bound of the range of gcm global detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gcm global detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmGlobalDetails> findAll(int start, int end,
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

        List<GcmGlobalDetails> list = (List<GcmGlobalDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GCMGLOBALDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GCMGLOBALDETAILS;

                if (pagination) {
                    sql = sql.concat(GcmGlobalDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GcmGlobalDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GcmGlobalDetails>(list);
                } else {
                    list = (List<GcmGlobalDetails>) QueryUtil.list(q,
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
     * Removes all the gcm global detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GcmGlobalDetails gcmGlobalDetails : findAll()) {
            remove(gcmGlobalDetails);
        }
    }

    /**
     * Returns the number of gcm global detailses.
     *
     * @return the number of gcm global detailses
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

                Query q = session.createQuery(_SQL_COUNT_GCMGLOBALDETAILS);

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
     * Initializes the gcm global details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GcmGlobalDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GcmGlobalDetails>> listenersList = new ArrayList<ModelListener<GcmGlobalDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GcmGlobalDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GcmGlobalDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
