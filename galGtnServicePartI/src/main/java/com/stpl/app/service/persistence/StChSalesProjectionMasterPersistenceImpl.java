package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStChSalesProjectionMasterException;
import com.stpl.app.model.StChSalesProjectionMaster;
import com.stpl.app.model.impl.StChSalesProjectionMasterImpl;
import com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPersistence;

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
 * The persistence implementation for the st ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChSalesProjectionMasterPersistence
 * @see StChSalesProjectionMasterUtil
 * @generated
 */
public class StChSalesProjectionMasterPersistenceImpl
    extends BasePersistenceImpl<StChSalesProjectionMaster>
    implements StChSalesProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StChSalesProjectionMasterUtil} to access the st ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StChSalesProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            StChSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            StChSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_STCHSALESPROJECTIONMASTER = "SELECT stChSalesProjectionMaster FROM StChSalesProjectionMaster stChSalesProjectionMaster";
    private static final String _SQL_COUNT_STCHSALESPROJECTIONMASTER = "SELECT COUNT(stChSalesProjectionMaster) FROM StChSalesProjectionMaster stChSalesProjectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stChSalesProjectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChSalesProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StChSalesProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "checkRecord", "calculationPeriods",
                "projectionDetailsSid", "userId", "sessionId", "methodology"
            });
    private static StChSalesProjectionMaster _nullStChSalesProjectionMaster = new StChSalesProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StChSalesProjectionMaster> toCacheModel() {
                return _nullStChSalesProjectionMasterCacheModel;
            }
        };

    private static CacheModel<StChSalesProjectionMaster> _nullStChSalesProjectionMasterCacheModel =
        new CacheModel<StChSalesProjectionMaster>() {
            @Override
            public StChSalesProjectionMaster toEntityModel() {
                return _nullStChSalesProjectionMaster;
            }
        };

    public StChSalesProjectionMasterPersistenceImpl() {
        setModelClass(StChSalesProjectionMaster.class);
    }

    /**
     * Caches the st ch sales projection master in the entity cache if it is enabled.
     *
     * @param stChSalesProjectionMaster the st ch sales projection master
     */
    @Override
    public void cacheResult(StChSalesProjectionMaster stChSalesProjectionMaster) {
        EntityCacheUtil.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterImpl.class,
            stChSalesProjectionMaster.getPrimaryKey(), stChSalesProjectionMaster);

        stChSalesProjectionMaster.resetOriginalValues();
    }

    /**
     * Caches the st ch sales projection masters in the entity cache if it is enabled.
     *
     * @param stChSalesProjectionMasters the st ch sales projection masters
     */
    @Override
    public void cacheResult(
        List<StChSalesProjectionMaster> stChSalesProjectionMasters) {
        for (StChSalesProjectionMaster stChSalesProjectionMaster : stChSalesProjectionMasters) {
            if (EntityCacheUtil.getResult(
                        StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StChSalesProjectionMasterImpl.class,
                        stChSalesProjectionMaster.getPrimaryKey()) == null) {
                cacheResult(stChSalesProjectionMaster);
            } else {
                stChSalesProjectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st ch sales projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StChSalesProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StChSalesProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st ch sales projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StChSalesProjectionMaster stChSalesProjectionMaster) {
        EntityCacheUtil.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterImpl.class,
            stChSalesProjectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StChSalesProjectionMaster> stChSalesProjectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StChSalesProjectionMaster stChSalesProjectionMaster : stChSalesProjectionMasters) {
            EntityCacheUtil.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                StChSalesProjectionMasterImpl.class,
                stChSalesProjectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
     *
     * @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
     * @return the new st ch sales projection master
     */
    @Override
    public StChSalesProjectionMaster create(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
        StChSalesProjectionMaster stChSalesProjectionMaster = new StChSalesProjectionMasterImpl();

        stChSalesProjectionMaster.setNew(true);
        stChSalesProjectionMaster.setPrimaryKey(stChSalesProjectionMasterPK);

        return stChSalesProjectionMaster;
    }

    /**
     * Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
     * @return the st ch sales projection master that was removed
     * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster remove(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws NoSuchStChSalesProjectionMasterException, SystemException {
        return remove((Serializable) stChSalesProjectionMasterPK);
    }

    /**
     * Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st ch sales projection master
     * @return the st ch sales projection master that was removed
     * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster remove(Serializable primaryKey)
        throws NoSuchStChSalesProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StChSalesProjectionMaster stChSalesProjectionMaster = (StChSalesProjectionMaster) session.get(StChSalesProjectionMasterImpl.class,
                    primaryKey);

            if (stChSalesProjectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stChSalesProjectionMaster);
        } catch (NoSuchStChSalesProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StChSalesProjectionMaster removeImpl(
        StChSalesProjectionMaster stChSalesProjectionMaster)
        throws SystemException {
        stChSalesProjectionMaster = toUnwrappedModel(stChSalesProjectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stChSalesProjectionMaster)) {
                stChSalesProjectionMaster = (StChSalesProjectionMaster) session.get(StChSalesProjectionMasterImpl.class,
                        stChSalesProjectionMaster.getPrimaryKeyObj());
            }

            if (stChSalesProjectionMaster != null) {
                session.delete(stChSalesProjectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stChSalesProjectionMaster != null) {
            clearCache(stChSalesProjectionMaster);
        }

        return stChSalesProjectionMaster;
    }

    @Override
    public StChSalesProjectionMaster updateImpl(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster)
        throws SystemException {
        stChSalesProjectionMaster = toUnwrappedModel(stChSalesProjectionMaster);

        boolean isNew = stChSalesProjectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stChSalesProjectionMaster.isNew()) {
                session.save(stChSalesProjectionMaster);

                stChSalesProjectionMaster.setNew(false);
            } else {
                session.merge(stChSalesProjectionMaster);
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

        EntityCacheUtil.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChSalesProjectionMasterImpl.class,
            stChSalesProjectionMaster.getPrimaryKey(), stChSalesProjectionMaster);

        return stChSalesProjectionMaster;
    }

    protected StChSalesProjectionMaster toUnwrappedModel(
        StChSalesProjectionMaster stChSalesProjectionMaster) {
        if (stChSalesProjectionMaster instanceof StChSalesProjectionMasterImpl) {
            return stChSalesProjectionMaster;
        }

        StChSalesProjectionMasterImpl stChSalesProjectionMasterImpl = new StChSalesProjectionMasterImpl();

        stChSalesProjectionMasterImpl.setNew(stChSalesProjectionMaster.isNew());
        stChSalesProjectionMasterImpl.setPrimaryKey(stChSalesProjectionMaster.getPrimaryKey());

        stChSalesProjectionMasterImpl.setLastModifiedDate(stChSalesProjectionMaster.getLastModifiedDate());
        stChSalesProjectionMasterImpl.setCheckRecord(stChSalesProjectionMaster.isCheckRecord());
        stChSalesProjectionMasterImpl.setCalculationPeriods(stChSalesProjectionMaster.getCalculationPeriods());
        stChSalesProjectionMasterImpl.setProjectionDetailsSid(stChSalesProjectionMaster.getProjectionDetailsSid());
        stChSalesProjectionMasterImpl.setUserId(stChSalesProjectionMaster.getUserId());
        stChSalesProjectionMasterImpl.setSessionId(stChSalesProjectionMaster.getSessionId());
        stChSalesProjectionMasterImpl.setMethodology(stChSalesProjectionMaster.getMethodology());

        return stChSalesProjectionMasterImpl;
    }

    /**
     * Returns the st ch sales projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st ch sales projection master
     * @return the st ch sales projection master
     * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStChSalesProjectionMasterException, SystemException {
        StChSalesProjectionMaster stChSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

        if (stChSalesProjectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stChSalesProjectionMaster;
    }

    /**
     * Returns the st ch sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchStChSalesProjectionMasterException} if it could not be found.
     *
     * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
     * @return the st ch sales projection master
     * @throws com.stpl.app.NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster findByPrimaryKey(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws NoSuchStChSalesProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) stChSalesProjectionMasterPK);
    }

    /**
     * Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st ch sales projection master
     * @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StChSalesProjectionMaster stChSalesProjectionMaster = (StChSalesProjectionMaster) EntityCacheUtil.getResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                StChSalesProjectionMasterImpl.class, primaryKey);

        if (stChSalesProjectionMaster == _nullStChSalesProjectionMaster) {
            return null;
        }

        if (stChSalesProjectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stChSalesProjectionMaster = (StChSalesProjectionMaster) session.get(StChSalesProjectionMasterImpl.class,
                        primaryKey);

                if (stChSalesProjectionMaster != null) {
                    cacheResult(stChSalesProjectionMaster);
                } else {
                    EntityCacheUtil.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StChSalesProjectionMasterImpl.class, primaryKey,
                        _nullStChSalesProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StChSalesProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stChSalesProjectionMaster;
    }

    /**
     * Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
     * @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChSalesProjectionMaster fetchByPrimaryKey(
        StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stChSalesProjectionMasterPK);
    }

    /**
     * Returns all the st ch sales projection masters.
     *
     * @return the st ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChSalesProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st ch sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch sales projection masters
     * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
     * @return the range of st ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChSalesProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st ch sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch sales projection masters
     * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChSalesProjectionMaster> findAll(int start, int end,
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

        List<StChSalesProjectionMaster> list = (List<StChSalesProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCHSALESPROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCHSALESPROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(StChSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StChSalesProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StChSalesProjectionMaster>(list);
                } else {
                    list = (List<StChSalesProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the st ch sales projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StChSalesProjectionMaster stChSalesProjectionMaster : findAll()) {
            remove(stChSalesProjectionMaster);
        }
    }

    /**
     * Returns the number of st ch sales projection masters.
     *
     * @return the number of st ch sales projection masters
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

                Query q = session.createQuery(_SQL_COUNT_STCHSALESPROJECTIONMASTER);

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
     * Initializes the st ch sales projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StChSalesProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StChSalesProjectionMaster>> listenersList = new ArrayList<ModelListener<StChSalesProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StChSalesProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StChSalesProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
