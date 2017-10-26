package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyGroupDetailsException;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.impl.CompanyGroupDetailsImpl;
import com.stpl.app.model.impl.CompanyGroupDetailsModelImpl;
import com.stpl.app.service.persistence.CompanyGroupDetailsPersistence;

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
 * The persistence implementation for the company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupDetailsPersistence
 * @see CompanyGroupDetailsUtil
 * @generated
 */
public class CompanyGroupDetailsPersistenceImpl extends BasePersistenceImpl<CompanyGroupDetails>
    implements CompanyGroupDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyGroupDetailsUtil} to access the company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyGroupDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            CompanyGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            CompanyGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COMPANYGROUPDETAILS = "SELECT companyGroupDetails FROM CompanyGroupDetails companyGroupDetails";
    private static final String _SQL_COUNT_COMPANYGROUPDETAILS = "SELECT COUNT(companyGroupDetails) FROM CompanyGroupDetails companyGroupDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyGroupDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyGroupDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyGroupDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "companyParentDetailsSid",
                "companyTradeclassSid", "companyGroupSid", "versionNo",
                "companyGroupDetailsSid", "modifiedBy", "modifiedDate",
                "companyMasterSid"
            });
    private static CompanyGroupDetails _nullCompanyGroupDetails = new CompanyGroupDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyGroupDetails> toCacheModel() {
                return _nullCompanyGroupDetailsCacheModel;
            }
        };

    private static CacheModel<CompanyGroupDetails> _nullCompanyGroupDetailsCacheModel =
        new CacheModel<CompanyGroupDetails>() {
            @Override
            public CompanyGroupDetails toEntityModel() {
                return _nullCompanyGroupDetails;
            }
        };

    public CompanyGroupDetailsPersistenceImpl() {
        setModelClass(CompanyGroupDetails.class);
    }

    /**
     * Caches the company group details in the entity cache if it is enabled.
     *
     * @param companyGroupDetails the company group details
     */
    @Override
    public void cacheResult(CompanyGroupDetails companyGroupDetails) {
        EntityCacheUtil.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey(),
            companyGroupDetails);

        companyGroupDetails.resetOriginalValues();
    }

    /**
     * Caches the company group detailses in the entity cache if it is enabled.
     *
     * @param companyGroupDetailses the company group detailses
     */
    @Override
    public void cacheResult(List<CompanyGroupDetails> companyGroupDetailses) {
        for (CompanyGroupDetails companyGroupDetails : companyGroupDetailses) {
            if (EntityCacheUtil.getResult(
                        CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyGroupDetailsImpl.class,
                        companyGroupDetails.getPrimaryKey()) == null) {
                cacheResult(companyGroupDetails);
            } else {
                companyGroupDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company group detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyGroupDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyGroupDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company group details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyGroupDetails companyGroupDetails) {
        EntityCacheUtil.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyGroupDetails> companyGroupDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyGroupDetails companyGroupDetails : companyGroupDetailses) {
            EntityCacheUtil.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CompanyGroupDetailsImpl.class,
                companyGroupDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new company group details with the primary key. Does not add the company group details to the database.
     *
     * @param companyGroupDetailsSid the primary key for the new company group details
     * @return the new company group details
     */
    @Override
    public CompanyGroupDetails create(int companyGroupDetailsSid) {
        CompanyGroupDetails companyGroupDetails = new CompanyGroupDetailsImpl();

        companyGroupDetails.setNew(true);
        companyGroupDetails.setPrimaryKey(companyGroupDetailsSid);

        return companyGroupDetails;
    }

    /**
     * Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyGroupDetailsSid the primary key of the company group details
     * @return the company group details that was removed
     * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails remove(int companyGroupDetailsSid)
        throws NoSuchCompanyGroupDetailsException, SystemException {
        return remove((Serializable) companyGroupDetailsSid);
    }

    /**
     * Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company group details
     * @return the company group details that was removed
     * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails remove(Serializable primaryKey)
        throws NoSuchCompanyGroupDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyGroupDetails companyGroupDetails = (CompanyGroupDetails) session.get(CompanyGroupDetailsImpl.class,
                    primaryKey);

            if (companyGroupDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyGroupDetails);
        } catch (NoSuchCompanyGroupDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyGroupDetails removeImpl(
        CompanyGroupDetails companyGroupDetails) throws SystemException {
        companyGroupDetails = toUnwrappedModel(companyGroupDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyGroupDetails)) {
                companyGroupDetails = (CompanyGroupDetails) session.get(CompanyGroupDetailsImpl.class,
                        companyGroupDetails.getPrimaryKeyObj());
            }

            if (companyGroupDetails != null) {
                session.delete(companyGroupDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyGroupDetails != null) {
            clearCache(companyGroupDetails);
        }

        return companyGroupDetails;
    }

    @Override
    public CompanyGroupDetails updateImpl(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails)
        throws SystemException {
        companyGroupDetails = toUnwrappedModel(companyGroupDetails);

        boolean isNew = companyGroupDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (companyGroupDetails.isNew()) {
                session.save(companyGroupDetails);

                companyGroupDetails.setNew(false);
            } else {
                session.merge(companyGroupDetails);
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

        EntityCacheUtil.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupDetailsImpl.class, companyGroupDetails.getPrimaryKey(),
            companyGroupDetails);

        return companyGroupDetails;
    }

    protected CompanyGroupDetails toUnwrappedModel(
        CompanyGroupDetails companyGroupDetails) {
        if (companyGroupDetails instanceof CompanyGroupDetailsImpl) {
            return companyGroupDetails;
        }

        CompanyGroupDetailsImpl companyGroupDetailsImpl = new CompanyGroupDetailsImpl();

        companyGroupDetailsImpl.setNew(companyGroupDetails.isNew());
        companyGroupDetailsImpl.setPrimaryKey(companyGroupDetails.getPrimaryKey());

        companyGroupDetailsImpl.setCreatedDate(companyGroupDetails.getCreatedDate());
        companyGroupDetailsImpl.setCreatedBy(companyGroupDetails.getCreatedBy());
        companyGroupDetailsImpl.setCompanyParentDetailsSid(companyGroupDetails.getCompanyParentDetailsSid());
        companyGroupDetailsImpl.setCompanyTradeclassSid(companyGroupDetails.getCompanyTradeclassSid());
        companyGroupDetailsImpl.setCompanyGroupSid(companyGroupDetails.getCompanyGroupSid());
        companyGroupDetailsImpl.setVersionNo(companyGroupDetails.getVersionNo());
        companyGroupDetailsImpl.setCompanyGroupDetailsSid(companyGroupDetails.getCompanyGroupDetailsSid());
        companyGroupDetailsImpl.setModifiedBy(companyGroupDetails.getModifiedBy());
        companyGroupDetailsImpl.setModifiedDate(companyGroupDetails.getModifiedDate());
        companyGroupDetailsImpl.setCompanyMasterSid(companyGroupDetails.getCompanyMasterSid());

        return companyGroupDetailsImpl;
    }

    /**
     * Returns the company group details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company group details
     * @return the company group details
     * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyGroupDetailsException, SystemException {
        CompanyGroupDetails companyGroupDetails = fetchByPrimaryKey(primaryKey);

        if (companyGroupDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyGroupDetails;
    }

    /**
     * Returns the company group details with the primary key or throws a {@link com.stpl.app.NoSuchCompanyGroupDetailsException} if it could not be found.
     *
     * @param companyGroupDetailsSid the primary key of the company group details
     * @return the company group details
     * @throws com.stpl.app.NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails findByPrimaryKey(int companyGroupDetailsSid)
        throws NoSuchCompanyGroupDetailsException, SystemException {
        return findByPrimaryKey((Serializable) companyGroupDetailsSid);
    }

    /**
     * Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company group details
     * @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyGroupDetails companyGroupDetails = (CompanyGroupDetails) EntityCacheUtil.getResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CompanyGroupDetailsImpl.class, primaryKey);

        if (companyGroupDetails == _nullCompanyGroupDetails) {
            return null;
        }

        if (companyGroupDetails == null) {
            Session session = null;

            try {
                session = openSession();

                companyGroupDetails = (CompanyGroupDetails) session.get(CompanyGroupDetailsImpl.class,
                        primaryKey);

                if (companyGroupDetails != null) {
                    cacheResult(companyGroupDetails);
                } else {
                    EntityCacheUtil.putResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyGroupDetailsImpl.class, primaryKey,
                        _nullCompanyGroupDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyGroupDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyGroupDetails;
    }

    /**
     * Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyGroupDetailsSid the primary key of the company group details
     * @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroupDetails fetchByPrimaryKey(int companyGroupDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyGroupDetailsSid);
    }

    /**
     * Returns all the company group detailses.
     *
     * @return the company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroupDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company group detailses
     * @param end the upper bound of the range of company group detailses (not inclusive)
     * @return the range of company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroupDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company group detailses
     * @param end the upper bound of the range of company group detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroupDetails> findAll(int start, int end,
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

        List<CompanyGroupDetails> list = (List<CompanyGroupDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYGROUPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYGROUPDETAILS;

                if (pagination) {
                    sql = sql.concat(CompanyGroupDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyGroupDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyGroupDetails>(list);
                } else {
                    list = (List<CompanyGroupDetails>) QueryUtil.list(q,
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
     * Removes all the company group detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyGroupDetails companyGroupDetails : findAll()) {
            remove(companyGroupDetails);
        }
    }

    /**
     * Returns the number of company group detailses.
     *
     * @return the number of company group detailses
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYGROUPDETAILS);

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
     * Initializes the company group details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyGroupDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyGroupDetails>> listenersList = new ArrayList<ModelListener<CompanyGroupDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyGroupDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyGroupDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
