package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNaProjDetailsException;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.impl.NaProjDetailsImpl;
import com.stpl.app.model.impl.NaProjDetailsModelImpl;
import com.stpl.app.service.persistence.NaProjDetailsPersistence;

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
 * The persistence implementation for the na proj details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjDetailsPersistence
 * @see NaProjDetailsUtil
 * @generated
 */
public class NaProjDetailsPersistenceImpl extends BasePersistenceImpl<NaProjDetails>
    implements NaProjDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NaProjDetailsUtil} to access the na proj details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NaProjDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsModelImpl.FINDER_CACHE_ENABLED,
            NaProjDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsModelImpl.FINDER_CACHE_ENABLED,
            NaProjDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NAPROJDETAILS = "SELECT naProjDetails FROM NaProjDetails naProjDetails";
    private static final String _SQL_COUNT_NAPROJDETAILS = "SELECT COUNT(naProjDetails) FROM NaProjDetails naProjDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "naProjDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NaProjDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemMasterSid", "naProjMasterSid", "naProjDetailsSid"
            });
    private static NaProjDetails _nullNaProjDetails = new NaProjDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NaProjDetails> toCacheModel() {
                return _nullNaProjDetailsCacheModel;
            }
        };

    private static CacheModel<NaProjDetails> _nullNaProjDetailsCacheModel = new CacheModel<NaProjDetails>() {
            @Override
            public NaProjDetails toEntityModel() {
                return _nullNaProjDetails;
            }
        };

    public NaProjDetailsPersistenceImpl() {
        setModelClass(NaProjDetails.class);
    }

    /**
     * Caches the na proj details in the entity cache if it is enabled.
     *
     * @param naProjDetails the na proj details
     */
    @Override
    public void cacheResult(NaProjDetails naProjDetails) {
        EntityCacheUtil.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsImpl.class, naProjDetails.getPrimaryKey(),
            naProjDetails);

        naProjDetails.resetOriginalValues();
    }

    /**
     * Caches the na proj detailses in the entity cache if it is enabled.
     *
     * @param naProjDetailses the na proj detailses
     */
    @Override
    public void cacheResult(List<NaProjDetails> naProjDetailses) {
        for (NaProjDetails naProjDetails : naProjDetailses) {
            if (EntityCacheUtil.getResult(
                        NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjDetailsImpl.class, naProjDetails.getPrimaryKey()) == null) {
                cacheResult(naProjDetails);
            } else {
                naProjDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all na proj detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NaProjDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NaProjDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the na proj details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NaProjDetails naProjDetails) {
        EntityCacheUtil.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsImpl.class, naProjDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NaProjDetails> naProjDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NaProjDetails naProjDetails : naProjDetailses) {
            EntityCacheUtil.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
                NaProjDetailsImpl.class, naProjDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new na proj details with the primary key. Does not add the na proj details to the database.
     *
     * @param naProjDetailsSid the primary key for the new na proj details
     * @return the new na proj details
     */
    @Override
    public NaProjDetails create(int naProjDetailsSid) {
        NaProjDetails naProjDetails = new NaProjDetailsImpl();

        naProjDetails.setNew(true);
        naProjDetails.setPrimaryKey(naProjDetailsSid);

        return naProjDetails;
    }

    /**
     * Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param naProjDetailsSid the primary key of the na proj details
     * @return the na proj details that was removed
     * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails remove(int naProjDetailsSid)
        throws NoSuchNaProjDetailsException, SystemException {
        return remove((Serializable) naProjDetailsSid);
    }

    /**
     * Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the na proj details
     * @return the na proj details that was removed
     * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails remove(Serializable primaryKey)
        throws NoSuchNaProjDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NaProjDetails naProjDetails = (NaProjDetails) session.get(NaProjDetailsImpl.class,
                    primaryKey);

            if (naProjDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNaProjDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(naProjDetails);
        } catch (NoSuchNaProjDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NaProjDetails removeImpl(NaProjDetails naProjDetails)
        throws SystemException {
        naProjDetails = toUnwrappedModel(naProjDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(naProjDetails)) {
                naProjDetails = (NaProjDetails) session.get(NaProjDetailsImpl.class,
                        naProjDetails.getPrimaryKeyObj());
            }

            if (naProjDetails != null) {
                session.delete(naProjDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (naProjDetails != null) {
            clearCache(naProjDetails);
        }

        return naProjDetails;
    }

    @Override
    public NaProjDetails updateImpl(
        com.stpl.app.model.NaProjDetails naProjDetails)
        throws SystemException {
        naProjDetails = toUnwrappedModel(naProjDetails);

        boolean isNew = naProjDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (naProjDetails.isNew()) {
                session.save(naProjDetails);

                naProjDetails.setNew(false);
            } else {
                session.merge(naProjDetails);
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

        EntityCacheUtil.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
            NaProjDetailsImpl.class, naProjDetails.getPrimaryKey(),
            naProjDetails);

        return naProjDetails;
    }

    protected NaProjDetails toUnwrappedModel(NaProjDetails naProjDetails) {
        if (naProjDetails instanceof NaProjDetailsImpl) {
            return naProjDetails;
        }

        NaProjDetailsImpl naProjDetailsImpl = new NaProjDetailsImpl();

        naProjDetailsImpl.setNew(naProjDetails.isNew());
        naProjDetailsImpl.setPrimaryKey(naProjDetails.getPrimaryKey());

        naProjDetailsImpl.setItemMasterSid(naProjDetails.getItemMasterSid());
        naProjDetailsImpl.setNaProjMasterSid(naProjDetails.getNaProjMasterSid());
        naProjDetailsImpl.setNaProjDetailsSid(naProjDetails.getNaProjDetailsSid());

        return naProjDetailsImpl;
    }

    /**
     * Returns the na proj details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the na proj details
     * @return the na proj details
     * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNaProjDetailsException, SystemException {
        NaProjDetails naProjDetails = fetchByPrimaryKey(primaryKey);

        if (naProjDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNaProjDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return naProjDetails;
    }

    /**
     * Returns the na proj details with the primary key or throws a {@link com.stpl.app.NoSuchNaProjDetailsException} if it could not be found.
     *
     * @param naProjDetailsSid the primary key of the na proj details
     * @return the na proj details
     * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails findByPrimaryKey(int naProjDetailsSid)
        throws NoSuchNaProjDetailsException, SystemException {
        return findByPrimaryKey((Serializable) naProjDetailsSid);
    }

    /**
     * Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the na proj details
     * @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NaProjDetails naProjDetails = (NaProjDetails) EntityCacheUtil.getResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
                NaProjDetailsImpl.class, primaryKey);

        if (naProjDetails == _nullNaProjDetails) {
            return null;
        }

        if (naProjDetails == null) {
            Session session = null;

            try {
                session = openSession();

                naProjDetails = (NaProjDetails) session.get(NaProjDetailsImpl.class,
                        primaryKey);

                if (naProjDetails != null) {
                    cacheResult(naProjDetails);
                } else {
                    EntityCacheUtil.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjDetailsImpl.class, primaryKey, _nullNaProjDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    NaProjDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return naProjDetails;
    }

    /**
     * Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param naProjDetailsSid the primary key of the na proj details
     * @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjDetails fetchByPrimaryKey(int naProjDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) naProjDetailsSid);
    }

    /**
     * Returns all the na proj detailses.
     *
     * @return the na proj detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the na proj detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na proj detailses
     * @param end the upper bound of the range of na proj detailses (not inclusive)
     * @return the range of na proj detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the na proj detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na proj detailses
     * @param end the upper bound of the range of na proj detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of na proj detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjDetails> findAll(int start, int end,
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

        List<NaProjDetails> list = (List<NaProjDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NAPROJDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NAPROJDETAILS;

                if (pagination) {
                    sql = sql.concat(NaProjDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NaProjDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NaProjDetails>(list);
                } else {
                    list = (List<NaProjDetails>) QueryUtil.list(q,
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
     * Removes all the na proj detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NaProjDetails naProjDetails : findAll()) {
            remove(naProjDetails);
        }
    }

    /**
     * Returns the number of na proj detailses.
     *
     * @return the number of na proj detailses
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

                Query q = session.createQuery(_SQL_COUNT_NAPROJDETAILS);

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
     * Initializes the na proj details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NaProjDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NaProjDetails>> listenersList = new ArrayList<ModelListener<NaProjDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NaProjDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NaProjDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
