package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldActualMasterException;
import com.stpl.app.model.IvldActualMaster;
import com.stpl.app.model.impl.IvldActualMasterImpl;
import com.stpl.app.model.impl.IvldActualMasterModelImpl;
import com.stpl.app.service.persistence.IvldActualMasterPersistence;

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
 * The persistence implementation for the ivld actual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldActualMasterPersistence
 * @see IvldActualMasterUtil
 * @generated
 */
public class IvldActualMasterPersistenceImpl extends BasePersistenceImpl<IvldActualMaster>
    implements IvldActualMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldActualMasterUtil} to access the ivld actual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldActualMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldActualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldActualMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDACTUALMASTER = "SELECT ivldActualMaster FROM IvldActualMaster ivldActualMaster";
    private static final String _SQL_COUNT_IVLDACTUALMASTER = "SELECT COUNT(ivldActualMaster) FROM IvldActualMaster ivldActualMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldActualMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldActualMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldActualMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualIntfid", "accountId", "programStateCode", "settlementNo",
                "accrualActualEndDate", "actualId", "modifiedDate", "divisionId",
                "organizationKey", "quantityInclusion", "cashPaidDate", "source",
                "addChgDelIndicator", "analysisCode", "accrualActualStartDate",
                "modifiedBy", "salesAmount", "quantity", "sentOut", "accountNo",
                "reasonForFailure", "accountName", "provisionId", "amount",
                "marketId", "isActive", "settlementMethodNo",
                "itemIdentifierCodeQualifier", "priceAdjustmentName",
                "errorField", "recordSequence", "mandatedDiscountAmount",
                "parentcomDivmktBrandProdkey", "price", "dispensedDate",
                "itemId", "accrualProcessed", "uploadDate", "createdBy",
                "createdDate", "invoiceLineNo", "errorCode", "intfInsertedDate",
                "itemNo", "reprocessedFlag", "acctIdentifierCodeQualifier",
                "contractId", "brandId", "comDivMktBrandProdKey",
                "claimIndicator", "ivldActualMasterSid", "batchId",
                "provisionClaimIndicator"
            });
    private static IvldActualMaster _nullIvldActualMaster = new IvldActualMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldActualMaster> toCacheModel() {
                return _nullIvldActualMasterCacheModel;
            }
        };

    private static CacheModel<IvldActualMaster> _nullIvldActualMasterCacheModel = new CacheModel<IvldActualMaster>() {
            @Override
            public IvldActualMaster toEntityModel() {
                return _nullIvldActualMaster;
            }
        };

    public IvldActualMasterPersistenceImpl() {
        setModelClass(IvldActualMaster.class);
    }

    /**
     * Caches the ivld actual master in the entity cache if it is enabled.
     *
     * @param ivldActualMaster the ivld actual master
     */
    @Override
    public void cacheResult(IvldActualMaster ivldActualMaster) {
        EntityCacheUtil.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey(),
            ivldActualMaster);

        ivldActualMaster.resetOriginalValues();
    }

    /**
     * Caches the ivld actual masters in the entity cache if it is enabled.
     *
     * @param ivldActualMasters the ivld actual masters
     */
    @Override
    public void cacheResult(List<IvldActualMaster> ivldActualMasters) {
        for (IvldActualMaster ivldActualMaster : ivldActualMasters) {
            if (EntityCacheUtil.getResult(
                        IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldActualMasterImpl.class,
                        ivldActualMaster.getPrimaryKey()) == null) {
                cacheResult(ivldActualMaster);
            } else {
                ivldActualMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld actual masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldActualMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldActualMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld actual master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldActualMaster ivldActualMaster) {
        EntityCacheUtil.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldActualMaster> ivldActualMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldActualMaster ivldActualMaster : ivldActualMasters) {
            EntityCacheUtil.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
     *
     * @param ivldActualMasterSid the primary key for the new ivld actual master
     * @return the new ivld actual master
     */
    @Override
    public IvldActualMaster create(int ivldActualMasterSid) {
        IvldActualMaster ivldActualMaster = new IvldActualMasterImpl();

        ivldActualMaster.setNew(true);
        ivldActualMaster.setPrimaryKey(ivldActualMasterSid);

        return ivldActualMaster;
    }

    /**
     * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldActualMasterSid the primary key of the ivld actual master
     * @return the ivld actual master that was removed
     * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster remove(int ivldActualMasterSid)
        throws NoSuchIvldActualMasterException, SystemException {
        return remove((Serializable) ivldActualMasterSid);
    }

    /**
     * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld actual master
     * @return the ivld actual master that was removed
     * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster remove(Serializable primaryKey)
        throws NoSuchIvldActualMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldActualMaster ivldActualMaster = (IvldActualMaster) session.get(IvldActualMasterImpl.class,
                    primaryKey);

            if (ivldActualMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldActualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldActualMaster);
        } catch (NoSuchIvldActualMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldActualMaster removeImpl(IvldActualMaster ivldActualMaster)
        throws SystemException {
        ivldActualMaster = toUnwrappedModel(ivldActualMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldActualMaster)) {
                ivldActualMaster = (IvldActualMaster) session.get(IvldActualMasterImpl.class,
                        ivldActualMaster.getPrimaryKeyObj());
            }

            if (ivldActualMaster != null) {
                session.delete(ivldActualMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldActualMaster != null) {
            clearCache(ivldActualMaster);
        }

        return ivldActualMaster;
    }

    @Override
    public IvldActualMaster updateImpl(
        com.stpl.app.model.IvldActualMaster ivldActualMaster)
        throws SystemException {
        ivldActualMaster = toUnwrappedModel(ivldActualMaster);

        boolean isNew = ivldActualMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldActualMaster.isNew()) {
                session.save(ivldActualMaster);

                ivldActualMaster.setNew(false);
            } else {
                session.merge(ivldActualMaster);
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

        EntityCacheUtil.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey(),
            ivldActualMaster);

        return ivldActualMaster;
    }

    protected IvldActualMaster toUnwrappedModel(
        IvldActualMaster ivldActualMaster) {
        if (ivldActualMaster instanceof IvldActualMasterImpl) {
            return ivldActualMaster;
        }

        IvldActualMasterImpl ivldActualMasterImpl = new IvldActualMasterImpl();

        ivldActualMasterImpl.setNew(ivldActualMaster.isNew());
        ivldActualMasterImpl.setPrimaryKey(ivldActualMaster.getPrimaryKey());

        ivldActualMasterImpl.setActualIntfid(ivldActualMaster.getActualIntfid());
        ivldActualMasterImpl.setAccountId(ivldActualMaster.getAccountId());
        ivldActualMasterImpl.setProgramStateCode(ivldActualMaster.getProgramStateCode());
        ivldActualMasterImpl.setSettlementNo(ivldActualMaster.getSettlementNo());
        ivldActualMasterImpl.setAccrualActualEndDate(ivldActualMaster.getAccrualActualEndDate());
        ivldActualMasterImpl.setActualId(ivldActualMaster.getActualId());
        ivldActualMasterImpl.setModifiedDate(ivldActualMaster.getModifiedDate());
        ivldActualMasterImpl.setDivisionId(ivldActualMaster.getDivisionId());
        ivldActualMasterImpl.setOrganizationKey(ivldActualMaster.getOrganizationKey());
        ivldActualMasterImpl.setQuantityInclusion(ivldActualMaster.getQuantityInclusion());
        ivldActualMasterImpl.setCashPaidDate(ivldActualMaster.getCashPaidDate());
        ivldActualMasterImpl.setSource(ivldActualMaster.getSource());
        ivldActualMasterImpl.setAddChgDelIndicator(ivldActualMaster.getAddChgDelIndicator());
        ivldActualMasterImpl.setAnalysisCode(ivldActualMaster.getAnalysisCode());
        ivldActualMasterImpl.setAccrualActualStartDate(ivldActualMaster.getAccrualActualStartDate());
        ivldActualMasterImpl.setModifiedBy(ivldActualMaster.getModifiedBy());
        ivldActualMasterImpl.setSalesAmount(ivldActualMaster.getSalesAmount());
        ivldActualMasterImpl.setQuantity(ivldActualMaster.getQuantity());
        ivldActualMasterImpl.setSentOut(ivldActualMaster.getSentOut());
        ivldActualMasterImpl.setAccountNo(ivldActualMaster.getAccountNo());
        ivldActualMasterImpl.setReasonForFailure(ivldActualMaster.getReasonForFailure());
        ivldActualMasterImpl.setAccountName(ivldActualMaster.getAccountName());
        ivldActualMasterImpl.setProvisionId(ivldActualMaster.getProvisionId());
        ivldActualMasterImpl.setAmount(ivldActualMaster.getAmount());
        ivldActualMasterImpl.setMarketId(ivldActualMaster.getMarketId());
        ivldActualMasterImpl.setIsActive(ivldActualMaster.getIsActive());
        ivldActualMasterImpl.setSettlementMethodNo(ivldActualMaster.getSettlementMethodNo());
        ivldActualMasterImpl.setItemIdentifierCodeQualifier(ivldActualMaster.getItemIdentifierCodeQualifier());
        ivldActualMasterImpl.setPriceAdjustmentName(ivldActualMaster.getPriceAdjustmentName());
        ivldActualMasterImpl.setErrorField(ivldActualMaster.getErrorField());
        ivldActualMasterImpl.setRecordSequence(ivldActualMaster.getRecordSequence());
        ivldActualMasterImpl.setMandatedDiscountAmount(ivldActualMaster.getMandatedDiscountAmount());
        ivldActualMasterImpl.setParentcomDivmktBrandProdkey(ivldActualMaster.getParentcomDivmktBrandProdkey());
        ivldActualMasterImpl.setPrice(ivldActualMaster.getPrice());
        ivldActualMasterImpl.setDispensedDate(ivldActualMaster.getDispensedDate());
        ivldActualMasterImpl.setItemId(ivldActualMaster.getItemId());
        ivldActualMasterImpl.setAccrualProcessed(ivldActualMaster.getAccrualProcessed());
        ivldActualMasterImpl.setUploadDate(ivldActualMaster.getUploadDate());
        ivldActualMasterImpl.setCreatedBy(ivldActualMaster.getCreatedBy());
        ivldActualMasterImpl.setCreatedDate(ivldActualMaster.getCreatedDate());
        ivldActualMasterImpl.setInvoiceLineNo(ivldActualMaster.getInvoiceLineNo());
        ivldActualMasterImpl.setErrorCode(ivldActualMaster.getErrorCode());
        ivldActualMasterImpl.setIntfInsertedDate(ivldActualMaster.getIntfInsertedDate());
        ivldActualMasterImpl.setItemNo(ivldActualMaster.getItemNo());
        ivldActualMasterImpl.setReprocessedFlag(ivldActualMaster.getReprocessedFlag());
        ivldActualMasterImpl.setAcctIdentifierCodeQualifier(ivldActualMaster.getAcctIdentifierCodeQualifier());
        ivldActualMasterImpl.setContractId(ivldActualMaster.getContractId());
        ivldActualMasterImpl.setBrandId(ivldActualMaster.getBrandId());
        ivldActualMasterImpl.setComDivMktBrandProdKey(ivldActualMaster.getComDivMktBrandProdKey());
        ivldActualMasterImpl.setClaimIndicator(ivldActualMaster.getClaimIndicator());
        ivldActualMasterImpl.setIvldActualMasterSid(ivldActualMaster.getIvldActualMasterSid());
        ivldActualMasterImpl.setBatchId(ivldActualMaster.getBatchId());
        ivldActualMasterImpl.setProvisionClaimIndicator(ivldActualMaster.getProvisionClaimIndicator());

        return ivldActualMasterImpl;
    }

    /**
     * Returns the ivld actual master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld actual master
     * @return the ivld actual master
     * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldActualMasterException, SystemException {
        IvldActualMaster ivldActualMaster = fetchByPrimaryKey(primaryKey);

        if (ivldActualMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldActualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldActualMaster;
    }

    /**
     * Returns the ivld actual master with the primary key or throws a {@link com.stpl.app.NoSuchIvldActualMasterException} if it could not be found.
     *
     * @param ivldActualMasterSid the primary key of the ivld actual master
     * @return the ivld actual master
     * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster findByPrimaryKey(int ivldActualMasterSid)
        throws NoSuchIvldActualMasterException, SystemException {
        return findByPrimaryKey((Serializable) ivldActualMasterSid);
    }

    /**
     * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld actual master
     * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldActualMaster ivldActualMaster = (IvldActualMaster) EntityCacheUtil.getResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldActualMasterImpl.class, primaryKey);

        if (ivldActualMaster == _nullIvldActualMaster) {
            return null;
        }

        if (ivldActualMaster == null) {
            Session session = null;

            try {
                session = openSession();

                ivldActualMaster = (IvldActualMaster) session.get(IvldActualMasterImpl.class,
                        primaryKey);

                if (ivldActualMaster != null) {
                    cacheResult(ivldActualMaster);
                } else {
                    EntityCacheUtil.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldActualMasterImpl.class, primaryKey,
                        _nullIvldActualMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldActualMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldActualMaster;
    }

    /**
     * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldActualMasterSid the primary key of the ivld actual master
     * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldActualMaster fetchByPrimaryKey(int ivldActualMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldActualMasterSid);
    }

    /**
     * Returns all the ivld actual masters.
     *
     * @return the ivld actual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldActualMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld actual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld actual masters
     * @param end the upper bound of the range of ivld actual masters (not inclusive)
     * @return the range of ivld actual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldActualMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld actual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld actual masters
     * @param end the upper bound of the range of ivld actual masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld actual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldActualMaster> findAll(int start, int end,
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

        List<IvldActualMaster> list = (List<IvldActualMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDACTUALMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDACTUALMASTER;

                if (pagination) {
                    sql = sql.concat(IvldActualMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldActualMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldActualMaster>(list);
                } else {
                    list = (List<IvldActualMaster>) QueryUtil.list(q,
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
     * Removes all the ivld actual masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldActualMaster ivldActualMaster : findAll()) {
            remove(ivldActualMaster);
        }
    }

    /**
     * Returns the number of ivld actual masters.
     *
     * @return the number of ivld actual masters
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

                Query q = session.createQuery(_SQL_COUNT_IVLDACTUALMASTER);

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
     * Initializes the ivld actual master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldActualMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldActualMaster>> listenersList = new ArrayList<ModelListener<IvldActualMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldActualMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldActualMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
