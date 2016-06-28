package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChProjectionDiscountException;
import com.stpl.app.model.ChProjectionDiscount;
import com.stpl.app.model.impl.ChProjectionDiscountImpl;
import com.stpl.app.model.impl.ChProjectionDiscountModelImpl;
import com.stpl.app.service.persistence.ChProjectionDiscountPersistence;

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
 * The persistence implementation for the ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionDiscountPersistence
 * @see ChProjectionDiscountUtil
 * @generated
 */
public class ChProjectionDiscountPersistenceImpl extends BasePersistenceImpl<ChProjectionDiscount>
    implements ChProjectionDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChProjectionDiscountUtil} to access the ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChProjectionDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
            ChProjectionDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
            ChProjectionDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHPROJECTIONDISCOUNT = "SELECT chProjectionDiscount FROM ChProjectionDiscount chProjectionDiscount";
    private static final String _SQL_COUNT_CHPROJECTIONDISCOUNT = "SELECT COUNT(chProjectionDiscount) FROM ChProjectionDiscount chProjectionDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chProjectionDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChProjectionDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChProjectionDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentMethodology", "productGrowth", "projectionRate",
                "projectionDetailsSid", "accountGrowth", "discountAmount",
                "discountRate", "periodSid", "adjustmentBasis",
                "adjustmentValue", "adjustmentType", "rsModelSid",
                "projectionSales"
            });
    private static ChProjectionDiscount _nullChProjectionDiscount = new ChProjectionDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChProjectionDiscount> toCacheModel() {
                return _nullChProjectionDiscountCacheModel;
            }
        };

    private static CacheModel<ChProjectionDiscount> _nullChProjectionDiscountCacheModel =
        new CacheModel<ChProjectionDiscount>() {
            @Override
            public ChProjectionDiscount toEntityModel() {
                return _nullChProjectionDiscount;
            }
        };

    public ChProjectionDiscountPersistenceImpl() {
        setModelClass(ChProjectionDiscount.class);
    }

    /**
     * Caches the ch projection discount in the entity cache if it is enabled.
     *
     * @param chProjectionDiscount the ch projection discount
     */
    @Override
    public void cacheResult(ChProjectionDiscount chProjectionDiscount) {
        EntityCacheUtil.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountImpl.class,
            chProjectionDiscount.getPrimaryKey(), chProjectionDiscount);

        chProjectionDiscount.resetOriginalValues();
    }

    /**
     * Caches the ch projection discounts in the entity cache if it is enabled.
     *
     * @param chProjectionDiscounts the ch projection discounts
     */
    @Override
    public void cacheResult(List<ChProjectionDiscount> chProjectionDiscounts) {
        for (ChProjectionDiscount chProjectionDiscount : chProjectionDiscounts) {
            if (EntityCacheUtil.getResult(
                        ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        ChProjectionDiscountImpl.class,
                        chProjectionDiscount.getPrimaryKey()) == null) {
                cacheResult(chProjectionDiscount);
            } else {
                chProjectionDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch projection discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChProjectionDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChProjectionDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch projection discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChProjectionDiscount chProjectionDiscount) {
        EntityCacheUtil.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountImpl.class, chProjectionDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChProjectionDiscount> chProjectionDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChProjectionDiscount chProjectionDiscount : chProjectionDiscounts) {
            EntityCacheUtil.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                ChProjectionDiscountImpl.class,
                chProjectionDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
     *
     * @param chProjectionDiscountPK the primary key for the new ch projection discount
     * @return the new ch projection discount
     */
    @Override
    public ChProjectionDiscount create(
        ChProjectionDiscountPK chProjectionDiscountPK) {
        ChProjectionDiscount chProjectionDiscount = new ChProjectionDiscountImpl();

        chProjectionDiscount.setNew(true);
        chProjectionDiscount.setPrimaryKey(chProjectionDiscountPK);

        return chProjectionDiscount;
    }

    /**
     * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chProjectionDiscountPK the primary key of the ch projection discount
     * @return the ch projection discount that was removed
     * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount remove(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws NoSuchChProjectionDiscountException, SystemException {
        return remove((Serializable) chProjectionDiscountPK);
    }

    /**
     * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch projection discount
     * @return the ch projection discount that was removed
     * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount remove(Serializable primaryKey)
        throws NoSuchChProjectionDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChProjectionDiscount chProjectionDiscount = (ChProjectionDiscount) session.get(ChProjectionDiscountImpl.class,
                    primaryKey);

            if (chProjectionDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chProjectionDiscount);
        } catch (NoSuchChProjectionDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChProjectionDiscount removeImpl(
        ChProjectionDiscount chProjectionDiscount) throws SystemException {
        chProjectionDiscount = toUnwrappedModel(chProjectionDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chProjectionDiscount)) {
                chProjectionDiscount = (ChProjectionDiscount) session.get(ChProjectionDiscountImpl.class,
                        chProjectionDiscount.getPrimaryKeyObj());
            }

            if (chProjectionDiscount != null) {
                session.delete(chProjectionDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chProjectionDiscount != null) {
            clearCache(chProjectionDiscount);
        }

        return chProjectionDiscount;
    }

    @Override
    public ChProjectionDiscount updateImpl(
        com.stpl.app.model.ChProjectionDiscount chProjectionDiscount)
        throws SystemException {
        chProjectionDiscount = toUnwrappedModel(chProjectionDiscount);

        boolean isNew = chProjectionDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chProjectionDiscount.isNew()) {
                session.save(chProjectionDiscount);

                chProjectionDiscount.setNew(false);
            } else {
                session.merge(chProjectionDiscount);
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

        EntityCacheUtil.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
            ChProjectionDiscountImpl.class,
            chProjectionDiscount.getPrimaryKey(), chProjectionDiscount);

        return chProjectionDiscount;
    }

    protected ChProjectionDiscount toUnwrappedModel(
        ChProjectionDiscount chProjectionDiscount) {
        if (chProjectionDiscount instanceof ChProjectionDiscountImpl) {
            return chProjectionDiscount;
        }

        ChProjectionDiscountImpl chProjectionDiscountImpl = new ChProjectionDiscountImpl();

        chProjectionDiscountImpl.setNew(chProjectionDiscount.isNew());
        chProjectionDiscountImpl.setPrimaryKey(chProjectionDiscount.getPrimaryKey());

        chProjectionDiscountImpl.setAdjustmentMethodology(chProjectionDiscount.getAdjustmentMethodology());
        chProjectionDiscountImpl.setProductGrowth(chProjectionDiscount.getProductGrowth());
        chProjectionDiscountImpl.setProjectionRate(chProjectionDiscount.getProjectionRate());
        chProjectionDiscountImpl.setProjectionDetailsSid(chProjectionDiscount.getProjectionDetailsSid());
        chProjectionDiscountImpl.setAccountGrowth(chProjectionDiscount.getAccountGrowth());
        chProjectionDiscountImpl.setDiscountAmount(chProjectionDiscount.getDiscountAmount());
        chProjectionDiscountImpl.setDiscountRate(chProjectionDiscount.getDiscountRate());
        chProjectionDiscountImpl.setPeriodSid(chProjectionDiscount.getPeriodSid());
        chProjectionDiscountImpl.setAdjustmentBasis(chProjectionDiscount.getAdjustmentBasis());
        chProjectionDiscountImpl.setAdjustmentValue(chProjectionDiscount.getAdjustmentValue());
        chProjectionDiscountImpl.setAdjustmentType(chProjectionDiscount.getAdjustmentType());
        chProjectionDiscountImpl.setRsModelSid(chProjectionDiscount.getRsModelSid());
        chProjectionDiscountImpl.setProjectionSales(chProjectionDiscount.getProjectionSales());

        return chProjectionDiscountImpl;
    }

    /**
     * Returns the ch projection discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch projection discount
     * @return the ch projection discount
     * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChProjectionDiscountException, SystemException {
        ChProjectionDiscount chProjectionDiscount = fetchByPrimaryKey(primaryKey);

        if (chProjectionDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chProjectionDiscount;
    }

    /**
     * Returns the ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchChProjectionDiscountException} if it could not be found.
     *
     * @param chProjectionDiscountPK the primary key of the ch projection discount
     * @return the ch projection discount
     * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount findByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws NoSuchChProjectionDiscountException, SystemException {
        return findByPrimaryKey((Serializable) chProjectionDiscountPK);
    }

    /**
     * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch projection discount
     * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChProjectionDiscount chProjectionDiscount = (ChProjectionDiscount) EntityCacheUtil.getResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                ChProjectionDiscountImpl.class, primaryKey);

        if (chProjectionDiscount == _nullChProjectionDiscount) {
            return null;
        }

        if (chProjectionDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                chProjectionDiscount = (ChProjectionDiscount) session.get(ChProjectionDiscountImpl.class,
                        primaryKey);

                if (chProjectionDiscount != null) {
                    cacheResult(chProjectionDiscount);
                } else {
                    EntityCacheUtil.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        ChProjectionDiscountImpl.class, primaryKey,
                        _nullChProjectionDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    ChProjectionDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chProjectionDiscount;
    }

    /**
     * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chProjectionDiscountPK the primary key of the ch projection discount
     * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChProjectionDiscount fetchByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) chProjectionDiscountPK);
    }

    /**
     * Returns all the ch projection discounts.
     *
     * @return the ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch projection discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch projection discounts
     * @param end the upper bound of the range of ch projection discounts (not inclusive)
     * @return the range of ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch projection discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch projection discounts
     * @param end the upper bound of the range of ch projection discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch projection discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChProjectionDiscount> findAll(int start, int end,
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

        List<ChProjectionDiscount> list = (List<ChProjectionDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHPROJECTIONDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHPROJECTIONDISCOUNT;

                if (pagination) {
                    sql = sql.concat(ChProjectionDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChProjectionDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChProjectionDiscount>(list);
                } else {
                    list = (List<ChProjectionDiscount>) QueryUtil.list(q,
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
     * Removes all the ch projection discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChProjectionDiscount chProjectionDiscount : findAll()) {
            remove(chProjectionDiscount);
        }
    }

    /**
     * Returns the number of ch projection discounts.
     *
     * @return the number of ch projection discounts
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

                Query q = session.createQuery(_SQL_COUNT_CHPROJECTIONDISCOUNT);

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
     * Initializes the ch projection discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChProjectionDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChProjectionDiscount>> listenersList = new ArrayList<ModelListener<ChProjectionDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChProjectionDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChProjectionDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
