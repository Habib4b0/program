package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionProdHierarchyException;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.model.impl.ProjectionProdHierarchyImpl;
import com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl;
import com.stpl.app.service.persistence.ProjectionProdHierarchyPersistence;

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
 * The persistence implementation for the projection prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdHierarchyPersistence
 * @see ProjectionProdHierarchyUtil
 * @generated
 */
public class ProjectionProdHierarchyPersistenceImpl extends BasePersistenceImpl<ProjectionProdHierarchy>
    implements ProjectionProdHierarchyPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionProdHierarchyUtil} to access the projection prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionProdHierarchyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
            ProjectionProdHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
            ProjectionProdHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONPRODHIERARCHY = "SELECT projectionProdHierarchy FROM ProjectionProdHierarchy projectionProdHierarchy";
    private static final String _SQL_COUNT_PROJECTIONPRODHIERARCHY = "SELECT COUNT(projectionProdHierarchy) FROM ProjectionProdHierarchy projectionProdHierarchy";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionProdHierarchy.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionProdHierarchy exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionProdHierarchyPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "projectionMasterSid", "projectionProdHierarchySid",
                "relationshipLevelSid"
            });
    private static ProjectionProdHierarchy _nullProjectionProdHierarchy = new ProjectionProdHierarchyImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionProdHierarchy> toCacheModel() {
                return _nullProjectionProdHierarchyCacheModel;
            }
        };

    private static CacheModel<ProjectionProdHierarchy> _nullProjectionProdHierarchyCacheModel =
        new CacheModel<ProjectionProdHierarchy>() {
            @Override
            public ProjectionProdHierarchy toEntityModel() {
                return _nullProjectionProdHierarchy;
            }
        };

    public ProjectionProdHierarchyPersistenceImpl() {
        setModelClass(ProjectionProdHierarchy.class);
    }

    /**
     * Caches the projection prod hierarchy in the entity cache if it is enabled.
     *
     * @param projectionProdHierarchy the projection prod hierarchy
     */
    @Override
    public void cacheResult(ProjectionProdHierarchy projectionProdHierarchy) {
        EntityCacheUtil.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyImpl.class,
            projectionProdHierarchy.getPrimaryKey(), projectionProdHierarchy);

        projectionProdHierarchy.resetOriginalValues();
    }

    /**
     * Caches the projection prod hierarchies in the entity cache if it is enabled.
     *
     * @param projectionProdHierarchies the projection prod hierarchies
     */
    @Override
    public void cacheResult(
        List<ProjectionProdHierarchy> projectionProdHierarchies) {
        for (ProjectionProdHierarchy projectionProdHierarchy : projectionProdHierarchies) {
            if (EntityCacheUtil.getResult(
                        ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionProdHierarchyImpl.class,
                        projectionProdHierarchy.getPrimaryKey()) == null) {
                cacheResult(projectionProdHierarchy);
            } else {
                projectionProdHierarchy.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection prod hierarchies.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionProdHierarchyImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionProdHierarchyImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection prod hierarchy.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionProdHierarchy projectionProdHierarchy) {
        EntityCacheUtil.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyImpl.class,
            projectionProdHierarchy.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ProjectionProdHierarchy> projectionProdHierarchies) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionProdHierarchy projectionProdHierarchy : projectionProdHierarchies) {
            EntityCacheUtil.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionProdHierarchyImpl.class,
                projectionProdHierarchy.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
     *
     * @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
     * @return the new projection prod hierarchy
     */
    @Override
    public ProjectionProdHierarchy create(int projectionProdHierarchySid) {
        ProjectionProdHierarchy projectionProdHierarchy = new ProjectionProdHierarchyImpl();

        projectionProdHierarchy.setNew(true);
        projectionProdHierarchy.setPrimaryKey(projectionProdHierarchySid);

        return projectionProdHierarchy;
    }

    /**
     * Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy that was removed
     * @throws com.stpl.app.NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy remove(int projectionProdHierarchySid)
        throws NoSuchProjectionProdHierarchyException, SystemException {
        return remove((Serializable) projectionProdHierarchySid);
    }

    /**
     * Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy that was removed
     * @throws com.stpl.app.NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy remove(Serializable primaryKey)
        throws NoSuchProjectionProdHierarchyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionProdHierarchy projectionProdHierarchy = (ProjectionProdHierarchy) session.get(ProjectionProdHierarchyImpl.class,
                    primaryKey);

            if (projectionProdHierarchy == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionProdHierarchy);
        } catch (NoSuchProjectionProdHierarchyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionProdHierarchy removeImpl(
        ProjectionProdHierarchy projectionProdHierarchy)
        throws SystemException {
        projectionProdHierarchy = toUnwrappedModel(projectionProdHierarchy);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionProdHierarchy)) {
                projectionProdHierarchy = (ProjectionProdHierarchy) session.get(ProjectionProdHierarchyImpl.class,
                        projectionProdHierarchy.getPrimaryKeyObj());
            }

            if (projectionProdHierarchy != null) {
                session.delete(projectionProdHierarchy);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionProdHierarchy != null) {
            clearCache(projectionProdHierarchy);
        }

        return projectionProdHierarchy;
    }

    @Override
    public ProjectionProdHierarchy updateImpl(
        com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy)
        throws SystemException {
        projectionProdHierarchy = toUnwrappedModel(projectionProdHierarchy);

        boolean isNew = projectionProdHierarchy.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionProdHierarchy.isNew()) {
                session.save(projectionProdHierarchy);

                projectionProdHierarchy.setNew(false);
            } else {
                session.merge(projectionProdHierarchy);
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

        EntityCacheUtil.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdHierarchyImpl.class,
            projectionProdHierarchy.getPrimaryKey(), projectionProdHierarchy);

        return projectionProdHierarchy;
    }

    protected ProjectionProdHierarchy toUnwrappedModel(
        ProjectionProdHierarchy projectionProdHierarchy) {
        if (projectionProdHierarchy instanceof ProjectionProdHierarchyImpl) {
            return projectionProdHierarchy;
        }

        ProjectionProdHierarchyImpl projectionProdHierarchyImpl = new ProjectionProdHierarchyImpl();

        projectionProdHierarchyImpl.setNew(projectionProdHierarchy.isNew());
        projectionProdHierarchyImpl.setPrimaryKey(projectionProdHierarchy.getPrimaryKey());

        projectionProdHierarchyImpl.setProjectionMasterSid(projectionProdHierarchy.getProjectionMasterSid());
        projectionProdHierarchyImpl.setProjectionProdHierarchySid(projectionProdHierarchy.getProjectionProdHierarchySid());
        projectionProdHierarchyImpl.setRelationshipLevelSid(projectionProdHierarchy.getRelationshipLevelSid());

        return projectionProdHierarchyImpl;
    }

    /**
     * Returns the projection prod hierarchy with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy
     * @throws com.stpl.app.NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionProdHierarchyException, SystemException {
        ProjectionProdHierarchy projectionProdHierarchy = fetchByPrimaryKey(primaryKey);

        if (projectionProdHierarchy == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionProdHierarchy;
    }

    /**
     * Returns the projection prod hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchProjectionProdHierarchyException} if it could not be found.
     *
     * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy
     * @throws com.stpl.app.NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy findByPrimaryKey(
        int projectionProdHierarchySid)
        throws NoSuchProjectionProdHierarchyException, SystemException {
        return findByPrimaryKey((Serializable) projectionProdHierarchySid);
    }

    /**
     * Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionProdHierarchy projectionProdHierarchy = (ProjectionProdHierarchy) EntityCacheUtil.getResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionProdHierarchyImpl.class, primaryKey);

        if (projectionProdHierarchy == _nullProjectionProdHierarchy) {
            return null;
        }

        if (projectionProdHierarchy == null) {
            Session session = null;

            try {
                session = openSession();

                projectionProdHierarchy = (ProjectionProdHierarchy) session.get(ProjectionProdHierarchyImpl.class,
                        primaryKey);

                if (projectionProdHierarchy != null) {
                    cacheResult(projectionProdHierarchy);
                } else {
                    EntityCacheUtil.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionProdHierarchyImpl.class, primaryKey,
                        _nullProjectionProdHierarchy);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionProdHierarchyImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionProdHierarchy;
    }

    /**
     * Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
     * @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdHierarchy fetchByPrimaryKey(
        int projectionProdHierarchySid) throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionProdHierarchySid);
    }

    /**
     * Returns all the projection prod hierarchies.
     *
     * @return the projection prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdHierarchy> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection prod hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection prod hierarchies
     * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
     * @return the range of projection prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdHierarchy> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection prod hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection prod hierarchies
     * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection prod hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdHierarchy> findAll(int start, int end,
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

        List<ProjectionProdHierarchy> list = (List<ProjectionProdHierarchy>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONPRODHIERARCHY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONPRODHIERARCHY;

                if (pagination) {
                    sql = sql.concat(ProjectionProdHierarchyModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionProdHierarchy>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionProdHierarchy>(list);
                } else {
                    list = (List<ProjectionProdHierarchy>) QueryUtil.list(q,
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
     * Removes all the projection prod hierarchies from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionProdHierarchy projectionProdHierarchy : findAll()) {
            remove(projectionProdHierarchy);
        }
    }

    /**
     * Returns the number of projection prod hierarchies.
     *
     * @return the number of projection prod hierarchies
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONPRODHIERARCHY);

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
     * Initializes the projection prod hierarchy persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionProdHierarchy")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionProdHierarchy>> listenersList = new ArrayList<ModelListener<ProjectionProdHierarchy>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionProdHierarchy>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionProdHierarchyImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
