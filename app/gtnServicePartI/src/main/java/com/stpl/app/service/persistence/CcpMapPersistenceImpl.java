package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCcpMapException;
import com.stpl.app.model.CcpMap;
import com.stpl.app.model.impl.CcpMapImpl;
import com.stpl.app.model.impl.CcpMapModelImpl;
import com.stpl.app.service.persistence.CcpMapPersistence;

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
 * The persistence implementation for the ccp map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpMapPersistence
 * @see CcpMapUtil
 * @generated
 */
public class CcpMapPersistenceImpl extends BasePersistenceImpl<CcpMap>
    implements CcpMapPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CcpMapUtil} to access the ccp map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CcpMapImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapModelImpl.FINDER_CACHE_ENABLED, CcpMapImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapModelImpl.FINDER_CACHE_ENABLED, CcpMapImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CCPMAP = "SELECT ccpMap FROM CcpMap ccpMap";
    private static final String _SQL_COUNT_CCPMAP = "SELECT COUNT(ccpMap) FROM CcpMap ccpMap";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ccpMap.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CcpMap exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CcpMapPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ccpDetailsSid", "relationshipLevelSid", "ccpMapSid"
            });
    private static CcpMap _nullCcpMap = new CcpMapImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CcpMap> toCacheModel() {
                return _nullCcpMapCacheModel;
            }
        };

    private static CacheModel<CcpMap> _nullCcpMapCacheModel = new CacheModel<CcpMap>() {
            @Override
            public CcpMap toEntityModel() {
                return _nullCcpMap;
            }
        };

    public CcpMapPersistenceImpl() {
        setModelClass(CcpMap.class);
    }

    /**
     * Caches the ccp map in the entity cache if it is enabled.
     *
     * @param ccpMap the ccp map
     */
    @Override
    public void cacheResult(CcpMap ccpMap) {
        EntityCacheUtil.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapImpl.class, ccpMap.getPrimaryKey(), ccpMap);

        ccpMap.resetOriginalValues();
    }

    /**
     * Caches the ccp maps in the entity cache if it is enabled.
     *
     * @param ccpMaps the ccp maps
     */
    @Override
    public void cacheResult(List<CcpMap> ccpMaps) {
        for (CcpMap ccpMap : ccpMaps) {
            if (EntityCacheUtil.getResult(
                        CcpMapModelImpl.ENTITY_CACHE_ENABLED, CcpMapImpl.class,
                        ccpMap.getPrimaryKey()) == null) {
                cacheResult(ccpMap);
            } else {
                ccpMap.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ccp maps.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CcpMapImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CcpMapImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ccp map.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CcpMap ccpMap) {
        EntityCacheUtil.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapImpl.class, ccpMap.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CcpMap> ccpMaps) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CcpMap ccpMap : ccpMaps) {
            EntityCacheUtil.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
                CcpMapImpl.class, ccpMap.getPrimaryKey());
        }
    }

    /**
     * Creates a new ccp map with the primary key. Does not add the ccp map to the database.
     *
     * @param ccpMapSid the primary key for the new ccp map
     * @return the new ccp map
     */
    @Override
    public CcpMap create(int ccpMapSid) {
        CcpMap ccpMap = new CcpMapImpl();

        ccpMap.setNew(true);
        ccpMap.setPrimaryKey(ccpMapSid);

        return ccpMap;
    }

    /**
     * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ccpMapSid the primary key of the ccp map
     * @return the ccp map that was removed
     * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap remove(int ccpMapSid)
        throws NoSuchCcpMapException, SystemException {
        return remove((Serializable) ccpMapSid);
    }

    /**
     * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ccp map
     * @return the ccp map that was removed
     * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap remove(Serializable primaryKey)
        throws NoSuchCcpMapException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CcpMap ccpMap = (CcpMap) session.get(CcpMapImpl.class, primaryKey);

            if (ccpMap == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCcpMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ccpMap);
        } catch (NoSuchCcpMapException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CcpMap removeImpl(CcpMap ccpMap) throws SystemException {
        ccpMap = toUnwrappedModel(ccpMap);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ccpMap)) {
                ccpMap = (CcpMap) session.get(CcpMapImpl.class,
                        ccpMap.getPrimaryKeyObj());
            }

            if (ccpMap != null) {
                session.delete(ccpMap);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ccpMap != null) {
            clearCache(ccpMap);
        }

        return ccpMap;
    }

    @Override
    public CcpMap updateImpl(com.stpl.app.model.CcpMap ccpMap)
        throws SystemException {
        ccpMap = toUnwrappedModel(ccpMap);

        boolean isNew = ccpMap.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ccpMap.isNew()) {
                session.save(ccpMap);

                ccpMap.setNew(false);
            } else {
                session.merge(ccpMap);
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

        EntityCacheUtil.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
            CcpMapImpl.class, ccpMap.getPrimaryKey(), ccpMap);

        return ccpMap;
    }

    protected CcpMap toUnwrappedModel(CcpMap ccpMap) {
        if (ccpMap instanceof CcpMapImpl) {
            return ccpMap;
        }

        CcpMapImpl ccpMapImpl = new CcpMapImpl();

        ccpMapImpl.setNew(ccpMap.isNew());
        ccpMapImpl.setPrimaryKey(ccpMap.getPrimaryKey());

        ccpMapImpl.setCcpDetailsSid(ccpMap.getCcpDetailsSid());
        ccpMapImpl.setRelationshipLevelSid(ccpMap.getRelationshipLevelSid());
        ccpMapImpl.setCcpMapSid(ccpMap.getCcpMapSid());

        return ccpMapImpl;
    }

    /**
     * Returns the ccp map with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ccp map
     * @return the ccp map
     * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCcpMapException, SystemException {
        CcpMap ccpMap = fetchByPrimaryKey(primaryKey);

        if (ccpMap == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCcpMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ccpMap;
    }

    /**
     * Returns the ccp map with the primary key or throws a {@link com.stpl.app.NoSuchCcpMapException} if it could not be found.
     *
     * @param ccpMapSid the primary key of the ccp map
     * @return the ccp map
     * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap findByPrimaryKey(int ccpMapSid)
        throws NoSuchCcpMapException, SystemException {
        return findByPrimaryKey((Serializable) ccpMapSid);
    }

    /**
     * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ccp map
     * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CcpMap ccpMap = (CcpMap) EntityCacheUtil.getResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
                CcpMapImpl.class, primaryKey);

        if (ccpMap == _nullCcpMap) {
            return null;
        }

        if (ccpMap == null) {
            Session session = null;

            try {
                session = openSession();

                ccpMap = (CcpMap) session.get(CcpMapImpl.class, primaryKey);

                if (ccpMap != null) {
                    cacheResult(ccpMap);
                } else {
                    EntityCacheUtil.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
                        CcpMapImpl.class, primaryKey, _nullCcpMap);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
                    CcpMapImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ccpMap;
    }

    /**
     * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ccpMapSid the primary key of the ccp map
     * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpMap fetchByPrimaryKey(int ccpMapSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ccpMapSid);
    }

    /**
     * Returns all the ccp maps.
     *
     * @return the ccp maps
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpMap> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ccp maps.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ccp maps
     * @param end the upper bound of the range of ccp maps (not inclusive)
     * @return the range of ccp maps
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpMap> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ccp maps.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ccp maps
     * @param end the upper bound of the range of ccp maps (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ccp maps
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpMap> findAll(int start, int end,
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

        List<CcpMap> list = (List<CcpMap>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CCPMAP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CCPMAP;

                if (pagination) {
                    sql = sql.concat(CcpMapModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CcpMap>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CcpMap>(list);
                } else {
                    list = (List<CcpMap>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ccp maps from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CcpMap ccpMap : findAll()) {
            remove(ccpMap);
        }
    }

    /**
     * Returns the number of ccp maps.
     *
     * @return the number of ccp maps
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

                Query q = session.createQuery(_SQL_COUNT_CCPMAP);

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
     * Initializes the ccp map persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CcpMap")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CcpMap>> listenersList = new ArrayList<ModelListener<CcpMap>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CcpMap>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CcpMapImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
