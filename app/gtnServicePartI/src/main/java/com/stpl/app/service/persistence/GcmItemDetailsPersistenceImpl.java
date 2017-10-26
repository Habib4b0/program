package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGcmItemDetailsException;
import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.model.impl.GcmItemDetailsImpl;
import com.stpl.app.model.impl.GcmItemDetailsModelImpl;
import com.stpl.app.service.persistence.GcmItemDetailsPersistence;

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
 * The persistence implementation for the gcm item details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmItemDetailsPersistence
 * @see GcmItemDetailsUtil
 * @generated
 */
public class GcmItemDetailsPersistenceImpl extends BasePersistenceImpl<GcmItemDetails>
    implements GcmItemDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GcmItemDetailsUtil} to access the gcm item details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GcmItemDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmItemDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmItemDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GCMITEMDETAILS = "SELECT gcmItemDetails FROM GcmItemDetails gcmItemDetails";
    private static final String _SQL_COUNT_GCMITEMDETAILS = "SELECT COUNT(gcmItemDetails) FROM GcmItemDetails gcmItemDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "gcmItemDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmItemDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GcmItemDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ifpDetailsEndDate", "itemStatus", "checkRecord",
                "ifpDetailsStartDate", "userId", "itemMasterSid", "itemEndDate",
                "gcmItemDetailsSid", "itemIfpDetailsSid", "itemId", "brandName",
                "modifiedDate", "createdDate", "createdBy", "itemStartDate",
                "sessionId", "itemName", "operation", "modifiedBy",
                "inboundStatus", "itemStatusSid", "itemNo", "ifpModelSid",
                "theraputicClass"
            });
    private static GcmItemDetails _nullGcmItemDetails = new GcmItemDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GcmItemDetails> toCacheModel() {
                return _nullGcmItemDetailsCacheModel;
            }
        };

    private static CacheModel<GcmItemDetails> _nullGcmItemDetailsCacheModel = new CacheModel<GcmItemDetails>() {
            @Override
            public GcmItemDetails toEntityModel() {
                return _nullGcmItemDetails;
            }
        };

    public GcmItemDetailsPersistenceImpl() {
        setModelClass(GcmItemDetails.class);
    }

    /**
     * Caches the gcm item details in the entity cache if it is enabled.
     *
     * @param gcmItemDetails the gcm item details
     */
    @Override
    public void cacheResult(GcmItemDetails gcmItemDetails) {
        EntityCacheUtil.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey(),
            gcmItemDetails);

        gcmItemDetails.resetOriginalValues();
    }

    /**
     * Caches the gcm item detailses in the entity cache if it is enabled.
     *
     * @param gcmItemDetailses the gcm item detailses
     */
    @Override
    public void cacheResult(List<GcmItemDetails> gcmItemDetailses) {
        for (GcmItemDetails gcmItemDetails : gcmItemDetailses) {
            if (EntityCacheUtil.getResult(
                        GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey()) == null) {
                cacheResult(gcmItemDetails);
            } else {
                gcmItemDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gcm item detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GcmItemDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GcmItemDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gcm item details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GcmItemDetails gcmItemDetails) {
        EntityCacheUtil.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GcmItemDetails> gcmItemDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GcmItemDetails gcmItemDetails : gcmItemDetailses) {
            EntityCacheUtil.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
     *
     * @param gcmItemDetailsSid the primary key for the new gcm item details
     * @return the new gcm item details
     */
    @Override
    public GcmItemDetails create(int gcmItemDetailsSid) {
        GcmItemDetails gcmItemDetails = new GcmItemDetailsImpl();

        gcmItemDetails.setNew(true);
        gcmItemDetails.setPrimaryKey(gcmItemDetailsSid);

        return gcmItemDetails;
    }

    /**
     * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param gcmItemDetailsSid the primary key of the gcm item details
     * @return the gcm item details that was removed
     * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails remove(int gcmItemDetailsSid)
        throws NoSuchGcmItemDetailsException, SystemException {
        return remove((Serializable) gcmItemDetailsSid);
    }

    /**
     * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gcm item details
     * @return the gcm item details that was removed
     * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails remove(Serializable primaryKey)
        throws NoSuchGcmItemDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GcmItemDetails gcmItemDetails = (GcmItemDetails) session.get(GcmItemDetailsImpl.class,
                    primaryKey);

            if (gcmItemDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGcmItemDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(gcmItemDetails);
        } catch (NoSuchGcmItemDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GcmItemDetails removeImpl(GcmItemDetails gcmItemDetails)
        throws SystemException {
        gcmItemDetails = toUnwrappedModel(gcmItemDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(gcmItemDetails)) {
                gcmItemDetails = (GcmItemDetails) session.get(GcmItemDetailsImpl.class,
                        gcmItemDetails.getPrimaryKeyObj());
            }

            if (gcmItemDetails != null) {
                session.delete(gcmItemDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (gcmItemDetails != null) {
            clearCache(gcmItemDetails);
        }

        return gcmItemDetails;
    }

    @Override
    public GcmItemDetails updateImpl(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws SystemException {
        gcmItemDetails = toUnwrappedModel(gcmItemDetails);

        boolean isNew = gcmItemDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (gcmItemDetails.isNew()) {
                session.save(gcmItemDetails);

                gcmItemDetails.setNew(false);
            } else {
                session.merge(gcmItemDetails);
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

        EntityCacheUtil.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmItemDetailsImpl.class, gcmItemDetails.getPrimaryKey(),
            gcmItemDetails);

        return gcmItemDetails;
    }

    protected GcmItemDetails toUnwrappedModel(GcmItemDetails gcmItemDetails) {
        if (gcmItemDetails instanceof GcmItemDetailsImpl) {
            return gcmItemDetails;
        }

        GcmItemDetailsImpl gcmItemDetailsImpl = new GcmItemDetailsImpl();

        gcmItemDetailsImpl.setNew(gcmItemDetails.isNew());
        gcmItemDetailsImpl.setPrimaryKey(gcmItemDetails.getPrimaryKey());

        gcmItemDetailsImpl.setIfpDetailsEndDate(gcmItemDetails.getIfpDetailsEndDate());
        gcmItemDetailsImpl.setItemStatus(gcmItemDetails.getItemStatus());
        gcmItemDetailsImpl.setCheckRecord(gcmItemDetails.isCheckRecord());
        gcmItemDetailsImpl.setIfpDetailsStartDate(gcmItemDetails.getIfpDetailsStartDate());
        gcmItemDetailsImpl.setUserId(gcmItemDetails.getUserId());
        gcmItemDetailsImpl.setItemMasterSid(gcmItemDetails.getItemMasterSid());
        gcmItemDetailsImpl.setItemEndDate(gcmItemDetails.getItemEndDate());
        gcmItemDetailsImpl.setGcmItemDetailsSid(gcmItemDetails.getGcmItemDetailsSid());
        gcmItemDetailsImpl.setItemIfpDetailsSid(gcmItemDetails.getItemIfpDetailsSid());
        gcmItemDetailsImpl.setItemId(gcmItemDetails.getItemId());
        gcmItemDetailsImpl.setBrandName(gcmItemDetails.getBrandName());
        gcmItemDetailsImpl.setModifiedDate(gcmItemDetails.getModifiedDate());
        gcmItemDetailsImpl.setCreatedDate(gcmItemDetails.getCreatedDate());
        gcmItemDetailsImpl.setCreatedBy(gcmItemDetails.getCreatedBy());
        gcmItemDetailsImpl.setItemStartDate(gcmItemDetails.getItemStartDate());
        gcmItemDetailsImpl.setSessionId(gcmItemDetails.getSessionId());
        gcmItemDetailsImpl.setItemName(gcmItemDetails.getItemName());
        gcmItemDetailsImpl.setOperation(gcmItemDetails.getOperation());
        gcmItemDetailsImpl.setModifiedBy(gcmItemDetails.getModifiedBy());
        gcmItemDetailsImpl.setInboundStatus(gcmItemDetails.getInboundStatus());
        gcmItemDetailsImpl.setItemStatusSid(gcmItemDetails.getItemStatusSid());
        gcmItemDetailsImpl.setItemNo(gcmItemDetails.getItemNo());
        gcmItemDetailsImpl.setIfpModelSid(gcmItemDetails.getIfpModelSid());
        gcmItemDetailsImpl.setTheraputicClass(gcmItemDetails.getTheraputicClass());

        return gcmItemDetailsImpl;
    }

    /**
     * Returns the gcm item details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gcm item details
     * @return the gcm item details
     * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGcmItemDetailsException, SystemException {
        GcmItemDetails gcmItemDetails = fetchByPrimaryKey(primaryKey);

        if (gcmItemDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGcmItemDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return gcmItemDetails;
    }

    /**
     * Returns the gcm item details with the primary key or throws a {@link com.stpl.app.NoSuchGcmItemDetailsException} if it could not be found.
     *
     * @param gcmItemDetailsSid the primary key of the gcm item details
     * @return the gcm item details
     * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails findByPrimaryKey(int gcmItemDetailsSid)
        throws NoSuchGcmItemDetailsException, SystemException {
        return findByPrimaryKey((Serializable) gcmItemDetailsSid);
    }

    /**
     * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gcm item details
     * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GcmItemDetails gcmItemDetails = (GcmItemDetails) EntityCacheUtil.getResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmItemDetailsImpl.class, primaryKey);

        if (gcmItemDetails == _nullGcmItemDetails) {
            return null;
        }

        if (gcmItemDetails == null) {
            Session session = null;

            try {
                session = openSession();

                gcmItemDetails = (GcmItemDetails) session.get(GcmItemDetailsImpl.class,
                        primaryKey);

                if (gcmItemDetails != null) {
                    cacheResult(gcmItemDetails);
                } else {
                    EntityCacheUtil.putResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmItemDetailsImpl.class, primaryKey,
                        _nullGcmItemDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GcmItemDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    GcmItemDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return gcmItemDetails;
    }

    /**
     * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param gcmItemDetailsSid the primary key of the gcm item details
     * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmItemDetails fetchByPrimaryKey(int gcmItemDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) gcmItemDetailsSid);
    }

    /**
     * Returns all the gcm item detailses.
     *
     * @return the gcm item detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmItemDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gcm item detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm item detailses
     * @param end the upper bound of the range of gcm item detailses (not inclusive)
     * @return the range of gcm item detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmItemDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gcm item detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm item detailses
     * @param end the upper bound of the range of gcm item detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gcm item detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmItemDetails> findAll(int start, int end,
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

        List<GcmItemDetails> list = (List<GcmItemDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GCMITEMDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GCMITEMDETAILS;

                if (pagination) {
                    sql = sql.concat(GcmItemDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GcmItemDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GcmItemDetails>(list);
                } else {
                    list = (List<GcmItemDetails>) QueryUtil.list(q,
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
     * Removes all the gcm item detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GcmItemDetails gcmItemDetails : findAll()) {
            remove(gcmItemDetails);
        }
    }

    /**
     * Returns the number of gcm item detailses.
     *
     * @return the number of gcm item detailses
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

                Query q = session.createQuery(_SQL_COUNT_GCMITEMDETAILS);

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
     * Initializes the gcm item details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GcmItemDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GcmItemDetails>> listenersList = new ArrayList<ModelListener<GcmItemDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GcmItemDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GcmItemDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
