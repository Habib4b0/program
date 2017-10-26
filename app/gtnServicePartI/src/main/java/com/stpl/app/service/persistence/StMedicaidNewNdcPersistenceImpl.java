package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStMedicaidNewNdcException;
import com.stpl.app.model.StMedicaidNewNdc;
import com.stpl.app.model.impl.StMedicaidNewNdcImpl;
import com.stpl.app.model.impl.StMedicaidNewNdcModelImpl;
import com.stpl.app.service.persistence.StMedicaidNewNdcPersistence;

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
 * The persistence implementation for the st medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMedicaidNewNdcPersistence
 * @see StMedicaidNewNdcUtil
 * @generated
 */
public class StMedicaidNewNdcPersistenceImpl extends BasePersistenceImpl<StMedicaidNewNdc>
    implements StMedicaidNewNdcPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StMedicaidNewNdcUtil} to access the st medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StMedicaidNewNdcImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
            StMedicaidNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
            StMedicaidNewNdcImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STMEDICAIDNEWNDC = "SELECT stMedicaidNewNdc FROM StMedicaidNewNdc stMedicaidNewNdc";
    private static final String _SQL_COUNT_STMEDICAIDNEWNDC = "SELECT COUNT(stMedicaidNewNdc) FROM StMedicaidNewNdc stMedicaidNewNdc";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stMedicaidNewNdc.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMedicaidNewNdc exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StMedicaidNewNdcPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastAmp", "forecastBestprice", "baseYearCpi", "ndc9",
                "userId", "lastModifiedDate", "wacPrice", "baseYearAmp",
                "sessionId"
            });
    private static StMedicaidNewNdc _nullStMedicaidNewNdc = new StMedicaidNewNdcImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StMedicaidNewNdc> toCacheModel() {
                return _nullStMedicaidNewNdcCacheModel;
            }
        };

    private static CacheModel<StMedicaidNewNdc> _nullStMedicaidNewNdcCacheModel = new CacheModel<StMedicaidNewNdc>() {
            @Override
            public StMedicaidNewNdc toEntityModel() {
                return _nullStMedicaidNewNdc;
            }
        };

    public StMedicaidNewNdcPersistenceImpl() {
        setModelClass(StMedicaidNewNdc.class);
    }

    /**
     * Caches the st medicaid new ndc in the entity cache if it is enabled.
     *
     * @param stMedicaidNewNdc the st medicaid new ndc
     */
    @Override
    public void cacheResult(StMedicaidNewNdc stMedicaidNewNdc) {
        EntityCacheUtil.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey(),
            stMedicaidNewNdc);

        stMedicaidNewNdc.resetOriginalValues();
    }

    /**
     * Caches the st medicaid new ndcs in the entity cache if it is enabled.
     *
     * @param stMedicaidNewNdcs the st medicaid new ndcs
     */
    @Override
    public void cacheResult(List<StMedicaidNewNdc> stMedicaidNewNdcs) {
        for (StMedicaidNewNdc stMedicaidNewNdc : stMedicaidNewNdcs) {
            if (EntityCacheUtil.getResult(
                        StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StMedicaidNewNdcImpl.class,
                        stMedicaidNewNdc.getPrimaryKey()) == null) {
                cacheResult(stMedicaidNewNdc);
            } else {
                stMedicaidNewNdc.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st medicaid new ndcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StMedicaidNewNdcImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StMedicaidNewNdcImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st medicaid new ndc.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StMedicaidNewNdc stMedicaidNewNdc) {
        EntityCacheUtil.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StMedicaidNewNdc> stMedicaidNewNdcs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StMedicaidNewNdc stMedicaidNewNdc : stMedicaidNewNdcs) {
            EntityCacheUtil.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey());
        }
    }

    /**
     * Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
     *
     * @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
     * @return the new st medicaid new ndc
     */
    @Override
    public StMedicaidNewNdc create(StMedicaidNewNdcPK stMedicaidNewNdcPK) {
        StMedicaidNewNdc stMedicaidNewNdc = new StMedicaidNewNdcImpl();

        stMedicaidNewNdc.setNew(true);
        stMedicaidNewNdc.setPrimaryKey(stMedicaidNewNdcPK);

        return stMedicaidNewNdc;
    }

    /**
     * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc that was removed
     * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc remove(StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws NoSuchStMedicaidNewNdcException, SystemException {
        return remove((Serializable) stMedicaidNewNdcPK);
    }

    /**
     * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc that was removed
     * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc remove(Serializable primaryKey)
        throws NoSuchStMedicaidNewNdcException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StMedicaidNewNdc stMedicaidNewNdc = (StMedicaidNewNdc) session.get(StMedicaidNewNdcImpl.class,
                    primaryKey);

            if (stMedicaidNewNdc == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stMedicaidNewNdc);
        } catch (NoSuchStMedicaidNewNdcException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StMedicaidNewNdc removeImpl(StMedicaidNewNdc stMedicaidNewNdc)
        throws SystemException {
        stMedicaidNewNdc = toUnwrappedModel(stMedicaidNewNdc);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stMedicaidNewNdc)) {
                stMedicaidNewNdc = (StMedicaidNewNdc) session.get(StMedicaidNewNdcImpl.class,
                        stMedicaidNewNdc.getPrimaryKeyObj());
            }

            if (stMedicaidNewNdc != null) {
                session.delete(stMedicaidNewNdc);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stMedicaidNewNdc != null) {
            clearCache(stMedicaidNewNdc);
        }

        return stMedicaidNewNdc;
    }

    @Override
    public StMedicaidNewNdc updateImpl(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws SystemException {
        stMedicaidNewNdc = toUnwrappedModel(stMedicaidNewNdc);

        boolean isNew = stMedicaidNewNdc.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stMedicaidNewNdc.isNew()) {
                session.save(stMedicaidNewNdc);

                stMedicaidNewNdc.setNew(false);
            } else {
                session.merge(stMedicaidNewNdc);
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

        EntityCacheUtil.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey(),
            stMedicaidNewNdc);

        return stMedicaidNewNdc;
    }

    protected StMedicaidNewNdc toUnwrappedModel(
        StMedicaidNewNdc stMedicaidNewNdc) {
        if (stMedicaidNewNdc instanceof StMedicaidNewNdcImpl) {
            return stMedicaidNewNdc;
        }

        StMedicaidNewNdcImpl stMedicaidNewNdcImpl = new StMedicaidNewNdcImpl();

        stMedicaidNewNdcImpl.setNew(stMedicaidNewNdc.isNew());
        stMedicaidNewNdcImpl.setPrimaryKey(stMedicaidNewNdc.getPrimaryKey());

        stMedicaidNewNdcImpl.setForecastAmp(stMedicaidNewNdc.getForecastAmp());
        stMedicaidNewNdcImpl.setForecastBestprice(stMedicaidNewNdc.getForecastBestprice());
        stMedicaidNewNdcImpl.setBaseYearCpi(stMedicaidNewNdc.getBaseYearCpi());
        stMedicaidNewNdcImpl.setNdc9(stMedicaidNewNdc.getNdc9());
        stMedicaidNewNdcImpl.setUserId(stMedicaidNewNdc.getUserId());
        stMedicaidNewNdcImpl.setLastModifiedDate(stMedicaidNewNdc.getLastModifiedDate());
        stMedicaidNewNdcImpl.setWacPrice(stMedicaidNewNdc.getWacPrice());
        stMedicaidNewNdcImpl.setBaseYearAmp(stMedicaidNewNdc.getBaseYearAmp());
        stMedicaidNewNdcImpl.setSessionId(stMedicaidNewNdc.getSessionId());

        return stMedicaidNewNdcImpl;
    }

    /**
     * Returns the st medicaid new ndc with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc
     * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStMedicaidNewNdcException, SystemException {
        StMedicaidNewNdc stMedicaidNewNdc = fetchByPrimaryKey(primaryKey);

        if (stMedicaidNewNdc == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stMedicaidNewNdc;
    }

    /**
     * Returns the st medicaid new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStMedicaidNewNdcException} if it could not be found.
     *
     * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc
     * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc findByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws NoSuchStMedicaidNewNdcException, SystemException {
        return findByPrimaryKey((Serializable) stMedicaidNewNdcPK);
    }

    /**
     * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StMedicaidNewNdc stMedicaidNewNdc = (StMedicaidNewNdc) EntityCacheUtil.getResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StMedicaidNewNdcImpl.class, primaryKey);

        if (stMedicaidNewNdc == _nullStMedicaidNewNdc) {
            return null;
        }

        if (stMedicaidNewNdc == null) {
            Session session = null;

            try {
                session = openSession();

                stMedicaidNewNdc = (StMedicaidNewNdc) session.get(StMedicaidNewNdcImpl.class,
                        primaryKey);

                if (stMedicaidNewNdc != null) {
                    cacheResult(stMedicaidNewNdc);
                } else {
                    EntityCacheUtil.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StMedicaidNewNdcImpl.class, primaryKey,
                        _nullStMedicaidNewNdc);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                    StMedicaidNewNdcImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stMedicaidNewNdc;
    }

    /**
     * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
     * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMedicaidNewNdc fetchByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stMedicaidNewNdcPK);
    }

    /**
     * Returns all the st medicaid new ndcs.
     *
     * @return the st medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMedicaidNewNdc> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st medicaid new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st medicaid new ndcs
     * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
     * @return the range of st medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMedicaidNewNdc> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st medicaid new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st medicaid new ndcs
     * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMedicaidNewNdc> findAll(int start, int end,
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

        List<StMedicaidNewNdc> list = (List<StMedicaidNewNdc>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STMEDICAIDNEWNDC);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STMEDICAIDNEWNDC;

                if (pagination) {
                    sql = sql.concat(StMedicaidNewNdcModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StMedicaidNewNdc>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StMedicaidNewNdc>(list);
                } else {
                    list = (List<StMedicaidNewNdc>) QueryUtil.list(q,
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
     * Removes all the st medicaid new ndcs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StMedicaidNewNdc stMedicaidNewNdc : findAll()) {
            remove(stMedicaidNewNdc);
        }
    }

    /**
     * Returns the number of st medicaid new ndcs.
     *
     * @return the number of st medicaid new ndcs
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

                Query q = session.createQuery(_SQL_COUNT_STMEDICAIDNEWNDC);

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
     * Initializes the st medicaid new ndc persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StMedicaidNewNdc")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StMedicaidNewNdc>> listenersList = new ArrayList<ModelListener<StMedicaidNewNdc>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StMedicaidNewNdc>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StMedicaidNewNdcImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
