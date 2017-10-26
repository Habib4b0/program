package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCustomViewMasterException;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.impl.CustomViewMasterImpl;
import com.stpl.app.model.impl.CustomViewMasterModelImpl;
import com.stpl.app.service.persistence.CustomViewMasterPersistence;

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
 * The persistence implementation for the custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewMasterPersistence
 * @see CustomViewMasterUtil
 * @generated
 */
public class CustomViewMasterPersistenceImpl extends BasePersistenceImpl<CustomViewMaster>
    implements CustomViewMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CustomViewMasterUtil} to access the custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CustomViewMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CustomViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
            CustomViewMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CUSTOMVIEWMASTER = "SELECT customViewMaster FROM CustomViewMaster customViewMaster";
    private static final String _SQL_COUNT_CUSTOMVIEWMASTER = "SELECT COUNT(customViewMaster) FROM CustomViewMaster customViewMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "customViewMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomViewMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CustomViewMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "customViewMasterSid", "createdDate", "createdBy",
                "projectionMasterSid", "modifiedBy", "modifiedDate", "viewName"
            });
    private static CustomViewMaster _nullCustomViewMaster = new CustomViewMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CustomViewMaster> toCacheModel() {
                return _nullCustomViewMasterCacheModel;
            }
        };

    private static CacheModel<CustomViewMaster> _nullCustomViewMasterCacheModel = new CacheModel<CustomViewMaster>() {
            @Override
            public CustomViewMaster toEntityModel() {
                return _nullCustomViewMaster;
            }
        };

    public CustomViewMasterPersistenceImpl() {
        setModelClass(CustomViewMaster.class);
    }

    /**
     * Caches the custom view master in the entity cache if it is enabled.
     *
     * @param customViewMaster the custom view master
     */
    @Override
    public void cacheResult(CustomViewMaster customViewMaster) {
        EntityCacheUtil.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterImpl.class, customViewMaster.getPrimaryKey(),
            customViewMaster);

        customViewMaster.resetOriginalValues();
    }

    /**
     * Caches the custom view masters in the entity cache if it is enabled.
     *
     * @param customViewMasters the custom view masters
     */
    @Override
    public void cacheResult(List<CustomViewMaster> customViewMasters) {
        for (CustomViewMaster customViewMaster : customViewMasters) {
            if (EntityCacheUtil.getResult(
                        CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CustomViewMasterImpl.class,
                        customViewMaster.getPrimaryKey()) == null) {
                cacheResult(customViewMaster);
            } else {
                customViewMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all custom view masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CustomViewMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CustomViewMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the custom view master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CustomViewMaster customViewMaster) {
        EntityCacheUtil.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterImpl.class, customViewMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CustomViewMaster> customViewMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CustomViewMaster customViewMaster : customViewMasters) {
            EntityCacheUtil.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CustomViewMasterImpl.class, customViewMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new custom view master with the primary key. Does not add the custom view master to the database.
     *
     * @param customViewMasterSid the primary key for the new custom view master
     * @return the new custom view master
     */
    @Override
    public CustomViewMaster create(int customViewMasterSid) {
        CustomViewMaster customViewMaster = new CustomViewMasterImpl();

        customViewMaster.setNew(true);
        customViewMaster.setPrimaryKey(customViewMasterSid);

        return customViewMaster;
    }

    /**
     * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param customViewMasterSid the primary key of the custom view master
     * @return the custom view master that was removed
     * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster remove(int customViewMasterSid)
        throws NoSuchCustomViewMasterException, SystemException {
        return remove((Serializable) customViewMasterSid);
    }

    /**
     * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the custom view master
     * @return the custom view master that was removed
     * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster remove(Serializable primaryKey)
        throws NoSuchCustomViewMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CustomViewMaster customViewMaster = (CustomViewMaster) session.get(CustomViewMasterImpl.class,
                    primaryKey);

            if (customViewMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(customViewMaster);
        } catch (NoSuchCustomViewMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CustomViewMaster removeImpl(CustomViewMaster customViewMaster)
        throws SystemException {
        customViewMaster = toUnwrappedModel(customViewMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(customViewMaster)) {
                customViewMaster = (CustomViewMaster) session.get(CustomViewMasterImpl.class,
                        customViewMaster.getPrimaryKeyObj());
            }

            if (customViewMaster != null) {
                session.delete(customViewMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (customViewMaster != null) {
            clearCache(customViewMaster);
        }

        return customViewMaster;
    }

    @Override
    public CustomViewMaster updateImpl(
        com.stpl.app.model.CustomViewMaster customViewMaster)
        throws SystemException {
        customViewMaster = toUnwrappedModel(customViewMaster);

        boolean isNew = customViewMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (customViewMaster.isNew()) {
                session.save(customViewMaster);

                customViewMaster.setNew(false);
            } else {
                session.merge(customViewMaster);
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

        EntityCacheUtil.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
            CustomViewMasterImpl.class, customViewMaster.getPrimaryKey(),
            customViewMaster);

        return customViewMaster;
    }

    protected CustomViewMaster toUnwrappedModel(
        CustomViewMaster customViewMaster) {
        if (customViewMaster instanceof CustomViewMasterImpl) {
            return customViewMaster;
        }

        CustomViewMasterImpl customViewMasterImpl = new CustomViewMasterImpl();

        customViewMasterImpl.setNew(customViewMaster.isNew());
        customViewMasterImpl.setPrimaryKey(customViewMaster.getPrimaryKey());

        customViewMasterImpl.setCustomViewMasterSid(customViewMaster.getCustomViewMasterSid());
        customViewMasterImpl.setCreatedDate(customViewMaster.getCreatedDate());
        customViewMasterImpl.setCreatedBy(customViewMaster.getCreatedBy());
        customViewMasterImpl.setProjectionMasterSid(customViewMaster.getProjectionMasterSid());
        customViewMasterImpl.setModifiedBy(customViewMaster.getModifiedBy());
        customViewMasterImpl.setModifiedDate(customViewMaster.getModifiedDate());
        customViewMasterImpl.setViewName(customViewMaster.getViewName());

        return customViewMasterImpl;
    }

    /**
     * Returns the custom view master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the custom view master
     * @return the custom view master
     * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCustomViewMasterException, SystemException {
        CustomViewMaster customViewMaster = fetchByPrimaryKey(primaryKey);

        if (customViewMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return customViewMaster;
    }

    /**
     * Returns the custom view master with the primary key or throws a {@link com.stpl.app.NoSuchCustomViewMasterException} if it could not be found.
     *
     * @param customViewMasterSid the primary key of the custom view master
     * @return the custom view master
     * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster findByPrimaryKey(int customViewMasterSid)
        throws NoSuchCustomViewMasterException, SystemException {
        return findByPrimaryKey((Serializable) customViewMasterSid);
    }

    /**
     * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the custom view master
     * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CustomViewMaster customViewMaster = (CustomViewMaster) EntityCacheUtil.getResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                CustomViewMasterImpl.class, primaryKey);

        if (customViewMaster == _nullCustomViewMaster) {
            return null;
        }

        if (customViewMaster == null) {
            Session session = null;

            try {
                session = openSession();

                customViewMaster = (CustomViewMaster) session.get(CustomViewMasterImpl.class,
                        primaryKey);

                if (customViewMaster != null) {
                    cacheResult(customViewMaster);
                } else {
                    EntityCacheUtil.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CustomViewMasterImpl.class, primaryKey,
                        _nullCustomViewMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
                    CustomViewMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return customViewMaster;
    }

    /**
     * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param customViewMasterSid the primary key of the custom view master
     * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CustomViewMaster fetchByPrimaryKey(int customViewMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) customViewMasterSid);
    }

    /**
     * Returns all the custom view masters.
     *
     * @return the custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomViewMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the custom view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of custom view masters
     * @param end the upper bound of the range of custom view masters (not inclusive)
     * @return the range of custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomViewMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the custom view masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of custom view masters
     * @param end the upper bound of the range of custom view masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of custom view masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CustomViewMaster> findAll(int start, int end,
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

        List<CustomViewMaster> list = (List<CustomViewMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CUSTOMVIEWMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CUSTOMVIEWMASTER;

                if (pagination) {
                    sql = sql.concat(CustomViewMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CustomViewMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CustomViewMaster>(list);
                } else {
                    list = (List<CustomViewMaster>) QueryUtil.list(q,
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
     * Removes all the custom view masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CustomViewMaster customViewMaster : findAll()) {
            remove(customViewMaster);
        }
    }

    /**
     * Returns the number of custom view masters.
     *
     * @return the number of custom view masters
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

                Query q = session.createQuery(_SQL_COUNT_CUSTOMVIEWMASTER);

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
     * Initializes the custom view master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CustomViewMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CustomViewMaster>> listenersList = new ArrayList<ModelListener<CustomViewMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CustomViewMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CustomViewMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
