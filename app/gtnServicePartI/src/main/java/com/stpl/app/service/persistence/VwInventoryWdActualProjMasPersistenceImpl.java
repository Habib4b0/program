package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchVwInventoryWdActualProjMasException;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.impl.VwInventoryWdActualProjMasImpl;
import com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl;
import com.stpl.app.service.persistence.VwInventoryWdActualProjMasPersistence;

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
 * The persistence implementation for the vw inventory wd actual proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMasPersistence
 * @see VwInventoryWdActualProjMasUtil
 * @generated
 */
public class VwInventoryWdActualProjMasPersistenceImpl
    extends BasePersistenceImpl<VwInventoryWdActualProjMas>
    implements VwInventoryWdActualProjMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwInventoryWdActualProjMasUtil} to access the vw inventory wd actual proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwInventoryWdActualProjMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            VwInventoryWdActualProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            VwInventoryWdActualProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_VWINVENTORYWDACTUALPROJMAS = "SELECT vwInventoryWdActualProjMas FROM VwInventoryWdActualProjMas vwInventoryWdActualProjMas";
    private static final String _SQL_COUNT_VWINVENTORYWDACTUALPROJMAS = "SELECT COUNT(vwInventoryWdActualProjMas) FROM VwInventoryWdActualProjMas vwInventoryWdActualProjMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwInventoryWdActualProjMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwInventoryWdActualProjMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwInventoryWdActualProjMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "quantityOnOrder", "week", "price", "amountOnHand", "isMaster",
                "companyName", "year", "itemId", "modifiedDate",
                "organizationKey", "inventoryWdActualProjMasSid", "source",
                "createdBy", "createdDate", "day", "addChgDelIndicator",
                "unitsOnHand", "amountReceived", "modifiedBy", "month",
                "amountWithdrawn", "quantityReceived", "unitsWithdrawn",
                "country", "companyId", "isForecast", "forecastVer", "batchId",
                "itemName", "amountOnOrder", "forecastName"
            });
    private static VwInventoryWdActualProjMas _nullVwInventoryWdActualProjMas = new VwInventoryWdActualProjMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwInventoryWdActualProjMas> toCacheModel() {
                return _nullVwInventoryWdActualProjMasCacheModel;
            }
        };

    private static CacheModel<VwInventoryWdActualProjMas> _nullVwInventoryWdActualProjMasCacheModel =
        new CacheModel<VwInventoryWdActualProjMas>() {
            @Override
            public VwInventoryWdActualProjMas toEntityModel() {
                return _nullVwInventoryWdActualProjMas;
            }
        };

    public VwInventoryWdActualProjMasPersistenceImpl() {
        setModelClass(VwInventoryWdActualProjMas.class);
    }

    /**
     * Caches the vw inventory wd actual proj mas in the entity cache if it is enabled.
     *
     * @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
     */
    @Override
    public void cacheResult(
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        EntityCacheUtil.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasImpl.class,
            vwInventoryWdActualProjMas.getPrimaryKey(),
            vwInventoryWdActualProjMas);

        vwInventoryWdActualProjMas.resetOriginalValues();
    }

    /**
     * Caches the vw inventory wd actual proj mases in the entity cache if it is enabled.
     *
     * @param vwInventoryWdActualProjMases the vw inventory wd actual proj mases
     */
    @Override
    public void cacheResult(
        List<VwInventoryWdActualProjMas> vwInventoryWdActualProjMases) {
        for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : vwInventoryWdActualProjMases) {
            if (EntityCacheUtil.getResult(
                        VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        VwInventoryWdActualProjMasImpl.class,
                        vwInventoryWdActualProjMas.getPrimaryKey()) == null) {
                cacheResult(vwInventoryWdActualProjMas);
            } else {
                vwInventoryWdActualProjMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw inventory wd actual proj mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwInventoryWdActualProjMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwInventoryWdActualProjMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw inventory wd actual proj mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        EntityCacheUtil.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasImpl.class,
            vwInventoryWdActualProjMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwInventoryWdActualProjMas> vwInventoryWdActualProjMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : vwInventoryWdActualProjMases) {
            EntityCacheUtil.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                VwInventoryWdActualProjMasImpl.class,
                vwInventoryWdActualProjMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw inventory wd actual proj mas with the primary key. Does not add the vw inventory wd actual proj mas to the database.
     *
     * @param inventoryWdActualProjMasSid the primary key for the new vw inventory wd actual proj mas
     * @return the new vw inventory wd actual proj mas
     */
    @Override
    public VwInventoryWdActualProjMas create(int inventoryWdActualProjMasSid) {
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas = new VwInventoryWdActualProjMasImpl();

        vwInventoryWdActualProjMas.setNew(true);
        vwInventoryWdActualProjMas.setPrimaryKey(inventoryWdActualProjMasSid);

        return vwInventoryWdActualProjMas;
    }

    /**
     * Removes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas that was removed
     * @throws com.stpl.app.NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas remove(int inventoryWdActualProjMasSid)
        throws NoSuchVwInventoryWdActualProjMasException, SystemException {
        return remove((Serializable) inventoryWdActualProjMasSid);
    }

    /**
     * Removes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas that was removed
     * @throws com.stpl.app.NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas remove(Serializable primaryKey)
        throws NoSuchVwInventoryWdActualProjMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwInventoryWdActualProjMas vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas) session.get(VwInventoryWdActualProjMasImpl.class,
                    primaryKey);

            if (vwInventoryWdActualProjMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwInventoryWdActualProjMas);
        } catch (NoSuchVwInventoryWdActualProjMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwInventoryWdActualProjMas removeImpl(
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas)
        throws SystemException {
        vwInventoryWdActualProjMas = toUnwrappedModel(vwInventoryWdActualProjMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwInventoryWdActualProjMas)) {
                vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas) session.get(VwInventoryWdActualProjMasImpl.class,
                        vwInventoryWdActualProjMas.getPrimaryKeyObj());
            }

            if (vwInventoryWdActualProjMas != null) {
                session.delete(vwInventoryWdActualProjMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwInventoryWdActualProjMas != null) {
            clearCache(vwInventoryWdActualProjMas);
        }

        return vwInventoryWdActualProjMas;
    }

    @Override
    public VwInventoryWdActualProjMas updateImpl(
        com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas)
        throws SystemException {
        vwInventoryWdActualProjMas = toUnwrappedModel(vwInventoryWdActualProjMas);

        boolean isNew = vwInventoryWdActualProjMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwInventoryWdActualProjMas.isNew()) {
                session.save(vwInventoryWdActualProjMas);

                vwInventoryWdActualProjMas.setNew(false);
            } else {
                session.merge(vwInventoryWdActualProjMas);
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

        EntityCacheUtil.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
            VwInventoryWdActualProjMasImpl.class,
            vwInventoryWdActualProjMas.getPrimaryKey(),
            vwInventoryWdActualProjMas);

        return vwInventoryWdActualProjMas;
    }

    protected VwInventoryWdActualProjMas toUnwrappedModel(
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        if (vwInventoryWdActualProjMas instanceof VwInventoryWdActualProjMasImpl) {
            return vwInventoryWdActualProjMas;
        }

        VwInventoryWdActualProjMasImpl vwInventoryWdActualProjMasImpl = new VwInventoryWdActualProjMasImpl();

        vwInventoryWdActualProjMasImpl.setNew(vwInventoryWdActualProjMas.isNew());
        vwInventoryWdActualProjMasImpl.setPrimaryKey(vwInventoryWdActualProjMas.getPrimaryKey());

        vwInventoryWdActualProjMasImpl.setQuantityOnOrder(vwInventoryWdActualProjMas.getQuantityOnOrder());
        vwInventoryWdActualProjMasImpl.setWeek(vwInventoryWdActualProjMas.getWeek());
        vwInventoryWdActualProjMasImpl.setPrice(vwInventoryWdActualProjMas.getPrice());
        vwInventoryWdActualProjMasImpl.setAmountOnHand(vwInventoryWdActualProjMas.getAmountOnHand());
        vwInventoryWdActualProjMasImpl.setIsMaster(vwInventoryWdActualProjMas.getIsMaster());
        vwInventoryWdActualProjMasImpl.setCompanyName(vwInventoryWdActualProjMas.getCompanyName());
        vwInventoryWdActualProjMasImpl.setYear(vwInventoryWdActualProjMas.getYear());
        vwInventoryWdActualProjMasImpl.setItemId(vwInventoryWdActualProjMas.getItemId());
        vwInventoryWdActualProjMasImpl.setModifiedDate(vwInventoryWdActualProjMas.getModifiedDate());
        vwInventoryWdActualProjMasImpl.setOrganizationKey(vwInventoryWdActualProjMas.getOrganizationKey());
        vwInventoryWdActualProjMasImpl.setInventoryWdActualProjMasSid(vwInventoryWdActualProjMas.getInventoryWdActualProjMasSid());
        vwInventoryWdActualProjMasImpl.setSource(vwInventoryWdActualProjMas.getSource());
        vwInventoryWdActualProjMasImpl.setCreatedBy(vwInventoryWdActualProjMas.getCreatedBy());
        vwInventoryWdActualProjMasImpl.setCreatedDate(vwInventoryWdActualProjMas.getCreatedDate());
        vwInventoryWdActualProjMasImpl.setDay(vwInventoryWdActualProjMas.getDay());
        vwInventoryWdActualProjMasImpl.setAddChgDelIndicator(vwInventoryWdActualProjMas.getAddChgDelIndicator());
        vwInventoryWdActualProjMasImpl.setUnitsOnHand(vwInventoryWdActualProjMas.getUnitsOnHand());
        vwInventoryWdActualProjMasImpl.setAmountReceived(vwInventoryWdActualProjMas.getAmountReceived());
        vwInventoryWdActualProjMasImpl.setModifiedBy(vwInventoryWdActualProjMas.getModifiedBy());
        vwInventoryWdActualProjMasImpl.setMonth(vwInventoryWdActualProjMas.getMonth());
        vwInventoryWdActualProjMasImpl.setAmountWithdrawn(vwInventoryWdActualProjMas.getAmountWithdrawn());
        vwInventoryWdActualProjMasImpl.setQuantityReceived(vwInventoryWdActualProjMas.getQuantityReceived());
        vwInventoryWdActualProjMasImpl.setUnitsWithdrawn(vwInventoryWdActualProjMas.getUnitsWithdrawn());
        vwInventoryWdActualProjMasImpl.setCountry(vwInventoryWdActualProjMas.getCountry());
        vwInventoryWdActualProjMasImpl.setCompanyId(vwInventoryWdActualProjMas.getCompanyId());
        vwInventoryWdActualProjMasImpl.setIsForecast(vwInventoryWdActualProjMas.getIsForecast());
        vwInventoryWdActualProjMasImpl.setForecastVer(vwInventoryWdActualProjMas.getForecastVer());
        vwInventoryWdActualProjMasImpl.setBatchId(vwInventoryWdActualProjMas.getBatchId());
        vwInventoryWdActualProjMasImpl.setItemName(vwInventoryWdActualProjMas.getItemName());
        vwInventoryWdActualProjMasImpl.setAmountOnOrder(vwInventoryWdActualProjMas.getAmountOnOrder());
        vwInventoryWdActualProjMasImpl.setForecastName(vwInventoryWdActualProjMas.getForecastName());

        return vwInventoryWdActualProjMasImpl;
    }

    /**
     * Returns the vw inventory wd actual proj mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas
     * @throws com.stpl.app.NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwInventoryWdActualProjMasException, SystemException {
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

        if (vwInventoryWdActualProjMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwInventoryWdActualProjMas;
    }

    /**
     * Returns the vw inventory wd actual proj mas with the primary key or throws a {@link com.stpl.app.NoSuchVwInventoryWdActualProjMasException} if it could not be found.
     *
     * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas
     * @throws com.stpl.app.NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas findByPrimaryKey(
        int inventoryWdActualProjMasSid)
        throws NoSuchVwInventoryWdActualProjMasException, SystemException {
        return findByPrimaryKey((Serializable) inventoryWdActualProjMasSid);
    }

    /**
     * Returns the vw inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas, or <code>null</code> if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwInventoryWdActualProjMas vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas) EntityCacheUtil.getResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                VwInventoryWdActualProjMasImpl.class, primaryKey);

        if (vwInventoryWdActualProjMas == _nullVwInventoryWdActualProjMas) {
            return null;
        }

        if (vwInventoryWdActualProjMas == null) {
            Session session = null;

            try {
                session = openSession();

                vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas) session.get(VwInventoryWdActualProjMasImpl.class,
                        primaryKey);

                if (vwInventoryWdActualProjMas != null) {
                    cacheResult(vwInventoryWdActualProjMas);
                } else {
                    EntityCacheUtil.putResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        VwInventoryWdActualProjMasImpl.class, primaryKey,
                        _nullVwInventoryWdActualProjMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
                    VwInventoryWdActualProjMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwInventoryWdActualProjMas;
    }

    /**
     * Returns the vw inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
     * @return the vw inventory wd actual proj mas, or <code>null</code> if a vw inventory wd actual proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwInventoryWdActualProjMas fetchByPrimaryKey(
        int inventoryWdActualProjMasSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) inventoryWdActualProjMasSid);
    }

    /**
     * Returns all the vw inventory wd actual proj mases.
     *
     * @return the vw inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwInventoryWdActualProjMas> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw inventory wd actual proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw inventory wd actual proj mases
     * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
     * @return the range of vw inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwInventoryWdActualProjMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw inventory wd actual proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw inventory wd actual proj mases
     * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw inventory wd actual proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwInventoryWdActualProjMas> findAll(int start, int end,
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

        List<VwInventoryWdActualProjMas> list = (List<VwInventoryWdActualProjMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWINVENTORYWDACTUALPROJMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWINVENTORYWDACTUALPROJMAS;

                if (pagination) {
                    sql = sql.concat(VwInventoryWdActualProjMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwInventoryWdActualProjMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwInventoryWdActualProjMas>(list);
                } else {
                    list = (List<VwInventoryWdActualProjMas>) QueryUtil.list(q,
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
     * Removes all the vw inventory wd actual proj mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwInventoryWdActualProjMas vwInventoryWdActualProjMas : findAll()) {
            remove(vwInventoryWdActualProjMas);
        }
    }

    /**
     * Returns the number of vw inventory wd actual proj mases.
     *
     * @return the number of vw inventory wd actual proj mases
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

                Query q = session.createQuery(_SQL_COUNT_VWINVENTORYWDACTUALPROJMAS);

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
     * Initializes the vw inventory wd actual proj mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.VwInventoryWdActualProjMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwInventoryWdActualProjMas>> listenersList = new ArrayList<ModelListener<VwInventoryWdActualProjMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwInventoryWdActualProjMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwInventoryWdActualProjMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
