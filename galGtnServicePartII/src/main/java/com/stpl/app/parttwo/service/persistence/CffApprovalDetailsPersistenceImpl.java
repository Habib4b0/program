package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffApprovalDetailsException;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.impl.CffApprovalDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffApprovalDetailsPersistence;

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
 * The persistence implementation for the cff approval details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffApprovalDetailsPersistence
 * @see CffApprovalDetailsUtil
 * @generated
 */
public class CffApprovalDetailsPersistenceImpl extends BasePersistenceImpl<CffApprovalDetails>
    implements CffApprovalDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffApprovalDetailsUtil} to access the cff approval details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffApprovalDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffApprovalDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffApprovalDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFAPPROVALDETAILS = "SELECT cffApprovalDetails FROM CffApprovalDetails cffApprovalDetails";
    private static final String _SQL_COUNT_CFFAPPROVALDETAILS = "SELECT COUNT(cffApprovalDetails) FROM CffApprovalDetails cffApprovalDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffApprovalDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffApprovalDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffApprovalDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "approvalSequence", "approvedDate", "approvedBy",
                "approvalStatus", "cffMasterSid", "inboundStatus",
                "cffApprovalDetailsSid"
            });
    private static CffApprovalDetails _nullCffApprovalDetails = new CffApprovalDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffApprovalDetails> toCacheModel() {
                return _nullCffApprovalDetailsCacheModel;
            }
        };

    private static CacheModel<CffApprovalDetails> _nullCffApprovalDetailsCacheModel =
        new CacheModel<CffApprovalDetails>() {
            @Override
            public CffApprovalDetails toEntityModel() {
                return _nullCffApprovalDetails;
            }
        };

    public CffApprovalDetailsPersistenceImpl() {
        setModelClass(CffApprovalDetails.class);
    }

    /**
     * Caches the cff approval details in the entity cache if it is enabled.
     *
     * @param cffApprovalDetails the cff approval details
     */
    @Override
    public void cacheResult(CffApprovalDetails cffApprovalDetails) {
        EntityCacheUtil.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey(),
            cffApprovalDetails);

        cffApprovalDetails.resetOriginalValues();
    }

    /**
     * Caches the cff approval detailses in the entity cache if it is enabled.
     *
     * @param cffApprovalDetailses the cff approval detailses
     */
    @Override
    public void cacheResult(List<CffApprovalDetails> cffApprovalDetailses) {
        for (CffApprovalDetails cffApprovalDetails : cffApprovalDetailses) {
            if (EntityCacheUtil.getResult(
                        CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffApprovalDetailsImpl.class,
                        cffApprovalDetails.getPrimaryKey()) == null) {
                cacheResult(cffApprovalDetails);
            } else {
                cffApprovalDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff approval detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffApprovalDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffApprovalDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff approval details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffApprovalDetails cffApprovalDetails) {
        EntityCacheUtil.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffApprovalDetails> cffApprovalDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffApprovalDetails cffApprovalDetails : cffApprovalDetailses) {
            EntityCacheUtil.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
     *
     * @param cffApprovalDetailsSid the primary key for the new cff approval details
     * @return the new cff approval details
     */
    @Override
    public CffApprovalDetails create(int cffApprovalDetailsSid) {
        CffApprovalDetails cffApprovalDetails = new CffApprovalDetailsImpl();

        cffApprovalDetails.setNew(true);
        cffApprovalDetails.setPrimaryKey(cffApprovalDetailsSid);

        return cffApprovalDetails;
    }

    /**
     * Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffApprovalDetailsSid the primary key of the cff approval details
     * @return the cff approval details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails remove(int cffApprovalDetailsSid)
        throws NoSuchCffApprovalDetailsException, SystemException {
        return remove((Serializable) cffApprovalDetailsSid);
    }

    /**
     * Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff approval details
     * @return the cff approval details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails remove(Serializable primaryKey)
        throws NoSuchCffApprovalDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffApprovalDetails cffApprovalDetails = (CffApprovalDetails) session.get(CffApprovalDetailsImpl.class,
                    primaryKey);

            if (cffApprovalDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffApprovalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffApprovalDetails);
        } catch (NoSuchCffApprovalDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffApprovalDetails removeImpl(
        CffApprovalDetails cffApprovalDetails) throws SystemException {
        cffApprovalDetails = toUnwrappedModel(cffApprovalDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffApprovalDetails)) {
                cffApprovalDetails = (CffApprovalDetails) session.get(CffApprovalDetailsImpl.class,
                        cffApprovalDetails.getPrimaryKeyObj());
            }

            if (cffApprovalDetails != null) {
                session.delete(cffApprovalDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffApprovalDetails != null) {
            clearCache(cffApprovalDetails);
        }

        return cffApprovalDetails;
    }

    @Override
    public CffApprovalDetails updateImpl(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails)
        throws SystemException {
        cffApprovalDetails = toUnwrappedModel(cffApprovalDetails);

        boolean isNew = cffApprovalDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffApprovalDetails.isNew()) {
                session.save(cffApprovalDetails);

                cffApprovalDetails.setNew(false);
            } else {
                session.merge(cffApprovalDetails);
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

        EntityCacheUtil.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey(),
            cffApprovalDetails);

        return cffApprovalDetails;
    }

    protected CffApprovalDetails toUnwrappedModel(
        CffApprovalDetails cffApprovalDetails) {
        if (cffApprovalDetails instanceof CffApprovalDetailsImpl) {
            return cffApprovalDetails;
        }

        CffApprovalDetailsImpl cffApprovalDetailsImpl = new CffApprovalDetailsImpl();

        cffApprovalDetailsImpl.setNew(cffApprovalDetails.isNew());
        cffApprovalDetailsImpl.setPrimaryKey(cffApprovalDetails.getPrimaryKey());

        cffApprovalDetailsImpl.setApprovalSequence(cffApprovalDetails.getApprovalSequence());
        cffApprovalDetailsImpl.setApprovedDate(cffApprovalDetails.getApprovedDate());
        cffApprovalDetailsImpl.setApprovedBy(cffApprovalDetails.getApprovedBy());
        cffApprovalDetailsImpl.setApprovalStatus(cffApprovalDetails.getApprovalStatus());
        cffApprovalDetailsImpl.setCffMasterSid(cffApprovalDetails.getCffMasterSid());
        cffApprovalDetailsImpl.setInboundStatus(cffApprovalDetails.getInboundStatus());
        cffApprovalDetailsImpl.setCffApprovalDetailsSid(cffApprovalDetails.getCffApprovalDetailsSid());

        return cffApprovalDetailsImpl;
    }

    /**
     * Returns the cff approval details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff approval details
     * @return the cff approval details
     * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffApprovalDetailsException, SystemException {
        CffApprovalDetails cffApprovalDetails = fetchByPrimaryKey(primaryKey);

        if (cffApprovalDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffApprovalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffApprovalDetails;
    }

    /**
     * Returns the cff approval details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffApprovalDetailsException} if it could not be found.
     *
     * @param cffApprovalDetailsSid the primary key of the cff approval details
     * @return the cff approval details
     * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails findByPrimaryKey(int cffApprovalDetailsSid)
        throws NoSuchCffApprovalDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cffApprovalDetailsSid);
    }

    /**
     * Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff approval details
     * @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffApprovalDetails cffApprovalDetails = (CffApprovalDetails) EntityCacheUtil.getResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffApprovalDetailsImpl.class, primaryKey);

        if (cffApprovalDetails == _nullCffApprovalDetails) {
            return null;
        }

        if (cffApprovalDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cffApprovalDetails = (CffApprovalDetails) session.get(CffApprovalDetailsImpl.class,
                        primaryKey);

                if (cffApprovalDetails != null) {
                    cacheResult(cffApprovalDetails);
                } else {
                    EntityCacheUtil.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffApprovalDetailsImpl.class, primaryKey,
                        _nullCffApprovalDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CffApprovalDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffApprovalDetails;
    }

    /**
     * Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffApprovalDetailsSid the primary key of the cff approval details
     * @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffApprovalDetails fetchByPrimaryKey(int cffApprovalDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffApprovalDetailsSid);
    }

    /**
     * Returns all the cff approval detailses.
     *
     * @return the cff approval detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffApprovalDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff approval detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff approval detailses
     * @param end the upper bound of the range of cff approval detailses (not inclusive)
     * @return the range of cff approval detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffApprovalDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff approval detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff approval detailses
     * @param end the upper bound of the range of cff approval detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff approval detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffApprovalDetails> findAll(int start, int end,
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

        List<CffApprovalDetails> list = (List<CffApprovalDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFAPPROVALDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFAPPROVALDETAILS;

                if (pagination) {
                    sql = sql.concat(CffApprovalDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffApprovalDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffApprovalDetails>(list);
                } else {
                    list = (List<CffApprovalDetails>) QueryUtil.list(q,
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
     * Removes all the cff approval detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffApprovalDetails cffApprovalDetails : findAll()) {
            remove(cffApprovalDetails);
        }
    }

    /**
     * Returns the number of cff approval detailses.
     *
     * @return the number of cff approval detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFFAPPROVALDETAILS);

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
     * Initializes the cff approval details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffApprovalDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffApprovalDetails>> listenersList = new ArrayList<ModelListener<CffApprovalDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffApprovalDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffApprovalDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
