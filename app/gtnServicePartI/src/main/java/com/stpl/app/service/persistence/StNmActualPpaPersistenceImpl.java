package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmActualPpaException;
import com.stpl.app.model.StNmActualPpa;
import com.stpl.app.model.impl.StNmActualPpaImpl;
import com.stpl.app.model.impl.StNmActualPpaModelImpl;
import com.stpl.app.service.persistence.StNmActualPpaPersistence;

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
 * The persistence implementation for the st nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualPpaPersistence
 * @see StNmActualPpaUtil
 * @generated
 */
public class StNmActualPpaPersistenceImpl extends BasePersistenceImpl<StNmActualPpa>
    implements StNmActualPpaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmActualPpaUtil} to access the st nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmActualPpaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaModelImpl.FINDER_CACHE_ENABLED,
            StNmActualPpaImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaModelImpl.FINDER_CACHE_ENABLED,
            StNmActualPpaImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMACTUALPPA = "SELECT stNmActualPpa FROM StNmActualPpa stNmActualPpa";
    private static final String _SQL_COUNT_STNMACTUALPPA = "SELECT COUNT(stNmActualPpa) FROM StNmActualPpa stNmActualPpa";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmActualPpa.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmActualPpa exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmActualPpaPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "actualRate", "periodSid",
                "actualProjDiscountDollar", "actualProjectionSales",
                "projectionDetailsSid", "userId", "actualProjectionRate",
                "sessionId", "actualProjDiscountUnits", "actualDiscountDollar",
                "actualDiscountUnits", "actualSales"
            });
    private static StNmActualPpa _nullStNmActualPpa = new StNmActualPpaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmActualPpa> toCacheModel() {
                return _nullStNmActualPpaCacheModel;
            }
        };

    private static CacheModel<StNmActualPpa> _nullStNmActualPpaCacheModel = new CacheModel<StNmActualPpa>() {
            @Override
            public StNmActualPpa toEntityModel() {
                return _nullStNmActualPpa;
            }
        };

    public StNmActualPpaPersistenceImpl() {
        setModelClass(StNmActualPpa.class);
    }

    /**
     * Caches the st nm actual ppa in the entity cache if it is enabled.
     *
     * @param stNmActualPpa the st nm actual ppa
     */
    @Override
    public void cacheResult(StNmActualPpa stNmActualPpa) {
        EntityCacheUtil.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey(),
            stNmActualPpa);

        stNmActualPpa.resetOriginalValues();
    }

    /**
     * Caches the st nm actual ppas in the entity cache if it is enabled.
     *
     * @param stNmActualPpas the st nm actual ppas
     */
    @Override
    public void cacheResult(List<StNmActualPpa> stNmActualPpas) {
        for (StNmActualPpa stNmActualPpa : stNmActualPpas) {
            if (EntityCacheUtil.getResult(
                        StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                        StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey()) == null) {
                cacheResult(stNmActualPpa);
            } else {
                stNmActualPpa.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm actual ppas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmActualPpaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmActualPpaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm actual ppa.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmActualPpa stNmActualPpa) {
        EntityCacheUtil.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmActualPpa> stNmActualPpas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmActualPpa stNmActualPpa : stNmActualPpas) {
            EntityCacheUtil.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
     *
     * @param stNmActualPpaPK the primary key for the new st nm actual ppa
     * @return the new st nm actual ppa
     */
    @Override
    public StNmActualPpa create(StNmActualPpaPK stNmActualPpaPK) {
        StNmActualPpa stNmActualPpa = new StNmActualPpaImpl();

        stNmActualPpa.setNew(true);
        stNmActualPpa.setPrimaryKey(stNmActualPpaPK);

        return stNmActualPpa;
    }

    /**
     * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmActualPpaPK the primary key of the st nm actual ppa
     * @return the st nm actual ppa that was removed
     * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa remove(StNmActualPpaPK stNmActualPpaPK)
        throws NoSuchStNmActualPpaException, SystemException {
        return remove((Serializable) stNmActualPpaPK);
    }

    /**
     * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm actual ppa
     * @return the st nm actual ppa that was removed
     * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa remove(Serializable primaryKey)
        throws NoSuchStNmActualPpaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmActualPpa stNmActualPpa = (StNmActualPpa) session.get(StNmActualPpaImpl.class,
                    primaryKey);

            if (stNmActualPpa == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmActualPpa);
        } catch (NoSuchStNmActualPpaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmActualPpa removeImpl(StNmActualPpa stNmActualPpa)
        throws SystemException {
        stNmActualPpa = toUnwrappedModel(stNmActualPpa);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmActualPpa)) {
                stNmActualPpa = (StNmActualPpa) session.get(StNmActualPpaImpl.class,
                        stNmActualPpa.getPrimaryKeyObj());
            }

            if (stNmActualPpa != null) {
                session.delete(stNmActualPpa);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmActualPpa != null) {
            clearCache(stNmActualPpa);
        }

        return stNmActualPpa;
    }

    @Override
    public StNmActualPpa updateImpl(
        com.stpl.app.model.StNmActualPpa stNmActualPpa)
        throws SystemException {
        stNmActualPpa = toUnwrappedModel(stNmActualPpa);

        boolean isNew = stNmActualPpa.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmActualPpa.isNew()) {
                session.save(stNmActualPpa);

                stNmActualPpa.setNew(false);
            } else {
                session.merge(stNmActualPpa);
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

        EntityCacheUtil.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey(),
            stNmActualPpa);

        return stNmActualPpa;
    }

    protected StNmActualPpa toUnwrappedModel(StNmActualPpa stNmActualPpa) {
        if (stNmActualPpa instanceof StNmActualPpaImpl) {
            return stNmActualPpa;
        }

        StNmActualPpaImpl stNmActualPpaImpl = new StNmActualPpaImpl();

        stNmActualPpaImpl.setNew(stNmActualPpa.isNew());
        stNmActualPpaImpl.setPrimaryKey(stNmActualPpa.getPrimaryKey());

        stNmActualPpaImpl.setLastModifiedDate(stNmActualPpa.getLastModifiedDate());
        stNmActualPpaImpl.setActualRate(stNmActualPpa.getActualRate());
        stNmActualPpaImpl.setPeriodSid(stNmActualPpa.getPeriodSid());
        stNmActualPpaImpl.setActualProjDiscountDollar(stNmActualPpa.getActualProjDiscountDollar());
        stNmActualPpaImpl.setActualProjectionSales(stNmActualPpa.getActualProjectionSales());
        stNmActualPpaImpl.setProjectionDetailsSid(stNmActualPpa.getProjectionDetailsSid());
        stNmActualPpaImpl.setUserId(stNmActualPpa.getUserId());
        stNmActualPpaImpl.setActualProjectionRate(stNmActualPpa.getActualProjectionRate());
        stNmActualPpaImpl.setSessionId(stNmActualPpa.getSessionId());
        stNmActualPpaImpl.setActualProjDiscountUnits(stNmActualPpa.getActualProjDiscountUnits());
        stNmActualPpaImpl.setActualDiscountDollar(stNmActualPpa.getActualDiscountDollar());
        stNmActualPpaImpl.setActualDiscountUnits(stNmActualPpa.getActualDiscountUnits());
        stNmActualPpaImpl.setActualSales(stNmActualPpa.getActualSales());

        return stNmActualPpaImpl;
    }

    /**
     * Returns the st nm actual ppa with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm actual ppa
     * @return the st nm actual ppa
     * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmActualPpaException, SystemException {
        StNmActualPpa stNmActualPpa = fetchByPrimaryKey(primaryKey);

        if (stNmActualPpa == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmActualPpa;
    }

    /**
     * Returns the st nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchStNmActualPpaException} if it could not be found.
     *
     * @param stNmActualPpaPK the primary key of the st nm actual ppa
     * @return the st nm actual ppa
     * @throws com.stpl.app.NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa findByPrimaryKey(StNmActualPpaPK stNmActualPpaPK)
        throws NoSuchStNmActualPpaException, SystemException {
        return findByPrimaryKey((Serializable) stNmActualPpaPK);
    }

    /**
     * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm actual ppa
     * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmActualPpa stNmActualPpa = (StNmActualPpa) EntityCacheUtil.getResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                StNmActualPpaImpl.class, primaryKey);

        if (stNmActualPpa == _nullStNmActualPpa) {
            return null;
        }

        if (stNmActualPpa == null) {
            Session session = null;

            try {
                session = openSession();

                stNmActualPpa = (StNmActualPpa) session.get(StNmActualPpaImpl.class,
                        primaryKey);

                if (stNmActualPpa != null) {
                    cacheResult(stNmActualPpa);
                } else {
                    EntityCacheUtil.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                        StNmActualPpaImpl.class, primaryKey, _nullStNmActualPpa);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                    StNmActualPpaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmActualPpa;
    }

    /**
     * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmActualPpaPK the primary key of the st nm actual ppa
     * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmActualPpa fetchByPrimaryKey(StNmActualPpaPK stNmActualPpaPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmActualPpaPK);
    }

    /**
     * Returns all the st nm actual ppas.
     *
     * @return the st nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualPpa> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm actual ppas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm actual ppas
     * @param end the upper bound of the range of st nm actual ppas (not inclusive)
     * @return the range of st nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualPpa> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm actual ppas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm actual ppas
     * @param end the upper bound of the range of st nm actual ppas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmActualPpa> findAll(int start, int end,
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

        List<StNmActualPpa> list = (List<StNmActualPpa>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMACTUALPPA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMACTUALPPA;

                if (pagination) {
                    sql = sql.concat(StNmActualPpaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmActualPpa>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmActualPpa>(list);
                } else {
                    list = (List<StNmActualPpa>) QueryUtil.list(q,
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
     * Removes all the st nm actual ppas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmActualPpa stNmActualPpa : findAll()) {
            remove(stNmActualPpa);
        }
    }

    /**
     * Returns the number of st nm actual ppas.
     *
     * @return the number of st nm actual ppas
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

                Query q = session.createQuery(_SQL_COUNT_STNMACTUALPPA);

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
     * Initializes the st nm actual ppa persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmActualPpa")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmActualPpa>> listenersList = new ArrayList<ModelListener<StNmActualPpa>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmActualPpa>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmActualPpaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
