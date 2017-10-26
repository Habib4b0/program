package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMSupplementalDiscMasterException;
import com.stpl.app.model.MSupplementalDiscMaster;
import com.stpl.app.model.impl.MSupplementalDiscMasterImpl;
import com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscMasterPersistence;

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
 * The persistence implementation for the m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscMasterPersistence
 * @see MSupplementalDiscMasterUtil
 * @generated
 */
public class MSupplementalDiscMasterPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscMaster>
    implements MSupplementalDiscMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MSupplementalDiscMasterUtil} to access the m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
            MSupplementalDiscMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MSUPPLEMENTALDISCMASTER = "SELECT mSupplementalDiscMaster FROM MSupplementalDiscMaster mSupplementalDiscMaster";
    private static final String _SQL_COUNT_MSUPPLEMENTALDISCMASTER = "SELECT COUNT(mSupplementalDiscMaster) FROM MSupplementalDiscMaster mSupplementalDiscMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MSupplementalDiscMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualDiscountRate2", "actualDiscountRate1", "marketType",
                "actualMethodology", "actualContractPrice",
                "projectionDetailsSid", "actualDiscount", "checkRecord",
                "contractEndDate"
            });
    private static MSupplementalDiscMaster _nullMSupplementalDiscMaster = new MSupplementalDiscMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MSupplementalDiscMaster> toCacheModel() {
                return _nullMSupplementalDiscMasterCacheModel;
            }
        };

    private static CacheModel<MSupplementalDiscMaster> _nullMSupplementalDiscMasterCacheModel =
        new CacheModel<MSupplementalDiscMaster>() {
            @Override
            public MSupplementalDiscMaster toEntityModel() {
                return _nullMSupplementalDiscMaster;
            }
        };

    public MSupplementalDiscMasterPersistenceImpl() {
        setModelClass(MSupplementalDiscMaster.class);
    }

    /**
     * Caches the m supplemental disc master in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscMaster the m supplemental disc master
     */
    @Override
    public void cacheResult(MSupplementalDiscMaster mSupplementalDiscMaster) {
        EntityCacheUtil.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterImpl.class,
            mSupplementalDiscMaster.getPrimaryKey(), mSupplementalDiscMaster);

        mSupplementalDiscMaster.resetOriginalValues();
    }

    /**
     * Caches the m supplemental disc masters in the entity cache if it is enabled.
     *
     * @param mSupplementalDiscMasters the m supplemental disc masters
     */
    @Override
    public void cacheResult(
        List<MSupplementalDiscMaster> mSupplementalDiscMasters) {
        for (MSupplementalDiscMaster mSupplementalDiscMaster : mSupplementalDiscMasters) {
            if (EntityCacheUtil.getResult(
                        MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscMasterImpl.class,
                        mSupplementalDiscMaster.getPrimaryKey()) == null) {
                cacheResult(mSupplementalDiscMaster);
            } else {
                mSupplementalDiscMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m supplemental disc masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MSupplementalDiscMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MSupplementalDiscMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m supplemental disc master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MSupplementalDiscMaster mSupplementalDiscMaster) {
        EntityCacheUtil.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterImpl.class,
            mSupplementalDiscMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<MSupplementalDiscMaster> mSupplementalDiscMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MSupplementalDiscMaster mSupplementalDiscMaster : mSupplementalDiscMasters) {
            EntityCacheUtil.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscMasterImpl.class,
                mSupplementalDiscMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new m supplemental disc master with the primary key. Does not add the m supplemental disc master to the database.
     *
     * @param projectionDetailsSid the primary key for the new m supplemental disc master
     * @return the new m supplemental disc master
     */
    @Override
    public MSupplementalDiscMaster create(int projectionDetailsSid) {
        MSupplementalDiscMaster mSupplementalDiscMaster = new MSupplementalDiscMasterImpl();

        mSupplementalDiscMaster.setNew(true);
        mSupplementalDiscMaster.setPrimaryKey(projectionDetailsSid);

        return mSupplementalDiscMaster;
    }

    /**
     * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc master
     * @return the m supplemental disc master that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster remove(int projectionDetailsSid)
        throws NoSuchMSupplementalDiscMasterException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m supplemental disc master
     * @return the m supplemental disc master that was removed
     * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster remove(Serializable primaryKey)
        throws NoSuchMSupplementalDiscMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MSupplementalDiscMaster mSupplementalDiscMaster = (MSupplementalDiscMaster) session.get(MSupplementalDiscMasterImpl.class,
                    primaryKey);

            if (mSupplementalDiscMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mSupplementalDiscMaster);
        } catch (NoSuchMSupplementalDiscMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MSupplementalDiscMaster removeImpl(
        MSupplementalDiscMaster mSupplementalDiscMaster)
        throws SystemException {
        mSupplementalDiscMaster = toUnwrappedModel(mSupplementalDiscMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mSupplementalDiscMaster)) {
                mSupplementalDiscMaster = (MSupplementalDiscMaster) session.get(MSupplementalDiscMasterImpl.class,
                        mSupplementalDiscMaster.getPrimaryKeyObj());
            }

            if (mSupplementalDiscMaster != null) {
                session.delete(mSupplementalDiscMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mSupplementalDiscMaster != null) {
            clearCache(mSupplementalDiscMaster);
        }

        return mSupplementalDiscMaster;
    }

    @Override
    public MSupplementalDiscMaster updateImpl(
        com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster)
        throws SystemException {
        mSupplementalDiscMaster = toUnwrappedModel(mSupplementalDiscMaster);

        boolean isNew = mSupplementalDiscMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mSupplementalDiscMaster.isNew()) {
                session.save(mSupplementalDiscMaster);

                mSupplementalDiscMaster.setNew(false);
            } else {
                session.merge(mSupplementalDiscMaster);
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

        EntityCacheUtil.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            MSupplementalDiscMasterImpl.class,
            mSupplementalDiscMaster.getPrimaryKey(), mSupplementalDiscMaster);

        return mSupplementalDiscMaster;
    }

    protected MSupplementalDiscMaster toUnwrappedModel(
        MSupplementalDiscMaster mSupplementalDiscMaster) {
        if (mSupplementalDiscMaster instanceof MSupplementalDiscMasterImpl) {
            return mSupplementalDiscMaster;
        }

        MSupplementalDiscMasterImpl mSupplementalDiscMasterImpl = new MSupplementalDiscMasterImpl();

        mSupplementalDiscMasterImpl.setNew(mSupplementalDiscMaster.isNew());
        mSupplementalDiscMasterImpl.setPrimaryKey(mSupplementalDiscMaster.getPrimaryKey());

        mSupplementalDiscMasterImpl.setActualDiscountRate2(mSupplementalDiscMaster.getActualDiscountRate2());
        mSupplementalDiscMasterImpl.setActualDiscountRate1(mSupplementalDiscMaster.getActualDiscountRate1());
        mSupplementalDiscMasterImpl.setMarketType(mSupplementalDiscMaster.getMarketType());
        mSupplementalDiscMasterImpl.setActualMethodology(mSupplementalDiscMaster.getActualMethodology());
        mSupplementalDiscMasterImpl.setActualContractPrice(mSupplementalDiscMaster.getActualContractPrice());
        mSupplementalDiscMasterImpl.setProjectionDetailsSid(mSupplementalDiscMaster.getProjectionDetailsSid());
        mSupplementalDiscMasterImpl.setActualDiscount(mSupplementalDiscMaster.getActualDiscount());
        mSupplementalDiscMasterImpl.setCheckRecord(mSupplementalDiscMaster.getCheckRecord());
        mSupplementalDiscMasterImpl.setContractEndDate(mSupplementalDiscMaster.getContractEndDate());

        return mSupplementalDiscMasterImpl;
    }

    /**
     * Returns the m supplemental disc master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc master
     * @return the m supplemental disc master
     * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMSupplementalDiscMasterException, SystemException {
        MSupplementalDiscMaster mSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

        if (mSupplementalDiscMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mSupplementalDiscMaster;
    }

    /**
     * Returns the m supplemental disc master with the primary key or throws a {@link com.stpl.app.NoSuchMSupplementalDiscMasterException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc master
     * @return the m supplemental disc master
     * @throws com.stpl.app.NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchMSupplementalDiscMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m supplemental disc master
     * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MSupplementalDiscMaster mSupplementalDiscMaster = (MSupplementalDiscMaster) EntityCacheUtil.getResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                MSupplementalDiscMasterImpl.class, primaryKey);

        if (mSupplementalDiscMaster == _nullMSupplementalDiscMaster) {
            return null;
        }

        if (mSupplementalDiscMaster == null) {
            Session session = null;

            try {
                session = openSession();

                mSupplementalDiscMaster = (MSupplementalDiscMaster) session.get(MSupplementalDiscMasterImpl.class,
                        primaryKey);

                if (mSupplementalDiscMaster != null) {
                    cacheResult(mSupplementalDiscMaster);
                } else {
                    EntityCacheUtil.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                        MSupplementalDiscMasterImpl.class, primaryKey,
                        _nullMSupplementalDiscMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                    MSupplementalDiscMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mSupplementalDiscMaster;
    }

    /**
     * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the m supplemental disc master
     * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MSupplementalDiscMaster fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the m supplemental disc masters.
     *
     * @return the m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m supplemental disc masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc masters
     * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
     * @return the range of m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m supplemental disc masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m supplemental disc masters
     * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MSupplementalDiscMaster> findAll(int start, int end,
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

        List<MSupplementalDiscMaster> list = (List<MSupplementalDiscMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MSUPPLEMENTALDISCMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MSUPPLEMENTALDISCMASTER;

                if (pagination) {
                    sql = sql.concat(MSupplementalDiscMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MSupplementalDiscMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MSupplementalDiscMaster>(list);
                } else {
                    list = (List<MSupplementalDiscMaster>) QueryUtil.list(q,
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
     * Removes all the m supplemental disc masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MSupplementalDiscMaster mSupplementalDiscMaster : findAll()) {
            remove(mSupplementalDiscMaster);
        }
    }

    /**
     * Returns the number of m supplemental disc masters.
     *
     * @return the number of m supplemental disc masters
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

                Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCMASTER);

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
     * Initializes the m supplemental disc master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MSupplementalDiscMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MSupplementalDiscMaster>> listenersList = new ArrayList<ModelListener<MSupplementalDiscMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MSupplementalDiscMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MSupplementalDiscMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
