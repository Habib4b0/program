package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwAccrualMasterException;
import com.stpl.app.model.VwAccrualMaster;
import com.stpl.app.model.impl.VwAccrualMasterImpl;
import com.stpl.app.model.impl.VwAccrualMasterModelImpl;
import com.stpl.app.service.persistence.VwAccrualMasterPersistence;

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
 * The persistence implementation for the vw accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAccrualMasterPersistence
 * @see VwAccrualMasterUtil
 * @generated
 */
public class VwAccrualMasterPersistenceImpl extends BasePersistenceImpl<VwAccrualMaster>
    implements VwAccrualMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwAccrualMasterUtil} to access the vw accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwAccrualMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED,
            VwAccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED,
            VwAccrualMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWACCRUALMASTER = "SELECT vwAccrualMaster FROM VwAccrualMaster vwAccrualMaster";
    private static final String _SQL_COUNT_VWACCRUALMASTER = "SELECT COUNT(vwAccrualMaster) FROM VwAccrualMaster vwAccrualMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwAccrualMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwAccrualMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwAccrualMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemQualifier", "businessUnitQualifier", "itemNo",
                "postingIndicator", "createdDate", "costCenter", "subLedger",
                "accrualPeriodEd", "accrualId", "companyQualifier", "contractNo",
                "batchId", "itemName", "brandId", "postingDate",
                "businessUnitName", "salesId", "amount", "businessUnitNo",
                "subLedgerType", "documentType", "accuralType", "createdBy",
                "programNo", "customerId", "itemId", "accrualMasterSid",
                "contractId", "contractName", "glAccount", "glDate",
                "businessUnitId", "programType", "customerName", "customerNo",
                "source", "accrualPeriodSd"
            });
    private static VwAccrualMaster _nullVwAccrualMaster = new VwAccrualMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwAccrualMaster> toCacheModel() {
                return _nullVwAccrualMasterCacheModel;
            }
        };

    private static CacheModel<VwAccrualMaster> _nullVwAccrualMasterCacheModel = new CacheModel<VwAccrualMaster>() {
            @Override
            public VwAccrualMaster toEntityModel() {
                return _nullVwAccrualMaster;
            }
        };

    public VwAccrualMasterPersistenceImpl() {
        setModelClass(VwAccrualMaster.class);
    }

    /**
     * Caches the vw accrual master in the entity cache if it is enabled.
     *
     * @param vwAccrualMaster the vw accrual master
     */
    @Override
    public void cacheResult(VwAccrualMaster vwAccrualMaster) {
        EntityCacheUtil.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey(),
            vwAccrualMaster);

        vwAccrualMaster.resetOriginalValues();
    }

    /**
     * Caches the vw accrual masters in the entity cache if it is enabled.
     *
     * @param vwAccrualMasters the vw accrual masters
     */
    @Override
    public void cacheResult(List<VwAccrualMaster> vwAccrualMasters) {
        for (VwAccrualMaster vwAccrualMaster : vwAccrualMasters) {
            if (EntityCacheUtil.getResult(
                        VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwAccrualMasterImpl.class,
                        vwAccrualMaster.getPrimaryKey()) == null) {
                cacheResult(vwAccrualMaster);
            } else {
                vwAccrualMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw accrual masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwAccrualMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwAccrualMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw accrual master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwAccrualMaster vwAccrualMaster) {
        EntityCacheUtil.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwAccrualMaster> vwAccrualMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwAccrualMaster vwAccrualMaster : vwAccrualMasters) {
            EntityCacheUtil.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
     *
     * @param accrualMasterSid the primary key for the new vw accrual master
     * @return the new vw accrual master
     */
    @Override
    public VwAccrualMaster create(int accrualMasterSid) {
        VwAccrualMaster vwAccrualMaster = new VwAccrualMasterImpl();

        vwAccrualMaster.setNew(true);
        vwAccrualMaster.setPrimaryKey(accrualMasterSid);

        return vwAccrualMaster;
    }

    /**
     * Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accrualMasterSid the primary key of the vw accrual master
     * @return the vw accrual master that was removed
     * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster remove(int accrualMasterSid)
        throws NoSuchVwAccrualMasterException, SystemException {
        return remove((Serializable) accrualMasterSid);
    }

    /**
     * Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw accrual master
     * @return the vw accrual master that was removed
     * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster remove(Serializable primaryKey)
        throws NoSuchVwAccrualMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwAccrualMaster vwAccrualMaster = (VwAccrualMaster) session.get(VwAccrualMasterImpl.class,
                    primaryKey);

            if (vwAccrualMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwAccrualMaster);
        } catch (NoSuchVwAccrualMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwAccrualMaster removeImpl(VwAccrualMaster vwAccrualMaster)
        throws SystemException {
        vwAccrualMaster = toUnwrappedModel(vwAccrualMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwAccrualMaster)) {
                vwAccrualMaster = (VwAccrualMaster) session.get(VwAccrualMasterImpl.class,
                        vwAccrualMaster.getPrimaryKeyObj());
            }

            if (vwAccrualMaster != null) {
                session.delete(vwAccrualMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwAccrualMaster != null) {
            clearCache(vwAccrualMaster);
        }

        return vwAccrualMaster;
    }

    @Override
    public VwAccrualMaster updateImpl(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster)
        throws SystemException {
        vwAccrualMaster = toUnwrappedModel(vwAccrualMaster);

        boolean isNew = vwAccrualMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwAccrualMaster.isNew()) {
                session.save(vwAccrualMaster);

                vwAccrualMaster.setNew(false);
            } else {
                session.merge(vwAccrualMaster);
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

        EntityCacheUtil.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey(),
            vwAccrualMaster);

        return vwAccrualMaster;
    }

    protected VwAccrualMaster toUnwrappedModel(VwAccrualMaster vwAccrualMaster) {
        if (vwAccrualMaster instanceof VwAccrualMasterImpl) {
            return vwAccrualMaster;
        }

        VwAccrualMasterImpl vwAccrualMasterImpl = new VwAccrualMasterImpl();

        vwAccrualMasterImpl.setNew(vwAccrualMaster.isNew());
        vwAccrualMasterImpl.setPrimaryKey(vwAccrualMaster.getPrimaryKey());

        vwAccrualMasterImpl.setItemQualifier(vwAccrualMaster.getItemQualifier());
        vwAccrualMasterImpl.setBusinessUnitQualifier(vwAccrualMaster.getBusinessUnitQualifier());
        vwAccrualMasterImpl.setItemNo(vwAccrualMaster.getItemNo());
        vwAccrualMasterImpl.setPostingIndicator(vwAccrualMaster.getPostingIndicator());
        vwAccrualMasterImpl.setCreatedDate(vwAccrualMaster.getCreatedDate());
        vwAccrualMasterImpl.setCostCenter(vwAccrualMaster.getCostCenter());
        vwAccrualMasterImpl.setSubLedger(vwAccrualMaster.getSubLedger());
        vwAccrualMasterImpl.setAccrualPeriodEd(vwAccrualMaster.getAccrualPeriodEd());
        vwAccrualMasterImpl.setAccrualId(vwAccrualMaster.getAccrualId());
        vwAccrualMasterImpl.setCompanyQualifier(vwAccrualMaster.getCompanyQualifier());
        vwAccrualMasterImpl.setContractNo(vwAccrualMaster.getContractNo());
        vwAccrualMasterImpl.setBatchId(vwAccrualMaster.getBatchId());
        vwAccrualMasterImpl.setItemName(vwAccrualMaster.getItemName());
        vwAccrualMasterImpl.setBrandId(vwAccrualMaster.getBrandId());
        vwAccrualMasterImpl.setPostingDate(vwAccrualMaster.getPostingDate());
        vwAccrualMasterImpl.setBusinessUnitName(vwAccrualMaster.getBusinessUnitName());
        vwAccrualMasterImpl.setSalesId(vwAccrualMaster.getSalesId());
        vwAccrualMasterImpl.setAmount(vwAccrualMaster.getAmount());
        vwAccrualMasterImpl.setBusinessUnitNo(vwAccrualMaster.getBusinessUnitNo());
        vwAccrualMasterImpl.setSubLedgerType(vwAccrualMaster.getSubLedgerType());
        vwAccrualMasterImpl.setDocumentType(vwAccrualMaster.getDocumentType());
        vwAccrualMasterImpl.setAccuralType(vwAccrualMaster.getAccuralType());
        vwAccrualMasterImpl.setCreatedBy(vwAccrualMaster.getCreatedBy());
        vwAccrualMasterImpl.setProgramNo(vwAccrualMaster.getProgramNo());
        vwAccrualMasterImpl.setCustomerId(vwAccrualMaster.getCustomerId());
        vwAccrualMasterImpl.setItemId(vwAccrualMaster.getItemId());
        vwAccrualMasterImpl.setAccrualMasterSid(vwAccrualMaster.getAccrualMasterSid());
        vwAccrualMasterImpl.setContractId(vwAccrualMaster.getContractId());
        vwAccrualMasterImpl.setContractName(vwAccrualMaster.getContractName());
        vwAccrualMasterImpl.setGlAccount(vwAccrualMaster.getGlAccount());
        vwAccrualMasterImpl.setGlDate(vwAccrualMaster.getGlDate());
        vwAccrualMasterImpl.setBusinessUnitId(vwAccrualMaster.getBusinessUnitId());
        vwAccrualMasterImpl.setProgramType(vwAccrualMaster.getProgramType());
        vwAccrualMasterImpl.setCustomerName(vwAccrualMaster.getCustomerName());
        vwAccrualMasterImpl.setCustomerNo(vwAccrualMaster.getCustomerNo());
        vwAccrualMasterImpl.setSource(vwAccrualMaster.getSource());
        vwAccrualMasterImpl.setAccrualPeriodSd(vwAccrualMaster.getAccrualPeriodSd());

        return vwAccrualMasterImpl;
    }

    /**
     * Returns the vw accrual master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw accrual master
     * @return the vw accrual master
     * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwAccrualMasterException, SystemException {
        VwAccrualMaster vwAccrualMaster = fetchByPrimaryKey(primaryKey);

        if (vwAccrualMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwAccrualMaster;
    }

    /**
     * Returns the vw accrual master with the primary key or throws a {@link com.stpl.app.NoSuchVwAccrualMasterException} if it could not be found.
     *
     * @param accrualMasterSid the primary key of the vw accrual master
     * @return the vw accrual master
     * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster findByPrimaryKey(int accrualMasterSid)
        throws NoSuchVwAccrualMasterException, SystemException {
        return findByPrimaryKey((Serializable) accrualMasterSid);
    }

    /**
     * Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw accrual master
     * @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwAccrualMaster vwAccrualMaster = (VwAccrualMaster) EntityCacheUtil.getResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwAccrualMasterImpl.class, primaryKey);

        if (vwAccrualMaster == _nullVwAccrualMaster) {
            return null;
        }

        if (vwAccrualMaster == null) {
            Session session = null;

            try {
                session = openSession();

                vwAccrualMaster = (VwAccrualMaster) session.get(VwAccrualMasterImpl.class,
                        primaryKey);

                if (vwAccrualMaster != null) {
                    cacheResult(vwAccrualMaster);
                } else {
                    EntityCacheUtil.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwAccrualMasterImpl.class, primaryKey,
                        _nullVwAccrualMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
                    VwAccrualMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwAccrualMaster;
    }

    /**
     * Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accrualMasterSid the primary key of the vw accrual master
     * @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwAccrualMaster fetchByPrimaryKey(int accrualMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accrualMasterSid);
    }

    /**
     * Returns all the vw accrual masters.
     *
     * @return the vw accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAccrualMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw accrual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw accrual masters
     * @param end the upper bound of the range of vw accrual masters (not inclusive)
     * @return the range of vw accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAccrualMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw accrual masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw accrual masters
     * @param end the upper bound of the range of vw accrual masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw accrual masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwAccrualMaster> findAll(int start, int end,
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

        List<VwAccrualMaster> list = (List<VwAccrualMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWACCRUALMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWACCRUALMASTER;

                if (pagination) {
                    sql = sql.concat(VwAccrualMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwAccrualMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwAccrualMaster>(list);
                } else {
                    list = (List<VwAccrualMaster>) QueryUtil.list(q,
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
     * Removes all the vw accrual masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwAccrualMaster vwAccrualMaster : findAll()) {
            remove(vwAccrualMaster);
        }
    }

    /**
     * Returns the number of vw accrual masters.
     *
     * @return the number of vw accrual masters
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

                Query q = session.createQuery(_SQL_COUNT_VWACCRUALMASTER);

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
     * Initializes the vw accrual master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwAccrualMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwAccrualMaster>> listenersList = new ArrayList<ModelListener<VwAccrualMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwAccrualMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwAccrualMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}