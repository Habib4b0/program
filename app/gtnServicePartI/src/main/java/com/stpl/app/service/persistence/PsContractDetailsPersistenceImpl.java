package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchPsContractDetailsException;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.impl.PsContractDetailsImpl;
import com.stpl.app.model.impl.PsContractDetailsModelImpl;
import com.stpl.app.service.persistence.PsContractDetailsPersistence;

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
 * The persistence implementation for the ps contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractDetailsPersistence
 * @see PsContractDetailsUtil
 * @generated
 */
public class PsContractDetailsPersistenceImpl extends BasePersistenceImpl<PsContractDetails>
    implements PsContractDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PsContractDetailsUtil} to access the ps contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PsContractDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            PsContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            PsContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PSCONTRACTDETAILS = "SELECT psContractDetails FROM PsContractDetails psContractDetails";
    private static final String _SQL_COUNT_PSCONTRACTDETAILS = "SELECT COUNT(psContractDetails) FROM PsContractDetails psContractDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "psContractDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsContractDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PsContractDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "price", "itemMasterSid", "priceProtectionStartDate",
                "basePrice", "modifiedDate", "revisionDate", "priceTolerance",
                "createdDate", "source", "createdBy", "suggestedPrice",
                "psContractAttachedDate", "psContractDetailsSid", "modifiedBy",
                "inboundStatus", "contractPrice", "priceToleranceType",
                "itemPricingQualifierSid", "contractPriceEndDate",
                "priceToleranceFrequency", "contractPriceStartDate",
                "psContractSid", "priceProtectionEndDate",
                "psContractAttachedStatus", "recordLockStatus", "batchId",
                "priceToleranceInterval", "priceRevision", "brandMasterSid",
                "nep", "priceProtectionStatus", "priceProtectionPriceType",
                "nepFormula", "maxIncrementalChange", "resetEligible",
                "resetType", "resetDate", "resetInterval", "resetFrequency",
                "netPriceType", "netPriceTypeFormula", "basePriceType",
                "basePriceEntry", "basePriceDate", "basePriceDdlb",
                "netBasePrice", "netBasePriceFormulaId",
                "subsequentPeriodPriceType", "netSubsequentPeriodPrice",
                "netSubsequentPriceFormulaId", "resetPriceType",
                "netResetPriceType", "netResetPriceFormulaId"
            });
    private static PsContractDetails _nullPsContractDetails = new PsContractDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PsContractDetails> toCacheModel() {
                return _nullPsContractDetailsCacheModel;
            }
        };

    private static CacheModel<PsContractDetails> _nullPsContractDetailsCacheModel =
        new CacheModel<PsContractDetails>() {
            @Override
            public PsContractDetails toEntityModel() {
                return _nullPsContractDetails;
            }
        };

    public PsContractDetailsPersistenceImpl() {
        setModelClass(PsContractDetails.class);
    }

    /**
     * Caches the ps contract details in the entity cache if it is enabled.
     *
     * @param psContractDetails the ps contract details
     */
    @Override
    public void cacheResult(PsContractDetails psContractDetails) {
        EntityCacheUtil.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsImpl.class, psContractDetails.getPrimaryKey(),
            psContractDetails);

        psContractDetails.resetOriginalValues();
    }

    /**
     * Caches the ps contract detailses in the entity cache if it is enabled.
     *
     * @param psContractDetailses the ps contract detailses
     */
    @Override
    public void cacheResult(List<PsContractDetails> psContractDetailses) {
        for (PsContractDetails psContractDetails : psContractDetailses) {
            if (EntityCacheUtil.getResult(
                        PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        PsContractDetailsImpl.class,
                        psContractDetails.getPrimaryKey()) == null) {
                cacheResult(psContractDetails);
            } else {
                psContractDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ps contract detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PsContractDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PsContractDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ps contract details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PsContractDetails psContractDetails) {
        EntityCacheUtil.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsImpl.class, psContractDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PsContractDetails> psContractDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PsContractDetails psContractDetails : psContractDetailses) {
            EntityCacheUtil.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                PsContractDetailsImpl.class, psContractDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
     *
     * @param psContractDetailsSid the primary key for the new ps contract details
     * @return the new ps contract details
     */
    @Override
    public PsContractDetails create(int psContractDetailsSid) {
        PsContractDetails psContractDetails = new PsContractDetailsImpl();

        psContractDetails.setNew(true);
        psContractDetails.setPrimaryKey(psContractDetailsSid);

        return psContractDetails;
    }

    /**
     * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param psContractDetailsSid the primary key of the ps contract details
     * @return the ps contract details that was removed
     * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails remove(int psContractDetailsSid)
        throws NoSuchPsContractDetailsException, SystemException {
        return remove((Serializable) psContractDetailsSid);
    }

    /**
     * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ps contract details
     * @return the ps contract details that was removed
     * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails remove(Serializable primaryKey)
        throws NoSuchPsContractDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PsContractDetails psContractDetails = (PsContractDetails) session.get(PsContractDetailsImpl.class,
                    primaryKey);

            if (psContractDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(psContractDetails);
        } catch (NoSuchPsContractDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PsContractDetails removeImpl(PsContractDetails psContractDetails)
        throws SystemException {
        psContractDetails = toUnwrappedModel(psContractDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(psContractDetails)) {
                psContractDetails = (PsContractDetails) session.get(PsContractDetailsImpl.class,
                        psContractDetails.getPrimaryKeyObj());
            }

            if (psContractDetails != null) {
                session.delete(psContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (psContractDetails != null) {
            clearCache(psContractDetails);
        }

        return psContractDetails;
    }

    @Override
    public PsContractDetails updateImpl(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws SystemException {
        psContractDetails = toUnwrappedModel(psContractDetails);

        boolean isNew = psContractDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (psContractDetails.isNew()) {
                session.save(psContractDetails);

                psContractDetails.setNew(false);
            } else {
                session.merge(psContractDetails);
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

        EntityCacheUtil.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            PsContractDetailsImpl.class, psContractDetails.getPrimaryKey(),
            psContractDetails);

        return psContractDetails;
    }

    protected PsContractDetails toUnwrappedModel(
        PsContractDetails psContractDetails) {
        if (psContractDetails instanceof PsContractDetailsImpl) {
            return psContractDetails;
        }

        PsContractDetailsImpl psContractDetailsImpl = new PsContractDetailsImpl();

        psContractDetailsImpl.setNew(psContractDetails.isNew());
        psContractDetailsImpl.setPrimaryKey(psContractDetails.getPrimaryKey());

        psContractDetailsImpl.setPrice(psContractDetails.getPrice());
        psContractDetailsImpl.setItemMasterSid(psContractDetails.getItemMasterSid());
        psContractDetailsImpl.setPriceProtectionStartDate(psContractDetails.getPriceProtectionStartDate());
        psContractDetailsImpl.setBasePrice(psContractDetails.getBasePrice());
        psContractDetailsImpl.setModifiedDate(psContractDetails.getModifiedDate());
        psContractDetailsImpl.setRevisionDate(psContractDetails.getRevisionDate());
        psContractDetailsImpl.setPriceTolerance(psContractDetails.getPriceTolerance());
        psContractDetailsImpl.setCreatedDate(psContractDetails.getCreatedDate());
        psContractDetailsImpl.setSource(psContractDetails.getSource());
        psContractDetailsImpl.setCreatedBy(psContractDetails.getCreatedBy());
        psContractDetailsImpl.setSuggestedPrice(psContractDetails.getSuggestedPrice());
        psContractDetailsImpl.setPsContractAttachedDate(psContractDetails.getPsContractAttachedDate());
        psContractDetailsImpl.setPsContractDetailsSid(psContractDetails.getPsContractDetailsSid());
        psContractDetailsImpl.setModifiedBy(psContractDetails.getModifiedBy());
        psContractDetailsImpl.setInboundStatus(psContractDetails.getInboundStatus());
        psContractDetailsImpl.setContractPrice(psContractDetails.getContractPrice());
        psContractDetailsImpl.setPriceToleranceType(psContractDetails.getPriceToleranceType());
        psContractDetailsImpl.setItemPricingQualifierSid(psContractDetails.getItemPricingQualifierSid());
        psContractDetailsImpl.setContractPriceEndDate(psContractDetails.getContractPriceEndDate());
        psContractDetailsImpl.setPriceToleranceFrequency(psContractDetails.getPriceToleranceFrequency());
        psContractDetailsImpl.setContractPriceStartDate(psContractDetails.getContractPriceStartDate());
        psContractDetailsImpl.setPsContractSid(psContractDetails.getPsContractSid());
        psContractDetailsImpl.setPriceProtectionEndDate(psContractDetails.getPriceProtectionEndDate());
        psContractDetailsImpl.setPsContractAttachedStatus(psContractDetails.getPsContractAttachedStatus());
        psContractDetailsImpl.setRecordLockStatus(psContractDetails.isRecordLockStatus());
        psContractDetailsImpl.setBatchId(psContractDetails.getBatchId());
        psContractDetailsImpl.setPriceToleranceInterval(psContractDetails.getPriceToleranceInterval());
        psContractDetailsImpl.setPriceRevision(psContractDetails.getPriceRevision());
        psContractDetailsImpl.setBrandMasterSid(psContractDetails.getBrandMasterSid());
        psContractDetailsImpl.setNep(psContractDetails.getNep());
        psContractDetailsImpl.setPriceProtectionStatus(psContractDetails.getPriceProtectionStatus());
        psContractDetailsImpl.setPriceProtectionPriceType(psContractDetails.getPriceProtectionPriceType());
        psContractDetailsImpl.setNepFormula(psContractDetails.getNepFormula());
        psContractDetailsImpl.setMaxIncrementalChange(psContractDetails.getMaxIncrementalChange());
        psContractDetailsImpl.setResetEligible(psContractDetails.getResetEligible());
        psContractDetailsImpl.setResetType(psContractDetails.getResetType());
        psContractDetailsImpl.setResetDate(psContractDetails.getResetDate());
        psContractDetailsImpl.setResetInterval(psContractDetails.getResetInterval());
        psContractDetailsImpl.setResetFrequency(psContractDetails.getResetFrequency());
        psContractDetailsImpl.setNetPriceType(psContractDetails.getNetPriceType());
        psContractDetailsImpl.setNetPriceTypeFormula(psContractDetails.getNetPriceTypeFormula());
        psContractDetailsImpl.setBasePriceType(psContractDetails.getBasePriceType());
        psContractDetailsImpl.setBasePriceEntry(psContractDetails.getBasePriceEntry());
        psContractDetailsImpl.setBasePriceDate(psContractDetails.getBasePriceDate());
        psContractDetailsImpl.setBasePriceDdlb(psContractDetails.getBasePriceDdlb());
        psContractDetailsImpl.setNetBasePrice(psContractDetails.getNetBasePrice());
        psContractDetailsImpl.setNetBasePriceFormulaId(psContractDetails.getNetBasePriceFormulaId());
        psContractDetailsImpl.setSubsequentPeriodPriceType(psContractDetails.getSubsequentPeriodPriceType());
        psContractDetailsImpl.setNetSubsequentPeriodPrice(psContractDetails.getNetSubsequentPeriodPrice());
        psContractDetailsImpl.setNetSubsequentPriceFormulaId(psContractDetails.getNetSubsequentPriceFormulaId());
        psContractDetailsImpl.setResetPriceType(psContractDetails.getResetPriceType());
        psContractDetailsImpl.setNetResetPriceType(psContractDetails.getNetResetPriceType());
        psContractDetailsImpl.setNetResetPriceFormulaId(psContractDetails.getNetResetPriceFormulaId());

        return psContractDetailsImpl;
    }

    /**
     * Returns the ps contract details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ps contract details
     * @return the ps contract details
     * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPsContractDetailsException, SystemException {
        PsContractDetails psContractDetails = fetchByPrimaryKey(primaryKey);

        if (psContractDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return psContractDetails;
    }

    /**
     * Returns the ps contract details with the primary key or throws a {@link com.stpl.app.NoSuchPsContractDetailsException} if it could not be found.
     *
     * @param psContractDetailsSid the primary key of the ps contract details
     * @return the ps contract details
     * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails findByPrimaryKey(int psContractDetailsSid)
        throws NoSuchPsContractDetailsException, SystemException {
        return findByPrimaryKey((Serializable) psContractDetailsSid);
    }

    /**
     * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ps contract details
     * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PsContractDetails psContractDetails = (PsContractDetails) EntityCacheUtil.getResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                PsContractDetailsImpl.class, primaryKey);

        if (psContractDetails == _nullPsContractDetails) {
            return null;
        }

        if (psContractDetails == null) {
            Session session = null;

            try {
                session = openSession();

                psContractDetails = (PsContractDetails) session.get(PsContractDetailsImpl.class,
                        primaryKey);

                if (psContractDetails != null) {
                    cacheResult(psContractDetails);
                } else {
                    EntityCacheUtil.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        PsContractDetailsImpl.class, primaryKey,
                        _nullPsContractDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    PsContractDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return psContractDetails;
    }

    /**
     * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param psContractDetailsSid the primary key of the ps contract details
     * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PsContractDetails fetchByPrimaryKey(int psContractDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) psContractDetailsSid);
    }

    /**
     * Returns all the ps contract detailses.
     *
     * @return the ps contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContractDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ps contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps contract detailses
     * @param end the upper bound of the range of ps contract detailses (not inclusive)
     * @return the range of ps contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContractDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ps contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ps contract detailses
     * @param end the upper bound of the range of ps contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ps contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PsContractDetails> findAll(int start, int end,
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

        List<PsContractDetails> list = (List<PsContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PSCONTRACTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PSCONTRACTDETAILS;

                if (pagination) {
                    sql = sql.concat(PsContractDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PsContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PsContractDetails>(list);
                } else {
                    list = (List<PsContractDetails>) QueryUtil.list(q,
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
     * Removes all the ps contract detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PsContractDetails psContractDetails : findAll()) {
            remove(psContractDetails);
        }
    }

    /**
     * Returns the number of ps contract detailses.
     *
     * @return the number of ps contract detailses
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

                Query q = session.createQuery(_SQL_COUNT_PSCONTRACTDETAILS);

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
     * Initializes the ps contract details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.PsContractDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PsContractDetails>> listenersList = new ArrayList<ModelListener<PsContractDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PsContractDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PsContractDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
