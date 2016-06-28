package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldBestPriceException;
import com.stpl.app.model.IvldBestPrice;
import com.stpl.app.model.impl.IvldBestPriceImpl;
import com.stpl.app.model.impl.IvldBestPriceModelImpl;
import com.stpl.app.service.persistence.IvldBestPricePersistence;

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
 * The persistence implementation for the ivld best price service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldBestPricePersistence
 * @see IvldBestPriceUtil
 * @generated
 */
public class IvldBestPricePersistenceImpl extends BasePersistenceImpl<IvldBestPrice>
    implements IvldBestPricePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldBestPriceUtil} to access the ivld best price persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldBestPriceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceModelImpl.FINDER_CACHE_ENABLED,
            IvldBestPriceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceModelImpl.FINDER_CACHE_ENABLED,
            IvldBestPriceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDBESTPRICE = "SELECT ivldBestPrice FROM IvldBestPrice ivldBestPrice";
    private static final String _SQL_COUNT_IVLDBESTPRICE = "SELECT COUNT(ivldBestPrice) FROM IvldBestPrice ivldBestPrice";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldBestPrice.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldBestPrice exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldBestPricePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemDesc", "bestPriceIntfid", "accountId", "sellingPrice",
                "period", "itemId", "modifiedDate", "createdBy", "createdDate",
                "source", "addChgDelIndicator", "initialDiscount", "errorCode",
                "modifiedBy", "intfInsertedDate", "itemNo", "reprocessedFlag",
                "contractEndDate", "ivldBestPriceSid", "contractId",
                "beginningWacPackage", "reasonForFailure", "contractStartDate",
                "batchId", "errorField", "initialBestPrice", "contractNo"
            });
    private static IvldBestPrice _nullIvldBestPrice = new IvldBestPriceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldBestPrice> toCacheModel() {
                return _nullIvldBestPriceCacheModel;
            }
        };

    private static CacheModel<IvldBestPrice> _nullIvldBestPriceCacheModel = new CacheModel<IvldBestPrice>() {
            @Override
            public IvldBestPrice toEntityModel() {
                return _nullIvldBestPrice;
            }
        };

    public IvldBestPricePersistenceImpl() {
        setModelClass(IvldBestPrice.class);
    }

    /**
     * Caches the ivld best price in the entity cache if it is enabled.
     *
     * @param ivldBestPrice the ivld best price
     */
    @Override
    public void cacheResult(IvldBestPrice ivldBestPrice) {
        EntityCacheUtil.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey(),
            ivldBestPrice);

        ivldBestPrice.resetOriginalValues();
    }

    /**
     * Caches the ivld best prices in the entity cache if it is enabled.
     *
     * @param ivldBestPrices the ivld best prices
     */
    @Override
    public void cacheResult(List<IvldBestPrice> ivldBestPrices) {
        for (IvldBestPrice ivldBestPrice : ivldBestPrices) {
            if (EntityCacheUtil.getResult(
                        IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
                        IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey()) == null) {
                cacheResult(ivldBestPrice);
            } else {
                ivldBestPrice.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld best prices.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldBestPriceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldBestPriceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld best price.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldBestPrice ivldBestPrice) {
        EntityCacheUtil.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldBestPrice> ivldBestPrices) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldBestPrice ivldBestPrice : ivldBestPrices) {
            EntityCacheUtil.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
                IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
     *
     * @param ivldBestPriceSid the primary key for the new ivld best price
     * @return the new ivld best price
     */
    @Override
    public IvldBestPrice create(int ivldBestPriceSid) {
        IvldBestPrice ivldBestPrice = new IvldBestPriceImpl();

        ivldBestPrice.setNew(true);
        ivldBestPrice.setPrimaryKey(ivldBestPriceSid);

        return ivldBestPrice;
    }

    /**
     * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldBestPriceSid the primary key of the ivld best price
     * @return the ivld best price that was removed
     * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice remove(int ivldBestPriceSid)
        throws NoSuchIvldBestPriceException, SystemException {
        return remove((Serializable) ivldBestPriceSid);
    }

    /**
     * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld best price
     * @return the ivld best price that was removed
     * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice remove(Serializable primaryKey)
        throws NoSuchIvldBestPriceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldBestPrice ivldBestPrice = (IvldBestPrice) session.get(IvldBestPriceImpl.class,
                    primaryKey);

            if (ivldBestPrice == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldBestPriceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldBestPrice);
        } catch (NoSuchIvldBestPriceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldBestPrice removeImpl(IvldBestPrice ivldBestPrice)
        throws SystemException {
        ivldBestPrice = toUnwrappedModel(ivldBestPrice);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldBestPrice)) {
                ivldBestPrice = (IvldBestPrice) session.get(IvldBestPriceImpl.class,
                        ivldBestPrice.getPrimaryKeyObj());
            }

            if (ivldBestPrice != null) {
                session.delete(ivldBestPrice);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldBestPrice != null) {
            clearCache(ivldBestPrice);
        }

        return ivldBestPrice;
    }

    @Override
    public IvldBestPrice updateImpl(
        com.stpl.app.model.IvldBestPrice ivldBestPrice)
        throws SystemException {
        ivldBestPrice = toUnwrappedModel(ivldBestPrice);

        boolean isNew = ivldBestPrice.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldBestPrice.isNew()) {
                session.save(ivldBestPrice);

                ivldBestPrice.setNew(false);
            } else {
                session.merge(ivldBestPrice);
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

        EntityCacheUtil.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
            IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey(),
            ivldBestPrice);

        return ivldBestPrice;
    }

    protected IvldBestPrice toUnwrappedModel(IvldBestPrice ivldBestPrice) {
        if (ivldBestPrice instanceof IvldBestPriceImpl) {
            return ivldBestPrice;
        }

        IvldBestPriceImpl ivldBestPriceImpl = new IvldBestPriceImpl();

        ivldBestPriceImpl.setNew(ivldBestPrice.isNew());
        ivldBestPriceImpl.setPrimaryKey(ivldBestPrice.getPrimaryKey());

        ivldBestPriceImpl.setItemDesc(ivldBestPrice.getItemDesc());
        ivldBestPriceImpl.setBestPriceIntfid(ivldBestPrice.getBestPriceIntfid());
        ivldBestPriceImpl.setAccountId(ivldBestPrice.getAccountId());
        ivldBestPriceImpl.setSellingPrice(ivldBestPrice.getSellingPrice());
        ivldBestPriceImpl.setPeriod(ivldBestPrice.getPeriod());
        ivldBestPriceImpl.setItemId(ivldBestPrice.getItemId());
        ivldBestPriceImpl.setModifiedDate(ivldBestPrice.getModifiedDate());
        ivldBestPriceImpl.setCreatedBy(ivldBestPrice.getCreatedBy());
        ivldBestPriceImpl.setCreatedDate(ivldBestPrice.getCreatedDate());
        ivldBestPriceImpl.setSource(ivldBestPrice.getSource());
        ivldBestPriceImpl.setAddChgDelIndicator(ivldBestPrice.getAddChgDelIndicator());
        ivldBestPriceImpl.setInitialDiscount(ivldBestPrice.getInitialDiscount());
        ivldBestPriceImpl.setErrorCode(ivldBestPrice.getErrorCode());
        ivldBestPriceImpl.setModifiedBy(ivldBestPrice.getModifiedBy());
        ivldBestPriceImpl.setIntfInsertedDate(ivldBestPrice.getIntfInsertedDate());
        ivldBestPriceImpl.setItemNo(ivldBestPrice.getItemNo());
        ivldBestPriceImpl.setReprocessedFlag(ivldBestPrice.getReprocessedFlag());
        ivldBestPriceImpl.setContractEndDate(ivldBestPrice.getContractEndDate());
        ivldBestPriceImpl.setIvldBestPriceSid(ivldBestPrice.getIvldBestPriceSid());
        ivldBestPriceImpl.setContractId(ivldBestPrice.getContractId());
        ivldBestPriceImpl.setBeginningWacPackage(ivldBestPrice.getBeginningWacPackage());
        ivldBestPriceImpl.setReasonForFailure(ivldBestPrice.getReasonForFailure());
        ivldBestPriceImpl.setContractStartDate(ivldBestPrice.getContractStartDate());
        ivldBestPriceImpl.setBatchId(ivldBestPrice.getBatchId());
        ivldBestPriceImpl.setErrorField(ivldBestPrice.getErrorField());
        ivldBestPriceImpl.setInitialBestPrice(ivldBestPrice.getInitialBestPrice());
        ivldBestPriceImpl.setContractNo(ivldBestPrice.getContractNo());

        return ivldBestPriceImpl;
    }

    /**
     * Returns the ivld best price with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld best price
     * @return the ivld best price
     * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldBestPriceException, SystemException {
        IvldBestPrice ivldBestPrice = fetchByPrimaryKey(primaryKey);

        if (ivldBestPrice == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldBestPriceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldBestPrice;
    }

    /**
     * Returns the ivld best price with the primary key or throws a {@link com.stpl.app.NoSuchIvldBestPriceException} if it could not be found.
     *
     * @param ivldBestPriceSid the primary key of the ivld best price
     * @return the ivld best price
     * @throws com.stpl.app.NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice findByPrimaryKey(int ivldBestPriceSid)
        throws NoSuchIvldBestPriceException, SystemException {
        return findByPrimaryKey((Serializable) ivldBestPriceSid);
    }

    /**
     * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld best price
     * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldBestPrice ivldBestPrice = (IvldBestPrice) EntityCacheUtil.getResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
                IvldBestPriceImpl.class, primaryKey);

        if (ivldBestPrice == _nullIvldBestPrice) {
            return null;
        }

        if (ivldBestPrice == null) {
            Session session = null;

            try {
                session = openSession();

                ivldBestPrice = (IvldBestPrice) session.get(IvldBestPriceImpl.class,
                        primaryKey);

                if (ivldBestPrice != null) {
                    cacheResult(ivldBestPrice);
                } else {
                    EntityCacheUtil.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
                        IvldBestPriceImpl.class, primaryKey, _nullIvldBestPrice);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
                    IvldBestPriceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldBestPrice;
    }

    /**
     * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldBestPriceSid the primary key of the ivld best price
     * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldBestPrice fetchByPrimaryKey(int ivldBestPriceSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldBestPriceSid);
    }

    /**
     * Returns all the ivld best prices.
     *
     * @return the ivld best prices
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldBestPrice> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld best prices.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld best prices
     * @param end the upper bound of the range of ivld best prices (not inclusive)
     * @return the range of ivld best prices
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldBestPrice> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld best prices.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld best prices
     * @param end the upper bound of the range of ivld best prices (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld best prices
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldBestPrice> findAll(int start, int end,
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

        List<IvldBestPrice> list = (List<IvldBestPrice>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDBESTPRICE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDBESTPRICE;

                if (pagination) {
                    sql = sql.concat(IvldBestPriceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldBestPrice>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldBestPrice>(list);
                } else {
                    list = (List<IvldBestPrice>) QueryUtil.list(q,
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
     * Removes all the ivld best prices from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldBestPrice ivldBestPrice : findAll()) {
            remove(ivldBestPrice);
        }
    }

    /**
     * Returns the number of ivld best prices.
     *
     * @return the number of ivld best prices
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

                Query q = session.createQuery(_SQL_COUNT_IVLDBESTPRICE);

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
     * Initializes the ivld best price persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldBestPrice")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldBestPrice>> listenersList = new ArrayList<ModelListener<IvldBestPrice>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldBestPrice>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldBestPriceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
