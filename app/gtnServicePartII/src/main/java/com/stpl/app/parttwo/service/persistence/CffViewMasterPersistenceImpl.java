package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffViewMasterException;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.model.impl.CffViewMasterImpl;
import com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffViewMasterPersistence;

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
 * The persistence implementation for the cff view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffViewMasterPersistence
 * @see CffViewMasterUtil
 * @generated
 */
public class CffViewMasterPersistenceImpl extends BasePersistenceImpl<CffViewMaster>
    implements CffViewMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffViewMasterUtil} to access the cff view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffViewMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CffViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CffViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFVIEWMASTER = "SELECT cffViewMaster FROM CffViewMaster cffViewMaster";
    private static final String _SQL_COUNT_CFFVIEWMASTER = "SELECT COUNT(cffViewMaster) FROM CffViewMaster cffViewMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffViewMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffViewMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffViewMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "viewType", "cffViewMasterSid",
                "cffMasterSid", "modifiedBy", "modifiedDate", "viewName"
            });
    private static CffViewMaster _nullCffViewMaster = new CffViewMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffViewMaster> toCacheModel() {
                return _nullCffViewMasterCacheModel;
            }
        };

    private static CacheModel<CffViewMaster> _nullCffViewMasterCacheModel = new CacheModel<CffViewMaster>() {
            @Override
            public CffViewMaster toEntityModel() {
                return _nullCffViewMaster;
            }
        };

    public CffViewMasterPersistenceImpl() {
        setModelClass(CffViewMaster.class);
    }

    /**
     * Caches the cff view master in the entity cache if it is enabled.
     *
     * @param cffViewMaster the cff view master
     */
    @Override
    public void cacheResult(CffViewMaster cffViewMaster) {
        EntityCacheUtil.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterImpl.class, cffViewMaster.getPrimaryKey(),
            cffViewMaster);

        cffViewMaster.resetOriginalValues();
    }

    /**
     * Caches the cff view masters in the entity cache if it is enabled.
     *
     * @param cffViewMasters the cff view masters
     */
    @Override
    public void cacheResult(List<CffViewMaster> cffViewMasters) {
        for (CffViewMaster cffViewMaster : cffViewMasters) {
            if (EntityCacheUtil.getResult(
                        CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffViewMasterImpl.class, cffViewMaster.getPrimaryKey()) == null) {
                cacheResult(cffViewMaster);
            } else {
                cffViewMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff view masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffViewMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffViewMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff view master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffViewMaster cffViewMaster) {
        EntityCacheUtil.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterImpl.class, cffViewMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffViewMaster> cffViewMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffViewMaster cffViewMaster : cffViewMasters) {
            EntityCacheUtil.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffViewMasterImpl.class, cffViewMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff view master with the primary key. Does not add the cff view master to the database.
     *
     * @param cffViewMasterSid the primary key for the new cff view master
     * @return the new cff view master
     */
    @Override
    public CffViewMaster create(int cffViewMasterSid) {
        CffViewMaster cffViewMaster = new CffViewMasterImpl();

        cffViewMaster.setNew(true);
        cffViewMaster.setPrimaryKey(cffViewMasterSid);

        return cffViewMaster;
    }

    /**
     * Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffViewMasterSid the primary key of the cff view master
     * @return the cff view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster remove(int cffViewMasterSid)
        throws NoSuchCffViewMasterException, SystemException {
        return remove((Serializable) cffViewMasterSid);
    }

    /**
     * Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff view master
     * @return the cff view master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster remove(Serializable primaryKey)
        throws NoSuchCffViewMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffViewMaster cffViewMaster = (CffViewMaster) session.get(CffViewMasterImpl.class,
                    primaryKey);

            if (cffViewMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffViewMaster);
        } catch (NoSuchCffViewMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffViewMaster removeImpl(CffViewMaster cffViewMaster)
        throws SystemException {
        cffViewMaster = toUnwrappedModel(cffViewMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffViewMaster)) {
                cffViewMaster = (CffViewMaster) session.get(CffViewMasterImpl.class,
                        cffViewMaster.getPrimaryKeyObj());
            }

            if (cffViewMaster != null) {
                session.delete(cffViewMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffViewMaster != null) {
            clearCache(cffViewMaster);
        }

        return cffViewMaster;
    }

    @Override
    public CffViewMaster updateImpl(
        com.stpl.app.parttwo.model.CffViewMaster cffViewMaster)
        throws SystemException {
        cffViewMaster = toUnwrappedModel(cffViewMaster);

        boolean isNew = cffViewMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffViewMaster.isNew()) {
                session.save(cffViewMaster);

                cffViewMaster.setNew(false);
            } else {
                session.merge(cffViewMaster);
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

        EntityCacheUtil.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffViewMasterImpl.class, cffViewMaster.getPrimaryKey(),
            cffViewMaster);

        return cffViewMaster;
    }

    protected CffViewMaster toUnwrappedModel(CffViewMaster cffViewMaster) {
        if (cffViewMaster instanceof CffViewMasterImpl) {
            return cffViewMaster;
        }

        CffViewMasterImpl cffViewMasterImpl = new CffViewMasterImpl();

        cffViewMasterImpl.setNew(cffViewMaster.isNew());
        cffViewMasterImpl.setPrimaryKey(cffViewMaster.getPrimaryKey());

        cffViewMasterImpl.setCreatedDate(cffViewMaster.getCreatedDate());
        cffViewMasterImpl.setCreatedBy(cffViewMaster.getCreatedBy());
        cffViewMasterImpl.setViewType(cffViewMaster.getViewType());
        cffViewMasterImpl.setCffViewMasterSid(cffViewMaster.getCffViewMasterSid());
        cffViewMasterImpl.setCffMasterSid(cffViewMaster.getCffMasterSid());
        cffViewMasterImpl.setModifiedBy(cffViewMaster.getModifiedBy());
        cffViewMasterImpl.setModifiedDate(cffViewMaster.getModifiedDate());
        cffViewMasterImpl.setViewName(cffViewMaster.getViewName());

        return cffViewMasterImpl;
    }

    /**
     * Returns the cff view master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff view master
     * @return the cff view master
     * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffViewMasterException, SystemException {
        CffViewMaster cffViewMaster = fetchByPrimaryKey(primaryKey);

        if (cffViewMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffViewMaster;
    }

    /**
     * Returns the cff view master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffViewMasterException} if it could not be found.
     *
     * @param cffViewMasterSid the primary key of the cff view master
     * @return the cff view master
     * @throws com.stpl.app.parttwo.NoSuchCffViewMasterException if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster findByPrimaryKey(int cffViewMasterSid)
        throws NoSuchCffViewMasterException, SystemException {
        return findByPrimaryKey((Serializable) cffViewMasterSid);
    }

    /**
     * Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff view master
     * @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffViewMaster cffViewMaster = (CffViewMaster) EntityCacheUtil.getResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffViewMasterImpl.class, primaryKey);

        if (cffViewMaster == _nullCffViewMaster) {
            return null;
        }

        if (cffViewMaster == null) {
            Session session = null;

            try {
                session = openSession();

                cffViewMaster = (CffViewMaster) session.get(CffViewMasterImpl.class,
                        primaryKey);

                if (cffViewMaster != null) {
                    cacheResult(cffViewMaster);
                } else {
                    EntityCacheUtil.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffViewMasterImpl.class, primaryKey, _nullCffViewMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                    CffViewMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffViewMaster;
    }

    /**
     * Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffViewMasterSid the primary key of the cff view master
     * @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffViewMaster fetchByPrimaryKey(int cffViewMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffViewMasterSid);
    }

    /**
     * Returns all the cff view masters.
     *
     * @return the cff view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffViewMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff view masters
     * @param end the upper bound of the range of cff view masters (not inclusive)
     * @return the range of cff view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffViewMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff view masters
     * @param end the upper bound of the range of cff view masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffViewMaster> findAll(int start, int end,
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

        List<CffViewMaster> list = (List<CffViewMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFVIEWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFVIEWMASTER;

                if (pagination) {
                    sql = sql.concat(CffViewMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffViewMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffViewMaster>(list);
                } else {
                    list = (List<CffViewMaster>) QueryUtil.list(q,
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
     * Removes all the cff view masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffViewMaster cffViewMaster : findAll()) {
            remove(cffViewMaster);
        }
    }

    /**
     * Returns the number of cff view masters.
     *
     * @return the number of cff view masters
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

                Query q = session.createQuery(_SQL_COUNT_CFFVIEWMASTER);

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
     * Initializes the cff view master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffViewMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffViewMaster>> listenersList = new ArrayList<ModelListener<CffViewMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffViewMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffViewMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
