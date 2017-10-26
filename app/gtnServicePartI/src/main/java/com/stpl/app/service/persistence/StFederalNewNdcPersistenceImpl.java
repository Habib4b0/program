package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStFederalNewNdcException;
import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.model.impl.StFederalNewNdcImpl;
import com.stpl.app.model.impl.StFederalNewNdcModelImpl;
import com.stpl.app.service.persistence.StFederalNewNdcPersistence;

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
 * The persistence implementation for the st federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StFederalNewNdcPersistence
 * @see StFederalNewNdcUtil
 * @generated
 */
public class StFederalNewNdcPersistenceImpl extends BasePersistenceImpl<StFederalNewNdc>
    implements StFederalNewNdcPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StFederalNewNdcUtil} to access the st federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StFederalNewNdcImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
            StFederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
            StFederalNewNdcImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STFEDERALNEWNDC = "SELECT stFederalNewNdc FROM StFederalNewNdc stFederalNewNdc";
    private static final String _SQL_COUNT_STFEDERALNEWNDC = "SELECT COUNT(stFederalNewNdc) FROM StFederalNewNdc stFederalNewNdc";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stFederalNewNdc.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StFederalNewNdc exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StFederalNewNdcPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "fss", "userId", "lastModifiedDate", "itemMasterSid", "wacPrice",
                "sessionId", "nonFamp"
            });
    private static StFederalNewNdc _nullStFederalNewNdc = new StFederalNewNdcImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StFederalNewNdc> toCacheModel() {
                return _nullStFederalNewNdcCacheModel;
            }
        };

    private static CacheModel<StFederalNewNdc> _nullStFederalNewNdcCacheModel = new CacheModel<StFederalNewNdc>() {
            @Override
            public StFederalNewNdc toEntityModel() {
                return _nullStFederalNewNdc;
            }
        };

    public StFederalNewNdcPersistenceImpl() {
        setModelClass(StFederalNewNdc.class);
    }

    /**
     * Caches the st federal new ndc in the entity cache if it is enabled.
     *
     * @param stFederalNewNdc the st federal new ndc
     */
    @Override
    public void cacheResult(StFederalNewNdc stFederalNewNdc) {
        EntityCacheUtil.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey(),
            stFederalNewNdc);

        stFederalNewNdc.resetOriginalValues();
    }

    /**
     * Caches the st federal new ndcs in the entity cache if it is enabled.
     *
     * @param stFederalNewNdcs the st federal new ndcs
     */
    @Override
    public void cacheResult(List<StFederalNewNdc> stFederalNewNdcs) {
        for (StFederalNewNdc stFederalNewNdc : stFederalNewNdcs) {
            if (EntityCacheUtil.getResult(
                        StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StFederalNewNdcImpl.class,
                        stFederalNewNdc.getPrimaryKey()) == null) {
                cacheResult(stFederalNewNdc);
            } else {
                stFederalNewNdc.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st federal new ndcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StFederalNewNdcImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StFederalNewNdcImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st federal new ndc.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StFederalNewNdc stFederalNewNdc) {
        EntityCacheUtil.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StFederalNewNdc> stFederalNewNdcs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StFederalNewNdc stFederalNewNdc : stFederalNewNdcs) {
            EntityCacheUtil.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey());
        }
    }

    /**
     * Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
     *
     * @param stFederalNewNdcPK the primary key for the new st federal new ndc
     * @return the new st federal new ndc
     */
    @Override
    public StFederalNewNdc create(StFederalNewNdcPK stFederalNewNdcPK) {
        StFederalNewNdc stFederalNewNdc = new StFederalNewNdcImpl();

        stFederalNewNdc.setNew(true);
        stFederalNewNdc.setPrimaryKey(stFederalNewNdcPK);

        return stFederalNewNdc;
    }

    /**
     * Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stFederalNewNdcPK the primary key of the st federal new ndc
     * @return the st federal new ndc that was removed
     * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc remove(StFederalNewNdcPK stFederalNewNdcPK)
        throws NoSuchStFederalNewNdcException, SystemException {
        return remove((Serializable) stFederalNewNdcPK);
    }

    /**
     * Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st federal new ndc
     * @return the st federal new ndc that was removed
     * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc remove(Serializable primaryKey)
        throws NoSuchStFederalNewNdcException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StFederalNewNdc stFederalNewNdc = (StFederalNewNdc) session.get(StFederalNewNdcImpl.class,
                    primaryKey);

            if (stFederalNewNdc == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stFederalNewNdc);
        } catch (NoSuchStFederalNewNdcException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StFederalNewNdc removeImpl(StFederalNewNdc stFederalNewNdc)
        throws SystemException {
        stFederalNewNdc = toUnwrappedModel(stFederalNewNdc);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stFederalNewNdc)) {
                stFederalNewNdc = (StFederalNewNdc) session.get(StFederalNewNdcImpl.class,
                        stFederalNewNdc.getPrimaryKeyObj());
            }

            if (stFederalNewNdc != null) {
                session.delete(stFederalNewNdc);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stFederalNewNdc != null) {
            clearCache(stFederalNewNdc);
        }

        return stFederalNewNdc;
    }

    @Override
    public StFederalNewNdc updateImpl(
        com.stpl.app.model.StFederalNewNdc stFederalNewNdc)
        throws SystemException {
        stFederalNewNdc = toUnwrappedModel(stFederalNewNdc);

        boolean isNew = stFederalNewNdc.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stFederalNewNdc.isNew()) {
                session.save(stFederalNewNdc);

                stFederalNewNdc.setNew(false);
            } else {
                session.merge(stFederalNewNdc);
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

        EntityCacheUtil.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey(),
            stFederalNewNdc);

        return stFederalNewNdc;
    }

    protected StFederalNewNdc toUnwrappedModel(StFederalNewNdc stFederalNewNdc) {
        if (stFederalNewNdc instanceof StFederalNewNdcImpl) {
            return stFederalNewNdc;
        }

        StFederalNewNdcImpl stFederalNewNdcImpl = new StFederalNewNdcImpl();

        stFederalNewNdcImpl.setNew(stFederalNewNdc.isNew());
        stFederalNewNdcImpl.setPrimaryKey(stFederalNewNdc.getPrimaryKey());

        stFederalNewNdcImpl.setFss(stFederalNewNdc.getFss());
        stFederalNewNdcImpl.setUserId(stFederalNewNdc.getUserId());
        stFederalNewNdcImpl.setLastModifiedDate(stFederalNewNdc.getLastModifiedDate());
        stFederalNewNdcImpl.setItemMasterSid(stFederalNewNdc.getItemMasterSid());
        stFederalNewNdcImpl.setWacPrice(stFederalNewNdc.getWacPrice());
        stFederalNewNdcImpl.setSessionId(stFederalNewNdc.getSessionId());
        stFederalNewNdcImpl.setNonFamp(stFederalNewNdc.getNonFamp());

        return stFederalNewNdcImpl;
    }

    /**
     * Returns the st federal new ndc with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st federal new ndc
     * @return the st federal new ndc
     * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStFederalNewNdcException, SystemException {
        StFederalNewNdc stFederalNewNdc = fetchByPrimaryKey(primaryKey);

        if (stFederalNewNdc == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stFederalNewNdc;
    }

    /**
     * Returns the st federal new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStFederalNewNdcException} if it could not be found.
     *
     * @param stFederalNewNdcPK the primary key of the st federal new ndc
     * @return the st federal new ndc
     * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc findByPrimaryKey(StFederalNewNdcPK stFederalNewNdcPK)
        throws NoSuchStFederalNewNdcException, SystemException {
        return findByPrimaryKey((Serializable) stFederalNewNdcPK);
    }

    /**
     * Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st federal new ndc
     * @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StFederalNewNdc stFederalNewNdc = (StFederalNewNdc) EntityCacheUtil.getResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                StFederalNewNdcImpl.class, primaryKey);

        if (stFederalNewNdc == _nullStFederalNewNdc) {
            return null;
        }

        if (stFederalNewNdc == null) {
            Session session = null;

            try {
                session = openSession();

                stFederalNewNdc = (StFederalNewNdc) session.get(StFederalNewNdcImpl.class,
                        primaryKey);

                if (stFederalNewNdc != null) {
                    cacheResult(stFederalNewNdc);
                } else {
                    EntityCacheUtil.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        StFederalNewNdcImpl.class, primaryKey,
                        _nullStFederalNewNdc);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                    StFederalNewNdcImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stFederalNewNdc;
    }

    /**
     * Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stFederalNewNdcPK the primary key of the st federal new ndc
     * @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StFederalNewNdc fetchByPrimaryKey(
        StFederalNewNdcPK stFederalNewNdcPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stFederalNewNdcPK);
    }

    /**
     * Returns all the st federal new ndcs.
     *
     * @return the st federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StFederalNewNdc> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st federal new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st federal new ndcs
     * @param end the upper bound of the range of st federal new ndcs (not inclusive)
     * @return the range of st federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StFederalNewNdc> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st federal new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st federal new ndcs
     * @param end the upper bound of the range of st federal new ndcs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st federal new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StFederalNewNdc> findAll(int start, int end,
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

        List<StFederalNewNdc> list = (List<StFederalNewNdc>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STFEDERALNEWNDC);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STFEDERALNEWNDC;

                if (pagination) {
                    sql = sql.concat(StFederalNewNdcModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StFederalNewNdc>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StFederalNewNdc>(list);
                } else {
                    list = (List<StFederalNewNdc>) QueryUtil.list(q,
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
     * Removes all the st federal new ndcs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StFederalNewNdc stFederalNewNdc : findAll()) {
            remove(stFederalNewNdc);
        }
    }

    /**
     * Returns the number of st federal new ndcs.
     *
     * @return the number of st federal new ndcs
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

                Query q = session.createQuery(_SQL_COUNT_STFEDERALNEWNDC);

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
     * Initializes the st federal new ndc persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StFederalNewNdc")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StFederalNewNdc>> listenersList = new ArrayList<ModelListener<StFederalNewNdc>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StFederalNewNdc>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StFederalNewNdcImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
