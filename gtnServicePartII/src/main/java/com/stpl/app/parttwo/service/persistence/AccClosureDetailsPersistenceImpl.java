package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAccClosureDetailsException;
import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.model.impl.AccClosureDetailsImpl;
import com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureDetailsPersistence;

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
 * The persistence implementation for the acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureDetailsPersistence
 * @see AccClosureDetailsUtil
 * @generated
 */
public class AccClosureDetailsPersistenceImpl extends BasePersistenceImpl<AccClosureDetails>
    implements AccClosureDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AccClosureDetailsUtil} to access the acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AccClosureDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
            AccClosureDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
            AccClosureDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACCCLOSUREDETAILS = "SELECT accClosureDetails FROM AccClosureDetails accClosureDetails";
    private static final String _SQL_COUNT_ACCCLOSUREDETAILS = "SELECT COUNT(accClosureDetails) FROM AccClosureDetails accClosureDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AccClosureDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "accClosureDetailsSid", "ccpDetailsSid", "accClosureMasterSid",
                "rsModelSid"
            });
    private static AccClosureDetails _nullAccClosureDetails = new AccClosureDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AccClosureDetails> toCacheModel() {
                return _nullAccClosureDetailsCacheModel;
            }
        };

    private static CacheModel<AccClosureDetails> _nullAccClosureDetailsCacheModel =
        new CacheModel<AccClosureDetails>() {
            @Override
            public AccClosureDetails toEntityModel() {
                return _nullAccClosureDetails;
            }
        };

    public AccClosureDetailsPersistenceImpl() {
        setModelClass(AccClosureDetails.class);
    }

    /**
     * Caches the acc closure details in the entity cache if it is enabled.
     *
     * @param accClosureDetails the acc closure details
     */
    @Override
    public void cacheResult(AccClosureDetails accClosureDetails) {
        EntityCacheUtil.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey(),
            accClosureDetails);

        accClosureDetails.resetOriginalValues();
    }

    /**
     * Caches the acc closure detailses in the entity cache if it is enabled.
     *
     * @param accClosureDetailses the acc closure detailses
     */
    @Override
    public void cacheResult(List<AccClosureDetails> accClosureDetailses) {
        for (AccClosureDetails accClosureDetails : accClosureDetailses) {
            if (EntityCacheUtil.getResult(
                        AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureDetailsImpl.class,
                        accClosureDetails.getPrimaryKey()) == null) {
                cacheResult(accClosureDetails);
            } else {
                accClosureDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all acc closure detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AccClosureDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AccClosureDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the acc closure details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AccClosureDetails accClosureDetails) {
        EntityCacheUtil.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AccClosureDetails> accClosureDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AccClosureDetails accClosureDetails : accClosureDetailses) {
            EntityCacheUtil.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new acc closure details with the primary key. Does not add the acc closure details to the database.
     *
     * @param accClosureDetailsSid the primary key for the new acc closure details
     * @return the new acc closure details
     */
    @Override
    public AccClosureDetails create(int accClosureDetailsSid) {
        AccClosureDetails accClosureDetails = new AccClosureDetailsImpl();

        accClosureDetails.setNew(true);
        accClosureDetails.setPrimaryKey(accClosureDetailsSid);

        return accClosureDetails;
    }

    /**
     * Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accClosureDetailsSid the primary key of the acc closure details
     * @return the acc closure details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails remove(int accClosureDetailsSid)
        throws NoSuchAccClosureDetailsException, SystemException {
        return remove((Serializable) accClosureDetailsSid);
    }

    /**
     * Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the acc closure details
     * @return the acc closure details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails remove(Serializable primaryKey)
        throws NoSuchAccClosureDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AccClosureDetails accClosureDetails = (AccClosureDetails) session.get(AccClosureDetailsImpl.class,
                    primaryKey);

            if (accClosureDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(accClosureDetails);
        } catch (NoSuchAccClosureDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AccClosureDetails removeImpl(AccClosureDetails accClosureDetails)
        throws SystemException {
        accClosureDetails = toUnwrappedModel(accClosureDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(accClosureDetails)) {
                accClosureDetails = (AccClosureDetails) session.get(AccClosureDetailsImpl.class,
                        accClosureDetails.getPrimaryKeyObj());
            }

            if (accClosureDetails != null) {
                session.delete(accClosureDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (accClosureDetails != null) {
            clearCache(accClosureDetails);
        }

        return accClosureDetails;
    }

    @Override
    public AccClosureDetails updateImpl(
        com.stpl.app.parttwo.model.AccClosureDetails accClosureDetails)
        throws SystemException {
        accClosureDetails = toUnwrappedModel(accClosureDetails);

        boolean isNew = accClosureDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (accClosureDetails.isNew()) {
                session.save(accClosureDetails);

                accClosureDetails.setNew(false);
            } else {
                session.merge(accClosureDetails);
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

        EntityCacheUtil.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey(),
            accClosureDetails);

        return accClosureDetails;
    }

    protected AccClosureDetails toUnwrappedModel(
        AccClosureDetails accClosureDetails) {
        if (accClosureDetails instanceof AccClosureDetailsImpl) {
            return accClosureDetails;
        }

        AccClosureDetailsImpl accClosureDetailsImpl = new AccClosureDetailsImpl();

        accClosureDetailsImpl.setNew(accClosureDetails.isNew());
        accClosureDetailsImpl.setPrimaryKey(accClosureDetails.getPrimaryKey());

        accClosureDetailsImpl.setAccClosureDetailsSid(accClosureDetails.getAccClosureDetailsSid());
        accClosureDetailsImpl.setCcpDetailsSid(accClosureDetails.getCcpDetailsSid());
        accClosureDetailsImpl.setAccClosureMasterSid(accClosureDetails.getAccClosureMasterSid());
        accClosureDetailsImpl.setRsModelSid(accClosureDetails.getRsModelSid());

        return accClosureDetailsImpl;
    }

    /**
     * Returns the acc closure details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure details
     * @return the acc closure details
     * @throws com.stpl.app.parttwo.NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAccClosureDetailsException, SystemException {
        AccClosureDetails accClosureDetails = fetchByPrimaryKey(primaryKey);

        if (accClosureDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return accClosureDetails;
    }

    /**
     * Returns the acc closure details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAccClosureDetailsException} if it could not be found.
     *
     * @param accClosureDetailsSid the primary key of the acc closure details
     * @return the acc closure details
     * @throws com.stpl.app.parttwo.NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails findByPrimaryKey(int accClosureDetailsSid)
        throws NoSuchAccClosureDetailsException, SystemException {
        return findByPrimaryKey((Serializable) accClosureDetailsSid);
    }

    /**
     * Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the acc closure details
     * @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AccClosureDetails accClosureDetails = (AccClosureDetails) EntityCacheUtil.getResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AccClosureDetailsImpl.class, primaryKey);

        if (accClosureDetails == _nullAccClosureDetails) {
            return null;
        }

        if (accClosureDetails == null) {
            Session session = null;

            try {
                session = openSession();

                accClosureDetails = (AccClosureDetails) session.get(AccClosureDetailsImpl.class,
                        primaryKey);

                if (accClosureDetails != null) {
                    cacheResult(accClosureDetails);
                } else {
                    EntityCacheUtil.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AccClosureDetailsImpl.class, primaryKey,
                        _nullAccClosureDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    AccClosureDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return accClosureDetails;
    }

    /**
     * Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accClosureDetailsSid the primary key of the acc closure details
     * @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AccClosureDetails fetchByPrimaryKey(int accClosureDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accClosureDetailsSid);
    }

    /**
     * Returns all the acc closure detailses.
     *
     * @return the acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the acc closure detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure detailses
     * @param end the upper bound of the range of acc closure detailses (not inclusive)
     * @return the range of acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the acc closure detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of acc closure detailses
     * @param end the upper bound of the range of acc closure detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of acc closure detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AccClosureDetails> findAll(int start, int end,
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

        List<AccClosureDetails> list = (List<AccClosureDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACCCLOSUREDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACCCLOSUREDETAILS;

                if (pagination) {
                    sql = sql.concat(AccClosureDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AccClosureDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AccClosureDetails>(list);
                } else {
                    list = (List<AccClosureDetails>) QueryUtil.list(q,
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
     * Removes all the acc closure detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AccClosureDetails accClosureDetails : findAll()) {
            remove(accClosureDetails);
        }
    }

    /**
     * Returns the number of acc closure detailses.
     *
     * @return the number of acc closure detailses
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

                Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREDETAILS);

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
     * Initializes the acc closure details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AccClosureDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AccClosureDetails>> listenersList = new ArrayList<ModelListener<AccClosureDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AccClosureDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AccClosureDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
