package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;
import com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl;
import com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl;
import com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActPersistence;

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
 * The persistence implementation for the vw adjust demand forecast act service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastActPersistence
 * @see VwAdjustDemandForecastActUtil
 * @generated
 */
public class VwAdjustDemandForecastActPersistenceImpl
    extends BasePersistenceImpl<VwAdjustDemandForecastAct>
    implements VwAdjustDemandForecastActPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwAdjustDemandForecastActUtil} to access the vw adjust demand forecast act persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwAdjustDemandForecastActImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
            VwAdjustDemandForecastActImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
            VwAdjustDemandForecastActImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_VWADJUSTDEMANDFORECASTACT = "SELECT vwAdjustDemandForecastAct FROM VwAdjustDemandForecastAct vwAdjustDemandForecastAct";
    private static final String _SQL_COUNT_VWADJUSTDEMANDFORECASTACT = "SELECT COUNT(vwAdjustDemandForecastAct) FROM VwAdjustDemandForecastAct vwAdjustDemandForecastAct";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwAdjustDemandForecastAct.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwAdjustDemandForecastAct exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwAdjustDemandForecastActPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastVersion", "grossUnits", "businessUnitNo", "year",
                "brandName", "itemId", "organizationKey", "source",
                "marketShareRatio", "businessUnitName", "marketShareUnits",
                "month", "inventoryUnitChange", "uncapturedUnitsRatio",
                "country", "forecastType", "totalAdjustedDemandUnits", "brandId",
                "isForecast", "totalAdjustedDemandAmount", "uncapturedUnits",
                "grossPrice", "grossAmount", "batchId",
                "adjustedDemandForecastId", "itemName", "netSalesPrice",
                "netSalesAmount", "segment", "forecastName", "marketSizeUnits"
            });
    private static VwAdjustDemandForecastAct _nullVwAdjustDemandForecastAct = new VwAdjustDemandForecastActImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwAdjustDemandForecastAct> toCacheModel() {
                return _nullVwAdjustDemandForecastActCacheModel;
            }
        };

    private static CacheModel<VwAdjustDemandForecastAct> _nullVwAdjustDemandForecastActCacheModel =
        new CacheModel<VwAdjustDemandForecastAct>() {
            @Override
            public VwAdjustDemandForecastAct toEntityModel() {
                return _nullVwAdjustDemandForecastAct;
            }
        };

    public VwAdjustDemandForecastActPersistenceImpl() {
        setModelClass(VwAdjustDemandForecastAct.class);
    }

    /**
     * Caches the vw adjust demand forecast act in the entity cache if it is enabled.
     *
     * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
     */
    @Override
    public void cacheResult(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        EntityCacheUtil.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActImpl.class,
            vwAdjustDemandForecastAct.getPrimaryKey(), vwAdjustDemandForecastAct);

        vwAdjustDemandForecastAct.resetOriginalValues();
    }

    /**
     * Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
     *
     * @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
     */
    @Override
    public void cacheResult(
        List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
        for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : vwAdjustDemandForecastActs) {
            if (EntityCacheUtil.getResult(
                        VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
                        VwAdjustDemandForecastActImpl.class,
                        vwAdjustDemandForecastAct.getPrimaryKey()) == null) {
                cacheResult(vwAdjustDemandForecastAct);
            } else {
                vwAdjustDemandForecastAct.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw adjust demand forecast acts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwAdjustDemandForecastActImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwAdjustDemandForecastActImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw adjust demand forecast act.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        EntityCacheUtil.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActImpl.class,
            vwAdjustDemandForecastAct.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : vwAdjustDemandForecastActs) {
            EntityCacheUtil.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
                VwAdjustDemandForecastActImpl.class,
                vwAdjustDemandForecastAct.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
     *
     * @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
     * @return the new vw adjust demand forecast act
     */
    @Override
    public VwAdjustDemandForecastAct create(int adjustedDemandForecastId) {
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct = new VwAdjustDemandForecastActImpl();

        vwAdjustDemandForecastAct.setNew(true);
        vwAdjustDemandForecastAct.setPrimaryKey(adjustedDemandForecastId);

        return vwAdjustDemandForecastAct;
    }

    /**
     * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct remove(int adjustedDemandForecastId)
        throws NoSuchVwAdjustDemandForecastActException, SystemException {
        return remove((Serializable) adjustedDemandForecastId);
    }

    /**
     * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct remove(Serializable primaryKey)
        throws NoSuchVwAdjustDemandForecastActException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwAdjustDemandForecastAct vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct) session.get(VwAdjustDemandForecastActImpl.class,
                    primaryKey);

            if (vwAdjustDemandForecastAct == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwAdjustDemandForecastActException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwAdjustDemandForecastAct);
        } catch (NoSuchVwAdjustDemandForecastActException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwAdjustDemandForecastAct removeImpl(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws SystemException {
        vwAdjustDemandForecastAct = toUnwrappedModel(vwAdjustDemandForecastAct);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwAdjustDemandForecastAct)) {
                vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct) session.get(VwAdjustDemandForecastActImpl.class,
                        vwAdjustDemandForecastAct.getPrimaryKeyObj());
            }

            if (vwAdjustDemandForecastAct != null) {
                session.delete(vwAdjustDemandForecastAct);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwAdjustDemandForecastAct != null) {
            clearCache(vwAdjustDemandForecastAct);
        }

        return vwAdjustDemandForecastAct;
    }

    @Override
    public VwAdjustDemandForecastAct updateImpl(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws SystemException {
        vwAdjustDemandForecastAct = toUnwrappedModel(vwAdjustDemandForecastAct);

        boolean isNew = vwAdjustDemandForecastAct.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwAdjustDemandForecastAct.isNew()) {
                session.save(vwAdjustDemandForecastAct);

                vwAdjustDemandForecastAct.setNew(false);
            } else {
                session.merge(vwAdjustDemandForecastAct);
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

        EntityCacheUtil.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
            VwAdjustDemandForecastActImpl.class,
            vwAdjustDemandForecastAct.getPrimaryKey(), vwAdjustDemandForecastAct);

        return vwAdjustDemandForecastAct;
    }

    protected VwAdjustDemandForecastAct toUnwrappedModel(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        if (vwAdjustDemandForecastAct instanceof VwAdjustDemandForecastActImpl) {
            return vwAdjustDemandForecastAct;
        }

        VwAdjustDemandForecastActImpl vwAdjustDemandForecastActImpl = new VwAdjustDemandForecastActImpl();

        vwAdjustDemandForecastActImpl.setNew(vwAdjustDemandForecastAct.isNew());
        vwAdjustDemandForecastActImpl.setPrimaryKey(vwAdjustDemandForecastAct.getPrimaryKey());

        vwAdjustDemandForecastActImpl.setForecastVersion(vwAdjustDemandForecastAct.getForecastVersion());
        vwAdjustDemandForecastActImpl.setGrossUnits(vwAdjustDemandForecastAct.getGrossUnits());
        vwAdjustDemandForecastActImpl.setBusinessUnitNo(vwAdjustDemandForecastAct.getBusinessUnitNo());
        vwAdjustDemandForecastActImpl.setYear(vwAdjustDemandForecastAct.getYear());
        vwAdjustDemandForecastActImpl.setBrandName(vwAdjustDemandForecastAct.getBrandName());
        vwAdjustDemandForecastActImpl.setItemId(vwAdjustDemandForecastAct.getItemId());
        vwAdjustDemandForecastActImpl.setOrganizationKey(vwAdjustDemandForecastAct.getOrganizationKey());
        vwAdjustDemandForecastActImpl.setSource(vwAdjustDemandForecastAct.getSource());
        vwAdjustDemandForecastActImpl.setMarketShareRatio(vwAdjustDemandForecastAct.getMarketShareRatio());
        vwAdjustDemandForecastActImpl.setBusinessUnitName(vwAdjustDemandForecastAct.getBusinessUnitName());
        vwAdjustDemandForecastActImpl.setMarketShareUnits(vwAdjustDemandForecastAct.getMarketShareUnits());
        vwAdjustDemandForecastActImpl.setMonth(vwAdjustDemandForecastAct.getMonth());
        vwAdjustDemandForecastActImpl.setInventoryUnitChange(vwAdjustDemandForecastAct.getInventoryUnitChange());
        vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(vwAdjustDemandForecastAct.getUncapturedUnitsRatio());
        vwAdjustDemandForecastActImpl.setCountry(vwAdjustDemandForecastAct.getCountry());
        vwAdjustDemandForecastActImpl.setForecastType(vwAdjustDemandForecastAct.getForecastType());
        vwAdjustDemandForecastActImpl.setTotalAdjustedDemandUnits(vwAdjustDemandForecastAct.getTotalAdjustedDemandUnits());
        vwAdjustDemandForecastActImpl.setBrandId(vwAdjustDemandForecastAct.getBrandId());
        vwAdjustDemandForecastActImpl.setIsForecast(vwAdjustDemandForecastAct.getIsForecast());
        vwAdjustDemandForecastActImpl.setTotalAdjustedDemandAmount(vwAdjustDemandForecastAct.getTotalAdjustedDemandAmount());
        vwAdjustDemandForecastActImpl.setUncapturedUnits(vwAdjustDemandForecastAct.getUncapturedUnits());
        vwAdjustDemandForecastActImpl.setGrossPrice(vwAdjustDemandForecastAct.getGrossPrice());
        vwAdjustDemandForecastActImpl.setGrossAmount(vwAdjustDemandForecastAct.getGrossAmount());
        vwAdjustDemandForecastActImpl.setBatchId(vwAdjustDemandForecastAct.getBatchId());
        vwAdjustDemandForecastActImpl.setAdjustedDemandForecastId(vwAdjustDemandForecastAct.getAdjustedDemandForecastId());
        vwAdjustDemandForecastActImpl.setItemName(vwAdjustDemandForecastAct.getItemName());
        vwAdjustDemandForecastActImpl.setNetSalesPrice(vwAdjustDemandForecastAct.getNetSalesPrice());
        vwAdjustDemandForecastActImpl.setNetSalesAmount(vwAdjustDemandForecastAct.getNetSalesAmount());
        vwAdjustDemandForecastActImpl.setSegment(vwAdjustDemandForecastAct.getSegment());
        vwAdjustDemandForecastActImpl.setForecastName(vwAdjustDemandForecastAct.getForecastName());
        vwAdjustDemandForecastActImpl.setMarketSizeUnits(vwAdjustDemandForecastAct.getMarketSizeUnits());

        return vwAdjustDemandForecastActImpl;
    }

    /**
     * Returns the vw adjust demand forecast act with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act
     * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwAdjustDemandForecastActException, SystemException {
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct = fetchByPrimaryKey(primaryKey);

        if (vwAdjustDemandForecastAct == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwAdjustDemandForecastActException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwAdjustDemandForecastAct;
    }

    /**
     * Returns the vw adjust demand forecast act with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException} if it could not be found.
     *
     * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act
     * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct findByPrimaryKey(
        int adjustedDemandForecastId)
        throws NoSuchVwAdjustDemandForecastActException, SystemException {
        return findByPrimaryKey((Serializable) adjustedDemandForecastId);
    }

    /**
     * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct) EntityCacheUtil.getResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
                VwAdjustDemandForecastActImpl.class, primaryKey);

        if (vwAdjustDemandForecastAct == _nullVwAdjustDemandForecastAct) {
            return null;
        }

        if (vwAdjustDemandForecastAct == null) {
            Session session = null;

            try {
                session = openSession();

                vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct) session.get(VwAdjustDemandForecastActImpl.class,
                        primaryKey);

                if (vwAdjustDemandForecastAct != null) {
                    cacheResult(vwAdjustDemandForecastAct);
                } else {
                    EntityCacheUtil.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
                        VwAdjustDemandForecastActImpl.class, primaryKey,
                        _nullVwAdjustDemandForecastAct);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
                    VwAdjustDemandForecastActImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwAdjustDemandForecastAct;
    }

    /**
     * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
     * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAdjustDemandForecastAct fetchByPrimaryKey(
        int adjustedDemandForecastId) throws SystemException {
        return fetchByPrimaryKey((Serializable) adjustedDemandForecastId);
    }

    /**
     * Returns all the vw adjust demand forecast acts.
     *
     * @return the vw adjust demand forecast acts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAdjustDemandForecastAct> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw adjust demand forecast acts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw adjust demand forecast acts
     * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
     * @return the range of vw adjust demand forecast acts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAdjustDemandForecastAct> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw adjust demand forecast acts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw adjust demand forecast acts
     * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw adjust demand forecast acts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAdjustDemandForecastAct> findAll(int start, int end,
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

        List<VwAdjustDemandForecastAct> list = (List<VwAdjustDemandForecastAct>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWADJUSTDEMANDFORECASTACT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWADJUSTDEMANDFORECASTACT;

                if (pagination) {
                    sql = sql.concat(VwAdjustDemandForecastActModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwAdjustDemandForecastAct>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwAdjustDemandForecastAct>(list);
                } else {
                    list = (List<VwAdjustDemandForecastAct>) QueryUtil.list(q,
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
     * Removes all the vw adjust demand forecast acts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : findAll()) {
            remove(vwAdjustDemandForecastAct);
        }
    }

    /**
     * Returns the number of vw adjust demand forecast acts.
     *
     * @return the number of vw adjust demand forecast acts
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

                Query q = session.createQuery(_SQL_COUNT_VWADJUSTDEMANDFORECASTACT);

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
     * Initializes the vw adjust demand forecast act persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwAdjustDemandForecastAct")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwAdjustDemandForecastAct>> listenersList = new ArrayList<ModelListener<VwAdjustDemandForecastAct>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwAdjustDemandForecastAct>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwAdjustDemandForecastActImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
