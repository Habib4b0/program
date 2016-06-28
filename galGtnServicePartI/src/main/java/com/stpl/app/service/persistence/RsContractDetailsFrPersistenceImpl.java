package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRsContractDetailsFrException;
import com.stpl.app.model.RsContractDetailsFr;
import com.stpl.app.model.impl.RsContractDetailsFrImpl;
import com.stpl.app.model.impl.RsContractDetailsFrModelImpl;
import com.stpl.app.service.persistence.RsContractDetailsFrPersistence;

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
 * The persistence implementation for the rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsFrPersistence
 * @see RsContractDetailsFrUtil
 * @generated
 */
public class RsContractDetailsFrPersistenceImpl extends BasePersistenceImpl<RsContractDetailsFr>
    implements RsContractDetailsFrPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RsContractDetailsFrUtil} to access the rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RsContractDetailsFrImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            RsContractDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
            RsContractDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RSCONTRACTDETAILSFR = "SELECT rsContractDetailsFr FROM RsContractDetailsFr rsContractDetailsFr";
    private static final String _SQL_COUNT_RSCONTRACTDETAILSFR = "SELECT COUNT(rsContractDetailsFr) FROM RsContractDetailsFr rsContractDetailsFr";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rsContractDetailsFr.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsContractDetailsFr exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RsContractDetailsFrPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "recordLockStatus", "createdDate", "createdBy", "source",
                "formulaMethodId", "itemMasterSid", "batchId",
                "rsContractDetailsFrSid", "modifiedBy", "inboundStatus",
                "formulaId", "modifiedDate", "rsContractDetailsSid"
            });
    private static RsContractDetailsFr _nullRsContractDetailsFr = new RsContractDetailsFrImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RsContractDetailsFr> toCacheModel() {
                return _nullRsContractDetailsFrCacheModel;
            }
        };

    private static CacheModel<RsContractDetailsFr> _nullRsContractDetailsFrCacheModel =
        new CacheModel<RsContractDetailsFr>() {
            @Override
            public RsContractDetailsFr toEntityModel() {
                return _nullRsContractDetailsFr;
            }
        };

    public RsContractDetailsFrPersistenceImpl() {
        setModelClass(RsContractDetailsFr.class);
    }

    /**
     * Caches the rs contract details fr in the entity cache if it is enabled.
     *
     * @param rsContractDetailsFr the rs contract details fr
     */
    @Override
    public void cacheResult(RsContractDetailsFr rsContractDetailsFr) {
        EntityCacheUtil.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey(),
            rsContractDetailsFr);

        rsContractDetailsFr.resetOriginalValues();
    }

    /**
     * Caches the rs contract details frs in the entity cache if it is enabled.
     *
     * @param rsContractDetailsFrs the rs contract details frs
     */
    @Override
    public void cacheResult(List<RsContractDetailsFr> rsContractDetailsFrs) {
        for (RsContractDetailsFr rsContractDetailsFr : rsContractDetailsFrs) {
            if (EntityCacheUtil.getResult(
                        RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        RsContractDetailsFrImpl.class,
                        rsContractDetailsFr.getPrimaryKey()) == null) {
                cacheResult(rsContractDetailsFr);
            } else {
                rsContractDetailsFr.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rs contract details frs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RsContractDetailsFrImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RsContractDetailsFrImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rs contract details fr.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RsContractDetailsFr rsContractDetailsFr) {
        EntityCacheUtil.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RsContractDetailsFr> rsContractDetailsFrs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RsContractDetailsFr rsContractDetailsFr : rsContractDetailsFrs) {
            EntityCacheUtil.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                RsContractDetailsFrImpl.class,
                rsContractDetailsFr.getPrimaryKey());
        }
    }

    /**
     * Creates a new rs contract details fr with the primary key. Does not add the rs contract details fr to the database.
     *
     * @param rsContractDetailsFrSid the primary key for the new rs contract details fr
     * @return the new rs contract details fr
     */
    @Override
    public RsContractDetailsFr create(int rsContractDetailsFrSid) {
        RsContractDetailsFr rsContractDetailsFr = new RsContractDetailsFrImpl();

        rsContractDetailsFr.setNew(true);
        rsContractDetailsFr.setPrimaryKey(rsContractDetailsFrSid);

        return rsContractDetailsFr;
    }

    /**
     * Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rsContractDetailsFrSid the primary key of the rs contract details fr
     * @return the rs contract details fr that was removed
     * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr remove(int rsContractDetailsFrSid)
        throws NoSuchRsContractDetailsFrException, SystemException {
        return remove((Serializable) rsContractDetailsFrSid);
    }

    /**
     * Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rs contract details fr
     * @return the rs contract details fr that was removed
     * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr remove(Serializable primaryKey)
        throws NoSuchRsContractDetailsFrException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RsContractDetailsFr rsContractDetailsFr = (RsContractDetailsFr) session.get(RsContractDetailsFrImpl.class,
                    primaryKey);

            if (rsContractDetailsFr == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rsContractDetailsFr);
        } catch (NoSuchRsContractDetailsFrException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RsContractDetailsFr removeImpl(
        RsContractDetailsFr rsContractDetailsFr) throws SystemException {
        rsContractDetailsFr = toUnwrappedModel(rsContractDetailsFr);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rsContractDetailsFr)) {
                rsContractDetailsFr = (RsContractDetailsFr) session.get(RsContractDetailsFrImpl.class,
                        rsContractDetailsFr.getPrimaryKeyObj());
            }

            if (rsContractDetailsFr != null) {
                session.delete(rsContractDetailsFr);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rsContractDetailsFr != null) {
            clearCache(rsContractDetailsFr);
        }

        return rsContractDetailsFr;
    }

    @Override
    public RsContractDetailsFr updateImpl(
        com.stpl.app.model.RsContractDetailsFr rsContractDetailsFr)
        throws SystemException {
        rsContractDetailsFr = toUnwrappedModel(rsContractDetailsFr);

        boolean isNew = rsContractDetailsFr.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rsContractDetailsFr.isNew()) {
                session.save(rsContractDetailsFr);

                rsContractDetailsFr.setNew(false);
            } else {
                session.merge(rsContractDetailsFr);
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

        EntityCacheUtil.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey(),
            rsContractDetailsFr);

        return rsContractDetailsFr;
    }

    protected RsContractDetailsFr toUnwrappedModel(
        RsContractDetailsFr rsContractDetailsFr) {
        if (rsContractDetailsFr instanceof RsContractDetailsFrImpl) {
            return rsContractDetailsFr;
        }

        RsContractDetailsFrImpl rsContractDetailsFrImpl = new RsContractDetailsFrImpl();

        rsContractDetailsFrImpl.setNew(rsContractDetailsFr.isNew());
        rsContractDetailsFrImpl.setPrimaryKey(rsContractDetailsFr.getPrimaryKey());

        rsContractDetailsFrImpl.setRecordLockStatus(rsContractDetailsFr.isRecordLockStatus());
        rsContractDetailsFrImpl.setCreatedDate(rsContractDetailsFr.getCreatedDate());
        rsContractDetailsFrImpl.setCreatedBy(rsContractDetailsFr.getCreatedBy());
        rsContractDetailsFrImpl.setSource(rsContractDetailsFr.getSource());
        rsContractDetailsFrImpl.setFormulaMethodId(rsContractDetailsFr.getFormulaMethodId());
        rsContractDetailsFrImpl.setItemMasterSid(rsContractDetailsFr.getItemMasterSid());
        rsContractDetailsFrImpl.setBatchId(rsContractDetailsFr.getBatchId());
        rsContractDetailsFrImpl.setRsContractDetailsFrSid(rsContractDetailsFr.getRsContractDetailsFrSid());
        rsContractDetailsFrImpl.setModifiedBy(rsContractDetailsFr.getModifiedBy());
        rsContractDetailsFrImpl.setInboundStatus(rsContractDetailsFr.getInboundStatus());
        rsContractDetailsFrImpl.setFormulaId(rsContractDetailsFr.getFormulaId());
        rsContractDetailsFrImpl.setModifiedDate(rsContractDetailsFr.getModifiedDate());
        rsContractDetailsFrImpl.setRsContractDetailsSid(rsContractDetailsFr.getRsContractDetailsSid());

        return rsContractDetailsFrImpl;
    }

    /**
     * Returns the rs contract details fr with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rs contract details fr
     * @return the rs contract details fr
     * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRsContractDetailsFrException, SystemException {
        RsContractDetailsFr rsContractDetailsFr = fetchByPrimaryKey(primaryKey);

        if (rsContractDetailsFr == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rsContractDetailsFr;
    }

    /**
     * Returns the rs contract details fr with the primary key or throws a {@link com.stpl.app.NoSuchRsContractDetailsFrException} if it could not be found.
     *
     * @param rsContractDetailsFrSid the primary key of the rs contract details fr
     * @return the rs contract details fr
     * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr findByPrimaryKey(int rsContractDetailsFrSid)
        throws NoSuchRsContractDetailsFrException, SystemException {
        return findByPrimaryKey((Serializable) rsContractDetailsFrSid);
    }

    /**
     * Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rs contract details fr
     * @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RsContractDetailsFr rsContractDetailsFr = (RsContractDetailsFr) EntityCacheUtil.getResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                RsContractDetailsFrImpl.class, primaryKey);

        if (rsContractDetailsFr == _nullRsContractDetailsFr) {
            return null;
        }

        if (rsContractDetailsFr == null) {
            Session session = null;

            try {
                session = openSession();

                rsContractDetailsFr = (RsContractDetailsFr) session.get(RsContractDetailsFrImpl.class,
                        primaryKey);

                if (rsContractDetailsFr != null) {
                    cacheResult(rsContractDetailsFr);
                } else {
                    EntityCacheUtil.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        RsContractDetailsFrImpl.class, primaryKey,
                        _nullRsContractDetailsFr);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                    RsContractDetailsFrImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rsContractDetailsFr;
    }

    /**
     * Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rsContractDetailsFrSid the primary key of the rs contract details fr
     * @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsContractDetailsFr fetchByPrimaryKey(int rsContractDetailsFrSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rsContractDetailsFrSid);
    }

    /**
     * Returns all the rs contract details frs.
     *
     * @return the rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContractDetailsFr> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rs contract details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs contract details frs
     * @param end the upper bound of the range of rs contract details frs (not inclusive)
     * @return the range of rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContractDetailsFr> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rs contract details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs contract details frs
     * @param end the upper bound of the range of rs contract details frs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rs contract details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsContractDetailsFr> findAll(int start, int end,
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

        List<RsContractDetailsFr> list = (List<RsContractDetailsFr>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RSCONTRACTDETAILSFR);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RSCONTRACTDETAILSFR;

                if (pagination) {
                    sql = sql.concat(RsContractDetailsFrModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RsContractDetailsFr>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsContractDetailsFr>(list);
                } else {
                    list = (List<RsContractDetailsFr>) QueryUtil.list(q,
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
     * Removes all the rs contract details frs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RsContractDetailsFr rsContractDetailsFr : findAll()) {
            remove(rsContractDetailsFr);
        }
    }

    /**
     * Returns the number of rs contract details frs.
     *
     * @return the number of rs contract details frs
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

                Query q = session.createQuery(_SQL_COUNT_RSCONTRACTDETAILSFR);

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
     * Initializes the rs contract details fr persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RsContractDetailsFr")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RsContractDetailsFr>> listenersList = new ArrayList<ModelListener<RsContractDetailsFr>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RsContractDetailsFr>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RsContractDetailsFrImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
