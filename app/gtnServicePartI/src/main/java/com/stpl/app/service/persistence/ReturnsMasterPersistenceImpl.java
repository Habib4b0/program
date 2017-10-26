package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchReturnsMasterException;
import com.stpl.app.model.ReturnsMaster;
import com.stpl.app.model.impl.ReturnsMasterImpl;
import com.stpl.app.model.impl.ReturnsMasterModelImpl;
import com.stpl.app.service.persistence.ReturnsMasterPersistence;

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
 * The persistence implementation for the returns master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ReturnsMasterPersistence
 * @see ReturnsMasterUtil
 * @generated
 */
public class ReturnsMasterPersistenceImpl extends BasePersistenceImpl<ReturnsMaster>
    implements ReturnsMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ReturnsMasterUtil} to access the returns master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ReturnsMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterModelImpl.FINDER_CACHE_ENABLED,
            ReturnsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterModelImpl.FINDER_CACHE_ENABLED,
            ReturnsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RETURNSMASTER = "SELECT returnsMaster FROM ReturnsMaster returnsMaster";
    private static final String _SQL_COUNT_RETURNSMASTER = "SELECT COUNT(returnsMaster) FROM ReturnsMaster returnsMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "returnsMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReturnsMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ReturnsMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjValueAtOrigAsp", "itemMasterSid", "firstReturn", "asp",
                "maxExpiredMonthPlusCutoff", "posEstimatedReturnUnits",
                "origSaleMonthCutOff", "calcUsed", "modifiedDate",
                "brandMasterSid", "lastReturn", "expectedReturnUnits",
                "createdDate", "createdBy", "source", "version",
                "addChgDelIndicator", "returnsMasterSid", "weightedAvgMonths",
                "modifiedBy", "inboundStatus", "pct25th", "loadDate",
                "maxExpiredMonth", "actualReturnRate", "rreserveId",
                "returnComplete", "expectedReturnRate", "pct50th",
                "within50qrtile", "cumReturnUnits", "origSaleMonth",
                "description", "sku", "upperLimit", "lowerLimit",
                "valueAtOrigAsp", "adjEstimatedReturnUnits", "pct75th",
                "recordLockStatus", "pastExpiration", "batchId", "maximum",
                "origSaleUnits", "brand", "origSaleDollars"
            });
    private static ReturnsMaster _nullReturnsMaster = new ReturnsMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ReturnsMaster> toCacheModel() {
                return _nullReturnsMasterCacheModel;
            }
        };

    private static CacheModel<ReturnsMaster> _nullReturnsMasterCacheModel = new CacheModel<ReturnsMaster>() {
            @Override
            public ReturnsMaster toEntityModel() {
                return _nullReturnsMaster;
            }
        };

    public ReturnsMasterPersistenceImpl() {
        setModelClass(ReturnsMaster.class);
    }

    /**
     * Caches the returns master in the entity cache if it is enabled.
     *
     * @param returnsMaster the returns master
     */
    @Override
    public void cacheResult(ReturnsMaster returnsMaster) {
        EntityCacheUtil.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterImpl.class, returnsMaster.getPrimaryKey(),
            returnsMaster);

        returnsMaster.resetOriginalValues();
    }

    /**
     * Caches the returns masters in the entity cache if it is enabled.
     *
     * @param returnsMasters the returns masters
     */
    @Override
    public void cacheResult(List<ReturnsMaster> returnsMasters) {
        for (ReturnsMaster returnsMaster : returnsMasters) {
            if (EntityCacheUtil.getResult(
                        ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ReturnsMasterImpl.class, returnsMaster.getPrimaryKey()) == null) {
                cacheResult(returnsMaster);
            } else {
                returnsMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all returns masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ReturnsMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ReturnsMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the returns master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ReturnsMaster returnsMaster) {
        EntityCacheUtil.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterImpl.class, returnsMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ReturnsMaster> returnsMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ReturnsMaster returnsMaster : returnsMasters) {
            EntityCacheUtil.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
                ReturnsMasterImpl.class, returnsMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new returns master with the primary key. Does not add the returns master to the database.
     *
     * @param returnsMasterSid the primary key for the new returns master
     * @return the new returns master
     */
    @Override
    public ReturnsMaster create(int returnsMasterSid) {
        ReturnsMaster returnsMaster = new ReturnsMasterImpl();

        returnsMaster.setNew(true);
        returnsMaster.setPrimaryKey(returnsMasterSid);

        return returnsMaster;
    }

    /**
     * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param returnsMasterSid the primary key of the returns master
     * @return the returns master that was removed
     * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster remove(int returnsMasterSid)
        throws NoSuchReturnsMasterException, SystemException {
        return remove((Serializable) returnsMasterSid);
    }

    /**
     * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the returns master
     * @return the returns master that was removed
     * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster remove(Serializable primaryKey)
        throws NoSuchReturnsMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ReturnsMaster returnsMaster = (ReturnsMaster) session.get(ReturnsMasterImpl.class,
                    primaryKey);

            if (returnsMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchReturnsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(returnsMaster);
        } catch (NoSuchReturnsMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ReturnsMaster removeImpl(ReturnsMaster returnsMaster)
        throws SystemException {
        returnsMaster = toUnwrappedModel(returnsMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(returnsMaster)) {
                returnsMaster = (ReturnsMaster) session.get(ReturnsMasterImpl.class,
                        returnsMaster.getPrimaryKeyObj());
            }

            if (returnsMaster != null) {
                session.delete(returnsMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (returnsMaster != null) {
            clearCache(returnsMaster);
        }

        return returnsMaster;
    }

    @Override
    public ReturnsMaster updateImpl(
        com.stpl.app.model.ReturnsMaster returnsMaster)
        throws SystemException {
        returnsMaster = toUnwrappedModel(returnsMaster);

        boolean isNew = returnsMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (returnsMaster.isNew()) {
                session.save(returnsMaster);

                returnsMaster.setNew(false);
            } else {
                session.merge(returnsMaster);
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

        EntityCacheUtil.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
            ReturnsMasterImpl.class, returnsMaster.getPrimaryKey(),
            returnsMaster);

        return returnsMaster;
    }

    protected ReturnsMaster toUnwrappedModel(ReturnsMaster returnsMaster) {
        if (returnsMaster instanceof ReturnsMasterImpl) {
            return returnsMaster;
        }

        ReturnsMasterImpl returnsMasterImpl = new ReturnsMasterImpl();

        returnsMasterImpl.setNew(returnsMaster.isNew());
        returnsMasterImpl.setPrimaryKey(returnsMaster.getPrimaryKey());

        returnsMasterImpl.setAdjValueAtOrigAsp(returnsMaster.getAdjValueAtOrigAsp());
        returnsMasterImpl.setItemMasterSid(returnsMaster.getItemMasterSid());
        returnsMasterImpl.setFirstReturn(returnsMaster.getFirstReturn());
        returnsMasterImpl.setAsp(returnsMaster.getAsp());
        returnsMasterImpl.setMaxExpiredMonthPlusCutoff(returnsMaster.getMaxExpiredMonthPlusCutoff());
        returnsMasterImpl.setPosEstimatedReturnUnits(returnsMaster.getPosEstimatedReturnUnits());
        returnsMasterImpl.setOrigSaleMonthCutOff(returnsMaster.getOrigSaleMonthCutOff());
        returnsMasterImpl.setCalcUsed(returnsMaster.getCalcUsed());
        returnsMasterImpl.setModifiedDate(returnsMaster.getModifiedDate());
        returnsMasterImpl.setBrandMasterSid(returnsMaster.getBrandMasterSid());
        returnsMasterImpl.setLastReturn(returnsMaster.getLastReturn());
        returnsMasterImpl.setExpectedReturnUnits(returnsMaster.getExpectedReturnUnits());
        returnsMasterImpl.setCreatedDate(returnsMaster.getCreatedDate());
        returnsMasterImpl.setCreatedBy(returnsMaster.getCreatedBy());
        returnsMasterImpl.setSource(returnsMaster.getSource());
        returnsMasterImpl.setVersion(returnsMaster.getVersion());
        returnsMasterImpl.setAddChgDelIndicator(returnsMaster.getAddChgDelIndicator());
        returnsMasterImpl.setReturnsMasterSid(returnsMaster.getReturnsMasterSid());
        returnsMasterImpl.setWeightedAvgMonths(returnsMaster.getWeightedAvgMonths());
        returnsMasterImpl.setModifiedBy(returnsMaster.getModifiedBy());
        returnsMasterImpl.setInboundStatus(returnsMaster.getInboundStatus());
        returnsMasterImpl.setPct25th(returnsMaster.getPct25th());
        returnsMasterImpl.setLoadDate(returnsMaster.getLoadDate());
        returnsMasterImpl.setMaxExpiredMonth(returnsMaster.getMaxExpiredMonth());
        returnsMasterImpl.setActualReturnRate(returnsMaster.getActualReturnRate());
        returnsMasterImpl.setRreserveId(returnsMaster.getRreserveId());
        returnsMasterImpl.setReturnComplete(returnsMaster.getReturnComplete());
        returnsMasterImpl.setExpectedReturnRate(returnsMaster.getExpectedReturnRate());
        returnsMasterImpl.setPct50th(returnsMaster.getPct50th());
        returnsMasterImpl.setWithin50qrtile(returnsMaster.getWithin50qrtile());
        returnsMasterImpl.setCumReturnUnits(returnsMaster.getCumReturnUnits());
        returnsMasterImpl.setOrigSaleMonth(returnsMaster.getOrigSaleMonth());
        returnsMasterImpl.setDescription(returnsMaster.getDescription());
        returnsMasterImpl.setSku(returnsMaster.getSku());
        returnsMasterImpl.setUpperLimit(returnsMaster.getUpperLimit());
        returnsMasterImpl.setLowerLimit(returnsMaster.getLowerLimit());
        returnsMasterImpl.setValueAtOrigAsp(returnsMaster.getValueAtOrigAsp());
        returnsMasterImpl.setAdjEstimatedReturnUnits(returnsMaster.getAdjEstimatedReturnUnits());
        returnsMasterImpl.setPct75th(returnsMaster.getPct75th());
        returnsMasterImpl.setRecordLockStatus(returnsMaster.isRecordLockStatus());
        returnsMasterImpl.setPastExpiration(returnsMaster.getPastExpiration());
        returnsMasterImpl.setBatchId(returnsMaster.getBatchId());
        returnsMasterImpl.setMaximum(returnsMaster.getMaximum());
        returnsMasterImpl.setOrigSaleUnits(returnsMaster.getOrigSaleUnits());
        returnsMasterImpl.setBrand(returnsMaster.getBrand());
        returnsMasterImpl.setOrigSaleDollars(returnsMaster.getOrigSaleDollars());

        return returnsMasterImpl;
    }

    /**
     * Returns the returns master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the returns master
     * @return the returns master
     * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchReturnsMasterException, SystemException {
        ReturnsMaster returnsMaster = fetchByPrimaryKey(primaryKey);

        if (returnsMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchReturnsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return returnsMaster;
    }

    /**
     * Returns the returns master with the primary key or throws a {@link com.stpl.app.NoSuchReturnsMasterException} if it could not be found.
     *
     * @param returnsMasterSid the primary key of the returns master
     * @return the returns master
     * @throws com.stpl.app.NoSuchReturnsMasterException if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster findByPrimaryKey(int returnsMasterSid)
        throws NoSuchReturnsMasterException, SystemException {
        return findByPrimaryKey((Serializable) returnsMasterSid);
    }

    /**
     * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the returns master
     * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ReturnsMaster returnsMaster = (ReturnsMaster) EntityCacheUtil.getResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
                ReturnsMasterImpl.class, primaryKey);

        if (returnsMaster == _nullReturnsMaster) {
            return null;
        }

        if (returnsMaster == null) {
            Session session = null;

            try {
                session = openSession();

                returnsMaster = (ReturnsMaster) session.get(ReturnsMasterImpl.class,
                        primaryKey);

                if (returnsMaster != null) {
                    cacheResult(returnsMaster);
                } else {
                    EntityCacheUtil.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
                        ReturnsMasterImpl.class, primaryKey, _nullReturnsMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
                    ReturnsMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return returnsMaster;
    }

    /**
     * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param returnsMasterSid the primary key of the returns master
     * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ReturnsMaster fetchByPrimaryKey(int returnsMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) returnsMasterSid);
    }

    /**
     * Returns all the returns masters.
     *
     * @return the returns masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReturnsMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the returns masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of returns masters
     * @param end the upper bound of the range of returns masters (not inclusive)
     * @return the range of returns masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReturnsMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the returns masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of returns masters
     * @param end the upper bound of the range of returns masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of returns masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ReturnsMaster> findAll(int start, int end,
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

        List<ReturnsMaster> list = (List<ReturnsMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RETURNSMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RETURNSMASTER;

                if (pagination) {
                    sql = sql.concat(ReturnsMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ReturnsMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ReturnsMaster>(list);
                } else {
                    list = (List<ReturnsMaster>) QueryUtil.list(q,
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
     * Removes all the returns masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ReturnsMaster returnsMaster : findAll()) {
            remove(returnsMaster);
        }
    }

    /**
     * Returns the number of returns masters.
     *
     * @return the number of returns masters
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

                Query q = session.createQuery(_SQL_COUNT_RETURNSMASTER);

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
     * Initializes the returns master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ReturnsMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ReturnsMaster>> listenersList = new ArrayList<ModelListener<ReturnsMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ReturnsMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ReturnsMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
