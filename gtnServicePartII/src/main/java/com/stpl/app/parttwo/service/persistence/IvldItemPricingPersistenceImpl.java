package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldItemPricingException;
import com.stpl.app.parttwo.model.IvldItemPricing;
import com.stpl.app.parttwo.model.impl.IvldItemPricingImpl;
import com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemPricingPersistence;

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
 * The persistence implementation for the ivld item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemPricingPersistence
 * @see IvldItemPricingUtil
 * @generated
 */
public class IvldItemPricingPersistenceImpl extends BasePersistenceImpl<IvldItemPricing>
    implements IvldItemPricingPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldItemPricingUtil} to access the ivld item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldItemPricingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingModelImpl.FINDER_CACHE_ENABLED,
            IvldItemPricingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingModelImpl.FINDER_CACHE_ENABLED,
            IvldItemPricingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDITEMPRICING = "SELECT ivldItemPricing FROM IvldItemPricing ivldItemPricing";
    private static final String _SQL_COUNT_IVLDITEMPRICING = "SELECT COUNT(ivldItemPricing) FROM IvldItemPricing ivldItemPricing";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemPricing.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemPricing exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldItemPricingPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemNo", "modifiedBy", "pricingCodeQualifierName",
                "createdDate", "endDate", "batchId", "itemName", "errorCode",
                "reprocessedFlag", "itemPricingIntfid", "ivldItemPricingSid",
                "pricingCodeStatus", "createdBy", "itemId", "errorField",
                "startDate", "itemUom", "modifiedDate", "reasonForFailure",
                "source", "pricingCodeQualifier", "addChgDelIndicator",
                "entityCode", "itemPrice", "intfInsertedDate", "checkRecord"
            });
    private static IvldItemPricing _nullIvldItemPricing = new IvldItemPricingImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldItemPricing> toCacheModel() {
                return _nullIvldItemPricingCacheModel;
            }
        };

    private static CacheModel<IvldItemPricing> _nullIvldItemPricingCacheModel = new CacheModel<IvldItemPricing>() {
            @Override
            public IvldItemPricing toEntityModel() {
                return _nullIvldItemPricing;
            }
        };

    public IvldItemPricingPersistenceImpl() {
        setModelClass(IvldItemPricing.class);
    }

    /**
     * Caches the ivld item pricing in the entity cache if it is enabled.
     *
     * @param ivldItemPricing the ivld item pricing
     */
    @Override
    public void cacheResult(IvldItemPricing ivldItemPricing) {
        EntityCacheUtil.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey(),
            ivldItemPricing);

        ivldItemPricing.resetOriginalValues();
    }

    /**
     * Caches the ivld item pricings in the entity cache if it is enabled.
     *
     * @param ivldItemPricings the ivld item pricings
     */
    @Override
    public void cacheResult(List<IvldItemPricing> ivldItemPricings) {
        for (IvldItemPricing ivldItemPricing : ivldItemPricings) {
            if (EntityCacheUtil.getResult(
                        IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemPricingImpl.class,
                        ivldItemPricing.getPrimaryKey()) == null) {
                cacheResult(ivldItemPricing);
            } else {
                ivldItemPricing.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld item pricings.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldItemPricingImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldItemPricingImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld item pricing.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldItemPricing ivldItemPricing) {
        EntityCacheUtil.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldItemPricing> ivldItemPricings) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldItemPricing ivldItemPricing : ivldItemPricings) {
            EntityCacheUtil.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
     *
     * @param ivldItemPricingSid the primary key for the new ivld item pricing
     * @return the new ivld item pricing
     */
    @Override
    public IvldItemPricing create(int ivldItemPricingSid) {
        IvldItemPricing ivldItemPricing = new IvldItemPricingImpl();

        ivldItemPricing.setNew(true);
        ivldItemPricing.setPrimaryKey(ivldItemPricingSid);

        return ivldItemPricing;
    }

    /**
     * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldItemPricingSid the primary key of the ivld item pricing
     * @return the ivld item pricing that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing remove(int ivldItemPricingSid)
        throws NoSuchIvldItemPricingException, SystemException {
        return remove((Serializable) ivldItemPricingSid);
    }

    /**
     * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld item pricing
     * @return the ivld item pricing that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing remove(Serializable primaryKey)
        throws NoSuchIvldItemPricingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldItemPricing ivldItemPricing = (IvldItemPricing) session.get(IvldItemPricingImpl.class,
                    primaryKey);

            if (ivldItemPricing == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldItemPricing);
        } catch (NoSuchIvldItemPricingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldItemPricing removeImpl(IvldItemPricing ivldItemPricing)
        throws SystemException {
        ivldItemPricing = toUnwrappedModel(ivldItemPricing);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldItemPricing)) {
                ivldItemPricing = (IvldItemPricing) session.get(IvldItemPricingImpl.class,
                        ivldItemPricing.getPrimaryKeyObj());
            }

            if (ivldItemPricing != null) {
                session.delete(ivldItemPricing);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldItemPricing != null) {
            clearCache(ivldItemPricing);
        }

        return ivldItemPricing;
    }

    @Override
    public IvldItemPricing updateImpl(
        com.stpl.app.parttwo.model.IvldItemPricing ivldItemPricing)
        throws SystemException {
        ivldItemPricing = toUnwrappedModel(ivldItemPricing);

        boolean isNew = ivldItemPricing.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldItemPricing.isNew()) {
                session.save(ivldItemPricing);

                ivldItemPricing.setNew(false);
            } else {
                session.merge(ivldItemPricing);
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

        EntityCacheUtil.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey(),
            ivldItemPricing);

        return ivldItemPricing;
    }

    protected IvldItemPricing toUnwrappedModel(IvldItemPricing ivldItemPricing) {
        if (ivldItemPricing instanceof IvldItemPricingImpl) {
            return ivldItemPricing;
        }

        IvldItemPricingImpl ivldItemPricingImpl = new IvldItemPricingImpl();

        ivldItemPricingImpl.setNew(ivldItemPricing.isNew());
        ivldItemPricingImpl.setPrimaryKey(ivldItemPricing.getPrimaryKey());

        ivldItemPricingImpl.setItemNo(ivldItemPricing.getItemNo());
        ivldItemPricingImpl.setModifiedBy(ivldItemPricing.getModifiedBy());
        ivldItemPricingImpl.setPricingCodeQualifierName(ivldItemPricing.getPricingCodeQualifierName());
        ivldItemPricingImpl.setCreatedDate(ivldItemPricing.getCreatedDate());
        ivldItemPricingImpl.setEndDate(ivldItemPricing.getEndDate());
        ivldItemPricingImpl.setBatchId(ivldItemPricing.getBatchId());
        ivldItemPricingImpl.setItemName(ivldItemPricing.getItemName());
        ivldItemPricingImpl.setErrorCode(ivldItemPricing.getErrorCode());
        ivldItemPricingImpl.setReprocessedFlag(ivldItemPricing.getReprocessedFlag());
        ivldItemPricingImpl.setItemPricingIntfid(ivldItemPricing.getItemPricingIntfid());
        ivldItemPricingImpl.setIvldItemPricingSid(ivldItemPricing.getIvldItemPricingSid());
        ivldItemPricingImpl.setPricingCodeStatus(ivldItemPricing.getPricingCodeStatus());
        ivldItemPricingImpl.setCreatedBy(ivldItemPricing.getCreatedBy());
        ivldItemPricingImpl.setItemId(ivldItemPricing.getItemId());
        ivldItemPricingImpl.setErrorField(ivldItemPricing.getErrorField());
        ivldItemPricingImpl.setStartDate(ivldItemPricing.getStartDate());
        ivldItemPricingImpl.setItemUom(ivldItemPricing.getItemUom());
        ivldItemPricingImpl.setModifiedDate(ivldItemPricing.getModifiedDate());
        ivldItemPricingImpl.setReasonForFailure(ivldItemPricing.getReasonForFailure());
        ivldItemPricingImpl.setSource(ivldItemPricing.getSource());
        ivldItemPricingImpl.setPricingCodeQualifier(ivldItemPricing.getPricingCodeQualifier());
        ivldItemPricingImpl.setAddChgDelIndicator(ivldItemPricing.getAddChgDelIndicator());
        ivldItemPricingImpl.setEntityCode(ivldItemPricing.getEntityCode());
        ivldItemPricingImpl.setItemPrice(ivldItemPricing.getItemPrice());
        ivldItemPricingImpl.setIntfInsertedDate(ivldItemPricing.getIntfInsertedDate());
        ivldItemPricingImpl.setCheckRecord(ivldItemPricing.isCheckRecord());

        return ivldItemPricingImpl;
    }

    /**
     * Returns the ivld item pricing with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item pricing
     * @return the ivld item pricing
     * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldItemPricingException, SystemException {
        IvldItemPricing ivldItemPricing = fetchByPrimaryKey(primaryKey);

        if (ivldItemPricing == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldItemPricing;
    }

    /**
     * Returns the ivld item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemPricingException} if it could not be found.
     *
     * @param ivldItemPricingSid the primary key of the ivld item pricing
     * @return the ivld item pricing
     * @throws com.stpl.app.parttwo.NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing findByPrimaryKey(int ivldItemPricingSid)
        throws NoSuchIvldItemPricingException, SystemException {
        return findByPrimaryKey((Serializable) ivldItemPricingSid);
    }

    /**
     * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item pricing
     * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldItemPricing ivldItemPricing = (IvldItemPricing) EntityCacheUtil.getResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemPricingImpl.class, primaryKey);

        if (ivldItemPricing == _nullIvldItemPricing) {
            return null;
        }

        if (ivldItemPricing == null) {
            Session session = null;

            try {
                session = openSession();

                ivldItemPricing = (IvldItemPricing) session.get(IvldItemPricingImpl.class,
                        primaryKey);

                if (ivldItemPricing != null) {
                    cacheResult(ivldItemPricing);
                } else {
                    EntityCacheUtil.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemPricingImpl.class, primaryKey,
                        _nullIvldItemPricing);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                    IvldItemPricingImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldItemPricing;
    }

    /**
     * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldItemPricingSid the primary key of the ivld item pricing
     * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemPricing fetchByPrimaryKey(int ivldItemPricingSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldItemPricingSid);
    }

    /**
     * Returns all the ivld item pricings.
     *
     * @return the ivld item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemPricing> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item pricings
     * @param end the upper bound of the range of ivld item pricings (not inclusive)
     * @return the range of ivld item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemPricing> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item pricings
     * @param end the upper bound of the range of ivld item pricings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemPricing> findAll(int start, int end,
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

        List<IvldItemPricing> list = (List<IvldItemPricing>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDITEMPRICING);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDITEMPRICING;

                if (pagination) {
                    sql = sql.concat(IvldItemPricingModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldItemPricing>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldItemPricing>(list);
                } else {
                    list = (List<IvldItemPricing>) QueryUtil.list(q,
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
     * Removes all the ivld item pricings from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldItemPricing ivldItemPricing : findAll()) {
            remove(ivldItemPricing);
        }
    }

    /**
     * Returns the number of ivld item pricings.
     *
     * @return the number of ivld item pricings
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

                Query q = session.createQuery(_SQL_COUNT_IVLDITEMPRICING);

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
     * Initializes the ivld item pricing persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldItemPricing")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldItemPricing>> listenersList = new ArrayList<ModelListener<IvldItemPricing>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldItemPricing>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldItemPricingImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
