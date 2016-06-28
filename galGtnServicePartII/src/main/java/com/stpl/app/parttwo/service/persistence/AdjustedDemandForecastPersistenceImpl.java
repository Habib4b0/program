package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.model.impl.AdjustedDemandForecastImpl;
import com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastPersistence;

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
 * The persistence implementation for the adjusted demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdjustedDemandForecastPersistence
 * @see AdjustedDemandForecastUtil
 * @generated
 */
public class AdjustedDemandForecastPersistenceImpl extends BasePersistenceImpl<AdjustedDemandForecast>
    implements AdjustedDemandForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AdjustedDemandForecastUtil} to access the adjusted demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AdjustedDemandForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED,
            AdjustedDemandForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED,
            AdjustedDemandForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ADJUSTEDDEMANDFORECAST = "SELECT adjustedDemandForecast FROM AdjustedDemandForecast adjustedDemandForecast";
    private static final String _SQL_COUNT_ADJUSTEDDEMANDFORECAST = "SELECT COUNT(adjustedDemandForecast) FROM AdjustedDemandForecast adjustedDemandForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "adjustedDemandForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AdjustedDemandForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AdjustedDemandForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "adjustedDemandForecastSid", "grossUnits",
                "totalDemandUnits", "year", "itemId", "modifiedDate",
                "brandMasterSid", "organizationKey", "source", "createdDate",
                "createdBy", "marketShareRatio", "itemIdentifier",
                "inboundStatus", "modifiedBy", "marketShareUnits", "month",
                "inventoryUnitChange", "uncapturedUnitsRatio", "country",
                "forecastType", "brandId", "uncapturedUnits", "grossPrice",
                "recordLockStatus", "grossAmount", "itemIdentifierCodeQualifier",
                "forecastVer", "batchId", "netSalesPrice", "netSalesAmount",
                "segment", "totalDemandAmount", "forecastName",
                "marketSizeUnits"
            });
    private static AdjustedDemandForecast _nullAdjustedDemandForecast = new AdjustedDemandForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AdjustedDemandForecast> toCacheModel() {
                return _nullAdjustedDemandForecastCacheModel;
            }
        };

    private static CacheModel<AdjustedDemandForecast> _nullAdjustedDemandForecastCacheModel =
        new CacheModel<AdjustedDemandForecast>() {
            @Override
            public AdjustedDemandForecast toEntityModel() {
                return _nullAdjustedDemandForecast;
            }
        };

    public AdjustedDemandForecastPersistenceImpl() {
        setModelClass(AdjustedDemandForecast.class);
    }

    /**
     * Caches the adjusted demand forecast in the entity cache if it is enabled.
     *
     * @param adjustedDemandForecast the adjusted demand forecast
     */
    @Override
    public void cacheResult(AdjustedDemandForecast adjustedDemandForecast) {
        EntityCacheUtil.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastImpl.class,
            adjustedDemandForecast.getPrimaryKey(), adjustedDemandForecast);

        adjustedDemandForecast.resetOriginalValues();
    }

    /**
     * Caches the adjusted demand forecasts in the entity cache if it is enabled.
     *
     * @param adjustedDemandForecasts the adjusted demand forecasts
     */
    @Override
    public void cacheResult(
        List<AdjustedDemandForecast> adjustedDemandForecasts) {
        for (AdjustedDemandForecast adjustedDemandForecast : adjustedDemandForecasts) {
            if (EntityCacheUtil.getResult(
                        AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        AdjustedDemandForecastImpl.class,
                        adjustedDemandForecast.getPrimaryKey()) == null) {
                cacheResult(adjustedDemandForecast);
            } else {
                adjustedDemandForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all adjusted demand forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AdjustedDemandForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AdjustedDemandForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the adjusted demand forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AdjustedDemandForecast adjustedDemandForecast) {
        EntityCacheUtil.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastImpl.class,
            adjustedDemandForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AdjustedDemandForecast> adjustedDemandForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AdjustedDemandForecast adjustedDemandForecast : adjustedDemandForecasts) {
            EntityCacheUtil.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                AdjustedDemandForecastImpl.class,
                adjustedDemandForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
     *
     * @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
     * @return the new adjusted demand forecast
     */
    @Override
    public AdjustedDemandForecast create(int adjustedDemandForecastSid) {
        AdjustedDemandForecast adjustedDemandForecast = new AdjustedDemandForecastImpl();

        adjustedDemandForecast.setNew(true);
        adjustedDemandForecast.setPrimaryKey(adjustedDemandForecastSid);

        return adjustedDemandForecast;
    }

    /**
     * Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast remove(int adjustedDemandForecastSid)
        throws NoSuchAdjustedDemandForecastException, SystemException {
        return remove((Serializable) adjustedDemandForecastSid);
    }

    /**
     * Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast remove(Serializable primaryKey)
        throws NoSuchAdjustedDemandForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AdjustedDemandForecast adjustedDemandForecast = (AdjustedDemandForecast) session.get(AdjustedDemandForecastImpl.class,
                    primaryKey);

            if (adjustedDemandForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAdjustedDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(adjustedDemandForecast);
        } catch (NoSuchAdjustedDemandForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AdjustedDemandForecast removeImpl(
        AdjustedDemandForecast adjustedDemandForecast)
        throws SystemException {
        adjustedDemandForecast = toUnwrappedModel(adjustedDemandForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(adjustedDemandForecast)) {
                adjustedDemandForecast = (AdjustedDemandForecast) session.get(AdjustedDemandForecastImpl.class,
                        adjustedDemandForecast.getPrimaryKeyObj());
            }

            if (adjustedDemandForecast != null) {
                session.delete(adjustedDemandForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (adjustedDemandForecast != null) {
            clearCache(adjustedDemandForecast);
        }

        return adjustedDemandForecast;
    }

    @Override
    public AdjustedDemandForecast updateImpl(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast)
        throws SystemException {
        adjustedDemandForecast = toUnwrappedModel(adjustedDemandForecast);

        boolean isNew = adjustedDemandForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (adjustedDemandForecast.isNew()) {
                session.save(adjustedDemandForecast);

                adjustedDemandForecast.setNew(false);
            } else {
                session.merge(adjustedDemandForecast);
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

        EntityCacheUtil.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            AdjustedDemandForecastImpl.class,
            adjustedDemandForecast.getPrimaryKey(), adjustedDemandForecast);

        return adjustedDemandForecast;
    }

    protected AdjustedDemandForecast toUnwrappedModel(
        AdjustedDemandForecast adjustedDemandForecast) {
        if (adjustedDemandForecast instanceof AdjustedDemandForecastImpl) {
            return adjustedDemandForecast;
        }

        AdjustedDemandForecastImpl adjustedDemandForecastImpl = new AdjustedDemandForecastImpl();

        adjustedDemandForecastImpl.setNew(adjustedDemandForecast.isNew());
        adjustedDemandForecastImpl.setPrimaryKey(adjustedDemandForecast.getPrimaryKey());

        adjustedDemandForecastImpl.setItemMasterSid(adjustedDemandForecast.getItemMasterSid());
        adjustedDemandForecastImpl.setAdjustedDemandForecastSid(adjustedDemandForecast.getAdjustedDemandForecastSid());
        adjustedDemandForecastImpl.setGrossUnits(adjustedDemandForecast.getGrossUnits());
        adjustedDemandForecastImpl.setTotalDemandUnits(adjustedDemandForecast.getTotalDemandUnits());
        adjustedDemandForecastImpl.setYear(adjustedDemandForecast.getYear());
        adjustedDemandForecastImpl.setItemId(adjustedDemandForecast.getItemId());
        adjustedDemandForecastImpl.setModifiedDate(adjustedDemandForecast.getModifiedDate());
        adjustedDemandForecastImpl.setBrandMasterSid(adjustedDemandForecast.getBrandMasterSid());
        adjustedDemandForecastImpl.setOrganizationKey(adjustedDemandForecast.getOrganizationKey());
        adjustedDemandForecastImpl.setSource(adjustedDemandForecast.getSource());
        adjustedDemandForecastImpl.setCreatedDate(adjustedDemandForecast.getCreatedDate());
        adjustedDemandForecastImpl.setCreatedBy(adjustedDemandForecast.getCreatedBy());
        adjustedDemandForecastImpl.setMarketShareRatio(adjustedDemandForecast.getMarketShareRatio());
        adjustedDemandForecastImpl.setItemIdentifier(adjustedDemandForecast.getItemIdentifier());
        adjustedDemandForecastImpl.setInboundStatus(adjustedDemandForecast.getInboundStatus());
        adjustedDemandForecastImpl.setModifiedBy(adjustedDemandForecast.getModifiedBy());
        adjustedDemandForecastImpl.setMarketShareUnits(adjustedDemandForecast.getMarketShareUnits());
        adjustedDemandForecastImpl.setMonth(adjustedDemandForecast.getMonth());
        adjustedDemandForecastImpl.setInventoryUnitChange(adjustedDemandForecast.getInventoryUnitChange());
        adjustedDemandForecastImpl.setUncapturedUnitsRatio(adjustedDemandForecast.getUncapturedUnitsRatio());
        adjustedDemandForecastImpl.setCountry(adjustedDemandForecast.getCountry());
        adjustedDemandForecastImpl.setForecastType(adjustedDemandForecast.getForecastType());
        adjustedDemandForecastImpl.setBrandId(adjustedDemandForecast.getBrandId());
        adjustedDemandForecastImpl.setUncapturedUnits(adjustedDemandForecast.getUncapturedUnits());
        adjustedDemandForecastImpl.setGrossPrice(adjustedDemandForecast.getGrossPrice());
        adjustedDemandForecastImpl.setRecordLockStatus(adjustedDemandForecast.isRecordLockStatus());
        adjustedDemandForecastImpl.setGrossAmount(adjustedDemandForecast.getGrossAmount());
        adjustedDemandForecastImpl.setItemIdentifierCodeQualifier(adjustedDemandForecast.getItemIdentifierCodeQualifier());
        adjustedDemandForecastImpl.setForecastVer(adjustedDemandForecast.getForecastVer());
        adjustedDemandForecastImpl.setBatchId(adjustedDemandForecast.getBatchId());
        adjustedDemandForecastImpl.setNetSalesPrice(adjustedDemandForecast.getNetSalesPrice());
        adjustedDemandForecastImpl.setNetSalesAmount(adjustedDemandForecast.getNetSalesAmount());
        adjustedDemandForecastImpl.setSegment(adjustedDemandForecast.getSegment());
        adjustedDemandForecastImpl.setTotalDemandAmount(adjustedDemandForecast.getTotalDemandAmount());
        adjustedDemandForecastImpl.setForecastName(adjustedDemandForecast.getForecastName());
        adjustedDemandForecastImpl.setMarketSizeUnits(adjustedDemandForecast.getMarketSizeUnits());

        return adjustedDemandForecastImpl;
    }

    /**
     * Returns the adjusted demand forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast
     * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAdjustedDemandForecastException, SystemException {
        AdjustedDemandForecast adjustedDemandForecast = fetchByPrimaryKey(primaryKey);

        if (adjustedDemandForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAdjustedDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return adjustedDemandForecast;
    }

    /**
     * Returns the adjusted demand forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException} if it could not be found.
     *
     * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast
     * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast findByPrimaryKey(
        int adjustedDemandForecastSid)
        throws NoSuchAdjustedDemandForecastException, SystemException {
        return findByPrimaryKey((Serializable) adjustedDemandForecastSid);
    }

    /**
     * Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AdjustedDemandForecast adjustedDemandForecast = (AdjustedDemandForecast) EntityCacheUtil.getResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                AdjustedDemandForecastImpl.class, primaryKey);

        if (adjustedDemandForecast == _nullAdjustedDemandForecast) {
            return null;
        }

        if (adjustedDemandForecast == null) {
            Session session = null;

            try {
                session = openSession();

                adjustedDemandForecast = (AdjustedDemandForecast) session.get(AdjustedDemandForecastImpl.class,
                        primaryKey);

                if (adjustedDemandForecast != null) {
                    cacheResult(adjustedDemandForecast);
                } else {
                    EntityCacheUtil.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        AdjustedDemandForecastImpl.class, primaryKey,
                        _nullAdjustedDemandForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                    AdjustedDemandForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return adjustedDemandForecast;
    }

    /**
     * Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
     * @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AdjustedDemandForecast fetchByPrimaryKey(
        int adjustedDemandForecastSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) adjustedDemandForecastSid);
    }

    /**
     * Returns all the adjusted demand forecasts.
     *
     * @return the adjusted demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdjustedDemandForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the adjusted demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of adjusted demand forecasts
     * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
     * @return the range of adjusted demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdjustedDemandForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the adjusted demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of adjusted demand forecasts
     * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of adjusted demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AdjustedDemandForecast> findAll(int start, int end,
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

        List<AdjustedDemandForecast> list = (List<AdjustedDemandForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ADJUSTEDDEMANDFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ADJUSTEDDEMANDFORECAST;

                if (pagination) {
                    sql = sql.concat(AdjustedDemandForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AdjustedDemandForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AdjustedDemandForecast>(list);
                } else {
                    list = (List<AdjustedDemandForecast>) QueryUtil.list(q,
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
     * Removes all the adjusted demand forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AdjustedDemandForecast adjustedDemandForecast : findAll()) {
            remove(adjustedDemandForecast);
        }
    }

    /**
     * Returns the number of adjusted demand forecasts.
     *
     * @return the number of adjusted demand forecasts
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

                Query q = session.createQuery(_SQL_COUNT_ADJUSTEDDEMANDFORECAST);

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
     * Initializes the adjusted demand forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AdjustedDemandForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AdjustedDemandForecast>> listenersList = new ArrayList<ModelListener<AdjustedDemandForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AdjustedDemandForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AdjustedDemandForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
