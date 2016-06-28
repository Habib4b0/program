package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffProdHierarchyException;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.impl.CffProdHierarchyImpl;
import com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl;
import com.stpl.app.parttwo.service.persistence.CffProdHierarchyPersistence;

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
 * The persistence implementation for the cff prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffProdHierarchyPersistence
 * @see CffProdHierarchyUtil
 * @generated
 */
public class CffProdHierarchyPersistenceImpl extends BasePersistenceImpl<CffProdHierarchy>
    implements CffProdHierarchyPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffProdHierarchyUtil} to access the cff prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffProdHierarchyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
            CffProdHierarchyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
            CffProdHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFPRODHIERARCHY = "SELECT cffProdHierarchy FROM CffProdHierarchy cffProdHierarchy";
    private static final String _SQL_COUNT_CFFPRODHIERARCHY = "SELECT COUNT(cffProdHierarchy) FROM CffProdHierarchy cffProdHierarchy";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffProdHierarchy.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffProdHierarchy exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffProdHierarchyPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "cffMasterSid", "relationshipLevelSid", "cffProdHierarchySid"
            });
    private static CffProdHierarchy _nullCffProdHierarchy = new CffProdHierarchyImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffProdHierarchy> toCacheModel() {
                return _nullCffProdHierarchyCacheModel;
            }
        };

    private static CacheModel<CffProdHierarchy> _nullCffProdHierarchyCacheModel = new CacheModel<CffProdHierarchy>() {
            @Override
            public CffProdHierarchy toEntityModel() {
                return _nullCffProdHierarchy;
            }
        };

    public CffProdHierarchyPersistenceImpl() {
        setModelClass(CffProdHierarchy.class);
    }

    /**
     * Caches the cff prod hierarchy in the entity cache if it is enabled.
     *
     * @param cffProdHierarchy the cff prod hierarchy
     */
    @Override
    public void cacheResult(CffProdHierarchy cffProdHierarchy) {
        EntityCacheUtil.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey(),
            cffProdHierarchy);

        cffProdHierarchy.resetOriginalValues();
    }

    /**
     * Caches the cff prod hierarchies in the entity cache if it is enabled.
     *
     * @param cffProdHierarchies the cff prod hierarchies
     */
    @Override
    public void cacheResult(List<CffProdHierarchy> cffProdHierarchies) {
        for (CffProdHierarchy cffProdHierarchy : cffProdHierarchies) {
            if (EntityCacheUtil.getResult(
                        CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        CffProdHierarchyImpl.class,
                        cffProdHierarchy.getPrimaryKey()) == null) {
                cacheResult(cffProdHierarchy);
            } else {
                cffProdHierarchy.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff prod hierarchies.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffProdHierarchyImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffProdHierarchyImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff prod hierarchy.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffProdHierarchy cffProdHierarchy) {
        EntityCacheUtil.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffProdHierarchy> cffProdHierarchies) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffProdHierarchy cffProdHierarchy : cffProdHierarchies) {
            EntityCacheUtil.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
     *
     * @param cffProdHierarchySid the primary key for the new cff prod hierarchy
     * @return the new cff prod hierarchy
     */
    @Override
    public CffProdHierarchy create(int cffProdHierarchySid) {
        CffProdHierarchy cffProdHierarchy = new CffProdHierarchyImpl();

        cffProdHierarchy.setNew(true);
        cffProdHierarchy.setPrimaryKey(cffProdHierarchySid);

        return cffProdHierarchy;
    }

    /**
     * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffProdHierarchySid the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy remove(int cffProdHierarchySid)
        throws NoSuchCffProdHierarchyException, SystemException {
        return remove((Serializable) cffProdHierarchySid);
    }

    /**
     * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy remove(Serializable primaryKey)
        throws NoSuchCffProdHierarchyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffProdHierarchy cffProdHierarchy = (CffProdHierarchy) session.get(CffProdHierarchyImpl.class,
                    primaryKey);

            if (cffProdHierarchy == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffProdHierarchy);
        } catch (NoSuchCffProdHierarchyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffProdHierarchy removeImpl(CffProdHierarchy cffProdHierarchy)
        throws SystemException {
        cffProdHierarchy = toUnwrappedModel(cffProdHierarchy);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffProdHierarchy)) {
                cffProdHierarchy = (CffProdHierarchy) session.get(CffProdHierarchyImpl.class,
                        cffProdHierarchy.getPrimaryKeyObj());
            }

            if (cffProdHierarchy != null) {
                session.delete(cffProdHierarchy);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffProdHierarchy != null) {
            clearCache(cffProdHierarchy);
        }

        return cffProdHierarchy;
    }

    @Override
    public CffProdHierarchy updateImpl(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws SystemException {
        cffProdHierarchy = toUnwrappedModel(cffProdHierarchy);

        boolean isNew = cffProdHierarchy.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffProdHierarchy.isNew()) {
                session.save(cffProdHierarchy);

                cffProdHierarchy.setNew(false);
            } else {
                session.merge(cffProdHierarchy);
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

        EntityCacheUtil.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey(),
            cffProdHierarchy);

        return cffProdHierarchy;
    }

    protected CffProdHierarchy toUnwrappedModel(
        CffProdHierarchy cffProdHierarchy) {
        if (cffProdHierarchy instanceof CffProdHierarchyImpl) {
            return cffProdHierarchy;
        }

        CffProdHierarchyImpl cffProdHierarchyImpl = new CffProdHierarchyImpl();

        cffProdHierarchyImpl.setNew(cffProdHierarchy.isNew());
        cffProdHierarchyImpl.setPrimaryKey(cffProdHierarchy.getPrimaryKey());

        cffProdHierarchyImpl.setCffMasterSid(cffProdHierarchy.getCffMasterSid());
        cffProdHierarchyImpl.setRelationshipLevelSid(cffProdHierarchy.getRelationshipLevelSid());
        cffProdHierarchyImpl.setCffProdHierarchySid(cffProdHierarchy.getCffProdHierarchySid());

        return cffProdHierarchyImpl;
    }

    /**
     * Returns the cff prod hierarchy with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy
     * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffProdHierarchyException, SystemException {
        CffProdHierarchy cffProdHierarchy = fetchByPrimaryKey(primaryKey);

        if (cffProdHierarchy == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffProdHierarchy;
    }

    /**
     * Returns the cff prod hierarchy with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffProdHierarchyException} if it could not be found.
     *
     * @param cffProdHierarchySid the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy
     * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy findByPrimaryKey(int cffProdHierarchySid)
        throws NoSuchCffProdHierarchyException, SystemException {
        return findByPrimaryKey((Serializable) cffProdHierarchySid);
    }

    /**
     * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffProdHierarchy cffProdHierarchy = (CffProdHierarchy) EntityCacheUtil.getResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                CffProdHierarchyImpl.class, primaryKey);

        if (cffProdHierarchy == _nullCffProdHierarchy) {
            return null;
        }

        if (cffProdHierarchy == null) {
            Session session = null;

            try {
                session = openSession();

                cffProdHierarchy = (CffProdHierarchy) session.get(CffProdHierarchyImpl.class,
                        primaryKey);

                if (cffProdHierarchy != null) {
                    cacheResult(cffProdHierarchy);
                } else {
                    EntityCacheUtil.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        CffProdHierarchyImpl.class, primaryKey,
                        _nullCffProdHierarchy);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                    CffProdHierarchyImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffProdHierarchy;
    }

    /**
     * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffProdHierarchySid the primary key of the cff prod hierarchy
     * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffProdHierarchy fetchByPrimaryKey(int cffProdHierarchySid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffProdHierarchySid);
    }

    /**
     * Returns all the cff prod hierarchies.
     *
     * @return the cff prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffProdHierarchy> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff prod hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff prod hierarchies
     * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
     * @return the range of cff prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffProdHierarchy> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff prod hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff prod hierarchies
     * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffProdHierarchy> findAll(int start, int end,
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

        List<CffProdHierarchy> list = (List<CffProdHierarchy>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFPRODHIERARCHY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFPRODHIERARCHY;

                if (pagination) {
                    sql = sql.concat(CffProdHierarchyModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffProdHierarchy>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffProdHierarchy>(list);
                } else {
                    list = (List<CffProdHierarchy>) QueryUtil.list(q,
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
     * Removes all the cff prod hierarchies from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffProdHierarchy cffProdHierarchy : findAll()) {
            remove(cffProdHierarchy);
        }
    }

    /**
     * Returns the number of cff prod hierarchies.
     *
     * @return the number of cff prod hierarchies
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

                Query q = session.createQuery(_SQL_COUNT_CFFPRODHIERARCHY);

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
     * Initializes the cff prod hierarchy persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffProdHierarchy")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffProdHierarchy>> listenersList = new ArrayList<ModelListener<CffProdHierarchy>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffProdHierarchy>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffProdHierarchyImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
