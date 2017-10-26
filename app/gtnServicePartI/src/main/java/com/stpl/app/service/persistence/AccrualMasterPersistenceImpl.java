package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchAccrualMasterException;
import com.stpl.app.model.AccrualMaster;
import com.stpl.app.model.impl.AccrualMasterImpl;
import com.stpl.app.model.impl.AccrualMasterModelImpl;
import com.stpl.app.service.persistence.AccrualMasterPersistence;

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
 * The persistence implementation for the accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualMasterPersistence
 * @see AccrualMasterUtil
 * @generated
 */
public class AccrualMasterPersistenceImpl extends BasePersistenceImpl<AccrualMaster>
    implements AccrualMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AccrualMasterUtil} to access the accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AccrualMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterModelImpl.FINDER_CACHE_ENABLED,
            AccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterModelImpl.FINDER_CACHE_ENABLED,
            AccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACCRUALMASTER = "SELECT accrualMaster FROM AccrualMaster accrualMaster";
    private static final String _SQL_COUNT_ACCRUALMASTER = "SELECT COUNT(accrualMaster) FROM AccrualMaster accrualMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "accrualMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccrualMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AccrualMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "accountId", "recordCreatedDate", "postingIndicator",
                "brandName", "accrualPeriodEndDate", "modifiedDate",
                "salesMasterId", "source", "contractMasterSid", "documentType",
                "inboundStatus", "modifiedBy", "acctIdentCodeQualifier",
                "compIdentCodeQualifier", "companyMasterSid", "contractName",
                "accountNo", "accountName", "provisionId", "amount", "subLedger",
                "recordLockStatus", "companyNo", "itemName", "rsModelSid",
                "accrualMasterSid", "itemMasterSid", "itemId", "brandMasterSid",
                "itemIdentCodeQualifier", "glCompanyMasterSid", "createdBy",
                "createdDate", "accrualPeriodStartDate", "subLedgerType",
                "programNo", "glCompanyName", "categoryId", "itemNo",
                "contractId", "accrualId", "companyId", "accrualType", "brandId",
                "postingDate", "glDate", "glAmount", "companyCostCenter",
                "glAccount", "batchId", "programType", "contractNo"
            });
    private static AccrualMaster _nullAccrualMaster = new AccrualMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AccrualMaster> toCacheModel() {
                return _nullAccrualMasterCacheModel;
            }
        };

    private static CacheModel<AccrualMaster> _nullAccrualMasterCacheModel = new CacheModel<AccrualMaster>() {
            @Override
            public AccrualMaster toEntityModel() {
                return _nullAccrualMaster;
            }
        };

    public AccrualMasterPersistenceImpl() {
        setModelClass(AccrualMaster.class);
    }

    /**
     * Caches the accrual master in the entity cache if it is enabled.
     *
     * @param accrualMaster the accrual master
     */
    @Override
    public void cacheResult(AccrualMaster accrualMaster) {
        EntityCacheUtil.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterImpl.class, accrualMaster.getPrimaryKey(),
            accrualMaster);

        accrualMaster.resetOriginalValues();
    }

    /**
     * Caches the accrual masters in the entity cache if it is enabled.
     *
     * @param accrualMasters the accrual masters
     */
    @Override
    public void cacheResult(List<AccrualMaster> accrualMasters) {
        for (AccrualMaster accrualMaster : accrualMasters) {
            if (EntityCacheUtil.getResult(
                        AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccrualMasterImpl.class, accrualMaster.getPrimaryKey()) == null) {
                cacheResult(accrualMaster);
            } else {
                accrualMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all accrual masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AccrualMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AccrualMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the accrual master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AccrualMaster accrualMaster) {
        EntityCacheUtil.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterImpl.class, accrualMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AccrualMaster> accrualMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AccrualMaster accrualMaster : accrualMasters) {
            EntityCacheUtil.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccrualMasterImpl.class, accrualMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new accrual master with the primary key. Does not add the accrual master to the database.
     *
     * @param accrualMasterSid the primary key for the new accrual master
     * @return the new accrual master
     */
    @Override
    public AccrualMaster create(int accrualMasterSid) {
        AccrualMaster accrualMaster = new AccrualMasterImpl();

        accrualMaster.setNew(true);
        accrualMaster.setPrimaryKey(accrualMasterSid);

        return accrualMaster;
    }

    /**
     * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accrualMasterSid the primary key of the accrual master
     * @return the accrual master that was removed
     * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster remove(int accrualMasterSid)
        throws NoSuchAccrualMasterException, SystemException {
        return remove((Serializable) accrualMasterSid);
    }

    /**
     * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the accrual master
     * @return the accrual master that was removed
     * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster remove(Serializable primaryKey)
        throws NoSuchAccrualMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AccrualMaster accrualMaster = (AccrualMaster) session.get(AccrualMasterImpl.class,
                    primaryKey);

            if (accrualMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(accrualMaster);
        } catch (NoSuchAccrualMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AccrualMaster removeImpl(AccrualMaster accrualMaster)
        throws SystemException {
        accrualMaster = toUnwrappedModel(accrualMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(accrualMaster)) {
                accrualMaster = (AccrualMaster) session.get(AccrualMasterImpl.class,
                        accrualMaster.getPrimaryKeyObj());
            }

            if (accrualMaster != null) {
                session.delete(accrualMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (accrualMaster != null) {
            clearCache(accrualMaster);
        }

        return accrualMaster;
    }

    @Override
    public AccrualMaster updateImpl(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws SystemException {
        accrualMaster = toUnwrappedModel(accrualMaster);

        boolean isNew = accrualMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (accrualMaster.isNew()) {
                session.save(accrualMaster);

                accrualMaster.setNew(false);
            } else {
                session.merge(accrualMaster);
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

        EntityCacheUtil.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            AccrualMasterImpl.class, accrualMaster.getPrimaryKey(),
            accrualMaster);

        return accrualMaster;
    }

    protected AccrualMaster toUnwrappedModel(AccrualMaster accrualMaster) {
        if (accrualMaster instanceof AccrualMasterImpl) {
            return accrualMaster;
        }

        AccrualMasterImpl accrualMasterImpl = new AccrualMasterImpl();

        accrualMasterImpl.setNew(accrualMaster.isNew());
        accrualMasterImpl.setPrimaryKey(accrualMaster.getPrimaryKey());

        accrualMasterImpl.setAccountId(accrualMaster.getAccountId());
        accrualMasterImpl.setRecordCreatedDate(accrualMaster.getRecordCreatedDate());
        accrualMasterImpl.setPostingIndicator(accrualMaster.getPostingIndicator());
        accrualMasterImpl.setBrandName(accrualMaster.getBrandName());
        accrualMasterImpl.setAccrualPeriodEndDate(accrualMaster.getAccrualPeriodEndDate());
        accrualMasterImpl.setModifiedDate(accrualMaster.getModifiedDate());
        accrualMasterImpl.setSalesMasterId(accrualMaster.getSalesMasterId());
        accrualMasterImpl.setSource(accrualMaster.getSource());
        accrualMasterImpl.setContractMasterSid(accrualMaster.getContractMasterSid());
        accrualMasterImpl.setDocumentType(accrualMaster.getDocumentType());
        accrualMasterImpl.setInboundStatus(accrualMaster.getInboundStatus());
        accrualMasterImpl.setModifiedBy(accrualMaster.getModifiedBy());
        accrualMasterImpl.setAcctIdentCodeQualifier(accrualMaster.getAcctIdentCodeQualifier());
        accrualMasterImpl.setCompIdentCodeQualifier(accrualMaster.getCompIdentCodeQualifier());
        accrualMasterImpl.setCompanyMasterSid(accrualMaster.getCompanyMasterSid());
        accrualMasterImpl.setContractName(accrualMaster.getContractName());
        accrualMasterImpl.setAccountNo(accrualMaster.getAccountNo());
        accrualMasterImpl.setAccountName(accrualMaster.getAccountName());
        accrualMasterImpl.setProvisionId(accrualMaster.getProvisionId());
        accrualMasterImpl.setAmount(accrualMaster.getAmount());
        accrualMasterImpl.setSubLedger(accrualMaster.getSubLedger());
        accrualMasterImpl.setRecordLockStatus(accrualMaster.isRecordLockStatus());
        accrualMasterImpl.setCompanyNo(accrualMaster.getCompanyNo());
        accrualMasterImpl.setItemName(accrualMaster.getItemName());
        accrualMasterImpl.setRsModelSid(accrualMaster.getRsModelSid());
        accrualMasterImpl.setAccrualMasterSid(accrualMaster.getAccrualMasterSid());
        accrualMasterImpl.setItemMasterSid(accrualMaster.getItemMasterSid());
        accrualMasterImpl.setItemId(accrualMaster.getItemId());
        accrualMasterImpl.setBrandMasterSid(accrualMaster.getBrandMasterSid());
        accrualMasterImpl.setItemIdentCodeQualifier(accrualMaster.getItemIdentCodeQualifier());
        accrualMasterImpl.setGlCompanyMasterSid(accrualMaster.getGlCompanyMasterSid());
        accrualMasterImpl.setCreatedBy(accrualMaster.getCreatedBy());
        accrualMasterImpl.setCreatedDate(accrualMaster.getCreatedDate());
        accrualMasterImpl.setAccrualPeriodStartDate(accrualMaster.getAccrualPeriodStartDate());
        accrualMasterImpl.setSubLedgerType(accrualMaster.getSubLedgerType());
        accrualMasterImpl.setProgramNo(accrualMaster.getProgramNo());
        accrualMasterImpl.setGlCompanyName(accrualMaster.getGlCompanyName());
        accrualMasterImpl.setCategoryId(accrualMaster.getCategoryId());
        accrualMasterImpl.setItemNo(accrualMaster.getItemNo());
        accrualMasterImpl.setContractId(accrualMaster.getContractId());
        accrualMasterImpl.setAccrualId(accrualMaster.getAccrualId());
        accrualMasterImpl.setCompanyId(accrualMaster.getCompanyId());
        accrualMasterImpl.setAccrualType(accrualMaster.getAccrualType());
        accrualMasterImpl.setBrandId(accrualMaster.getBrandId());
        accrualMasterImpl.setPostingDate(accrualMaster.getPostingDate());
        accrualMasterImpl.setGlDate(accrualMaster.getGlDate());
        accrualMasterImpl.setGlAmount(accrualMaster.getGlAmount());
        accrualMasterImpl.setCompanyCostCenter(accrualMaster.getCompanyCostCenter());
        accrualMasterImpl.setGlAccount(accrualMaster.getGlAccount());
        accrualMasterImpl.setBatchId(accrualMaster.getBatchId());
        accrualMasterImpl.setProgramType(accrualMaster.getProgramType());
        accrualMasterImpl.setContractNo(accrualMaster.getContractNo());

        return accrualMasterImpl;
    }

    /**
     * Returns the accrual master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the accrual master
     * @return the accrual master
     * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAccrualMasterException, SystemException {
        AccrualMaster accrualMaster = fetchByPrimaryKey(primaryKey);

        if (accrualMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return accrualMaster;
    }

    /**
     * Returns the accrual master with the primary key or throws a {@link com.stpl.app.NoSuchAccrualMasterException} if it could not be found.
     *
     * @param accrualMasterSid the primary key of the accrual master
     * @return the accrual master
     * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster findByPrimaryKey(int accrualMasterSid)
        throws NoSuchAccrualMasterException, SystemException {
        return findByPrimaryKey((Serializable) accrualMasterSid);
    }

    /**
     * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the accrual master
     * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AccrualMaster accrualMaster = (AccrualMaster) EntityCacheUtil.getResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                AccrualMasterImpl.class, primaryKey);

        if (accrualMaster == _nullAccrualMaster) {
            return null;
        }

        if (accrualMaster == null) {
            Session session = null;

            try {
                session = openSession();

                accrualMaster = (AccrualMaster) session.get(AccrualMasterImpl.class,
                        primaryKey);

                if (accrualMaster != null) {
                    cacheResult(accrualMaster);
                } else {
                    EntityCacheUtil.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        AccrualMasterImpl.class, primaryKey, _nullAccrualMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                    AccrualMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return accrualMaster;
    }

    /**
     * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accrualMasterSid the primary key of the accrual master
     * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccrualMaster fetchByPrimaryKey(int accrualMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accrualMasterSid);
    }

    /**
     * Returns all the accrual masters.
     *
     * @return the accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the accrual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of accrual masters
     * @param end the upper bound of the range of accrual masters (not inclusive)
     * @return the range of accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the accrual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of accrual masters
     * @param end the upper bound of the range of accrual masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccrualMaster> findAll(int start, int end,
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

        List<AccrualMaster> list = (List<AccrualMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACCRUALMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACCRUALMASTER;

                if (pagination) {
                    sql = sql.concat(AccrualMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AccrualMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AccrualMaster>(list);
                } else {
                    list = (List<AccrualMaster>) QueryUtil.list(q,
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
     * Removes all the accrual masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AccrualMaster accrualMaster : findAll()) {
            remove(accrualMaster);
        }
    }

    /**
     * Returns the number of accrual masters.
     *
     * @return the number of accrual masters
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

                Query q = session.createQuery(_SQL_COUNT_ACCRUALMASTER);

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
     * Initializes the accrual master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.AccrualMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AccrualMaster>> listenersList = new ArrayList<ModelListener<AccrualMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AccrualMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AccrualMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
