package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRebatePlanTierException;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.model.impl.RebatePlanTierImpl;
import com.stpl.app.model.impl.RebatePlanTierModelImpl;
import com.stpl.app.service.persistence.RebatePlanTierPersistence;

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
 * The persistence implementation for the rebate plan tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanTierPersistence
 * @see RebatePlanTierUtil
 * @generated
 */
public class RebatePlanTierPersistenceImpl extends BasePersistenceImpl<RebatePlanTier>
    implements RebatePlanTierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RebatePlanTierUtil} to access the rebate plan tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RebatePlanTierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanTierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierModelImpl.FINDER_CACHE_ENABLED,
            RebatePlanTierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_REBATEPLANTIER = "SELECT rebatePlanTier FROM RebatePlanTier rebatePlanTier";
    private static final String _SQL_COUNT_REBATEPLANTIER = "SELECT COUNT(rebatePlanTier) FROM RebatePlanTier rebatePlanTier";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rebatePlanTier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RebatePlanTier exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RebatePlanTierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "tierValue", "returnRateSid", "rebatePlanMasterSid",
                "rebatePlanTierSid", "itemPricingQualifierSid", "modifiedDate",
                "tierTolerance", "tierFrom", "tierOperator", "recordLockStatus",
                "createdDate", "createdBy", "source", "tierTo", "batchId",
                "rebatePlanTierId", "freeAmount", "modifiedBy", "inboundStatus",
                "tierLevel", "formulaNo", "formulaName", "secondaryRebatePlanNo",
                "secondaryRebatePlanName", "secondaryRebatePlanSid"
            });
    private static RebatePlanTier _nullRebatePlanTier = new RebatePlanTierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RebatePlanTier> toCacheModel() {
                return _nullRebatePlanTierCacheModel;
            }
        };

    private static CacheModel<RebatePlanTier> _nullRebatePlanTierCacheModel = new CacheModel<RebatePlanTier>() {
            @Override
            public RebatePlanTier toEntityModel() {
                return _nullRebatePlanTier;
            }
        };

    public RebatePlanTierPersistenceImpl() {
        setModelClass(RebatePlanTier.class);
    }

    /**
     * Caches the rebate plan tier in the entity cache if it is enabled.
     *
     * @param rebatePlanTier the rebate plan tier
     */
    @Override
    public void cacheResult(RebatePlanTier rebatePlanTier) {
        EntityCacheUtil.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey(),
            rebatePlanTier);

        rebatePlanTier.resetOriginalValues();
    }

    /**
     * Caches the rebate plan tiers in the entity cache if it is enabled.
     *
     * @param rebatePlanTiers the rebate plan tiers
     */
    @Override
    public void cacheResult(List<RebatePlanTier> rebatePlanTiers) {
        for (RebatePlanTier rebatePlanTier : rebatePlanTiers) {
            if (EntityCacheUtil.getResult(
                        RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
                        RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey()) == null) {
                cacheResult(rebatePlanTier);
            } else {
                rebatePlanTier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rebate plan tiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RebatePlanTierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RebatePlanTierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rebate plan tier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RebatePlanTier rebatePlanTier) {
        EntityCacheUtil.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RebatePlanTier> rebatePlanTiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RebatePlanTier rebatePlanTier : rebatePlanTiers) {
            EntityCacheUtil.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
                RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey());
        }
    }

    /**
     * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
     *
     * @param rebatePlanTierSid the primary key for the new rebate plan tier
     * @return the new rebate plan tier
     */
    @Override
    public RebatePlanTier create(int rebatePlanTierSid) {
        RebatePlanTier rebatePlanTier = new RebatePlanTierImpl();

        rebatePlanTier.setNew(true);
        rebatePlanTier.setPrimaryKey(rebatePlanTierSid);

        return rebatePlanTier;
    }

    /**
     * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rebatePlanTierSid the primary key of the rebate plan tier
     * @return the rebate plan tier that was removed
     * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier remove(int rebatePlanTierSid)
        throws NoSuchRebatePlanTierException, SystemException {
        return remove((Serializable) rebatePlanTierSid);
    }

    /**
     * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rebate plan tier
     * @return the rebate plan tier that was removed
     * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier remove(Serializable primaryKey)
        throws NoSuchRebatePlanTierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RebatePlanTier rebatePlanTier = (RebatePlanTier) session.get(RebatePlanTierImpl.class,
                    primaryKey);

            if (rebatePlanTier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRebatePlanTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rebatePlanTier);
        } catch (NoSuchRebatePlanTierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RebatePlanTier removeImpl(RebatePlanTier rebatePlanTier)
        throws SystemException {
        rebatePlanTier = toUnwrappedModel(rebatePlanTier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rebatePlanTier)) {
                rebatePlanTier = (RebatePlanTier) session.get(RebatePlanTierImpl.class,
                        rebatePlanTier.getPrimaryKeyObj());
            }

            if (rebatePlanTier != null) {
                session.delete(rebatePlanTier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rebatePlanTier != null) {
            clearCache(rebatePlanTier);
        }

        return rebatePlanTier;
    }

    @Override
    public RebatePlanTier updateImpl(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws SystemException {
        rebatePlanTier = toUnwrappedModel(rebatePlanTier);

        boolean isNew = rebatePlanTier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rebatePlanTier.isNew()) {
                session.save(rebatePlanTier);

                rebatePlanTier.setNew(false);
            } else {
                session.merge(rebatePlanTier);
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

        EntityCacheUtil.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
            RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey(),
            rebatePlanTier);

        return rebatePlanTier;
    }

    protected RebatePlanTier toUnwrappedModel(RebatePlanTier rebatePlanTier) {
        if (rebatePlanTier instanceof RebatePlanTierImpl) {
            return rebatePlanTier;
        }

        RebatePlanTierImpl rebatePlanTierImpl = new RebatePlanTierImpl();

        rebatePlanTierImpl.setNew(rebatePlanTier.isNew());
        rebatePlanTierImpl.setPrimaryKey(rebatePlanTier.getPrimaryKey());

        rebatePlanTierImpl.setTierValue(rebatePlanTier.getTierValue());
        rebatePlanTierImpl.setReturnRateSid(rebatePlanTier.getReturnRateSid());
        rebatePlanTierImpl.setRebatePlanMasterSid(rebatePlanTier.getRebatePlanMasterSid());
        rebatePlanTierImpl.setRebatePlanTierSid(rebatePlanTier.getRebatePlanTierSid());
        rebatePlanTierImpl.setItemPricingQualifierSid(rebatePlanTier.getItemPricingQualifierSid());
        rebatePlanTierImpl.setModifiedDate(rebatePlanTier.getModifiedDate());
        rebatePlanTierImpl.setTierTolerance(rebatePlanTier.getTierTolerance());
        rebatePlanTierImpl.setTierFrom(rebatePlanTier.getTierFrom());
        rebatePlanTierImpl.setTierOperator(rebatePlanTier.getTierOperator());
        rebatePlanTierImpl.setRecordLockStatus(rebatePlanTier.isRecordLockStatus());
        rebatePlanTierImpl.setCreatedDate(rebatePlanTier.getCreatedDate());
        rebatePlanTierImpl.setCreatedBy(rebatePlanTier.getCreatedBy());
        rebatePlanTierImpl.setSource(rebatePlanTier.getSource());
        rebatePlanTierImpl.setTierTo(rebatePlanTier.getTierTo());
        rebatePlanTierImpl.setBatchId(rebatePlanTier.getBatchId());
        rebatePlanTierImpl.setRebatePlanTierId(rebatePlanTier.getRebatePlanTierId());
        rebatePlanTierImpl.setFreeAmount(rebatePlanTier.getFreeAmount());
        rebatePlanTierImpl.setModifiedBy(rebatePlanTier.getModifiedBy());
        rebatePlanTierImpl.setInboundStatus(rebatePlanTier.getInboundStatus());
        rebatePlanTierImpl.setTierLevel(rebatePlanTier.getTierLevel());
        rebatePlanTierImpl.setFormulaNo(rebatePlanTier.getFormulaNo());
        rebatePlanTierImpl.setFormulaName(rebatePlanTier.getFormulaName());
        rebatePlanTierImpl.setSecondaryRebatePlanNo(rebatePlanTier.getSecondaryRebatePlanNo());
        rebatePlanTierImpl.setSecondaryRebatePlanName(rebatePlanTier.getSecondaryRebatePlanName());
        rebatePlanTierImpl.setSecondaryRebatePlanSid(rebatePlanTier.getSecondaryRebatePlanSid());

        return rebatePlanTierImpl;
    }

    /**
     * Returns the rebate plan tier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rebate plan tier
     * @return the rebate plan tier
     * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRebatePlanTierException, SystemException {
        RebatePlanTier rebatePlanTier = fetchByPrimaryKey(primaryKey);

        if (rebatePlanTier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRebatePlanTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rebatePlanTier;
    }

    /**
     * Returns the rebate plan tier with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanTierException} if it could not be found.
     *
     * @param rebatePlanTierSid the primary key of the rebate plan tier
     * @return the rebate plan tier
     * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier findByPrimaryKey(int rebatePlanTierSid)
        throws NoSuchRebatePlanTierException, SystemException {
        return findByPrimaryKey((Serializable) rebatePlanTierSid);
    }

    /**
     * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rebate plan tier
     * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RebatePlanTier rebatePlanTier = (RebatePlanTier) EntityCacheUtil.getResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
                RebatePlanTierImpl.class, primaryKey);

        if (rebatePlanTier == _nullRebatePlanTier) {
            return null;
        }

        if (rebatePlanTier == null) {
            Session session = null;

            try {
                session = openSession();

                rebatePlanTier = (RebatePlanTier) session.get(RebatePlanTierImpl.class,
                        primaryKey);

                if (rebatePlanTier != null) {
                    cacheResult(rebatePlanTier);
                } else {
                    EntityCacheUtil.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
                        RebatePlanTierImpl.class, primaryKey,
                        _nullRebatePlanTier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
                    RebatePlanTierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rebatePlanTier;
    }

    /**
     * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rebatePlanTierSid the primary key of the rebate plan tier
     * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebatePlanTier fetchByPrimaryKey(int rebatePlanTierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rebatePlanTierSid);
    }

    /**
     * Returns all the rebate plan tiers.
     *
     * @return the rebate plan tiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanTier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate plan tiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate plan tiers
     * @param end the upper bound of the range of rebate plan tiers (not inclusive)
     * @return the range of rebate plan tiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanTier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate plan tiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate plan tiers
     * @param end the upper bound of the range of rebate plan tiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rebate plan tiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebatePlanTier> findAll(int start, int end,
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

        List<RebatePlanTier> list = (List<RebatePlanTier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_REBATEPLANTIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_REBATEPLANTIER;

                if (pagination) {
                    sql = sql.concat(RebatePlanTierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RebatePlanTier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebatePlanTier>(list);
                } else {
                    list = (List<RebatePlanTier>) QueryUtil.list(q,
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
     * Removes all the rebate plan tiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RebatePlanTier rebatePlanTier : findAll()) {
            remove(rebatePlanTier);
        }
    }

    /**
     * Returns the number of rebate plan tiers.
     *
     * @return the number of rebate plan tiers
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

                Query q = session.createQuery(_SQL_COUNT_REBATEPLANTIER);

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
     * Initializes the rebate plan tier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RebatePlanTier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RebatePlanTier>> listenersList = new ArrayList<ModelListener<RebatePlanTier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RebatePlanTier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RebatePlanTierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
