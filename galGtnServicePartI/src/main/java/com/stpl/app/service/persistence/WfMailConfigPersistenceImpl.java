package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchWfMailConfigException;
import com.stpl.app.model.WfMailConfig;
import com.stpl.app.model.impl.WfMailConfigImpl;
import com.stpl.app.model.impl.WfMailConfigModelImpl;
import com.stpl.app.service.persistence.WfMailConfigPersistence;

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
 * The persistence implementation for the wf mail config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WfMailConfigPersistence
 * @see WfMailConfigUtil
 * @generated
 */
public class WfMailConfigPersistenceImpl extends BasePersistenceImpl<WfMailConfig>
    implements WfMailConfigPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link WfMailConfigUtil} to access the wf mail config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = WfMailConfigImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigModelImpl.FINDER_CACHE_ENABLED, WfMailConfigImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigModelImpl.FINDER_CACHE_ENABLED, WfMailConfigImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_WFMAILCONFIG = "SELECT wfMailConfig FROM WfMailConfig wfMailConfig";
    private static final String _SQL_COUNT_WFMAILCONFIG = "SELECT COUNT(wfMailConfig) FROM WfMailConfig wfMailConfig";
    private static final String _ORDER_BY_ENTITY_ALIAS = "wfMailConfig.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WfMailConfig exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(WfMailConfigPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "smtpFlag", "createdBy", "emailAddress", "password",
                "modifiedBy", "wfMailConfigSid", "hostName", "createdDate",
                "portNumber", "modifiedDate", "inboundStatus"
            });
    private static WfMailConfig _nullWfMailConfig = new WfMailConfigImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<WfMailConfig> toCacheModel() {
                return _nullWfMailConfigCacheModel;
            }
        };

    private static CacheModel<WfMailConfig> _nullWfMailConfigCacheModel = new CacheModel<WfMailConfig>() {
            @Override
            public WfMailConfig toEntityModel() {
                return _nullWfMailConfig;
            }
        };

    public WfMailConfigPersistenceImpl() {
        setModelClass(WfMailConfig.class);
    }

    /**
     * Caches the wf mail config in the entity cache if it is enabled.
     *
     * @param wfMailConfig the wf mail config
     */
    @Override
    public void cacheResult(WfMailConfig wfMailConfig) {
        EntityCacheUtil.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigImpl.class, wfMailConfig.getPrimaryKey(), wfMailConfig);

        wfMailConfig.resetOriginalValues();
    }

    /**
     * Caches the wf mail configs in the entity cache if it is enabled.
     *
     * @param wfMailConfigs the wf mail configs
     */
    @Override
    public void cacheResult(List<WfMailConfig> wfMailConfigs) {
        for (WfMailConfig wfMailConfig : wfMailConfigs) {
            if (EntityCacheUtil.getResult(
                        WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
                        WfMailConfigImpl.class, wfMailConfig.getPrimaryKey()) == null) {
                cacheResult(wfMailConfig);
            } else {
                wfMailConfig.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all wf mail configs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(WfMailConfigImpl.class.getName());
        }

        EntityCacheUtil.clearCache(WfMailConfigImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the wf mail config.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(WfMailConfig wfMailConfig) {
        EntityCacheUtil.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigImpl.class, wfMailConfig.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<WfMailConfig> wfMailConfigs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (WfMailConfig wfMailConfig : wfMailConfigs) {
            EntityCacheUtil.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
                WfMailConfigImpl.class, wfMailConfig.getPrimaryKey());
        }
    }

    /**
     * Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
     *
     * @param wfMailConfigSid the primary key for the new wf mail config
     * @return the new wf mail config
     */
    @Override
    public WfMailConfig create(int wfMailConfigSid) {
        WfMailConfig wfMailConfig = new WfMailConfigImpl();

        wfMailConfig.setNew(true);
        wfMailConfig.setPrimaryKey(wfMailConfigSid);

        return wfMailConfig;
    }

    /**
     * Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param wfMailConfigSid the primary key of the wf mail config
     * @return the wf mail config that was removed
     * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig remove(int wfMailConfigSid)
        throws NoSuchWfMailConfigException, SystemException {
        return remove((Serializable) wfMailConfigSid);
    }

    /**
     * Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the wf mail config
     * @return the wf mail config that was removed
     * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig remove(Serializable primaryKey)
        throws NoSuchWfMailConfigException, SystemException {
        Session session = null;

        try {
            session = openSession();

            WfMailConfig wfMailConfig = (WfMailConfig) session.get(WfMailConfigImpl.class,
                    primaryKey);

            if (wfMailConfig == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchWfMailConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(wfMailConfig);
        } catch (NoSuchWfMailConfigException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected WfMailConfig removeImpl(WfMailConfig wfMailConfig)
        throws SystemException {
        wfMailConfig = toUnwrappedModel(wfMailConfig);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(wfMailConfig)) {
                wfMailConfig = (WfMailConfig) session.get(WfMailConfigImpl.class,
                        wfMailConfig.getPrimaryKeyObj());
            }

            if (wfMailConfig != null) {
                session.delete(wfMailConfig);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (wfMailConfig != null) {
            clearCache(wfMailConfig);
        }

        return wfMailConfig;
    }

    @Override
    public WfMailConfig updateImpl(com.stpl.app.model.WfMailConfig wfMailConfig)
        throws SystemException {
        wfMailConfig = toUnwrappedModel(wfMailConfig);

        boolean isNew = wfMailConfig.isNew();

        Session session = null;

        try {
            session = openSession();

            if (wfMailConfig.isNew()) {
                session.save(wfMailConfig);

                wfMailConfig.setNew(false);
            } else {
                session.merge(wfMailConfig);
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

        EntityCacheUtil.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
            WfMailConfigImpl.class, wfMailConfig.getPrimaryKey(), wfMailConfig);

        return wfMailConfig;
    }

    protected WfMailConfig toUnwrappedModel(WfMailConfig wfMailConfig) {
        if (wfMailConfig instanceof WfMailConfigImpl) {
            return wfMailConfig;
        }

        WfMailConfigImpl wfMailConfigImpl = new WfMailConfigImpl();

        wfMailConfigImpl.setNew(wfMailConfig.isNew());
        wfMailConfigImpl.setPrimaryKey(wfMailConfig.getPrimaryKey());

        wfMailConfigImpl.setSmtpFlag(wfMailConfig.getSmtpFlag());
        wfMailConfigImpl.setCreatedBy(wfMailConfig.getCreatedBy());
        wfMailConfigImpl.setEmailAddress(wfMailConfig.getEmailAddress());
        wfMailConfigImpl.setPassword(wfMailConfig.getPassword());
        wfMailConfigImpl.setModifiedBy(wfMailConfig.getModifiedBy());
        wfMailConfigImpl.setWfMailConfigSid(wfMailConfig.getWfMailConfigSid());
        wfMailConfigImpl.setHostName(wfMailConfig.getHostName());
        wfMailConfigImpl.setCreatedDate(wfMailConfig.getCreatedDate());
        wfMailConfigImpl.setPortNumber(wfMailConfig.getPortNumber());
        wfMailConfigImpl.setModifiedDate(wfMailConfig.getModifiedDate());
        wfMailConfigImpl.setInboundStatus(wfMailConfig.getInboundStatus());

        return wfMailConfigImpl;
    }

    /**
     * Returns the wf mail config with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the wf mail config
     * @return the wf mail config
     * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig findByPrimaryKey(Serializable primaryKey)
        throws NoSuchWfMailConfigException, SystemException {
        WfMailConfig wfMailConfig = fetchByPrimaryKey(primaryKey);

        if (wfMailConfig == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchWfMailConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return wfMailConfig;
    }

    /**
     * Returns the wf mail config with the primary key or throws a {@link com.stpl.app.NoSuchWfMailConfigException} if it could not be found.
     *
     * @param wfMailConfigSid the primary key of the wf mail config
     * @return the wf mail config
     * @throws com.stpl.app.NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig findByPrimaryKey(int wfMailConfigSid)
        throws NoSuchWfMailConfigException, SystemException {
        return findByPrimaryKey((Serializable) wfMailConfigSid);
    }

    /**
     * Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the wf mail config
     * @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        WfMailConfig wfMailConfig = (WfMailConfig) EntityCacheUtil.getResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
                WfMailConfigImpl.class, primaryKey);

        if (wfMailConfig == _nullWfMailConfig) {
            return null;
        }

        if (wfMailConfig == null) {
            Session session = null;

            try {
                session = openSession();

                wfMailConfig = (WfMailConfig) session.get(WfMailConfigImpl.class,
                        primaryKey);

                if (wfMailConfig != null) {
                    cacheResult(wfMailConfig);
                } else {
                    EntityCacheUtil.putResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
                        WfMailConfigImpl.class, primaryKey, _nullWfMailConfig);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(WfMailConfigModelImpl.ENTITY_CACHE_ENABLED,
                    WfMailConfigImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return wfMailConfig;
    }

    /**
     * Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param wfMailConfigSid the primary key of the wf mail config
     * @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public WfMailConfig fetchByPrimaryKey(int wfMailConfigSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) wfMailConfigSid);
    }

    /**
     * Returns all the wf mail configs.
     *
     * @return the wf mail configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WfMailConfig> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the wf mail configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of wf mail configs
     * @param end the upper bound of the range of wf mail configs (not inclusive)
     * @return the range of wf mail configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WfMailConfig> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the wf mail configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of wf mail configs
     * @param end the upper bound of the range of wf mail configs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of wf mail configs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<WfMailConfig> findAll(int start, int end,
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

        List<WfMailConfig> list = (List<WfMailConfig>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_WFMAILCONFIG);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_WFMAILCONFIG;

                if (pagination) {
                    sql = sql.concat(WfMailConfigModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<WfMailConfig>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<WfMailConfig>(list);
                } else {
                    list = (List<WfMailConfig>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the wf mail configs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (WfMailConfig wfMailConfig : findAll()) {
            remove(wfMailConfig);
        }
    }

    /**
     * Returns the number of wf mail configs.
     *
     * @return the number of wf mail configs
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

                Query q = session.createQuery(_SQL_COUNT_WFMAILCONFIG);

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
     * Initializes the wf mail config persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.WfMailConfig")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<WfMailConfig>> listenersList = new ArrayList<ModelListener<WfMailConfig>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<WfMailConfig>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(WfMailConfigImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
