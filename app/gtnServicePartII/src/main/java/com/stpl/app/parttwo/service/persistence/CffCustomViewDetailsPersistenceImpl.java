package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.impl.CffCustomViewDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsPersistence;

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
 * The persistence implementation for the cff custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewDetailsPersistence
 * @see CffCustomViewDetailsUtil
 * @generated
 */
public class CffCustomViewDetailsPersistenceImpl extends BasePersistenceImpl<CffCustomViewDetails>
    implements CffCustomViewDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffCustomViewDetailsUtil} to access the cff custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffCustomViewDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffCustomViewDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffCustomViewDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFCUSTOMVIEWDETAILS = "SELECT cffCustomViewDetails FROM CffCustomViewDetails cffCustomViewDetails";
    private static final String _SQL_COUNT_CFFCUSTOMVIEWDETAILS = "SELECT COUNT(cffCustomViewDetails) FROM CffCustomViewDetails cffCustomViewDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffCustomViewDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffCustomViewDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffCustomViewDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "hierarchyId", "hierarchyIndicator", "cffCustomViewDetailsSid",
                "levelNo", "cffCustomViewMasterSid"
            });
    private static CffCustomViewDetails _nullCffCustomViewDetails = new CffCustomViewDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffCustomViewDetails> toCacheModel() {
                return _nullCffCustomViewDetailsCacheModel;
            }
        };

    private static CacheModel<CffCustomViewDetails> _nullCffCustomViewDetailsCacheModel =
        new CacheModel<CffCustomViewDetails>() {
            @Override
            public CffCustomViewDetails toEntityModel() {
                return _nullCffCustomViewDetails;
            }
        };

    public CffCustomViewDetailsPersistenceImpl() {
        setModelClass(CffCustomViewDetails.class);
    }

    /**
     * Caches the cff custom view details in the entity cache if it is enabled.
     *
     * @param cffCustomViewDetails the cff custom view details
     */
    @Override
    public void cacheResult(CffCustomViewDetails cffCustomViewDetails) {
        EntityCacheUtil.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsImpl.class,
            cffCustomViewDetails.getPrimaryKey(), cffCustomViewDetails);

        cffCustomViewDetails.resetOriginalValues();
    }

    /**
     * Caches the cff custom view detailses in the entity cache if it is enabled.
     *
     * @param cffCustomViewDetailses the cff custom view detailses
     */
    @Override
    public void cacheResult(List<CffCustomViewDetails> cffCustomViewDetailses) {
        for (CffCustomViewDetails cffCustomViewDetails : cffCustomViewDetailses) {
            if (EntityCacheUtil.getResult(
                        CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffCustomViewDetailsImpl.class,
                        cffCustomViewDetails.getPrimaryKey()) == null) {
                cacheResult(cffCustomViewDetails);
            } else {
                cffCustomViewDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff custom view detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffCustomViewDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffCustomViewDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff custom view details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffCustomViewDetails cffCustomViewDetails) {
        EntityCacheUtil.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsImpl.class, cffCustomViewDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffCustomViewDetails> cffCustomViewDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffCustomViewDetails cffCustomViewDetails : cffCustomViewDetailses) {
            EntityCacheUtil.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffCustomViewDetailsImpl.class,
                cffCustomViewDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
     *
     * @param cffCustomViewDetailsSid the primary key for the new cff custom view details
     * @return the new cff custom view details
     */
    @Override
    public CffCustomViewDetails create(int cffCustomViewDetailsSid) {
        CffCustomViewDetails cffCustomViewDetails = new CffCustomViewDetailsImpl();

        cffCustomViewDetails.setNew(true);
        cffCustomViewDetails.setPrimaryKey(cffCustomViewDetailsSid);

        return cffCustomViewDetails;
    }

    /**
     * Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffCustomViewDetailsSid the primary key of the cff custom view details
     * @return the cff custom view details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails remove(int cffCustomViewDetailsSid)
        throws NoSuchCffCustomViewDetailsException, SystemException {
        return remove((Serializable) cffCustomViewDetailsSid);
    }

    /**
     * Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff custom view details
     * @return the cff custom view details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails remove(Serializable primaryKey)
        throws NoSuchCffCustomViewDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffCustomViewDetails cffCustomViewDetails = (CffCustomViewDetails) session.get(CffCustomViewDetailsImpl.class,
                    primaryKey);

            if (cffCustomViewDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffCustomViewDetails);
        } catch (NoSuchCffCustomViewDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffCustomViewDetails removeImpl(
        CffCustomViewDetails cffCustomViewDetails) throws SystemException {
        cffCustomViewDetails = toUnwrappedModel(cffCustomViewDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffCustomViewDetails)) {
                cffCustomViewDetails = (CffCustomViewDetails) session.get(CffCustomViewDetailsImpl.class,
                        cffCustomViewDetails.getPrimaryKeyObj());
            }

            if (cffCustomViewDetails != null) {
                session.delete(cffCustomViewDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffCustomViewDetails != null) {
            clearCache(cffCustomViewDetails);
        }

        return cffCustomViewDetails;
    }

    @Override
    public CffCustomViewDetails updateImpl(
        com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails)
        throws SystemException {
        cffCustomViewDetails = toUnwrappedModel(cffCustomViewDetails);

        boolean isNew = cffCustomViewDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffCustomViewDetails.isNew()) {
                session.save(cffCustomViewDetails);

                cffCustomViewDetails.setNew(false);
            } else {
                session.merge(cffCustomViewDetails);
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

        EntityCacheUtil.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffCustomViewDetailsImpl.class,
            cffCustomViewDetails.getPrimaryKey(), cffCustomViewDetails);

        return cffCustomViewDetails;
    }

    protected CffCustomViewDetails toUnwrappedModel(
        CffCustomViewDetails cffCustomViewDetails) {
        if (cffCustomViewDetails instanceof CffCustomViewDetailsImpl) {
            return cffCustomViewDetails;
        }

        CffCustomViewDetailsImpl cffCustomViewDetailsImpl = new CffCustomViewDetailsImpl();

        cffCustomViewDetailsImpl.setNew(cffCustomViewDetails.isNew());
        cffCustomViewDetailsImpl.setPrimaryKey(cffCustomViewDetails.getPrimaryKey());

        cffCustomViewDetailsImpl.setHierarchyId(cffCustomViewDetails.getHierarchyId());
        cffCustomViewDetailsImpl.setHierarchyIndicator(cffCustomViewDetails.getHierarchyIndicator());
        cffCustomViewDetailsImpl.setCffCustomViewDetailsSid(cffCustomViewDetails.getCffCustomViewDetailsSid());
        cffCustomViewDetailsImpl.setLevelNo(cffCustomViewDetails.getLevelNo());
        cffCustomViewDetailsImpl.setCffCustomViewMasterSid(cffCustomViewDetails.getCffCustomViewMasterSid());

        return cffCustomViewDetailsImpl;
    }

    /**
     * Returns the cff custom view details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff custom view details
     * @return the cff custom view details
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffCustomViewDetailsException, SystemException {
        CffCustomViewDetails cffCustomViewDetails = fetchByPrimaryKey(primaryKey);

        if (cffCustomViewDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffCustomViewDetails;
    }

    /**
     * Returns the cff custom view details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException} if it could not be found.
     *
     * @param cffCustomViewDetailsSid the primary key of the cff custom view details
     * @return the cff custom view details
     * @throws com.stpl.app.parttwo.NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails findByPrimaryKey(int cffCustomViewDetailsSid)
        throws NoSuchCffCustomViewDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cffCustomViewDetailsSid);
    }

    /**
     * Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff custom view details
     * @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffCustomViewDetails cffCustomViewDetails = (CffCustomViewDetails) EntityCacheUtil.getResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffCustomViewDetailsImpl.class, primaryKey);

        if (cffCustomViewDetails == _nullCffCustomViewDetails) {
            return null;
        }

        if (cffCustomViewDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cffCustomViewDetails = (CffCustomViewDetails) session.get(CffCustomViewDetailsImpl.class,
                        primaryKey);

                if (cffCustomViewDetails != null) {
                    cacheResult(cffCustomViewDetails);
                } else {
                    EntityCacheUtil.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffCustomViewDetailsImpl.class, primaryKey,
                        _nullCffCustomViewDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CffCustomViewDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffCustomViewDetails;
    }

    /**
     * Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffCustomViewDetailsSid the primary key of the cff custom view details
     * @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffCustomViewDetails fetchByPrimaryKey(int cffCustomViewDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffCustomViewDetailsSid);
    }

    /**
     * Returns all the cff custom view detailses.
     *
     * @return the cff custom view detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff custom view detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff custom view detailses
     * @param end the upper bound of the range of cff custom view detailses (not inclusive)
     * @return the range of cff custom view detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff custom view detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff custom view detailses
     * @param end the upper bound of the range of cff custom view detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff custom view detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffCustomViewDetails> findAll(int start, int end,
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

        List<CffCustomViewDetails> list = (List<CffCustomViewDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFCUSTOMVIEWDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFCUSTOMVIEWDETAILS;

                if (pagination) {
                    sql = sql.concat(CffCustomViewDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffCustomViewDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffCustomViewDetails>(list);
                } else {
                    list = (List<CffCustomViewDetails>) QueryUtil.list(q,
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
     * Removes all the cff custom view detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffCustomViewDetails cffCustomViewDetails : findAll()) {
            remove(cffCustomViewDetails);
        }
    }

    /**
     * Returns the number of cff custom view detailses.
     *
     * @return the number of cff custom view detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFFCUSTOMVIEWDETAILS);

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
     * Initializes the cff custom view details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffCustomViewDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffCustomViewDetails>> listenersList = new ArrayList<ModelListener<CffCustomViewDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffCustomViewDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffCustomViewDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
