package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmDiscountProjectionException;
import com.stpl.app.model.NmDiscountProjection;
import com.stpl.app.model.impl.NmDiscountProjectionImpl;
import com.stpl.app.model.impl.NmDiscountProjectionModelImpl;
import com.stpl.app.service.persistence.NmDiscountProjectionPersistence;

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
 * The persistence implementation for the nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjectionPersistence
 * @see NmDiscountProjectionUtil
 * @generated
 */
public class NmDiscountProjectionPersistenceImpl extends BasePersistenceImpl<NmDiscountProjection>
    implements NmDiscountProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmDiscountProjectionUtil} to access the nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmDiscountProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmDiscountProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
            NmDiscountProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMDISCOUNTPROJECTION = "SELECT nmDiscountProjection FROM NmDiscountProjection nmDiscountProjection";
    private static final String _SQL_COUNT_NMDISCOUNTPROJECTION = "SELECT COUNT(nmDiscountProjection) FROM NmDiscountProjection nmDiscountProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmDiscountProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmDiscountProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmDiscountProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentMethodology", "adjustmentBasis", "periodSid",
                "projectionRate", "projectionDetailsSid", "adjustmentVariable",
                "adjustmentValue", "adjustmentType", "projectionSales",
                "discountRate"
            });
    private static NmDiscountProjection _nullNmDiscountProjection = new NmDiscountProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmDiscountProjection> toCacheModel() {
                return _nullNmDiscountProjectionCacheModel;
            }
        };

    private static CacheModel<NmDiscountProjection> _nullNmDiscountProjectionCacheModel =
        new CacheModel<NmDiscountProjection>() {
            @Override
            public NmDiscountProjection toEntityModel() {
                return _nullNmDiscountProjection;
            }
        };

    public NmDiscountProjectionPersistenceImpl() {
        setModelClass(NmDiscountProjection.class);
    }

    /**
     * Caches the nm discount projection in the entity cache if it is enabled.
     *
     * @param nmDiscountProjection the nm discount projection
     */
    @Override
    public void cacheResult(NmDiscountProjection nmDiscountProjection) {
        EntityCacheUtil.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionImpl.class,
            nmDiscountProjection.getPrimaryKey(), nmDiscountProjection);

        nmDiscountProjection.resetOriginalValues();
    }

    /**
     * Caches the nm discount projections in the entity cache if it is enabled.
     *
     * @param nmDiscountProjections the nm discount projections
     */
    @Override
    public void cacheResult(List<NmDiscountProjection> nmDiscountProjections) {
        for (NmDiscountProjection nmDiscountProjection : nmDiscountProjections) {
            if (EntityCacheUtil.getResult(
                        NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmDiscountProjectionImpl.class,
                        nmDiscountProjection.getPrimaryKey()) == null) {
                cacheResult(nmDiscountProjection);
            } else {
                nmDiscountProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm discount projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmDiscountProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmDiscountProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm discount projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmDiscountProjection nmDiscountProjection) {
        EntityCacheUtil.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionImpl.class, nmDiscountProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmDiscountProjection> nmDiscountProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmDiscountProjection nmDiscountProjection : nmDiscountProjections) {
            EntityCacheUtil.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmDiscountProjectionImpl.class,
                nmDiscountProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
     *
     * @param nmDiscountProjectionPK the primary key for the new nm discount projection
     * @return the new nm discount projection
     */
    @Override
    public NmDiscountProjection create(
        NmDiscountProjectionPK nmDiscountProjectionPK) {
        NmDiscountProjection nmDiscountProjection = new NmDiscountProjectionImpl();

        nmDiscountProjection.setNew(true);
        nmDiscountProjection.setPrimaryKey(nmDiscountProjectionPK);

        return nmDiscountProjection;
    }

    /**
     * Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmDiscountProjectionPK the primary key of the nm discount projection
     * @return the nm discount projection that was removed
     * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection remove(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws NoSuchNmDiscountProjectionException, SystemException {
        return remove((Serializable) nmDiscountProjectionPK);
    }

    /**
     * Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm discount projection
     * @return the nm discount projection that was removed
     * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection remove(Serializable primaryKey)
        throws NoSuchNmDiscountProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmDiscountProjection nmDiscountProjection = (NmDiscountProjection) session.get(NmDiscountProjectionImpl.class,
                    primaryKey);

            if (nmDiscountProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmDiscountProjection);
        } catch (NoSuchNmDiscountProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmDiscountProjection removeImpl(
        NmDiscountProjection nmDiscountProjection) throws SystemException {
        nmDiscountProjection = toUnwrappedModel(nmDiscountProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmDiscountProjection)) {
                nmDiscountProjection = (NmDiscountProjection) session.get(NmDiscountProjectionImpl.class,
                        nmDiscountProjection.getPrimaryKeyObj());
            }

            if (nmDiscountProjection != null) {
                session.delete(nmDiscountProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmDiscountProjection != null) {
            clearCache(nmDiscountProjection);
        }

        return nmDiscountProjection;
    }

    @Override
    public NmDiscountProjection updateImpl(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection)
        throws SystemException {
        nmDiscountProjection = toUnwrappedModel(nmDiscountProjection);

        boolean isNew = nmDiscountProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmDiscountProjection.isNew()) {
                session.save(nmDiscountProjection);

                nmDiscountProjection.setNew(false);
            } else {
                session.merge(nmDiscountProjection);
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

        EntityCacheUtil.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjectionImpl.class,
            nmDiscountProjection.getPrimaryKey(), nmDiscountProjection);

        return nmDiscountProjection;
    }

    protected NmDiscountProjection toUnwrappedModel(
        NmDiscountProjection nmDiscountProjection) {
        if (nmDiscountProjection instanceof NmDiscountProjectionImpl) {
            return nmDiscountProjection;
        }

        NmDiscountProjectionImpl nmDiscountProjectionImpl = new NmDiscountProjectionImpl();

        nmDiscountProjectionImpl.setNew(nmDiscountProjection.isNew());
        nmDiscountProjectionImpl.setPrimaryKey(nmDiscountProjection.getPrimaryKey());

        nmDiscountProjectionImpl.setAdjustmentMethodology(nmDiscountProjection.getAdjustmentMethodology());
        nmDiscountProjectionImpl.setAdjustmentBasis(nmDiscountProjection.getAdjustmentBasis());
        nmDiscountProjectionImpl.setPeriodSid(nmDiscountProjection.getPeriodSid());
        nmDiscountProjectionImpl.setProjectionRate(nmDiscountProjection.getProjectionRate());
        nmDiscountProjectionImpl.setProjectionDetailsSid(nmDiscountProjection.getProjectionDetailsSid());
        nmDiscountProjectionImpl.setAdjustmentVariable(nmDiscountProjection.isAdjustmentVariable());
        nmDiscountProjectionImpl.setAdjustmentValue(nmDiscountProjection.getAdjustmentValue());
        nmDiscountProjectionImpl.setAdjustmentType(nmDiscountProjection.getAdjustmentType());
        nmDiscountProjectionImpl.setProjectionSales(nmDiscountProjection.getProjectionSales());
        nmDiscountProjectionImpl.setDiscountRate(nmDiscountProjection.getDiscountRate());

        return nmDiscountProjectionImpl;
    }

    /**
     * Returns the nm discount projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm discount projection
     * @return the nm discount projection
     * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmDiscountProjectionException, SystemException {
        NmDiscountProjection nmDiscountProjection = fetchByPrimaryKey(primaryKey);

        if (nmDiscountProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmDiscountProjection;
    }

    /**
     * Returns the nm discount projection with the primary key or throws a {@link com.stpl.app.NoSuchNmDiscountProjectionException} if it could not be found.
     *
     * @param nmDiscountProjectionPK the primary key of the nm discount projection
     * @return the nm discount projection
     * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection findByPrimaryKey(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws NoSuchNmDiscountProjectionException, SystemException {
        return findByPrimaryKey((Serializable) nmDiscountProjectionPK);
    }

    /**
     * Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm discount projection
     * @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmDiscountProjection nmDiscountProjection = (NmDiscountProjection) EntityCacheUtil.getResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                NmDiscountProjectionImpl.class, primaryKey);

        if (nmDiscountProjection == _nullNmDiscountProjection) {
            return null;
        }

        if (nmDiscountProjection == null) {
            Session session = null;

            try {
                session = openSession();

                nmDiscountProjection = (NmDiscountProjection) session.get(NmDiscountProjectionImpl.class,
                        primaryKey);

                if (nmDiscountProjection != null) {
                    cacheResult(nmDiscountProjection);
                } else {
                    EntityCacheUtil.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        NmDiscountProjectionImpl.class, primaryKey,
                        _nullNmDiscountProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    NmDiscountProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmDiscountProjection;
    }

    /**
     * Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmDiscountProjectionPK the primary key of the nm discount projection
     * @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjection fetchByPrimaryKey(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) nmDiscountProjectionPK);
    }

    /**
     * Returns all the nm discount projections.
     *
     * @return the nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm discount projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm discount projections
     * @param end the upper bound of the range of nm discount projections (not inclusive)
     * @return the range of nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm discount projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm discount projections
     * @param end the upper bound of the range of nm discount projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm discount projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjection> findAll(int start, int end,
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

        List<NmDiscountProjection> list = (List<NmDiscountProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMDISCOUNTPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMDISCOUNTPROJECTION;

                if (pagination) {
                    sql = sql.concat(NmDiscountProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmDiscountProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmDiscountProjection>(list);
                } else {
                    list = (List<NmDiscountProjection>) QueryUtil.list(q,
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
     * Removes all the nm discount projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmDiscountProjection nmDiscountProjection : findAll()) {
            remove(nmDiscountProjection);
        }
    }

    /**
     * Returns the number of nm discount projections.
     *
     * @return the number of nm discount projections
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

                Query q = session.createQuery(_SQL_COUNT_NMDISCOUNTPROJECTION);

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
     * Initializes the nm discount projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmDiscountProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmDiscountProjection>> listenersList = new ArrayList<ModelListener<NmDiscountProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmDiscountProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmDiscountProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
