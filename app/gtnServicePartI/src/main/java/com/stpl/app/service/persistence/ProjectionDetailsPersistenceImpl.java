package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionDetailsException;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.impl.ProjectionDetailsImpl;
import com.stpl.app.model.impl.ProjectionDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionDetailsPersistence;

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
 * The persistence implementation for the projection details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionDetailsPersistence
 * @see ProjectionDetailsUtil
 * @generated
 */
public class ProjectionDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionDetails>
    implements ProjectionDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionDetailsUtil} to access the projection details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONDETAILS = "SELECT projectionDetails FROM ProjectionDetails projectionDetails";
    private static final String _SQL_COUNT_PROJECTIONDETAILS = "SELECT COUNT(projectionDetails) FROM ProjectionDetails projectionDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "projectionDetailsSid", "ccpDetailsSid", "projectionMasterSid"
            });
    private static ProjectionDetails _nullProjectionDetails = new ProjectionDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionDetails> toCacheModel() {
                return _nullProjectionDetailsCacheModel;
            }
        };

    private static CacheModel<ProjectionDetails> _nullProjectionDetailsCacheModel =
        new CacheModel<ProjectionDetails>() {
            @Override
            public ProjectionDetails toEntityModel() {
                return _nullProjectionDetails;
            }
        };

    public ProjectionDetailsPersistenceImpl() {
        setModelClass(ProjectionDetails.class);
    }

    /**
     * Caches the projection details in the entity cache if it is enabled.
     *
     * @param projectionDetails the projection details
     */
    @Override
    public void cacheResult(ProjectionDetails projectionDetails) {
        EntityCacheUtil.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey(),
            projectionDetails);

        projectionDetails.resetOriginalValues();
    }

    /**
     * Caches the projection detailses in the entity cache if it is enabled.
     *
     * @param projectionDetailses the projection detailses
     */
    @Override
    public void cacheResult(List<ProjectionDetails> projectionDetailses) {
        for (ProjectionDetails projectionDetails : projectionDetailses) {
            if (EntityCacheUtil.getResult(
                        ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionDetailsImpl.class,
                        projectionDetails.getPrimaryKey()) == null) {
                cacheResult(projectionDetails);
            } else {
                projectionDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionDetails projectionDetails) {
        EntityCacheUtil.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProjectionDetails> projectionDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionDetails projectionDetails : projectionDetailses) {
            EntityCacheUtil.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection details with the primary key. Does not add the projection details to the database.
     *
     * @param projectionDetailsSid the primary key for the new projection details
     * @return the new projection details
     */
    @Override
    public ProjectionDetails create(int projectionDetailsSid) {
        ProjectionDetails projectionDetails = new ProjectionDetailsImpl();

        projectionDetails.setNew(true);
        projectionDetails.setPrimaryKey(projectionDetailsSid);

        return projectionDetails;
    }

    /**
     * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the projection details
     * @return the projection details that was removed
     * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails remove(int projectionDetailsSid)
        throws NoSuchProjectionDetailsException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection details
     * @return the projection details that was removed
     * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails remove(Serializable primaryKey)
        throws NoSuchProjectionDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionDetails projectionDetails = (ProjectionDetails) session.get(ProjectionDetailsImpl.class,
                    primaryKey);

            if (projectionDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionDetails);
        } catch (NoSuchProjectionDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionDetails removeImpl(ProjectionDetails projectionDetails)
        throws SystemException {
        projectionDetails = toUnwrappedModel(projectionDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionDetails)) {
                projectionDetails = (ProjectionDetails) session.get(ProjectionDetailsImpl.class,
                        projectionDetails.getPrimaryKeyObj());
            }

            if (projectionDetails != null) {
                session.delete(projectionDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionDetails != null) {
            clearCache(projectionDetails);
        }

        return projectionDetails;
    }

    @Override
    public ProjectionDetails updateImpl(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws SystemException {
        projectionDetails = toUnwrappedModel(projectionDetails);

        boolean isNew = projectionDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionDetails.isNew()) {
                session.save(projectionDetails);

                projectionDetails.setNew(false);
            } else {
                session.merge(projectionDetails);
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

        EntityCacheUtil.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionDetailsImpl.class, projectionDetails.getPrimaryKey(),
            projectionDetails);

        return projectionDetails;
    }

    protected ProjectionDetails toUnwrappedModel(
        ProjectionDetails projectionDetails) {
        if (projectionDetails instanceof ProjectionDetailsImpl) {
            return projectionDetails;
        }

        ProjectionDetailsImpl projectionDetailsImpl = new ProjectionDetailsImpl();

        projectionDetailsImpl.setNew(projectionDetails.isNew());
        projectionDetailsImpl.setPrimaryKey(projectionDetails.getPrimaryKey());

        projectionDetailsImpl.setProjectionDetailsSid(projectionDetails.getProjectionDetailsSid());
        projectionDetailsImpl.setCcpDetailsSid(projectionDetails.getCcpDetailsSid());
        projectionDetailsImpl.setProjectionMasterSid(projectionDetails.getProjectionMasterSid());

        return projectionDetailsImpl;
    }

    /**
     * Returns the projection details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection details
     * @return the projection details
     * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionDetailsException, SystemException {
        ProjectionDetails projectionDetails = fetchByPrimaryKey(primaryKey);

        if (projectionDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionDetails;
    }

    /**
     * Returns the projection details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionDetailsException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the projection details
     * @return the projection details
     * @throws com.stpl.app.NoSuchProjectionDetailsException if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchProjectionDetailsException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection details
     * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionDetails projectionDetails = (ProjectionDetails) EntityCacheUtil.getResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionDetailsImpl.class, primaryKey);

        if (projectionDetails == _nullProjectionDetails) {
            return null;
        }

        if (projectionDetails == null) {
            Session session = null;

            try {
                session = openSession();

                projectionDetails = (ProjectionDetails) session.get(ProjectionDetailsImpl.class,
                        primaryKey);

                if (projectionDetails != null) {
                    cacheResult(projectionDetails);
                } else {
                    EntityCacheUtil.putResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionDetailsImpl.class, primaryKey,
                        _nullProjectionDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionDetails;
    }

    /**
     * Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the projection details
     * @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionDetails fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the projection detailses.
     *
     * @return the projection detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection detailses
     * @param end the upper bound of the range of projection detailses (not inclusive)
     * @return the range of projection detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection detailses
     * @param end the upper bound of the range of projection detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionDetails> findAll(int start, int end,
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

        List<ProjectionDetails> list = (List<ProjectionDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONDETAILS;

                if (pagination) {
                    sql = sql.concat(ProjectionDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionDetails>(list);
                } else {
                    list = (List<ProjectionDetails>) QueryUtil.list(q,
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
     * Removes all the projection detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionDetails projectionDetails : findAll()) {
            remove(projectionDetails);
        }
    }

    /**
     * Returns the number of projection detailses.
     *
     * @return the number of projection detailses
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONDETAILS);

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
     * Initializes the projection details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionDetails>> listenersList = new ArrayList<ModelListener<ProjectionDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
