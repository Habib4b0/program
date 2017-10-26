package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChSalesProjectionMasterException;
import com.stpl.app.model.ChSalesProjectionMaster;
import com.stpl.app.model.impl.ChSalesProjectionMasterImpl;
import com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.ChSalesProjectionMasterPersistence;

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
 * The persistence implementation for the ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionMasterPersistence
 * @see ChSalesProjectionMasterUtil
 * @generated
 */
public class ChSalesProjectionMasterPersistenceImpl extends BasePersistenceImpl<ChSalesProjectionMaster>
    implements ChSalesProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChSalesProjectionMasterUtil} to access the ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChSalesProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            ChSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            ChSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHSALESPROJECTIONMASTER = "SELECT chSalesProjectionMaster FROM ChSalesProjectionMaster chSalesProjectionMaster";
    private static final String _SQL_COUNT_CHSALESPROJECTIONMASTER = "SELECT COUNT(chSalesProjectionMaster) FROM ChSalesProjectionMaster chSalesProjectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chSalesProjectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChSalesProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChSalesProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "calculationPeriods", "projectionDetailsSid",
                "methodology"
            });
    private static ChSalesProjectionMaster _nullChSalesProjectionMaster = new ChSalesProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChSalesProjectionMaster> toCacheModel() {
                return _nullChSalesProjectionMasterCacheModel;
            }
        };

    private static CacheModel<ChSalesProjectionMaster> _nullChSalesProjectionMasterCacheModel =
        new CacheModel<ChSalesProjectionMaster>() {
            @Override
            public ChSalesProjectionMaster toEntityModel() {
                return _nullChSalesProjectionMaster;
            }
        };

    public ChSalesProjectionMasterPersistenceImpl() {
        setModelClass(ChSalesProjectionMaster.class);
    }

    /**
     * Caches the ch sales projection master in the entity cache if it is enabled.
     *
     * @param chSalesProjectionMaster the ch sales projection master
     */
    @Override
    public void cacheResult(ChSalesProjectionMaster chSalesProjectionMaster) {
        EntityCacheUtil.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterImpl.class,
            chSalesProjectionMaster.getPrimaryKey(), chSalesProjectionMaster);

        chSalesProjectionMaster.resetOriginalValues();
    }

    /**
     * Caches the ch sales projection masters in the entity cache if it is enabled.
     *
     * @param chSalesProjectionMasters the ch sales projection masters
     */
    @Override
    public void cacheResult(
        List<ChSalesProjectionMaster> chSalesProjectionMasters) {
        for (ChSalesProjectionMaster chSalesProjectionMaster : chSalesProjectionMasters) {
            if (EntityCacheUtil.getResult(
                        ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ChSalesProjectionMasterImpl.class,
                        chSalesProjectionMaster.getPrimaryKey()) == null) {
                cacheResult(chSalesProjectionMaster);
            } else {
                chSalesProjectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch sales projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChSalesProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChSalesProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch sales projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChSalesProjectionMaster chSalesProjectionMaster) {
        EntityCacheUtil.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterImpl.class,
            chSalesProjectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ChSalesProjectionMaster> chSalesProjectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChSalesProjectionMaster chSalesProjectionMaster : chSalesProjectionMasters) {
            EntityCacheUtil.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                ChSalesProjectionMasterImpl.class,
                chSalesProjectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch sales projection master with the primary key. Does not add the ch sales projection master to the database.
     *
     * @param projectionDetailsSid the primary key for the new ch sales projection master
     * @return the new ch sales projection master
     */
    @Override
    public ChSalesProjectionMaster create(int projectionDetailsSid) {
        ChSalesProjectionMaster chSalesProjectionMaster = new ChSalesProjectionMasterImpl();

        chSalesProjectionMaster.setNew(true);
        chSalesProjectionMaster.setPrimaryKey(projectionDetailsSid);

        return chSalesProjectionMaster;
    }

    /**
     * Removes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the ch sales projection master
     * @return the ch sales projection master that was removed
     * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster remove(int projectionDetailsSid)
        throws NoSuchChSalesProjectionMasterException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch sales projection master
     * @return the ch sales projection master that was removed
     * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster remove(Serializable primaryKey)
        throws NoSuchChSalesProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChSalesProjectionMaster chSalesProjectionMaster = (ChSalesProjectionMaster) session.get(ChSalesProjectionMasterImpl.class,
                    primaryKey);

            if (chSalesProjectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chSalesProjectionMaster);
        } catch (NoSuchChSalesProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChSalesProjectionMaster removeImpl(
        ChSalesProjectionMaster chSalesProjectionMaster)
        throws SystemException {
        chSalesProjectionMaster = toUnwrappedModel(chSalesProjectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chSalesProjectionMaster)) {
                chSalesProjectionMaster = (ChSalesProjectionMaster) session.get(ChSalesProjectionMasterImpl.class,
                        chSalesProjectionMaster.getPrimaryKeyObj());
            }

            if (chSalesProjectionMaster != null) {
                session.delete(chSalesProjectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chSalesProjectionMaster != null) {
            clearCache(chSalesProjectionMaster);
        }

        return chSalesProjectionMaster;
    }

    @Override
    public ChSalesProjectionMaster updateImpl(
        com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster)
        throws SystemException {
        chSalesProjectionMaster = toUnwrappedModel(chSalesProjectionMaster);

        boolean isNew = chSalesProjectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chSalesProjectionMaster.isNew()) {
                session.save(chSalesProjectionMaster);

                chSalesProjectionMaster.setNew(false);
            } else {
                session.merge(chSalesProjectionMaster);
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

        EntityCacheUtil.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ChSalesProjectionMasterImpl.class,
            chSalesProjectionMaster.getPrimaryKey(), chSalesProjectionMaster);

        return chSalesProjectionMaster;
    }

    protected ChSalesProjectionMaster toUnwrappedModel(
        ChSalesProjectionMaster chSalesProjectionMaster) {
        if (chSalesProjectionMaster instanceof ChSalesProjectionMasterImpl) {
            return chSalesProjectionMaster;
        }

        ChSalesProjectionMasterImpl chSalesProjectionMasterImpl = new ChSalesProjectionMasterImpl();

        chSalesProjectionMasterImpl.setNew(chSalesProjectionMaster.isNew());
        chSalesProjectionMasterImpl.setPrimaryKey(chSalesProjectionMaster.getPrimaryKey());

        chSalesProjectionMasterImpl.setCheckRecord(chSalesProjectionMaster.isCheckRecord());
        chSalesProjectionMasterImpl.setCalculationPeriods(chSalesProjectionMaster.getCalculationPeriods());
        chSalesProjectionMasterImpl.setProjectionDetailsSid(chSalesProjectionMaster.getProjectionDetailsSid());
        chSalesProjectionMasterImpl.setMethodology(chSalesProjectionMaster.getMethodology());

        return chSalesProjectionMasterImpl;
    }

    /**
     * Returns the ch sales projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch sales projection master
     * @return the ch sales projection master
     * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChSalesProjectionMasterException, SystemException {
        ChSalesProjectionMaster chSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

        if (chSalesProjectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chSalesProjectionMaster;
    }

    /**
     * Returns the ch sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchChSalesProjectionMasterException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the ch sales projection master
     * @return the ch sales projection master
     * @throws com.stpl.app.NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchChSalesProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch sales projection master
     * @return the ch sales projection master, or <code>null</code> if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChSalesProjectionMaster chSalesProjectionMaster = (ChSalesProjectionMaster) EntityCacheUtil.getResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                ChSalesProjectionMasterImpl.class, primaryKey);

        if (chSalesProjectionMaster == _nullChSalesProjectionMaster) {
            return null;
        }

        if (chSalesProjectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                chSalesProjectionMaster = (ChSalesProjectionMaster) session.get(ChSalesProjectionMasterImpl.class,
                        primaryKey);

                if (chSalesProjectionMaster != null) {
                    cacheResult(chSalesProjectionMaster);
                } else {
                    EntityCacheUtil.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ChSalesProjectionMasterImpl.class, primaryKey,
                        _nullChSalesProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ChSalesProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chSalesProjectionMaster;
    }

    /**
     * Returns the ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the ch sales projection master
     * @return the ch sales projection master, or <code>null</code> if a ch sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChSalesProjectionMaster fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the ch sales projection masters.
     *
     * @return the ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch sales projection masters
     * @param end the upper bound of the range of ch sales projection masters (not inclusive)
     * @return the range of ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch sales projection masters
     * @param end the upper bound of the range of ch sales projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChSalesProjectionMaster> findAll(int start, int end,
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

        List<ChSalesProjectionMaster> list = (List<ChSalesProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHSALESPROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHSALESPROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(ChSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChSalesProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChSalesProjectionMaster>(list);
                } else {
                    list = (List<ChSalesProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the ch sales projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChSalesProjectionMaster chSalesProjectionMaster : findAll()) {
            remove(chSalesProjectionMaster);
        }
    }

    /**
     * Returns the number of ch sales projection masters.
     *
     * @return the number of ch sales projection masters
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

                Query q = session.createQuery(_SQL_COUNT_CHSALESPROJECTIONMASTER);

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
     * Initializes the ch sales projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChSalesProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChSalesProjectionMaster>> listenersList = new ArrayList<ModelListener<ChSalesProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChSalesProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChSalesProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
