package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastPersistence;

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
 * The persistence implementation for the vw customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecastPersistence
 * @see VwCustomerGtsForecastUtil
 * @generated
 */
public class VwCustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<VwCustomerGtsForecast>
    implements VwCustomerGtsForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwCustomerGtsForecastUtil} to access the vw customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwCustomerGtsForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            VwCustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            VwCustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWCUSTOMERGTSFORECAST = "SELECT vwCustomerGtsForecast FROM VwCustomerGtsForecast vwCustomerGtsForecast";
    private static final String _SQL_COUNT_VWCUSTOMERGTSFORECAST = "SELECT COUNT(vwCustomerGtsForecast) FROM VwCustomerGtsForecast vwCustomerGtsForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwCustomerGtsForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCustomerGtsForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwCustomerGtsForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "price", "forecastYear", "deductionAmount", "deductionId",
                "forecastDate", "itemId", "modifiedDate", "source", "createdBy",
                "createdDate", "addChgDelIndicator", "modifiedBy", "salesAmount",
                "udc6", "udc5", "deductionType", "udc4", "units",
                "deductionRate", "udc1", "customerGtsForecastSid", "udc2",
                "udc3", "country", "companyId", "forecastValueType",
                "deductionCategory", "adjustmentCode", "deductionProgramType",
                "customerGtsForecastIntfId", "salesInclusion", "forecastVer",
                "batchId", "priceType", "forecastMonth", "deductionInclusion",
                "segment", "brand", "forecastName"
            });
    private static VwCustomerGtsForecast _nullVwCustomerGtsForecast = new VwCustomerGtsForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwCustomerGtsForecast> toCacheModel() {
                return _nullVwCustomerGtsForecastCacheModel;
            }
        };

    private static CacheModel<VwCustomerGtsForecast> _nullVwCustomerGtsForecastCacheModel =
        new CacheModel<VwCustomerGtsForecast>() {
            @Override
            public VwCustomerGtsForecast toEntityModel() {
                return _nullVwCustomerGtsForecast;
            }
        };

    public VwCustomerGtsForecastPersistenceImpl() {
        setModelClass(VwCustomerGtsForecast.class);
    }

    /**
     * Caches the vw customer gts forecast in the entity cache if it is enabled.
     *
     * @param vwCustomerGtsForecast the vw customer gts forecast
     */
    @Override
    public void cacheResult(VwCustomerGtsForecast vwCustomerGtsForecast) {
        EntityCacheUtil.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastImpl.class,
            vwCustomerGtsForecast.getPrimaryKey(), vwCustomerGtsForecast);

        vwCustomerGtsForecast.resetOriginalValues();
    }

    /**
     * Caches the vw customer gts forecasts in the entity cache if it is enabled.
     *
     * @param vwCustomerGtsForecasts the vw customer gts forecasts
     */
    @Override
    public void cacheResult(List<VwCustomerGtsForecast> vwCustomerGtsForecasts) {
        for (VwCustomerGtsForecast vwCustomerGtsForecast : vwCustomerGtsForecasts) {
            if (EntityCacheUtil.getResult(
                        VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        VwCustomerGtsForecastImpl.class,
                        vwCustomerGtsForecast.getPrimaryKey()) == null) {
                cacheResult(vwCustomerGtsForecast);
            } else {
                vwCustomerGtsForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw customer gts forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwCustomerGtsForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwCustomerGtsForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw customer gts forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwCustomerGtsForecast vwCustomerGtsForecast) {
        EntityCacheUtil.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastImpl.class,
            vwCustomerGtsForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwCustomerGtsForecast> vwCustomerGtsForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwCustomerGtsForecast vwCustomerGtsForecast : vwCustomerGtsForecasts) {
            EntityCacheUtil.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                VwCustomerGtsForecastImpl.class,
                vwCustomerGtsForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
     *
     * @param customerGtsForecastSid the primary key for the new vw customer gts forecast
     * @return the new vw customer gts forecast
     */
    @Override
    public VwCustomerGtsForecast create(int customerGtsForecastSid) {
        VwCustomerGtsForecast vwCustomerGtsForecast = new VwCustomerGtsForecastImpl();

        vwCustomerGtsForecast.setNew(true);
        vwCustomerGtsForecast.setPrimaryKey(customerGtsForecastSid);

        return vwCustomerGtsForecast;
    }

    /**
     * Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param customerGtsForecastSid the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast remove(int customerGtsForecastSid)
        throws NoSuchVwCustomerGtsForecastException, SystemException {
        return remove((Serializable) customerGtsForecastSid);
    }

    /**
     * Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast remove(Serializable primaryKey)
        throws NoSuchVwCustomerGtsForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwCustomerGtsForecast vwCustomerGtsForecast = (VwCustomerGtsForecast) session.get(VwCustomerGtsForecastImpl.class,
                    primaryKey);

            if (vwCustomerGtsForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwCustomerGtsForecast);
        } catch (NoSuchVwCustomerGtsForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwCustomerGtsForecast removeImpl(
        VwCustomerGtsForecast vwCustomerGtsForecast) throws SystemException {
        vwCustomerGtsForecast = toUnwrappedModel(vwCustomerGtsForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwCustomerGtsForecast)) {
                vwCustomerGtsForecast = (VwCustomerGtsForecast) session.get(VwCustomerGtsForecastImpl.class,
                        vwCustomerGtsForecast.getPrimaryKeyObj());
            }

            if (vwCustomerGtsForecast != null) {
                session.delete(vwCustomerGtsForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwCustomerGtsForecast != null) {
            clearCache(vwCustomerGtsForecast);
        }

        return vwCustomerGtsForecast;
    }

    @Override
    public VwCustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast)
        throws SystemException {
        vwCustomerGtsForecast = toUnwrappedModel(vwCustomerGtsForecast);

        boolean isNew = vwCustomerGtsForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwCustomerGtsForecast.isNew()) {
                session.save(vwCustomerGtsForecast);

                vwCustomerGtsForecast.setNew(false);
            } else {
                session.merge(vwCustomerGtsForecast);
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

        EntityCacheUtil.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            VwCustomerGtsForecastImpl.class,
            vwCustomerGtsForecast.getPrimaryKey(), vwCustomerGtsForecast);

        return vwCustomerGtsForecast;
    }

    protected VwCustomerGtsForecast toUnwrappedModel(
        VwCustomerGtsForecast vwCustomerGtsForecast) {
        if (vwCustomerGtsForecast instanceof VwCustomerGtsForecastImpl) {
            return vwCustomerGtsForecast;
        }

        VwCustomerGtsForecastImpl vwCustomerGtsForecastImpl = new VwCustomerGtsForecastImpl();

        vwCustomerGtsForecastImpl.setNew(vwCustomerGtsForecast.isNew());
        vwCustomerGtsForecastImpl.setPrimaryKey(vwCustomerGtsForecast.getPrimaryKey());

        vwCustomerGtsForecastImpl.setPrice(vwCustomerGtsForecast.getPrice());
        vwCustomerGtsForecastImpl.setForecastYear(vwCustomerGtsForecast.getForecastYear());
        vwCustomerGtsForecastImpl.setDeductionAmount(vwCustomerGtsForecast.getDeductionAmount());
        vwCustomerGtsForecastImpl.setDeductionId(vwCustomerGtsForecast.getDeductionId());
        vwCustomerGtsForecastImpl.setForecastDate(vwCustomerGtsForecast.getForecastDate());
        vwCustomerGtsForecastImpl.setItemId(vwCustomerGtsForecast.getItemId());
        vwCustomerGtsForecastImpl.setModifiedDate(vwCustomerGtsForecast.getModifiedDate());
        vwCustomerGtsForecastImpl.setSource(vwCustomerGtsForecast.getSource());
        vwCustomerGtsForecastImpl.setCreatedBy(vwCustomerGtsForecast.getCreatedBy());
        vwCustomerGtsForecastImpl.setCreatedDate(vwCustomerGtsForecast.getCreatedDate());
        vwCustomerGtsForecastImpl.setAddChgDelIndicator(vwCustomerGtsForecast.getAddChgDelIndicator());
        vwCustomerGtsForecastImpl.setModifiedBy(vwCustomerGtsForecast.getModifiedBy());
        vwCustomerGtsForecastImpl.setSalesAmount(vwCustomerGtsForecast.getSalesAmount());
        vwCustomerGtsForecastImpl.setUdc6(vwCustomerGtsForecast.getUdc6());
        vwCustomerGtsForecastImpl.setUdc5(vwCustomerGtsForecast.getUdc5());
        vwCustomerGtsForecastImpl.setDeductionType(vwCustomerGtsForecast.getDeductionType());
        vwCustomerGtsForecastImpl.setUdc4(vwCustomerGtsForecast.getUdc4());
        vwCustomerGtsForecastImpl.setUnits(vwCustomerGtsForecast.getUnits());
        vwCustomerGtsForecastImpl.setDeductionRate(vwCustomerGtsForecast.getDeductionRate());
        vwCustomerGtsForecastImpl.setUdc1(vwCustomerGtsForecast.getUdc1());
        vwCustomerGtsForecastImpl.setCustomerGtsForecastSid(vwCustomerGtsForecast.getCustomerGtsForecastSid());
        vwCustomerGtsForecastImpl.setUdc2(vwCustomerGtsForecast.getUdc2());
        vwCustomerGtsForecastImpl.setUdc3(vwCustomerGtsForecast.getUdc3());
        vwCustomerGtsForecastImpl.setCountry(vwCustomerGtsForecast.getCountry());
        vwCustomerGtsForecastImpl.setCompanyId(vwCustomerGtsForecast.getCompanyId());
        vwCustomerGtsForecastImpl.setForecastValueType(vwCustomerGtsForecast.getForecastValueType());
        vwCustomerGtsForecastImpl.setDeductionCategory(vwCustomerGtsForecast.getDeductionCategory());
        vwCustomerGtsForecastImpl.setAdjustmentCode(vwCustomerGtsForecast.getAdjustmentCode());
        vwCustomerGtsForecastImpl.setDeductionProgramType(vwCustomerGtsForecast.getDeductionProgramType());
        vwCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(vwCustomerGtsForecast.getCustomerGtsForecastIntfId());
        vwCustomerGtsForecastImpl.setSalesInclusion(vwCustomerGtsForecast.getSalesInclusion());
        vwCustomerGtsForecastImpl.setForecastVer(vwCustomerGtsForecast.getForecastVer());
        vwCustomerGtsForecastImpl.setBatchId(vwCustomerGtsForecast.getBatchId());
        vwCustomerGtsForecastImpl.setPriceType(vwCustomerGtsForecast.getPriceType());
        vwCustomerGtsForecastImpl.setForecastMonth(vwCustomerGtsForecast.getForecastMonth());
        vwCustomerGtsForecastImpl.setDeductionInclusion(vwCustomerGtsForecast.getDeductionInclusion());
        vwCustomerGtsForecastImpl.setSegment(vwCustomerGtsForecast.getSegment());
        vwCustomerGtsForecastImpl.setBrand(vwCustomerGtsForecast.getBrand());
        vwCustomerGtsForecastImpl.setForecastName(vwCustomerGtsForecast.getForecastName());

        return vwCustomerGtsForecastImpl;
    }

    /**
     * Returns the vw customer gts forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwCustomerGtsForecastException, SystemException {
        VwCustomerGtsForecast vwCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

        if (vwCustomerGtsForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwCustomerGtsForecast;
    }

    /**
     * Returns the vw customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException} if it could not be found.
     *
     * @param customerGtsForecastSid the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
        throws NoSuchVwCustomerGtsForecastException, SystemException {
        return findByPrimaryKey((Serializable) customerGtsForecastSid);
    }

    /**
     * Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwCustomerGtsForecast vwCustomerGtsForecast = (VwCustomerGtsForecast) EntityCacheUtil.getResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                VwCustomerGtsForecastImpl.class, primaryKey);

        if (vwCustomerGtsForecast == _nullVwCustomerGtsForecast) {
            return null;
        }

        if (vwCustomerGtsForecast == null) {
            Session session = null;

            try {
                session = openSession();

                vwCustomerGtsForecast = (VwCustomerGtsForecast) session.get(VwCustomerGtsForecastImpl.class,
                        primaryKey);

                if (vwCustomerGtsForecast != null) {
                    cacheResult(vwCustomerGtsForecast);
                } else {
                    EntityCacheUtil.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        VwCustomerGtsForecastImpl.class, primaryKey,
                        _nullVwCustomerGtsForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                    VwCustomerGtsForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwCustomerGtsForecast;
    }

    /**
     * Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param customerGtsForecastSid the primary key of the vw customer gts forecast
     * @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) customerGtsForecastSid);
    }

    /**
     * Returns all the vw customer gts forecasts.
     *
     * @return the vw customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCustomerGtsForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw customer gts forecasts
     * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
     * @return the range of vw customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCustomerGtsForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw customer gts forecasts
     * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCustomerGtsForecast> findAll(int start, int end,
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

        List<VwCustomerGtsForecast> list = (List<VwCustomerGtsForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWCUSTOMERGTSFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWCUSTOMERGTSFORECAST;

                if (pagination) {
                    sql = sql.concat(VwCustomerGtsForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwCustomerGtsForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwCustomerGtsForecast>(list);
                } else {
                    list = (List<VwCustomerGtsForecast>) QueryUtil.list(q,
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
     * Removes all the vw customer gts forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwCustomerGtsForecast vwCustomerGtsForecast : findAll()) {
            remove(vwCustomerGtsForecast);
        }
    }

    /**
     * Returns the number of vw customer gts forecasts.
     *
     * @return the number of vw customer gts forecasts
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

                Query q = session.createQuery(_SQL_COUNT_VWCUSTOMERGTSFORECAST);

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
     * Initializes the vw customer gts forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwCustomerGtsForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwCustomerGtsForecast>> listenersList = new ArrayList<ModelListener<VwCustomerGtsForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwCustomerGtsForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwCustomerGtsForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
