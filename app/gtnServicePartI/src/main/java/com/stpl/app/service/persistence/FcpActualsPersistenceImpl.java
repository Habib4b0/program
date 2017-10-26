package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchFcpActualsException;
import com.stpl.app.model.FcpActuals;
import com.stpl.app.model.impl.FcpActualsImpl;
import com.stpl.app.model.impl.FcpActualsModelImpl;
import com.stpl.app.service.persistence.FcpActualsPersistence;

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
 * The persistence implementation for the fcp actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpActualsPersistence
 * @see FcpActualsUtil
 * @generated
 */
public class FcpActualsPersistenceImpl extends BasePersistenceImpl<FcpActuals>
    implements FcpActualsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FcpActualsUtil} to access the fcp actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FcpActualsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsModelImpl.FINDER_CACHE_ENABLED, FcpActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsModelImpl.FINDER_CACHE_ENABLED, FcpActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FCPACTUALS = "SELECT fcpActuals FROM FcpActuals fcpActuals";
    private static final String _SQL_COUNT_FCPACTUALS = "SELECT COUNT(fcpActuals) FROM FcpActuals fcpActuals";
    private static final String _ORDER_BY_ENTITY_ALIAS = "fcpActuals.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FcpActuals exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FcpActualsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodSid", "priceType", "actualPrice", "notes",
                "naProjDetailsSid"
            });
    private static FcpActuals _nullFcpActuals = new FcpActualsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FcpActuals> toCacheModel() {
                return _nullFcpActualsCacheModel;
            }
        };

    private static CacheModel<FcpActuals> _nullFcpActualsCacheModel = new CacheModel<FcpActuals>() {
            @Override
            public FcpActuals toEntityModel() {
                return _nullFcpActuals;
            }
        };

    public FcpActualsPersistenceImpl() {
        setModelClass(FcpActuals.class);
    }

    /**
     * Caches the fcp actuals in the entity cache if it is enabled.
     *
     * @param fcpActuals the fcp actuals
     */
    @Override
    public void cacheResult(FcpActuals fcpActuals) {
        EntityCacheUtil.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsImpl.class, fcpActuals.getPrimaryKey(), fcpActuals);

        fcpActuals.resetOriginalValues();
    }

    /**
     * Caches the fcp actualses in the entity cache if it is enabled.
     *
     * @param fcpActualses the fcp actualses
     */
    @Override
    public void cacheResult(List<FcpActuals> fcpActualses) {
        for (FcpActuals fcpActuals : fcpActualses) {
            if (EntityCacheUtil.getResult(
                        FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
                        FcpActualsImpl.class, fcpActuals.getPrimaryKey()) == null) {
                cacheResult(fcpActuals);
            } else {
                fcpActuals.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all fcp actualses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FcpActualsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FcpActualsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the fcp actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FcpActuals fcpActuals) {
        EntityCacheUtil.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsImpl.class, fcpActuals.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FcpActuals> fcpActualses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FcpActuals fcpActuals : fcpActualses) {
            EntityCacheUtil.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
                FcpActualsImpl.class, fcpActuals.getPrimaryKey());
        }
    }

    /**
     * Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
     *
     * @param fcpActualsPK the primary key for the new fcp actuals
     * @return the new fcp actuals
     */
    @Override
    public FcpActuals create(FcpActualsPK fcpActualsPK) {
        FcpActuals fcpActuals = new FcpActualsImpl();

        fcpActuals.setNew(true);
        fcpActuals.setPrimaryKey(fcpActualsPK);

        return fcpActuals;
    }

    /**
     * Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param fcpActualsPK the primary key of the fcp actuals
     * @return the fcp actuals that was removed
     * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals remove(FcpActualsPK fcpActualsPK)
        throws NoSuchFcpActualsException, SystemException {
        return remove((Serializable) fcpActualsPK);
    }

    /**
     * Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the fcp actuals
     * @return the fcp actuals that was removed
     * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals remove(Serializable primaryKey)
        throws NoSuchFcpActualsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FcpActuals fcpActuals = (FcpActuals) session.get(FcpActualsImpl.class,
                    primaryKey);

            if (fcpActuals == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFcpActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(fcpActuals);
        } catch (NoSuchFcpActualsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FcpActuals removeImpl(FcpActuals fcpActuals)
        throws SystemException {
        fcpActuals = toUnwrappedModel(fcpActuals);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(fcpActuals)) {
                fcpActuals = (FcpActuals) session.get(FcpActualsImpl.class,
                        fcpActuals.getPrimaryKeyObj());
            }

            if (fcpActuals != null) {
                session.delete(fcpActuals);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (fcpActuals != null) {
            clearCache(fcpActuals);
        }

        return fcpActuals;
    }

    @Override
    public FcpActuals updateImpl(com.stpl.app.model.FcpActuals fcpActuals)
        throws SystemException {
        fcpActuals = toUnwrappedModel(fcpActuals);

        boolean isNew = fcpActuals.isNew();

        Session session = null;

        try {
            session = openSession();

            if (fcpActuals.isNew()) {
                session.save(fcpActuals);

                fcpActuals.setNew(false);
            } else {
                session.merge(fcpActuals);
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

        EntityCacheUtil.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
            FcpActualsImpl.class, fcpActuals.getPrimaryKey(), fcpActuals);

        return fcpActuals;
    }

    protected FcpActuals toUnwrappedModel(FcpActuals fcpActuals) {
        if (fcpActuals instanceof FcpActualsImpl) {
            return fcpActuals;
        }

        FcpActualsImpl fcpActualsImpl = new FcpActualsImpl();

        fcpActualsImpl.setNew(fcpActuals.isNew());
        fcpActualsImpl.setPrimaryKey(fcpActuals.getPrimaryKey());

        fcpActualsImpl.setPeriodSid(fcpActuals.getPeriodSid());
        fcpActualsImpl.setPriceType(fcpActuals.getPriceType());
        fcpActualsImpl.setActualPrice(fcpActuals.getActualPrice());
        fcpActualsImpl.setNotes(fcpActuals.getNotes());
        fcpActualsImpl.setNaProjDetailsSid(fcpActuals.getNaProjDetailsSid());

        return fcpActualsImpl;
    }

    /**
     * Returns the fcp actuals with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the fcp actuals
     * @return the fcp actuals
     * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals findByPrimaryKey(Serializable primaryKey)
        throws NoSuchFcpActualsException, SystemException {
        FcpActuals fcpActuals = fetchByPrimaryKey(primaryKey);

        if (fcpActuals == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchFcpActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return fcpActuals;
    }

    /**
     * Returns the fcp actuals with the primary key or throws a {@link com.stpl.app.NoSuchFcpActualsException} if it could not be found.
     *
     * @param fcpActualsPK the primary key of the fcp actuals
     * @return the fcp actuals
     * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals findByPrimaryKey(FcpActualsPK fcpActualsPK)
        throws NoSuchFcpActualsException, SystemException {
        return findByPrimaryKey((Serializable) fcpActualsPK);
    }

    /**
     * Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the fcp actuals
     * @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        FcpActuals fcpActuals = (FcpActuals) EntityCacheUtil.getResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
                FcpActualsImpl.class, primaryKey);

        if (fcpActuals == _nullFcpActuals) {
            return null;
        }

        if (fcpActuals == null) {
            Session session = null;

            try {
                session = openSession();

                fcpActuals = (FcpActuals) session.get(FcpActualsImpl.class,
                        primaryKey);

                if (fcpActuals != null) {
                    cacheResult(fcpActuals);
                } else {
                    EntityCacheUtil.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
                        FcpActualsImpl.class, primaryKey, _nullFcpActuals);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
                    FcpActualsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return fcpActuals;
    }

    /**
     * Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param fcpActualsPK the primary key of the fcp actuals
     * @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpActuals fetchByPrimaryKey(FcpActualsPK fcpActualsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) fcpActualsPK);
    }

    /**
     * Returns all the fcp actualses.
     *
     * @return the fcp actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpActuals> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the fcp actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of fcp actualses
     * @param end the upper bound of the range of fcp actualses (not inclusive)
     * @return the range of fcp actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpActuals> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the fcp actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of fcp actualses
     * @param end the upper bound of the range of fcp actualses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of fcp actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpActuals> findAll(int start, int end,
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

        List<FcpActuals> list = (List<FcpActuals>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FCPACTUALS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FCPACTUALS;

                if (pagination) {
                    sql = sql.concat(FcpActualsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<FcpActuals>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<FcpActuals>(list);
                } else {
                    list = (List<FcpActuals>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the fcp actualses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (FcpActuals fcpActuals : findAll()) {
            remove(fcpActuals);
        }
    }

    /**
     * Returns the number of fcp actualses.
     *
     * @return the number of fcp actualses
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

                Query q = session.createQuery(_SQL_COUNT_FCPACTUALS);

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
     * Initializes the fcp actuals persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.FcpActuals")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FcpActuals>> listenersList = new ArrayList<ModelListener<FcpActuals>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FcpActuals>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FcpActualsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
