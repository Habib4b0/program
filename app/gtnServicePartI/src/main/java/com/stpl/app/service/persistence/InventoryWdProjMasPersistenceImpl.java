package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchInventoryWdProjMasException;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.model.impl.InventoryWdProjMasImpl;
import com.stpl.app.model.impl.InventoryWdProjMasModelImpl;
import com.stpl.app.service.persistence.InventoryWdProjMasPersistence;

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
 * The persistence implementation for the inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdProjMasPersistence
 * @see InventoryWdProjMasUtil
 * @generated
 */
public class InventoryWdProjMasPersistenceImpl extends BasePersistenceImpl<InventoryWdProjMas>
    implements InventoryWdProjMasPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link InventoryWdProjMasUtil} to access the inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = InventoryWdProjMasImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
            InventoryWdProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
            InventoryWdProjMasImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_INVENTORYWDPROJMAS = "SELECT inventoryWdProjMas FROM InventoryWdProjMas inventoryWdProjMas";
    private static final String _SQL_COUNT_INVENTORYWDPROJMAS = "SELECT COUNT(inventoryWdProjMas) FROM InventoryWdProjMas inventoryWdProjMas";
    private static final String _ORDER_BY_ENTITY_ALIAS = "inventoryWdProjMas.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InventoryWdProjMas exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(InventoryWdProjMasPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "week", "itemMasterSid", "unitsWithdrawn", "country", "year",
                "itemId", "modifiedDate", "organizationKey", "recordLockStatus",
                "itemIdentifierCodeQualifier", "source", "createdDate",
                "createdBy", "inventoryWdProjMasSid", "day", "forecastVer",
                "batchId", "itemIdentifier", "inboundStatus", "modifiedBy",
                "month", "forecastName", "amountWithdrawn"
            });
    private static InventoryWdProjMas _nullInventoryWdProjMas = new InventoryWdProjMasImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<InventoryWdProjMas> toCacheModel() {
                return _nullInventoryWdProjMasCacheModel;
            }
        };

    private static CacheModel<InventoryWdProjMas> _nullInventoryWdProjMasCacheModel =
        new CacheModel<InventoryWdProjMas>() {
            @Override
            public InventoryWdProjMas toEntityModel() {
                return _nullInventoryWdProjMas;
            }
        };

    public InventoryWdProjMasPersistenceImpl() {
        setModelClass(InventoryWdProjMas.class);
    }

    /**
     * Caches the inventory wd proj mas in the entity cache if it is enabled.
     *
     * @param inventoryWdProjMas the inventory wd proj mas
     */
    @Override
    public void cacheResult(InventoryWdProjMas inventoryWdProjMas) {
        EntityCacheUtil.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey(),
            inventoryWdProjMas);

        inventoryWdProjMas.resetOriginalValues();
    }

    /**
     * Caches the inventory wd proj mases in the entity cache if it is enabled.
     *
     * @param inventoryWdProjMases the inventory wd proj mases
     */
    @Override
    public void cacheResult(List<InventoryWdProjMas> inventoryWdProjMases) {
        for (InventoryWdProjMas inventoryWdProjMas : inventoryWdProjMases) {
            if (EntityCacheUtil.getResult(
                        InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        InventoryWdProjMasImpl.class,
                        inventoryWdProjMas.getPrimaryKey()) == null) {
                cacheResult(inventoryWdProjMas);
            } else {
                inventoryWdProjMas.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all inventory wd proj mases.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(InventoryWdProjMasImpl.class.getName());
        }

        EntityCacheUtil.clearCache(InventoryWdProjMasImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the inventory wd proj mas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(InventoryWdProjMas inventoryWdProjMas) {
        EntityCacheUtil.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<InventoryWdProjMas> inventoryWdProjMases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (InventoryWdProjMas inventoryWdProjMas : inventoryWdProjMases) {
            EntityCacheUtil.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey());
        }
    }

    /**
     * Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
     *
     * @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
     * @return the new inventory wd proj mas
     */
    @Override
    public InventoryWdProjMas create(int inventoryWdProjMasSid) {
        InventoryWdProjMas inventoryWdProjMas = new InventoryWdProjMasImpl();

        inventoryWdProjMas.setNew(true);
        inventoryWdProjMas.setPrimaryKey(inventoryWdProjMasSid);

        return inventoryWdProjMas;
    }

    /**
     * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas that was removed
     * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas remove(int inventoryWdProjMasSid)
        throws NoSuchInventoryWdProjMasException, SystemException {
        return remove((Serializable) inventoryWdProjMasSid);
    }

    /**
     * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas that was removed
     * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas remove(Serializable primaryKey)
        throws NoSuchInventoryWdProjMasException, SystemException {
        Session session = null;

        try {
            session = openSession();

            InventoryWdProjMas inventoryWdProjMas = (InventoryWdProjMas) session.get(InventoryWdProjMasImpl.class,
                    primaryKey);

            if (inventoryWdProjMas == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(inventoryWdProjMas);
        } catch (NoSuchInventoryWdProjMasException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected InventoryWdProjMas removeImpl(
        InventoryWdProjMas inventoryWdProjMas) throws SystemException {
        inventoryWdProjMas = toUnwrappedModel(inventoryWdProjMas);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(inventoryWdProjMas)) {
                inventoryWdProjMas = (InventoryWdProjMas) session.get(InventoryWdProjMasImpl.class,
                        inventoryWdProjMas.getPrimaryKeyObj());
            }

            if (inventoryWdProjMas != null) {
                session.delete(inventoryWdProjMas);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (inventoryWdProjMas != null) {
            clearCache(inventoryWdProjMas);
        }

        return inventoryWdProjMas;
    }

    @Override
    public InventoryWdProjMas updateImpl(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws SystemException {
        inventoryWdProjMas = toUnwrappedModel(inventoryWdProjMas);

        boolean isNew = inventoryWdProjMas.isNew();

        Session session = null;

        try {
            session = openSession();

            if (inventoryWdProjMas.isNew()) {
                session.save(inventoryWdProjMas);

                inventoryWdProjMas.setNew(false);
            } else {
                session.merge(inventoryWdProjMas);
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

        EntityCacheUtil.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
            InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey(),
            inventoryWdProjMas);

        return inventoryWdProjMas;
    }

    protected InventoryWdProjMas toUnwrappedModel(
        InventoryWdProjMas inventoryWdProjMas) {
        if (inventoryWdProjMas instanceof InventoryWdProjMasImpl) {
            return inventoryWdProjMas;
        }

        InventoryWdProjMasImpl inventoryWdProjMasImpl = new InventoryWdProjMasImpl();

        inventoryWdProjMasImpl.setNew(inventoryWdProjMas.isNew());
        inventoryWdProjMasImpl.setPrimaryKey(inventoryWdProjMas.getPrimaryKey());

        inventoryWdProjMasImpl.setWeek(inventoryWdProjMas.getWeek());
        inventoryWdProjMasImpl.setItemMasterSid(inventoryWdProjMas.getItemMasterSid());
        inventoryWdProjMasImpl.setUnitsWithdrawn(inventoryWdProjMas.getUnitsWithdrawn());
        inventoryWdProjMasImpl.setCountry(inventoryWdProjMas.getCountry());
        inventoryWdProjMasImpl.setYear(inventoryWdProjMas.getYear());
        inventoryWdProjMasImpl.setItemId(inventoryWdProjMas.getItemId());
        inventoryWdProjMasImpl.setModifiedDate(inventoryWdProjMas.getModifiedDate());
        inventoryWdProjMasImpl.setOrganizationKey(inventoryWdProjMas.getOrganizationKey());
        inventoryWdProjMasImpl.setRecordLockStatus(inventoryWdProjMas.isRecordLockStatus());
        inventoryWdProjMasImpl.setItemIdentifierCodeQualifier(inventoryWdProjMas.getItemIdentifierCodeQualifier());
        inventoryWdProjMasImpl.setSource(inventoryWdProjMas.getSource());
        inventoryWdProjMasImpl.setCreatedDate(inventoryWdProjMas.getCreatedDate());
        inventoryWdProjMasImpl.setCreatedBy(inventoryWdProjMas.getCreatedBy());
        inventoryWdProjMasImpl.setInventoryWdProjMasSid(inventoryWdProjMas.getInventoryWdProjMasSid());
        inventoryWdProjMasImpl.setDay(inventoryWdProjMas.getDay());
        inventoryWdProjMasImpl.setForecastVer(inventoryWdProjMas.getForecastVer());
        inventoryWdProjMasImpl.setBatchId(inventoryWdProjMas.getBatchId());
        inventoryWdProjMasImpl.setItemIdentifier(inventoryWdProjMas.getItemIdentifier());
        inventoryWdProjMasImpl.setInboundStatus(inventoryWdProjMas.getInboundStatus());
        inventoryWdProjMasImpl.setModifiedBy(inventoryWdProjMas.getModifiedBy());
        inventoryWdProjMasImpl.setMonth(inventoryWdProjMas.getMonth());
        inventoryWdProjMasImpl.setForecastName(inventoryWdProjMas.getForecastName());
        inventoryWdProjMasImpl.setAmountWithdrawn(inventoryWdProjMas.getAmountWithdrawn());

        return inventoryWdProjMasImpl;
    }

    /**
     * Returns the inventory wd proj mas with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas
     * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas findByPrimaryKey(Serializable primaryKey)
        throws NoSuchInventoryWdProjMasException, SystemException {
        InventoryWdProjMas inventoryWdProjMas = fetchByPrimaryKey(primaryKey);

        if (inventoryWdProjMas == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return inventoryWdProjMas;
    }

    /**
     * Returns the inventory wd proj mas with the primary key or throws a {@link com.stpl.app.NoSuchInventoryWdProjMasException} if it could not be found.
     *
     * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas
     * @throws com.stpl.app.NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas findByPrimaryKey(int inventoryWdProjMasSid)
        throws NoSuchInventoryWdProjMasException, SystemException {
        return findByPrimaryKey((Serializable) inventoryWdProjMasSid);
    }

    /**
     * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        InventoryWdProjMas inventoryWdProjMas = (InventoryWdProjMas) EntityCacheUtil.getResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                InventoryWdProjMasImpl.class, primaryKey);

        if (inventoryWdProjMas == _nullInventoryWdProjMas) {
            return null;
        }

        if (inventoryWdProjMas == null) {
            Session session = null;

            try {
                session = openSession();

                inventoryWdProjMas = (InventoryWdProjMas) session.get(InventoryWdProjMasImpl.class,
                        primaryKey);

                if (inventoryWdProjMas != null) {
                    cacheResult(inventoryWdProjMas);
                } else {
                    EntityCacheUtil.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                        InventoryWdProjMasImpl.class, primaryKey,
                        _nullInventoryWdProjMas);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
                    InventoryWdProjMasImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return inventoryWdProjMas;
    }

    /**
     * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
     * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public InventoryWdProjMas fetchByPrimaryKey(int inventoryWdProjMasSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) inventoryWdProjMasSid);
    }

    /**
     * Returns all the inventory wd proj mases.
     *
     * @return the inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdProjMas> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the inventory wd proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of inventory wd proj mases
     * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
     * @return the range of inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdProjMas> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the inventory wd proj mases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of inventory wd proj mases
     * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of inventory wd proj mases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<InventoryWdProjMas> findAll(int start, int end,
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

        List<InventoryWdProjMas> list = (List<InventoryWdProjMas>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_INVENTORYWDPROJMAS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_INVENTORYWDPROJMAS;

                if (pagination) {
                    sql = sql.concat(InventoryWdProjMasModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<InventoryWdProjMas>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<InventoryWdProjMas>(list);
                } else {
                    list = (List<InventoryWdProjMas>) QueryUtil.list(q,
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
     * Removes all the inventory wd proj mases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (InventoryWdProjMas inventoryWdProjMas : findAll()) {
            remove(inventoryWdProjMas);
        }
    }

    /**
     * Returns the number of inventory wd proj mases.
     *
     * @return the number of inventory wd proj mases
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

                Query q = session.createQuery(_SQL_COUNT_INVENTORYWDPROJMAS);

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
     * Initializes the inventory wd proj mas persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.InventoryWdProjMas")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<InventoryWdProjMas>> listenersList = new ArrayList<ModelListener<InventoryWdProjMas>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<InventoryWdProjMas>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(InventoryWdProjMasImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
