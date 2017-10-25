package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCdrDetailsException;
import com.stpl.app.model.CdrDetails;
import com.stpl.app.model.impl.CdrDetailsImpl;
import com.stpl.app.model.impl.CdrDetailsModelImpl;
import com.stpl.app.service.persistence.CdrDetailsPersistence;

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
 * The persistence implementation for the cdr details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrDetailsPersistence
 * @see CdrDetailsUtil
 * @generated
 */
public class CdrDetailsPersistenceImpl extends BasePersistenceImpl<CdrDetails>
    implements CdrDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CdrDetailsUtil} to access the cdr details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CdrDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsModelImpl.FINDER_CACHE_ENABLED, CdrDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsModelImpl.FINDER_CACHE_ENABLED, CdrDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CDRDETAILS = "SELECT cdrDetails FROM CdrDetails cdrDetails";
    private static final String _SQL_COUNT_CDRDETAILS = "SELECT COUNT(cdrDetails) FROM CdrDetails cdrDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cdrDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CdrDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CdrDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "createdDate", "lineType", "keyword",
                "itemGroupMsAssociation", "value", "modifiedDate",
                "logicalOperator", "operator", "cdrDetailsSid", "cdrModelSid",
                "comparison"
            });
    private static CdrDetails _nullCdrDetails = new CdrDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CdrDetails> toCacheModel() {
                return _nullCdrDetailsCacheModel;
            }
        };

    private static CacheModel<CdrDetails> _nullCdrDetailsCacheModel = new CacheModel<CdrDetails>() {
            @Override
            public CdrDetails toEntityModel() {
                return _nullCdrDetails;
            }
        };

    public CdrDetailsPersistenceImpl() {
        setModelClass(CdrDetails.class);
    }

    /**
     * Caches the cdr details in the entity cache if it is enabled.
     *
     * @param cdrDetails the cdr details
     */
    @Override
    public void cacheResult(CdrDetails cdrDetails) {
        EntityCacheUtil.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsImpl.class, cdrDetails.getPrimaryKey(), cdrDetails);

        cdrDetails.resetOriginalValues();
    }

    /**
     * Caches the cdr detailses in the entity cache if it is enabled.
     *
     * @param cdrDetailses the cdr detailses
     */
    @Override
    public void cacheResult(List<CdrDetails> cdrDetailses) {
        for (CdrDetails cdrDetails : cdrDetailses) {
            if (EntityCacheUtil.getResult(
                        CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CdrDetailsImpl.class, cdrDetails.getPrimaryKey()) == null) {
                cacheResult(cdrDetails);
            } else {
                cdrDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cdr detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CdrDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CdrDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cdr details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CdrDetails cdrDetails) {
        EntityCacheUtil.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsImpl.class, cdrDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CdrDetails> cdrDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CdrDetails cdrDetails : cdrDetailses) {
            EntityCacheUtil.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CdrDetailsImpl.class, cdrDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cdr details with the primary key. Does not add the cdr details to the database.
     *
     * @param cdrDetailsSid the primary key for the new cdr details
     * @return the new cdr details
     */
    @Override
    public CdrDetails create(int cdrDetailsSid) {
        CdrDetails cdrDetails = new CdrDetailsImpl();

        cdrDetails.setNew(true);
        cdrDetails.setPrimaryKey(cdrDetailsSid);

        return cdrDetails;
    }

    /**
     * Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cdrDetailsSid the primary key of the cdr details
     * @return the cdr details that was removed
     * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails remove(int cdrDetailsSid)
        throws NoSuchCdrDetailsException, SystemException {
        return remove((Serializable) cdrDetailsSid);
    }

    /**
     * Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cdr details
     * @return the cdr details that was removed
     * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails remove(Serializable primaryKey)
        throws NoSuchCdrDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CdrDetails cdrDetails = (CdrDetails) session.get(CdrDetailsImpl.class,
                    primaryKey);

            if (cdrDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCdrDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cdrDetails);
        } catch (NoSuchCdrDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CdrDetails removeImpl(CdrDetails cdrDetails)
        throws SystemException {
        cdrDetails = toUnwrappedModel(cdrDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cdrDetails)) {
                cdrDetails = (CdrDetails) session.get(CdrDetailsImpl.class,
                        cdrDetails.getPrimaryKeyObj());
            }

            if (cdrDetails != null) {
                session.delete(cdrDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cdrDetails != null) {
            clearCache(cdrDetails);
        }

        return cdrDetails;
    }

    @Override
    public CdrDetails updateImpl(com.stpl.app.model.CdrDetails cdrDetails)
        throws SystemException {
        cdrDetails = toUnwrappedModel(cdrDetails);

        boolean isNew = cdrDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cdrDetails.isNew()) {
                session.save(cdrDetails);

                cdrDetails.setNew(false);
            } else {
                session.merge(cdrDetails);
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

        EntityCacheUtil.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CdrDetailsImpl.class, cdrDetails.getPrimaryKey(), cdrDetails);

        return cdrDetails;
    }

    protected CdrDetails toUnwrappedModel(CdrDetails cdrDetails) {
        if (cdrDetails instanceof CdrDetailsImpl) {
            return cdrDetails;
        }

        CdrDetailsImpl cdrDetailsImpl = new CdrDetailsImpl();

        cdrDetailsImpl.setNew(cdrDetails.isNew());
        cdrDetailsImpl.setPrimaryKey(cdrDetails.getPrimaryKey());

        cdrDetailsImpl.setCreatedBy(cdrDetails.getCreatedBy());
        cdrDetailsImpl.setModifiedBy(cdrDetails.getModifiedBy());
        cdrDetailsImpl.setCreatedDate(cdrDetails.getCreatedDate());
        cdrDetailsImpl.setLineType(cdrDetails.getLineType());
        cdrDetailsImpl.setKeyword(cdrDetails.getKeyword());
        cdrDetailsImpl.setItemGroupMsAssociation(cdrDetails.getItemGroupMsAssociation());
        cdrDetailsImpl.setValue(cdrDetails.getValue());
        cdrDetailsImpl.setModifiedDate(cdrDetails.getModifiedDate());
        cdrDetailsImpl.setLogicalOperator(cdrDetails.getLogicalOperator());
        cdrDetailsImpl.setOperator(cdrDetails.getOperator());
        cdrDetailsImpl.setCdrDetailsSid(cdrDetails.getCdrDetailsSid());
        cdrDetailsImpl.setCdrModelSid(cdrDetails.getCdrModelSid());
        cdrDetailsImpl.setComparison(cdrDetails.getComparison());

        return cdrDetailsImpl;
    }

    /**
     * Returns the cdr details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cdr details
     * @return the cdr details
     * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCdrDetailsException, SystemException {
        CdrDetails cdrDetails = fetchByPrimaryKey(primaryKey);

        if (cdrDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCdrDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cdrDetails;
    }

    /**
     * Returns the cdr details with the primary key or throws a {@link com.stpl.app.NoSuchCdrDetailsException} if it could not be found.
     *
     * @param cdrDetailsSid the primary key of the cdr details
     * @return the cdr details
     * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails findByPrimaryKey(int cdrDetailsSid)
        throws NoSuchCdrDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cdrDetailsSid);
    }

    /**
     * Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cdr details
     * @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CdrDetails cdrDetails = (CdrDetails) EntityCacheUtil.getResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CdrDetailsImpl.class, primaryKey);

        if (cdrDetails == _nullCdrDetails) {
            return null;
        }

        if (cdrDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cdrDetails = (CdrDetails) session.get(CdrDetailsImpl.class,
                        primaryKey);

                if (cdrDetails != null) {
                    cacheResult(cdrDetails);
                } else {
                    EntityCacheUtil.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CdrDetailsImpl.class, primaryKey, _nullCdrDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CdrDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cdrDetails;
    }

    /**
     * Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cdrDetailsSid the primary key of the cdr details
     * @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CdrDetails fetchByPrimaryKey(int cdrDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cdrDetailsSid);
    }

    /**
     * Returns all the cdr detailses.
     *
     * @return the cdr detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cdr detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cdr detailses
     * @param end the upper bound of the range of cdr detailses (not inclusive)
     * @return the range of cdr detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cdr detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cdr detailses
     * @param end the upper bound of the range of cdr detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cdr detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CdrDetails> findAll(int start, int end,
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

        List<CdrDetails> list = (List<CdrDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CDRDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CDRDETAILS;

                if (pagination) {
                    sql = sql.concat(CdrDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CdrDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CdrDetails>(list);
                } else {
                    list = (List<CdrDetails>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the cdr detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CdrDetails cdrDetails : findAll()) {
            remove(cdrDetails);
        }
    }

    /**
     * Returns the number of cdr detailses.
     *
     * @return the number of cdr detailses
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

                Query q = session.createQuery(_SQL_COUNT_CDRDETAILS);

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
     * Initializes the cdr details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CdrDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CdrDetails>> listenersList = new ArrayList<ModelListener<CdrDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CdrDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CdrDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
