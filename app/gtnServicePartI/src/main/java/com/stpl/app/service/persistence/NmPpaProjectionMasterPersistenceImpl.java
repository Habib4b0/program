package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmPpaProjectionMasterException;
import com.stpl.app.model.NmPpaProjectionMaster;
import com.stpl.app.model.impl.NmPpaProjectionMasterImpl;
import com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl;
import com.stpl.app.service.persistence.NmPpaProjectionMasterPersistence;

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
 * The persistence implementation for the nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionMasterPersistence
 * @see NmPpaProjectionMasterUtil
 * @generated
 */
public class NmPpaProjectionMasterPersistenceImpl extends BasePersistenceImpl<NmPpaProjectionMaster>
    implements NmPpaProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmPpaProjectionMasterUtil} to access the nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmPpaProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            NmPpaProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            NmPpaProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMPPAPROJECTIONMASTER = "SELECT nmPpaProjectionMaster FROM NmPpaProjectionMaster nmPpaProjectionMaster";
    private static final String _SQL_COUNT_NMPPAPROJECTIONMASTER = "SELECT COUNT(nmPpaProjectionMaster) FROM NmPpaProjectionMaster nmPpaProjectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmPpaProjectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmPpaProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmPpaProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "userGroup", "projectionDetailsSid", "priceBasis",
                "priceProtectionEndDate", "priceProtectionStartDate",
                "actualPriceCap"
            });
    private static NmPpaProjectionMaster _nullNmPpaProjectionMaster = new NmPpaProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmPpaProjectionMaster> toCacheModel() {
                return _nullNmPpaProjectionMasterCacheModel;
            }
        };

    private static CacheModel<NmPpaProjectionMaster> _nullNmPpaProjectionMasterCacheModel =
        new CacheModel<NmPpaProjectionMaster>() {
            @Override
            public NmPpaProjectionMaster toEntityModel() {
                return _nullNmPpaProjectionMaster;
            }
        };

    public NmPpaProjectionMasterPersistenceImpl() {
        setModelClass(NmPpaProjectionMaster.class);
    }

    /**
     * Caches the nm ppa projection master in the entity cache if it is enabled.
     *
     * @param nmPpaProjectionMaster the nm ppa projection master
     */
    @Override
    public void cacheResult(NmPpaProjectionMaster nmPpaProjectionMaster) {
        EntityCacheUtil.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterImpl.class,
            nmPpaProjectionMaster.getPrimaryKey(), nmPpaProjectionMaster);

        nmPpaProjectionMaster.resetOriginalValues();
    }

    /**
     * Caches the nm ppa projection masters in the entity cache if it is enabled.
     *
     * @param nmPpaProjectionMasters the nm ppa projection masters
     */
    @Override
    public void cacheResult(List<NmPpaProjectionMaster> nmPpaProjectionMasters) {
        for (NmPpaProjectionMaster nmPpaProjectionMaster : nmPpaProjectionMasters) {
            if (EntityCacheUtil.getResult(
                        NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NmPpaProjectionMasterImpl.class,
                        nmPpaProjectionMaster.getPrimaryKey()) == null) {
                cacheResult(nmPpaProjectionMaster);
            } else {
                nmPpaProjectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm ppa projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmPpaProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmPpaProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm ppa projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmPpaProjectionMaster nmPpaProjectionMaster) {
        EntityCacheUtil.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterImpl.class,
            nmPpaProjectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmPpaProjectionMaster> nmPpaProjectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmPpaProjectionMaster nmPpaProjectionMaster : nmPpaProjectionMasters) {
            EntityCacheUtil.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                NmPpaProjectionMasterImpl.class,
                nmPpaProjectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
     *
     * @param projectionDetailsSid the primary key for the new nm ppa projection master
     * @return the new nm ppa projection master
     */
    @Override
    public NmPpaProjectionMaster create(int projectionDetailsSid) {
        NmPpaProjectionMaster nmPpaProjectionMaster = new NmPpaProjectionMasterImpl();

        nmPpaProjectionMaster.setNew(true);
        nmPpaProjectionMaster.setPrimaryKey(projectionDetailsSid);

        return nmPpaProjectionMaster;
    }

    /**
     * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the nm ppa projection master
     * @return the nm ppa projection master that was removed
     * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster remove(int projectionDetailsSid)
        throws NoSuchNmPpaProjectionMasterException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm ppa projection master
     * @return the nm ppa projection master that was removed
     * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster remove(Serializable primaryKey)
        throws NoSuchNmPpaProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmPpaProjectionMaster nmPpaProjectionMaster = (NmPpaProjectionMaster) session.get(NmPpaProjectionMasterImpl.class,
                    primaryKey);

            if (nmPpaProjectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmPpaProjectionMaster);
        } catch (NoSuchNmPpaProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmPpaProjectionMaster removeImpl(
        NmPpaProjectionMaster nmPpaProjectionMaster) throws SystemException {
        nmPpaProjectionMaster = toUnwrappedModel(nmPpaProjectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmPpaProjectionMaster)) {
                nmPpaProjectionMaster = (NmPpaProjectionMaster) session.get(NmPpaProjectionMasterImpl.class,
                        nmPpaProjectionMaster.getPrimaryKeyObj());
            }

            if (nmPpaProjectionMaster != null) {
                session.delete(nmPpaProjectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmPpaProjectionMaster != null) {
            clearCache(nmPpaProjectionMaster);
        }

        return nmPpaProjectionMaster;
    }

    @Override
    public NmPpaProjectionMaster updateImpl(
        com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster)
        throws SystemException {
        nmPpaProjectionMaster = toUnwrappedModel(nmPpaProjectionMaster);

        boolean isNew = nmPpaProjectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmPpaProjectionMaster.isNew()) {
                session.save(nmPpaProjectionMaster);

                nmPpaProjectionMaster.setNew(false);
            } else {
                session.merge(nmPpaProjectionMaster);
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

        EntityCacheUtil.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmPpaProjectionMasterImpl.class,
            nmPpaProjectionMaster.getPrimaryKey(), nmPpaProjectionMaster);

        return nmPpaProjectionMaster;
    }

    protected NmPpaProjectionMaster toUnwrappedModel(
        NmPpaProjectionMaster nmPpaProjectionMaster) {
        if (nmPpaProjectionMaster instanceof NmPpaProjectionMasterImpl) {
            return nmPpaProjectionMaster;
        }

        NmPpaProjectionMasterImpl nmPpaProjectionMasterImpl = new NmPpaProjectionMasterImpl();

        nmPpaProjectionMasterImpl.setNew(nmPpaProjectionMaster.isNew());
        nmPpaProjectionMasterImpl.setPrimaryKey(nmPpaProjectionMaster.getPrimaryKey());

        nmPpaProjectionMasterImpl.setCheckRecord(nmPpaProjectionMaster.isCheckRecord());
        nmPpaProjectionMasterImpl.setUserGroup(nmPpaProjectionMaster.getUserGroup());
        nmPpaProjectionMasterImpl.setProjectionDetailsSid(nmPpaProjectionMaster.getProjectionDetailsSid());
        nmPpaProjectionMasterImpl.setPriceBasis(nmPpaProjectionMaster.getPriceBasis());
        nmPpaProjectionMasterImpl.setPriceProtectionEndDate(nmPpaProjectionMaster.getPriceProtectionEndDate());
        nmPpaProjectionMasterImpl.setPriceProtectionStartDate(nmPpaProjectionMaster.getPriceProtectionStartDate());
        nmPpaProjectionMasterImpl.setActualPriceCap(nmPpaProjectionMaster.getActualPriceCap());

        return nmPpaProjectionMasterImpl;
    }

    /**
     * Returns the nm ppa projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm ppa projection master
     * @return the nm ppa projection master
     * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmPpaProjectionMasterException, SystemException {
        NmPpaProjectionMaster nmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

        if (nmPpaProjectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmPpaProjectionMaster;
    }

    /**
     * Returns the nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchNmPpaProjectionMasterException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the nm ppa projection master
     * @return the nm ppa projection master
     * @throws com.stpl.app.NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchNmPpaProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm ppa projection master
     * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmPpaProjectionMaster nmPpaProjectionMaster = (NmPpaProjectionMaster) EntityCacheUtil.getResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                NmPpaProjectionMasterImpl.class, primaryKey);

        if (nmPpaProjectionMaster == _nullNmPpaProjectionMaster) {
            return null;
        }

        if (nmPpaProjectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                nmPpaProjectionMaster = (NmPpaProjectionMaster) session.get(NmPpaProjectionMasterImpl.class,
                        primaryKey);

                if (nmPpaProjectionMaster != null) {
                    cacheResult(nmPpaProjectionMaster);
                } else {
                    EntityCacheUtil.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NmPpaProjectionMasterImpl.class, primaryKey,
                        _nullNmPpaProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    NmPpaProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmPpaProjectionMaster;
    }

    /**
     * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the nm ppa projection master
     * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmPpaProjectionMaster fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the nm ppa projection masters.
     *
     * @return the nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm ppa projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm ppa projection masters
     * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
     * @return the range of nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm ppa projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm ppa projection masters
     * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmPpaProjectionMaster> findAll(int start, int end,
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

        List<NmPpaProjectionMaster> list = (List<NmPpaProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMPPAPROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMPPAPROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(NmPpaProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmPpaProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmPpaProjectionMaster>(list);
                } else {
                    list = (List<NmPpaProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the nm ppa projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmPpaProjectionMaster nmPpaProjectionMaster : findAll()) {
            remove(nmPpaProjectionMaster);
        }
    }

    /**
     * Returns the number of nm ppa projection masters.
     *
     * @return the number of nm ppa projection masters
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

                Query q = session.createQuery(_SQL_COUNT_NMPPAPROJECTIONMASTER);

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
     * Initializes the nm ppa projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmPpaProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmPpaProjectionMaster>> listenersList = new ArrayList<ModelListener<NmPpaProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmPpaProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmPpaProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
