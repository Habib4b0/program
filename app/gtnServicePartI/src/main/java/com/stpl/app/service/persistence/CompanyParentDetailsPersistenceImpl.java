package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyParentDetailsException;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.impl.CompanyParentDetailsImpl;
import com.stpl.app.model.impl.CompanyParentDetailsModelImpl;
import com.stpl.app.service.persistence.CompanyParentDetailsPersistence;

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
 * The persistence implementation for the company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyParentDetailsPersistence
 * @see CompanyParentDetailsUtil
 * @generated
 */
public class CompanyParentDetailsPersistenceImpl extends BasePersistenceImpl<CompanyParentDetails>
    implements CompanyParentDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyParentDetailsUtil} to access the company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyParentDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
            CompanyParentDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED,
            CompanyParentDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COMPANYPARENTDETAILS = "SELECT companyParentDetails FROM CompanyParentDetails companyParentDetails";
    private static final String _SQL_COUNT_COMPANYPARENTDETAILS = "SELECT COUNT(companyParentDetails) FROM CompanyParentDetails companyParentDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyParentDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyParentDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyParentDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastUpdatedDate", "parentEndDate", "modifiedDate",
                "parentCompanyMasterSid", "recordLockStatus",
                "priorParentStartDate", "createdDate", "source", "createdBy",
                "companyParentDetailsSid", "priorParentCmpyMasterSid", "batchId",
                "modifiedBy", "inboundStatus", "companyMasterSid",
                "parentStartDate"
            });
    private static CompanyParentDetails _nullCompanyParentDetails = new CompanyParentDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyParentDetails> toCacheModel() {
                return _nullCompanyParentDetailsCacheModel;
            }
        };

    private static CacheModel<CompanyParentDetails> _nullCompanyParentDetailsCacheModel =
        new CacheModel<CompanyParentDetails>() {
            @Override
            public CompanyParentDetails toEntityModel() {
                return _nullCompanyParentDetails;
            }
        };

    public CompanyParentDetailsPersistenceImpl() {
        setModelClass(CompanyParentDetails.class);
    }

    /**
     * Caches the company parent details in the entity cache if it is enabled.
     *
     * @param companyParentDetails the company parent details
     */
    @Override
    public void cacheResult(CompanyParentDetails companyParentDetails) {
        EntityCacheUtil.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsImpl.class,
            companyParentDetails.getPrimaryKey(), companyParentDetails);

        companyParentDetails.resetOriginalValues();
    }

    /**
     * Caches the company parent detailses in the entity cache if it is enabled.
     *
     * @param companyParentDetailses the company parent detailses
     */
    @Override
    public void cacheResult(List<CompanyParentDetails> companyParentDetailses) {
        for (CompanyParentDetails companyParentDetails : companyParentDetailses) {
            if (EntityCacheUtil.getResult(
                        CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyParentDetailsImpl.class,
                        companyParentDetails.getPrimaryKey()) == null) {
                cacheResult(companyParentDetails);
            } else {
                companyParentDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company parent detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyParentDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyParentDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company parent details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyParentDetails companyParentDetails) {
        EntityCacheUtil.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsImpl.class, companyParentDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyParentDetails> companyParentDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyParentDetails companyParentDetails : companyParentDetailses) {
            EntityCacheUtil.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CompanyParentDetailsImpl.class,
                companyParentDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new company parent details with the primary key. Does not add the company parent details to the database.
     *
     * @param companyParentDetailsSid the primary key for the new company parent details
     * @return the new company parent details
     */
    @Override
    public CompanyParentDetails create(int companyParentDetailsSid) {
        CompanyParentDetails companyParentDetails = new CompanyParentDetailsImpl();

        companyParentDetails.setNew(true);
        companyParentDetails.setPrimaryKey(companyParentDetailsSid);

        return companyParentDetails;
    }

    /**
     * Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyParentDetailsSid the primary key of the company parent details
     * @return the company parent details that was removed
     * @throws com.stpl.app.NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails remove(int companyParentDetailsSid)
        throws NoSuchCompanyParentDetailsException, SystemException {
        return remove((Serializable) companyParentDetailsSid);
    }

    /**
     * Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company parent details
     * @return the company parent details that was removed
     * @throws com.stpl.app.NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails remove(Serializable primaryKey)
        throws NoSuchCompanyParentDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyParentDetails companyParentDetails = (CompanyParentDetails) session.get(CompanyParentDetailsImpl.class,
                    primaryKey);

            if (companyParentDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyParentDetails);
        } catch (NoSuchCompanyParentDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyParentDetails removeImpl(
        CompanyParentDetails companyParentDetails) throws SystemException {
        companyParentDetails = toUnwrappedModel(companyParentDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyParentDetails)) {
                companyParentDetails = (CompanyParentDetails) session.get(CompanyParentDetailsImpl.class,
                        companyParentDetails.getPrimaryKeyObj());
            }

            if (companyParentDetails != null) {
                session.delete(companyParentDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyParentDetails != null) {
            clearCache(companyParentDetails);
        }

        return companyParentDetails;
    }

    @Override
    public CompanyParentDetails updateImpl(
        com.stpl.app.model.CompanyParentDetails companyParentDetails)
        throws SystemException {
        companyParentDetails = toUnwrappedModel(companyParentDetails);

        boolean isNew = companyParentDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (companyParentDetails.isNew()) {
                session.save(companyParentDetails);

                companyParentDetails.setNew(false);
            } else {
                session.merge(companyParentDetails);
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

        EntityCacheUtil.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyParentDetailsImpl.class,
            companyParentDetails.getPrimaryKey(), companyParentDetails);

        return companyParentDetails;
    }

    protected CompanyParentDetails toUnwrappedModel(
        CompanyParentDetails companyParentDetails) {
        if (companyParentDetails instanceof CompanyParentDetailsImpl) {
            return companyParentDetails;
        }

        CompanyParentDetailsImpl companyParentDetailsImpl = new CompanyParentDetailsImpl();

        companyParentDetailsImpl.setNew(companyParentDetails.isNew());
        companyParentDetailsImpl.setPrimaryKey(companyParentDetails.getPrimaryKey());

        companyParentDetailsImpl.setLastUpdatedDate(companyParentDetails.getLastUpdatedDate());
        companyParentDetailsImpl.setParentEndDate(companyParentDetails.getParentEndDate());
        companyParentDetailsImpl.setModifiedDate(companyParentDetails.getModifiedDate());
        companyParentDetailsImpl.setParentCompanyMasterSid(companyParentDetails.getParentCompanyMasterSid());
        companyParentDetailsImpl.setRecordLockStatus(companyParentDetails.isRecordLockStatus());
        companyParentDetailsImpl.setPriorParentStartDate(companyParentDetails.getPriorParentStartDate());
        companyParentDetailsImpl.setCreatedDate(companyParentDetails.getCreatedDate());
        companyParentDetailsImpl.setSource(companyParentDetails.getSource());
        companyParentDetailsImpl.setCreatedBy(companyParentDetails.getCreatedBy());
        companyParentDetailsImpl.setCompanyParentDetailsSid(companyParentDetails.getCompanyParentDetailsSid());
        companyParentDetailsImpl.setPriorParentCmpyMasterSid(companyParentDetails.getPriorParentCmpyMasterSid());
        companyParentDetailsImpl.setBatchId(companyParentDetails.getBatchId());
        companyParentDetailsImpl.setModifiedBy(companyParentDetails.getModifiedBy());
        companyParentDetailsImpl.setInboundStatus(companyParentDetails.getInboundStatus());
        companyParentDetailsImpl.setCompanyMasterSid(companyParentDetails.getCompanyMasterSid());
        companyParentDetailsImpl.setParentStartDate(companyParentDetails.getParentStartDate());

        return companyParentDetailsImpl;
    }

    /**
     * Returns the company parent details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company parent details
     * @return the company parent details
     * @throws com.stpl.app.NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyParentDetailsException, SystemException {
        CompanyParentDetails companyParentDetails = fetchByPrimaryKey(primaryKey);

        if (companyParentDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyParentDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyParentDetails;
    }

    /**
     * Returns the company parent details with the primary key or throws a {@link com.stpl.app.NoSuchCompanyParentDetailsException} if it could not be found.
     *
     * @param companyParentDetailsSid the primary key of the company parent details
     * @return the company parent details
     * @throws com.stpl.app.NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
        throws NoSuchCompanyParentDetailsException, SystemException {
        return findByPrimaryKey((Serializable) companyParentDetailsSid);
    }

    /**
     * Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company parent details
     * @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyParentDetails companyParentDetails = (CompanyParentDetails) EntityCacheUtil.getResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CompanyParentDetailsImpl.class, primaryKey);

        if (companyParentDetails == _nullCompanyParentDetails) {
            return null;
        }

        if (companyParentDetails == null) {
            Session session = null;

            try {
                session = openSession();

                companyParentDetails = (CompanyParentDetails) session.get(CompanyParentDetailsImpl.class,
                        primaryKey);

                if (companyParentDetails != null) {
                    cacheResult(companyParentDetails);
                } else {
                    EntityCacheUtil.putResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyParentDetailsImpl.class, primaryKey,
                        _nullCompanyParentDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyParentDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyParentDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyParentDetails;
    }

    /**
     * Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyParentDetailsSid the primary key of the company parent details
     * @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyParentDetailsSid);
    }

    /**
     * Returns all the company parent detailses.
     *
     * @return the company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyParentDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company parent detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company parent detailses
     * @param end the upper bound of the range of company parent detailses (not inclusive)
     * @return the range of company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyParentDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company parent detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company parent detailses
     * @param end the upper bound of the range of company parent detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company parent detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyParentDetails> findAll(int start, int end,
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

        List<CompanyParentDetails> list = (List<CompanyParentDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYPARENTDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYPARENTDETAILS;

                if (pagination) {
                    sql = sql.concat(CompanyParentDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyParentDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyParentDetails>(list);
                } else {
                    list = (List<CompanyParentDetails>) QueryUtil.list(q,
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
     * Removes all the company parent detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyParentDetails companyParentDetails : findAll()) {
            remove(companyParentDetails);
        }
    }

    /**
     * Returns the number of company parent detailses.
     *
     * @return the number of company parent detailses
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYPARENTDETAILS);

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
     * Initializes the company parent details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyParentDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyParentDetails>> listenersList = new ArrayList<ModelListener<CompanyParentDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyParentDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyParentDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
