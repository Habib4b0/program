package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDemandForecastException;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.impl.DemandForecastImpl;
import com.stpl.app.model.impl.DemandForecastModelImpl;
import com.stpl.app.service.persistence.DemandForecastPersistence;

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
 * The persistence implementation for the demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DemandForecastPersistence
 * @see DemandForecastUtil
 * @generated
 */
public class DemandForecastPersistenceImpl extends BasePersistenceImpl<DemandForecast>
    implements DemandForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DemandForecastUtil} to access the demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DemandForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastModelImpl.FINDER_CACHE_ENABLED,
            DemandForecastImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastModelImpl.FINDER_CACHE_ENABLED,
            DemandForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEMANDFORECAST = "SELECT demandForecast FROM DemandForecast demandForecast";
    private static final String _SQL_COUNT_DEMANDFORECAST = "SELECT COUNT(demandForecast) FROM DemandForecast demandForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "demandForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DemandForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DemandForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "modifiedBy", "createdDate", "totalDemandUnits",
                "brandMasterSid", "marketShareUnits", "batchId", "grossAmount",
                "forecastVer", "brandId", "grossUnits", "country",
                "demandForecastSid", "forecastType", "itemMasterSid",
                "totalDemandAmount", "forecastMonth", "organizationKey",
                "createdBy", "marketSizeUnits", "segment", "forecastYear",
                "itemId", "inventoryUnitChange", "grossPrice", "forecastName",
                "netSalesAmount", "modifiedDate", "itemIdentifier",
                "recordLockStatus", "uncapturedUnitsRatio",
                "itemIdentifierCodeQualifier", "marketShareRatio", "source",
                "uncapturedUnits", "netSalesPrice", "inboundStatus"
            });
    private static DemandForecast _nullDemandForecast = new DemandForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DemandForecast> toCacheModel() {
                return _nullDemandForecastCacheModel;
            }
        };

    private static CacheModel<DemandForecast> _nullDemandForecastCacheModel = new CacheModel<DemandForecast>() {
            @Override
            public DemandForecast toEntityModel() {
                return _nullDemandForecast;
            }
        };

    public DemandForecastPersistenceImpl() {
        setModelClass(DemandForecast.class);
    }

    /**
     * Caches the demand forecast in the entity cache if it is enabled.
     *
     * @param demandForecast the demand forecast
     */
    @Override
    public void cacheResult(DemandForecast demandForecast) {
        EntityCacheUtil.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastImpl.class, demandForecast.getPrimaryKey(),
            demandForecast);

        demandForecast.resetOriginalValues();
    }

    /**
     * Caches the demand forecasts in the entity cache if it is enabled.
     *
     * @param demandForecasts the demand forecasts
     */
    @Override
    public void cacheResult(List<DemandForecast> demandForecasts) {
        for (DemandForecast demandForecast : demandForecasts) {
            if (EntityCacheUtil.getResult(
                        DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        DemandForecastImpl.class, demandForecast.getPrimaryKey()) == null) {
                cacheResult(demandForecast);
            } else {
                demandForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all demand forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DemandForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DemandForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the demand forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DemandForecast demandForecast) {
        EntityCacheUtil.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastImpl.class, demandForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<DemandForecast> demandForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DemandForecast demandForecast : demandForecasts) {
            EntityCacheUtil.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                DemandForecastImpl.class, demandForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new demand forecast with the primary key. Does not add the demand forecast to the database.
     *
     * @param demandForecastSid the primary key for the new demand forecast
     * @return the new demand forecast
     */
    @Override
    public DemandForecast create(int demandForecastSid) {
        DemandForecast demandForecast = new DemandForecastImpl();

        demandForecast.setNew(true);
        demandForecast.setPrimaryKey(demandForecastSid);

        return demandForecast;
    }

    /**
     * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param demandForecastSid the primary key of the demand forecast
     * @return the demand forecast that was removed
     * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast remove(int demandForecastSid)
        throws NoSuchDemandForecastException, SystemException {
        return remove((Serializable) demandForecastSid);
    }

    /**
     * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the demand forecast
     * @return the demand forecast that was removed
     * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast remove(Serializable primaryKey)
        throws NoSuchDemandForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DemandForecast demandForecast = (DemandForecast) session.get(DemandForecastImpl.class,
                    primaryKey);

            if (demandForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(demandForecast);
        } catch (NoSuchDemandForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DemandForecast removeImpl(DemandForecast demandForecast)
        throws SystemException {
        demandForecast = toUnwrappedModel(demandForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(demandForecast)) {
                demandForecast = (DemandForecast) session.get(DemandForecastImpl.class,
                        demandForecast.getPrimaryKeyObj());
            }

            if (demandForecast != null) {
                session.delete(demandForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (demandForecast != null) {
            clearCache(demandForecast);
        }

        return demandForecast;
    }

    @Override
    public DemandForecast updateImpl(
        com.stpl.app.model.DemandForecast demandForecast)
        throws SystemException {
        demandForecast = toUnwrappedModel(demandForecast);

        boolean isNew = demandForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (demandForecast.isNew()) {
                session.save(demandForecast);

                demandForecast.setNew(false);
            } else {
                session.merge(demandForecast);
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

        EntityCacheUtil.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
            DemandForecastImpl.class, demandForecast.getPrimaryKey(),
            demandForecast);

        return demandForecast;
    }

    protected DemandForecast toUnwrappedModel(DemandForecast demandForecast) {
        if (demandForecast instanceof DemandForecastImpl) {
            return demandForecast;
        }

        DemandForecastImpl demandForecastImpl = new DemandForecastImpl();

        demandForecastImpl.setNew(demandForecast.isNew());
        demandForecastImpl.setPrimaryKey(demandForecast.getPrimaryKey());

        demandForecastImpl.setModifiedBy(demandForecast.getModifiedBy());
        demandForecastImpl.setCreatedDate(demandForecast.getCreatedDate());
        demandForecastImpl.setTotalDemandUnits(demandForecast.getTotalDemandUnits());
        demandForecastImpl.setBrandMasterSid(demandForecast.getBrandMasterSid());
        demandForecastImpl.setMarketShareUnits(demandForecast.getMarketShareUnits());
        demandForecastImpl.setBatchId(demandForecast.getBatchId());
        demandForecastImpl.setGrossAmount(demandForecast.getGrossAmount());
        demandForecastImpl.setForecastVer(demandForecast.getForecastVer());
        demandForecastImpl.setBrandId(demandForecast.getBrandId());
        demandForecastImpl.setGrossUnits(demandForecast.getGrossUnits());
        demandForecastImpl.setCountry(demandForecast.getCountry());
        demandForecastImpl.setDemandForecastSid(demandForecast.getDemandForecastSid());
        demandForecastImpl.setForecastType(demandForecast.getForecastType());
        demandForecastImpl.setItemMasterSid(demandForecast.getItemMasterSid());
        demandForecastImpl.setTotalDemandAmount(demandForecast.getTotalDemandAmount());
        demandForecastImpl.setForecastMonth(demandForecast.getForecastMonth());
        demandForecastImpl.setOrganizationKey(demandForecast.getOrganizationKey());
        demandForecastImpl.setCreatedBy(demandForecast.getCreatedBy());
        demandForecastImpl.setMarketSizeUnits(demandForecast.getMarketSizeUnits());
        demandForecastImpl.setSegment(demandForecast.getSegment());
        demandForecastImpl.setForecastYear(demandForecast.getForecastYear());
        demandForecastImpl.setItemId(demandForecast.getItemId());
        demandForecastImpl.setInventoryUnitChange(demandForecast.getInventoryUnitChange());
        demandForecastImpl.setGrossPrice(demandForecast.getGrossPrice());
        demandForecastImpl.setForecastName(demandForecast.getForecastName());
        demandForecastImpl.setNetSalesAmount(demandForecast.getNetSalesAmount());
        demandForecastImpl.setModifiedDate(demandForecast.getModifiedDate());
        demandForecastImpl.setItemIdentifier(demandForecast.getItemIdentifier());
        demandForecastImpl.setRecordLockStatus(demandForecast.isRecordLockStatus());
        demandForecastImpl.setUncapturedUnitsRatio(demandForecast.getUncapturedUnitsRatio());
        demandForecastImpl.setItemIdentifierCodeQualifier(demandForecast.getItemIdentifierCodeQualifier());
        demandForecastImpl.setMarketShareRatio(demandForecast.getMarketShareRatio());
        demandForecastImpl.setSource(demandForecast.getSource());
        demandForecastImpl.setUncapturedUnits(demandForecast.getUncapturedUnits());
        demandForecastImpl.setNetSalesPrice(demandForecast.getNetSalesPrice());
        demandForecastImpl.setInboundStatus(demandForecast.getInboundStatus());

        return demandForecastImpl;
    }

    /**
     * Returns the demand forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the demand forecast
     * @return the demand forecast
     * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDemandForecastException, SystemException {
        DemandForecast demandForecast = fetchByPrimaryKey(primaryKey);

        if (demandForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return demandForecast;
    }

    /**
     * Returns the demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchDemandForecastException} if it could not be found.
     *
     * @param demandForecastSid the primary key of the demand forecast
     * @return the demand forecast
     * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast findByPrimaryKey(int demandForecastSid)
        throws NoSuchDemandForecastException, SystemException {
        return findByPrimaryKey((Serializable) demandForecastSid);
    }

    /**
     * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the demand forecast
     * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DemandForecast demandForecast = (DemandForecast) EntityCacheUtil.getResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                DemandForecastImpl.class, primaryKey);

        if (demandForecast == _nullDemandForecast) {
            return null;
        }

        if (demandForecast == null) {
            Session session = null;

            try {
                session = openSession();

                demandForecast = (DemandForecast) session.get(DemandForecastImpl.class,
                        primaryKey);

                if (demandForecast != null) {
                    cacheResult(demandForecast);
                } else {
                    EntityCacheUtil.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                        DemandForecastImpl.class, primaryKey,
                        _nullDemandForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
                    DemandForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return demandForecast;
    }

    /**
     * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param demandForecastSid the primary key of the demand forecast
     * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DemandForecast fetchByPrimaryKey(int demandForecastSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) demandForecastSid);
    }

    /**
     * Returns all the demand forecasts.
     *
     * @return the demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DemandForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of demand forecasts
     * @param end the upper bound of the range of demand forecasts (not inclusive)
     * @return the range of demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DemandForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the demand forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of demand forecasts
     * @param end the upper bound of the range of demand forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of demand forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DemandForecast> findAll(int start, int end,
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

        List<DemandForecast> list = (List<DemandForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEMANDFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEMANDFORECAST;

                if (pagination) {
                    sql = sql.concat(DemandForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DemandForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DemandForecast>(list);
                } else {
                    list = (List<DemandForecast>) QueryUtil.list(q,
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
     * Removes all the demand forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DemandForecast demandForecast : findAll()) {
            remove(demandForecast);
        }
    }

    /**
     * Returns the number of demand forecasts.
     *
     * @return the number of demand forecasts
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

                Query q = session.createQuery(_SQL_COUNT_DEMANDFORECAST);

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
     * Initializes the demand forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DemandForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DemandForecast>> listenersList = new ArrayList<ModelListener<DemandForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DemandForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DemandForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
