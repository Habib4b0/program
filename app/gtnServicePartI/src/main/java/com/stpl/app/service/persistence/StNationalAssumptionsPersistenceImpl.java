package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNationalAssumptionsException;
import com.stpl.app.model.StNationalAssumptions;
import com.stpl.app.model.impl.StNationalAssumptionsImpl;
import com.stpl.app.model.impl.StNationalAssumptionsModelImpl;
import com.stpl.app.service.persistence.StNationalAssumptionsPersistence;

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
 * The persistence implementation for the st national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNationalAssumptionsPersistence
 * @see StNationalAssumptionsUtil
 * @generated
 */
public class StNationalAssumptionsPersistenceImpl extends BasePersistenceImpl<StNationalAssumptions>
    implements StNationalAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNationalAssumptionsUtil} to access the st national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNationalAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StNationalAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StNationalAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNATIONALASSUMPTIONS = "SELECT stNationalAssumptions FROM StNationalAssumptions stNationalAssumptions";
    private static final String _SQL_COUNT_STNATIONALASSUMPTIONS = "SELECT COUNT(stNationalAssumptions) FROM StNationalAssumptions stNationalAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNationalAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNationalAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNationalAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "baselinePeriod", "startPeriod", "frequency",
                "userId", "endPeriod", "naProjMasterSid", "rollingPeriod",
                "forecastMethodology", "priceType", "priceBasis", "sessionId",
                "baselineMethodology", "growthRate"
            });
    private static StNationalAssumptions _nullStNationalAssumptions = new StNationalAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNationalAssumptions> toCacheModel() {
                return _nullStNationalAssumptionsCacheModel;
            }
        };

    private static CacheModel<StNationalAssumptions> _nullStNationalAssumptionsCacheModel =
        new CacheModel<StNationalAssumptions>() {
            @Override
            public StNationalAssumptions toEntityModel() {
                return _nullStNationalAssumptions;
            }
        };

    public StNationalAssumptionsPersistenceImpl() {
        setModelClass(StNationalAssumptions.class);
    }

    /**
     * Caches the st national assumptions in the entity cache if it is enabled.
     *
     * @param stNationalAssumptions the st national assumptions
     */
    @Override
    public void cacheResult(StNationalAssumptions stNationalAssumptions) {
        EntityCacheUtil.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsImpl.class,
            stNationalAssumptions.getPrimaryKey(), stNationalAssumptions);

        stNationalAssumptions.resetOriginalValues();
    }

    /**
     * Caches the st national assumptionses in the entity cache if it is enabled.
     *
     * @param stNationalAssumptionses the st national assumptionses
     */
    @Override
    public void cacheResult(List<StNationalAssumptions> stNationalAssumptionses) {
        for (StNationalAssumptions stNationalAssumptions : stNationalAssumptionses) {
            if (EntityCacheUtil.getResult(
                        StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StNationalAssumptionsImpl.class,
                        stNationalAssumptions.getPrimaryKey()) == null) {
                cacheResult(stNationalAssumptions);
            } else {
                stNationalAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st national assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNationalAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNationalAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st national assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNationalAssumptions stNationalAssumptions) {
        EntityCacheUtil.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsImpl.class,
            stNationalAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNationalAssumptions> stNationalAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNationalAssumptions stNationalAssumptions : stNationalAssumptionses) {
            EntityCacheUtil.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StNationalAssumptionsImpl.class,
                stNationalAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
     *
     * @param stNationalAssumptionsPK the primary key for the new st national assumptions
     * @return the new st national assumptions
     */
    @Override
    public StNationalAssumptions create(
        StNationalAssumptionsPK stNationalAssumptionsPK) {
        StNationalAssumptions stNationalAssumptions = new StNationalAssumptionsImpl();

        stNationalAssumptions.setNew(true);
        stNationalAssumptions.setPrimaryKey(stNationalAssumptionsPK);

        return stNationalAssumptions;
    }

    /**
     * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNationalAssumptionsPK the primary key of the st national assumptions
     * @return the st national assumptions that was removed
     * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions remove(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws NoSuchStNationalAssumptionsException, SystemException {
        return remove((Serializable) stNationalAssumptionsPK);
    }

    /**
     * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st national assumptions
     * @return the st national assumptions that was removed
     * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions remove(Serializable primaryKey)
        throws NoSuchStNationalAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNationalAssumptions stNationalAssumptions = (StNationalAssumptions) session.get(StNationalAssumptionsImpl.class,
                    primaryKey);

            if (stNationalAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNationalAssumptions);
        } catch (NoSuchStNationalAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNationalAssumptions removeImpl(
        StNationalAssumptions stNationalAssumptions) throws SystemException {
        stNationalAssumptions = toUnwrappedModel(stNationalAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNationalAssumptions)) {
                stNationalAssumptions = (StNationalAssumptions) session.get(StNationalAssumptionsImpl.class,
                        stNationalAssumptions.getPrimaryKeyObj());
            }

            if (stNationalAssumptions != null) {
                session.delete(stNationalAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNationalAssumptions != null) {
            clearCache(stNationalAssumptions);
        }

        return stNationalAssumptions;
    }

    @Override
    public StNationalAssumptions updateImpl(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws SystemException {
        stNationalAssumptions = toUnwrappedModel(stNationalAssumptions);

        boolean isNew = stNationalAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNationalAssumptions.isNew()) {
                session.save(stNationalAssumptions);

                stNationalAssumptions.setNew(false);
            } else {
                session.merge(stNationalAssumptions);
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

        EntityCacheUtil.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNationalAssumptionsImpl.class,
            stNationalAssumptions.getPrimaryKey(), stNationalAssumptions);

        return stNationalAssumptions;
    }

    protected StNationalAssumptions toUnwrappedModel(
        StNationalAssumptions stNationalAssumptions) {
        if (stNationalAssumptions instanceof StNationalAssumptionsImpl) {
            return stNationalAssumptions;
        }

        StNationalAssumptionsImpl stNationalAssumptionsImpl = new StNationalAssumptionsImpl();

        stNationalAssumptionsImpl.setNew(stNationalAssumptions.isNew());
        stNationalAssumptionsImpl.setPrimaryKey(stNationalAssumptions.getPrimaryKey());

        stNationalAssumptionsImpl.setLastModifiedDate(stNationalAssumptions.getLastModifiedDate());
        stNationalAssumptionsImpl.setBaselinePeriod(stNationalAssumptions.getBaselinePeriod());
        stNationalAssumptionsImpl.setStartPeriod(stNationalAssumptions.getStartPeriod());
        stNationalAssumptionsImpl.setFrequency(stNationalAssumptions.getFrequency());
        stNationalAssumptionsImpl.setUserId(stNationalAssumptions.getUserId());
        stNationalAssumptionsImpl.setEndPeriod(stNationalAssumptions.getEndPeriod());
        stNationalAssumptionsImpl.setNaProjMasterSid(stNationalAssumptions.getNaProjMasterSid());
        stNationalAssumptionsImpl.setRollingPeriod(stNationalAssumptions.getRollingPeriod());
        stNationalAssumptionsImpl.setForecastMethodology(stNationalAssumptions.getForecastMethodology());
        stNationalAssumptionsImpl.setPriceType(stNationalAssumptions.getPriceType());
        stNationalAssumptionsImpl.setPriceBasis(stNationalAssumptions.getPriceBasis());
        stNationalAssumptionsImpl.setSessionId(stNationalAssumptions.getSessionId());
        stNationalAssumptionsImpl.setBaselineMethodology(stNationalAssumptions.getBaselineMethodology());
        stNationalAssumptionsImpl.setGrowthRate(stNationalAssumptions.getGrowthRate());

        return stNationalAssumptionsImpl;
    }

    /**
     * Returns the st national assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st national assumptions
     * @return the st national assumptions
     * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNationalAssumptionsException, SystemException {
        StNationalAssumptions stNationalAssumptions = fetchByPrimaryKey(primaryKey);

        if (stNationalAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNationalAssumptions;
    }

    /**
     * Returns the st national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNationalAssumptionsException} if it could not be found.
     *
     * @param stNationalAssumptionsPK the primary key of the st national assumptions
     * @return the st national assumptions
     * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions findByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws NoSuchStNationalAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) stNationalAssumptionsPK);
    }

    /**
     * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st national assumptions
     * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNationalAssumptions stNationalAssumptions = (StNationalAssumptions) EntityCacheUtil.getResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StNationalAssumptionsImpl.class, primaryKey);

        if (stNationalAssumptions == _nullStNationalAssumptions) {
            return null;
        }

        if (stNationalAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                stNationalAssumptions = (StNationalAssumptions) session.get(StNationalAssumptionsImpl.class,
                        primaryKey);

                if (stNationalAssumptions != null) {
                    cacheResult(stNationalAssumptions);
                } else {
                    EntityCacheUtil.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StNationalAssumptionsImpl.class, primaryKey,
                        _nullStNationalAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    StNationalAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNationalAssumptions;
    }

    /**
     * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNationalAssumptionsPK the primary key of the st national assumptions
     * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNationalAssumptions fetchByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNationalAssumptionsPK);
    }

    /**
     * Returns all the st national assumptionses.
     *
     * @return the st national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNationalAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st national assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st national assumptionses
     * @param end the upper bound of the range of st national assumptionses (not inclusive)
     * @return the range of st national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNationalAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st national assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st national assumptionses
     * @param end the upper bound of the range of st national assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNationalAssumptions> findAll(int start, int end,
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

        List<StNationalAssumptions> list = (List<StNationalAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNATIONALASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNATIONALASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(StNationalAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNationalAssumptions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNationalAssumptions>(list);
                } else {
                    list = (List<StNationalAssumptions>) QueryUtil.list(q,
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
     * Removes all the st national assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNationalAssumptions stNationalAssumptions : findAll()) {
            remove(stNationalAssumptions);
        }
    }

    /**
     * Returns the number of st national assumptionses.
     *
     * @return the number of st national assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_STNATIONALASSUMPTIONS);

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
     * Initializes the st national assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNationalAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNationalAssumptions>> listenersList = new ArrayList<ModelListener<StNationalAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNationalAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNationalAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
