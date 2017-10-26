package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchUsergroupBusinessroleException;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.impl.UsergroupBusinessroleImpl;
import com.stpl.app.model.impl.UsergroupBusinessroleModelImpl;
import com.stpl.app.service.persistence.UsergroupBusinessrolePersistence;

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
 * The persistence implementation for the usergroup businessrole service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupBusinessrolePersistence
 * @see UsergroupBusinessroleUtil
 * @generated
 */
public class UsergroupBusinessrolePersistenceImpl extends BasePersistenceImpl<UsergroupBusinessrole>
    implements UsergroupBusinessrolePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link UsergroupBusinessroleUtil} to access the usergroup businessrole persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = UsergroupBusinessroleImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED,
            UsergroupBusinessroleImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED,
            UsergroupBusinessroleImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_USERGROUPBUSINESSROLE = "SELECT usergroupBusinessrole FROM UsergroupBusinessrole usergroupBusinessrole";
    private static final String _SQL_COUNT_USERGROUPBUSINESSROLE = "SELECT COUNT(usergroupBusinessrole) FROM UsergroupBusinessrole usergroupBusinessrole";
    private static final String _ORDER_BY_ENTITY_ALIAS = "usergroupBusinessrole.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UsergroupBusinessrole exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(UsergroupBusinessrolePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "businessroleMasterSid", "usersSid", "modifiedBy",
                "createdDate", "processed", "usergroupId", "versionNo",
                "isActive", "usergroupBusinessroleSid", "modifiedDate"
            });
    private static UsergroupBusinessrole _nullUsergroupBusinessrole = new UsergroupBusinessroleImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<UsergroupBusinessrole> toCacheModel() {
                return _nullUsergroupBusinessroleCacheModel;
            }
        };

    private static CacheModel<UsergroupBusinessrole> _nullUsergroupBusinessroleCacheModel =
        new CacheModel<UsergroupBusinessrole>() {
            @Override
            public UsergroupBusinessrole toEntityModel() {
                return _nullUsergroupBusinessrole;
            }
        };

    public UsergroupBusinessrolePersistenceImpl() {
        setModelClass(UsergroupBusinessrole.class);
    }

    /**
     * Caches the usergroup businessrole in the entity cache if it is enabled.
     *
     * @param usergroupBusinessrole the usergroup businessrole
     */
    @Override
    public void cacheResult(UsergroupBusinessrole usergroupBusinessrole) {
        EntityCacheUtil.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleImpl.class,
            usergroupBusinessrole.getPrimaryKey(), usergroupBusinessrole);

        usergroupBusinessrole.resetOriginalValues();
    }

    /**
     * Caches the usergroup businessroles in the entity cache if it is enabled.
     *
     * @param usergroupBusinessroles the usergroup businessroles
     */
    @Override
    public void cacheResult(List<UsergroupBusinessrole> usergroupBusinessroles) {
        for (UsergroupBusinessrole usergroupBusinessrole : usergroupBusinessroles) {
            if (EntityCacheUtil.getResult(
                        UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
                        UsergroupBusinessroleImpl.class,
                        usergroupBusinessrole.getPrimaryKey()) == null) {
                cacheResult(usergroupBusinessrole);
            } else {
                usergroupBusinessrole.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all usergroup businessroles.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(UsergroupBusinessroleImpl.class.getName());
        }

        EntityCacheUtil.clearCache(UsergroupBusinessroleImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the usergroup businessrole.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(UsergroupBusinessrole usergroupBusinessrole) {
        EntityCacheUtil.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleImpl.class,
            usergroupBusinessrole.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<UsergroupBusinessrole> usergroupBusinessroles) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (UsergroupBusinessrole usergroupBusinessrole : usergroupBusinessroles) {
            EntityCacheUtil.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
                UsergroupBusinessroleImpl.class,
                usergroupBusinessrole.getPrimaryKey());
        }
    }

    /**
     * Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
     *
     * @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
     * @return the new usergroup businessrole
     */
    @Override
    public UsergroupBusinessrole create(int usergroupBusinessroleSid) {
        UsergroupBusinessrole usergroupBusinessrole = new UsergroupBusinessroleImpl();

        usergroupBusinessrole.setNew(true);
        usergroupBusinessrole.setPrimaryKey(usergroupBusinessroleSid);

        return usergroupBusinessrole;
    }

    /**
     * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
     * @return the usergroup businessrole that was removed
     * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole remove(int usergroupBusinessroleSid)
        throws NoSuchUsergroupBusinessroleException, SystemException {
        return remove((Serializable) usergroupBusinessroleSid);
    }

    /**
     * Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the usergroup businessrole
     * @return the usergroup businessrole that was removed
     * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole remove(Serializable primaryKey)
        throws NoSuchUsergroupBusinessroleException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UsergroupBusinessrole usergroupBusinessrole = (UsergroupBusinessrole) session.get(UsergroupBusinessroleImpl.class,
                    primaryKey);

            if (usergroupBusinessrole == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUsergroupBusinessroleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(usergroupBusinessrole);
        } catch (NoSuchUsergroupBusinessroleException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected UsergroupBusinessrole removeImpl(
        UsergroupBusinessrole usergroupBusinessrole) throws SystemException {
        usergroupBusinessrole = toUnwrappedModel(usergroupBusinessrole);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(usergroupBusinessrole)) {
                usergroupBusinessrole = (UsergroupBusinessrole) session.get(UsergroupBusinessroleImpl.class,
                        usergroupBusinessrole.getPrimaryKeyObj());
            }

            if (usergroupBusinessrole != null) {
                session.delete(usergroupBusinessrole);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (usergroupBusinessrole != null) {
            clearCache(usergroupBusinessrole);
        }

        return usergroupBusinessrole;
    }

    @Override
    public UsergroupBusinessrole updateImpl(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws SystemException {
        usergroupBusinessrole = toUnwrappedModel(usergroupBusinessrole);

        boolean isNew = usergroupBusinessrole.isNew();

        Session session = null;

        try {
            session = openSession();

            if (usergroupBusinessrole.isNew()) {
                session.save(usergroupBusinessrole);

                usergroupBusinessrole.setNew(false);
            } else {
                session.merge(usergroupBusinessrole);
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

        EntityCacheUtil.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupBusinessroleImpl.class,
            usergroupBusinessrole.getPrimaryKey(), usergroupBusinessrole);

        return usergroupBusinessrole;
    }

    protected UsergroupBusinessrole toUnwrappedModel(
        UsergroupBusinessrole usergroupBusinessrole) {
        if (usergroupBusinessrole instanceof UsergroupBusinessroleImpl) {
            return usergroupBusinessrole;
        }

        UsergroupBusinessroleImpl usergroupBusinessroleImpl = new UsergroupBusinessroleImpl();

        usergroupBusinessroleImpl.setNew(usergroupBusinessrole.isNew());
        usergroupBusinessroleImpl.setPrimaryKey(usergroupBusinessrole.getPrimaryKey());

        usergroupBusinessroleImpl.setCreatedBy(usergroupBusinessrole.getCreatedBy());
        usergroupBusinessroleImpl.setBusinessroleMasterSid(usergroupBusinessrole.getBusinessroleMasterSid());
        usergroupBusinessroleImpl.setUsersSid(usergroupBusinessrole.getUsersSid());
        usergroupBusinessroleImpl.setModifiedBy(usergroupBusinessrole.getModifiedBy());
        usergroupBusinessroleImpl.setCreatedDate(usergroupBusinessrole.getCreatedDate());
        usergroupBusinessroleImpl.setProcessed(usergroupBusinessrole.getProcessed());
        usergroupBusinessroleImpl.setUsergroupId(usergroupBusinessrole.getUsergroupId());
        usergroupBusinessroleImpl.setVersionNo(usergroupBusinessrole.getVersionNo());
        usergroupBusinessroleImpl.setIsActive(usergroupBusinessrole.getIsActive());
        usergroupBusinessroleImpl.setUsergroupBusinessroleSid(usergroupBusinessrole.getUsergroupBusinessroleSid());
        usergroupBusinessroleImpl.setModifiedDate(usergroupBusinessrole.getModifiedDate());

        return usergroupBusinessroleImpl;
    }

    /**
     * Returns the usergroup businessrole with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the usergroup businessrole
     * @return the usergroup businessrole
     * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole findByPrimaryKey(Serializable primaryKey)
        throws NoSuchUsergroupBusinessroleException, SystemException {
        UsergroupBusinessrole usergroupBusinessrole = fetchByPrimaryKey(primaryKey);

        if (usergroupBusinessrole == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchUsergroupBusinessroleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return usergroupBusinessrole;
    }

    /**
     * Returns the usergroup businessrole with the primary key or throws a {@link com.stpl.app.NoSuchUsergroupBusinessroleException} if it could not be found.
     *
     * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
     * @return the usergroup businessrole
     * @throws com.stpl.app.NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole findByPrimaryKey(int usergroupBusinessroleSid)
        throws NoSuchUsergroupBusinessroleException, SystemException {
        return findByPrimaryKey((Serializable) usergroupBusinessroleSid);
    }

    /**
     * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the usergroup businessrole
     * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        UsergroupBusinessrole usergroupBusinessrole = (UsergroupBusinessrole) EntityCacheUtil.getResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
                UsergroupBusinessroleImpl.class, primaryKey);

        if (usergroupBusinessrole == _nullUsergroupBusinessrole) {
            return null;
        }

        if (usergroupBusinessrole == null) {
            Session session = null;

            try {
                session = openSession();

                usergroupBusinessrole = (UsergroupBusinessrole) session.get(UsergroupBusinessroleImpl.class,
                        primaryKey);

                if (usergroupBusinessrole != null) {
                    cacheResult(usergroupBusinessrole);
                } else {
                    EntityCacheUtil.putResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
                        UsergroupBusinessroleImpl.class, primaryKey,
                        _nullUsergroupBusinessrole);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(UsergroupBusinessroleModelImpl.ENTITY_CACHE_ENABLED,
                    UsergroupBusinessroleImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return usergroupBusinessrole;
    }

    /**
     * Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
     * @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupBusinessrole fetchByPrimaryKey(int usergroupBusinessroleSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) usergroupBusinessroleSid);
    }

    /**
     * Returns all the usergroup businessroles.
     *
     * @return the usergroup businessroles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupBusinessrole> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the usergroup businessroles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of usergroup businessroles
     * @param end the upper bound of the range of usergroup businessroles (not inclusive)
     * @return the range of usergroup businessroles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupBusinessrole> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the usergroup businessroles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of usergroup businessroles
     * @param end the upper bound of the range of usergroup businessroles (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of usergroup businessroles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupBusinessrole> findAll(int start, int end,
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

        List<UsergroupBusinessrole> list = (List<UsergroupBusinessrole>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_USERGROUPBUSINESSROLE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_USERGROUPBUSINESSROLE;

                if (pagination) {
                    sql = sql.concat(UsergroupBusinessroleModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<UsergroupBusinessrole>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<UsergroupBusinessrole>(list);
                } else {
                    list = (List<UsergroupBusinessrole>) QueryUtil.list(q,
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
     * Removes all the usergroup businessroles from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (UsergroupBusinessrole usergroupBusinessrole : findAll()) {
            remove(usergroupBusinessrole);
        }
    }

    /**
     * Returns the number of usergroup businessroles.
     *
     * @return the number of usergroup businessroles
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

                Query q = session.createQuery(_SQL_COUNT_USERGROUPBUSINESSROLE);

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
     * Initializes the usergroup businessrole persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.UsergroupBusinessrole")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UsergroupBusinessrole>> listenersList = new ArrayList<ModelListener<UsergroupBusinessrole>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UsergroupBusinessrole>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(UsergroupBusinessroleImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
