package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStChProjectionDiscountException;
import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.model.impl.StChProjectionDiscountImpl;
import com.stpl.app.model.impl.StChProjectionDiscountModelImpl;
import com.stpl.app.service.persistence.StChProjectionDiscountPersistence;

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
 * The persistence implementation for the st ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChProjectionDiscountPersistence
 * @see StChProjectionDiscountUtil
 * @generated
 */
public class StChProjectionDiscountPersistenceImpl extends BasePersistenceImpl<StChProjectionDiscount>
    implements StChProjectionDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StChProjectionDiscountUtil} to access the st ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StChProjectionDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
            StChProjectionDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
            StChProjectionDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STCHPROJECTIONDISCOUNT = "SELECT stChProjectionDiscount FROM StChProjectionDiscount stChProjectionDiscount";
    private static final String _SQL_COUNT_STCHPROJECTIONDISCOUNT = "SELECT COUNT(stChProjectionDiscount) FROM StChProjectionDiscount stChProjectionDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stChProjectionDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChProjectionDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StChProjectionDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "adjustmentMethodology", "productGrowth",
                "projectionRate", "projectionDetailsSid", "userId",
                "accountGrowth", "discountAmount", "discountRate", "periodSid",
                "adjustmentBasis", "sessionId", "adjustmentValue",
                "adjustmentType", "rsModelSid", "projectionSales"
            });
    private static StChProjectionDiscount _nullStChProjectionDiscount = new StChProjectionDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StChProjectionDiscount> toCacheModel() {
                return _nullStChProjectionDiscountCacheModel;
            }
        };

    private static CacheModel<StChProjectionDiscount> _nullStChProjectionDiscountCacheModel =
        new CacheModel<StChProjectionDiscount>() {
            @Override
            public StChProjectionDiscount toEntityModel() {
                return _nullStChProjectionDiscount;
            }
        };

    public StChProjectionDiscountPersistenceImpl() {
        setModelClass(StChProjectionDiscount.class);
    }

    /**
     * Caches the st ch projection discount in the entity cache if it is enabled.
     *
     * @param stChProjectionDiscount the st ch projection discount
     */
    @Override
    public void cacheResult(StChProjectionDiscount stChProjectionDiscount) {
        EntityCacheUtil.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountImpl.class,
            stChProjectionDiscount.getPrimaryKey(), stChProjectionDiscount);

        stChProjectionDiscount.resetOriginalValues();
    }

    /**
     * Caches the st ch projection discounts in the entity cache if it is enabled.
     *
     * @param stChProjectionDiscounts the st ch projection discounts
     */
    @Override
    public void cacheResult(
        List<StChProjectionDiscount> stChProjectionDiscounts) {
        for (StChProjectionDiscount stChProjectionDiscount : stChProjectionDiscounts) {
            if (EntityCacheUtil.getResult(
                        StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StChProjectionDiscountImpl.class,
                        stChProjectionDiscount.getPrimaryKey()) == null) {
                cacheResult(stChProjectionDiscount);
            } else {
                stChProjectionDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st ch projection discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StChProjectionDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StChProjectionDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st ch projection discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StChProjectionDiscount stChProjectionDiscount) {
        EntityCacheUtil.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountImpl.class,
            stChProjectionDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StChProjectionDiscount> stChProjectionDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StChProjectionDiscount stChProjectionDiscount : stChProjectionDiscounts) {
            EntityCacheUtil.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StChProjectionDiscountImpl.class,
                stChProjectionDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
     *
     * @param stChProjectionDiscountPK the primary key for the new st ch projection discount
     * @return the new st ch projection discount
     */
    @Override
    public StChProjectionDiscount create(
        StChProjectionDiscountPK stChProjectionDiscountPK) {
        StChProjectionDiscount stChProjectionDiscount = new StChProjectionDiscountImpl();

        stChProjectionDiscount.setNew(true);
        stChProjectionDiscount.setPrimaryKey(stChProjectionDiscountPK);

        return stChProjectionDiscount;
    }

    /**
     * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stChProjectionDiscountPK the primary key of the st ch projection discount
     * @return the st ch projection discount that was removed
     * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount remove(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws NoSuchStChProjectionDiscountException, SystemException {
        return remove((Serializable) stChProjectionDiscountPK);
    }

    /**
     * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st ch projection discount
     * @return the st ch projection discount that was removed
     * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount remove(Serializable primaryKey)
        throws NoSuchStChProjectionDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StChProjectionDiscount stChProjectionDiscount = (StChProjectionDiscount) session.get(StChProjectionDiscountImpl.class,
                    primaryKey);

            if (stChProjectionDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stChProjectionDiscount);
        } catch (NoSuchStChProjectionDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StChProjectionDiscount removeImpl(
        StChProjectionDiscount stChProjectionDiscount)
        throws SystemException {
        stChProjectionDiscount = toUnwrappedModel(stChProjectionDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stChProjectionDiscount)) {
                stChProjectionDiscount = (StChProjectionDiscount) session.get(StChProjectionDiscountImpl.class,
                        stChProjectionDiscount.getPrimaryKeyObj());
            }

            if (stChProjectionDiscount != null) {
                session.delete(stChProjectionDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stChProjectionDiscount != null) {
            clearCache(stChProjectionDiscount);
        }

        return stChProjectionDiscount;
    }

    @Override
    public StChProjectionDiscount updateImpl(
        com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount)
        throws SystemException {
        stChProjectionDiscount = toUnwrappedModel(stChProjectionDiscount);

        boolean isNew = stChProjectionDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stChProjectionDiscount.isNew()) {
                session.save(stChProjectionDiscount);

                stChProjectionDiscount.setNew(false);
            } else {
                session.merge(stChProjectionDiscount);
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

        EntityCacheUtil.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            StChProjectionDiscountImpl.class,
            stChProjectionDiscount.getPrimaryKey(), stChProjectionDiscount);

        return stChProjectionDiscount;
    }

    protected StChProjectionDiscount toUnwrappedModel(
        StChProjectionDiscount stChProjectionDiscount) {
        if (stChProjectionDiscount instanceof StChProjectionDiscountImpl) {
            return stChProjectionDiscount;
        }

        StChProjectionDiscountImpl stChProjectionDiscountImpl = new StChProjectionDiscountImpl();

        stChProjectionDiscountImpl.setNew(stChProjectionDiscount.isNew());
        stChProjectionDiscountImpl.setPrimaryKey(stChProjectionDiscount.getPrimaryKey());

        stChProjectionDiscountImpl.setLastModifiedDate(stChProjectionDiscount.getLastModifiedDate());
        stChProjectionDiscountImpl.setAdjustmentMethodology(stChProjectionDiscount.getAdjustmentMethodology());
        stChProjectionDiscountImpl.setProductGrowth(stChProjectionDiscount.getProductGrowth());
        stChProjectionDiscountImpl.setProjectionRate(stChProjectionDiscount.getProjectionRate());
        stChProjectionDiscountImpl.setProjectionDetailsSid(stChProjectionDiscount.getProjectionDetailsSid());
        stChProjectionDiscountImpl.setUserId(stChProjectionDiscount.getUserId());
        stChProjectionDiscountImpl.setAccountGrowth(stChProjectionDiscount.getAccountGrowth());
        stChProjectionDiscountImpl.setDiscountAmount(stChProjectionDiscount.getDiscountAmount());
        stChProjectionDiscountImpl.setDiscountRate(stChProjectionDiscount.getDiscountRate());
        stChProjectionDiscountImpl.setPeriodSid(stChProjectionDiscount.getPeriodSid());
        stChProjectionDiscountImpl.setAdjustmentBasis(stChProjectionDiscount.getAdjustmentBasis());
        stChProjectionDiscountImpl.setSessionId(stChProjectionDiscount.getSessionId());
        stChProjectionDiscountImpl.setAdjustmentValue(stChProjectionDiscount.getAdjustmentValue());
        stChProjectionDiscountImpl.setAdjustmentType(stChProjectionDiscount.getAdjustmentType());
        stChProjectionDiscountImpl.setRsModelSid(stChProjectionDiscount.getRsModelSid());
        stChProjectionDiscountImpl.setProjectionSales(stChProjectionDiscount.getProjectionSales());

        return stChProjectionDiscountImpl;
    }

    /**
     * Returns the st ch projection discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st ch projection discount
     * @return the st ch projection discount
     * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStChProjectionDiscountException, SystemException {
        StChProjectionDiscount stChProjectionDiscount = fetchByPrimaryKey(primaryKey);

        if (stChProjectionDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stChProjectionDiscount;
    }

    /**
     * Returns the st ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchStChProjectionDiscountException} if it could not be found.
     *
     * @param stChProjectionDiscountPK the primary key of the st ch projection discount
     * @return the st ch projection discount
     * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount findByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws NoSuchStChProjectionDiscountException, SystemException {
        return findByPrimaryKey((Serializable) stChProjectionDiscountPK);
    }

    /**
     * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st ch projection discount
     * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StChProjectionDiscount stChProjectionDiscount = (StChProjectionDiscount) EntityCacheUtil.getResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                StChProjectionDiscountImpl.class, primaryKey);

        if (stChProjectionDiscount == _nullStChProjectionDiscount) {
            return null;
        }

        if (stChProjectionDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                stChProjectionDiscount = (StChProjectionDiscount) session.get(StChProjectionDiscountImpl.class,
                        primaryKey);

                if (stChProjectionDiscount != null) {
                    cacheResult(stChProjectionDiscount);
                } else {
                    EntityCacheUtil.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        StChProjectionDiscountImpl.class, primaryKey,
                        _nullStChProjectionDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    StChProjectionDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stChProjectionDiscount;
    }

    /**
     * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stChProjectionDiscountPK the primary key of the st ch projection discount
     * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChProjectionDiscount fetchByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stChProjectionDiscountPK);
    }

    /**
     * Returns all the st ch projection discounts.
     *
     * @return the st ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChProjectionDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st ch projection discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch projection discounts
     * @param end the upper bound of the range of st ch projection discounts (not inclusive)
     * @return the range of st ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChProjectionDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st ch projection discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch projection discounts
     * @param end the upper bound of the range of st ch projection discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChProjectionDiscount> findAll(int start, int end,
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

        List<StChProjectionDiscount> list = (List<StChProjectionDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCHPROJECTIONDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCHPROJECTIONDISCOUNT;

                if (pagination) {
                    sql = sql.concat(StChProjectionDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StChProjectionDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StChProjectionDiscount>(list);
                } else {
                    list = (List<StChProjectionDiscount>) QueryUtil.list(q,
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
     * Removes all the st ch projection discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StChProjectionDiscount stChProjectionDiscount : findAll()) {
            remove(stChProjectionDiscount);
        }
    }

    /**
     * Returns the number of st ch projection discounts.
     *
     * @return the number of st ch projection discounts
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

                Query q = session.createQuery(_SQL_COUNT_STCHPROJECTIONDISCOUNT);

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
     * Initializes the st ch projection discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StChProjectionDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StChProjectionDiscount>> listenersList = new ArrayList<ModelListener<StChProjectionDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StChProjectionDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StChProjectionDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
