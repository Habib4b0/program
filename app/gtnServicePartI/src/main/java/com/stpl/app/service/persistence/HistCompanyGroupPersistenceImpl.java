package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistCompanyGroupException;
import com.stpl.app.model.HistCompanyGroup;
import com.stpl.app.model.impl.HistCompanyGroupImpl;
import com.stpl.app.model.impl.HistCompanyGroupModelImpl;
import com.stpl.app.service.persistence.HistCompanyGroupPersistence;

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
 * The persistence implementation for the hist company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupPersistence
 * @see HistCompanyGroupUtil
 * @generated
 */
public class HistCompanyGroupPersistenceImpl extends BasePersistenceImpl<HistCompanyGroup>
    implements HistCompanyGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistCompanyGroupUtil} to access the hist company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistCompanyGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED,
            HistCompanyGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED,
            HistCompanyGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTCOMPANYGROUP = "SELECT histCompanyGroup FROM HistCompanyGroup histCompanyGroup";
    private static final String _SQL_COUNT_HISTCOMPANYGROUP = "SELECT COUNT(histCompanyGroup) FROM HistCompanyGroup histCompanyGroup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histCompanyGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistCompanyGroup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistCompanyGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "companyGroupNo", "createdDate", "createdBy", "actionDate",
                "actionFlag", "companyGroupSid", "companyGroupDescription",
                "versionNo", "modifiedBy", "modifiedDate", "companyGroupName",
                "companyFilter"
            });
    private static HistCompanyGroup _nullHistCompanyGroup = new HistCompanyGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistCompanyGroup> toCacheModel() {
                return _nullHistCompanyGroupCacheModel;
            }
        };

    private static CacheModel<HistCompanyGroup> _nullHistCompanyGroupCacheModel = new CacheModel<HistCompanyGroup>() {
            @Override
            public HistCompanyGroup toEntityModel() {
                return _nullHistCompanyGroup;
            }
        };

    public HistCompanyGroupPersistenceImpl() {
        setModelClass(HistCompanyGroup.class);
    }

    /**
     * Caches the hist company group in the entity cache if it is enabled.
     *
     * @param histCompanyGroup the hist company group
     */
    @Override
    public void cacheResult(HistCompanyGroup histCompanyGroup) {
        EntityCacheUtil.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey(),
            histCompanyGroup);

        histCompanyGroup.resetOriginalValues();
    }

    /**
     * Caches the hist company groups in the entity cache if it is enabled.
     *
     * @param histCompanyGroups the hist company groups
     */
    @Override
    public void cacheResult(List<HistCompanyGroup> histCompanyGroups) {
        for (HistCompanyGroup histCompanyGroup : histCompanyGroups) {
            if (EntityCacheUtil.getResult(
                        HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                        HistCompanyGroupImpl.class,
                        histCompanyGroup.getPrimaryKey()) == null) {
                cacheResult(histCompanyGroup);
            } else {
                histCompanyGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist company groups.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistCompanyGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistCompanyGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist company group.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistCompanyGroup histCompanyGroup) {
        EntityCacheUtil.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HistCompanyGroup> histCompanyGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistCompanyGroup histCompanyGroup : histCompanyGroups) {
            EntityCacheUtil.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
     *
     * @param histCompanyGroupPK the primary key for the new hist company group
     * @return the new hist company group
     */
    @Override
    public HistCompanyGroup create(HistCompanyGroupPK histCompanyGroupPK) {
        HistCompanyGroup histCompanyGroup = new HistCompanyGroupImpl();

        histCompanyGroup.setNew(true);
        histCompanyGroup.setPrimaryKey(histCompanyGroupPK);

        return histCompanyGroup;
    }

    /**
     * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histCompanyGroupPK the primary key of the hist company group
     * @return the hist company group that was removed
     * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup remove(HistCompanyGroupPK histCompanyGroupPK)
        throws NoSuchHistCompanyGroupException, SystemException {
        return remove((Serializable) histCompanyGroupPK);
    }

    /**
     * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist company group
     * @return the hist company group that was removed
     * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup remove(Serializable primaryKey)
        throws NoSuchHistCompanyGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistCompanyGroup histCompanyGroup = (HistCompanyGroup) session.get(HistCompanyGroupImpl.class,
                    primaryKey);

            if (histCompanyGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histCompanyGroup);
        } catch (NoSuchHistCompanyGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistCompanyGroup removeImpl(HistCompanyGroup histCompanyGroup)
        throws SystemException {
        histCompanyGroup = toUnwrappedModel(histCompanyGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histCompanyGroup)) {
                histCompanyGroup = (HistCompanyGroup) session.get(HistCompanyGroupImpl.class,
                        histCompanyGroup.getPrimaryKeyObj());
            }

            if (histCompanyGroup != null) {
                session.delete(histCompanyGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histCompanyGroup != null) {
            clearCache(histCompanyGroup);
        }

        return histCompanyGroup;
    }

    @Override
    public HistCompanyGroup updateImpl(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws SystemException {
        histCompanyGroup = toUnwrappedModel(histCompanyGroup);

        boolean isNew = histCompanyGroup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histCompanyGroup.isNew()) {
                session.save(histCompanyGroup);

                histCompanyGroup.setNew(false);
            } else {
                session.merge(histCompanyGroup);
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

        EntityCacheUtil.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistCompanyGroupImpl.class, histCompanyGroup.getPrimaryKey(),
            histCompanyGroup);

        return histCompanyGroup;
    }

    protected HistCompanyGroup toUnwrappedModel(
        HistCompanyGroup histCompanyGroup) {
        if (histCompanyGroup instanceof HistCompanyGroupImpl) {
            return histCompanyGroup;
        }

        HistCompanyGroupImpl histCompanyGroupImpl = new HistCompanyGroupImpl();

        histCompanyGroupImpl.setNew(histCompanyGroup.isNew());
        histCompanyGroupImpl.setPrimaryKey(histCompanyGroup.getPrimaryKey());

        histCompanyGroupImpl.setCompanyGroupNo(histCompanyGroup.getCompanyGroupNo());
        histCompanyGroupImpl.setCreatedDate(histCompanyGroup.getCreatedDate());
        histCompanyGroupImpl.setCreatedBy(histCompanyGroup.getCreatedBy());
        histCompanyGroupImpl.setActionDate(histCompanyGroup.getActionDate());
        histCompanyGroupImpl.setActionFlag(histCompanyGroup.getActionFlag());
        histCompanyGroupImpl.setCompanyGroupSid(histCompanyGroup.getCompanyGroupSid());
        histCompanyGroupImpl.setCompanyGroupDescription(histCompanyGroup.getCompanyGroupDescription());
        histCompanyGroupImpl.setVersionNo(histCompanyGroup.getVersionNo());
        histCompanyGroupImpl.setModifiedBy(histCompanyGroup.getModifiedBy());
        histCompanyGroupImpl.setModifiedDate(histCompanyGroup.getModifiedDate());
        histCompanyGroupImpl.setCompanyGroupName(histCompanyGroup.getCompanyGroupName());
        histCompanyGroupImpl.setCompanyFilter(histCompanyGroup.getCompanyFilter());

        return histCompanyGroupImpl;
    }

    /**
     * Returns the hist company group with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist company group
     * @return the hist company group
     * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistCompanyGroupException, SystemException {
        HistCompanyGroup histCompanyGroup = fetchByPrimaryKey(primaryKey);

        if (histCompanyGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histCompanyGroup;
    }

    /**
     * Returns the hist company group with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupException} if it could not be found.
     *
     * @param histCompanyGroupPK the primary key of the hist company group
     * @return the hist company group
     * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup findByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK)
        throws NoSuchHistCompanyGroupException, SystemException {
        return findByPrimaryKey((Serializable) histCompanyGroupPK);
    }

    /**
     * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist company group
     * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistCompanyGroup histCompanyGroup = (HistCompanyGroup) EntityCacheUtil.getResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                HistCompanyGroupImpl.class, primaryKey);

        if (histCompanyGroup == _nullHistCompanyGroup) {
            return null;
        }

        if (histCompanyGroup == null) {
            Session session = null;

            try {
                session = openSession();

                histCompanyGroup = (HistCompanyGroup) session.get(HistCompanyGroupImpl.class,
                        primaryKey);

                if (histCompanyGroup != null) {
                    cacheResult(histCompanyGroup);
                } else {
                    EntityCacheUtil.putResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                        HistCompanyGroupImpl.class, primaryKey,
                        _nullHistCompanyGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistCompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                    HistCompanyGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histCompanyGroup;
    }

    /**
     * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histCompanyGroupPK the primary key of the hist company group
     * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistCompanyGroup fetchByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) histCompanyGroupPK);
    }

    /**
     * Returns all the hist company groups.
     *
     * @return the hist company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist company groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist company groups
     * @param end the upper bound of the range of hist company groups (not inclusive)
     * @return the range of hist company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist company groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist company groups
     * @param end the upper bound of the range of hist company groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistCompanyGroup> findAll(int start, int end,
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

        List<HistCompanyGroup> list = (List<HistCompanyGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTCOMPANYGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTCOMPANYGROUP;

                if (pagination) {
                    sql = sql.concat(HistCompanyGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistCompanyGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistCompanyGroup>(list);
                } else {
                    list = (List<HistCompanyGroup>) QueryUtil.list(q,
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
     * Removes all the hist company groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistCompanyGroup histCompanyGroup : findAll()) {
            remove(histCompanyGroup);
        }
    }

    /**
     * Returns the number of hist company groups.
     *
     * @return the number of hist company groups
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

                Query q = session.createQuery(_SQL_COUNT_HISTCOMPANYGROUP);

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
     * Initializes the hist company group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistCompanyGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistCompanyGroup>> listenersList = new ArrayList<ModelListener<HistCompanyGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistCompanyGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistCompanyGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
