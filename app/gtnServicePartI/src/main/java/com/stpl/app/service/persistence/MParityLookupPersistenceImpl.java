package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMParityLookupException;
import com.stpl.app.model.MParityLookup;
import com.stpl.app.model.impl.MParityLookupImpl;
import com.stpl.app.model.impl.MParityLookupModelImpl;
import com.stpl.app.service.persistence.MParityLookupPersistence;

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
 * The persistence implementation for the m parity lookup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MParityLookupPersistence
 * @see MParityLookupUtil
 * @generated
 */
public class MParityLookupPersistenceImpl extends BasePersistenceImpl<MParityLookup>
    implements MParityLookupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MParityLookupUtil} to access the m parity lookup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MParityLookupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupModelImpl.FINDER_CACHE_ENABLED,
            MParityLookupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupModelImpl.FINDER_CACHE_ENABLED,
            MParityLookupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MPARITYLOOKUP = "SELECT mParityLookup FROM MParityLookup mParityLookup";
    private static final String _SQL_COUNT_MPARITYLOOKUP = "SELECT COUNT(mParityLookup) FROM MParityLookup mParityLookup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mParityLookup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MParityLookup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MParityLookupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "contractMasterSid", "marketType", "itemMasterSid",
                "mParityLookupSid", "projectionDetailsSid"
            });
    private static MParityLookup _nullMParityLookup = new MParityLookupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MParityLookup> toCacheModel() {
                return _nullMParityLookupCacheModel;
            }
        };

    private static CacheModel<MParityLookup> _nullMParityLookupCacheModel = new CacheModel<MParityLookup>() {
            @Override
            public MParityLookup toEntityModel() {
                return _nullMParityLookup;
            }
        };

    public MParityLookupPersistenceImpl() {
        setModelClass(MParityLookup.class);
    }

    /**
     * Caches the m parity lookup in the entity cache if it is enabled.
     *
     * @param mParityLookup the m parity lookup
     */
    @Override
    public void cacheResult(MParityLookup mParityLookup) {
        EntityCacheUtil.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupImpl.class, mParityLookup.getPrimaryKey(),
            mParityLookup);

        mParityLookup.resetOriginalValues();
    }

    /**
     * Caches the m parity lookups in the entity cache if it is enabled.
     *
     * @param mParityLookups the m parity lookups
     */
    @Override
    public void cacheResult(List<MParityLookup> mParityLookups) {
        for (MParityLookup mParityLookup : mParityLookups) {
            if (EntityCacheUtil.getResult(
                        MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
                        MParityLookupImpl.class, mParityLookup.getPrimaryKey()) == null) {
                cacheResult(mParityLookup);
            } else {
                mParityLookup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m parity lookups.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MParityLookupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MParityLookupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m parity lookup.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MParityLookup mParityLookup) {
        EntityCacheUtil.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupImpl.class, mParityLookup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MParityLookup> mParityLookups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MParityLookup mParityLookup : mParityLookups) {
            EntityCacheUtil.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
                MParityLookupImpl.class, mParityLookup.getPrimaryKey());
        }
    }

    /**
     * Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
     *
     * @param mParityLookupSid the primary key for the new m parity lookup
     * @return the new m parity lookup
     */
    @Override
    public MParityLookup create(int mParityLookupSid) {
        MParityLookup mParityLookup = new MParityLookupImpl();

        mParityLookup.setNew(true);
        mParityLookup.setPrimaryKey(mParityLookupSid);

        return mParityLookup;
    }

    /**
     * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param mParityLookupSid the primary key of the m parity lookup
     * @return the m parity lookup that was removed
     * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup remove(int mParityLookupSid)
        throws NoSuchMParityLookupException, SystemException {
        return remove((Serializable) mParityLookupSid);
    }

    /**
     * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m parity lookup
     * @return the m parity lookup that was removed
     * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup remove(Serializable primaryKey)
        throws NoSuchMParityLookupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MParityLookup mParityLookup = (MParityLookup) session.get(MParityLookupImpl.class,
                    primaryKey);

            if (mParityLookup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMParityLookupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mParityLookup);
        } catch (NoSuchMParityLookupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MParityLookup removeImpl(MParityLookup mParityLookup)
        throws SystemException {
        mParityLookup = toUnwrappedModel(mParityLookup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mParityLookup)) {
                mParityLookup = (MParityLookup) session.get(MParityLookupImpl.class,
                        mParityLookup.getPrimaryKeyObj());
            }

            if (mParityLookup != null) {
                session.delete(mParityLookup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mParityLookup != null) {
            clearCache(mParityLookup);
        }

        return mParityLookup;
    }

    @Override
    public MParityLookup updateImpl(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws SystemException {
        mParityLookup = toUnwrappedModel(mParityLookup);

        boolean isNew = mParityLookup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mParityLookup.isNew()) {
                session.save(mParityLookup);

                mParityLookup.setNew(false);
            } else {
                session.merge(mParityLookup);
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

        EntityCacheUtil.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
            MParityLookupImpl.class, mParityLookup.getPrimaryKey(),
            mParityLookup);

        return mParityLookup;
    }

    protected MParityLookup toUnwrappedModel(MParityLookup mParityLookup) {
        if (mParityLookup instanceof MParityLookupImpl) {
            return mParityLookup;
        }

        MParityLookupImpl mParityLookupImpl = new MParityLookupImpl();

        mParityLookupImpl.setNew(mParityLookup.isNew());
        mParityLookupImpl.setPrimaryKey(mParityLookup.getPrimaryKey());

        mParityLookupImpl.setContractMasterSid(mParityLookup.getContractMasterSid());
        mParityLookupImpl.setMarketType(mParityLookup.getMarketType());
        mParityLookupImpl.setItemMasterSid(mParityLookup.getItemMasterSid());
        mParityLookupImpl.setMParityLookupSid(mParityLookup.getMParityLookupSid());
        mParityLookupImpl.setProjectionDetailsSid(mParityLookup.getProjectionDetailsSid());

        return mParityLookupImpl;
    }

    /**
     * Returns the m parity lookup with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m parity lookup
     * @return the m parity lookup
     * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMParityLookupException, SystemException {
        MParityLookup mParityLookup = fetchByPrimaryKey(primaryKey);

        if (mParityLookup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMParityLookupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mParityLookup;
    }

    /**
     * Returns the m parity lookup with the primary key or throws a {@link com.stpl.app.NoSuchMParityLookupException} if it could not be found.
     *
     * @param mParityLookupSid the primary key of the m parity lookup
     * @return the m parity lookup
     * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup findByPrimaryKey(int mParityLookupSid)
        throws NoSuchMParityLookupException, SystemException {
        return findByPrimaryKey((Serializable) mParityLookupSid);
    }

    /**
     * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m parity lookup
     * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MParityLookup mParityLookup = (MParityLookup) EntityCacheUtil.getResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
                MParityLookupImpl.class, primaryKey);

        if (mParityLookup == _nullMParityLookup) {
            return null;
        }

        if (mParityLookup == null) {
            Session session = null;

            try {
                session = openSession();

                mParityLookup = (MParityLookup) session.get(MParityLookupImpl.class,
                        primaryKey);

                if (mParityLookup != null) {
                    cacheResult(mParityLookup);
                } else {
                    EntityCacheUtil.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
                        MParityLookupImpl.class, primaryKey, _nullMParityLookup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
                    MParityLookupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mParityLookup;
    }

    /**
     * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param mParityLookupSid the primary key of the m parity lookup
     * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MParityLookup fetchByPrimaryKey(int mParityLookupSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) mParityLookupSid);
    }

    /**
     * Returns all the m parity lookups.
     *
     * @return the m parity lookups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MParityLookup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m parity lookups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m parity lookups
     * @param end the upper bound of the range of m parity lookups (not inclusive)
     * @return the range of m parity lookups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MParityLookup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m parity lookups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m parity lookups
     * @param end the upper bound of the range of m parity lookups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m parity lookups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MParityLookup> findAll(int start, int end,
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

        List<MParityLookup> list = (List<MParityLookup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MPARITYLOOKUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MPARITYLOOKUP;

                if (pagination) {
                    sql = sql.concat(MParityLookupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MParityLookup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MParityLookup>(list);
                } else {
                    list = (List<MParityLookup>) QueryUtil.list(q,
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
     * Removes all the m parity lookups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MParityLookup mParityLookup : findAll()) {
            remove(mParityLookup);
        }
    }

    /**
     * Returns the number of m parity lookups.
     *
     * @return the number of m parity lookups
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

                Query q = session.createQuery(_SQL_COUNT_MPARITYLOOKUP);

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
     * Initializes the m parity lookup persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MParityLookup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MParityLookup>> listenersList = new ArrayList<ModelListener<MParityLookup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MParityLookup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MParityLookupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
