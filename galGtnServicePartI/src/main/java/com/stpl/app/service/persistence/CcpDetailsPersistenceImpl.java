package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCcpDetailsException;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.impl.CcpDetailsImpl;
import com.stpl.app.model.impl.CcpDetailsModelImpl;
import com.stpl.app.service.persistence.CcpDetailsPersistence;

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
 * The persistence implementation for the ccp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpDetailsPersistence
 * @see CcpDetailsUtil
 * @generated
 */
public class CcpDetailsPersistenceImpl extends BasePersistenceImpl<CcpDetails>
    implements CcpDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CcpDetailsUtil} to access the ccp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CcpDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsModelImpl.FINDER_CACHE_ENABLED, CcpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsModelImpl.FINDER_CACHE_ENABLED, CcpDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CCPDETAILS = "SELECT ccpDetails FROM CcpDetails ccpDetails";
    private static final String _SQL_COUNT_CCPDETAILS = "SELECT COUNT(ccpDetails) FROM CcpDetails ccpDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ccpDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CcpDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CcpDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "contractMasterSid", "ccpDetailsSid",
                "companyMasterSid"
            });
    private static CcpDetails _nullCcpDetails = new CcpDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CcpDetails> toCacheModel() {
                return _nullCcpDetailsCacheModel;
            }
        };

    private static CacheModel<CcpDetails> _nullCcpDetailsCacheModel = new CacheModel<CcpDetails>() {
            @Override
            public CcpDetails toEntityModel() {
                return _nullCcpDetails;
            }
        };

    public CcpDetailsPersistenceImpl() {
        setModelClass(CcpDetails.class);
    }

    /**
     * Caches the ccp details in the entity cache if it is enabled.
     *
     * @param ccpDetails the ccp details
     */
    @Override
    public void cacheResult(CcpDetails ccpDetails) {
        EntityCacheUtil.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsImpl.class, ccpDetails.getPrimaryKey(), ccpDetails);

        ccpDetails.resetOriginalValues();
    }

    /**
     * Caches the ccp detailses in the entity cache if it is enabled.
     *
     * @param ccpDetailses the ccp detailses
     */
    @Override
    public void cacheResult(List<CcpDetails> ccpDetailses) {
        for (CcpDetails ccpDetails : ccpDetailses) {
            if (EntityCacheUtil.getResult(
                        CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CcpDetailsImpl.class, ccpDetails.getPrimaryKey()) == null) {
                cacheResult(ccpDetails);
            } else {
                ccpDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ccp detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CcpDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CcpDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ccp details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CcpDetails ccpDetails) {
        EntityCacheUtil.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsImpl.class, ccpDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CcpDetails> ccpDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CcpDetails ccpDetails : ccpDetailses) {
            EntityCacheUtil.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CcpDetailsImpl.class, ccpDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ccp details with the primary key. Does not add the ccp details to the database.
     *
     * @param ccpDetailsSid the primary key for the new ccp details
     * @return the new ccp details
     */
    @Override
    public CcpDetails create(int ccpDetailsSid) {
        CcpDetails ccpDetails = new CcpDetailsImpl();

        ccpDetails.setNew(true);
        ccpDetails.setPrimaryKey(ccpDetailsSid);

        return ccpDetails;
    }

    /**
     * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ccpDetailsSid the primary key of the ccp details
     * @return the ccp details that was removed
     * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails remove(int ccpDetailsSid)
        throws NoSuchCcpDetailsException, SystemException {
        return remove((Serializable) ccpDetailsSid);
    }

    /**
     * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ccp details
     * @return the ccp details that was removed
     * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails remove(Serializable primaryKey)
        throws NoSuchCcpDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CcpDetails ccpDetails = (CcpDetails) session.get(CcpDetailsImpl.class,
                    primaryKey);

            if (ccpDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCcpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ccpDetails);
        } catch (NoSuchCcpDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CcpDetails removeImpl(CcpDetails ccpDetails)
        throws SystemException {
        ccpDetails = toUnwrappedModel(ccpDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ccpDetails)) {
                ccpDetails = (CcpDetails) session.get(CcpDetailsImpl.class,
                        ccpDetails.getPrimaryKeyObj());
            }

            if (ccpDetails != null) {
                session.delete(ccpDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ccpDetails != null) {
            clearCache(ccpDetails);
        }

        return ccpDetails;
    }

    @Override
    public CcpDetails updateImpl(com.stpl.app.model.CcpDetails ccpDetails)
        throws SystemException {
        ccpDetails = toUnwrappedModel(ccpDetails);

        boolean isNew = ccpDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ccpDetails.isNew()) {
                session.save(ccpDetails);

                ccpDetails.setNew(false);
            } else {
                session.merge(ccpDetails);
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

        EntityCacheUtil.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CcpDetailsImpl.class, ccpDetails.getPrimaryKey(), ccpDetails);

        return ccpDetails;
    }

    protected CcpDetails toUnwrappedModel(CcpDetails ccpDetails) {
        if (ccpDetails instanceof CcpDetailsImpl) {
            return ccpDetails;
        }

        CcpDetailsImpl ccpDetailsImpl = new CcpDetailsImpl();

        ccpDetailsImpl.setNew(ccpDetails.isNew());
        ccpDetailsImpl.setPrimaryKey(ccpDetails.getPrimaryKey());

        ccpDetailsImpl.setItemMasterSid(ccpDetails.getItemMasterSid());
        ccpDetailsImpl.setContractMasterSid(ccpDetails.getContractMasterSid());
        ccpDetailsImpl.setCcpDetailsSid(ccpDetails.getCcpDetailsSid());
        ccpDetailsImpl.setCompanyMasterSid(ccpDetails.getCompanyMasterSid());

        return ccpDetailsImpl;
    }

    /**
     * Returns the ccp details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ccp details
     * @return the ccp details
     * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCcpDetailsException, SystemException {
        CcpDetails ccpDetails = fetchByPrimaryKey(primaryKey);

        if (ccpDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCcpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ccpDetails;
    }

    /**
     * Returns the ccp details with the primary key or throws a {@link com.stpl.app.NoSuchCcpDetailsException} if it could not be found.
     *
     * @param ccpDetailsSid the primary key of the ccp details
     * @return the ccp details
     * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails findByPrimaryKey(int ccpDetailsSid)
        throws NoSuchCcpDetailsException, SystemException {
        return findByPrimaryKey((Serializable) ccpDetailsSid);
    }

    /**
     * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ccp details
     * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CcpDetails ccpDetails = (CcpDetails) EntityCacheUtil.getResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CcpDetailsImpl.class, primaryKey);

        if (ccpDetails == _nullCcpDetails) {
            return null;
        }

        if (ccpDetails == null) {
            Session session = null;

            try {
                session = openSession();

                ccpDetails = (CcpDetails) session.get(CcpDetailsImpl.class,
                        primaryKey);

                if (ccpDetails != null) {
                    cacheResult(ccpDetails);
                } else {
                    EntityCacheUtil.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CcpDetailsImpl.class, primaryKey, _nullCcpDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CcpDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ccpDetails;
    }

    /**
     * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ccpDetailsSid the primary key of the ccp details
     * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CcpDetails fetchByPrimaryKey(int ccpDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ccpDetailsSid);
    }

    /**
     * Returns all the ccp detailses.
     *
     * @return the ccp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ccp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ccp detailses
     * @param end the upper bound of the range of ccp detailses (not inclusive)
     * @return the range of ccp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ccp detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ccp detailses
     * @param end the upper bound of the range of ccp detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ccp detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CcpDetails> findAll(int start, int end,
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

        List<CcpDetails> list = (List<CcpDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CCPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CCPDETAILS;

                if (pagination) {
                    sql = sql.concat(CcpDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CcpDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CcpDetails>(list);
                } else {
                    list = (List<CcpDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ccp detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CcpDetails ccpDetails : findAll()) {
            remove(ccpDetails);
        }
    }

    /**
     * Returns the number of ccp detailses.
     *
     * @return the number of ccp detailses
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

                Query q = session.createQuery(_SQL_COUNT_CCPDETAILS);

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
     * Initializes the ccp details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CcpDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CcpDetails>> listenersList = new ArrayList<ModelListener<CcpDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CcpDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CcpDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
