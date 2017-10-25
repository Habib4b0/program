package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHierarchyLevelValuesException;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.impl.HierarchyLevelValuesImpl;
import com.stpl.app.model.impl.HierarchyLevelValuesModelImpl;
import com.stpl.app.service.persistence.HierarchyLevelValuesPersistence;

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
 * The persistence implementation for the hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelValuesPersistence
 * @see HierarchyLevelValuesUtil
 * @generated
 */
public class HierarchyLevelValuesPersistenceImpl extends BasePersistenceImpl<HierarchyLevelValues>
    implements HierarchyLevelValuesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HierarchyLevelValuesUtil} to access the hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HierarchyLevelValuesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            HierarchyLevelValuesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            HierarchyLevelValuesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HIERARCHYLEVELVALUES = "SELECT hierarchyLevelValues FROM HierarchyLevelValues hierarchyLevelValues";
    private static final String _SQL_COUNT_HIERARCHYLEVELVALUES = "SELECT COUNT(hierarchyLevelValues) FROM HierarchyLevelValues hierarchyLevelValues";
    private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyLevelValues.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyLevelValues exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HierarchyLevelValuesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "levelValues", "hierarchyLevelValuesSid", "createdDate",
                "createdBy", "hierarchyLevelDefinitionSid", "versionNo",
                "modifiedBy", "modifiedDate"
            });
    private static HierarchyLevelValues _nullHierarchyLevelValues = new HierarchyLevelValuesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HierarchyLevelValues> toCacheModel() {
                return _nullHierarchyLevelValuesCacheModel;
            }
        };

    private static CacheModel<HierarchyLevelValues> _nullHierarchyLevelValuesCacheModel =
        new CacheModel<HierarchyLevelValues>() {
            @Override
            public HierarchyLevelValues toEntityModel() {
                return _nullHierarchyLevelValues;
            }
        };

    public HierarchyLevelValuesPersistenceImpl() {
        setModelClass(HierarchyLevelValues.class);
    }

    /**
     * Caches the hierarchy level values in the entity cache if it is enabled.
     *
     * @param hierarchyLevelValues the hierarchy level values
     */
    @Override
    public void cacheResult(HierarchyLevelValues hierarchyLevelValues) {
        EntityCacheUtil.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesImpl.class,
            hierarchyLevelValues.getPrimaryKey(), hierarchyLevelValues);

        hierarchyLevelValues.resetOriginalValues();
    }

    /**
     * Caches the hierarchy level valueses in the entity cache if it is enabled.
     *
     * @param hierarchyLevelValueses the hierarchy level valueses
     */
    @Override
    public void cacheResult(List<HierarchyLevelValues> hierarchyLevelValueses) {
        for (HierarchyLevelValues hierarchyLevelValues : hierarchyLevelValueses) {
            if (EntityCacheUtil.getResult(
                        HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyLevelValuesImpl.class,
                        hierarchyLevelValues.getPrimaryKey()) == null) {
                cacheResult(hierarchyLevelValues);
            } else {
                hierarchyLevelValues.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hierarchy level valueses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HierarchyLevelValuesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HierarchyLevelValuesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hierarchy level values.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HierarchyLevelValues hierarchyLevelValues) {
        EntityCacheUtil.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesImpl.class, hierarchyLevelValues.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HierarchyLevelValues> hierarchyLevelValueses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HierarchyLevelValues hierarchyLevelValues : hierarchyLevelValueses) {
            EntityCacheUtil.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyLevelValuesImpl.class,
                hierarchyLevelValues.getPrimaryKey());
        }
    }

    /**
     * Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
     *
     * @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
     * @return the new hierarchy level values
     */
    @Override
    public HierarchyLevelValues create(int hierarchyLevelValuesSid) {
        HierarchyLevelValues hierarchyLevelValues = new HierarchyLevelValuesImpl();

        hierarchyLevelValues.setNew(true);
        hierarchyLevelValues.setPrimaryKey(hierarchyLevelValuesSid);

        return hierarchyLevelValues;
    }

    /**
     * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
     * @return the hierarchy level values that was removed
     * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues remove(int hierarchyLevelValuesSid)
        throws NoSuchHierarchyLevelValuesException, SystemException {
        return remove((Serializable) hierarchyLevelValuesSid);
    }

    /**
     * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hierarchy level values
     * @return the hierarchy level values that was removed
     * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues remove(Serializable primaryKey)
        throws NoSuchHierarchyLevelValuesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HierarchyLevelValues hierarchyLevelValues = (HierarchyLevelValues) session.get(HierarchyLevelValuesImpl.class,
                    primaryKey);

            if (hierarchyLevelValues == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(hierarchyLevelValues);
        } catch (NoSuchHierarchyLevelValuesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HierarchyLevelValues removeImpl(
        HierarchyLevelValues hierarchyLevelValues) throws SystemException {
        hierarchyLevelValues = toUnwrappedModel(hierarchyLevelValues);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(hierarchyLevelValues)) {
                hierarchyLevelValues = (HierarchyLevelValues) session.get(HierarchyLevelValuesImpl.class,
                        hierarchyLevelValues.getPrimaryKeyObj());
            }

            if (hierarchyLevelValues != null) {
                session.delete(hierarchyLevelValues);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (hierarchyLevelValues != null) {
            clearCache(hierarchyLevelValues);
        }

        return hierarchyLevelValues;
    }

    @Override
    public HierarchyLevelValues updateImpl(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws SystemException {
        hierarchyLevelValues = toUnwrappedModel(hierarchyLevelValues);

        boolean isNew = hierarchyLevelValues.isNew();

        Session session = null;

        try {
            session = openSession();

            if (hierarchyLevelValues.isNew()) {
                session.save(hierarchyLevelValues);

                hierarchyLevelValues.setNew(false);
            } else {
                session.merge(hierarchyLevelValues);
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

        EntityCacheUtil.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelValuesImpl.class,
            hierarchyLevelValues.getPrimaryKey(), hierarchyLevelValues);

        return hierarchyLevelValues;
    }

    protected HierarchyLevelValues toUnwrappedModel(
        HierarchyLevelValues hierarchyLevelValues) {
        if (hierarchyLevelValues instanceof HierarchyLevelValuesImpl) {
            return hierarchyLevelValues;
        }

        HierarchyLevelValuesImpl hierarchyLevelValuesImpl = new HierarchyLevelValuesImpl();

        hierarchyLevelValuesImpl.setNew(hierarchyLevelValues.isNew());
        hierarchyLevelValuesImpl.setPrimaryKey(hierarchyLevelValues.getPrimaryKey());

        hierarchyLevelValuesImpl.setLevelValues(hierarchyLevelValues.getLevelValues());
        hierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValues.getHierarchyLevelValuesSid());
        hierarchyLevelValuesImpl.setCreatedDate(hierarchyLevelValues.getCreatedDate());
        hierarchyLevelValuesImpl.setCreatedBy(hierarchyLevelValues.getCreatedBy());
        hierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelValues.getHierarchyLevelDefinitionSid());
        hierarchyLevelValuesImpl.setVersionNo(hierarchyLevelValues.getVersionNo());
        hierarchyLevelValuesImpl.setModifiedBy(hierarchyLevelValues.getModifiedBy());
        hierarchyLevelValuesImpl.setModifiedDate(hierarchyLevelValues.getModifiedDate());

        return hierarchyLevelValuesImpl;
    }

    /**
     * Returns the hierarchy level values with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy level values
     * @return the hierarchy level values
     * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHierarchyLevelValuesException, SystemException {
        HierarchyLevelValues hierarchyLevelValues = fetchByPrimaryKey(primaryKey);

        if (hierarchyLevelValues == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return hierarchyLevelValues;
    }

    /**
     * Returns the hierarchy level values with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelValuesException} if it could not be found.
     *
     * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
     * @return the hierarchy level values
     * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues findByPrimaryKey(int hierarchyLevelValuesSid)
        throws NoSuchHierarchyLevelValuesException, SystemException {
        return findByPrimaryKey((Serializable) hierarchyLevelValuesSid);
    }

    /**
     * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy level values
     * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HierarchyLevelValues hierarchyLevelValues = (HierarchyLevelValues) EntityCacheUtil.getResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyLevelValuesImpl.class, primaryKey);

        if (hierarchyLevelValues == _nullHierarchyLevelValues) {
            return null;
        }

        if (hierarchyLevelValues == null) {
            Session session = null;

            try {
                session = openSession();

                hierarchyLevelValues = (HierarchyLevelValues) session.get(HierarchyLevelValuesImpl.class,
                        primaryKey);

                if (hierarchyLevelValues != null) {
                    cacheResult(hierarchyLevelValues);
                } else {
                    EntityCacheUtil.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyLevelValuesImpl.class, primaryKey,
                        _nullHierarchyLevelValues);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                    HierarchyLevelValuesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return hierarchyLevelValues;
    }

    /**
     * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
     * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelValues fetchByPrimaryKey(int hierarchyLevelValuesSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) hierarchyLevelValuesSid);
    }

    /**
     * Returns all the hierarchy level valueses.
     *
     * @return the hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelValues> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hierarchy level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy level valueses
     * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
     * @return the range of hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelValues> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hierarchy level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy level valueses
     * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelValues> findAll(int start, int end,
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

        List<HierarchyLevelValues> list = (List<HierarchyLevelValues>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HIERARCHYLEVELVALUES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HIERARCHYLEVELVALUES;

                if (pagination) {
                    sql = sql.concat(HierarchyLevelValuesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HierarchyLevelValues>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HierarchyLevelValues>(list);
                } else {
                    list = (List<HierarchyLevelValues>) QueryUtil.list(q,
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
     * Removes all the hierarchy level valueses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HierarchyLevelValues hierarchyLevelValues : findAll()) {
            remove(hierarchyLevelValues);
        }
    }

    /**
     * Returns the number of hierarchy level valueses.
     *
     * @return the number of hierarchy level valueses
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

                Query q = session.createQuery(_SQL_COUNT_HIERARCHYLEVELVALUES);

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
     * Initializes the hierarchy level values persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HierarchyLevelValues")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HierarchyLevelValues>> listenersList = new ArrayList<ModelListener<HierarchyLevelValues>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HierarchyLevelValues>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HierarchyLevelValuesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
