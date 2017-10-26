package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistCompanyGroupDetailsException;
import com.stpl.app.model.HistCompanyGroupDetails;
import com.stpl.app.model.impl.HistCompanyGroupDetailsImpl;
import com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPersistence;

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
 * The persistence implementation for the hist company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetailsPersistence
 * @see HistCompanyGroupDetailsUtil
 * @generated
 */
public class HistCompanyGroupDetailsPersistenceImpl extends BasePersistenceImpl<HistCompanyGroupDetails>
    implements HistCompanyGroupDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistCompanyGroupDetailsUtil} to access the hist company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistCompanyGroupDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            HistCompanyGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            HistCompanyGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTCOMPANYGROUPDETAILS = "SELECT histCompanyGroupDetails FROM HistCompanyGroupDetails histCompanyGroupDetails";
    private static final String _SQL_COUNT_HISTCOMPANYGROUPDETAILS = "SELECT COUNT(histCompanyGroupDetails) FROM HistCompanyGroupDetails histCompanyGroupDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histCompanyGroupDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistCompanyGroupDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistCompanyGroupDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "actionDate",
                "companyParentDetailsSid", "companyTradeclassSid", "actionFlag",
                "companyGroupSid", "versionNo", "companyGroupDetailsSid",
                "modifiedBy", "modifiedDate", "companyMasterSid"
            });
    private static HistCompanyGroupDetails _nullHistCompanyGroupDetails = new HistCompanyGroupDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistCompanyGroupDetails> toCacheModel() {
                return _nullHistCompanyGroupDetailsCacheModel;
            }
        };

    private static CacheModel<HistCompanyGroupDetails> _nullHistCompanyGroupDetailsCacheModel =
        new CacheModel<HistCompanyGroupDetails>() {
            @Override
            public HistCompanyGroupDetails toEntityModel() {
                return _nullHistCompanyGroupDetails;
            }
        };

    public HistCompanyGroupDetailsPersistenceImpl() {
        setModelClass(HistCompanyGroupDetails.class);
    }

    /**
     * Caches the hist company group details in the entity cache if it is enabled.
     *
     * @param histCompanyGroupDetails the hist company group details
     */
    @Override
    public void cacheResult(HistCompanyGroupDetails histCompanyGroupDetails) {
        EntityCacheUtil.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsImpl.class,
            histCompanyGroupDetails.getPrimaryKey(), histCompanyGroupDetails);

        histCompanyGroupDetails.resetOriginalValues();
    }

    /**
     * Caches the hist company group detailses in the entity cache if it is enabled.
     *
     * @param histCompanyGroupDetailses the hist company group detailses
     */
    @Override
    public void cacheResult(
        List<HistCompanyGroupDetails> histCompanyGroupDetailses) {
        for (HistCompanyGroupDetails histCompanyGroupDetails : histCompanyGroupDetailses) {
            if (EntityCacheUtil.getResult(
                        HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        HistCompanyGroupDetailsImpl.class,
                        histCompanyGroupDetails.getPrimaryKey()) == null) {
                cacheResult(histCompanyGroupDetails);
            } else {
                histCompanyGroupDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist company group detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistCompanyGroupDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistCompanyGroupDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist company group details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistCompanyGroupDetails histCompanyGroupDetails) {
        EntityCacheUtil.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsImpl.class,
            histCompanyGroupDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HistCompanyGroupDetails> histCompanyGroupDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistCompanyGroupDetails histCompanyGroupDetails : histCompanyGroupDetailses) {
            EntityCacheUtil.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                HistCompanyGroupDetailsImpl.class,
                histCompanyGroupDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
     *
     * @param histCompanyGroupDetailsPK the primary key for the new hist company group details
     * @return the new hist company group details
     */
    @Override
    public HistCompanyGroupDetails create(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
        HistCompanyGroupDetails histCompanyGroupDetails = new HistCompanyGroupDetailsImpl();

        histCompanyGroupDetails.setNew(true);
        histCompanyGroupDetails.setPrimaryKey(histCompanyGroupDetailsPK);

        return histCompanyGroupDetails;
    }

    /**
     * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histCompanyGroupDetailsPK the primary key of the hist company group details
     * @return the hist company group details that was removed
     * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails remove(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws NoSuchHistCompanyGroupDetailsException, SystemException {
        return remove((Serializable) histCompanyGroupDetailsPK);
    }

    /**
     * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist company group details
     * @return the hist company group details that was removed
     * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails remove(Serializable primaryKey)
        throws NoSuchHistCompanyGroupDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistCompanyGroupDetails histCompanyGroupDetails = (HistCompanyGroupDetails) session.get(HistCompanyGroupDetailsImpl.class,
                    primaryKey);

            if (histCompanyGroupDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histCompanyGroupDetails);
        } catch (NoSuchHistCompanyGroupDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistCompanyGroupDetails removeImpl(
        HistCompanyGroupDetails histCompanyGroupDetails)
        throws SystemException {
        histCompanyGroupDetails = toUnwrappedModel(histCompanyGroupDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histCompanyGroupDetails)) {
                histCompanyGroupDetails = (HistCompanyGroupDetails) session.get(HistCompanyGroupDetailsImpl.class,
                        histCompanyGroupDetails.getPrimaryKeyObj());
            }

            if (histCompanyGroupDetails != null) {
                session.delete(histCompanyGroupDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histCompanyGroupDetails != null) {
            clearCache(histCompanyGroupDetails);
        }

        return histCompanyGroupDetails;
    }

    @Override
    public HistCompanyGroupDetails updateImpl(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws SystemException {
        histCompanyGroupDetails = toUnwrappedModel(histCompanyGroupDetails);

        boolean isNew = histCompanyGroupDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histCompanyGroupDetails.isNew()) {
                session.save(histCompanyGroupDetails);

                histCompanyGroupDetails.setNew(false);
            } else {
                session.merge(histCompanyGroupDetails);
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

        EntityCacheUtil.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupDetailsImpl.class,
            histCompanyGroupDetails.getPrimaryKey(), histCompanyGroupDetails);

        return histCompanyGroupDetails;
    }

    protected HistCompanyGroupDetails toUnwrappedModel(
        HistCompanyGroupDetails histCompanyGroupDetails) {
        if (histCompanyGroupDetails instanceof HistCompanyGroupDetailsImpl) {
            return histCompanyGroupDetails;
        }

        HistCompanyGroupDetailsImpl histCompanyGroupDetailsImpl = new HistCompanyGroupDetailsImpl();

        histCompanyGroupDetailsImpl.setNew(histCompanyGroupDetails.isNew());
        histCompanyGroupDetailsImpl.setPrimaryKey(histCompanyGroupDetails.getPrimaryKey());

        histCompanyGroupDetailsImpl.setCreatedDate(histCompanyGroupDetails.getCreatedDate());
        histCompanyGroupDetailsImpl.setCreatedBy(histCompanyGroupDetails.getCreatedBy());
        histCompanyGroupDetailsImpl.setActionDate(histCompanyGroupDetails.getActionDate());
        histCompanyGroupDetailsImpl.setCompanyParentDetailsSid(histCompanyGroupDetails.getCompanyParentDetailsSid());
        histCompanyGroupDetailsImpl.setCompanyTradeclassSid(histCompanyGroupDetails.getCompanyTradeclassSid());
        histCompanyGroupDetailsImpl.setActionFlag(histCompanyGroupDetails.getActionFlag());
        histCompanyGroupDetailsImpl.setCompanyGroupSid(histCompanyGroupDetails.getCompanyGroupSid());
        histCompanyGroupDetailsImpl.setVersionNo(histCompanyGroupDetails.getVersionNo());
        histCompanyGroupDetailsImpl.setCompanyGroupDetailsSid(histCompanyGroupDetails.getCompanyGroupDetailsSid());
        histCompanyGroupDetailsImpl.setModifiedBy(histCompanyGroupDetails.getModifiedBy());
        histCompanyGroupDetailsImpl.setModifiedDate(histCompanyGroupDetails.getModifiedDate());
        histCompanyGroupDetailsImpl.setCompanyMasterSid(histCompanyGroupDetails.getCompanyMasterSid());

        return histCompanyGroupDetailsImpl;
    }

    /**
     * Returns the hist company group details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist company group details
     * @return the hist company group details
     * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistCompanyGroupDetailsException, SystemException {
        HistCompanyGroupDetails histCompanyGroupDetails = fetchByPrimaryKey(primaryKey);

        if (histCompanyGroupDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistCompanyGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histCompanyGroupDetails;
    }

    /**
     * Returns the hist company group details with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupDetailsException} if it could not be found.
     *
     * @param histCompanyGroupDetailsPK the primary key of the hist company group details
     * @return the hist company group details
     * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails findByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws NoSuchHistCompanyGroupDetailsException, SystemException {
        return findByPrimaryKey((Serializable) histCompanyGroupDetailsPK);
    }

    /**
     * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist company group details
     * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistCompanyGroupDetails histCompanyGroupDetails = (HistCompanyGroupDetails) EntityCacheUtil.getResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                HistCompanyGroupDetailsImpl.class, primaryKey);

        if (histCompanyGroupDetails == _nullHistCompanyGroupDetails) {
            return null;
        }

        if (histCompanyGroupDetails == null) {
            Session session = null;

            try {
                session = openSession();

                histCompanyGroupDetails = (HistCompanyGroupDetails) session.get(HistCompanyGroupDetailsImpl.class,
                        primaryKey);

                if (histCompanyGroupDetails != null) {
                    cacheResult(histCompanyGroupDetails);
                } else {
                    EntityCacheUtil.putResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        HistCompanyGroupDetailsImpl.class, primaryKey,
                        _nullHistCompanyGroupDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistCompanyGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    HistCompanyGroupDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histCompanyGroupDetails;
    }

    /**
     * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histCompanyGroupDetailsPK the primary key of the hist company group details
     * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroupDetails fetchByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histCompanyGroupDetailsPK);
    }

    /**
     * Returns all the hist company group detailses.
     *
     * @return the hist company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroupDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist company group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist company group detailses
     * @param end the upper bound of the range of hist company group detailses (not inclusive)
     * @return the range of hist company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroupDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist company group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist company group detailses
     * @param end the upper bound of the range of hist company group detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist company group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroupDetails> findAll(int start, int end,
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

        List<HistCompanyGroupDetails> list = (List<HistCompanyGroupDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTCOMPANYGROUPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTCOMPANYGROUPDETAILS;

                if (pagination) {
                    sql = sql.concat(HistCompanyGroupDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistCompanyGroupDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistCompanyGroupDetails>(list);
                } else {
                    list = (List<HistCompanyGroupDetails>) QueryUtil.list(q,
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
     * Removes all the hist company group detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistCompanyGroupDetails histCompanyGroupDetails : findAll()) {
            remove(histCompanyGroupDetails);
        }
    }

    /**
     * Returns the number of hist company group detailses.
     *
     * @return the number of hist company group detailses
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

                Query q = session.createQuery(_SQL_COUNT_HISTCOMPANYGROUPDETAILS);

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
     * Initializes the hist company group details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistCompanyGroupDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistCompanyGroupDetails>> listenersList = new ArrayList<ModelListener<HistCompanyGroupDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistCompanyGroupDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistCompanyGroupDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
