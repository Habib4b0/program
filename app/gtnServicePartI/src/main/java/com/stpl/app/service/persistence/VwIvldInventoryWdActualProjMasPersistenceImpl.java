package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasImpl;
import com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl;
import com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasPersistence;

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
 * The persistence implementation for the vw ivld inventory wd actual proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldInventoryWdActualProjMasPersistence
 * @see VwIvldInventoryWdActualProjMasUtil
 * @generated
 */
public class VwIvldInventoryWdActualProjMasPersistenceImpl
    extends BasePersistenceImpl<VwIvldInventoryWdActualProjMas>
    implements VwIvldInventoryWdActualProjMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwIvldInventoryWdActualProjMasUtil} to access the vw ivld inventory wd actual proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwIvldInventoryWdActualProjMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS = "SELECT vwIvldInventoryWdActualProjMas FROM VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas";
    private static final String _SQL_COUNT_VWIVLDINVENTORYWDACTUALPROJMAS = "SELECT COUNT(vwIvldInventoryWdActualProjMas) FROM VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldInventoryWdActualProjMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldInventoryWdActualProjMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwIvldInventoryWdActualProjMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ivldInventoryWdActualProjMasSid", "quantityOnOrder", "week",
                "price", "amountOnHand", "isMaster", "companyName", "year",
                "itemId", "modifiedDate", "organizationKey", "source",
                "createdBy", "createdDate", "day", "addChgDelIndicator",
                "unitsOnHand", "amountReceived", "errorCode", "intfInsertedDate",
                "modifiedBy", "month", "reprocessedFlag", "amountWithdrawn",
                "quantityReceived", "unitsWithdrawn", "reasonForFailure",
                "country", "companyId", "isForecast",
                "inventoryWdActualProjMasIntfId", "forecastVer", "batchId",
                "itemName", "errorField", "amountOnOrder", "forecastName",
                "checkRecord"
            });
    private static VwIvldInventoryWdActualProjMas _nullVwIvldInventoryWdActualProjMas =
        new VwIvldInventoryWdActualProjMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwIvldInventoryWdActualProjMas> toCacheModel() {
                return _nullVwIvldInventoryWdActualProjMasCacheModel;
            }
        };

    private static CacheModel<VwIvldInventoryWdActualProjMas> _nullVwIvldInventoryWdActualProjMasCacheModel =
        new CacheModel<VwIvldInventoryWdActualProjMas>() {
            @Override
            public VwIvldInventoryWdActualProjMas toEntityModel() {
                return _nullVwIvldInventoryWdActualProjMas;
            }
        };

    public VwIvldInventoryWdActualProjMasPersistenceImpl() {
        setModelClass(VwIvldInventoryWdActualProjMas.class);
    }

    /**
     * Caches the vw ivld inventory wd actual proj mas in the entity cache if it is enabled.
     *
     * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
     */
    @Override
    public void cacheResult(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
        EntityCacheUtil.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasImpl.class,
            vwIvldInventoryWdActualProjMas.getPrimaryKey(),
            vwIvldInventoryWdActualProjMas);

        vwIvldInventoryWdActualProjMas.resetOriginalValues();
    }

    /**
     * Caches the vw ivld inventory wd actual proj mases in the entity cache if it is enabled.
     *
     * @param vwIvldInventoryWdActualProjMases the vw ivld inventory wd actual proj mases
     */
    @Override
    public void cacheResult(
        List<VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases) {
        for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : vwIvldInventoryWdActualProjMases) {
            if (EntityCacheUtil.getResult(
                        VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldInventoryWdActualProjMasImpl.class,
                        vwIvldInventoryWdActualProjMas.getPrimaryKey()) == null) {
                cacheResult(vwIvldInventoryWdActualProjMas);
            } else {
                vwIvldInventoryWdActualProjMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw ivld inventory wd actual proj mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwIvldInventoryWdActualProjMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwIvldInventoryWdActualProjMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw ivld inventory wd actual proj mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
        EntityCacheUtil.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasImpl.class,
            vwIvldInventoryWdActualProjMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : vwIvldInventoryWdActualProjMases) {
            EntityCacheUtil.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldInventoryWdActualProjMasImpl.class,
                vwIvldInventoryWdActualProjMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw ivld inventory wd actual proj mas with the primary key. Does not add the vw ivld inventory wd actual proj mas to the database.
     *
     * @param ivldInventoryWdActualProjMasSid the primary key for the new vw ivld inventory wd actual proj mas
     * @return the new vw ivld inventory wd actual proj mas
     */
    @Override
    public VwIvldInventoryWdActualProjMas create(
        int ivldInventoryWdActualProjMasSid) {
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = new VwIvldInventoryWdActualProjMasImpl();

        vwIvldInventoryWdActualProjMas.setNew(true);
        vwIvldInventoryWdActualProjMas.setPrimaryKey(ivldInventoryWdActualProjMasSid);

        return vwIvldInventoryWdActualProjMas;
    }

    /**
     * Removes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas that was removed
     * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas remove(
        int ivldInventoryWdActualProjMasSid)
        throws NoSuchVwIvldInventoryWdActualProjMasException, SystemException {
        return remove((Serializable) ivldInventoryWdActualProjMasSid);
    }

    /**
     * Removes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas that was removed
     * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas remove(Serializable primaryKey)
        throws NoSuchVwIvldInventoryWdActualProjMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas) session.get(VwIvldInventoryWdActualProjMasImpl.class,
                    primaryKey);

            if (vwIvldInventoryWdActualProjMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwIvldInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwIvldInventoryWdActualProjMas);
        } catch (NoSuchVwIvldInventoryWdActualProjMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwIvldInventoryWdActualProjMas removeImpl(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws SystemException {
        vwIvldInventoryWdActualProjMas = toUnwrappedModel(vwIvldInventoryWdActualProjMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwIvldInventoryWdActualProjMas)) {
                vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas) session.get(VwIvldInventoryWdActualProjMasImpl.class,
                        vwIvldInventoryWdActualProjMas.getPrimaryKeyObj());
            }

            if (vwIvldInventoryWdActualProjMas != null) {
                session.delete(vwIvldInventoryWdActualProjMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwIvldInventoryWdActualProjMas != null) {
            clearCache(vwIvldInventoryWdActualProjMas);
        }

        return vwIvldInventoryWdActualProjMas;
    }

    @Override
    public VwIvldInventoryWdActualProjMas updateImpl(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws SystemException {
        vwIvldInventoryWdActualProjMas = toUnwrappedModel(vwIvldInventoryWdActualProjMas);

        boolean isNew = vwIvldInventoryWdActualProjMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwIvldInventoryWdActualProjMas.isNew()) {
                session.save(vwIvldInventoryWdActualProjMas);

                vwIvldInventoryWdActualProjMas.setNew(false);
            } else {
                session.merge(vwIvldInventoryWdActualProjMas);
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

        EntityCacheUtil.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwIvldInventoryWdActualProjMasImpl.class,
            vwIvldInventoryWdActualProjMas.getPrimaryKey(),
            vwIvldInventoryWdActualProjMas);

        return vwIvldInventoryWdActualProjMas;
    }

    protected VwIvldInventoryWdActualProjMas toUnwrappedModel(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
        if (vwIvldInventoryWdActualProjMas instanceof VwIvldInventoryWdActualProjMasImpl) {
            return vwIvldInventoryWdActualProjMas;
        }

        VwIvldInventoryWdActualProjMasImpl vwIvldInventoryWdActualProjMasImpl = new VwIvldInventoryWdActualProjMasImpl();

        vwIvldInventoryWdActualProjMasImpl.setNew(vwIvldInventoryWdActualProjMas.isNew());
        vwIvldInventoryWdActualProjMasImpl.setPrimaryKey(vwIvldInventoryWdActualProjMas.getPrimaryKey());

        vwIvldInventoryWdActualProjMasImpl.setIvldInventoryWdActualProjMasSid(vwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid());
        vwIvldInventoryWdActualProjMasImpl.setQuantityOnOrder(vwIvldInventoryWdActualProjMas.getQuantityOnOrder());
        vwIvldInventoryWdActualProjMasImpl.setWeek(vwIvldInventoryWdActualProjMas.getWeek());
        vwIvldInventoryWdActualProjMasImpl.setPrice(vwIvldInventoryWdActualProjMas.getPrice());
        vwIvldInventoryWdActualProjMasImpl.setAmountOnHand(vwIvldInventoryWdActualProjMas.getAmountOnHand());
        vwIvldInventoryWdActualProjMasImpl.setIsMaster(vwIvldInventoryWdActualProjMas.getIsMaster());
        vwIvldInventoryWdActualProjMasImpl.setCompanyName(vwIvldInventoryWdActualProjMas.getCompanyName());
        vwIvldInventoryWdActualProjMasImpl.setYear(vwIvldInventoryWdActualProjMas.getYear());
        vwIvldInventoryWdActualProjMasImpl.setItemId(vwIvldInventoryWdActualProjMas.getItemId());
        vwIvldInventoryWdActualProjMasImpl.setModifiedDate(vwIvldInventoryWdActualProjMas.getModifiedDate());
        vwIvldInventoryWdActualProjMasImpl.setOrganizationKey(vwIvldInventoryWdActualProjMas.getOrganizationKey());
        vwIvldInventoryWdActualProjMasImpl.setSource(vwIvldInventoryWdActualProjMas.getSource());
        vwIvldInventoryWdActualProjMasImpl.setCreatedBy(vwIvldInventoryWdActualProjMas.getCreatedBy());
        vwIvldInventoryWdActualProjMasImpl.setCreatedDate(vwIvldInventoryWdActualProjMas.getCreatedDate());
        vwIvldInventoryWdActualProjMasImpl.setDay(vwIvldInventoryWdActualProjMas.getDay());
        vwIvldInventoryWdActualProjMasImpl.setAddChgDelIndicator(vwIvldInventoryWdActualProjMas.getAddChgDelIndicator());
        vwIvldInventoryWdActualProjMasImpl.setUnitsOnHand(vwIvldInventoryWdActualProjMas.getUnitsOnHand());
        vwIvldInventoryWdActualProjMasImpl.setAmountReceived(vwIvldInventoryWdActualProjMas.getAmountReceived());
        vwIvldInventoryWdActualProjMasImpl.setErrorCode(vwIvldInventoryWdActualProjMas.getErrorCode());
        vwIvldInventoryWdActualProjMasImpl.setIntfInsertedDate(vwIvldInventoryWdActualProjMas.getIntfInsertedDate());
        vwIvldInventoryWdActualProjMasImpl.setModifiedBy(vwIvldInventoryWdActualProjMas.getModifiedBy());
        vwIvldInventoryWdActualProjMasImpl.setMonth(vwIvldInventoryWdActualProjMas.getMonth());
        vwIvldInventoryWdActualProjMasImpl.setReprocessedFlag(vwIvldInventoryWdActualProjMas.getReprocessedFlag());
        vwIvldInventoryWdActualProjMasImpl.setAmountWithdrawn(vwIvldInventoryWdActualProjMas.getAmountWithdrawn());
        vwIvldInventoryWdActualProjMasImpl.setQuantityReceived(vwIvldInventoryWdActualProjMas.getQuantityReceived());
        vwIvldInventoryWdActualProjMasImpl.setUnitsWithdrawn(vwIvldInventoryWdActualProjMas.getUnitsWithdrawn());
        vwIvldInventoryWdActualProjMasImpl.setReasonForFailure(vwIvldInventoryWdActualProjMas.getReasonForFailure());
        vwIvldInventoryWdActualProjMasImpl.setCountry(vwIvldInventoryWdActualProjMas.getCountry());
        vwIvldInventoryWdActualProjMasImpl.setCompanyId(vwIvldInventoryWdActualProjMas.getCompanyId());
        vwIvldInventoryWdActualProjMasImpl.setIsForecast(vwIvldInventoryWdActualProjMas.getIsForecast());
        vwIvldInventoryWdActualProjMasImpl.setInventoryWdActualProjMasIntfId(vwIvldInventoryWdActualProjMas.getInventoryWdActualProjMasIntfId());
        vwIvldInventoryWdActualProjMasImpl.setForecastVer(vwIvldInventoryWdActualProjMas.getForecastVer());
        vwIvldInventoryWdActualProjMasImpl.setBatchId(vwIvldInventoryWdActualProjMas.getBatchId());
        vwIvldInventoryWdActualProjMasImpl.setItemName(vwIvldInventoryWdActualProjMas.getItemName());
        vwIvldInventoryWdActualProjMasImpl.setErrorField(vwIvldInventoryWdActualProjMas.getErrorField());
        vwIvldInventoryWdActualProjMasImpl.setAmountOnOrder(vwIvldInventoryWdActualProjMas.getAmountOnOrder());
        vwIvldInventoryWdActualProjMasImpl.setForecastName(vwIvldInventoryWdActualProjMas.getForecastName());
        vwIvldInventoryWdActualProjMasImpl.setCheckRecord(vwIvldInventoryWdActualProjMas.isCheckRecord());

        return vwIvldInventoryWdActualProjMasImpl;
    }

    /**
     * Returns the vw ivld inventory wd actual proj mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas
     * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchVwIvldInventoryWdActualProjMasException, SystemException {
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

        if (vwIvldInventoryWdActualProjMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwIvldInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwIvldInventoryWdActualProjMas;
    }

    /**
     * Returns the vw ivld inventory wd actual proj mas with the primary key or throws a {@link com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException} if it could not be found.
     *
     * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas
     * @throws com.stpl.app.NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas findByPrimaryKey(
        int ivldInventoryWdActualProjMasSid)
        throws NoSuchVwIvldInventoryWdActualProjMasException, SystemException {
        return findByPrimaryKey((Serializable) ivldInventoryWdActualProjMasSid);
    }

    /**
     * Returns the vw ivld inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas, or <code>null</code> if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas) EntityCacheUtil.getResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                VwIvldInventoryWdActualProjMasImpl.class, primaryKey);

        if (vwIvldInventoryWdActualProjMas == _nullVwIvldInventoryWdActualProjMas) {
            return null;
        }

        if (vwIvldInventoryWdActualProjMas == null) {
            Session session = null;

            try {
                session = openSession();

                vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas) session.get(VwIvldInventoryWdActualProjMasImpl.class,
                        primaryKey);

                if (vwIvldInventoryWdActualProjMas != null) {
                    cacheResult(vwIvldInventoryWdActualProjMas);
                } else {
                    EntityCacheUtil.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        VwIvldInventoryWdActualProjMasImpl.class, primaryKey,
                        _nullVwIvldInventoryWdActualProjMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                    VwIvldInventoryWdActualProjMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwIvldInventoryWdActualProjMas;
    }

    /**
     * Returns the vw ivld inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
     * @return the vw ivld inventory wd actual proj mas, or <code>null</code> if a vw ivld inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwIvldInventoryWdActualProjMas fetchByPrimaryKey(
        int ivldInventoryWdActualProjMasSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldInventoryWdActualProjMasSid);
    }

    /**
     * Returns all the vw ivld inventory wd actual proj mases.
     *
     * @return the vw ivld inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldInventoryWdActualProjMas> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw ivld inventory wd actual proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
     * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
     * @return the range of vw ivld inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldInventoryWdActualProjMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw ivld inventory wd actual proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
     * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw ivld inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwIvldInventoryWdActualProjMas> findAll(int start, int end,
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

        List<VwIvldInventoryWdActualProjMas> list = (List<VwIvldInventoryWdActualProjMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS;

                if (pagination) {
                    sql = sql.concat(VwIvldInventoryWdActualProjMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwIvldInventoryWdActualProjMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwIvldInventoryWdActualProjMas>(list);
                } else {
                    list = (List<VwIvldInventoryWdActualProjMas>) QueryUtil.list(q,
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
     * Removes all the vw ivld inventory wd actual proj mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : findAll()) {
            remove(vwIvldInventoryWdActualProjMas);
        }
    }

    /**
     * Returns the number of vw ivld inventory wd actual proj mases.
     *
     * @return the number of vw ivld inventory wd actual proj mases
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

                Query q = session.createQuery(_SQL_COUNT_VWIVLDINVENTORYWDACTUALPROJMAS);

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
     * Initializes the vw ivld inventory wd actual proj mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwIvldInventoryWdActualProjMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwIvldInventoryWdActualProjMas>> listenersList =
                    new ArrayList<ModelListener<VwIvldInventoryWdActualProjMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwIvldInventoryWdActualProjMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwIvldInventoryWdActualProjMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
