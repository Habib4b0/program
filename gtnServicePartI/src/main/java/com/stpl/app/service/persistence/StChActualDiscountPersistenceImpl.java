package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStChActualDiscountException;
import com.stpl.app.model.StChActualDiscount;
import com.stpl.app.model.impl.StChActualDiscountImpl;
import com.stpl.app.model.impl.StChActualDiscountModelImpl;
import com.stpl.app.service.persistence.StChActualDiscountPersistence;

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
 * The persistence implementation for the st ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChActualDiscountPersistence
 * @see StChActualDiscountUtil
 * @generated
 */
public class StChActualDiscountPersistenceImpl extends BasePersistenceImpl<StChActualDiscount>
    implements StChActualDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StChActualDiscountUtil} to access the st ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StChActualDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            StChActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            StChActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STCHACTUALDISCOUNT = "SELECT stChActualDiscount FROM StChActualDiscount stChActualDiscount";
    private static final String _SQL_COUNT_STCHACTUALDISCOUNT = "SELECT COUNT(stChActualDiscount) FROM StChActualDiscount stChActualDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stChActualDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChActualDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StChActualDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "actualRate", "periodSid",
                "projectionDetailsSid", "userId", "sessionId", "rsModelSid",
                "actualSales"
            });
    private static StChActualDiscount _nullStChActualDiscount = new StChActualDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StChActualDiscount> toCacheModel() {
                return _nullStChActualDiscountCacheModel;
            }
        };

    private static CacheModel<StChActualDiscount> _nullStChActualDiscountCacheModel =
        new CacheModel<StChActualDiscount>() {
            @Override
            public StChActualDiscount toEntityModel() {
                return _nullStChActualDiscount;
            }
        };

    public StChActualDiscountPersistenceImpl() {
        setModelClass(StChActualDiscount.class);
    }

    /**
     * Caches the st ch actual discount in the entity cache if it is enabled.
     *
     * @param stChActualDiscount the st ch actual discount
     */
    @Override
    public void cacheResult(StChActualDiscount stChActualDiscount) {
        EntityCacheUtil.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey(),
            stChActualDiscount);

        stChActualDiscount.resetOriginalValues();
    }

    /**
     * Caches the st ch actual discounts in the entity cache if it is enabled.
     *
     * @param stChActualDiscounts the st ch actual discounts
     */
    @Override
    public void cacheResult(List<StChActualDiscount> stChActualDiscounts) {
        for (StChActualDiscount stChActualDiscount : stChActualDiscounts) {
            if (EntityCacheUtil.getResult(
                        StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StChActualDiscountImpl.class,
                        stChActualDiscount.getPrimaryKey()) == null) {
                cacheResult(stChActualDiscount);
            } else {
                stChActualDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st ch actual discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StChActualDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StChActualDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st ch actual discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StChActualDiscount stChActualDiscount) {
        EntityCacheUtil.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StChActualDiscount> stChActualDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StChActualDiscount stChActualDiscount : stChActualDiscounts) {
            EntityCacheUtil.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
     *
     * @param stChActualDiscountPK the primary key for the new st ch actual discount
     * @return the new st ch actual discount
     */
    @Override
    public StChActualDiscount create(StChActualDiscountPK stChActualDiscountPK) {
        StChActualDiscount stChActualDiscount = new StChActualDiscountImpl();

        stChActualDiscount.setNew(true);
        stChActualDiscount.setPrimaryKey(stChActualDiscountPK);

        return stChActualDiscount;
    }

    /**
     * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stChActualDiscountPK the primary key of the st ch actual discount
     * @return the st ch actual discount that was removed
     * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount remove(StChActualDiscountPK stChActualDiscountPK)
        throws NoSuchStChActualDiscountException, SystemException {
        return remove((Serializable) stChActualDiscountPK);
    }

    /**
     * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st ch actual discount
     * @return the st ch actual discount that was removed
     * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount remove(Serializable primaryKey)
        throws NoSuchStChActualDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StChActualDiscount stChActualDiscount = (StChActualDiscount) session.get(StChActualDiscountImpl.class,
                    primaryKey);

            if (stChActualDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stChActualDiscount);
        } catch (NoSuchStChActualDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StChActualDiscount removeImpl(
        StChActualDiscount stChActualDiscount) throws SystemException {
        stChActualDiscount = toUnwrappedModel(stChActualDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stChActualDiscount)) {
                stChActualDiscount = (StChActualDiscount) session.get(StChActualDiscountImpl.class,
                        stChActualDiscount.getPrimaryKeyObj());
            }

            if (stChActualDiscount != null) {
                session.delete(stChActualDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stChActualDiscount != null) {
            clearCache(stChActualDiscount);
        }

        return stChActualDiscount;
    }

    @Override
    public StChActualDiscount updateImpl(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws SystemException {
        stChActualDiscount = toUnwrappedModel(stChActualDiscount);

        boolean isNew = stChActualDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stChActualDiscount.isNew()) {
                session.save(stChActualDiscount);

                stChActualDiscount.setNew(false);
            } else {
                session.merge(stChActualDiscount);
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

        EntityCacheUtil.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey(),
            stChActualDiscount);

        return stChActualDiscount;
    }

    protected StChActualDiscount toUnwrappedModel(
        StChActualDiscount stChActualDiscount) {
        if (stChActualDiscount instanceof StChActualDiscountImpl) {
            return stChActualDiscount;
        }

        StChActualDiscountImpl stChActualDiscountImpl = new StChActualDiscountImpl();

        stChActualDiscountImpl.setNew(stChActualDiscount.isNew());
        stChActualDiscountImpl.setPrimaryKey(stChActualDiscount.getPrimaryKey());

        stChActualDiscountImpl.setLastModifiedDate(stChActualDiscount.getLastModifiedDate());
        stChActualDiscountImpl.setActualRate(stChActualDiscount.getActualRate());
        stChActualDiscountImpl.setPeriodSid(stChActualDiscount.getPeriodSid());
        stChActualDiscountImpl.setProjectionDetailsSid(stChActualDiscount.getProjectionDetailsSid());
        stChActualDiscountImpl.setUserId(stChActualDiscount.getUserId());
        stChActualDiscountImpl.setSessionId(stChActualDiscount.getSessionId());
        stChActualDiscountImpl.setRsModelSid(stChActualDiscount.getRsModelSid());
        stChActualDiscountImpl.setActualSales(stChActualDiscount.getActualSales());

        return stChActualDiscountImpl;
    }

    /**
     * Returns the st ch actual discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st ch actual discount
     * @return the st ch actual discount
     * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStChActualDiscountException, SystemException {
        StChActualDiscount stChActualDiscount = fetchByPrimaryKey(primaryKey);

        if (stChActualDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stChActualDiscount;
    }

    /**
     * Returns the st ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchStChActualDiscountException} if it could not be found.
     *
     * @param stChActualDiscountPK the primary key of the st ch actual discount
     * @return the st ch actual discount
     * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount findByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK)
        throws NoSuchStChActualDiscountException, SystemException {
        return findByPrimaryKey((Serializable) stChActualDiscountPK);
    }

    /**
     * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st ch actual discount
     * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StChActualDiscount stChActualDiscount = (StChActualDiscount) EntityCacheUtil.getResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StChActualDiscountImpl.class, primaryKey);

        if (stChActualDiscount == _nullStChActualDiscount) {
            return null;
        }

        if (stChActualDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                stChActualDiscount = (StChActualDiscount) session.get(StChActualDiscountImpl.class,
                        primaryKey);

                if (stChActualDiscount != null) {
                    cacheResult(stChActualDiscount);
                } else {
                    EntityCacheUtil.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StChActualDiscountImpl.class, primaryKey,
                        _nullStChActualDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    StChActualDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stChActualDiscount;
    }

    /**
     * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stChActualDiscountPK the primary key of the st ch actual discount
     * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChActualDiscount fetchByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stChActualDiscountPK);
    }

    /**
     * Returns all the st ch actual discounts.
     *
     * @return the st ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChActualDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st ch actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch actual discounts
     * @param end the upper bound of the range of st ch actual discounts (not inclusive)
     * @return the range of st ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChActualDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st ch actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch actual discounts
     * @param end the upper bound of the range of st ch actual discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChActualDiscount> findAll(int start, int end,
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

        List<StChActualDiscount> list = (List<StChActualDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCHACTUALDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCHACTUALDISCOUNT;

                if (pagination) {
                    sql = sql.concat(StChActualDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StChActualDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StChActualDiscount>(list);
                } else {
                    list = (List<StChActualDiscount>) QueryUtil.list(q,
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
     * Removes all the st ch actual discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StChActualDiscount stChActualDiscount : findAll()) {
            remove(stChActualDiscount);
        }
    }

    /**
     * Returns the number of st ch actual discounts.
     *
     * @return the number of st ch actual discounts
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

                Query q = session.createQuery(_SQL_COUNT_STCHACTUALDISCOUNT);

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
     * Initializes the st ch actual discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StChActualDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StChActualDiscount>> listenersList = new ArrayList<ModelListener<StChActualDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StChActualDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StChActualDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
