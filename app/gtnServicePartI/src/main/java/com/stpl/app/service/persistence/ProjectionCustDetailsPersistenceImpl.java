package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionCustDetailsException;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.impl.ProjectionCustDetailsImpl;
import com.stpl.app.model.impl.ProjectionCustDetailsModelImpl;
import com.stpl.app.service.persistence.ProjectionCustDetailsPersistence;

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
 * The persistence implementation for the projection cust details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustDetailsPersistence
 * @see ProjectionCustDetailsUtil
 * @generated
 */
public class ProjectionCustDetailsPersistenceImpl extends BasePersistenceImpl<ProjectionCustDetails>
    implements ProjectionCustDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionCustDetailsUtil} to access the projection cust details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionCustDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionCustDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED,
            ProjectionCustDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONCUSTDETAILS = "SELECT projectionCustDetails FROM ProjectionCustDetails projectionCustDetails";
    private static final String _SQL_COUNT_PROJECTIONCUSTDETAILS = "SELECT COUNT(projectionCustDetails) FROM ProjectionCustDetails projectionCustDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionCustDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionCustDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionCustDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "contractName", "customerName", "customerDetailsId",
                "costCenter", "customerAlias", "subLedgerCode", "projectionId",
                "marketType", "contractNo"
            });
    private static ProjectionCustDetails _nullProjectionCustDetails = new ProjectionCustDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionCustDetails> toCacheModel() {
                return _nullProjectionCustDetailsCacheModel;
            }
        };

    private static CacheModel<ProjectionCustDetails> _nullProjectionCustDetailsCacheModel =
        new CacheModel<ProjectionCustDetails>() {
            @Override
            public ProjectionCustDetails toEntityModel() {
                return _nullProjectionCustDetails;
            }
        };

    public ProjectionCustDetailsPersistenceImpl() {
        setModelClass(ProjectionCustDetails.class);
    }

    /**
     * Caches the projection cust details in the entity cache if it is enabled.
     *
     * @param projectionCustDetails the projection cust details
     */
    @Override
    public void cacheResult(ProjectionCustDetails projectionCustDetails) {
        EntityCacheUtil.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsImpl.class,
            projectionCustDetails.getPrimaryKey(), projectionCustDetails);

        projectionCustDetails.resetOriginalValues();
    }

    /**
     * Caches the projection cust detailses in the entity cache if it is enabled.
     *
     * @param projectionCustDetailses the projection cust detailses
     */
    @Override
    public void cacheResult(List<ProjectionCustDetails> projectionCustDetailses) {
        for (ProjectionCustDetails projectionCustDetails : projectionCustDetailses) {
            if (EntityCacheUtil.getResult(
                        ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionCustDetailsImpl.class,
                        projectionCustDetails.getPrimaryKey()) == null) {
                cacheResult(projectionCustDetails);
            } else {
                projectionCustDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection cust detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionCustDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionCustDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection cust details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionCustDetails projectionCustDetails) {
        EntityCacheUtil.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsImpl.class,
            projectionCustDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProjectionCustDetails> projectionCustDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionCustDetails projectionCustDetails : projectionCustDetailses) {
            EntityCacheUtil.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionCustDetailsImpl.class,
                projectionCustDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
     *
     * @param customerDetailsId the primary key for the new projection cust details
     * @return the new projection cust details
     */
    @Override
    public ProjectionCustDetails create(int customerDetailsId) {
        ProjectionCustDetails projectionCustDetails = new ProjectionCustDetailsImpl();

        projectionCustDetails.setNew(true);
        projectionCustDetails.setPrimaryKey(customerDetailsId);

        return projectionCustDetails;
    }

    /**
     * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param customerDetailsId the primary key of the projection cust details
     * @return the projection cust details that was removed
     * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails remove(int customerDetailsId)
        throws NoSuchProjectionCustDetailsException, SystemException {
        return remove((Serializable) customerDetailsId);
    }

    /**
     * Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection cust details
     * @return the projection cust details that was removed
     * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails remove(Serializable primaryKey)
        throws NoSuchProjectionCustDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionCustDetails projectionCustDetails = (ProjectionCustDetails) session.get(ProjectionCustDetailsImpl.class,
                    primaryKey);

            if (projectionCustDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionCustDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionCustDetails);
        } catch (NoSuchProjectionCustDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionCustDetails removeImpl(
        ProjectionCustDetails projectionCustDetails) throws SystemException {
        projectionCustDetails = toUnwrappedModel(projectionCustDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionCustDetails)) {
                projectionCustDetails = (ProjectionCustDetails) session.get(ProjectionCustDetailsImpl.class,
                        projectionCustDetails.getPrimaryKeyObj());
            }

            if (projectionCustDetails != null) {
                session.delete(projectionCustDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionCustDetails != null) {
            clearCache(projectionCustDetails);
        }

        return projectionCustDetails;
    }

    @Override
    public ProjectionCustDetails updateImpl(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws SystemException {
        projectionCustDetails = toUnwrappedModel(projectionCustDetails);

        boolean isNew = projectionCustDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionCustDetails.isNew()) {
                session.save(projectionCustDetails);

                projectionCustDetails.setNew(false);
            } else {
                session.merge(projectionCustDetails);
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

        EntityCacheUtil.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionCustDetailsImpl.class,
            projectionCustDetails.getPrimaryKey(), projectionCustDetails);

        return projectionCustDetails;
    }

    protected ProjectionCustDetails toUnwrappedModel(
        ProjectionCustDetails projectionCustDetails) {
        if (projectionCustDetails instanceof ProjectionCustDetailsImpl) {
            return projectionCustDetails;
        }

        ProjectionCustDetailsImpl projectionCustDetailsImpl = new ProjectionCustDetailsImpl();

        projectionCustDetailsImpl.setNew(projectionCustDetails.isNew());
        projectionCustDetailsImpl.setPrimaryKey(projectionCustDetails.getPrimaryKey());

        projectionCustDetailsImpl.setContractName(projectionCustDetails.getContractName());
        projectionCustDetailsImpl.setCustomerName(projectionCustDetails.getCustomerName());
        projectionCustDetailsImpl.setCustomerDetailsId(projectionCustDetails.getCustomerDetailsId());
        projectionCustDetailsImpl.setCostCenter(projectionCustDetails.getCostCenter());
        projectionCustDetailsImpl.setCustomerAlias(projectionCustDetails.getCustomerAlias());
        projectionCustDetailsImpl.setSubLedgerCode(projectionCustDetails.getSubLedgerCode());
        projectionCustDetailsImpl.setProjectionId(projectionCustDetails.getProjectionId());
        projectionCustDetailsImpl.setMarketType(projectionCustDetails.getMarketType());
        projectionCustDetailsImpl.setContractNo(projectionCustDetails.getContractNo());

        return projectionCustDetailsImpl;
    }

    /**
     * Returns the projection cust details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection cust details
     * @return the projection cust details
     * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionCustDetailsException, SystemException {
        ProjectionCustDetails projectionCustDetails = fetchByPrimaryKey(primaryKey);

        if (projectionCustDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionCustDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionCustDetails;
    }

    /**
     * Returns the projection cust details with the primary key or throws a {@link com.stpl.app.NoSuchProjectionCustDetailsException} if it could not be found.
     *
     * @param customerDetailsId the primary key of the projection cust details
     * @return the projection cust details
     * @throws com.stpl.app.NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails findByPrimaryKey(int customerDetailsId)
        throws NoSuchProjectionCustDetailsException, SystemException {
        return findByPrimaryKey((Serializable) customerDetailsId);
    }

    /**
     * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection cust details
     * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionCustDetails projectionCustDetails = (ProjectionCustDetails) EntityCacheUtil.getResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionCustDetailsImpl.class, primaryKey);

        if (projectionCustDetails == _nullProjectionCustDetails) {
            return null;
        }

        if (projectionCustDetails == null) {
            Session session = null;

            try {
                session = openSession();

                projectionCustDetails = (ProjectionCustDetails) session.get(ProjectionCustDetailsImpl.class,
                        primaryKey);

                if (projectionCustDetails != null) {
                    cacheResult(projectionCustDetails);
                } else {
                    EntityCacheUtil.putResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionCustDetailsImpl.class, primaryKey,
                        _nullProjectionCustDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionCustDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionCustDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionCustDetails;
    }

    /**
     * Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param customerDetailsId the primary key of the projection cust details
     * @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionCustDetails fetchByPrimaryKey(int customerDetailsId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) customerDetailsId);
    }

    /**
     * Returns all the projection cust detailses.
     *
     * @return the projection cust detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection cust detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection cust detailses
     * @param end the upper bound of the range of projection cust detailses (not inclusive)
     * @return the range of projection cust detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection cust detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection cust detailses
     * @param end the upper bound of the range of projection cust detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection cust detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionCustDetails> findAll(int start, int end,
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

        List<ProjectionCustDetails> list = (List<ProjectionCustDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONCUSTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONCUSTDETAILS;

                if (pagination) {
                    sql = sql.concat(ProjectionCustDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionCustDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionCustDetails>(list);
                } else {
                    list = (List<ProjectionCustDetails>) QueryUtil.list(q,
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
     * Removes all the projection cust detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionCustDetails projectionCustDetails : findAll()) {
            remove(projectionCustDetails);
        }
    }

    /**
     * Returns the number of projection cust detailses.
     *
     * @return the number of projection cust detailses
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONCUSTDETAILS);

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
     * Initializes the projection cust details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionCustDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionCustDetails>> listenersList = new ArrayList<ModelListener<ProjectionCustDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionCustDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionCustDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
