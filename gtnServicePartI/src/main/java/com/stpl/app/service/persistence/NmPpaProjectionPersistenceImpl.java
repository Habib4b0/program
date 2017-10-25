package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmPpaProjectionException;
import com.stpl.app.model.NmPpaProjection;
import com.stpl.app.model.impl.NmPpaProjectionImpl;
import com.stpl.app.model.impl.NmPpaProjectionModelImpl;
import com.stpl.app.service.persistence.NmPpaProjectionPersistence;

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
 * The persistence implementation for the nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionPersistence
 * @see NmPpaProjectionUtil
 * @generated
 */
public class NmPpaProjectionPersistenceImpl extends BasePersistenceImpl<NmPpaProjection>
    implements NmPpaProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmPpaProjectionUtil} to access the nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmPpaProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmPpaProjectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmPpaProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMPPAPROJECTION = "SELECT nmPpaProjection FROM NmPpaProjection nmPpaProjection";
    private static final String _SQL_COUNT_NMPPAPROJECTION = "SELECT COUNT(nmPpaProjection) FROM NmPpaProjection nmPpaProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmPpaProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmPpaProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmPpaProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodSid", "projectionRate", "projectionDetailsSid",
                "priceCap", "projectionDiscountUnits",
                "projectionDiscountDollar", "reset", "projectionSales",
                "projectionMap", "resetPriceCap"
            });
    private static NmPpaProjection _nullNmPpaProjection = new NmPpaProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmPpaProjection> toCacheModel() {
                return _nullNmPpaProjectionCacheModel;
            }
        };

    private static CacheModel<NmPpaProjection> _nullNmPpaProjectionCacheModel = new CacheModel<NmPpaProjection>() {
            @Override
            public NmPpaProjection toEntityModel() {
                return _nullNmPpaProjection;
            }
        };

    public NmPpaProjectionPersistenceImpl() {
        setModelClass(NmPpaProjection.class);
    }

    /**
     * Caches the nm ppa projection in the entity cache if it is enabled.
     *
     * @param nmPpaProjection the nm ppa projection
     */
    @Override
    public void cacheResult(NmPpaProjection nmPpaProjection) {
        EntityCacheUtil.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey(),
            nmPpaProjection);

        nmPpaProjection.resetOriginalValues();
    }

    /**
     * Caches the nm ppa projections in the entity cache if it is enabled.
     *
     * @param nmPpaProjections the nm ppa projections
     */
    @Override
    public void cacheResult(List<NmPpaProjection> nmPpaProjections) {
        for (NmPpaProjection nmPpaProjection : nmPpaProjections) {
            if (EntityCacheUtil.getResult(
                        NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmPpaProjectionImpl.class,
                        nmPpaProjection.getPrimaryKey()) == null) {
                cacheResult(nmPpaProjection);
            } else {
                nmPpaProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm ppa projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmPpaProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmPpaProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm ppa projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmPpaProjection nmPpaProjection) {
        EntityCacheUtil.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmPpaProjection> nmPpaProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmPpaProjection nmPpaProjection : nmPpaProjections) {
            EntityCacheUtil.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
     *
     * @param nmPpaProjectionPK the primary key for the new nm ppa projection
     * @return the new nm ppa projection
     */
    @Override
    public NmPpaProjection create(NmPpaProjectionPK nmPpaProjectionPK) {
        NmPpaProjection nmPpaProjection = new NmPpaProjectionImpl();

        nmPpaProjection.setNew(true);
        nmPpaProjection.setPrimaryKey(nmPpaProjectionPK);

        return nmPpaProjection;
    }

    /**
     * Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmPpaProjectionPK the primary key of the nm ppa projection
     * @return the nm ppa projection that was removed
     * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection remove(NmPpaProjectionPK nmPpaProjectionPK)
        throws NoSuchNmPpaProjectionException, SystemException {
        return remove((Serializable) nmPpaProjectionPK);
    }

    /**
     * Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm ppa projection
     * @return the nm ppa projection that was removed
     * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection remove(Serializable primaryKey)
        throws NoSuchNmPpaProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmPpaProjection nmPpaProjection = (NmPpaProjection) session.get(NmPpaProjectionImpl.class,
                    primaryKey);

            if (nmPpaProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmPpaProjection);
        } catch (NoSuchNmPpaProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmPpaProjection removeImpl(NmPpaProjection nmPpaProjection)
        throws SystemException {
        nmPpaProjection = toUnwrappedModel(nmPpaProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmPpaProjection)) {
                nmPpaProjection = (NmPpaProjection) session.get(NmPpaProjectionImpl.class,
                        nmPpaProjection.getPrimaryKeyObj());
            }

            if (nmPpaProjection != null) {
                session.delete(nmPpaProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmPpaProjection != null) {
            clearCache(nmPpaProjection);
        }

        return nmPpaProjection;
    }

    @Override
    public NmPpaProjection updateImpl(
        com.stpl.app.model.NmPpaProjection nmPpaProjection)
        throws SystemException {
        nmPpaProjection = toUnwrappedModel(nmPpaProjection);

        boolean isNew = nmPpaProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmPpaProjection.isNew()) {
                session.save(nmPpaProjection);

                nmPpaProjection.setNew(false);
            } else {
                session.merge(nmPpaProjection);
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

        EntityCacheUtil.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey(),
            nmPpaProjection);

        return nmPpaProjection;
    }

    protected NmPpaProjection toUnwrappedModel(NmPpaProjection nmPpaProjection) {
        if (nmPpaProjection instanceof NmPpaProjectionImpl) {
            return nmPpaProjection;
        }

        NmPpaProjectionImpl nmPpaProjectionImpl = new NmPpaProjectionImpl();

        nmPpaProjectionImpl.setNew(nmPpaProjection.isNew());
        nmPpaProjectionImpl.setPrimaryKey(nmPpaProjection.getPrimaryKey());

        nmPpaProjectionImpl.setPeriodSid(nmPpaProjection.getPeriodSid());
        nmPpaProjectionImpl.setProjectionRate(nmPpaProjection.getProjectionRate());
        nmPpaProjectionImpl.setProjectionDetailsSid(nmPpaProjection.getProjectionDetailsSid());
        nmPpaProjectionImpl.setPriceCap(nmPpaProjection.getPriceCap());
        nmPpaProjectionImpl.setProjectionDiscountUnits(nmPpaProjection.getProjectionDiscountUnits());
        nmPpaProjectionImpl.setProjectionDiscountDollar(nmPpaProjection.getProjectionDiscountDollar());
        nmPpaProjectionImpl.setReset(nmPpaProjection.isReset());
        nmPpaProjectionImpl.setProjectionSales(nmPpaProjection.getProjectionSales());
        nmPpaProjectionImpl.setProjectionMap(nmPpaProjection.getProjectionMap());
        nmPpaProjectionImpl.setResetPriceCap(nmPpaProjection.isResetPriceCap());

        return nmPpaProjectionImpl;
    }

    /**
     * Returns the nm ppa projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm ppa projection
     * @return the nm ppa projection
     * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmPpaProjectionException, SystemException {
        NmPpaProjection nmPpaProjection = fetchByPrimaryKey(primaryKey);

        if (nmPpaProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmPpaProjection;
    }

    /**
     * Returns the nm ppa projection with the primary key or throws a {@link com.stpl.app.NoSuchNmPpaProjectionException} if it could not be found.
     *
     * @param nmPpaProjectionPK the primary key of the nm ppa projection
     * @return the nm ppa projection
     * @throws com.stpl.app.NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection findByPrimaryKey(NmPpaProjectionPK nmPpaProjectionPK)
        throws NoSuchNmPpaProjectionException, SystemException {
        return findByPrimaryKey((Serializable) nmPpaProjectionPK);
    }

    /**
     * Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm ppa projection
     * @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmPpaProjection nmPpaProjection = (NmPpaProjection) EntityCacheUtil.getResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmPpaProjectionImpl.class, primaryKey);

        if (nmPpaProjection == _nullNmPpaProjection) {
            return null;
        }

        if (nmPpaProjection == null) {
            Session session = null;

            try {
                session = openSession();

                nmPpaProjection = (NmPpaProjection) session.get(NmPpaProjectionImpl.class,
                        primaryKey);

                if (nmPpaProjection != null) {
                    cacheResult(nmPpaProjection);
                } else {
                    EntityCacheUtil.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmPpaProjectionImpl.class, primaryKey,
                        _nullNmPpaProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    NmPpaProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmPpaProjection;
    }

    /**
     * Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmPpaProjectionPK the primary key of the nm ppa projection
     * @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjection fetchByPrimaryKey(
        NmPpaProjectionPK nmPpaProjectionPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) nmPpaProjectionPK);
    }

    /**
     * Returns all the nm ppa projections.
     *
     * @return the nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm ppa projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm ppa projections
     * @param end the upper bound of the range of nm ppa projections (not inclusive)
     * @return the range of nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm ppa projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm ppa projections
     * @param end the upper bound of the range of nm ppa projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm ppa projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjection> findAll(int start, int end,
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

        List<NmPpaProjection> list = (List<NmPpaProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMPPAPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMPPAPROJECTION;

                if (pagination) {
                    sql = sql.concat(NmPpaProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmPpaProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmPpaProjection>(list);
                } else {
                    list = (List<NmPpaProjection>) QueryUtil.list(q,
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
     * Removes all the nm ppa projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmPpaProjection nmPpaProjection : findAll()) {
            remove(nmPpaProjection);
        }
    }

    /**
     * Returns the number of nm ppa projections.
     *
     * @return the number of nm ppa projections
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

                Query q = session.createQuery(_SQL_COUNT_NMPPAPROJECTION);

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
     * Initializes the nm ppa projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmPpaProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmPpaProjection>> listenersList = new ArrayList<ModelListener<NmPpaProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmPpaProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmPpaProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
