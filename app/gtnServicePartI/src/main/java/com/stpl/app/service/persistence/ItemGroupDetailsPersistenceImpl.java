package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchItemGroupDetailsException;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.impl.ItemGroupDetailsImpl;
import com.stpl.app.model.impl.ItemGroupDetailsModelImpl;
import com.stpl.app.service.persistence.ItemGroupDetailsPersistence;

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
 * The persistence implementation for the item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemGroupDetailsPersistence
 * @see ItemGroupDetailsUtil
 * @generated
 */
public class ItemGroupDetailsPersistenceImpl extends BasePersistenceImpl<ItemGroupDetails>
    implements ItemGroupDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ItemGroupDetailsUtil} to access the item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ItemGroupDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            ItemGroupDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            ItemGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ITEMGROUPDETAILS = "SELECT itemGroupDetails FROM ItemGroupDetails itemGroupDetails";
    private static final String _SQL_COUNT_ITEMGROUPDETAILS = "SELECT COUNT(itemGroupDetails) FROM ItemGroupDetails itemGroupDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "itemGroupDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemGroupDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ItemGroupDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemGroupDetailsSid", "createdDate", "createdBy",
                "itemMasterSid", "versionNo", "modifiedBy", "modifiedDate",
                "itemGroupSid"
            });
    private static ItemGroupDetails _nullItemGroupDetails = new ItemGroupDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ItemGroupDetails> toCacheModel() {
                return _nullItemGroupDetailsCacheModel;
            }
        };

    private static CacheModel<ItemGroupDetails> _nullItemGroupDetailsCacheModel = new CacheModel<ItemGroupDetails>() {
            @Override
            public ItemGroupDetails toEntityModel() {
                return _nullItemGroupDetails;
            }
        };

    public ItemGroupDetailsPersistenceImpl() {
        setModelClass(ItemGroupDetails.class);
    }

    /**
     * Caches the item group details in the entity cache if it is enabled.
     *
     * @param itemGroupDetails the item group details
     */
    @Override
    public void cacheResult(ItemGroupDetails itemGroupDetails) {
        EntityCacheUtil.putResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsImpl.class, itemGroupDetails.getPrimaryKey(),
            itemGroupDetails);

        itemGroupDetails.resetOriginalValues();
    }

    /**
     * Caches the item group detailses in the entity cache if it is enabled.
     *
     * @param itemGroupDetailses the item group detailses
     */
    @Override
    public void cacheResult(List<ItemGroupDetails> itemGroupDetailses) {
        for (ItemGroupDetails itemGroupDetails : itemGroupDetailses) {
            if (EntityCacheUtil.getResult(
                        ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ItemGroupDetailsImpl.class,
                        itemGroupDetails.getPrimaryKey()) == null) {
                cacheResult(itemGroupDetails);
            } else {
                itemGroupDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all item group detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ItemGroupDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ItemGroupDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the item group details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ItemGroupDetails itemGroupDetails) {
        EntityCacheUtil.removeResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsImpl.class, itemGroupDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ItemGroupDetails> itemGroupDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ItemGroupDetails itemGroupDetails : itemGroupDetailses) {
            EntityCacheUtil.removeResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ItemGroupDetailsImpl.class, itemGroupDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new item group details with the primary key. Does not add the item group details to the database.
     *
     * @param itemGroupDetailsSid the primary key for the new item group details
     * @return the new item group details
     */
    @Override
    public ItemGroupDetails create(int itemGroupDetailsSid) {
        ItemGroupDetails itemGroupDetails = new ItemGroupDetailsImpl();

        itemGroupDetails.setNew(true);
        itemGroupDetails.setPrimaryKey(itemGroupDetailsSid);

        return itemGroupDetails;
    }

    /**
     * Removes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemGroupDetailsSid the primary key of the item group details
     * @return the item group details that was removed
     * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails remove(int itemGroupDetailsSid)
        throws NoSuchItemGroupDetailsException, SystemException {
        return remove((Serializable) itemGroupDetailsSid);
    }

    /**
     * Removes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the item group details
     * @return the item group details that was removed
     * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails remove(Serializable primaryKey)
        throws NoSuchItemGroupDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ItemGroupDetails itemGroupDetails = (ItemGroupDetails) session.get(ItemGroupDetailsImpl.class,
                    primaryKey);

            if (itemGroupDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(itemGroupDetails);
        } catch (NoSuchItemGroupDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ItemGroupDetails removeImpl(ItemGroupDetails itemGroupDetails)
        throws SystemException {
        itemGroupDetails = toUnwrappedModel(itemGroupDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(itemGroupDetails)) {
                itemGroupDetails = (ItemGroupDetails) session.get(ItemGroupDetailsImpl.class,
                        itemGroupDetails.getPrimaryKeyObj());
            }

            if (itemGroupDetails != null) {
                session.delete(itemGroupDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (itemGroupDetails != null) {
            clearCache(itemGroupDetails);
        }

        return itemGroupDetails;
    }

    @Override
    public ItemGroupDetails updateImpl(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails)
        throws SystemException {
        itemGroupDetails = toUnwrappedModel(itemGroupDetails);

        boolean isNew = itemGroupDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (itemGroupDetails.isNew()) {
                session.save(itemGroupDetails);

                itemGroupDetails.setNew(false);
            } else {
                session.merge(itemGroupDetails);
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

        EntityCacheUtil.putResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            ItemGroupDetailsImpl.class, itemGroupDetails.getPrimaryKey(),
            itemGroupDetails);

        return itemGroupDetails;
    }

    protected ItemGroupDetails toUnwrappedModel(
        ItemGroupDetails itemGroupDetails) {
        if (itemGroupDetails instanceof ItemGroupDetailsImpl) {
            return itemGroupDetails;
        }

        ItemGroupDetailsImpl itemGroupDetailsImpl = new ItemGroupDetailsImpl();

        itemGroupDetailsImpl.setNew(itemGroupDetails.isNew());
        itemGroupDetailsImpl.setPrimaryKey(itemGroupDetails.getPrimaryKey());

        itemGroupDetailsImpl.setItemGroupDetailsSid(itemGroupDetails.getItemGroupDetailsSid());
        itemGroupDetailsImpl.setCreatedDate(itemGroupDetails.getCreatedDate());
        itemGroupDetailsImpl.setCreatedBy(itemGroupDetails.getCreatedBy());
        itemGroupDetailsImpl.setItemMasterSid(itemGroupDetails.getItemMasterSid());
        itemGroupDetailsImpl.setVersionNo(itemGroupDetails.getVersionNo());
        itemGroupDetailsImpl.setModifiedBy(itemGroupDetails.getModifiedBy());
        itemGroupDetailsImpl.setModifiedDate(itemGroupDetails.getModifiedDate());
        itemGroupDetailsImpl.setItemGroupSid(itemGroupDetails.getItemGroupSid());

        return itemGroupDetailsImpl;
    }

    /**
     * Returns the item group details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the item group details
     * @return the item group details
     * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchItemGroupDetailsException, SystemException {
        ItemGroupDetails itemGroupDetails = fetchByPrimaryKey(primaryKey);

        if (itemGroupDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return itemGroupDetails;
    }

    /**
     * Returns the item group details with the primary key or throws a {@link com.stpl.app.NoSuchItemGroupDetailsException} if it could not be found.
     *
     * @param itemGroupDetailsSid the primary key of the item group details
     * @return the item group details
     * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails findByPrimaryKey(int itemGroupDetailsSid)
        throws NoSuchItemGroupDetailsException, SystemException {
        return findByPrimaryKey((Serializable) itemGroupDetailsSid);
    }

    /**
     * Returns the item group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the item group details
     * @return the item group details, or <code>null</code> if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ItemGroupDetails itemGroupDetails = (ItemGroupDetails) EntityCacheUtil.getResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                ItemGroupDetailsImpl.class, primaryKey);

        if (itemGroupDetails == _nullItemGroupDetails) {
            return null;
        }

        if (itemGroupDetails == null) {
            Session session = null;

            try {
                session = openSession();

                itemGroupDetails = (ItemGroupDetails) session.get(ItemGroupDetailsImpl.class,
                        primaryKey);

                if (itemGroupDetails != null) {
                    cacheResult(itemGroupDetails);
                } else {
                    EntityCacheUtil.putResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        ItemGroupDetailsImpl.class, primaryKey,
                        _nullItemGroupDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    ItemGroupDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return itemGroupDetails;
    }

    /**
     * Returns the item group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemGroupDetailsSid the primary key of the item group details
     * @return the item group details, or <code>null</code> if a item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ItemGroupDetails fetchByPrimaryKey(int itemGroupDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemGroupDetailsSid);
    }

    /**
     * Returns all the item group detailses.
     *
     * @return the item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemGroupDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the item group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item group detailses
     * @param end the upper bound of the range of item group detailses (not inclusive)
     * @return the range of item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemGroupDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the item group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of item group detailses
     * @param end the upper bound of the range of item group detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ItemGroupDetails> findAll(int start, int end,
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

        List<ItemGroupDetails> list = (List<ItemGroupDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ITEMGROUPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ITEMGROUPDETAILS;

                if (pagination) {
                    sql = sql.concat(ItemGroupDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ItemGroupDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ItemGroupDetails>(list);
                } else {
                    list = (List<ItemGroupDetails>) QueryUtil.list(q,
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
     * Removes all the item group detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ItemGroupDetails itemGroupDetails : findAll()) {
            remove(itemGroupDetails);
        }
    }

    /**
     * Returns the number of item group detailses.
     *
     * @return the number of item group detailses
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

                Query q = session.createQuery(_SQL_COUNT_ITEMGROUPDETAILS);

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
     * Initializes the item group details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ItemGroupDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ItemGroupDetails>> listenersList = new ArrayList<ModelListener<ItemGroupDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ItemGroupDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ItemGroupDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
