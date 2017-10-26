package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchForecastingViewMasterException;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.impl.ForecastingViewMasterImpl;
import com.stpl.app.model.impl.ForecastingViewMasterModelImpl;
import com.stpl.app.service.persistence.ForecastingViewMasterPersistence;

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
 * The persistence implementation for the forecasting view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingViewMasterPersistence
 * @see ForecastingViewMasterUtil
 * @generated
 */
public class ForecastingViewMasterPersistenceImpl extends BasePersistenceImpl<ForecastingViewMaster>
    implements ForecastingViewMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ForecastingViewMasterUtil} to access the forecasting view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ForecastingViewMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED,
            ForecastingViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FORECASTINGVIEWMASTER = "SELECT forecastingViewMaster FROM ForecastingViewMaster forecastingViewMaster";
    private static final String _SQL_COUNT_FORECASTINGVIEWMASTER = "SELECT COUNT(forecastingViewMaster) FROM ForecastingViewMaster forecastingViewMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "forecastingViewMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastingViewMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ForecastingViewMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "viewType", "viewId", "projectionId",
                "modifiedBy", "modifiedDate", "viewName"
            });
    private static ForecastingViewMaster _nullForecastingViewMaster = new ForecastingViewMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ForecastingViewMaster> toCacheModel() {
                return _nullForecastingViewMasterCacheModel;
            }
        };

    private static CacheModel<ForecastingViewMaster> _nullForecastingViewMasterCacheModel =
        new CacheModel<ForecastingViewMaster>() {
            @Override
            public ForecastingViewMaster toEntityModel() {
                return _nullForecastingViewMaster;
            }
        };

    public ForecastingViewMasterPersistenceImpl() {
        setModelClass(ForecastingViewMaster.class);
    }

    /**
     * Caches the forecasting view master in the entity cache if it is enabled.
     *
     * @param forecastingViewMaster the forecasting view master
     */
    @Override
    public void cacheResult(ForecastingViewMaster forecastingViewMaster) {
        EntityCacheUtil.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterImpl.class,
            forecastingViewMaster.getPrimaryKey(), forecastingViewMaster);

        forecastingViewMaster.resetOriginalValues();
    }

    /**
     * Caches the forecasting view masters in the entity cache if it is enabled.
     *
     * @param forecastingViewMasters the forecasting view masters
     */
    @Override
    public void cacheResult(List<ForecastingViewMaster> forecastingViewMasters) {
        for (ForecastingViewMaster forecastingViewMaster : forecastingViewMasters) {
            if (EntityCacheUtil.getResult(
                        ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingViewMasterImpl.class,
                        forecastingViewMaster.getPrimaryKey()) == null) {
                cacheResult(forecastingViewMaster);
            } else {
                forecastingViewMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all forecasting view masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ForecastingViewMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ForecastingViewMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the forecasting view master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ForecastingViewMaster forecastingViewMaster) {
        EntityCacheUtil.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterImpl.class,
            forecastingViewMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ForecastingViewMaster> forecastingViewMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ForecastingViewMaster forecastingViewMaster : forecastingViewMasters) {
            EntityCacheUtil.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingViewMasterImpl.class,
                forecastingViewMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
     *
     * @param viewId the primary key for the new forecasting view master
     * @return the new forecasting view master
     */
    @Override
    public ForecastingViewMaster create(int viewId) {
        ForecastingViewMaster forecastingViewMaster = new ForecastingViewMasterImpl();

        forecastingViewMaster.setNew(true);
        forecastingViewMaster.setPrimaryKey(viewId);

        return forecastingViewMaster;
    }

    /**
     * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param viewId the primary key of the forecasting view master
     * @return the forecasting view master that was removed
     * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster remove(int viewId)
        throws NoSuchForecastingViewMasterException, SystemException {
        return remove((Serializable) viewId);
    }

    /**
     * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the forecasting view master
     * @return the forecasting view master that was removed
     * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster remove(Serializable primaryKey)
        throws NoSuchForecastingViewMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ForecastingViewMaster forecastingViewMaster = (ForecastingViewMaster) session.get(ForecastingViewMasterImpl.class,
                    primaryKey);

            if (forecastingViewMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchForecastingViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(forecastingViewMaster);
        } catch (NoSuchForecastingViewMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ForecastingViewMaster removeImpl(
        ForecastingViewMaster forecastingViewMaster) throws SystemException {
        forecastingViewMaster = toUnwrappedModel(forecastingViewMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(forecastingViewMaster)) {
                forecastingViewMaster = (ForecastingViewMaster) session.get(ForecastingViewMasterImpl.class,
                        forecastingViewMaster.getPrimaryKeyObj());
            }

            if (forecastingViewMaster != null) {
                session.delete(forecastingViewMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (forecastingViewMaster != null) {
            clearCache(forecastingViewMaster);
        }

        return forecastingViewMaster;
    }

    @Override
    public ForecastingViewMaster updateImpl(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws SystemException {
        forecastingViewMaster = toUnwrappedModel(forecastingViewMaster);

        boolean isNew = forecastingViewMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (forecastingViewMaster.isNew()) {
                session.save(forecastingViewMaster);

                forecastingViewMaster.setNew(false);
            } else {
                session.merge(forecastingViewMaster);
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

        EntityCacheUtil.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingViewMasterImpl.class,
            forecastingViewMaster.getPrimaryKey(), forecastingViewMaster);

        return forecastingViewMaster;
    }

    protected ForecastingViewMaster toUnwrappedModel(
        ForecastingViewMaster forecastingViewMaster) {
        if (forecastingViewMaster instanceof ForecastingViewMasterImpl) {
            return forecastingViewMaster;
        }

        ForecastingViewMasterImpl forecastingViewMasterImpl = new ForecastingViewMasterImpl();

        forecastingViewMasterImpl.setNew(forecastingViewMaster.isNew());
        forecastingViewMasterImpl.setPrimaryKey(forecastingViewMaster.getPrimaryKey());

        forecastingViewMasterImpl.setCreatedDate(forecastingViewMaster.getCreatedDate());
        forecastingViewMasterImpl.setCreatedBy(forecastingViewMaster.getCreatedBy());
        forecastingViewMasterImpl.setViewType(forecastingViewMaster.getViewType());
        forecastingViewMasterImpl.setViewId(forecastingViewMaster.getViewId());
        forecastingViewMasterImpl.setProjectionId(forecastingViewMaster.getProjectionId());
        forecastingViewMasterImpl.setModifiedBy(forecastingViewMaster.getModifiedBy());
        forecastingViewMasterImpl.setModifiedDate(forecastingViewMaster.getModifiedDate());
        forecastingViewMasterImpl.setViewName(forecastingViewMaster.getViewName());

        return forecastingViewMasterImpl;
    }

    /**
     * Returns the forecasting view master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting view master
     * @return the forecasting view master
     * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchForecastingViewMasterException, SystemException {
        ForecastingViewMaster forecastingViewMaster = fetchByPrimaryKey(primaryKey);

        if (forecastingViewMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchForecastingViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return forecastingViewMaster;
    }

    /**
     * Returns the forecasting view master with the primary key or throws a {@link com.stpl.app.NoSuchForecastingViewMasterException} if it could not be found.
     *
     * @param viewId the primary key of the forecasting view master
     * @return the forecasting view master
     * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster findByPrimaryKey(int viewId)
        throws NoSuchForecastingViewMasterException, SystemException {
        return findByPrimaryKey((Serializable) viewId);
    }

    /**
     * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting view master
     * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ForecastingViewMaster forecastingViewMaster = (ForecastingViewMaster) EntityCacheUtil.getResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingViewMasterImpl.class, primaryKey);

        if (forecastingViewMaster == _nullForecastingViewMaster) {
            return null;
        }

        if (forecastingViewMaster == null) {
            Session session = null;

            try {
                session = openSession();

                forecastingViewMaster = (ForecastingViewMaster) session.get(ForecastingViewMasterImpl.class,
                        primaryKey);

                if (forecastingViewMaster != null) {
                    cacheResult(forecastingViewMaster);
                } else {
                    EntityCacheUtil.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingViewMasterImpl.class, primaryKey,
                        _nullForecastingViewMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ForecastingViewMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return forecastingViewMaster;
    }

    /**
     * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param viewId the primary key of the forecasting view master
     * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingViewMaster fetchByPrimaryKey(int viewId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) viewId);
    }

    /**
     * Returns all the forecasting view masters.
     *
     * @return the forecasting view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingViewMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting view masters
     * @param end the upper bound of the range of forecasting view masters (not inclusive)
     * @return the range of forecasting view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingViewMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting view masters
     * @param end the upper bound of the range of forecasting view masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of forecasting view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingViewMaster> findAll(int start, int end,
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

        List<ForecastingViewMaster> list = (List<ForecastingViewMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FORECASTINGVIEWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FORECASTINGVIEWMASTER;

                if (pagination) {
                    sql = sql.concat(ForecastingViewMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ForecastingViewMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingViewMaster>(list);
                } else {
                    list = (List<ForecastingViewMaster>) QueryUtil.list(q,
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
     * Removes all the forecasting view masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ForecastingViewMaster forecastingViewMaster : findAll()) {
            remove(forecastingViewMaster);
        }
    }

    /**
     * Returns the number of forecasting view masters.
     *
     * @return the number of forecasting view masters
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

                Query q = session.createQuery(_SQL_COUNT_FORECASTINGVIEWMASTER);

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
     * Initializes the forecasting view master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ForecastingViewMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ForecastingViewMaster>> listenersList = new ArrayList<ModelListener<ForecastingViewMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ForecastingViewMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ForecastingViewMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
