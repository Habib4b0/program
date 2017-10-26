package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchUdcsException;
import com.stpl.app.model.Udcs;
import com.stpl.app.model.impl.UdcsImpl;
import com.stpl.app.model.impl.UdcsModelImpl;
import com.stpl.app.service.persistence.UdcsPersistence;

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
 * The persistence implementation for the udcs service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UdcsPersistence
 * @see UdcsUtil
 * @generated
 */
public class UdcsPersistenceImpl extends BasePersistenceImpl<Udcs>
    implements UdcsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link UdcsUtil} to access the udcs persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = UdcsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsModelImpl.FINDER_CACHE_ENABLED, UdcsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsModelImpl.FINDER_CACHE_ENABLED, UdcsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_UDCS = "SELECT udcs FROM Udcs udcs";
    private static final String _SQL_COUNT_UDCS = "SELECT COUNT(udcs) FROM Udcs udcs";
    private static final String _ORDER_BY_ENTITY_ALIAS = "udcs.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Udcs exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(UdcsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "udc1", "udc2", "masterType", "udc3", "udc12", "udc11",
                "udcsSid", "masterSid", "udc10", "udc9", "udc8", "udc7", "udc6",
                "udc5", "udc4"
            });
    private static Udcs _nullUdcs = new UdcsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Udcs> toCacheModel() {
                return _nullUdcsCacheModel;
            }
        };

    private static CacheModel<Udcs> _nullUdcsCacheModel = new CacheModel<Udcs>() {
            @Override
            public Udcs toEntityModel() {
                return _nullUdcs;
            }
        };

    public UdcsPersistenceImpl() {
        setModelClass(Udcs.class);
    }

    /**
     * Caches the udcs in the entity cache if it is enabled.
     *
     * @param udcs the udcs
     */
    @Override
    public void cacheResult(Udcs udcs) {
        EntityCacheUtil.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsImpl.class, udcs.getPrimaryKey(), udcs);

        udcs.resetOriginalValues();
    }

    /**
     * Caches the udcses in the entity cache if it is enabled.
     *
     * @param udcses the udcses
     */
    @Override
    public void cacheResult(List<Udcs> udcses) {
        for (Udcs udcs : udcses) {
            if (EntityCacheUtil.getResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
                        UdcsImpl.class, udcs.getPrimaryKey()) == null) {
                cacheResult(udcs);
            } else {
                udcs.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all udcses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(UdcsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(UdcsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the udcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Udcs udcs) {
        EntityCacheUtil.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsImpl.class, udcs.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Udcs> udcses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Udcs udcs : udcses) {
            EntityCacheUtil.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
                UdcsImpl.class, udcs.getPrimaryKey());
        }
    }

    /**
     * Creates a new udcs with the primary key. Does not add the udcs to the database.
     *
     * @param udcsSid the primary key for the new udcs
     * @return the new udcs
     */
    @Override
    public Udcs create(int udcsSid) {
        Udcs udcs = new UdcsImpl();

        udcs.setNew(true);
        udcs.setPrimaryKey(udcsSid);

        return udcs;
    }

    /**
     * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param udcsSid the primary key of the udcs
     * @return the udcs that was removed
     * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs remove(int udcsSid) throws NoSuchUdcsException, SystemException {
        return remove((Serializable) udcsSid);
    }

    /**
     * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the udcs
     * @return the udcs that was removed
     * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs remove(Serializable primaryKey)
        throws NoSuchUdcsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Udcs udcs = (Udcs) session.get(UdcsImpl.class, primaryKey);

            if (udcs == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUdcsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(udcs);
        } catch (NoSuchUdcsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Udcs removeImpl(Udcs udcs) throws SystemException {
        udcs = toUnwrappedModel(udcs);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(udcs)) {
                udcs = (Udcs) session.get(UdcsImpl.class,
                        udcs.getPrimaryKeyObj());
            }

            if (udcs != null) {
                session.delete(udcs);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (udcs != null) {
            clearCache(udcs);
        }

        return udcs;
    }

    @Override
    public Udcs updateImpl(com.stpl.app.model.Udcs udcs)
        throws SystemException {
        udcs = toUnwrappedModel(udcs);

        boolean isNew = udcs.isNew();

        Session session = null;

        try {
            session = openSession();

            if (udcs.isNew()) {
                session.save(udcs);

                udcs.setNew(false);
            } else {
                session.merge(udcs);
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

        EntityCacheUtil.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
            UdcsImpl.class, udcs.getPrimaryKey(), udcs);

        return udcs;
    }

    protected Udcs toUnwrappedModel(Udcs udcs) {
        if (udcs instanceof UdcsImpl) {
            return udcs;
        }

        UdcsImpl udcsImpl = new UdcsImpl();

        udcsImpl.setNew(udcs.isNew());
        udcsImpl.setPrimaryKey(udcs.getPrimaryKey());

        udcsImpl.setUdc1(udcs.getUdc1());
        udcsImpl.setUdc2(udcs.getUdc2());
        udcsImpl.setMasterType(udcs.getMasterType());
        udcsImpl.setUdc3(udcs.getUdc3());
        udcsImpl.setUdc12(udcs.getUdc12());
        udcsImpl.setUdc11(udcs.getUdc11());
        udcsImpl.setUdcsSid(udcs.getUdcsSid());
        udcsImpl.setMasterSid(udcs.getMasterSid());
        udcsImpl.setUdc10(udcs.getUdc10());
        udcsImpl.setUdc9(udcs.getUdc9());
        udcsImpl.setUdc8(udcs.getUdc8());
        udcsImpl.setUdc7(udcs.getUdc7());
        udcsImpl.setUdc6(udcs.getUdc6());
        udcsImpl.setUdc5(udcs.getUdc5());
        udcsImpl.setUdc4(udcs.getUdc4());

        return udcsImpl;
    }

    /**
     * Returns the udcs with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the udcs
     * @return the udcs
     * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs findByPrimaryKey(Serializable primaryKey)
        throws NoSuchUdcsException, SystemException {
        Udcs udcs = fetchByPrimaryKey(primaryKey);

        if (udcs == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchUdcsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return udcs;
    }

    /**
     * Returns the udcs with the primary key or throws a {@link com.stpl.app.NoSuchUdcsException} if it could not be found.
     *
     * @param udcsSid the primary key of the udcs
     * @return the udcs
     * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs findByPrimaryKey(int udcsSid)
        throws NoSuchUdcsException, SystemException {
        return findByPrimaryKey((Serializable) udcsSid);
    }

    /**
     * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the udcs
     * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Udcs udcs = (Udcs) EntityCacheUtil.getResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
                UdcsImpl.class, primaryKey);

        if (udcs == _nullUdcs) {
            return null;
        }

        if (udcs == null) {
            Session session = null;

            try {
                session = openSession();

                udcs = (Udcs) session.get(UdcsImpl.class, primaryKey);

                if (udcs != null) {
                    cacheResult(udcs);
                } else {
                    EntityCacheUtil.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
                        UdcsImpl.class, primaryKey, _nullUdcs);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
                    UdcsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return udcs;
    }

    /**
     * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param udcsSid the primary key of the udcs
     * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Udcs fetchByPrimaryKey(int udcsSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) udcsSid);
    }

    /**
     * Returns all the udcses.
     *
     * @return the udcses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Udcs> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the udcses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of udcses
     * @param end the upper bound of the range of udcses (not inclusive)
     * @return the range of udcses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Udcs> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the udcses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of udcses
     * @param end the upper bound of the range of udcses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of udcses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Udcs> findAll(int start, int end,
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

        List<Udcs> list = (List<Udcs>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_UDCS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_UDCS;

                if (pagination) {
                    sql = sql.concat(UdcsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Udcs>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Udcs>(list);
                } else {
                    list = (List<Udcs>) QueryUtil.list(q, getDialect(), start,
                            end);
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
     * Removes all the udcses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Udcs udcs : findAll()) {
            remove(udcs);
        }
    }

    /**
     * Returns the number of udcses.
     *
     * @return the number of udcses
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

                Query q = session.createQuery(_SQL_COUNT_UDCS);

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
     * Initializes the udcs persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.Udcs")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Udcs>> listenersList = new ArrayList<ModelListener<Udcs>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Udcs>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(UdcsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
