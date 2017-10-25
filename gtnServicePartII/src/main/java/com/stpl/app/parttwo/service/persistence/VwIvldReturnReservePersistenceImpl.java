package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException;
import com.stpl.app.parttwo.model.VwIvldReturnReserve;
import com.stpl.app.parttwo.model.impl.VwIvldReturnReserveImpl;
import com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl;
import com.stpl.app.parttwo.service.persistence.VwIvldReturnReservePersistence;

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
 * The persistence implementation for the vw ivld return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldReturnReservePersistence
 * @see VwIvldReturnReserveUtil
 * @generated
 */
public class VwIvldReturnReservePersistenceImpl extends BasePersistenceImpl<VwIvldReturnReserve>
    implements VwIvldReturnReservePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwIvldReturnReserveUtil} to access the vw ivld return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwIvldReturnReserveImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED,
            VwIvldReturnReserveImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED,
            VwIvldReturnReserveImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWIVLDRETURNRESERVE = "SELECT vwIvldReturnReserve FROM VwIvldReturnReserve vwIvldReturnReserve";
    private static final String _SQL_COUNT_VWIVLDRETURNRESERVE = "SELECT COUNT(vwIvldReturnReserve) FROM VwIvldReturnReserve vwIvldReturnReserve";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldReturnReserve.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldReturnReserve exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwIvldReturnReservePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ivldReturnReserveSid", "companyName", "year", "project",
                "itemId", "brandName", "modifiedDate", "account",
                "returnReserveIntfId", "source", "createdDate", "createdBy",
                "businessUnit", "businessUnitName", "addChgDelIndicator",
                "errorCode", "intfInsertedDate", "modifiedBy", "itemNo", "month",
                "reprocessedFlag", "udc6", "udc5", "udc4", "udc1", "units",
                "udc2", "udc3", "reasonForFailure", "country", "companyId",
                "costCenter", "glCompany", "brandId", "future1", "future2",
                "amount", "division", "companyNo", "batchId", "itemName",
                "errorField", "checkRecord"
            });
    private static VwIvldReturnReserve _nullVwIvldReturnReserve = new VwIvldReturnReserveImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwIvldReturnReserve> toCacheModel() {
                return _nullVwIvldReturnReserveCacheModel;
            }
        };

    private static CacheModel<VwIvldReturnReserve> _nullVwIvldReturnReserveCacheModel =
        new CacheModel<VwIvldReturnReserve>() {
            @Override
            public VwIvldReturnReserve toEntityModel() {
                return _nullVwIvldReturnReserve;
            }
        };

    public VwIvldReturnReservePersistenceImpl() {
        setModelClass(VwIvldReturnReserve.class);
    }

    /**
     * Caches the vw ivld return reserve in the entity cache if it is enabled.
     *
     * @param vwIvldReturnReserve the vw ivld return reserve
     */
    @Override
    public void cacheResult(VwIvldReturnReserve vwIvldReturnReserve) {
        EntityCacheUtil.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey(),
            vwIvldReturnReserve);

        vwIvldReturnReserve.resetOriginalValues();
    }

    /**
     * Caches the vw ivld return reserves in the entity cache if it is enabled.
     *
     * @param vwIvldReturnReserves the vw ivld return reserves
     */
    @Override
    public void cacheResult(List<VwIvldReturnReserve> vwIvldReturnReserves) {
        for (VwIvldReturnReserve vwIvldReturnReserve : vwIvldReturnReserves) {
            if (EntityCacheUtil.getResult(
                        VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldReturnReserveImpl.class,
                        vwIvldReturnReserve.getPrimaryKey()) == null) {
                cacheResult(vwIvldReturnReserve);
            } else {
                vwIvldReturnReserve.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw ivld return reserves.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwIvldReturnReserveImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwIvldReturnReserveImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw ivld return reserve.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwIvldReturnReserve vwIvldReturnReserve) {
        EntityCacheUtil.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwIvldReturnReserve> vwIvldReturnReserves) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwIvldReturnReserve vwIvldReturnReserve : vwIvldReturnReserves) {
            EntityCacheUtil.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldReturnReserveImpl.class,
                vwIvldReturnReserve.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
     *
     * @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
     * @return the new vw ivld return reserve
     */
    @Override
    public VwIvldReturnReserve create(int ivldReturnReserveSid) {
        VwIvldReturnReserve vwIvldReturnReserve = new VwIvldReturnReserveImpl();

        vwIvldReturnReserve.setNew(true);
        vwIvldReturnReserve.setPrimaryKey(ivldReturnReserveSid);

        return vwIvldReturnReserve;
    }

    /**
     * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve remove(int ivldReturnReserveSid)
        throws NoSuchVwIvldReturnReserveException, SystemException {
        return remove((Serializable) ivldReturnReserveSid);
    }

    /**
     * Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve remove(Serializable primaryKey)
        throws NoSuchVwIvldReturnReserveException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwIvldReturnReserve vwIvldReturnReserve = (VwIvldReturnReserve) session.get(VwIvldReturnReserveImpl.class,
                    primaryKey);

            if (vwIvldReturnReserve == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwIvldReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwIvldReturnReserve);
        } catch (NoSuchVwIvldReturnReserveException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwIvldReturnReserve removeImpl(
        VwIvldReturnReserve vwIvldReturnReserve) throws SystemException {
        vwIvldReturnReserve = toUnwrappedModel(vwIvldReturnReserve);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwIvldReturnReserve)) {
                vwIvldReturnReserve = (VwIvldReturnReserve) session.get(VwIvldReturnReserveImpl.class,
                        vwIvldReturnReserve.getPrimaryKeyObj());
            }

            if (vwIvldReturnReserve != null) {
                session.delete(vwIvldReturnReserve);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwIvldReturnReserve != null) {
            clearCache(vwIvldReturnReserve);
        }

        return vwIvldReturnReserve;
    }

    @Override
    public VwIvldReturnReserve updateImpl(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws SystemException {
        vwIvldReturnReserve = toUnwrappedModel(vwIvldReturnReserve);

        boolean isNew = vwIvldReturnReserve.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwIvldReturnReserve.isNew()) {
                session.save(vwIvldReturnReserve);

                vwIvldReturnReserve.setNew(false);
            } else {
                session.merge(vwIvldReturnReserve);
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

        EntityCacheUtil.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldReturnReserveImpl.class, vwIvldReturnReserve.getPrimaryKey(),
            vwIvldReturnReserve);

        return vwIvldReturnReserve;
    }

    protected VwIvldReturnReserve toUnwrappedModel(
        VwIvldReturnReserve vwIvldReturnReserve) {
        if (vwIvldReturnReserve instanceof VwIvldReturnReserveImpl) {
            return vwIvldReturnReserve;
        }

        VwIvldReturnReserveImpl vwIvldReturnReserveImpl = new VwIvldReturnReserveImpl();

        vwIvldReturnReserveImpl.setNew(vwIvldReturnReserve.isNew());
        vwIvldReturnReserveImpl.setPrimaryKey(vwIvldReturnReserve.getPrimaryKey());

        vwIvldReturnReserveImpl.setIvldReturnReserveSid(vwIvldReturnReserve.getIvldReturnReserveSid());
        vwIvldReturnReserveImpl.setCompanyName(vwIvldReturnReserve.getCompanyName());
        vwIvldReturnReserveImpl.setYear(vwIvldReturnReserve.getYear());
        vwIvldReturnReserveImpl.setProject(vwIvldReturnReserve.getProject());
        vwIvldReturnReserveImpl.setItemId(vwIvldReturnReserve.getItemId());
        vwIvldReturnReserveImpl.setBrandName(vwIvldReturnReserve.getBrandName());
        vwIvldReturnReserveImpl.setModifiedDate(vwIvldReturnReserve.getModifiedDate());
        vwIvldReturnReserveImpl.setAccount(vwIvldReturnReserve.getAccount());
        vwIvldReturnReserveImpl.setReturnReserveIntfId(vwIvldReturnReserve.getReturnReserveIntfId());
        vwIvldReturnReserveImpl.setSource(vwIvldReturnReserve.getSource());
        vwIvldReturnReserveImpl.setCreatedDate(vwIvldReturnReserve.getCreatedDate());
        vwIvldReturnReserveImpl.setCreatedBy(vwIvldReturnReserve.getCreatedBy());
        vwIvldReturnReserveImpl.setBusinessUnit(vwIvldReturnReserve.getBusinessUnit());
        vwIvldReturnReserveImpl.setBusinessUnitName(vwIvldReturnReserve.getBusinessUnitName());
        vwIvldReturnReserveImpl.setAddChgDelIndicator(vwIvldReturnReserve.getAddChgDelIndicator());
        vwIvldReturnReserveImpl.setErrorCode(vwIvldReturnReserve.getErrorCode());
        vwIvldReturnReserveImpl.setIntfInsertedDate(vwIvldReturnReserve.getIntfInsertedDate());
        vwIvldReturnReserveImpl.setModifiedBy(vwIvldReturnReserve.getModifiedBy());
        vwIvldReturnReserveImpl.setItemNo(vwIvldReturnReserve.getItemNo());
        vwIvldReturnReserveImpl.setMonth(vwIvldReturnReserve.getMonth());
        vwIvldReturnReserveImpl.setReprocessedFlag(vwIvldReturnReserve.getReprocessedFlag());
        vwIvldReturnReserveImpl.setUdc6(vwIvldReturnReserve.getUdc6());
        vwIvldReturnReserveImpl.setUdc5(vwIvldReturnReserve.getUdc5());
        vwIvldReturnReserveImpl.setUdc4(vwIvldReturnReserve.getUdc4());
        vwIvldReturnReserveImpl.setUdc1(vwIvldReturnReserve.getUdc1());
        vwIvldReturnReserveImpl.setUnits(vwIvldReturnReserve.getUnits());
        vwIvldReturnReserveImpl.setUdc2(vwIvldReturnReserve.getUdc2());
        vwIvldReturnReserveImpl.setUdc3(vwIvldReturnReserve.getUdc3());
        vwIvldReturnReserveImpl.setReasonForFailure(vwIvldReturnReserve.getReasonForFailure());
        vwIvldReturnReserveImpl.setCountry(vwIvldReturnReserve.getCountry());
        vwIvldReturnReserveImpl.setCompanyId(vwIvldReturnReserve.getCompanyId());
        vwIvldReturnReserveImpl.setCostCenter(vwIvldReturnReserve.getCostCenter());
        vwIvldReturnReserveImpl.setGlCompany(vwIvldReturnReserve.getGlCompany());
        vwIvldReturnReserveImpl.setBrandId(vwIvldReturnReserve.getBrandId());
        vwIvldReturnReserveImpl.setFuture1(vwIvldReturnReserve.getFuture1());
        vwIvldReturnReserveImpl.setFuture2(vwIvldReturnReserve.getFuture2());
        vwIvldReturnReserveImpl.setAmount(vwIvldReturnReserve.getAmount());
        vwIvldReturnReserveImpl.setDivision(vwIvldReturnReserve.getDivision());
        vwIvldReturnReserveImpl.setCompanyNo(vwIvldReturnReserve.getCompanyNo());
        vwIvldReturnReserveImpl.setBatchId(vwIvldReturnReserve.getBatchId());
        vwIvldReturnReserveImpl.setItemName(vwIvldReturnReserve.getItemName());
        vwIvldReturnReserveImpl.setErrorField(vwIvldReturnReserve.getErrorField());
        vwIvldReturnReserveImpl.setCheckRecord(vwIvldReturnReserve.isCheckRecord());

        return vwIvldReturnReserveImpl;
    }

    /**
     * Returns the vw ivld return reserve with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve
     * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwIvldReturnReserveException, SystemException {
        VwIvldReturnReserve vwIvldReturnReserve = fetchByPrimaryKey(primaryKey);

        if (vwIvldReturnReserve == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwIvldReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwIvldReturnReserve;
    }

    /**
     * Returns the vw ivld return reserve with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException} if it could not be found.
     *
     * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve
     * @throws com.stpl.app.parttwo.NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve findByPrimaryKey(int ivldReturnReserveSid)
        throws NoSuchVwIvldReturnReserveException, SystemException {
        return findByPrimaryKey((Serializable) ivldReturnReserveSid);
    }

    /**
     * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwIvldReturnReserve vwIvldReturnReserve = (VwIvldReturnReserve) EntityCacheUtil.getResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldReturnReserveImpl.class, primaryKey);

        if (vwIvldReturnReserve == _nullVwIvldReturnReserve) {
            return null;
        }

        if (vwIvldReturnReserve == null) {
            Session session = null;

            try {
                session = openSession();

                vwIvldReturnReserve = (VwIvldReturnReserve) session.get(VwIvldReturnReserveImpl.class,
                        primaryKey);

                if (vwIvldReturnReserve != null) {
                    cacheResult(vwIvldReturnReserve);
                } else {
                    EntityCacheUtil.putResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldReturnReserveImpl.class, primaryKey,
                        _nullVwIvldReturnReserve);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwIvldReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                    VwIvldReturnReserveImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwIvldReturnReserve;
    }

    /**
     * Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
     * @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldReturnReserve fetchByPrimaryKey(int ivldReturnReserveSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldReturnReserveSid);
    }

    /**
     * Returns all the vw ivld return reserves.
     *
     * @return the vw ivld return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldReturnReserve> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw ivld return reserves.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld return reserves
     * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
     * @return the range of vw ivld return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldReturnReserve> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw ivld return reserves.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld return reserves
     * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw ivld return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldReturnReserve> findAll(int start, int end,
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

        List<VwIvldReturnReserve> list = (List<VwIvldReturnReserve>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWIVLDRETURNRESERVE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWIVLDRETURNRESERVE;

                if (pagination) {
                    sql = sql.concat(VwIvldReturnReserveModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwIvldReturnReserve>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwIvldReturnReserve>(list);
                } else {
                    list = (List<VwIvldReturnReserve>) QueryUtil.list(q,
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
     * Removes all the vw ivld return reserves from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwIvldReturnReserve vwIvldReturnReserve : findAll()) {
            remove(vwIvldReturnReserve);
        }
    }

    /**
     * Returns the number of vw ivld return reserves.
     *
     * @return the number of vw ivld return reserves
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

                Query q = session.createQuery(_SQL_COUNT_VWIVLDRETURNRESERVE);

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
     * Initializes the vw ivld return reserve persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwIvldReturnReserve")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwIvldReturnReserve>> listenersList = new ArrayList<ModelListener<VwIvldReturnReserve>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwIvldReturnReserve>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwIvldReturnReserveImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
