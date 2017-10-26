package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHierarchyLevelDefinitionException;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.impl.HierarchyLevelDefinitionImpl;
import com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl;
import com.stpl.app.service.persistence.HierarchyLevelDefinitionPersistence;

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
 * The persistence implementation for the hierarchy level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinitionPersistence
 * @see HierarchyLevelDefinitionUtil
 * @generated
 */
public class HierarchyLevelDefinitionPersistenceImpl extends BasePersistenceImpl<HierarchyLevelDefinition>
    implements HierarchyLevelDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HierarchyLevelDefinitionUtil} to access the hierarchy level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HierarchyLevelDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HierarchyLevelDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HierarchyLevelDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HIERARCHYLEVELDEFINITION = "SELECT hierarchyLevelDefinition FROM HierarchyLevelDefinition hierarchyLevelDefinition";
    private static final String _SQL_COUNT_HIERARCHYLEVELDEFINITION = "SELECT COUNT(hierarchyLevelDefinition) FROM HierarchyLevelDefinition hierarchyLevelDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyLevelDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyLevelDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HierarchyLevelDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "tableName", "createdDate", "createdBy", "levelValueReference",
                "fieldName", "levelNo", "hierarchyLevelDefinitionSid",
                "hierarchyDefinitionSid", "versionNo", "modifiedBy",
                "modifiedDate", "levelName"
            });
    private static HierarchyLevelDefinition _nullHierarchyLevelDefinition = new HierarchyLevelDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HierarchyLevelDefinition> toCacheModel() {
                return _nullHierarchyLevelDefinitionCacheModel;
            }
        };

    private static CacheModel<HierarchyLevelDefinition> _nullHierarchyLevelDefinitionCacheModel =
        new CacheModel<HierarchyLevelDefinition>() {
            @Override
            public HierarchyLevelDefinition toEntityModel() {
                return _nullHierarchyLevelDefinition;
            }
        };

    public HierarchyLevelDefinitionPersistenceImpl() {
        setModelClass(HierarchyLevelDefinition.class);
    }

    /**
     * Caches the hierarchy level definition in the entity cache if it is enabled.
     *
     * @param hierarchyLevelDefinition the hierarchy level definition
     */
    @Override
    public void cacheResult(HierarchyLevelDefinition hierarchyLevelDefinition) {
        EntityCacheUtil.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionImpl.class,
            hierarchyLevelDefinition.getPrimaryKey(), hierarchyLevelDefinition);

        hierarchyLevelDefinition.resetOriginalValues();
    }

    /**
     * Caches the hierarchy level definitions in the entity cache if it is enabled.
     *
     * @param hierarchyLevelDefinitions the hierarchy level definitions
     */
    @Override
    public void cacheResult(
        List<HierarchyLevelDefinition> hierarchyLevelDefinitions) {
        for (HierarchyLevelDefinition hierarchyLevelDefinition : hierarchyLevelDefinitions) {
            if (EntityCacheUtil.getResult(
                        HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyLevelDefinitionImpl.class,
                        hierarchyLevelDefinition.getPrimaryKey()) == null) {
                cacheResult(hierarchyLevelDefinition);
            } else {
                hierarchyLevelDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hierarchy level definitions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HierarchyLevelDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HierarchyLevelDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hierarchy level definition.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HierarchyLevelDefinition hierarchyLevelDefinition) {
        EntityCacheUtil.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionImpl.class,
            hierarchyLevelDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HierarchyLevelDefinition> hierarchyLevelDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HierarchyLevelDefinition hierarchyLevelDefinition : hierarchyLevelDefinitions) {
            EntityCacheUtil.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyLevelDefinitionImpl.class,
                hierarchyLevelDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
     *
     * @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
     * @return the new hierarchy level definition
     */
    @Override
    public HierarchyLevelDefinition create(int hierarchyLevelDefinitionSid) {
        HierarchyLevelDefinition hierarchyLevelDefinition = new HierarchyLevelDefinitionImpl();

        hierarchyLevelDefinition.setNew(true);
        hierarchyLevelDefinition.setPrimaryKey(hierarchyLevelDefinitionSid);

        return hierarchyLevelDefinition;
    }

    /**
     * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
     * @return the hierarchy level definition that was removed
     * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition remove(int hierarchyLevelDefinitionSid)
        throws NoSuchHierarchyLevelDefinitionException, SystemException {
        return remove((Serializable) hierarchyLevelDefinitionSid);
    }

    /**
     * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hierarchy level definition
     * @return the hierarchy level definition that was removed
     * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition remove(Serializable primaryKey)
        throws NoSuchHierarchyLevelDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HierarchyLevelDefinition hierarchyLevelDefinition = (HierarchyLevelDefinition) session.get(HierarchyLevelDefinitionImpl.class,
                    primaryKey);

            if (hierarchyLevelDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHierarchyLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(hierarchyLevelDefinition);
        } catch (NoSuchHierarchyLevelDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HierarchyLevelDefinition removeImpl(
        HierarchyLevelDefinition hierarchyLevelDefinition)
        throws SystemException {
        hierarchyLevelDefinition = toUnwrappedModel(hierarchyLevelDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(hierarchyLevelDefinition)) {
                hierarchyLevelDefinition = (HierarchyLevelDefinition) session.get(HierarchyLevelDefinitionImpl.class,
                        hierarchyLevelDefinition.getPrimaryKeyObj());
            }

            if (hierarchyLevelDefinition != null) {
                session.delete(hierarchyLevelDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (hierarchyLevelDefinition != null) {
            clearCache(hierarchyLevelDefinition);
        }

        return hierarchyLevelDefinition;
    }

    @Override
    public HierarchyLevelDefinition updateImpl(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws SystemException {
        hierarchyLevelDefinition = toUnwrappedModel(hierarchyLevelDefinition);

        boolean isNew = hierarchyLevelDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (hierarchyLevelDefinition.isNew()) {
                session.save(hierarchyLevelDefinition);

                hierarchyLevelDefinition.setNew(false);
            } else {
                session.merge(hierarchyLevelDefinition);
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

        EntityCacheUtil.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyLevelDefinitionImpl.class,
            hierarchyLevelDefinition.getPrimaryKey(), hierarchyLevelDefinition);

        return hierarchyLevelDefinition;
    }

    protected HierarchyLevelDefinition toUnwrappedModel(
        HierarchyLevelDefinition hierarchyLevelDefinition) {
        if (hierarchyLevelDefinition instanceof HierarchyLevelDefinitionImpl) {
            return hierarchyLevelDefinition;
        }

        HierarchyLevelDefinitionImpl hierarchyLevelDefinitionImpl = new HierarchyLevelDefinitionImpl();

        hierarchyLevelDefinitionImpl.setNew(hierarchyLevelDefinition.isNew());
        hierarchyLevelDefinitionImpl.setPrimaryKey(hierarchyLevelDefinition.getPrimaryKey());

        hierarchyLevelDefinitionImpl.setTableName(hierarchyLevelDefinition.getTableName());
        hierarchyLevelDefinitionImpl.setCreatedDate(hierarchyLevelDefinition.getCreatedDate());
        hierarchyLevelDefinitionImpl.setCreatedBy(hierarchyLevelDefinition.getCreatedBy());
        hierarchyLevelDefinitionImpl.setLevelValueReference(hierarchyLevelDefinition.getLevelValueReference());
        hierarchyLevelDefinitionImpl.setFieldName(hierarchyLevelDefinition.getFieldName());
        hierarchyLevelDefinitionImpl.setLevelNo(hierarchyLevelDefinition.getLevelNo());
        hierarchyLevelDefinitionImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
        hierarchyLevelDefinitionImpl.setHierarchyDefinitionSid(hierarchyLevelDefinition.getHierarchyDefinitionSid());
        hierarchyLevelDefinitionImpl.setVersionNo(hierarchyLevelDefinition.getVersionNo());
        hierarchyLevelDefinitionImpl.setModifiedBy(hierarchyLevelDefinition.getModifiedBy());
        hierarchyLevelDefinitionImpl.setModifiedDate(hierarchyLevelDefinition.getModifiedDate());
        hierarchyLevelDefinitionImpl.setLevelName(hierarchyLevelDefinition.getLevelName());

        return hierarchyLevelDefinitionImpl;
    }

    /**
     * Returns the hierarchy level definition with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy level definition
     * @return the hierarchy level definition
     * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHierarchyLevelDefinitionException, SystemException {
        HierarchyLevelDefinition hierarchyLevelDefinition = fetchByPrimaryKey(primaryKey);

        if (hierarchyLevelDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHierarchyLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return hierarchyLevelDefinition;
    }

    /**
     * Returns the hierarchy level definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelDefinitionException} if it could not be found.
     *
     * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
     * @return the hierarchy level definition
     * @throws com.stpl.app.NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition findByPrimaryKey(
        int hierarchyLevelDefinitionSid)
        throws NoSuchHierarchyLevelDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) hierarchyLevelDefinitionSid);
    }

    /**
     * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy level definition
     * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HierarchyLevelDefinition hierarchyLevelDefinition = (HierarchyLevelDefinition) EntityCacheUtil.getResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyLevelDefinitionImpl.class, primaryKey);

        if (hierarchyLevelDefinition == _nullHierarchyLevelDefinition) {
            return null;
        }

        if (hierarchyLevelDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                hierarchyLevelDefinition = (HierarchyLevelDefinition) session.get(HierarchyLevelDefinitionImpl.class,
                        primaryKey);

                if (hierarchyLevelDefinition != null) {
                    cacheResult(hierarchyLevelDefinition);
                } else {
                    EntityCacheUtil.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyLevelDefinitionImpl.class, primaryKey,
                        _nullHierarchyLevelDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    HierarchyLevelDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return hierarchyLevelDefinition;
    }

    /**
     * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
     * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyLevelDefinition fetchByPrimaryKey(
        int hierarchyLevelDefinitionSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) hierarchyLevelDefinitionSid);
    }

    /**
     * Returns all the hierarchy level definitions.
     *
     * @return the hierarchy level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hierarchy level definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy level definitions
     * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
     * @return the range of hierarchy level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hierarchy level definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy level definitions
     * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hierarchy level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyLevelDefinition> findAll(int start, int end,
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

        List<HierarchyLevelDefinition> list = (List<HierarchyLevelDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HIERARCHYLEVELDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HIERARCHYLEVELDEFINITION;

                if (pagination) {
                    sql = sql.concat(HierarchyLevelDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HierarchyLevelDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HierarchyLevelDefinition>(list);
                } else {
                    list = (List<HierarchyLevelDefinition>) QueryUtil.list(q,
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
     * Removes all the hierarchy level definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HierarchyLevelDefinition hierarchyLevelDefinition : findAll()) {
            remove(hierarchyLevelDefinition);
        }
    }

    /**
     * Returns the number of hierarchy level definitions.
     *
     * @return the number of hierarchy level definitions
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

                Query q = session.createQuery(_SQL_COUNT_HIERARCHYLEVELDEFINITION);

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
     * Initializes the hierarchy level definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HierarchyLevelDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HierarchyLevelDefinition>> listenersList = new ArrayList<ModelListener<HierarchyLevelDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HierarchyLevelDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HierarchyLevelDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
