package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdSalesBasisDetailsException;
import com.stpl.app.model.ImtdSalesBasisDetails;
import com.stpl.app.model.impl.ImtdSalesBasisDetailsImpl;
import com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdSalesBasisDetailsPersistence;

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
 * The persistence implementation for the imtd sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetailsPersistence
 * @see ImtdSalesBasisDetailsUtil
 * @generated
 */
public class ImtdSalesBasisDetailsPersistenceImpl extends BasePersistenceImpl<ImtdSalesBasisDetails>
    implements ImtdSalesBasisDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdSalesBasisDetailsUtil} to access the imtd sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdSalesBasisDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdSalesBasisDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
            ImtdSalesBasisDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDSALESBASISDETAILS = "SELECT imtdSalesBasisDetails FROM ImtdSalesBasisDetails imtdSalesBasisDetails";
    private static final String _SQL_COUNT_IMTDSALESBASISDETAILS = "SELECT COUNT(imtdSalesBasisDetails) FROM ImtdSalesBasisDetails imtdSalesBasisDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdSalesBasisDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdSalesBasisDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdSalesBasisDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "netSalesFormulaMasterSid", "usersSid",
                "modifiedBy", "createdDate", "contractMasterSid", "cfpNo",
                "imtdCreatedDate", "contractNo", "contractName",
                "salesBasisDetailsSid", "checkRecord", "modifiedDate",
                "customerName", "operation", "customerNo",
                "imtdSalesBasisDetailsSid", "cfpName", "cdrModelSid",
                "sessionId", "cfpContractDetailsSid", "inboundStatus"
            });
    private static ImtdSalesBasisDetails _nullImtdSalesBasisDetails = new ImtdSalesBasisDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdSalesBasisDetails> toCacheModel() {
                return _nullImtdSalesBasisDetailsCacheModel;
            }
        };

    private static CacheModel<ImtdSalesBasisDetails> _nullImtdSalesBasisDetailsCacheModel =
        new CacheModel<ImtdSalesBasisDetails>() {
            @Override
            public ImtdSalesBasisDetails toEntityModel() {
                return _nullImtdSalesBasisDetails;
            }
        };

    public ImtdSalesBasisDetailsPersistenceImpl() {
        setModelClass(ImtdSalesBasisDetails.class);
    }

    /**
     * Caches the imtd sales basis details in the entity cache if it is enabled.
     *
     * @param imtdSalesBasisDetails the imtd sales basis details
     */
    @Override
    public void cacheResult(ImtdSalesBasisDetails imtdSalesBasisDetails) {
        EntityCacheUtil.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsImpl.class,
            imtdSalesBasisDetails.getPrimaryKey(), imtdSalesBasisDetails);

        imtdSalesBasisDetails.resetOriginalValues();
    }

    /**
     * Caches the imtd sales basis detailses in the entity cache if it is enabled.
     *
     * @param imtdSalesBasisDetailses the imtd sales basis detailses
     */
    @Override
    public void cacheResult(List<ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
        for (ImtdSalesBasisDetails imtdSalesBasisDetails : imtdSalesBasisDetailses) {
            if (EntityCacheUtil.getResult(
                        ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdSalesBasisDetailsImpl.class,
                        imtdSalesBasisDetails.getPrimaryKey()) == null) {
                cacheResult(imtdSalesBasisDetails);
            } else {
                imtdSalesBasisDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd sales basis detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdSalesBasisDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdSalesBasisDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd sales basis details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdSalesBasisDetails imtdSalesBasisDetails) {
        EntityCacheUtil.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsImpl.class,
            imtdSalesBasisDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdSalesBasisDetails imtdSalesBasisDetails : imtdSalesBasisDetailses) {
            EntityCacheUtil.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdSalesBasisDetailsImpl.class,
                imtdSalesBasisDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
     *
     * @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
     * @return the new imtd sales basis details
     */
    @Override
    public ImtdSalesBasisDetails create(int imtdSalesBasisDetailsSid) {
        ImtdSalesBasisDetails imtdSalesBasisDetails = new ImtdSalesBasisDetailsImpl();

        imtdSalesBasisDetails.setNew(true);
        imtdSalesBasisDetails.setPrimaryKey(imtdSalesBasisDetailsSid);

        return imtdSalesBasisDetails;
    }

    /**
     * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
     * @return the imtd sales basis details that was removed
     * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails remove(int imtdSalesBasisDetailsSid)
        throws NoSuchImtdSalesBasisDetailsException, SystemException {
        return remove((Serializable) imtdSalesBasisDetailsSid);
    }

    /**
     * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd sales basis details
     * @return the imtd sales basis details that was removed
     * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails remove(Serializable primaryKey)
        throws NoSuchImtdSalesBasisDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdSalesBasisDetails imtdSalesBasisDetails = (ImtdSalesBasisDetails) session.get(ImtdSalesBasisDetailsImpl.class,
                    primaryKey);

            if (imtdSalesBasisDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdSalesBasisDetails);
        } catch (NoSuchImtdSalesBasisDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdSalesBasisDetails removeImpl(
        ImtdSalesBasisDetails imtdSalesBasisDetails) throws SystemException {
        imtdSalesBasisDetails = toUnwrappedModel(imtdSalesBasisDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdSalesBasisDetails)) {
                imtdSalesBasisDetails = (ImtdSalesBasisDetails) session.get(ImtdSalesBasisDetailsImpl.class,
                        imtdSalesBasisDetails.getPrimaryKeyObj());
            }

            if (imtdSalesBasisDetails != null) {
                session.delete(imtdSalesBasisDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdSalesBasisDetails != null) {
            clearCache(imtdSalesBasisDetails);
        }

        return imtdSalesBasisDetails;
    }

    @Override
    public ImtdSalesBasisDetails updateImpl(
        com.stpl.app.model.ImtdSalesBasisDetails imtdSalesBasisDetails)
        throws SystemException {
        imtdSalesBasisDetails = toUnwrappedModel(imtdSalesBasisDetails);

        boolean isNew = imtdSalesBasisDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdSalesBasisDetails.isNew()) {
                session.save(imtdSalesBasisDetails);

                imtdSalesBasisDetails.setNew(false);
            } else {
                session.merge(imtdSalesBasisDetails);
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

        EntityCacheUtil.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ImtdSalesBasisDetailsImpl.class,
            imtdSalesBasisDetails.getPrimaryKey(), imtdSalesBasisDetails);

        return imtdSalesBasisDetails;
    }

    protected ImtdSalesBasisDetails toUnwrappedModel(
        ImtdSalesBasisDetails imtdSalesBasisDetails) {
        if (imtdSalesBasisDetails instanceof ImtdSalesBasisDetailsImpl) {
            return imtdSalesBasisDetails;
        }

        ImtdSalesBasisDetailsImpl imtdSalesBasisDetailsImpl = new ImtdSalesBasisDetailsImpl();

        imtdSalesBasisDetailsImpl.setNew(imtdSalesBasisDetails.isNew());
        imtdSalesBasisDetailsImpl.setPrimaryKey(imtdSalesBasisDetails.getPrimaryKey());

        imtdSalesBasisDetailsImpl.setCreatedBy(imtdSalesBasisDetails.getCreatedBy());
        imtdSalesBasisDetailsImpl.setNetSalesFormulaMasterSid(imtdSalesBasisDetails.getNetSalesFormulaMasterSid());
        imtdSalesBasisDetailsImpl.setUsersSid(imtdSalesBasisDetails.getUsersSid());
        imtdSalesBasisDetailsImpl.setModifiedBy(imtdSalesBasisDetails.getModifiedBy());
        imtdSalesBasisDetailsImpl.setCreatedDate(imtdSalesBasisDetails.getCreatedDate());
        imtdSalesBasisDetailsImpl.setContractMasterSid(imtdSalesBasisDetails.getContractMasterSid());
        imtdSalesBasisDetailsImpl.setCfpNo(imtdSalesBasisDetails.getCfpNo());
        imtdSalesBasisDetailsImpl.setImtdCreatedDate(imtdSalesBasisDetails.getImtdCreatedDate());
        imtdSalesBasisDetailsImpl.setContractNo(imtdSalesBasisDetails.getContractNo());
        imtdSalesBasisDetailsImpl.setContractName(imtdSalesBasisDetails.getContractName());
        imtdSalesBasisDetailsImpl.setSalesBasisDetailsSid(imtdSalesBasisDetails.getSalesBasisDetailsSid());
        imtdSalesBasisDetailsImpl.setCheckRecord(imtdSalesBasisDetails.isCheckRecord());
        imtdSalesBasisDetailsImpl.setModifiedDate(imtdSalesBasisDetails.getModifiedDate());
        imtdSalesBasisDetailsImpl.setCustomerName(imtdSalesBasisDetails.getCustomerName());
        imtdSalesBasisDetailsImpl.setOperation(imtdSalesBasisDetails.getOperation());
        imtdSalesBasisDetailsImpl.setCustomerNo(imtdSalesBasisDetails.getCustomerNo());
        imtdSalesBasisDetailsImpl.setImtdSalesBasisDetailsSid(imtdSalesBasisDetails.getImtdSalesBasisDetailsSid());
        imtdSalesBasisDetailsImpl.setCfpName(imtdSalesBasisDetails.getCfpName());
        imtdSalesBasisDetailsImpl.setCdrModelSid(imtdSalesBasisDetails.getCdrModelSid());
        imtdSalesBasisDetailsImpl.setSessionId(imtdSalesBasisDetails.getSessionId());
        imtdSalesBasisDetailsImpl.setCfpContractDetailsSid(imtdSalesBasisDetails.getCfpContractDetailsSid());
        imtdSalesBasisDetailsImpl.setInboundStatus(imtdSalesBasisDetails.getInboundStatus());

        return imtdSalesBasisDetailsImpl;
    }

    /**
     * Returns the imtd sales basis details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd sales basis details
     * @return the imtd sales basis details
     * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdSalesBasisDetailsException, SystemException {
        ImtdSalesBasisDetails imtdSalesBasisDetails = fetchByPrimaryKey(primaryKey);

        if (imtdSalesBasisDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdSalesBasisDetails;
    }

    /**
     * Returns the imtd sales basis details with the primary key or throws a {@link com.stpl.app.NoSuchImtdSalesBasisDetailsException} if it could not be found.
     *
     * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
     * @return the imtd sales basis details
     * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails findByPrimaryKey(int imtdSalesBasisDetailsSid)
        throws NoSuchImtdSalesBasisDetailsException, SystemException {
        return findByPrimaryKey((Serializable) imtdSalesBasisDetailsSid);
    }

    /**
     * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd sales basis details
     * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdSalesBasisDetails imtdSalesBasisDetails = (ImtdSalesBasisDetails) EntityCacheUtil.getResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ImtdSalesBasisDetailsImpl.class, primaryKey);

        if (imtdSalesBasisDetails == _nullImtdSalesBasisDetails) {
            return null;
        }

        if (imtdSalesBasisDetails == null) {
            Session session = null;

            try {
                session = openSession();

                imtdSalesBasisDetails = (ImtdSalesBasisDetails) session.get(ImtdSalesBasisDetailsImpl.class,
                        primaryKey);

                if (imtdSalesBasisDetails != null) {
                    cacheResult(imtdSalesBasisDetails);
                } else {
                    EntityCacheUtil.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdSalesBasisDetailsImpl.class, primaryKey,
                        _nullImtdSalesBasisDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdSalesBasisDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdSalesBasisDetails;
    }

    /**
     * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
     * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdSalesBasisDetails fetchByPrimaryKey(int imtdSalesBasisDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdSalesBasisDetailsSid);
    }

    /**
     * Returns all the imtd sales basis detailses.
     *
     * @return the imtd sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdSalesBasisDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd sales basis detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd sales basis detailses
     * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
     * @return the range of imtd sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdSalesBasisDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd sales basis detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd sales basis detailses
     * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd sales basis detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdSalesBasisDetails> findAll(int start, int end,
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

        List<ImtdSalesBasisDetails> list = (List<ImtdSalesBasisDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDSALESBASISDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDSALESBASISDETAILS;

                if (pagination) {
                    sql = sql.concat(ImtdSalesBasisDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdSalesBasisDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdSalesBasisDetails>(list);
                } else {
                    list = (List<ImtdSalesBasisDetails>) QueryUtil.list(q,
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
     * Removes all the imtd sales basis detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdSalesBasisDetails imtdSalesBasisDetails : findAll()) {
            remove(imtdSalesBasisDetails);
        }
    }

    /**
     * Returns the number of imtd sales basis detailses.
     *
     * @return the number of imtd sales basis detailses
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

                Query q = session.createQuery(_SQL_COUNT_IMTDSALESBASISDETAILS);

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
     * Initializes the imtd sales basis details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdSalesBasisDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdSalesBasisDetails>> listenersList = new ArrayList<ModelListener<ImtdSalesBasisDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdSalesBasisDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdSalesBasisDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
