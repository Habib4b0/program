package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchAccrualDetailsException;
import com.stpl.app.model.AccrualDetails;
import com.stpl.app.model.impl.AccrualDetailsImpl;
import com.stpl.app.model.impl.AccrualDetailsModelImpl;
import com.stpl.app.service.persistence.AccrualDetailsPersistence;

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
 * The persistence implementation for the accrual details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualDetailsPersistence
 * @see AccrualDetailsUtil
 * @generated
 */
public class AccrualDetailsPersistenceImpl extends BasePersistenceImpl<AccrualDetails>
    implements AccrualDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AccrualDetailsUtil} to access the accrual details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AccrualDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsModelImpl.FINDER_CACHE_ENABLED,
            AccrualDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsModelImpl.FINDER_CACHE_ENABLED,
            AccrualDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACCRUALDETAILS = "SELECT accrualDetails FROM AccrualDetails accrualDetails";
    private static final String _SQL_COUNT_ACCRUALDETAILS = "SELECT COUNT(accrualDetails) FROM AccrualDetails accrualDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "accrualDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccrualDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AccrualDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "accountId", "recordCreatedDate", "postingIndicator",
                "brandName", "accrualPeriodEndDate", "modifiedDate",
                "salesMasterId", "source", "contractMasterSid",
                "accrualDetailsSid", "documentType", "inboundStatus",
                "modifiedBy", "deductionType", "companyMasterSid",
                "contractName", "accountNo", "accountName", "versionNo",
                "provisionId", "companyIdentifierCodeQualifier", "amount",
                "subLedger", "recordLockStatus", "itemIdentifierCodeQualifier",
                "companyNo", "postingStatus", "itemName", "rsModelSid",
                "itemMasterSid", "itemId", "brandMasterSid",
                "glCompanyMasterSid", "createdBy", "createdDate",
                "accrualPeriodStartDate", "subLedgerType", "programNo",
                "categoryId", "itemNo", "deductionSubType",
                "acctIdentifierCodeQualifier", "contractId", "accrualId",
                "companyId", "accrualType", "brandId", "postingDate", "glDate",
                "companyCostCenter", "glAccount", "batchId", "programType",
                "contractNo"
            });
    private static AccrualDetails _nullAccrualDetails = new AccrualDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AccrualDetails> toCacheModel() {
                return _nullAccrualDetailsCacheModel;
            }
        };

    private static CacheModel<AccrualDetails> _nullAccrualDetailsCacheModel = new CacheModel<AccrualDetails>() {
            @Override
            public AccrualDetails toEntityModel() {
                return _nullAccrualDetails;
            }
        };

    public AccrualDetailsPersistenceImpl() {
        setModelClass(AccrualDetails.class);
    }

    /**
     * Caches the accrual details in the entity cache if it is enabled.
     *
     * @param accrualDetails the accrual details
     */
    @Override
    public void cacheResult(AccrualDetails accrualDetails) {
        EntityCacheUtil.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsImpl.class, accrualDetails.getPrimaryKey(),
            accrualDetails);

        accrualDetails.resetOriginalValues();
    }

    /**
     * Caches the accrual detailses in the entity cache if it is enabled.
     *
     * @param accrualDetailses the accrual detailses
     */
    @Override
    public void cacheResult(List<AccrualDetails> accrualDetailses) {
        for (AccrualDetails accrualDetails : accrualDetailses) {
            if (EntityCacheUtil.getResult(
                        AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AccrualDetailsImpl.class, accrualDetails.getPrimaryKey()) == null) {
                cacheResult(accrualDetails);
            } else {
                accrualDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all accrual detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AccrualDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AccrualDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the accrual details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AccrualDetails accrualDetails) {
        EntityCacheUtil.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsImpl.class, accrualDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AccrualDetails> accrualDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AccrualDetails accrualDetails : accrualDetailses) {
            EntityCacheUtil.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AccrualDetailsImpl.class, accrualDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new accrual details with the primary key. Does not add the accrual details to the database.
     *
     * @param accrualDetailsSid the primary key for the new accrual details
     * @return the new accrual details
     */
    @Override
    public AccrualDetails create(int accrualDetailsSid) {
        AccrualDetails accrualDetails = new AccrualDetailsImpl();

        accrualDetails.setNew(true);
        accrualDetails.setPrimaryKey(accrualDetailsSid);

        return accrualDetails;
    }

    /**
     * Removes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accrualDetailsSid the primary key of the accrual details
     * @return the accrual details that was removed
     * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails remove(int accrualDetailsSid)
        throws NoSuchAccrualDetailsException, SystemException {
        return remove((Serializable) accrualDetailsSid);
    }

    /**
     * Removes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the accrual details
     * @return the accrual details that was removed
     * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails remove(Serializable primaryKey)
        throws NoSuchAccrualDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AccrualDetails accrualDetails = (AccrualDetails) session.get(AccrualDetailsImpl.class,
                    primaryKey);

            if (accrualDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAccrualDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(accrualDetails);
        } catch (NoSuchAccrualDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AccrualDetails removeImpl(AccrualDetails accrualDetails)
        throws SystemException {
        accrualDetails = toUnwrappedModel(accrualDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(accrualDetails)) {
                accrualDetails = (AccrualDetails) session.get(AccrualDetailsImpl.class,
                        accrualDetails.getPrimaryKeyObj());
            }

            if (accrualDetails != null) {
                session.delete(accrualDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (accrualDetails != null) {
            clearCache(accrualDetails);
        }

        return accrualDetails;
    }

    @Override
    public AccrualDetails updateImpl(
        com.stpl.app.model.AccrualDetails accrualDetails)
        throws SystemException {
        accrualDetails = toUnwrappedModel(accrualDetails);

        boolean isNew = accrualDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (accrualDetails.isNew()) {
                session.save(accrualDetails);

                accrualDetails.setNew(false);
            } else {
                session.merge(accrualDetails);
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

        EntityCacheUtil.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccrualDetailsImpl.class, accrualDetails.getPrimaryKey(),
            accrualDetails);

        return accrualDetails;
    }

    protected AccrualDetails toUnwrappedModel(AccrualDetails accrualDetails) {
        if (accrualDetails instanceof AccrualDetailsImpl) {
            return accrualDetails;
        }

        AccrualDetailsImpl accrualDetailsImpl = new AccrualDetailsImpl();

        accrualDetailsImpl.setNew(accrualDetails.isNew());
        accrualDetailsImpl.setPrimaryKey(accrualDetails.getPrimaryKey());

        accrualDetailsImpl.setAccountId(accrualDetails.getAccountId());
        accrualDetailsImpl.setRecordCreatedDate(accrualDetails.getRecordCreatedDate());
        accrualDetailsImpl.setPostingIndicator(accrualDetails.getPostingIndicator());
        accrualDetailsImpl.setBrandName(accrualDetails.getBrandName());
        accrualDetailsImpl.setAccrualPeriodEndDate(accrualDetails.getAccrualPeriodEndDate());
        accrualDetailsImpl.setModifiedDate(accrualDetails.getModifiedDate());
        accrualDetailsImpl.setSalesMasterId(accrualDetails.getSalesMasterId());
        accrualDetailsImpl.setSource(accrualDetails.getSource());
        accrualDetailsImpl.setContractMasterSid(accrualDetails.getContractMasterSid());
        accrualDetailsImpl.setAccrualDetailsSid(accrualDetails.getAccrualDetailsSid());
        accrualDetailsImpl.setDocumentType(accrualDetails.getDocumentType());
        accrualDetailsImpl.setInboundStatus(accrualDetails.getInboundStatus());
        accrualDetailsImpl.setModifiedBy(accrualDetails.getModifiedBy());
        accrualDetailsImpl.setDeductionType(accrualDetails.getDeductionType());
        accrualDetailsImpl.setCompanyMasterSid(accrualDetails.getCompanyMasterSid());
        accrualDetailsImpl.setContractName(accrualDetails.getContractName());
        accrualDetailsImpl.setAccountNo(accrualDetails.getAccountNo());
        accrualDetailsImpl.setAccountName(accrualDetails.getAccountName());
        accrualDetailsImpl.setVersionNo(accrualDetails.getVersionNo());
        accrualDetailsImpl.setProvisionId(accrualDetails.getProvisionId());
        accrualDetailsImpl.setCompanyIdentifierCodeQualifier(accrualDetails.getCompanyIdentifierCodeQualifier());
        accrualDetailsImpl.setAmount(accrualDetails.getAmount());
        accrualDetailsImpl.setSubLedger(accrualDetails.getSubLedger());
        accrualDetailsImpl.setRecordLockStatus(accrualDetails.isRecordLockStatus());
        accrualDetailsImpl.setItemIdentifierCodeQualifier(accrualDetails.getItemIdentifierCodeQualifier());
        accrualDetailsImpl.setCompanyNo(accrualDetails.getCompanyNo());
        accrualDetailsImpl.setPostingStatus(accrualDetails.getPostingStatus());
        accrualDetailsImpl.setItemName(accrualDetails.getItemName());
        accrualDetailsImpl.setRsModelSid(accrualDetails.getRsModelSid());
        accrualDetailsImpl.setItemMasterSid(accrualDetails.getItemMasterSid());
        accrualDetailsImpl.setItemId(accrualDetails.getItemId());
        accrualDetailsImpl.setBrandMasterSid(accrualDetails.getBrandMasterSid());
        accrualDetailsImpl.setGlCompanyMasterSid(accrualDetails.getGlCompanyMasterSid());
        accrualDetailsImpl.setCreatedBy(accrualDetails.getCreatedBy());
        accrualDetailsImpl.setCreatedDate(accrualDetails.getCreatedDate());
        accrualDetailsImpl.setAccrualPeriodStartDate(accrualDetails.getAccrualPeriodStartDate());
        accrualDetailsImpl.setSubLedgerType(accrualDetails.getSubLedgerType());
        accrualDetailsImpl.setProgramNo(accrualDetails.getProgramNo());
        accrualDetailsImpl.setCategoryId(accrualDetails.getCategoryId());
        accrualDetailsImpl.setItemNo(accrualDetails.getItemNo());
        accrualDetailsImpl.setDeductionSubType(accrualDetails.getDeductionSubType());
        accrualDetailsImpl.setAcctIdentifierCodeQualifier(accrualDetails.getAcctIdentifierCodeQualifier());
        accrualDetailsImpl.setContractId(accrualDetails.getContractId());
        accrualDetailsImpl.setAccrualId(accrualDetails.getAccrualId());
        accrualDetailsImpl.setCompanyId(accrualDetails.getCompanyId());
        accrualDetailsImpl.setAccrualType(accrualDetails.getAccrualType());
        accrualDetailsImpl.setBrandId(accrualDetails.getBrandId());
        accrualDetailsImpl.setPostingDate(accrualDetails.getPostingDate());
        accrualDetailsImpl.setGlDate(accrualDetails.getGlDate());
        accrualDetailsImpl.setCompanyCostCenter(accrualDetails.getCompanyCostCenter());
        accrualDetailsImpl.setGlAccount(accrualDetails.getGlAccount());
        accrualDetailsImpl.setBatchId(accrualDetails.getBatchId());
        accrualDetailsImpl.setProgramType(accrualDetails.getProgramType());
        accrualDetailsImpl.setContractNo(accrualDetails.getContractNo());

        return accrualDetailsImpl;
    }

    /**
     * Returns the accrual details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the accrual details
     * @return the accrual details
     * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAccrualDetailsException, SystemException {
        AccrualDetails accrualDetails = fetchByPrimaryKey(primaryKey);

        if (accrualDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAccrualDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return accrualDetails;
    }

    /**
     * Returns the accrual details with the primary key or throws a {@link com.stpl.app.NoSuchAccrualDetailsException} if it could not be found.
     *
     * @param accrualDetailsSid the primary key of the accrual details
     * @return the accrual details
     * @throws com.stpl.app.NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails findByPrimaryKey(int accrualDetailsSid)
        throws NoSuchAccrualDetailsException, SystemException {
        return findByPrimaryKey((Serializable) accrualDetailsSid);
    }

    /**
     * Returns the accrual details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the accrual details
     * @return the accrual details, or <code>null</code> if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AccrualDetails accrualDetails = (AccrualDetails) EntityCacheUtil.getResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AccrualDetailsImpl.class, primaryKey);

        if (accrualDetails == _nullAccrualDetails) {
            return null;
        }

        if (accrualDetails == null) {
            Session session = null;

            try {
                session = openSession();

                accrualDetails = (AccrualDetails) session.get(AccrualDetailsImpl.class,
                        primaryKey);

                if (accrualDetails != null) {
                    cacheResult(accrualDetails);
                } else {
                    EntityCacheUtil.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AccrualDetailsImpl.class, primaryKey,
                        _nullAccrualDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    AccrualDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return accrualDetails;
    }

    /**
     * Returns the accrual details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accrualDetailsSid the primary key of the accrual details
     * @return the accrual details, or <code>null</code> if a accrual details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualDetails fetchByPrimaryKey(int accrualDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accrualDetailsSid);
    }

    /**
     * Returns all the accrual detailses.
     *
     * @return the accrual detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the accrual detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of accrual detailses
     * @param end the upper bound of the range of accrual detailses (not inclusive)
     * @return the range of accrual detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the accrual detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of accrual detailses
     * @param end the upper bound of the range of accrual detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of accrual detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualDetails> findAll(int start, int end,
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

        List<AccrualDetails> list = (List<AccrualDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACCRUALDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACCRUALDETAILS;

                if (pagination) {
                    sql = sql.concat(AccrualDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AccrualDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AccrualDetails>(list);
                } else {
                    list = (List<AccrualDetails>) QueryUtil.list(q,
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
     * Removes all the accrual detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AccrualDetails accrualDetails : findAll()) {
            remove(accrualDetails);
        }
    }

    /**
     * Returns the number of accrual detailses.
     *
     * @return the number of accrual detailses
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

                Query q = session.createQuery(_SQL_COUNT_ACCRUALDETAILS);

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
     * Initializes the accrual details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.AccrualDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AccrualDetails>> listenersList = new ArrayList<ModelListener<AccrualDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AccrualDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AccrualDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
