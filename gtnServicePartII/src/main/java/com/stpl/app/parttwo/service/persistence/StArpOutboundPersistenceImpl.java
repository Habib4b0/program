package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchStArpOutboundException;
import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.model.impl.StArpOutboundImpl;
import com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPersistence;

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
 * The persistence implementation for the st arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StArpOutboundPersistence
 * @see StArpOutboundUtil
 * @generated
 */
public class StArpOutboundPersistenceImpl extends BasePersistenceImpl<StArpOutbound>
    implements StArpOutboundPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StArpOutboundUtil} to access the st arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StArpOutboundImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundModelImpl.FINDER_CACHE_ENABLED,
            StArpOutboundImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundModelImpl.FINDER_CACHE_ENABLED,
            StArpOutboundImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STARPOUTBOUND = "SELECT stArpOutbound FROM StArpOutbound stArpOutbound";
    private static final String _SQL_COUNT_STARPOUTBOUND = "SELECT COUNT(stArpOutbound) FROM StArpOutbound stArpOutbound";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stArpOutbound.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StArpOutbound exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StArpOutboundPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "salesUnitsRate", "accountType", "originalBatchId",
                "companyMasterSid", "brandMasterSid", "arpApprovalDate",
                "userId", "arpMasterSid", "arpCreationDate", "checkRecord",
                "arpId", "account", "outboundStatus", "periodSid",
                "itemMasterSid", "rsModelSid", "sessionId"
            });
    private static StArpOutbound _nullStArpOutbound = new StArpOutboundImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StArpOutbound> toCacheModel() {
                return _nullStArpOutboundCacheModel;
            }
        };

    private static CacheModel<StArpOutbound> _nullStArpOutboundCacheModel = new CacheModel<StArpOutbound>() {
            @Override
            public StArpOutbound toEntityModel() {
                return _nullStArpOutbound;
            }
        };

    public StArpOutboundPersistenceImpl() {
        setModelClass(StArpOutbound.class);
    }

    /**
     * Caches the st arp outbound in the entity cache if it is enabled.
     *
     * @param stArpOutbound the st arp outbound
     */
    @Override
    public void cacheResult(StArpOutbound stArpOutbound) {
        EntityCacheUtil.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundImpl.class, stArpOutbound.getPrimaryKey(),
            stArpOutbound);

        stArpOutbound.resetOriginalValues();
    }

    /**
     * Caches the st arp outbounds in the entity cache if it is enabled.
     *
     * @param stArpOutbounds the st arp outbounds
     */
    @Override
    public void cacheResult(List<StArpOutbound> stArpOutbounds) {
        for (StArpOutbound stArpOutbound : stArpOutbounds) {
            if (EntityCacheUtil.getResult(
                        StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                        StArpOutboundImpl.class, stArpOutbound.getPrimaryKey()) == null) {
                cacheResult(stArpOutbound);
            } else {
                stArpOutbound.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st arp outbounds.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StArpOutboundImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StArpOutboundImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st arp outbound.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StArpOutbound stArpOutbound) {
        EntityCacheUtil.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundImpl.class, stArpOutbound.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StArpOutbound> stArpOutbounds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StArpOutbound stArpOutbound : stArpOutbounds) {
            EntityCacheUtil.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                StArpOutboundImpl.class, stArpOutbound.getPrimaryKey());
        }
    }

    /**
     * Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
     *
     * @param stArpOutboundPK the primary key for the new st arp outbound
     * @return the new st arp outbound
     */
    @Override
    public StArpOutbound create(StArpOutboundPK stArpOutboundPK) {
        StArpOutbound stArpOutbound = new StArpOutboundImpl();

        stArpOutbound.setNew(true);
        stArpOutbound.setPrimaryKey(stArpOutboundPK);

        return stArpOutbound;
    }

    /**
     * Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stArpOutboundPK the primary key of the st arp outbound
     * @return the st arp outbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound remove(StArpOutboundPK stArpOutboundPK)
        throws NoSuchStArpOutboundException, SystemException {
        return remove((Serializable) stArpOutboundPK);
    }

    /**
     * Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st arp outbound
     * @return the st arp outbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound remove(Serializable primaryKey)
        throws NoSuchStArpOutboundException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StArpOutbound stArpOutbound = (StArpOutbound) session.get(StArpOutboundImpl.class,
                    primaryKey);

            if (stArpOutbound == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stArpOutbound);
        } catch (NoSuchStArpOutboundException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StArpOutbound removeImpl(StArpOutbound stArpOutbound)
        throws SystemException {
        stArpOutbound = toUnwrappedModel(stArpOutbound);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stArpOutbound)) {
                stArpOutbound = (StArpOutbound) session.get(StArpOutboundImpl.class,
                        stArpOutbound.getPrimaryKeyObj());
            }

            if (stArpOutbound != null) {
                session.delete(stArpOutbound);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stArpOutbound != null) {
            clearCache(stArpOutbound);
        }

        return stArpOutbound;
    }

    @Override
    public StArpOutbound updateImpl(
        com.stpl.app.parttwo.model.StArpOutbound stArpOutbound)
        throws SystemException {
        stArpOutbound = toUnwrappedModel(stArpOutbound);

        boolean isNew = stArpOutbound.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stArpOutbound.isNew()) {
                session.save(stArpOutbound);

                stArpOutbound.setNew(false);
            } else {
                session.merge(stArpOutbound);
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

        EntityCacheUtil.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
            StArpOutboundImpl.class, stArpOutbound.getPrimaryKey(),
            stArpOutbound);

        return stArpOutbound;
    }

    protected StArpOutbound toUnwrappedModel(StArpOutbound stArpOutbound) {
        if (stArpOutbound instanceof StArpOutboundImpl) {
            return stArpOutbound;
        }

        StArpOutboundImpl stArpOutboundImpl = new StArpOutboundImpl();

        stArpOutboundImpl.setNew(stArpOutbound.isNew());
        stArpOutboundImpl.setPrimaryKey(stArpOutbound.getPrimaryKey());

        stArpOutboundImpl.setSalesUnitsRate(stArpOutbound.getSalesUnitsRate());
        stArpOutboundImpl.setAccountType(stArpOutbound.getAccountType());
        stArpOutboundImpl.setOriginalBatchId(stArpOutbound.getOriginalBatchId());
        stArpOutboundImpl.setCompanyMasterSid(stArpOutbound.getCompanyMasterSid());
        stArpOutboundImpl.setBrandMasterSid(stArpOutbound.getBrandMasterSid());
        stArpOutboundImpl.setArpApprovalDate(stArpOutbound.getArpApprovalDate());
        stArpOutboundImpl.setUserId(stArpOutbound.getUserId());
        stArpOutboundImpl.setArpMasterSid(stArpOutbound.getArpMasterSid());
        stArpOutboundImpl.setArpCreationDate(stArpOutbound.getArpCreationDate());
        stArpOutboundImpl.setCheckRecord(stArpOutbound.isCheckRecord());
        stArpOutboundImpl.setArpId(stArpOutbound.getArpId());
        stArpOutboundImpl.setAccount(stArpOutbound.getAccount());
        stArpOutboundImpl.setOutboundStatus(stArpOutbound.isOutboundStatus());
        stArpOutboundImpl.setPeriodSid(stArpOutbound.getPeriodSid());
        stArpOutboundImpl.setItemMasterSid(stArpOutbound.getItemMasterSid());
        stArpOutboundImpl.setRsModelSid(stArpOutbound.getRsModelSid());
        stArpOutboundImpl.setSessionId(stArpOutbound.getSessionId());

        return stArpOutboundImpl;
    }

    /**
     * Returns the st arp outbound with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st arp outbound
     * @return the st arp outbound
     * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStArpOutboundException, SystemException {
        StArpOutbound stArpOutbound = fetchByPrimaryKey(primaryKey);

        if (stArpOutbound == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stArpOutbound;
    }

    /**
     * Returns the st arp outbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStArpOutboundException} if it could not be found.
     *
     * @param stArpOutboundPK the primary key of the st arp outbound
     * @return the st arp outbound
     * @throws com.stpl.app.parttwo.NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound findByPrimaryKey(StArpOutboundPK stArpOutboundPK)
        throws NoSuchStArpOutboundException, SystemException {
        return findByPrimaryKey((Serializable) stArpOutboundPK);
    }

    /**
     * Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st arp outbound
     * @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StArpOutbound stArpOutbound = (StArpOutbound) EntityCacheUtil.getResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                StArpOutboundImpl.class, primaryKey);

        if (stArpOutbound == _nullStArpOutbound) {
            return null;
        }

        if (stArpOutbound == null) {
            Session session = null;

            try {
                session = openSession();

                stArpOutbound = (StArpOutbound) session.get(StArpOutboundImpl.class,
                        primaryKey);

                if (stArpOutbound != null) {
                    cacheResult(stArpOutbound);
                } else {
                    EntityCacheUtil.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                        StArpOutboundImpl.class, primaryKey, _nullStArpOutbound);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
                    StArpOutboundImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stArpOutbound;
    }

    /**
     * Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stArpOutboundPK the primary key of the st arp outbound
     * @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StArpOutbound fetchByPrimaryKey(StArpOutboundPK stArpOutboundPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stArpOutboundPK);
    }

    /**
     * Returns all the st arp outbounds.
     *
     * @return the st arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StArpOutbound> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st arp outbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st arp outbounds
     * @param end the upper bound of the range of st arp outbounds (not inclusive)
     * @return the range of st arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StArpOutbound> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st arp outbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st arp outbounds
     * @param end the upper bound of the range of st arp outbounds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st arp outbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StArpOutbound> findAll(int start, int end,
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

        List<StArpOutbound> list = (List<StArpOutbound>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STARPOUTBOUND);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STARPOUTBOUND;

                if (pagination) {
                    sql = sql.concat(StArpOutboundModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StArpOutbound>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StArpOutbound>(list);
                } else {
                    list = (List<StArpOutbound>) QueryUtil.list(q,
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
     * Removes all the st arp outbounds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StArpOutbound stArpOutbound : findAll()) {
            remove(stArpOutbound);
        }
    }

    /**
     * Returns the number of st arp outbounds.
     *
     * @return the number of st arp outbounds
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

                Query q = session.createQuery(_SQL_COUNT_STARPOUTBOUND);

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
     * Initializes the st arp outbound persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.StArpOutbound")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StArpOutbound>> listenersList = new ArrayList<ModelListener<StArpOutbound>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StArpOutbound>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StArpOutboundImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
