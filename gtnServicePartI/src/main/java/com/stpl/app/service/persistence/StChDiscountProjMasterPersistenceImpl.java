package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStChDiscountProjMasterException;
import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.model.impl.StChDiscountProjMasterImpl;
import com.stpl.app.model.impl.StChDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.StChDiscountProjMasterPersistence;

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
 * The persistence implementation for the st ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChDiscountProjMasterPersistence
 * @see StChDiscountProjMasterUtil
 * @generated
 */
public class StChDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<StChDiscountProjMaster>
    implements StChDiscountProjMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StChDiscountProjMasterUtil} to access the st ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StChDiscountProjMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            StChDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            StChDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STCHDISCOUNTPROJMASTER = "SELECT stChDiscountProjMaster FROM StChDiscountProjMaster stChDiscountProjMaster";
    private static final String _SQL_COUNT_STCHDISCOUNTPROJMASTER = "SELECT COUNT(stChDiscountProjMaster) FROM StChDiscountProjMaster stChDiscountProjMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stChDiscountProjMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChDiscountProjMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StChDiscountProjMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "selectedPeriods", "lastModifiedDate",
                "projectionDetailsSid", "priceGroupType", "userId", "netFlag",
                "projectedType", "baselinePeriods", "sessionId", "methodology",
                "rsModelSid", "discountType"
            });
    private static StChDiscountProjMaster _nullStChDiscountProjMaster = new StChDiscountProjMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StChDiscountProjMaster> toCacheModel() {
                return _nullStChDiscountProjMasterCacheModel;
            }
        };

    private static CacheModel<StChDiscountProjMaster> _nullStChDiscountProjMasterCacheModel =
        new CacheModel<StChDiscountProjMaster>() {
            @Override
            public StChDiscountProjMaster toEntityModel() {
                return _nullStChDiscountProjMaster;
            }
        };

    public StChDiscountProjMasterPersistenceImpl() {
        setModelClass(StChDiscountProjMaster.class);
    }

    /**
     * Caches the st ch discount proj master in the entity cache if it is enabled.
     *
     * @param stChDiscountProjMaster the st ch discount proj master
     */
    @Override
    public void cacheResult(StChDiscountProjMaster stChDiscountProjMaster) {
        EntityCacheUtil.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterImpl.class,
            stChDiscountProjMaster.getPrimaryKey(), stChDiscountProjMaster);

        stChDiscountProjMaster.resetOriginalValues();
    }

    /**
     * Caches the st ch discount proj masters in the entity cache if it is enabled.
     *
     * @param stChDiscountProjMasters the st ch discount proj masters
     */
    @Override
    public void cacheResult(
        List<StChDiscountProjMaster> stChDiscountProjMasters) {
        for (StChDiscountProjMaster stChDiscountProjMaster : stChDiscountProjMasters) {
            if (EntityCacheUtil.getResult(
                        StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StChDiscountProjMasterImpl.class,
                        stChDiscountProjMaster.getPrimaryKey()) == null) {
                cacheResult(stChDiscountProjMaster);
            } else {
                stChDiscountProjMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st ch discount proj masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StChDiscountProjMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StChDiscountProjMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st ch discount proj master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StChDiscountProjMaster stChDiscountProjMaster) {
        EntityCacheUtil.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterImpl.class,
            stChDiscountProjMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StChDiscountProjMaster> stChDiscountProjMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StChDiscountProjMaster stChDiscountProjMaster : stChDiscountProjMasters) {
            EntityCacheUtil.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                StChDiscountProjMasterImpl.class,
                stChDiscountProjMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
     *
     * @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
     * @return the new st ch discount proj master
     */
    @Override
    public StChDiscountProjMaster create(
        StChDiscountProjMasterPK stChDiscountProjMasterPK) {
        StChDiscountProjMaster stChDiscountProjMaster = new StChDiscountProjMasterImpl();

        stChDiscountProjMaster.setNew(true);
        stChDiscountProjMaster.setPrimaryKey(stChDiscountProjMasterPK);

        return stChDiscountProjMaster;
    }

    /**
     * Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
     * @return the st ch discount proj master that was removed
     * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster remove(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws NoSuchStChDiscountProjMasterException, SystemException {
        return remove((Serializable) stChDiscountProjMasterPK);
    }

    /**
     * Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st ch discount proj master
     * @return the st ch discount proj master that was removed
     * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster remove(Serializable primaryKey)
        throws NoSuchStChDiscountProjMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StChDiscountProjMaster stChDiscountProjMaster = (StChDiscountProjMaster) session.get(StChDiscountProjMasterImpl.class,
                    primaryKey);

            if (stChDiscountProjMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stChDiscountProjMaster);
        } catch (NoSuchStChDiscountProjMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StChDiscountProjMaster removeImpl(
        StChDiscountProjMaster stChDiscountProjMaster)
        throws SystemException {
        stChDiscountProjMaster = toUnwrappedModel(stChDiscountProjMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stChDiscountProjMaster)) {
                stChDiscountProjMaster = (StChDiscountProjMaster) session.get(StChDiscountProjMasterImpl.class,
                        stChDiscountProjMaster.getPrimaryKeyObj());
            }

            if (stChDiscountProjMaster != null) {
                session.delete(stChDiscountProjMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stChDiscountProjMaster != null) {
            clearCache(stChDiscountProjMaster);
        }

        return stChDiscountProjMaster;
    }

    @Override
    public StChDiscountProjMaster updateImpl(
        com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster)
        throws SystemException {
        stChDiscountProjMaster = toUnwrappedModel(stChDiscountProjMaster);

        boolean isNew = stChDiscountProjMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stChDiscountProjMaster.isNew()) {
                session.save(stChDiscountProjMaster);

                stChDiscountProjMaster.setNew(false);
            } else {
                session.merge(stChDiscountProjMaster);
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

        EntityCacheUtil.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StChDiscountProjMasterImpl.class,
            stChDiscountProjMaster.getPrimaryKey(), stChDiscountProjMaster);

        return stChDiscountProjMaster;
    }

    protected StChDiscountProjMaster toUnwrappedModel(
        StChDiscountProjMaster stChDiscountProjMaster) {
        if (stChDiscountProjMaster instanceof StChDiscountProjMasterImpl) {
            return stChDiscountProjMaster;
        }

        StChDiscountProjMasterImpl stChDiscountProjMasterImpl = new StChDiscountProjMasterImpl();

        stChDiscountProjMasterImpl.setNew(stChDiscountProjMaster.isNew());
        stChDiscountProjMasterImpl.setPrimaryKey(stChDiscountProjMaster.getPrimaryKey());

        stChDiscountProjMasterImpl.setCheckRecord(stChDiscountProjMaster.isCheckRecord());
        stChDiscountProjMasterImpl.setSelectedPeriods(stChDiscountProjMaster.getSelectedPeriods());
        stChDiscountProjMasterImpl.setLastModifiedDate(stChDiscountProjMaster.getLastModifiedDate());
        stChDiscountProjMasterImpl.setProjectionDetailsSid(stChDiscountProjMaster.getProjectionDetailsSid());
        stChDiscountProjMasterImpl.setPriceGroupType(stChDiscountProjMaster.getPriceGroupType());
        stChDiscountProjMasterImpl.setUserId(stChDiscountProjMaster.getUserId());
        stChDiscountProjMasterImpl.setNetFlag(stChDiscountProjMaster.getNetFlag());
        stChDiscountProjMasterImpl.setProjectedType(stChDiscountProjMaster.getProjectedType());
        stChDiscountProjMasterImpl.setBaselinePeriods(stChDiscountProjMaster.getBaselinePeriods());
        stChDiscountProjMasterImpl.setSessionId(stChDiscountProjMaster.getSessionId());
        stChDiscountProjMasterImpl.setMethodology(stChDiscountProjMaster.getMethodology());
        stChDiscountProjMasterImpl.setRsModelSid(stChDiscountProjMaster.getRsModelSid());
        stChDiscountProjMasterImpl.setDiscountType(stChDiscountProjMaster.getDiscountType());

        return stChDiscountProjMasterImpl;
    }

    /**
     * Returns the st ch discount proj master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st ch discount proj master
     * @return the st ch discount proj master
     * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStChDiscountProjMasterException, SystemException {
        StChDiscountProjMaster stChDiscountProjMaster = fetchByPrimaryKey(primaryKey);

        if (stChDiscountProjMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stChDiscountProjMaster;
    }

    /**
     * Returns the st ch discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchStChDiscountProjMasterException} if it could not be found.
     *
     * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
     * @return the st ch discount proj master
     * @throws com.stpl.app.NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster findByPrimaryKey(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws NoSuchStChDiscountProjMasterException, SystemException {
        return findByPrimaryKey((Serializable) stChDiscountProjMasterPK);
    }

    /**
     * Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st ch discount proj master
     * @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StChDiscountProjMaster stChDiscountProjMaster = (StChDiscountProjMaster) EntityCacheUtil.getResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                StChDiscountProjMasterImpl.class, primaryKey);

        if (stChDiscountProjMaster == _nullStChDiscountProjMaster) {
            return null;
        }

        if (stChDiscountProjMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stChDiscountProjMaster = (StChDiscountProjMaster) session.get(StChDiscountProjMasterImpl.class,
                        primaryKey);

                if (stChDiscountProjMaster != null) {
                    cacheResult(stChDiscountProjMaster);
                } else {
                    EntityCacheUtil.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StChDiscountProjMasterImpl.class, primaryKey,
                        _nullStChDiscountProjMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StChDiscountProjMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stChDiscountProjMaster;
    }

    /**
     * Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
     * @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChDiscountProjMaster fetchByPrimaryKey(
        StChDiscountProjMasterPK stChDiscountProjMasterPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stChDiscountProjMasterPK);
    }

    /**
     * Returns all the st ch discount proj masters.
     *
     * @return the st ch discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChDiscountProjMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st ch discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch discount proj masters
     * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
     * @return the range of st ch discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChDiscountProjMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st ch discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch discount proj masters
     * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st ch discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChDiscountProjMaster> findAll(int start, int end,
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

        List<StChDiscountProjMaster> list = (List<StChDiscountProjMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCHDISCOUNTPROJMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCHDISCOUNTPROJMASTER;

                if (pagination) {
                    sql = sql.concat(StChDiscountProjMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StChDiscountProjMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StChDiscountProjMaster>(list);
                } else {
                    list = (List<StChDiscountProjMaster>) QueryUtil.list(q,
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
     * Removes all the st ch discount proj masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StChDiscountProjMaster stChDiscountProjMaster : findAll()) {
            remove(stChDiscountProjMaster);
        }
    }

    /**
     * Returns the number of st ch discount proj masters.
     *
     * @return the number of st ch discount proj masters
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

                Query q = session.createQuery(_SQL_COUNT_STCHDISCOUNTPROJMASTER);

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
     * Initializes the st ch discount proj master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StChDiscountProjMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StChDiscountProjMaster>> listenersList = new ArrayList<ModelListener<StChDiscountProjMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StChDiscountProjMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StChDiscountProjMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
