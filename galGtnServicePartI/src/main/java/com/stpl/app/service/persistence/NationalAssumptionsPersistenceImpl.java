package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNationalAssumptionsException;
import com.stpl.app.model.NationalAssumptions;
import com.stpl.app.model.impl.NationalAssumptionsImpl;
import com.stpl.app.model.impl.NationalAssumptionsModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsPersistence;

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
 * The persistence implementation for the national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsPersistence
 * @see NationalAssumptionsUtil
 * @generated
 */
public class NationalAssumptionsPersistenceImpl extends BasePersistenceImpl<NationalAssumptions>
    implements NationalAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NationalAssumptionsUtil} to access the national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NATIONALASSUMPTIONS = "SELECT nationalAssumptions FROM NationalAssumptions nationalAssumptions";
    private static final String _SQL_COUNT_NATIONALASSUMPTIONS = "SELECT COUNT(nationalAssumptions) FROM NationalAssumptions nationalAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NationalAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "baselinePeriod", "frequency", "startPeriod",
                "forecastMethodology", "priceType", "endPeriod", "priceBasis",
                "naProjMasterSid", "rollingPeriod", "baselineMethodology",
                "growthRate"
            });
    private static NationalAssumptions _nullNationalAssumptions = new NationalAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NationalAssumptions> toCacheModel() {
                return _nullNationalAssumptionsCacheModel;
            }
        };

    private static CacheModel<NationalAssumptions> _nullNationalAssumptionsCacheModel =
        new CacheModel<NationalAssumptions>() {
            @Override
            public NationalAssumptions toEntityModel() {
                return _nullNationalAssumptions;
            }
        };

    public NationalAssumptionsPersistenceImpl() {
        setModelClass(NationalAssumptions.class);
    }

    /**
     * Caches the national assumptions in the entity cache if it is enabled.
     *
     * @param nationalAssumptions the national assumptions
     */
    @Override
    public void cacheResult(NationalAssumptions nationalAssumptions) {
        EntityCacheUtil.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey(),
            nationalAssumptions);

        nationalAssumptions.resetOriginalValues();
    }

    /**
     * Caches the national assumptionses in the entity cache if it is enabled.
     *
     * @param nationalAssumptionses the national assumptionses
     */
    @Override
    public void cacheResult(List<NationalAssumptions> nationalAssumptionses) {
        for (NationalAssumptions nationalAssumptions : nationalAssumptionses) {
            if (EntityCacheUtil.getResult(
                        NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsImpl.class,
                        nationalAssumptions.getPrimaryKey()) == null) {
                cacheResult(nationalAssumptions);
            } else {
                nationalAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all national assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NationalAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NationalAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the national assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NationalAssumptions nationalAssumptions) {
        EntityCacheUtil.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NationalAssumptions> nationalAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NationalAssumptions nationalAssumptions : nationalAssumptionses) {
            EntityCacheUtil.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsImpl.class,
                nationalAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
     *
     * @param nationalAssumptionsPK the primary key for the new national assumptions
     * @return the new national assumptions
     */
    @Override
    public NationalAssumptions create(
        NationalAssumptionsPK nationalAssumptionsPK) {
        NationalAssumptions nationalAssumptions = new NationalAssumptionsImpl();

        nationalAssumptions.setNew(true);
        nationalAssumptions.setPrimaryKey(nationalAssumptionsPK);

        return nationalAssumptions;
    }

    /**
     * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nationalAssumptionsPK the primary key of the national assumptions
     * @return the national assumptions that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions remove(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws NoSuchNationalAssumptionsException, SystemException {
        return remove((Serializable) nationalAssumptionsPK);
    }

    /**
     * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the national assumptions
     * @return the national assumptions that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions remove(Serializable primaryKey)
        throws NoSuchNationalAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NationalAssumptions nationalAssumptions = (NationalAssumptions) session.get(NationalAssumptionsImpl.class,
                    primaryKey);

            if (nationalAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nationalAssumptions);
        } catch (NoSuchNationalAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NationalAssumptions removeImpl(
        NationalAssumptions nationalAssumptions) throws SystemException {
        nationalAssumptions = toUnwrappedModel(nationalAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nationalAssumptions)) {
                nationalAssumptions = (NationalAssumptions) session.get(NationalAssumptionsImpl.class,
                        nationalAssumptions.getPrimaryKeyObj());
            }

            if (nationalAssumptions != null) {
                session.delete(nationalAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nationalAssumptions != null) {
            clearCache(nationalAssumptions);
        }

        return nationalAssumptions;
    }

    @Override
    public NationalAssumptions updateImpl(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws SystemException {
        nationalAssumptions = toUnwrappedModel(nationalAssumptions);

        boolean isNew = nationalAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nationalAssumptions.isNew()) {
                session.save(nationalAssumptions);

                nationalAssumptions.setNew(false);
            } else {
                session.merge(nationalAssumptions);
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

        EntityCacheUtil.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey(),
            nationalAssumptions);

        return nationalAssumptions;
    }

    protected NationalAssumptions toUnwrappedModel(
        NationalAssumptions nationalAssumptions) {
        if (nationalAssumptions instanceof NationalAssumptionsImpl) {
            return nationalAssumptions;
        }

        NationalAssumptionsImpl nationalAssumptionsImpl = new NationalAssumptionsImpl();

        nationalAssumptionsImpl.setNew(nationalAssumptions.isNew());
        nationalAssumptionsImpl.setPrimaryKey(nationalAssumptions.getPrimaryKey());

        nationalAssumptionsImpl.setBaselinePeriod(nationalAssumptions.getBaselinePeriod());
        nationalAssumptionsImpl.setFrequency(nationalAssumptions.getFrequency());
        nationalAssumptionsImpl.setStartPeriod(nationalAssumptions.getStartPeriod());
        nationalAssumptionsImpl.setForecastMethodology(nationalAssumptions.getForecastMethodology());
        nationalAssumptionsImpl.setPriceType(nationalAssumptions.getPriceType());
        nationalAssumptionsImpl.setEndPeriod(nationalAssumptions.getEndPeriod());
        nationalAssumptionsImpl.setPriceBasis(nationalAssumptions.getPriceBasis());
        nationalAssumptionsImpl.setNaProjMasterSid(nationalAssumptions.getNaProjMasterSid());
        nationalAssumptionsImpl.setRollingPeriod(nationalAssumptions.getRollingPeriod());
        nationalAssumptionsImpl.setBaselineMethodology(nationalAssumptions.getBaselineMethodology());
        nationalAssumptionsImpl.setGrowthRate(nationalAssumptions.getGrowthRate());

        return nationalAssumptionsImpl;
    }

    /**
     * Returns the national assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions
     * @return the national assumptions
     * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNationalAssumptionsException, SystemException {
        NationalAssumptions nationalAssumptions = fetchByPrimaryKey(primaryKey);

        if (nationalAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nationalAssumptions;
    }

    /**
     * Returns the national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsException} if it could not be found.
     *
     * @param nationalAssumptionsPK the primary key of the national assumptions
     * @return the national assumptions
     * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions findByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws NoSuchNationalAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) nationalAssumptionsPK);
    }

    /**
     * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions
     * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NationalAssumptions nationalAssumptions = (NationalAssumptions) EntityCacheUtil.getResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsImpl.class, primaryKey);

        if (nationalAssumptions == _nullNationalAssumptions) {
            return null;
        }

        if (nationalAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                nationalAssumptions = (NationalAssumptions) session.get(NationalAssumptionsImpl.class,
                        primaryKey);

                if (nationalAssumptions != null) {
                    cacheResult(nationalAssumptions);
                } else {
                    EntityCacheUtil.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsImpl.class, primaryKey,
                        _nullNationalAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    NationalAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nationalAssumptions;
    }

    /**
     * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nationalAssumptionsPK the primary key of the national assumptions
     * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptions fetchByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) nationalAssumptionsPK);
    }

    /**
     * Returns all the national assumptionses.
     *
     * @return the national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the national assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptionses
     * @param end the upper bound of the range of national assumptionses (not inclusive)
     * @return the range of national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the national assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptionses
     * @param end the upper bound of the range of national assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of national assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptions> findAll(int start, int end,
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

        List<NationalAssumptions> list = (List<NationalAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NATIONALASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NATIONALASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(NationalAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NationalAssumptions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NationalAssumptions>(list);
                } else {
                    list = (List<NationalAssumptions>) QueryUtil.list(q,
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
     * Removes all the national assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NationalAssumptions nationalAssumptions : findAll()) {
            remove(nationalAssumptions);
        }
    }

    /**
     * Returns the number of national assumptionses.
     *
     * @return the number of national assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONS);

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
     * Initializes the national assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NationalAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NationalAssumptions>> listenersList = new ArrayList<ModelListener<NationalAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NationalAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NationalAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
