package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCustomerGtsForecastException;
import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.CustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.CustomerGtsForecastPersistence;

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
 * The persistence implementation for the customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsForecastPersistence
 * @see CustomerGtsForecastUtil
 * @generated
 */
public class CustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<CustomerGtsForecast>
    implements CustomerGtsForecastPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CustomerGtsForecastUtil} to access the customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CustomerGtsForecastImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            CustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
            CustomerGtsForecastImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CUSTOMERGTSFORECAST = "SELECT customerGtsForecast FROM CustomerGtsForecast customerGtsForecast";
    private static final String _SQL_COUNT_CUSTOMERGTSFORECAST = "SELECT COUNT(customerGtsForecast) FROM CustomerGtsForecast customerGtsForecast";
    private static final String _ORDER_BY_ENTITY_ALIAS = "customerGtsForecast.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomerGtsForecast exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CustomerGtsForecastPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "price", "itemMasterSid", "forecastYear", "deductionAmount",
                "deductionId", "forecastDate", "itemId", "modifiedDate",
                "brandMasterSid", "source", "createdDate", "createdBy",
                "addChgDelIndicator", "inboundStatus", "modifiedBy",
                "salesAmount", "deductionType", "companyMasterSid", "units",
                "deductionRate", "customerGtsForecastSid", "country",
                "companyId", "forecastValueType", "deductionCategory",
                "adjustmentCode", "deductionProgramType", "recordLockStatus",
                "salesInclusion", "forecastVer", "batchId", "priceType",
                "forecastMonth", "deductionInclusion", "segment", "brand",
                "forecastName"
            });
    private static CustomerGtsForecast _nullCustomerGtsForecast = new CustomerGtsForecastImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CustomerGtsForecast> toCacheModel() {
                return _nullCustomerGtsForecastCacheModel;
            }
        };

    private static CacheModel<CustomerGtsForecast> _nullCustomerGtsForecastCacheModel =
        new CacheModel<CustomerGtsForecast>() {
            @Override
            public CustomerGtsForecast toEntityModel() {
                return _nullCustomerGtsForecast;
            }
        };

    public CustomerGtsForecastPersistenceImpl() {
        setModelClass(CustomerGtsForecast.class);
    }

    /**
     * Caches the customer gts forecast in the entity cache if it is enabled.
     *
     * @param customerGtsForecast the customer gts forecast
     */
    @Override
    public void cacheResult(CustomerGtsForecast customerGtsForecast) {
        EntityCacheUtil.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey(),
            customerGtsForecast);

        customerGtsForecast.resetOriginalValues();
    }

    /**
     * Caches the customer gts forecasts in the entity cache if it is enabled.
     *
     * @param customerGtsForecasts the customer gts forecasts
     */
    @Override
    public void cacheResult(List<CustomerGtsForecast> customerGtsForecasts) {
        for (CustomerGtsForecast customerGtsForecast : customerGtsForecasts) {
            if (EntityCacheUtil.getResult(
                        CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        CustomerGtsForecastImpl.class,
                        customerGtsForecast.getPrimaryKey()) == null) {
                cacheResult(customerGtsForecast);
            } else {
                customerGtsForecast.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all customer gts forecasts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CustomerGtsForecastImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CustomerGtsForecastImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the customer gts forecast.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CustomerGtsForecast customerGtsForecast) {
        EntityCacheUtil.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CustomerGtsForecast> customerGtsForecasts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CustomerGtsForecast customerGtsForecast : customerGtsForecasts) {
            EntityCacheUtil.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                CustomerGtsForecastImpl.class,
                customerGtsForecast.getPrimaryKey());
        }
    }

    /**
     * Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
     *
     * @param customerGtsForecastSid the primary key for the new customer gts forecast
     * @return the new customer gts forecast
     */
    @Override
    public CustomerGtsForecast create(int customerGtsForecastSid) {
        CustomerGtsForecast customerGtsForecast = new CustomerGtsForecastImpl();

        customerGtsForecast.setNew(true);
        customerGtsForecast.setPrimaryKey(customerGtsForecastSid);

        return customerGtsForecast;
    }

    /**
     * Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param customerGtsForecastSid the primary key of the customer gts forecast
     * @return the customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast remove(int customerGtsForecastSid)
        throws NoSuchCustomerGtsForecastException, SystemException {
        return remove((Serializable) customerGtsForecastSid);
    }

    /**
     * Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the customer gts forecast
     * @return the customer gts forecast that was removed
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast remove(Serializable primaryKey)
        throws NoSuchCustomerGtsForecastException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CustomerGtsForecast customerGtsForecast = (CustomerGtsForecast) session.get(CustomerGtsForecastImpl.class,
                    primaryKey);

            if (customerGtsForecast == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(customerGtsForecast);
        } catch (NoSuchCustomerGtsForecastException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CustomerGtsForecast removeImpl(
        CustomerGtsForecast customerGtsForecast) throws SystemException {
        customerGtsForecast = toUnwrappedModel(customerGtsForecast);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(customerGtsForecast)) {
                customerGtsForecast = (CustomerGtsForecast) session.get(CustomerGtsForecastImpl.class,
                        customerGtsForecast.getPrimaryKeyObj());
            }

            if (customerGtsForecast != null) {
                session.delete(customerGtsForecast);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (customerGtsForecast != null) {
            clearCache(customerGtsForecast);
        }

        return customerGtsForecast;
    }

    @Override
    public CustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast)
        throws SystemException {
        customerGtsForecast = toUnwrappedModel(customerGtsForecast);

        boolean isNew = customerGtsForecast.isNew();

        Session session = null;

        try {
            session = openSession();

            if (customerGtsForecast.isNew()) {
                session.save(customerGtsForecast);

                customerGtsForecast.setNew(false);
            } else {
                session.merge(customerGtsForecast);
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

        EntityCacheUtil.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey(),
            customerGtsForecast);

        return customerGtsForecast;
    }

    protected CustomerGtsForecast toUnwrappedModel(
        CustomerGtsForecast customerGtsForecast) {
        if (customerGtsForecast instanceof CustomerGtsForecastImpl) {
            return customerGtsForecast;
        }

        CustomerGtsForecastImpl customerGtsForecastImpl = new CustomerGtsForecastImpl();

        customerGtsForecastImpl.setNew(customerGtsForecast.isNew());
        customerGtsForecastImpl.setPrimaryKey(customerGtsForecast.getPrimaryKey());

        customerGtsForecastImpl.setPrice(customerGtsForecast.getPrice());
        customerGtsForecastImpl.setItemMasterSid(customerGtsForecast.getItemMasterSid());
        customerGtsForecastImpl.setForecastYear(customerGtsForecast.getForecastYear());
        customerGtsForecastImpl.setDeductionAmount(customerGtsForecast.getDeductionAmount());
        customerGtsForecastImpl.setDeductionId(customerGtsForecast.getDeductionId());
        customerGtsForecastImpl.setForecastDate(customerGtsForecast.getForecastDate());
        customerGtsForecastImpl.setItemId(customerGtsForecast.getItemId());
        customerGtsForecastImpl.setModifiedDate(customerGtsForecast.getModifiedDate());
        customerGtsForecastImpl.setBrandMasterSid(customerGtsForecast.getBrandMasterSid());
        customerGtsForecastImpl.setSource(customerGtsForecast.getSource());
        customerGtsForecastImpl.setCreatedDate(customerGtsForecast.getCreatedDate());
        customerGtsForecastImpl.setCreatedBy(customerGtsForecast.getCreatedBy());
        customerGtsForecastImpl.setAddChgDelIndicator(customerGtsForecast.getAddChgDelIndicator());
        customerGtsForecastImpl.setInboundStatus(customerGtsForecast.getInboundStatus());
        customerGtsForecastImpl.setModifiedBy(customerGtsForecast.getModifiedBy());
        customerGtsForecastImpl.setSalesAmount(customerGtsForecast.getSalesAmount());
        customerGtsForecastImpl.setDeductionType(customerGtsForecast.getDeductionType());
        customerGtsForecastImpl.setCompanyMasterSid(customerGtsForecast.getCompanyMasterSid());
        customerGtsForecastImpl.setUnits(customerGtsForecast.getUnits());
        customerGtsForecastImpl.setDeductionRate(customerGtsForecast.getDeductionRate());
        customerGtsForecastImpl.setCustomerGtsForecastSid(customerGtsForecast.getCustomerGtsForecastSid());
        customerGtsForecastImpl.setCountry(customerGtsForecast.getCountry());
        customerGtsForecastImpl.setCompanyId(customerGtsForecast.getCompanyId());
        customerGtsForecastImpl.setForecastValueType(customerGtsForecast.getForecastValueType());
        customerGtsForecastImpl.setDeductionCategory(customerGtsForecast.getDeductionCategory());
        customerGtsForecastImpl.setAdjustmentCode(customerGtsForecast.getAdjustmentCode());
        customerGtsForecastImpl.setDeductionProgramType(customerGtsForecast.getDeductionProgramType());
        customerGtsForecastImpl.setRecordLockStatus(customerGtsForecast.isRecordLockStatus());
        customerGtsForecastImpl.setSalesInclusion(customerGtsForecast.getSalesInclusion());
        customerGtsForecastImpl.setForecastVer(customerGtsForecast.getForecastVer());
        customerGtsForecastImpl.setBatchId(customerGtsForecast.getBatchId());
        customerGtsForecastImpl.setPriceType(customerGtsForecast.getPriceType());
        customerGtsForecastImpl.setForecastMonth(customerGtsForecast.getForecastMonth());
        customerGtsForecastImpl.setDeductionInclusion(customerGtsForecast.getDeductionInclusion());
        customerGtsForecastImpl.setSegment(customerGtsForecast.getSegment());
        customerGtsForecastImpl.setBrand(customerGtsForecast.getBrand());
        customerGtsForecastImpl.setForecastName(customerGtsForecast.getForecastName());

        return customerGtsForecastImpl;
    }

    /**
     * Returns the customer gts forecast with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the customer gts forecast
     * @return the customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCustomerGtsForecastException, SystemException {
        CustomerGtsForecast customerGtsForecast = fetchByPrimaryKey(primaryKey);

        if (customerGtsForecast == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return customerGtsForecast;
    }

    /**
     * Returns the customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCustomerGtsForecastException} if it could not be found.
     *
     * @param customerGtsForecastSid the primary key of the customer gts forecast
     * @return the customer gts forecast
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
        throws NoSuchCustomerGtsForecastException, SystemException {
        return findByPrimaryKey((Serializable) customerGtsForecastSid);
    }

    /**
     * Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the customer gts forecast
     * @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CustomerGtsForecast customerGtsForecast = (CustomerGtsForecast) EntityCacheUtil.getResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                CustomerGtsForecastImpl.class, primaryKey);

        if (customerGtsForecast == _nullCustomerGtsForecast) {
            return null;
        }

        if (customerGtsForecast == null) {
            Session session = null;

            try {
                session = openSession();

                customerGtsForecast = (CustomerGtsForecast) session.get(CustomerGtsForecastImpl.class,
                        primaryKey);

                if (customerGtsForecast != null) {
                    cacheResult(customerGtsForecast);
                } else {
                    EntityCacheUtil.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                        CustomerGtsForecastImpl.class, primaryKey,
                        _nullCustomerGtsForecast);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
                    CustomerGtsForecastImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return customerGtsForecast;
    }

    /**
     * Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param customerGtsForecastSid the primary key of the customer gts forecast
     * @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) customerGtsForecastSid);
    }

    /**
     * Returns all the customer gts forecasts.
     *
     * @return the customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsForecast> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of customer gts forecasts
     * @param end the upper bound of the range of customer gts forecasts (not inclusive)
     * @return the range of customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsForecast> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the customer gts forecasts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of customer gts forecasts
     * @param end the upper bound of the range of customer gts forecasts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of customer gts forecasts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsForecast> findAll(int start, int end,
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

        List<CustomerGtsForecast> list = (List<CustomerGtsForecast>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CUSTOMERGTSFORECAST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CUSTOMERGTSFORECAST;

                if (pagination) {
                    sql = sql.concat(CustomerGtsForecastModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CustomerGtsForecast>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CustomerGtsForecast>(list);
                } else {
                    list = (List<CustomerGtsForecast>) QueryUtil.list(q,
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
     * Removes all the customer gts forecasts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CustomerGtsForecast customerGtsForecast : findAll()) {
            remove(customerGtsForecast);
        }
    }

    /**
     * Returns the number of customer gts forecasts.
     *
     * @return the number of customer gts forecasts
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

                Query q = session.createQuery(_SQL_COUNT_CUSTOMERGTSFORECAST);

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
     * Initializes the customer gts forecast persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CustomerGtsForecast")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CustomerGtsForecast>> listenersList = new ArrayList<ModelListener<CustomerGtsForecast>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CustomerGtsForecast>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CustomerGtsForecastImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
