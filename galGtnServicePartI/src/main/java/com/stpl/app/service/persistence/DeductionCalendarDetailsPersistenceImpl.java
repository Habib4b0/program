package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDeductionCalendarDetailsException;
import com.stpl.app.model.DeductionCalendarDetails;
import com.stpl.app.model.impl.DeductionCalendarDetailsImpl;
import com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPersistence;

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
 * The persistence implementation for the deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarDetailsPersistence
 * @see DeductionCalendarDetailsUtil
 * @generated
 */
public class DeductionCalendarDetailsPersistenceImpl extends BasePersistenceImpl<DeductionCalendarDetails>
    implements DeductionCalendarDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DeductionCalendarDetailsUtil} to access the deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DeductionCalendarDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEDUCTIONCALENDARDETAILS = "SELECT deductionCalendarDetails FROM DeductionCalendarDetails deductionCalendarDetails";
    private static final String _SQL_COUNT_DEDUCTIONCALENDARDETAILS = "SELECT COUNT(deductionCalendarDetails) FROM DeductionCalendarDetails deductionCalendarDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "deductionCalendarDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionCalendarDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DeductionCalendarDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "deductionCalendarMasterSid", "adjustmentBasis", "periodSid",
                "adjustmentValue", "adjustmentAllocationMethodology",
                "companyMasterSid", "discountAmount", "adjustmentVariable",
                "itemMasterSid", "adjustmentType", "checkRecord"
            });
    private static DeductionCalendarDetails _nullDeductionCalendarDetails = new DeductionCalendarDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DeductionCalendarDetails> toCacheModel() {
                return _nullDeductionCalendarDetailsCacheModel;
            }
        };

    private static CacheModel<DeductionCalendarDetails> _nullDeductionCalendarDetailsCacheModel =
        new CacheModel<DeductionCalendarDetails>() {
            @Override
            public DeductionCalendarDetails toEntityModel() {
                return _nullDeductionCalendarDetails;
            }
        };

    public DeductionCalendarDetailsPersistenceImpl() {
        setModelClass(DeductionCalendarDetails.class);
    }

    /**
     * Caches the deduction calendar details in the entity cache if it is enabled.
     *
     * @param deductionCalendarDetails the deduction calendar details
     */
    @Override
    public void cacheResult(DeductionCalendarDetails deductionCalendarDetails) {
        EntityCacheUtil.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsImpl.class,
            deductionCalendarDetails.getPrimaryKey(), deductionCalendarDetails);

        deductionCalendarDetails.resetOriginalValues();
    }

    /**
     * Caches the deduction calendar detailses in the entity cache if it is enabled.
     *
     * @param deductionCalendarDetailses the deduction calendar detailses
     */
    @Override
    public void cacheResult(
        List<DeductionCalendarDetails> deductionCalendarDetailses) {
        for (DeductionCalendarDetails deductionCalendarDetails : deductionCalendarDetailses) {
            if (EntityCacheUtil.getResult(
                        DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionCalendarDetailsImpl.class,
                        deductionCalendarDetails.getPrimaryKey()) == null) {
                cacheResult(deductionCalendarDetails);
            } else {
                deductionCalendarDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all deduction calendar detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DeductionCalendarDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DeductionCalendarDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the deduction calendar details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DeductionCalendarDetails deductionCalendarDetails) {
        EntityCacheUtil.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsImpl.class,
            deductionCalendarDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<DeductionCalendarDetails> deductionCalendarDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DeductionCalendarDetails deductionCalendarDetails : deductionCalendarDetailses) {
            EntityCacheUtil.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionCalendarDetailsImpl.class,
                deductionCalendarDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new deduction calendar details with the primary key. Does not add the deduction calendar details to the database.
     *
     * @param deductionCalendarDetailsPK the primary key for the new deduction calendar details
     * @return the new deduction calendar details
     */
    @Override
    public DeductionCalendarDetails create(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK) {
        DeductionCalendarDetails deductionCalendarDetails = new DeductionCalendarDetailsImpl();

        deductionCalendarDetails.setNew(true);
        deductionCalendarDetails.setPrimaryKey(deductionCalendarDetailsPK);

        return deductionCalendarDetails;
    }

    /**
     * Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
     * @return the deduction calendar details that was removed
     * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails remove(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws NoSuchDeductionCalendarDetailsException, SystemException {
        return remove((Serializable) deductionCalendarDetailsPK);
    }

    /**
     * Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the deduction calendar details
     * @return the deduction calendar details that was removed
     * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails remove(Serializable primaryKey)
        throws NoSuchDeductionCalendarDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DeductionCalendarDetails deductionCalendarDetails = (DeductionCalendarDetails) session.get(DeductionCalendarDetailsImpl.class,
                    primaryKey);

            if (deductionCalendarDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(deductionCalendarDetails);
        } catch (NoSuchDeductionCalendarDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DeductionCalendarDetails removeImpl(
        DeductionCalendarDetails deductionCalendarDetails)
        throws SystemException {
        deductionCalendarDetails = toUnwrappedModel(deductionCalendarDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(deductionCalendarDetails)) {
                deductionCalendarDetails = (DeductionCalendarDetails) session.get(DeductionCalendarDetailsImpl.class,
                        deductionCalendarDetails.getPrimaryKeyObj());
            }

            if (deductionCalendarDetails != null) {
                session.delete(deductionCalendarDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (deductionCalendarDetails != null) {
            clearCache(deductionCalendarDetails);
        }

        return deductionCalendarDetails;
    }

    @Override
    public DeductionCalendarDetails updateImpl(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails)
        throws SystemException {
        deductionCalendarDetails = toUnwrappedModel(deductionCalendarDetails);

        boolean isNew = deductionCalendarDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (deductionCalendarDetails.isNew()) {
                session.save(deductionCalendarDetails);

                deductionCalendarDetails.setNew(false);
            } else {
                session.merge(deductionCalendarDetails);
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

        EntityCacheUtil.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarDetailsImpl.class,
            deductionCalendarDetails.getPrimaryKey(), deductionCalendarDetails);

        return deductionCalendarDetails;
    }

    protected DeductionCalendarDetails toUnwrappedModel(
        DeductionCalendarDetails deductionCalendarDetails) {
        if (deductionCalendarDetails instanceof DeductionCalendarDetailsImpl) {
            return deductionCalendarDetails;
        }

        DeductionCalendarDetailsImpl deductionCalendarDetailsImpl = new DeductionCalendarDetailsImpl();

        deductionCalendarDetailsImpl.setNew(deductionCalendarDetails.isNew());
        deductionCalendarDetailsImpl.setPrimaryKey(deductionCalendarDetails.getPrimaryKey());

        deductionCalendarDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarDetails.getDeductionCalendarMasterSid());
        deductionCalendarDetailsImpl.setAdjustmentBasis(deductionCalendarDetails.getAdjustmentBasis());
        deductionCalendarDetailsImpl.setPeriodSid(deductionCalendarDetails.getPeriodSid());
        deductionCalendarDetailsImpl.setAdjustmentValue(deductionCalendarDetails.getAdjustmentValue());
        deductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(deductionCalendarDetails.getAdjustmentAllocationMethodology());
        deductionCalendarDetailsImpl.setCompanyMasterSid(deductionCalendarDetails.getCompanyMasterSid());
        deductionCalendarDetailsImpl.setDiscountAmount(deductionCalendarDetails.getDiscountAmount());
        deductionCalendarDetailsImpl.setAdjustmentVariable(deductionCalendarDetails.getAdjustmentVariable());
        deductionCalendarDetailsImpl.setItemMasterSid(deductionCalendarDetails.getItemMasterSid());
        deductionCalendarDetailsImpl.setAdjustmentType(deductionCalendarDetails.getAdjustmentType());
        deductionCalendarDetailsImpl.setCheckRecord(deductionCalendarDetails.isCheckRecord());

        return deductionCalendarDetailsImpl;
    }

    /**
     * Returns the deduction calendar details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the deduction calendar details
     * @return the deduction calendar details
     * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDeductionCalendarDetailsException, SystemException {
        DeductionCalendarDetails deductionCalendarDetails = fetchByPrimaryKey(primaryKey);

        if (deductionCalendarDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return deductionCalendarDetails;
    }

    /**
     * Returns the deduction calendar details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionCalendarDetailsException} if it could not be found.
     *
     * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
     * @return the deduction calendar details
     * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails findByPrimaryKey(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws NoSuchDeductionCalendarDetailsException, SystemException {
        return findByPrimaryKey((Serializable) deductionCalendarDetailsPK);
    }

    /**
     * Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the deduction calendar details
     * @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DeductionCalendarDetails deductionCalendarDetails = (DeductionCalendarDetails) EntityCacheUtil.getResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionCalendarDetailsImpl.class, primaryKey);

        if (deductionCalendarDetails == _nullDeductionCalendarDetails) {
            return null;
        }

        if (deductionCalendarDetails == null) {
            Session session = null;

            try {
                session = openSession();

                deductionCalendarDetails = (DeductionCalendarDetails) session.get(DeductionCalendarDetailsImpl.class,
                        primaryKey);

                if (deductionCalendarDetails != null) {
                    cacheResult(deductionCalendarDetails);
                } else {
                    EntityCacheUtil.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionCalendarDetailsImpl.class, primaryKey,
                        _nullDeductionCalendarDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    DeductionCalendarDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return deductionCalendarDetails;
    }

    /**
     * Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
     * @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarDetails fetchByPrimaryKey(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) deductionCalendarDetailsPK);
    }

    /**
     * Returns all the deduction calendar detailses.
     *
     * @return the deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the deduction calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction calendar detailses
     * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
     * @return the range of deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the deduction calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction calendar detailses
     * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of deduction calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarDetails> findAll(int start, int end,
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

        List<DeductionCalendarDetails> list = (List<DeductionCalendarDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEDUCTIONCALENDARDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEDUCTIONCALENDARDETAILS;

                if (pagination) {
                    sql = sql.concat(DeductionCalendarDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DeductionCalendarDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DeductionCalendarDetails>(list);
                } else {
                    list = (List<DeductionCalendarDetails>) QueryUtil.list(q,
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
     * Removes all the deduction calendar detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DeductionCalendarDetails deductionCalendarDetails : findAll()) {
            remove(deductionCalendarDetails);
        }
    }

    /**
     * Returns the number of deduction calendar detailses.
     *
     * @return the number of deduction calendar detailses
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

                Query q = session.createQuery(_SQL_COUNT_DEDUCTIONCALENDARDETAILS);

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
     * Initializes the deduction calendar details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DeductionCalendarDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DeductionCalendarDetails>> listenersList = new ArrayList<ModelListener<DeductionCalendarDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DeductionCalendarDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DeductionCalendarDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
