package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRsDetailsException;
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.impl.RsDetailsImpl;
import com.stpl.app.model.impl.RsDetailsModelImpl;
import com.stpl.app.service.persistence.RsDetailsPersistence;

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
 * The persistence implementation for the rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsPersistence
 * @see RsDetailsUtil
 * @generated
 */
public class RsDetailsPersistenceImpl extends BasePersistenceImpl<RsDetails>
    implements RsDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RsDetailsUtil} to access the rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RsDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsModelImpl.FINDER_CACHE_ENABLED, RsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsModelImpl.FINDER_CACHE_ENABLED, RsDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RSDETAILS = "SELECT rsDetails FROM RsDetails rsDetails";
    private static final String _SQL_COUNT_RSDETAILS = "SELECT COUNT(rsDetails) FROM RsDetails rsDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rsDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RsDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "rebateAmount", "itemRsAttachedStatus", "formulaMethodId",
                "itemMasterSid", "rebatePlanMasterSid", "modifiedDate",
                "bundleNo", "recordLockStatus", "createdDate", "createdBy",
                "source", "itemRebateEndDate", "batchId", "itemRebateStartDate",
                "modifiedBy", "inboundStatus", "rsDetailsSid", "rsModelSid",
                "formulaId", "itemRsAttachedDate", "ifpModelSid",
                "deductionCalendarMasterSid", "netSalesFormulaMasterSid",
                "evaluationRule", "netSalesRule", "formulaType",
                "calculationRule", "calculationRuleBundle",
                "evaluationRuleBundle"
            });
    private static RsDetails _nullRsDetails = new RsDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RsDetails> toCacheModel() {
                return _nullRsDetailsCacheModel;
            }
        };

    private static CacheModel<RsDetails> _nullRsDetailsCacheModel = new CacheModel<RsDetails>() {
            @Override
            public RsDetails toEntityModel() {
                return _nullRsDetails;
            }
        };

    public RsDetailsPersistenceImpl() {
        setModelClass(RsDetails.class);
    }

    /**
     * Caches the rs details in the entity cache if it is enabled.
     *
     * @param rsDetails the rs details
     */
    @Override
    public void cacheResult(RsDetails rsDetails) {
        EntityCacheUtil.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsImpl.class, rsDetails.getPrimaryKey(), rsDetails);

        rsDetails.resetOriginalValues();
    }

    /**
     * Caches the rs detailses in the entity cache if it is enabled.
     *
     * @param rsDetailses the rs detailses
     */
    @Override
    public void cacheResult(List<RsDetails> rsDetailses) {
        for (RsDetails rsDetails : rsDetailses) {
            if (EntityCacheUtil.getResult(
                        RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        RsDetailsImpl.class, rsDetails.getPrimaryKey()) == null) {
                cacheResult(rsDetails);
            } else {
                rsDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rs detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RsDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RsDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rs details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RsDetails rsDetails) {
        EntityCacheUtil.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsImpl.class, rsDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RsDetails> rsDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RsDetails rsDetails : rsDetailses) {
            EntityCacheUtil.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                RsDetailsImpl.class, rsDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new rs details with the primary key. Does not add the rs details to the database.
     *
     * @param rsDetailsSid the primary key for the new rs details
     * @return the new rs details
     */
    @Override
    public RsDetails create(int rsDetailsSid) {
        RsDetails rsDetails = new RsDetailsImpl();

        rsDetails.setNew(true);
        rsDetails.setPrimaryKey(rsDetailsSid);

        return rsDetails;
    }

    /**
     * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rsDetailsSid the primary key of the rs details
     * @return the rs details that was removed
     * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails remove(int rsDetailsSid)
        throws NoSuchRsDetailsException, SystemException {
        return remove((Serializable) rsDetailsSid);
    }

    /**
     * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rs details
     * @return the rs details that was removed
     * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails remove(Serializable primaryKey)
        throws NoSuchRsDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RsDetails rsDetails = (RsDetails) session.get(RsDetailsImpl.class,
                    primaryKey);

            if (rsDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rsDetails);
        } catch (NoSuchRsDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RsDetails removeImpl(RsDetails rsDetails)
        throws SystemException {
        rsDetails = toUnwrappedModel(rsDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rsDetails)) {
                rsDetails = (RsDetails) session.get(RsDetailsImpl.class,
                        rsDetails.getPrimaryKeyObj());
            }

            if (rsDetails != null) {
                session.delete(rsDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rsDetails != null) {
            clearCache(rsDetails);
        }

        return rsDetails;
    }

    @Override
    public RsDetails updateImpl(com.stpl.app.model.RsDetails rsDetails)
        throws SystemException {
        rsDetails = toUnwrappedModel(rsDetails);

        boolean isNew = rsDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rsDetails.isNew()) {
                session.save(rsDetails);

                rsDetails.setNew(false);
            } else {
                session.merge(rsDetails);
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

        EntityCacheUtil.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
            RsDetailsImpl.class, rsDetails.getPrimaryKey(), rsDetails);

        return rsDetails;
    }

    protected RsDetails toUnwrappedModel(RsDetails rsDetails) {
        if (rsDetails instanceof RsDetailsImpl) {
            return rsDetails;
        }

        RsDetailsImpl rsDetailsImpl = new RsDetailsImpl();

        rsDetailsImpl.setNew(rsDetails.isNew());
        rsDetailsImpl.setPrimaryKey(rsDetails.getPrimaryKey());

        rsDetailsImpl.setRebateAmount(rsDetails.getRebateAmount());
        rsDetailsImpl.setItemRsAttachedStatus(rsDetails.getItemRsAttachedStatus());
        rsDetailsImpl.setFormulaMethodId(rsDetails.getFormulaMethodId());
        rsDetailsImpl.setItemMasterSid(rsDetails.getItemMasterSid());
        rsDetailsImpl.setRebatePlanMasterSid(rsDetails.getRebatePlanMasterSid());
        rsDetailsImpl.setModifiedDate(rsDetails.getModifiedDate());
        rsDetailsImpl.setBundleNo(rsDetails.getBundleNo());
        rsDetailsImpl.setRecordLockStatus(rsDetails.isRecordLockStatus());
        rsDetailsImpl.setCreatedDate(rsDetails.getCreatedDate());
        rsDetailsImpl.setCreatedBy(rsDetails.getCreatedBy());
        rsDetailsImpl.setSource(rsDetails.getSource());
        rsDetailsImpl.setItemRebateEndDate(rsDetails.getItemRebateEndDate());
        rsDetailsImpl.setBatchId(rsDetails.getBatchId());
        rsDetailsImpl.setItemRebateStartDate(rsDetails.getItemRebateStartDate());
        rsDetailsImpl.setModifiedBy(rsDetails.getModifiedBy());
        rsDetailsImpl.setInboundStatus(rsDetails.getInboundStatus());
        rsDetailsImpl.setRsDetailsSid(rsDetails.getRsDetailsSid());
        rsDetailsImpl.setRsModelSid(rsDetails.getRsModelSid());
        rsDetailsImpl.setFormulaId(rsDetails.getFormulaId());
        rsDetailsImpl.setItemRsAttachedDate(rsDetails.getItemRsAttachedDate());
        rsDetailsImpl.setIfpModelSid(rsDetails.getIfpModelSid());
        rsDetailsImpl.setDeductionCalendarMasterSid(rsDetails.getDeductionCalendarMasterSid());
        rsDetailsImpl.setNetSalesFormulaMasterSid(rsDetails.getNetSalesFormulaMasterSid());
        rsDetailsImpl.setEvaluationRule(rsDetails.getEvaluationRule());
        rsDetailsImpl.setNetSalesRule(rsDetails.getNetSalesRule());
        rsDetailsImpl.setFormulaType(rsDetails.getFormulaType());
        rsDetailsImpl.setCalculationRule(rsDetails.getCalculationRule());
        rsDetailsImpl.setCalculationRuleBundle(rsDetails.getCalculationRuleBundle());
        rsDetailsImpl.setEvaluationRuleBundle(rsDetails.getEvaluationRuleBundle());

        return rsDetailsImpl;
    }

    /**
     * Returns the rs details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rs details
     * @return the rs details
     * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRsDetailsException, SystemException {
        RsDetails rsDetails = fetchByPrimaryKey(primaryKey);

        if (rsDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rsDetails;
    }

    /**
     * Returns the rs details with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsException} if it could not be found.
     *
     * @param rsDetailsSid the primary key of the rs details
     * @return the rs details
     * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails findByPrimaryKey(int rsDetailsSid)
        throws NoSuchRsDetailsException, SystemException {
        return findByPrimaryKey((Serializable) rsDetailsSid);
    }

    /**
     * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rs details
     * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RsDetails rsDetails = (RsDetails) EntityCacheUtil.getResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                RsDetailsImpl.class, primaryKey);

        if (rsDetails == _nullRsDetails) {
            return null;
        }

        if (rsDetails == null) {
            Session session = null;

            try {
                session = openSession();

                rsDetails = (RsDetails) session.get(RsDetailsImpl.class,
                        primaryKey);

                if (rsDetails != null) {
                    cacheResult(rsDetails);
                } else {
                    EntityCacheUtil.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        RsDetailsImpl.class, primaryKey, _nullRsDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    RsDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rsDetails;
    }

    /**
     * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rsDetailsSid the primary key of the rs details
     * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RsDetails fetchByPrimaryKey(int rsDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rsDetailsSid);
    }

    /**
     * Returns all the rs detailses.
     *
     * @return the rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rs detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs detailses
     * @param end the upper bound of the range of rs detailses (not inclusive)
     * @return the range of rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rs detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rs detailses
     * @param end the upper bound of the range of rs detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rs detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RsDetails> findAll(int start, int end,
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

        List<RsDetails> list = (List<RsDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RSDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RSDETAILS;

                if (pagination) {
                    sql = sql.concat(RsDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RsDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RsDetails>(list);
                } else {
                    list = (List<RsDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the rs detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RsDetails rsDetails : findAll()) {
            remove(rsDetails);
        }
    }

    /**
     * Returns the number of rs detailses.
     *
     * @return the number of rs detailses
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

                Query q = session.createQuery(_SQL_COUNT_RSDETAILS);

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
     * Initializes the rs details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RsDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RsDetails>> listenersList = new ArrayList<ModelListener<RsDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RsDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RsDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
