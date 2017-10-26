package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffDocDetailsException;
import com.stpl.app.parttwo.model.CffDocDetails;
import com.stpl.app.parttwo.model.impl.CffDocDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffDocDetailsPersistence;

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
 * The persistence implementation for the cff doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDocDetailsPersistence
 * @see CffDocDetailsUtil
 * @generated
 */
public class CffDocDetailsPersistenceImpl extends BasePersistenceImpl<CffDocDetails>
    implements CffDocDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffDocDetailsUtil} to access the cff doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffDocDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffDocDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsModelImpl.FINDER_CACHE_ENABLED,
            CffDocDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFDOCDETAILS = "SELECT cffDocDetails FROM CffDocDetails cffDocDetails";
    private static final String _SQL_COUNT_CFFDOCDETAILS = "SELECT COUNT(cffDocDetails) FROM CffDocDetails cffDocDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffDocDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffDocDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffDocDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "fileName", "uploadDate", "fileType", "uploadBy", "cffMasterSid",
                "cffDocDetailsSid", "fileSize"
            });
    private static CffDocDetails _nullCffDocDetails = new CffDocDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffDocDetails> toCacheModel() {
                return _nullCffDocDetailsCacheModel;
            }
        };

    private static CacheModel<CffDocDetails> _nullCffDocDetailsCacheModel = new CacheModel<CffDocDetails>() {
            @Override
            public CffDocDetails toEntityModel() {
                return _nullCffDocDetails;
            }
        };

    public CffDocDetailsPersistenceImpl() {
        setModelClass(CffDocDetails.class);
    }

    /**
     * Caches the cff doc details in the entity cache if it is enabled.
     *
     * @param cffDocDetails the cff doc details
     */
    @Override
    public void cacheResult(CffDocDetails cffDocDetails) {
        EntityCacheUtil.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey(),
            cffDocDetails);

        cffDocDetails.resetOriginalValues();
    }

    /**
     * Caches the cff doc detailses in the entity cache if it is enabled.
     *
     * @param cffDocDetailses the cff doc detailses
     */
    @Override
    public void cacheResult(List<CffDocDetails> cffDocDetailses) {
        for (CffDocDetails cffDocDetails : cffDocDetailses) {
            if (EntityCacheUtil.getResult(
                        CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey()) == null) {
                cacheResult(cffDocDetails);
            } else {
                cffDocDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff doc detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffDocDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffDocDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff doc details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffDocDetails cffDocDetails) {
        EntityCacheUtil.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffDocDetails> cffDocDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffDocDetails cffDocDetails : cffDocDetailses) {
            EntityCacheUtil.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
     *
     * @param cffDocDetailsSid the primary key for the new cff doc details
     * @return the new cff doc details
     */
    @Override
    public CffDocDetails create(int cffDocDetailsSid) {
        CffDocDetails cffDocDetails = new CffDocDetailsImpl();

        cffDocDetails.setNew(true);
        cffDocDetails.setPrimaryKey(cffDocDetailsSid);

        return cffDocDetails;
    }

    /**
     * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffDocDetailsSid the primary key of the cff doc details
     * @return the cff doc details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails remove(int cffDocDetailsSid)
        throws NoSuchCffDocDetailsException, SystemException {
        return remove((Serializable) cffDocDetailsSid);
    }

    /**
     * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff doc details
     * @return the cff doc details that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails remove(Serializable primaryKey)
        throws NoSuchCffDocDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffDocDetails cffDocDetails = (CffDocDetails) session.get(CffDocDetailsImpl.class,
                    primaryKey);

            if (cffDocDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffDocDetails);
        } catch (NoSuchCffDocDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffDocDetails removeImpl(CffDocDetails cffDocDetails)
        throws SystemException {
        cffDocDetails = toUnwrappedModel(cffDocDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffDocDetails)) {
                cffDocDetails = (CffDocDetails) session.get(CffDocDetailsImpl.class,
                        cffDocDetails.getPrimaryKeyObj());
            }

            if (cffDocDetails != null) {
                session.delete(cffDocDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffDocDetails != null) {
            clearCache(cffDocDetails);
        }

        return cffDocDetails;
    }

    @Override
    public CffDocDetails updateImpl(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws SystemException {
        cffDocDetails = toUnwrappedModel(cffDocDetails);

        boolean isNew = cffDocDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffDocDetails.isNew()) {
                session.save(cffDocDetails);

                cffDocDetails.setNew(false);
            } else {
                session.merge(cffDocDetails);
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

        EntityCacheUtil.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            CffDocDetailsImpl.class, cffDocDetails.getPrimaryKey(),
            cffDocDetails);

        return cffDocDetails;
    }

    protected CffDocDetails toUnwrappedModel(CffDocDetails cffDocDetails) {
        if (cffDocDetails instanceof CffDocDetailsImpl) {
            return cffDocDetails;
        }

        CffDocDetailsImpl cffDocDetailsImpl = new CffDocDetailsImpl();

        cffDocDetailsImpl.setNew(cffDocDetails.isNew());
        cffDocDetailsImpl.setPrimaryKey(cffDocDetails.getPrimaryKey());

        cffDocDetailsImpl.setFileName(cffDocDetails.getFileName());
        cffDocDetailsImpl.setUploadDate(cffDocDetails.getUploadDate());
        cffDocDetailsImpl.setFileType(cffDocDetails.getFileType());
        cffDocDetailsImpl.setUploadBy(cffDocDetails.getUploadBy());
        cffDocDetailsImpl.setCffMasterSid(cffDocDetails.getCffMasterSid());
        cffDocDetailsImpl.setCffDocDetailsSid(cffDocDetails.getCffDocDetailsSid());
        cffDocDetailsImpl.setFileSize(cffDocDetails.getFileSize());

        return cffDocDetailsImpl;
    }

    /**
     * Returns the cff doc details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff doc details
     * @return the cff doc details
     * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffDocDetailsException, SystemException {
        CffDocDetails cffDocDetails = fetchByPrimaryKey(primaryKey);

        if (cffDocDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffDocDetails;
    }

    /**
     * Returns the cff doc details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffDocDetailsException} if it could not be found.
     *
     * @param cffDocDetailsSid the primary key of the cff doc details
     * @return the cff doc details
     * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails findByPrimaryKey(int cffDocDetailsSid)
        throws NoSuchCffDocDetailsException, SystemException {
        return findByPrimaryKey((Serializable) cffDocDetailsSid);
    }

    /**
     * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff doc details
     * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffDocDetails cffDocDetails = (CffDocDetails) EntityCacheUtil.getResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                CffDocDetailsImpl.class, primaryKey);

        if (cffDocDetails == _nullCffDocDetails) {
            return null;
        }

        if (cffDocDetails == null) {
            Session session = null;

            try {
                session = openSession();

                cffDocDetails = (CffDocDetails) session.get(CffDocDetailsImpl.class,
                        primaryKey);

                if (cffDocDetails != null) {
                    cacheResult(cffDocDetails);
                } else {
                    EntityCacheUtil.putResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        CffDocDetailsImpl.class, primaryKey, _nullCffDocDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffDocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    CffDocDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffDocDetails;
    }

    /**
     * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffDocDetailsSid the primary key of the cff doc details
     * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffDocDetails fetchByPrimaryKey(int cffDocDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffDocDetailsSid);
    }

    /**
     * Returns all the cff doc detailses.
     *
     * @return the cff doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDocDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff doc detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff doc detailses
     * @param end the upper bound of the range of cff doc detailses (not inclusive)
     * @return the range of cff doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDocDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff doc detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff doc detailses
     * @param end the upper bound of the range of cff doc detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffDocDetails> findAll(int start, int end,
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

        List<CffDocDetails> list = (List<CffDocDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFDOCDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFDOCDETAILS;

                if (pagination) {
                    sql = sql.concat(CffDocDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffDocDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffDocDetails>(list);
                } else {
                    list = (List<CffDocDetails>) QueryUtil.list(q,
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
     * Removes all the cff doc detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffDocDetails cffDocDetails : findAll()) {
            remove(cffDocDetails);
        }
    }

    /**
     * Returns the number of cff doc detailses.
     *
     * @return the number of cff doc detailses
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

                Query q = session.createQuery(_SQL_COUNT_CFFDOCDETAILS);

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
     * Initializes the cff doc details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffDocDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffDocDetails>> listenersList = new ArrayList<ModelListener<CffDocDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffDocDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffDocDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
