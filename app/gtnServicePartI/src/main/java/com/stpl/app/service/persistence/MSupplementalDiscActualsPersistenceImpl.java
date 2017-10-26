package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMSupplementalDiscActualsException;
import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.model.impl.MSupplementalDiscActualsImpl;
import com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPersistence;

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
 * The persistence implementation for the m supplemental disc actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscActualsPersistence
 * @see MSupplementalDiscActualsUtil
 * @generated
 */
public class MSupplementalDiscActualsPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscActuals>
    implements MSupplementalDiscActualsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MSupplementalDiscActualsUtil} to access the m supplemental disc actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscActualsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MSUPPLEMENTALDISCACTUALS = "SELECT mSupplementalDiscActuals FROM MSupplementalDiscActuals mSupplementalDiscActuals";
    private static final String _SQL_COUNT_MSUPPLEMENTALDISCACTUALS = "SELECT COUNT(mSupplementalDiscActuals) FROM MSupplementalDiscActuals mSupplementalDiscActuals";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscActuals.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscActuals exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MSupplementalDiscActualsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualSales", "periodSid", "actualRate",
                "actualProjectionSales", "actualProjectionRate",
                "projectionDetailsSid"
            });
    private static MSupplementalDiscActuals _nullMSupplementalDiscActuals = new MSupplementalDiscActualsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MSupplementalDiscActuals> toCacheModel() {
                return _nullMSupplementalDiscActualsCacheModel;
            }
        };

    private static CacheModel<MSupplementalDiscActuals> _nullMSupplementalDiscActualsCacheModel =
        new CacheModel<MSupplementalDiscActuals>() {
            @Override
            public MSupplementalDiscActuals toEntityModel() {
                return _nullMSupplementalDiscActuals;
            }
        };

    public MSupplementalDiscActualsPersistenceImpl() {
        setModelClass(MSupplementalDiscActuals.class);
    }

    /**
     * Caches the m supplemental disc actuals in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscActuals the m supplemental disc actuals
     */
    @Override
    public void cacheResult(MSupplementalDiscActuals mSupplementalDiscActuals) {
        EntityCacheUtil.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsImpl.class,
            mSupplementalDiscActuals.getPrimaryKey(), mSupplementalDiscActuals);

        mSupplementalDiscActuals.resetOriginalValues();
    }

    /**
     * Caches the m supplemental disc actualses in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscActualses the m supplemental disc actualses
     */
    @Override
    public void cacheResult(
        List<MSupplementalDiscActuals> mSupplementalDiscActualses) {
        for (MSupplementalDiscActuals mSupplementalDiscActuals : mSupplementalDiscActualses) {
            if (EntityCacheUtil.getResult(
                        MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscActualsImpl.class,
                        mSupplementalDiscActuals.getPrimaryKey()) == null) {
                cacheResult(mSupplementalDiscActuals);
            } else {
                mSupplementalDiscActuals.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m supplemental disc actualses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MSupplementalDiscActualsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MSupplementalDiscActualsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m supplemental disc actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MSupplementalDiscActuals mSupplementalDiscActuals) {
        EntityCacheUtil.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsImpl.class,
            mSupplementalDiscActuals.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<MSupplementalDiscActuals> mSupplementalDiscActualses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MSupplementalDiscActuals mSupplementalDiscActuals : mSupplementalDiscActualses) {
            EntityCacheUtil.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscActualsImpl.class,
                mSupplementalDiscActuals.getPrimaryKey());
        }
    }

    /**
     * Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
     *
     * @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
     * @return the new m supplemental disc actuals
     */
    @Override
    public MSupplementalDiscActuals create(
        MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
        MSupplementalDiscActuals mSupplementalDiscActuals = new MSupplementalDiscActualsImpl();

        mSupplementalDiscActuals.setNew(true);
        mSupplementalDiscActuals.setPrimaryKey(mSupplementalDiscActualsPK);

        return mSupplementalDiscActuals;
    }

    /**
     * Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals remove(
        MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws NoSuchMSupplementalDiscActualsException, SystemException {
        return remove((Serializable) mSupplementalDiscActualsPK);
    }

    /**
     * Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals remove(Serializable primaryKey)
        throws NoSuchMSupplementalDiscActualsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MSupplementalDiscActuals mSupplementalDiscActuals = (MSupplementalDiscActuals) session.get(MSupplementalDiscActualsImpl.class,
                    primaryKey);

            if (mSupplementalDiscActuals == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mSupplementalDiscActuals);
        } catch (NoSuchMSupplementalDiscActualsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MSupplementalDiscActuals removeImpl(
        MSupplementalDiscActuals mSupplementalDiscActuals)
        throws SystemException {
        mSupplementalDiscActuals = toUnwrappedModel(mSupplementalDiscActuals);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mSupplementalDiscActuals)) {
                mSupplementalDiscActuals = (MSupplementalDiscActuals) session.get(MSupplementalDiscActualsImpl.class,
                        mSupplementalDiscActuals.getPrimaryKeyObj());
            }

            if (mSupplementalDiscActuals != null) {
                session.delete(mSupplementalDiscActuals);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mSupplementalDiscActuals != null) {
            clearCache(mSupplementalDiscActuals);
        }

        return mSupplementalDiscActuals;
    }

    @Override
    public MSupplementalDiscActuals updateImpl(
        com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals)
        throws SystemException {
        mSupplementalDiscActuals = toUnwrappedModel(mSupplementalDiscActuals);

        boolean isNew = mSupplementalDiscActuals.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mSupplementalDiscActuals.isNew()) {
                session.save(mSupplementalDiscActuals);

                mSupplementalDiscActuals.setNew(false);
            } else {
                session.merge(mSupplementalDiscActuals);
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

        EntityCacheUtil.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscActualsImpl.class,
            mSupplementalDiscActuals.getPrimaryKey(), mSupplementalDiscActuals);

        return mSupplementalDiscActuals;
    }

    protected MSupplementalDiscActuals toUnwrappedModel(
        MSupplementalDiscActuals mSupplementalDiscActuals) {
        if (mSupplementalDiscActuals instanceof MSupplementalDiscActualsImpl) {
            return mSupplementalDiscActuals;
        }

        MSupplementalDiscActualsImpl mSupplementalDiscActualsImpl = new MSupplementalDiscActualsImpl();

        mSupplementalDiscActualsImpl.setNew(mSupplementalDiscActuals.isNew());
        mSupplementalDiscActualsImpl.setPrimaryKey(mSupplementalDiscActuals.getPrimaryKey());

        mSupplementalDiscActualsImpl.setActualSales(mSupplementalDiscActuals.getActualSales());
        mSupplementalDiscActualsImpl.setPeriodSid(mSupplementalDiscActuals.getPeriodSid());
        mSupplementalDiscActualsImpl.setActualRate(mSupplementalDiscActuals.getActualRate());
        mSupplementalDiscActualsImpl.setActualProjectionSales(mSupplementalDiscActuals.getActualProjectionSales());
        mSupplementalDiscActualsImpl.setActualProjectionRate(mSupplementalDiscActuals.getActualProjectionRate());
        mSupplementalDiscActualsImpl.setProjectionDetailsSid(mSupplementalDiscActuals.getProjectionDetailsSid());

        return mSupplementalDiscActualsImpl;
    }

    /**
     * Returns the m supplemental disc actuals with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals
     * @throws com.stpl.app.NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMSupplementalDiscActualsException, SystemException {
        MSupplementalDiscActuals mSupplementalDiscActuals = fetchByPrimaryKey(primaryKey);

        if (mSupplementalDiscActuals == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mSupplementalDiscActuals;
    }

    /**
     * Returns the m supplemental disc actuals with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscActualsException} if it could not be found.
     *
     * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals
     * @throws com.stpl.app.NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals findByPrimaryKey(
        MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws NoSuchMSupplementalDiscActualsException, SystemException {
        return findByPrimaryKey((Serializable) mSupplementalDiscActualsPK);
    }

    /**
     * Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MSupplementalDiscActuals mSupplementalDiscActuals = (MSupplementalDiscActuals) EntityCacheUtil.getResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscActualsImpl.class, primaryKey);

        if (mSupplementalDiscActuals == _nullMSupplementalDiscActuals) {
            return null;
        }

        if (mSupplementalDiscActuals == null) {
            Session session = null;

            try {
                session = openSession();

                mSupplementalDiscActuals = (MSupplementalDiscActuals) session.get(MSupplementalDiscActualsImpl.class,
                        primaryKey);

                if (mSupplementalDiscActuals != null) {
                    cacheResult(mSupplementalDiscActuals);
                } else {
                    EntityCacheUtil.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscActualsImpl.class, primaryKey,
                        _nullMSupplementalDiscActuals);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
                    MSupplementalDiscActualsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mSupplementalDiscActuals;
    }

    /**
     * Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
     * @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscActuals fetchByPrimaryKey(
        MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) mSupplementalDiscActualsPK);
    }

    /**
     * Returns all the m supplemental disc actualses.
     *
     * @return the m supplemental disc actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscActuals> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m supplemental disc actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc actualses
     * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
     * @return the range of m supplemental disc actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscActuals> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m supplemental disc actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc actualses
     * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m supplemental disc actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscActuals> findAll(int start, int end,
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

        List<MSupplementalDiscActuals> list = (List<MSupplementalDiscActuals>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MSUPPLEMENTALDISCACTUALS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MSUPPLEMENTALDISCACTUALS;

                if (pagination) {
                    sql = sql.concat(MSupplementalDiscActualsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MSupplementalDiscActuals>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MSupplementalDiscActuals>(list);
                } else {
                    list = (List<MSupplementalDiscActuals>) QueryUtil.list(q,
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
     * Removes all the m supplemental disc actualses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MSupplementalDiscActuals mSupplementalDiscActuals : findAll()) {
            remove(mSupplementalDiscActuals);
        }
    }

    /**
     * Returns the number of m supplemental disc actualses.
     *
     * @return the number of m supplemental disc actualses
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

                Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCACTUALS);

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
     * Initializes the m supplemental disc actuals persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MSupplementalDiscActuals")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MSupplementalDiscActuals>> listenersList = new ArrayList<ModelListener<MSupplementalDiscActuals>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MSupplementalDiscActuals>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MSupplementalDiscActualsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
