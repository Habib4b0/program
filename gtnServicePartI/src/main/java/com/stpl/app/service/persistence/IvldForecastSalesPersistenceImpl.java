package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldForecastSalesException;
import com.stpl.app.model.IvldForecastSales;
import com.stpl.app.model.impl.IvldForecastSalesImpl;
import com.stpl.app.model.impl.IvldForecastSalesModelImpl;
import com.stpl.app.service.persistence.IvldForecastSalesPersistence;

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
 * The persistence implementation for the ivld forecast sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldForecastSalesPersistence
 * @see IvldForecastSalesUtil
 * @generated
 */
public class IvldForecastSalesPersistenceImpl extends BasePersistenceImpl<IvldForecastSales>
    implements IvldForecastSalesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldForecastSalesUtil} to access the ivld forecast sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldForecastSalesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED,
            IvldForecastSalesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED,
            IvldForecastSalesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDFORECASTSALES = "SELECT ivldForecastSales FROM IvldForecastSales ivldForecastSales";
    private static final String _SQL_COUNT_IVLDFORECASTSALES = "SELECT COUNT(ivldForecastSales) FROM IvldForecastSales ivldForecastSales";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldForecastSales.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldForecastSales exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldForecastSalesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "price", "forecastYear", "forecastDate", "modifiedDate",
                "forecastValue", "forecastIntfid", "dollars", "ndc",
                "actualSalesPercentage", "source", "createdDate", "createdBy",
                "addChgDelIndicator", "actualSalesPercentageMonth", "errorCode",
                "intfInsertedDate", "modifiedBy", "reprocessedFlag",
                "percentageEstimate", "percentageEstimateYear", "units",
                "reasonForFailure", "forecastStartDate", "forecastValueType",
                "forecastedSalesPercentMonth", "country", "product", "batchId",
                "forecastVer", "forecastMonth", "ivldForecastSalesSid",
                "errorField", "segment", "brand", "forecastedSalesPercentage",
                "forecastName", "checkRecord"
            });
    private static IvldForecastSales _nullIvldForecastSales = new IvldForecastSalesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldForecastSales> toCacheModel() {
                return _nullIvldForecastSalesCacheModel;
            }
        };

    private static CacheModel<IvldForecastSales> _nullIvldForecastSalesCacheModel =
        new CacheModel<IvldForecastSales>() {
            @Override
            public IvldForecastSales toEntityModel() {
                return _nullIvldForecastSales;
            }
        };

    public IvldForecastSalesPersistenceImpl() {
        setModelClass(IvldForecastSales.class);
    }

    /**
     * Caches the ivld forecast sales in the entity cache if it is enabled.
     *
     * @param ivldForecastSales the ivld forecast sales
     */
    @Override
    public void cacheResult(IvldForecastSales ivldForecastSales) {
        EntityCacheUtil.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey(),
            ivldForecastSales);

        ivldForecastSales.resetOriginalValues();
    }

    /**
     * Caches the ivld forecast saleses in the entity cache if it is enabled.
     *
     * @param ivldForecastSaleses the ivld forecast saleses
     */
    @Override
    public void cacheResult(List<IvldForecastSales> ivldForecastSaleses) {
        for (IvldForecastSales ivldForecastSales : ivldForecastSaleses) {
            if (EntityCacheUtil.getResult(
                        IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
                        IvldForecastSalesImpl.class,
                        ivldForecastSales.getPrimaryKey()) == null) {
                cacheResult(ivldForecastSales);
            } else {
                ivldForecastSales.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld forecast saleses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldForecastSalesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldForecastSalesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld forecast sales.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldForecastSales ivldForecastSales) {
        EntityCacheUtil.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldForecastSales> ivldForecastSaleses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldForecastSales ivldForecastSales : ivldForecastSaleses) {
            EntityCacheUtil.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
                IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
     *
     * @param ivldForecastSalesSid the primary key for the new ivld forecast sales
     * @return the new ivld forecast sales
     */
    @Override
    public IvldForecastSales create(int ivldForecastSalesSid) {
        IvldForecastSales ivldForecastSales = new IvldForecastSalesImpl();

        ivldForecastSales.setNew(true);
        ivldForecastSales.setPrimaryKey(ivldForecastSalesSid);

        return ivldForecastSales;
    }

    /**
     * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldForecastSalesSid the primary key of the ivld forecast sales
     * @return the ivld forecast sales that was removed
     * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales remove(int ivldForecastSalesSid)
        throws NoSuchIvldForecastSalesException, SystemException {
        return remove((Serializable) ivldForecastSalesSid);
    }

    /**
     * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld forecast sales
     * @return the ivld forecast sales that was removed
     * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales remove(Serializable primaryKey)
        throws NoSuchIvldForecastSalesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldForecastSales ivldForecastSales = (IvldForecastSales) session.get(IvldForecastSalesImpl.class,
                    primaryKey);

            if (ivldForecastSales == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldForecastSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldForecastSales);
        } catch (NoSuchIvldForecastSalesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldForecastSales removeImpl(IvldForecastSales ivldForecastSales)
        throws SystemException {
        ivldForecastSales = toUnwrappedModel(ivldForecastSales);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldForecastSales)) {
                ivldForecastSales = (IvldForecastSales) session.get(IvldForecastSalesImpl.class,
                        ivldForecastSales.getPrimaryKeyObj());
            }

            if (ivldForecastSales != null) {
                session.delete(ivldForecastSales);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldForecastSales != null) {
            clearCache(ivldForecastSales);
        }

        return ivldForecastSales;
    }

    @Override
    public IvldForecastSales updateImpl(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws SystemException {
        ivldForecastSales = toUnwrappedModel(ivldForecastSales);

        boolean isNew = ivldForecastSales.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldForecastSales.isNew()) {
                session.save(ivldForecastSales);

                ivldForecastSales.setNew(false);
            } else {
                session.merge(ivldForecastSales);
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

        EntityCacheUtil.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
            IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey(),
            ivldForecastSales);

        return ivldForecastSales;
    }

    protected IvldForecastSales toUnwrappedModel(
        IvldForecastSales ivldForecastSales) {
        if (ivldForecastSales instanceof IvldForecastSalesImpl) {
            return ivldForecastSales;
        }

        IvldForecastSalesImpl ivldForecastSalesImpl = new IvldForecastSalesImpl();

        ivldForecastSalesImpl.setNew(ivldForecastSales.isNew());
        ivldForecastSalesImpl.setPrimaryKey(ivldForecastSales.getPrimaryKey());

        ivldForecastSalesImpl.setPrice(ivldForecastSales.getPrice());
        ivldForecastSalesImpl.setForecastYear(ivldForecastSales.getForecastYear());
        ivldForecastSalesImpl.setForecastDate(ivldForecastSales.getForecastDate());
        ivldForecastSalesImpl.setModifiedDate(ivldForecastSales.getModifiedDate());
        ivldForecastSalesImpl.setForecastValue(ivldForecastSales.getForecastValue());
        ivldForecastSalesImpl.setForecastIntfid(ivldForecastSales.getForecastIntfid());
        ivldForecastSalesImpl.setDollars(ivldForecastSales.getDollars());
        ivldForecastSalesImpl.setNdc(ivldForecastSales.getNdc());
        ivldForecastSalesImpl.setActualSalesPercentage(ivldForecastSales.getActualSalesPercentage());
        ivldForecastSalesImpl.setSource(ivldForecastSales.getSource());
        ivldForecastSalesImpl.setCreatedDate(ivldForecastSales.getCreatedDate());
        ivldForecastSalesImpl.setCreatedBy(ivldForecastSales.getCreatedBy());
        ivldForecastSalesImpl.setAddChgDelIndicator(ivldForecastSales.getAddChgDelIndicator());
        ivldForecastSalesImpl.setActualSalesPercentageMonth(ivldForecastSales.getActualSalesPercentageMonth());
        ivldForecastSalesImpl.setErrorCode(ivldForecastSales.getErrorCode());
        ivldForecastSalesImpl.setIntfInsertedDate(ivldForecastSales.getIntfInsertedDate());
        ivldForecastSalesImpl.setModifiedBy(ivldForecastSales.getModifiedBy());
        ivldForecastSalesImpl.setReprocessedFlag(ivldForecastSales.getReprocessedFlag());
        ivldForecastSalesImpl.setPercentageEstimate(ivldForecastSales.getPercentageEstimate());
        ivldForecastSalesImpl.setPercentageEstimateYear(ivldForecastSales.getPercentageEstimateYear());
        ivldForecastSalesImpl.setUnits(ivldForecastSales.getUnits());
        ivldForecastSalesImpl.setReasonForFailure(ivldForecastSales.getReasonForFailure());
        ivldForecastSalesImpl.setForecastStartDate(ivldForecastSales.getForecastStartDate());
        ivldForecastSalesImpl.setForecastValueType(ivldForecastSales.getForecastValueType());
        ivldForecastSalesImpl.setForecastedSalesPercentMonth(ivldForecastSales.getForecastedSalesPercentMonth());
        ivldForecastSalesImpl.setCountry(ivldForecastSales.getCountry());
        ivldForecastSalesImpl.setProduct(ivldForecastSales.getProduct());
        ivldForecastSalesImpl.setBatchId(ivldForecastSales.getBatchId());
        ivldForecastSalesImpl.setForecastVer(ivldForecastSales.getForecastVer());
        ivldForecastSalesImpl.setForecastMonth(ivldForecastSales.getForecastMonth());
        ivldForecastSalesImpl.setIvldForecastSalesSid(ivldForecastSales.getIvldForecastSalesSid());
        ivldForecastSalesImpl.setErrorField(ivldForecastSales.getErrorField());
        ivldForecastSalesImpl.setSegment(ivldForecastSales.getSegment());
        ivldForecastSalesImpl.setBrand(ivldForecastSales.getBrand());
        ivldForecastSalesImpl.setForecastedSalesPercentage(ivldForecastSales.getForecastedSalesPercentage());
        ivldForecastSalesImpl.setForecastName(ivldForecastSales.getForecastName());
        ivldForecastSalesImpl.setCheckRecord(ivldForecastSales.isCheckRecord());

        return ivldForecastSalesImpl;
    }

    /**
     * Returns the ivld forecast sales with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld forecast sales
     * @return the ivld forecast sales
     * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldForecastSalesException, SystemException {
        IvldForecastSales ivldForecastSales = fetchByPrimaryKey(primaryKey);

        if (ivldForecastSales == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldForecastSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldForecastSales;
    }

    /**
     * Returns the ivld forecast sales with the primary key or throws a {@link com.stpl.app.NoSuchIvldForecastSalesException} if it could not be found.
     *
     * @param ivldForecastSalesSid the primary key of the ivld forecast sales
     * @return the ivld forecast sales
     * @throws com.stpl.app.NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales findByPrimaryKey(int ivldForecastSalesSid)
        throws NoSuchIvldForecastSalesException, SystemException {
        return findByPrimaryKey((Serializable) ivldForecastSalesSid);
    }

    /**
     * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld forecast sales
     * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldForecastSales ivldForecastSales = (IvldForecastSales) EntityCacheUtil.getResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
                IvldForecastSalesImpl.class, primaryKey);

        if (ivldForecastSales == _nullIvldForecastSales) {
            return null;
        }

        if (ivldForecastSales == null) {
            Session session = null;

            try {
                session = openSession();

                ivldForecastSales = (IvldForecastSales) session.get(IvldForecastSalesImpl.class,
                        primaryKey);

                if (ivldForecastSales != null) {
                    cacheResult(ivldForecastSales);
                } else {
                    EntityCacheUtil.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
                        IvldForecastSalesImpl.class, primaryKey,
                        _nullIvldForecastSales);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
                    IvldForecastSalesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldForecastSales;
    }

    /**
     * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldForecastSalesSid the primary key of the ivld forecast sales
     * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldForecastSales fetchByPrimaryKey(int ivldForecastSalesSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldForecastSalesSid);
    }

    /**
     * Returns all the ivld forecast saleses.
     *
     * @return the ivld forecast saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldForecastSales> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld forecast saleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld forecast saleses
     * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
     * @return the range of ivld forecast saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldForecastSales> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld forecast saleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld forecast saleses
     * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld forecast saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldForecastSales> findAll(int start, int end,
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

        List<IvldForecastSales> list = (List<IvldForecastSales>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDFORECASTSALES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDFORECASTSALES;

                if (pagination) {
                    sql = sql.concat(IvldForecastSalesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldForecastSales>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldForecastSales>(list);
                } else {
                    list = (List<IvldForecastSales>) QueryUtil.list(q,
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
     * Removes all the ivld forecast saleses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldForecastSales ivldForecastSales : findAll()) {
            remove(ivldForecastSales);
        }
    }

    /**
     * Returns the number of ivld forecast saleses.
     *
     * @return the number of ivld forecast saleses
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

                Query q = session.createQuery(_SQL_COUNT_IVLDFORECASTSALES);

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
     * Initializes the ivld forecast sales persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldForecastSales")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldForecastSales>> listenersList = new ArrayList<ModelListener<IvldForecastSales>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldForecastSales>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldForecastSalesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
