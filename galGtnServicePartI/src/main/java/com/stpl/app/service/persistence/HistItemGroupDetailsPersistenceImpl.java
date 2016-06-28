package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistItemGroupDetailsException;
import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.model.impl.HistItemGroupDetailsImpl;
import com.stpl.app.model.impl.HistItemGroupDetailsModelImpl;
import com.stpl.app.service.persistence.HistItemGroupDetailsPersistence;

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
 * The persistence implementation for the hist item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupDetailsPersistence
 * @see HistItemGroupDetailsUtil
 * @generated
 */
public class HistItemGroupDetailsPersistenceImpl extends BasePersistenceImpl<HistItemGroupDetails>
    implements HistItemGroupDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistItemGroupDetailsUtil} to access the hist item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistItemGroupDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            HistItemGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            HistItemGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTITEMGROUPDETAILS = "SELECT histItemGroupDetails FROM HistItemGroupDetails histItemGroupDetails";
    private static final String _SQL_COUNT_HISTITEMGROUPDETAILS = "SELECT COUNT(histItemGroupDetails) FROM HistItemGroupDetails histItemGroupDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histItemGroupDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistItemGroupDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistItemGroupDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemGroupDetailsSid", "createdDate", "createdBy", "actionDate",
                "itemMasterSid", "actionFlag", "versionNo", "modifiedBy",
                "modifiedDate", "itemGroupSid"
            });
    private static HistItemGroupDetails _nullHistItemGroupDetails = new HistItemGroupDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistItemGroupDetails> toCacheModel() {
                return _nullHistItemGroupDetailsCacheModel;
            }
        };

    private static CacheModel<HistItemGroupDetails> _nullHistItemGroupDetailsCacheModel =
        new CacheModel<HistItemGroupDetails>() {
            @Override
            public HistItemGroupDetails toEntityModel() {
                return _nullHistItemGroupDetails;
            }
        };

    public HistItemGroupDetailsPersistenceImpl() {
        setModelClass(HistItemGroupDetails.class);
    }

    /**
     * Caches the hist item group details in the entity cache if it is enabled.
     *
     * @param histItemGroupDetails the hist item group details
     */
    @Override
    public void cacheResult(HistItemGroupDetails histItemGroupDetails) {
        EntityCacheUtil.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsImpl.class,
            histItemGroupDetails.getPrimaryKey(), histItemGroupDetails);

        histItemGroupDetails.resetOriginalValues();
    }

    /**
     * Caches the hist item group detailses in the entity cache if it is enabled.
     *
     * @param histItemGroupDetailses the hist item group detailses
     */
    @Override
    public void cacheResult(List<HistItemGroupDetails> histItemGroupDetailses) {
        for (HistItemGroupDetails histItemGroupDetails : histItemGroupDetailses) {
            if (EntityCacheUtil.getResult(
                        HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        HistItemGroupDetailsImpl.class,
                        histItemGroupDetails.getPrimaryKey()) == null) {
                cacheResult(histItemGroupDetails);
            } else {
                histItemGroupDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist item group detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistItemGroupDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistItemGroupDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist item group details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistItemGroupDetails histItemGroupDetails) {
        EntityCacheUtil.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsImpl.class, histItemGroupDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HistItemGroupDetails> histItemGroupDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistItemGroupDetails histItemGroupDetails : histItemGroupDetailses) {
            EntityCacheUtil.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                HistItemGroupDetailsImpl.class,
                histItemGroupDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
     *
     * @param histItemGroupDetailsPK the primary key for the new hist item group details
     * @return the new hist item group details
     */
    @Override
    public HistItemGroupDetails create(
        HistItemGroupDetailsPK histItemGroupDetailsPK) {
        HistItemGroupDetails histItemGroupDetails = new HistItemGroupDetailsImpl();

        histItemGroupDetails.setNew(true);
        histItemGroupDetails.setPrimaryKey(histItemGroupDetailsPK);

        return histItemGroupDetails;
    }

    /**
     * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histItemGroupDetailsPK the primary key of the hist item group details
     * @return the hist item group details that was removed
     * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails remove(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws NoSuchHistItemGroupDetailsException, SystemException {
        return remove((Serializable) histItemGroupDetailsPK);
    }

    /**
     * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist item group details
     * @return the hist item group details that was removed
     * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails remove(Serializable primaryKey)
        throws NoSuchHistItemGroupDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistItemGroupDetails histItemGroupDetails = (HistItemGroupDetails) session.get(HistItemGroupDetailsImpl.class,
                    primaryKey);

            if (histItemGroupDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histItemGroupDetails);
        } catch (NoSuchHistItemGroupDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistItemGroupDetails removeImpl(
        HistItemGroupDetails histItemGroupDetails) throws SystemException {
        histItemGroupDetails = toUnwrappedModel(histItemGroupDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histItemGroupDetails)) {
                histItemGroupDetails = (HistItemGroupDetails) session.get(HistItemGroupDetailsImpl.class,
                        histItemGroupDetails.getPrimaryKeyObj());
            }

            if (histItemGroupDetails != null) {
                session.delete(histItemGroupDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histItemGroupDetails != null) {
            clearCache(histItemGroupDetails);
        }

        return histItemGroupDetails;
    }

    @Override
    public HistItemGroupDetails updateImpl(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws SystemException {
        histItemGroupDetails = toUnwrappedModel(histItemGroupDetails);

        boolean isNew = histItemGroupDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histItemGroupDetails.isNew()) {
                session.save(histItemGroupDetails);

                histItemGroupDetails.setNew(false);
            } else {
                session.merge(histItemGroupDetails);
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

        EntityCacheUtil.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupDetailsImpl.class,
            histItemGroupDetails.getPrimaryKey(), histItemGroupDetails);

        return histItemGroupDetails;
    }

    protected HistItemGroupDetails toUnwrappedModel(
        HistItemGroupDetails histItemGroupDetails) {
        if (histItemGroupDetails instanceof HistItemGroupDetailsImpl) {
            return histItemGroupDetails;
        }

        HistItemGroupDetailsImpl histItemGroupDetailsImpl = new HistItemGroupDetailsImpl();

        histItemGroupDetailsImpl.setNew(histItemGroupDetails.isNew());
        histItemGroupDetailsImpl.setPrimaryKey(histItemGroupDetails.getPrimaryKey());

        histItemGroupDetailsImpl.setItemGroupDetailsSid(histItemGroupDetails.getItemGroupDetailsSid());
        histItemGroupDetailsImpl.setCreatedDate(histItemGroupDetails.getCreatedDate());
        histItemGroupDetailsImpl.setCreatedBy(histItemGroupDetails.getCreatedBy());
        histItemGroupDetailsImpl.setActionDate(histItemGroupDetails.getActionDate());
        histItemGroupDetailsImpl.setItemMasterSid(histItemGroupDetails.getItemMasterSid());
        histItemGroupDetailsImpl.setActionFlag(histItemGroupDetails.getActionFlag());
        histItemGroupDetailsImpl.setVersionNo(histItemGroupDetails.getVersionNo());
        histItemGroupDetailsImpl.setModifiedBy(histItemGroupDetails.getModifiedBy());
        histItemGroupDetailsImpl.setModifiedDate(histItemGroupDetails.getModifiedDate());
        histItemGroupDetailsImpl.setItemGroupSid(histItemGroupDetails.getItemGroupSid());

        return histItemGroupDetailsImpl;
    }

    /**
     * Returns the hist item group details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist item group details
     * @return the hist item group details
     * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistItemGroupDetailsException, SystemException {
        HistItemGroupDetails histItemGroupDetails = fetchByPrimaryKey(primaryKey);

        if (histItemGroupDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histItemGroupDetails;
    }

    /**
     * Returns the hist item group details with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupDetailsException} if it could not be found.
     *
     * @param histItemGroupDetailsPK the primary key of the hist item group details
     * @return the hist item group details
     * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails findByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws NoSuchHistItemGroupDetailsException, SystemException {
        return findByPrimaryKey((Serializable) histItemGroupDetailsPK);
    }

    /**
     * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist item group details
     * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistItemGroupDetails histItemGroupDetails = (HistItemGroupDetails) EntityCacheUtil.getResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                HistItemGroupDetailsImpl.class, primaryKey);

        if (histItemGroupDetails == _nullHistItemGroupDetails) {
            return null;
        }

        if (histItemGroupDetails == null) {
            Session session = null;

            try {
                session = openSession();

                histItemGroupDetails = (HistItemGroupDetails) session.get(HistItemGroupDetailsImpl.class,
                        primaryKey);

                if (histItemGroupDetails != null) {
                    cacheResult(histItemGroupDetails);
                } else {
                    EntityCacheUtil.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        HistItemGroupDetailsImpl.class, primaryKey,
                        _nullHistItemGroupDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    HistItemGroupDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histItemGroupDetails;
    }

    /**
     * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histItemGroupDetailsPK the primary key of the hist item group details
     * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroupDetails fetchByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histItemGroupDetailsPK);
    }

    /**
     * Returns all the hist item group detailses.
     *
     * @return the hist item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroupDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist item group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist item group detailses
     * @param end the upper bound of the range of hist item group detailses (not inclusive)
     * @return the range of hist item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroupDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist item group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist item group detailses
     * @param end the upper bound of the range of hist item group detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist item group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroupDetails> findAll(int start, int end,
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

        List<HistItemGroupDetails> list = (List<HistItemGroupDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTITEMGROUPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTITEMGROUPDETAILS;

                if (pagination) {
                    sql = sql.concat(HistItemGroupDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistItemGroupDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistItemGroupDetails>(list);
                } else {
                    list = (List<HistItemGroupDetails>) QueryUtil.list(q,
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
     * Removes all the hist item group detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistItemGroupDetails histItemGroupDetails : findAll()) {
            remove(histItemGroupDetails);
        }
    }

    /**
     * Returns the number of hist item group detailses.
     *
     * @return the number of hist item group detailses
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

                Query q = session.createQuery(_SQL_COUNT_HISTITEMGROUPDETAILS);

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
     * Initializes the hist item group details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistItemGroupDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistItemGroupDetails>> listenersList = new ArrayList<ModelListener<HistItemGroupDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistItemGroupDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistItemGroupDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
