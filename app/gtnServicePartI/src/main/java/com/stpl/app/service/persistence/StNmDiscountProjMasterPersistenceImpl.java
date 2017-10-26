package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmDiscountProjMasterException;
import com.stpl.app.model.StNmDiscountProjMaster;
import com.stpl.app.model.impl.StNmDiscountProjMasterImpl;
import com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPersistence;

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
 * The persistence implementation for the st nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjMasterPersistence
 * @see StNmDiscountProjMasterUtil
 * @generated
 */
public class StNmDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<StNmDiscountProjMaster>
    implements StNmDiscountProjMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmDiscountProjMasterUtil} to access the st nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmDiscountProjMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            StNmDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
            StNmDiscountProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMDISCOUNTPROJMASTER = "SELECT stNmDiscountProjMaster FROM StNmDiscountProjMaster stNmDiscountProjMaster";
    private static final String _SQL_COUNT_STNMDISCOUNTPROJMASTER = "SELECT COUNT(stNmDiscountProjMaster) FROM StNmDiscountProjMaster stNmDiscountProjMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmDiscountProjMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmDiscountProjMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmDiscountProjMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "selectedPeriods", "methodology", "netFlag", "priceGroupType",
                "userGroup", "userId", "lastModifiedDate",
                "projectionDetailsSid", "rsModelSid", "sessionId", "checkRecord",
                "baselinePeriods"
            });
    private static StNmDiscountProjMaster _nullStNmDiscountProjMaster = new StNmDiscountProjMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmDiscountProjMaster> toCacheModel() {
                return _nullStNmDiscountProjMasterCacheModel;
            }
        };

    private static CacheModel<StNmDiscountProjMaster> _nullStNmDiscountProjMasterCacheModel =
        new CacheModel<StNmDiscountProjMaster>() {
            @Override
            public StNmDiscountProjMaster toEntityModel() {
                return _nullStNmDiscountProjMaster;
            }
        };

    public StNmDiscountProjMasterPersistenceImpl() {
        setModelClass(StNmDiscountProjMaster.class);
    }

    /**
     * Caches the st nm discount proj master in the entity cache if it is enabled.
     *
     * @param stNmDiscountProjMaster the st nm discount proj master
     */
    @Override
    public void cacheResult(StNmDiscountProjMaster stNmDiscountProjMaster) {
        EntityCacheUtil.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterImpl.class,
            stNmDiscountProjMaster.getPrimaryKey(), stNmDiscountProjMaster);

        stNmDiscountProjMaster.resetOriginalValues();
    }

    /**
     * Caches the st nm discount proj masters in the entity cache if it is enabled.
     *
     * @param stNmDiscountProjMasters the st nm discount proj masters
     */
    @Override
    public void cacheResult(
        List<StNmDiscountProjMaster> stNmDiscountProjMasters) {
        for (StNmDiscountProjMaster stNmDiscountProjMaster : stNmDiscountProjMasters) {
            if (EntityCacheUtil.getResult(
                        StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StNmDiscountProjMasterImpl.class,
                        stNmDiscountProjMaster.getPrimaryKey()) == null) {
                cacheResult(stNmDiscountProjMaster);
            } else {
                stNmDiscountProjMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm discount proj masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmDiscountProjMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmDiscountProjMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm discount proj master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmDiscountProjMaster stNmDiscountProjMaster) {
        EntityCacheUtil.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterImpl.class,
            stNmDiscountProjMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmDiscountProjMaster> stNmDiscountProjMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmDiscountProjMaster stNmDiscountProjMaster : stNmDiscountProjMasters) {
            EntityCacheUtil.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                StNmDiscountProjMasterImpl.class,
                stNmDiscountProjMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
     *
     * @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
     * @return the new st nm discount proj master
     */
    @Override
    public StNmDiscountProjMaster create(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
        StNmDiscountProjMaster stNmDiscountProjMaster = new StNmDiscountProjMasterImpl();

        stNmDiscountProjMaster.setNew(true);
        stNmDiscountProjMaster.setPrimaryKey(stNmDiscountProjMasterPK);

        return stNmDiscountProjMaster;
    }

    /**
     * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
     * @return the st nm discount proj master that was removed
     * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster remove(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws NoSuchStNmDiscountProjMasterException, SystemException {
        return remove((Serializable) stNmDiscountProjMasterPK);
    }

    /**
     * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm discount proj master
     * @return the st nm discount proj master that was removed
     * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster remove(Serializable primaryKey)
        throws NoSuchStNmDiscountProjMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmDiscountProjMaster stNmDiscountProjMaster = (StNmDiscountProjMaster) session.get(StNmDiscountProjMasterImpl.class,
                    primaryKey);

            if (stNmDiscountProjMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmDiscountProjMaster);
        } catch (NoSuchStNmDiscountProjMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmDiscountProjMaster removeImpl(
        StNmDiscountProjMaster stNmDiscountProjMaster)
        throws SystemException {
        stNmDiscountProjMaster = toUnwrappedModel(stNmDiscountProjMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmDiscountProjMaster)) {
                stNmDiscountProjMaster = (StNmDiscountProjMaster) session.get(StNmDiscountProjMasterImpl.class,
                        stNmDiscountProjMaster.getPrimaryKeyObj());
            }

            if (stNmDiscountProjMaster != null) {
                session.delete(stNmDiscountProjMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmDiscountProjMaster != null) {
            clearCache(stNmDiscountProjMaster);
        }

        return stNmDiscountProjMaster;
    }

    @Override
    public StNmDiscountProjMaster updateImpl(
        com.stpl.app.model.StNmDiscountProjMaster stNmDiscountProjMaster)
        throws SystemException {
        stNmDiscountProjMaster = toUnwrappedModel(stNmDiscountProjMaster);

        boolean isNew = stNmDiscountProjMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmDiscountProjMaster.isNew()) {
                session.save(stNmDiscountProjMaster);

                stNmDiscountProjMaster.setNew(false);
            } else {
                session.merge(stNmDiscountProjMaster);
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

        EntityCacheUtil.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmDiscountProjMasterImpl.class,
            stNmDiscountProjMaster.getPrimaryKey(), stNmDiscountProjMaster);

        return stNmDiscountProjMaster;
    }

    protected StNmDiscountProjMaster toUnwrappedModel(
        StNmDiscountProjMaster stNmDiscountProjMaster) {
        if (stNmDiscountProjMaster instanceof StNmDiscountProjMasterImpl) {
            return stNmDiscountProjMaster;
        }

        StNmDiscountProjMasterImpl stNmDiscountProjMasterImpl = new StNmDiscountProjMasterImpl();

        stNmDiscountProjMasterImpl.setNew(stNmDiscountProjMaster.isNew());
        stNmDiscountProjMasterImpl.setPrimaryKey(stNmDiscountProjMaster.getPrimaryKey());

        stNmDiscountProjMasterImpl.setSelectedPeriods(stNmDiscountProjMaster.getSelectedPeriods());
        stNmDiscountProjMasterImpl.setMethodology(stNmDiscountProjMaster.getMethodology());
        stNmDiscountProjMasterImpl.setNetFlag(stNmDiscountProjMaster.getNetFlag());
        stNmDiscountProjMasterImpl.setPriceGroupType(stNmDiscountProjMaster.getPriceGroupType());
        stNmDiscountProjMasterImpl.setUserGroup(stNmDiscountProjMaster.getUserGroup());
        stNmDiscountProjMasterImpl.setUserId(stNmDiscountProjMaster.getUserId());
        stNmDiscountProjMasterImpl.setLastModifiedDate(stNmDiscountProjMaster.getLastModifiedDate());
        stNmDiscountProjMasterImpl.setProjectionDetailsSid(stNmDiscountProjMaster.getProjectionDetailsSid());
        stNmDiscountProjMasterImpl.setRsModelSid(stNmDiscountProjMaster.getRsModelSid());
        stNmDiscountProjMasterImpl.setSessionId(stNmDiscountProjMaster.getSessionId());
        stNmDiscountProjMasterImpl.setCheckRecord(stNmDiscountProjMaster.isCheckRecord());
        stNmDiscountProjMasterImpl.setBaselinePeriods(stNmDiscountProjMaster.getBaselinePeriods());

        return stNmDiscountProjMasterImpl;
    }

    /**
     * Returns the st nm discount proj master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm discount proj master
     * @return the st nm discount proj master
     * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmDiscountProjMasterException, SystemException {
        StNmDiscountProjMaster stNmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

        if (stNmDiscountProjMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmDiscountProjMaster;
    }

    /**
     * Returns the st nm discount proj master with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjMasterException} if it could not be found.
     *
     * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
     * @return the st nm discount proj master
     * @throws com.stpl.app.NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster findByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws NoSuchStNmDiscountProjMasterException, SystemException {
        return findByPrimaryKey((Serializable) stNmDiscountProjMasterPK);
    }

    /**
     * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm discount proj master
     * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmDiscountProjMaster stNmDiscountProjMaster = (StNmDiscountProjMaster) EntityCacheUtil.getResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                StNmDiscountProjMasterImpl.class, primaryKey);

        if (stNmDiscountProjMaster == _nullStNmDiscountProjMaster) {
            return null;
        }

        if (stNmDiscountProjMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stNmDiscountProjMaster = (StNmDiscountProjMaster) session.get(StNmDiscountProjMasterImpl.class,
                        primaryKey);

                if (stNmDiscountProjMaster != null) {
                    cacheResult(stNmDiscountProjMaster);
                } else {
                    EntityCacheUtil.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StNmDiscountProjMasterImpl.class, primaryKey,
                        _nullStNmDiscountProjMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StNmDiscountProjMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmDiscountProjMaster;
    }

    /**
     * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
     * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmDiscountProjMaster fetchByPrimaryKey(
        StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmDiscountProjMasterPK);
    }

    /**
     * Returns all the st nm discount proj masters.
     *
     * @return the st nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm discount proj masters
     * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
     * @return the range of st nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm discount proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm discount proj masters
     * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm discount proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmDiscountProjMaster> findAll(int start, int end,
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

        List<StNmDiscountProjMaster> list = (List<StNmDiscountProjMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMDISCOUNTPROJMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMDISCOUNTPROJMASTER;

                if (pagination) {
                    sql = sql.concat(StNmDiscountProjMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmDiscountProjMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmDiscountProjMaster>(list);
                } else {
                    list = (List<StNmDiscountProjMaster>) QueryUtil.list(q,
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
     * Removes all the st nm discount proj masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmDiscountProjMaster stNmDiscountProjMaster : findAll()) {
            remove(stNmDiscountProjMaster);
        }
    }

    /**
     * Returns the number of st nm discount proj masters.
     *
     * @return the number of st nm discount proj masters
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

                Query q = session.createQuery(_SQL_COUNT_STNMDISCOUNTPROJMASTER);

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
     * Initializes the st nm discount proj master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmDiscountProjMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmDiscountProjMaster>> listenersList = new ArrayList<ModelListener<StNmDiscountProjMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmDiscountProjMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmDiscountProjMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
