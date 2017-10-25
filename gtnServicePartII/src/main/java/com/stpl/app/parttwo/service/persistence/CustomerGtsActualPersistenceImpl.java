package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCustomerGtsActualException;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.model.impl.CustomerGtsActualImpl;
import com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl;
import com.stpl.app.parttwo.service.persistence.CustomerGtsActualPersistence;

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
 * The persistence implementation for the customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsActualPersistence
 * @see CustomerGtsActualUtil
 * @generated
 */
public class CustomerGtsActualPersistenceImpl extends BasePersistenceImpl<CustomerGtsActual>
    implements CustomerGtsActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CustomerGtsActualUtil} to access the customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CustomerGtsActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
            CustomerGtsActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED,
            CustomerGtsActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CUSTOMERGTSACTUAL = "SELECT customerGtsActual FROM CustomerGtsActual customerGtsActual";
    private static final String _SQL_COUNT_CUSTOMERGTSACTUAL = "SELECT COUNT(customerGtsActual) FROM CustomerGtsActual customerGtsActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "customerGtsActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomerGtsActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CustomerGtsActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "parentAccountId", "contractId", "accountId",
                "customerGtsActualSid", "orderReceivedDate", "itemId",
                "modifiedDate", "amount", "orderNumber", "organizationKey",
                "invoiceDate", "customerGtsActualIntfId", "createdDate",
                "createdBy", "source", "batchId", "salesId", "itemUom",
                "inboundStatus", "modifiedBy", "invoiceNumber", "lotNo",
                "invoiceLineNumber", "quantity"
            });
    private static CustomerGtsActual _nullCustomerGtsActual = new CustomerGtsActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CustomerGtsActual> toCacheModel() {
                return _nullCustomerGtsActualCacheModel;
            }
        };

    private static CacheModel<CustomerGtsActual> _nullCustomerGtsActualCacheModel =
        new CacheModel<CustomerGtsActual>() {
            @Override
            public CustomerGtsActual toEntityModel() {
                return _nullCustomerGtsActual;
            }
        };

    public CustomerGtsActualPersistenceImpl() {
        setModelClass(CustomerGtsActual.class);
    }

    /**
     * Caches the customer gts actual in the entity cache if it is enabled.
     *
     * @param customerGtsActual the customer gts actual
     */
    @Override
    public void cacheResult(CustomerGtsActual customerGtsActual) {
        EntityCacheUtil.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey(),
            customerGtsActual);

        customerGtsActual.resetOriginalValues();
    }

    /**
     * Caches the customer gts actuals in the entity cache if it is enabled.
     *
     * @param customerGtsActuals the customer gts actuals
     */
    @Override
    public void cacheResult(List<CustomerGtsActual> customerGtsActuals) {
        for (CustomerGtsActual customerGtsActual : customerGtsActuals) {
            if (EntityCacheUtil.getResult(
                        CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                        CustomerGtsActualImpl.class,
                        customerGtsActual.getPrimaryKey()) == null) {
                cacheResult(customerGtsActual);
            } else {
                customerGtsActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all customer gts actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CustomerGtsActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CustomerGtsActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the customer gts actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CustomerGtsActual customerGtsActual) {
        EntityCacheUtil.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CustomerGtsActual> customerGtsActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CustomerGtsActual customerGtsActual : customerGtsActuals) {
            EntityCacheUtil.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new customer gts actual with the primary key. Does not add the customer gts actual to the database.
     *
     * @param customerGtsActualSid the primary key for the new customer gts actual
     * @return the new customer gts actual
     */
    @Override
    public CustomerGtsActual create(int customerGtsActualSid) {
        CustomerGtsActual customerGtsActual = new CustomerGtsActualImpl();

        customerGtsActual.setNew(true);
        customerGtsActual.setPrimaryKey(customerGtsActualSid);

        return customerGtsActual;
    }

    /**
     * Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param customerGtsActualSid the primary key of the customer gts actual
     * @return the customer gts actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual remove(int customerGtsActualSid)
        throws NoSuchCustomerGtsActualException, SystemException {
        return remove((Serializable) customerGtsActualSid);
    }

    /**
     * Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the customer gts actual
     * @return the customer gts actual that was removed
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual remove(Serializable primaryKey)
        throws NoSuchCustomerGtsActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CustomerGtsActual customerGtsActual = (CustomerGtsActual) session.get(CustomerGtsActualImpl.class,
                    primaryKey);

            if (customerGtsActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(customerGtsActual);
        } catch (NoSuchCustomerGtsActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CustomerGtsActual removeImpl(CustomerGtsActual customerGtsActual)
        throws SystemException {
        customerGtsActual = toUnwrappedModel(customerGtsActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(customerGtsActual)) {
                customerGtsActual = (CustomerGtsActual) session.get(CustomerGtsActualImpl.class,
                        customerGtsActual.getPrimaryKeyObj());
            }

            if (customerGtsActual != null) {
                session.delete(customerGtsActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (customerGtsActual != null) {
            clearCache(customerGtsActual);
        }

        return customerGtsActual;
    }

    @Override
    public CustomerGtsActual updateImpl(
        com.stpl.app.parttwo.model.CustomerGtsActual customerGtsActual)
        throws SystemException {
        customerGtsActual = toUnwrappedModel(customerGtsActual);

        boolean isNew = customerGtsActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (customerGtsActual.isNew()) {
                session.save(customerGtsActual);

                customerGtsActual.setNew(false);
            } else {
                session.merge(customerGtsActual);
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

        EntityCacheUtil.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
            CustomerGtsActualImpl.class, customerGtsActual.getPrimaryKey(),
            customerGtsActual);

        return customerGtsActual;
    }

    protected CustomerGtsActual toUnwrappedModel(
        CustomerGtsActual customerGtsActual) {
        if (customerGtsActual instanceof CustomerGtsActualImpl) {
            return customerGtsActual;
        }

        CustomerGtsActualImpl customerGtsActualImpl = new CustomerGtsActualImpl();

        customerGtsActualImpl.setNew(customerGtsActual.isNew());
        customerGtsActualImpl.setPrimaryKey(customerGtsActual.getPrimaryKey());

        customerGtsActualImpl.setParentAccountId(customerGtsActual.getParentAccountId());
        customerGtsActualImpl.setContractId(customerGtsActual.getContractId());
        customerGtsActualImpl.setAccountId(customerGtsActual.getAccountId());
        customerGtsActualImpl.setCustomerGtsActualSid(customerGtsActual.getCustomerGtsActualSid());
        customerGtsActualImpl.setOrderReceivedDate(customerGtsActual.getOrderReceivedDate());
        customerGtsActualImpl.setItemId(customerGtsActual.getItemId());
        customerGtsActualImpl.setModifiedDate(customerGtsActual.getModifiedDate());
        customerGtsActualImpl.setAmount(customerGtsActual.getAmount());
        customerGtsActualImpl.setOrderNumber(customerGtsActual.getOrderNumber());
        customerGtsActualImpl.setOrganizationKey(customerGtsActual.getOrganizationKey());
        customerGtsActualImpl.setInvoiceDate(customerGtsActual.getInvoiceDate());
        customerGtsActualImpl.setCustomerGtsActualIntfId(customerGtsActual.getCustomerGtsActualIntfId());
        customerGtsActualImpl.setCreatedDate(customerGtsActual.getCreatedDate());
        customerGtsActualImpl.setCreatedBy(customerGtsActual.getCreatedBy());
        customerGtsActualImpl.setSource(customerGtsActual.getSource());
        customerGtsActualImpl.setBatchId(customerGtsActual.getBatchId());
        customerGtsActualImpl.setSalesId(customerGtsActual.getSalesId());
        customerGtsActualImpl.setItemUom(customerGtsActual.getItemUom());
        customerGtsActualImpl.setInboundStatus(customerGtsActual.getInboundStatus());
        customerGtsActualImpl.setModifiedBy(customerGtsActual.getModifiedBy());
        customerGtsActualImpl.setInvoiceNumber(customerGtsActual.getInvoiceNumber());
        customerGtsActualImpl.setLotNo(customerGtsActual.getLotNo());
        customerGtsActualImpl.setInvoiceLineNumber(customerGtsActual.getInvoiceLineNumber());
        customerGtsActualImpl.setQuantity(customerGtsActual.getQuantity());

        return customerGtsActualImpl;
    }

    /**
     * Returns the customer gts actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the customer gts actual
     * @return the customer gts actual
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCustomerGtsActualException, SystemException {
        CustomerGtsActual customerGtsActual = fetchByPrimaryKey(primaryKey);

        if (customerGtsActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCustomerGtsActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return customerGtsActual;
    }

    /**
     * Returns the customer gts actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCustomerGtsActualException} if it could not be found.
     *
     * @param customerGtsActualSid the primary key of the customer gts actual
     * @return the customer gts actual
     * @throws com.stpl.app.parttwo.NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual findByPrimaryKey(int customerGtsActualSid)
        throws NoSuchCustomerGtsActualException, SystemException {
        return findByPrimaryKey((Serializable) customerGtsActualSid);
    }

    /**
     * Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the customer gts actual
     * @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CustomerGtsActual customerGtsActual = (CustomerGtsActual) EntityCacheUtil.getResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                CustomerGtsActualImpl.class, primaryKey);

        if (customerGtsActual == _nullCustomerGtsActual) {
            return null;
        }

        if (customerGtsActual == null) {
            Session session = null;

            try {
                session = openSession();

                customerGtsActual = (CustomerGtsActual) session.get(CustomerGtsActualImpl.class,
                        primaryKey);

                if (customerGtsActual != null) {
                    cacheResult(customerGtsActual);
                } else {
                    EntityCacheUtil.putResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                        CustomerGtsActualImpl.class, primaryKey,
                        _nullCustomerGtsActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CustomerGtsActualModelImpl.ENTITY_CACHE_ENABLED,
                    CustomerGtsActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return customerGtsActual;
    }

    /**
     * Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param customerGtsActualSid the primary key of the customer gts actual
     * @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomerGtsActual fetchByPrimaryKey(int customerGtsActualSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) customerGtsActualSid);
    }

    /**
     * Returns all the customer gts actuals.
     *
     * @return the customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the customer gts actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of customer gts actuals
     * @param end the upper bound of the range of customer gts actuals (not inclusive)
     * @return the range of customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the customer gts actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of customer gts actuals
     * @param end the upper bound of the range of customer gts actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of customer gts actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomerGtsActual> findAll(int start, int end,
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

        List<CustomerGtsActual> list = (List<CustomerGtsActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CUSTOMERGTSACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CUSTOMERGTSACTUAL;

                if (pagination) {
                    sql = sql.concat(CustomerGtsActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CustomerGtsActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CustomerGtsActual>(list);
                } else {
                    list = (List<CustomerGtsActual>) QueryUtil.list(q,
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
     * Removes all the customer gts actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CustomerGtsActual customerGtsActual : findAll()) {
            remove(customerGtsActual);
        }
    }

    /**
     * Returns the number of customer gts actuals.
     *
     * @return the number of customer gts actuals
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

                Query q = session.createQuery(_SQL_COUNT_CUSTOMERGTSACTUAL);

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
     * Initializes the customer gts actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CustomerGtsActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CustomerGtsActual>> listenersList = new ArrayList<ModelListener<CustomerGtsActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CustomerGtsActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CustomerGtsActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
