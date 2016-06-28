package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionProdDetailsException;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.model.impl.ProjectionProdDetailsImpl;
import com.stpl.app.model.impl.ProjectionProdDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionProdDetailsPersistence;

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
 * The persistence implementation for the projection prod details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdDetailsPersistence
 * @see ProjectionProdDetailsUtil
 * @generated
 */
public class ProjectionProdDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionProdDetails>
    implements ProjectionProdDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionProdDetailsUtil} to access the projection prod details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionProdDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionProdDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionProdDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONPRODDETAILS = "SELECT projectionProdDetails FROM ProjectionProdDetails projectionProdDetails";
    private static final String _SQL_COUNT_PROJECTIONPRODDETAILS = "SELECT COUNT(projectionProdDetails) FROM ProjectionProdDetails projectionProdDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionProdDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionProdDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionProdDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "productName", "costCenter", "productNo", "subLedgerCode",
                "productDetailsId", "brandName", "projectionId"
            });
    private static ProjectionProdDetails _nullProjectionProdDetails = new ProjectionProdDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionProdDetails> toCacheModel() {
                return _nullProjectionProdDetailsCacheModel;
            }
        };

    private static CacheModel<ProjectionProdDetails> _nullProjectionProdDetailsCacheModel =
        new CacheModel<ProjectionProdDetails>() {
            @Override
            public ProjectionProdDetails toEntityModel() {
                return _nullProjectionProdDetails;
            }
        };

    public ProjectionProdDetailsPersistenceImpl() {
        setModelClass(ProjectionProdDetails.class);
    }

    /**
     * Caches the projection prod details in the entity cache if it is enabled.
     *
     * @param projectionProdDetails the projection prod details
     */
    @Override
    public void cacheResult(ProjectionProdDetails projectionProdDetails) {
        EntityCacheUtil.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsImpl.class,
            projectionProdDetails.getPrimaryKey(), projectionProdDetails);

        projectionProdDetails.resetOriginalValues();
    }

    /**
     * Caches the projection prod detailses in the entity cache if it is enabled.
     *
     * @param projectionProdDetailses the projection prod detailses
     */
    @Override
    public void cacheResult(List<ProjectionProdDetails> projectionProdDetailses) {
        for (ProjectionProdDetails projectionProdDetails : projectionProdDetailses) {
            if (EntityCacheUtil.getResult(
                        ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionProdDetailsImpl.class,
                        projectionProdDetails.getPrimaryKey()) == null) {
                cacheResult(projectionProdDetails);
            } else {
                projectionProdDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection prod detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionProdDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionProdDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection prod details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionProdDetails projectionProdDetails) {
        EntityCacheUtil.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsImpl.class,
            projectionProdDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProjectionProdDetails> projectionProdDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionProdDetails projectionProdDetails : projectionProdDetailses) {
            EntityCacheUtil.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionProdDetailsImpl.class,
                projectionProdDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
     *
     * @param productDetailsId the primary key for the new projection prod details
     * @return the new projection prod details
     */
    @Override
    public ProjectionProdDetails create(int productDetailsId) {
        ProjectionProdDetails projectionProdDetails = new ProjectionProdDetailsImpl();

        projectionProdDetails.setNew(true);
        projectionProdDetails.setPrimaryKey(productDetailsId);

        return projectionProdDetails;
    }

    /**
     * Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param productDetailsId the primary key of the projection prod details
     * @return the projection prod details that was removed
     * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails remove(int productDetailsId)
        throws NoSuchProjectionProdDetailsException, SystemException {
        return remove((Serializable) productDetailsId);
    }

    /**
     * Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection prod details
     * @return the projection prod details that was removed
     * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails remove(Serializable primaryKey)
        throws NoSuchProjectionProdDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionProdDetails projectionProdDetails = (ProjectionProdDetails) session.get(ProjectionProdDetailsImpl.class,
                    primaryKey);

            if (projectionProdDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionProdDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionProdDetails);
        } catch (NoSuchProjectionProdDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionProdDetails removeImpl(
        ProjectionProdDetails projectionProdDetails) throws SystemException {
        projectionProdDetails = toUnwrappedModel(projectionProdDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionProdDetails)) {
                projectionProdDetails = (ProjectionProdDetails) session.get(ProjectionProdDetailsImpl.class,
                        projectionProdDetails.getPrimaryKeyObj());
            }

            if (projectionProdDetails != null) {
                session.delete(projectionProdDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionProdDetails != null) {
            clearCache(projectionProdDetails);
        }

        return projectionProdDetails;
    }

    @Override
    public ProjectionProdDetails updateImpl(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails)
        throws SystemException {
        projectionProdDetails = toUnwrappedModel(projectionProdDetails);

        boolean isNew = projectionProdDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionProdDetails.isNew()) {
                session.save(projectionProdDetails);

                projectionProdDetails.setNew(false);
            } else {
                session.merge(projectionProdDetails);
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

        EntityCacheUtil.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionProdDetailsImpl.class,
            projectionProdDetails.getPrimaryKey(), projectionProdDetails);

        return projectionProdDetails;
    }

    protected ProjectionProdDetails toUnwrappedModel(
        ProjectionProdDetails projectionProdDetails) {
        if (projectionProdDetails instanceof ProjectionProdDetailsImpl) {
            return projectionProdDetails;
        }

        ProjectionProdDetailsImpl projectionProdDetailsImpl = new ProjectionProdDetailsImpl();

        projectionProdDetailsImpl.setNew(projectionProdDetails.isNew());
        projectionProdDetailsImpl.setPrimaryKey(projectionProdDetails.getPrimaryKey());

        projectionProdDetailsImpl.setProductName(projectionProdDetails.getProductName());
        projectionProdDetailsImpl.setCostCenter(projectionProdDetails.getCostCenter());
        projectionProdDetailsImpl.setProductNo(projectionProdDetails.getProductNo());
        projectionProdDetailsImpl.setSubLedgerCode(projectionProdDetails.getSubLedgerCode());
        projectionProdDetailsImpl.setProductDetailsId(projectionProdDetails.getProductDetailsId());
        projectionProdDetailsImpl.setBrandName(projectionProdDetails.getBrandName());
        projectionProdDetailsImpl.setProjectionId(projectionProdDetails.getProjectionId());

        return projectionProdDetailsImpl;
    }

    /**
     * Returns the projection prod details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection prod details
     * @return the projection prod details
     * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionProdDetailsException, SystemException {
        ProjectionProdDetails projectionProdDetails = fetchByPrimaryKey(primaryKey);

        if (projectionProdDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionProdDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionProdDetails;
    }

    /**
     * Returns the projection prod details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionProdDetailsException} if it could not be found.
     *
     * @param productDetailsId the primary key of the projection prod details
     * @return the projection prod details
     * @throws com.stpl.app.NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails findByPrimaryKey(int productDetailsId)
        throws NoSuchProjectionProdDetailsException, SystemException {
        return findByPrimaryKey((Serializable) productDetailsId);
    }

    /**
     * Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection prod details
     * @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionProdDetails projectionProdDetails = (ProjectionProdDetails) EntityCacheUtil.getResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionProdDetailsImpl.class, primaryKey);

        if (projectionProdDetails == _nullProjectionProdDetails) {
            return null;
        }

        if (projectionProdDetails == null) {
            Session session = null;

            try {
                session = openSession();

                projectionProdDetails = (ProjectionProdDetails) session.get(ProjectionProdDetailsImpl.class,
                        primaryKey);

                if (projectionProdDetails != null) {
                    cacheResult(projectionProdDetails);
                } else {
                    EntityCacheUtil.putResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionProdDetailsImpl.class, primaryKey,
                        _nullProjectionProdDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionProdDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionProdDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionProdDetails;
    }

    /**
     * Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param productDetailsId the primary key of the projection prod details
     * @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionProdDetails fetchByPrimaryKey(int productDetailsId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) productDetailsId);
    }

    /**
     * Returns all the projection prod detailses.
     *
     * @return the projection prod detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection prod detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection prod detailses
     * @param end the upper bound of the range of projection prod detailses (not inclusive)
     * @return the range of projection prod detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection prod detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection prod detailses
     * @param end the upper bound of the range of projection prod detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection prod detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionProdDetails> findAll(int start, int end,
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

        List<ProjectionProdDetails> list = (List<ProjectionProdDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONPRODDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONPRODDETAILS;

                if (pagination) {
                    sql = sql.concat(ProjectionProdDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionProdDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionProdDetails>(list);
                } else {
                    list = (List<ProjectionProdDetails>) QueryUtil.list(q,
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
     * Removes all the projection prod detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionProdDetails projectionProdDetails : findAll()) {
            remove(projectionProdDetails);
        }
    }

    /**
     * Returns the number of projection prod detailses.
     *
     * @return the number of projection prod detailses
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONPRODDETAILS);

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
     * Initializes the projection prod details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionProdDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionProdDetails>> listenersList = new ArrayList<ModelListener<ProjectionProdDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionProdDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionProdDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
