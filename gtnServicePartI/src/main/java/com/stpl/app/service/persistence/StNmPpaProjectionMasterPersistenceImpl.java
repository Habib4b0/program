package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmPpaProjectionMasterException;
import com.stpl.app.model.StNmPpaProjectionMaster;
import com.stpl.app.model.impl.StNmPpaProjectionMasterImpl;
import com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPersistence;

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
 * The persistence implementation for the st nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMasterPersistence
 * @see StNmPpaProjectionMasterUtil
 * @generated
 */
public class StNmPpaProjectionMasterPersistenceImpl extends BasePersistenceImpl<StNmPpaProjectionMaster>
    implements StNmPpaProjectionMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmPpaProjectionMasterUtil} to access the st nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmPpaProjectionMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            StNmPpaProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
            StNmPpaProjectionMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMPPAPROJECTIONMASTER = "SELECT stNmPpaProjectionMaster FROM StNmPpaProjectionMaster stNmPpaProjectionMaster";
    private static final String _SQL_COUNT_STNMPPAPROJECTIONMASTER = "SELECT COUNT(stNmPpaProjectionMaster) FROM StNmPpaProjectionMaster stNmPpaProjectionMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmPpaProjectionMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmPpaProjectionMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmPpaProjectionMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "checkRecord", "userGroup",
                "projectionDetailsSid", "userId", "sessionId", "priceBasis",
                "priceProtectionEndDate", "priceProtectionStartDate",
                "actualPriceCap"
            });
    private static StNmPpaProjectionMaster _nullStNmPpaProjectionMaster = new StNmPpaProjectionMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmPpaProjectionMaster> toCacheModel() {
                return _nullStNmPpaProjectionMasterCacheModel;
            }
        };

    private static CacheModel<StNmPpaProjectionMaster> _nullStNmPpaProjectionMasterCacheModel =
        new CacheModel<StNmPpaProjectionMaster>() {
            @Override
            public StNmPpaProjectionMaster toEntityModel() {
                return _nullStNmPpaProjectionMaster;
            }
        };

    public StNmPpaProjectionMasterPersistenceImpl() {
        setModelClass(StNmPpaProjectionMaster.class);
    }

    /**
     * Caches the st nm ppa projection master in the entity cache if it is enabled.
     *
     * @param stNmPpaProjectionMaster the st nm ppa projection master
     */
    @Override
    public void cacheResult(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        EntityCacheUtil.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterImpl.class,
            stNmPpaProjectionMaster.getPrimaryKey(), stNmPpaProjectionMaster);

        stNmPpaProjectionMaster.resetOriginalValues();
    }

    /**
     * Caches the st nm ppa projection masters in the entity cache if it is enabled.
     *
     * @param stNmPpaProjectionMasters the st nm ppa projection masters
     */
    @Override
    public void cacheResult(
        List<StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
        for (StNmPpaProjectionMaster stNmPpaProjectionMaster : stNmPpaProjectionMasters) {
            if (EntityCacheUtil.getResult(
                        StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StNmPpaProjectionMasterImpl.class,
                        stNmPpaProjectionMaster.getPrimaryKey()) == null) {
                cacheResult(stNmPpaProjectionMaster);
            } else {
                stNmPpaProjectionMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm ppa projection masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmPpaProjectionMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmPpaProjectionMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm ppa projection master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        EntityCacheUtil.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterImpl.class,
            stNmPpaProjectionMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmPpaProjectionMaster stNmPpaProjectionMaster : stNmPpaProjectionMasters) {
            EntityCacheUtil.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                StNmPpaProjectionMasterImpl.class,
                stNmPpaProjectionMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
     *
     * @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
     * @return the new st nm ppa projection master
     */
    @Override
    public StNmPpaProjectionMaster create(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
        StNmPpaProjectionMaster stNmPpaProjectionMaster = new StNmPpaProjectionMasterImpl();

        stNmPpaProjectionMaster.setNew(true);
        stNmPpaProjectionMaster.setPrimaryKey(stNmPpaProjectionMasterPK);

        return stNmPpaProjectionMaster;
    }

    /**
     * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master that was removed
     * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster remove(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws NoSuchStNmPpaProjectionMasterException, SystemException {
        return remove((Serializable) stNmPpaProjectionMasterPK);
    }

    /**
     * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master that was removed
     * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster remove(Serializable primaryKey)
        throws NoSuchStNmPpaProjectionMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmPpaProjectionMaster stNmPpaProjectionMaster = (StNmPpaProjectionMaster) session.get(StNmPpaProjectionMasterImpl.class,
                    primaryKey);

            if (stNmPpaProjectionMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmPpaProjectionMaster);
        } catch (NoSuchStNmPpaProjectionMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmPpaProjectionMaster removeImpl(
        StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws SystemException {
        stNmPpaProjectionMaster = toUnwrappedModel(stNmPpaProjectionMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmPpaProjectionMaster)) {
                stNmPpaProjectionMaster = (StNmPpaProjectionMaster) session.get(StNmPpaProjectionMasterImpl.class,
                        stNmPpaProjectionMaster.getPrimaryKeyObj());
            }

            if (stNmPpaProjectionMaster != null) {
                session.delete(stNmPpaProjectionMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmPpaProjectionMaster != null) {
            clearCache(stNmPpaProjectionMaster);
        }

        return stNmPpaProjectionMaster;
    }

    @Override
    public StNmPpaProjectionMaster updateImpl(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws SystemException {
        stNmPpaProjectionMaster = toUnwrappedModel(stNmPpaProjectionMaster);

        boolean isNew = stNmPpaProjectionMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmPpaProjectionMaster.isNew()) {
                session.save(stNmPpaProjectionMaster);

                stNmPpaProjectionMaster.setNew(false);
            } else {
                session.merge(stNmPpaProjectionMaster);
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

        EntityCacheUtil.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
            StNmPpaProjectionMasterImpl.class,
            stNmPpaProjectionMaster.getPrimaryKey(), stNmPpaProjectionMaster);

        return stNmPpaProjectionMaster;
    }

    protected StNmPpaProjectionMaster toUnwrappedModel(
        StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        if (stNmPpaProjectionMaster instanceof StNmPpaProjectionMasterImpl) {
            return stNmPpaProjectionMaster;
        }

        StNmPpaProjectionMasterImpl stNmPpaProjectionMasterImpl = new StNmPpaProjectionMasterImpl();

        stNmPpaProjectionMasterImpl.setNew(stNmPpaProjectionMaster.isNew());
        stNmPpaProjectionMasterImpl.setPrimaryKey(stNmPpaProjectionMaster.getPrimaryKey());

        stNmPpaProjectionMasterImpl.setLastModifiedDate(stNmPpaProjectionMaster.getLastModifiedDate());
        stNmPpaProjectionMasterImpl.setCheckRecord(stNmPpaProjectionMaster.isCheckRecord());
        stNmPpaProjectionMasterImpl.setUserGroup(stNmPpaProjectionMaster.getUserGroup());
        stNmPpaProjectionMasterImpl.setProjectionDetailsSid(stNmPpaProjectionMaster.getProjectionDetailsSid());
        stNmPpaProjectionMasterImpl.setUserId(stNmPpaProjectionMaster.getUserId());
        stNmPpaProjectionMasterImpl.setSessionId(stNmPpaProjectionMaster.getSessionId());
        stNmPpaProjectionMasterImpl.setPriceBasis(stNmPpaProjectionMaster.getPriceBasis());
        stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(stNmPpaProjectionMaster.getPriceProtectionEndDate());
        stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(stNmPpaProjectionMaster.getPriceProtectionStartDate());
        stNmPpaProjectionMasterImpl.setActualPriceCap(stNmPpaProjectionMaster.getActualPriceCap());

        return stNmPpaProjectionMasterImpl;
    }

    /**
     * Returns the st nm ppa projection master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master
     * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmPpaProjectionMasterException, SystemException {
        StNmPpaProjectionMaster stNmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

        if (stNmPpaProjectionMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmPpaProjectionMaster;
    }

    /**
     * Returns the st nm ppa projection master with the primary key or throws a {@link com.stpl.app.NoSuchStNmPpaProjectionMasterException} if it could not be found.
     *
     * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master
     * @throws com.stpl.app.NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster findByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws NoSuchStNmPpaProjectionMasterException, SystemException {
        return findByPrimaryKey((Serializable) stNmPpaProjectionMasterPK);
    }

    /**
     * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmPpaProjectionMaster stNmPpaProjectionMaster = (StNmPpaProjectionMaster) EntityCacheUtil.getResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                StNmPpaProjectionMasterImpl.class, primaryKey);

        if (stNmPpaProjectionMaster == _nullStNmPpaProjectionMaster) {
            return null;
        }

        if (stNmPpaProjectionMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stNmPpaProjectionMaster = (StNmPpaProjectionMaster) session.get(StNmPpaProjectionMasterImpl.class,
                        primaryKey);

                if (stNmPpaProjectionMaster != null) {
                    cacheResult(stNmPpaProjectionMaster);
                } else {
                    EntityCacheUtil.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StNmPpaProjectionMasterImpl.class, primaryKey,
                        _nullStNmPpaProjectionMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StNmPpaProjectionMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmPpaProjectionMaster;
    }

    /**
     * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
     * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmPpaProjectionMaster fetchByPrimaryKey(
        StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmPpaProjectionMasterPK);
    }

    /**
     * Returns all the st nm ppa projection masters.
     *
     * @return the st nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjectionMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm ppa projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm ppa projection masters
     * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
     * @return the range of st nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjectionMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm ppa projection masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm ppa projection masters
     * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm ppa projection masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmPpaProjectionMaster> findAll(int start, int end,
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

        List<StNmPpaProjectionMaster> list = (List<StNmPpaProjectionMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMPPAPROJECTIONMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMPPAPROJECTIONMASTER;

                if (pagination) {
                    sql = sql.concat(StNmPpaProjectionMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmPpaProjectionMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmPpaProjectionMaster>(list);
                } else {
                    list = (List<StNmPpaProjectionMaster>) QueryUtil.list(q,
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
     * Removes all the st nm ppa projection masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmPpaProjectionMaster stNmPpaProjectionMaster : findAll()) {
            remove(stNmPpaProjectionMaster);
        }
    }

    /**
     * Returns the number of st nm ppa projection masters.
     *
     * @return the number of st nm ppa projection masters
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

                Query q = session.createQuery(_SQL_COUNT_STNMPPAPROJECTIONMASTER);

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
     * Initializes the st nm ppa projection master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmPpaProjectionMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmPpaProjectionMaster>> listenersList = new ArrayList<ModelListener<StNmPpaProjectionMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmPpaProjectionMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmPpaProjectionMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
