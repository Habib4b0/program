package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStChAssumptionsException;
import com.stpl.app.model.StChAssumptions;
import com.stpl.app.model.impl.StChAssumptionsImpl;
import com.stpl.app.model.impl.StChAssumptionsModelImpl;
import com.stpl.app.service.persistence.StChAssumptionsPersistence;

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
 * The persistence implementation for the st ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChAssumptionsPersistence
 * @see StChAssumptionsUtil
 * @generated
 */
public class StChAssumptionsPersistenceImpl extends BasePersistenceImpl<StChAssumptions>
    implements StChAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StChAssumptionsUtil} to access the st ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StChAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            StChAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STCHASSUMPTIONS = "SELECT stChAssumptions FROM StChAssumptions stChAssumptions";
    private static final String _SQL_COUNT_STCHASSUMPTIONS = "SELECT COUNT(stChAssumptions) FROM StChAssumptions stChAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stChAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StChAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "parent", "commentary",
                "projectionDetailsSid", "userId", "quarter",
                "totalDiscountPercentChange", "reasonCodes", "year",
                "totalDiscountPercentProjected", "totalDiscountPercentPrior",
                "stChAssumptionsSid", "chAssumptionsSid", "totalDiscountChange",
                "sessionId", "totalDiscountProjected", "isChecked", "camId",
                "grossTradeSales", "totalDiscountPrior"
            });
    private static StChAssumptions _nullStChAssumptions = new StChAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StChAssumptions> toCacheModel() {
                return _nullStChAssumptionsCacheModel;
            }
        };

    private static CacheModel<StChAssumptions> _nullStChAssumptionsCacheModel = new CacheModel<StChAssumptions>() {
            @Override
            public StChAssumptions toEntityModel() {
                return _nullStChAssumptions;
            }
        };

    public StChAssumptionsPersistenceImpl() {
        setModelClass(StChAssumptions.class);
    }

    /**
     * Caches the st ch assumptions in the entity cache if it is enabled.
     *
     * @param stChAssumptions the st ch assumptions
     */
    @Override
    public void cacheResult(StChAssumptions stChAssumptions) {
        EntityCacheUtil.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey(),
            stChAssumptions);

        stChAssumptions.resetOriginalValues();
    }

    /**
     * Caches the st ch assumptionses in the entity cache if it is enabled.
     *
     * @param stChAssumptionses the st ch assumptionses
     */
    @Override
    public void cacheResult(List<StChAssumptions> stChAssumptionses) {
        for (StChAssumptions stChAssumptions : stChAssumptionses) {
            if (EntityCacheUtil.getResult(
                        StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StChAssumptionsImpl.class,
                        stChAssumptions.getPrimaryKey()) == null) {
                cacheResult(stChAssumptions);
            } else {
                stChAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st ch assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StChAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StChAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st ch assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StChAssumptions stChAssumptions) {
        EntityCacheUtil.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StChAssumptions> stChAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StChAssumptions stChAssumptions : stChAssumptionses) {
            EntityCacheUtil.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
     *
     * @param stChAssumptionsPK the primary key for the new st ch assumptions
     * @return the new st ch assumptions
     */
    @Override
    public StChAssumptions create(StChAssumptionsPK stChAssumptionsPK) {
        StChAssumptions stChAssumptions = new StChAssumptionsImpl();

        stChAssumptions.setNew(true);
        stChAssumptions.setPrimaryKey(stChAssumptionsPK);

        return stChAssumptions;
    }

    /**
     * Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stChAssumptionsPK the primary key of the st ch assumptions
     * @return the st ch assumptions that was removed
     * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions remove(StChAssumptionsPK stChAssumptionsPK)
        throws NoSuchStChAssumptionsException, SystemException {
        return remove((Serializable) stChAssumptionsPK);
    }

    /**
     * Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st ch assumptions
     * @return the st ch assumptions that was removed
     * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions remove(Serializable primaryKey)
        throws NoSuchStChAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StChAssumptions stChAssumptions = (StChAssumptions) session.get(StChAssumptionsImpl.class,
                    primaryKey);

            if (stChAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stChAssumptions);
        } catch (NoSuchStChAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StChAssumptions removeImpl(StChAssumptions stChAssumptions)
        throws SystemException {
        stChAssumptions = toUnwrappedModel(stChAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stChAssumptions)) {
                stChAssumptions = (StChAssumptions) session.get(StChAssumptionsImpl.class,
                        stChAssumptions.getPrimaryKeyObj());
            }

            if (stChAssumptions != null) {
                session.delete(stChAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stChAssumptions != null) {
            clearCache(stChAssumptions);
        }

        return stChAssumptions;
    }

    @Override
    public StChAssumptions updateImpl(
        com.stpl.app.model.StChAssumptions stChAssumptions)
        throws SystemException {
        stChAssumptions = toUnwrappedModel(stChAssumptions);

        boolean isNew = stChAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stChAssumptions.isNew()) {
                session.save(stChAssumptions);

                stChAssumptions.setNew(false);
            } else {
                session.merge(stChAssumptions);
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

        EntityCacheUtil.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey(),
            stChAssumptions);

        return stChAssumptions;
    }

    protected StChAssumptions toUnwrappedModel(StChAssumptions stChAssumptions) {
        if (stChAssumptions instanceof StChAssumptionsImpl) {
            return stChAssumptions;
        }

        StChAssumptionsImpl stChAssumptionsImpl = new StChAssumptionsImpl();

        stChAssumptionsImpl.setNew(stChAssumptions.isNew());
        stChAssumptionsImpl.setPrimaryKey(stChAssumptions.getPrimaryKey());

        stChAssumptionsImpl.setLastModifiedDate(stChAssumptions.getLastModifiedDate());
        stChAssumptionsImpl.setParent(stChAssumptions.isParent());
        stChAssumptionsImpl.setCommentary(stChAssumptions.getCommentary());
        stChAssumptionsImpl.setProjectionDetailsSid(stChAssumptions.getProjectionDetailsSid());
        stChAssumptionsImpl.setUserId(stChAssumptions.getUserId());
        stChAssumptionsImpl.setQuarter(stChAssumptions.getQuarter());
        stChAssumptionsImpl.setTotalDiscountPercentChange(stChAssumptions.getTotalDiscountPercentChange());
        stChAssumptionsImpl.setReasonCodes(stChAssumptions.getReasonCodes());
        stChAssumptionsImpl.setYear(stChAssumptions.getYear());
        stChAssumptionsImpl.setTotalDiscountPercentProjected(stChAssumptions.getTotalDiscountPercentProjected());
        stChAssumptionsImpl.setTotalDiscountPercentPrior(stChAssumptions.getTotalDiscountPercentPrior());
        stChAssumptionsImpl.setStChAssumptionsSid(stChAssumptions.getStChAssumptionsSid());
        stChAssumptionsImpl.setChAssumptionsSid(stChAssumptions.getChAssumptionsSid());
        stChAssumptionsImpl.setTotalDiscountChange(stChAssumptions.getTotalDiscountChange());
        stChAssumptionsImpl.setSessionId(stChAssumptions.getSessionId());
        stChAssumptionsImpl.setTotalDiscountProjected(stChAssumptions.getTotalDiscountProjected());
        stChAssumptionsImpl.setIsChecked(stChAssumptions.isIsChecked());
        stChAssumptionsImpl.setCamId(stChAssumptions.getCamId());
        stChAssumptionsImpl.setGrossTradeSales(stChAssumptions.getGrossTradeSales());
        stChAssumptionsImpl.setTotalDiscountPrior(stChAssumptions.getTotalDiscountPrior());

        return stChAssumptionsImpl;
    }

    /**
     * Returns the st ch assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st ch assumptions
     * @return the st ch assumptions
     * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStChAssumptionsException, SystemException {
        StChAssumptions stChAssumptions = fetchByPrimaryKey(primaryKey);

        if (stChAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stChAssumptions;
    }

    /**
     * Returns the st ch assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStChAssumptionsException} if it could not be found.
     *
     * @param stChAssumptionsPK the primary key of the st ch assumptions
     * @return the st ch assumptions
     * @throws com.stpl.app.NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions findByPrimaryKey(StChAssumptionsPK stChAssumptionsPK)
        throws NoSuchStChAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) stChAssumptionsPK);
    }

    /**
     * Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st ch assumptions
     * @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StChAssumptions stChAssumptions = (StChAssumptions) EntityCacheUtil.getResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                StChAssumptionsImpl.class, primaryKey);

        if (stChAssumptions == _nullStChAssumptions) {
            return null;
        }

        if (stChAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                stChAssumptions = (StChAssumptions) session.get(StChAssumptionsImpl.class,
                        primaryKey);

                if (stChAssumptions != null) {
                    cacheResult(stChAssumptions);
                } else {
                    EntityCacheUtil.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        StChAssumptionsImpl.class, primaryKey,
                        _nullStChAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    StChAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stChAssumptions;
    }

    /**
     * Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stChAssumptionsPK the primary key of the st ch assumptions
     * @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StChAssumptions fetchByPrimaryKey(
        StChAssumptionsPK stChAssumptionsPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) stChAssumptionsPK);
    }

    /**
     * Returns all the st ch assumptionses.
     *
     * @return the st ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st ch assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch assumptionses
     * @param end the upper bound of the range of st ch assumptionses (not inclusive)
     * @return the range of st ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st ch assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st ch assumptionses
     * @param end the upper bound of the range of st ch assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StChAssumptions> findAll(int start, int end,
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

        List<StChAssumptions> list = (List<StChAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STCHASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STCHASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(StChAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StChAssumptions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StChAssumptions>(list);
                } else {
                    list = (List<StChAssumptions>) QueryUtil.list(q,
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
     * Removes all the st ch assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StChAssumptions stChAssumptions : findAll()) {
            remove(stChAssumptions);
        }
    }

    /**
     * Returns the number of st ch assumptionses.
     *
     * @return the number of st ch assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_STCHASSUMPTIONS);

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
     * Initializes the st ch assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StChAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StChAssumptions>> listenersList = new ArrayList<ModelListener<StChAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StChAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StChAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
