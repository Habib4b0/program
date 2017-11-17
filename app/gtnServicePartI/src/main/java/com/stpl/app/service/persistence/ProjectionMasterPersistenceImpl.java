package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionMasterException;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.impl.ProjectionMasterImpl;
import com.stpl.app.model.impl.ProjectionMasterModelImpl;
import com.stpl.app.service.persistence.ProjectionMasterPersistence;

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
 * The persistence implementation for the projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionMasterPersistence
 * @see ProjectionMasterUtil
 * @generated
 */
public class ProjectionMasterPersistenceImpl extends BasePersistenceImpl<ProjectionMaster>
    implements ProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionMasterUtil} to access the projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            ProjectionMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            ProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONMASTER = "SELECT projectionMaster FROM ProjectionMaster projectionMaster";
    private static final String _SQL_COUNT_PROJECTIONMASTER = "SELECT COUNT(projectionMaster) FROM ProjectionMaster projectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "productHierarchyLevel", "saveFlag", "projectionName", "toDate",
                "projectionMasterSid", "forecastingType", "productHierVersionNo",
                "customerHierVersionNo", "modifiedDate",
                "customerHierarchyLevel", "fromDate", "productHierarchySid",
                "createdDate", "createdBy", "customerHierarchySid",
                "companyGroupSid", "brandType", "modifiedBy",
                "projectionDescription", "isApproved", "itemGroupSid",
                "companyMasterSid", "customerHierarchyInnerLevel",
                "productHierarchyInnerLevel", "custRelationshipBuilderSid",
                "prodRelationshipBuilderSid", "discountType", "businessUnit",
                "deductionHierarchySid", "dedRelationshipBuilderSid",
                "projectionCustVersionNo", "projectionProdVersionNo"
            });
    private static ProjectionMaster _nullProjectionMaster = new ProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionMaster> toCacheModel() {
                return _nullProjectionMasterCacheModel;
            }
        };

    private static CacheModel<ProjectionMaster> _nullProjectionMasterCacheModel = new CacheModel<ProjectionMaster>() {
            @Override
            public ProjectionMaster toEntityModel() {
                return _nullProjectionMaster;
            }
        };

    public ProjectionMasterPersistenceImpl() {
        setModelClass(ProjectionMaster.class);
    }

    /**
     * Caches the projection master in the entity cache if it is enabled.
     *
     * @param projectionMaster the projection master
     */
    @Override
    public void cacheResult(ProjectionMaster projectionMaster) {
        EntityCacheUtil.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterImpl.class, projectionMaster.getPrimaryKey(),
            projectionMaster);

        projectionMaster.resetOriginalValues();
    }

    /**
     * Caches the projection masters in the entity cache if it is enabled.
     *
     * @param projectionMasters the projection masters
     */
    @Override
    public void cacheResult(List<ProjectionMaster> projectionMasters) {
        for (ProjectionMaster projectionMaster : projectionMasters) {
            if (EntityCacheUtil.getResult(
                        ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionMasterImpl.class,
                        projectionMaster.getPrimaryKey()) == null) {
                cacheResult(projectionMaster);
            } else {
                projectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionMaster projectionMaster) {
        EntityCacheUtil.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterImpl.class, projectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProjectionMaster> projectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionMaster projectionMaster : projectionMasters) {
            EntityCacheUtil.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionMasterImpl.class, projectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection master with the primary key. Does not add the projection master to the database.
     *
     * @param projectionMasterSid the primary key for the new projection master
     * @return the new projection master
     */
    @Override
    public ProjectionMaster create(int projectionMasterSid) {
        ProjectionMaster projectionMaster = new ProjectionMasterImpl();

        projectionMaster.setNew(true);
        projectionMaster.setPrimaryKey(projectionMasterSid);

        return projectionMaster;
    }

    /**
     * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionMasterSid the primary key of the projection master
     * @return the projection master that was removed
     * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster remove(int projectionMasterSid)
        throws NoSuchProjectionMasterException, SystemException {
        return remove((Serializable) projectionMasterSid);
    }

    /**
     * Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection master
     * @return the projection master that was removed
     * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster remove(Serializable primaryKey)
        throws NoSuchProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionMaster projectionMaster = (ProjectionMaster) session.get(ProjectionMasterImpl.class,
                    primaryKey);

            if (projectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionMaster);
        } catch (NoSuchProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionMaster removeImpl(ProjectionMaster projectionMaster)
        throws SystemException {
        projectionMaster = toUnwrappedModel(projectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionMaster)) {
                projectionMaster = (ProjectionMaster) session.get(ProjectionMasterImpl.class,
                        projectionMaster.getPrimaryKeyObj());
            }

            if (projectionMaster != null) {
                session.delete(projectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionMaster != null) {
            clearCache(projectionMaster);
        }

        return projectionMaster;
    }

    @Override
    public ProjectionMaster updateImpl(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws SystemException {
        projectionMaster = toUnwrappedModel(projectionMaster);

        boolean isNew = projectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionMaster.isNew()) {
                session.save(projectionMaster);

                projectionMaster.setNew(false);
            } else {
                session.merge(projectionMaster);
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

        EntityCacheUtil.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionMasterImpl.class, projectionMaster.getPrimaryKey(),
            projectionMaster);

        return projectionMaster;
    }

    protected ProjectionMaster toUnwrappedModel(
        ProjectionMaster projectionMaster) {
        if (projectionMaster instanceof ProjectionMasterImpl) {
            return projectionMaster;
        }

        ProjectionMasterImpl projectionMasterImpl = new ProjectionMasterImpl();

        projectionMasterImpl.setNew(projectionMaster.isNew());
        projectionMasterImpl.setPrimaryKey(projectionMaster.getPrimaryKey());

        projectionMasterImpl.setProductHierarchyLevel(projectionMaster.getProductHierarchyLevel());
        projectionMasterImpl.setSaveFlag(projectionMaster.isSaveFlag());
        projectionMasterImpl.setProjectionName(projectionMaster.getProjectionName());
        projectionMasterImpl.setToDate(projectionMaster.getToDate());
        projectionMasterImpl.setProjectionMasterSid(projectionMaster.getProjectionMasterSid());
        projectionMasterImpl.setForecastingType(projectionMaster.getForecastingType());
        projectionMasterImpl.setProductHierVersionNo(projectionMaster.getProductHierVersionNo());
        projectionMasterImpl.setCustomerHierVersionNo(projectionMaster.getCustomerHierVersionNo());
        projectionMasterImpl.setModifiedDate(projectionMaster.getModifiedDate());
        projectionMasterImpl.setCustomerHierarchyLevel(projectionMaster.getCustomerHierarchyLevel());
        projectionMasterImpl.setFromDate(projectionMaster.getFromDate());
        projectionMasterImpl.setProductHierarchySid(projectionMaster.getProductHierarchySid());
        projectionMasterImpl.setCreatedDate(projectionMaster.getCreatedDate());
        projectionMasterImpl.setCreatedBy(projectionMaster.getCreatedBy());
        projectionMasterImpl.setCustomerHierarchySid(projectionMaster.getCustomerHierarchySid());
        projectionMasterImpl.setCompanyGroupSid(projectionMaster.getCompanyGroupSid());
        projectionMasterImpl.setBrandType(projectionMaster.isBrandType());
        projectionMasterImpl.setModifiedBy(projectionMaster.getModifiedBy());
        projectionMasterImpl.setProjectionDescription(projectionMaster.getProjectionDescription());
        projectionMasterImpl.setIsApproved(projectionMaster.getIsApproved());
        projectionMasterImpl.setItemGroupSid(projectionMaster.getItemGroupSid());
        projectionMasterImpl.setCompanyMasterSid(projectionMaster.getCompanyMasterSid());
        projectionMasterImpl.setCustomerHierarchyInnerLevel(projectionMaster.getCustomerHierarchyInnerLevel());
        projectionMasterImpl.setProductHierarchyInnerLevel(projectionMaster.getProductHierarchyInnerLevel());
        projectionMasterImpl.setCustRelationshipBuilderSid(projectionMaster.getCustRelationshipBuilderSid());
        projectionMasterImpl.setProdRelationshipBuilderSid(projectionMaster.getProdRelationshipBuilderSid());
        projectionMasterImpl.setDiscountType(projectionMaster.getDiscountType());
        projectionMasterImpl.setBusinessUnit(projectionMaster.getBusinessUnit());
        projectionMasterImpl.setDeductionHierarchySid(projectionMaster.getDeductionHierarchySid());
        projectionMasterImpl.setDedRelationshipBuilderSid(projectionMaster.getDedRelationshipBuilderSid());
        projectionMasterImpl.setProjectionCustVersionNo(projectionMaster.getProjectionCustVersionNo());
        projectionMasterImpl.setProjectionProdVersionNo(projectionMaster.getProjectionProdVersionNo());

        return projectionMasterImpl;
    }

    /**
     * Returns the projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection master
     * @return the projection master
     * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionMasterException, SystemException {
        ProjectionMaster projectionMaster = fetchByPrimaryKey(primaryKey);

        if (projectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionMaster;
    }

    /**
     * Returns the projection master with the primary key or throws a {@link com.stpl.app.NoSuchProjectionMasterException} if it could not be found.
     *
     * @param projectionMasterSid the primary key of the projection master
     * @return the projection master
     * @throws com.stpl.app.NoSuchProjectionMasterException if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster findByPrimaryKey(int projectionMasterSid)
        throws NoSuchProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionMasterSid);
    }

    /**
     * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection master
     * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionMaster projectionMaster = (ProjectionMaster) EntityCacheUtil.getResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionMasterImpl.class, primaryKey);

        if (projectionMaster == _nullProjectionMaster) {
            return null;
        }

        if (projectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                projectionMaster = (ProjectionMaster) session.get(ProjectionMasterImpl.class,
                        primaryKey);

                if (projectionMaster != null) {
                    cacheResult(projectionMaster);
                } else {
                    EntityCacheUtil.putResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionMasterImpl.class, primaryKey,
                        _nullProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionMaster;
    }

    /**
     * Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionMasterSid the primary key of the projection master
     * @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionMaster fetchByPrimaryKey(int projectionMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionMasterSid);
    }

    /**
     * Returns all the projection masters.
     *
     * @return the projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection masters
     * @param end the upper bound of the range of projection masters (not inclusive)
     * @return the range of projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection masters
     * @param end the upper bound of the range of projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionMaster> findAll(int start, int end,
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

        List<ProjectionMaster> list = (List<ProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(ProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionMaster>(list);
                } else {
                    list = (List<ProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionMaster projectionMaster : findAll()) {
            remove(projectionMaster);
        }
    }

    /**
     * Returns the number of projection masters.
     *
     * @return the number of projection masters
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONMASTER);

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
     * Initializes the projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionMaster>> listenersList = new ArrayList<ModelListener<ProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
