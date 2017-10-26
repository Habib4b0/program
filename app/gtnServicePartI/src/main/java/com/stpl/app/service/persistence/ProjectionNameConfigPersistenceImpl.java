package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchProjectionNameConfigException;
import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.model.impl.ProjectionNameConfigImpl;
import com.stpl.app.model.impl.ProjectionNameConfigModelImpl;
import com.stpl.app.service.persistence.ProjectionNameConfigPersistence;

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
 * The persistence implementation for the projection name config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionNameConfigPersistence
 * @see ProjectionNameConfigUtil
 * @generated
 */
public class ProjectionNameConfigPersistenceImpl extends BasePersistenceImpl<ProjectionNameConfig>
    implements ProjectionNameConfigPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProjectionNameConfigUtil} to access the projection name config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProjectionNameConfigImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED,
            ProjectionNameConfigImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED,
            ProjectionNameConfigImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROJECTIONNAMECONFIG = "SELECT projectionNameConfig FROM ProjectionNameConfig projectionNameConfig";
    private static final String _SQL_COUNT_PROJECTIONNAMECONFIG = "SELECT COUNT(projectionNameConfig) FROM ProjectionNameConfig projectionNameConfig";
    private static final String _ORDER_BY_ENTITY_ALIAS = "projectionNameConfig.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionNameConfig exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProjectionNameConfigPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "businessProcessType", "versionNo",
                "modifiedBy", "projectionNameConfigSid", "modifiedDate",
                "selectedAttributes"
            });
    private static ProjectionNameConfig _nullProjectionNameConfig = new ProjectionNameConfigImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProjectionNameConfig> toCacheModel() {
                return _nullProjectionNameConfigCacheModel;
            }
        };

    private static CacheModel<ProjectionNameConfig> _nullProjectionNameConfigCacheModel =
        new CacheModel<ProjectionNameConfig>() {
            @Override
            public ProjectionNameConfig toEntityModel() {
                return _nullProjectionNameConfig;
            }
        };

    public ProjectionNameConfigPersistenceImpl() {
        setModelClass(ProjectionNameConfig.class);
    }

    /**
     * Caches the projection name config in the entity cache if it is enabled.
     *
     * @param projectionNameConfig the projection name config
     */
    @Override
    public void cacheResult(ProjectionNameConfig projectionNameConfig) {
        EntityCacheUtil.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigImpl.class,
            projectionNameConfig.getPrimaryKey(), projectionNameConfig);

        projectionNameConfig.resetOriginalValues();
    }

    /**
     * Caches the projection name configs in the entity cache if it is enabled.
     *
     * @param projectionNameConfigs the projection name configs
     */
    @Override
    public void cacheResult(List<ProjectionNameConfig> projectionNameConfigs) {
        for (ProjectionNameConfig projectionNameConfig : projectionNameConfigs) {
            if (EntityCacheUtil.getResult(
                        ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionNameConfigImpl.class,
                        projectionNameConfig.getPrimaryKey()) == null) {
                cacheResult(projectionNameConfig);
            } else {
                projectionNameConfig.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all projection name configs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProjectionNameConfigImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProjectionNameConfigImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the projection name config.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProjectionNameConfig projectionNameConfig) {
        EntityCacheUtil.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigImpl.class, projectionNameConfig.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProjectionNameConfig> projectionNameConfigs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProjectionNameConfig projectionNameConfig : projectionNameConfigs) {
            EntityCacheUtil.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionNameConfigImpl.class,
                projectionNameConfig.getPrimaryKey());
        }
    }

    /**
     * Creates a new projection name config with the primary key. Does not add the projection name config to the database.
     *
     * @param projectionNameConfigSid the primary key for the new projection name config
     * @return the new projection name config
     */
    @Override
    public ProjectionNameConfig create(int projectionNameConfigSid) {
        ProjectionNameConfig projectionNameConfig = new ProjectionNameConfigImpl();

        projectionNameConfig.setNew(true);
        projectionNameConfig.setPrimaryKey(projectionNameConfigSid);

        return projectionNameConfig;
    }

    /**
     * Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionNameConfigSid the primary key of the projection name config
     * @return the projection name config that was removed
     * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig remove(int projectionNameConfigSid)
        throws NoSuchProjectionNameConfigException, SystemException {
        return remove((Serializable) projectionNameConfigSid);
    }

    /**
     * Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the projection name config
     * @return the projection name config that was removed
     * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig remove(Serializable primaryKey)
        throws NoSuchProjectionNameConfigException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProjectionNameConfig projectionNameConfig = (ProjectionNameConfig) session.get(ProjectionNameConfigImpl.class,
                    primaryKey);

            if (projectionNameConfig == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProjectionNameConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(projectionNameConfig);
        } catch (NoSuchProjectionNameConfigException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProjectionNameConfig removeImpl(
        ProjectionNameConfig projectionNameConfig) throws SystemException {
        projectionNameConfig = toUnwrappedModel(projectionNameConfig);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(projectionNameConfig)) {
                projectionNameConfig = (ProjectionNameConfig) session.get(ProjectionNameConfigImpl.class,
                        projectionNameConfig.getPrimaryKeyObj());
            }

            if (projectionNameConfig != null) {
                session.delete(projectionNameConfig);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (projectionNameConfig != null) {
            clearCache(projectionNameConfig);
        }

        return projectionNameConfig;
    }

    @Override
    public ProjectionNameConfig updateImpl(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig)
        throws SystemException {
        projectionNameConfig = toUnwrappedModel(projectionNameConfig);

        boolean isNew = projectionNameConfig.isNew();

        Session session = null;

        try {
            session = openSession();

            if (projectionNameConfig.isNew()) {
                session.save(projectionNameConfig);

                projectionNameConfig.setNew(false);
            } else {
                session.merge(projectionNameConfig);
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

        EntityCacheUtil.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
            ProjectionNameConfigImpl.class,
            projectionNameConfig.getPrimaryKey(), projectionNameConfig);

        return projectionNameConfig;
    }

    protected ProjectionNameConfig toUnwrappedModel(
        ProjectionNameConfig projectionNameConfig) {
        if (projectionNameConfig instanceof ProjectionNameConfigImpl) {
            return projectionNameConfig;
        }

        ProjectionNameConfigImpl projectionNameConfigImpl = new ProjectionNameConfigImpl();

        projectionNameConfigImpl.setNew(projectionNameConfig.isNew());
        projectionNameConfigImpl.setPrimaryKey(projectionNameConfig.getPrimaryKey());

        projectionNameConfigImpl.setCreatedDate(projectionNameConfig.getCreatedDate());
        projectionNameConfigImpl.setCreatedBy(projectionNameConfig.getCreatedBy());
        projectionNameConfigImpl.setBusinessProcessType(projectionNameConfig.getBusinessProcessType());
        projectionNameConfigImpl.setVersionNo(projectionNameConfig.getVersionNo());
        projectionNameConfigImpl.setModifiedBy(projectionNameConfig.getModifiedBy());
        projectionNameConfigImpl.setProjectionNameConfigSid(projectionNameConfig.getProjectionNameConfigSid());
        projectionNameConfigImpl.setModifiedDate(projectionNameConfig.getModifiedDate());
        projectionNameConfigImpl.setSelectedAttributes(projectionNameConfig.getSelectedAttributes());

        return projectionNameConfigImpl;
    }

    /**
     * Returns the projection name config with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the projection name config
     * @return the projection name config
     * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProjectionNameConfigException, SystemException {
        ProjectionNameConfig projectionNameConfig = fetchByPrimaryKey(primaryKey);

        if (projectionNameConfig == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProjectionNameConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return projectionNameConfig;
    }

    /**
     * Returns the projection name config with the primary key or throws a {@link com.stpl.app.NoSuchProjectionNameConfigException} if it could not be found.
     *
     * @param projectionNameConfigSid the primary key of the projection name config
     * @return the projection name config
     * @throws com.stpl.app.NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig findByPrimaryKey(int projectionNameConfigSid)
        throws NoSuchProjectionNameConfigException, SystemException {
        return findByPrimaryKey((Serializable) projectionNameConfigSid);
    }

    /**
     * Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the projection name config
     * @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProjectionNameConfig projectionNameConfig = (ProjectionNameConfig) EntityCacheUtil.getResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
                ProjectionNameConfigImpl.class, primaryKey);

        if (projectionNameConfig == _nullProjectionNameConfig) {
            return null;
        }

        if (projectionNameConfig == null) {
            Session session = null;

            try {
                session = openSession();

                projectionNameConfig = (ProjectionNameConfig) session.get(ProjectionNameConfigImpl.class,
                        primaryKey);

                if (projectionNameConfig != null) {
                    cacheResult(projectionNameConfig);
                } else {
                    EntityCacheUtil.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
                        ProjectionNameConfigImpl.class, primaryKey,
                        _nullProjectionNameConfig);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
                    ProjectionNameConfigImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return projectionNameConfig;
    }

    /**
     * Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionNameConfigSid the primary key of the projection name config
     * @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProjectionNameConfig fetchByPrimaryKey(int projectionNameConfigSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionNameConfigSid);
    }

    /**
     * Returns all the projection name configs.
     *
     * @return the projection name configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionNameConfig> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the projection name configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection name configs
     * @param end the upper bound of the range of projection name configs (not inclusive)
     * @return the range of projection name configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionNameConfig> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the projection name configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of projection name configs
     * @param end the upper bound of the range of projection name configs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of projection name configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProjectionNameConfig> findAll(int start, int end,
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

        List<ProjectionNameConfig> list = (List<ProjectionNameConfig>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROJECTIONNAMECONFIG);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROJECTIONNAMECONFIG;

                if (pagination) {
                    sql = sql.concat(ProjectionNameConfigModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProjectionNameConfig>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProjectionNameConfig>(list);
                } else {
                    list = (List<ProjectionNameConfig>) QueryUtil.list(q,
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
     * Removes all the projection name configs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProjectionNameConfig projectionNameConfig : findAll()) {
            remove(projectionNameConfig);
        }
    }

    /**
     * Returns the number of projection name configs.
     *
     * @return the number of projection name configs
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

                Query q = session.createQuery(_SQL_COUNT_PROJECTIONNAMECONFIG);

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
     * Initializes the projection name config persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ProjectionNameConfig")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProjectionNameConfig>> listenersList = new ArrayList<ModelListener<ProjectionNameConfig>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProjectionNameConfig>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProjectionNameConfigImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
