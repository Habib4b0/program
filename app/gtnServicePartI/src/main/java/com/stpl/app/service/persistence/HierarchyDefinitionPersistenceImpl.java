package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHierarchyDefinitionException;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.impl.HierarchyDefinitionImpl;
import com.stpl.app.model.impl.HierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.HierarchyDefinitionPersistence;

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
 * The persistence implementation for the hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyDefinitionPersistence
 * @see HierarchyDefinitionUtil
 * @generated
 */
public class HierarchyDefinitionPersistenceImpl extends BasePersistenceImpl<HierarchyDefinition>
    implements HierarchyDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HierarchyDefinitionUtil} to access the hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HierarchyDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HIERARCHYDEFINITION = "SELECT hierarchyDefinition FROM HierarchyDefinition hierarchyDefinition";
    private static final String _SQL_COUNT_HIERARCHYDEFINITION = "SELECT COUNT(hierarchyDefinition) FROM HierarchyDefinition hierarchyDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HierarchyDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "noOfLevels", "hierarchyType",
                "hierarchyName", "hierarchyDefinitionSid", "versionNo",
                "modifiedBy", "modifiedDate", "hierarchyCategory"
            });
    private static HierarchyDefinition _nullHierarchyDefinition = new HierarchyDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HierarchyDefinition> toCacheModel() {
                return _nullHierarchyDefinitionCacheModel;
            }
        };

    private static CacheModel<HierarchyDefinition> _nullHierarchyDefinitionCacheModel =
        new CacheModel<HierarchyDefinition>() {
            @Override
            public HierarchyDefinition toEntityModel() {
                return _nullHierarchyDefinition;
            }
        };

    public HierarchyDefinitionPersistenceImpl() {
        setModelClass(HierarchyDefinition.class);
    }

    /**
     * Caches the hierarchy definition in the entity cache if it is enabled.
     *
     * @param hierarchyDefinition the hierarchy definition
     */
    @Override
    public void cacheResult(HierarchyDefinition hierarchyDefinition) {
        EntityCacheUtil.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey(),
            hierarchyDefinition);

        hierarchyDefinition.resetOriginalValues();
    }

    /**
     * Caches the hierarchy definitions in the entity cache if it is enabled.
     *
     * @param hierarchyDefinitions the hierarchy definitions
     */
    @Override
    public void cacheResult(List<HierarchyDefinition> hierarchyDefinitions) {
        for (HierarchyDefinition hierarchyDefinition : hierarchyDefinitions) {
            if (EntityCacheUtil.getResult(
                        HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyDefinitionImpl.class,
                        hierarchyDefinition.getPrimaryKey()) == null) {
                cacheResult(hierarchyDefinition);
            } else {
                hierarchyDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hierarchy definitions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HierarchyDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HierarchyDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hierarchy definition.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HierarchyDefinition hierarchyDefinition) {
        EntityCacheUtil.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HierarchyDefinition> hierarchyDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HierarchyDefinition hierarchyDefinition : hierarchyDefinitions) {
            EntityCacheUtil.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyDefinitionImpl.class,
                hierarchyDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
     *
     * @param hierarchyDefinitionSid the primary key for the new hierarchy definition
     * @return the new hierarchy definition
     */
    @Override
    public HierarchyDefinition create(int hierarchyDefinitionSid) {
        HierarchyDefinition hierarchyDefinition = new HierarchyDefinitionImpl();

        hierarchyDefinition.setNew(true);
        hierarchyDefinition.setPrimaryKey(hierarchyDefinitionSid);

        return hierarchyDefinition;
    }

    /**
     * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param hierarchyDefinitionSid the primary key of the hierarchy definition
     * @return the hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition remove(int hierarchyDefinitionSid)
        throws NoSuchHierarchyDefinitionException, SystemException {
        return remove((Serializable) hierarchyDefinitionSid);
    }

    /**
     * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hierarchy definition
     * @return the hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition remove(Serializable primaryKey)
        throws NoSuchHierarchyDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HierarchyDefinition hierarchyDefinition = (HierarchyDefinition) session.get(HierarchyDefinitionImpl.class,
                    primaryKey);

            if (hierarchyDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(hierarchyDefinition);
        } catch (NoSuchHierarchyDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HierarchyDefinition removeImpl(
        HierarchyDefinition hierarchyDefinition) throws SystemException {
        hierarchyDefinition = toUnwrappedModel(hierarchyDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(hierarchyDefinition)) {
                hierarchyDefinition = (HierarchyDefinition) session.get(HierarchyDefinitionImpl.class,
                        hierarchyDefinition.getPrimaryKeyObj());
            }

            if (hierarchyDefinition != null) {
                session.delete(hierarchyDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (hierarchyDefinition != null) {
            clearCache(hierarchyDefinition);
        }

        return hierarchyDefinition;
    }

    @Override
    public HierarchyDefinition updateImpl(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws SystemException {
        hierarchyDefinition = toUnwrappedModel(hierarchyDefinition);

        boolean isNew = hierarchyDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (hierarchyDefinition.isNew()) {
                session.save(hierarchyDefinition);

                hierarchyDefinition.setNew(false);
            } else {
                session.merge(hierarchyDefinition);
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

        EntityCacheUtil.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey(),
            hierarchyDefinition);

        return hierarchyDefinition;
    }

    protected HierarchyDefinition toUnwrappedModel(
        HierarchyDefinition hierarchyDefinition) {
        if (hierarchyDefinition instanceof HierarchyDefinitionImpl) {
            return hierarchyDefinition;
        }

        HierarchyDefinitionImpl hierarchyDefinitionImpl = new HierarchyDefinitionImpl();

        hierarchyDefinitionImpl.setNew(hierarchyDefinition.isNew());
        hierarchyDefinitionImpl.setPrimaryKey(hierarchyDefinition.getPrimaryKey());

        hierarchyDefinitionImpl.setCreatedDate(hierarchyDefinition.getCreatedDate());
        hierarchyDefinitionImpl.setCreatedBy(hierarchyDefinition.getCreatedBy());
        hierarchyDefinitionImpl.setNoOfLevels(hierarchyDefinition.getNoOfLevels());
        hierarchyDefinitionImpl.setHierarchyType(hierarchyDefinition.getHierarchyType());
        hierarchyDefinitionImpl.setHierarchyName(hierarchyDefinition.getHierarchyName());
        hierarchyDefinitionImpl.setHierarchyDefinitionSid(hierarchyDefinition.getHierarchyDefinitionSid());
        hierarchyDefinitionImpl.setVersionNo(hierarchyDefinition.getVersionNo());
        hierarchyDefinitionImpl.setModifiedBy(hierarchyDefinition.getModifiedBy());
        hierarchyDefinitionImpl.setModifiedDate(hierarchyDefinition.getModifiedDate());
        hierarchyDefinitionImpl.setHierarchyCategory(hierarchyDefinition.getHierarchyCategory());

        return hierarchyDefinitionImpl;
    }

    /**
     * Returns the hierarchy definition with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy definition
     * @return the hierarchy definition
     * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHierarchyDefinitionException, SystemException {
        HierarchyDefinition hierarchyDefinition = fetchByPrimaryKey(primaryKey);

        if (hierarchyDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return hierarchyDefinition;
    }

    /**
     * Returns the hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyDefinitionException} if it could not be found.
     *
     * @param hierarchyDefinitionSid the primary key of the hierarchy definition
     * @return the hierarchy definition
     * @throws com.stpl.app.NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition findByPrimaryKey(int hierarchyDefinitionSid)
        throws NoSuchHierarchyDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) hierarchyDefinitionSid);
    }

    /**
     * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hierarchy definition
     * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HierarchyDefinition hierarchyDefinition = (HierarchyDefinition) EntityCacheUtil.getResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HierarchyDefinitionImpl.class, primaryKey);

        if (hierarchyDefinition == _nullHierarchyDefinition) {
            return null;
        }

        if (hierarchyDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                hierarchyDefinition = (HierarchyDefinition) session.get(HierarchyDefinitionImpl.class,
                        primaryKey);

                if (hierarchyDefinition != null) {
                    cacheResult(hierarchyDefinition);
                } else {
                    EntityCacheUtil.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HierarchyDefinitionImpl.class, primaryKey,
                        _nullHierarchyDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    HierarchyDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return hierarchyDefinition;
    }

    /**
     * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param hierarchyDefinitionSid the primary key of the hierarchy definition
     * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HierarchyDefinition fetchByPrimaryKey(int hierarchyDefinitionSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) hierarchyDefinitionSid);
    }

    /**
     * Returns all the hierarchy definitions.
     *
     * @return the hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy definitions
     * @param end the upper bound of the range of hierarchy definitions (not inclusive)
     * @return the range of hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hierarchy definitions
     * @param end the upper bound of the range of hierarchy definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HierarchyDefinition> findAll(int start, int end,
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

        List<HierarchyDefinition> list = (List<HierarchyDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HIERARCHYDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HIERARCHYDEFINITION;

                if (pagination) {
                    sql = sql.concat(HierarchyDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HierarchyDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HierarchyDefinition>(list);
                } else {
                    list = (List<HierarchyDefinition>) QueryUtil.list(q,
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
     * Removes all the hierarchy definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HierarchyDefinition hierarchyDefinition : findAll()) {
            remove(hierarchyDefinition);
        }
    }

    /**
     * Returns the number of hierarchy definitions.
     *
     * @return the number of hierarchy definitions
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

                Query q = session.createQuery(_SQL_COUNT_HIERARCHYDEFINITION);

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
     * Initializes the hierarchy definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HierarchyDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HierarchyDefinition>> listenersList = new ArrayList<ModelListener<HierarchyDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HierarchyDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HierarchyDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
