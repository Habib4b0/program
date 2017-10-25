package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwIvldDemandForecastActualException;
import com.stpl.app.model.VwIvldDemandForecastActual;
import com.stpl.app.model.impl.VwIvldDemandForecastActualImpl;
import com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl;
import com.stpl.app.service.persistence.VwIvldDemandForecastActualPersistence;

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
 * The persistence implementation for the vw ivld demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldDemandForecastActualPersistence
 * @see VwIvldDemandForecastActualUtil
 * @generated
 */
public class VwIvldDemandForecastActualPersistenceImpl
    extends BasePersistenceImpl<VwIvldDemandForecastActual>
    implements VwIvldDemandForecastActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwIvldDemandForecastActualUtil} to access the vw ivld demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwIvldDemandForecastActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
            VwIvldDemandForecastActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
            VwIvldDemandForecastActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL = "SELECT vwIvldDemandForecastActual FROM VwIvldDemandForecastActual vwIvldDemandForecastActual";
    private static final String _SQL_COUNT_VWIVLDDEMANDFORECASTACTUAL = "SELECT COUNT(vwIvldDemandForecastActual) FROM VwIvldDemandForecastActual vwIvldDemandForecastActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldDemandForecastActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldDemandForecastActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwIvldDemandForecastActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "demandIntSid", "forecastYear", "grossUnits", "businessUnitNo",
                "totalDemandUnits", "brandName", "itemId", "organizationKey",
                "source", "marketShareRatio", "ivldDemandActualForecastSid",
                "businessUnitName", "addChgDelIndicator", "errorCode",
                "marketShareUnits", "inventoryUnitChange", "reprocessedFlag",
                "uncapturedUnitsRatio", "reasonForFailure", "country",
                "forecastType", "brandId", "isForecast", "uncapturedUnits",
                "grossPrice", "isActive", "grossAmount", "batchId",
                "forecastVer", "itemName", "forecastMonth", "errorField",
                "netSalesPrice", "netSalesAmount", "segment",
                "totalDemandAmount", "forecastName", "marketSizeUnits",
                "checkRecord"
            });
    private static VwIvldDemandForecastActual _nullVwIvldDemandForecastActual = new VwIvldDemandForecastActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwIvldDemandForecastActual> toCacheModel() {
                return _nullVwIvldDemandForecastActualCacheModel;
            }
        };

    private static CacheModel<VwIvldDemandForecastActual> _nullVwIvldDemandForecastActualCacheModel =
        new CacheModel<VwIvldDemandForecastActual>() {
            @Override
            public VwIvldDemandForecastActual toEntityModel() {
                return _nullVwIvldDemandForecastActual;
            }
        };

    public VwIvldDemandForecastActualPersistenceImpl() {
        setModelClass(VwIvldDemandForecastActual.class);
    }

    /**
     * Caches the vw ivld demand forecast actual in the entity cache if it is enabled.
     *
     * @param vwIvldDemandForecastActual the vw ivld demand forecast actual
     */
    @Override
    public void cacheResult(
        VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        EntityCacheUtil.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualImpl.class,
            vwIvldDemandForecastActual.getPrimaryKey(),
            vwIvldDemandForecastActual);

        vwIvldDemandForecastActual.resetOriginalValues();
    }

    /**
     * Caches the vw ivld demand forecast actuals in the entity cache if it is enabled.
     *
     * @param vwIvldDemandForecastActuals the vw ivld demand forecast actuals
     */
    @Override
    public void cacheResult(
        List<VwIvldDemandForecastActual> vwIvldDemandForecastActuals) {
        for (VwIvldDemandForecastActual vwIvldDemandForecastActual : vwIvldDemandForecastActuals) {
            if (EntityCacheUtil.getResult(
                        VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldDemandForecastActualImpl.class,
                        vwIvldDemandForecastActual.getPrimaryKey()) == null) {
                cacheResult(vwIvldDemandForecastActual);
            } else {
                vwIvldDemandForecastActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw ivld demand forecast actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwIvldDemandForecastActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwIvldDemandForecastActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw ivld demand forecast actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        EntityCacheUtil.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualImpl.class,
            vwIvldDemandForecastActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwIvldDemandForecastActual> vwIvldDemandForecastActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwIvldDemandForecastActual vwIvldDemandForecastActual : vwIvldDemandForecastActuals) {
            EntityCacheUtil.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldDemandForecastActualImpl.class,
                vwIvldDemandForecastActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw ivld demand forecast actual with the primary key. Does not add the vw ivld demand forecast actual to the database.
     *
     * @param ivldDemandActualForecastSid the primary key for the new vw ivld demand forecast actual
     * @return the new vw ivld demand forecast actual
     */
    @Override
    public VwIvldDemandForecastActual create(int ivldDemandActualForecastSid) {
        VwIvldDemandForecastActual vwIvldDemandForecastActual = new VwIvldDemandForecastActualImpl();

        vwIvldDemandForecastActual.setNew(true);
        vwIvldDemandForecastActual.setPrimaryKey(ivldDemandActualForecastSid);

        return vwIvldDemandForecastActual;
    }

    /**
     * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual that was removed
     * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual remove(int ivldDemandActualForecastSid)
        throws NoSuchVwIvldDemandForecastActualException, SystemException {
        return remove((Serializable) ivldDemandActualForecastSid);
    }

    /**
     * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual that was removed
     * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual remove(Serializable primaryKey)
        throws NoSuchVwIvldDemandForecastActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwIvldDemandForecastActual vwIvldDemandForecastActual = (VwIvldDemandForecastActual) session.get(VwIvldDemandForecastActualImpl.class,
                    primaryKey);

            if (vwIvldDemandForecastActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwIvldDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwIvldDemandForecastActual);
        } catch (NoSuchVwIvldDemandForecastActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwIvldDemandForecastActual removeImpl(
        VwIvldDemandForecastActual vwIvldDemandForecastActual)
        throws SystemException {
        vwIvldDemandForecastActual = toUnwrappedModel(vwIvldDemandForecastActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwIvldDemandForecastActual)) {
                vwIvldDemandForecastActual = (VwIvldDemandForecastActual) session.get(VwIvldDemandForecastActualImpl.class,
                        vwIvldDemandForecastActual.getPrimaryKeyObj());
            }

            if (vwIvldDemandForecastActual != null) {
                session.delete(vwIvldDemandForecastActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwIvldDemandForecastActual != null) {
            clearCache(vwIvldDemandForecastActual);
        }

        return vwIvldDemandForecastActual;
    }

    @Override
    public VwIvldDemandForecastActual updateImpl(
        com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual)
        throws SystemException {
        vwIvldDemandForecastActual = toUnwrappedModel(vwIvldDemandForecastActual);

        boolean isNew = vwIvldDemandForecastActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwIvldDemandForecastActual.isNew()) {
                session.save(vwIvldDemandForecastActual);

                vwIvldDemandForecastActual.setNew(false);
            } else {
                session.merge(vwIvldDemandForecastActual);
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

        EntityCacheUtil.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldDemandForecastActualImpl.class,
            vwIvldDemandForecastActual.getPrimaryKey(),
            vwIvldDemandForecastActual);

        return vwIvldDemandForecastActual;
    }

    protected VwIvldDemandForecastActual toUnwrappedModel(
        VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        if (vwIvldDemandForecastActual instanceof VwIvldDemandForecastActualImpl) {
            return vwIvldDemandForecastActual;
        }

        VwIvldDemandForecastActualImpl vwIvldDemandForecastActualImpl = new VwIvldDemandForecastActualImpl();

        vwIvldDemandForecastActualImpl.setNew(vwIvldDemandForecastActual.isNew());
        vwIvldDemandForecastActualImpl.setPrimaryKey(vwIvldDemandForecastActual.getPrimaryKey());

        vwIvldDemandForecastActualImpl.setDemandIntSid(vwIvldDemandForecastActual.getDemandIntSid());
        vwIvldDemandForecastActualImpl.setForecastYear(vwIvldDemandForecastActual.getForecastYear());
        vwIvldDemandForecastActualImpl.setGrossUnits(vwIvldDemandForecastActual.getGrossUnits());
        vwIvldDemandForecastActualImpl.setBusinessUnitNo(vwIvldDemandForecastActual.getBusinessUnitNo());
        vwIvldDemandForecastActualImpl.setTotalDemandUnits(vwIvldDemandForecastActual.getTotalDemandUnits());
        vwIvldDemandForecastActualImpl.setBrandName(vwIvldDemandForecastActual.getBrandName());
        vwIvldDemandForecastActualImpl.setItemId(vwIvldDemandForecastActual.getItemId());
        vwIvldDemandForecastActualImpl.setOrganizationKey(vwIvldDemandForecastActual.getOrganizationKey());
        vwIvldDemandForecastActualImpl.setSource(vwIvldDemandForecastActual.getSource());
        vwIvldDemandForecastActualImpl.setMarketShareRatio(vwIvldDemandForecastActual.getMarketShareRatio());
        vwIvldDemandForecastActualImpl.setIvldDemandActualForecastSid(vwIvldDemandForecastActual.getIvldDemandActualForecastSid());
        vwIvldDemandForecastActualImpl.setBusinessUnitName(vwIvldDemandForecastActual.getBusinessUnitName());
        vwIvldDemandForecastActualImpl.setAddChgDelIndicator(vwIvldDemandForecastActual.getAddChgDelIndicator());
        vwIvldDemandForecastActualImpl.setErrorCode(vwIvldDemandForecastActual.getErrorCode());
        vwIvldDemandForecastActualImpl.setMarketShareUnits(vwIvldDemandForecastActual.getMarketShareUnits());
        vwIvldDemandForecastActualImpl.setInventoryUnitChange(vwIvldDemandForecastActual.getInventoryUnitChange());
        vwIvldDemandForecastActualImpl.setReprocessedFlag(vwIvldDemandForecastActual.getReprocessedFlag());
        vwIvldDemandForecastActualImpl.setUncapturedUnitsRatio(vwIvldDemandForecastActual.getUncapturedUnitsRatio());
        vwIvldDemandForecastActualImpl.setReasonForFailure(vwIvldDemandForecastActual.getReasonForFailure());
        vwIvldDemandForecastActualImpl.setCountry(vwIvldDemandForecastActual.getCountry());
        vwIvldDemandForecastActualImpl.setForecastType(vwIvldDemandForecastActual.getForecastType());
        vwIvldDemandForecastActualImpl.setBrandId(vwIvldDemandForecastActual.getBrandId());
        vwIvldDemandForecastActualImpl.setIsForecast(vwIvldDemandForecastActual.getIsForecast());
        vwIvldDemandForecastActualImpl.setUncapturedUnits(vwIvldDemandForecastActual.getUncapturedUnits());
        vwIvldDemandForecastActualImpl.setGrossPrice(vwIvldDemandForecastActual.getGrossPrice());
        vwIvldDemandForecastActualImpl.setIsActive(vwIvldDemandForecastActual.getIsActive());
        vwIvldDemandForecastActualImpl.setGrossAmount(vwIvldDemandForecastActual.getGrossAmount());
        vwIvldDemandForecastActualImpl.setBatchId(vwIvldDemandForecastActual.getBatchId());
        vwIvldDemandForecastActualImpl.setForecastVer(vwIvldDemandForecastActual.getForecastVer());
        vwIvldDemandForecastActualImpl.setItemName(vwIvldDemandForecastActual.getItemName());
        vwIvldDemandForecastActualImpl.setForecastMonth(vwIvldDemandForecastActual.getForecastMonth());
        vwIvldDemandForecastActualImpl.setErrorField(vwIvldDemandForecastActual.getErrorField());
        vwIvldDemandForecastActualImpl.setNetSalesPrice(vwIvldDemandForecastActual.getNetSalesPrice());
        vwIvldDemandForecastActualImpl.setNetSalesAmount(vwIvldDemandForecastActual.getNetSalesAmount());
        vwIvldDemandForecastActualImpl.setSegment(vwIvldDemandForecastActual.getSegment());
        vwIvldDemandForecastActualImpl.setTotalDemandAmount(vwIvldDemandForecastActual.getTotalDemandAmount());
        vwIvldDemandForecastActualImpl.setForecastName(vwIvldDemandForecastActual.getForecastName());
        vwIvldDemandForecastActualImpl.setMarketSizeUnits(vwIvldDemandForecastActual.getMarketSizeUnits());
        vwIvldDemandForecastActualImpl.setCheckRecord(vwIvldDemandForecastActual.isCheckRecord());

        return vwIvldDemandForecastActualImpl;
    }

    /**
     * Returns the vw ivld demand forecast actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual
     * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwIvldDemandForecastActualException, SystemException {
        VwIvldDemandForecastActual vwIvldDemandForecastActual = fetchByPrimaryKey(primaryKey);

        if (vwIvldDemandForecastActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwIvldDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwIvldDemandForecastActual;
    }

    /**
     * Returns the vw ivld demand forecast actual with the primary key or throws a {@link com.stpl.app.NoSuchVwIvldDemandForecastActualException} if it could not be found.
     *
     * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual
     * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual findByPrimaryKey(
        int ivldDemandActualForecastSid)
        throws NoSuchVwIvldDemandForecastActualException, SystemException {
        return findByPrimaryKey((Serializable) ivldDemandActualForecastSid);
    }

    /**
     * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwIvldDemandForecastActual vwIvldDemandForecastActual = (VwIvldDemandForecastActual) EntityCacheUtil.getResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldDemandForecastActualImpl.class, primaryKey);

        if (vwIvldDemandForecastActual == _nullVwIvldDemandForecastActual) {
            return null;
        }

        if (vwIvldDemandForecastActual == null) {
            Session session = null;

            try {
                session = openSession();

                vwIvldDemandForecastActual = (VwIvldDemandForecastActual) session.get(VwIvldDemandForecastActualImpl.class,
                        primaryKey);

                if (vwIvldDemandForecastActual != null) {
                    cacheResult(vwIvldDemandForecastActual);
                } else {
                    EntityCacheUtil.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldDemandForecastActualImpl.class, primaryKey,
                        _nullVwIvldDemandForecastActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                    VwIvldDemandForecastActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwIvldDemandForecastActual;
    }

    /**
     * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
     * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldDemandForecastActual fetchByPrimaryKey(
        int ivldDemandActualForecastSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldDemandActualForecastSid);
    }

    /**
     * Returns all the vw ivld demand forecast actuals.
     *
     * @return the vw ivld demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldDemandForecastActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw ivld demand forecast actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld demand forecast actuals
     * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
     * @return the range of vw ivld demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldDemandForecastActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw ivld demand forecast actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld demand forecast actuals
     * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw ivld demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldDemandForecastActual> findAll(int start, int end,
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

        List<VwIvldDemandForecastActual> list = (List<VwIvldDemandForecastActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL;

                if (pagination) {
                    sql = sql.concat(VwIvldDemandForecastActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwIvldDemandForecastActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwIvldDemandForecastActual>(list);
                } else {
                    list = (List<VwIvldDemandForecastActual>) QueryUtil.list(q,
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
     * Removes all the vw ivld demand forecast actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwIvldDemandForecastActual vwIvldDemandForecastActual : findAll()) {
            remove(vwIvldDemandForecastActual);
        }
    }

    /**
     * Returns the number of vw ivld demand forecast actuals.
     *
     * @return the number of vw ivld demand forecast actuals
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

                Query q = session.createQuery(_SQL_COUNT_VWIVLDDEMANDFORECASTACTUAL);

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
     * Initializes the vw ivld demand forecast actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwIvldDemandForecastActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwIvldDemandForecastActual>> listenersList = new ArrayList<ModelListener<VwIvldDemandForecastActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwIvldDemandForecastActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwIvldDemandForecastActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
