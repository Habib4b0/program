package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAccClosureViewMasterException;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.model.impl.AccClosureViewMasterImpl;
import com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureViewMasterPersistence;

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
 * The persistence implementation for the acc closure view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureViewMasterPersistence
 * @see AccClosureViewMasterUtil
 * @generated
 */
public class AccClosureViewMasterPersistenceImpl extends BasePersistenceImpl<AccClosureViewMaster>
    implements AccClosureViewMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AccClosureViewMasterUtil} to access the acc closure view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AccClosureViewMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED,
            AccClosureViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED,
            AccClosureViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACCCLOSUREVIEWMASTER = "SELECT accClosureViewMaster FROM AccClosureViewMaster accClosureViewMaster";
    private static final String _SQL_COUNT_ACCCLOSUREVIEWMASTER = "SELECT COUNT(accClosureViewMaster) FROM AccClosureViewMaster accClosureViewMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureViewMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureViewMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AccClosureViewMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "viewType", "accClosureMasterSid",
                "modifiedBy", "accClosureViewMasterSid", "modifiedDate",
                "viewName"
            });
    private static AccClosureViewMaster _nullAccClosureViewMaster = new AccClosureViewMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AccClosureViewMaster> toCacheModel() {
                return _nullAccClosureViewMasterCacheModel;
            }
        };

    private static CacheModel<AccClosureViewMaster> _nullAccClosureViewMasterCacheModel =
        new CacheModel<AccClosureViewMaster>() {
            @Override
            public AccClosureViewMaster toEntityModel() {
                return _nullAccClosureViewMaster;
            }
        };

    public AccClosureViewMasterPersistenceImpl() {
        setModelClass(AccClosureViewMaster.class);
    }

    /**
     * Caches the acc closure view master in the entity cache if it is enabled.
     *
     * @param accClosureViewMaster the acc closure view master
     */
    @Override
    public void cacheResult(AccClosureViewMaster accClosureViewMaster) {
        EntityCacheUtil.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterImpl.class,
            accClosureViewMaster.getPrimaryKey(), accClosureViewMaster);

        accClosureViewMaster.resetOriginalValues();
    }

    /**
     * Caches the acc closure view masters in the entity cache if it is enabled.
     *
     * @param accClosureViewMasters the acc closure view masters
     */
    @Override
    public void cacheResult(List<AccClosureViewMaster> accClosureViewMasters) {
        for (AccClosureViewMaster accClosureViewMaster : accClosureViewMasters) {
            if (EntityCacheUtil.getResult(
                        AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureViewMasterImpl.class,
                        accClosureViewMaster.getPrimaryKey()) == null) {
                cacheResult(accClosureViewMaster);
            } else {
                accClosureViewMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all acc closure view masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AccClosureViewMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AccClosureViewMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the acc closure view master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AccClosureViewMaster accClosureViewMaster) {
        EntityCacheUtil.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterImpl.class, accClosureViewMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AccClosureViewMaster> accClosureViewMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AccClosureViewMaster accClosureViewMaster : accClosureViewMasters) {
            EntityCacheUtil.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureViewMasterImpl.class,
                accClosureViewMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
     *
     * @param accClosureViewMasterSid the primary key for the new acc closure view master
     * @return the new acc closure view master
     */
    @Override
    public AccClosureViewMaster create(int accClosureViewMasterSid) {
        AccClosureViewMaster accClosureViewMaster = new AccClosureViewMasterImpl();

        accClosureViewMaster.setNew(true);
        accClosureViewMaster.setPrimaryKey(accClosureViewMasterSid);

        return accClosureViewMaster;
    }

    /**
     * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accClosureViewMasterSid the primary key of the acc closure view master
     * @return the acc closure view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster remove(int accClosureViewMasterSid)
        throws NoSuchAccClosureViewMasterException, SystemException {
        return remove((Serializable) accClosureViewMasterSid);
    }

    /**
     * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the acc closure view master
     * @return the acc closure view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster remove(Serializable primaryKey)
        throws NoSuchAccClosureViewMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AccClosureViewMaster accClosureViewMaster = (AccClosureViewMaster) session.get(AccClosureViewMasterImpl.class,
                    primaryKey);

            if (accClosureViewMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAccClosureViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(accClosureViewMaster);
        } catch (NoSuchAccClosureViewMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AccClosureViewMaster removeImpl(
        AccClosureViewMaster accClosureViewMaster) throws SystemException {
        accClosureViewMaster = toUnwrappedModel(accClosureViewMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(accClosureViewMaster)) {
                accClosureViewMaster = (AccClosureViewMaster) session.get(AccClosureViewMasterImpl.class,
                        accClosureViewMaster.getPrimaryKeyObj());
            }

            if (accClosureViewMaster != null) {
                session.delete(accClosureViewMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (accClosureViewMaster != null) {
            clearCache(accClosureViewMaster);
        }

        return accClosureViewMaster;
    }

    @Override
    public AccClosureViewMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws SystemException {
        accClosureViewMaster = toUnwrappedModel(accClosureViewMaster);

        boolean isNew = accClosureViewMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (accClosureViewMaster.isNew()) {
                session.save(accClosureViewMaster);

                accClosureViewMaster.setNew(false);
            } else {
                session.merge(accClosureViewMaster);
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

        EntityCacheUtil.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureViewMasterImpl.class,
            accClosureViewMaster.getPrimaryKey(), accClosureViewMaster);

        return accClosureViewMaster;
    }

    protected AccClosureViewMaster toUnwrappedModel(
        AccClosureViewMaster accClosureViewMaster) {
        if (accClosureViewMaster instanceof AccClosureViewMasterImpl) {
            return accClosureViewMaster;
        }

        AccClosureViewMasterImpl accClosureViewMasterImpl = new AccClosureViewMasterImpl();

        accClosureViewMasterImpl.setNew(accClosureViewMaster.isNew());
        accClosureViewMasterImpl.setPrimaryKey(accClosureViewMaster.getPrimaryKey());

        accClosureViewMasterImpl.setCreatedDate(accClosureViewMaster.getCreatedDate());
        accClosureViewMasterImpl.setCreatedBy(accClosureViewMaster.getCreatedBy());
        accClosureViewMasterImpl.setViewType(accClosureViewMaster.getViewType());
        accClosureViewMasterImpl.setAccClosureMasterSid(accClosureViewMaster.getAccClosureMasterSid());
        accClosureViewMasterImpl.setModifiedBy(accClosureViewMaster.getModifiedBy());
        accClosureViewMasterImpl.setAccClosureViewMasterSid(accClosureViewMaster.getAccClosureViewMasterSid());
        accClosureViewMasterImpl.setModifiedDate(accClosureViewMaster.getModifiedDate());
        accClosureViewMasterImpl.setViewName(accClosureViewMaster.getViewName());

        return accClosureViewMasterImpl;
    }

    /**
     * Returns the acc closure view master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure view master
     * @return the acc closure view master
     * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAccClosureViewMasterException, SystemException {
        AccClosureViewMaster accClosureViewMaster = fetchByPrimaryKey(primaryKey);

        if (accClosureViewMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAccClosureViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return accClosureViewMaster;
    }

    /**
     * Returns the acc closure view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureViewMasterException} if it could not be found.
     *
     * @param accClosureViewMasterSid the primary key of the acc closure view master
     * @return the acc closure view master
     * @throws com.stpl.app.parttwo.NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster findByPrimaryKey(int accClosureViewMasterSid)
        throws NoSuchAccClosureViewMasterException, SystemException {
        return findByPrimaryKey((Serializable) accClosureViewMasterSid);
    }

    /**
     * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure view master
     * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AccClosureViewMaster accClosureViewMaster = (AccClosureViewMaster) EntityCacheUtil.getResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureViewMasterImpl.class, primaryKey);

        if (accClosureViewMaster == _nullAccClosureViewMaster) {
            return null;
        }

        if (accClosureViewMaster == null) {
            Session session = null;

            try {
                session = openSession();

                accClosureViewMaster = (AccClosureViewMaster) session.get(AccClosureViewMasterImpl.class,
                        primaryKey);

                if (accClosureViewMaster != null) {
                    cacheResult(accClosureViewMaster);
                } else {
                    EntityCacheUtil.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureViewMasterImpl.class, primaryKey,
                        _nullAccClosureViewMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                    AccClosureViewMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return accClosureViewMaster;
    }

    /**
     * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accClosureViewMasterSid the primary key of the acc closure view master
     * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureViewMaster fetchByPrimaryKey(int accClosureViewMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accClosureViewMasterSid);
    }

    /**
     * Returns all the acc closure view masters.
     *
     * @return the acc closure view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureViewMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the acc closure view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure view masters
     * @param end the upper bound of the range of acc closure view masters (not inclusive)
     * @return the range of acc closure view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureViewMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the acc closure view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure view masters
     * @param end the upper bound of the range of acc closure view masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of acc closure view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureViewMaster> findAll(int start, int end,
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

        List<AccClosureViewMaster> list = (List<AccClosureViewMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACCCLOSUREVIEWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACCCLOSUREVIEWMASTER;

                if (pagination) {
                    sql = sql.concat(AccClosureViewMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AccClosureViewMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AccClosureViewMaster>(list);
                } else {
                    list = (List<AccClosureViewMaster>) QueryUtil.list(q,
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
     * Removes all the acc closure view masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AccClosureViewMaster accClosureViewMaster : findAll()) {
            remove(accClosureViewMaster);
        }
    }

    /**
     * Returns the number of acc closure view masters.
     *
     * @return the number of acc closure view masters
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

                Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREVIEWMASTER);

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
     * Initializes the acc closure view master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AccClosureViewMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AccClosureViewMaster>> listenersList = new ArrayList<ModelListener<AccClosureViewMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AccClosureViewMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AccClosureViewMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
