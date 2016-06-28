package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchStDeductionCalendarDetailsException;
import com.stpl.app.model.StDeductionCalendarDetails;
import com.stpl.app.model.impl.StDeductionCalendarDetailsImpl;
import com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPersistence;

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
 * The persistence implementation for the st deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetailsPersistence
 * @see StDeductionCalendarDetailsUtil
 * @generated
 */
public class StDeductionCalendarDetailsPersistenceImpl
    extends BasePersistenceImpl<StDeductionCalendarDetails>
    implements StDeductionCalendarDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StDeductionCalendarDetailsUtil} to access the st deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StDeductionCalendarDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            StDeductionCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            StDeductionCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_STDEDUCTIONCALENDARDETAILS = "SELECT stDeductionCalendarDetails FROM StDeductionCalendarDetails stDeductionCalendarDetails";
    private static final String _SQL_COUNT_STDEDUCTIONCALENDARDETAILS = "SELECT COUNT(stDeductionCalendarDetails) FROM StDeductionCalendarDetails stDeductionCalendarDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stDeductionCalendarDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StDeductionCalendarDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StDeductionCalendarDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustmentBasis", "periodSid", "adjustmentValue",
                "adjustmentAllocationMethodology", "companyMasterSid",
                "discountAmount", "adjustmentVariable", "userId",
                "itemMasterSid", "adjustmentType", "sessionId", "checkRecord"
            });
    private static StDeductionCalendarDetails _nullStDeductionCalendarDetails = new StDeductionCalendarDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StDeductionCalendarDetails> toCacheModel() {
                return _nullStDeductionCalendarDetailsCacheModel;
            }
        };

    private static CacheModel<StDeductionCalendarDetails> _nullStDeductionCalendarDetailsCacheModel =
        new CacheModel<StDeductionCalendarDetails>() {
            @Override
            public StDeductionCalendarDetails toEntityModel() {
                return _nullStDeductionCalendarDetails;
            }
        };

    public StDeductionCalendarDetailsPersistenceImpl() {
        setModelClass(StDeductionCalendarDetails.class);
    }

    /**
     * Caches the st deduction calendar details in the entity cache if it is enabled.
     *
     * @param stDeductionCalendarDetails the st deduction calendar details
     */
    @Override
    public void cacheResult(
        StDeductionCalendarDetails stDeductionCalendarDetails) {
        EntityCacheUtil.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsImpl.class,
            stDeductionCalendarDetails.getPrimaryKey(),
            stDeductionCalendarDetails);

        stDeductionCalendarDetails.resetOriginalValues();
    }

    /**
     * Caches the st deduction calendar detailses in the entity cache if it is enabled.
     *
     * @param stDeductionCalendarDetailses the st deduction calendar detailses
     */
    @Override
    public void cacheResult(
        List<StDeductionCalendarDetails> stDeductionCalendarDetailses) {
        for (StDeductionCalendarDetails stDeductionCalendarDetails : stDeductionCalendarDetailses) {
            if (EntityCacheUtil.getResult(
                        StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        StDeductionCalendarDetailsImpl.class,
                        stDeductionCalendarDetails.getPrimaryKey()) == null) {
                cacheResult(stDeductionCalendarDetails);
            } else {
                stDeductionCalendarDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st deduction calendar detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StDeductionCalendarDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StDeductionCalendarDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st deduction calendar details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        StDeductionCalendarDetails stDeductionCalendarDetails) {
        EntityCacheUtil.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsImpl.class,
            stDeductionCalendarDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<StDeductionCalendarDetails> stDeductionCalendarDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StDeductionCalendarDetails stDeductionCalendarDetails : stDeductionCalendarDetailses) {
            EntityCacheUtil.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                StDeductionCalendarDetailsImpl.class,
                stDeductionCalendarDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
     *
     * @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
     * @return the new st deduction calendar details
     */
    @Override
    public StDeductionCalendarDetails create(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK) {
        StDeductionCalendarDetails stDeductionCalendarDetails = new StDeductionCalendarDetailsImpl();

        stDeductionCalendarDetails.setNew(true);
        stDeductionCalendarDetails.setPrimaryKey(stDeductionCalendarDetailsPK);

        return stDeductionCalendarDetails;
    }

    /**
     * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
     * @return the st deduction calendar details that was removed
     * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails remove(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws NoSuchStDeductionCalendarDetailsException, SystemException {
        return remove((Serializable) stDeductionCalendarDetailsPK);
    }

    /**
     * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st deduction calendar details
     * @return the st deduction calendar details that was removed
     * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails remove(Serializable primaryKey)
        throws NoSuchStDeductionCalendarDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StDeductionCalendarDetails stDeductionCalendarDetails = (StDeductionCalendarDetails) session.get(StDeductionCalendarDetailsImpl.class,
                    primaryKey);

            if (stDeductionCalendarDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stDeductionCalendarDetails);
        } catch (NoSuchStDeductionCalendarDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StDeductionCalendarDetails removeImpl(
        StDeductionCalendarDetails stDeductionCalendarDetails)
        throws SystemException {
        stDeductionCalendarDetails = toUnwrappedModel(stDeductionCalendarDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stDeductionCalendarDetails)) {
                stDeductionCalendarDetails = (StDeductionCalendarDetails) session.get(StDeductionCalendarDetailsImpl.class,
                        stDeductionCalendarDetails.getPrimaryKeyObj());
            }

            if (stDeductionCalendarDetails != null) {
                session.delete(stDeductionCalendarDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stDeductionCalendarDetails != null) {
            clearCache(stDeductionCalendarDetails);
        }

        return stDeductionCalendarDetails;
    }

    @Override
    public StDeductionCalendarDetails updateImpl(
        com.stpl.app.model.StDeductionCalendarDetails stDeductionCalendarDetails)
        throws SystemException {
        stDeductionCalendarDetails = toUnwrappedModel(stDeductionCalendarDetails);

        boolean isNew = stDeductionCalendarDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stDeductionCalendarDetails.isNew()) {
                session.save(stDeductionCalendarDetails);

                stDeductionCalendarDetails.setNew(false);
            } else {
                session.merge(stDeductionCalendarDetails);
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

        EntityCacheUtil.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StDeductionCalendarDetailsImpl.class,
            stDeductionCalendarDetails.getPrimaryKey(),
            stDeductionCalendarDetails);

        return stDeductionCalendarDetails;
    }

    protected StDeductionCalendarDetails toUnwrappedModel(
        StDeductionCalendarDetails stDeductionCalendarDetails) {
        if (stDeductionCalendarDetails instanceof StDeductionCalendarDetailsImpl) {
            return stDeductionCalendarDetails;
        }

        StDeductionCalendarDetailsImpl stDeductionCalendarDetailsImpl = new StDeductionCalendarDetailsImpl();

        stDeductionCalendarDetailsImpl.setNew(stDeductionCalendarDetails.isNew());
        stDeductionCalendarDetailsImpl.setPrimaryKey(stDeductionCalendarDetails.getPrimaryKey());

        stDeductionCalendarDetailsImpl.setAdjustmentBasis(stDeductionCalendarDetails.getAdjustmentBasis());
        stDeductionCalendarDetailsImpl.setPeriodSid(stDeductionCalendarDetails.getPeriodSid());
        stDeductionCalendarDetailsImpl.setAdjustmentValue(stDeductionCalendarDetails.getAdjustmentValue());
        stDeductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(stDeductionCalendarDetails.getAdjustmentAllocationMethodology());
        stDeductionCalendarDetailsImpl.setCompanyMasterSid(stDeductionCalendarDetails.getCompanyMasterSid());
        stDeductionCalendarDetailsImpl.setDiscountAmount(stDeductionCalendarDetails.getDiscountAmount());
        stDeductionCalendarDetailsImpl.setAdjustmentVariable(stDeductionCalendarDetails.getAdjustmentVariable());
        stDeductionCalendarDetailsImpl.setUserId(stDeductionCalendarDetails.getUserId());
        stDeductionCalendarDetailsImpl.setItemMasterSid(stDeductionCalendarDetails.getItemMasterSid());
        stDeductionCalendarDetailsImpl.setAdjustmentType(stDeductionCalendarDetails.getAdjustmentType());
        stDeductionCalendarDetailsImpl.setSessionId(stDeductionCalendarDetails.getSessionId());
        stDeductionCalendarDetailsImpl.setCheckRecord(stDeductionCalendarDetails.isCheckRecord());

        return stDeductionCalendarDetailsImpl;
    }

    /**
     * Returns the st deduction calendar details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st deduction calendar details
     * @return the st deduction calendar details
     * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStDeductionCalendarDetailsException, SystemException {
        StDeductionCalendarDetails stDeductionCalendarDetails = fetchByPrimaryKey(primaryKey);

        if (stDeductionCalendarDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stDeductionCalendarDetails;
    }

    /**
     * Returns the st deduction calendar details with the primary key or throws a {@link com.stpl.app.NoSuchStDeductionCalendarDetailsException} if it could not be found.
     *
     * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
     * @return the st deduction calendar details
     * @throws com.stpl.app.NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails findByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws NoSuchStDeductionCalendarDetailsException, SystemException {
        return findByPrimaryKey((Serializable) stDeductionCalendarDetailsPK);
    }

    /**
     * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st deduction calendar details
     * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StDeductionCalendarDetails stDeductionCalendarDetails = (StDeductionCalendarDetails) EntityCacheUtil.getResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                StDeductionCalendarDetailsImpl.class, primaryKey);

        if (stDeductionCalendarDetails == _nullStDeductionCalendarDetails) {
            return null;
        }

        if (stDeductionCalendarDetails == null) {
            Session session = null;

            try {
                session = openSession();

                stDeductionCalendarDetails = (StDeductionCalendarDetails) session.get(StDeductionCalendarDetailsImpl.class,
                        primaryKey);

                if (stDeductionCalendarDetails != null) {
                    cacheResult(stDeductionCalendarDetails);
                } else {
                    EntityCacheUtil.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        StDeductionCalendarDetailsImpl.class, primaryKey,
                        _nullStDeductionCalendarDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    StDeductionCalendarDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stDeductionCalendarDetails;
    }

    /**
     * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
     * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StDeductionCalendarDetails fetchByPrimaryKey(
        StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) stDeductionCalendarDetailsPK);
    }

    /**
     * Returns all the st deduction calendar detailses.
     *
     * @return the st deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StDeductionCalendarDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st deduction calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st deduction calendar detailses
     * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
     * @return the range of st deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StDeductionCalendarDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st deduction calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st deduction calendar detailses
     * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StDeductionCalendarDetails> findAll(int start, int end,
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

        List<StDeductionCalendarDetails> list = (List<StDeductionCalendarDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STDEDUCTIONCALENDARDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STDEDUCTIONCALENDARDETAILS;

                if (pagination) {
                    sql = sql.concat(StDeductionCalendarDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StDeductionCalendarDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StDeductionCalendarDetails>(list);
                } else {
                    list = (List<StDeductionCalendarDetails>) QueryUtil.list(q,
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
     * Removes all the st deduction calendar detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StDeductionCalendarDetails stDeductionCalendarDetails : findAll()) {
            remove(stDeductionCalendarDetails);
        }
    }

    /**
     * Returns the number of st deduction calendar detailses.
     *
     * @return the number of st deduction calendar detailses
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

                Query q = session.createQuery(_SQL_COUNT_STDEDUCTIONCALENDARDETAILS);

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
     * Initializes the st deduction calendar details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.StDeductionCalendarDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StDeductionCalendarDetails>> listenersList = new ArrayList<ModelListener<StDeductionCalendarDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StDeductionCalendarDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StDeductionCalendarDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
