package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffCustomViewMasterException;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.model.impl.CffCustomViewMasterImpl;
import com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffCustomViewMasterPersistence;

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
 * The persistence implementation for the cff custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewMasterPersistence
 * @see CffCustomViewMasterUtil
 * @generated
 */
public class CffCustomViewMasterPersistenceImpl extends BasePersistenceImpl<CffCustomViewMaster>
    implements CffCustomViewMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffCustomViewMasterUtil} to access the cff custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffCustomViewMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CffCustomViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CffCustomViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFCUSTOMVIEWMASTER = "SELECT cffCustomViewMaster FROM CffCustomViewMaster cffCustomViewMaster";
    private static final String _SQL_COUNT_CFFCUSTOMVIEWMASTER = "SELECT COUNT(cffCustomViewMaster) FROM CffCustomViewMaster cffCustomViewMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffCustomViewMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffCustomViewMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffCustomViewMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "cffMasterSid", "modifiedBy",
                "cffCustomViewMasterSid", "modifiedDate", "viewName"
            });
    private static CffCustomViewMaster _nullCffCustomViewMaster = new CffCustomViewMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffCustomViewMaster> toCacheModel() {
                return _nullCffCustomViewMasterCacheModel;
            }
        };

    private static CacheModel<CffCustomViewMaster> _nullCffCustomViewMasterCacheModel =
        new CacheModel<CffCustomViewMaster>() {
            @Override
            public CffCustomViewMaster toEntityModel() {
                return _nullCffCustomViewMaster;
            }
        };

    public CffCustomViewMasterPersistenceImpl() {
        setModelClass(CffCustomViewMaster.class);
    }

    /**
     * Caches the cff custom view master in the entity cache if it is enabled.
     *
     * @param cffCustomViewMaster the cff custom view master
     */
    @Override
    public void cacheResult(CffCustomViewMaster cffCustomViewMaster) {
        EntityCacheUtil.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey(),
            cffCustomViewMaster);

        cffCustomViewMaster.resetOriginalValues();
    }

    /**
     * Caches the cff custom view masters in the entity cache if it is enabled.
     *
     * @param cffCustomViewMasters the cff custom view masters
     */
    @Override
    public void cacheResult(List<CffCustomViewMaster> cffCustomViewMasters) {
        for (CffCustomViewMaster cffCustomViewMaster : cffCustomViewMasters) {
            if (EntityCacheUtil.getResult(
                        CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffCustomViewMasterImpl.class,
                        cffCustomViewMaster.getPrimaryKey()) == null) {
                cacheResult(cffCustomViewMaster);
            } else {
                cffCustomViewMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff custom view masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffCustomViewMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffCustomViewMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff custom view master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffCustomViewMaster cffCustomViewMaster) {
        EntityCacheUtil.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffCustomViewMaster> cffCustomViewMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffCustomViewMaster cffCustomViewMaster : cffCustomViewMasters) {
            EntityCacheUtil.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffCustomViewMasterImpl.class,
                cffCustomViewMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
     *
     * @param cffCustomViewMasterSid the primary key for the new cff custom view master
     * @return the new cff custom view master
     */
    @Override
    public CffCustomViewMaster create(int cffCustomViewMasterSid) {
        CffCustomViewMaster cffCustomViewMaster = new CffCustomViewMasterImpl();

        cffCustomViewMaster.setNew(true);
        cffCustomViewMaster.setPrimaryKey(cffCustomViewMasterSid);

        return cffCustomViewMaster;
    }

    /**
     * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffCustomViewMasterSid the primary key of the cff custom view master
     * @return the cff custom view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster remove(int cffCustomViewMasterSid)
        throws NoSuchCffCustomViewMasterException, SystemException {
        return remove((Serializable) cffCustomViewMasterSid);
    }

    /**
     * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff custom view master
     * @return the cff custom view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster remove(Serializable primaryKey)
        throws NoSuchCffCustomViewMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffCustomViewMaster cffCustomViewMaster = (CffCustomViewMaster) session.get(CffCustomViewMasterImpl.class,
                    primaryKey);

            if (cffCustomViewMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffCustomViewMaster);
        } catch (NoSuchCffCustomViewMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffCustomViewMaster removeImpl(
        CffCustomViewMaster cffCustomViewMaster) throws SystemException {
        cffCustomViewMaster = toUnwrappedModel(cffCustomViewMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffCustomViewMaster)) {
                cffCustomViewMaster = (CffCustomViewMaster) session.get(CffCustomViewMasterImpl.class,
                        cffCustomViewMaster.getPrimaryKeyObj());
            }

            if (cffCustomViewMaster != null) {
                session.delete(cffCustomViewMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffCustomViewMaster != null) {
            clearCache(cffCustomViewMaster);
        }

        return cffCustomViewMaster;
    }

    @Override
    public CffCustomViewMaster updateImpl(
        com.stpl.app.parttwo.model.CffCustomViewMaster cffCustomViewMaster)
        throws SystemException {
        cffCustomViewMaster = toUnwrappedModel(cffCustomViewMaster);

        boolean isNew = cffCustomViewMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffCustomViewMaster.isNew()) {
                session.save(cffCustomViewMaster);

                cffCustomViewMaster.setNew(false);
            } else {
                session.merge(cffCustomViewMaster);
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

        EntityCacheUtil.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey(),
            cffCustomViewMaster);

        return cffCustomViewMaster;
    }

    protected CffCustomViewMaster toUnwrappedModel(
        CffCustomViewMaster cffCustomViewMaster) {
        if (cffCustomViewMaster instanceof CffCustomViewMasterImpl) {
            return cffCustomViewMaster;
        }

        CffCustomViewMasterImpl cffCustomViewMasterImpl = new CffCustomViewMasterImpl();

        cffCustomViewMasterImpl.setNew(cffCustomViewMaster.isNew());
        cffCustomViewMasterImpl.setPrimaryKey(cffCustomViewMaster.getPrimaryKey());

        cffCustomViewMasterImpl.setCreatedDate(cffCustomViewMaster.getCreatedDate());
        cffCustomViewMasterImpl.setCreatedBy(cffCustomViewMaster.getCreatedBy());
        cffCustomViewMasterImpl.setCffMasterSid(cffCustomViewMaster.getCffMasterSid());
        cffCustomViewMasterImpl.setModifiedBy(cffCustomViewMaster.getModifiedBy());
        cffCustomViewMasterImpl.setCffCustomViewMasterSid(cffCustomViewMaster.getCffCustomViewMasterSid());
        cffCustomViewMasterImpl.setModifiedDate(cffCustomViewMaster.getModifiedDate());
        cffCustomViewMasterImpl.setViewName(cffCustomViewMaster.getViewName());

        return cffCustomViewMasterImpl;
    }

    /**
     * Returns the cff custom view master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff custom view master
     * @return the cff custom view master
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffCustomViewMasterException, SystemException {
        CffCustomViewMaster cffCustomViewMaster = fetchByPrimaryKey(primaryKey);

        if (cffCustomViewMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffCustomViewMaster;
    }

    /**
     * Returns the cff custom view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffCustomViewMasterException} if it could not be found.
     *
     * @param cffCustomViewMasterSid the primary key of the cff custom view master
     * @return the cff custom view master
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster findByPrimaryKey(int cffCustomViewMasterSid)
        throws NoSuchCffCustomViewMasterException, SystemException {
        return findByPrimaryKey((Serializable) cffCustomViewMasterSid);
    }

    /**
     * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff custom view master
     * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffCustomViewMaster cffCustomViewMaster = (CffCustomViewMaster) EntityCacheUtil.getResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffCustomViewMasterImpl.class, primaryKey);

        if (cffCustomViewMaster == _nullCffCustomViewMaster) {
            return null;
        }

        if (cffCustomViewMaster == null) {
            Session session = null;

            try {
                session = openSession();

                cffCustomViewMaster = (CffCustomViewMaster) session.get(CffCustomViewMasterImpl.class,
                        primaryKey);

                if (cffCustomViewMaster != null) {
                    cacheResult(cffCustomViewMaster);
                } else {
                    EntityCacheUtil.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffCustomViewMasterImpl.class, primaryKey,
                        _nullCffCustomViewMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                    CffCustomViewMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffCustomViewMaster;
    }

    /**
     * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffCustomViewMasterSid the primary key of the cff custom view master
     * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewMaster fetchByPrimaryKey(int cffCustomViewMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffCustomViewMasterSid);
    }

    /**
     * Returns all the cff custom view masters.
     *
     * @return the cff custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff custom view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff custom view masters
     * @param end the upper bound of the range of cff custom view masters (not inclusive)
     * @return the range of cff custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff custom view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff custom view masters
     * @param end the upper bound of the range of cff custom view masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewMaster> findAll(int start, int end,
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

        List<CffCustomViewMaster> list = (List<CffCustomViewMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFCUSTOMVIEWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFCUSTOMVIEWMASTER;

                if (pagination) {
                    sql = sql.concat(CffCustomViewMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffCustomViewMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffCustomViewMaster>(list);
                } else {
                    list = (List<CffCustomViewMaster>) QueryUtil.list(q,
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
     * Removes all the cff custom view masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffCustomViewMaster cffCustomViewMaster : findAll()) {
            remove(cffCustomViewMaster);
        }
    }

    /**
     * Returns the number of cff custom view masters.
     *
     * @return the number of cff custom view masters
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

                Query q = session.createQuery(_SQL_COUNT_CFFCUSTOMVIEWMASTER);

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
     * Initializes the cff custom view master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffCustomViewMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffCustomViewMaster>> listenersList = new ArrayList<ModelListener<CffCustomViewMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffCustomViewMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffCustomViewMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
