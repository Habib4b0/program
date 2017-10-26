package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastPersistence;

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
 * The persistence implementation for the ivld customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsForecastPersistence
 * @see IvldCustomerGtsForecastUtil
 * @generated
 */
public class IvldCustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<IvldCustomerGtsForecast>
    implements IvldCustomerGtsForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCustomerGtsForecastUtil} to access the ivld customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCustomerGtsForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            IvldCustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            IvldCustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCUSTOMERGTSFORECAST = "SELECT ivldCustomerGtsForecast FROM IvldCustomerGtsForecast ivldCustomerGtsForecast";
    private static final String _SQL_COUNT_IVLDCUSTOMERGTSFORECAST = "SELECT COUNT(ivldCustomerGtsForecast) FROM IvldCustomerGtsForecast ivldCustomerGtsForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCustomerGtsForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCustomerGtsForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCustomerGtsForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "price", "forecastYear", "deductionAmount",
                "ivldCustomerGtsForecastSid", "deductionId", "forecastDate",
                "itemId", "modifiedDate", "source", "createdDate", "createdBy",
                "addChgDelIndicator", "errorCode", "intfInsertedDate",
                "modifiedBy", "salesAmount", "reprocessedFlag", "udc6", "udc5",
                "deductionType", "udc4", "udc1", "units", "deductionRate",
                "udc2", "udc3", "reasonForFailure", "country", "companyId",
                "forecastValueType", "deductionCategory", "adjustmentCode",
                "deductionProgramType", "customerGtsForecastIntfId",
                "salesInclusion", "forecastVer", "batchId", "priceType",
                "forecastMonth", "deductionInclusion", "errorField", "segment",
                "brand", "forecastName", "checkRecord"
            });
    private static IvldCustomerGtsForecast _nullIvldCustomerGtsForecast = new IvldCustomerGtsForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCustomerGtsForecast> toCacheModel() {
                return _nullIvldCustomerGtsForecastCacheModel;
            }
        };

    private static CacheModel<IvldCustomerGtsForecast> _nullIvldCustomerGtsForecastCacheModel =
        new CacheModel<IvldCustomerGtsForecast>() {
            @Override
            public IvldCustomerGtsForecast toEntityModel() {
                return _nullIvldCustomerGtsForecast;
            }
        };

    public IvldCustomerGtsForecastPersistenceImpl() {
        setModelClass(IvldCustomerGtsForecast.class);
    }

    /**
     * Caches the ivld customer gts forecast in the entity cache if it is enabled.
     *
     * @param ivldCustomerGtsForecast the ivld customer gts forecast
     */
    @Override
    public void cacheResult(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
        EntityCacheUtil.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastImpl.class,
            ivldCustomerGtsForecast.getPrimaryKey(), ivldCustomerGtsForecast);

        ivldCustomerGtsForecast.resetOriginalValues();
    }

    /**
     * Caches the ivld customer gts forecasts in the entity cache if it is enabled.
     *
     * @param ivldCustomerGtsForecasts the ivld customer gts forecasts
     */
    @Override
    public void cacheResult(
        List<IvldCustomerGtsForecast> ivldCustomerGtsForecasts) {
        for (IvldCustomerGtsForecast ivldCustomerGtsForecast : ivldCustomerGtsForecasts) {
            if (EntityCacheUtil.getResult(
                        IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCustomerGtsForecastImpl.class,
                        ivldCustomerGtsForecast.getPrimaryKey()) == null) {
                cacheResult(ivldCustomerGtsForecast);
            } else {
                ivldCustomerGtsForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld customer gts forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCustomerGtsForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCustomerGtsForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld customer gts forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
        EntityCacheUtil.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastImpl.class,
            ivldCustomerGtsForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<IvldCustomerGtsForecast> ivldCustomerGtsForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCustomerGtsForecast ivldCustomerGtsForecast : ivldCustomerGtsForecasts) {
            EntityCacheUtil.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                IvldCustomerGtsForecastImpl.class,
                ivldCustomerGtsForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld customer gts forecast with the primary key. Does not add the ivld customer gts forecast to the database.
     *
     * @param ivldCustomerGtsForecastSid the primary key for the new ivld customer gts forecast
     * @return the new ivld customer gts forecast
     */
    @Override
    public IvldCustomerGtsForecast create(int ivldCustomerGtsForecastSid) {
        IvldCustomerGtsForecast ivldCustomerGtsForecast = new IvldCustomerGtsForecastImpl();

        ivldCustomerGtsForecast.setNew(true);
        ivldCustomerGtsForecast.setPrimaryKey(ivldCustomerGtsForecastSid);

        return ivldCustomerGtsForecast;
    }

    /**
     * Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast remove(int ivldCustomerGtsForecastSid)
        throws NoSuchIvldCustomerGtsForecastException, SystemException {
        return remove((Serializable) ivldCustomerGtsForecastSid);
    }

    /**
     * Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast remove(Serializable primaryKey)
        throws NoSuchIvldCustomerGtsForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCustomerGtsForecast ivldCustomerGtsForecast = (IvldCustomerGtsForecast) session.get(IvldCustomerGtsForecastImpl.class,
                    primaryKey);

            if (ivldCustomerGtsForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCustomerGtsForecast);
        } catch (NoSuchIvldCustomerGtsForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCustomerGtsForecast removeImpl(
        IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws SystemException {
        ivldCustomerGtsForecast = toUnwrappedModel(ivldCustomerGtsForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCustomerGtsForecast)) {
                ivldCustomerGtsForecast = (IvldCustomerGtsForecast) session.get(IvldCustomerGtsForecastImpl.class,
                        ivldCustomerGtsForecast.getPrimaryKeyObj());
            }

            if (ivldCustomerGtsForecast != null) {
                session.delete(ivldCustomerGtsForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCustomerGtsForecast != null) {
            clearCache(ivldCustomerGtsForecast);
        }

        return ivldCustomerGtsForecast;
    }

    @Override
    public IvldCustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws SystemException {
        ivldCustomerGtsForecast = toUnwrappedModel(ivldCustomerGtsForecast);

        boolean isNew = ivldCustomerGtsForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCustomerGtsForecast.isNew()) {
                session.save(ivldCustomerGtsForecast);

                ivldCustomerGtsForecast.setNew(false);
            } else {
                session.merge(ivldCustomerGtsForecast);
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

        EntityCacheUtil.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            IvldCustomerGtsForecastImpl.class,
            ivldCustomerGtsForecast.getPrimaryKey(), ivldCustomerGtsForecast);

        return ivldCustomerGtsForecast;
    }

    protected IvldCustomerGtsForecast toUnwrappedModel(
        IvldCustomerGtsForecast ivldCustomerGtsForecast) {
        if (ivldCustomerGtsForecast instanceof IvldCustomerGtsForecastImpl) {
            return ivldCustomerGtsForecast;
        }

        IvldCustomerGtsForecastImpl ivldCustomerGtsForecastImpl = new IvldCustomerGtsForecastImpl();

        ivldCustomerGtsForecastImpl.setNew(ivldCustomerGtsForecast.isNew());
        ivldCustomerGtsForecastImpl.setPrimaryKey(ivldCustomerGtsForecast.getPrimaryKey());

        ivldCustomerGtsForecastImpl.setPrice(ivldCustomerGtsForecast.getPrice());
        ivldCustomerGtsForecastImpl.setForecastYear(ivldCustomerGtsForecast.getForecastYear());
        ivldCustomerGtsForecastImpl.setDeductionAmount(ivldCustomerGtsForecast.getDeductionAmount());
        ivldCustomerGtsForecastImpl.setIvldCustomerGtsForecastSid(ivldCustomerGtsForecast.getIvldCustomerGtsForecastSid());
        ivldCustomerGtsForecastImpl.setDeductionId(ivldCustomerGtsForecast.getDeductionId());
        ivldCustomerGtsForecastImpl.setForecastDate(ivldCustomerGtsForecast.getForecastDate());
        ivldCustomerGtsForecastImpl.setItemId(ivldCustomerGtsForecast.getItemId());
        ivldCustomerGtsForecastImpl.setModifiedDate(ivldCustomerGtsForecast.getModifiedDate());
        ivldCustomerGtsForecastImpl.setSource(ivldCustomerGtsForecast.getSource());
        ivldCustomerGtsForecastImpl.setCreatedDate(ivldCustomerGtsForecast.getCreatedDate());
        ivldCustomerGtsForecastImpl.setCreatedBy(ivldCustomerGtsForecast.getCreatedBy());
        ivldCustomerGtsForecastImpl.setAddChgDelIndicator(ivldCustomerGtsForecast.getAddChgDelIndicator());
        ivldCustomerGtsForecastImpl.setErrorCode(ivldCustomerGtsForecast.getErrorCode());
        ivldCustomerGtsForecastImpl.setIntfInsertedDate(ivldCustomerGtsForecast.getIntfInsertedDate());
        ivldCustomerGtsForecastImpl.setModifiedBy(ivldCustomerGtsForecast.getModifiedBy());
        ivldCustomerGtsForecastImpl.setSalesAmount(ivldCustomerGtsForecast.getSalesAmount());
        ivldCustomerGtsForecastImpl.setReprocessedFlag(ivldCustomerGtsForecast.getReprocessedFlag());
        ivldCustomerGtsForecastImpl.setUdc6(ivldCustomerGtsForecast.getUdc6());
        ivldCustomerGtsForecastImpl.setUdc5(ivldCustomerGtsForecast.getUdc5());
        ivldCustomerGtsForecastImpl.setDeductionType(ivldCustomerGtsForecast.getDeductionType());
        ivldCustomerGtsForecastImpl.setUdc4(ivldCustomerGtsForecast.getUdc4());
        ivldCustomerGtsForecastImpl.setUdc1(ivldCustomerGtsForecast.getUdc1());
        ivldCustomerGtsForecastImpl.setUnits(ivldCustomerGtsForecast.getUnits());
        ivldCustomerGtsForecastImpl.setDeductionRate(ivldCustomerGtsForecast.getDeductionRate());
        ivldCustomerGtsForecastImpl.setUdc2(ivldCustomerGtsForecast.getUdc2());
        ivldCustomerGtsForecastImpl.setUdc3(ivldCustomerGtsForecast.getUdc3());
        ivldCustomerGtsForecastImpl.setReasonForFailure(ivldCustomerGtsForecast.getReasonForFailure());
        ivldCustomerGtsForecastImpl.setCountry(ivldCustomerGtsForecast.getCountry());
        ivldCustomerGtsForecastImpl.setCompanyId(ivldCustomerGtsForecast.getCompanyId());
        ivldCustomerGtsForecastImpl.setForecastValueType(ivldCustomerGtsForecast.getForecastValueType());
        ivldCustomerGtsForecastImpl.setDeductionCategory(ivldCustomerGtsForecast.getDeductionCategory());
        ivldCustomerGtsForecastImpl.setAdjustmentCode(ivldCustomerGtsForecast.getAdjustmentCode());
        ivldCustomerGtsForecastImpl.setDeductionProgramType(ivldCustomerGtsForecast.getDeductionProgramType());
        ivldCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(ivldCustomerGtsForecast.getCustomerGtsForecastIntfId());
        ivldCustomerGtsForecastImpl.setSalesInclusion(ivldCustomerGtsForecast.getSalesInclusion());
        ivldCustomerGtsForecastImpl.setForecastVer(ivldCustomerGtsForecast.getForecastVer());
        ivldCustomerGtsForecastImpl.setBatchId(ivldCustomerGtsForecast.getBatchId());
        ivldCustomerGtsForecastImpl.setPriceType(ivldCustomerGtsForecast.getPriceType());
        ivldCustomerGtsForecastImpl.setForecastMonth(ivldCustomerGtsForecast.getForecastMonth());
        ivldCustomerGtsForecastImpl.setDeductionInclusion(ivldCustomerGtsForecast.getDeductionInclusion());
        ivldCustomerGtsForecastImpl.setErrorField(ivldCustomerGtsForecast.getErrorField());
        ivldCustomerGtsForecastImpl.setSegment(ivldCustomerGtsForecast.getSegment());
        ivldCustomerGtsForecastImpl.setBrand(ivldCustomerGtsForecast.getBrand());
        ivldCustomerGtsForecastImpl.setForecastName(ivldCustomerGtsForecast.getForecastName());
        ivldCustomerGtsForecastImpl.setCheckRecord(ivldCustomerGtsForecast.isCheckRecord());

        return ivldCustomerGtsForecastImpl;
    }

    /**
     * Returns the ivld customer gts forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCustomerGtsForecastException, SystemException {
        IvldCustomerGtsForecast ivldCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

        if (ivldCustomerGtsForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCustomerGtsForecast;
    }

    /**
     * Returns the ivld customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException} if it could not be found.
     *
     * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast findByPrimaryKey(
        int ivldCustomerGtsForecastSid)
        throws NoSuchIvldCustomerGtsForecastException, SystemException {
        return findByPrimaryKey((Serializable) ivldCustomerGtsForecastSid);
    }

    /**
     * Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCustomerGtsForecast ivldCustomerGtsForecast = (IvldCustomerGtsForecast) EntityCacheUtil.getResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                IvldCustomerGtsForecastImpl.class, primaryKey);

        if (ivldCustomerGtsForecast == _nullIvldCustomerGtsForecast) {
            return null;
        }

        if (ivldCustomerGtsForecast == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCustomerGtsForecast = (IvldCustomerGtsForecast) session.get(IvldCustomerGtsForecastImpl.class,
                        primaryKey);

                if (ivldCustomerGtsForecast != null) {
                    cacheResult(ivldCustomerGtsForecast);
                } else {
                    EntityCacheUtil.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCustomerGtsForecastImpl.class, primaryKey,
                        _nullIvldCustomerGtsForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCustomerGtsForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCustomerGtsForecast;
    }

    /**
     * Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
     * @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCustomerGtsForecast fetchByPrimaryKey(
        int ivldCustomerGtsForecastSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCustomerGtsForecastSid);
    }

    /**
     * Returns all the ivld customer gts forecasts.
     *
     * @return the ivld customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld customer gts forecasts
     * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
     * @return the range of ivld customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld customer gts forecasts
     * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCustomerGtsForecast> findAll(int start, int end,
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

        List<IvldCustomerGtsForecast> list = (List<IvldCustomerGtsForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCUSTOMERGTSFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCUSTOMERGTSFORECAST;

                if (pagination) {
                    sql = sql.concat(IvldCustomerGtsForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCustomerGtsForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCustomerGtsForecast>(list);
                } else {
                    list = (List<IvldCustomerGtsForecast>) QueryUtil.list(q,
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
     * Removes all the ivld customer gts forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCustomerGtsForecast ivldCustomerGtsForecast : findAll()) {
            remove(ivldCustomerGtsForecast);
        }
    }

    /**
     * Returns the number of ivld customer gts forecasts.
     *
     * @return the number of ivld customer gts forecasts
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCUSTOMERGTSFORECAST);

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
     * Initializes the ivld customer gts forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCustomerGtsForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCustomerGtsForecast>> listenersList = new ArrayList<ModelListener<IvldCustomerGtsForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCustomerGtsForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCustomerGtsForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
