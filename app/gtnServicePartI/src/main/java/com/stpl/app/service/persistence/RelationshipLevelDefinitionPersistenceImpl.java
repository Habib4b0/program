package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRelationshipLevelDefinitionException;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.model.impl.RelationshipLevelDefinitionImpl;
import com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl;
import com.stpl.app.service.persistence.RelationshipLevelDefinitionPersistence;

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
 * The persistence implementation for the relationship level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinitionPersistence
 * @see RelationshipLevelDefinitionUtil
 * @generated
 */
public class RelationshipLevelDefinitionPersistenceImpl
    extends BasePersistenceImpl<RelationshipLevelDefinition>
    implements RelationshipLevelDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RelationshipLevelDefinitionUtil} to access the relationship level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RelationshipLevelDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
            RelationshipLevelDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
            RelationshipLevelDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_RELATIONSHIPLEVELDEFINITION = "SELECT relationshipLevelDefinition FROM RelationshipLevelDefinition relationshipLevelDefinition";
    private static final String _SQL_COUNT_RELATIONSHIPLEVELDEFINITION = "SELECT COUNT(relationshipLevelDefinition) FROM RelationshipLevelDefinition relationshipLevelDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "relationshipLevelDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RelationshipLevelDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RelationshipLevelDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "relationshipLevelValues", "hierarchyLevelDefinitionSid",
                "parentNode", "versionNo", "relationshipBuilderSid",
                "modifiedDate", "createdBy", "createdDate", "levelNo",
                "modifiedBy", "hierarchyNo", "relationshipLevelSid", "flag",
                "levelName", "parentHierarchyNo"
            });
    private static RelationshipLevelDefinition _nullRelationshipLevelDefinition = new RelationshipLevelDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RelationshipLevelDefinition> toCacheModel() {
                return _nullRelationshipLevelDefinitionCacheModel;
            }
        };

    private static CacheModel<RelationshipLevelDefinition> _nullRelationshipLevelDefinitionCacheModel =
        new CacheModel<RelationshipLevelDefinition>() {
            @Override
            public RelationshipLevelDefinition toEntityModel() {
                return _nullRelationshipLevelDefinition;
            }
        };

    public RelationshipLevelDefinitionPersistenceImpl() {
        setModelClass(RelationshipLevelDefinition.class);
    }

    /**
     * Caches the relationship level definition in the entity cache if it is enabled.
     *
     * @param relationshipLevelDefinition the relationship level definition
     */
    @Override
    public void cacheResult(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        EntityCacheUtil.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionImpl.class,
            relationshipLevelDefinition.getPrimaryKey(),
            relationshipLevelDefinition);

        relationshipLevelDefinition.resetOriginalValues();
    }

    /**
     * Caches the relationship level definitions in the entity cache if it is enabled.
     *
     * @param relationshipLevelDefinitions the relationship level definitions
     */
    @Override
    public void cacheResult(
        List<RelationshipLevelDefinition> relationshipLevelDefinitions) {
        for (RelationshipLevelDefinition relationshipLevelDefinition : relationshipLevelDefinitions) {
            if (EntityCacheUtil.getResult(
                        RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        RelationshipLevelDefinitionImpl.class,
                        relationshipLevelDefinition.getPrimaryKey()) == null) {
                cacheResult(relationshipLevelDefinition);
            } else {
                relationshipLevelDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all relationship level definitions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RelationshipLevelDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RelationshipLevelDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the relationship level definition.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        EntityCacheUtil.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionImpl.class,
            relationshipLevelDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<RelationshipLevelDefinition> relationshipLevelDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RelationshipLevelDefinition relationshipLevelDefinition : relationshipLevelDefinitions) {
            EntityCacheUtil.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                RelationshipLevelDefinitionImpl.class,
                relationshipLevelDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
     *
     * @param relationshipLevelSid the primary key for the new relationship level definition
     * @return the new relationship level definition
     */
    @Override
    public RelationshipLevelDefinition create(int relationshipLevelSid) {
        RelationshipLevelDefinition relationshipLevelDefinition = new RelationshipLevelDefinitionImpl();

        relationshipLevelDefinition.setNew(true);
        relationshipLevelDefinition.setPrimaryKey(relationshipLevelSid);

        return relationshipLevelDefinition;
    }

    /**
     * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param relationshipLevelSid the primary key of the relationship level definition
     * @return the relationship level definition that was removed
     * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition remove(int relationshipLevelSid)
        throws NoSuchRelationshipLevelDefinitionException, SystemException {
        return remove((Serializable) relationshipLevelSid);
    }

    /**
     * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the relationship level definition
     * @return the relationship level definition that was removed
     * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition remove(Serializable primaryKey)
        throws NoSuchRelationshipLevelDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RelationshipLevelDefinition relationshipLevelDefinition = (RelationshipLevelDefinition) session.get(RelationshipLevelDefinitionImpl.class,
                    primaryKey);

            if (relationshipLevelDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRelationshipLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(relationshipLevelDefinition);
        } catch (NoSuchRelationshipLevelDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RelationshipLevelDefinition removeImpl(
        RelationshipLevelDefinition relationshipLevelDefinition)
        throws SystemException {
        relationshipLevelDefinition = toUnwrappedModel(relationshipLevelDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(relationshipLevelDefinition)) {
                relationshipLevelDefinition = (RelationshipLevelDefinition) session.get(RelationshipLevelDefinitionImpl.class,
                        relationshipLevelDefinition.getPrimaryKeyObj());
            }

            if (relationshipLevelDefinition != null) {
                session.delete(relationshipLevelDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (relationshipLevelDefinition != null) {
            clearCache(relationshipLevelDefinition);
        }

        return relationshipLevelDefinition;
    }

    @Override
    public RelationshipLevelDefinition updateImpl(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws SystemException {
        relationshipLevelDefinition = toUnwrappedModel(relationshipLevelDefinition);

        boolean isNew = relationshipLevelDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (relationshipLevelDefinition.isNew()) {
                session.save(relationshipLevelDefinition);

                relationshipLevelDefinition.setNew(false);
            } else {
                session.merge(relationshipLevelDefinition);
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

        EntityCacheUtil.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipLevelDefinitionImpl.class,
            relationshipLevelDefinition.getPrimaryKey(),
            relationshipLevelDefinition);

        return relationshipLevelDefinition;
    }

    protected RelationshipLevelDefinition toUnwrappedModel(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        if (relationshipLevelDefinition instanceof RelationshipLevelDefinitionImpl) {
            return relationshipLevelDefinition;
        }

        RelationshipLevelDefinitionImpl relationshipLevelDefinitionImpl = new RelationshipLevelDefinitionImpl();

        relationshipLevelDefinitionImpl.setNew(relationshipLevelDefinition.isNew());
        relationshipLevelDefinitionImpl.setPrimaryKey(relationshipLevelDefinition.getPrimaryKey());

        relationshipLevelDefinitionImpl.setRelationshipLevelValues(relationshipLevelDefinition.getRelationshipLevelValues());
        relationshipLevelDefinitionImpl.setHierarchyLevelDefinitionSid(relationshipLevelDefinition.getHierarchyLevelDefinitionSid());
        relationshipLevelDefinitionImpl.setParentNode(relationshipLevelDefinition.getParentNode());
        relationshipLevelDefinitionImpl.setVersionNo(relationshipLevelDefinition.getVersionNo());
        relationshipLevelDefinitionImpl.setRelationshipBuilderSid(relationshipLevelDefinition.getRelationshipBuilderSid());
        relationshipLevelDefinitionImpl.setModifiedDate(relationshipLevelDefinition.getModifiedDate());
        relationshipLevelDefinitionImpl.setCreatedBy(relationshipLevelDefinition.getCreatedBy());
        relationshipLevelDefinitionImpl.setCreatedDate(relationshipLevelDefinition.getCreatedDate());
        relationshipLevelDefinitionImpl.setLevelNo(relationshipLevelDefinition.getLevelNo());
        relationshipLevelDefinitionImpl.setModifiedBy(relationshipLevelDefinition.getModifiedBy());
        relationshipLevelDefinitionImpl.setHierarchyNo(relationshipLevelDefinition.getHierarchyNo());
        relationshipLevelDefinitionImpl.setRelationshipLevelSid(relationshipLevelDefinition.getRelationshipLevelSid());
        relationshipLevelDefinitionImpl.setFlag(relationshipLevelDefinition.getFlag());
        relationshipLevelDefinitionImpl.setLevelName(relationshipLevelDefinition.getLevelName());
        relationshipLevelDefinitionImpl.setParentHierarchyNo(relationshipLevelDefinition.getParentHierarchyNo());

        return relationshipLevelDefinitionImpl;
    }

    /**
     * Returns the relationship level definition with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the relationship level definition
     * @return the relationship level definition
     * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRelationshipLevelDefinitionException, SystemException {
        RelationshipLevelDefinition relationshipLevelDefinition = fetchByPrimaryKey(primaryKey);

        if (relationshipLevelDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRelationshipLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return relationshipLevelDefinition;
    }

    /**
     * Returns the relationship level definition with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipLevelDefinitionException} if it could not be found.
     *
     * @param relationshipLevelSid the primary key of the relationship level definition
     * @return the relationship level definition
     * @throws com.stpl.app.NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition findByPrimaryKey(
        int relationshipLevelSid)
        throws NoSuchRelationshipLevelDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) relationshipLevelSid);
    }

    /**
     * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the relationship level definition
     * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        RelationshipLevelDefinition relationshipLevelDefinition = (RelationshipLevelDefinition) EntityCacheUtil.getResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                RelationshipLevelDefinitionImpl.class, primaryKey);

        if (relationshipLevelDefinition == _nullRelationshipLevelDefinition) {
            return null;
        }

        if (relationshipLevelDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                relationshipLevelDefinition = (RelationshipLevelDefinition) session.get(RelationshipLevelDefinitionImpl.class,
                        primaryKey);

                if (relationshipLevelDefinition != null) {
                    cacheResult(relationshipLevelDefinition);
                } else {
                    EntityCacheUtil.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        RelationshipLevelDefinitionImpl.class, primaryKey,
                        _nullRelationshipLevelDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    RelationshipLevelDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return relationshipLevelDefinition;
    }

    /**
     * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param relationshipLevelSid the primary key of the relationship level definition
     * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipLevelDefinition fetchByPrimaryKey(
        int relationshipLevelSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) relationshipLevelSid);
    }

    /**
     * Returns all the relationship level definitions.
     *
     * @return the relationship level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipLevelDefinition> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the relationship level definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of relationship level definitions
     * @param end the upper bound of the range of relationship level definitions (not inclusive)
     * @return the range of relationship level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipLevelDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the relationship level definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of relationship level definitions
     * @param end the upper bound of the range of relationship level definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of relationship level definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipLevelDefinition> findAll(int start, int end,
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

        List<RelationshipLevelDefinition> list = (List<RelationshipLevelDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RELATIONSHIPLEVELDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RELATIONSHIPLEVELDEFINITION;

                if (pagination) {
                    sql = sql.concat(RelationshipLevelDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RelationshipLevelDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RelationshipLevelDefinition>(list);
                } else {
                    list = (List<RelationshipLevelDefinition>) QueryUtil.list(q,
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
     * Removes all the relationship level definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RelationshipLevelDefinition relationshipLevelDefinition : findAll()) {
            remove(relationshipLevelDefinition);
        }
    }

    /**
     * Returns the number of relationship level definitions.
     *
     * @return the number of relationship level definitions
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

                Query q = session.createQuery(_SQL_COUNT_RELATIONSHIPLEVELDEFINITION);

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
     * Initializes the relationship level definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RelationshipLevelDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RelationshipLevelDefinition>> listenersList = new ArrayList<ModelListener<RelationshipLevelDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RelationshipLevelDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RelationshipLevelDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
