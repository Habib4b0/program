package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchInventoryWdActualMasException;
import com.stpl.app.model.InventoryWdActualMas;
import com.stpl.app.model.impl.InventoryWdActualMasImpl;
import com.stpl.app.model.impl.InventoryWdActualMasModelImpl;
import com.stpl.app.service.persistence.InventoryWdActualMasPersistence;

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
 * The persistence implementation for the inventory wd actual mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdActualMasPersistence
 * @see InventoryWdActualMasUtil
 * @generated
 */
public class InventoryWdActualMasPersistenceImpl extends BasePersistenceImpl<InventoryWdActualMas>
    implements InventoryWdActualMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link InventoryWdActualMasUtil} to access the inventory wd actual mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = InventoryWdActualMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
            InventoryWdActualMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
            InventoryWdActualMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_INVENTORYWDACTUALMAS = "SELECT inventoryWdActualMas FROM InventoryWdActualMas inventoryWdActualMas";
    private static final String _SQL_COUNT_INVENTORYWDACTUALMAS = "SELECT COUNT(inventoryWdActualMas) FROM InventoryWdActualMas inventoryWdActualMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "inventoryWdActualMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InventoryWdActualMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(InventoryWdActualMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "quantityOnOrder", "week", "amountOnHand", "itemMasterSid",
                "inventoryWdActualMasSid", "year", "itemId", "modifiedDate",
                "organizationKey", "source", "createdBy", "createdDate", "day",
                "unitsOnHand", "amountReceived", "itemIdentifier", "modifiedBy",
                "inboundStatus", "month", "amountWithdrawn", "quantityReceived",
                "unitsWithdrawn", "country", "recordLockStatus",
                "itemIdentifierCodeQualifier", "batchId", "amountOnOrder"
            });
    private static InventoryWdActualMas _nullInventoryWdActualMas = new InventoryWdActualMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<InventoryWdActualMas> toCacheModel() {
                return _nullInventoryWdActualMasCacheModel;
            }
        };

    private static CacheModel<InventoryWdActualMas> _nullInventoryWdActualMasCacheModel =
        new CacheModel<InventoryWdActualMas>() {
            @Override
            public InventoryWdActualMas toEntityModel() {
                return _nullInventoryWdActualMas;
            }
        };

    public InventoryWdActualMasPersistenceImpl() {
        setModelClass(InventoryWdActualMas.class);
    }

    /**
     * Caches the inventory wd actual mas in the entity cache if it is enabled.
     *
     * @param inventoryWdActualMas the inventory wd actual mas
     */
    @Override
    public void cacheResult(InventoryWdActualMas inventoryWdActualMas) {
        EntityCacheUtil.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasImpl.class,
            inventoryWdActualMas.getPrimaryKey(), inventoryWdActualMas);

        inventoryWdActualMas.resetOriginalValues();
    }

    /**
     * Caches the inventory wd actual mases in the entity cache if it is enabled.
     *
     * @param inventoryWdActualMases the inventory wd actual mases
     */
    @Override
    public void cacheResult(List<InventoryWdActualMas> inventoryWdActualMases) {
        for (InventoryWdActualMas inventoryWdActualMas : inventoryWdActualMases) {
            if (EntityCacheUtil.getResult(
                        InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                        InventoryWdActualMasImpl.class,
                        inventoryWdActualMas.getPrimaryKey()) == null) {
                cacheResult(inventoryWdActualMas);
            } else {
                inventoryWdActualMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all inventory wd actual mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(InventoryWdActualMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(InventoryWdActualMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the inventory wd actual mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(InventoryWdActualMas inventoryWdActualMas) {
        EntityCacheUtil.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasImpl.class, inventoryWdActualMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<InventoryWdActualMas> inventoryWdActualMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (InventoryWdActualMas inventoryWdActualMas : inventoryWdActualMases) {
            EntityCacheUtil.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                InventoryWdActualMasImpl.class,
                inventoryWdActualMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
     *
     * @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
     * @return the new inventory wd actual mas
     */
    @Override
    public InventoryWdActualMas create(int inventoryWdActualMasSid) {
        InventoryWdActualMas inventoryWdActualMas = new InventoryWdActualMasImpl();

        inventoryWdActualMas.setNew(true);
        inventoryWdActualMas.setPrimaryKey(inventoryWdActualMasSid);

        return inventoryWdActualMas;
    }

    /**
     * Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas that was removed
     * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas remove(int inventoryWdActualMasSid)
        throws NoSuchInventoryWdActualMasException, SystemException {
        return remove((Serializable) inventoryWdActualMasSid);
    }

    /**
     * Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas that was removed
     * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas remove(Serializable primaryKey)
        throws NoSuchInventoryWdActualMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            InventoryWdActualMas inventoryWdActualMas = (InventoryWdActualMas) session.get(InventoryWdActualMasImpl.class,
                    primaryKey);

            if (inventoryWdActualMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(inventoryWdActualMas);
        } catch (NoSuchInventoryWdActualMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected InventoryWdActualMas removeImpl(
        InventoryWdActualMas inventoryWdActualMas) throws SystemException {
        inventoryWdActualMas = toUnwrappedModel(inventoryWdActualMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(inventoryWdActualMas)) {
                inventoryWdActualMas = (InventoryWdActualMas) session.get(InventoryWdActualMasImpl.class,
                        inventoryWdActualMas.getPrimaryKeyObj());
            }

            if (inventoryWdActualMas != null) {
                session.delete(inventoryWdActualMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (inventoryWdActualMas != null) {
            clearCache(inventoryWdActualMas);
        }

        return inventoryWdActualMas;
    }

    @Override
    public InventoryWdActualMas updateImpl(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas)
        throws SystemException {
        inventoryWdActualMas = toUnwrappedModel(inventoryWdActualMas);

        boolean isNew = inventoryWdActualMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (inventoryWdActualMas.isNew()) {
                session.save(inventoryWdActualMas);

                inventoryWdActualMas.setNew(false);
            } else {
                session.merge(inventoryWdActualMas);
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

        EntityCacheUtil.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdActualMasImpl.class,
            inventoryWdActualMas.getPrimaryKey(), inventoryWdActualMas);

        return inventoryWdActualMas;
    }

    protected InventoryWdActualMas toUnwrappedModel(
        InventoryWdActualMas inventoryWdActualMas) {
        if (inventoryWdActualMas instanceof InventoryWdActualMasImpl) {
            return inventoryWdActualMas;
        }

        InventoryWdActualMasImpl inventoryWdActualMasImpl = new InventoryWdActualMasImpl();

        inventoryWdActualMasImpl.setNew(inventoryWdActualMas.isNew());
        inventoryWdActualMasImpl.setPrimaryKey(inventoryWdActualMas.getPrimaryKey());

        inventoryWdActualMasImpl.setQuantityOnOrder(inventoryWdActualMas.getQuantityOnOrder());
        inventoryWdActualMasImpl.setWeek(inventoryWdActualMas.getWeek());
        inventoryWdActualMasImpl.setAmountOnHand(inventoryWdActualMas.getAmountOnHand());
        inventoryWdActualMasImpl.setItemMasterSid(inventoryWdActualMas.getItemMasterSid());
        inventoryWdActualMasImpl.setInventoryWdActualMasSid(inventoryWdActualMas.getInventoryWdActualMasSid());
        inventoryWdActualMasImpl.setYear(inventoryWdActualMas.getYear());
        inventoryWdActualMasImpl.setItemId(inventoryWdActualMas.getItemId());
        inventoryWdActualMasImpl.setModifiedDate(inventoryWdActualMas.getModifiedDate());
        inventoryWdActualMasImpl.setOrganizationKey(inventoryWdActualMas.getOrganizationKey());
        inventoryWdActualMasImpl.setSource(inventoryWdActualMas.getSource());
        inventoryWdActualMasImpl.setCreatedBy(inventoryWdActualMas.getCreatedBy());
        inventoryWdActualMasImpl.setCreatedDate(inventoryWdActualMas.getCreatedDate());
        inventoryWdActualMasImpl.setDay(inventoryWdActualMas.getDay());
        inventoryWdActualMasImpl.setUnitsOnHand(inventoryWdActualMas.getUnitsOnHand());
        inventoryWdActualMasImpl.setAmountReceived(inventoryWdActualMas.getAmountReceived());
        inventoryWdActualMasImpl.setItemIdentifier(inventoryWdActualMas.getItemIdentifier());
        inventoryWdActualMasImpl.setModifiedBy(inventoryWdActualMas.getModifiedBy());
        inventoryWdActualMasImpl.setInboundStatus(inventoryWdActualMas.getInboundStatus());
        inventoryWdActualMasImpl.setMonth(inventoryWdActualMas.getMonth());
        inventoryWdActualMasImpl.setAmountWithdrawn(inventoryWdActualMas.getAmountWithdrawn());
        inventoryWdActualMasImpl.setQuantityReceived(inventoryWdActualMas.getQuantityReceived());
        inventoryWdActualMasImpl.setUnitsWithdrawn(inventoryWdActualMas.getUnitsWithdrawn());
        inventoryWdActualMasImpl.setCountry(inventoryWdActualMas.getCountry());
        inventoryWdActualMasImpl.setRecordLockStatus(inventoryWdActualMas.isRecordLockStatus());
        inventoryWdActualMasImpl.setItemIdentifierCodeQualifier(inventoryWdActualMas.getItemIdentifierCodeQualifier());
        inventoryWdActualMasImpl.setBatchId(inventoryWdActualMas.getBatchId());
        inventoryWdActualMasImpl.setAmountOnOrder(inventoryWdActualMas.getAmountOnOrder());

        return inventoryWdActualMasImpl;
    }

    /**
     * Returns the inventory wd actual mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas
     * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas findByPrimaryKey(Serializable primaryKey)
        throws NoSuchInventoryWdActualMasException, SystemException {
        InventoryWdActualMas inventoryWdActualMas = fetchByPrimaryKey(primaryKey);

        if (inventoryWdActualMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return inventoryWdActualMas;
    }

    /**
     * Returns the inventory wd actual mas with the primary key or throws a {@link com.stpl.app.NoSuchInventoryWdActualMasException} if it could not be found.
     *
     * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas
     * @throws com.stpl.app.NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas findByPrimaryKey(int inventoryWdActualMasSid)
        throws NoSuchInventoryWdActualMasException, SystemException {
        return findByPrimaryKey((Serializable) inventoryWdActualMasSid);
    }

    /**
     * Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        InventoryWdActualMas inventoryWdActualMas = (InventoryWdActualMas) EntityCacheUtil.getResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                InventoryWdActualMasImpl.class, primaryKey);

        if (inventoryWdActualMas == _nullInventoryWdActualMas) {
            return null;
        }

        if (inventoryWdActualMas == null) {
            Session session = null;

            try {
                session = openSession();

                inventoryWdActualMas = (InventoryWdActualMas) session.get(InventoryWdActualMasImpl.class,
                        primaryKey);

                if (inventoryWdActualMas != null) {
                    cacheResult(inventoryWdActualMas);
                } else {
                    EntityCacheUtil.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                        InventoryWdActualMasImpl.class, primaryKey,
                        _nullInventoryWdActualMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
                    InventoryWdActualMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return inventoryWdActualMas;
    }

    /**
     * Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
     * @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdActualMas fetchByPrimaryKey(int inventoryWdActualMasSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) inventoryWdActualMasSid);
    }

    /**
     * Returns all the inventory wd actual mases.
     *
     * @return the inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdActualMas> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the inventory wd actual mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of inventory wd actual mases
     * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
     * @return the range of inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdActualMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the inventory wd actual mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of inventory wd actual mases
     * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of inventory wd actual mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdActualMas> findAll(int start, int end,
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

        List<InventoryWdActualMas> list = (List<InventoryWdActualMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_INVENTORYWDACTUALMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_INVENTORYWDACTUALMAS;

                if (pagination) {
                    sql = sql.concat(InventoryWdActualMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<InventoryWdActualMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<InventoryWdActualMas>(list);
                } else {
                    list = (List<InventoryWdActualMas>) QueryUtil.list(q,
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
     * Removes all the inventory wd actual mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (InventoryWdActualMas inventoryWdActualMas : findAll()) {
            remove(inventoryWdActualMas);
        }
    }

    /**
     * Returns the number of inventory wd actual mases.
     *
     * @return the number of inventory wd actual mases
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

                Query q = session.createQuery(_SQL_COUNT_INVENTORYWDACTUALMAS);

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
     * Initializes the inventory wd actual mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.InventoryWdActualMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<InventoryWdActualMas>> listenersList = new ArrayList<ModelListener<InventoryWdActualMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<InventoryWdActualMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(InventoryWdActualMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
