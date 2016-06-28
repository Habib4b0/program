package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNetSalesFormulaMasterException;
import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.model.impl.NetSalesFormulaMasterImpl;
import com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl;
import com.stpl.app.service.persistence.NetSalesFormulaMasterPersistence;

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
 * The persistence implementation for the net sales formula master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterPersistence
 * @see NetSalesFormulaMasterUtil
 * @generated
 */
public class NetSalesFormulaMasterPersistenceImpl extends BasePersistenceImpl<NetSalesFormulaMaster>
    implements NetSalesFormulaMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NetSalesFormulaMasterUtil} to access the net sales formula master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NetSalesFormulaMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED,
            NetSalesFormulaMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED,
            NetSalesFormulaMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NETSALESFORMULAMASTER = "SELECT netSalesFormulaMaster FROM NetSalesFormulaMaster netSalesFormulaMaster";
    private static final String _SQL_COUNT_NETSALESFORMULAMASTER = "SELECT COUNT(netSalesFormulaMaster) FROM NetSalesFormulaMaster netSalesFormulaMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "netSalesFormulaMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NetSalesFormulaMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NetSalesFormulaMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "netSalesFormulaMasterSid", "modifiedBy",
                "createdDate", "netSalesFormulaName", "netSalesFormulaType",
                "netSalesFormulaCategory", "contractSelection", "modifiedDate",
                "recordLockStatus", "source", "netSalesFormulaId",
                "netSalesFormulaNo", "inboundStatus", "cdrModelSid"
            });
    private static NetSalesFormulaMaster _nullNetSalesFormulaMaster = new NetSalesFormulaMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NetSalesFormulaMaster> toCacheModel() {
                return _nullNetSalesFormulaMasterCacheModel;
            }
        };

    private static CacheModel<NetSalesFormulaMaster> _nullNetSalesFormulaMasterCacheModel =
        new CacheModel<NetSalesFormulaMaster>() {
            @Override
            public NetSalesFormulaMaster toEntityModel() {
                return _nullNetSalesFormulaMaster;
            }
        };

    public NetSalesFormulaMasterPersistenceImpl() {
        setModelClass(NetSalesFormulaMaster.class);
    }

    /**
     * Caches the net sales formula master in the entity cache if it is enabled.
     *
     * @param netSalesFormulaMaster the net sales formula master
     */
    @Override
    public void cacheResult(NetSalesFormulaMaster netSalesFormulaMaster) {
        EntityCacheUtil.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterImpl.class,
            netSalesFormulaMaster.getPrimaryKey(), netSalesFormulaMaster);

        netSalesFormulaMaster.resetOriginalValues();
    }

    /**
     * Caches the net sales formula masters in the entity cache if it is enabled.
     *
     * @param netSalesFormulaMasters the net sales formula masters
     */
    @Override
    public void cacheResult(List<NetSalesFormulaMaster> netSalesFormulaMasters) {
        for (NetSalesFormulaMaster netSalesFormulaMaster : netSalesFormulaMasters) {
            if (EntityCacheUtil.getResult(
                        NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NetSalesFormulaMasterImpl.class,
                        netSalesFormulaMaster.getPrimaryKey()) == null) {
                cacheResult(netSalesFormulaMaster);
            } else {
                netSalesFormulaMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all net sales formula masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NetSalesFormulaMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NetSalesFormulaMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the net sales formula master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NetSalesFormulaMaster netSalesFormulaMaster) {
        EntityCacheUtil.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterImpl.class,
            netSalesFormulaMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NetSalesFormulaMaster> netSalesFormulaMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NetSalesFormulaMaster netSalesFormulaMaster : netSalesFormulaMasters) {
            EntityCacheUtil.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
                NetSalesFormulaMasterImpl.class,
                netSalesFormulaMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
     *
     * @param netSalesFormulaMasterSid the primary key for the new net sales formula master
     * @return the new net sales formula master
     */
    @Override
    public NetSalesFormulaMaster create(int netSalesFormulaMasterSid) {
        NetSalesFormulaMaster netSalesFormulaMaster = new NetSalesFormulaMasterImpl();

        netSalesFormulaMaster.setNew(true);
        netSalesFormulaMaster.setPrimaryKey(netSalesFormulaMasterSid);

        return netSalesFormulaMaster;
    }

    /**
     * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param netSalesFormulaMasterSid the primary key of the net sales formula master
     * @return the net sales formula master that was removed
     * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster remove(int netSalesFormulaMasterSid)
        throws NoSuchNetSalesFormulaMasterException, SystemException {
        return remove((Serializable) netSalesFormulaMasterSid);
    }

    /**
     * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the net sales formula master
     * @return the net sales formula master that was removed
     * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster remove(Serializable primaryKey)
        throws NoSuchNetSalesFormulaMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NetSalesFormulaMaster netSalesFormulaMaster = (NetSalesFormulaMaster) session.get(NetSalesFormulaMasterImpl.class,
                    primaryKey);

            if (netSalesFormulaMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNetSalesFormulaMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(netSalesFormulaMaster);
        } catch (NoSuchNetSalesFormulaMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NetSalesFormulaMaster removeImpl(
        NetSalesFormulaMaster netSalesFormulaMaster) throws SystemException {
        netSalesFormulaMaster = toUnwrappedModel(netSalesFormulaMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(netSalesFormulaMaster)) {
                netSalesFormulaMaster = (NetSalesFormulaMaster) session.get(NetSalesFormulaMasterImpl.class,
                        netSalesFormulaMaster.getPrimaryKeyObj());
            }

            if (netSalesFormulaMaster != null) {
                session.delete(netSalesFormulaMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (netSalesFormulaMaster != null) {
            clearCache(netSalesFormulaMaster);
        }

        return netSalesFormulaMaster;
    }

    @Override
    public NetSalesFormulaMaster updateImpl(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws SystemException {
        netSalesFormulaMaster = toUnwrappedModel(netSalesFormulaMaster);

        boolean isNew = netSalesFormulaMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (netSalesFormulaMaster.isNew()) {
                session.save(netSalesFormulaMaster);

                netSalesFormulaMaster.setNew(false);
            } else {
                session.merge(netSalesFormulaMaster);
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

        EntityCacheUtil.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
            NetSalesFormulaMasterImpl.class,
            netSalesFormulaMaster.getPrimaryKey(), netSalesFormulaMaster);

        return netSalesFormulaMaster;
    }

    protected NetSalesFormulaMaster toUnwrappedModel(
        NetSalesFormulaMaster netSalesFormulaMaster) {
        if (netSalesFormulaMaster instanceof NetSalesFormulaMasterImpl) {
            return netSalesFormulaMaster;
        }

        NetSalesFormulaMasterImpl netSalesFormulaMasterImpl = new NetSalesFormulaMasterImpl();

        netSalesFormulaMasterImpl.setNew(netSalesFormulaMaster.isNew());
        netSalesFormulaMasterImpl.setPrimaryKey(netSalesFormulaMaster.getPrimaryKey());

        netSalesFormulaMasterImpl.setCreatedBy(netSalesFormulaMaster.getCreatedBy());
        netSalesFormulaMasterImpl.setNetSalesFormulaMasterSid(netSalesFormulaMaster.getNetSalesFormulaMasterSid());
        netSalesFormulaMasterImpl.setModifiedBy(netSalesFormulaMaster.getModifiedBy());
        netSalesFormulaMasterImpl.setCreatedDate(netSalesFormulaMaster.getCreatedDate());
        netSalesFormulaMasterImpl.setNetSalesFormulaName(netSalesFormulaMaster.getNetSalesFormulaName());
        netSalesFormulaMasterImpl.setNetSalesFormulaType(netSalesFormulaMaster.getNetSalesFormulaType());
        netSalesFormulaMasterImpl.setNetSalesFormulaCategory(netSalesFormulaMaster.getNetSalesFormulaCategory());
        netSalesFormulaMasterImpl.setContractSelection(netSalesFormulaMaster.getContractSelection());
        netSalesFormulaMasterImpl.setModifiedDate(netSalesFormulaMaster.getModifiedDate());
        netSalesFormulaMasterImpl.setRecordLockStatus(netSalesFormulaMaster.isRecordLockStatus());
        netSalesFormulaMasterImpl.setSource(netSalesFormulaMaster.getSource());
        netSalesFormulaMasterImpl.setNetSalesFormulaId(netSalesFormulaMaster.getNetSalesFormulaId());
        netSalesFormulaMasterImpl.setNetSalesFormulaNo(netSalesFormulaMaster.getNetSalesFormulaNo());
        netSalesFormulaMasterImpl.setInboundStatus(netSalesFormulaMaster.getInboundStatus());
        netSalesFormulaMasterImpl.setCdrModelSid(netSalesFormulaMaster.getCdrModelSid());

        return netSalesFormulaMasterImpl;
    }

    /**
     * Returns the net sales formula master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the net sales formula master
     * @return the net sales formula master
     * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNetSalesFormulaMasterException, SystemException {
        NetSalesFormulaMaster netSalesFormulaMaster = fetchByPrimaryKey(primaryKey);

        if (netSalesFormulaMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNetSalesFormulaMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return netSalesFormulaMaster;
    }

    /**
     * Returns the net sales formula master with the primary key or throws a {@link com.stpl.app.NoSuchNetSalesFormulaMasterException} if it could not be found.
     *
     * @param netSalesFormulaMasterSid the primary key of the net sales formula master
     * @return the net sales formula master
     * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster findByPrimaryKey(int netSalesFormulaMasterSid)
        throws NoSuchNetSalesFormulaMasterException, SystemException {
        return findByPrimaryKey((Serializable) netSalesFormulaMasterSid);
    }

    /**
     * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the net sales formula master
     * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NetSalesFormulaMaster netSalesFormulaMaster = (NetSalesFormulaMaster) EntityCacheUtil.getResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
                NetSalesFormulaMasterImpl.class, primaryKey);

        if (netSalesFormulaMaster == _nullNetSalesFormulaMaster) {
            return null;
        }

        if (netSalesFormulaMaster == null) {
            Session session = null;

            try {
                session = openSession();

                netSalesFormulaMaster = (NetSalesFormulaMaster) session.get(NetSalesFormulaMasterImpl.class,
                        primaryKey);

                if (netSalesFormulaMaster != null) {
                    cacheResult(netSalesFormulaMaster);
                } else {
                    EntityCacheUtil.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NetSalesFormulaMasterImpl.class, primaryKey,
                        _nullNetSalesFormulaMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
                    NetSalesFormulaMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return netSalesFormulaMaster;
    }

    /**
     * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param netSalesFormulaMasterSid the primary key of the net sales formula master
     * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NetSalesFormulaMaster fetchByPrimaryKey(int netSalesFormulaMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) netSalesFormulaMasterSid);
    }

    /**
     * Returns all the net sales formula masters.
     *
     * @return the net sales formula masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NetSalesFormulaMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the net sales formula masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of net sales formula masters
     * @param end the upper bound of the range of net sales formula masters (not inclusive)
     * @return the range of net sales formula masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NetSalesFormulaMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the net sales formula masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of net sales formula masters
     * @param end the upper bound of the range of net sales formula masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of net sales formula masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NetSalesFormulaMaster> findAll(int start, int end,
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

        List<NetSalesFormulaMaster> list = (List<NetSalesFormulaMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NETSALESFORMULAMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NETSALESFORMULAMASTER;

                if (pagination) {
                    sql = sql.concat(NetSalesFormulaMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NetSalesFormulaMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NetSalesFormulaMaster>(list);
                } else {
                    list = (List<NetSalesFormulaMaster>) QueryUtil.list(q,
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
     * Removes all the net sales formula masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NetSalesFormulaMaster netSalesFormulaMaster : findAll()) {
            remove(netSalesFormulaMaster);
        }
    }

    /**
     * Returns the number of net sales formula masters.
     *
     * @return the number of net sales formula masters
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

                Query q = session.createQuery(_SQL_COUNT_NETSALESFORMULAMASTER);

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
     * Initializes the net sales formula master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NetSalesFormulaMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NetSalesFormulaMaster>> listenersList = new ArrayList<ModelListener<NetSalesFormulaMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NetSalesFormulaMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NetSalesFormulaMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
