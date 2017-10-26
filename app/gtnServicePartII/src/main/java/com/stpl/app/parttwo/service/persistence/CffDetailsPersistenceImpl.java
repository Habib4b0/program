package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffDetailsException;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.impl.CffDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffDetailsPersistence;

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
 * The persistence implementation for the cff details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDetailsPersistence
 * @see CffDetailsUtil
 * @generated
 */
public class CffDetailsPersistenceImpl extends BasePersistenceImpl<CffDetails>
    implements CffDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffDetailsUtil} to access the cff details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsModelImpl.FINDER_CACHE_ENABLED, CffDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsModelImpl.FINDER_CACHE_ENABLED, CffDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFDETAILS = "SELECT cffDetails FROM CffDetails cffDetails";
    private static final String _SQL_COUNT_CFFDETAILS = "SELECT COUNT(cffDetails) FROM CffDetails cffDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ccpDetailsSid", "projectionMasterSid", "cffMasterSid",
                "inboundStatus", "cffDetailsSid"
            });
    private static CffDetails _nullCffDetails = new CffDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffDetails> toCacheModel() {
                return _nullCffDetailsCacheModel;
            }
        };

    private static CacheModel<CffDetails> _nullCffDetailsCacheModel = new CacheModel<CffDetails>() {
            @Override
            public CffDetails toEntityModel() {
                return _nullCffDetails;
            }
        };

    public CffDetailsPersistenceImpl() {
        setModelClass(CffDetails.class);
    }

    /**
     * Caches the cff details in the entity cache if it is enabled.
     *
     * @param cffDetails the cff details
     */
    @Override
    public void cacheResult(CffDetails cffDetails) {
        EntityCacheUtil.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsImpl.class, cffDetails.getPrimaryKey(), cffDetails);

        cffDetails.resetOriginalValues();
    }

    /**
     * Caches the cff detailses in the entity cache if it is enabled.
     *
     * @param cffDetailses the cff detailses
     */
    @Override
    public void cacheResult(List<CffDetails> cffDetailses) {
        for (CffDetails cffDetails : cffDetailses) {
            if (EntityCacheUtil.getResult(
                        CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffDetailsImpl.class, cffDetails.getPrimaryKey()) == null) {
                cacheResult(cffDetails);
            } else {
                cffDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffDetails cffDetails) {
        EntityCacheUtil.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsImpl.class, cffDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffDetails> cffDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffDetails cffDetails : cffDetailses) {
            EntityCacheUtil.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffDetailsImpl.class, cffDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff details with the primary key. Does not add the cff details to the database.
     *
     * @param cffDetailsSid the primary key for the new cff details
     * @return the new cff details
     */
    @Override
    public CffDetails create(int cffDetailsSid) {
        CffDetails cffDetails = new CffDetailsImpl();

        cffDetails.setNew(true);
        cffDetails.setPrimaryKey(cffDetailsSid);

        return cffDetails;
    }

    /**
     * Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffDetailsSid the primary key of the cff details
     * @return the cff details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails remove(int cffDetailsSid)
        throws NoSuchCffDetailsException, SystemException {
        return remove((Serializable) cffDetailsSid);
    }

    /**
     * Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff details
     * @return the cff details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails remove(Serializable primaryKey)
        throws NoSuchCffDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffDetails cffDetails = (CffDetails) session.get(CffDetailsImpl.class,
                    primaryKey);

            if (cffDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffDetails);
        } catch (NoSuchCffDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffDetails removeImpl(CffDetails cffDetails)
        throws SystemException {
        cffDetails = toUnwrappedModel(cffDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffDetails)) {
                cffDetails = (CffDetails) session.get(CffDetailsImpl.class,
                        cffDetails.getPrimaryKeyObj());
            }

            if (cffDetails != null) {
                session.delete(cffDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffDetails != null) {
            clearCache(cffDetails);
        }

        return cffDetails;
    }

    @Override
    public CffDetails updateImpl(
        com.stpl.app.parttwo.model.CffDetails cffDetails)
        throws SystemException {
        cffDetails = toUnwrappedModel(cffDetails);

        boolean isNew = cffDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffDetails.isNew()) {
                session.save(cffDetails);

                cffDetails.setNew(false);
            } else {
                session.merge(cffDetails);
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

        EntityCacheUtil.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDetailsImpl.class, cffDetails.getPrimaryKey(), cffDetails);

        return cffDetails;
    }

    protected CffDetails toUnwrappedModel(CffDetails cffDetails) {
        if (cffDetails instanceof CffDetailsImpl) {
            return cffDetails;
        }

        CffDetailsImpl cffDetailsImpl = new CffDetailsImpl();

        cffDetailsImpl.setNew(cffDetails.isNew());
        cffDetailsImpl.setPrimaryKey(cffDetails.getPrimaryKey());

        cffDetailsImpl.setCcpDetailsSid(cffDetails.getCcpDetailsSid());
        cffDetailsImpl.setProjectionMasterSid(cffDetails.getProjectionMasterSid());
        cffDetailsImpl.setCffMasterSid(cffDetails.getCffMasterSid());
        cffDetailsImpl.setInboundStatus(cffDetails.getInboundStatus());
        cffDetailsImpl.setCffDetailsSid(cffDetails.getCffDetailsSid());

        return cffDetailsImpl;
    }

    /**
     * Returns the cff details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff details
     * @return the cff details
     * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffDetailsException, SystemException {
        CffDetails cffDetails = fetchByPrimaryKey(primaryKey);

        if (cffDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffDetails;
    }

    /**
     * Returns the cff details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffDetailsException} if it could not be found.
     *
     * @param cffDetailsSid the primary key of the cff details
     * @return the cff details
     * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails findByPrimaryKey(int cffDetailsSid)
        throws NoSuchCffDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cffDetailsSid);
    }

    /**
     * Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff details
     * @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffDetails cffDetails = (CffDetails) EntityCacheUtil.getResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffDetailsImpl.class, primaryKey);

        if (cffDetails == _nullCffDetails) {
            return null;
        }

        if (cffDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cffDetails = (CffDetails) session.get(CffDetailsImpl.class,
                        primaryKey);

                if (cffDetails != null) {
                    cacheResult(cffDetails);
                } else {
                    EntityCacheUtil.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffDetailsImpl.class, primaryKey, _nullCffDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CffDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffDetails;
    }

    /**
     * Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffDetailsSid the primary key of the cff details
     * @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDetails fetchByPrimaryKey(int cffDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffDetailsSid);
    }

    /**
     * Returns all the cff detailses.
     *
     * @return the cff detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff detailses
     * @param end the upper bound of the range of cff detailses (not inclusive)
     * @return the range of cff detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff detailses
     * @param end the upper bound of the range of cff detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDetails> findAll(int start, int end,
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

        List<CffDetails> list = (List<CffDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFDETAILS;

                if (pagination) {
                    sql = sql.concat(CffDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffDetails>(list);
                } else {
                    list = (List<CffDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the cff detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffDetails cffDetails : findAll()) {
            remove(cffDetails);
        }
    }

    /**
     * Returns the number of cff detailses.
     *
     * @return the number of cff detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFFDETAILS);

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
     * Initializes the cff details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffDetails>> listenersList = new ArrayList<ModelListener<CffDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
