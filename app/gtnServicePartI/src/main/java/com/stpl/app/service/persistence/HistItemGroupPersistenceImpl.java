package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistItemGroupException;
import com.stpl.app.model.HistItemGroup;
import com.stpl.app.model.impl.HistItemGroupImpl;
import com.stpl.app.model.impl.HistItemGroupModelImpl;
import com.stpl.app.service.persistence.HistItemGroupPersistence;

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
 * The persistence implementation for the hist item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupPersistence
 * @see HistItemGroupUtil
 * @generated
 */
public class HistItemGroupPersistenceImpl extends BasePersistenceImpl<HistItemGroup>
    implements HistItemGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistItemGroupUtil} to access the hist item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistItemGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupModelImpl.FINDER_CACHE_ENABLED,
            HistItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupModelImpl.FINDER_CACHE_ENABLED,
            HistItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTITEMGROUP = "SELECT histItemGroup FROM HistItemGroup histItemGroup";
    private static final String _SQL_COUNT_HISTITEMGROUP = "SELECT COUNT(histItemGroup) FROM HistItemGroup histItemGroup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histItemGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistItemGroup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistItemGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "actionFlag", "itemGroupNo",
                "versionNo", "modifiedBy", "itemGroupDescription",
                "modifiedDate", "itemGroupName", "itemGroupSid",
                "companyMasterSid"
            });
    private static HistItemGroup _nullHistItemGroup = new HistItemGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistItemGroup> toCacheModel() {
                return _nullHistItemGroupCacheModel;
            }
        };

    private static CacheModel<HistItemGroup> _nullHistItemGroupCacheModel = new CacheModel<HistItemGroup>() {
            @Override
            public HistItemGroup toEntityModel() {
                return _nullHistItemGroup;
            }
        };

    public HistItemGroupPersistenceImpl() {
        setModelClass(HistItemGroup.class);
    }

    /**
     * Caches the hist item group in the entity cache if it is enabled.
     *
     * @param histItemGroup the hist item group
     */
    @Override
    public void cacheResult(HistItemGroup histItemGroup) {
        EntityCacheUtil.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupImpl.class, histItemGroup.getPrimaryKey(),
            histItemGroup);

        histItemGroup.resetOriginalValues();
    }

    /**
     * Caches the hist item groups in the entity cache if it is enabled.
     *
     * @param histItemGroups the hist item groups
     */
    @Override
    public void cacheResult(List<HistItemGroup> histItemGroups) {
        for (HistItemGroup histItemGroup : histItemGroups) {
            if (EntityCacheUtil.getResult(
                        HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                        HistItemGroupImpl.class, histItemGroup.getPrimaryKey()) == null) {
                cacheResult(histItemGroup);
            } else {
                histItemGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist item groups.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistItemGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistItemGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist item group.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistItemGroup histItemGroup) {
        EntityCacheUtil.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupImpl.class, histItemGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HistItemGroup> histItemGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistItemGroup histItemGroup : histItemGroups) {
            EntityCacheUtil.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                HistItemGroupImpl.class, histItemGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist item group with the primary key. Does not add the hist item group to the database.
     *
     * @param histItemGroupPK the primary key for the new hist item group
     * @return the new hist item group
     */
    @Override
    public HistItemGroup create(HistItemGroupPK histItemGroupPK) {
        HistItemGroup histItemGroup = new HistItemGroupImpl();

        histItemGroup.setNew(true);
        histItemGroup.setPrimaryKey(histItemGroupPK);

        return histItemGroup;
    }

    /**
     * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histItemGroupPK the primary key of the hist item group
     * @return the hist item group that was removed
     * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup remove(HistItemGroupPK histItemGroupPK)
        throws NoSuchHistItemGroupException, SystemException {
        return remove((Serializable) histItemGroupPK);
    }

    /**
     * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist item group
     * @return the hist item group that was removed
     * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup remove(Serializable primaryKey)
        throws NoSuchHistItemGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistItemGroup histItemGroup = (HistItemGroup) session.get(HistItemGroupImpl.class,
                    primaryKey);

            if (histItemGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histItemGroup);
        } catch (NoSuchHistItemGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistItemGroup removeImpl(HistItemGroup histItemGroup)
        throws SystemException {
        histItemGroup = toUnwrappedModel(histItemGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histItemGroup)) {
                histItemGroup = (HistItemGroup) session.get(HistItemGroupImpl.class,
                        histItemGroup.getPrimaryKeyObj());
            }

            if (histItemGroup != null) {
                session.delete(histItemGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histItemGroup != null) {
            clearCache(histItemGroup);
        }

        return histItemGroup;
    }

    @Override
    public HistItemGroup updateImpl(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws SystemException {
        histItemGroup = toUnwrappedModel(histItemGroup);

        boolean isNew = histItemGroup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histItemGroup.isNew()) {
                session.save(histItemGroup);

                histItemGroup.setNew(false);
            } else {
                session.merge(histItemGroup);
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

        EntityCacheUtil.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            HistItemGroupImpl.class, histItemGroup.getPrimaryKey(),
            histItemGroup);

        return histItemGroup;
    }

    protected HistItemGroup toUnwrappedModel(HistItemGroup histItemGroup) {
        if (histItemGroup instanceof HistItemGroupImpl) {
            return histItemGroup;
        }

        HistItemGroupImpl histItemGroupImpl = new HistItemGroupImpl();

        histItemGroupImpl.setNew(histItemGroup.isNew());
        histItemGroupImpl.setPrimaryKey(histItemGroup.getPrimaryKey());

        histItemGroupImpl.setCreatedDate(histItemGroup.getCreatedDate());
        histItemGroupImpl.setCreatedBy(histItemGroup.getCreatedBy());
        histItemGroupImpl.setActionFlag(histItemGroup.getActionFlag());
        histItemGroupImpl.setItemGroupNo(histItemGroup.getItemGroupNo());
        histItemGroupImpl.setVersionNo(histItemGroup.getVersionNo());
        histItemGroupImpl.setModifiedBy(histItemGroup.getModifiedBy());
        histItemGroupImpl.setItemGroupDescription(histItemGroup.getItemGroupDescription());
        histItemGroupImpl.setModifiedDate(histItemGroup.getModifiedDate());
        histItemGroupImpl.setItemGroupName(histItemGroup.getItemGroupName());
        histItemGroupImpl.setItemGroupSid(histItemGroup.getItemGroupSid());
        histItemGroupImpl.setCompanyMasterSid(histItemGroup.getCompanyMasterSid());

        return histItemGroupImpl;
    }

    /**
     * Returns the hist item group with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist item group
     * @return the hist item group
     * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistItemGroupException, SystemException {
        HistItemGroup histItemGroup = fetchByPrimaryKey(primaryKey);

        if (histItemGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histItemGroup;
    }

    /**
     * Returns the hist item group with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupException} if it could not be found.
     *
     * @param histItemGroupPK the primary key of the hist item group
     * @return the hist item group
     * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup findByPrimaryKey(HistItemGroupPK histItemGroupPK)
        throws NoSuchHistItemGroupException, SystemException {
        return findByPrimaryKey((Serializable) histItemGroupPK);
    }

    /**
     * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist item group
     * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistItemGroup histItemGroup = (HistItemGroup) EntityCacheUtil.getResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                HistItemGroupImpl.class, primaryKey);

        if (histItemGroup == _nullHistItemGroup) {
            return null;
        }

        if (histItemGroup == null) {
            Session session = null;

            try {
                session = openSession();

                histItemGroup = (HistItemGroup) session.get(HistItemGroupImpl.class,
                        primaryKey);

                if (histItemGroup != null) {
                    cacheResult(histItemGroup);
                } else {
                    EntityCacheUtil.putResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                        HistItemGroupImpl.class, primaryKey, _nullHistItemGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                    HistItemGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histItemGroup;
    }

    /**
     * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histItemGroupPK the primary key of the hist item group
     * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistItemGroup fetchByPrimaryKey(HistItemGroupPK histItemGroupPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histItemGroupPK);
    }

    /**
     * Returns all the hist item groups.
     *
     * @return the hist item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist item groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist item groups
     * @param end the upper bound of the range of hist item groups (not inclusive)
     * @return the range of hist item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist item groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist item groups
     * @param end the upper bound of the range of hist item groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistItemGroup> findAll(int start, int end,
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

        List<HistItemGroup> list = (List<HistItemGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTITEMGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTITEMGROUP;

                if (pagination) {
                    sql = sql.concat(HistItemGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistItemGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistItemGroup>(list);
                } else {
                    list = (List<HistItemGroup>) QueryUtil.list(q,
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
     * Removes all the hist item groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistItemGroup histItemGroup : findAll()) {
            remove(histItemGroup);
        }
    }

    /**
     * Returns the number of hist item groups.
     *
     * @return the number of hist item groups
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

                Query q = session.createQuery(_SQL_COUNT_HISTITEMGROUP);

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
     * Initializes the hist item group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistItemGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistItemGroup>> listenersList = new ArrayList<ModelListener<HistItemGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistItemGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistItemGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
