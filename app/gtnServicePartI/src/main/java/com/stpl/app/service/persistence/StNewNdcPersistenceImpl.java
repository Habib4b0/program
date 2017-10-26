package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNewNdcException;
import com.stpl.app.model.StNewNdc;
import com.stpl.app.model.impl.StNewNdcImpl;
import com.stpl.app.model.impl.StNewNdcModelImpl;
import com.stpl.app.service.persistence.StNewNdcPersistence;

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
 * The persistence implementation for the st new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNewNdcPersistence
 * @see StNewNdcUtil
 * @generated
 */
public class StNewNdcPersistenceImpl extends BasePersistenceImpl<StNewNdc>
    implements StNewNdcPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNewNdcUtil} to access the st new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNewNdcImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcModelImpl.FINDER_CACHE_ENABLED, StNewNdcImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcModelImpl.FINDER_CACHE_ENABLED, StNewNdcImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNEWNDC = "SELECT stNewNdc FROM StNewNdc stNewNdc";
    private static final String _SQL_COUNT_STNEWNDC = "SELECT COUNT(stNewNdc) FROM StNewNdc stNewNdc";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNewNdc.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNewNdc exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNewNdcPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastAmp", "forecastBestprice", "naProjDetailsSid",
                "baseYearCpi", "userId", "lastModifiedDate", "itemMasterSid",
                "wacPrice", "baseYearAmp", "sessionId"
            });
    private static StNewNdc _nullStNewNdc = new StNewNdcImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNewNdc> toCacheModel() {
                return _nullStNewNdcCacheModel;
            }
        };

    private static CacheModel<StNewNdc> _nullStNewNdcCacheModel = new CacheModel<StNewNdc>() {
            @Override
            public StNewNdc toEntityModel() {
                return _nullStNewNdc;
            }
        };

    public StNewNdcPersistenceImpl() {
        setModelClass(StNewNdc.class);
    }

    /**
     * Caches the st new ndc in the entity cache if it is enabled.
     *
     * @param stNewNdc the st new ndc
     */
    @Override
    public void cacheResult(StNewNdc stNewNdc) {
        EntityCacheUtil.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcImpl.class, stNewNdc.getPrimaryKey(), stNewNdc);

        stNewNdc.resetOriginalValues();
    }

    /**
     * Caches the st new ndcs in the entity cache if it is enabled.
     *
     * @param stNewNdcs the st new ndcs
     */
    @Override
    public void cacheResult(List<StNewNdc> stNewNdcs) {
        for (StNewNdc stNewNdc : stNewNdcs) {
            if (EntityCacheUtil.getResult(
                        StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StNewNdcImpl.class, stNewNdc.getPrimaryKey()) == null) {
                cacheResult(stNewNdc);
            } else {
                stNewNdc.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st new ndcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNewNdcImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNewNdcImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st new ndc.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNewNdc stNewNdc) {
        EntityCacheUtil.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcImpl.class, stNewNdc.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNewNdc> stNewNdcs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNewNdc stNewNdc : stNewNdcs) {
            EntityCacheUtil.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StNewNdcImpl.class, stNewNdc.getPrimaryKey());
        }
    }

    /**
     * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
     *
     * @param stNewNdcPK the primary key for the new st new ndc
     * @return the new st new ndc
     */
    @Override
    public StNewNdc create(StNewNdcPK stNewNdcPK) {
        StNewNdc stNewNdc = new StNewNdcImpl();

        stNewNdc.setNew(true);
        stNewNdc.setPrimaryKey(stNewNdcPK);

        return stNewNdc;
    }

    /**
     * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNewNdcPK the primary key of the st new ndc
     * @return the st new ndc that was removed
     * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc remove(StNewNdcPK stNewNdcPK)
        throws NoSuchStNewNdcException, SystemException {
        return remove((Serializable) stNewNdcPK);
    }

    /**
     * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st new ndc
     * @return the st new ndc that was removed
     * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc remove(Serializable primaryKey)
        throws NoSuchStNewNdcException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNewNdc stNewNdc = (StNewNdc) session.get(StNewNdcImpl.class,
                    primaryKey);

            if (stNewNdc == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNewNdc);
        } catch (NoSuchStNewNdcException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNewNdc removeImpl(StNewNdc stNewNdc) throws SystemException {
        stNewNdc = toUnwrappedModel(stNewNdc);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNewNdc)) {
                stNewNdc = (StNewNdc) session.get(StNewNdcImpl.class,
                        stNewNdc.getPrimaryKeyObj());
            }

            if (stNewNdc != null) {
                session.delete(stNewNdc);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNewNdc != null) {
            clearCache(stNewNdc);
        }

        return stNewNdc;
    }

    @Override
    public StNewNdc updateImpl(com.stpl.app.model.StNewNdc stNewNdc)
        throws SystemException {
        stNewNdc = toUnwrappedModel(stNewNdc);

        boolean isNew = stNewNdc.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNewNdc.isNew()) {
                session.save(stNewNdc);

                stNewNdc.setNew(false);
            } else {
                session.merge(stNewNdc);
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

        EntityCacheUtil.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StNewNdcImpl.class, stNewNdc.getPrimaryKey(), stNewNdc);

        return stNewNdc;
    }

    protected StNewNdc toUnwrappedModel(StNewNdc stNewNdc) {
        if (stNewNdc instanceof StNewNdcImpl) {
            return stNewNdc;
        }

        StNewNdcImpl stNewNdcImpl = new StNewNdcImpl();

        stNewNdcImpl.setNew(stNewNdc.isNew());
        stNewNdcImpl.setPrimaryKey(stNewNdc.getPrimaryKey());

        stNewNdcImpl.setForecastAmp(stNewNdc.getForecastAmp());
        stNewNdcImpl.setForecastBestprice(stNewNdc.getForecastBestprice());
        stNewNdcImpl.setNaProjDetailsSid(stNewNdc.getNaProjDetailsSid());
        stNewNdcImpl.setBaseYearCpi(stNewNdc.getBaseYearCpi());
        stNewNdcImpl.setUserId(stNewNdc.getUserId());
        stNewNdcImpl.setLastModifiedDate(stNewNdc.getLastModifiedDate());
        stNewNdcImpl.setItemMasterSid(stNewNdc.getItemMasterSid());
        stNewNdcImpl.setWacPrice(stNewNdc.getWacPrice());
        stNewNdcImpl.setBaseYearAmp(stNewNdc.getBaseYearAmp());
        stNewNdcImpl.setSessionId(stNewNdc.getSessionId());

        return stNewNdcImpl;
    }

    /**
     * Returns the st new ndc with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st new ndc
     * @return the st new ndc
     * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNewNdcException, SystemException {
        StNewNdc stNewNdc = fetchByPrimaryKey(primaryKey);

        if (stNewNdc == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNewNdc;
    }

    /**
     * Returns the st new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStNewNdcException} if it could not be found.
     *
     * @param stNewNdcPK the primary key of the st new ndc
     * @return the st new ndc
     * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc findByPrimaryKey(StNewNdcPK stNewNdcPK)
        throws NoSuchStNewNdcException, SystemException {
        return findByPrimaryKey((Serializable) stNewNdcPK);
    }

    /**
     * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st new ndc
     * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNewNdc stNewNdc = (StNewNdc) EntityCacheUtil.getResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StNewNdcImpl.class, primaryKey);

        if (stNewNdc == _nullStNewNdc) {
            return null;
        }

        if (stNewNdc == null) {
            Session session = null;

            try {
                session = openSession();

                stNewNdc = (StNewNdc) session.get(StNewNdcImpl.class, primaryKey);

                if (stNewNdc != null) {
                    cacheResult(stNewNdc);
                } else {
                    EntityCacheUtil.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StNewNdcImpl.class, primaryKey, _nullStNewNdc);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                    StNewNdcImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNewNdc;
    }

    /**
     * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNewNdcPK the primary key of the st new ndc
     * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNewNdc fetchByPrimaryKey(StNewNdcPK stNewNdcPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNewNdcPK);
    }

    /**
     * Returns all the st new ndcs.
     *
     * @return the st new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNewNdc> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st new ndcs
     * @param end the upper bound of the range of st new ndcs (not inclusive)
     * @return the range of st new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNewNdc> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st new ndcs
     * @param end the upper bound of the range of st new ndcs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNewNdc> findAll(int start, int end,
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

        List<StNewNdc> list = (List<StNewNdc>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNEWNDC);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNEWNDC;

                if (pagination) {
                    sql = sql.concat(StNewNdcModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNewNdc>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNewNdc>(list);
                } else {
                    list = (List<StNewNdc>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the st new ndcs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNewNdc stNewNdc : findAll()) {
            remove(stNewNdc);
        }
    }

    /**
     * Returns the number of st new ndcs.
     *
     * @return the number of st new ndcs
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

                Query q = session.createQuery(_SQL_COUNT_STNEWNDC);

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
     * Initializes the st new ndc persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNewNdc")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNewNdc>> listenersList = new ArrayList<ModelListener<StNewNdc>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNewNdc>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNewNdcImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
