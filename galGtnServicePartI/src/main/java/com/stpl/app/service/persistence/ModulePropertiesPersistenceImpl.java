package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchModulePropertiesException;
import com.stpl.app.model.ModuleProperties;
import com.stpl.app.model.impl.ModulePropertiesImpl;
import com.stpl.app.model.impl.ModulePropertiesModelImpl;
import com.stpl.app.service.persistence.ModulePropertiesPersistence;

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
 * The persistence implementation for the module properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModulePropertiesPersistence
 * @see ModulePropertiesUtil
 * @generated
 */
public class ModulePropertiesPersistenceImpl extends BasePersistenceImpl<ModuleProperties>
    implements ModulePropertiesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModulePropertiesUtil} to access the module properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModulePropertiesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesModelImpl.FINDER_CACHE_ENABLED,
            ModulePropertiesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesModelImpl.FINDER_CACHE_ENABLED,
            ModulePropertiesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MODULEPROPERTIES = "SELECT moduleProperties FROM ModuleProperties moduleProperties";
    private static final String _SQL_COUNT_MODULEPROPERTIES = "SELECT COUNT(moduleProperties) FROM ModuleProperties moduleProperties";
    private static final String _ORDER_BY_ENTITY_ALIAS = "moduleProperties.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModuleProperties exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModulePropertiesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "modulePropertySid", "createdBy", "moduleName", "modifiedBy",
                "createdDate", "nullFlag", "versionNo", "moduleSubmoduleSid",
                "categoryName", "propertyName", "displayName", "modifiedDate"
            });
    private static ModuleProperties _nullModuleProperties = new ModulePropertiesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModuleProperties> toCacheModel() {
                return _nullModulePropertiesCacheModel;
            }
        };

    private static CacheModel<ModuleProperties> _nullModulePropertiesCacheModel = new CacheModel<ModuleProperties>() {
            @Override
            public ModuleProperties toEntityModel() {
                return _nullModuleProperties;
            }
        };

    public ModulePropertiesPersistenceImpl() {
        setModelClass(ModuleProperties.class);
    }

    /**
     * Caches the module properties in the entity cache if it is enabled.
     *
     * @param moduleProperties the module properties
     */
    @Override
    public void cacheResult(ModuleProperties moduleProperties) {
        EntityCacheUtil.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesImpl.class, moduleProperties.getPrimaryKey(),
            moduleProperties);

        moduleProperties.resetOriginalValues();
    }

    /**
     * Caches the module propertieses in the entity cache if it is enabled.
     *
     * @param modulePropertieses the module propertieses
     */
    @Override
    public void cacheResult(List<ModuleProperties> modulePropertieses) {
        for (ModuleProperties moduleProperties : modulePropertieses) {
            if (EntityCacheUtil.getResult(
                        ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
                        ModulePropertiesImpl.class,
                        moduleProperties.getPrimaryKey()) == null) {
                cacheResult(moduleProperties);
            } else {
                moduleProperties.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all module propertieses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModulePropertiesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModulePropertiesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the module properties.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModuleProperties moduleProperties) {
        EntityCacheUtil.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesImpl.class, moduleProperties.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ModuleProperties> modulePropertieses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModuleProperties moduleProperties : modulePropertieses) {
            EntityCacheUtil.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
                ModulePropertiesImpl.class, moduleProperties.getPrimaryKey());
        }
    }

    /**
     * Creates a new module properties with the primary key. Does not add the module properties to the database.
     *
     * @param modulePropertySid the primary key for the new module properties
     * @return the new module properties
     */
    @Override
    public ModuleProperties create(int modulePropertySid) {
        ModuleProperties moduleProperties = new ModulePropertiesImpl();

        moduleProperties.setNew(true);
        moduleProperties.setPrimaryKey(modulePropertySid);

        return moduleProperties;
    }

    /**
     * Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modulePropertySid the primary key of the module properties
     * @return the module properties that was removed
     * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties remove(int modulePropertySid)
        throws NoSuchModulePropertiesException, SystemException {
        return remove((Serializable) modulePropertySid);
    }

    /**
     * Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the module properties
     * @return the module properties that was removed
     * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties remove(Serializable primaryKey)
        throws NoSuchModulePropertiesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModuleProperties moduleProperties = (ModuleProperties) session.get(ModulePropertiesImpl.class,
                    primaryKey);

            if (moduleProperties == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModulePropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(moduleProperties);
        } catch (NoSuchModulePropertiesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModuleProperties removeImpl(ModuleProperties moduleProperties)
        throws SystemException {
        moduleProperties = toUnwrappedModel(moduleProperties);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(moduleProperties)) {
                moduleProperties = (ModuleProperties) session.get(ModulePropertiesImpl.class,
                        moduleProperties.getPrimaryKeyObj());
            }

            if (moduleProperties != null) {
                session.delete(moduleProperties);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (moduleProperties != null) {
            clearCache(moduleProperties);
        }

        return moduleProperties;
    }

    @Override
    public ModuleProperties updateImpl(
        com.stpl.app.model.ModuleProperties moduleProperties)
        throws SystemException {
        moduleProperties = toUnwrappedModel(moduleProperties);

        boolean isNew = moduleProperties.isNew();

        Session session = null;

        try {
            session = openSession();

            if (moduleProperties.isNew()) {
                session.save(moduleProperties);

                moduleProperties.setNew(false);
            } else {
                session.merge(moduleProperties);
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

        EntityCacheUtil.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
            ModulePropertiesImpl.class, moduleProperties.getPrimaryKey(),
            moduleProperties);

        return moduleProperties;
    }

    protected ModuleProperties toUnwrappedModel(
        ModuleProperties moduleProperties) {
        if (moduleProperties instanceof ModulePropertiesImpl) {
            return moduleProperties;
        }

        ModulePropertiesImpl modulePropertiesImpl = new ModulePropertiesImpl();

        modulePropertiesImpl.setNew(moduleProperties.isNew());
        modulePropertiesImpl.setPrimaryKey(moduleProperties.getPrimaryKey());

        modulePropertiesImpl.setModulePropertySid(moduleProperties.getModulePropertySid());
        modulePropertiesImpl.setCreatedBy(moduleProperties.getCreatedBy());
        modulePropertiesImpl.setModuleName(moduleProperties.getModuleName());
        modulePropertiesImpl.setModifiedBy(moduleProperties.getModifiedBy());
        modulePropertiesImpl.setCreatedDate(moduleProperties.getCreatedDate());
        modulePropertiesImpl.setNullFlag(moduleProperties.getNullFlag());
        modulePropertiesImpl.setVersionNo(moduleProperties.getVersionNo());
        modulePropertiesImpl.setModuleSubmoduleSid(moduleProperties.getModuleSubmoduleSid());
        modulePropertiesImpl.setCategoryName(moduleProperties.getCategoryName());
        modulePropertiesImpl.setPropertyName(moduleProperties.getPropertyName());
        modulePropertiesImpl.setDisplayName(moduleProperties.getDisplayName());
        modulePropertiesImpl.setModifiedDate(moduleProperties.getModifiedDate());

        return modulePropertiesImpl;
    }

    /**
     * Returns the module properties with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the module properties
     * @return the module properties
     * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModulePropertiesException, SystemException {
        ModuleProperties moduleProperties = fetchByPrimaryKey(primaryKey);

        if (moduleProperties == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchModulePropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return moduleProperties;
    }

    /**
     * Returns the module properties with the primary key or throws a {@link com.stpl.app.NoSuchModulePropertiesException} if it could not be found.
     *
     * @param modulePropertySid the primary key of the module properties
     * @return the module properties
     * @throws com.stpl.app.NoSuchModulePropertiesException if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties findByPrimaryKey(int modulePropertySid)
        throws NoSuchModulePropertiesException, SystemException {
        return findByPrimaryKey((Serializable) modulePropertySid);
    }

    /**
     * Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the module properties
     * @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ModuleProperties moduleProperties = (ModuleProperties) EntityCacheUtil.getResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
                ModulePropertiesImpl.class, primaryKey);

        if (moduleProperties == _nullModuleProperties) {
            return null;
        }

        if (moduleProperties == null) {
            Session session = null;

            try {
                session = openSession();

                moduleProperties = (ModuleProperties) session.get(ModulePropertiesImpl.class,
                        primaryKey);

                if (moduleProperties != null) {
                    cacheResult(moduleProperties);
                } else {
                    EntityCacheUtil.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
                        ModulePropertiesImpl.class, primaryKey,
                        _nullModuleProperties);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
                    ModulePropertiesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return moduleProperties;
    }

    /**
     * Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modulePropertySid the primary key of the module properties
     * @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModuleProperties fetchByPrimaryKey(int modulePropertySid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) modulePropertySid);
    }

    /**
     * Returns all the module propertieses.
     *
     * @return the module propertieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleProperties> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the module propertieses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of module propertieses
     * @param end the upper bound of the range of module propertieses (not inclusive)
     * @return the range of module propertieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleProperties> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the module propertieses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of module propertieses
     * @param end the upper bound of the range of module propertieses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of module propertieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModuleProperties> findAll(int start, int end,
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

        List<ModuleProperties> list = (List<ModuleProperties>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODULEPROPERTIES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODULEPROPERTIES;

                if (pagination) {
                    sql = sql.concat(ModulePropertiesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ModuleProperties>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModuleProperties>(list);
                } else {
                    list = (List<ModuleProperties>) QueryUtil.list(q,
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
     * Removes all the module propertieses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ModuleProperties moduleProperties : findAll()) {
            remove(moduleProperties);
        }
    }

    /**
     * Returns the number of module propertieses.
     *
     * @return the number of module propertieses
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

                Query q = session.createQuery(_SQL_COUNT_MODULEPROPERTIES);

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
     * Initializes the module properties persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ModuleProperties")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModuleProperties>> listenersList = new ArrayList<ModelListener<ModuleProperties>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModuleProperties>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModulePropertiesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
