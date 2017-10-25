package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChActualSalesException;
import com.stpl.app.model.ChActualSales;
import com.stpl.app.model.impl.ChActualSalesImpl;
import com.stpl.app.model.impl.ChActualSalesModelImpl;
import com.stpl.app.service.persistence.ChActualSalesPersistence;

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
 * The persistence implementation for the ch actual sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualSalesPersistence
 * @see ChActualSalesUtil
 * @generated
 */
public class ChActualSalesPersistenceImpl extends BasePersistenceImpl<ChActualSales>
    implements ChActualSalesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChActualSalesUtil} to access the ch actual sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChActualSalesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesModelImpl.FINDER_CACHE_ENABLED,
            ChActualSalesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesModelImpl.FINDER_CACHE_ENABLED,
            ChActualSalesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHACTUALSALES = "SELECT chActualSales FROM ChActualSales chActualSales";
    private static final String _SQL_COUNT_CHACTUALSALES = "SELECT COUNT(chActualSales) FROM ChActualSales chActualSales";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chActualSales.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChActualSales exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChActualSalesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "contractUnits", "perOfBusiness", "periodSid", "contractSales",
                "projectionDetailsSid", "gtsSales"
            });
    private static ChActualSales _nullChActualSales = new ChActualSalesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChActualSales> toCacheModel() {
                return _nullChActualSalesCacheModel;
            }
        };

    private static CacheModel<ChActualSales> _nullChActualSalesCacheModel = new CacheModel<ChActualSales>() {
            @Override
            public ChActualSales toEntityModel() {
                return _nullChActualSales;
            }
        };

    public ChActualSalesPersistenceImpl() {
        setModelClass(ChActualSales.class);
    }

    /**
     * Caches the ch actual sales in the entity cache if it is enabled.
     *
     * @param chActualSales the ch actual sales
     */
    @Override
    public void cacheResult(ChActualSales chActualSales) {
        EntityCacheUtil.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesImpl.class, chActualSales.getPrimaryKey(),
            chActualSales);

        chActualSales.resetOriginalValues();
    }

    /**
     * Caches the ch actual saleses in the entity cache if it is enabled.
     *
     * @param chActualSaleses the ch actual saleses
     */
    @Override
    public void cacheResult(List<ChActualSales> chActualSaleses) {
        for (ChActualSales chActualSales : chActualSaleses) {
            if (EntityCacheUtil.getResult(
                        ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
                        ChActualSalesImpl.class, chActualSales.getPrimaryKey()) == null) {
                cacheResult(chActualSales);
            } else {
                chActualSales.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch actual saleses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChActualSalesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChActualSalesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch actual sales.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChActualSales chActualSales) {
        EntityCacheUtil.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesImpl.class, chActualSales.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChActualSales> chActualSaleses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChActualSales chActualSales : chActualSaleses) {
            EntityCacheUtil.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
                ChActualSalesImpl.class, chActualSales.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
     *
     * @param chActualSalesPK the primary key for the new ch actual sales
     * @return the new ch actual sales
     */
    @Override
    public ChActualSales create(ChActualSalesPK chActualSalesPK) {
        ChActualSales chActualSales = new ChActualSalesImpl();

        chActualSales.setNew(true);
        chActualSales.setPrimaryKey(chActualSalesPK);

        return chActualSales;
    }

    /**
     * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chActualSalesPK the primary key of the ch actual sales
     * @return the ch actual sales that was removed
     * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales remove(ChActualSalesPK chActualSalesPK)
        throws NoSuchChActualSalesException, SystemException {
        return remove((Serializable) chActualSalesPK);
    }

    /**
     * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch actual sales
     * @return the ch actual sales that was removed
     * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales remove(Serializable primaryKey)
        throws NoSuchChActualSalesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChActualSales chActualSales = (ChActualSales) session.get(ChActualSalesImpl.class,
                    primaryKey);

            if (chActualSales == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChActualSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chActualSales);
        } catch (NoSuchChActualSalesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChActualSales removeImpl(ChActualSales chActualSales)
        throws SystemException {
        chActualSales = toUnwrappedModel(chActualSales);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chActualSales)) {
                chActualSales = (ChActualSales) session.get(ChActualSalesImpl.class,
                        chActualSales.getPrimaryKeyObj());
            }

            if (chActualSales != null) {
                session.delete(chActualSales);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chActualSales != null) {
            clearCache(chActualSales);
        }

        return chActualSales;
    }

    @Override
    public ChActualSales updateImpl(
        com.stpl.app.model.ChActualSales chActualSales)
        throws SystemException {
        chActualSales = toUnwrappedModel(chActualSales);

        boolean isNew = chActualSales.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chActualSales.isNew()) {
                session.save(chActualSales);

                chActualSales.setNew(false);
            } else {
                session.merge(chActualSales);
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

        EntityCacheUtil.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
            ChActualSalesImpl.class, chActualSales.getPrimaryKey(),
            chActualSales);

        return chActualSales;
    }

    protected ChActualSales toUnwrappedModel(ChActualSales chActualSales) {
        if (chActualSales instanceof ChActualSalesImpl) {
            return chActualSales;
        }

        ChActualSalesImpl chActualSalesImpl = new ChActualSalesImpl();

        chActualSalesImpl.setNew(chActualSales.isNew());
        chActualSalesImpl.setPrimaryKey(chActualSales.getPrimaryKey());

        chActualSalesImpl.setContractUnits(chActualSales.getContractUnits());
        chActualSalesImpl.setPerOfBusiness(chActualSales.getPerOfBusiness());
        chActualSalesImpl.setPeriodSid(chActualSales.getPeriodSid());
        chActualSalesImpl.setContractSales(chActualSales.getContractSales());
        chActualSalesImpl.setProjectionDetailsSid(chActualSales.getProjectionDetailsSid());
        chActualSalesImpl.setGtsSales(chActualSales.getGtsSales());

        return chActualSalesImpl;
    }

    /**
     * Returns the ch actual sales with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch actual sales
     * @return the ch actual sales
     * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChActualSalesException, SystemException {
        ChActualSales chActualSales = fetchByPrimaryKey(primaryKey);

        if (chActualSales == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChActualSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chActualSales;
    }

    /**
     * Returns the ch actual sales with the primary key or throws a {@link com.stpl.app.NoSuchChActualSalesException} if it could not be found.
     *
     * @param chActualSalesPK the primary key of the ch actual sales
     * @return the ch actual sales
     * @throws com.stpl.app.NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales findByPrimaryKey(ChActualSalesPK chActualSalesPK)
        throws NoSuchChActualSalesException, SystemException {
        return findByPrimaryKey((Serializable) chActualSalesPK);
    }

    /**
     * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch actual sales
     * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChActualSales chActualSales = (ChActualSales) EntityCacheUtil.getResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
                ChActualSalesImpl.class, primaryKey);

        if (chActualSales == _nullChActualSales) {
            return null;
        }

        if (chActualSales == null) {
            Session session = null;

            try {
                session = openSession();

                chActualSales = (ChActualSales) session.get(ChActualSalesImpl.class,
                        primaryKey);

                if (chActualSales != null) {
                    cacheResult(chActualSales);
                } else {
                    EntityCacheUtil.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
                        ChActualSalesImpl.class, primaryKey, _nullChActualSales);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
                    ChActualSalesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chActualSales;
    }

    /**
     * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chActualSalesPK the primary key of the ch actual sales
     * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualSales fetchByPrimaryKey(ChActualSalesPK chActualSalesPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) chActualSalesPK);
    }

    /**
     * Returns all the ch actual saleses.
     *
     * @return the ch actual saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualSales> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch actual saleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch actual saleses
     * @param end the upper bound of the range of ch actual saleses (not inclusive)
     * @return the range of ch actual saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualSales> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch actual saleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch actual saleses
     * @param end the upper bound of the range of ch actual saleses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch actual saleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualSales> findAll(int start, int end,
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

        List<ChActualSales> list = (List<ChActualSales>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHACTUALSALES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHACTUALSALES;

                if (pagination) {
                    sql = sql.concat(ChActualSalesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChActualSales>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChActualSales>(list);
                } else {
                    list = (List<ChActualSales>) QueryUtil.list(q,
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
     * Removes all the ch actual saleses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChActualSales chActualSales : findAll()) {
            remove(chActualSales);
        }
    }

    /**
     * Returns the number of ch actual saleses.
     *
     * @return the number of ch actual saleses
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

                Query q = session.createQuery(_SQL_COUNT_CHACTUALSALES);

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
     * Initializes the ch actual sales persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChActualSales")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChActualSales>> listenersList = new ArrayList<ModelListener<ChActualSales>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChActualSales>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChActualSalesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
