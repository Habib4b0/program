package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmDiscountProjectionException;
import com.stpl.app.model.StNmDiscountProjection;
import com.stpl.app.model.impl.StNmDiscountProjectionImpl;
import com.stpl.app.model.impl.StNmDiscountProjectionModelImpl;
import com.stpl.app.service.persistence.StNmDiscountProjectionPersistence;

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
 * The persistence implementation for the st nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjectionPersistence
 * @see StNmDiscountProjectionUtil
 * @generated
 */
public class StNmDiscountProjectionPersistenceImpl extends BasePersistenceImpl<StNmDiscountProjection>
    implements StNmDiscountProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmDiscountProjectionUtil} to access the st nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmDiscountProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
            StNmDiscountProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
            StNmDiscountProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMDISCOUNTPROJECTION = "SELECT stNmDiscountProjection FROM StNmDiscountProjection stNmDiscountProjection";
    private static final String _SQL_COUNT_STNMDISCOUNTPROJECTION = "SELECT COUNT(stNmDiscountProjection) FROM StNmDiscountProjection stNmDiscountProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmDiscountProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmDiscountProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmDiscountProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "projectionRate", "adjustmentValue", "userId",
                "lastModifiedDate", "discountRate", "projectionSales",
                "adjustmentType", "adjustmentBasis", "periodSid",
                "adjustmentMethodology", "projectionDetailsSid", "rsModelSid",
                "sessionId"
            });
    private static StNmDiscountProjection _nullStNmDiscountProjection = new StNmDiscountProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmDiscountProjection> toCacheModel() {
                return _nullStNmDiscountProjectionCacheModel;
            }
        };

    private static CacheModel<StNmDiscountProjection> _nullStNmDiscountProjectionCacheModel =
        new CacheModel<StNmDiscountProjection>() {
            @Override
            public StNmDiscountProjection toEntityModel() {
                return _nullStNmDiscountProjection;
            }
        };

    public StNmDiscountProjectionPersistenceImpl() {
        setModelClass(StNmDiscountProjection.class);
    }

    /**
     * Caches the st nm discount projection in the entity cache if it is enabled.
     *
     * @param stNmDiscountProjection the st nm discount projection
     */
    @Override
    public void cacheResult(StNmDiscountProjection stNmDiscountProjection) {
        EntityCacheUtil.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionImpl.class,
            stNmDiscountProjection.getPrimaryKey(), stNmDiscountProjection);

        stNmDiscountProjection.resetOriginalValues();
    }

    /**
     * Caches the st nm discount projections in the entity cache if it is enabled.
     *
     * @param stNmDiscountProjections the st nm discount projections
     */
    @Override
    public void cacheResult(
        List<StNmDiscountProjection> stNmDiscountProjections) {
        for (StNmDiscountProjection stNmDiscountProjection : stNmDiscountProjections) {
            if (EntityCacheUtil.getResult(
                        StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        StNmDiscountProjectionImpl.class,
                        stNmDiscountProjection.getPrimaryKey()) == null) {
                cacheResult(stNmDiscountProjection);
            } else {
                stNmDiscountProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm discount projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmDiscountProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmDiscountProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm discount projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmDiscountProjection stNmDiscountProjection) {
        EntityCacheUtil.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionImpl.class,
            stNmDiscountProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmDiscountProjection> stNmDiscountProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmDiscountProjection stNmDiscountProjection : stNmDiscountProjections) {
            EntityCacheUtil.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                StNmDiscountProjectionImpl.class,
                stNmDiscountProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
     *
     * @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
     * @return the new st nm discount projection
     */
    @Override
    public StNmDiscountProjection create(
        StNmDiscountProjectionPK stNmDiscountProjectionPK) {
        StNmDiscountProjection stNmDiscountProjection = new StNmDiscountProjectionImpl();

        stNmDiscountProjection.setNew(true);
        stNmDiscountProjection.setPrimaryKey(stNmDiscountProjectionPK);

        return stNmDiscountProjection;
    }

    /**
     * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
     * @return the st nm discount projection that was removed
     * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection remove(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws NoSuchStNmDiscountProjectionException, SystemException {
        return remove((Serializable) stNmDiscountProjectionPK);
    }

    /**
     * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm discount projection
     * @return the st nm discount projection that was removed
     * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection remove(Serializable primaryKey)
        throws NoSuchStNmDiscountProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmDiscountProjection stNmDiscountProjection = (StNmDiscountProjection) session.get(StNmDiscountProjectionImpl.class,
                    primaryKey);

            if (stNmDiscountProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmDiscountProjection);
        } catch (NoSuchStNmDiscountProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmDiscountProjection removeImpl(
        StNmDiscountProjection stNmDiscountProjection)
        throws SystemException {
        stNmDiscountProjection = toUnwrappedModel(stNmDiscountProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmDiscountProjection)) {
                stNmDiscountProjection = (StNmDiscountProjection) session.get(StNmDiscountProjectionImpl.class,
                        stNmDiscountProjection.getPrimaryKeyObj());
            }

            if (stNmDiscountProjection != null) {
                session.delete(stNmDiscountProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmDiscountProjection != null) {
            clearCache(stNmDiscountProjection);
        }

        return stNmDiscountProjection;
    }

    @Override
    public StNmDiscountProjection updateImpl(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws SystemException {
        stNmDiscountProjection = toUnwrappedModel(stNmDiscountProjection);

        boolean isNew = stNmDiscountProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmDiscountProjection.isNew()) {
                session.save(stNmDiscountProjection);

                stNmDiscountProjection.setNew(false);
            } else {
                session.merge(stNmDiscountProjection);
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

        EntityCacheUtil.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjectionImpl.class,
            stNmDiscountProjection.getPrimaryKey(), stNmDiscountProjection);

        return stNmDiscountProjection;
    }

    protected StNmDiscountProjection toUnwrappedModel(
        StNmDiscountProjection stNmDiscountProjection) {
        if (stNmDiscountProjection instanceof StNmDiscountProjectionImpl) {
            return stNmDiscountProjection;
        }

        StNmDiscountProjectionImpl stNmDiscountProjectionImpl = new StNmDiscountProjectionImpl();

        stNmDiscountProjectionImpl.setNew(stNmDiscountProjection.isNew());
        stNmDiscountProjectionImpl.setPrimaryKey(stNmDiscountProjection.getPrimaryKey());

        stNmDiscountProjectionImpl.setProjectionRate(stNmDiscountProjection.getProjectionRate());
        stNmDiscountProjectionImpl.setAdjustmentValue(stNmDiscountProjection.getAdjustmentValue());
        stNmDiscountProjectionImpl.setUserId(stNmDiscountProjection.getUserId());
        stNmDiscountProjectionImpl.setLastModifiedDate(stNmDiscountProjection.getLastModifiedDate());
        stNmDiscountProjectionImpl.setDiscountRate(stNmDiscountProjection.getDiscountRate());
        stNmDiscountProjectionImpl.setProjectionSales(stNmDiscountProjection.getProjectionSales());
        stNmDiscountProjectionImpl.setAdjustmentType(stNmDiscountProjection.getAdjustmentType());
        stNmDiscountProjectionImpl.setAdjustmentBasis(stNmDiscountProjection.getAdjustmentBasis());
        stNmDiscountProjectionImpl.setPeriodSid(stNmDiscountProjection.getPeriodSid());
        stNmDiscountProjectionImpl.setAdjustmentMethodology(stNmDiscountProjection.getAdjustmentMethodology());
        stNmDiscountProjectionImpl.setProjectionDetailsSid(stNmDiscountProjection.getProjectionDetailsSid());
        stNmDiscountProjectionImpl.setRsModelSid(stNmDiscountProjection.getRsModelSid());
        stNmDiscountProjectionImpl.setSessionId(stNmDiscountProjection.getSessionId());

        return stNmDiscountProjectionImpl;
    }

    /**
     * Returns the st nm discount projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm discount projection
     * @return the st nm discount projection
     * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmDiscountProjectionException, SystemException {
        StNmDiscountProjection stNmDiscountProjection = fetchByPrimaryKey(primaryKey);

        if (stNmDiscountProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmDiscountProjection;
    }

    /**
     * Returns the st nm discount projection with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjectionException} if it could not be found.
     *
     * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
     * @return the st nm discount projection
     * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection findByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws NoSuchStNmDiscountProjectionException, SystemException {
        return findByPrimaryKey((Serializable) stNmDiscountProjectionPK);
    }

    /**
     * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm discount projection
     * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmDiscountProjection stNmDiscountProjection = (StNmDiscountProjection) EntityCacheUtil.getResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                StNmDiscountProjectionImpl.class, primaryKey);

        if (stNmDiscountProjection == _nullStNmDiscountProjection) {
            return null;
        }

        if (stNmDiscountProjection == null) {
            Session session = null;

            try {
                session = openSession();

                stNmDiscountProjection = (StNmDiscountProjection) session.get(StNmDiscountProjectionImpl.class,
                        primaryKey);

                if (stNmDiscountProjection != null) {
                    cacheResult(stNmDiscountProjection);
                } else {
                    EntityCacheUtil.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        StNmDiscountProjectionImpl.class, primaryKey,
                        _nullStNmDiscountProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    StNmDiscountProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmDiscountProjection;
    }

    /**
     * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
     * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjection fetchByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmDiscountProjectionPK);
    }

    /**
     * Returns all the st nm discount projections.
     *
     * @return the st nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm discount projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm discount projections
     * @param end the upper bound of the range of st nm discount projections (not inclusive)
     * @return the range of st nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm discount projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm discount projections
     * @param end the upper bound of the range of st nm discount projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjection> findAll(int start, int end,
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

        List<StNmDiscountProjection> list = (List<StNmDiscountProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMDISCOUNTPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMDISCOUNTPROJECTION;

                if (pagination) {
                    sql = sql.concat(StNmDiscountProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmDiscountProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmDiscountProjection>(list);
                } else {
                    list = (List<StNmDiscountProjection>) QueryUtil.list(q,
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
     * Removes all the st nm discount projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmDiscountProjection stNmDiscountProjection : findAll()) {
            remove(stNmDiscountProjection);
        }
    }

    /**
     * Returns the number of st nm discount projections.
     *
     * @return the number of st nm discount projections
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

                Query q = session.createQuery(_SQL_COUNT_STNMDISCOUNTPROJECTION);

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
     * Initializes the st nm discount projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmDiscountProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmDiscountProjection>> listenersList = new ArrayList<ModelListener<StNmDiscountProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmDiscountProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmDiscountProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
