package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmActualDiscountException;
import com.stpl.app.model.NmActualDiscount;
import com.stpl.app.model.impl.NmActualDiscountImpl;
import com.stpl.app.model.impl.NmActualDiscountModelImpl;
import com.stpl.app.service.persistence.NmActualDiscountPersistence;

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
 * The persistence implementation for the nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualDiscountPersistence
 * @see NmActualDiscountUtil
 * @generated
 */
public class NmActualDiscountPersistenceImpl extends BasePersistenceImpl<NmActualDiscount>
    implements NmActualDiscountPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmActualDiscountUtil} to access the nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmActualDiscountImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            NmActualDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
            NmActualDiscountImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMACTUALDISCOUNT = "SELECT nmActualDiscount FROM NmActualDiscount nmActualDiscount";
    private static final String _SQL_COUNT_NMACTUALDISCOUNT = "SELECT COUNT(nmActualDiscount) FROM NmActualDiscount nmActualDiscount";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmActualDiscount.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmActualDiscount exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmActualDiscountPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualRate", "periodSid", "actualProjectionSales",
                "projectionDetailsSid", "actualProjectionRate", "actualSales"
            });
    private static NmActualDiscount _nullNmActualDiscount = new NmActualDiscountImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmActualDiscount> toCacheModel() {
                return _nullNmActualDiscountCacheModel;
            }
        };

    private static CacheModel<NmActualDiscount> _nullNmActualDiscountCacheModel = new CacheModel<NmActualDiscount>() {
            @Override
            public NmActualDiscount toEntityModel() {
                return _nullNmActualDiscount;
            }
        };

    public NmActualDiscountPersistenceImpl() {
        setModelClass(NmActualDiscount.class);
    }

    /**
     * Caches the nm actual discount in the entity cache if it is enabled.
     *
     * @param nmActualDiscount the nm actual discount
     */
    @Override
    public void cacheResult(NmActualDiscount nmActualDiscount) {
        EntityCacheUtil.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey(),
            nmActualDiscount);

        nmActualDiscount.resetOriginalValues();
    }

    /**
     * Caches the nm actual discounts in the entity cache if it is enabled.
     *
     * @param nmActualDiscounts the nm actual discounts
     */
    @Override
    public void cacheResult(List<NmActualDiscount> nmActualDiscounts) {
        for (NmActualDiscount nmActualDiscount : nmActualDiscounts) {
            if (EntityCacheUtil.getResult(
                        NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        NmActualDiscountImpl.class,
                        nmActualDiscount.getPrimaryKey()) == null) {
                cacheResult(nmActualDiscount);
            } else {
                nmActualDiscount.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm actual discounts.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmActualDiscountImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmActualDiscountImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm actual discount.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmActualDiscount nmActualDiscount) {
        EntityCacheUtil.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmActualDiscount> nmActualDiscounts) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmActualDiscount nmActualDiscount : nmActualDiscounts) {
            EntityCacheUtil.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
     *
     * @param nmActualDiscountPK the primary key for the new nm actual discount
     * @return the new nm actual discount
     */
    @Override
    public NmActualDiscount create(NmActualDiscountPK nmActualDiscountPK) {
        NmActualDiscount nmActualDiscount = new NmActualDiscountImpl();

        nmActualDiscount.setNew(true);
        nmActualDiscount.setPrimaryKey(nmActualDiscountPK);

        return nmActualDiscount;
    }

    /**
     * Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmActualDiscountPK the primary key of the nm actual discount
     * @return the nm actual discount that was removed
     * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount remove(NmActualDiscountPK nmActualDiscountPK)
        throws NoSuchNmActualDiscountException, SystemException {
        return remove((Serializable) nmActualDiscountPK);
    }

    /**
     * Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm actual discount
     * @return the nm actual discount that was removed
     * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount remove(Serializable primaryKey)
        throws NoSuchNmActualDiscountException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmActualDiscount nmActualDiscount = (NmActualDiscount) session.get(NmActualDiscountImpl.class,
                    primaryKey);

            if (nmActualDiscount == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmActualDiscount);
        } catch (NoSuchNmActualDiscountException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmActualDiscount removeImpl(NmActualDiscount nmActualDiscount)
        throws SystemException {
        nmActualDiscount = toUnwrappedModel(nmActualDiscount);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmActualDiscount)) {
                nmActualDiscount = (NmActualDiscount) session.get(NmActualDiscountImpl.class,
                        nmActualDiscount.getPrimaryKeyObj());
            }

            if (nmActualDiscount != null) {
                session.delete(nmActualDiscount);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmActualDiscount != null) {
            clearCache(nmActualDiscount);
        }

        return nmActualDiscount;
    }

    @Override
    public NmActualDiscount updateImpl(
        com.stpl.app.model.NmActualDiscount nmActualDiscount)
        throws SystemException {
        nmActualDiscount = toUnwrappedModel(nmActualDiscount);

        boolean isNew = nmActualDiscount.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmActualDiscount.isNew()) {
                session.save(nmActualDiscount);

                nmActualDiscount.setNew(false);
            } else {
                session.merge(nmActualDiscount);
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

        EntityCacheUtil.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
            NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey(),
            nmActualDiscount);

        return nmActualDiscount;
    }

    protected NmActualDiscount toUnwrappedModel(
        NmActualDiscount nmActualDiscount) {
        if (nmActualDiscount instanceof NmActualDiscountImpl) {
            return nmActualDiscount;
        }

        NmActualDiscountImpl nmActualDiscountImpl = new NmActualDiscountImpl();

        nmActualDiscountImpl.setNew(nmActualDiscount.isNew());
        nmActualDiscountImpl.setPrimaryKey(nmActualDiscount.getPrimaryKey());

        nmActualDiscountImpl.setActualRate(nmActualDiscount.getActualRate());
        nmActualDiscountImpl.setPeriodSid(nmActualDiscount.getPeriodSid());
        nmActualDiscountImpl.setActualProjectionSales(nmActualDiscount.getActualProjectionSales());
        nmActualDiscountImpl.setProjectionDetailsSid(nmActualDiscount.getProjectionDetailsSid());
        nmActualDiscountImpl.setActualProjectionRate(nmActualDiscount.getActualProjectionRate());
        nmActualDiscountImpl.setActualSales(nmActualDiscount.getActualSales());

        return nmActualDiscountImpl;
    }

    /**
     * Returns the nm actual discount with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm actual discount
     * @return the nm actual discount
     * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmActualDiscountException, SystemException {
        NmActualDiscount nmActualDiscount = fetchByPrimaryKey(primaryKey);

        if (nmActualDiscount == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmActualDiscount;
    }

    /**
     * Returns the nm actual discount with the primary key or throws a {@link com.stpl.app.NoSuchNmActualDiscountException} if it could not be found.
     *
     * @param nmActualDiscountPK the primary key of the nm actual discount
     * @return the nm actual discount
     * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount findByPrimaryKey(
        NmActualDiscountPK nmActualDiscountPK)
        throws NoSuchNmActualDiscountException, SystemException {
        return findByPrimaryKey((Serializable) nmActualDiscountPK);
    }

    /**
     * Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm actual discount
     * @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmActualDiscount nmActualDiscount = (NmActualDiscount) EntityCacheUtil.getResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                NmActualDiscountImpl.class, primaryKey);

        if (nmActualDiscount == _nullNmActualDiscount) {
            return null;
        }

        if (nmActualDiscount == null) {
            Session session = null;

            try {
                session = openSession();

                nmActualDiscount = (NmActualDiscount) session.get(NmActualDiscountImpl.class,
                        primaryKey);

                if (nmActualDiscount != null) {
                    cacheResult(nmActualDiscount);
                } else {
                    EntityCacheUtil.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                        NmActualDiscountImpl.class, primaryKey,
                        _nullNmActualDiscount);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
                    NmActualDiscountImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmActualDiscount;
    }

    /**
     * Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmActualDiscountPK the primary key of the nm actual discount
     * @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualDiscount fetchByPrimaryKey(
        NmActualDiscountPK nmActualDiscountPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) nmActualDiscountPK);
    }

    /**
     * Returns all the nm actual discounts.
     *
     * @return the nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualDiscount> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm actual discounts
     * @param end the upper bound of the range of nm actual discounts (not inclusive)
     * @return the range of nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualDiscount> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm actual discounts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm actual discounts
     * @param end the upper bound of the range of nm actual discounts (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm actual discounts
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualDiscount> findAll(int start, int end,
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

        List<NmActualDiscount> list = (List<NmActualDiscount>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMACTUALDISCOUNT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMACTUALDISCOUNT;

                if (pagination) {
                    sql = sql.concat(NmActualDiscountModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmActualDiscount>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmActualDiscount>(list);
                } else {
                    list = (List<NmActualDiscount>) QueryUtil.list(q,
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
     * Removes all the nm actual discounts from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmActualDiscount nmActualDiscount : findAll()) {
            remove(nmActualDiscount);
        }
    }

    /**
     * Returns the number of nm actual discounts.
     *
     * @return the number of nm actual discounts
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

                Query q = session.createQuery(_SQL_COUNT_NMACTUALDISCOUNT);

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
     * Initializes the nm actual discount persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmActualDiscount")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmActualDiscount>> listenersList = new ArrayList<ModelListener<NmActualDiscount>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmActualDiscount>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmActualDiscountImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
