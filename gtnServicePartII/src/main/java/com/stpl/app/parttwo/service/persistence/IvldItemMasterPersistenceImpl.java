package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldItemMasterException;
import com.stpl.app.parttwo.model.IvldItemMaster;
import com.stpl.app.parttwo.model.impl.IvldItemMasterImpl;
import com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemMasterPersistence;

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
 * The persistence implementation for the ivld item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemMasterPersistence
 * @see IvldItemMasterUtil
 * @generated
 */
public class IvldItemMasterPersistenceImpl extends BasePersistenceImpl<IvldItemMaster>
    implements IvldItemMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldItemMasterUtil} to access the ivld item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldItemMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldItemMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldItemMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDITEMMASTER = "SELECT ivldItemMaster FROM IvldItemMaster ivldItemMaster";
    private static final String _SQL_COUNT_IVLDITEMMASTER = "SELECT COUNT(ivldItemMaster) FROM IvldItemMaster ivldItemMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldItemMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemNo", "udc6", "createdDate", "newFormulationIndicator",
                "udc5", "newFormulationEndDate", "udc4",
                "clottingFactorStartDate", "secondaryUom", "itemDesc",
                "authorizedGenericEndDate", "manufacturerName", "itemName",
                "reprocessedFlag", "status", "baseCpi", "baselineAmp",
                "authorizedGeneric", "therapeuticClass", "itemFamilyId",
                "pediatricExclusiveStartDate", "createdBy", "primaryUom", "ndc9",
                "itemId", "lastLotExpirationDate", "errorField", "itemCode",
                "strength", "modifiedDate", "brand", "ndc8", "labelerCode",
                "udc3", "source", "udc2", "addChgDelIndicator", "udc1",
                "acquiredAmp", "discontinuationDate", "itemMasterIntfid",
                "intfInsertedDate", "divestitureDate", "modifiedBy",
                "baseCpiPeriod", "clottingFactorEndDate", "dosesPerUnit",
                "manufacturerId", "clottingFactorIndicator", "batchId",
                "acquisitionDate", "dualPricingIndicator",
                "nonFederalExpirationDate", "errorCode", "newFormulation",
                "obraBamp", "brandId", "itemStatus",
                "authorizedGenericStartDate", "newFormulationStartDate",
                "itemCategory", "itemEndDate", "itemType",
                "pediatricExclusiveEndDate", "organizationKey", "firstSaleDate",
                "shelfLifeType", "itemStartDate", "itemTypeIndication",
                "acquiredBamp", "form", "itemClass", "manufacturerNo",
                "pediatricExclusiveIndicator", "packageSizeCode", "displayBrand",
                "dra", "reasonForFailure", "packageSizeIntroDate", "upps",
                "ivldItemMasterSid", "packageSize", "shelfLife",
                "marketTerminationDate", "checkRecord"
            });
    private static IvldItemMaster _nullIvldItemMaster = new IvldItemMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldItemMaster> toCacheModel() {
                return _nullIvldItemMasterCacheModel;
            }
        };

    private static CacheModel<IvldItemMaster> _nullIvldItemMasterCacheModel = new CacheModel<IvldItemMaster>() {
            @Override
            public IvldItemMaster toEntityModel() {
                return _nullIvldItemMaster;
            }
        };

    public IvldItemMasterPersistenceImpl() {
        setModelClass(IvldItemMaster.class);
    }

    /**
     * Caches the ivld item master in the entity cache if it is enabled.
     *
     * @param ivldItemMaster the ivld item master
     */
    @Override
    public void cacheResult(IvldItemMaster ivldItemMaster) {
        EntityCacheUtil.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey(),
            ivldItemMaster);

        ivldItemMaster.resetOriginalValues();
    }

    /**
     * Caches the ivld item masters in the entity cache if it is enabled.
     *
     * @param ivldItemMasters the ivld item masters
     */
    @Override
    public void cacheResult(List<IvldItemMaster> ivldItemMasters) {
        for (IvldItemMaster ivldItemMaster : ivldItemMasters) {
            if (EntityCacheUtil.getResult(
                        IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey()) == null) {
                cacheResult(ivldItemMaster);
            } else {
                ivldItemMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld item masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldItemMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldItemMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld item master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldItemMaster ivldItemMaster) {
        EntityCacheUtil.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldItemMaster> ivldItemMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldItemMaster ivldItemMaster : ivldItemMasters) {
            EntityCacheUtil.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
     *
     * @param ivldItemMasterSid the primary key for the new ivld item master
     * @return the new ivld item master
     */
    @Override
    public IvldItemMaster create(int ivldItemMasterSid) {
        IvldItemMaster ivldItemMaster = new IvldItemMasterImpl();

        ivldItemMaster.setNew(true);
        ivldItemMaster.setPrimaryKey(ivldItemMasterSid);

        return ivldItemMaster;
    }

    /**
     * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldItemMasterSid the primary key of the ivld item master
     * @return the ivld item master that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster remove(int ivldItemMasterSid)
        throws NoSuchIvldItemMasterException, SystemException {
        return remove((Serializable) ivldItemMasterSid);
    }

    /**
     * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld item master
     * @return the ivld item master that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster remove(Serializable primaryKey)
        throws NoSuchIvldItemMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldItemMaster ivldItemMaster = (IvldItemMaster) session.get(IvldItemMasterImpl.class,
                    primaryKey);

            if (ivldItemMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldItemMaster);
        } catch (NoSuchIvldItemMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldItemMaster removeImpl(IvldItemMaster ivldItemMaster)
        throws SystemException {
        ivldItemMaster = toUnwrappedModel(ivldItemMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldItemMaster)) {
                ivldItemMaster = (IvldItemMaster) session.get(IvldItemMasterImpl.class,
                        ivldItemMaster.getPrimaryKeyObj());
            }

            if (ivldItemMaster != null) {
                session.delete(ivldItemMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldItemMaster != null) {
            clearCache(ivldItemMaster);
        }

        return ivldItemMaster;
    }

    @Override
    public IvldItemMaster updateImpl(
        com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster)
        throws SystemException {
        ivldItemMaster = toUnwrappedModel(ivldItemMaster);

        boolean isNew = ivldItemMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldItemMaster.isNew()) {
                session.save(ivldItemMaster);

                ivldItemMaster.setNew(false);
            } else {
                session.merge(ivldItemMaster);
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

        EntityCacheUtil.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey(),
            ivldItemMaster);

        return ivldItemMaster;
    }

    protected IvldItemMaster toUnwrappedModel(IvldItemMaster ivldItemMaster) {
        if (ivldItemMaster instanceof IvldItemMasterImpl) {
            return ivldItemMaster;
        }

        IvldItemMasterImpl ivldItemMasterImpl = new IvldItemMasterImpl();

        ivldItemMasterImpl.setNew(ivldItemMaster.isNew());
        ivldItemMasterImpl.setPrimaryKey(ivldItemMaster.getPrimaryKey());

        ivldItemMasterImpl.setItemNo(ivldItemMaster.getItemNo());
        ivldItemMasterImpl.setUdc6(ivldItemMaster.getUdc6());
        ivldItemMasterImpl.setCreatedDate(ivldItemMaster.getCreatedDate());
        ivldItemMasterImpl.setNewFormulationIndicator(ivldItemMaster.getNewFormulationIndicator());
        ivldItemMasterImpl.setUdc5(ivldItemMaster.getUdc5());
        ivldItemMasterImpl.setNewFormulationEndDate(ivldItemMaster.getNewFormulationEndDate());
        ivldItemMasterImpl.setUdc4(ivldItemMaster.getUdc4());
        ivldItemMasterImpl.setClottingFactorStartDate(ivldItemMaster.getClottingFactorStartDate());
        ivldItemMasterImpl.setSecondaryUom(ivldItemMaster.getSecondaryUom());
        ivldItemMasterImpl.setItemDesc(ivldItemMaster.getItemDesc());
        ivldItemMasterImpl.setAuthorizedGenericEndDate(ivldItemMaster.getAuthorizedGenericEndDate());
        ivldItemMasterImpl.setManufacturerName(ivldItemMaster.getManufacturerName());
        ivldItemMasterImpl.setItemName(ivldItemMaster.getItemName());
        ivldItemMasterImpl.setReprocessedFlag(ivldItemMaster.getReprocessedFlag());
        ivldItemMasterImpl.setStatus(ivldItemMaster.getStatus());
        ivldItemMasterImpl.setBaseCpi(ivldItemMaster.getBaseCpi());
        ivldItemMasterImpl.setBaselineAmp(ivldItemMaster.getBaselineAmp());
        ivldItemMasterImpl.setAuthorizedGeneric(ivldItemMaster.getAuthorizedGeneric());
        ivldItemMasterImpl.setTherapeuticClass(ivldItemMaster.getTherapeuticClass());
        ivldItemMasterImpl.setItemFamilyId(ivldItemMaster.getItemFamilyId());
        ivldItemMasterImpl.setPediatricExclusiveStartDate(ivldItemMaster.getPediatricExclusiveStartDate());
        ivldItemMasterImpl.setCreatedBy(ivldItemMaster.getCreatedBy());
        ivldItemMasterImpl.setPrimaryUom(ivldItemMaster.getPrimaryUom());
        ivldItemMasterImpl.setNdc9(ivldItemMaster.getNdc9());
        ivldItemMasterImpl.setItemId(ivldItemMaster.getItemId());
        ivldItemMasterImpl.setLastLotExpirationDate(ivldItemMaster.getLastLotExpirationDate());
        ivldItemMasterImpl.setErrorField(ivldItemMaster.getErrorField());
        ivldItemMasterImpl.setItemCode(ivldItemMaster.getItemCode());
        ivldItemMasterImpl.setStrength(ivldItemMaster.getStrength());
        ivldItemMasterImpl.setModifiedDate(ivldItemMaster.getModifiedDate());
        ivldItemMasterImpl.setBrand(ivldItemMaster.getBrand());
        ivldItemMasterImpl.setNdc8(ivldItemMaster.getNdc8());
        ivldItemMasterImpl.setLabelerCode(ivldItemMaster.getLabelerCode());
        ivldItemMasterImpl.setUdc3(ivldItemMaster.getUdc3());
        ivldItemMasterImpl.setSource(ivldItemMaster.getSource());
        ivldItemMasterImpl.setUdc2(ivldItemMaster.getUdc2());
        ivldItemMasterImpl.setAddChgDelIndicator(ivldItemMaster.getAddChgDelIndicator());
        ivldItemMasterImpl.setUdc1(ivldItemMaster.getUdc1());
        ivldItemMasterImpl.setAcquiredAmp(ivldItemMaster.getAcquiredAmp());
        ivldItemMasterImpl.setDiscontinuationDate(ivldItemMaster.getDiscontinuationDate());
        ivldItemMasterImpl.setItemMasterIntfid(ivldItemMaster.getItemMasterIntfid());
        ivldItemMasterImpl.setIntfInsertedDate(ivldItemMaster.getIntfInsertedDate());
        ivldItemMasterImpl.setDivestitureDate(ivldItemMaster.getDivestitureDate());
        ivldItemMasterImpl.setModifiedBy(ivldItemMaster.getModifiedBy());
        ivldItemMasterImpl.setBaseCpiPeriod(ivldItemMaster.getBaseCpiPeriod());
        ivldItemMasterImpl.setClottingFactorEndDate(ivldItemMaster.getClottingFactorEndDate());
        ivldItemMasterImpl.setDosesPerUnit(ivldItemMaster.getDosesPerUnit());
        ivldItemMasterImpl.setManufacturerId(ivldItemMaster.getManufacturerId());
        ivldItemMasterImpl.setClottingFactorIndicator(ivldItemMaster.getClottingFactorIndicator());
        ivldItemMasterImpl.setBatchId(ivldItemMaster.getBatchId());
        ivldItemMasterImpl.setAcquisitionDate(ivldItemMaster.getAcquisitionDate());
        ivldItemMasterImpl.setDualPricingIndicator(ivldItemMaster.getDualPricingIndicator());
        ivldItemMasterImpl.setNonFederalExpirationDate(ivldItemMaster.getNonFederalExpirationDate());
        ivldItemMasterImpl.setErrorCode(ivldItemMaster.getErrorCode());
        ivldItemMasterImpl.setNewFormulation(ivldItemMaster.getNewFormulation());
        ivldItemMasterImpl.setObraBamp(ivldItemMaster.getObraBamp());
        ivldItemMasterImpl.setBrandId(ivldItemMaster.getBrandId());
        ivldItemMasterImpl.setItemStatus(ivldItemMaster.getItemStatus());
        ivldItemMasterImpl.setAuthorizedGenericStartDate(ivldItemMaster.getAuthorizedGenericStartDate());
        ivldItemMasterImpl.setNewFormulationStartDate(ivldItemMaster.getNewFormulationStartDate());
        ivldItemMasterImpl.setItemCategory(ivldItemMaster.getItemCategory());
        ivldItemMasterImpl.setItemEndDate(ivldItemMaster.getItemEndDate());
        ivldItemMasterImpl.setItemType(ivldItemMaster.getItemType());
        ivldItemMasterImpl.setPediatricExclusiveEndDate(ivldItemMaster.getPediatricExclusiveEndDate());
        ivldItemMasterImpl.setOrganizationKey(ivldItemMaster.getOrganizationKey());
        ivldItemMasterImpl.setFirstSaleDate(ivldItemMaster.getFirstSaleDate());
        ivldItemMasterImpl.setShelfLifeType(ivldItemMaster.getShelfLifeType());
        ivldItemMasterImpl.setItemStartDate(ivldItemMaster.getItemStartDate());
        ivldItemMasterImpl.setItemTypeIndication(ivldItemMaster.getItemTypeIndication());
        ivldItemMasterImpl.setAcquiredBamp(ivldItemMaster.getAcquiredBamp());
        ivldItemMasterImpl.setForm(ivldItemMaster.getForm());
        ivldItemMasterImpl.setItemClass(ivldItemMaster.getItemClass());
        ivldItemMasterImpl.setManufacturerNo(ivldItemMaster.getManufacturerNo());
        ivldItemMasterImpl.setPediatricExclusiveIndicator(ivldItemMaster.getPediatricExclusiveIndicator());
        ivldItemMasterImpl.setPackageSizeCode(ivldItemMaster.getPackageSizeCode());
        ivldItemMasterImpl.setDisplayBrand(ivldItemMaster.getDisplayBrand());
        ivldItemMasterImpl.setDra(ivldItemMaster.getDra());
        ivldItemMasterImpl.setReasonForFailure(ivldItemMaster.getReasonForFailure());
        ivldItemMasterImpl.setPackageSizeIntroDate(ivldItemMaster.getPackageSizeIntroDate());
        ivldItemMasterImpl.setUpps(ivldItemMaster.getUpps());
        ivldItemMasterImpl.setIvldItemMasterSid(ivldItemMaster.getIvldItemMasterSid());
        ivldItemMasterImpl.setPackageSize(ivldItemMaster.getPackageSize());
        ivldItemMasterImpl.setShelfLife(ivldItemMaster.getShelfLife());
        ivldItemMasterImpl.setMarketTerminationDate(ivldItemMaster.getMarketTerminationDate());
        ivldItemMasterImpl.setCheckRecord(ivldItemMaster.isCheckRecord());

        return ivldItemMasterImpl;
    }

    /**
     * Returns the ivld item master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item master
     * @return the ivld item master
     * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldItemMasterException, SystemException {
        IvldItemMaster ivldItemMaster = fetchByPrimaryKey(primaryKey);

        if (ivldItemMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldItemMaster;
    }

    /**
     * Returns the ivld item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemMasterException} if it could not be found.
     *
     * @param ivldItemMasterSid the primary key of the ivld item master
     * @return the ivld item master
     * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster findByPrimaryKey(int ivldItemMasterSid)
        throws NoSuchIvldItemMasterException, SystemException {
        return findByPrimaryKey((Serializable) ivldItemMasterSid);
    }

    /**
     * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item master
     * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldItemMaster ivldItemMaster = (IvldItemMaster) EntityCacheUtil.getResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemMasterImpl.class, primaryKey);

        if (ivldItemMaster == _nullIvldItemMaster) {
            return null;
        }

        if (ivldItemMaster == null) {
            Session session = null;

            try {
                session = openSession();

                ivldItemMaster = (IvldItemMaster) session.get(IvldItemMasterImpl.class,
                        primaryKey);

                if (ivldItemMaster != null) {
                    cacheResult(ivldItemMaster);
                } else {
                    EntityCacheUtil.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemMasterImpl.class, primaryKey,
                        _nullIvldItemMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldItemMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldItemMaster;
    }

    /**
     * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldItemMasterSid the primary key of the ivld item master
     * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemMaster fetchByPrimaryKey(int ivldItemMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldItemMasterSid);
    }

    /**
     * Returns all the ivld item masters.
     *
     * @return the ivld item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld item masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item masters
     * @param end the upper bound of the range of ivld item masters (not inclusive)
     * @return the range of ivld item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld item masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item masters
     * @param end the upper bound of the range of ivld item masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld item masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemMaster> findAll(int start, int end,
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

        List<IvldItemMaster> list = (List<IvldItemMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDITEMMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDITEMMASTER;

                if (pagination) {
                    sql = sql.concat(IvldItemMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldItemMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldItemMaster>(list);
                } else {
                    list = (List<IvldItemMaster>) QueryUtil.list(q,
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
     * Removes all the ivld item masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldItemMaster ivldItemMaster : findAll()) {
            remove(ivldItemMaster);
        }
    }

    /**
     * Returns the number of ivld item masters.
     *
     * @return the number of ivld item masters
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

                Query q = session.createQuery(_SQL_COUNT_IVLDITEMMASTER);

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
     * Initializes the ivld item master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldItemMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldItemMaster>> listenersList = new ArrayList<ModelListener<IvldItemMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldItemMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldItemMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
