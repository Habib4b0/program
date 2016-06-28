package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwDemandForecastActualException;
import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.model.impl.VwDemandForecastActualImpl;
import com.stpl.app.model.impl.VwDemandForecastActualModelImpl;
import com.stpl.app.service.persistence.VwDemandForecastActualPersistence;

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
 * The persistence implementation for the vw demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwDemandForecastActualPersistence
 * @see VwDemandForecastActualUtil
 * @generated
 */
public class VwDemandForecastActualPersistenceImpl extends BasePersistenceImpl<VwDemandForecastActual>
    implements VwDemandForecastActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwDemandForecastActualUtil} to access the vw demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwDemandForecastActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
            VwDemandForecastActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
            VwDemandForecastActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWDEMANDFORECASTACTUAL = "SELECT vwDemandForecastActual FROM VwDemandForecastActual vwDemandForecastActual";
    private static final String _SQL_COUNT_VWDEMANDFORECASTACTUAL = "SELECT COUNT(vwDemandForecastActual) FROM VwDemandForecastActual vwDemandForecastActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwDemandForecastActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwDemandForecastActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwDemandForecastActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastYear", "grossUnits", "businessUnitNo",
                "totalDemandUnits", "brandName", "itemId", "organizationKey",
                "source", "marketShareRatio", "businessUnitName",
                "demandForecastActualSid", "marketShareUnits",
                "inventoryUnitChange", "uncapturedUnitsRatio", "country",
                "forecastType", "brandId", "isForecast", "uncapturedUnits",
                "grossPrice", "isActive", "grossAmount", "batchId",
                "forecastVer", "itemName", "forecastMonth", "netSalesPrice",
                "netSalesAmount", "segment", "totalDemandAmount", "forecastName",
                "marketSizeUnits", "demandId"
            });
    private static VwDemandForecastActual _nullVwDemandForecastActual = new VwDemandForecastActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwDemandForecastActual> toCacheModel() {
                return _nullVwDemandForecastActualCacheModel;
            }
        };

    private static CacheModel<VwDemandForecastActual> _nullVwDemandForecastActualCacheModel =
        new CacheModel<VwDemandForecastActual>() {
            @Override
            public VwDemandForecastActual toEntityModel() {
                return _nullVwDemandForecastActual;
            }
        };

    public VwDemandForecastActualPersistenceImpl() {
        setModelClass(VwDemandForecastActual.class);
    }

    /**
     * Caches the vw demand forecast actual in the entity cache if it is enabled.
     *
     * @param vwDemandForecastActual the vw demand forecast actual
     */
    @Override
    public void cacheResult(VwDemandForecastActual vwDemandForecastActual) {
        EntityCacheUtil.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualImpl.class,
            vwDemandForecastActual.getPrimaryKey(), vwDemandForecastActual);

        vwDemandForecastActual.resetOriginalValues();
    }

    /**
     * Caches the vw demand forecast actuals in the entity cache if it is enabled.
     *
     * @param vwDemandForecastActuals the vw demand forecast actuals
     */
    @Override
    public void cacheResult(
        List<VwDemandForecastActual> vwDemandForecastActuals) {
        for (VwDemandForecastActual vwDemandForecastActual : vwDemandForecastActuals) {
            if (EntityCacheUtil.getResult(
                        VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwDemandForecastActualImpl.class,
                        vwDemandForecastActual.getPrimaryKey()) == null) {
                cacheResult(vwDemandForecastActual);
            } else {
                vwDemandForecastActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw demand forecast actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwDemandForecastActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwDemandForecastActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw demand forecast actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwDemandForecastActual vwDemandForecastActual) {
        EntityCacheUtil.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualImpl.class,
            vwDemandForecastActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwDemandForecastActual> vwDemandForecastActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwDemandForecastActual vwDemandForecastActual : vwDemandForecastActuals) {
            EntityCacheUtil.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                VwDemandForecastActualImpl.class,
                vwDemandForecastActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw demand forecast actual with the primary key. Does not add the vw demand forecast actual to the database.
     *
     * @param demandForecastActualSid the primary key for the new vw demand forecast actual
     * @return the new vw demand forecast actual
     */
    @Override
    public VwDemandForecastActual create(int demandForecastActualSid) {
        VwDemandForecastActual vwDemandForecastActual = new VwDemandForecastActualImpl();

        vwDemandForecastActual.setNew(true);
        vwDemandForecastActual.setPrimaryKey(demandForecastActualSid);

        return vwDemandForecastActual;
    }

    /**
     * Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param demandForecastActualSid the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual that was removed
     * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual remove(int demandForecastActualSid)
        throws NoSuchVwDemandForecastActualException, SystemException {
        return remove((Serializable) demandForecastActualSid);
    }

    /**
     * Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual that was removed
     * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual remove(Serializable primaryKey)
        throws NoSuchVwDemandForecastActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwDemandForecastActual vwDemandForecastActual = (VwDemandForecastActual) session.get(VwDemandForecastActualImpl.class,
                    primaryKey);

            if (vwDemandForecastActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwDemandForecastActual);
        } catch (NoSuchVwDemandForecastActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwDemandForecastActual removeImpl(
        VwDemandForecastActual vwDemandForecastActual)
        throws SystemException {
        vwDemandForecastActual = toUnwrappedModel(vwDemandForecastActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwDemandForecastActual)) {
                vwDemandForecastActual = (VwDemandForecastActual) session.get(VwDemandForecastActualImpl.class,
                        vwDemandForecastActual.getPrimaryKeyObj());
            }

            if (vwDemandForecastActual != null) {
                session.delete(vwDemandForecastActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwDemandForecastActual != null) {
            clearCache(vwDemandForecastActual);
        }

        return vwDemandForecastActual;
    }

    @Override
    public VwDemandForecastActual updateImpl(
        com.stpl.app.model.VwDemandForecastActual vwDemandForecastActual)
        throws SystemException {
        vwDemandForecastActual = toUnwrappedModel(vwDemandForecastActual);

        boolean isNew = vwDemandForecastActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwDemandForecastActual.isNew()) {
                session.save(vwDemandForecastActual);

                vwDemandForecastActual.setNew(false);
            } else {
                session.merge(vwDemandForecastActual);
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

        EntityCacheUtil.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
            VwDemandForecastActualImpl.class,
            vwDemandForecastActual.getPrimaryKey(), vwDemandForecastActual);

        return vwDemandForecastActual;
    }

    protected VwDemandForecastActual toUnwrappedModel(
        VwDemandForecastActual vwDemandForecastActual) {
        if (vwDemandForecastActual instanceof VwDemandForecastActualImpl) {
            return vwDemandForecastActual;
        }

        VwDemandForecastActualImpl vwDemandForecastActualImpl = new VwDemandForecastActualImpl();

        vwDemandForecastActualImpl.setNew(vwDemandForecastActual.isNew());
        vwDemandForecastActualImpl.setPrimaryKey(vwDemandForecastActual.getPrimaryKey());

        vwDemandForecastActualImpl.setForecastYear(vwDemandForecastActual.getForecastYear());
        vwDemandForecastActualImpl.setGrossUnits(vwDemandForecastActual.getGrossUnits());
        vwDemandForecastActualImpl.setBusinessUnitNo(vwDemandForecastActual.getBusinessUnitNo());
        vwDemandForecastActualImpl.setTotalDemandUnits(vwDemandForecastActual.getTotalDemandUnits());
        vwDemandForecastActualImpl.setBrandName(vwDemandForecastActual.getBrandName());
        vwDemandForecastActualImpl.setItemId(vwDemandForecastActual.getItemId());
        vwDemandForecastActualImpl.setOrganizationKey(vwDemandForecastActual.getOrganizationKey());
        vwDemandForecastActualImpl.setSource(vwDemandForecastActual.getSource());
        vwDemandForecastActualImpl.setMarketShareRatio(vwDemandForecastActual.getMarketShareRatio());
        vwDemandForecastActualImpl.setBusinessUnitName(vwDemandForecastActual.getBusinessUnitName());
        vwDemandForecastActualImpl.setDemandForecastActualSid(vwDemandForecastActual.getDemandForecastActualSid());
        vwDemandForecastActualImpl.setMarketShareUnits(vwDemandForecastActual.getMarketShareUnits());
        vwDemandForecastActualImpl.setInventoryUnitChange(vwDemandForecastActual.getInventoryUnitChange());
        vwDemandForecastActualImpl.setUncapturedUnitsRatio(vwDemandForecastActual.getUncapturedUnitsRatio());
        vwDemandForecastActualImpl.setCountry(vwDemandForecastActual.getCountry());
        vwDemandForecastActualImpl.setForecastType(vwDemandForecastActual.getForecastType());
        vwDemandForecastActualImpl.setBrandId(vwDemandForecastActual.getBrandId());
        vwDemandForecastActualImpl.setIsForecast(vwDemandForecastActual.getIsForecast());
        vwDemandForecastActualImpl.setUncapturedUnits(vwDemandForecastActual.getUncapturedUnits());
        vwDemandForecastActualImpl.setGrossPrice(vwDemandForecastActual.getGrossPrice());
        vwDemandForecastActualImpl.setIsActive(vwDemandForecastActual.getIsActive());
        vwDemandForecastActualImpl.setGrossAmount(vwDemandForecastActual.getGrossAmount());
        vwDemandForecastActualImpl.setBatchId(vwDemandForecastActual.getBatchId());
        vwDemandForecastActualImpl.setForecastVer(vwDemandForecastActual.getForecastVer());
        vwDemandForecastActualImpl.setItemName(vwDemandForecastActual.getItemName());
        vwDemandForecastActualImpl.setForecastMonth(vwDemandForecastActual.getForecastMonth());
        vwDemandForecastActualImpl.setNetSalesPrice(vwDemandForecastActual.getNetSalesPrice());
        vwDemandForecastActualImpl.setNetSalesAmount(vwDemandForecastActual.getNetSalesAmount());
        vwDemandForecastActualImpl.setSegment(vwDemandForecastActual.getSegment());
        vwDemandForecastActualImpl.setTotalDemandAmount(vwDemandForecastActual.getTotalDemandAmount());
        vwDemandForecastActualImpl.setForecastName(vwDemandForecastActual.getForecastName());
        vwDemandForecastActualImpl.setMarketSizeUnits(vwDemandForecastActual.getMarketSizeUnits());
        vwDemandForecastActualImpl.setDemandId(vwDemandForecastActual.getDemandId());

        return vwDemandForecastActualImpl;
    }

    /**
     * Returns the vw demand forecast actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual
     * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwDemandForecastActualException, SystemException {
        VwDemandForecastActual vwDemandForecastActual = fetchByPrimaryKey(primaryKey);

        if (vwDemandForecastActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwDemandForecastActual;
    }

    /**
     * Returns the vw demand forecast actual with the primary key or throws a {@link com.stpl.app.NoSuchVwDemandForecastActualException} if it could not be found.
     *
     * @param demandForecastActualSid the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual
     * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual findByPrimaryKey(int demandForecastActualSid)
        throws NoSuchVwDemandForecastActualException, SystemException {
        return findByPrimaryKey((Serializable) demandForecastActualSid);
    }

    /**
     * Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwDemandForecastActual vwDemandForecastActual = (VwDemandForecastActual) EntityCacheUtil.getResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                VwDemandForecastActualImpl.class, primaryKey);

        if (vwDemandForecastActual == _nullVwDemandForecastActual) {
            return null;
        }

        if (vwDemandForecastActual == null) {
            Session session = null;

            try {
                session = openSession();

                vwDemandForecastActual = (VwDemandForecastActual) session.get(VwDemandForecastActualImpl.class,
                        primaryKey);

                if (vwDemandForecastActual != null) {
                    cacheResult(vwDemandForecastActual);
                } else {
                    EntityCacheUtil.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                        VwDemandForecastActualImpl.class, primaryKey,
                        _nullVwDemandForecastActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
                    VwDemandForecastActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwDemandForecastActual;
    }

    /**
     * Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param demandForecastActualSid the primary key of the vw demand forecast actual
     * @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwDemandForecastActual fetchByPrimaryKey(int demandForecastActualSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) demandForecastActualSid);
    }

    /**
     * Returns all the vw demand forecast actuals.
     *
     * @return the vw demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwDemandForecastActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw demand forecast actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw demand forecast actuals
     * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
     * @return the range of vw demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwDemandForecastActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw demand forecast actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw demand forecast actuals
     * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw demand forecast actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwDemandForecastActual> findAll(int start, int end,
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

        List<VwDemandForecastActual> list = (List<VwDemandForecastActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWDEMANDFORECASTACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWDEMANDFORECASTACTUAL;

                if (pagination) {
                    sql = sql.concat(VwDemandForecastActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwDemandForecastActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwDemandForecastActual>(list);
                } else {
                    list = (List<VwDemandForecastActual>) QueryUtil.list(q,
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
     * Removes all the vw demand forecast actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwDemandForecastActual vwDemandForecastActual : findAll()) {
            remove(vwDemandForecastActual);
        }
    }

    /**
     * Returns the number of vw demand forecast actuals.
     *
     * @return the number of vw demand forecast actuals
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

                Query q = session.createQuery(_SQL_COUNT_VWDEMANDFORECASTACTUAL);

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
     * Initializes the vw demand forecast actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwDemandForecastActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwDemandForecastActual>> listenersList = new ArrayList<ModelListener<VwDemandForecastActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwDemandForecastActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwDemandForecastActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
