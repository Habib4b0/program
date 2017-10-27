package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwItemPricingException;
import com.stpl.app.parttwo.model.VwItemPricing;
import com.stpl.app.parttwo.model.impl.VwItemPricingImpl;
import com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl;
import com.stpl.app.parttwo.service.persistence.VwItemPricingPersistence;

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
 * The persistence implementation for the vw item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemPricingPersistence
 * @see VwItemPricingUtil
 * @generated
 */
public class VwItemPricingPersistenceImpl extends BasePersistenceImpl<VwItemPricing>
    implements VwItemPricingPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwItemPricingUtil} to access the vw item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwItemPricingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingModelImpl.FINDER_CACHE_ENABLED,
            VwItemPricingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingModelImpl.FINDER_CACHE_ENABLED,
            VwItemPricingImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWITEMPRICING = "SELECT vwItemPricing FROM VwItemPricing vwItemPricing";
    private static final String _SQL_COUNT_VWITEMPRICING = "SELECT COUNT(vwItemPricing) FROM VwItemPricing vwItemPricing";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwItemPricing.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwItemPricing exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwItemPricingPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "pricingCodeQualifier", "itemPrice", "endDate", "itemId",
                "modifiedDate", "entityCode", "startDate", "createdDate",
                "createdBy", "source", "batchId", "addChgDelIndicator",
                "itemName", "itemUom", "modifiedBy", "itemNo", "itemPricingSid",
                "pricingCodeStatus", "pricingCodeQualifierName",
                "itemPriceprecision"
            });
    private static VwItemPricing _nullVwItemPricing = new VwItemPricingImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwItemPricing> toCacheModel() {
                return _nullVwItemPricingCacheModel;
            }
        };

    private static CacheModel<VwItemPricing> _nullVwItemPricingCacheModel = new CacheModel<VwItemPricing>() {
            @Override
            public VwItemPricing toEntityModel() {
                return _nullVwItemPricing;
            }
        };

    public VwItemPricingPersistenceImpl() {
        setModelClass(VwItemPricing.class);
    }

    /**
     * Caches the vw item pricing in the entity cache if it is enabled.
     *
     * @param vwItemPricing the vw item pricing
     */
    @Override
    public void cacheResult(VwItemPricing vwItemPricing) {
        EntityCacheUtil.putResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingImpl.class, vwItemPricing.getPrimaryKey(),
            vwItemPricing);

        vwItemPricing.resetOriginalValues();
    }

    /**
     * Caches the vw item pricings in the entity cache if it is enabled.
     *
     * @param vwItemPricings the vw item pricings
     */
    @Override
    public void cacheResult(List<VwItemPricing> vwItemPricings) {
        for (VwItemPricing vwItemPricing : vwItemPricings) {
            if (EntityCacheUtil.getResult(
                        VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemPricingImpl.class, vwItemPricing.getPrimaryKey()) == null) {
                cacheResult(vwItemPricing);
            } else {
                vwItemPricing.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw item pricings.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwItemPricingImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwItemPricingImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw item pricing.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwItemPricing vwItemPricing) {
        EntityCacheUtil.removeResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingImpl.class, vwItemPricing.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwItemPricing> vwItemPricings) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwItemPricing vwItemPricing : vwItemPricings) {
            EntityCacheUtil.removeResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                VwItemPricingImpl.class, vwItemPricing.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
     *
     * @param itemPricingSid the primary key for the new vw item pricing
     * @return the new vw item pricing
     */
    @Override
    public VwItemPricing create(int itemPricingSid) {
        VwItemPricing vwItemPricing = new VwItemPricingImpl();

        vwItemPricing.setNew(true);
        vwItemPricing.setPrimaryKey(itemPricingSid);

        return vwItemPricing;
    }

    /**
     * Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemPricingSid the primary key of the vw item pricing
     * @return the vw item pricing that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing remove(int itemPricingSid)
        throws NoSuchVwItemPricingException, SystemException {
        return remove((Serializable) itemPricingSid);
    }

    /**
     * Removes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw item pricing
     * @return the vw item pricing that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing remove(Serializable primaryKey)
        throws NoSuchVwItemPricingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwItemPricing vwItemPricing = (VwItemPricing) session.get(VwItemPricingImpl.class,
                    primaryKey);

            if (vwItemPricing == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwItemPricing);
        } catch (NoSuchVwItemPricingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwItemPricing removeImpl(VwItemPricing vwItemPricing)
        throws SystemException {
        vwItemPricing = toUnwrappedModel(vwItemPricing);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwItemPricing)) {
                vwItemPricing = (VwItemPricing) session.get(VwItemPricingImpl.class,
                        vwItemPricing.getPrimaryKeyObj());
            }

            if (vwItemPricing != null) {
                session.delete(vwItemPricing);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwItemPricing != null) {
            clearCache(vwItemPricing);
        }

        return vwItemPricing;
    }

    @Override
    public VwItemPricing updateImpl(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws SystemException {
        vwItemPricing = toUnwrappedModel(vwItemPricing);

        boolean isNew = vwItemPricing.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwItemPricing.isNew()) {
                session.save(vwItemPricing);

                vwItemPricing.setNew(false);
            } else {
                session.merge(vwItemPricing);
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

        EntityCacheUtil.putResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
            VwItemPricingImpl.class, vwItemPricing.getPrimaryKey(),
            vwItemPricing);

        return vwItemPricing;
    }

    protected VwItemPricing toUnwrappedModel(VwItemPricing vwItemPricing) {
        if (vwItemPricing instanceof VwItemPricingImpl) {
            return vwItemPricing;
        }

        VwItemPricingImpl vwItemPricingImpl = new VwItemPricingImpl();

        vwItemPricingImpl.setNew(vwItemPricing.isNew());
        vwItemPricingImpl.setPrimaryKey(vwItemPricing.getPrimaryKey());

        vwItemPricingImpl.setPricingCodeQualifier(vwItemPricing.getPricingCodeQualifier());
        vwItemPricingImpl.setItemPrice(vwItemPricing.getItemPrice());
        vwItemPricingImpl.setEndDate(vwItemPricing.getEndDate());
        vwItemPricingImpl.setItemId(vwItemPricing.getItemId());
        vwItemPricingImpl.setModifiedDate(vwItemPricing.getModifiedDate());
        vwItemPricingImpl.setEntityCode(vwItemPricing.getEntityCode());
        vwItemPricingImpl.setStartDate(vwItemPricing.getStartDate());
        vwItemPricingImpl.setCreatedDate(vwItemPricing.getCreatedDate());
        vwItemPricingImpl.setCreatedBy(vwItemPricing.getCreatedBy());
        vwItemPricingImpl.setSource(vwItemPricing.getSource());
        vwItemPricingImpl.setBatchId(vwItemPricing.getBatchId());
        vwItemPricingImpl.setAddChgDelIndicator(vwItemPricing.getAddChgDelIndicator());
        vwItemPricingImpl.setItemName(vwItemPricing.getItemName());
        vwItemPricingImpl.setItemUom(vwItemPricing.getItemUom());
        vwItemPricingImpl.setModifiedBy(vwItemPricing.getModifiedBy());
        vwItemPricingImpl.setItemNo(vwItemPricing.getItemNo());
        vwItemPricingImpl.setItemPricingSid(vwItemPricing.getItemPricingSid());
        vwItemPricingImpl.setPricingCodeStatus(vwItemPricing.getPricingCodeStatus());
        vwItemPricingImpl.setPricingCodeQualifierName(vwItemPricing.getPricingCodeQualifierName());
        vwItemPricingImpl.setItemPriceprecision(vwItemPricing.getItemPriceprecision());

        return vwItemPricingImpl;
    }

    /**
     * Returns the vw item pricing with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw item pricing
     * @return the vw item pricing
     * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwItemPricingException, SystemException {
        VwItemPricing vwItemPricing = fetchByPrimaryKey(primaryKey);

        if (vwItemPricing == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwItemPricing;
    }

    /**
     * Returns the vw item pricing with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemPricingException} if it could not be found.
     *
     * @param itemPricingSid the primary key of the vw item pricing
     * @return the vw item pricing
     * @throws com.stpl.app.parttwo.NoSuchVwItemPricingException if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing findByPrimaryKey(int itemPricingSid)
        throws NoSuchVwItemPricingException, SystemException {
        return findByPrimaryKey((Serializable) itemPricingSid);
    }

    /**
     * Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw item pricing
     * @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwItemPricing vwItemPricing = (VwItemPricing) EntityCacheUtil.getResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                VwItemPricingImpl.class, primaryKey);

        if (vwItemPricing == _nullVwItemPricing) {
            return null;
        }

        if (vwItemPricing == null) {
            Session session = null;

            try {
                session = openSession();

                vwItemPricing = (VwItemPricing) session.get(VwItemPricingImpl.class,
                        primaryKey);

                if (vwItemPricing != null) {
                    cacheResult(vwItemPricing);
                } else {
                    EntityCacheUtil.putResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemPricingImpl.class, primaryKey, _nullVwItemPricing);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwItemPricingModelImpl.ENTITY_CACHE_ENABLED,
                    VwItemPricingImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwItemPricing;
    }

    /**
     * Returns the vw item pricing with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemPricingSid the primary key of the vw item pricing
     * @return the vw item pricing, or <code>null</code> if a vw item pricing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemPricing fetchByPrimaryKey(int itemPricingSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemPricingSid);
    }

    /**
     * Returns all the vw item pricings.
     *
     * @return the vw item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemPricing> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item pricings
     * @param end the upper bound of the range of vw item pricings (not inclusive)
     * @return the range of vw item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemPricing> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw item pricings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item pricings
     * @param end the upper bound of the range of vw item pricings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw item pricings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemPricing> findAll(int start, int end,
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

        List<VwItemPricing> list = (List<VwItemPricing>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWITEMPRICING);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWITEMPRICING;

                if (pagination) {
                    sql = sql.concat(VwItemPricingModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwItemPricing>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwItemPricing>(list);
                } else {
                    list = (List<VwItemPricing>) QueryUtil.list(q,
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
     * Removes all the vw item pricings from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwItemPricing vwItemPricing : findAll()) {
            remove(vwItemPricing);
        }
    }

    /**
     * Returns the number of vw item pricings.
     *
     * @return the number of vw item pricings
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

                Query q = session.createQuery(_SQL_COUNT_VWITEMPRICING);

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
     * Initializes the vw item pricing persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwItemPricing")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwItemPricing>> listenersList = new ArrayList<ModelListener<VwItemPricing>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwItemPricing>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwItemPricingImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
