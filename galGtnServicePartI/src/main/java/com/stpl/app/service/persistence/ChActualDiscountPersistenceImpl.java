package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChActualDiscountException;
import com.stpl.app.model.ChActualDiscount;
import com.stpl.app.model.impl.ChActualDiscountImpl;
import com.stpl.app.model.impl.ChActualDiscountModelImpl;
import com.stpl.app.service.persistence.ChActualDiscountPersistence;

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
 * The persistence implementation for the ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualDiscountPersistence
 * @see ChActualDiscountUtil
 * @generated
 */
public class ChActualDiscountPersistenceImpl extends BasePersistenceImpl<ChActualDiscount>
    implements ChActualDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChActualDiscountUtil} to access the ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChActualDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            ChActualDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            ChActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHACTUALDISCOUNT = "SELECT chActualDiscount FROM ChActualDiscount chActualDiscount";
    private static final String _SQL_COUNT_CHACTUALDISCOUNT = "SELECT COUNT(chActualDiscount) FROM ChActualDiscount chActualDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chActualDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChActualDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChActualDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualRate", "periodSid", "projectionDetailsSid", "rsModelSid",
                "actualSales"
            });
    private static ChActualDiscount _nullChActualDiscount = new ChActualDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChActualDiscount> toCacheModel() {
                return _nullChActualDiscountCacheModel;
            }
        };

    private static CacheModel<ChActualDiscount> _nullChActualDiscountCacheModel = new CacheModel<ChActualDiscount>() {
            @Override
            public ChActualDiscount toEntityModel() {
                return _nullChActualDiscount;
            }
        };

    public ChActualDiscountPersistenceImpl() {
        setModelClass(ChActualDiscount.class);
    }

    /**
     * Caches the ch actual discount in the entity cache if it is enabled.
     *
     * @param chActualDiscount the ch actual discount
     */
    @Override
    public void cacheResult(ChActualDiscount chActualDiscount) {
        EntityCacheUtil.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey(),
            chActualDiscount);

        chActualDiscount.resetOriginalValues();
    }

    /**
     * Caches the ch actual discounts in the entity cache if it is enabled.
     *
     * @param chActualDiscounts the ch actual discounts
     */
    @Override
    public void cacheResult(List<ChActualDiscount> chActualDiscounts) {
        for (ChActualDiscount chActualDiscount : chActualDiscounts) {
            if (EntityCacheUtil.getResult(
                        ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        ChActualDiscountImpl.class,
                        chActualDiscount.getPrimaryKey()) == null) {
                cacheResult(chActualDiscount);
            } else {
                chActualDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch actual discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChActualDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChActualDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch actual discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChActualDiscount chActualDiscount) {
        EntityCacheUtil.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChActualDiscount> chActualDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChActualDiscount chActualDiscount : chActualDiscounts) {
            EntityCacheUtil.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
     *
     * @param chActualDiscountPK the primary key for the new ch actual discount
     * @return the new ch actual discount
     */
    @Override
    public ChActualDiscount create(ChActualDiscountPK chActualDiscountPK) {
        ChActualDiscount chActualDiscount = new ChActualDiscountImpl();

        chActualDiscount.setNew(true);
        chActualDiscount.setPrimaryKey(chActualDiscountPK);

        return chActualDiscount;
    }

    /**
     * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chActualDiscountPK the primary key of the ch actual discount
     * @return the ch actual discount that was removed
     * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount remove(ChActualDiscountPK chActualDiscountPK)
        throws NoSuchChActualDiscountException, SystemException {
        return remove((Serializable) chActualDiscountPK);
    }

    /**
     * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch actual discount
     * @return the ch actual discount that was removed
     * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount remove(Serializable primaryKey)
        throws NoSuchChActualDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChActualDiscount chActualDiscount = (ChActualDiscount) session.get(ChActualDiscountImpl.class,
                    primaryKey);

            if (chActualDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chActualDiscount);
        } catch (NoSuchChActualDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChActualDiscount removeImpl(ChActualDiscount chActualDiscount)
        throws SystemException {
        chActualDiscount = toUnwrappedModel(chActualDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chActualDiscount)) {
                chActualDiscount = (ChActualDiscount) session.get(ChActualDiscountImpl.class,
                        chActualDiscount.getPrimaryKeyObj());
            }

            if (chActualDiscount != null) {
                session.delete(chActualDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chActualDiscount != null) {
            clearCache(chActualDiscount);
        }

        return chActualDiscount;
    }

    @Override
    public ChActualDiscount updateImpl(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws SystemException {
        chActualDiscount = toUnwrappedModel(chActualDiscount);

        boolean isNew = chActualDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chActualDiscount.isNew()) {
                session.save(chActualDiscount);

                chActualDiscount.setNew(false);
            } else {
                session.merge(chActualDiscount);
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

        EntityCacheUtil.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey(),
            chActualDiscount);

        return chActualDiscount;
    }

    protected ChActualDiscount toUnwrappedModel(
        ChActualDiscount chActualDiscount) {
        if (chActualDiscount instanceof ChActualDiscountImpl) {
            return chActualDiscount;
        }

        ChActualDiscountImpl chActualDiscountImpl = new ChActualDiscountImpl();

        chActualDiscountImpl.setNew(chActualDiscount.isNew());
        chActualDiscountImpl.setPrimaryKey(chActualDiscount.getPrimaryKey());

        chActualDiscountImpl.setActualRate(chActualDiscount.getActualRate());
        chActualDiscountImpl.setPeriodSid(chActualDiscount.getPeriodSid());
        chActualDiscountImpl.setProjectionDetailsSid(chActualDiscount.getProjectionDetailsSid());
        chActualDiscountImpl.setRsModelSid(chActualDiscount.getRsModelSid());
        chActualDiscountImpl.setActualSales(chActualDiscount.getActualSales());

        return chActualDiscountImpl;
    }

    /**
     * Returns the ch actual discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch actual discount
     * @return the ch actual discount
     * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChActualDiscountException, SystemException {
        ChActualDiscount chActualDiscount = fetchByPrimaryKey(primaryKey);

        if (chActualDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chActualDiscount;
    }

    /**
     * Returns the ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchChActualDiscountException} if it could not be found.
     *
     * @param chActualDiscountPK the primary key of the ch actual discount
     * @return the ch actual discount
     * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount findByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK)
        throws NoSuchChActualDiscountException, SystemException {
        return findByPrimaryKey((Serializable) chActualDiscountPK);
    }

    /**
     * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch actual discount
     * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChActualDiscount chActualDiscount = (ChActualDiscount) EntityCacheUtil.getResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                ChActualDiscountImpl.class, primaryKey);

        if (chActualDiscount == _nullChActualDiscount) {
            return null;
        }

        if (chActualDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                chActualDiscount = (ChActualDiscount) session.get(ChActualDiscountImpl.class,
                        primaryKey);

                if (chActualDiscount != null) {
                    cacheResult(chActualDiscount);
                } else {
                    EntityCacheUtil.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        ChActualDiscountImpl.class, primaryKey,
                        _nullChActualDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    ChActualDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chActualDiscount;
    }

    /**
     * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chActualDiscountPK the primary key of the ch actual discount
     * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChActualDiscount fetchByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) chActualDiscountPK);
    }

    /**
     * Returns all the ch actual discounts.
     *
     * @return the ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch actual discounts
     * @param end the upper bound of the range of ch actual discounts (not inclusive)
     * @return the range of ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch actual discounts
     * @param end the upper bound of the range of ch actual discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChActualDiscount> findAll(int start, int end,
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

        List<ChActualDiscount> list = (List<ChActualDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHACTUALDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHACTUALDISCOUNT;

                if (pagination) {
                    sql = sql.concat(ChActualDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChActualDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChActualDiscount>(list);
                } else {
                    list = (List<ChActualDiscount>) QueryUtil.list(q,
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
     * Removes all the ch actual discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChActualDiscount chActualDiscount : findAll()) {
            remove(chActualDiscount);
        }
    }

    /**
     * Returns the number of ch actual discounts.
     *
     * @return the number of ch actual discounts
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

                Query q = session.createQuery(_SQL_COUNT_CHACTUALDISCOUNT);

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
     * Initializes the ch actual discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChActualDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChActualDiscount>> listenersList = new ArrayList<ModelListener<ChActualDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChActualDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChActualDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
