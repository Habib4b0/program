package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmPpaProjectionException;
import com.stpl.app.model.StNmPpaProjection;
import com.stpl.app.model.impl.StNmPpaProjectionImpl;
import com.stpl.app.model.impl.StNmPpaProjectionModelImpl;
import com.stpl.app.service.persistence.StNmPpaProjectionPersistence;

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
 * The persistence implementation for the st nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionPersistence
 * @see StNmPpaProjectionUtil
 * @generated
 */
public class StNmPpaProjectionPersistenceImpl extends BasePersistenceImpl<StNmPpaProjection>
    implements StNmPpaProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmPpaProjectionUtil} to access the st nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmPpaProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
            StNmPpaProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
            StNmPpaProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMPPAPROJECTION = "SELECT stNmPpaProjection FROM StNmPpaProjection stNmPpaProjection";
    private static final String _SQL_COUNT_STNMPPAPROJECTION = "SELECT COUNT(stNmPpaProjection) FROM StNmPpaProjection stNmPpaProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmPpaProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmPpaProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmPpaProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "periodSid", "projectionRate",
                "projectionDetailsSid", "userId", "priceCap",
                "projectionDiscountUnits", "sessionId",
                "projectionDiscountDollar", "reset", "projectionSales",
                "projectionMap", "resetPriceCap"
            });
    private static StNmPpaProjection _nullStNmPpaProjection = new StNmPpaProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmPpaProjection> toCacheModel() {
                return _nullStNmPpaProjectionCacheModel;
            }
        };

    private static CacheModel<StNmPpaProjection> _nullStNmPpaProjectionCacheModel =
        new CacheModel<StNmPpaProjection>() {
            @Override
            public StNmPpaProjection toEntityModel() {
                return _nullStNmPpaProjection;
            }
        };

    public StNmPpaProjectionPersistenceImpl() {
        setModelClass(StNmPpaProjection.class);
    }

    /**
     * Caches the st nm ppa projection in the entity cache if it is enabled.
     *
     * @param stNmPpaProjection the st nm ppa projection
     */
    @Override
    public void cacheResult(StNmPpaProjection stNmPpaProjection) {
        EntityCacheUtil.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey(),
            stNmPpaProjection);

        stNmPpaProjection.resetOriginalValues();
    }

    /**
     * Caches the st nm ppa projections in the entity cache if it is enabled.
     *
     * @param stNmPpaProjections the st nm ppa projections
     */
    @Override
    public void cacheResult(List<StNmPpaProjection> stNmPpaProjections) {
        for (StNmPpaProjection stNmPpaProjection : stNmPpaProjections) {
            if (EntityCacheUtil.getResult(
                        StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        StNmPpaProjectionImpl.class,
                        stNmPpaProjection.getPrimaryKey()) == null) {
                cacheResult(stNmPpaProjection);
            } else {
                stNmPpaProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm ppa projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmPpaProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmPpaProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm ppa projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmPpaProjection stNmPpaProjection) {
        EntityCacheUtil.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmPpaProjection> stNmPpaProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmPpaProjection stNmPpaProjection : stNmPpaProjections) {
            EntityCacheUtil.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
     *
     * @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
     * @return the new st nm ppa projection
     */
    @Override
    public StNmPpaProjection create(StNmPpaProjectionPK stNmPpaProjectionPK) {
        StNmPpaProjection stNmPpaProjection = new StNmPpaProjectionImpl();

        stNmPpaProjection.setNew(true);
        stNmPpaProjection.setPrimaryKey(stNmPpaProjectionPK);

        return stNmPpaProjection;
    }

    /**
     * Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
     * @return the st nm ppa projection that was removed
     * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection remove(StNmPpaProjectionPK stNmPpaProjectionPK)
        throws NoSuchStNmPpaProjectionException, SystemException {
        return remove((Serializable) stNmPpaProjectionPK);
    }

    /**
     * Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm ppa projection
     * @return the st nm ppa projection that was removed
     * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection remove(Serializable primaryKey)
        throws NoSuchStNmPpaProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmPpaProjection stNmPpaProjection = (StNmPpaProjection) session.get(StNmPpaProjectionImpl.class,
                    primaryKey);

            if (stNmPpaProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmPpaProjection);
        } catch (NoSuchStNmPpaProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmPpaProjection removeImpl(StNmPpaProjection stNmPpaProjection)
        throws SystemException {
        stNmPpaProjection = toUnwrappedModel(stNmPpaProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmPpaProjection)) {
                stNmPpaProjection = (StNmPpaProjection) session.get(StNmPpaProjectionImpl.class,
                        stNmPpaProjection.getPrimaryKeyObj());
            }

            if (stNmPpaProjection != null) {
                session.delete(stNmPpaProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmPpaProjection != null) {
            clearCache(stNmPpaProjection);
        }

        return stNmPpaProjection;
    }

    @Override
    public StNmPpaProjection updateImpl(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection)
        throws SystemException {
        stNmPpaProjection = toUnwrappedModel(stNmPpaProjection);

        boolean isNew = stNmPpaProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmPpaProjection.isNew()) {
                session.save(stNmPpaProjection);

                stNmPpaProjection.setNew(false);
            } else {
                session.merge(stNmPpaProjection);
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

        EntityCacheUtil.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey(),
            stNmPpaProjection);

        return stNmPpaProjection;
    }

    protected StNmPpaProjection toUnwrappedModel(
        StNmPpaProjection stNmPpaProjection) {
        if (stNmPpaProjection instanceof StNmPpaProjectionImpl) {
            return stNmPpaProjection;
        }

        StNmPpaProjectionImpl stNmPpaProjectionImpl = new StNmPpaProjectionImpl();

        stNmPpaProjectionImpl.setNew(stNmPpaProjection.isNew());
        stNmPpaProjectionImpl.setPrimaryKey(stNmPpaProjection.getPrimaryKey());

        stNmPpaProjectionImpl.setLastModifiedDate(stNmPpaProjection.getLastModifiedDate());
        stNmPpaProjectionImpl.setPeriodSid(stNmPpaProjection.getPeriodSid());
        stNmPpaProjectionImpl.setProjectionRate(stNmPpaProjection.getProjectionRate());
        stNmPpaProjectionImpl.setProjectionDetailsSid(stNmPpaProjection.getProjectionDetailsSid());
        stNmPpaProjectionImpl.setUserId(stNmPpaProjection.getUserId());
        stNmPpaProjectionImpl.setPriceCap(stNmPpaProjection.getPriceCap());
        stNmPpaProjectionImpl.setProjectionDiscountUnits(stNmPpaProjection.getProjectionDiscountUnits());
        stNmPpaProjectionImpl.setSessionId(stNmPpaProjection.getSessionId());
        stNmPpaProjectionImpl.setProjectionDiscountDollar(stNmPpaProjection.getProjectionDiscountDollar());
        stNmPpaProjectionImpl.setReset(stNmPpaProjection.isReset());
        stNmPpaProjectionImpl.setProjectionSales(stNmPpaProjection.getProjectionSales());
        stNmPpaProjectionImpl.setProjectionMap(stNmPpaProjection.getProjectionMap());
        stNmPpaProjectionImpl.setResetPriceCap(stNmPpaProjection.isResetPriceCap());

        return stNmPpaProjectionImpl;
    }

    /**
     * Returns the st nm ppa projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm ppa projection
     * @return the st nm ppa projection
     * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmPpaProjectionException, SystemException {
        StNmPpaProjection stNmPpaProjection = fetchByPrimaryKey(primaryKey);

        if (stNmPpaProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmPpaProjection;
    }

    /**
     * Returns the st nm ppa projection with the primary key or throws a {@link com.stpl.app.NoSuchStNmPpaProjectionException} if it could not be found.
     *
     * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
     * @return the st nm ppa projection
     * @throws com.stpl.app.NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection findByPrimaryKey(
        StNmPpaProjectionPK stNmPpaProjectionPK)
        throws NoSuchStNmPpaProjectionException, SystemException {
        return findByPrimaryKey((Serializable) stNmPpaProjectionPK);
    }

    /**
     * Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm ppa projection
     * @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmPpaProjection stNmPpaProjection = (StNmPpaProjection) EntityCacheUtil.getResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                StNmPpaProjectionImpl.class, primaryKey);

        if (stNmPpaProjection == _nullStNmPpaProjection) {
            return null;
        }

        if (stNmPpaProjection == null) {
            Session session = null;

            try {
                session = openSession();

                stNmPpaProjection = (StNmPpaProjection) session.get(StNmPpaProjectionImpl.class,
                        primaryKey);

                if (stNmPpaProjection != null) {
                    cacheResult(stNmPpaProjection);
                } else {
                    EntityCacheUtil.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        StNmPpaProjectionImpl.class, primaryKey,
                        _nullStNmPpaProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    StNmPpaProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmPpaProjection;
    }

    /**
     * Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
     * @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjection fetchByPrimaryKey(
        StNmPpaProjectionPK stNmPpaProjectionPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmPpaProjectionPK);
    }

    /**
     * Returns all the st nm ppa projections.
     *
     * @return the st nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm ppa projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm ppa projections
     * @param end the upper bound of the range of st nm ppa projections (not inclusive)
     * @return the range of st nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm ppa projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm ppa projections
     * @param end the upper bound of the range of st nm ppa projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjection> findAll(int start, int end,
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

        List<StNmPpaProjection> list = (List<StNmPpaProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMPPAPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMPPAPROJECTION;

                if (pagination) {
                    sql = sql.concat(StNmPpaProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmPpaProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmPpaProjection>(list);
                } else {
                    list = (List<StNmPpaProjection>) QueryUtil.list(q,
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
     * Removes all the st nm ppa projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmPpaProjection stNmPpaProjection : findAll()) {
            remove(stNmPpaProjection);
        }
    }

    /**
     * Returns the number of st nm ppa projections.
     *
     * @return the number of st nm ppa projections
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

                Query q = session.createQuery(_SQL_COUNT_STNMPPAPROJECTION);

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
     * Initializes the st nm ppa projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmPpaProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmPpaProjection>> listenersList = new ArrayList<ModelListener<StNmPpaProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmPpaProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmPpaProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
