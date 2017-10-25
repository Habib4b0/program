package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchArpOutboundException;
import com.stpl.app.parttwo.model.ArpOutbound;
import com.stpl.app.parttwo.model.impl.ArpOutboundImpl;
import com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPersistence;

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
 * The persistence implementation for the arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ArpOutboundPersistence
 * @see ArpOutboundUtil
 * @generated
 */
public class ArpOutboundPersistenceImpl extends BasePersistenceImpl<ArpOutbound>
    implements ArpOutboundPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ArpOutboundUtil} to access the arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ArpOutboundImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundModelImpl.FINDER_CACHE_ENABLED, ArpOutboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundModelImpl.FINDER_CACHE_ENABLED, ArpOutboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ARPOUTBOUND = "SELECT arpOutbound FROM ArpOutbound arpOutbound";
    private static final String _SQL_COUNT_ARPOUTBOUND = "SELECT COUNT(arpOutbound) FROM ArpOutbound arpOutbound";
    private static final String _ORDER_BY_ENTITY_ALIAS = "arpOutbound.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ArpOutbound exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ArpOutboundPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "salesUnitsRate", "accountType", "originalBatchId",
                "companyMasterSid", "brandMasterSid", "arpApprovalDate",
                "arpMasterSid", "arpCreationDate", "checkRecord", "arpId",
                "account", "outboundStatus", "periodSid", "itemMasterSid",
                "rsModelSid"
            });
    private static ArpOutbound _nullArpOutbound = new ArpOutboundImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ArpOutbound> toCacheModel() {
                return _nullArpOutboundCacheModel;
            }
        };

    private static CacheModel<ArpOutbound> _nullArpOutboundCacheModel = new CacheModel<ArpOutbound>() {
            @Override
            public ArpOutbound toEntityModel() {
                return _nullArpOutbound;
            }
        };

    public ArpOutboundPersistenceImpl() {
        setModelClass(ArpOutbound.class);
    }

    /**
     * Caches the arp outbound in the entity cache if it is enabled.
     *
     * @param arpOutbound the arp outbound
     */
    @Override
    public void cacheResult(ArpOutbound arpOutbound) {
        EntityCacheUtil.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundImpl.class, arpOutbound.getPrimaryKey(), arpOutbound);

        arpOutbound.resetOriginalValues();
    }

    /**
     * Caches the arp outbounds in the entity cache if it is enabled.
     *
     * @param arpOutbounds the arp outbounds
     */
    @Override
    public void cacheResult(List<ArpOutbound> arpOutbounds) {
        for (ArpOutbound arpOutbound : arpOutbounds) {
            if (EntityCacheUtil.getResult(
                        ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                        ArpOutboundImpl.class, arpOutbound.getPrimaryKey()) == null) {
                cacheResult(arpOutbound);
            } else {
                arpOutbound.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all arp outbounds.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ArpOutboundImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ArpOutboundImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the arp outbound.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ArpOutbound arpOutbound) {
        EntityCacheUtil.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundImpl.class, arpOutbound.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ArpOutbound> arpOutbounds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ArpOutbound arpOutbound : arpOutbounds) {
            EntityCacheUtil.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                ArpOutboundImpl.class, arpOutbound.getPrimaryKey());
        }
    }

    /**
     * Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
     *
     * @param arpOutboundPK the primary key for the new arp outbound
     * @return the new arp outbound
     */
    @Override
    public ArpOutbound create(ArpOutboundPK arpOutboundPK) {
        ArpOutbound arpOutbound = new ArpOutboundImpl();

        arpOutbound.setNew(true);
        arpOutbound.setPrimaryKey(arpOutboundPK);

        return arpOutbound;
    }

    /**
     * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param arpOutboundPK the primary key of the arp outbound
     * @return the arp outbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound remove(ArpOutboundPK arpOutboundPK)
        throws NoSuchArpOutboundException, SystemException {
        return remove((Serializable) arpOutboundPK);
    }

    /**
     * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the arp outbound
     * @return the arp outbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound remove(Serializable primaryKey)
        throws NoSuchArpOutboundException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ArpOutbound arpOutbound = (ArpOutbound) session.get(ArpOutboundImpl.class,
                    primaryKey);

            if (arpOutbound == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(arpOutbound);
        } catch (NoSuchArpOutboundException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ArpOutbound removeImpl(ArpOutbound arpOutbound)
        throws SystemException {
        arpOutbound = toUnwrappedModel(arpOutbound);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(arpOutbound)) {
                arpOutbound = (ArpOutbound) session.get(ArpOutboundImpl.class,
                        arpOutbound.getPrimaryKeyObj());
            }

            if (arpOutbound != null) {
                session.delete(arpOutbound);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (arpOutbound != null) {
            clearCache(arpOutbound);
        }

        return arpOutbound;
    }

    @Override
    public ArpOutbound updateImpl(
        com.stpl.app.parttwo.model.ArpOutbound arpOutbound)
        throws SystemException {
        arpOutbound = toUnwrappedModel(arpOutbound);

        boolean isNew = arpOutbound.isNew();

        Session session = null;

        try {
            session = openSession();

            if (arpOutbound.isNew()) {
                session.save(arpOutbound);

                arpOutbound.setNew(false);
            } else {
                session.merge(arpOutbound);
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

        EntityCacheUtil.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            ArpOutboundImpl.class, arpOutbound.getPrimaryKey(), arpOutbound);

        return arpOutbound;
    }

    protected ArpOutbound toUnwrappedModel(ArpOutbound arpOutbound) {
        if (arpOutbound instanceof ArpOutboundImpl) {
            return arpOutbound;
        }

        ArpOutboundImpl arpOutboundImpl = new ArpOutboundImpl();

        arpOutboundImpl.setNew(arpOutbound.isNew());
        arpOutboundImpl.setPrimaryKey(arpOutbound.getPrimaryKey());

        arpOutboundImpl.setSalesUnitsRate(arpOutbound.getSalesUnitsRate());
        arpOutboundImpl.setAccountType(arpOutbound.getAccountType());
        arpOutboundImpl.setOriginalBatchId(arpOutbound.getOriginalBatchId());
        arpOutboundImpl.setCompanyMasterSid(arpOutbound.getCompanyMasterSid());
        arpOutboundImpl.setBrandMasterSid(arpOutbound.getBrandMasterSid());
        arpOutboundImpl.setArpApprovalDate(arpOutbound.getArpApprovalDate());
        arpOutboundImpl.setArpMasterSid(arpOutbound.getArpMasterSid());
        arpOutboundImpl.setArpCreationDate(arpOutbound.getArpCreationDate());
        arpOutboundImpl.setCheckRecord(arpOutbound.isCheckRecord());
        arpOutboundImpl.setArpId(arpOutbound.getArpId());
        arpOutboundImpl.setAccount(arpOutbound.getAccount());
        arpOutboundImpl.setOutboundStatus(arpOutbound.isOutboundStatus());
        arpOutboundImpl.setPeriodSid(arpOutbound.getPeriodSid());
        arpOutboundImpl.setItemMasterSid(arpOutbound.getItemMasterSid());
        arpOutboundImpl.setRsModelSid(arpOutbound.getRsModelSid());

        return arpOutboundImpl;
    }

    /**
     * Returns the arp outbound with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the arp outbound
     * @return the arp outbound
     * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound findByPrimaryKey(Serializable primaryKey)
        throws NoSuchArpOutboundException, SystemException {
        ArpOutbound arpOutbound = fetchByPrimaryKey(primaryKey);

        if (arpOutbound == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return arpOutbound;
    }

    /**
     * Returns the arp outbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchArpOutboundException} if it could not be found.
     *
     * @param arpOutboundPK the primary key of the arp outbound
     * @return the arp outbound
     * @throws com.stpl.app.parttwo.NoSuchArpOutboundException if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound findByPrimaryKey(ArpOutboundPK arpOutboundPK)
        throws NoSuchArpOutboundException, SystemException {
        return findByPrimaryKey((Serializable) arpOutboundPK);
    }

    /**
     * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the arp outbound
     * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ArpOutbound arpOutbound = (ArpOutbound) EntityCacheUtil.getResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                ArpOutboundImpl.class, primaryKey);

        if (arpOutbound == _nullArpOutbound) {
            return null;
        }

        if (arpOutbound == null) {
            Session session = null;

            try {
                session = openSession();

                arpOutbound = (ArpOutbound) session.get(ArpOutboundImpl.class,
                        primaryKey);

                if (arpOutbound != null) {
                    cacheResult(arpOutbound);
                } else {
                    EntityCacheUtil.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                        ArpOutboundImpl.class, primaryKey, _nullArpOutbound);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                    ArpOutboundImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return arpOutbound;
    }

    /**
     * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param arpOutboundPK the primary key of the arp outbound
     * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ArpOutbound fetchByPrimaryKey(ArpOutboundPK arpOutboundPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) arpOutboundPK);
    }

    /**
     * Returns all the arp outbounds.
     *
     * @return the arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ArpOutbound> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the arp outbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of arp outbounds
     * @param end the upper bound of the range of arp outbounds (not inclusive)
     * @return the range of arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ArpOutbound> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the arp outbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of arp outbounds
     * @param end the upper bound of the range of arp outbounds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ArpOutbound> findAll(int start, int end,
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

        List<ArpOutbound> list = (List<ArpOutbound>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ARPOUTBOUND);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ARPOUTBOUND;

                if (pagination) {
                    sql = sql.concat(ArpOutboundModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ArpOutbound>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ArpOutbound>(list);
                } else {
                    list = (List<ArpOutbound>) QueryUtil.list(q, getDialect(),
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
     * Removes all the arp outbounds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ArpOutbound arpOutbound : findAll()) {
            remove(arpOutbound);
        }
    }

    /**
     * Returns the number of arp outbounds.
     *
     * @return the number of arp outbounds
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

                Query q = session.createQuery(_SQL_COUNT_ARPOUTBOUND);

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
     * Initializes the arp outbound persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.ArpOutbound")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ArpOutbound>> listenersList = new ArrayList<ModelListener<ArpOutbound>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ArpOutbound>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ArpOutboundImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
