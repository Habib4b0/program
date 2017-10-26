package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldDemandForecastException;
import com.stpl.app.model.IvldDemandForecast;
import com.stpl.app.model.impl.IvldDemandForecastImpl;
import com.stpl.app.model.impl.IvldDemandForecastModelImpl;
import com.stpl.app.service.persistence.IvldDemandForecastPersistence;

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
 * The persistence implementation for the ivld demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandForecastPersistence
 * @see IvldDemandForecastUtil
 * @generated
 */
public class IvldDemandForecastPersistenceImpl extends BasePersistenceImpl<IvldDemandForecast>
    implements IvldDemandForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldDemandForecastUtil} to access the ivld demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldDemandForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED,
            IvldDemandForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED,
            IvldDemandForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDDEMANDFORECAST = "SELECT ivldDemandForecast FROM IvldDemandForecast ivldDemandForecast";
    private static final String _SQL_COUNT_IVLDDEMANDFORECAST = "SELECT COUNT(ivldDemandForecast) FROM IvldDemandForecast ivldDemandForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldDemandForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldDemandForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldDemandForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastYear", "grossUnits", "totalDemandUnits", "itemId",
                "modifiedDate", "organizationKey", "ivldDemandForecastSid",
                "source", "marketShareRatio", "createdBy", "createdDate",
                "addChgDelIndicator", "itemIdentifier", "errorCode",
                "intfInsertedDate", "modifiedBy", "marketShareUnits",
                "inventoryUnitChange", "reprocessedFlag", "uncapturedUnitsRatio",
                "reasonForFailure", "country", "forecastType", "brandId",
                "demandForecastInterfaceId", "uncapturedUnits", "grossPrice",
                "grossAmount", "itemIdentifierCodeQualifier", "forecastVer",
                "batchId", "forecastMonth", "errorField", "netSalesPrice",
                "netSalesAmount", "segment", "totalDemandAmount", "forecastName",
                "marketSizeUnits", "checkRecord"
            });
    private static IvldDemandForecast _nullIvldDemandForecast = new IvldDemandForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldDemandForecast> toCacheModel() {
                return _nullIvldDemandForecastCacheModel;
            }
        };

    private static CacheModel<IvldDemandForecast> _nullIvldDemandForecastCacheModel =
        new CacheModel<IvldDemandForecast>() {
            @Override
            public IvldDemandForecast toEntityModel() {
                return _nullIvldDemandForecast;
            }
        };

    public IvldDemandForecastPersistenceImpl() {
        setModelClass(IvldDemandForecast.class);
    }

    /**
     * Caches the ivld demand forecast in the entity cache if it is enabled.
     *
     * @param ivldDemandForecast the ivld demand forecast
     */
    @Override
    public void cacheResult(IvldDemandForecast ivldDemandForecast) {
        EntityCacheUtil.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey(),
            ivldDemandForecast);

        ivldDemandForecast.resetOriginalValues();
    }

    /**
     * Caches the ivld demand forecasts in the entity cache if it is enabled.
     *
     * @param ivldDemandForecasts the ivld demand forecasts
     */
    @Override
    public void cacheResult(List<IvldDemandForecast> ivldDemandForecasts) {
        for (IvldDemandForecast ivldDemandForecast : ivldDemandForecasts) {
            if (EntityCacheUtil.getResult(
                        IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        IvldDemandForecastImpl.class,
                        ivldDemandForecast.getPrimaryKey()) == null) {
                cacheResult(ivldDemandForecast);
            } else {
                ivldDemandForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld demand forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldDemandForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldDemandForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld demand forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldDemandForecast ivldDemandForecast) {
        EntityCacheUtil.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldDemandForecast> ivldDemandForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldDemandForecast ivldDemandForecast : ivldDemandForecasts) {
            EntityCacheUtil.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld demand forecast with the primary key. Does not add the ivld demand forecast to the database.
     *
     * @param ivldDemandForecastSid the primary key for the new ivld demand forecast
     * @return the new ivld demand forecast
     */
    @Override
    public IvldDemandForecast create(int ivldDemandForecastSid) {
        IvldDemandForecast ivldDemandForecast = new IvldDemandForecastImpl();

        ivldDemandForecast.setNew(true);
        ivldDemandForecast.setPrimaryKey(ivldDemandForecastSid);

        return ivldDemandForecast;
    }

    /**
     * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldDemandForecastSid the primary key of the ivld demand forecast
     * @return the ivld demand forecast that was removed
     * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast remove(int ivldDemandForecastSid)
        throws NoSuchIvldDemandForecastException, SystemException {
        return remove((Serializable) ivldDemandForecastSid);
    }

    /**
     * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld demand forecast
     * @return the ivld demand forecast that was removed
     * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast remove(Serializable primaryKey)
        throws NoSuchIvldDemandForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldDemandForecast ivldDemandForecast = (IvldDemandForecast) session.get(IvldDemandForecastImpl.class,
                    primaryKey);

            if (ivldDemandForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldDemandForecast);
        } catch (NoSuchIvldDemandForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldDemandForecast removeImpl(
        IvldDemandForecast ivldDemandForecast) throws SystemException {
        ivldDemandForecast = toUnwrappedModel(ivldDemandForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldDemandForecast)) {
                ivldDemandForecast = (IvldDemandForecast) session.get(IvldDemandForecastImpl.class,
                        ivldDemandForecast.getPrimaryKeyObj());
            }

            if (ivldDemandForecast != null) {
                session.delete(ivldDemandForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldDemandForecast != null) {
            clearCache(ivldDemandForecast);
        }

        return ivldDemandForecast;
    }

    @Override
    public IvldDemandForecast updateImpl(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws SystemException {
        ivldDemandForecast = toUnwrappedModel(ivldDemandForecast);

        boolean isNew = ivldDemandForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldDemandForecast.isNew()) {
                session.save(ivldDemandForecast);

                ivldDemandForecast.setNew(false);
            } else {
                session.merge(ivldDemandForecast);
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

        EntityCacheUtil.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey(),
            ivldDemandForecast);

        return ivldDemandForecast;
    }

    protected IvldDemandForecast toUnwrappedModel(
        IvldDemandForecast ivldDemandForecast) {
        if (ivldDemandForecast instanceof IvldDemandForecastImpl) {
            return ivldDemandForecast;
        }

        IvldDemandForecastImpl ivldDemandForecastImpl = new IvldDemandForecastImpl();

        ivldDemandForecastImpl.setNew(ivldDemandForecast.isNew());
        ivldDemandForecastImpl.setPrimaryKey(ivldDemandForecast.getPrimaryKey());

        ivldDemandForecastImpl.setForecastYear(ivldDemandForecast.getForecastYear());
        ivldDemandForecastImpl.setGrossUnits(ivldDemandForecast.getGrossUnits());
        ivldDemandForecastImpl.setTotalDemandUnits(ivldDemandForecast.getTotalDemandUnits());
        ivldDemandForecastImpl.setItemId(ivldDemandForecast.getItemId());
        ivldDemandForecastImpl.setModifiedDate(ivldDemandForecast.getModifiedDate());
        ivldDemandForecastImpl.setOrganizationKey(ivldDemandForecast.getOrganizationKey());
        ivldDemandForecastImpl.setIvldDemandForecastSid(ivldDemandForecast.getIvldDemandForecastSid());
        ivldDemandForecastImpl.setSource(ivldDemandForecast.getSource());
        ivldDemandForecastImpl.setMarketShareRatio(ivldDemandForecast.getMarketShareRatio());
        ivldDemandForecastImpl.setCreatedBy(ivldDemandForecast.getCreatedBy());
        ivldDemandForecastImpl.setCreatedDate(ivldDemandForecast.getCreatedDate());
        ivldDemandForecastImpl.setAddChgDelIndicator(ivldDemandForecast.getAddChgDelIndicator());
        ivldDemandForecastImpl.setItemIdentifier(ivldDemandForecast.getItemIdentifier());
        ivldDemandForecastImpl.setErrorCode(ivldDemandForecast.getErrorCode());
        ivldDemandForecastImpl.setIntfInsertedDate(ivldDemandForecast.getIntfInsertedDate());
        ivldDemandForecastImpl.setModifiedBy(ivldDemandForecast.getModifiedBy());
        ivldDemandForecastImpl.setMarketShareUnits(ivldDemandForecast.getMarketShareUnits());
        ivldDemandForecastImpl.setInventoryUnitChange(ivldDemandForecast.getInventoryUnitChange());
        ivldDemandForecastImpl.setReprocessedFlag(ivldDemandForecast.getReprocessedFlag());
        ivldDemandForecastImpl.setUncapturedUnitsRatio(ivldDemandForecast.getUncapturedUnitsRatio());
        ivldDemandForecastImpl.setReasonForFailure(ivldDemandForecast.getReasonForFailure());
        ivldDemandForecastImpl.setCountry(ivldDemandForecast.getCountry());
        ivldDemandForecastImpl.setForecastType(ivldDemandForecast.getForecastType());
        ivldDemandForecastImpl.setBrandId(ivldDemandForecast.getBrandId());
        ivldDemandForecastImpl.setDemandForecastInterfaceId(ivldDemandForecast.getDemandForecastInterfaceId());
        ivldDemandForecastImpl.setUncapturedUnits(ivldDemandForecast.getUncapturedUnits());
        ivldDemandForecastImpl.setGrossPrice(ivldDemandForecast.getGrossPrice());
        ivldDemandForecastImpl.setGrossAmount(ivldDemandForecast.getGrossAmount());
        ivldDemandForecastImpl.setItemIdentifierCodeQualifier(ivldDemandForecast.getItemIdentifierCodeQualifier());
        ivldDemandForecastImpl.setForecastVer(ivldDemandForecast.getForecastVer());
        ivldDemandForecastImpl.setBatchId(ivldDemandForecast.getBatchId());
        ivldDemandForecastImpl.setForecastMonth(ivldDemandForecast.getForecastMonth());
        ivldDemandForecastImpl.setErrorField(ivldDemandForecast.getErrorField());
        ivldDemandForecastImpl.setNetSalesPrice(ivldDemandForecast.getNetSalesPrice());
        ivldDemandForecastImpl.setNetSalesAmount(ivldDemandForecast.getNetSalesAmount());
        ivldDemandForecastImpl.setSegment(ivldDemandForecast.getSegment());
        ivldDemandForecastImpl.setTotalDemandAmount(ivldDemandForecast.getTotalDemandAmount());
        ivldDemandForecastImpl.setForecastName(ivldDemandForecast.getForecastName());
        ivldDemandForecastImpl.setMarketSizeUnits(ivldDemandForecast.getMarketSizeUnits());
        ivldDemandForecastImpl.setCheckRecord(ivldDemandForecast.isCheckRecord());

        return ivldDemandForecastImpl;
    }

    /**
     * Returns the ivld demand forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld demand forecast
     * @return the ivld demand forecast
     * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldDemandForecastException, SystemException {
        IvldDemandForecast ivldDemandForecast = fetchByPrimaryKey(primaryKey);

        if (ivldDemandForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldDemandForecast;
    }

    /**
     * Returns the ivld demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchIvldDemandForecastException} if it could not be found.
     *
     * @param ivldDemandForecastSid the primary key of the ivld demand forecast
     * @return the ivld demand forecast
     * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast findByPrimaryKey(int ivldDemandForecastSid)
        throws NoSuchIvldDemandForecastException, SystemException {
        return findByPrimaryKey((Serializable) ivldDemandForecastSid);
    }

    /**
     * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld demand forecast
     * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldDemandForecast ivldDemandForecast = (IvldDemandForecast) EntityCacheUtil.getResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                IvldDemandForecastImpl.class, primaryKey);

        if (ivldDemandForecast == _nullIvldDemandForecast) {
            return null;
        }

        if (ivldDemandForecast == null) {
            Session session = null;

            try {
                session = openSession();

                ivldDemandForecast = (IvldDemandForecast) session.get(IvldDemandForecastImpl.class,
                        primaryKey);

                if (ivldDemandForecast != null) {
                    cacheResult(ivldDemandForecast);
                } else {
                    EntityCacheUtil.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        IvldDemandForecastImpl.class, primaryKey,
                        _nullIvldDemandForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                    IvldDemandForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldDemandForecast;
    }

    /**
     * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldDemandForecastSid the primary key of the ivld demand forecast
     * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandForecast fetchByPrimaryKey(int ivldDemandForecastSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldDemandForecastSid);
    }

    /**
     * Returns all the ivld demand forecasts.
     *
     * @return the ivld demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld demand forecasts
     * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
     * @return the range of ivld demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld demand forecasts
     * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandForecast> findAll(int start, int end,
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

        List<IvldDemandForecast> list = (List<IvldDemandForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDDEMANDFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDDEMANDFORECAST;

                if (pagination) {
                    sql = sql.concat(IvldDemandForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldDemandForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldDemandForecast>(list);
                } else {
                    list = (List<IvldDemandForecast>) QueryUtil.list(q,
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
     * Removes all the ivld demand forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldDemandForecast ivldDemandForecast : findAll()) {
            remove(ivldDemandForecast);
        }
    }

    /**
     * Returns the number of ivld demand forecasts.
     *
     * @return the number of ivld demand forecasts
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

                Query q = session.createQuery(_SQL_COUNT_IVLDDEMANDFORECAST);

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
     * Initializes the ivld demand forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldDemandForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldDemandForecast>> listenersList = new ArrayList<ModelListener<IvldDemandForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldDemandForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldDemandForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
