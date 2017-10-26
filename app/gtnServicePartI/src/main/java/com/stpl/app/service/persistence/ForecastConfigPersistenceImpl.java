package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchForecastConfigException;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.impl.ForecastConfigImpl;
import com.stpl.app.model.impl.ForecastConfigModelImpl;
import com.stpl.app.service.persistence.ForecastConfigPersistence;

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
 * The persistence implementation for the forecast config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastConfigPersistence
 * @see ForecastConfigUtil
 * @generated
 */
public class ForecastConfigPersistenceImpl extends BasePersistenceImpl<ForecastConfig>
    implements ForecastConfigPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ForecastConfigUtil} to access the forecast config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ForecastConfigImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigModelImpl.FINDER_CACHE_ENABLED,
            ForecastConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigModelImpl.FINDER_CACHE_ENABLED,
            ForecastConfigImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FORECASTCONFIG = "SELECT forecastConfig FROM ForecastConfig forecastConfig";
    private static final String _SQL_COUNT_FORECASTCONFIG = "SELECT COUNT(forecastConfig) FROM ForecastConfig forecastConfig";
    private static final String _ORDER_BY_ENTITY_ALIAS = "forecastConfig.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastConfig exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ForecastConfigPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "processType", "toDate", "versionNo", "forecastConfigSid",
                "modifiedDate", "fromDate", "projValue", "createdBy",
                "createdDate", "projFreq", "histValue", "businessProcessType",
                "modifiedBy", "histFreq", "activeStartDate", "activeEndDate",
                "processMode", "historicalDataIntervalFrom",
                "historicalTimePeriodFrom", "projHistFreq",
                "futureTimePeriodFrom", "historicalDataIntervalTo",
                "projHistValue"
            });
    private static ForecastConfig _nullForecastConfig = new ForecastConfigImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ForecastConfig> toCacheModel() {
                return _nullForecastConfigCacheModel;
            }
        };

    private static CacheModel<ForecastConfig> _nullForecastConfigCacheModel = new CacheModel<ForecastConfig>() {
            @Override
            public ForecastConfig toEntityModel() {
                return _nullForecastConfig;
            }
        };

    public ForecastConfigPersistenceImpl() {
        setModelClass(ForecastConfig.class);
    }

    /**
     * Caches the forecast config in the entity cache if it is enabled.
     *
     * @param forecastConfig the forecast config
     */
    @Override
    public void cacheResult(ForecastConfig forecastConfig) {
        EntityCacheUtil.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigImpl.class, forecastConfig.getPrimaryKey(),
            forecastConfig);

        forecastConfig.resetOriginalValues();
    }

    /**
     * Caches the forecast configs in the entity cache if it is enabled.
     *
     * @param forecastConfigs the forecast configs
     */
    @Override
    public void cacheResult(List<ForecastConfig> forecastConfigs) {
        for (ForecastConfig forecastConfig : forecastConfigs) {
            if (EntityCacheUtil.getResult(
                        ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastConfigImpl.class, forecastConfig.getPrimaryKey()) == null) {
                cacheResult(forecastConfig);
            } else {
                forecastConfig.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all forecast configs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ForecastConfigImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ForecastConfigImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the forecast config.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ForecastConfig forecastConfig) {
        EntityCacheUtil.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigImpl.class, forecastConfig.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ForecastConfig> forecastConfigs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ForecastConfig forecastConfig : forecastConfigs) {
            EntityCacheUtil.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
                ForecastConfigImpl.class, forecastConfig.getPrimaryKey());
        }
    }

    /**
     * Creates a new forecast config with the primary key. Does not add the forecast config to the database.
     *
     * @param forecastConfigSid the primary key for the new forecast config
     * @return the new forecast config
     */
    @Override
    public ForecastConfig create(int forecastConfigSid) {
        ForecastConfig forecastConfig = new ForecastConfigImpl();

        forecastConfig.setNew(true);
        forecastConfig.setPrimaryKey(forecastConfigSid);

        return forecastConfig;
    }

    /**
     * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param forecastConfigSid the primary key of the forecast config
     * @return the forecast config that was removed
     * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig remove(int forecastConfigSid)
        throws NoSuchForecastConfigException, SystemException {
        return remove((Serializable) forecastConfigSid);
    }

    /**
     * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the forecast config
     * @return the forecast config that was removed
     * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig remove(Serializable primaryKey)
        throws NoSuchForecastConfigException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ForecastConfig forecastConfig = (ForecastConfig) session.get(ForecastConfigImpl.class,
                    primaryKey);

            if (forecastConfig == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchForecastConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(forecastConfig);
        } catch (NoSuchForecastConfigException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ForecastConfig removeImpl(ForecastConfig forecastConfig)
        throws SystemException {
        forecastConfig = toUnwrappedModel(forecastConfig);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(forecastConfig)) {
                forecastConfig = (ForecastConfig) session.get(ForecastConfigImpl.class,
                        forecastConfig.getPrimaryKeyObj());
            }

            if (forecastConfig != null) {
                session.delete(forecastConfig);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (forecastConfig != null) {
            clearCache(forecastConfig);
        }

        return forecastConfig;
    }

    @Override
    public ForecastConfig updateImpl(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws SystemException {
        forecastConfig = toUnwrappedModel(forecastConfig);

        boolean isNew = forecastConfig.isNew();

        Session session = null;

        try {
            session = openSession();

            if (forecastConfig.isNew()) {
                session.save(forecastConfig);

                forecastConfig.setNew(false);
            } else {
                session.merge(forecastConfig);
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

        EntityCacheUtil.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
            ForecastConfigImpl.class, forecastConfig.getPrimaryKey(),
            forecastConfig);

        return forecastConfig;
    }

    protected ForecastConfig toUnwrappedModel(ForecastConfig forecastConfig) {
        if (forecastConfig instanceof ForecastConfigImpl) {
            return forecastConfig;
        }

        ForecastConfigImpl forecastConfigImpl = new ForecastConfigImpl();

        forecastConfigImpl.setNew(forecastConfig.isNew());
        forecastConfigImpl.setPrimaryKey(forecastConfig.getPrimaryKey());

        forecastConfigImpl.setProcessType(forecastConfig.isProcessType());
        forecastConfigImpl.setToDate(forecastConfig.getToDate());
        forecastConfigImpl.setVersionNo(forecastConfig.getVersionNo());
        forecastConfigImpl.setForecastConfigSid(forecastConfig.getForecastConfigSid());
        forecastConfigImpl.setModifiedDate(forecastConfig.getModifiedDate());
        forecastConfigImpl.setFromDate(forecastConfig.getFromDate());
        forecastConfigImpl.setProjValue(forecastConfig.getProjValue());
        forecastConfigImpl.setCreatedBy(forecastConfig.getCreatedBy());
        forecastConfigImpl.setCreatedDate(forecastConfig.getCreatedDate());
        forecastConfigImpl.setProjFreq(forecastConfig.getProjFreq());
        forecastConfigImpl.setHistValue(forecastConfig.getHistValue());
        forecastConfigImpl.setBusinessProcessType(forecastConfig.getBusinessProcessType());
        forecastConfigImpl.setModifiedBy(forecastConfig.getModifiedBy());
        forecastConfigImpl.setHistFreq(forecastConfig.getHistFreq());
        forecastConfigImpl.setActiveStartDate(forecastConfig.getActiveStartDate());
        forecastConfigImpl.setActiveEndDate(forecastConfig.getActiveEndDate());
        forecastConfigImpl.setProcessMode(forecastConfig.isProcessMode());
        forecastConfigImpl.setHistoricalDataIntervalFrom(forecastConfig.getHistoricalDataIntervalFrom());
        forecastConfigImpl.setHistoricalTimePeriodFrom(forecastConfig.getHistoricalTimePeriodFrom());
        forecastConfigImpl.setProjHistFreq(forecastConfig.getProjHistFreq());
        forecastConfigImpl.setFutureTimePeriodFrom(forecastConfig.getFutureTimePeriodFrom());
        forecastConfigImpl.setHistoricalDataIntervalTo(forecastConfig.getHistoricalDataIntervalTo());
        forecastConfigImpl.setProjHistValue(forecastConfig.getProjHistValue());

        return forecastConfigImpl;
    }

    /**
     * Returns the forecast config with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the forecast config
     * @return the forecast config
     * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig findByPrimaryKey(Serializable primaryKey)
        throws NoSuchForecastConfigException, SystemException {
        ForecastConfig forecastConfig = fetchByPrimaryKey(primaryKey);

        if (forecastConfig == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchForecastConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return forecastConfig;
    }

    /**
     * Returns the forecast config with the primary key or throws a {@link com.stpl.app.NoSuchForecastConfigException} if it could not be found.
     *
     * @param forecastConfigSid the primary key of the forecast config
     * @return the forecast config
     * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig findByPrimaryKey(int forecastConfigSid)
        throws NoSuchForecastConfigException, SystemException {
        return findByPrimaryKey((Serializable) forecastConfigSid);
    }

    /**
     * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the forecast config
     * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ForecastConfig forecastConfig = (ForecastConfig) EntityCacheUtil.getResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
                ForecastConfigImpl.class, primaryKey);

        if (forecastConfig == _nullForecastConfig) {
            return null;
        }

        if (forecastConfig == null) {
            Session session = null;

            try {
                session = openSession();

                forecastConfig = (ForecastConfig) session.get(ForecastConfigImpl.class,
                        primaryKey);

                if (forecastConfig != null) {
                    cacheResult(forecastConfig);
                } else {
                    EntityCacheUtil.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastConfigImpl.class, primaryKey,
                        _nullForecastConfig);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
                    ForecastConfigImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return forecastConfig;
    }

    /**
     * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param forecastConfigSid the primary key of the forecast config
     * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastConfig fetchByPrimaryKey(int forecastConfigSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) forecastConfigSid);
    }

    /**
     * Returns all the forecast configs.
     *
     * @return the forecast configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastConfig> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecast configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecast configs
     * @param end the upper bound of the range of forecast configs (not inclusive)
     * @return the range of forecast configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastConfig> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the forecast configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecast configs
     * @param end the upper bound of the range of forecast configs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of forecast configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastConfig> findAll(int start, int end,
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

        List<ForecastConfig> list = (List<ForecastConfig>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FORECASTCONFIG);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FORECASTCONFIG;

                if (pagination) {
                    sql = sql.concat(ForecastConfigModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ForecastConfig>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastConfig>(list);
                } else {
                    list = (List<ForecastConfig>) QueryUtil.list(q,
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
     * Removes all the forecast configs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ForecastConfig forecastConfig : findAll()) {
            remove(forecastConfig);
        }
    }

    /**
     * Returns the number of forecast configs.
     *
     * @return the number of forecast configs
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

                Query q = session.createQuery(_SQL_COUNT_FORECASTCONFIG);

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
     * Initializes the forecast config persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ForecastConfig")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ForecastConfig>> listenersList = new ArrayList<ModelListener<ForecastConfig>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ForecastConfig>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ForecastConfigImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
