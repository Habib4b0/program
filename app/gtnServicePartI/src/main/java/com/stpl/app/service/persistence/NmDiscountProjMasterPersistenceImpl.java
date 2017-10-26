package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmDiscountProjMasterException;
import com.stpl.app.model.NmDiscountProjMaster;
import com.stpl.app.model.impl.NmDiscountProjMasterImpl;
import com.stpl.app.model.impl.NmDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.NmDiscountProjMasterPersistence;

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
 * The persistence implementation for the nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjMasterPersistence
 * @see NmDiscountProjMasterUtil
 * @generated
 */
public class NmDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<NmDiscountProjMaster>
    implements NmDiscountProjMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmDiscountProjMasterUtil} to access the nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmDiscountProjMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            NmDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            NmDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMDISCOUNTPROJMASTER = "SELECT nmDiscountProjMaster FROM NmDiscountProjMaster nmDiscountProjMaster";
    private static final String _SQL_COUNT_NMDISCOUNTPROJMASTER = "SELECT COUNT(nmDiscountProjMaster) FROM NmDiscountProjMaster nmDiscountProjMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmDiscountProjMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmDiscountProjMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmDiscountProjMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "discountId", "userGroup", "priceGroupType",
                "projectionDetailsSid", "netFlag", "methodology", "discountName"
            });
    private static NmDiscountProjMaster _nullNmDiscountProjMaster = new NmDiscountProjMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmDiscountProjMaster> toCacheModel() {
                return _nullNmDiscountProjMasterCacheModel;
            }
        };

    private static CacheModel<NmDiscountProjMaster> _nullNmDiscountProjMasterCacheModel =
        new CacheModel<NmDiscountProjMaster>() {
            @Override
            public NmDiscountProjMaster toEntityModel() {
                return _nullNmDiscountProjMaster;
            }
        };

    public NmDiscountProjMasterPersistenceImpl() {
        setModelClass(NmDiscountProjMaster.class);
    }

    /**
     * Caches the nm discount proj master in the entity cache if it is enabled.
     *
     * @param nmDiscountProjMaster the nm discount proj master
     */
    @Override
    public void cacheResult(NmDiscountProjMaster nmDiscountProjMaster) {
        EntityCacheUtil.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterImpl.class,
            nmDiscountProjMaster.getPrimaryKey(), nmDiscountProjMaster);

        nmDiscountProjMaster.resetOriginalValues();
    }

    /**
     * Caches the nm discount proj masters in the entity cache if it is enabled.
     *
     * @param nmDiscountProjMasters the nm discount proj masters
     */
    @Override
    public void cacheResult(List<NmDiscountProjMaster> nmDiscountProjMasters) {
        for (NmDiscountProjMaster nmDiscountProjMaster : nmDiscountProjMasters) {
            if (EntityCacheUtil.getResult(
                        NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NmDiscountProjMasterImpl.class,
                        nmDiscountProjMaster.getPrimaryKey()) == null) {
                cacheResult(nmDiscountProjMaster);
            } else {
                nmDiscountProjMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm discount proj masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmDiscountProjMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmDiscountProjMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm discount proj master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmDiscountProjMaster nmDiscountProjMaster) {
        EntityCacheUtil.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterImpl.class, nmDiscountProjMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmDiscountProjMaster> nmDiscountProjMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmDiscountProjMaster nmDiscountProjMaster : nmDiscountProjMasters) {
            EntityCacheUtil.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                NmDiscountProjMasterImpl.class,
                nmDiscountProjMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
     *
     * @param projectionDetailsSid the primary key for the new nm discount proj master
     * @return the new nm discount proj master
     */
    @Override
    public NmDiscountProjMaster create(int projectionDetailsSid) {
        NmDiscountProjMaster nmDiscountProjMaster = new NmDiscountProjMasterImpl();

        nmDiscountProjMaster.setNew(true);
        nmDiscountProjMaster.setPrimaryKey(projectionDetailsSid);

        return nmDiscountProjMaster;
    }

    /**
     * Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param projectionDetailsSid the primary key of the nm discount proj master
     * @return the nm discount proj master that was removed
     * @throws com.stpl.app.NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster remove(int projectionDetailsSid)
        throws NoSuchNmDiscountProjMasterException, SystemException {
        return remove((Serializable) projectionDetailsSid);
    }

    /**
     * Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm discount proj master
     * @return the nm discount proj master that was removed
     * @throws com.stpl.app.NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster remove(Serializable primaryKey)
        throws NoSuchNmDiscountProjMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmDiscountProjMaster nmDiscountProjMaster = (NmDiscountProjMaster) session.get(NmDiscountProjMasterImpl.class,
                    primaryKey);

            if (nmDiscountProjMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmDiscountProjMaster);
        } catch (NoSuchNmDiscountProjMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmDiscountProjMaster removeImpl(
        NmDiscountProjMaster nmDiscountProjMaster) throws SystemException {
        nmDiscountProjMaster = toUnwrappedModel(nmDiscountProjMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmDiscountProjMaster)) {
                nmDiscountProjMaster = (NmDiscountProjMaster) session.get(NmDiscountProjMasterImpl.class,
                        nmDiscountProjMaster.getPrimaryKeyObj());
            }

            if (nmDiscountProjMaster != null) {
                session.delete(nmDiscountProjMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmDiscountProjMaster != null) {
            clearCache(nmDiscountProjMaster);
        }

        return nmDiscountProjMaster;
    }

    @Override
    public NmDiscountProjMaster updateImpl(
        com.stpl.app.model.NmDiscountProjMaster nmDiscountProjMaster)
        throws SystemException {
        nmDiscountProjMaster = toUnwrappedModel(nmDiscountProjMaster);

        boolean isNew = nmDiscountProjMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmDiscountProjMaster.isNew()) {
                session.save(nmDiscountProjMaster);

                nmDiscountProjMaster.setNew(false);
            } else {
                session.merge(nmDiscountProjMaster);
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

        EntityCacheUtil.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NmDiscountProjMasterImpl.class,
            nmDiscountProjMaster.getPrimaryKey(), nmDiscountProjMaster);

        return nmDiscountProjMaster;
    }

    protected NmDiscountProjMaster toUnwrappedModel(
        NmDiscountProjMaster nmDiscountProjMaster) {
        if (nmDiscountProjMaster instanceof NmDiscountProjMasterImpl) {
            return nmDiscountProjMaster;
        }

        NmDiscountProjMasterImpl nmDiscountProjMasterImpl = new NmDiscountProjMasterImpl();

        nmDiscountProjMasterImpl.setNew(nmDiscountProjMaster.isNew());
        nmDiscountProjMasterImpl.setPrimaryKey(nmDiscountProjMaster.getPrimaryKey());

        nmDiscountProjMasterImpl.setCheckRecord(nmDiscountProjMaster.isCheckRecord());
        nmDiscountProjMasterImpl.setDiscountId(nmDiscountProjMaster.getDiscountId());
        nmDiscountProjMasterImpl.setUserGroup(nmDiscountProjMaster.getUserGroup());
        nmDiscountProjMasterImpl.setPriceGroupType(nmDiscountProjMaster.getPriceGroupType());
        nmDiscountProjMasterImpl.setProjectionDetailsSid(nmDiscountProjMaster.getProjectionDetailsSid());
        nmDiscountProjMasterImpl.setNetFlag(nmDiscountProjMaster.getNetFlag());
        nmDiscountProjMasterImpl.setMethodology(nmDiscountProjMaster.getMethodology());
        nmDiscountProjMasterImpl.setDiscountName(nmDiscountProjMaster.getDiscountName());

        return nmDiscountProjMasterImpl;
    }

    /**
     * Returns the nm discount proj master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm discount proj master
     * @return the nm discount proj master
     * @throws com.stpl.app.NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmDiscountProjMasterException, SystemException {
        NmDiscountProjMaster nmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

        if (nmDiscountProjMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmDiscountProjMaster;
    }

    /**
     * Returns the nm discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchNmDiscountProjMasterException} if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the nm discount proj master
     * @return the nm discount proj master
     * @throws com.stpl.app.NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster findByPrimaryKey(int projectionDetailsSid)
        throws NoSuchNmDiscountProjMasterException, SystemException {
        return findByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm discount proj master
     * @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmDiscountProjMaster nmDiscountProjMaster = (NmDiscountProjMaster) EntityCacheUtil.getResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                NmDiscountProjMasterImpl.class, primaryKey);

        if (nmDiscountProjMaster == _nullNmDiscountProjMaster) {
            return null;
        }

        if (nmDiscountProjMaster == null) {
            Session session = null;

            try {
                session = openSession();

                nmDiscountProjMaster = (NmDiscountProjMaster) session.get(NmDiscountProjMasterImpl.class,
                        primaryKey);

                if (nmDiscountProjMaster != null) {
                    cacheResult(nmDiscountProjMaster);
                } else {
                    EntityCacheUtil.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NmDiscountProjMasterImpl.class, primaryKey,
                        _nullNmDiscountProjMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                    NmDiscountProjMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmDiscountProjMaster;
    }

    /**
     * Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param projectionDetailsSid the primary key of the nm discount proj master
     * @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmDiscountProjMaster fetchByPrimaryKey(int projectionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) projectionDetailsSid);
    }

    /**
     * Returns all the nm discount proj masters.
     *
     * @return the nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm discount proj masters
     * @param end the upper bound of the range of nm discount proj masters (not inclusive)
     * @return the range of nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm discount proj masters
     * @param end the upper bound of the range of nm discount proj masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmDiscountProjMaster> findAll(int start, int end,
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

        List<NmDiscountProjMaster> list = (List<NmDiscountProjMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMDISCOUNTPROJMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMDISCOUNTPROJMASTER;

                if (pagination) {
                    sql = sql.concat(NmDiscountProjMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmDiscountProjMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmDiscountProjMaster>(list);
                } else {
                    list = (List<NmDiscountProjMaster>) QueryUtil.list(q,
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
     * Removes all the nm discount proj masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmDiscountProjMaster nmDiscountProjMaster : findAll()) {
            remove(nmDiscountProjMaster);
        }
    }

    /**
     * Returns the number of nm discount proj masters.
     *
     * @return the number of nm discount proj masters
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

                Query q = session.createQuery(_SQL_COUNT_NMDISCOUNTPROJMASTER);

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
     * Initializes the nm discount proj master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmDiscountProjMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmDiscountProjMaster>> listenersList = new ArrayList<ModelListener<NmDiscountProjMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmDiscountProjMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmDiscountProjMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
