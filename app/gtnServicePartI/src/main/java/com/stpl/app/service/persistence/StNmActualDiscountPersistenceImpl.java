package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmActualDiscountException;
import com.stpl.app.model.StNmActualDiscount;
import com.stpl.app.model.impl.StNmActualDiscountImpl;
import com.stpl.app.model.impl.StNmActualDiscountModelImpl;
import com.stpl.app.service.persistence.StNmActualDiscountPersistence;

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
 * The persistence implementation for the st nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualDiscountPersistence
 * @see StNmActualDiscountUtil
 * @generated
 */
public class StNmActualDiscountPersistenceImpl extends BasePersistenceImpl<StNmActualDiscount>
    implements StNmActualDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmActualDiscountUtil} to access the st nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmActualDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            StNmActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            StNmActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMACTUALDISCOUNT = "SELECT stNmActualDiscount FROM StNmActualDiscount stNmActualDiscount";
    private static final String _SQL_COUNT_STNMACTUALDISCOUNT = "SELECT COUNT(stNmActualDiscount) FROM StNmActualDiscount stNmActualDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmActualDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmActualDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmActualDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualSales", "periodSid", "actualRate", "userId",
                "lastModifiedDate", "actualProjectionSales",
                "actualProjectionRate", "projectionDetailsSid", "rsModelSid",
                "sessionId"
            });
    private static StNmActualDiscount _nullStNmActualDiscount = new StNmActualDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmActualDiscount> toCacheModel() {
                return _nullStNmActualDiscountCacheModel;
            }
        };

    private static CacheModel<StNmActualDiscount> _nullStNmActualDiscountCacheModel =
        new CacheModel<StNmActualDiscount>() {
            @Override
            public StNmActualDiscount toEntityModel() {
                return _nullStNmActualDiscount;
            }
        };

    public StNmActualDiscountPersistenceImpl() {
        setModelClass(StNmActualDiscount.class);
    }

    /**
     * Caches the st nm actual discount in the entity cache if it is enabled.
     *
     * @param stNmActualDiscount the st nm actual discount
     */
    @Override
    public void cacheResult(StNmActualDiscount stNmActualDiscount) {
        EntityCacheUtil.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey(),
            stNmActualDiscount);

        stNmActualDiscount.resetOriginalValues();
    }

    /**
     * Caches the st nm actual discounts in the entity cache if it is enabled.
     *
     * @param stNmActualDiscounts the st nm actual discounts
     */
    @Override
    public void cacheResult(List<StNmActualDiscount> stNmActualDiscounts) {
        for (StNmActualDiscount stNmActualDiscount : stNmActualDiscounts) {
            if (EntityCacheUtil.getResult(
                        StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StNmActualDiscountImpl.class,
                        stNmActualDiscount.getPrimaryKey()) == null) {
                cacheResult(stNmActualDiscount);
            } else {
                stNmActualDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm actual discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmActualDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmActualDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm actual discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmActualDiscount stNmActualDiscount) {
        EntityCacheUtil.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmActualDiscount> stNmActualDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmActualDiscount stNmActualDiscount : stNmActualDiscounts) {
            EntityCacheUtil.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
     *
     * @param stNmActualDiscountPK the primary key for the new st nm actual discount
     * @return the new st nm actual discount
     */
    @Override
    public StNmActualDiscount create(StNmActualDiscountPK stNmActualDiscountPK) {
        StNmActualDiscount stNmActualDiscount = new StNmActualDiscountImpl();

        stNmActualDiscount.setNew(true);
        stNmActualDiscount.setPrimaryKey(stNmActualDiscountPK);

        return stNmActualDiscount;
    }

    /**
     * Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmActualDiscountPK the primary key of the st nm actual discount
     * @return the st nm actual discount that was removed
     * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount remove(StNmActualDiscountPK stNmActualDiscountPK)
        throws NoSuchStNmActualDiscountException, SystemException {
        return remove((Serializable) stNmActualDiscountPK);
    }

    /**
     * Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm actual discount
     * @return the st nm actual discount that was removed
     * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount remove(Serializable primaryKey)
        throws NoSuchStNmActualDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmActualDiscount stNmActualDiscount = (StNmActualDiscount) session.get(StNmActualDiscountImpl.class,
                    primaryKey);

            if (stNmActualDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmActualDiscount);
        } catch (NoSuchStNmActualDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmActualDiscount removeImpl(
        StNmActualDiscount stNmActualDiscount) throws SystemException {
        stNmActualDiscount = toUnwrappedModel(stNmActualDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmActualDiscount)) {
                stNmActualDiscount = (StNmActualDiscount) session.get(StNmActualDiscountImpl.class,
                        stNmActualDiscount.getPrimaryKeyObj());
            }

            if (stNmActualDiscount != null) {
                session.delete(stNmActualDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmActualDiscount != null) {
            clearCache(stNmActualDiscount);
        }

        return stNmActualDiscount;
    }

    @Override
    public StNmActualDiscount updateImpl(
        com.stpl.app.model.StNmActualDiscount stNmActualDiscount)
        throws SystemException {
        stNmActualDiscount = toUnwrappedModel(stNmActualDiscount);

        boolean isNew = stNmActualDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmActualDiscount.isNew()) {
                session.save(stNmActualDiscount);

                stNmActualDiscount.setNew(false);
            } else {
                session.merge(stNmActualDiscount);
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

        EntityCacheUtil.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey(),
            stNmActualDiscount);

        return stNmActualDiscount;
    }

    protected StNmActualDiscount toUnwrappedModel(
        StNmActualDiscount stNmActualDiscount) {
        if (stNmActualDiscount instanceof StNmActualDiscountImpl) {
            return stNmActualDiscount;
        }

        StNmActualDiscountImpl stNmActualDiscountImpl = new StNmActualDiscountImpl();

        stNmActualDiscountImpl.setNew(stNmActualDiscount.isNew());
        stNmActualDiscountImpl.setPrimaryKey(stNmActualDiscount.getPrimaryKey());

        stNmActualDiscountImpl.setActualSales(stNmActualDiscount.getActualSales());
        stNmActualDiscountImpl.setPeriodSid(stNmActualDiscount.getPeriodSid());
        stNmActualDiscountImpl.setActualRate(stNmActualDiscount.getActualRate());
        stNmActualDiscountImpl.setUserId(stNmActualDiscount.getUserId());
        stNmActualDiscountImpl.setLastModifiedDate(stNmActualDiscount.getLastModifiedDate());
        stNmActualDiscountImpl.setActualProjectionSales(stNmActualDiscount.getActualProjectionSales());
        stNmActualDiscountImpl.setActualProjectionRate(stNmActualDiscount.getActualProjectionRate());
        stNmActualDiscountImpl.setProjectionDetailsSid(stNmActualDiscount.getProjectionDetailsSid());
        stNmActualDiscountImpl.setRsModelSid(stNmActualDiscount.getRsModelSid());
        stNmActualDiscountImpl.setSessionId(stNmActualDiscount.getSessionId());

        return stNmActualDiscountImpl;
    }

    /**
     * Returns the st nm actual discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm actual discount
     * @return the st nm actual discount
     * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmActualDiscountException, SystemException {
        StNmActualDiscount stNmActualDiscount = fetchByPrimaryKey(primaryKey);

        if (stNmActualDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmActualDiscount;
    }

    /**
     * Returns the st nm actual discount with the primary key or throws a {@link com.stpl.app.NoSuchStNmActualDiscountException} if it could not be found.
     *
     * @param stNmActualDiscountPK the primary key of the st nm actual discount
     * @return the st nm actual discount
     * @throws com.stpl.app.NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount findByPrimaryKey(
        StNmActualDiscountPK stNmActualDiscountPK)
        throws NoSuchStNmActualDiscountException, SystemException {
        return findByPrimaryKey((Serializable) stNmActualDiscountPK);
    }

    /**
     * Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm actual discount
     * @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmActualDiscount stNmActualDiscount = (StNmActualDiscount) EntityCacheUtil.getResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StNmActualDiscountImpl.class, primaryKey);

        if (stNmActualDiscount == _nullStNmActualDiscount) {
            return null;
        }

        if (stNmActualDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                stNmActualDiscount = (StNmActualDiscount) session.get(StNmActualDiscountImpl.class,
                        primaryKey);

                if (stNmActualDiscount != null) {
                    cacheResult(stNmActualDiscount);
                } else {
                    EntityCacheUtil.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StNmActualDiscountImpl.class, primaryKey,
                        _nullStNmActualDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    StNmActualDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmActualDiscount;
    }

    /**
     * Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmActualDiscountPK the primary key of the st nm actual discount
     * @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualDiscount fetchByPrimaryKey(
        StNmActualDiscountPK stNmActualDiscountPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmActualDiscountPK);
    }

    /**
     * Returns all the st nm actual discounts.
     *
     * @return the st nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm actual discounts
     * @param end the upper bound of the range of st nm actual discounts (not inclusive)
     * @return the range of st nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm actual discounts
     * @param end the upper bound of the range of st nm actual discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualDiscount> findAll(int start, int end,
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

        List<StNmActualDiscount> list = (List<StNmActualDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMACTUALDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMACTUALDISCOUNT;

                if (pagination) {
                    sql = sql.concat(StNmActualDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmActualDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmActualDiscount>(list);
                } else {
                    list = (List<StNmActualDiscount>) QueryUtil.list(q,
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
     * Removes all the st nm actual discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmActualDiscount stNmActualDiscount : findAll()) {
            remove(stNmActualDiscount);
        }
    }

    /**
     * Returns the number of st nm actual discounts.
     *
     * @return the number of st nm actual discounts
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

                Query q = session.createQuery(_SQL_COUNT_STNMACTUALDISCOUNT);

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
     * Initializes the st nm actual discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmActualDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmActualDiscount>> listenersList = new ArrayList<ModelListener<StNmActualDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmActualDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmActualDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
