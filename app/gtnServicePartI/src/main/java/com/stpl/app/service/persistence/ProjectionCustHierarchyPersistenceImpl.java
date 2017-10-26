package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionCustHierarchyException;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.impl.ProjectionCustHierarchyImpl;
import com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl;
import com.stpl.app.service.persistence.ProjectionCustHierarchyPersistence;

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
 * The persistence implementation for the projection cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustHierarchyPersistence
 * @see ProjectionCustHierarchyUtil
 * @generated
 */
public class ProjectionCustHierarchyPersistenceImpl extends BasePersistenceImpl<ProjectionCustHierarchy>
    implements ProjectionCustHierarchyPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionCustHierarchyUtil} to access the projection cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionCustHierarchyImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
            ProjectionCustHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
            ProjectionCustHierarchyImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONCUSTHIERARCHY = "SELECT projectionCustHierarchy FROM ProjectionCustHierarchy projectionCustHierarchy";
    private static final String _SQL_COUNT_PROJECTIONCUSTHIERARCHY = "SELECT COUNT(projectionCustHierarchy) FROM ProjectionCustHierarchy projectionCustHierarchy";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionCustHierarchy.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionCustHierarchy exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionCustHierarchyPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "projectionMasterSid", "projectionCustHierarchySid",
                "relationshipLevelSid"
            });
    private static ProjectionCustHierarchy _nullProjectionCustHierarchy = new ProjectionCustHierarchyImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionCustHierarchy> toCacheModel() {
                return _nullProjectionCustHierarchyCacheModel;
            }
        };

    private static CacheModel<ProjectionCustHierarchy> _nullProjectionCustHierarchyCacheModel =
        new CacheModel<ProjectionCustHierarchy>() {
            @Override
            public ProjectionCustHierarchy toEntityModel() {
                return _nullProjectionCustHierarchy;
            }
        };

    public ProjectionCustHierarchyPersistenceImpl() {
        setModelClass(ProjectionCustHierarchy.class);
    }

    /**
     * Caches the projection cust hierarchy in the entity cache if it is enabled.
     *
     * @param projectionCustHierarchy the projection cust hierarchy
     */
    @Override
    public void cacheResult(ProjectionCustHierarchy projectionCustHierarchy) {
        EntityCacheUtil.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyImpl.class,
            projectionCustHierarchy.getPrimaryKey(), projectionCustHierarchy);

        projectionCustHierarchy.resetOriginalValues();
    }

    /**
     * Caches the projection cust hierarchies in the entity cache if it is enabled.
     *
     * @param projectionCustHierarchies the projection cust hierarchies
     */
    @Override
    public void cacheResult(
        List<ProjectionCustHierarchy> projectionCustHierarchies) {
        for (ProjectionCustHierarchy projectionCustHierarchy : projectionCustHierarchies) {
            if (EntityCacheUtil.getResult(
                        ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionCustHierarchyImpl.class,
                        projectionCustHierarchy.getPrimaryKey()) == null) {
                cacheResult(projectionCustHierarchy);
            } else {
                projectionCustHierarchy.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection cust hierarchies.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionCustHierarchyImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionCustHierarchyImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection cust hierarchy.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionCustHierarchy projectionCustHierarchy) {
        EntityCacheUtil.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyImpl.class,
            projectionCustHierarchy.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ProjectionCustHierarchy> projectionCustHierarchies) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionCustHierarchy projectionCustHierarchy : projectionCustHierarchies) {
            EntityCacheUtil.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionCustHierarchyImpl.class,
                projectionCustHierarchy.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
     *
     * @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
     * @return the new projection cust hierarchy
     */
    @Override
    public ProjectionCustHierarchy create(int projectionCustHierarchySid) {
        ProjectionCustHierarchy projectionCustHierarchy = new ProjectionCustHierarchyImpl();

        projectionCustHierarchy.setNew(true);
        projectionCustHierarchy.setPrimaryKey(projectionCustHierarchySid);

        return projectionCustHierarchy;
    }

    /**
     * Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy that was removed
     * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy remove(int projectionCustHierarchySid)
        throws NoSuchProjectionCustHierarchyException, SystemException {
        return remove((Serializable) projectionCustHierarchySid);
    }

    /**
     * Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy that was removed
     * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy remove(Serializable primaryKey)
        throws NoSuchProjectionCustHierarchyException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionCustHierarchy projectionCustHierarchy = (ProjectionCustHierarchy) session.get(ProjectionCustHierarchyImpl.class,
                    primaryKey);

            if (projectionCustHierarchy == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionCustHierarchy);
        } catch (NoSuchProjectionCustHierarchyException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionCustHierarchy removeImpl(
        ProjectionCustHierarchy projectionCustHierarchy)
        throws SystemException {
        projectionCustHierarchy = toUnwrappedModel(projectionCustHierarchy);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionCustHierarchy)) {
                projectionCustHierarchy = (ProjectionCustHierarchy) session.get(ProjectionCustHierarchyImpl.class,
                        projectionCustHierarchy.getPrimaryKeyObj());
            }

            if (projectionCustHierarchy != null) {
                session.delete(projectionCustHierarchy);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionCustHierarchy != null) {
            clearCache(projectionCustHierarchy);
        }

        return projectionCustHierarchy;
    }

    @Override
    public ProjectionCustHierarchy updateImpl(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws SystemException {
        projectionCustHierarchy = toUnwrappedModel(projectionCustHierarchy);

        boolean isNew = projectionCustHierarchy.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionCustHierarchy.isNew()) {
                session.save(projectionCustHierarchy);

                projectionCustHierarchy.setNew(false);
            } else {
                session.merge(projectionCustHierarchy);
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

        EntityCacheUtil.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustHierarchyImpl.class,
            projectionCustHierarchy.getPrimaryKey(), projectionCustHierarchy);

        return projectionCustHierarchy;
    }

    protected ProjectionCustHierarchy toUnwrappedModel(
        ProjectionCustHierarchy projectionCustHierarchy) {
        if (projectionCustHierarchy instanceof ProjectionCustHierarchyImpl) {
            return projectionCustHierarchy;
        }

        ProjectionCustHierarchyImpl projectionCustHierarchyImpl = new ProjectionCustHierarchyImpl();

        projectionCustHierarchyImpl.setNew(projectionCustHierarchy.isNew());
        projectionCustHierarchyImpl.setPrimaryKey(projectionCustHierarchy.getPrimaryKey());

        projectionCustHierarchyImpl.setProjectionMasterSid(projectionCustHierarchy.getProjectionMasterSid());
        projectionCustHierarchyImpl.setProjectionCustHierarchySid(projectionCustHierarchy.getProjectionCustHierarchySid());
        projectionCustHierarchyImpl.setRelationshipLevelSid(projectionCustHierarchy.getRelationshipLevelSid());

        return projectionCustHierarchyImpl;
    }

    /**
     * Returns the projection cust hierarchy with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy
     * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionCustHierarchyException, SystemException {
        ProjectionCustHierarchy projectionCustHierarchy = fetchByPrimaryKey(primaryKey);

        if (projectionCustHierarchy == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionCustHierarchy;
    }

    /**
     * Returns the projection cust hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchProjectionCustHierarchyException} if it could not be found.
     *
     * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy
     * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy findByPrimaryKey(
        int projectionCustHierarchySid)
        throws NoSuchProjectionCustHierarchyException, SystemException {
        return findByPrimaryKey((Serializable) projectionCustHierarchySid);
    }

    /**
     * Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionCustHierarchy projectionCustHierarchy = (ProjectionCustHierarchy) EntityCacheUtil.getResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionCustHierarchyImpl.class, primaryKey);

        if (projectionCustHierarchy == _nullProjectionCustHierarchy) {
            return null;
        }

        if (projectionCustHierarchy == null) {
            Session session = null;

            try {
                session = openSession();

                projectionCustHierarchy = (ProjectionCustHierarchy) session.get(ProjectionCustHierarchyImpl.class,
                        primaryKey);

                if (projectionCustHierarchy != null) {
                    cacheResult(projectionCustHierarchy);
                } else {
                    EntityCacheUtil.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionCustHierarchyImpl.class, primaryKey,
                        _nullProjectionCustHierarchy);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionCustHierarchyImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionCustHierarchy;
    }

    /**
     * Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
     * @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustHierarchy fetchByPrimaryKey(
        int projectionCustHierarchySid) throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionCustHierarchySid);
    }

    /**
     * Returns all the projection cust hierarchies.
     *
     * @return the projection cust hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustHierarchy> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection cust hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection cust hierarchies
     * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
     * @return the range of projection cust hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustHierarchy> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection cust hierarchies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection cust hierarchies
     * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection cust hierarchies
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustHierarchy> findAll(int start, int end,
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

        List<ProjectionCustHierarchy> list = (List<ProjectionCustHierarchy>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONCUSTHIERARCHY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONCUSTHIERARCHY;

                if (pagination) {
                    sql = sql.concat(ProjectionCustHierarchyModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionCustHierarchy>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionCustHierarchy>(list);
                } else {
                    list = (List<ProjectionCustHierarchy>) QueryUtil.list(q,
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
     * Removes all the projection cust hierarchies from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionCustHierarchy projectionCustHierarchy : findAll()) {
            remove(projectionCustHierarchy);
        }
    }

    /**
     * Returns the number of projection cust hierarchies.
     *
     * @return the number of projection cust hierarchies
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONCUSTHIERARCHY);

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
     * Initializes the projection cust hierarchy persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionCustHierarchy")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionCustHierarchy>> listenersList = new ArrayList<ModelListener<ProjectionCustHierarchy>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionCustHierarchy>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionCustHierarchyImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
