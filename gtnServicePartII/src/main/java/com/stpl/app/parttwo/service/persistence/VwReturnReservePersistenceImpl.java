package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwReturnReserveException;
import com.stpl.app.parttwo.model.VwReturnReserve;
import com.stpl.app.parttwo.model.impl.VwReturnReserveImpl;
import com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl;
import com.stpl.app.parttwo.service.persistence.VwReturnReservePersistence;

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
 * The persistence implementation for the vw return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwReturnReservePersistence
 * @see VwReturnReserveUtil
 * @generated
 */
public class VwReturnReservePersistenceImpl extends BasePersistenceImpl<VwReturnReserve>
    implements VwReturnReservePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwReturnReserveUtil} to access the vw return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwReturnReserveImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveModelImpl.FINDER_CACHE_ENABLED,
            VwReturnReserveImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveModelImpl.FINDER_CACHE_ENABLED,
            VwReturnReserveImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWRETURNRESERVE = "SELECT vwReturnReserve FROM VwReturnReserve vwReturnReserve";
    private static final String _SQL_COUNT_VWRETURNRESERVE = "SELECT COUNT(vwReturnReserve) FROM VwReturnReserve vwReturnReserve";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwReturnReserve.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwReturnReserve exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwReturnReservePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "companyName", "project", "year", "itemId",
                "brandName", "modifiedDate", "brandMasterSid", "account",
                "returnReserveIntfId", "glCompanyMasterSid", "source",
                "createdDate", "createdBy", "businessUnit", "businessUnitName",
                "buCompanyMasterSid", "inboundStatus", "modifiedBy", "itemNo",
                "month", "udc6", "udc5", "udc4", "udc1", "units", "udc2", "udc3",
                "country", "companyId", "costCenter", "glCompany", "brandId",
                "future1", "future2", "amount", "recordLockStatus", "division",
                "returnReserveSid", "companyNo", "batchId", "itemName"
            });
    private static VwReturnReserve _nullVwReturnReserve = new VwReturnReserveImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwReturnReserve> toCacheModel() {
                return _nullVwReturnReserveCacheModel;
            }
        };

    private static CacheModel<VwReturnReserve> _nullVwReturnReserveCacheModel = new CacheModel<VwReturnReserve>() {
            @Override
            public VwReturnReserve toEntityModel() {
                return _nullVwReturnReserve;
            }
        };

    public VwReturnReservePersistenceImpl() {
        setModelClass(VwReturnReserve.class);
    }

    /**
     * Caches the vw return reserve in the entity cache if it is enabled.
     *
     * @param vwReturnReserve the vw return reserve
     */
    @Override
    public void cacheResult(VwReturnReserve vwReturnReserve) {
        EntityCacheUtil.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey(),
            vwReturnReserve);

        vwReturnReserve.resetOriginalValues();
    }

    /**
     * Caches the vw return reserves in the entity cache if it is enabled.
     *
     * @param vwReturnReserves the vw return reserves
     */
    @Override
    public void cacheResult(List<VwReturnReserve> vwReturnReserves) {
        for (VwReturnReserve vwReturnReserve : vwReturnReserves) {
            if (EntityCacheUtil.getResult(
                        VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                        VwReturnReserveImpl.class,
                        vwReturnReserve.getPrimaryKey()) == null) {
                cacheResult(vwReturnReserve);
            } else {
                vwReturnReserve.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw return reserves.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwReturnReserveImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwReturnReserveImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw return reserve.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwReturnReserve vwReturnReserve) {
        EntityCacheUtil.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwReturnReserve> vwReturnReserves) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwReturnReserve vwReturnReserve : vwReturnReserves) {
            EntityCacheUtil.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw return reserve with the primary key. Does not add the vw return reserve to the database.
     *
     * @param returnReserveSid the primary key for the new vw return reserve
     * @return the new vw return reserve
     */
    @Override
    public VwReturnReserve create(int returnReserveSid) {
        VwReturnReserve vwReturnReserve = new VwReturnReserveImpl();

        vwReturnReserve.setNew(true);
        vwReturnReserve.setPrimaryKey(returnReserveSid);

        return vwReturnReserve;
    }

    /**
     * Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param returnReserveSid the primary key of the vw return reserve
     * @return the vw return reserve that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve remove(int returnReserveSid)
        throws NoSuchVwReturnReserveException, SystemException {
        return remove((Serializable) returnReserveSid);
    }

    /**
     * Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw return reserve
     * @return the vw return reserve that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve remove(Serializable primaryKey)
        throws NoSuchVwReturnReserveException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwReturnReserve vwReturnReserve = (VwReturnReserve) session.get(VwReturnReserveImpl.class,
                    primaryKey);

            if (vwReturnReserve == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwReturnReserve);
        } catch (NoSuchVwReturnReserveException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwReturnReserve removeImpl(VwReturnReserve vwReturnReserve)
        throws SystemException {
        vwReturnReserve = toUnwrappedModel(vwReturnReserve);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwReturnReserve)) {
                vwReturnReserve = (VwReturnReserve) session.get(VwReturnReserveImpl.class,
                        vwReturnReserve.getPrimaryKeyObj());
            }

            if (vwReturnReserve != null) {
                session.delete(vwReturnReserve);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwReturnReserve != null) {
            clearCache(vwReturnReserve);
        }

        return vwReturnReserve;
    }

    @Override
    public VwReturnReserve updateImpl(
        com.stpl.app.parttwo.model.VwReturnReserve vwReturnReserve)
        throws SystemException {
        vwReturnReserve = toUnwrappedModel(vwReturnReserve);

        boolean isNew = vwReturnReserve.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwReturnReserve.isNew()) {
                session.save(vwReturnReserve);

                vwReturnReserve.setNew(false);
            } else {
                session.merge(vwReturnReserve);
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

        EntityCacheUtil.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
            VwReturnReserveImpl.class, vwReturnReserve.getPrimaryKey(),
            vwReturnReserve);

        return vwReturnReserve;
    }

    protected VwReturnReserve toUnwrappedModel(VwReturnReserve vwReturnReserve) {
        if (vwReturnReserve instanceof VwReturnReserveImpl) {
            return vwReturnReserve;
        }

        VwReturnReserveImpl vwReturnReserveImpl = new VwReturnReserveImpl();

        vwReturnReserveImpl.setNew(vwReturnReserve.isNew());
        vwReturnReserveImpl.setPrimaryKey(vwReturnReserve.getPrimaryKey());

        vwReturnReserveImpl.setItemMasterSid(vwReturnReserve.getItemMasterSid());
        vwReturnReserveImpl.setCompanyName(vwReturnReserve.getCompanyName());
        vwReturnReserveImpl.setProject(vwReturnReserve.getProject());
        vwReturnReserveImpl.setYear(vwReturnReserve.getYear());
        vwReturnReserveImpl.setItemId(vwReturnReserve.getItemId());
        vwReturnReserveImpl.setBrandName(vwReturnReserve.getBrandName());
        vwReturnReserveImpl.setModifiedDate(vwReturnReserve.getModifiedDate());
        vwReturnReserveImpl.setBrandMasterSid(vwReturnReserve.getBrandMasterSid());
        vwReturnReserveImpl.setAccount(vwReturnReserve.getAccount());
        vwReturnReserveImpl.setReturnReserveIntfId(vwReturnReserve.getReturnReserveIntfId());
        vwReturnReserveImpl.setGlCompanyMasterSid(vwReturnReserve.getGlCompanyMasterSid());
        vwReturnReserveImpl.setSource(vwReturnReserve.getSource());
        vwReturnReserveImpl.setCreatedDate(vwReturnReserve.getCreatedDate());
        vwReturnReserveImpl.setCreatedBy(vwReturnReserve.getCreatedBy());
        vwReturnReserveImpl.setBusinessUnit(vwReturnReserve.getBusinessUnit());
        vwReturnReserveImpl.setBusinessUnitName(vwReturnReserve.getBusinessUnitName());
        vwReturnReserveImpl.setBuCompanyMasterSid(vwReturnReserve.getBuCompanyMasterSid());
        vwReturnReserveImpl.setInboundStatus(vwReturnReserve.getInboundStatus());
        vwReturnReserveImpl.setModifiedBy(vwReturnReserve.getModifiedBy());
        vwReturnReserveImpl.setItemNo(vwReturnReserve.getItemNo());
        vwReturnReserveImpl.setMonth(vwReturnReserve.getMonth());
        vwReturnReserveImpl.setUdc6(vwReturnReserve.getUdc6());
        vwReturnReserveImpl.setUdc5(vwReturnReserve.getUdc5());
        vwReturnReserveImpl.setUdc4(vwReturnReserve.getUdc4());
        vwReturnReserveImpl.setUdc1(vwReturnReserve.getUdc1());
        vwReturnReserveImpl.setUnits(vwReturnReserve.getUnits());
        vwReturnReserveImpl.setUdc2(vwReturnReserve.getUdc2());
        vwReturnReserveImpl.setUdc3(vwReturnReserve.getUdc3());
        vwReturnReserveImpl.setCountry(vwReturnReserve.getCountry());
        vwReturnReserveImpl.setCompanyId(vwReturnReserve.getCompanyId());
        vwReturnReserveImpl.setCostCenter(vwReturnReserve.getCostCenter());
        vwReturnReserveImpl.setGlCompany(vwReturnReserve.getGlCompany());
        vwReturnReserveImpl.setBrandId(vwReturnReserve.getBrandId());
        vwReturnReserveImpl.setFuture1(vwReturnReserve.getFuture1());
        vwReturnReserveImpl.setFuture2(vwReturnReserve.getFuture2());
        vwReturnReserveImpl.setAmount(vwReturnReserve.getAmount());
        vwReturnReserveImpl.setRecordLockStatus(vwReturnReserve.isRecordLockStatus());
        vwReturnReserveImpl.setDivision(vwReturnReserve.getDivision());
        vwReturnReserveImpl.setReturnReserveSid(vwReturnReserve.getReturnReserveSid());
        vwReturnReserveImpl.setCompanyNo(vwReturnReserve.getCompanyNo());
        vwReturnReserveImpl.setBatchId(vwReturnReserve.getBatchId());
        vwReturnReserveImpl.setItemName(vwReturnReserve.getItemName());

        return vwReturnReserveImpl;
    }

    /**
     * Returns the vw return reserve with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw return reserve
     * @return the vw return reserve
     * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwReturnReserveException, SystemException {
        VwReturnReserve vwReturnReserve = fetchByPrimaryKey(primaryKey);

        if (vwReturnReserve == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwReturnReserveException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwReturnReserve;
    }

    /**
     * Returns the vw return reserve with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwReturnReserveException} if it could not be found.
     *
     * @param returnReserveSid the primary key of the vw return reserve
     * @return the vw return reserve
     * @throws com.stpl.app.parttwo.NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve findByPrimaryKey(int returnReserveSid)
        throws NoSuchVwReturnReserveException, SystemException {
        return findByPrimaryKey((Serializable) returnReserveSid);
    }

    /**
     * Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw return reserve
     * @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwReturnReserve vwReturnReserve = (VwReturnReserve) EntityCacheUtil.getResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                VwReturnReserveImpl.class, primaryKey);

        if (vwReturnReserve == _nullVwReturnReserve) {
            return null;
        }

        if (vwReturnReserve == null) {
            Session session = null;

            try {
                session = openSession();

                vwReturnReserve = (VwReturnReserve) session.get(VwReturnReserveImpl.class,
                        primaryKey);

                if (vwReturnReserve != null) {
                    cacheResult(vwReturnReserve);
                } else {
                    EntityCacheUtil.putResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                        VwReturnReserveImpl.class, primaryKey,
                        _nullVwReturnReserve);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwReturnReserveModelImpl.ENTITY_CACHE_ENABLED,
                    VwReturnReserveImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwReturnReserve;
    }

    /**
     * Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param returnReserveSid the primary key of the vw return reserve
     * @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwReturnReserve fetchByPrimaryKey(int returnReserveSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) returnReserveSid);
    }

    /**
     * Returns all the vw return reserves.
     *
     * @return the vw return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwReturnReserve> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw return reserves.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw return reserves
     * @param end the upper bound of the range of vw return reserves (not inclusive)
     * @return the range of vw return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwReturnReserve> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw return reserves.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw return reserves
     * @param end the upper bound of the range of vw return reserves (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw return reserves
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwReturnReserve> findAll(int start, int end,
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

        List<VwReturnReserve> list = (List<VwReturnReserve>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWRETURNRESERVE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWRETURNRESERVE;

                if (pagination) {
                    sql = sql.concat(VwReturnReserveModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwReturnReserve>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwReturnReserve>(list);
                } else {
                    list = (List<VwReturnReserve>) QueryUtil.list(q,
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
     * Removes all the vw return reserves from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwReturnReserve vwReturnReserve : findAll()) {
            remove(vwReturnReserve);
        }
    }

    /**
     * Returns the number of vw return reserves.
     *
     * @return the number of vw return reserves
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

                Query q = session.createQuery(_SQL_COUNT_VWRETURNRESERVE);

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
     * Initializes the vw return reserve persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwReturnReserve")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwReturnReserve>> listenersList = new ArrayList<ModelListener<VwReturnReserve>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwReturnReserve>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwReturnReserveImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
