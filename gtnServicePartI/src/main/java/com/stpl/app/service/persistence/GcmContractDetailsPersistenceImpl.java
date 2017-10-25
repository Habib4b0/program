package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGcmContractDetailsException;
import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.model.impl.GcmContractDetailsImpl;
import com.stpl.app.model.impl.GcmContractDetailsModelImpl;
import com.stpl.app.service.persistence.GcmContractDetailsPersistence;

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
 * The persistence implementation for the gcm contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmContractDetailsPersistence
 * @see GcmContractDetailsUtil
 * @generated
 */
public class GcmContractDetailsPersistenceImpl extends BasePersistenceImpl<GcmContractDetails>
    implements GcmContractDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GcmContractDetailsUtil} to access the gcm contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GcmContractDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmContractDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GCMCONTRACTDETAILS = "SELECT gcmContractDetails FROM GcmContractDetails gcmContractDetails";
    private static final String _SQL_COUNT_GCMCONTRACTDETAILS = "SELECT COUNT(gcmContractDetails) FROM GcmContractDetails gcmContractDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "gcmContractDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmContractDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GcmContractDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "paymentMethod", "userId", "endDate", "paymentFrequency",
                "gcmContractDetailsSid", "componentId", "modifiedDate",
                "componentName", "rsCalendar", "fileName", "startDate",
                "planLevel", "createdDate", "createdBy", "componentNo",
                "programType", "sessionId", "modifiedBy", "componentStatus",
                "componentType"
            });
    private static GcmContractDetails _nullGcmContractDetails = new GcmContractDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GcmContractDetails> toCacheModel() {
                return _nullGcmContractDetailsCacheModel;
            }
        };

    private static CacheModel<GcmContractDetails> _nullGcmContractDetailsCacheModel =
        new CacheModel<GcmContractDetails>() {
            @Override
            public GcmContractDetails toEntityModel() {
                return _nullGcmContractDetails;
            }
        };

    public GcmContractDetailsPersistenceImpl() {
        setModelClass(GcmContractDetails.class);
    }

    /**
     * Caches the gcm contract details in the entity cache if it is enabled.
     *
     * @param gcmContractDetails the gcm contract details
     */
    @Override
    public void cacheResult(GcmContractDetails gcmContractDetails) {
        EntityCacheUtil.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey(),
            gcmContractDetails);

        gcmContractDetails.resetOriginalValues();
    }

    /**
     * Caches the gcm contract detailses in the entity cache if it is enabled.
     *
     * @param gcmContractDetailses the gcm contract detailses
     */
    @Override
    public void cacheResult(List<GcmContractDetails> gcmContractDetailses) {
        for (GcmContractDetails gcmContractDetails : gcmContractDetailses) {
            if (EntityCacheUtil.getResult(
                        GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmContractDetailsImpl.class,
                        gcmContractDetails.getPrimaryKey()) == null) {
                cacheResult(gcmContractDetails);
            } else {
                gcmContractDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gcm contract detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GcmContractDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GcmContractDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gcm contract details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GcmContractDetails gcmContractDetails) {
        EntityCacheUtil.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GcmContractDetails> gcmContractDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GcmContractDetails gcmContractDetails : gcmContractDetailses) {
            EntityCacheUtil.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
     *
     * @param gcmContractDetailsSid the primary key for the new gcm contract details
     * @return the new gcm contract details
     */
    @Override
    public GcmContractDetails create(int gcmContractDetailsSid) {
        GcmContractDetails gcmContractDetails = new GcmContractDetailsImpl();

        gcmContractDetails.setNew(true);
        gcmContractDetails.setPrimaryKey(gcmContractDetailsSid);

        return gcmContractDetails;
    }

    /**
     * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param gcmContractDetailsSid the primary key of the gcm contract details
     * @return the gcm contract details that was removed
     * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails remove(int gcmContractDetailsSid)
        throws NoSuchGcmContractDetailsException, SystemException {
        return remove((Serializable) gcmContractDetailsSid);
    }

    /**
     * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gcm contract details
     * @return the gcm contract details that was removed
     * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails remove(Serializable primaryKey)
        throws NoSuchGcmContractDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GcmContractDetails gcmContractDetails = (GcmContractDetails) session.get(GcmContractDetailsImpl.class,
                    primaryKey);

            if (gcmContractDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGcmContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(gcmContractDetails);
        } catch (NoSuchGcmContractDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GcmContractDetails removeImpl(
        GcmContractDetails gcmContractDetails) throws SystemException {
        gcmContractDetails = toUnwrappedModel(gcmContractDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(gcmContractDetails)) {
                gcmContractDetails = (GcmContractDetails) session.get(GcmContractDetailsImpl.class,
                        gcmContractDetails.getPrimaryKeyObj());
            }

            if (gcmContractDetails != null) {
                session.delete(gcmContractDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (gcmContractDetails != null) {
            clearCache(gcmContractDetails);
        }

        return gcmContractDetails;
    }

    @Override
    public GcmContractDetails updateImpl(
        com.stpl.app.model.GcmContractDetails gcmContractDetails)
        throws SystemException {
        gcmContractDetails = toUnwrappedModel(gcmContractDetails);

        boolean isNew = gcmContractDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (gcmContractDetails.isNew()) {
                session.save(gcmContractDetails);

                gcmContractDetails.setNew(false);
            } else {
                session.merge(gcmContractDetails);
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

        EntityCacheUtil.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey(),
            gcmContractDetails);

        return gcmContractDetails;
    }

    protected GcmContractDetails toUnwrappedModel(
        GcmContractDetails gcmContractDetails) {
        if (gcmContractDetails instanceof GcmContractDetailsImpl) {
            return gcmContractDetails;
        }

        GcmContractDetailsImpl gcmContractDetailsImpl = new GcmContractDetailsImpl();

        gcmContractDetailsImpl.setNew(gcmContractDetails.isNew());
        gcmContractDetailsImpl.setPrimaryKey(gcmContractDetails.getPrimaryKey());

        gcmContractDetailsImpl.setPaymentMethod(gcmContractDetails.getPaymentMethod());
        gcmContractDetailsImpl.setUserId(gcmContractDetails.getUserId());
        gcmContractDetailsImpl.setEndDate(gcmContractDetails.getEndDate());
        gcmContractDetailsImpl.setPaymentFrequency(gcmContractDetails.getPaymentFrequency());
        gcmContractDetailsImpl.setGcmContractDetailsSid(gcmContractDetails.getGcmContractDetailsSid());
        gcmContractDetailsImpl.setComponentId(gcmContractDetails.getComponentId());
        gcmContractDetailsImpl.setModifiedDate(gcmContractDetails.getModifiedDate());
        gcmContractDetailsImpl.setComponentName(gcmContractDetails.getComponentName());
        gcmContractDetailsImpl.setRsCalendar(gcmContractDetails.getRsCalendar());
        gcmContractDetailsImpl.setFileName(gcmContractDetails.getFileName());
        gcmContractDetailsImpl.setStartDate(gcmContractDetails.getStartDate());
        gcmContractDetailsImpl.setPlanLevel(gcmContractDetails.getPlanLevel());
        gcmContractDetailsImpl.setCreatedDate(gcmContractDetails.getCreatedDate());
        gcmContractDetailsImpl.setCreatedBy(gcmContractDetails.getCreatedBy());
        gcmContractDetailsImpl.setComponentNo(gcmContractDetails.getComponentNo());
        gcmContractDetailsImpl.setProgramType(gcmContractDetails.getProgramType());
        gcmContractDetailsImpl.setSessionId(gcmContractDetails.getSessionId());
        gcmContractDetailsImpl.setModifiedBy(gcmContractDetails.getModifiedBy());
        gcmContractDetailsImpl.setComponentStatus(gcmContractDetails.getComponentStatus());
        gcmContractDetailsImpl.setComponentType(gcmContractDetails.getComponentType());

        return gcmContractDetailsImpl;
    }

    /**
     * Returns the gcm contract details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gcm contract details
     * @return the gcm contract details
     * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGcmContractDetailsException, SystemException {
        GcmContractDetails gcmContractDetails = fetchByPrimaryKey(primaryKey);

        if (gcmContractDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGcmContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return gcmContractDetails;
    }

    /**
     * Returns the gcm contract details with the primary key or throws a {@link com.stpl.app.NoSuchGcmContractDetailsException} if it could not be found.
     *
     * @param gcmContractDetailsSid the primary key of the gcm contract details
     * @return the gcm contract details
     * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails findByPrimaryKey(int gcmContractDetailsSid)
        throws NoSuchGcmContractDetailsException, SystemException {
        return findByPrimaryKey((Serializable) gcmContractDetailsSid);
    }

    /**
     * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gcm contract details
     * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GcmContractDetails gcmContractDetails = (GcmContractDetails) EntityCacheUtil.getResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmContractDetailsImpl.class, primaryKey);

        if (gcmContractDetails == _nullGcmContractDetails) {
            return null;
        }

        if (gcmContractDetails == null) {
            Session session = null;

            try {
                session = openSession();

                gcmContractDetails = (GcmContractDetails) session.get(GcmContractDetailsImpl.class,
                        primaryKey);

                if (gcmContractDetails != null) {
                    cacheResult(gcmContractDetails);
                } else {
                    EntityCacheUtil.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmContractDetailsImpl.class, primaryKey,
                        _nullGcmContractDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    GcmContractDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return gcmContractDetails;
    }

    /**
     * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param gcmContractDetailsSid the primary key of the gcm contract details
     * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmContractDetails fetchByPrimaryKey(int gcmContractDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) gcmContractDetailsSid);
    }

    /**
     * Returns all the gcm contract detailses.
     *
     * @return the gcm contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmContractDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gcm contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm contract detailses
     * @param end the upper bound of the range of gcm contract detailses (not inclusive)
     * @return the range of gcm contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmContractDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gcm contract detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm contract detailses
     * @param end the upper bound of the range of gcm contract detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gcm contract detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmContractDetails> findAll(int start, int end,
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

        List<GcmContractDetails> list = (List<GcmContractDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GCMCONTRACTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GCMCONTRACTDETAILS;

                if (pagination) {
                    sql = sql.concat(GcmContractDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GcmContractDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GcmContractDetails>(list);
                } else {
                    list = (List<GcmContractDetails>) QueryUtil.list(q,
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
     * Removes all the gcm contract detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GcmContractDetails gcmContractDetails : findAll()) {
            remove(gcmContractDetails);
        }
    }

    /**
     * Returns the number of gcm contract detailses.
     *
     * @return the number of gcm contract detailses
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

                Query q = session.createQuery(_SQL_COUNT_GCMCONTRACTDETAILS);

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
     * Initializes the gcm contract details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GcmContractDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GcmContractDetails>> listenersList = new ArrayList<ModelListener<GcmContractDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GcmContractDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GcmContractDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
