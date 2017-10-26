package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDeductionDetailsException;
import com.stpl.app.model.DeductionDetails;
import com.stpl.app.model.impl.DeductionDetailsImpl;
import com.stpl.app.model.impl.DeductionDetailsModelImpl;
import com.stpl.app.service.persistence.DeductionDetailsPersistence;

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
 * The persistence implementation for the deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionDetailsPersistence
 * @see DeductionDetailsUtil
 * @generated
 */
public class DeductionDetailsPersistenceImpl extends BasePersistenceImpl<DeductionDetails>
    implements DeductionDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DeductionDetailsUtil} to access the deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DeductionDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEDUCTIONDETAILS = "SELECT deductionDetails FROM DeductionDetails deductionDetails";
    private static final String _SQL_COUNT_DEDUCTIONDETAILS = "SELECT COUNT(deductionDetails) FROM DeductionDetails deductionDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "deductionDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DeductionDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "netSalesFormulaMasterSid", "modifiedBy",
                "rsContractSid", "createdDate", "contractMasterSid",
                "deductionDetailsSid", "indicator", "modifiedDate",
                "recordLockStatus", "source", "cdrModelSid", "inboundStatus",
                "deductionSubType", "deductionType", "deductionCategory"
            });
    private static DeductionDetails _nullDeductionDetails = new DeductionDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DeductionDetails> toCacheModel() {
                return _nullDeductionDetailsCacheModel;
            }
        };

    private static CacheModel<DeductionDetails> _nullDeductionDetailsCacheModel = new CacheModel<DeductionDetails>() {
            @Override
            public DeductionDetails toEntityModel() {
                return _nullDeductionDetails;
            }
        };

    public DeductionDetailsPersistenceImpl() {
        setModelClass(DeductionDetails.class);
    }

    /**
     * Caches the deduction details in the entity cache if it is enabled.
     *
     * @param deductionDetails the deduction details
     */
    @Override
    public void cacheResult(DeductionDetails deductionDetails) {
        EntityCacheUtil.putResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsImpl.class, deductionDetails.getPrimaryKey(),
            deductionDetails);

        deductionDetails.resetOriginalValues();
    }

    /**
     * Caches the deduction detailses in the entity cache if it is enabled.
     *
     * @param deductionDetailses the deduction detailses
     */
    @Override
    public void cacheResult(List<DeductionDetails> deductionDetailses) {
        for (DeductionDetails deductionDetails : deductionDetailses) {
            if (EntityCacheUtil.getResult(
                        DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionDetailsImpl.class,
                        deductionDetails.getPrimaryKey()) == null) {
                cacheResult(deductionDetails);
            } else {
                deductionDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all deduction detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DeductionDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DeductionDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the deduction details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DeductionDetails deductionDetails) {
        EntityCacheUtil.removeResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsImpl.class, deductionDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<DeductionDetails> deductionDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DeductionDetails deductionDetails : deductionDetailses) {
            EntityCacheUtil.removeResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionDetailsImpl.class, deductionDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new deduction details with the primary key. Does not add the deduction details to the database.
     *
     * @param deductionDetailsSid the primary key for the new deduction details
     * @return the new deduction details
     */
    @Override
    public DeductionDetails create(int deductionDetailsSid) {
        DeductionDetails deductionDetails = new DeductionDetailsImpl();

        deductionDetails.setNew(true);
        deductionDetails.setPrimaryKey(deductionDetailsSid);

        return deductionDetails;
    }

    /**
     * Removes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param deductionDetailsSid the primary key of the deduction details
     * @return the deduction details that was removed
     * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails remove(int deductionDetailsSid)
        throws NoSuchDeductionDetailsException, SystemException {
        return remove((Serializable) deductionDetailsSid);
    }

    /**
     * Removes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the deduction details
     * @return the deduction details that was removed
     * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails remove(Serializable primaryKey)
        throws NoSuchDeductionDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DeductionDetails deductionDetails = (DeductionDetails) session.get(DeductionDetailsImpl.class,
                    primaryKey);

            if (deductionDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(deductionDetails);
        } catch (NoSuchDeductionDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DeductionDetails removeImpl(DeductionDetails deductionDetails)
        throws SystemException {
        deductionDetails = toUnwrappedModel(deductionDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(deductionDetails)) {
                deductionDetails = (DeductionDetails) session.get(DeductionDetailsImpl.class,
                        deductionDetails.getPrimaryKeyObj());
            }

            if (deductionDetails != null) {
                session.delete(deductionDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (deductionDetails != null) {
            clearCache(deductionDetails);
        }

        return deductionDetails;
    }

    @Override
    public DeductionDetails updateImpl(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws SystemException {
        deductionDetails = toUnwrappedModel(deductionDetails);

        boolean isNew = deductionDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (deductionDetails.isNew()) {
                session.save(deductionDetails);

                deductionDetails.setNew(false);
            } else {
                session.merge(deductionDetails);
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

        EntityCacheUtil.putResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionDetailsImpl.class, deductionDetails.getPrimaryKey(),
            deductionDetails);

        return deductionDetails;
    }

    protected DeductionDetails toUnwrappedModel(
        DeductionDetails deductionDetails) {
        if (deductionDetails instanceof DeductionDetailsImpl) {
            return deductionDetails;
        }

        DeductionDetailsImpl deductionDetailsImpl = new DeductionDetailsImpl();

        deductionDetailsImpl.setNew(deductionDetails.isNew());
        deductionDetailsImpl.setPrimaryKey(deductionDetails.getPrimaryKey());

        deductionDetailsImpl.setCreatedBy(deductionDetails.getCreatedBy());
        deductionDetailsImpl.setNetSalesFormulaMasterSid(deductionDetails.getNetSalesFormulaMasterSid());
        deductionDetailsImpl.setModifiedBy(deductionDetails.getModifiedBy());
        deductionDetailsImpl.setRsContractSid(deductionDetails.getRsContractSid());
        deductionDetailsImpl.setCreatedDate(deductionDetails.getCreatedDate());
        deductionDetailsImpl.setContractMasterSid(deductionDetails.getContractMasterSid());
        deductionDetailsImpl.setDeductionDetailsSid(deductionDetails.getDeductionDetailsSid());
        deductionDetailsImpl.setIndicator(deductionDetails.getIndicator());
        deductionDetailsImpl.setModifiedDate(deductionDetails.getModifiedDate());
        deductionDetailsImpl.setRecordLockStatus(deductionDetails.isRecordLockStatus());
        deductionDetailsImpl.setSource(deductionDetails.getSource());
        deductionDetailsImpl.setCdrModelSid(deductionDetails.getCdrModelSid());
        deductionDetailsImpl.setInboundStatus(deductionDetails.getInboundStatus());
        deductionDetailsImpl.setDeductionSubType(deductionDetails.getDeductionSubType());
        deductionDetailsImpl.setDeductionType(deductionDetails.getDeductionType());
        deductionDetailsImpl.setDeductionCategory(deductionDetails.getDeductionCategory());

        return deductionDetailsImpl;
    }

    /**
     * Returns the deduction details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the deduction details
     * @return the deduction details
     * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDeductionDetailsException, SystemException {
        DeductionDetails deductionDetails = fetchByPrimaryKey(primaryKey);

        if (deductionDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return deductionDetails;
    }

    /**
     * Returns the deduction details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionDetailsException} if it could not be found.
     *
     * @param deductionDetailsSid the primary key of the deduction details
     * @return the deduction details
     * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails findByPrimaryKey(int deductionDetailsSid)
        throws NoSuchDeductionDetailsException, SystemException {
        return findByPrimaryKey((Serializable) deductionDetailsSid);
    }

    /**
     * Returns the deduction details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the deduction details
     * @return the deduction details, or <code>null</code> if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DeductionDetails deductionDetails = (DeductionDetails) EntityCacheUtil.getResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionDetailsImpl.class, primaryKey);

        if (deductionDetails == _nullDeductionDetails) {
            return null;
        }

        if (deductionDetails == null) {
            Session session = null;

            try {
                session = openSession();

                deductionDetails = (DeductionDetails) session.get(DeductionDetailsImpl.class,
                        primaryKey);

                if (deductionDetails != null) {
                    cacheResult(deductionDetails);
                } else {
                    EntityCacheUtil.putResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionDetailsImpl.class, primaryKey,
                        _nullDeductionDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    DeductionDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return deductionDetails;
    }

    /**
     * Returns the deduction details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param deductionDetailsSid the primary key of the deduction details
     * @return the deduction details, or <code>null</code> if a deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionDetails fetchByPrimaryKey(int deductionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) deductionDetailsSid);
    }

    /**
     * Returns all the deduction detailses.
     *
     * @return the deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the deduction detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction detailses
     * @param end the upper bound of the range of deduction detailses (not inclusive)
     * @return the range of deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the deduction detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction detailses
     * @param end the upper bound of the range of deduction detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionDetails> findAll(int start, int end,
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

        List<DeductionDetails> list = (List<DeductionDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEDUCTIONDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEDUCTIONDETAILS;

                if (pagination) {
                    sql = sql.concat(DeductionDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DeductionDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DeductionDetails>(list);
                } else {
                    list = (List<DeductionDetails>) QueryUtil.list(q,
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
     * Removes all the deduction detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DeductionDetails deductionDetails : findAll()) {
            remove(deductionDetails);
        }
    }

    /**
     * Returns the number of deduction detailses.
     *
     * @return the number of deduction detailses
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

                Query q = session.createQuery(_SQL_COUNT_DEDUCTIONDETAILS);

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
     * Initializes the deduction details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DeductionDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DeductionDetails>> listenersList = new ArrayList<ModelListener<DeductionDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DeductionDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DeductionDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
