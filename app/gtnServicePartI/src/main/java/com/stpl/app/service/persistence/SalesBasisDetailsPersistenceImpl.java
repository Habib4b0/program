package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchSalesBasisDetailsException;
import com.stpl.app.model.SalesBasisDetails;
import com.stpl.app.model.impl.SalesBasisDetailsImpl;
import com.stpl.app.model.impl.SalesBasisDetailsModelImpl;
import com.stpl.app.service.persistence.SalesBasisDetailsPersistence;

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
 * The persistence implementation for the sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesBasisDetailsPersistence
 * @see SalesBasisDetailsUtil
 * @generated
 */
public class SalesBasisDetailsPersistenceImpl extends BasePersistenceImpl<SalesBasisDetails>
    implements SalesBasisDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SalesBasisDetailsUtil} to access the sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SalesBasisDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
            SalesBasisDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
            SalesBasisDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_SALESBASISDETAILS = "SELECT salesBasisDetails FROM SalesBasisDetails salesBasisDetails";
    private static final String _SQL_COUNT_SALESBASISDETAILS = "SELECT COUNT(salesBasisDetails) FROM SalesBasisDetails salesBasisDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "salesBasisDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SalesBasisDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SalesBasisDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "netSalesFormulaMasterSid", "recordLockStatus",
                "modifiedBy", "createdDate", "contractMasterSid", "source",
                "cdrModelSid", "salesBasisDetailsSid", "cfpContractDetailsSid",
                "modifiedDate", "inboundStatus"
            });
    private static SalesBasisDetails _nullSalesBasisDetails = new SalesBasisDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SalesBasisDetails> toCacheModel() {
                return _nullSalesBasisDetailsCacheModel;
            }
        };

    private static CacheModel<SalesBasisDetails> _nullSalesBasisDetailsCacheModel =
        new CacheModel<SalesBasisDetails>() {
            @Override
            public SalesBasisDetails toEntityModel() {
                return _nullSalesBasisDetails;
            }
        };

    public SalesBasisDetailsPersistenceImpl() {
        setModelClass(SalesBasisDetails.class);
    }

    /**
     * Caches the sales basis details in the entity cache if it is enabled.
     *
     * @param salesBasisDetails the sales basis details
     */
    @Override
    public void cacheResult(SalesBasisDetails salesBasisDetails) {
        EntityCacheUtil.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey(),
            salesBasisDetails);

        salesBasisDetails.resetOriginalValues();
    }

    /**
     * Caches the sales basis detailses in the entity cache if it is enabled.
     *
     * @param salesBasisDetailses the sales basis detailses
     */
    @Override
    public void cacheResult(List<SalesBasisDetails> salesBasisDetailses) {
        for (SalesBasisDetails salesBasisDetails : salesBasisDetailses) {
            if (EntityCacheUtil.getResult(
                        SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        SalesBasisDetailsImpl.class,
                        salesBasisDetails.getPrimaryKey()) == null) {
                cacheResult(salesBasisDetails);
            } else {
                salesBasisDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all sales basis detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SalesBasisDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SalesBasisDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the sales basis details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SalesBasisDetails salesBasisDetails) {
        EntityCacheUtil.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SalesBasisDetails> salesBasisDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SalesBasisDetails salesBasisDetails : salesBasisDetailses) {
            EntityCacheUtil.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
     *
     * @param salesBasisDetailsSid the primary key for the new sales basis details
     * @return the new sales basis details
     */
    @Override
    public SalesBasisDetails create(int salesBasisDetailsSid) {
        SalesBasisDetails salesBasisDetails = new SalesBasisDetailsImpl();

        salesBasisDetails.setNew(true);
        salesBasisDetails.setPrimaryKey(salesBasisDetailsSid);

        return salesBasisDetails;
    }

    /**
     * Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param salesBasisDetailsSid the primary key of the sales basis details
     * @return the sales basis details that was removed
     * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails remove(int salesBasisDetailsSid)
        throws NoSuchSalesBasisDetailsException, SystemException {
        return remove((Serializable) salesBasisDetailsSid);
    }

    /**
     * Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the sales basis details
     * @return the sales basis details that was removed
     * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails remove(Serializable primaryKey)
        throws NoSuchSalesBasisDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SalesBasisDetails salesBasisDetails = (SalesBasisDetails) session.get(SalesBasisDetailsImpl.class,
                    primaryKey);

            if (salesBasisDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(salesBasisDetails);
        } catch (NoSuchSalesBasisDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SalesBasisDetails removeImpl(SalesBasisDetails salesBasisDetails)
        throws SystemException {
        salesBasisDetails = toUnwrappedModel(salesBasisDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(salesBasisDetails)) {
                salesBasisDetails = (SalesBasisDetails) session.get(SalesBasisDetailsImpl.class,
                        salesBasisDetails.getPrimaryKeyObj());
            }

            if (salesBasisDetails != null) {
                session.delete(salesBasisDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (salesBasisDetails != null) {
            clearCache(salesBasisDetails);
        }

        return salesBasisDetails;
    }

    @Override
    public SalesBasisDetails updateImpl(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails)
        throws SystemException {
        salesBasisDetails = toUnwrappedModel(salesBasisDetails);

        boolean isNew = salesBasisDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (salesBasisDetails.isNew()) {
                session.save(salesBasisDetails);

                salesBasisDetails.setNew(false);
            } else {
                session.merge(salesBasisDetails);
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

        EntityCacheUtil.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey(),
            salesBasisDetails);

        return salesBasisDetails;
    }

    protected SalesBasisDetails toUnwrappedModel(
        SalesBasisDetails salesBasisDetails) {
        if (salesBasisDetails instanceof SalesBasisDetailsImpl) {
            return salesBasisDetails;
        }

        SalesBasisDetailsImpl salesBasisDetailsImpl = new SalesBasisDetailsImpl();

        salesBasisDetailsImpl.setNew(salesBasisDetails.isNew());
        salesBasisDetailsImpl.setPrimaryKey(salesBasisDetails.getPrimaryKey());

        salesBasisDetailsImpl.setCreatedBy(salesBasisDetails.getCreatedBy());
        salesBasisDetailsImpl.setNetSalesFormulaMasterSid(salesBasisDetails.getNetSalesFormulaMasterSid());
        salesBasisDetailsImpl.setRecordLockStatus(salesBasisDetails.isRecordLockStatus());
        salesBasisDetailsImpl.setModifiedBy(salesBasisDetails.getModifiedBy());
        salesBasisDetailsImpl.setCreatedDate(salesBasisDetails.getCreatedDate());
        salesBasisDetailsImpl.setContractMasterSid(salesBasisDetails.getContractMasterSid());
        salesBasisDetailsImpl.setSource(salesBasisDetails.getSource());
        salesBasisDetailsImpl.setCdrModelSid(salesBasisDetails.getCdrModelSid());
        salesBasisDetailsImpl.setSalesBasisDetailsSid(salesBasisDetails.getSalesBasisDetailsSid());
        salesBasisDetailsImpl.setCfpContractDetailsSid(salesBasisDetails.getCfpContractDetailsSid());
        salesBasisDetailsImpl.setModifiedDate(salesBasisDetails.getModifiedDate());
        salesBasisDetailsImpl.setInboundStatus(salesBasisDetails.getInboundStatus());

        return salesBasisDetailsImpl;
    }

    /**
     * Returns the sales basis details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the sales basis details
     * @return the sales basis details
     * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSalesBasisDetailsException, SystemException {
        SalesBasisDetails salesBasisDetails = fetchByPrimaryKey(primaryKey);

        if (salesBasisDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return salesBasisDetails;
    }

    /**
     * Returns the sales basis details with the primary key or throws a {@link com.stpl.app.NoSuchSalesBasisDetailsException} if it could not be found.
     *
     * @param salesBasisDetailsSid the primary key of the sales basis details
     * @return the sales basis details
     * @throws com.stpl.app.NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails findByPrimaryKey(int salesBasisDetailsSid)
        throws NoSuchSalesBasisDetailsException, SystemException {
        return findByPrimaryKey((Serializable) salesBasisDetailsSid);
    }

    /**
     * Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the sales basis details
     * @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SalesBasisDetails salesBasisDetails = (SalesBasisDetails) EntityCacheUtil.getResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                SalesBasisDetailsImpl.class, primaryKey);

        if (salesBasisDetails == _nullSalesBasisDetails) {
            return null;
        }

        if (salesBasisDetails == null) {
            Session session = null;

            try {
                session = openSession();

                salesBasisDetails = (SalesBasisDetails) session.get(SalesBasisDetailsImpl.class,
                        primaryKey);

                if (salesBasisDetails != null) {
                    cacheResult(salesBasisDetails);
                } else {
                    EntityCacheUtil.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        SalesBasisDetailsImpl.class, primaryKey,
                        _nullSalesBasisDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    SalesBasisDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return salesBasisDetails;
    }

    /**
     * Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param salesBasisDetailsSid the primary key of the sales basis details
     * @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SalesBasisDetails fetchByPrimaryKey(int salesBasisDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) salesBasisDetailsSid);
    }

    /**
     * Returns all the sales basis detailses.
     *
     * @return the sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesBasisDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sales basis detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sales basis detailses
     * @param end the upper bound of the range of sales basis detailses (not inclusive)
     * @return the range of sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesBasisDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the sales basis detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sales basis detailses
     * @param end the upper bound of the range of sales basis detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SalesBasisDetails> findAll(int start, int end,
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

        List<SalesBasisDetails> list = (List<SalesBasisDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SALESBASISDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SALESBASISDETAILS;

                if (pagination) {
                    sql = sql.concat(SalesBasisDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SalesBasisDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SalesBasisDetails>(list);
                } else {
                    list = (List<SalesBasisDetails>) QueryUtil.list(q,
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
     * Removes all the sales basis detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SalesBasisDetails salesBasisDetails : findAll()) {
            remove(salesBasisDetails);
        }
    }

    /**
     * Returns the number of sales basis detailses.
     *
     * @return the number of sales basis detailses
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

                Query q = session.createQuery(_SQL_COUNT_SALESBASISDETAILS);

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
     * Initializes the sales basis details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.SalesBasisDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SalesBasisDetails>> listenersList = new ArrayList<ModelListener<SalesBasisDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SalesBasisDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SalesBasisDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
