package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldFormulaDetailsException;
import com.stpl.app.model.IvldFormulaDetails;
import com.stpl.app.model.impl.IvldFormulaDetailsImpl;
import com.stpl.app.model.impl.IvldFormulaDetailsModelImpl;
import com.stpl.app.service.persistence.IvldFormulaDetailsPersistence;

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
 * The persistence implementation for the ivld formula details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldFormulaDetailsPersistence
 * @see IvldFormulaDetailsUtil
 * @generated
 */
public class IvldFormulaDetailsPersistenceImpl extends BasePersistenceImpl<IvldFormulaDetails>
    implements IvldFormulaDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldFormulaDetailsUtil} to access the ivld formula details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldFormulaDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED,
            IvldFormulaDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED,
            IvldFormulaDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDFORMULADETAILS = "SELECT ivldFormulaDetails FROM IvldFormulaDetails ivldFormulaDetails";
    private static final String _SQL_COUNT_IVLDFORMULADETAILS = "SELECT COUNT(ivldFormulaDetails) FROM IvldFormulaDetails ivldFormulaDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldFormulaDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldFormulaDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldFormulaDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "endDate", "rebatePercent1", "itemId", "rebatePercent2",
                "formulaDesc", "modifiedDate", "rebatePercent3", "createdBy",
                "createdDate", "source", "addChgDelIndicator", "errorCode",
                "formulaId", "modifiedBy", "intfInsertedDate", "reprocessedFlag",
                "formulaDetailsIntfid", "reasonForFailure", "contractPrice1",
                "companyId", "contractPrice2", "formulaNo", "startDate",
                "batchId", "errorField", "contractPrice3",
                "ivldFormulaDetailsSid", "checkRecord"
            });
    private static IvldFormulaDetails _nullIvldFormulaDetails = new IvldFormulaDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldFormulaDetails> toCacheModel() {
                return _nullIvldFormulaDetailsCacheModel;
            }
        };

    private static CacheModel<IvldFormulaDetails> _nullIvldFormulaDetailsCacheModel =
        new CacheModel<IvldFormulaDetails>() {
            @Override
            public IvldFormulaDetails toEntityModel() {
                return _nullIvldFormulaDetails;
            }
        };

    public IvldFormulaDetailsPersistenceImpl() {
        setModelClass(IvldFormulaDetails.class);
    }

    /**
     * Caches the ivld formula details in the entity cache if it is enabled.
     *
     * @param ivldFormulaDetails the ivld formula details
     */
    @Override
    public void cacheResult(IvldFormulaDetails ivldFormulaDetails) {
        EntityCacheUtil.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey(),
            ivldFormulaDetails);

        ivldFormulaDetails.resetOriginalValues();
    }

    /**
     * Caches the ivld formula detailses in the entity cache if it is enabled.
     *
     * @param ivldFormulaDetailses the ivld formula detailses
     */
    @Override
    public void cacheResult(List<IvldFormulaDetails> ivldFormulaDetailses) {
        for (IvldFormulaDetails ivldFormulaDetails : ivldFormulaDetailses) {
            if (EntityCacheUtil.getResult(
                        IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IvldFormulaDetailsImpl.class,
                        ivldFormulaDetails.getPrimaryKey()) == null) {
                cacheResult(ivldFormulaDetails);
            } else {
                ivldFormulaDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld formula detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldFormulaDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldFormulaDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld formula details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldFormulaDetails ivldFormulaDetails) {
        EntityCacheUtil.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldFormulaDetails> ivldFormulaDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldFormulaDetails ivldFormulaDetails : ivldFormulaDetailses) {
            EntityCacheUtil.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
     *
     * @param ivldFormulaDetailsSid the primary key for the new ivld formula details
     * @return the new ivld formula details
     */
    @Override
    public IvldFormulaDetails create(int ivldFormulaDetailsSid) {
        IvldFormulaDetails ivldFormulaDetails = new IvldFormulaDetailsImpl();

        ivldFormulaDetails.setNew(true);
        ivldFormulaDetails.setPrimaryKey(ivldFormulaDetailsSid);

        return ivldFormulaDetails;
    }

    /**
     * Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldFormulaDetailsSid the primary key of the ivld formula details
     * @return the ivld formula details that was removed
     * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails remove(int ivldFormulaDetailsSid)
        throws NoSuchIvldFormulaDetailsException, SystemException {
        return remove((Serializable) ivldFormulaDetailsSid);
    }

    /**
     * Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld formula details
     * @return the ivld formula details that was removed
     * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails remove(Serializable primaryKey)
        throws NoSuchIvldFormulaDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldFormulaDetails ivldFormulaDetails = (IvldFormulaDetails) session.get(IvldFormulaDetailsImpl.class,
                    primaryKey);

            if (ivldFormulaDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldFormulaDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldFormulaDetails);
        } catch (NoSuchIvldFormulaDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldFormulaDetails removeImpl(
        IvldFormulaDetails ivldFormulaDetails) throws SystemException {
        ivldFormulaDetails = toUnwrappedModel(ivldFormulaDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldFormulaDetails)) {
                ivldFormulaDetails = (IvldFormulaDetails) session.get(IvldFormulaDetailsImpl.class,
                        ivldFormulaDetails.getPrimaryKeyObj());
            }

            if (ivldFormulaDetails != null) {
                session.delete(ivldFormulaDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldFormulaDetails != null) {
            clearCache(ivldFormulaDetails);
        }

        return ivldFormulaDetails;
    }

    @Override
    public IvldFormulaDetails updateImpl(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails)
        throws SystemException {
        ivldFormulaDetails = toUnwrappedModel(ivldFormulaDetails);

        boolean isNew = ivldFormulaDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldFormulaDetails.isNew()) {
                session.save(ivldFormulaDetails);

                ivldFormulaDetails.setNew(false);
            } else {
                session.merge(ivldFormulaDetails);
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

        EntityCacheUtil.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
            IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey(),
            ivldFormulaDetails);

        return ivldFormulaDetails;
    }

    protected IvldFormulaDetails toUnwrappedModel(
        IvldFormulaDetails ivldFormulaDetails) {
        if (ivldFormulaDetails instanceof IvldFormulaDetailsImpl) {
            return ivldFormulaDetails;
        }

        IvldFormulaDetailsImpl ivldFormulaDetailsImpl = new IvldFormulaDetailsImpl();

        ivldFormulaDetailsImpl.setNew(ivldFormulaDetails.isNew());
        ivldFormulaDetailsImpl.setPrimaryKey(ivldFormulaDetails.getPrimaryKey());

        ivldFormulaDetailsImpl.setEndDate(ivldFormulaDetails.getEndDate());
        ivldFormulaDetailsImpl.setRebatePercent1(ivldFormulaDetails.getRebatePercent1());
        ivldFormulaDetailsImpl.setItemId(ivldFormulaDetails.getItemId());
        ivldFormulaDetailsImpl.setRebatePercent2(ivldFormulaDetails.getRebatePercent2());
        ivldFormulaDetailsImpl.setFormulaDesc(ivldFormulaDetails.getFormulaDesc());
        ivldFormulaDetailsImpl.setModifiedDate(ivldFormulaDetails.getModifiedDate());
        ivldFormulaDetailsImpl.setRebatePercent3(ivldFormulaDetails.getRebatePercent3());
        ivldFormulaDetailsImpl.setCreatedBy(ivldFormulaDetails.getCreatedBy());
        ivldFormulaDetailsImpl.setCreatedDate(ivldFormulaDetails.getCreatedDate());
        ivldFormulaDetailsImpl.setSource(ivldFormulaDetails.getSource());
        ivldFormulaDetailsImpl.setAddChgDelIndicator(ivldFormulaDetails.getAddChgDelIndicator());
        ivldFormulaDetailsImpl.setErrorCode(ivldFormulaDetails.getErrorCode());
        ivldFormulaDetailsImpl.setFormulaId(ivldFormulaDetails.getFormulaId());
        ivldFormulaDetailsImpl.setModifiedBy(ivldFormulaDetails.getModifiedBy());
        ivldFormulaDetailsImpl.setIntfInsertedDate(ivldFormulaDetails.getIntfInsertedDate());
        ivldFormulaDetailsImpl.setReprocessedFlag(ivldFormulaDetails.getReprocessedFlag());
        ivldFormulaDetailsImpl.setFormulaDetailsIntfid(ivldFormulaDetails.getFormulaDetailsIntfid());
        ivldFormulaDetailsImpl.setReasonForFailure(ivldFormulaDetails.getReasonForFailure());
        ivldFormulaDetailsImpl.setContractPrice1(ivldFormulaDetails.getContractPrice1());
        ivldFormulaDetailsImpl.setCompanyId(ivldFormulaDetails.getCompanyId());
        ivldFormulaDetailsImpl.setContractPrice2(ivldFormulaDetails.getContractPrice2());
        ivldFormulaDetailsImpl.setFormulaNo(ivldFormulaDetails.getFormulaNo());
        ivldFormulaDetailsImpl.setStartDate(ivldFormulaDetails.getStartDate());
        ivldFormulaDetailsImpl.setBatchId(ivldFormulaDetails.getBatchId());
        ivldFormulaDetailsImpl.setErrorField(ivldFormulaDetails.getErrorField());
        ivldFormulaDetailsImpl.setContractPrice3(ivldFormulaDetails.getContractPrice3());
        ivldFormulaDetailsImpl.setIvldFormulaDetailsSid(ivldFormulaDetails.getIvldFormulaDetailsSid());
        ivldFormulaDetailsImpl.setCheckRecord(ivldFormulaDetails.isCheckRecord());

        return ivldFormulaDetailsImpl;
    }

    /**
     * Returns the ivld formula details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld formula details
     * @return the ivld formula details
     * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldFormulaDetailsException, SystemException {
        IvldFormulaDetails ivldFormulaDetails = fetchByPrimaryKey(primaryKey);

        if (ivldFormulaDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldFormulaDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldFormulaDetails;
    }

    /**
     * Returns the ivld formula details with the primary key or throws a {@link com.stpl.app.NoSuchIvldFormulaDetailsException} if it could not be found.
     *
     * @param ivldFormulaDetailsSid the primary key of the ivld formula details
     * @return the ivld formula details
     * @throws com.stpl.app.NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails findByPrimaryKey(int ivldFormulaDetailsSid)
        throws NoSuchIvldFormulaDetailsException, SystemException {
        return findByPrimaryKey((Serializable) ivldFormulaDetailsSid);
    }

    /**
     * Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld formula details
     * @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldFormulaDetails ivldFormulaDetails = (IvldFormulaDetails) EntityCacheUtil.getResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
                IvldFormulaDetailsImpl.class, primaryKey);

        if (ivldFormulaDetails == _nullIvldFormulaDetails) {
            return null;
        }

        if (ivldFormulaDetails == null) {
            Session session = null;

            try {
                session = openSession();

                ivldFormulaDetails = (IvldFormulaDetails) session.get(IvldFormulaDetailsImpl.class,
                        primaryKey);

                if (ivldFormulaDetails != null) {
                    cacheResult(ivldFormulaDetails);
                } else {
                    EntityCacheUtil.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        IvldFormulaDetailsImpl.class, primaryKey,
                        _nullIvldFormulaDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    IvldFormulaDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldFormulaDetails;
    }

    /**
     * Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldFormulaDetailsSid the primary key of the ivld formula details
     * @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldFormulaDetails fetchByPrimaryKey(int ivldFormulaDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldFormulaDetailsSid);
    }

    /**
     * Returns all the ivld formula detailses.
     *
     * @return the ivld formula detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldFormulaDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld formula detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld formula detailses
     * @param end the upper bound of the range of ivld formula detailses (not inclusive)
     * @return the range of ivld formula detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldFormulaDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld formula detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld formula detailses
     * @param end the upper bound of the range of ivld formula detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld formula detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldFormulaDetails> findAll(int start, int end,
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

        List<IvldFormulaDetails> list = (List<IvldFormulaDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDFORMULADETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDFORMULADETAILS;

                if (pagination) {
                    sql = sql.concat(IvldFormulaDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldFormulaDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldFormulaDetails>(list);
                } else {
                    list = (List<IvldFormulaDetails>) QueryUtil.list(q,
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
     * Removes all the ivld formula detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldFormulaDetails ivldFormulaDetails : findAll()) {
            remove(ivldFormulaDetails);
        }
    }

    /**
     * Returns the number of ivld formula detailses.
     *
     * @return the number of ivld formula detailses
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

                Query q = session.createQuery(_SQL_COUNT_IVLDFORMULADETAILS);

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
     * Initializes the ivld formula details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldFormulaDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldFormulaDetails>> listenersList = new ArrayList<ModelListener<IvldFormulaDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldFormulaDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldFormulaDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
