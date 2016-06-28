package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStMSupplementalDiscMasterException;
import com.stpl.app.model.StMSupplementalDiscMaster;
import com.stpl.app.model.impl.StMSupplementalDiscMasterImpl;
import com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPersistence;

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
 * The persistence implementation for the st m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscMasterPersistence
 * @see StMSupplementalDiscMasterUtil
 * @generated
 */
public class StMSupplementalDiscMasterPersistenceImpl
    extends BasePersistenceImpl<StMSupplementalDiscMaster>
    implements StMSupplementalDiscMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StMSupplementalDiscMasterUtil} to access the st m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StMSupplementalDiscMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
            StMSupplementalDiscMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
            StMSupplementalDiscMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_STMSUPPLEMENTALDISCMASTER = "SELECT stMSupplementalDiscMaster FROM StMSupplementalDiscMaster stMSupplementalDiscMaster";
    private static final String _SQL_COUNT_STMSUPPLEMENTALDISCMASTER = "SELECT COUNT(stMSupplementalDiscMaster) FROM StMSupplementalDiscMaster stMSupplementalDiscMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stMSupplementalDiscMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMSupplementalDiscMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StMSupplementalDiscMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualDiscountRate2", "actualDiscountRate1", "marketType",
                "actualMethodology", "actualContractPrice", "userId",
                "lastModifiedDate", "projectionDetailsSid", "actualDiscount",
                "sessionId", "checkRecord", "contractEndDate"
            });
    private static StMSupplementalDiscMaster _nullStMSupplementalDiscMaster = new StMSupplementalDiscMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StMSupplementalDiscMaster> toCacheModel() {
                return _nullStMSupplementalDiscMasterCacheModel;
            }
        };

    private static CacheModel<StMSupplementalDiscMaster> _nullStMSupplementalDiscMasterCacheModel =
        new CacheModel<StMSupplementalDiscMaster>() {
            @Override
            public StMSupplementalDiscMaster toEntityModel() {
                return _nullStMSupplementalDiscMaster;
            }
        };

    public StMSupplementalDiscMasterPersistenceImpl() {
        setModelClass(StMSupplementalDiscMaster.class);
    }

    /**
     * Caches the st m supplemental disc master in the entity cache if it is enabled.
     *
     * @param stMSupplementalDiscMaster the st m supplemental disc master
     */
    @Override
    public void cacheResult(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        EntityCacheUtil.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterImpl.class,
            stMSupplementalDiscMaster.getPrimaryKey(), stMSupplementalDiscMaster);

        stMSupplementalDiscMaster.resetOriginalValues();
    }

    /**
     * Caches the st m supplemental disc masters in the entity cache if it is enabled.
     *
     * @param stMSupplementalDiscMasters the st m supplemental disc masters
     */
    @Override
    public void cacheResult(
        List<StMSupplementalDiscMaster> stMSupplementalDiscMasters) {
        for (StMSupplementalDiscMaster stMSupplementalDiscMaster : stMSupplementalDiscMasters) {
            if (EntityCacheUtil.getResult(
                        StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StMSupplementalDiscMasterImpl.class,
                        stMSupplementalDiscMaster.getPrimaryKey()) == null) {
                cacheResult(stMSupplementalDiscMaster);
            } else {
                stMSupplementalDiscMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st m supplemental disc masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StMSupplementalDiscMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StMSupplementalDiscMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st m supplemental disc master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        EntityCacheUtil.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterImpl.class,
            stMSupplementalDiscMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StMSupplementalDiscMaster> stMSupplementalDiscMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StMSupplementalDiscMaster stMSupplementalDiscMaster : stMSupplementalDiscMasters) {
            EntityCacheUtil.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                StMSupplementalDiscMasterImpl.class,
                stMSupplementalDiscMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
     *
     * @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
     * @return the new st m supplemental disc master
     */
    @Override
    public StMSupplementalDiscMaster create(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
        StMSupplementalDiscMaster stMSupplementalDiscMaster = new StMSupplementalDiscMasterImpl();

        stMSupplementalDiscMaster.setNew(true);
        stMSupplementalDiscMaster.setPrimaryKey(stMSupplementalDiscMasterPK);

        return stMSupplementalDiscMaster;
    }

    /**
     * Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master that was removed
     * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster remove(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws NoSuchStMSupplementalDiscMasterException, SystemException {
        return remove((Serializable) stMSupplementalDiscMasterPK);
    }

    /**
     * Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master that was removed
     * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster remove(Serializable primaryKey)
        throws NoSuchStMSupplementalDiscMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StMSupplementalDiscMaster stMSupplementalDiscMaster = (StMSupplementalDiscMaster) session.get(StMSupplementalDiscMasterImpl.class,
                    primaryKey);

            if (stMSupplementalDiscMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stMSupplementalDiscMaster);
        } catch (NoSuchStMSupplementalDiscMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StMSupplementalDiscMaster removeImpl(
        StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws SystemException {
        stMSupplementalDiscMaster = toUnwrappedModel(stMSupplementalDiscMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stMSupplementalDiscMaster)) {
                stMSupplementalDiscMaster = (StMSupplementalDiscMaster) session.get(StMSupplementalDiscMasterImpl.class,
                        stMSupplementalDiscMaster.getPrimaryKeyObj());
            }

            if (stMSupplementalDiscMaster != null) {
                session.delete(stMSupplementalDiscMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stMSupplementalDiscMaster != null) {
            clearCache(stMSupplementalDiscMaster);
        }

        return stMSupplementalDiscMaster;
    }

    @Override
    public StMSupplementalDiscMaster updateImpl(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws SystemException {
        stMSupplementalDiscMaster = toUnwrappedModel(stMSupplementalDiscMaster);

        boolean isNew = stMSupplementalDiscMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stMSupplementalDiscMaster.isNew()) {
                session.save(stMSupplementalDiscMaster);

                stMSupplementalDiscMaster.setNew(false);
            } else {
                session.merge(stMSupplementalDiscMaster);
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

        EntityCacheUtil.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
            StMSupplementalDiscMasterImpl.class,
            stMSupplementalDiscMaster.getPrimaryKey(), stMSupplementalDiscMaster);

        return stMSupplementalDiscMaster;
    }

    protected StMSupplementalDiscMaster toUnwrappedModel(
        StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        if (stMSupplementalDiscMaster instanceof StMSupplementalDiscMasterImpl) {
            return stMSupplementalDiscMaster;
        }

        StMSupplementalDiscMasterImpl stMSupplementalDiscMasterImpl = new StMSupplementalDiscMasterImpl();

        stMSupplementalDiscMasterImpl.setNew(stMSupplementalDiscMaster.isNew());
        stMSupplementalDiscMasterImpl.setPrimaryKey(stMSupplementalDiscMaster.getPrimaryKey());

        stMSupplementalDiscMasterImpl.setActualDiscountRate2(stMSupplementalDiscMaster.getActualDiscountRate2());
        stMSupplementalDiscMasterImpl.setActualDiscountRate1(stMSupplementalDiscMaster.getActualDiscountRate1());
        stMSupplementalDiscMasterImpl.setMarketType(stMSupplementalDiscMaster.getMarketType());
        stMSupplementalDiscMasterImpl.setActualMethodology(stMSupplementalDiscMaster.getActualMethodology());
        stMSupplementalDiscMasterImpl.setActualContractPrice(stMSupplementalDiscMaster.getActualContractPrice());
        stMSupplementalDiscMasterImpl.setUserId(stMSupplementalDiscMaster.getUserId());
        stMSupplementalDiscMasterImpl.setLastModifiedDate(stMSupplementalDiscMaster.getLastModifiedDate());
        stMSupplementalDiscMasterImpl.setProjectionDetailsSid(stMSupplementalDiscMaster.getProjectionDetailsSid());
        stMSupplementalDiscMasterImpl.setActualDiscount(stMSupplementalDiscMaster.getActualDiscount());
        stMSupplementalDiscMasterImpl.setSessionId(stMSupplementalDiscMaster.getSessionId());
        stMSupplementalDiscMasterImpl.setCheckRecord(stMSupplementalDiscMaster.getCheckRecord());
        stMSupplementalDiscMasterImpl.setContractEndDate(stMSupplementalDiscMaster.getContractEndDate());

        return stMSupplementalDiscMasterImpl;
    }

    /**
     * Returns the st m supplemental disc master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master
     * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStMSupplementalDiscMasterException, SystemException {
        StMSupplementalDiscMaster stMSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

        if (stMSupplementalDiscMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stMSupplementalDiscMaster;
    }

    /**
     * Returns the st m supplemental disc master with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscMasterException} if it could not be found.
     *
     * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master
     * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster findByPrimaryKey(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws NoSuchStMSupplementalDiscMasterException, SystemException {
        return findByPrimaryKey((Serializable) stMSupplementalDiscMasterPK);
    }

    /**
     * Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StMSupplementalDiscMaster stMSupplementalDiscMaster = (StMSupplementalDiscMaster) EntityCacheUtil.getResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                StMSupplementalDiscMasterImpl.class, primaryKey);

        if (stMSupplementalDiscMaster == _nullStMSupplementalDiscMaster) {
            return null;
        }

        if (stMSupplementalDiscMaster == null) {
            Session session = null;

            try {
                session = openSession();

                stMSupplementalDiscMaster = (StMSupplementalDiscMaster) session.get(StMSupplementalDiscMasterImpl.class,
                        primaryKey);

                if (stMSupplementalDiscMaster != null) {
                    cacheResult(stMSupplementalDiscMaster);
                } else {
                    EntityCacheUtil.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                        StMSupplementalDiscMasterImpl.class, primaryKey,
                        _nullStMSupplementalDiscMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
                    StMSupplementalDiscMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stMSupplementalDiscMaster;
    }

    /**
     * Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
     * @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StMSupplementalDiscMaster fetchByPrimaryKey(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stMSupplementalDiscMasterPK);
    }

    /**
     * Returns all the st m supplemental disc masters.
     *
     * @return the st m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st m supplemental disc masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st m supplemental disc masters
     * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
     * @return the range of st m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st m supplemental disc masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st m supplemental disc masters
     * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st m supplemental disc masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StMSupplementalDiscMaster> findAll(int start, int end,
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

        List<StMSupplementalDiscMaster> list = (List<StMSupplementalDiscMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STMSUPPLEMENTALDISCMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STMSUPPLEMENTALDISCMASTER;

                if (pagination) {
                    sql = sql.concat(StMSupplementalDiscMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StMSupplementalDiscMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StMSupplementalDiscMaster>(list);
                } else {
                    list = (List<StMSupplementalDiscMaster>) QueryUtil.list(q,
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
     * Removes all the st m supplemental disc masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StMSupplementalDiscMaster stMSupplementalDiscMaster : findAll()) {
            remove(stMSupplementalDiscMaster);
        }
    }

    /**
     * Returns the number of st m supplemental disc masters.
     *
     * @return the number of st m supplemental disc masters
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

                Query q = session.createQuery(_SQL_COUNT_STMSUPPLEMENTALDISCMASTER);

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
     * Initializes the st m supplemental disc master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StMSupplementalDiscMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StMSupplementalDiscMaster>> listenersList = new ArrayList<ModelListener<StMSupplementalDiscMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StMSupplementalDiscMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StMSupplementalDiscMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
