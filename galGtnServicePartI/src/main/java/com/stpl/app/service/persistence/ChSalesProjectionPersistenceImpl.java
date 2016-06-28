package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChSalesProjectionException;
import com.stpl.app.model.ChSalesProjection;
import com.stpl.app.model.impl.ChSalesProjectionImpl;
import com.stpl.app.model.impl.ChSalesProjectionModelImpl;
import com.stpl.app.service.persistence.ChSalesProjectionPersistence;

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
 * The persistence implementation for the ch sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionPersistence
 * @see ChSalesProjectionUtil
 * @generated
 */
public class ChSalesProjectionPersistenceImpl extends BasePersistenceImpl<ChSalesProjection>
    implements ChSalesProjectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChSalesProjectionUtil} to access the ch sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChSalesProjectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
            ChSalesProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
            ChSalesProjectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHSALESPROJECTION = "SELECT chSalesProjection FROM ChSalesProjection chSalesProjection";
    private static final String _SQL_COUNT_CHSALESPROJECTION = "SELECT COUNT(chSalesProjection) FROM ChSalesProjection chSalesProjection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chSalesProjection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChSalesProjection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChSalesProjectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "contractUnits", "perOfBusiness", "periodSid", "contractSales",
                "projectionDetailsSid", "gtsSales"
            });
    private static ChSalesProjection _nullChSalesProjection = new ChSalesProjectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChSalesProjection> toCacheModel() {
                return _nullChSalesProjectionCacheModel;
            }
        };

    private static CacheModel<ChSalesProjection> _nullChSalesProjectionCacheModel =
        new CacheModel<ChSalesProjection>() {
            @Override
            public ChSalesProjection toEntityModel() {
                return _nullChSalesProjection;
            }
        };

    public ChSalesProjectionPersistenceImpl() {
        setModelClass(ChSalesProjection.class);
    }

    /**
     * Caches the ch sales projection in the entity cache if it is enabled.
     *
     * @param chSalesProjection the ch sales projection
     */
    @Override
    public void cacheResult(ChSalesProjection chSalesProjection) {
        EntityCacheUtil.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey(),
            chSalesProjection);

        chSalesProjection.resetOriginalValues();
    }

    /**
     * Caches the ch sales projections in the entity cache if it is enabled.
     *
     * @param chSalesProjections the ch sales projections
     */
    @Override
    public void cacheResult(List<ChSalesProjection> chSalesProjections) {
        for (ChSalesProjection chSalesProjection : chSalesProjections) {
            if (EntityCacheUtil.getResult(
                        ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        ChSalesProjectionImpl.class,
                        chSalesProjection.getPrimaryKey()) == null) {
                cacheResult(chSalesProjection);
            } else {
                chSalesProjection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch sales projections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChSalesProjectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChSalesProjectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch sales projection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChSalesProjection chSalesProjection) {
        EntityCacheUtil.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChSalesProjection> chSalesProjections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChSalesProjection chSalesProjection : chSalesProjections) {
            EntityCacheUtil.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
     *
     * @param chSalesProjectionPK the primary key for the new ch sales projection
     * @return the new ch sales projection
     */
    @Override
    public ChSalesProjection create(ChSalesProjectionPK chSalesProjectionPK) {
        ChSalesProjection chSalesProjection = new ChSalesProjectionImpl();

        chSalesProjection.setNew(true);
        chSalesProjection.setPrimaryKey(chSalesProjectionPK);

        return chSalesProjection;
    }

    /**
     * Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chSalesProjectionPK the primary key of the ch sales projection
     * @return the ch sales projection that was removed
     * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection remove(ChSalesProjectionPK chSalesProjectionPK)
        throws NoSuchChSalesProjectionException, SystemException {
        return remove((Serializable) chSalesProjectionPK);
    }

    /**
     * Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch sales projection
     * @return the ch sales projection that was removed
     * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection remove(Serializable primaryKey)
        throws NoSuchChSalesProjectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChSalesProjection chSalesProjection = (ChSalesProjection) session.get(ChSalesProjectionImpl.class,
                    primaryKey);

            if (chSalesProjection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chSalesProjection);
        } catch (NoSuchChSalesProjectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChSalesProjection removeImpl(ChSalesProjection chSalesProjection)
        throws SystemException {
        chSalesProjection = toUnwrappedModel(chSalesProjection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chSalesProjection)) {
                chSalesProjection = (ChSalesProjection) session.get(ChSalesProjectionImpl.class,
                        chSalesProjection.getPrimaryKeyObj());
            }

            if (chSalesProjection != null) {
                session.delete(chSalesProjection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chSalesProjection != null) {
            clearCache(chSalesProjection);
        }

        return chSalesProjection;
    }

    @Override
    public ChSalesProjection updateImpl(
        com.stpl.app.model.ChSalesProjection chSalesProjection)
        throws SystemException {
        chSalesProjection = toUnwrappedModel(chSalesProjection);

        boolean isNew = chSalesProjection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chSalesProjection.isNew()) {
                session.save(chSalesProjection);

                chSalesProjection.setNew(false);
            } else {
                session.merge(chSalesProjection);
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

        EntityCacheUtil.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey(),
            chSalesProjection);

        return chSalesProjection;
    }

    protected ChSalesProjection toUnwrappedModel(
        ChSalesProjection chSalesProjection) {
        if (chSalesProjection instanceof ChSalesProjectionImpl) {
            return chSalesProjection;
        }

        ChSalesProjectionImpl chSalesProjectionImpl = new ChSalesProjectionImpl();

        chSalesProjectionImpl.setNew(chSalesProjection.isNew());
        chSalesProjectionImpl.setPrimaryKey(chSalesProjection.getPrimaryKey());

        chSalesProjectionImpl.setContractUnits(chSalesProjection.getContractUnits());
        chSalesProjectionImpl.setPerOfBusiness(chSalesProjection.getPerOfBusiness());
        chSalesProjectionImpl.setPeriodSid(chSalesProjection.getPeriodSid());
        chSalesProjectionImpl.setContractSales(chSalesProjection.getContractSales());
        chSalesProjectionImpl.setProjectionDetailsSid(chSalesProjection.getProjectionDetailsSid());
        chSalesProjectionImpl.setGtsSales(chSalesProjection.getGtsSales());

        return chSalesProjectionImpl;
    }

    /**
     * Returns the ch sales projection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch sales projection
     * @return the ch sales projection
     * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChSalesProjectionException, SystemException {
        ChSalesProjection chSalesProjection = fetchByPrimaryKey(primaryKey);

        if (chSalesProjection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chSalesProjection;
    }

    /**
     * Returns the ch sales projection with the primary key or throws a {@link com.stpl.app.NoSuchChSalesProjectionException} if it could not be found.
     *
     * @param chSalesProjectionPK the primary key of the ch sales projection
     * @return the ch sales projection
     * @throws com.stpl.app.NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection findByPrimaryKey(
        ChSalesProjectionPK chSalesProjectionPK)
        throws NoSuchChSalesProjectionException, SystemException {
        return findByPrimaryKey((Serializable) chSalesProjectionPK);
    }

    /**
     * Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch sales projection
     * @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChSalesProjection chSalesProjection = (ChSalesProjection) EntityCacheUtil.getResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                ChSalesProjectionImpl.class, primaryKey);

        if (chSalesProjection == _nullChSalesProjection) {
            return null;
        }

        if (chSalesProjection == null) {
            Session session = null;

            try {
                session = openSession();

                chSalesProjection = (ChSalesProjection) session.get(ChSalesProjectionImpl.class,
                        primaryKey);

                if (chSalesProjection != null) {
                    cacheResult(chSalesProjection);
                } else {
                    EntityCacheUtil.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                        ChSalesProjectionImpl.class, primaryKey,
                        _nullChSalesProjection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
                    ChSalesProjectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chSalesProjection;
    }

    /**
     * Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chSalesProjectionPK the primary key of the ch sales projection
     * @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjection fetchByPrimaryKey(
        ChSalesProjectionPK chSalesProjectionPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) chSalesProjectionPK);
    }

    /**
     * Returns all the ch sales projections.
     *
     * @return the ch sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch sales projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch sales projections
     * @param end the upper bound of the range of ch sales projections (not inclusive)
     * @return the range of ch sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch sales projections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch sales projections
     * @param end the upper bound of the range of ch sales projections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch sales projections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjection> findAll(int start, int end,
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

        List<ChSalesProjection> list = (List<ChSalesProjection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHSALESPROJECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHSALESPROJECTION;

                if (pagination) {
                    sql = sql.concat(ChSalesProjectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChSalesProjection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChSalesProjection>(list);
                } else {
                    list = (List<ChSalesProjection>) QueryUtil.list(q,
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
     * Removes all the ch sales projections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChSalesProjection chSalesProjection : findAll()) {
            remove(chSalesProjection);
        }
    }

    /**
     * Returns the number of ch sales projections.
     *
     * @return the number of ch sales projections
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

                Query q = session.createQuery(_SQL_COUNT_CHSALESPROJECTION);

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
     * Initializes the ch sales projection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChSalesProjection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChSalesProjection>> listenersList = new ArrayList<ModelListener<ChSalesProjection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChSalesProjection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChSalesProjectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
