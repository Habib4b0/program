package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchGcmCompanyDetailsException;
import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.model.impl.GcmCompanyDetailsImpl;
import com.stpl.app.model.impl.GcmCompanyDetailsModelImpl;
import com.stpl.app.service.persistence.GcmCompanyDetailsPersistence;

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
 * The persistence implementation for the gcm company details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyDetailsPersistence
 * @see GcmCompanyDetailsUtil
 * @generated
 */
public class GcmCompanyDetailsPersistenceImpl extends BasePersistenceImpl<GcmCompanyDetails>
    implements GcmCompanyDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link GcmCompanyDetailsUtil} to access the gcm company details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = GcmCompanyDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmCompanyDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED,
            GcmCompanyDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_GCMCOMPANYDETAILS = "SELECT gcmCompanyDetails FROM GcmCompanyDetails gcmCompanyDetails";
    private static final String _SQL_COUNT_GCMCOMPANYDETAILS = "SELECT COUNT(gcmCompanyDetails) FROM GcmCompanyDetails gcmCompanyDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "gcmCompanyDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmCompanyDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(GcmCompanyDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "checkRecord", "userId", "moduleName", "companyId",
                "cfpDetailsTradeClass", "companyName", "modifiedDate",
                "gcmCompanyDetailsSid", "itemCfpDetailsSid", "createdDate",
                "createdBy", "companyStartDate", "companyNo", "companyStatus",
                "sessionId", "companyEndDate", "cfpDetailsStartDate",
                "operation", "cfpModelSid", "modifiedBy", "subModuleName",
                "cfpDetailsEndDate", "companyStatusSid", "companyMasterSid"
            });
    private static GcmCompanyDetails _nullGcmCompanyDetails = new GcmCompanyDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<GcmCompanyDetails> toCacheModel() {
                return _nullGcmCompanyDetailsCacheModel;
            }
        };

    private static CacheModel<GcmCompanyDetails> _nullGcmCompanyDetailsCacheModel =
        new CacheModel<GcmCompanyDetails>() {
            @Override
            public GcmCompanyDetails toEntityModel() {
                return _nullGcmCompanyDetails;
            }
        };

    public GcmCompanyDetailsPersistenceImpl() {
        setModelClass(GcmCompanyDetails.class);
    }

    /**
     * Caches the gcm company details in the entity cache if it is enabled.
     *
     * @param gcmCompanyDetails the gcm company details
     */
    @Override
    public void cacheResult(GcmCompanyDetails gcmCompanyDetails) {
        EntityCacheUtil.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey(),
            gcmCompanyDetails);

        gcmCompanyDetails.resetOriginalValues();
    }

    /**
     * Caches the gcm company detailses in the entity cache if it is enabled.
     *
     * @param gcmCompanyDetailses the gcm company detailses
     */
    @Override
    public void cacheResult(List<GcmCompanyDetails> gcmCompanyDetailses) {
        for (GcmCompanyDetails gcmCompanyDetails : gcmCompanyDetailses) {
            if (EntityCacheUtil.getResult(
                        GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmCompanyDetailsImpl.class,
                        gcmCompanyDetails.getPrimaryKey()) == null) {
                cacheResult(gcmCompanyDetails);
            } else {
                gcmCompanyDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all gcm company detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(GcmCompanyDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(GcmCompanyDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the gcm company details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(GcmCompanyDetails gcmCompanyDetails) {
        EntityCacheUtil.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<GcmCompanyDetails> gcmCompanyDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (GcmCompanyDetails gcmCompanyDetails : gcmCompanyDetailses) {
            EntityCacheUtil.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new gcm company details with the primary key. Does not add the gcm company details to the database.
     *
     * @param gcmCompanyDetailsSid the primary key for the new gcm company details
     * @return the new gcm company details
     */
    @Override
    public GcmCompanyDetails create(int gcmCompanyDetailsSid) {
        GcmCompanyDetails gcmCompanyDetails = new GcmCompanyDetailsImpl();

        gcmCompanyDetails.setNew(true);
        gcmCompanyDetails.setPrimaryKey(gcmCompanyDetailsSid);

        return gcmCompanyDetails;
    }

    /**
     * Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param gcmCompanyDetailsSid the primary key of the gcm company details
     * @return the gcm company details that was removed
     * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails remove(int gcmCompanyDetailsSid)
        throws NoSuchGcmCompanyDetailsException, SystemException {
        return remove((Serializable) gcmCompanyDetailsSid);
    }

    /**
     * Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the gcm company details
     * @return the gcm company details that was removed
     * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails remove(Serializable primaryKey)
        throws NoSuchGcmCompanyDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            GcmCompanyDetails gcmCompanyDetails = (GcmCompanyDetails) session.get(GcmCompanyDetailsImpl.class,
                    primaryKey);

            if (gcmCompanyDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchGcmCompanyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(gcmCompanyDetails);
        } catch (NoSuchGcmCompanyDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected GcmCompanyDetails removeImpl(GcmCompanyDetails gcmCompanyDetails)
        throws SystemException {
        gcmCompanyDetails = toUnwrappedModel(gcmCompanyDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(gcmCompanyDetails)) {
                gcmCompanyDetails = (GcmCompanyDetails) session.get(GcmCompanyDetailsImpl.class,
                        gcmCompanyDetails.getPrimaryKeyObj());
            }

            if (gcmCompanyDetails != null) {
                session.delete(gcmCompanyDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (gcmCompanyDetails != null) {
            clearCache(gcmCompanyDetails);
        }

        return gcmCompanyDetails;
    }

    @Override
    public GcmCompanyDetails updateImpl(
        com.stpl.app.model.GcmCompanyDetails gcmCompanyDetails)
        throws SystemException {
        gcmCompanyDetails = toUnwrappedModel(gcmCompanyDetails);

        boolean isNew = gcmCompanyDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (gcmCompanyDetails.isNew()) {
                session.save(gcmCompanyDetails);

                gcmCompanyDetails.setNew(false);
            } else {
                session.merge(gcmCompanyDetails);
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

        EntityCacheUtil.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            GcmCompanyDetailsImpl.class, gcmCompanyDetails.getPrimaryKey(),
            gcmCompanyDetails);

        return gcmCompanyDetails;
    }

    protected GcmCompanyDetails toUnwrappedModel(
        GcmCompanyDetails gcmCompanyDetails) {
        if (gcmCompanyDetails instanceof GcmCompanyDetailsImpl) {
            return gcmCompanyDetails;
        }

        GcmCompanyDetailsImpl gcmCompanyDetailsImpl = new GcmCompanyDetailsImpl();

        gcmCompanyDetailsImpl.setNew(gcmCompanyDetails.isNew());
        gcmCompanyDetailsImpl.setPrimaryKey(gcmCompanyDetails.getPrimaryKey());

        gcmCompanyDetailsImpl.setCheckRecord(gcmCompanyDetails.isCheckRecord());
        gcmCompanyDetailsImpl.setUserId(gcmCompanyDetails.getUserId());
        gcmCompanyDetailsImpl.setModuleName(gcmCompanyDetails.getModuleName());
        gcmCompanyDetailsImpl.setCompanyId(gcmCompanyDetails.getCompanyId());
        gcmCompanyDetailsImpl.setCfpDetailsTradeClass(gcmCompanyDetails.getCfpDetailsTradeClass());
        gcmCompanyDetailsImpl.setCompanyName(gcmCompanyDetails.getCompanyName());
        gcmCompanyDetailsImpl.setModifiedDate(gcmCompanyDetails.getModifiedDate());
        gcmCompanyDetailsImpl.setGcmCompanyDetailsSid(gcmCompanyDetails.getGcmCompanyDetailsSid());
        gcmCompanyDetailsImpl.setItemCfpDetailsSid(gcmCompanyDetails.getItemCfpDetailsSid());
        gcmCompanyDetailsImpl.setCreatedDate(gcmCompanyDetails.getCreatedDate());
        gcmCompanyDetailsImpl.setCreatedBy(gcmCompanyDetails.getCreatedBy());
        gcmCompanyDetailsImpl.setCompanyStartDate(gcmCompanyDetails.getCompanyStartDate());
        gcmCompanyDetailsImpl.setCompanyNo(gcmCompanyDetails.getCompanyNo());
        gcmCompanyDetailsImpl.setCompanyStatus(gcmCompanyDetails.getCompanyStatus());
        gcmCompanyDetailsImpl.setSessionId(gcmCompanyDetails.getSessionId());
        gcmCompanyDetailsImpl.setCompanyEndDate(gcmCompanyDetails.getCompanyEndDate());
        gcmCompanyDetailsImpl.setCfpDetailsStartDate(gcmCompanyDetails.getCfpDetailsStartDate());
        gcmCompanyDetailsImpl.setOperation(gcmCompanyDetails.getOperation());
        gcmCompanyDetailsImpl.setCfpModelSid(gcmCompanyDetails.getCfpModelSid());
        gcmCompanyDetailsImpl.setModifiedBy(gcmCompanyDetails.getModifiedBy());
        gcmCompanyDetailsImpl.setSubModuleName(gcmCompanyDetails.getSubModuleName());
        gcmCompanyDetailsImpl.setCfpDetailsEndDate(gcmCompanyDetails.getCfpDetailsEndDate());
        gcmCompanyDetailsImpl.setCompanyStatusSid(gcmCompanyDetails.getCompanyStatusSid());
        gcmCompanyDetailsImpl.setCompanyMasterSid(gcmCompanyDetails.getCompanyMasterSid());

        return gcmCompanyDetailsImpl;
    }

    /**
     * Returns the gcm company details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the gcm company details
     * @return the gcm company details
     * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchGcmCompanyDetailsException, SystemException {
        GcmCompanyDetails gcmCompanyDetails = fetchByPrimaryKey(primaryKey);

        if (gcmCompanyDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchGcmCompanyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return gcmCompanyDetails;
    }

    /**
     * Returns the gcm company details with the primary key or throws a {@link com.stpl.app.NoSuchGcmCompanyDetailsException} if it could not be found.
     *
     * @param gcmCompanyDetailsSid the primary key of the gcm company details
     * @return the gcm company details
     * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails findByPrimaryKey(int gcmCompanyDetailsSid)
        throws NoSuchGcmCompanyDetailsException, SystemException {
        return findByPrimaryKey((Serializable) gcmCompanyDetailsSid);
    }

    /**
     * Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the gcm company details
     * @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        GcmCompanyDetails gcmCompanyDetails = (GcmCompanyDetails) EntityCacheUtil.getResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                GcmCompanyDetailsImpl.class, primaryKey);

        if (gcmCompanyDetails == _nullGcmCompanyDetails) {
            return null;
        }

        if (gcmCompanyDetails == null) {
            Session session = null;

            try {
                session = openSession();

                gcmCompanyDetails = (GcmCompanyDetails) session.get(GcmCompanyDetailsImpl.class,
                        primaryKey);

                if (gcmCompanyDetails != null) {
                    cacheResult(gcmCompanyDetails);
                } else {
                    EntityCacheUtil.putResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        GcmCompanyDetailsImpl.class, primaryKey,
                        _nullGcmCompanyDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(GcmCompanyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    GcmCompanyDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return gcmCompanyDetails;
    }

    /**
     * Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param gcmCompanyDetailsSid the primary key of the gcm company details
     * @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public GcmCompanyDetails fetchByPrimaryKey(int gcmCompanyDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) gcmCompanyDetailsSid);
    }

    /**
     * Returns all the gcm company detailses.
     *
     * @return the gcm company detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the gcm company detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm company detailses
     * @param end the upper bound of the range of gcm company detailses (not inclusive)
     * @return the range of gcm company detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the gcm company detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of gcm company detailses
     * @param end the upper bound of the range of gcm company detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of gcm company detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<GcmCompanyDetails> findAll(int start, int end,
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

        List<GcmCompanyDetails> list = (List<GcmCompanyDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_GCMCOMPANYDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_GCMCOMPANYDETAILS;

                if (pagination) {
                    sql = sql.concat(GcmCompanyDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<GcmCompanyDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<GcmCompanyDetails>(list);
                } else {
                    list = (List<GcmCompanyDetails>) QueryUtil.list(q,
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
     * Removes all the gcm company detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (GcmCompanyDetails gcmCompanyDetails : findAll()) {
            remove(gcmCompanyDetails);
        }
    }

    /**
     * Returns the number of gcm company detailses.
     *
     * @return the number of gcm company detailses
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

                Query q = session.createQuery(_SQL_COUNT_GCMCOMPANYDETAILS);

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
     * Initializes the gcm company details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.GcmCompanyDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<GcmCompanyDetails>> listenersList = new ArrayList<ModelListener<GcmCompanyDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<GcmCompanyDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(GcmCompanyDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
