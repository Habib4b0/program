package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAccClosureMasterException;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.model.impl.AccClosureMasterImpl;
import com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterPersistence;

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
 * The persistence implementation for the acc closure master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureMasterPersistence
 * @see AccClosureMasterUtil
 * @generated
 */
public class AccClosureMasterPersistenceImpl extends BasePersistenceImpl<AccClosureMaster>
    implements AccClosureMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AccClosureMasterUtil} to access the acc closure master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AccClosureMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterModelImpl.FINDER_CACHE_ENABLED,
            AccClosureMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterModelImpl.FINDER_CACHE_ENABLED,
            AccClosureMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACCCLOSUREMASTER = "SELECT accClosureMaster FROM AccClosureMaster accClosureMaster";
    private static final String _SQL_COUNT_ACCCLOSUREMASTER = "SELECT COUNT(accClosureMaster) FROM AccClosureMaster accClosureMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AccClosureMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "saveFlag", "accountNo", "toDate", "itemMasterSid",
                "description", "reportName", "rsType", "productIdentifier",
                "modifiedDate", "workflowStatus", "moduleType", "fromDate",
                "contractType", "glCompanyMasterSid", "createdDate", "createdBy",
                "contractMasterSid", "accrualPeriod", "companyGroupSid",
                "accClosureMasterSid", "rsCategory", "adjustmentType",
                "modifiedBy", "itemGroupSid", "rebateProgramType"
            });
    private static AccClosureMaster _nullAccClosureMaster = new AccClosureMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AccClosureMaster> toCacheModel() {
                return _nullAccClosureMasterCacheModel;
            }
        };

    private static CacheModel<AccClosureMaster> _nullAccClosureMasterCacheModel = new CacheModel<AccClosureMaster>() {
            @Override
            public AccClosureMaster toEntityModel() {
                return _nullAccClosureMaster;
            }
        };

    public AccClosureMasterPersistenceImpl() {
        setModelClass(AccClosureMaster.class);
    }

    /**
     * Caches the acc closure master in the entity cache if it is enabled.
     *
     * @param accClosureMaster the acc closure master
     */
    @Override
    public void cacheResult(AccClosureMaster accClosureMaster) {
        EntityCacheUtil.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey(),
            accClosureMaster);

        accClosureMaster.resetOriginalValues();
    }

    /**
     * Caches the acc closure masters in the entity cache if it is enabled.
     *
     * @param accClosureMasters the acc closure masters
     */
    @Override
    public void cacheResult(List<AccClosureMaster> accClosureMasters) {
        for (AccClosureMaster accClosureMaster : accClosureMasters) {
            if (EntityCacheUtil.getResult(
                        AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureMasterImpl.class,
                        accClosureMaster.getPrimaryKey()) == null) {
                cacheResult(accClosureMaster);
            } else {
                accClosureMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all acc closure masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AccClosureMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AccClosureMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the acc closure master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AccClosureMaster accClosureMaster) {
        EntityCacheUtil.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AccClosureMaster> accClosureMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AccClosureMaster accClosureMaster : accClosureMasters) {
            EntityCacheUtil.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
     *
     * @param accClosureMasterSid the primary key for the new acc closure master
     * @return the new acc closure master
     */
    @Override
    public AccClosureMaster create(int accClosureMasterSid) {
        AccClosureMaster accClosureMaster = new AccClosureMasterImpl();

        accClosureMaster.setNew(true);
        accClosureMaster.setPrimaryKey(accClosureMasterSid);

        return accClosureMaster;
    }

    /**
     * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accClosureMasterSid the primary key of the acc closure master
     * @return the acc closure master that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster remove(int accClosureMasterSid)
        throws NoSuchAccClosureMasterException, SystemException {
        return remove((Serializable) accClosureMasterSid);
    }

    /**
     * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the acc closure master
     * @return the acc closure master that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster remove(Serializable primaryKey)
        throws NoSuchAccClosureMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AccClosureMaster accClosureMaster = (AccClosureMaster) session.get(AccClosureMasterImpl.class,
                    primaryKey);

            if (accClosureMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAccClosureMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(accClosureMaster);
        } catch (NoSuchAccClosureMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AccClosureMaster removeImpl(AccClosureMaster accClosureMaster)
        throws SystemException {
        accClosureMaster = toUnwrappedModel(accClosureMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(accClosureMaster)) {
                accClosureMaster = (AccClosureMaster) session.get(AccClosureMasterImpl.class,
                        accClosureMaster.getPrimaryKeyObj());
            }

            if (accClosureMaster != null) {
                session.delete(accClosureMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (accClosureMaster != null) {
            clearCache(accClosureMaster);
        }

        return accClosureMaster;
    }

    @Override
    public AccClosureMaster updateImpl(
        com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster)
        throws SystemException {
        accClosureMaster = toUnwrappedModel(accClosureMaster);

        boolean isNew = accClosureMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (accClosureMaster.isNew()) {
                session.save(accClosureMaster);

                accClosureMaster.setNew(false);
            } else {
                session.merge(accClosureMaster);
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

        EntityCacheUtil.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey(),
            accClosureMaster);

        return accClosureMaster;
    }

    protected AccClosureMaster toUnwrappedModel(
        AccClosureMaster accClosureMaster) {
        if (accClosureMaster instanceof AccClosureMasterImpl) {
            return accClosureMaster;
        }

        AccClosureMasterImpl accClosureMasterImpl = new AccClosureMasterImpl();

        accClosureMasterImpl.setNew(accClosureMaster.isNew());
        accClosureMasterImpl.setPrimaryKey(accClosureMaster.getPrimaryKey());

        accClosureMasterImpl.setSaveFlag(accClosureMaster.isSaveFlag());
        accClosureMasterImpl.setAccountNo(accClosureMaster.getAccountNo());
        accClosureMasterImpl.setToDate(accClosureMaster.getToDate());
        accClosureMasterImpl.setItemMasterSid(accClosureMaster.getItemMasterSid());
        accClosureMasterImpl.setDescription(accClosureMaster.getDescription());
        accClosureMasterImpl.setReportName(accClosureMaster.getReportName());
        accClosureMasterImpl.setRsType(accClosureMaster.getRsType());
        accClosureMasterImpl.setProductIdentifier(accClosureMaster.getProductIdentifier());
        accClosureMasterImpl.setModifiedDate(accClosureMaster.getModifiedDate());
        accClosureMasterImpl.setWorkflowStatus(accClosureMaster.getWorkflowStatus());
        accClosureMasterImpl.setModuleType(accClosureMaster.getModuleType());
        accClosureMasterImpl.setFromDate(accClosureMaster.getFromDate());
        accClosureMasterImpl.setContractType(accClosureMaster.getContractType());
        accClosureMasterImpl.setGlCompanyMasterSid(accClosureMaster.getGlCompanyMasterSid());
        accClosureMasterImpl.setCreatedDate(accClosureMaster.getCreatedDate());
        accClosureMasterImpl.setCreatedBy(accClosureMaster.getCreatedBy());
        accClosureMasterImpl.setContractMasterSid(accClosureMaster.getContractMasterSid());
        accClosureMasterImpl.setAccrualPeriod(accClosureMaster.getAccrualPeriod());
        accClosureMasterImpl.setCompanyGroupSid(accClosureMaster.getCompanyGroupSid());
        accClosureMasterImpl.setAccClosureMasterSid(accClosureMaster.getAccClosureMasterSid());
        accClosureMasterImpl.setRsCategory(accClosureMaster.getRsCategory());
        accClosureMasterImpl.setAdjustmentType(accClosureMaster.getAdjustmentType());
        accClosureMasterImpl.setModifiedBy(accClosureMaster.getModifiedBy());
        accClosureMasterImpl.setItemGroupSid(accClosureMaster.getItemGroupSid());
        accClosureMasterImpl.setRebateProgramType(accClosureMaster.getRebateProgramType());

        return accClosureMasterImpl;
    }

    /**
     * Returns the acc closure master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure master
     * @return the acc closure master
     * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAccClosureMasterException, SystemException {
        AccClosureMaster accClosureMaster = fetchByPrimaryKey(primaryKey);

        if (accClosureMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAccClosureMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return accClosureMaster;
    }

    /**
     * Returns the acc closure master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureMasterException} if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the acc closure master
     * @return the acc closure master
     * @throws com.stpl.app.parttwo.NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster findByPrimaryKey(int accClosureMasterSid)
        throws NoSuchAccClosureMasterException, SystemException {
        return findByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure master
     * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AccClosureMaster accClosureMaster = (AccClosureMaster) EntityCacheUtil.getResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureMasterImpl.class, primaryKey);

        if (accClosureMaster == _nullAccClosureMaster) {
            return null;
        }

        if (accClosureMaster == null) {
            Session session = null;

            try {
                session = openSession();

                accClosureMaster = (AccClosureMaster) session.get(AccClosureMasterImpl.class,
                        primaryKey);

                if (accClosureMaster != null) {
                    cacheResult(accClosureMaster);
                } else {
                    EntityCacheUtil.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureMasterImpl.class, primaryKey,
                        _nullAccClosureMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
                    AccClosureMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return accClosureMaster;
    }

    /**
     * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the acc closure master
     * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureMaster fetchByPrimaryKey(int accClosureMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns all the acc closure masters.
     *
     * @return the acc closure masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the acc closure masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure masters
     * @param end the upper bound of the range of acc closure masters (not inclusive)
     * @return the range of acc closure masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the acc closure masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure masters
     * @param end the upper bound of the range of acc closure masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of acc closure masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureMaster> findAll(int start, int end,
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

        List<AccClosureMaster> list = (List<AccClosureMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACCCLOSUREMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACCCLOSUREMASTER;

                if (pagination) {
                    sql = sql.concat(AccClosureMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AccClosureMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AccClosureMaster>(list);
                } else {
                    list = (List<AccClosureMaster>) QueryUtil.list(q,
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
     * Removes all the acc closure masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AccClosureMaster accClosureMaster : findAll()) {
            remove(accClosureMaster);
        }
    }

    /**
     * Returns the number of acc closure masters.
     *
     * @return the number of acc closure masters
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

                Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREMASTER);

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
     * Initializes the acc closure master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AccClosureMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AccClosureMaster>> listenersList = new ArrayList<ModelListener<AccClosureMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AccClosureMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AccClosureMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
