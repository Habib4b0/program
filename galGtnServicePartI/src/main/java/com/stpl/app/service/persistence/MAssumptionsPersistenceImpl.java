package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMAssumptionsException;
import com.stpl.app.model.MAssumptions;
import com.stpl.app.model.impl.MAssumptionsImpl;
import com.stpl.app.model.impl.MAssumptionsModelImpl;
import com.stpl.app.service.persistence.MAssumptionsPersistence;

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
 * The persistence implementation for the m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MAssumptionsPersistence
 * @see MAssumptionsUtil
 * @generated
 */
public class MAssumptionsPersistenceImpl extends BasePersistenceImpl<MAssumptions>
    implements MAssumptionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MAssumptionsUtil} to access the m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MAssumptionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsModelImpl.FINDER_CACHE_ENABLED, MAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsModelImpl.FINDER_CACHE_ENABLED, MAssumptionsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MASSUMPTIONS = "SELECT mAssumptions FROM MAssumptions mAssumptions";
    private static final String _SQL_COUNT_MASSUMPTIONS = "SELECT COUNT(mAssumptions) FROM MAssumptions mAssumptions";
    private static final String _ORDER_BY_ENTITY_ALIAS = "mAssumptions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MAssumptions exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MAssumptionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "grossSalesPercentChange", "grossSalesPrior", "projYear",
                "totalDiscountPercentProjected", "camId", "commentary",
                "grossSalesProjected", "totalDiscountPercentChange",
                "totalDiscountPercentPrior", "netSalesPercentChange", "parent",
                "projectionPeriod", "projectionDetailsSid", "netSalesPrior",
                "netSalesProjected", "reasonCodes", "mAssumptionsSid"
            });
    private static MAssumptions _nullMAssumptions = new MAssumptionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MAssumptions> toCacheModel() {
                return _nullMAssumptionsCacheModel;
            }
        };

    private static CacheModel<MAssumptions> _nullMAssumptionsCacheModel = new CacheModel<MAssumptions>() {
            @Override
            public MAssumptions toEntityModel() {
                return _nullMAssumptions;
            }
        };

    public MAssumptionsPersistenceImpl() {
        setModelClass(MAssumptions.class);
    }

    /**
     * Caches the m assumptions in the entity cache if it is enabled.
     *
     * @param mAssumptions the m assumptions
     */
    @Override
    public void cacheResult(MAssumptions mAssumptions) {
        EntityCacheUtil.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsImpl.class, mAssumptions.getPrimaryKey(), mAssumptions);

        mAssumptions.resetOriginalValues();
    }

    /**
     * Caches the m assumptionses in the entity cache if it is enabled.
     *
     * @param mAssumptionses the m assumptionses
     */
    @Override
    public void cacheResult(List<MAssumptions> mAssumptionses) {
        for (MAssumptions mAssumptions : mAssumptionses) {
            if (EntityCacheUtil.getResult(
                        MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        MAssumptionsImpl.class, mAssumptions.getPrimaryKey()) == null) {
                cacheResult(mAssumptions);
            } else {
                mAssumptions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all m assumptionses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MAssumptionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MAssumptionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the m assumptions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MAssumptions mAssumptions) {
        EntityCacheUtil.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsImpl.class, mAssumptions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MAssumptions> mAssumptionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MAssumptions mAssumptions : mAssumptionses) {
            EntityCacheUtil.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                MAssumptionsImpl.class, mAssumptions.getPrimaryKey());
        }
    }

    /**
     * Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
     *
     * @param mAssumptionsSid the primary key for the new m assumptions
     * @return the new m assumptions
     */
    @Override
    public MAssumptions create(int mAssumptionsSid) {
        MAssumptions mAssumptions = new MAssumptionsImpl();

        mAssumptions.setNew(true);
        mAssumptions.setPrimaryKey(mAssumptionsSid);

        return mAssumptions;
    }

    /**
     * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param mAssumptionsSid the primary key of the m assumptions
     * @return the m assumptions that was removed
     * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions remove(int mAssumptionsSid)
        throws NoSuchMAssumptionsException, SystemException {
        return remove((Serializable) mAssumptionsSid);
    }

    /**
     * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the m assumptions
     * @return the m assumptions that was removed
     * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions remove(Serializable primaryKey)
        throws NoSuchMAssumptionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MAssumptions mAssumptions = (MAssumptions) session.get(MAssumptionsImpl.class,
                    primaryKey);

            if (mAssumptions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(mAssumptions);
        } catch (NoSuchMAssumptionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MAssumptions removeImpl(MAssumptions mAssumptions)
        throws SystemException {
        mAssumptions = toUnwrappedModel(mAssumptions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(mAssumptions)) {
                mAssumptions = (MAssumptions) session.get(MAssumptionsImpl.class,
                        mAssumptions.getPrimaryKeyObj());
            }

            if (mAssumptions != null) {
                session.delete(mAssumptions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (mAssumptions != null) {
            clearCache(mAssumptions);
        }

        return mAssumptions;
    }

    @Override
    public MAssumptions updateImpl(com.stpl.app.model.MAssumptions mAssumptions)
        throws SystemException {
        mAssumptions = toUnwrappedModel(mAssumptions);

        boolean isNew = mAssumptions.isNew();

        Session session = null;

        try {
            session = openSession();

            if (mAssumptions.isNew()) {
                session.save(mAssumptions);

                mAssumptions.setNew(false);
            } else {
                session.merge(mAssumptions);
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

        EntityCacheUtil.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
            MAssumptionsImpl.class, mAssumptions.getPrimaryKey(), mAssumptions);

        return mAssumptions;
    }

    protected MAssumptions toUnwrappedModel(MAssumptions mAssumptions) {
        if (mAssumptions instanceof MAssumptionsImpl) {
            return mAssumptions;
        }

        MAssumptionsImpl mAssumptionsImpl = new MAssumptionsImpl();

        mAssumptionsImpl.setNew(mAssumptions.isNew());
        mAssumptionsImpl.setPrimaryKey(mAssumptions.getPrimaryKey());

        mAssumptionsImpl.setGrossSalesPercentChange(mAssumptions.getGrossSalesPercentChange());
        mAssumptionsImpl.setGrossSalesPrior(mAssumptions.getGrossSalesPrior());
        mAssumptionsImpl.setProjYear(mAssumptions.getProjYear());
        mAssumptionsImpl.setTotalDiscountPercentProjected(mAssumptions.getTotalDiscountPercentProjected());
        mAssumptionsImpl.setCamId(mAssumptions.getCamId());
        mAssumptionsImpl.setCommentary(mAssumptions.getCommentary());
        mAssumptionsImpl.setGrossSalesProjected(mAssumptions.getGrossSalesProjected());
        mAssumptionsImpl.setTotalDiscountPercentChange(mAssumptions.getTotalDiscountPercentChange());
        mAssumptionsImpl.setTotalDiscountPercentPrior(mAssumptions.getTotalDiscountPercentPrior());
        mAssumptionsImpl.setNetSalesPercentChange(mAssumptions.getNetSalesPercentChange());
        mAssumptionsImpl.setParent(mAssumptions.isParent());
        mAssumptionsImpl.setProjectionPeriod(mAssumptions.getProjectionPeriod());
        mAssumptionsImpl.setProjectionDetailsSid(mAssumptions.getProjectionDetailsSid());
        mAssumptionsImpl.setNetSalesPrior(mAssumptions.getNetSalesPrior());
        mAssumptionsImpl.setNetSalesProjected(mAssumptions.getNetSalesProjected());
        mAssumptionsImpl.setReasonCodes(mAssumptions.getReasonCodes());
        mAssumptionsImpl.setMAssumptionsSid(mAssumptions.getMAssumptionsSid());

        return mAssumptionsImpl;
    }

    /**
     * Returns the m assumptions with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the m assumptions
     * @return the m assumptions
     * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMAssumptionsException, SystemException {
        MAssumptions mAssumptions = fetchByPrimaryKey(primaryKey);

        if (mAssumptions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return mAssumptions;
    }

    /**
     * Returns the m assumptions with the primary key or throws a {@link com.stpl.app.NoSuchMAssumptionsException} if it could not be found.
     *
     * @param mAssumptionsSid the primary key of the m assumptions
     * @return the m assumptions
     * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions findByPrimaryKey(int mAssumptionsSid)
        throws NoSuchMAssumptionsException, SystemException {
        return findByPrimaryKey((Serializable) mAssumptionsSid);
    }

    /**
     * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the m assumptions
     * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MAssumptions mAssumptions = (MAssumptions) EntityCacheUtil.getResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                MAssumptionsImpl.class, primaryKey);

        if (mAssumptions == _nullMAssumptions) {
            return null;
        }

        if (mAssumptions == null) {
            Session session = null;

            try {
                session = openSession();

                mAssumptions = (MAssumptions) session.get(MAssumptionsImpl.class,
                        primaryKey);

                if (mAssumptions != null) {
                    cacheResult(mAssumptions);
                } else {
                    EntityCacheUtil.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                        MAssumptionsImpl.class, primaryKey, _nullMAssumptions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
                    MAssumptionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return mAssumptions;
    }

    /**
     * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param mAssumptionsSid the primary key of the m assumptions
     * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MAssumptions fetchByPrimaryKey(int mAssumptionsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) mAssumptionsSid);
    }

    /**
     * Returns all the m assumptionses.
     *
     * @return the m assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MAssumptions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the m assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m assumptionses
     * @param end the upper bound of the range of m assumptionses (not inclusive)
     * @return the range of m assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MAssumptions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the m assumptionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of m assumptionses
     * @param end the upper bound of the range of m assumptionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of m assumptionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MAssumptions> findAll(int start, int end,
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

        List<MAssumptions> list = (List<MAssumptions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MASSUMPTIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MASSUMPTIONS;

                if (pagination) {
                    sql = sql.concat(MAssumptionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MAssumptions>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MAssumptions>(list);
                } else {
                    list = (List<MAssumptions>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the m assumptionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MAssumptions mAssumptions : findAll()) {
            remove(mAssumptions);
        }
    }

    /**
     * Returns the number of m assumptionses.
     *
     * @return the number of m assumptionses
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

                Query q = session.createQuery(_SQL_COUNT_MASSUMPTIONS);

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
     * Initializes the m assumptions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MAssumptions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MAssumptions>> listenersList = new ArrayList<ModelListener<MAssumptions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MAssumptions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MAssumptionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
