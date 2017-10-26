package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwItemMasterException;
import com.stpl.app.parttwo.model.VwItemMaster;
import com.stpl.app.parttwo.model.impl.VwItemMasterImpl;
import com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.VwItemMasterPersistence;

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
 * The persistence implementation for the vw item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemMasterPersistence
 * @see VwItemMasterUtil
 * @generated
 */
public class VwItemMasterPersistenceImpl extends BasePersistenceImpl<VwItemMaster>
    implements VwItemMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwItemMasterUtil} to access the vw item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwItemMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterModelImpl.FINDER_CACHE_ENABLED, VwItemMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterModelImpl.FINDER_CACHE_ENABLED, VwItemMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWITEMMASTER = "SELECT vwItemMaster FROM VwItemMaster vwItemMaster";
    private static final String _SQL_COUNT_VWITEMMASTER = "SELECT COUNT(vwItemMaster) FROM VwItemMaster vwItemMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwItemMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwItemMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwItemMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemStatus", "itemDesc", "acquiredAmp",
                "authorizedGenericStartDate", "newFormulationStartDate",
                "marketTerminationDate", "obraBamp", "brand", "manufacturerNo",
                "modifiedDate", "therapeuticClass", "organizationKey",
                "acquiredBamp", "pediatricExclusiveEndDate", "source",
                "newFormulation", "addChgDelIndicator", "divestitureDate",
                "shelfLife", "primaryUom", "newFormulationEndDate", "modifiedBy",
                "packageSizeCode", "secondaryUom", "udc6", "udc5",
                "discontinuationDate", "udc4", "udc1", "udc2",
                "packageSizeIntroDate", "udc3", "itemEndDate", "manufacturerId",
                "itemFamilyId", "strength", "itemCategory", "upps",
                "shelfLifeType", "pediatricExclusiveIndicator",
                "itemTypeIndication", "acquisitionDate",
                "clottingFactorIndicator", "form", "itemName",
                "manufacturerName", "pediatricExclusiveStartDate",
                "firstSaleDate", "itemMasterSid", "itemType", "itemId",
                "baselineAmp", "dosesPerUnit", "dualPricingIndicator", "baseCpi",
                "createdDate", "createdBy", "itemStartDate", "authorizedGeneric",
                "ndc9", "authorizedGenericEndDate", "itemNo", "packageSize",
                "ndc8", "itemClass", "labelerCode", "displayBrand",
                "clottingFactorEndDate", "dra", "brandId", "baseCpiPeriod",
                "newFormulationIndicator", "lastLotExpirationDate", "batchId",
                "itemCode", "clottingFactorStartDate",
                "nonFederalExpirationDate"
            });
    private static VwItemMaster _nullVwItemMaster = new VwItemMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwItemMaster> toCacheModel() {
                return _nullVwItemMasterCacheModel;
            }
        };

    private static CacheModel<VwItemMaster> _nullVwItemMasterCacheModel = new CacheModel<VwItemMaster>() {
            @Override
            public VwItemMaster toEntityModel() {
                return _nullVwItemMaster;
            }
        };

    public VwItemMasterPersistenceImpl() {
        setModelClass(VwItemMaster.class);
    }

    /**
     * Caches the vw item master in the entity cache if it is enabled.
     *
     * @param vwItemMaster the vw item master
     */
    @Override
    public void cacheResult(VwItemMaster vwItemMaster) {
        EntityCacheUtil.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterImpl.class, vwItemMaster.getPrimaryKey(), vwItemMaster);

        vwItemMaster.resetOriginalValues();
    }

    /**
     * Caches the vw item masters in the entity cache if it is enabled.
     *
     * @param vwItemMasters the vw item masters
     */
    @Override
    public void cacheResult(List<VwItemMaster> vwItemMasters) {
        for (VwItemMaster vwItemMaster : vwItemMasters) {
            if (EntityCacheUtil.getResult(
                        VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemMasterImpl.class, vwItemMaster.getPrimaryKey()) == null) {
                cacheResult(vwItemMaster);
            } else {
                vwItemMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw item masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwItemMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwItemMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw item master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwItemMaster vwItemMaster) {
        EntityCacheUtil.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterImpl.class, vwItemMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwItemMaster> vwItemMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwItemMaster vwItemMaster : vwItemMasters) {
            EntityCacheUtil.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwItemMasterImpl.class, vwItemMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw item master with the primary key. Does not add the vw item master to the database.
     *
     * @param itemMasterSid the primary key for the new vw item master
     * @return the new vw item master
     */
    @Override
    public VwItemMaster create(int itemMasterSid) {
        VwItemMaster vwItemMaster = new VwItemMasterImpl();

        vwItemMaster.setNew(true);
        vwItemMaster.setPrimaryKey(itemMasterSid);

        return vwItemMaster;
    }

    /**
     * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemMasterSid the primary key of the vw item master
     * @return the vw item master that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster remove(int itemMasterSid)
        throws NoSuchVwItemMasterException, SystemException {
        return remove((Serializable) itemMasterSid);
    }

    /**
     * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw item master
     * @return the vw item master that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster remove(Serializable primaryKey)
        throws NoSuchVwItemMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwItemMaster vwItemMaster = (VwItemMaster) session.get(VwItemMasterImpl.class,
                    primaryKey);

            if (vwItemMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwItemMaster);
        } catch (NoSuchVwItemMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwItemMaster removeImpl(VwItemMaster vwItemMaster)
        throws SystemException {
        vwItemMaster = toUnwrappedModel(vwItemMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwItemMaster)) {
                vwItemMaster = (VwItemMaster) session.get(VwItemMasterImpl.class,
                        vwItemMaster.getPrimaryKeyObj());
            }

            if (vwItemMaster != null) {
                session.delete(vwItemMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwItemMaster != null) {
            clearCache(vwItemMaster);
        }

        return vwItemMaster;
    }

    @Override
    public VwItemMaster updateImpl(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws SystemException {
        vwItemMaster = toUnwrappedModel(vwItemMaster);

        boolean isNew = vwItemMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwItemMaster.isNew()) {
                session.save(vwItemMaster);

                vwItemMaster.setNew(false);
            } else {
                session.merge(vwItemMaster);
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

        EntityCacheUtil.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwItemMasterImpl.class, vwItemMaster.getPrimaryKey(), vwItemMaster);

        return vwItemMaster;
    }

    protected VwItemMaster toUnwrappedModel(VwItemMaster vwItemMaster) {
        if (vwItemMaster instanceof VwItemMasterImpl) {
            return vwItemMaster;
        }

        VwItemMasterImpl vwItemMasterImpl = new VwItemMasterImpl();

        vwItemMasterImpl.setNew(vwItemMaster.isNew());
        vwItemMasterImpl.setPrimaryKey(vwItemMaster.getPrimaryKey());

        vwItemMasterImpl.setItemStatus(vwItemMaster.getItemStatus());
        vwItemMasterImpl.setItemDesc(vwItemMaster.getItemDesc());
        vwItemMasterImpl.setAcquiredAmp(vwItemMaster.getAcquiredAmp());
        vwItemMasterImpl.setAuthorizedGenericStartDate(vwItemMaster.getAuthorizedGenericStartDate());
        vwItemMasterImpl.setNewFormulationStartDate(vwItemMaster.getNewFormulationStartDate());
        vwItemMasterImpl.setMarketTerminationDate(vwItemMaster.getMarketTerminationDate());
        vwItemMasterImpl.setObraBamp(vwItemMaster.getObraBamp());
        vwItemMasterImpl.setBrand(vwItemMaster.getBrand());
        vwItemMasterImpl.setManufacturerNo(vwItemMaster.getManufacturerNo());
        vwItemMasterImpl.setModifiedDate(vwItemMaster.getModifiedDate());
        vwItemMasterImpl.setTherapeuticClass(vwItemMaster.getTherapeuticClass());
        vwItemMasterImpl.setOrganizationKey(vwItemMaster.getOrganizationKey());
        vwItemMasterImpl.setAcquiredBamp(vwItemMaster.getAcquiredBamp());
        vwItemMasterImpl.setPediatricExclusiveEndDate(vwItemMaster.getPediatricExclusiveEndDate());
        vwItemMasterImpl.setSource(vwItemMaster.getSource());
        vwItemMasterImpl.setNewFormulation(vwItemMaster.getNewFormulation());
        vwItemMasterImpl.setAddChgDelIndicator(vwItemMaster.getAddChgDelIndicator());
        vwItemMasterImpl.setDivestitureDate(vwItemMaster.getDivestitureDate());
        vwItemMasterImpl.setShelfLife(vwItemMaster.getShelfLife());
        vwItemMasterImpl.setPrimaryUom(vwItemMaster.getPrimaryUom());
        vwItemMasterImpl.setNewFormulationEndDate(vwItemMaster.getNewFormulationEndDate());
        vwItemMasterImpl.setModifiedBy(vwItemMaster.getModifiedBy());
        vwItemMasterImpl.setPackageSizeCode(vwItemMaster.getPackageSizeCode());
        vwItemMasterImpl.setSecondaryUom(vwItemMaster.getSecondaryUom());
        vwItemMasterImpl.setUdc6(vwItemMaster.getUdc6());
        vwItemMasterImpl.setUdc5(vwItemMaster.getUdc5());
        vwItemMasterImpl.setDiscontinuationDate(vwItemMaster.getDiscontinuationDate());
        vwItemMasterImpl.setUdc4(vwItemMaster.getUdc4());
        vwItemMasterImpl.setUdc1(vwItemMaster.getUdc1());
        vwItemMasterImpl.setUdc2(vwItemMaster.getUdc2());
        vwItemMasterImpl.setPackageSizeIntroDate(vwItemMaster.getPackageSizeIntroDate());
        vwItemMasterImpl.setUdc3(vwItemMaster.getUdc3());
        vwItemMasterImpl.setItemEndDate(vwItemMaster.getItemEndDate());
        vwItemMasterImpl.setManufacturerId(vwItemMaster.getManufacturerId());
        vwItemMasterImpl.setItemFamilyId(vwItemMaster.getItemFamilyId());
        vwItemMasterImpl.setStrength(vwItemMaster.getStrength());
        vwItemMasterImpl.setItemCategory(vwItemMaster.getItemCategory());
        vwItemMasterImpl.setUpps(vwItemMaster.getUpps());
        vwItemMasterImpl.setShelfLifeType(vwItemMaster.getShelfLifeType());
        vwItemMasterImpl.setPediatricExclusiveIndicator(vwItemMaster.getPediatricExclusiveIndicator());
        vwItemMasterImpl.setItemTypeIndication(vwItemMaster.getItemTypeIndication());
        vwItemMasterImpl.setAcquisitionDate(vwItemMaster.getAcquisitionDate());
        vwItemMasterImpl.setClottingFactorIndicator(vwItemMaster.getClottingFactorIndicator());
        vwItemMasterImpl.setForm(vwItemMaster.getForm());
        vwItemMasterImpl.setItemName(vwItemMaster.getItemName());
        vwItemMasterImpl.setManufacturerName(vwItemMaster.getManufacturerName());
        vwItemMasterImpl.setPediatricExclusiveStartDate(vwItemMaster.getPediatricExclusiveStartDate());
        vwItemMasterImpl.setFirstSaleDate(vwItemMaster.getFirstSaleDate());
        vwItemMasterImpl.setItemMasterSid(vwItemMaster.getItemMasterSid());
        vwItemMasterImpl.setItemType(vwItemMaster.getItemType());
        vwItemMasterImpl.setItemId(vwItemMaster.getItemId());
        vwItemMasterImpl.setBaselineAmp(vwItemMaster.getBaselineAmp());
        vwItemMasterImpl.setDosesPerUnit(vwItemMaster.getDosesPerUnit());
        vwItemMasterImpl.setDualPricingIndicator(vwItemMaster.getDualPricingIndicator());
        vwItemMasterImpl.setBaseCpi(vwItemMaster.getBaseCpi());
        vwItemMasterImpl.setCreatedDate(vwItemMaster.getCreatedDate());
        vwItemMasterImpl.setCreatedBy(vwItemMaster.getCreatedBy());
        vwItemMasterImpl.setItemStartDate(vwItemMaster.getItemStartDate());
        vwItemMasterImpl.setAuthorizedGeneric(vwItemMaster.getAuthorizedGeneric());
        vwItemMasterImpl.setNdc9(vwItemMaster.getNdc9());
        vwItemMasterImpl.setAuthorizedGenericEndDate(vwItemMaster.getAuthorizedGenericEndDate());
        vwItemMasterImpl.setItemNo(vwItemMaster.getItemNo());
        vwItemMasterImpl.setPackageSize(vwItemMaster.getPackageSize());
        vwItemMasterImpl.setNdc8(vwItemMaster.getNdc8());
        vwItemMasterImpl.setItemClass(vwItemMaster.getItemClass());
        vwItemMasterImpl.setLabelerCode(vwItemMaster.getLabelerCode());
        vwItemMasterImpl.setDisplayBrand(vwItemMaster.getDisplayBrand());
        vwItemMasterImpl.setClottingFactorEndDate(vwItemMaster.getClottingFactorEndDate());
        vwItemMasterImpl.setDra(vwItemMaster.getDra());
        vwItemMasterImpl.setBrandId(vwItemMaster.getBrandId());
        vwItemMasterImpl.setBaseCpiPeriod(vwItemMaster.getBaseCpiPeriod());
        vwItemMasterImpl.setNewFormulationIndicator(vwItemMaster.getNewFormulationIndicator());
        vwItemMasterImpl.setLastLotExpirationDate(vwItemMaster.getLastLotExpirationDate());
        vwItemMasterImpl.setBatchId(vwItemMaster.getBatchId());
        vwItemMasterImpl.setItemCode(vwItemMaster.getItemCode());
        vwItemMasterImpl.setClottingFactorStartDate(vwItemMaster.getClottingFactorStartDate());
        vwItemMasterImpl.setNonFederalExpirationDate(vwItemMaster.getNonFederalExpirationDate());

        return vwItemMasterImpl;
    }

    /**
     * Returns the vw item master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw item master
     * @return the vw item master
     * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwItemMasterException, SystemException {
        VwItemMaster vwItemMaster = fetchByPrimaryKey(primaryKey);

        if (vwItemMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwItemMaster;
    }

    /**
     * Returns the vw item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemMasterException} if it could not be found.
     *
     * @param itemMasterSid the primary key of the vw item master
     * @return the vw item master
     * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster findByPrimaryKey(int itemMasterSid)
        throws NoSuchVwItemMasterException, SystemException {
        return findByPrimaryKey((Serializable) itemMasterSid);
    }

    /**
     * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw item master
     * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwItemMaster vwItemMaster = (VwItemMaster) EntityCacheUtil.getResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwItemMasterImpl.class, primaryKey);

        if (vwItemMaster == _nullVwItemMaster) {
            return null;
        }

        if (vwItemMaster == null) {
            Session session = null;

            try {
                session = openSession();

                vwItemMaster = (VwItemMaster) session.get(VwItemMasterImpl.class,
                        primaryKey);

                if (vwItemMaster != null) {
                    cacheResult(vwItemMaster);
                } else {
                    EntityCacheUtil.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemMasterImpl.class, primaryKey, _nullVwItemMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                    VwItemMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwItemMaster;
    }

    /**
     * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemMasterSid the primary key of the vw item master
     * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemMaster fetchByPrimaryKey(int itemMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemMasterSid);
    }

    /**
     * Returns all the vw item masters.
     *
     * @return the vw item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw item masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item masters
     * @param end the upper bound of the range of vw item masters (not inclusive)
     * @return the range of vw item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw item masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item masters
     * @param end the upper bound of the range of vw item masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemMaster> findAll(int start, int end,
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

        List<VwItemMaster> list = (List<VwItemMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWITEMMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWITEMMASTER;

                if (pagination) {
                    sql = sql.concat(VwItemMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwItemMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwItemMaster>(list);
                } else {
                    list = (List<VwItemMaster>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the vw item masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwItemMaster vwItemMaster : findAll()) {
            remove(vwItemMaster);
        }
    }

    /**
     * Returns the number of vw item masters.
     *
     * @return the number of vw item masters
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

                Query q = session.createQuery(_SQL_COUNT_VWITEMMASTER);

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
     * Initializes the vw item master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwItemMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwItemMaster>> listenersList = new ArrayList<ModelListener<VwItemMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwItemMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwItemMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
