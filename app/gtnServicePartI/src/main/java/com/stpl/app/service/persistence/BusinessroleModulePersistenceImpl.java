package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchBusinessroleModuleException;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.model.impl.BusinessroleModuleImpl;
import com.stpl.app.model.impl.BusinessroleModuleModelImpl;
import com.stpl.app.service.persistence.BusinessroleModulePersistence;

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
 * The persistence implementation for the businessrole module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleModulePersistence
 * @see BusinessroleModuleUtil
 * @generated
 */
public class BusinessroleModulePersistenceImpl extends BasePersistenceImpl<BusinessroleModule>
    implements BusinessroleModulePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BusinessroleModuleUtil} to access the businessrole module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BusinessroleModuleImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleModuleImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED,
            BusinessroleModuleImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_BUSINESSROLEMODULE = "SELECT businessroleModule FROM BusinessroleModule businessroleModule";
    private static final String _SQL_COUNT_BUSINESSROLEMODULE = "SELECT COUNT(businessroleModule) FROM BusinessroleModule businessroleModule";
    private static final String _ORDER_BY_ENTITY_ALIAS = "businessroleModule.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BusinessroleModule exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BusinessroleModulePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "businessroleModuleSid", "businessroleMasterSid",
                "addFlag", "viewFlag", "modifiedBy", "createdDate",
                "submodulePropertyId", "editFlag", "versionNo", "accessModule",
                "modifiedDate"
            });
    private static BusinessroleModule _nullBusinessroleModule = new BusinessroleModuleImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BusinessroleModule> toCacheModel() {
                return _nullBusinessroleModuleCacheModel;
            }
        };

    private static CacheModel<BusinessroleModule> _nullBusinessroleModuleCacheModel =
        new CacheModel<BusinessroleModule>() {
            @Override
            public BusinessroleModule toEntityModel() {
                return _nullBusinessroleModule;
            }
        };

    public BusinessroleModulePersistenceImpl() {
        setModelClass(BusinessroleModule.class);
    }

    /**
     * Caches the businessrole module in the entity cache if it is enabled.
     *
     * @param businessroleModule the businessrole module
     */
    @Override
    public void cacheResult(BusinessroleModule businessroleModule) {
        EntityCacheUtil.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey(),
            businessroleModule);

        businessroleModule.resetOriginalValues();
    }

    /**
     * Caches the businessrole modules in the entity cache if it is enabled.
     *
     * @param businessroleModules the businessrole modules
     */
    @Override
    public void cacheResult(List<BusinessroleModule> businessroleModules) {
        for (BusinessroleModule businessroleModule : businessroleModules) {
            if (EntityCacheUtil.getResult(
                        BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
                        BusinessroleModuleImpl.class,
                        businessroleModule.getPrimaryKey()) == null) {
                cacheResult(businessroleModule);
            } else {
                businessroleModule.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all businessrole modules.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BusinessroleModuleImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BusinessroleModuleImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the businessrole module.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BusinessroleModule businessroleModule) {
        EntityCacheUtil.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BusinessroleModule> businessroleModules) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BusinessroleModule businessroleModule : businessroleModules) {
            EntityCacheUtil.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
                BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey());
        }
    }

    /**
     * Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
     *
     * @param businessroleModuleSid the primary key for the new businessrole module
     * @return the new businessrole module
     */
    @Override
    public BusinessroleModule create(int businessroleModuleSid) {
        BusinessroleModule businessroleModule = new BusinessroleModuleImpl();

        businessroleModule.setNew(true);
        businessroleModule.setPrimaryKey(businessroleModuleSid);

        return businessroleModule;
    }

    /**
     * Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param businessroleModuleSid the primary key of the businessrole module
     * @return the businessrole module that was removed
     * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule remove(int businessroleModuleSid)
        throws NoSuchBusinessroleModuleException, SystemException {
        return remove((Serializable) businessroleModuleSid);
    }

    /**
     * Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the businessrole module
     * @return the businessrole module that was removed
     * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule remove(Serializable primaryKey)
        throws NoSuchBusinessroleModuleException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BusinessroleModule businessroleModule = (BusinessroleModule) session.get(BusinessroleModuleImpl.class,
                    primaryKey);

            if (businessroleModule == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBusinessroleModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(businessroleModule);
        } catch (NoSuchBusinessroleModuleException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BusinessroleModule removeImpl(
        BusinessroleModule businessroleModule) throws SystemException {
        businessroleModule = toUnwrappedModel(businessroleModule);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(businessroleModule)) {
                businessroleModule = (BusinessroleModule) session.get(BusinessroleModuleImpl.class,
                        businessroleModule.getPrimaryKeyObj());
            }

            if (businessroleModule != null) {
                session.delete(businessroleModule);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (businessroleModule != null) {
            clearCache(businessroleModule);
        }

        return businessroleModule;
    }

    @Override
    public BusinessroleModule updateImpl(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws SystemException {
        businessroleModule = toUnwrappedModel(businessroleModule);

        boolean isNew = businessroleModule.isNew();

        Session session = null;

        try {
            session = openSession();

            if (businessroleModule.isNew()) {
                session.save(businessroleModule);

                businessroleModule.setNew(false);
            } else {
                session.merge(businessroleModule);
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

        EntityCacheUtil.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
            BusinessroleModuleImpl.class, businessroleModule.getPrimaryKey(),
            businessroleModule);

        return businessroleModule;
    }

    protected BusinessroleModule toUnwrappedModel(
        BusinessroleModule businessroleModule) {
        if (businessroleModule instanceof BusinessroleModuleImpl) {
            return businessroleModule;
        }

        BusinessroleModuleImpl businessroleModuleImpl = new BusinessroleModuleImpl();

        businessroleModuleImpl.setNew(businessroleModule.isNew());
        businessroleModuleImpl.setPrimaryKey(businessroleModule.getPrimaryKey());

        businessroleModuleImpl.setCreatedBy(businessroleModule.getCreatedBy());
        businessroleModuleImpl.setBusinessroleModuleSid(businessroleModule.getBusinessroleModuleSid());
        businessroleModuleImpl.setBusinessroleMasterSid(businessroleModule.getBusinessroleMasterSid());
        businessroleModuleImpl.setAddFlag(businessroleModule.getAddFlag());
        businessroleModuleImpl.setViewFlag(businessroleModule.getViewFlag());
        businessroleModuleImpl.setModifiedBy(businessroleModule.getModifiedBy());
        businessroleModuleImpl.setCreatedDate(businessroleModule.getCreatedDate());
        businessroleModuleImpl.setSubmodulePropertyId(businessroleModule.getSubmodulePropertyId());
        businessroleModuleImpl.setEditFlag(businessroleModule.getEditFlag());
        businessroleModuleImpl.setVersionNo(businessroleModule.getVersionNo());
        businessroleModuleImpl.setAccessModule(businessroleModule.getAccessModule());
        businessroleModuleImpl.setModifiedDate(businessroleModule.getModifiedDate());

        return businessroleModuleImpl;
    }

    /**
     * Returns the businessrole module with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the businessrole module
     * @return the businessrole module
     * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBusinessroleModuleException, SystemException {
        BusinessroleModule businessroleModule = fetchByPrimaryKey(primaryKey);

        if (businessroleModule == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBusinessroleModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return businessroleModule;
    }

    /**
     * Returns the businessrole module with the primary key or throws a {@link com.stpl.app.NoSuchBusinessroleModuleException} if it could not be found.
     *
     * @param businessroleModuleSid the primary key of the businessrole module
     * @return the businessrole module
     * @throws com.stpl.app.NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule findByPrimaryKey(int businessroleModuleSid)
        throws NoSuchBusinessroleModuleException, SystemException {
        return findByPrimaryKey((Serializable) businessroleModuleSid);
    }

    /**
     * Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the businessrole module
     * @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BusinessroleModule businessroleModule = (BusinessroleModule) EntityCacheUtil.getResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
                BusinessroleModuleImpl.class, primaryKey);

        if (businessroleModule == _nullBusinessroleModule) {
            return null;
        }

        if (businessroleModule == null) {
            Session session = null;

            try {
                session = openSession();

                businessroleModule = (BusinessroleModule) session.get(BusinessroleModuleImpl.class,
                        primaryKey);

                if (businessroleModule != null) {
                    cacheResult(businessroleModule);
                } else {
                    EntityCacheUtil.putResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
                        BusinessroleModuleImpl.class, primaryKey,
                        _nullBusinessroleModule);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BusinessroleModuleModelImpl.ENTITY_CACHE_ENABLED,
                    BusinessroleModuleImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return businessroleModule;
    }

    /**
     * Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param businessroleModuleSid the primary key of the businessrole module
     * @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BusinessroleModule fetchByPrimaryKey(int businessroleModuleSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) businessroleModuleSid);
    }

    /**
     * Returns all the businessrole modules.
     *
     * @return the businessrole modules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleModule> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the businessrole modules.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of businessrole modules
     * @param end the upper bound of the range of businessrole modules (not inclusive)
     * @return the range of businessrole modules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleModule> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the businessrole modules.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of businessrole modules
     * @param end the upper bound of the range of businessrole modules (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of businessrole modules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BusinessroleModule> findAll(int start, int end,
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

        List<BusinessroleModule> list = (List<BusinessroleModule>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BUSINESSROLEMODULE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BUSINESSROLEMODULE;

                if (pagination) {
                    sql = sql.concat(BusinessroleModuleModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BusinessroleModule>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BusinessroleModule>(list);
                } else {
                    list = (List<BusinessroleModule>) QueryUtil.list(q,
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
     * Removes all the businessrole modules from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BusinessroleModule businessroleModule : findAll()) {
            remove(businessroleModule);
        }
    }

    /**
     * Returns the number of businessrole modules.
     *
     * @return the number of businessrole modules
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

                Query q = session.createQuery(_SQL_COUNT_BUSINESSROLEMODULE);

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
     * Initializes the businessrole module persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.BusinessroleModule")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BusinessroleModule>> listenersList = new ArrayList<ModelListener<BusinessroleModule>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BusinessroleModule>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BusinessroleModuleImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
