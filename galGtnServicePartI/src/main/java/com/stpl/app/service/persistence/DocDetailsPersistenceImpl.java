package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDocDetailsException;
import com.stpl.app.model.DocDetails;
import com.stpl.app.model.impl.DocDetailsImpl;
import com.stpl.app.model.impl.DocDetailsModelImpl;
import com.stpl.app.service.persistence.DocDetailsPersistence;

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
 * The persistence implementation for the doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DocDetailsPersistence
 * @see DocDetailsUtil
 * @generated
 */
public class DocDetailsPersistenceImpl extends BasePersistenceImpl<DocDetails>
    implements DocDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DocDetailsUtil} to access the doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DocDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsModelImpl.FINDER_CACHE_ENABLED, DocDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsModelImpl.FINDER_CACHE_ENABLED, DocDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DOCDETAILS = "SELECT docDetails FROM DocDetails docDetails";
    private static final String _SQL_COUNT_DOCDETAILS = "SELECT COUNT(docDetails) FROM DocDetails docDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "docDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DocDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DocDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "fileName", "fileType", "uploadedBy", "forecastType",
                "projectionId", "docDetailsId", "uploadedDate", "fileSize"
            });
    private static DocDetails _nullDocDetails = new DocDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DocDetails> toCacheModel() {
                return _nullDocDetailsCacheModel;
            }
        };

    private static CacheModel<DocDetails> _nullDocDetailsCacheModel = new CacheModel<DocDetails>() {
            @Override
            public DocDetails toEntityModel() {
                return _nullDocDetails;
            }
        };

    public DocDetailsPersistenceImpl() {
        setModelClass(DocDetails.class);
    }

    /**
     * Caches the doc details in the entity cache if it is enabled.
     *
     * @param docDetails the doc details
     */
    @Override
    public void cacheResult(DocDetails docDetails) {
        EntityCacheUtil.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsImpl.class, docDetails.getPrimaryKey(), docDetails);

        docDetails.resetOriginalValues();
    }

    /**
     * Caches the doc detailses in the entity cache if it is enabled.
     *
     * @param docDetailses the doc detailses
     */
    @Override
    public void cacheResult(List<DocDetails> docDetailses) {
        for (DocDetails docDetails : docDetailses) {
            if (EntityCacheUtil.getResult(
                        DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DocDetailsImpl.class, docDetails.getPrimaryKey()) == null) {
                cacheResult(docDetails);
            } else {
                docDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all doc detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DocDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DocDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the doc details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DocDetails docDetails) {
        EntityCacheUtil.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsImpl.class, docDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<DocDetails> docDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DocDetails docDetails : docDetailses) {
            EntityCacheUtil.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DocDetailsImpl.class, docDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new doc details with the primary key. Does not add the doc details to the database.
     *
     * @param docDetailsId the primary key for the new doc details
     * @return the new doc details
     */
    @Override
    public DocDetails create(int docDetailsId) {
        DocDetails docDetails = new DocDetailsImpl();

        docDetails.setNew(true);
        docDetails.setPrimaryKey(docDetailsId);

        return docDetails;
    }

    /**
     * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param docDetailsId the primary key of the doc details
     * @return the doc details that was removed
     * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails remove(int docDetailsId)
        throws NoSuchDocDetailsException, SystemException {
        return remove((Serializable) docDetailsId);
    }

    /**
     * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the doc details
     * @return the doc details that was removed
     * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails remove(Serializable primaryKey)
        throws NoSuchDocDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DocDetails docDetails = (DocDetails) session.get(DocDetailsImpl.class,
                    primaryKey);

            if (docDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(docDetails);
        } catch (NoSuchDocDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DocDetails removeImpl(DocDetails docDetails)
        throws SystemException {
        docDetails = toUnwrappedModel(docDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(docDetails)) {
                docDetails = (DocDetails) session.get(DocDetailsImpl.class,
                        docDetails.getPrimaryKeyObj());
            }

            if (docDetails != null) {
                session.delete(docDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (docDetails != null) {
            clearCache(docDetails);
        }

        return docDetails;
    }

    @Override
    public DocDetails updateImpl(com.stpl.app.model.DocDetails docDetails)
        throws SystemException {
        docDetails = toUnwrappedModel(docDetails);

        boolean isNew = docDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (docDetails.isNew()) {
                session.save(docDetails);

                docDetails.setNew(false);
            } else {
                session.merge(docDetails);
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

        EntityCacheUtil.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DocDetailsImpl.class, docDetails.getPrimaryKey(), docDetails);

        return docDetails;
    }

    protected DocDetails toUnwrappedModel(DocDetails docDetails) {
        if (docDetails instanceof DocDetailsImpl) {
            return docDetails;
        }

        DocDetailsImpl docDetailsImpl = new DocDetailsImpl();

        docDetailsImpl.setNew(docDetails.isNew());
        docDetailsImpl.setPrimaryKey(docDetails.getPrimaryKey());

        docDetailsImpl.setFileName(docDetails.getFileName());
        docDetailsImpl.setFileType(docDetails.getFileType());
        docDetailsImpl.setUploadedBy(docDetails.getUploadedBy());
        docDetailsImpl.setForecastType(docDetails.getForecastType());
        docDetailsImpl.setProjectionId(docDetails.getProjectionId());
        docDetailsImpl.setDocDetailsId(docDetails.getDocDetailsId());
        docDetailsImpl.setUploadedDate(docDetails.getUploadedDate());
        docDetailsImpl.setFileSize(docDetails.getFileSize());

        return docDetailsImpl;
    }

    /**
     * Returns the doc details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the doc details
     * @return the doc details
     * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDocDetailsException, SystemException {
        DocDetails docDetails = fetchByPrimaryKey(primaryKey);

        if (docDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDocDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return docDetails;
    }

    /**
     * Returns the doc details with the primary key or throws a {@link com.stpl.app.NoSuchDocDetailsException} if it could not be found.
     *
     * @param docDetailsId the primary key of the doc details
     * @return the doc details
     * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails findByPrimaryKey(int docDetailsId)
        throws NoSuchDocDetailsException, SystemException {
        return findByPrimaryKey((Serializable) docDetailsId);
    }

    /**
     * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the doc details
     * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DocDetails docDetails = (DocDetails) EntityCacheUtil.getResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DocDetailsImpl.class, primaryKey);

        if (docDetails == _nullDocDetails) {
            return null;
        }

        if (docDetails == null) {
            Session session = null;

            try {
                session = openSession();

                docDetails = (DocDetails) session.get(DocDetailsImpl.class,
                        primaryKey);

                if (docDetails != null) {
                    cacheResult(docDetails);
                } else {
                    EntityCacheUtil.putResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DocDetailsImpl.class, primaryKey, _nullDocDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DocDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    DocDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return docDetails;
    }

    /**
     * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param docDetailsId the primary key of the doc details
     * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DocDetails fetchByPrimaryKey(int docDetailsId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) docDetailsId);
    }

    /**
     * Returns all the doc detailses.
     *
     * @return the doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DocDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the doc detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of doc detailses
     * @param end the upper bound of the range of doc detailses (not inclusive)
     * @return the range of doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DocDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the doc detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of doc detailses
     * @param end the upper bound of the range of doc detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of doc detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DocDetails> findAll(int start, int end,
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

        List<DocDetails> list = (List<DocDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DOCDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DOCDETAILS;

                if (pagination) {
                    sql = sql.concat(DocDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DocDetails>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DocDetails>(list);
                } else {
                    list = (List<DocDetails>) QueryUtil.list(q, getDialect(),
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
     * Removes all the doc detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DocDetails docDetails : findAll()) {
            remove(docDetails);
        }
    }

    /**
     * Returns the number of doc detailses.
     *
     * @return the number of doc detailses
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

                Query q = session.createQuery(_SQL_COUNT_DOCDETAILS);

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
     * Initializes the doc details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DocDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DocDetails>> listenersList = new ArrayList<ModelListener<DocDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DocDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DocDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
