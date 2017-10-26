package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStNmAssumptionsException;
import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.model.impl.StNmAssumptionsImpl;
import com.stpl.app.model.impl.StNmAssumptionsModelImpl;
import com.stpl.app.service.persistence.StNmAssumptionsPersistence;

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
 * The persistence implementation for the st nm assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmAssumptionsPersistence
 * @see StNmAssumptionsUtil
 * @generated
 */
public class StNmAssumptionsPersistenceImpl extends BasePersistenceImpl<StNmAssumptions>
    implements StNmAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StNmAssumptionsUtil} to access the st nm assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StNmAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StNmAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StNmAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STNMASSUMPTIONS = "SELECT stNmAssumptions FROM StNmAssumptions stNmAssumptions";
    private static final String _SQL_COUNT_STNMASSUMPTIONS = "SELECT COUNT(stNmAssumptions) FROM StNmAssumptions stNmAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stNmAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StNmAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "parent", "projectionPeriod", "commentary",
                "nmAssumptionsSid", "projectionDetailsSid", "netSalesPrior",
                "userId", "grossSalesPercentChange",
                "totalDiscountPercentChange", "reasonCodes",
                "totalDiscountPercentProjected", "totalDiscountPercentPrior",
                "netSalesProjected", "stNmAssumptionsSid", "grossSalesProjected",
                "sessionId", "grossSalesPrior", "isChecked", "camId",
                "netSalesPercentChange", "segment"
            });
    private static StNmAssumptions _nullStNmAssumptions = new StNmAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StNmAssumptions> toCacheModel() {
                return _nullStNmAssumptionsCacheModel;
            }
        };

    private static CacheModel<StNmAssumptions> _nullStNmAssumptionsCacheModel = new CacheModel<StNmAssumptions>() {
            @Override
            public StNmAssumptions toEntityModel() {
                return _nullStNmAssumptions;
            }
        };

    public StNmAssumptionsPersistenceImpl() {
        setModelClass(StNmAssumptions.class);
    }

    /**
     * Caches the st nm assumptions in the entity cache if it is enabled.
     *
     * @param stNmAssumptions the st nm assumptions
     */
    @Override
    public void cacheResult(StNmAssumptions stNmAssumptions) {
        EntityCacheUtil.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey(),
            stNmAssumptions);

        stNmAssumptions.resetOriginalValues();
    }

    /**
     * Caches the st nm assumptionses in the entity cache if it is enabled.
     *
     * @param stNmAssumptionses the st nm assumptionses
     */
    @Override
    public void cacheResult(List<StNmAssumptions> stNmAssumptionses) {
        for (StNmAssumptions stNmAssumptions : stNmAssumptionses) {
            if (EntityCacheUtil.getResult(
                        StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StNmAssumptionsImpl.class,
                        stNmAssumptions.getPrimaryKey()) == null) {
                cacheResult(stNmAssumptions);
            } else {
                stNmAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st nm assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StNmAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StNmAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st nm assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StNmAssumptions stNmAssumptions) {
        EntityCacheUtil.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StNmAssumptions> stNmAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StNmAssumptions stNmAssumptions : stNmAssumptionses) {
            EntityCacheUtil.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
     *
     * @param stNmAssumptionsPK the primary key for the new st nm assumptions
     * @return the new st nm assumptions
     */
    @Override
    public StNmAssumptions create(StNmAssumptionsPK stNmAssumptionsPK) {
        StNmAssumptions stNmAssumptions = new StNmAssumptionsImpl();

        stNmAssumptions.setNew(true);
        stNmAssumptions.setPrimaryKey(stNmAssumptionsPK);

        return stNmAssumptions;
    }

    /**
     * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stNmAssumptionsPK the primary key of the st nm assumptions
     * @return the st nm assumptions that was removed
     * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions remove(StNmAssumptionsPK stNmAssumptionsPK)
        throws NoSuchStNmAssumptionsException, SystemException {
        return remove((Serializable) stNmAssumptionsPK);
    }

    /**
     * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st nm assumptions
     * @return the st nm assumptions that was removed
     * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions remove(Serializable primaryKey)
        throws NoSuchStNmAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StNmAssumptions stNmAssumptions = (StNmAssumptions) session.get(StNmAssumptionsImpl.class,
                    primaryKey);

            if (stNmAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStNmAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stNmAssumptions);
        } catch (NoSuchStNmAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StNmAssumptions removeImpl(StNmAssumptions stNmAssumptions)
        throws SystemException {
        stNmAssumptions = toUnwrappedModel(stNmAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stNmAssumptions)) {
                stNmAssumptions = (StNmAssumptions) session.get(StNmAssumptionsImpl.class,
                        stNmAssumptions.getPrimaryKeyObj());
            }

            if (stNmAssumptions != null) {
                session.delete(stNmAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stNmAssumptions != null) {
            clearCache(stNmAssumptions);
        }

        return stNmAssumptions;
    }

    @Override
    public StNmAssumptions updateImpl(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws SystemException {
        stNmAssumptions = toUnwrappedModel(stNmAssumptions);

        boolean isNew = stNmAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stNmAssumptions.isNew()) {
                session.save(stNmAssumptions);

                stNmAssumptions.setNew(false);
            } else {
                session.merge(stNmAssumptions);
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

        EntityCacheUtil.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey(),
            stNmAssumptions);

        return stNmAssumptions;
    }

    protected StNmAssumptions toUnwrappedModel(StNmAssumptions stNmAssumptions) {
        if (stNmAssumptions instanceof StNmAssumptionsImpl) {
            return stNmAssumptions;
        }

        StNmAssumptionsImpl stNmAssumptionsImpl = new StNmAssumptionsImpl();

        stNmAssumptionsImpl.setNew(stNmAssumptions.isNew());
        stNmAssumptionsImpl.setPrimaryKey(stNmAssumptions.getPrimaryKey());

        stNmAssumptionsImpl.setLastModifiedDate(stNmAssumptions.getLastModifiedDate());
        stNmAssumptionsImpl.setParent(stNmAssumptions.isParent());
        stNmAssumptionsImpl.setProjectionPeriod(stNmAssumptions.getProjectionPeriod());
        stNmAssumptionsImpl.setCommentary(stNmAssumptions.getCommentary());
        stNmAssumptionsImpl.setNmAssumptionsSid(stNmAssumptions.getNmAssumptionsSid());
        stNmAssumptionsImpl.setProjectionDetailsSid(stNmAssumptions.getProjectionDetailsSid());
        stNmAssumptionsImpl.setNetSalesPrior(stNmAssumptions.getNetSalesPrior());
        stNmAssumptionsImpl.setUserId(stNmAssumptions.getUserId());
        stNmAssumptionsImpl.setGrossSalesPercentChange(stNmAssumptions.getGrossSalesPercentChange());
        stNmAssumptionsImpl.setTotalDiscountPercentChange(stNmAssumptions.getTotalDiscountPercentChange());
        stNmAssumptionsImpl.setReasonCodes(stNmAssumptions.getReasonCodes());
        stNmAssumptionsImpl.setTotalDiscountPercentProjected(stNmAssumptions.getTotalDiscountPercentProjected());
        stNmAssumptionsImpl.setTotalDiscountPercentPrior(stNmAssumptions.getTotalDiscountPercentPrior());
        stNmAssumptionsImpl.setNetSalesProjected(stNmAssumptions.getNetSalesProjected());
        stNmAssumptionsImpl.setStNmAssumptionsSid(stNmAssumptions.getStNmAssumptionsSid());
        stNmAssumptionsImpl.setGrossSalesProjected(stNmAssumptions.getGrossSalesProjected());
        stNmAssumptionsImpl.setSessionId(stNmAssumptions.getSessionId());
        stNmAssumptionsImpl.setGrossSalesPrior(stNmAssumptions.getGrossSalesPrior());
        stNmAssumptionsImpl.setIsChecked(stNmAssumptions.isIsChecked());
        stNmAssumptionsImpl.setCamId(stNmAssumptions.getCamId());
        stNmAssumptionsImpl.setNetSalesPercentChange(stNmAssumptions.getNetSalesPercentChange());
        stNmAssumptionsImpl.setSegment(stNmAssumptions.getSegment());

        return stNmAssumptionsImpl;
    }

    /**
     * Returns the st nm assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st nm assumptions
     * @return the st nm assumptions
     * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStNmAssumptionsException, SystemException {
        StNmAssumptions stNmAssumptions = fetchByPrimaryKey(primaryKey);

        if (stNmAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStNmAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stNmAssumptions;
    }

    /**
     * Returns the st nm assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNmAssumptionsException} if it could not be found.
     *
     * @param stNmAssumptionsPK the primary key of the st nm assumptions
     * @return the st nm assumptions
     * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions findByPrimaryKey(StNmAssumptionsPK stNmAssumptionsPK)
        throws NoSuchStNmAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) stNmAssumptionsPK);
    }

    /**
     * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st nm assumptions
     * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StNmAssumptions stNmAssumptions = (StNmAssumptions) EntityCacheUtil.getResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StNmAssumptionsImpl.class, primaryKey);

        if (stNmAssumptions == _nullStNmAssumptions) {
            return null;
        }

        if (stNmAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                stNmAssumptions = (StNmAssumptions) session.get(StNmAssumptionsImpl.class,
                        primaryKey);

                if (stNmAssumptions != null) {
                    cacheResult(stNmAssumptions);
                } else {
                    EntityCacheUtil.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StNmAssumptionsImpl.class, primaryKey,
                        _nullStNmAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    StNmAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stNmAssumptions;
    }

    /**
     * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stNmAssumptionsPK the primary key of the st nm assumptions
     * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StNmAssumptions fetchByPrimaryKey(
        StNmAssumptionsPK stNmAssumptionsPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stNmAssumptionsPK);
    }

    /**
     * Returns all the st nm assumptionses.
     *
     * @return the st nm assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st nm assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm assumptionses
     * @param end the upper bound of the range of st nm assumptionses (not inclusive)
     * @return the range of st nm assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st nm assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st nm assumptionses
     * @param end the upper bound of the range of st nm assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st nm assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StNmAssumptions> findAll(int start, int end,
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

        List<StNmAssumptions> list = (List<StNmAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STNMASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STNMASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(StNmAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StNmAssumptions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StNmAssumptions>(list);
                } else {
                    list = (List<StNmAssumptions>) QueryUtil.list(q,
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
     * Removes all the st nm assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StNmAssumptions stNmAssumptions : findAll()) {
            remove(stNmAssumptions);
        }
    }

    /**
     * Returns the number of st nm assumptionses.
     *
     * @return the number of st nm assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_STNMASSUMPTIONS);

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
     * Initializes the st nm assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StNmAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StNmAssumptions>> listenersList = new ArrayList<ModelListener<StNmAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StNmAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StNmAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
