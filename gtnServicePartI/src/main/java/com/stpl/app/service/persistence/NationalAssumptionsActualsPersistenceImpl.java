package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNationalAssumptionsActualsException;
import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.model.impl.NationalAssumptionsActualsImpl;
import com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPersistence;

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
 * The persistence implementation for the national assumptions actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsActualsPersistence
 * @see NationalAssumptionsActualsUtil
 * @generated
 */
public class NationalAssumptionsActualsPersistenceImpl
    extends BasePersistenceImpl<NationalAssumptionsActuals>
    implements NationalAssumptionsActualsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NationalAssumptionsActualsUtil} to access the national assumptions actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsActualsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_NATIONALASSUMPTIONSACTUALS = "SELECT nationalAssumptionsActuals FROM NationalAssumptionsActuals nationalAssumptionsActuals";
    private static final String _SQL_COUNT_NATIONALASSUMPTIONSACTUALS = "SELECT COUNT(nationalAssumptionsActuals) FROM NationalAssumptionsActuals nationalAssumptionsActuals";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptionsActuals.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptionsActuals exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NationalAssumptionsActualsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodSid", "itemMasterSid", "priceType", "actualPrice"
            });
    private static NationalAssumptionsActuals _nullNationalAssumptionsActuals = new NationalAssumptionsActualsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NationalAssumptionsActuals> toCacheModel() {
                return _nullNationalAssumptionsActualsCacheModel;
            }
        };

    private static CacheModel<NationalAssumptionsActuals> _nullNationalAssumptionsActualsCacheModel =
        new CacheModel<NationalAssumptionsActuals>() {
            @Override
            public NationalAssumptionsActuals toEntityModel() {
                return _nullNationalAssumptionsActuals;
            }
        };

    public NationalAssumptionsActualsPersistenceImpl() {
        setModelClass(NationalAssumptionsActuals.class);
    }

    /**
     * Caches the national assumptions actuals in the entity cache if it is enabled.
     *
     * @param nationalAssumptionsActuals the national assumptions actuals
     */
    @Override
    public void cacheResult(
        NationalAssumptionsActuals nationalAssumptionsActuals) {
        EntityCacheUtil.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsImpl.class,
            nationalAssumptionsActuals.getPrimaryKey(),
            nationalAssumptionsActuals);

        nationalAssumptionsActuals.resetOriginalValues();
    }

    /**
     * Caches the national assumptions actualses in the entity cache if it is enabled.
     *
     * @param nationalAssumptionsActualses the national assumptions actualses
     */
    @Override
    public void cacheResult(
        List<NationalAssumptionsActuals> nationalAssumptionsActualses) {
        for (NationalAssumptionsActuals nationalAssumptionsActuals : nationalAssumptionsActualses) {
            if (EntityCacheUtil.getResult(
                        NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsActualsImpl.class,
                        nationalAssumptionsActuals.getPrimaryKey()) == null) {
                cacheResult(nationalAssumptionsActuals);
            } else {
                nationalAssumptionsActuals.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all national assumptions actualses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NationalAssumptionsActualsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NationalAssumptionsActualsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the national assumptions actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        NationalAssumptionsActuals nationalAssumptionsActuals) {
        EntityCacheUtil.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsImpl.class,
            nationalAssumptionsActuals.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<NationalAssumptionsActuals> nationalAssumptionsActualses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NationalAssumptionsActuals nationalAssumptionsActuals : nationalAssumptionsActualses) {
            EntityCacheUtil.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsActualsImpl.class,
                nationalAssumptionsActuals.getPrimaryKey());
        }
    }

    /**
     * Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
     *
     * @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
     * @return the new national assumptions actuals
     */
    @Override
    public NationalAssumptionsActuals create(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
        NationalAssumptionsActuals nationalAssumptionsActuals = new NationalAssumptionsActualsImpl();

        nationalAssumptionsActuals.setNew(true);
        nationalAssumptionsActuals.setPrimaryKey(nationalAssumptionsActualsPK);

        return nationalAssumptionsActuals;
    }

    /**
     * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
     * @return the national assumptions actuals that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals remove(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws NoSuchNationalAssumptionsActualsException, SystemException {
        return remove((Serializable) nationalAssumptionsActualsPK);
    }

    /**
     * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the national assumptions actuals
     * @return the national assumptions actuals that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals remove(Serializable primaryKey)
        throws NoSuchNationalAssumptionsActualsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NationalAssumptionsActuals nationalAssumptionsActuals = (NationalAssumptionsActuals) session.get(NationalAssumptionsActualsImpl.class,
                    primaryKey);

            if (nationalAssumptionsActuals == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNationalAssumptionsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nationalAssumptionsActuals);
        } catch (NoSuchNationalAssumptionsActualsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NationalAssumptionsActuals removeImpl(
        NationalAssumptionsActuals nationalAssumptionsActuals)
        throws SystemException {
        nationalAssumptionsActuals = toUnwrappedModel(nationalAssumptionsActuals);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nationalAssumptionsActuals)) {
                nationalAssumptionsActuals = (NationalAssumptionsActuals) session.get(NationalAssumptionsActualsImpl.class,
                        nationalAssumptionsActuals.getPrimaryKeyObj());
            }

            if (nationalAssumptionsActuals != null) {
                session.delete(nationalAssumptionsActuals);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nationalAssumptionsActuals != null) {
            clearCache(nationalAssumptionsActuals);
        }

        return nationalAssumptionsActuals;
    }

    @Override
    public NationalAssumptionsActuals updateImpl(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws SystemException {
        nationalAssumptionsActuals = toUnwrappedModel(nationalAssumptionsActuals);

        boolean isNew = nationalAssumptionsActuals.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nationalAssumptionsActuals.isNew()) {
                session.save(nationalAssumptionsActuals);

                nationalAssumptionsActuals.setNew(false);
            } else {
                session.merge(nationalAssumptionsActuals);
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

        EntityCacheUtil.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsActualsImpl.class,
            nationalAssumptionsActuals.getPrimaryKey(),
            nationalAssumptionsActuals);

        return nationalAssumptionsActuals;
    }

    protected NationalAssumptionsActuals toUnwrappedModel(
        NationalAssumptionsActuals nationalAssumptionsActuals) {
        if (nationalAssumptionsActuals instanceof NationalAssumptionsActualsImpl) {
            return nationalAssumptionsActuals;
        }

        NationalAssumptionsActualsImpl nationalAssumptionsActualsImpl = new NationalAssumptionsActualsImpl();

        nationalAssumptionsActualsImpl.setNew(nationalAssumptionsActuals.isNew());
        nationalAssumptionsActualsImpl.setPrimaryKey(nationalAssumptionsActuals.getPrimaryKey());

        nationalAssumptionsActualsImpl.setPeriodSid(nationalAssumptionsActuals.getPeriodSid());
        nationalAssumptionsActualsImpl.setItemMasterSid(nationalAssumptionsActuals.getItemMasterSid());
        nationalAssumptionsActualsImpl.setPriceType(nationalAssumptionsActuals.getPriceType());
        nationalAssumptionsActualsImpl.setActualPrice(nationalAssumptionsActuals.getActualPrice());

        return nationalAssumptionsActualsImpl;
    }

    /**
     * Returns the national assumptions actuals with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions actuals
     * @return the national assumptions actuals
     * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNationalAssumptionsActualsException, SystemException {
        NationalAssumptionsActuals nationalAssumptionsActuals = fetchByPrimaryKey(primaryKey);

        if (nationalAssumptionsActuals == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNationalAssumptionsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nationalAssumptionsActuals;
    }

    /**
     * Returns the national assumptions actuals with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsActualsException} if it could not be found.
     *
     * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
     * @return the national assumptions actuals
     * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals findByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws NoSuchNationalAssumptionsActualsException, SystemException {
        return findByPrimaryKey((Serializable) nationalAssumptionsActualsPK);
    }

    /**
     * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions actuals
     * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NationalAssumptionsActuals nationalAssumptionsActuals = (NationalAssumptionsActuals) EntityCacheUtil.getResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsActualsImpl.class, primaryKey);

        if (nationalAssumptionsActuals == _nullNationalAssumptionsActuals) {
            return null;
        }

        if (nationalAssumptionsActuals == null) {
            Session session = null;

            try {
                session = openSession();

                nationalAssumptionsActuals = (NationalAssumptionsActuals) session.get(NationalAssumptionsActualsImpl.class,
                        primaryKey);

                if (nationalAssumptionsActuals != null) {
                    cacheResult(nationalAssumptionsActuals);
                } else {
                    EntityCacheUtil.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsActualsImpl.class, primaryKey,
                        _nullNationalAssumptionsActuals);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
                    NationalAssumptionsActualsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nationalAssumptionsActuals;
    }

    /**
     * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
     * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsActuals fetchByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) nationalAssumptionsActualsPK);
    }

    /**
     * Returns all the national assumptions actualses.
     *
     * @return the national assumptions actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsActuals> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the national assumptions actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptions actualses
     * @param end the upper bound of the range of national assumptions actualses (not inclusive)
     * @return the range of national assumptions actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsActuals> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the national assumptions actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptions actualses
     * @param end the upper bound of the range of national assumptions actualses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of national assumptions actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsActuals> findAll(int start, int end,
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

        List<NationalAssumptionsActuals> list = (List<NationalAssumptionsActuals>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NATIONALASSUMPTIONSACTUALS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NATIONALASSUMPTIONSACTUALS;

                if (pagination) {
                    sql = sql.concat(NationalAssumptionsActualsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NationalAssumptionsActuals>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NationalAssumptionsActuals>(list);
                } else {
                    list = (List<NationalAssumptionsActuals>) QueryUtil.list(q,
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
     * Removes all the national assumptions actualses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NationalAssumptionsActuals nationalAssumptionsActuals : findAll()) {
            remove(nationalAssumptionsActuals);
        }
    }

    /**
     * Returns the number of national assumptions actualses.
     *
     * @return the number of national assumptions actualses
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

                Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONSACTUALS);

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
     * Initializes the national assumptions actuals persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NationalAssumptionsActuals")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NationalAssumptionsActuals>> listenersList = new ArrayList<ModelListener<NationalAssumptionsActuals>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NationalAssumptionsActuals>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NationalAssumptionsActualsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
