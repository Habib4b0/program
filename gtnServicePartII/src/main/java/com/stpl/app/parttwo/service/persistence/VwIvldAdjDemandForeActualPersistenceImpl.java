package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException;
import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;
import com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl;
import com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl;
import com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualPersistence;

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
 * The persistence implementation for the vw ivld adj demand fore actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActualPersistence
 * @see VwIvldAdjDemandForeActualUtil
 * @generated
 */
public class VwIvldAdjDemandForeActualPersistenceImpl
    extends BasePersistenceImpl<VwIvldAdjDemandForeActual>
    implements VwIvldAdjDemandForeActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwIvldAdjDemandForeActualUtil} to access the vw ivld adj demand fore actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwIvldAdjDemandForeActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
            VwIvldAdjDemandForeActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
            VwIvldAdjDemandForeActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL = "SELECT vwIvldAdjDemandForeActual FROM VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual";
    private static final String _SQL_COUNT_VWIVLDADJDEMANDFOREACTUAL = "SELECT COUNT(vwIvldAdjDemandForeActual) FROM VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldAdjDemandForeActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldAdjDemandForeActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwIvldAdjDemandForeActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastVersion", "grossUnits", "businessUnitNo", "year",
                "itemId", "brandName", "modifiedDate", "organizationKey",
                "createdDate", "createdBy", "source", "marketShareRatio",
                "businessUnitName", "addChgDelIndicator", "itemIdentifier",
                "errorCode", "modifiedBy", "marketShareUnits", "month",
                "inventoryUnitChange", "reprocessedFlag", "uncapturedUnitsRatio",
                "reasonForFailure", "adjustedDemandForecastIntfId", "country",
                "forecastType", "totalAdjustedDemandUnits", "brandId",
                "isForecast", "totalAdjustedDemandAmount", "uncapturedUnits",
                "grossPrice", "grossAmount", "itemIdentifierCodeQualifier",
                "batchId", "itemName", "errorField", "netSalesPrice",
                "netSalesAmount", "segment", "forecastName",
                "ivldAdjustedDemandForecastSid", "marketSizeUnits",
                "checkRecord"
            });
    private static VwIvldAdjDemandForeActual _nullVwIvldAdjDemandForeActual = new VwIvldAdjDemandForeActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwIvldAdjDemandForeActual> toCacheModel() {
                return _nullVwIvldAdjDemandForeActualCacheModel;
            }
        };

    private static CacheModel<VwIvldAdjDemandForeActual> _nullVwIvldAdjDemandForeActualCacheModel =
        new CacheModel<VwIvldAdjDemandForeActual>() {
            @Override
            public VwIvldAdjDemandForeActual toEntityModel() {
                return _nullVwIvldAdjDemandForeActual;
            }
        };

    public VwIvldAdjDemandForeActualPersistenceImpl() {
        setModelClass(VwIvldAdjDemandForeActual.class);
    }

    /**
     * Caches the vw ivld adj demand fore actual in the entity cache if it is enabled.
     *
     * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
     */
    @Override
    public void cacheResult(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        EntityCacheUtil.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualImpl.class,
            vwIvldAdjDemandForeActual.getPrimaryKey(), vwIvldAdjDemandForeActual);

        vwIvldAdjDemandForeActual.resetOriginalValues();
    }

    /**
     * Caches the vw ivld adj demand fore actuals in the entity cache if it is enabled.
     *
     * @param vwIvldAdjDemandForeActuals the vw ivld adj demand fore actuals
     */
    @Override
    public void cacheResult(
        List<VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
        for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : vwIvldAdjDemandForeActuals) {
            if (EntityCacheUtil.getResult(
                        VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldAdjDemandForeActualImpl.class,
                        vwIvldAdjDemandForeActual.getPrimaryKey()) == null) {
                cacheResult(vwIvldAdjDemandForeActual);
            } else {
                vwIvldAdjDemandForeActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw ivld adj demand fore actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwIvldAdjDemandForeActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwIvldAdjDemandForeActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw ivld adj demand fore actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        EntityCacheUtil.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualImpl.class,
            vwIvldAdjDemandForeActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : vwIvldAdjDemandForeActuals) {
            EntityCacheUtil.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldAdjDemandForeActualImpl.class,
                vwIvldAdjDemandForeActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
     *
     * @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
     * @return the new vw ivld adj demand fore actual
     */
    @Override
    public VwIvldAdjDemandForeActual create(int ivldAdjustedDemandForecastSid) {
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = new VwIvldAdjDemandForeActualImpl();

        vwIvldAdjDemandForeActual.setNew(true);
        vwIvldAdjDemandForeActual.setPrimaryKey(ivldAdjustedDemandForecastSid);

        return vwIvldAdjDemandForeActual;
    }

    /**
     * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual remove(int ivldAdjustedDemandForecastSid)
        throws NoSuchVwIvldAdjDemandForeActualException, SystemException {
        return remove((Serializable) ivldAdjustedDemandForecastSid);
    }

    /**
     * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual remove(Serializable primaryKey)
        throws NoSuchVwIvldAdjDemandForeActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual) session.get(VwIvldAdjDemandForeActualImpl.class,
                    primaryKey);

            if (vwIvldAdjDemandForeActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwIvldAdjDemandForeActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwIvldAdjDemandForeActual);
        } catch (NoSuchVwIvldAdjDemandForeActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwIvldAdjDemandForeActual removeImpl(
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws SystemException {
        vwIvldAdjDemandForeActual = toUnwrappedModel(vwIvldAdjDemandForeActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwIvldAdjDemandForeActual)) {
                vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual) session.get(VwIvldAdjDemandForeActualImpl.class,
                        vwIvldAdjDemandForeActual.getPrimaryKeyObj());
            }

            if (vwIvldAdjDemandForeActual != null) {
                session.delete(vwIvldAdjDemandForeActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwIvldAdjDemandForeActual != null) {
            clearCache(vwIvldAdjDemandForeActual);
        }

        return vwIvldAdjDemandForeActual;
    }

    @Override
    public VwIvldAdjDemandForeActual updateImpl(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws SystemException {
        vwIvldAdjDemandForeActual = toUnwrappedModel(vwIvldAdjDemandForeActual);

        boolean isNew = vwIvldAdjDemandForeActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwIvldAdjDemandForeActual.isNew()) {
                session.save(vwIvldAdjDemandForeActual);

                vwIvldAdjDemandForeActual.setNew(false);
            } else {
                session.merge(vwIvldAdjDemandForeActual);
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

        EntityCacheUtil.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldAdjDemandForeActualImpl.class,
            vwIvldAdjDemandForeActual.getPrimaryKey(), vwIvldAdjDemandForeActual);

        return vwIvldAdjDemandForeActual;
    }

    protected VwIvldAdjDemandForeActual toUnwrappedModel(
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        if (vwIvldAdjDemandForeActual instanceof VwIvldAdjDemandForeActualImpl) {
            return vwIvldAdjDemandForeActual;
        }

        VwIvldAdjDemandForeActualImpl vwIvldAdjDemandForeActualImpl = new VwIvldAdjDemandForeActualImpl();

        vwIvldAdjDemandForeActualImpl.setNew(vwIvldAdjDemandForeActual.isNew());
        vwIvldAdjDemandForeActualImpl.setPrimaryKey(vwIvldAdjDemandForeActual.getPrimaryKey());

        vwIvldAdjDemandForeActualImpl.setForecastVersion(vwIvldAdjDemandForeActual.getForecastVersion());
        vwIvldAdjDemandForeActualImpl.setGrossUnits(vwIvldAdjDemandForeActual.getGrossUnits());
        vwIvldAdjDemandForeActualImpl.setBusinessUnitNo(vwIvldAdjDemandForeActual.getBusinessUnitNo());
        vwIvldAdjDemandForeActualImpl.setYear(vwIvldAdjDemandForeActual.getYear());
        vwIvldAdjDemandForeActualImpl.setItemId(vwIvldAdjDemandForeActual.getItemId());
        vwIvldAdjDemandForeActualImpl.setBrandName(vwIvldAdjDemandForeActual.getBrandName());
        vwIvldAdjDemandForeActualImpl.setModifiedDate(vwIvldAdjDemandForeActual.getModifiedDate());
        vwIvldAdjDemandForeActualImpl.setOrganizationKey(vwIvldAdjDemandForeActual.getOrganizationKey());
        vwIvldAdjDemandForeActualImpl.setCreatedDate(vwIvldAdjDemandForeActual.getCreatedDate());
        vwIvldAdjDemandForeActualImpl.setCreatedBy(vwIvldAdjDemandForeActual.getCreatedBy());
        vwIvldAdjDemandForeActualImpl.setSource(vwIvldAdjDemandForeActual.getSource());
        vwIvldAdjDemandForeActualImpl.setMarketShareRatio(vwIvldAdjDemandForeActual.getMarketShareRatio());
        vwIvldAdjDemandForeActualImpl.setBusinessUnitName(vwIvldAdjDemandForeActual.getBusinessUnitName());
        vwIvldAdjDemandForeActualImpl.setAddChgDelIndicator(vwIvldAdjDemandForeActual.getAddChgDelIndicator());
        vwIvldAdjDemandForeActualImpl.setItemIdentifier(vwIvldAdjDemandForeActual.getItemIdentifier());
        vwIvldAdjDemandForeActualImpl.setErrorCode(vwIvldAdjDemandForeActual.getErrorCode());
        vwIvldAdjDemandForeActualImpl.setModifiedBy(vwIvldAdjDemandForeActual.getModifiedBy());
        vwIvldAdjDemandForeActualImpl.setMarketShareUnits(vwIvldAdjDemandForeActual.getMarketShareUnits());
        vwIvldAdjDemandForeActualImpl.setMonth(vwIvldAdjDemandForeActual.getMonth());
        vwIvldAdjDemandForeActualImpl.setInventoryUnitChange(vwIvldAdjDemandForeActual.getInventoryUnitChange());
        vwIvldAdjDemandForeActualImpl.setReprocessedFlag(vwIvldAdjDemandForeActual.getReprocessedFlag());
        vwIvldAdjDemandForeActualImpl.setUncapturedUnitsRatio(vwIvldAdjDemandForeActual.getUncapturedUnitsRatio());
        vwIvldAdjDemandForeActualImpl.setReasonForFailure(vwIvldAdjDemandForeActual.getReasonForFailure());
        vwIvldAdjDemandForeActualImpl.setAdjustedDemandForecastIntfId(vwIvldAdjDemandForeActual.getAdjustedDemandForecastIntfId());
        vwIvldAdjDemandForeActualImpl.setCountry(vwIvldAdjDemandForeActual.getCountry());
        vwIvldAdjDemandForeActualImpl.setForecastType(vwIvldAdjDemandForeActual.getForecastType());
        vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandUnits(vwIvldAdjDemandForeActual.getTotalAdjustedDemandUnits());
        vwIvldAdjDemandForeActualImpl.setBrandId(vwIvldAdjDemandForeActual.getBrandId());
        vwIvldAdjDemandForeActualImpl.setIsForecast(vwIvldAdjDemandForeActual.getIsForecast());
        vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandAmount(vwIvldAdjDemandForeActual.getTotalAdjustedDemandAmount());
        vwIvldAdjDemandForeActualImpl.setUncapturedUnits(vwIvldAdjDemandForeActual.getUncapturedUnits());
        vwIvldAdjDemandForeActualImpl.setGrossPrice(vwIvldAdjDemandForeActual.getGrossPrice());
        vwIvldAdjDemandForeActualImpl.setGrossAmount(vwIvldAdjDemandForeActual.getGrossAmount());
        vwIvldAdjDemandForeActualImpl.setItemIdentifierCodeQualifier(vwIvldAdjDemandForeActual.getItemIdentifierCodeQualifier());
        vwIvldAdjDemandForeActualImpl.setBatchId(vwIvldAdjDemandForeActual.getBatchId());
        vwIvldAdjDemandForeActualImpl.setItemName(vwIvldAdjDemandForeActual.getItemName());
        vwIvldAdjDemandForeActualImpl.setErrorField(vwIvldAdjDemandForeActual.getErrorField());
        vwIvldAdjDemandForeActualImpl.setNetSalesPrice(vwIvldAdjDemandForeActual.getNetSalesPrice());
        vwIvldAdjDemandForeActualImpl.setNetSalesAmount(vwIvldAdjDemandForeActual.getNetSalesAmount());
        vwIvldAdjDemandForeActualImpl.setSegment(vwIvldAdjDemandForeActual.getSegment());
        vwIvldAdjDemandForeActualImpl.setForecastName(vwIvldAdjDemandForeActual.getForecastName());
        vwIvldAdjDemandForeActualImpl.setIvldAdjustedDemandForecastSid(vwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid());
        vwIvldAdjDemandForeActualImpl.setMarketSizeUnits(vwIvldAdjDemandForeActual.getMarketSizeUnits());
        vwIvldAdjDemandForeActualImpl.setCheckRecord(vwIvldAdjDemandForeActual.isCheckRecord());

        return vwIvldAdjDemandForeActualImpl;
    }

    /**
     * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual
     * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwIvldAdjDemandForeActualException, SystemException {
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = fetchByPrimaryKey(primaryKey);

        if (vwIvldAdjDemandForeActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwIvldAdjDemandForeActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwIvldAdjDemandForeActual;
    }

    /**
     * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException} if it could not be found.
     *
     * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual
     * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual findByPrimaryKey(
        int ivldAdjustedDemandForecastSid)
        throws NoSuchVwIvldAdjDemandForeActualException, SystemException {
        return findByPrimaryKey((Serializable) ivldAdjustedDemandForecastSid);
    }

    /**
     * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual) EntityCacheUtil.getResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldAdjDemandForeActualImpl.class, primaryKey);

        if (vwIvldAdjDemandForeActual == _nullVwIvldAdjDemandForeActual) {
            return null;
        }

        if (vwIvldAdjDemandForeActual == null) {
            Session session = null;

            try {
                session = openSession();

                vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual) session.get(VwIvldAdjDemandForeActualImpl.class,
                        primaryKey);

                if (vwIvldAdjDemandForeActual != null) {
                    cacheResult(vwIvldAdjDemandForeActual);
                } else {
                    EntityCacheUtil.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldAdjDemandForeActualImpl.class, primaryKey,
                        _nullVwIvldAdjDemandForeActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
                    VwIvldAdjDemandForeActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwIvldAdjDemandForeActual;
    }

    /**
     * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
     * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldAdjDemandForeActual fetchByPrimaryKey(
        int ivldAdjustedDemandForecastSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldAdjustedDemandForecastSid);
    }

    /**
     * Returns all the vw ivld adj demand fore actuals.
     *
     * @return the vw ivld adj demand fore actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldAdjDemandForeActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw ivld adj demand fore actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld adj demand fore actuals
     * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
     * @return the range of vw ivld adj demand fore actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldAdjDemandForeActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw ivld adj demand fore actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld adj demand fore actuals
     * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw ivld adj demand fore actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldAdjDemandForeActual> findAll(int start, int end,
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

        List<VwIvldAdjDemandForeActual> list = (List<VwIvldAdjDemandForeActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL;

                if (pagination) {
                    sql = sql.concat(VwIvldAdjDemandForeActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwIvldAdjDemandForeActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwIvldAdjDemandForeActual>(list);
                } else {
                    list = (List<VwIvldAdjDemandForeActual>) QueryUtil.list(q,
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
     * Removes all the vw ivld adj demand fore actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : findAll()) {
            remove(vwIvldAdjDemandForeActual);
        }
    }

    /**
     * Returns the number of vw ivld adj demand fore actuals.
     *
     * @return the number of vw ivld adj demand fore actuals
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

                Query q = session.createQuery(_SQL_COUNT_VWIVLDADJDEMANDFOREACTUAL);

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
     * Initializes the vw ivld adj demand fore actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwIvldAdjDemandForeActual>> listenersList = new ArrayList<ModelListener<VwIvldAdjDemandForeActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwIvldAdjDemandForeActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwIvldAdjDemandForeActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
