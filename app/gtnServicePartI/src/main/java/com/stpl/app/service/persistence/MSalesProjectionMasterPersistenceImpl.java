package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMSalesProjectionMasterException;
import com.stpl.app.model.MSalesProjectionMaster;
import com.stpl.app.model.impl.MSalesProjectionMasterImpl;
import com.stpl.app.model.impl.MSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.MSalesProjectionMasterPersistence;

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
 * The persistence implementation for the m sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSalesProjectionMasterPersistence
 * @see MSalesProjectionMasterUtil
 * @generated
 */
public class MSalesProjectionMasterPersistenceImpl extends BasePersistenceImpl<MSalesProjectionMaster>
    implements MSalesProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MSalesProjectionMasterUtil} to access the m sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MSalesProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            MSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            MSalesProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MSALESPROJECTIONMASTER = "SELECT mSalesProjectionMaster FROM MSalesProjectionMaster mSalesProjectionMaster";
    private static final String _SQL_COUNT_MSALESPROJECTIONMASTER = "SELECT COUNT(mSalesProjectionMaster) FROM MSalesProjectionMaster mSalesProjectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mSalesProjectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSalesProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MSalesProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "methodology", "calculationPeriods", "calculationBased",
                "projectionDetailsSid", "checkRecord"
            });
    private static MSalesProjectionMaster _nullMSalesProjectionMaster = new MSalesProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MSalesProjectionMaster> toCacheModel() {
                return _nullMSalesProjectionMasterCacheModel;
            }
        };

    private static CacheModel<MSalesProjectionMaster> _nullMSalesProjectionMasterCacheModel =
        new CacheModel<MSalesProjectionMaster>() {
            @Override
            public MSalesProjectionMaster toEntityModel() {
                return _nullMSalesProjectionMaster;
            }
        };

    public MSalesProjectionMasterPersistenceImpl() {
        setModelClass(MSalesProjectionMaster.class);
    }

    /**
     * Caches the m sales projection master in the entity cache if it is enabled.
     *
     * @param mSalesProjectionMaster the m sales projection master
     */
    @Override
    public void cacheResult(MSalesProjectionMaster mSalesProjectionMaster) {
        EntityCacheUtil.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterImpl.class,
            mSalesProjectionMaster.getPrimaryKey(), mSalesProjectionMaster);

        mSalesProjectionMaster.resetOriginalValues();
    }

    /**
     * Caches the m sales projection masters in the entity cache if it is enabled.
     *
     * @param mSalesProjectionMasters the m sales projection masters
     */
    @Override
    public void cacheResult(
        List<MSalesProjectionMaster> mSalesProjectionMasters) {
        for (MSalesProjectionMaster mSalesProjectionMaster : mSalesProjectionMasters) {
            if (EntityCacheUtil.getResult(
                        MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MSalesProjectionMasterImpl.class,
                        mSalesProjectionMaster.getPrimaryKey()) == null) {
                cacheResult(mSalesProjectionMaster);
            } else {
                mSalesProjectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m sales projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MSalesProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MSalesProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m sales projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MSalesProjectionMaster mSalesProjectionMaster) {
        EntityCacheUtil.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterImpl.class,
            mSalesProjectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MSalesProjectionMaster> mSalesProjectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MSalesProjectionMaster mSalesProjectionMaster : mSalesProjectionMasters) {
            EntityCacheUtil.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                MSalesProjectionMasterImpl.class,
                mSalesProjectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
     *
     * @param projectionDetailsSid the primary key for the new m sales projection master
     * @return the new m sales projection master
     */
    @Override
    public MSalesProjectionMaster create(int projectionDetailsSid) {
        MSalesProjectionMaster mSalesProjectionMaster = new MSalesProjectionMasterImpl();

        mSalesProjectionMaster.setNew(true);
        mSalesProjectionMaster.setPrimaryKey(projectionDetailsSid);

        return mSalesProjectionMaster;
    }

    /**
     * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the m sales projection master
     * @return the m sales projection master that was removed
     * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster remove(int projectionDetailsSid)
        throws NoSuchMSalesProjectionMasterException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m sales projection master
     * @return the m sales projection master that was removed
     * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster remove(Serializable primaryKey)
        throws NoSuchMSalesProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MSalesProjectionMaster mSalesProjectionMaster = (MSalesProjectionMaster) session.get(MSalesProjectionMasterImpl.class,
                    primaryKey);

            if (mSalesProjectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mSalesProjectionMaster);
        } catch (NoSuchMSalesProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MSalesProjectionMaster removeImpl(
        MSalesProjectionMaster mSalesProjectionMaster)
        throws SystemException {
        mSalesProjectionMaster = toUnwrappedModel(mSalesProjectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mSalesProjectionMaster)) {
                mSalesProjectionMaster = (MSalesProjectionMaster) session.get(MSalesProjectionMasterImpl.class,
                        mSalesProjectionMaster.getPrimaryKeyObj());
            }

            if (mSalesProjectionMaster != null) {
                session.delete(mSalesProjectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mSalesProjectionMaster != null) {
            clearCache(mSalesProjectionMaster);
        }

        return mSalesProjectionMaster;
    }

    @Override
    public MSalesProjectionMaster updateImpl(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws SystemException {
        mSalesProjectionMaster = toUnwrappedModel(mSalesProjectionMaster);

        boolean isNew = mSalesProjectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mSalesProjectionMaster.isNew()) {
                session.save(mSalesProjectionMaster);

                mSalesProjectionMaster.setNew(false);
            } else {
                session.merge(mSalesProjectionMaster);
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

        EntityCacheUtil.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSalesProjectionMasterImpl.class,
            mSalesProjectionMaster.getPrimaryKey(), mSalesProjectionMaster);

        return mSalesProjectionMaster;
    }

    protected MSalesProjectionMaster toUnwrappedModel(
        MSalesProjectionMaster mSalesProjectionMaster) {
        if (mSalesProjectionMaster instanceof MSalesProjectionMasterImpl) {
            return mSalesProjectionMaster;
        }

        MSalesProjectionMasterImpl mSalesProjectionMasterImpl = new MSalesProjectionMasterImpl();

        mSalesProjectionMasterImpl.setNew(mSalesProjectionMaster.isNew());
        mSalesProjectionMasterImpl.setPrimaryKey(mSalesProjectionMaster.getPrimaryKey());

        mSalesProjectionMasterImpl.setMethodology(mSalesProjectionMaster.getMethodology());
        mSalesProjectionMasterImpl.setCalculationPeriods(mSalesProjectionMaster.getCalculationPeriods());
        mSalesProjectionMasterImpl.setCalculationBased(mSalesProjectionMaster.getCalculationBased());
        mSalesProjectionMasterImpl.setProjectionDetailsSid(mSalesProjectionMaster.getProjectionDetailsSid());
        mSalesProjectionMasterImpl.setCheckRecord(mSalesProjectionMaster.isCheckRecord());

        return mSalesProjectionMasterImpl;
    }

    /**
     * Returns the m sales projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m sales projection master
     * @return the m sales projection master
     * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMSalesProjectionMasterException, SystemException {
        MSalesProjectionMaster mSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

        if (mSalesProjectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mSalesProjectionMaster;
    }

    /**
     * Returns the m sales projection master with the primary key or throws a {@link com.stpl.app.NoSuchMSalesProjectionMasterException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m sales projection master
     * @return the m sales projection master
     * @throws com.stpl.app.NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchMSalesProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m sales projection master
     * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MSalesProjectionMaster mSalesProjectionMaster = (MSalesProjectionMaster) EntityCacheUtil.getResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                MSalesProjectionMasterImpl.class, primaryKey);

        if (mSalesProjectionMaster == _nullMSalesProjectionMaster) {
            return null;
        }

        if (mSalesProjectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                mSalesProjectionMaster = (MSalesProjectionMaster) session.get(MSalesProjectionMasterImpl.class,
                        primaryKey);

                if (mSalesProjectionMaster != null) {
                    cacheResult(mSalesProjectionMaster);
                } else {
                    EntityCacheUtil.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MSalesProjectionMasterImpl.class, primaryKey,
                        _nullMSalesProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    MSalesProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mSalesProjectionMaster;
    }

    /**
     * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m sales projection master
     * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSalesProjectionMaster fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the m sales projection masters.
     *
     * @return the m sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSalesProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m sales projection masters
     * @param end the upper bound of the range of m sales projection masters (not inclusive)
     * @return the range of m sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSalesProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m sales projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m sales projection masters
     * @param end the upper bound of the range of m sales projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m sales projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSalesProjectionMaster> findAll(int start, int end,
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

        List<MSalesProjectionMaster> list = (List<MSalesProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MSALESPROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MSALESPROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(MSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MSalesProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MSalesProjectionMaster>(list);
                } else {
                    list = (List<MSalesProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the m sales projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MSalesProjectionMaster mSalesProjectionMaster : findAll()) {
            remove(mSalesProjectionMaster);
        }
    }

    /**
     * Returns the number of m sales projection masters.
     *
     * @return the number of m sales projection masters
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

                Query q = session.createQuery(_SQL_COUNT_MSALESPROJECTIONMASTER);

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
     * Initializes the m sales projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MSalesProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MSalesProjectionMaster>> listenersList = new ArrayList<ModelListener<MSalesProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MSalesProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MSalesProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
