package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchChAssumptionsException;
import com.stpl.app.model.ChAssumptions;
import com.stpl.app.model.impl.ChAssumptionsImpl;
import com.stpl.app.model.impl.ChAssumptionsModelImpl;
import com.stpl.app.service.persistence.ChAssumptionsPersistence;

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
 * The persistence implementation for the ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChAssumptionsPersistence
 * @see ChAssumptionsUtil
 * @generated
 */
public class ChAssumptionsPersistenceImpl extends BasePersistenceImpl<ChAssumptions>
    implements ChAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ChAssumptionsUtil} to access the ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ChAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            ChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
            ChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CHASSUMPTIONS = "SELECT chAssumptions FROM ChAssumptions chAssumptions";
    private static final String _SQL_COUNT_CHASSUMPTIONS = "SELECT COUNT(chAssumptions) FROM ChAssumptions chAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "chAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ChAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "parent", "projectionDetailsSid", "commentary", "quarter",
                "totalDiscountPercentChange", "reasonCodes", "year",
                "totalDiscountPercentProjected", "totalDiscountPercentPrior",
                "chAssumptionsSid", "totalDiscountChange",
                "totalDiscountProjected", "camId", "grossTradeSales",
                "totalDiscountPrior"
            });
    private static ChAssumptions _nullChAssumptions = new ChAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ChAssumptions> toCacheModel() {
                return _nullChAssumptionsCacheModel;
            }
        };

    private static CacheModel<ChAssumptions> _nullChAssumptionsCacheModel = new CacheModel<ChAssumptions>() {
            @Override
            public ChAssumptions toEntityModel() {
                return _nullChAssumptions;
            }
        };

    public ChAssumptionsPersistenceImpl() {
        setModelClass(ChAssumptions.class);
    }

    /**
     * Caches the ch assumptions in the entity cache if it is enabled.
     *
     * @param chAssumptions the ch assumptions
     */
    @Override
    public void cacheResult(ChAssumptions chAssumptions) {
        EntityCacheUtil.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsImpl.class, chAssumptions.getPrimaryKey(),
            chAssumptions);

        chAssumptions.resetOriginalValues();
    }

    /**
     * Caches the ch assumptionses in the entity cache if it is enabled.
     *
     * @param chAssumptionses the ch assumptionses
     */
    @Override
    public void cacheResult(List<ChAssumptions> chAssumptionses) {
        for (ChAssumptions chAssumptions : chAssumptionses) {
            if (EntityCacheUtil.getResult(
                        ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        ChAssumptionsImpl.class, chAssumptions.getPrimaryKey()) == null) {
                cacheResult(chAssumptions);
            } else {
                chAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ch assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ChAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ChAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ch assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ChAssumptions chAssumptions) {
        EntityCacheUtil.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsImpl.class, chAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ChAssumptions> chAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ChAssumptions chAssumptions : chAssumptionses) {
            EntityCacheUtil.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                ChAssumptionsImpl.class, chAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
     *
     * @param chAssumptionsSid the primary key for the new ch assumptions
     * @return the new ch assumptions
     */
    @Override
    public ChAssumptions create(int chAssumptionsSid) {
        ChAssumptions chAssumptions = new ChAssumptionsImpl();

        chAssumptions.setNew(true);
        chAssumptions.setPrimaryKey(chAssumptionsSid);

        return chAssumptions;
    }

    /**
     * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param chAssumptionsSid the primary key of the ch assumptions
     * @return the ch assumptions that was removed
     * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions remove(int chAssumptionsSid)
        throws NoSuchChAssumptionsException, SystemException {
        return remove((Serializable) chAssumptionsSid);
    }

    /**
     * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ch assumptions
     * @return the ch assumptions that was removed
     * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions remove(Serializable primaryKey)
        throws NoSuchChAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ChAssumptions chAssumptions = (ChAssumptions) session.get(ChAssumptionsImpl.class,
                    primaryKey);

            if (chAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(chAssumptions);
        } catch (NoSuchChAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ChAssumptions removeImpl(ChAssumptions chAssumptions)
        throws SystemException {
        chAssumptions = toUnwrappedModel(chAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(chAssumptions)) {
                chAssumptions = (ChAssumptions) session.get(ChAssumptionsImpl.class,
                        chAssumptions.getPrimaryKeyObj());
            }

            if (chAssumptions != null) {
                session.delete(chAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (chAssumptions != null) {
            clearCache(chAssumptions);
        }

        return chAssumptions;
    }

    @Override
    public ChAssumptions updateImpl(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws SystemException {
        chAssumptions = toUnwrappedModel(chAssumptions);

        boolean isNew = chAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (chAssumptions.isNew()) {
                session.save(chAssumptions);

                chAssumptions.setNew(false);
            } else {
                session.merge(chAssumptions);
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

        EntityCacheUtil.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            ChAssumptionsImpl.class, chAssumptions.getPrimaryKey(),
            chAssumptions);

        return chAssumptions;
    }

    protected ChAssumptions toUnwrappedModel(ChAssumptions chAssumptions) {
        if (chAssumptions instanceof ChAssumptionsImpl) {
            return chAssumptions;
        }

        ChAssumptionsImpl chAssumptionsImpl = new ChAssumptionsImpl();

        chAssumptionsImpl.setNew(chAssumptions.isNew());
        chAssumptionsImpl.setPrimaryKey(chAssumptions.getPrimaryKey());

        chAssumptionsImpl.setParent(chAssumptions.isParent());
        chAssumptionsImpl.setProjectionDetailsSid(chAssumptions.getProjectionDetailsSid());
        chAssumptionsImpl.setCommentary(chAssumptions.getCommentary());
        chAssumptionsImpl.setQuarter(chAssumptions.getQuarter());
        chAssumptionsImpl.setTotalDiscountPercentChange(chAssumptions.getTotalDiscountPercentChange());
        chAssumptionsImpl.setReasonCodes(chAssumptions.getReasonCodes());
        chAssumptionsImpl.setYear(chAssumptions.getYear());
        chAssumptionsImpl.setTotalDiscountPercentProjected(chAssumptions.getTotalDiscountPercentProjected());
        chAssumptionsImpl.setTotalDiscountPercentPrior(chAssumptions.getTotalDiscountPercentPrior());
        chAssumptionsImpl.setChAssumptionsSid(chAssumptions.getChAssumptionsSid());
        chAssumptionsImpl.setTotalDiscountChange(chAssumptions.getTotalDiscountChange());
        chAssumptionsImpl.setTotalDiscountProjected(chAssumptions.getTotalDiscountProjected());
        chAssumptionsImpl.setCamId(chAssumptions.getCamId());
        chAssumptionsImpl.setGrossTradeSales(chAssumptions.getGrossTradeSales());
        chAssumptionsImpl.setTotalDiscountPrior(chAssumptions.getTotalDiscountPrior());

        return chAssumptionsImpl;
    }

    /**
     * Returns the ch assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ch assumptions
     * @return the ch assumptions
     * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchChAssumptionsException, SystemException {
        ChAssumptions chAssumptions = fetchByPrimaryKey(primaryKey);

        if (chAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return chAssumptions;
    }

    /**
     * Returns the ch assumptions with the primary key or throws a {@link com.stpl.app.NoSuchChAssumptionsException} if it could not be found.
     *
     * @param chAssumptionsSid the primary key of the ch assumptions
     * @return the ch assumptions
     * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions findByPrimaryKey(int chAssumptionsSid)
        throws NoSuchChAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) chAssumptionsSid);
    }

    /**
     * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ch assumptions
     * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ChAssumptions chAssumptions = (ChAssumptions) EntityCacheUtil.getResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                ChAssumptionsImpl.class, primaryKey);

        if (chAssumptions == _nullChAssumptions) {
            return null;
        }

        if (chAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                chAssumptions = (ChAssumptions) session.get(ChAssumptionsImpl.class,
                        primaryKey);

                if (chAssumptions != null) {
                    cacheResult(chAssumptions);
                } else {
                    EntityCacheUtil.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        ChAssumptionsImpl.class, primaryKey, _nullChAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    ChAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return chAssumptions;
    }

    /**
     * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param chAssumptionsSid the primary key of the ch assumptions
     * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ChAssumptions fetchByPrimaryKey(int chAssumptionsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) chAssumptionsSid);
    }

    /**
     * Returns all the ch assumptionses.
     *
     * @return the ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ch assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch assumptionses
     * @param end the upper bound of the range of ch assumptionses (not inclusive)
     * @return the range of ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ch assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ch assumptionses
     * @param end the upper bound of the range of ch assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ch assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ChAssumptions> findAll(int start, int end,
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

        List<ChAssumptions> list = (List<ChAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CHASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CHASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(ChAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ChAssumptions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ChAssumptions>(list);
                } else {
                    list = (List<ChAssumptions>) QueryUtil.list(q,
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
     * Removes all the ch assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ChAssumptions chAssumptions : findAll()) {
            remove(chAssumptions);
        }
    }

    /**
     * Returns the number of ch assumptionses.
     *
     * @return the number of ch assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_CHASSUMPTIONS);

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
     * Initializes the ch assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ChAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ChAssumptions>> listenersList = new ArrayList<ModelListener<ChAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ChAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ChAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
