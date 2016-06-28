package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdDeductionDetailsException;
import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.model.impl.ImtdDeductionDetailsImpl;
import com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdDeductionDetailsPersistence;

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
 * The persistence implementation for the imtd deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdDeductionDetailsPersistence
 * @see ImtdDeductionDetailsUtil
 * @generated
 */
public class ImtdDeductionDetailsPersistenceImpl extends BasePersistenceImpl<ImtdDeductionDetails>
    implements ImtdDeductionDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdDeductionDetailsUtil} to access the imtd deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdDeductionDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdDeductionDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdDeductionDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDDEDUCTIONDETAILS = "SELECT imtdDeductionDetails FROM ImtdDeductionDetails imtdDeductionDetails";
    private static final String _SQL_COUNT_IMTDDEDUCTIONDETAILS = "SELECT COUNT(imtdDeductionDetails) FROM ImtdDeductionDetails imtdDeductionDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdDeductionDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdDeductionDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdDeductionDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "imtdDeductionDetailsSid", "deductionName", "modifiedBy",
                "createdDate", "imtdCreatedDate", "deductionDetailsSid",
                "indicator", "contractNo", "checkRecord", "deductionSubType",
                "cdrModelSid", "createdBy", "deductionNo",
                "netSalesFormulaMasterSid", "usersSid", "contractMasterSid",
                "contractName", "deductionCategory", "modifiedDate",
                "deductionType", "recordLockStatus", "operation", "sessionId",
                "rsContractSid", "inboundStatus"
            });
    private static ImtdDeductionDetails _nullImtdDeductionDetails = new ImtdDeductionDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdDeductionDetails> toCacheModel() {
                return _nullImtdDeductionDetailsCacheModel;
            }
        };

    private static CacheModel<ImtdDeductionDetails> _nullImtdDeductionDetailsCacheModel =
        new CacheModel<ImtdDeductionDetails>() {
            @Override
            public ImtdDeductionDetails toEntityModel() {
                return _nullImtdDeductionDetails;
            }
        };

    public ImtdDeductionDetailsPersistenceImpl() {
        setModelClass(ImtdDeductionDetails.class);
    }

    /**
     * Caches the imtd deduction details in the entity cache if it is enabled.
     *
     * @param imtdDeductionDetails the imtd deduction details
     */
    @Override
    public void cacheResult(ImtdDeductionDetails imtdDeductionDetails) {
        EntityCacheUtil.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsImpl.class,
            imtdDeductionDetails.getPrimaryKey(), imtdDeductionDetails);

        imtdDeductionDetails.resetOriginalValues();
    }

    /**
     * Caches the imtd deduction detailses in the entity cache if it is enabled.
     *
     * @param imtdDeductionDetailses the imtd deduction detailses
     */
    @Override
    public void cacheResult(List<ImtdDeductionDetails> imtdDeductionDetailses) {
        for (ImtdDeductionDetails imtdDeductionDetails : imtdDeductionDetailses) {
            if (EntityCacheUtil.getResult(
                        ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdDeductionDetailsImpl.class,
                        imtdDeductionDetails.getPrimaryKey()) == null) {
                cacheResult(imtdDeductionDetails);
            } else {
                imtdDeductionDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd deduction detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdDeductionDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdDeductionDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd deduction details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdDeductionDetails imtdDeductionDetails) {
        EntityCacheUtil.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsImpl.class, imtdDeductionDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdDeductionDetails> imtdDeductionDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdDeductionDetails imtdDeductionDetails : imtdDeductionDetailses) {
            EntityCacheUtil.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdDeductionDetailsImpl.class,
                imtdDeductionDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd deduction details with the primary key. Does not add the imtd deduction details to the database.
     *
     * @param imtdDeductionDetailsSid the primary key for the new imtd deduction details
     * @return the new imtd deduction details
     */
    @Override
    public ImtdDeductionDetails create(int imtdDeductionDetailsSid) {
        ImtdDeductionDetails imtdDeductionDetails = new ImtdDeductionDetailsImpl();

        imtdDeductionDetails.setNew(true);
        imtdDeductionDetails.setPrimaryKey(imtdDeductionDetailsSid);

        return imtdDeductionDetails;
    }

    /**
     * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
     * @return the imtd deduction details that was removed
     * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails remove(int imtdDeductionDetailsSid)
        throws NoSuchImtdDeductionDetailsException, SystemException {
        return remove((Serializable) imtdDeductionDetailsSid);
    }

    /**
     * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd deduction details
     * @return the imtd deduction details that was removed
     * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails remove(Serializable primaryKey)
        throws NoSuchImtdDeductionDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdDeductionDetails imtdDeductionDetails = (ImtdDeductionDetails) session.get(ImtdDeductionDetailsImpl.class,
                    primaryKey);

            if (imtdDeductionDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdDeductionDetails);
        } catch (NoSuchImtdDeductionDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdDeductionDetails removeImpl(
        ImtdDeductionDetails imtdDeductionDetails) throws SystemException {
        imtdDeductionDetails = toUnwrappedModel(imtdDeductionDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdDeductionDetails)) {
                imtdDeductionDetails = (ImtdDeductionDetails) session.get(ImtdDeductionDetailsImpl.class,
                        imtdDeductionDetails.getPrimaryKeyObj());
            }

            if (imtdDeductionDetails != null) {
                session.delete(imtdDeductionDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdDeductionDetails != null) {
            clearCache(imtdDeductionDetails);
        }

        return imtdDeductionDetails;
    }

    @Override
    public ImtdDeductionDetails updateImpl(
        com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails)
        throws SystemException {
        imtdDeductionDetails = toUnwrappedModel(imtdDeductionDetails);

        boolean isNew = imtdDeductionDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdDeductionDetails.isNew()) {
                session.save(imtdDeductionDetails);

                imtdDeductionDetails.setNew(false);
            } else {
                session.merge(imtdDeductionDetails);
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

        EntityCacheUtil.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdDeductionDetailsImpl.class,
            imtdDeductionDetails.getPrimaryKey(), imtdDeductionDetails);

        return imtdDeductionDetails;
    }

    protected ImtdDeductionDetails toUnwrappedModel(
        ImtdDeductionDetails imtdDeductionDetails) {
        if (imtdDeductionDetails instanceof ImtdDeductionDetailsImpl) {
            return imtdDeductionDetails;
        }

        ImtdDeductionDetailsImpl imtdDeductionDetailsImpl = new ImtdDeductionDetailsImpl();

        imtdDeductionDetailsImpl.setNew(imtdDeductionDetails.isNew());
        imtdDeductionDetailsImpl.setPrimaryKey(imtdDeductionDetails.getPrimaryKey());

        imtdDeductionDetailsImpl.setImtdDeductionDetailsSid(imtdDeductionDetails.getImtdDeductionDetailsSid());
        imtdDeductionDetailsImpl.setDeductionName(imtdDeductionDetails.getDeductionName());
        imtdDeductionDetailsImpl.setModifiedBy(imtdDeductionDetails.getModifiedBy());
        imtdDeductionDetailsImpl.setCreatedDate(imtdDeductionDetails.getCreatedDate());
        imtdDeductionDetailsImpl.setImtdCreatedDate(imtdDeductionDetails.getImtdCreatedDate());
        imtdDeductionDetailsImpl.setDeductionDetailsSid(imtdDeductionDetails.getDeductionDetailsSid());
        imtdDeductionDetailsImpl.setIndicator(imtdDeductionDetails.getIndicator());
        imtdDeductionDetailsImpl.setContractNo(imtdDeductionDetails.getContractNo());
        imtdDeductionDetailsImpl.setCheckRecord(imtdDeductionDetails.isCheckRecord());
        imtdDeductionDetailsImpl.setDeductionSubType(imtdDeductionDetails.getDeductionSubType());
        imtdDeductionDetailsImpl.setCdrModelSid(imtdDeductionDetails.getCdrModelSid());
        imtdDeductionDetailsImpl.setCreatedBy(imtdDeductionDetails.getCreatedBy());
        imtdDeductionDetailsImpl.setDeductionNo(imtdDeductionDetails.getDeductionNo());
        imtdDeductionDetailsImpl.setNetSalesFormulaMasterSid(imtdDeductionDetails.getNetSalesFormulaMasterSid());
        imtdDeductionDetailsImpl.setUsersSid(imtdDeductionDetails.getUsersSid());
        imtdDeductionDetailsImpl.setContractMasterSid(imtdDeductionDetails.getContractMasterSid());
        imtdDeductionDetailsImpl.setContractName(imtdDeductionDetails.getContractName());
        imtdDeductionDetailsImpl.setDeductionCategory(imtdDeductionDetails.getDeductionCategory());
        imtdDeductionDetailsImpl.setModifiedDate(imtdDeductionDetails.getModifiedDate());
        imtdDeductionDetailsImpl.setDeductionType(imtdDeductionDetails.getDeductionType());
        imtdDeductionDetailsImpl.setRecordLockStatus(imtdDeductionDetails.isRecordLockStatus());
        imtdDeductionDetailsImpl.setOperation(imtdDeductionDetails.getOperation());
        imtdDeductionDetailsImpl.setSessionId(imtdDeductionDetails.getSessionId());
        imtdDeductionDetailsImpl.setRsContractSid(imtdDeductionDetails.getRsContractSid());
        imtdDeductionDetailsImpl.setInboundStatus(imtdDeductionDetails.getInboundStatus());

        return imtdDeductionDetailsImpl;
    }

    /**
     * Returns the imtd deduction details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd deduction details
     * @return the imtd deduction details
     * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdDeductionDetailsException, SystemException {
        ImtdDeductionDetails imtdDeductionDetails = fetchByPrimaryKey(primaryKey);

        if (imtdDeductionDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdDeductionDetails;
    }

    /**
     * Returns the imtd deduction details with the primary key or throws a {@link com.stpl.app.NoSuchImtdDeductionDetailsException} if it could not be found.
     *
     * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
     * @return the imtd deduction details
     * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails findByPrimaryKey(int imtdDeductionDetailsSid)
        throws NoSuchImtdDeductionDetailsException, SystemException {
        return findByPrimaryKey((Serializable) imtdDeductionDetailsSid);
    }

    /**
     * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd deduction details
     * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdDeductionDetails imtdDeductionDetails = (ImtdDeductionDetails) EntityCacheUtil.getResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdDeductionDetailsImpl.class, primaryKey);

        if (imtdDeductionDetails == _nullImtdDeductionDetails) {
            return null;
        }

        if (imtdDeductionDetails == null) {
            Session session = null;

            try {
                session = openSession();

                imtdDeductionDetails = (ImtdDeductionDetails) session.get(ImtdDeductionDetailsImpl.class,
                        primaryKey);

                if (imtdDeductionDetails != null) {
                    cacheResult(imtdDeductionDetails);
                } else {
                    EntityCacheUtil.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdDeductionDetailsImpl.class, primaryKey,
                        _nullImtdDeductionDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdDeductionDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdDeductionDetails;
    }

    /**
     * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
     * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdDeductionDetails fetchByPrimaryKey(int imtdDeductionDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdDeductionDetailsSid);
    }

    /**
     * Returns all the imtd deduction detailses.
     *
     * @return the imtd deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdDeductionDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd deduction detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd deduction detailses
     * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
     * @return the range of imtd deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdDeductionDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd deduction detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd deduction detailses
     * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd deduction detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdDeductionDetails> findAll(int start, int end,
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

        List<ImtdDeductionDetails> list = (List<ImtdDeductionDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDDEDUCTIONDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDDEDUCTIONDETAILS;

                if (pagination) {
                    sql = sql.concat(ImtdDeductionDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdDeductionDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdDeductionDetails>(list);
                } else {
                    list = (List<ImtdDeductionDetails>) QueryUtil.list(q,
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
     * Removes all the imtd deduction detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdDeductionDetails imtdDeductionDetails : findAll()) {
            remove(imtdDeductionDetails);
        }
    }

    /**
     * Returns the number of imtd deduction detailses.
     *
     * @return the number of imtd deduction detailses
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

                Query q = session.createQuery(_SQL_COUNT_IMTDDEDUCTIONDETAILS);

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
     * Initializes the imtd deduction details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdDeductionDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdDeductionDetails>> listenersList = new ArrayList<ModelListener<ImtdDeductionDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdDeductionDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdDeductionDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
