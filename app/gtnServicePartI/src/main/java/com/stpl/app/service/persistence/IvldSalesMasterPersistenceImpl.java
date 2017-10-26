package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldSalesMasterException;
import com.stpl.app.model.IvldSalesMaster;
import com.stpl.app.model.impl.IvldSalesMasterImpl;
import com.stpl.app.model.impl.IvldSalesMasterModelImpl;
import com.stpl.app.service.persistence.IvldSalesMasterPersistence;

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
 * The persistence implementation for the ivld sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldSalesMasterPersistence
 * @see IvldSalesMasterUtil
 * @generated
 */
public class IvldSalesMasterPersistenceImpl extends BasePersistenceImpl<IvldSalesMaster>
    implements IvldSalesMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldSalesMasterUtil} to access the ivld sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldSalesMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldSalesMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldSalesMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDSALESMASTER = "SELECT ivldSalesMaster FROM IvldSalesMaster ivldSalesMaster";
    private static final String _SQL_COUNT_IVLDSALESMASTER = "SELECT COUNT(ivldSalesMaster) FROM IvldSalesMaster ivldSalesMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldSalesMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldSalesMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldSalesMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "accountId", "salesIntfid", "modifiedDate", "organizationKey",
                "divisionId", "source", "addChgDelIndicator", "analysisCode",
                "ivldSalesMasterSid", "docType", "modifiedBy", "lotNo",
                "quantity", "invoiceLineNumber", "identifierCodeQualifier",
                "accountCodeQualifier", "parentItemId", "accountNo",
                "reasonForFailure", "accountName", "provisionId", "amount",
                "marketId", "isActive", "wholesaleOwnerId",
                "priceAdjustmentName", "salesId", "errorField", "recordSequence",
                "price", "customerSubtype", "parentItemNo", "itemId",
                "orderReceivedDate", "orderNumber", "accountType", "uploadDate",
                "createdBy", "createdDate", "errorCode", "itemUom",
                "intfInsertedDate", "invoiceNumber", "orderSubtype", "itemNo",
                "reprocessedFlag", "contractId", "customerCompanyCode",
                "orderType", "companyId", "brandId", "invoiceDate", "batchId",
                "contractNo", "checkRecord"
            });
    private static IvldSalesMaster _nullIvldSalesMaster = new IvldSalesMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldSalesMaster> toCacheModel() {
                return _nullIvldSalesMasterCacheModel;
            }
        };

    private static CacheModel<IvldSalesMaster> _nullIvldSalesMasterCacheModel = new CacheModel<IvldSalesMaster>() {
            @Override
            public IvldSalesMaster toEntityModel() {
                return _nullIvldSalesMaster;
            }
        };

    public IvldSalesMasterPersistenceImpl() {
        setModelClass(IvldSalesMaster.class);
    }

    /**
     * Caches the ivld sales master in the entity cache if it is enabled.
     *
     * @param ivldSalesMaster the ivld sales master
     */
    @Override
    public void cacheResult(IvldSalesMaster ivldSalesMaster) {
        EntityCacheUtil.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey(),
            ivldSalesMaster);

        ivldSalesMaster.resetOriginalValues();
    }

    /**
     * Caches the ivld sales masters in the entity cache if it is enabled.
     *
     * @param ivldSalesMasters the ivld sales masters
     */
    @Override
    public void cacheResult(List<IvldSalesMaster> ivldSalesMasters) {
        for (IvldSalesMaster ivldSalesMaster : ivldSalesMasters) {
            if (EntityCacheUtil.getResult(
                        IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldSalesMasterImpl.class,
                        ivldSalesMaster.getPrimaryKey()) == null) {
                cacheResult(ivldSalesMaster);
            } else {
                ivldSalesMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld sales masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldSalesMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldSalesMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld sales master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldSalesMaster ivldSalesMaster) {
        EntityCacheUtil.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldSalesMaster> ivldSalesMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldSalesMaster ivldSalesMaster : ivldSalesMasters) {
            EntityCacheUtil.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
     *
     * @param ivldSalesMasterSid the primary key for the new ivld sales master
     * @return the new ivld sales master
     */
    @Override
    public IvldSalesMaster create(int ivldSalesMasterSid) {
        IvldSalesMaster ivldSalesMaster = new IvldSalesMasterImpl();

        ivldSalesMaster.setNew(true);
        ivldSalesMaster.setPrimaryKey(ivldSalesMasterSid);

        return ivldSalesMaster;
    }

    /**
     * Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldSalesMasterSid the primary key of the ivld sales master
     * @return the ivld sales master that was removed
     * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster remove(int ivldSalesMasterSid)
        throws NoSuchIvldSalesMasterException, SystemException {
        return remove((Serializable) ivldSalesMasterSid);
    }

    /**
     * Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld sales master
     * @return the ivld sales master that was removed
     * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster remove(Serializable primaryKey)
        throws NoSuchIvldSalesMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldSalesMaster ivldSalesMaster = (IvldSalesMaster) session.get(IvldSalesMasterImpl.class,
                    primaryKey);

            if (ivldSalesMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldSalesMaster);
        } catch (NoSuchIvldSalesMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldSalesMaster removeImpl(IvldSalesMaster ivldSalesMaster)
        throws SystemException {
        ivldSalesMaster = toUnwrappedModel(ivldSalesMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldSalesMaster)) {
                ivldSalesMaster = (IvldSalesMaster) session.get(IvldSalesMasterImpl.class,
                        ivldSalesMaster.getPrimaryKeyObj());
            }

            if (ivldSalesMaster != null) {
                session.delete(ivldSalesMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldSalesMaster != null) {
            clearCache(ivldSalesMaster);
        }

        return ivldSalesMaster;
    }

    @Override
    public IvldSalesMaster updateImpl(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster)
        throws SystemException {
        ivldSalesMaster = toUnwrappedModel(ivldSalesMaster);

        boolean isNew = ivldSalesMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldSalesMaster.isNew()) {
                session.save(ivldSalesMaster);

                ivldSalesMaster.setNew(false);
            } else {
                session.merge(ivldSalesMaster);
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

        EntityCacheUtil.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey(),
            ivldSalesMaster);

        return ivldSalesMaster;
    }

    protected IvldSalesMaster toUnwrappedModel(IvldSalesMaster ivldSalesMaster) {
        if (ivldSalesMaster instanceof IvldSalesMasterImpl) {
            return ivldSalesMaster;
        }

        IvldSalesMasterImpl ivldSalesMasterImpl = new IvldSalesMasterImpl();

        ivldSalesMasterImpl.setNew(ivldSalesMaster.isNew());
        ivldSalesMasterImpl.setPrimaryKey(ivldSalesMaster.getPrimaryKey());

        ivldSalesMasterImpl.setAccountId(ivldSalesMaster.getAccountId());
        ivldSalesMasterImpl.setSalesIntfid(ivldSalesMaster.getSalesIntfid());
        ivldSalesMasterImpl.setModifiedDate(ivldSalesMaster.getModifiedDate());
        ivldSalesMasterImpl.setOrganizationKey(ivldSalesMaster.getOrganizationKey());
        ivldSalesMasterImpl.setDivisionId(ivldSalesMaster.getDivisionId());
        ivldSalesMasterImpl.setSource(ivldSalesMaster.getSource());
        ivldSalesMasterImpl.setAddChgDelIndicator(ivldSalesMaster.getAddChgDelIndicator());
        ivldSalesMasterImpl.setAnalysisCode(ivldSalesMaster.getAnalysisCode());
        ivldSalesMasterImpl.setIvldSalesMasterSid(ivldSalesMaster.getIvldSalesMasterSid());
        ivldSalesMasterImpl.setDocType(ivldSalesMaster.getDocType());
        ivldSalesMasterImpl.setModifiedBy(ivldSalesMaster.getModifiedBy());
        ivldSalesMasterImpl.setLotNo(ivldSalesMaster.getLotNo());
        ivldSalesMasterImpl.setQuantity(ivldSalesMaster.getQuantity());
        ivldSalesMasterImpl.setInvoiceLineNumber(ivldSalesMaster.getInvoiceLineNumber());
        ivldSalesMasterImpl.setIdentifierCodeQualifier(ivldSalesMaster.getIdentifierCodeQualifier());
        ivldSalesMasterImpl.setAccountCodeQualifier(ivldSalesMaster.getAccountCodeQualifier());
        ivldSalesMasterImpl.setParentItemId(ivldSalesMaster.getParentItemId());
        ivldSalesMasterImpl.setAccountNo(ivldSalesMaster.getAccountNo());
        ivldSalesMasterImpl.setReasonForFailure(ivldSalesMaster.getReasonForFailure());
        ivldSalesMasterImpl.setAccountName(ivldSalesMaster.getAccountName());
        ivldSalesMasterImpl.setProvisionId(ivldSalesMaster.getProvisionId());
        ivldSalesMasterImpl.setAmount(ivldSalesMaster.getAmount());
        ivldSalesMasterImpl.setMarketId(ivldSalesMaster.getMarketId());
        ivldSalesMasterImpl.setIsActive(ivldSalesMaster.getIsActive());
        ivldSalesMasterImpl.setWholesaleOwnerId(ivldSalesMaster.getWholesaleOwnerId());
        ivldSalesMasterImpl.setPriceAdjustmentName(ivldSalesMaster.getPriceAdjustmentName());
        ivldSalesMasterImpl.setSalesId(ivldSalesMaster.getSalesId());
        ivldSalesMasterImpl.setErrorField(ivldSalesMaster.getErrorField());
        ivldSalesMasterImpl.setRecordSequence(ivldSalesMaster.getRecordSequence());
        ivldSalesMasterImpl.setPrice(ivldSalesMaster.getPrice());
        ivldSalesMasterImpl.setCustomerSubtype(ivldSalesMaster.getCustomerSubtype());
        ivldSalesMasterImpl.setParentItemNo(ivldSalesMaster.getParentItemNo());
        ivldSalesMasterImpl.setItemId(ivldSalesMaster.getItemId());
        ivldSalesMasterImpl.setOrderReceivedDate(ivldSalesMaster.getOrderReceivedDate());
        ivldSalesMasterImpl.setOrderNumber(ivldSalesMaster.getOrderNumber());
        ivldSalesMasterImpl.setAccountType(ivldSalesMaster.getAccountType());
        ivldSalesMasterImpl.setUploadDate(ivldSalesMaster.getUploadDate());
        ivldSalesMasterImpl.setCreatedBy(ivldSalesMaster.getCreatedBy());
        ivldSalesMasterImpl.setCreatedDate(ivldSalesMaster.getCreatedDate());
        ivldSalesMasterImpl.setErrorCode(ivldSalesMaster.getErrorCode());
        ivldSalesMasterImpl.setItemUom(ivldSalesMaster.getItemUom());
        ivldSalesMasterImpl.setIntfInsertedDate(ivldSalesMaster.getIntfInsertedDate());
        ivldSalesMasterImpl.setInvoiceNumber(ivldSalesMaster.getInvoiceNumber());
        ivldSalesMasterImpl.setOrderSubtype(ivldSalesMaster.getOrderSubtype());
        ivldSalesMasterImpl.setItemNo(ivldSalesMaster.getItemNo());
        ivldSalesMasterImpl.setReprocessedFlag(ivldSalesMaster.getReprocessedFlag());
        ivldSalesMasterImpl.setContractId(ivldSalesMaster.getContractId());
        ivldSalesMasterImpl.setCustomerCompanyCode(ivldSalesMaster.getCustomerCompanyCode());
        ivldSalesMasterImpl.setOrderType(ivldSalesMaster.getOrderType());
        ivldSalesMasterImpl.setCompanyId(ivldSalesMaster.getCompanyId());
        ivldSalesMasterImpl.setBrandId(ivldSalesMaster.getBrandId());
        ivldSalesMasterImpl.setInvoiceDate(ivldSalesMaster.getInvoiceDate());
        ivldSalesMasterImpl.setBatchId(ivldSalesMaster.getBatchId());
        ivldSalesMasterImpl.setContractNo(ivldSalesMaster.getContractNo());
        ivldSalesMasterImpl.setCheckRecord(ivldSalesMaster.isCheckRecord());

        return ivldSalesMasterImpl;
    }

    /**
     * Returns the ivld sales master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld sales master
     * @return the ivld sales master
     * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldSalesMasterException, SystemException {
        IvldSalesMaster ivldSalesMaster = fetchByPrimaryKey(primaryKey);

        if (ivldSalesMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldSalesMaster;
    }

    /**
     * Returns the ivld sales master with the primary key or throws a {@link com.stpl.app.NoSuchIvldSalesMasterException} if it could not be found.
     *
     * @param ivldSalesMasterSid the primary key of the ivld sales master
     * @return the ivld sales master
     * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster findByPrimaryKey(int ivldSalesMasterSid)
        throws NoSuchIvldSalesMasterException, SystemException {
        return findByPrimaryKey((Serializable) ivldSalesMasterSid);
    }

    /**
     * Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld sales master
     * @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldSalesMaster ivldSalesMaster = (IvldSalesMaster) EntityCacheUtil.getResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldSalesMasterImpl.class, primaryKey);

        if (ivldSalesMaster == _nullIvldSalesMaster) {
            return null;
        }

        if (ivldSalesMaster == null) {
            Session session = null;

            try {
                session = openSession();

                ivldSalesMaster = (IvldSalesMaster) session.get(IvldSalesMasterImpl.class,
                        primaryKey);

                if (ivldSalesMaster != null) {
                    cacheResult(ivldSalesMaster);
                } else {
                    EntityCacheUtil.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldSalesMasterImpl.class, primaryKey,
                        _nullIvldSalesMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldSalesMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldSalesMaster;
    }

    /**
     * Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldSalesMasterSid the primary key of the ivld sales master
     * @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldSalesMaster fetchByPrimaryKey(int ivldSalesMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldSalesMasterSid);
    }

    /**
     * Returns all the ivld sales masters.
     *
     * @return the ivld sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldSalesMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld sales masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld sales masters
     * @param end the upper bound of the range of ivld sales masters (not inclusive)
     * @return the range of ivld sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldSalesMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld sales masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld sales masters
     * @param end the upper bound of the range of ivld sales masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld sales masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldSalesMaster> findAll(int start, int end,
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

        List<IvldSalesMaster> list = (List<IvldSalesMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDSALESMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDSALESMASTER;

                if (pagination) {
                    sql = sql.concat(IvldSalesMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldSalesMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldSalesMaster>(list);
                } else {
                    list = (List<IvldSalesMaster>) QueryUtil.list(q,
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
     * Removes all the ivld sales masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldSalesMaster ivldSalesMaster : findAll()) {
            remove(ivldSalesMaster);
        }
    }

    /**
     * Returns the number of ivld sales masters.
     *
     * @return the number of ivld sales masters
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

                Query q = session.createQuery(_SQL_COUNT_IVLDSALESMASTER);

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
     * Initializes the ivld sales master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldSalesMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldSalesMaster>> listenersList = new ArrayList<ModelListener<IvldSalesMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldSalesMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldSalesMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
