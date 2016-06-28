package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldReturnsException;
import com.stpl.app.model.IvldReturns;
import com.stpl.app.model.impl.IvldReturnsImpl;
import com.stpl.app.model.impl.IvldReturnsModelImpl;
import com.stpl.app.service.persistence.IvldReturnsPersistence;

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
 * The persistence implementation for the ivld returns service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldReturnsPersistence
 * @see IvldReturnsUtil
 * @generated
 */
public class IvldReturnsPersistenceImpl extends BasePersistenceImpl<IvldReturns>
    implements IvldReturnsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldReturnsUtil} to access the ivld returns persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldReturnsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsModelImpl.FINDER_CACHE_ENABLED, IvldReturnsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsModelImpl.FINDER_CACHE_ENABLED, IvldReturnsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDRETURNS = "SELECT ivldReturns FROM IvldReturns ivldReturns";
    private static final String _SQL_COUNT_IVLDRETURNS = "SELECT COUNT(ivldReturns) FROM IvldReturns ivldReturns";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldReturns.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldReturns exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldReturnsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjValueAtOrigAsp", "firstReturn", "asp",
                "maxExpiredMonthPlusCutoff", "posEstimatedReturnUnits",
                "origSaleMonthCutOff", "calcUsed", "modifiedDate", "lastReturn",
                "expectedReturnUnits", "createdDate", "createdBy", "source",
                "version", "addChgDelIndicator", "weightedAvgMonths",
                "errorCode", "modifiedBy", "ivldReturnsSid", "intfInsertedDate",
                "pct25th", "loadDate", "maxExpiredMonth", "reprocessedFlag",
                "actualReturnRate", "rreserveId", "returnComplete",
                "expectedReturnRate", "pct50th", "within50qrtile",
                "rreserveInterfaceId", "cumReturnUnits", "reasonForFailure",
                "origSaleMonth", "description", "sku", "upperLimit",
                "lowerLimit", "valueAtOrigAsp", "adjEstimatedReturnUnits",
                "pct75th", "pastExpiration", "batchId", "maximum",
                "origSaleUnits", "errorField", "brand", "origSaleDollars"
            });
    private static IvldReturns _nullIvldReturns = new IvldReturnsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldReturns> toCacheModel() {
                return _nullIvldReturnsCacheModel;
            }
        };

    private static CacheModel<IvldReturns> _nullIvldReturnsCacheModel = new CacheModel<IvldReturns>() {
            @Override
            public IvldReturns toEntityModel() {
                return _nullIvldReturns;
            }
        };

    public IvldReturnsPersistenceImpl() {
        setModelClass(IvldReturns.class);
    }

    /**
     * Caches the ivld returns in the entity cache if it is enabled.
     *
     * @param ivldReturns the ivld returns
     */
    @Override
    public void cacheResult(IvldReturns ivldReturns) {
        EntityCacheUtil.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsImpl.class, ivldReturns.getPrimaryKey(), ivldReturns);

        ivldReturns.resetOriginalValues();
    }

    /**
     * Caches the ivld returnses in the entity cache if it is enabled.
     *
     * @param ivldReturnses the ivld returnses
     */
    @Override
    public void cacheResult(List<IvldReturns> ivldReturnses) {
        for (IvldReturns ivldReturns : ivldReturnses) {
            if (EntityCacheUtil.getResult(
                        IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
                        IvldReturnsImpl.class, ivldReturns.getPrimaryKey()) == null) {
                cacheResult(ivldReturns);
            } else {
                ivldReturns.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld returnses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldReturnsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldReturnsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld returns.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldReturns ivldReturns) {
        EntityCacheUtil.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsImpl.class, ivldReturns.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldReturns> ivldReturnses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldReturns ivldReturns : ivldReturnses) {
            EntityCacheUtil.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
                IvldReturnsImpl.class, ivldReturns.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
     *
     * @param ivldReturnsSid the primary key for the new ivld returns
     * @return the new ivld returns
     */
    @Override
    public IvldReturns create(int ivldReturnsSid) {
        IvldReturns ivldReturns = new IvldReturnsImpl();

        ivldReturns.setNew(true);
        ivldReturns.setPrimaryKey(ivldReturnsSid);

        return ivldReturns;
    }

    /**
     * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldReturnsSid the primary key of the ivld returns
     * @return the ivld returns that was removed
     * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns remove(int ivldReturnsSid)
        throws NoSuchIvldReturnsException, SystemException {
        return remove((Serializable) ivldReturnsSid);
    }

    /**
     * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld returns
     * @return the ivld returns that was removed
     * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns remove(Serializable primaryKey)
        throws NoSuchIvldReturnsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldReturns ivldReturns = (IvldReturns) session.get(IvldReturnsImpl.class,
                    primaryKey);

            if (ivldReturns == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldReturnsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldReturns);
        } catch (NoSuchIvldReturnsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldReturns removeImpl(IvldReturns ivldReturns)
        throws SystemException {
        ivldReturns = toUnwrappedModel(ivldReturns);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldReturns)) {
                ivldReturns = (IvldReturns) session.get(IvldReturnsImpl.class,
                        ivldReturns.getPrimaryKeyObj());
            }

            if (ivldReturns != null) {
                session.delete(ivldReturns);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldReturns != null) {
            clearCache(ivldReturns);
        }

        return ivldReturns;
    }

    @Override
    public IvldReturns updateImpl(com.stpl.app.model.IvldReturns ivldReturns)
        throws SystemException {
        ivldReturns = toUnwrappedModel(ivldReturns);

        boolean isNew = ivldReturns.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldReturns.isNew()) {
                session.save(ivldReturns);

                ivldReturns.setNew(false);
            } else {
                session.merge(ivldReturns);
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

        EntityCacheUtil.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
            IvldReturnsImpl.class, ivldReturns.getPrimaryKey(), ivldReturns);

        return ivldReturns;
    }

    protected IvldReturns toUnwrappedModel(IvldReturns ivldReturns) {
        if (ivldReturns instanceof IvldReturnsImpl) {
            return ivldReturns;
        }

        IvldReturnsImpl ivldReturnsImpl = new IvldReturnsImpl();

        ivldReturnsImpl.setNew(ivldReturns.isNew());
        ivldReturnsImpl.setPrimaryKey(ivldReturns.getPrimaryKey());

        ivldReturnsImpl.setAdjValueAtOrigAsp(ivldReturns.getAdjValueAtOrigAsp());
        ivldReturnsImpl.setFirstReturn(ivldReturns.getFirstReturn());
        ivldReturnsImpl.setAsp(ivldReturns.getAsp());
        ivldReturnsImpl.setMaxExpiredMonthPlusCutoff(ivldReturns.getMaxExpiredMonthPlusCutoff());
        ivldReturnsImpl.setPosEstimatedReturnUnits(ivldReturns.getPosEstimatedReturnUnits());
        ivldReturnsImpl.setOrigSaleMonthCutOff(ivldReturns.getOrigSaleMonthCutOff());
        ivldReturnsImpl.setCalcUsed(ivldReturns.getCalcUsed());
        ivldReturnsImpl.setModifiedDate(ivldReturns.getModifiedDate());
        ivldReturnsImpl.setLastReturn(ivldReturns.getLastReturn());
        ivldReturnsImpl.setExpectedReturnUnits(ivldReturns.getExpectedReturnUnits());
        ivldReturnsImpl.setCreatedDate(ivldReturns.getCreatedDate());
        ivldReturnsImpl.setCreatedBy(ivldReturns.getCreatedBy());
        ivldReturnsImpl.setSource(ivldReturns.getSource());
        ivldReturnsImpl.setVersion(ivldReturns.getVersion());
        ivldReturnsImpl.setAddChgDelIndicator(ivldReturns.getAddChgDelIndicator());
        ivldReturnsImpl.setWeightedAvgMonths(ivldReturns.getWeightedAvgMonths());
        ivldReturnsImpl.setErrorCode(ivldReturns.getErrorCode());
        ivldReturnsImpl.setModifiedBy(ivldReturns.getModifiedBy());
        ivldReturnsImpl.setIvldReturnsSid(ivldReturns.getIvldReturnsSid());
        ivldReturnsImpl.setIntfInsertedDate(ivldReturns.getIntfInsertedDate());
        ivldReturnsImpl.setPct25th(ivldReturns.getPct25th());
        ivldReturnsImpl.setLoadDate(ivldReturns.getLoadDate());
        ivldReturnsImpl.setMaxExpiredMonth(ivldReturns.getMaxExpiredMonth());
        ivldReturnsImpl.setReprocessedFlag(ivldReturns.getReprocessedFlag());
        ivldReturnsImpl.setActualReturnRate(ivldReturns.getActualReturnRate());
        ivldReturnsImpl.setRreserveId(ivldReturns.getRreserveId());
        ivldReturnsImpl.setReturnComplete(ivldReturns.getReturnComplete());
        ivldReturnsImpl.setExpectedReturnRate(ivldReturns.getExpectedReturnRate());
        ivldReturnsImpl.setPct50th(ivldReturns.getPct50th());
        ivldReturnsImpl.setWithin50qrtile(ivldReturns.getWithin50qrtile());
        ivldReturnsImpl.setRreserveInterfaceId(ivldReturns.getRreserveInterfaceId());
        ivldReturnsImpl.setCumReturnUnits(ivldReturns.getCumReturnUnits());
        ivldReturnsImpl.setReasonForFailure(ivldReturns.getReasonForFailure());
        ivldReturnsImpl.setOrigSaleMonth(ivldReturns.getOrigSaleMonth());
        ivldReturnsImpl.setDescription(ivldReturns.getDescription());
        ivldReturnsImpl.setSku(ivldReturns.getSku());
        ivldReturnsImpl.setUpperLimit(ivldReturns.getUpperLimit());
        ivldReturnsImpl.setLowerLimit(ivldReturns.getLowerLimit());
        ivldReturnsImpl.setValueAtOrigAsp(ivldReturns.getValueAtOrigAsp());
        ivldReturnsImpl.setAdjEstimatedReturnUnits(ivldReturns.getAdjEstimatedReturnUnits());
        ivldReturnsImpl.setPct75th(ivldReturns.getPct75th());
        ivldReturnsImpl.setPastExpiration(ivldReturns.getPastExpiration());
        ivldReturnsImpl.setBatchId(ivldReturns.getBatchId());
        ivldReturnsImpl.setMaximum(ivldReturns.getMaximum());
        ivldReturnsImpl.setOrigSaleUnits(ivldReturns.getOrigSaleUnits());
        ivldReturnsImpl.setErrorField(ivldReturns.getErrorField());
        ivldReturnsImpl.setBrand(ivldReturns.getBrand());
        ivldReturnsImpl.setOrigSaleDollars(ivldReturns.getOrigSaleDollars());

        return ivldReturnsImpl;
    }

    /**
     * Returns the ivld returns with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld returns
     * @return the ivld returns
     * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldReturnsException, SystemException {
        IvldReturns ivldReturns = fetchByPrimaryKey(primaryKey);

        if (ivldReturns == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldReturnsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldReturns;
    }

    /**
     * Returns the ivld returns with the primary key or throws a {@link com.stpl.app.NoSuchIvldReturnsException} if it could not be found.
     *
     * @param ivldReturnsSid the primary key of the ivld returns
     * @return the ivld returns
     * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns findByPrimaryKey(int ivldReturnsSid)
        throws NoSuchIvldReturnsException, SystemException {
        return findByPrimaryKey((Serializable) ivldReturnsSid);
    }

    /**
     * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld returns
     * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldReturns ivldReturns = (IvldReturns) EntityCacheUtil.getResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
                IvldReturnsImpl.class, primaryKey);

        if (ivldReturns == _nullIvldReturns) {
            return null;
        }

        if (ivldReturns == null) {
            Session session = null;

            try {
                session = openSession();

                ivldReturns = (IvldReturns) session.get(IvldReturnsImpl.class,
                        primaryKey);

                if (ivldReturns != null) {
                    cacheResult(ivldReturns);
                } else {
                    EntityCacheUtil.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
                        IvldReturnsImpl.class, primaryKey, _nullIvldReturns);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
                    IvldReturnsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldReturns;
    }

    /**
     * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldReturnsSid the primary key of the ivld returns
     * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldReturns fetchByPrimaryKey(int ivldReturnsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldReturnsSid);
    }

    /**
     * Returns all the ivld returnses.
     *
     * @return the ivld returnses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldReturns> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld returnses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld returnses
     * @param end the upper bound of the range of ivld returnses (not inclusive)
     * @return the range of ivld returnses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldReturns> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld returnses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld returnses
     * @param end the upper bound of the range of ivld returnses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld returnses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldReturns> findAll(int start, int end,
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

        List<IvldReturns> list = (List<IvldReturns>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDRETURNS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDRETURNS;

                if (pagination) {
                    sql = sql.concat(IvldReturnsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldReturns>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldReturns>(list);
                } else {
                    list = (List<IvldReturns>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ivld returnses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldReturns ivldReturns : findAll()) {
            remove(ivldReturns);
        }
    }

    /**
     * Returns the number of ivld returnses.
     *
     * @return the number of ivld returnses
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

                Query q = session.createQuery(_SQL_COUNT_IVLDRETURNS);

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
     * Initializes the ivld returns persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldReturns")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldReturns>> listenersList = new ArrayList<ModelListener<IvldReturns>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldReturns>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldReturnsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
