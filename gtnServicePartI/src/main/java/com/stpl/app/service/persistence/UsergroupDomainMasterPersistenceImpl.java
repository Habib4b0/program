package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchUsergroupDomainMasterException;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.model.impl.UsergroupDomainMasterImpl;
import com.stpl.app.model.impl.UsergroupDomainMasterModelImpl;
import com.stpl.app.service.persistence.UsergroupDomainMasterPersistence;

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
 * The persistence implementation for the usergroup domain master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupDomainMasterPersistence
 * @see UsergroupDomainMasterUtil
 * @generated
 */
public class UsergroupDomainMasterPersistenceImpl extends BasePersistenceImpl<UsergroupDomainMaster>
    implements UsergroupDomainMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link UsergroupDomainMasterUtil} to access the usergroup domain master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = UsergroupDomainMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED,
            UsergroupDomainMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED,
            UsergroupDomainMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_USERGROUPDOMAINMASTER = "SELECT usergroupDomainMaster FROM UsergroupDomainMaster usergroupDomainMaster";
    private static final String _SQL_COUNT_USERGROUPDOMAINMASTER = "SELECT COUNT(usergroupDomainMaster) FROM UsergroupDomainMaster usergroupDomainMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "usergroupDomainMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UsergroupDomainMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(UsergroupDomainMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "usergroupDomainSid", "usersSid", "modifiedBy",
                "createdDate", "domainId", "processed", "usergroupId",
                "versionNo", "isActive", "modifiedDate"
            });
    private static UsergroupDomainMaster _nullUsergroupDomainMaster = new UsergroupDomainMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<UsergroupDomainMaster> toCacheModel() {
                return _nullUsergroupDomainMasterCacheModel;
            }
        };

    private static CacheModel<UsergroupDomainMaster> _nullUsergroupDomainMasterCacheModel =
        new CacheModel<UsergroupDomainMaster>() {
            @Override
            public UsergroupDomainMaster toEntityModel() {
                return _nullUsergroupDomainMaster;
            }
        };

    public UsergroupDomainMasterPersistenceImpl() {
        setModelClass(UsergroupDomainMaster.class);
    }

    /**
     * Caches the usergroup domain master in the entity cache if it is enabled.
     *
     * @param usergroupDomainMaster the usergroup domain master
     */
    @Override
    public void cacheResult(UsergroupDomainMaster usergroupDomainMaster) {
        EntityCacheUtil.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterImpl.class,
            usergroupDomainMaster.getPrimaryKey(), usergroupDomainMaster);

        usergroupDomainMaster.resetOriginalValues();
    }

    /**
     * Caches the usergroup domain masters in the entity cache if it is enabled.
     *
     * @param usergroupDomainMasters the usergroup domain masters
     */
    @Override
    public void cacheResult(List<UsergroupDomainMaster> usergroupDomainMasters) {
        for (UsergroupDomainMaster usergroupDomainMaster : usergroupDomainMasters) {
            if (EntityCacheUtil.getResult(
                        UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
                        UsergroupDomainMasterImpl.class,
                        usergroupDomainMaster.getPrimaryKey()) == null) {
                cacheResult(usergroupDomainMaster);
            } else {
                usergroupDomainMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all usergroup domain masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(UsergroupDomainMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(UsergroupDomainMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the usergroup domain master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(UsergroupDomainMaster usergroupDomainMaster) {
        EntityCacheUtil.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterImpl.class,
            usergroupDomainMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<UsergroupDomainMaster> usergroupDomainMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (UsergroupDomainMaster usergroupDomainMaster : usergroupDomainMasters) {
            EntityCacheUtil.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
                UsergroupDomainMasterImpl.class,
                usergroupDomainMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
     *
     * @param usergroupDomainSid the primary key for the new usergroup domain master
     * @return the new usergroup domain master
     */
    @Override
    public UsergroupDomainMaster create(int usergroupDomainSid) {
        UsergroupDomainMaster usergroupDomainMaster = new UsergroupDomainMasterImpl();

        usergroupDomainMaster.setNew(true);
        usergroupDomainMaster.setPrimaryKey(usergroupDomainSid);

        return usergroupDomainMaster;
    }

    /**
     * Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param usergroupDomainSid the primary key of the usergroup domain master
     * @return the usergroup domain master that was removed
     * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster remove(int usergroupDomainSid)
        throws NoSuchUsergroupDomainMasterException, SystemException {
        return remove((Serializable) usergroupDomainSid);
    }

    /**
     * Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the usergroup domain master
     * @return the usergroup domain master that was removed
     * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster remove(Serializable primaryKey)
        throws NoSuchUsergroupDomainMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            UsergroupDomainMaster usergroupDomainMaster = (UsergroupDomainMaster) session.get(UsergroupDomainMasterImpl.class,
                    primaryKey);

            if (usergroupDomainMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUsergroupDomainMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(usergroupDomainMaster);
        } catch (NoSuchUsergroupDomainMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected UsergroupDomainMaster removeImpl(
        UsergroupDomainMaster usergroupDomainMaster) throws SystemException {
        usergroupDomainMaster = toUnwrappedModel(usergroupDomainMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(usergroupDomainMaster)) {
                usergroupDomainMaster = (UsergroupDomainMaster) session.get(UsergroupDomainMasterImpl.class,
                        usergroupDomainMaster.getPrimaryKeyObj());
            }

            if (usergroupDomainMaster != null) {
                session.delete(usergroupDomainMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (usergroupDomainMaster != null) {
            clearCache(usergroupDomainMaster);
        }

        return usergroupDomainMaster;
    }

    @Override
    public UsergroupDomainMaster updateImpl(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster)
        throws SystemException {
        usergroupDomainMaster = toUnwrappedModel(usergroupDomainMaster);

        boolean isNew = usergroupDomainMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (usergroupDomainMaster.isNew()) {
                session.save(usergroupDomainMaster);

                usergroupDomainMaster.setNew(false);
            } else {
                session.merge(usergroupDomainMaster);
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

        EntityCacheUtil.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
            UsergroupDomainMasterImpl.class,
            usergroupDomainMaster.getPrimaryKey(), usergroupDomainMaster);

        return usergroupDomainMaster;
    }

    protected UsergroupDomainMaster toUnwrappedModel(
        UsergroupDomainMaster usergroupDomainMaster) {
        if (usergroupDomainMaster instanceof UsergroupDomainMasterImpl) {
            return usergroupDomainMaster;
        }

        UsergroupDomainMasterImpl usergroupDomainMasterImpl = new UsergroupDomainMasterImpl();

        usergroupDomainMasterImpl.setNew(usergroupDomainMaster.isNew());
        usergroupDomainMasterImpl.setPrimaryKey(usergroupDomainMaster.getPrimaryKey());

        usergroupDomainMasterImpl.setCreatedBy(usergroupDomainMaster.getCreatedBy());
        usergroupDomainMasterImpl.setUsergroupDomainSid(usergroupDomainMaster.getUsergroupDomainSid());
        usergroupDomainMasterImpl.setUsersSid(usergroupDomainMaster.getUsersSid());
        usergroupDomainMasterImpl.setModifiedBy(usergroupDomainMaster.getModifiedBy());
        usergroupDomainMasterImpl.setCreatedDate(usergroupDomainMaster.getCreatedDate());
        usergroupDomainMasterImpl.setDomainId(usergroupDomainMaster.getDomainId());
        usergroupDomainMasterImpl.setProcessed(usergroupDomainMaster.getProcessed());
        usergroupDomainMasterImpl.setUsergroupId(usergroupDomainMaster.getUsergroupId());
        usergroupDomainMasterImpl.setVersionNo(usergroupDomainMaster.getVersionNo());
        usergroupDomainMasterImpl.setIsActive(usergroupDomainMaster.getIsActive());
        usergroupDomainMasterImpl.setModifiedDate(usergroupDomainMaster.getModifiedDate());

        return usergroupDomainMasterImpl;
    }

    /**
     * Returns the usergroup domain master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the usergroup domain master
     * @return the usergroup domain master
     * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchUsergroupDomainMasterException, SystemException {
        UsergroupDomainMaster usergroupDomainMaster = fetchByPrimaryKey(primaryKey);

        if (usergroupDomainMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchUsergroupDomainMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return usergroupDomainMaster;
    }

    /**
     * Returns the usergroup domain master with the primary key or throws a {@link com.stpl.app.NoSuchUsergroupDomainMasterException} if it could not be found.
     *
     * @param usergroupDomainSid the primary key of the usergroup domain master
     * @return the usergroup domain master
     * @throws com.stpl.app.NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster findByPrimaryKey(int usergroupDomainSid)
        throws NoSuchUsergroupDomainMasterException, SystemException {
        return findByPrimaryKey((Serializable) usergroupDomainSid);
    }

    /**
     * Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the usergroup domain master
     * @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        UsergroupDomainMaster usergroupDomainMaster = (UsergroupDomainMaster) EntityCacheUtil.getResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
                UsergroupDomainMasterImpl.class, primaryKey);

        if (usergroupDomainMaster == _nullUsergroupDomainMaster) {
            return null;
        }

        if (usergroupDomainMaster == null) {
            Session session = null;

            try {
                session = openSession();

                usergroupDomainMaster = (UsergroupDomainMaster) session.get(UsergroupDomainMasterImpl.class,
                        primaryKey);

                if (usergroupDomainMaster != null) {
                    cacheResult(usergroupDomainMaster);
                } else {
                    EntityCacheUtil.putResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
                        UsergroupDomainMasterImpl.class, primaryKey,
                        _nullUsergroupDomainMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(UsergroupDomainMasterModelImpl.ENTITY_CACHE_ENABLED,
                    UsergroupDomainMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return usergroupDomainMaster;
    }

    /**
     * Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param usergroupDomainSid the primary key of the usergroup domain master
     * @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public UsergroupDomainMaster fetchByPrimaryKey(int usergroupDomainSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) usergroupDomainSid);
    }

    /**
     * Returns all the usergroup domain masters.
     *
     * @return the usergroup domain masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupDomainMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the usergroup domain masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of usergroup domain masters
     * @param end the upper bound of the range of usergroup domain masters (not inclusive)
     * @return the range of usergroup domain masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupDomainMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the usergroup domain masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of usergroup domain masters
     * @param end the upper bound of the range of usergroup domain masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of usergroup domain masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<UsergroupDomainMaster> findAll(int start, int end,
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

        List<UsergroupDomainMaster> list = (List<UsergroupDomainMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_USERGROUPDOMAINMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_USERGROUPDOMAINMASTER;

                if (pagination) {
                    sql = sql.concat(UsergroupDomainMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<UsergroupDomainMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<UsergroupDomainMaster>(list);
                } else {
                    list = (List<UsergroupDomainMaster>) QueryUtil.list(q,
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
     * Removes all the usergroup domain masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (UsergroupDomainMaster usergroupDomainMaster : findAll()) {
            remove(usergroupDomainMaster);
        }
    }

    /**
     * Returns the number of usergroup domain masters.
     *
     * @return the number of usergroup domain masters
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

                Query q = session.createQuery(_SQL_COUNT_USERGROUPDOMAINMASTER);

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
     * Initializes the usergroup domain master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.UsergroupDomainMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<UsergroupDomainMaster>> listenersList = new ArrayList<ModelListener<UsergroupDomainMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<UsergroupDomainMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(UsergroupDomainMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
