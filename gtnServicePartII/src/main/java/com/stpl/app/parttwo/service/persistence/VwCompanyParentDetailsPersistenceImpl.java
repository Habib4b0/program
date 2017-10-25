package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException;
import com.stpl.app.parttwo.model.VwCompanyParentDetails;
import com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyParentDetailsPersistence;

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
 * The persistence implementation for the vw company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyParentDetailsPersistence
 * @see VwCompanyParentDetailsUtil
 * @generated
 */
public class VwCompanyParentDetailsPersistenceImpl extends BasePersistenceImpl<VwCompanyParentDetails>
    implements VwCompanyParentDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwCompanyParentDetailsUtil} to access the vw company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyParentDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyParentDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyParentDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWCOMPANYPARENTDETAILS = "SELECT vwCompanyParentDetails FROM VwCompanyParentDetails vwCompanyParentDetails";
    private static final String _SQL_COUNT_VWCOMPANYPARENTDETAILS = "SELECT COUNT(vwCompanyParentDetails) FROM VwCompanyParentDetails vwCompanyParentDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyParentDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyParentDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwCompanyParentDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "parentCompanyId", "priorParentCompanyId", "companyId",
                "lastUpdatedDate", "parentEndDate", "modifiedDate",
                "priorParentStartDate", "source", "createdBy", "createdDate",
                "companyParentDetailsSid", "batchId", "addChgDelIndicator",
                "modifiedBy", "parentStartDate"
            });
    private static VwCompanyParentDetails _nullVwCompanyParentDetails = new VwCompanyParentDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwCompanyParentDetails> toCacheModel() {
                return _nullVwCompanyParentDetailsCacheModel;
            }
        };

    private static CacheModel<VwCompanyParentDetails> _nullVwCompanyParentDetailsCacheModel =
        new CacheModel<VwCompanyParentDetails>() {
            @Override
            public VwCompanyParentDetails toEntityModel() {
                return _nullVwCompanyParentDetails;
            }
        };

    public VwCompanyParentDetailsPersistenceImpl() {
        setModelClass(VwCompanyParentDetails.class);
    }

    /**
     * Caches the vw company parent details in the entity cache if it is enabled.
     *
     * @param vwCompanyParentDetails the vw company parent details
     */
    @Override
    public void cacheResult(VwCompanyParentDetails vwCompanyParentDetails) {
        EntityCacheUtil.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsImpl.class,
            vwCompanyParentDetails.getPrimaryKey(), vwCompanyParentDetails);

        vwCompanyParentDetails.resetOriginalValues();
    }

    /**
     * Caches the vw company parent detailses in the entity cache if it is enabled.
     *
     * @param vwCompanyParentDetailses the vw company parent detailses
     */
    @Override
    public void cacheResult(
        List<VwCompanyParentDetails> vwCompanyParentDetailses) {
        for (VwCompanyParentDetails vwCompanyParentDetails : vwCompanyParentDetailses) {
            if (EntityCacheUtil.getResult(
                        VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyParentDetailsImpl.class,
                        vwCompanyParentDetails.getPrimaryKey()) == null) {
                cacheResult(vwCompanyParentDetails);
            } else {
                vwCompanyParentDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw company parent detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwCompanyParentDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwCompanyParentDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw company parent details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwCompanyParentDetails vwCompanyParentDetails) {
        EntityCacheUtil.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsImpl.class,
            vwCompanyParentDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<VwCompanyParentDetails> vwCompanyParentDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwCompanyParentDetails vwCompanyParentDetails : vwCompanyParentDetailses) {
            EntityCacheUtil.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyParentDetailsImpl.class,
                vwCompanyParentDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
     *
     * @param companyParentDetailsSid the primary key for the new vw company parent details
     * @return the new vw company parent details
     */
    @Override
    public VwCompanyParentDetails create(int companyParentDetailsSid) {
        VwCompanyParentDetails vwCompanyParentDetails = new VwCompanyParentDetailsImpl();

        vwCompanyParentDetails.setNew(true);
        vwCompanyParentDetails.setPrimaryKey(companyParentDetailsSid);

        return vwCompanyParentDetails;
    }

    /**
     * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyParentDetailsSid the primary key of the vw company parent details
     * @return the vw company parent details that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails remove(int companyParentDetailsSid)
        throws NoSuchVwCompanyParentDetailsException, SystemException {
        return remove((Serializable) companyParentDetailsSid);
    }

    /**
     * Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw company parent details
     * @return the vw company parent details that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails remove(Serializable primaryKey)
        throws NoSuchVwCompanyParentDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwCompanyParentDetails vwCompanyParentDetails = (VwCompanyParentDetails) session.get(VwCompanyParentDetailsImpl.class,
                    primaryKey);

            if (vwCompanyParentDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwCompanyParentDetails);
        } catch (NoSuchVwCompanyParentDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwCompanyParentDetails removeImpl(
        VwCompanyParentDetails vwCompanyParentDetails)
        throws SystemException {
        vwCompanyParentDetails = toUnwrappedModel(vwCompanyParentDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwCompanyParentDetails)) {
                vwCompanyParentDetails = (VwCompanyParentDetails) session.get(VwCompanyParentDetailsImpl.class,
                        vwCompanyParentDetails.getPrimaryKeyObj());
            }

            if (vwCompanyParentDetails != null) {
                session.delete(vwCompanyParentDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwCompanyParentDetails != null) {
            clearCache(vwCompanyParentDetails);
        }

        return vwCompanyParentDetails;
    }

    @Override
    public VwCompanyParentDetails updateImpl(
        com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails)
        throws SystemException {
        vwCompanyParentDetails = toUnwrappedModel(vwCompanyParentDetails);

        boolean isNew = vwCompanyParentDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwCompanyParentDetails.isNew()) {
                session.save(vwCompanyParentDetails);

                vwCompanyParentDetails.setNew(false);
            } else {
                session.merge(vwCompanyParentDetails);
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

        EntityCacheUtil.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyParentDetailsImpl.class,
            vwCompanyParentDetails.getPrimaryKey(), vwCompanyParentDetails);

        return vwCompanyParentDetails;
    }

    protected VwCompanyParentDetails toUnwrappedModel(
        VwCompanyParentDetails vwCompanyParentDetails) {
        if (vwCompanyParentDetails instanceof VwCompanyParentDetailsImpl) {
            return vwCompanyParentDetails;
        }

        VwCompanyParentDetailsImpl vwCompanyParentDetailsImpl = new VwCompanyParentDetailsImpl();

        vwCompanyParentDetailsImpl.setNew(vwCompanyParentDetails.isNew());
        vwCompanyParentDetailsImpl.setPrimaryKey(vwCompanyParentDetails.getPrimaryKey());

        vwCompanyParentDetailsImpl.setParentCompanyId(vwCompanyParentDetails.getParentCompanyId());
        vwCompanyParentDetailsImpl.setPriorParentCompanyId(vwCompanyParentDetails.getPriorParentCompanyId());
        vwCompanyParentDetailsImpl.setCompanyId(vwCompanyParentDetails.getCompanyId());
        vwCompanyParentDetailsImpl.setLastUpdatedDate(vwCompanyParentDetails.getLastUpdatedDate());
        vwCompanyParentDetailsImpl.setParentEndDate(vwCompanyParentDetails.getParentEndDate());
        vwCompanyParentDetailsImpl.setModifiedDate(vwCompanyParentDetails.getModifiedDate());
        vwCompanyParentDetailsImpl.setPriorParentStartDate(vwCompanyParentDetails.getPriorParentStartDate());
        vwCompanyParentDetailsImpl.setSource(vwCompanyParentDetails.getSource());
        vwCompanyParentDetailsImpl.setCreatedBy(vwCompanyParentDetails.getCreatedBy());
        vwCompanyParentDetailsImpl.setCreatedDate(vwCompanyParentDetails.getCreatedDate());
        vwCompanyParentDetailsImpl.setCompanyParentDetailsSid(vwCompanyParentDetails.getCompanyParentDetailsSid());
        vwCompanyParentDetailsImpl.setBatchId(vwCompanyParentDetails.getBatchId());
        vwCompanyParentDetailsImpl.setAddChgDelIndicator(vwCompanyParentDetails.getAddChgDelIndicator());
        vwCompanyParentDetailsImpl.setModifiedBy(vwCompanyParentDetails.getModifiedBy());
        vwCompanyParentDetailsImpl.setParentStartDate(vwCompanyParentDetails.getParentStartDate());

        return vwCompanyParentDetailsImpl;
    }

    /**
     * Returns the vw company parent details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw company parent details
     * @return the vw company parent details
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwCompanyParentDetailsException, SystemException {
        VwCompanyParentDetails vwCompanyParentDetails = fetchByPrimaryKey(primaryKey);

        if (vwCompanyParentDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwCompanyParentDetails;
    }

    /**
     * Returns the vw company parent details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException} if it could not be found.
     *
     * @param companyParentDetailsSid the primary key of the vw company parent details
     * @return the vw company parent details
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
        throws NoSuchVwCompanyParentDetailsException, SystemException {
        return findByPrimaryKey((Serializable) companyParentDetailsSid);
    }

    /**
     * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw company parent details
     * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwCompanyParentDetails vwCompanyParentDetails = (VwCompanyParentDetails) EntityCacheUtil.getResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyParentDetailsImpl.class, primaryKey);

        if (vwCompanyParentDetails == _nullVwCompanyParentDetails) {
            return null;
        }

        if (vwCompanyParentDetails == null) {
            Session session = null;

            try {
                session = openSession();

                vwCompanyParentDetails = (VwCompanyParentDetails) session.get(VwCompanyParentDetailsImpl.class,
                        primaryKey);

                if (vwCompanyParentDetails != null) {
                    cacheResult(vwCompanyParentDetails);
                } else {
                    EntityCacheUtil.putResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyParentDetailsImpl.class, primaryKey,
                        _nullVwCompanyParentDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwCompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    VwCompanyParentDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwCompanyParentDetails;
    }

    /**
     * Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyParentDetailsSid the primary key of the vw company parent details
     * @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyParentDetailsSid);
    }

    /**
     * Returns all the vw company parent detailses.
     *
     * @return the vw company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyParentDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw company parent detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company parent detailses
     * @param end the upper bound of the range of vw company parent detailses (not inclusive)
     * @return the range of vw company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyParentDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw company parent detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company parent detailses
     * @param end the upper bound of the range of vw company parent detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyParentDetails> findAll(int start, int end,
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

        List<VwCompanyParentDetails> list = (List<VwCompanyParentDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWCOMPANYPARENTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWCOMPANYPARENTDETAILS;

                if (pagination) {
                    sql = sql.concat(VwCompanyParentDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwCompanyParentDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwCompanyParentDetails>(list);
                } else {
                    list = (List<VwCompanyParentDetails>) QueryUtil.list(q,
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
     * Removes all the vw company parent detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwCompanyParentDetails vwCompanyParentDetails : findAll()) {
            remove(vwCompanyParentDetails);
        }
    }

    /**
     * Returns the number of vw company parent detailses.
     *
     * @return the number of vw company parent detailses
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

                Query q = session.createQuery(_SQL_COUNT_VWCOMPANYPARENTDETAILS);

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
     * Initializes the vw company parent details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwCompanyParentDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwCompanyParentDetails>> listenersList = new ArrayList<ModelListener<VwCompanyParentDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwCompanyParentDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwCompanyParentDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
