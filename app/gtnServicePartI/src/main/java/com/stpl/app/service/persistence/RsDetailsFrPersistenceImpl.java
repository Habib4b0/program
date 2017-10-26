package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRsDetailsFrException;
import com.stpl.app.model.RsDetailsFr;
import com.stpl.app.model.impl.RsDetailsFrImpl;
import com.stpl.app.model.impl.RsDetailsFrModelImpl;
import com.stpl.app.service.persistence.RsDetailsFrPersistence;

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
 * The persistence implementation for the rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsFrPersistence
 * @see RsDetailsFrUtil
 * @generated
 */
public class RsDetailsFrPersistenceImpl extends BasePersistenceImpl<RsDetailsFr>
    implements RsDetailsFrPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RsDetailsFrUtil} to access the rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RsDetailsFrImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, RsDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, RsDetailsFrImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RSDETAILSFR = "SELECT rsDetailsFr FROM RsDetailsFr rsDetailsFr";
    private static final String _SQL_COUNT_RSDETAILSFR = "SELECT COUNT(rsDetailsFr) FROM RsDetailsFr rsDetailsFr";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rsDetailsFr.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsDetailsFr exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RsDetailsFrPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "recordLockStatus", "createdDate", "createdBy", "source",
                "formulaMethodId", "batchId", "modifiedBy", "inboundStatus",
                "formulaId", "itemMasterSid", "rsDetailsSid", "modifiedDate",
                "rsDetailsFrSid"
            });
    private static RsDetailsFr _nullRsDetailsFr = new RsDetailsFrImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RsDetailsFr> toCacheModel() {
                return _nullRsDetailsFrCacheModel;
            }
        };

    private static CacheModel<RsDetailsFr> _nullRsDetailsFrCacheModel = new CacheModel<RsDetailsFr>() {
            @Override
            public RsDetailsFr toEntityModel() {
                return _nullRsDetailsFr;
            }
        };

    public RsDetailsFrPersistenceImpl() {
        setModelClass(RsDetailsFr.class);
    }

    /**
     * Caches the rs details fr in the entity cache if it is enabled.
     *
     * @param rsDetailsFr the rs details fr
     */
    @Override
    public void cacheResult(RsDetailsFr rsDetailsFr) {
        EntityCacheUtil.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey(), rsDetailsFr);

        rsDetailsFr.resetOriginalValues();
    }

    /**
     * Caches the rs details frs in the entity cache if it is enabled.
     *
     * @param rsDetailsFrs the rs details frs
     */
    @Override
    public void cacheResult(List<RsDetailsFr> rsDetailsFrs) {
        for (RsDetailsFr rsDetailsFr : rsDetailsFrs) {
            if (EntityCacheUtil.getResult(
                        RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey()) == null) {
                cacheResult(rsDetailsFr);
            } else {
                rsDetailsFr.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rs details frs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RsDetailsFrImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RsDetailsFrImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rs details fr.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RsDetailsFr rsDetailsFr) {
        EntityCacheUtil.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RsDetailsFr> rsDetailsFrs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RsDetailsFr rsDetailsFr : rsDetailsFrs) {
            EntityCacheUtil.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey());
        }
    }

    /**
     * Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
     *
     * @param rsDetailsFrSid the primary key for the new rs details fr
     * @return the new rs details fr
     */
    @Override
    public RsDetailsFr create(int rsDetailsFrSid) {
        RsDetailsFr rsDetailsFr = new RsDetailsFrImpl();

        rsDetailsFr.setNew(true);
        rsDetailsFr.setPrimaryKey(rsDetailsFrSid);

        return rsDetailsFr;
    }

    /**
     * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rsDetailsFrSid the primary key of the rs details fr
     * @return the rs details fr that was removed
     * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr remove(int rsDetailsFrSid)
        throws NoSuchRsDetailsFrException, SystemException {
        return remove((Serializable) rsDetailsFrSid);
    }

    /**
     * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rs details fr
     * @return the rs details fr that was removed
     * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr remove(Serializable primaryKey)
        throws NoSuchRsDetailsFrException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RsDetailsFr rsDetailsFr = (RsDetailsFr) session.get(RsDetailsFrImpl.class,
                    primaryKey);

            if (rsDetailsFr == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rsDetailsFr);
        } catch (NoSuchRsDetailsFrException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RsDetailsFr removeImpl(RsDetailsFr rsDetailsFr)
        throws SystemException {
        rsDetailsFr = toUnwrappedModel(rsDetailsFr);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rsDetailsFr)) {
                rsDetailsFr = (RsDetailsFr) session.get(RsDetailsFrImpl.class,
                        rsDetailsFr.getPrimaryKeyObj());
            }

            if (rsDetailsFr != null) {
                session.delete(rsDetailsFr);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rsDetailsFr != null) {
            clearCache(rsDetailsFr);
        }

        return rsDetailsFr;
    }

    @Override
    public RsDetailsFr updateImpl(com.stpl.app.model.RsDetailsFr rsDetailsFr)
        throws SystemException {
        rsDetailsFr = toUnwrappedModel(rsDetailsFr);

        boolean isNew = rsDetailsFr.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rsDetailsFr.isNew()) {
                session.save(rsDetailsFr);

                rsDetailsFr.setNew(false);
            } else {
                session.merge(rsDetailsFr);
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

        EntityCacheUtil.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey(), rsDetailsFr);

        return rsDetailsFr;
    }

    protected RsDetailsFr toUnwrappedModel(RsDetailsFr rsDetailsFr) {
        if (rsDetailsFr instanceof RsDetailsFrImpl) {
            return rsDetailsFr;
        }

        RsDetailsFrImpl rsDetailsFrImpl = new RsDetailsFrImpl();

        rsDetailsFrImpl.setNew(rsDetailsFr.isNew());
        rsDetailsFrImpl.setPrimaryKey(rsDetailsFr.getPrimaryKey());

        rsDetailsFrImpl.setRecordLockStatus(rsDetailsFr.isRecordLockStatus());
        rsDetailsFrImpl.setCreatedDate(rsDetailsFr.getCreatedDate());
        rsDetailsFrImpl.setCreatedBy(rsDetailsFr.getCreatedBy());
        rsDetailsFrImpl.setSource(rsDetailsFr.getSource());
        rsDetailsFrImpl.setFormulaMethodId(rsDetailsFr.getFormulaMethodId());
        rsDetailsFrImpl.setBatchId(rsDetailsFr.getBatchId());
        rsDetailsFrImpl.setModifiedBy(rsDetailsFr.getModifiedBy());
        rsDetailsFrImpl.setInboundStatus(rsDetailsFr.getInboundStatus());
        rsDetailsFrImpl.setFormulaId(rsDetailsFr.getFormulaId());
        rsDetailsFrImpl.setItemMasterSid(rsDetailsFr.getItemMasterSid());
        rsDetailsFrImpl.setRsDetailsSid(rsDetailsFr.getRsDetailsSid());
        rsDetailsFrImpl.setModifiedDate(rsDetailsFr.getModifiedDate());
        rsDetailsFrImpl.setRsDetailsFrSid(rsDetailsFr.getRsDetailsFrSid());

        return rsDetailsFrImpl;
    }

    /**
     * Returns the rs details fr with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rs details fr
     * @return the rs details fr
     * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRsDetailsFrException, SystemException {
        RsDetailsFr rsDetailsFr = fetchByPrimaryKey(primaryKey);

        if (rsDetailsFr == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rsDetailsFr;
    }

    /**
     * Returns the rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsFrException} if it could not be found.
     *
     * @param rsDetailsFrSid the primary key of the rs details fr
     * @return the rs details fr
     * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr findByPrimaryKey(int rsDetailsFrSid)
        throws NoSuchRsDetailsFrException, SystemException {
        return findByPrimaryKey((Serializable) rsDetailsFrSid);
    }

    /**
     * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rs details fr
     * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RsDetailsFr rsDetailsFr = (RsDetailsFr) EntityCacheUtil.getResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                RsDetailsFrImpl.class, primaryKey);

        if (rsDetailsFr == _nullRsDetailsFr) {
            return null;
        }

        if (rsDetailsFr == null) {
            Session session = null;

            try {
                session = openSession();

                rsDetailsFr = (RsDetailsFr) session.get(RsDetailsFrImpl.class,
                        primaryKey);

                if (rsDetailsFr != null) {
                    cacheResult(rsDetailsFr);
                } else {
                    EntityCacheUtil.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                        RsDetailsFrImpl.class, primaryKey, _nullRsDetailsFr);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
                    RsDetailsFrImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rsDetailsFr;
    }

    /**
     * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rsDetailsFrSid the primary key of the rs details fr
     * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetailsFr fetchByPrimaryKey(int rsDetailsFrSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rsDetailsFrSid);
    }

    /**
     * Returns all the rs details frs.
     *
     * @return the rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetailsFr> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rs details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs details frs
     * @param end the upper bound of the range of rs details frs (not inclusive)
     * @return the range of rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetailsFr> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rs details frs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs details frs
     * @param end the upper bound of the range of rs details frs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rs details frs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetailsFr> findAll(int start, int end,
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

        List<RsDetailsFr> list = (List<RsDetailsFr>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RSDETAILSFR);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RSDETAILSFR;

                if (pagination) {
                    sql = sql.concat(RsDetailsFrModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RsDetailsFr>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsDetailsFr>(list);
                } else {
                    list = (List<RsDetailsFr>) QueryUtil.list(q, getDialect(),
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
     * Removes all the rs details frs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RsDetailsFr rsDetailsFr : findAll()) {
            remove(rsDetailsFr);
        }
    }

    /**
     * Returns the number of rs details frs.
     *
     * @return the number of rs details frs
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

                Query q = session.createQuery(_SQL_COUNT_RSDETAILSFR);

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
     * Initializes the rs details fr persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RsDetailsFr")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RsDetailsFr>> listenersList = new ArrayList<ModelListener<RsDetailsFr>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RsDetailsFr>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RsDetailsFrImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
