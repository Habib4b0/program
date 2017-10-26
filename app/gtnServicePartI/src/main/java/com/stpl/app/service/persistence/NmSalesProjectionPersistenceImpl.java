package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmSalesProjectionException;
import com.stpl.app.model.NmSalesProjection;
import com.stpl.app.model.impl.NmSalesProjectionImpl;
import com.stpl.app.model.impl.NmSalesProjectionModelImpl;
import com.stpl.app.service.persistence.NmSalesProjectionPersistence;

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
 * The persistence implementation for the nm sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionPersistence
 * @see NmSalesProjectionUtil
 * @generated
 */
public class NmSalesProjectionPersistenceImpl extends BasePersistenceImpl<NmSalesProjection>
    implements NmSalesProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmSalesProjectionUtil} to access the nm sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmSalesProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmSalesProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmSalesProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMSALESPROJECTION = "SELECT nmSalesProjection FROM NmSalesProjection nmSalesProjection";
    private static final String _SQL_COUNT_NMSALESPROJECTION = "SELECT COUNT(nmSalesProjection) FROM NmSalesProjection nmSalesProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmSalesProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmSalesProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmSalesProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentMethodology", "adjustmentBasis", "periodSid",
                "productGrowth", "projectionDetailsSid", "adjustmentValues",
                "adjustmentVariable", "accountGrowth", "projectionUnits",
                "adjustmentType", "projectionSales"
            });
    private static NmSalesProjection _nullNmSalesProjection = new NmSalesProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmSalesProjection> toCacheModel() {
                return _nullNmSalesProjectionCacheModel;
            }
        };

    private static CacheModel<NmSalesProjection> _nullNmSalesProjectionCacheModel =
        new CacheModel<NmSalesProjection>() {
            @Override
            public NmSalesProjection toEntityModel() {
                return _nullNmSalesProjection;
            }
        };

    public NmSalesProjectionPersistenceImpl() {
        setModelClass(NmSalesProjection.class);
    }

    /**
     * Caches the nm sales projection in the entity cache if it is enabled.
     *
     * @param nmSalesProjection the nm sales projection
     */
    @Override
    public void cacheResult(NmSalesProjection nmSalesProjection) {
        EntityCacheUtil.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey(),
            nmSalesProjection);

        nmSalesProjection.resetOriginalValues();
    }

    /**
     * Caches the nm sales projections in the entity cache if it is enabled.
     *
     * @param nmSalesProjections the nm sales projections
     */
    @Override
    public void cacheResult(List<NmSalesProjection> nmSalesProjections) {
        for (NmSalesProjection nmSalesProjection : nmSalesProjections) {
            if (EntityCacheUtil.getResult(
                        NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmSalesProjectionImpl.class,
                        nmSalesProjection.getPrimaryKey()) == null) {
                cacheResult(nmSalesProjection);
            } else {
                nmSalesProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm sales projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmSalesProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmSalesProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm sales projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmSalesProjection nmSalesProjection) {
        EntityCacheUtil.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmSalesProjection> nmSalesProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmSalesProjection nmSalesProjection : nmSalesProjections) {
            EntityCacheUtil.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
     *
     * @param nmSalesProjectionPK the primary key for the new nm sales projection
     * @return the new nm sales projection
     */
    @Override
    public NmSalesProjection create(NmSalesProjectionPK nmSalesProjectionPK) {
        NmSalesProjection nmSalesProjection = new NmSalesProjectionImpl();

        nmSalesProjection.setNew(true);
        nmSalesProjection.setPrimaryKey(nmSalesProjectionPK);

        return nmSalesProjection;
    }

    /**
     * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmSalesProjectionPK the primary key of the nm sales projection
     * @return the nm sales projection that was removed
     * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection remove(NmSalesProjectionPK nmSalesProjectionPK)
        throws NoSuchNmSalesProjectionException, SystemException {
        return remove((Serializable) nmSalesProjectionPK);
    }

    /**
     * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm sales projection
     * @return the nm sales projection that was removed
     * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection remove(Serializable primaryKey)
        throws NoSuchNmSalesProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmSalesProjection nmSalesProjection = (NmSalesProjection) session.get(NmSalesProjectionImpl.class,
                    primaryKey);

            if (nmSalesProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmSalesProjection);
        } catch (NoSuchNmSalesProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmSalesProjection removeImpl(NmSalesProjection nmSalesProjection)
        throws SystemException {
        nmSalesProjection = toUnwrappedModel(nmSalesProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmSalesProjection)) {
                nmSalesProjection = (NmSalesProjection) session.get(NmSalesProjectionImpl.class,
                        nmSalesProjection.getPrimaryKeyObj());
            }

            if (nmSalesProjection != null) {
                session.delete(nmSalesProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmSalesProjection != null) {
            clearCache(nmSalesProjection);
        }

        return nmSalesProjection;
    }

    @Override
    public NmSalesProjection updateImpl(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws SystemException {
        nmSalesProjection = toUnwrappedModel(nmSalesProjection);

        boolean isNew = nmSalesProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmSalesProjection.isNew()) {
                session.save(nmSalesProjection);

                nmSalesProjection.setNew(false);
            } else {
                session.merge(nmSalesProjection);
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

        EntityCacheUtil.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey(),
            nmSalesProjection);

        return nmSalesProjection;
    }

    protected NmSalesProjection toUnwrappedModel(
        NmSalesProjection nmSalesProjection) {
        if (nmSalesProjection instanceof NmSalesProjectionImpl) {
            return nmSalesProjection;
        }

        NmSalesProjectionImpl nmSalesProjectionImpl = new NmSalesProjectionImpl();

        nmSalesProjectionImpl.setNew(nmSalesProjection.isNew());
        nmSalesProjectionImpl.setPrimaryKey(nmSalesProjection.getPrimaryKey());

        nmSalesProjectionImpl.setAdjustmentMethodology(nmSalesProjection.getAdjustmentMethodology());
        nmSalesProjectionImpl.setAdjustmentBasis(nmSalesProjection.getAdjustmentBasis());
        nmSalesProjectionImpl.setPeriodSid(nmSalesProjection.getPeriodSid());
        nmSalesProjectionImpl.setProductGrowth(nmSalesProjection.getProductGrowth());
        nmSalesProjectionImpl.setProjectionDetailsSid(nmSalesProjection.getProjectionDetailsSid());
        nmSalesProjectionImpl.setAdjustmentValues(nmSalesProjection.getAdjustmentValues());
        nmSalesProjectionImpl.setAdjustmentVariable(nmSalesProjection.isAdjustmentVariable());
        nmSalesProjectionImpl.setAccountGrowth(nmSalesProjection.getAccountGrowth());
        nmSalesProjectionImpl.setProjectionUnits(nmSalesProjection.getProjectionUnits());
        nmSalesProjectionImpl.setAdjustmentType(nmSalesProjection.getAdjustmentType());
        nmSalesProjectionImpl.setProjectionSales(nmSalesProjection.getProjectionSales());

        return nmSalesProjectionImpl;
    }

    /**
     * Returns the nm sales projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm sales projection
     * @return the nm sales projection
     * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmSalesProjectionException, SystemException {
        NmSalesProjection nmSalesProjection = fetchByPrimaryKey(primaryKey);

        if (nmSalesProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmSalesProjection;
    }

    /**
     * Returns the nm sales projection with the primary key or throws a {@link com.stpl.app.NoSuchNmSalesProjectionException} if it could not be found.
     *
     * @param nmSalesProjectionPK the primary key of the nm sales projection
     * @return the nm sales projection
     * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection findByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws NoSuchNmSalesProjectionException, SystemException {
        return findByPrimaryKey((Serializable) nmSalesProjectionPK);
    }

    /**
     * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm sales projection
     * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmSalesProjection nmSalesProjection = (NmSalesProjection) EntityCacheUtil.getResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmSalesProjectionImpl.class, primaryKey);

        if (nmSalesProjection == _nullNmSalesProjection) {
            return null;
        }

        if (nmSalesProjection == null) {
            Session session = null;

            try {
                session = openSession();

                nmSalesProjection = (NmSalesProjection) session.get(NmSalesProjectionImpl.class,
                        primaryKey);

                if (nmSalesProjection != null) {
                    cacheResult(nmSalesProjection);
                } else {
                    EntityCacheUtil.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmSalesProjectionImpl.class, primaryKey,
                        _nullNmSalesProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    NmSalesProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmSalesProjection;
    }

    /**
     * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmSalesProjectionPK the primary key of the nm sales projection
     * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmSalesProjection fetchByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) nmSalesProjectionPK);
    }

    /**
     * Returns all the nm sales projections.
     *
     * @return the nm sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmSalesProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm sales projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm sales projections
     * @param end the upper bound of the range of nm sales projections (not inclusive)
     * @return the range of nm sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmSalesProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm sales projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm sales projections
     * @param end the upper bound of the range of nm sales projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmSalesProjection> findAll(int start, int end,
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

        List<NmSalesProjection> list = (List<NmSalesProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMSALESPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMSALESPROJECTION;

                if (pagination) {
                    sql = sql.concat(NmSalesProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmSalesProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmSalesProjection>(list);
                } else {
                    list = (List<NmSalesProjection>) QueryUtil.list(q,
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
     * Removes all the nm sales projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmSalesProjection nmSalesProjection : findAll()) {
            remove(nmSalesProjection);
        }
    }

    /**
     * Returns the number of nm sales projections.
     *
     * @return the number of nm sales projections
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

                Query q = session.createQuery(_SQL_COUNT_NMSALESPROJECTION);

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
     * Initializes the nm sales projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmSalesProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmSalesProjection>> listenersList = new ArrayList<ModelListener<NmSalesProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmSalesProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmSalesProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
