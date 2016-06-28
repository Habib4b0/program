package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGcmCompanyLinkException;
import com.stpl.app.model.GcmCompanyLink;
import com.stpl.app.model.impl.GcmCompanyLinkImpl;
import com.stpl.app.model.impl.GcmCompanyLinkModelImpl;
import com.stpl.app.service.persistence.GcmCompanyLinkPersistence;

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
 * The persistence implementation for the gcm company link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyLinkPersistence
 * @see GcmCompanyLinkUtil
 * @generated
 */
public class GcmCompanyLinkPersistenceImpl extends BasePersistenceImpl<GcmCompanyLink>
    implements GcmCompanyLinkPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GcmCompanyLinkUtil} to access the gcm company link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GcmCompanyLinkImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED,
            GcmCompanyLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED,
            GcmCompanyLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GCMCOMPANYLINK = "SELECT gcmCompanyLink FROM GcmCompanyLink gcmCompanyLink";
    private static final String _SQL_COUNT_GCMCOMPANYLINK = "SELECT COUNT(gcmCompanyLink) FROM GcmCompanyLink gcmCompanyLink";
    private static final String _ORDER_BY_ENTITY_ALIAS = "gcmCompanyLink.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmCompanyLink exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GcmCompanyLinkPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "userId", "companyNo", "companyId",
                "gcmCompanyLinkSid", "sessionId", "companyName", "linkId",
                "companyMasterSid"
            });
    private static GcmCompanyLink _nullGcmCompanyLink = new GcmCompanyLinkImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GcmCompanyLink> toCacheModel() {
                return _nullGcmCompanyLinkCacheModel;
            }
        };

    private static CacheModel<GcmCompanyLink> _nullGcmCompanyLinkCacheModel = new CacheModel<GcmCompanyLink>() {
            @Override
            public GcmCompanyLink toEntityModel() {
                return _nullGcmCompanyLink;
            }
        };

    public GcmCompanyLinkPersistenceImpl() {
        setModelClass(GcmCompanyLink.class);
    }

    /**
     * Caches the gcm company link in the entity cache if it is enabled.
     *
     * @param gcmCompanyLink the gcm company link
     */
    @Override
    public void cacheResult(GcmCompanyLink gcmCompanyLink) {
        EntityCacheUtil.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey(),
            gcmCompanyLink);

        gcmCompanyLink.resetOriginalValues();
    }

    /**
     * Caches the gcm company links in the entity cache if it is enabled.
     *
     * @param gcmCompanyLinks the gcm company links
     */
    @Override
    public void cacheResult(List<GcmCompanyLink> gcmCompanyLinks) {
        for (GcmCompanyLink gcmCompanyLink : gcmCompanyLinks) {
            if (EntityCacheUtil.getResult(
                        GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
                        GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey()) == null) {
                cacheResult(gcmCompanyLink);
            } else {
                gcmCompanyLink.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gcm company links.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GcmCompanyLinkImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GcmCompanyLinkImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gcm company link.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GcmCompanyLink gcmCompanyLink) {
        EntityCacheUtil.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GcmCompanyLink> gcmCompanyLinks) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GcmCompanyLink gcmCompanyLink : gcmCompanyLinks) {
            EntityCacheUtil.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
                GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey());
        }
    }

    /**
     * Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
     *
     * @param gcmCompanyLinkSid the primary key for the new gcm company link
     * @return the new gcm company link
     */
    @Override
    public GcmCompanyLink create(int gcmCompanyLinkSid) {
        GcmCompanyLink gcmCompanyLink = new GcmCompanyLinkImpl();

        gcmCompanyLink.setNew(true);
        gcmCompanyLink.setPrimaryKey(gcmCompanyLinkSid);

        return gcmCompanyLink;
    }

    /**
     * Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param gcmCompanyLinkSid the primary key of the gcm company link
     * @return the gcm company link that was removed
     * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink remove(int gcmCompanyLinkSid)
        throws NoSuchGcmCompanyLinkException, SystemException {
        return remove((Serializable) gcmCompanyLinkSid);
    }

    /**
     * Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gcm company link
     * @return the gcm company link that was removed
     * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink remove(Serializable primaryKey)
        throws NoSuchGcmCompanyLinkException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GcmCompanyLink gcmCompanyLink = (GcmCompanyLink) session.get(GcmCompanyLinkImpl.class,
                    primaryKey);

            if (gcmCompanyLink == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGcmCompanyLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(gcmCompanyLink);
        } catch (NoSuchGcmCompanyLinkException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GcmCompanyLink removeImpl(GcmCompanyLink gcmCompanyLink)
        throws SystemException {
        gcmCompanyLink = toUnwrappedModel(gcmCompanyLink);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(gcmCompanyLink)) {
                gcmCompanyLink = (GcmCompanyLink) session.get(GcmCompanyLinkImpl.class,
                        gcmCompanyLink.getPrimaryKeyObj());
            }

            if (gcmCompanyLink != null) {
                session.delete(gcmCompanyLink);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (gcmCompanyLink != null) {
            clearCache(gcmCompanyLink);
        }

        return gcmCompanyLink;
    }

    @Override
    public GcmCompanyLink updateImpl(
        com.stpl.app.model.GcmCompanyLink gcmCompanyLink)
        throws SystemException {
        gcmCompanyLink = toUnwrappedModel(gcmCompanyLink);

        boolean isNew = gcmCompanyLink.isNew();

        Session session = null;

        try {
            session = openSession();

            if (gcmCompanyLink.isNew()) {
                session.save(gcmCompanyLink);

                gcmCompanyLink.setNew(false);
            } else {
                session.merge(gcmCompanyLink);
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

        EntityCacheUtil.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyLinkImpl.class, gcmCompanyLink.getPrimaryKey(),
            gcmCompanyLink);

        return gcmCompanyLink;
    }

    protected GcmCompanyLink toUnwrappedModel(GcmCompanyLink gcmCompanyLink) {
        if (gcmCompanyLink instanceof GcmCompanyLinkImpl) {
            return gcmCompanyLink;
        }

        GcmCompanyLinkImpl gcmCompanyLinkImpl = new GcmCompanyLinkImpl();

        gcmCompanyLinkImpl.setNew(gcmCompanyLink.isNew());
        gcmCompanyLinkImpl.setPrimaryKey(gcmCompanyLink.getPrimaryKey());

        gcmCompanyLinkImpl.setCheckRecord(gcmCompanyLink.isCheckRecord());
        gcmCompanyLinkImpl.setUserId(gcmCompanyLink.getUserId());
        gcmCompanyLinkImpl.setCompanyNo(gcmCompanyLink.getCompanyNo());
        gcmCompanyLinkImpl.setCompanyId(gcmCompanyLink.getCompanyId());
        gcmCompanyLinkImpl.setGcmCompanyLinkSid(gcmCompanyLink.getGcmCompanyLinkSid());
        gcmCompanyLinkImpl.setSessionId(gcmCompanyLink.getSessionId());
        gcmCompanyLinkImpl.setCompanyName(gcmCompanyLink.getCompanyName());
        gcmCompanyLinkImpl.setLinkId(gcmCompanyLink.getLinkId());
        gcmCompanyLinkImpl.setCompanyMasterSid(gcmCompanyLink.getCompanyMasterSid());

        return gcmCompanyLinkImpl;
    }

    /**
     * Returns the gcm company link with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gcm company link
     * @return the gcm company link
     * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGcmCompanyLinkException, SystemException {
        GcmCompanyLink gcmCompanyLink = fetchByPrimaryKey(primaryKey);

        if (gcmCompanyLink == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGcmCompanyLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return gcmCompanyLink;
    }

    /**
     * Returns the gcm company link with the primary key or throws a {@link com.stpl.app.NoSuchGcmCompanyLinkException} if it could not be found.
     *
     * @param gcmCompanyLinkSid the primary key of the gcm company link
     * @return the gcm company link
     * @throws com.stpl.app.NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink findByPrimaryKey(int gcmCompanyLinkSid)
        throws NoSuchGcmCompanyLinkException, SystemException {
        return findByPrimaryKey((Serializable) gcmCompanyLinkSid);
    }

    /**
     * Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gcm company link
     * @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GcmCompanyLink gcmCompanyLink = (GcmCompanyLink) EntityCacheUtil.getResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
                GcmCompanyLinkImpl.class, primaryKey);

        if (gcmCompanyLink == _nullGcmCompanyLink) {
            return null;
        }

        if (gcmCompanyLink == null) {
            Session session = null;

            try {
                session = openSession();

                gcmCompanyLink = (GcmCompanyLink) session.get(GcmCompanyLinkImpl.class,
                        primaryKey);

                if (gcmCompanyLink != null) {
                    cacheResult(gcmCompanyLink);
                } else {
                    EntityCacheUtil.putResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
                        GcmCompanyLinkImpl.class, primaryKey,
                        _nullGcmCompanyLink);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GcmCompanyLinkModelImpl.ENTITY_CACHE_ENABLED,
                    GcmCompanyLinkImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return gcmCompanyLink;
    }

    /**
     * Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param gcmCompanyLinkSid the primary key of the gcm company link
     * @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyLink fetchByPrimaryKey(int gcmCompanyLinkSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) gcmCompanyLinkSid);
    }

    /**
     * Returns all the gcm company links.
     *
     * @return the gcm company links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyLink> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gcm company links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm company links
     * @param end the upper bound of the range of gcm company links (not inclusive)
     * @return the range of gcm company links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyLink> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gcm company links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm company links
     * @param end the upper bound of the range of gcm company links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gcm company links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyLink> findAll(int start, int end,
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

        List<GcmCompanyLink> list = (List<GcmCompanyLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GCMCOMPANYLINK);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GCMCOMPANYLINK;

                if (pagination) {
                    sql = sql.concat(GcmCompanyLinkModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GcmCompanyLink>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GcmCompanyLink>(list);
                } else {
                    list = (List<GcmCompanyLink>) QueryUtil.list(q,
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
     * Removes all the gcm company links from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GcmCompanyLink gcmCompanyLink : findAll()) {
            remove(gcmCompanyLink);
        }
    }

    /**
     * Returns the number of gcm company links.
     *
     * @return the number of gcm company links
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

                Query q = session.createQuery(_SQL_COUNT_GCMCOMPANYLINK);

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
     * Initializes the gcm company link persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GcmCompanyLink")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GcmCompanyLink>> listenersList = new ArrayList<ModelListener<GcmCompanyLink>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GcmCompanyLink>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GcmCompanyLinkImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
