package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldAccrualInboundException;
import com.stpl.app.parttwo.model.IvldAccrualInbound;
import com.stpl.app.parttwo.model.impl.IvldAccrualInboundImpl;
import com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldAccrualInboundPersistence;

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
 * The persistence implementation for the ivld accrual inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAccrualInboundPersistence
 * @see IvldAccrualInboundUtil
 * @generated
 */
public class IvldAccrualInboundPersistenceImpl extends BasePersistenceImpl<IvldAccrualInbound>
    implements IvldAccrualInboundPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldAccrualInboundUtil} to access the ivld accrual inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldAccrualInboundImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED,
            IvldAccrualInboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED,
            IvldAccrualInboundImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDACCRUALINBOUND = "SELECT ivldAccrualInbound FROM IvldAccrualInbound ivldAccrualInbound";
    private static final String _SQL_COUNT_IVLDACCRUALINBOUND = "SELECT COUNT(ivldAccrualInbound) FROM IvldAccrualInbound ivldAccrualInbound";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldAccrualInbound.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldAccrualInbound exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldAccrualInboundPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "recordCreatedDate", "accountId", "postingIndicator", "itemId",
                "modifiedDate", "accrualPeriodEndDate", "itemIdentCodeQualifier",
                "glCompanyMasterSid", "salesMasterId", "createdDate",
                "createdBy", "source", "accrualPeriodStartDate",
                "addChgDelIndicator", "subLedgerType", "programNo",
                "documentType", "accrualIntfid", "glCompanyName", "errorCode",
                "intfInsertedDate", "modifiedBy", "categoryId", "itemNo",
                "reprocessedFlag", "compIdentCodeQualifier",
                "acctIdentCodeQualifier", "contractId", "accountNo", "accrualId",
                "reasonForFailure", "companyId", "accountName", "accrualType",
                "postingDate", "brandId", "provisionId", "amount", "glDate",
                "subLedger", "companyCostCenter", "glAccount", "companyNo",
                "batchId", "programType", "itemName", "errorField",
                "ivldAccrualInboundSid", "contractNo", "brandName",
                "contractName"
            });
    private static IvldAccrualInbound _nullIvldAccrualInbound = new IvldAccrualInboundImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldAccrualInbound> toCacheModel() {
                return _nullIvldAccrualInboundCacheModel;
            }
        };

    private static CacheModel<IvldAccrualInbound> _nullIvldAccrualInboundCacheModel =
        new CacheModel<IvldAccrualInbound>() {
            @Override
            public IvldAccrualInbound toEntityModel() {
                return _nullIvldAccrualInbound;
            }
        };

    public IvldAccrualInboundPersistenceImpl() {
        setModelClass(IvldAccrualInbound.class);
    }

    /**
     * Caches the ivld accrual inbound in the entity cache if it is enabled.
     *
     * @param ivldAccrualInbound the ivld accrual inbound
     */
    @Override
    public void cacheResult(IvldAccrualInbound ivldAccrualInbound) {
        EntityCacheUtil.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey(),
            ivldAccrualInbound);

        ivldAccrualInbound.resetOriginalValues();
    }

    /**
     * Caches the ivld accrual inbounds in the entity cache if it is enabled.
     *
     * @param ivldAccrualInbounds the ivld accrual inbounds
     */
    @Override
    public void cacheResult(List<IvldAccrualInbound> ivldAccrualInbounds) {
        for (IvldAccrualInbound ivldAccrualInbound : ivldAccrualInbounds) {
            if (EntityCacheUtil.getResult(
                        IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
                        IvldAccrualInboundImpl.class,
                        ivldAccrualInbound.getPrimaryKey()) == null) {
                cacheResult(ivldAccrualInbound);
            } else {
                ivldAccrualInbound.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld accrual inbounds.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldAccrualInboundImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldAccrualInboundImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld accrual inbound.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldAccrualInbound ivldAccrualInbound) {
        EntityCacheUtil.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldAccrualInbound> ivldAccrualInbounds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldAccrualInbound ivldAccrualInbound : ivldAccrualInbounds) {
            EntityCacheUtil.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
                IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
     *
     * @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
     * @return the new ivld accrual inbound
     */
    @Override
    public IvldAccrualInbound create(int ivldAccrualInboundSid) {
        IvldAccrualInbound ivldAccrualInbound = new IvldAccrualInboundImpl();

        ivldAccrualInbound.setNew(true);
        ivldAccrualInbound.setPrimaryKey(ivldAccrualInboundSid);

        return ivldAccrualInbound;
    }

    /**
     * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound remove(int ivldAccrualInboundSid)
        throws NoSuchIvldAccrualInboundException, SystemException {
        return remove((Serializable) ivldAccrualInboundSid);
    }

    /**
     * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound remove(Serializable primaryKey)
        throws NoSuchIvldAccrualInboundException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldAccrualInbound ivldAccrualInbound = (IvldAccrualInbound) session.get(IvldAccrualInboundImpl.class,
                    primaryKey);

            if (ivldAccrualInbound == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldAccrualInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldAccrualInbound);
        } catch (NoSuchIvldAccrualInboundException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldAccrualInbound removeImpl(
        IvldAccrualInbound ivldAccrualInbound) throws SystemException {
        ivldAccrualInbound = toUnwrappedModel(ivldAccrualInbound);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldAccrualInbound)) {
                ivldAccrualInbound = (IvldAccrualInbound) session.get(IvldAccrualInboundImpl.class,
                        ivldAccrualInbound.getPrimaryKeyObj());
            }

            if (ivldAccrualInbound != null) {
                session.delete(ivldAccrualInbound);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldAccrualInbound != null) {
            clearCache(ivldAccrualInbound);
        }

        return ivldAccrualInbound;
    }

    @Override
    public IvldAccrualInbound updateImpl(
        com.stpl.app.parttwo.model.IvldAccrualInbound ivldAccrualInbound)
        throws SystemException {
        ivldAccrualInbound = toUnwrappedModel(ivldAccrualInbound);

        boolean isNew = ivldAccrualInbound.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldAccrualInbound.isNew()) {
                session.save(ivldAccrualInbound);

                ivldAccrualInbound.setNew(false);
            } else {
                session.merge(ivldAccrualInbound);
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

        EntityCacheUtil.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
            IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey(),
            ivldAccrualInbound);

        return ivldAccrualInbound;
    }

    protected IvldAccrualInbound toUnwrappedModel(
        IvldAccrualInbound ivldAccrualInbound) {
        if (ivldAccrualInbound instanceof IvldAccrualInboundImpl) {
            return ivldAccrualInbound;
        }

        IvldAccrualInboundImpl ivldAccrualInboundImpl = new IvldAccrualInboundImpl();

        ivldAccrualInboundImpl.setNew(ivldAccrualInbound.isNew());
        ivldAccrualInboundImpl.setPrimaryKey(ivldAccrualInbound.getPrimaryKey());

        ivldAccrualInboundImpl.setRecordCreatedDate(ivldAccrualInbound.getRecordCreatedDate());
        ivldAccrualInboundImpl.setAccountId(ivldAccrualInbound.getAccountId());
        ivldAccrualInboundImpl.setPostingIndicator(ivldAccrualInbound.getPostingIndicator());
        ivldAccrualInboundImpl.setItemId(ivldAccrualInbound.getItemId());
        ivldAccrualInboundImpl.setModifiedDate(ivldAccrualInbound.getModifiedDate());
        ivldAccrualInboundImpl.setAccrualPeriodEndDate(ivldAccrualInbound.getAccrualPeriodEndDate());
        ivldAccrualInboundImpl.setItemIdentCodeQualifier(ivldAccrualInbound.getItemIdentCodeQualifier());
        ivldAccrualInboundImpl.setGlCompanyMasterSid(ivldAccrualInbound.getGlCompanyMasterSid());
        ivldAccrualInboundImpl.setSalesMasterId(ivldAccrualInbound.getSalesMasterId());
        ivldAccrualInboundImpl.setCreatedDate(ivldAccrualInbound.getCreatedDate());
        ivldAccrualInboundImpl.setCreatedBy(ivldAccrualInbound.getCreatedBy());
        ivldAccrualInboundImpl.setSource(ivldAccrualInbound.getSource());
        ivldAccrualInboundImpl.setAccrualPeriodStartDate(ivldAccrualInbound.getAccrualPeriodStartDate());
        ivldAccrualInboundImpl.setAddChgDelIndicator(ivldAccrualInbound.getAddChgDelIndicator());
        ivldAccrualInboundImpl.setSubLedgerType(ivldAccrualInbound.getSubLedgerType());
        ivldAccrualInboundImpl.setProgramNo(ivldAccrualInbound.getProgramNo());
        ivldAccrualInboundImpl.setDocumentType(ivldAccrualInbound.getDocumentType());
        ivldAccrualInboundImpl.setAccrualIntfid(ivldAccrualInbound.getAccrualIntfid());
        ivldAccrualInboundImpl.setGlCompanyName(ivldAccrualInbound.getGlCompanyName());
        ivldAccrualInboundImpl.setErrorCode(ivldAccrualInbound.getErrorCode());
        ivldAccrualInboundImpl.setIntfInsertedDate(ivldAccrualInbound.getIntfInsertedDate());
        ivldAccrualInboundImpl.setModifiedBy(ivldAccrualInbound.getModifiedBy());
        ivldAccrualInboundImpl.setCategoryId(ivldAccrualInbound.getCategoryId());
        ivldAccrualInboundImpl.setItemNo(ivldAccrualInbound.getItemNo());
        ivldAccrualInboundImpl.setReprocessedFlag(ivldAccrualInbound.getReprocessedFlag());
        ivldAccrualInboundImpl.setCompIdentCodeQualifier(ivldAccrualInbound.getCompIdentCodeQualifier());
        ivldAccrualInboundImpl.setAcctIdentCodeQualifier(ivldAccrualInbound.getAcctIdentCodeQualifier());
        ivldAccrualInboundImpl.setContractId(ivldAccrualInbound.getContractId());
        ivldAccrualInboundImpl.setAccountNo(ivldAccrualInbound.getAccountNo());
        ivldAccrualInboundImpl.setAccrualId(ivldAccrualInbound.getAccrualId());
        ivldAccrualInboundImpl.setReasonForFailure(ivldAccrualInbound.getReasonForFailure());
        ivldAccrualInboundImpl.setCompanyId(ivldAccrualInbound.getCompanyId());
        ivldAccrualInboundImpl.setAccountName(ivldAccrualInbound.getAccountName());
        ivldAccrualInboundImpl.setAccrualType(ivldAccrualInbound.getAccrualType());
        ivldAccrualInboundImpl.setPostingDate(ivldAccrualInbound.getPostingDate());
        ivldAccrualInboundImpl.setBrandId(ivldAccrualInbound.getBrandId());
        ivldAccrualInboundImpl.setProvisionId(ivldAccrualInbound.getProvisionId());
        ivldAccrualInboundImpl.setAmount(ivldAccrualInbound.getAmount());
        ivldAccrualInboundImpl.setGlDate(ivldAccrualInbound.getGlDate());
        ivldAccrualInboundImpl.setSubLedger(ivldAccrualInbound.getSubLedger());
        ivldAccrualInboundImpl.setCompanyCostCenter(ivldAccrualInbound.getCompanyCostCenter());
        ivldAccrualInboundImpl.setGlAccount(ivldAccrualInbound.getGlAccount());
        ivldAccrualInboundImpl.setCompanyNo(ivldAccrualInbound.getCompanyNo());
        ivldAccrualInboundImpl.setBatchId(ivldAccrualInbound.getBatchId());
        ivldAccrualInboundImpl.setProgramType(ivldAccrualInbound.getProgramType());
        ivldAccrualInboundImpl.setItemName(ivldAccrualInbound.getItemName());
        ivldAccrualInboundImpl.setErrorField(ivldAccrualInbound.getErrorField());
        ivldAccrualInboundImpl.setIvldAccrualInboundSid(ivldAccrualInbound.getIvldAccrualInboundSid());
        ivldAccrualInboundImpl.setContractNo(ivldAccrualInbound.getContractNo());
        ivldAccrualInboundImpl.setBrandName(ivldAccrualInbound.getBrandName());
        ivldAccrualInboundImpl.setContractName(ivldAccrualInbound.getContractName());

        return ivldAccrualInboundImpl;
    }

    /**
     * Returns the ivld accrual inbound with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound
     * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldAccrualInboundException, SystemException {
        IvldAccrualInbound ivldAccrualInbound = fetchByPrimaryKey(primaryKey);

        if (ivldAccrualInbound == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldAccrualInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldAccrualInbound;
    }

    /**
     * Returns the ivld accrual inbound with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldAccrualInboundException} if it could not be found.
     *
     * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound
     * @throws com.stpl.app.parttwo.NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound findByPrimaryKey(int ivldAccrualInboundSid)
        throws NoSuchIvldAccrualInboundException, SystemException {
        return findByPrimaryKey((Serializable) ivldAccrualInboundSid);
    }

    /**
     * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldAccrualInbound ivldAccrualInbound = (IvldAccrualInbound) EntityCacheUtil.getResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
                IvldAccrualInboundImpl.class, primaryKey);

        if (ivldAccrualInbound == _nullIvldAccrualInbound) {
            return null;
        }

        if (ivldAccrualInbound == null) {
            Session session = null;

            try {
                session = openSession();

                ivldAccrualInbound = (IvldAccrualInbound) session.get(IvldAccrualInboundImpl.class,
                        primaryKey);

                if (ivldAccrualInbound != null) {
                    cacheResult(ivldAccrualInbound);
                } else {
                    EntityCacheUtil.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
                        IvldAccrualInboundImpl.class, primaryKey,
                        _nullIvldAccrualInbound);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
                    IvldAccrualInboundImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldAccrualInbound;
    }

    /**
     * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
     * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAccrualInbound fetchByPrimaryKey(int ivldAccrualInboundSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldAccrualInboundSid);
    }

    /**
     * Returns all the ivld accrual inbounds.
     *
     * @return the ivld accrual inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAccrualInbound> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld accrual inbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld accrual inbounds
     * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
     * @return the range of ivld accrual inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAccrualInbound> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld accrual inbounds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld accrual inbounds
     * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld accrual inbounds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAccrualInbound> findAll(int start, int end,
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

        List<IvldAccrualInbound> list = (List<IvldAccrualInbound>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDACCRUALINBOUND);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDACCRUALINBOUND;

                if (pagination) {
                    sql = sql.concat(IvldAccrualInboundModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldAccrualInbound>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldAccrualInbound>(list);
                } else {
                    list = (List<IvldAccrualInbound>) QueryUtil.list(q,
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
     * Removes all the ivld accrual inbounds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldAccrualInbound ivldAccrualInbound : findAll()) {
            remove(ivldAccrualInbound);
        }
    }

    /**
     * Returns the number of ivld accrual inbounds.
     *
     * @return the number of ivld accrual inbounds
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

                Query q = session.createQuery(_SQL_COUNT_IVLDACCRUALINBOUND);

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
     * Initializes the ivld accrual inbound persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldAccrualInbound")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldAccrualInbound>> listenersList = new ArrayList<ModelListener<IvldAccrualInbound>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldAccrualInbound>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldAccrualInboundImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
