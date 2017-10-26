package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchStAccClosureDetailsException;
import com.stpl.app.parttwo.model.StAccClosureDetails;
import com.stpl.app.parttwo.model.impl.StAccClosureDetailsImpl;
import com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.StAccClosureDetailsPersistence;

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
 * The persistence implementation for the st acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAccClosureDetailsPersistence
 * @see StAccClosureDetailsUtil
 * @generated
 */
public class StAccClosureDetailsPersistenceImpl extends BasePersistenceImpl<StAccClosureDetails>
    implements StAccClosureDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StAccClosureDetailsUtil} to access the st acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StAccClosureDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
            StAccClosureDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
            StAccClosureDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_STACCCLOSUREDETAILS = "SELECT stAccClosureDetails FROM StAccClosureDetails stAccClosureDetails";
    private static final String _SQL_COUNT_STACCCLOSUREDETAILS = "SELECT COUNT(stAccClosureDetails) FROM StAccClosureDetails stAccClosureDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "stAccClosureDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAccClosureDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StAccClosureDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "lastModifiedDate", "checkRecord", "contractName", "userId",
                "itemMasterSid", "moduleName", "companyName", "brandName",
                "companyCostCenter", "companyNo", "contractMasterSid",
                "sessionId", "ccpDetailsSid", "itemName", "accClosureMasterSid",
                "rsModelSid", "contractNo", "companyMasterSid", "ndc8"
            });
    private static StAccClosureDetails _nullStAccClosureDetails = new StAccClosureDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StAccClosureDetails> toCacheModel() {
                return _nullStAccClosureDetailsCacheModel;
            }
        };

    private static CacheModel<StAccClosureDetails> _nullStAccClosureDetailsCacheModel =
        new CacheModel<StAccClosureDetails>() {
            @Override
            public StAccClosureDetails toEntityModel() {
                return _nullStAccClosureDetails;
            }
        };

    public StAccClosureDetailsPersistenceImpl() {
        setModelClass(StAccClosureDetails.class);
    }

    /**
     * Caches the st acc closure details in the entity cache if it is enabled.
     *
     * @param stAccClosureDetails the st acc closure details
     */
    @Override
    public void cacheResult(StAccClosureDetails stAccClosureDetails) {
        EntityCacheUtil.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey(),
            stAccClosureDetails);

        stAccClosureDetails.resetOriginalValues();
    }

    /**
     * Caches the st acc closure detailses in the entity cache if it is enabled.
     *
     * @param stAccClosureDetailses the st acc closure detailses
     */
    @Override
    public void cacheResult(List<StAccClosureDetails> stAccClosureDetailses) {
        for (StAccClosureDetails stAccClosureDetails : stAccClosureDetailses) {
            if (EntityCacheUtil.getResult(
                        StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        StAccClosureDetailsImpl.class,
                        stAccClosureDetails.getPrimaryKey()) == null) {
                cacheResult(stAccClosureDetails);
            } else {
                stAccClosureDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all st acc closure detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StAccClosureDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StAccClosureDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the st acc closure details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StAccClosureDetails stAccClosureDetails) {
        EntityCacheUtil.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StAccClosureDetails> stAccClosureDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StAccClosureDetails stAccClosureDetails : stAccClosureDetailses) {
            EntityCacheUtil.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                StAccClosureDetailsImpl.class,
                stAccClosureDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
     *
     * @param accClosureMasterSid the primary key for the new st acc closure details
     * @return the new st acc closure details
     */
    @Override
    public StAccClosureDetails create(int accClosureMasterSid) {
        StAccClosureDetails stAccClosureDetails = new StAccClosureDetailsImpl();

        stAccClosureDetails.setNew(true);
        stAccClosureDetails.setPrimaryKey(accClosureMasterSid);

        return stAccClosureDetails;
    }

    /**
     * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accClosureMasterSid the primary key of the st acc closure details
     * @return the st acc closure details that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails remove(int accClosureMasterSid)
        throws NoSuchStAccClosureDetailsException, SystemException {
        return remove((Serializable) accClosureMasterSid);
    }

    /**
     * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the st acc closure details
     * @return the st acc closure details that was removed
     * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails remove(Serializable primaryKey)
        throws NoSuchStAccClosureDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StAccClosureDetails stAccClosureDetails = (StAccClosureDetails) session.get(StAccClosureDetailsImpl.class,
                    primaryKey);

            if (stAccClosureDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(stAccClosureDetails);
        } catch (NoSuchStAccClosureDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StAccClosureDetails removeImpl(
        StAccClosureDetails stAccClosureDetails) throws SystemException {
        stAccClosureDetails = toUnwrappedModel(stAccClosureDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(stAccClosureDetails)) {
                stAccClosureDetails = (StAccClosureDetails) session.get(StAccClosureDetailsImpl.class,
                        stAccClosureDetails.getPrimaryKeyObj());
            }

            if (stAccClosureDetails != null) {
                session.delete(stAccClosureDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (stAccClosureDetails != null) {
            clearCache(stAccClosureDetails);
        }

        return stAccClosureDetails;
    }

    @Override
    public StAccClosureDetails updateImpl(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws SystemException {
        stAccClosureDetails = toUnwrappedModel(stAccClosureDetails);

        boolean isNew = stAccClosureDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (stAccClosureDetails.isNew()) {
                session.save(stAccClosureDetails);

                stAccClosureDetails.setNew(false);
            } else {
                session.merge(stAccClosureDetails);
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

        EntityCacheUtil.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            StAccClosureDetailsImpl.class, stAccClosureDetails.getPrimaryKey(),
            stAccClosureDetails);

        return stAccClosureDetails;
    }

    protected StAccClosureDetails toUnwrappedModel(
        StAccClosureDetails stAccClosureDetails) {
        if (stAccClosureDetails instanceof StAccClosureDetailsImpl) {
            return stAccClosureDetails;
        }

        StAccClosureDetailsImpl stAccClosureDetailsImpl = new StAccClosureDetailsImpl();

        stAccClosureDetailsImpl.setNew(stAccClosureDetails.isNew());
        stAccClosureDetailsImpl.setPrimaryKey(stAccClosureDetails.getPrimaryKey());

        stAccClosureDetailsImpl.setLastModifiedDate(stAccClosureDetails.getLastModifiedDate());
        stAccClosureDetailsImpl.setCheckRecord(stAccClosureDetails.isCheckRecord());
        stAccClosureDetailsImpl.setContractName(stAccClosureDetails.getContractName());
        stAccClosureDetailsImpl.setUserId(stAccClosureDetails.getUserId());
        stAccClosureDetailsImpl.setItemMasterSid(stAccClosureDetails.getItemMasterSid());
        stAccClosureDetailsImpl.setModuleName(stAccClosureDetails.getModuleName());
        stAccClosureDetailsImpl.setCompanyName(stAccClosureDetails.getCompanyName());
        stAccClosureDetailsImpl.setBrandName(stAccClosureDetails.getBrandName());
        stAccClosureDetailsImpl.setCompanyCostCenter(stAccClosureDetails.getCompanyCostCenter());
        stAccClosureDetailsImpl.setCompanyNo(stAccClosureDetails.getCompanyNo());
        stAccClosureDetailsImpl.setContractMasterSid(stAccClosureDetails.getContractMasterSid());
        stAccClosureDetailsImpl.setSessionId(stAccClosureDetails.getSessionId());
        stAccClosureDetailsImpl.setCcpDetailsSid(stAccClosureDetails.getCcpDetailsSid());
        stAccClosureDetailsImpl.setItemName(stAccClosureDetails.getItemName());
        stAccClosureDetailsImpl.setAccClosureMasterSid(stAccClosureDetails.getAccClosureMasterSid());
        stAccClosureDetailsImpl.setRsModelSid(stAccClosureDetails.getRsModelSid());
        stAccClosureDetailsImpl.setContractNo(stAccClosureDetails.getContractNo());
        stAccClosureDetailsImpl.setCompanyMasterSid(stAccClosureDetails.getCompanyMasterSid());
        stAccClosureDetailsImpl.setNdc8(stAccClosureDetails.getNdc8());

        return stAccClosureDetailsImpl;
    }

    /**
     * Returns the st acc closure details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the st acc closure details
     * @return the st acc closure details
     * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStAccClosureDetailsException, SystemException {
        StAccClosureDetails stAccClosureDetails = fetchByPrimaryKey(primaryKey);

        if (stAccClosureDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return stAccClosureDetails;
    }

    /**
     * Returns the st acc closure details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAccClosureDetailsException} if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the st acc closure details
     * @return the st acc closure details
     * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails findByPrimaryKey(int accClosureMasterSid)
        throws NoSuchStAccClosureDetailsException, SystemException {
        return findByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the st acc closure details
     * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StAccClosureDetails stAccClosureDetails = (StAccClosureDetails) EntityCacheUtil.getResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                StAccClosureDetailsImpl.class, primaryKey);

        if (stAccClosureDetails == _nullStAccClosureDetails) {
            return null;
        }

        if (stAccClosureDetails == null) {
            Session session = null;

            try {
                session = openSession();

                stAccClosureDetails = (StAccClosureDetails) session.get(StAccClosureDetailsImpl.class,
                        primaryKey);

                if (stAccClosureDetails != null) {
                    cacheResult(stAccClosureDetails);
                } else {
                    EntityCacheUtil.putResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        StAccClosureDetailsImpl.class, primaryKey,
                        _nullStAccClosureDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StAccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    StAccClosureDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return stAccClosureDetails;
    }

    /**
     * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the st acc closure details
     * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StAccClosureDetails fetchByPrimaryKey(int accClosureMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns all the st acc closure detailses.
     *
     * @return the st acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAccClosureDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the st acc closure detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st acc closure detailses
     * @param end the upper bound of the range of st acc closure detailses (not inclusive)
     * @return the range of st acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAccClosureDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the st acc closure detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of st acc closure detailses
     * @param end the upper bound of the range of st acc closure detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of st acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StAccClosureDetails> findAll(int start, int end,
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

        List<StAccClosureDetails> list = (List<StAccClosureDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STACCCLOSUREDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STACCCLOSUREDETAILS;

                if (pagination) {
                    sql = sql.concat(StAccClosureDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StAccClosureDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StAccClosureDetails>(list);
                } else {
                    list = (List<StAccClosureDetails>) QueryUtil.list(q,
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
     * Removes all the st acc closure detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StAccClosureDetails stAccClosureDetails : findAll()) {
            remove(stAccClosureDetails);
        }
    }

    /**
     * Returns the number of st acc closure detailses.
     *
     * @return the number of st acc closure detailses
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

                Query q = session.createQuery(_SQL_COUNT_STACCCLOSUREDETAILS);

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
     * Initializes the st acc closure details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.StAccClosureDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StAccClosureDetails>> listenersList = new ArrayList<ModelListener<StAccClosureDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StAccClosureDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StAccClosureDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
