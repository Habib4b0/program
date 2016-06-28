package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRelationshipBuilderException;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.impl.RelationshipBuilderImpl;
import com.stpl.app.model.impl.RelationshipBuilderModelImpl;
import com.stpl.app.service.persistence.RelationshipBuilderPersistence;

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
 * The persistence implementation for the relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipBuilderPersistence
 * @see RelationshipBuilderUtil
 * @generated
 */
public class RelationshipBuilderPersistenceImpl extends BasePersistenceImpl<RelationshipBuilder>
    implements RelationshipBuilderPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RelationshipBuilderUtil} to access the relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RelationshipBuilderImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
            RelationshipBuilderImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
            RelationshipBuilderImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_RELATIONSHIPBUILDER = "SELECT relationshipBuilder FROM RelationshipBuilder relationshipBuilder";
    private static final String _SQL_COUNT_RELATIONSHIPBUILDER = "SELECT COUNT(relationshipBuilder) FROM RelationshipBuilder relationshipBuilder";
    private static final String _ORDER_BY_ENTITY_ALIAS = "relationshipBuilder.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RelationshipBuilder exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RelationshipBuilderPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "startDate", "createdDate", "createdBy",
                "relationshipDescription", "hierarchyDefinitionSid", "versionNo",
                "relationshipName", "relationshipBuilderSid", "hierarchyVersion",
                "modifiedBy", "modifiedDate", "relationshipType", "buildType"
            });
    private static RelationshipBuilder _nullRelationshipBuilder = new RelationshipBuilderImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RelationshipBuilder> toCacheModel() {
                return _nullRelationshipBuilderCacheModel;
            }
        };

    private static CacheModel<RelationshipBuilder> _nullRelationshipBuilderCacheModel =
        new CacheModel<RelationshipBuilder>() {
            @Override
            public RelationshipBuilder toEntityModel() {
                return _nullRelationshipBuilder;
            }
        };

    public RelationshipBuilderPersistenceImpl() {
        setModelClass(RelationshipBuilder.class);
    }

    /**
     * Caches the relationship builder in the entity cache if it is enabled.
     *
     * @param relationshipBuilder the relationship builder
     */
    @Override
    public void cacheResult(RelationshipBuilder relationshipBuilder) {
        EntityCacheUtil.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey(),
            relationshipBuilder);

        relationshipBuilder.resetOriginalValues();
    }

    /**
     * Caches the relationship builders in the entity cache if it is enabled.
     *
     * @param relationshipBuilders the relationship builders
     */
    @Override
    public void cacheResult(List<RelationshipBuilder> relationshipBuilders) {
        for (RelationshipBuilder relationshipBuilder : relationshipBuilders) {
            if (EntityCacheUtil.getResult(
                        RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                        RelationshipBuilderImpl.class,
                        relationshipBuilder.getPrimaryKey()) == null) {
                cacheResult(relationshipBuilder);
            } else {
                relationshipBuilder.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all relationship builders.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RelationshipBuilderImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RelationshipBuilderImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the relationship builder.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RelationshipBuilder relationshipBuilder) {
        EntityCacheUtil.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RelationshipBuilder> relationshipBuilders) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RelationshipBuilder relationshipBuilder : relationshipBuilders) {
            EntityCacheUtil.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                RelationshipBuilderImpl.class,
                relationshipBuilder.getPrimaryKey());
        }
    }

    /**
     * Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
     *
     * @param relationshipBuilderSid the primary key for the new relationship builder
     * @return the new relationship builder
     */
    @Override
    public RelationshipBuilder create(int relationshipBuilderSid) {
        RelationshipBuilder relationshipBuilder = new RelationshipBuilderImpl();

        relationshipBuilder.setNew(true);
        relationshipBuilder.setPrimaryKey(relationshipBuilderSid);

        return relationshipBuilder;
    }

    /**
     * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param relationshipBuilderSid the primary key of the relationship builder
     * @return the relationship builder that was removed
     * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder remove(int relationshipBuilderSid)
        throws NoSuchRelationshipBuilderException, SystemException {
        return remove((Serializable) relationshipBuilderSid);
    }

    /**
     * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the relationship builder
     * @return the relationship builder that was removed
     * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder remove(Serializable primaryKey)
        throws NoSuchRelationshipBuilderException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RelationshipBuilder relationshipBuilder = (RelationshipBuilder) session.get(RelationshipBuilderImpl.class,
                    primaryKey);

            if (relationshipBuilder == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(relationshipBuilder);
        } catch (NoSuchRelationshipBuilderException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RelationshipBuilder removeImpl(
        RelationshipBuilder relationshipBuilder) throws SystemException {
        relationshipBuilder = toUnwrappedModel(relationshipBuilder);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(relationshipBuilder)) {
                relationshipBuilder = (RelationshipBuilder) session.get(RelationshipBuilderImpl.class,
                        relationshipBuilder.getPrimaryKeyObj());
            }

            if (relationshipBuilder != null) {
                session.delete(relationshipBuilder);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (relationshipBuilder != null) {
            clearCache(relationshipBuilder);
        }

        return relationshipBuilder;
    }

    @Override
    public RelationshipBuilder updateImpl(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws SystemException {
        relationshipBuilder = toUnwrappedModel(relationshipBuilder);

        boolean isNew = relationshipBuilder.isNew();

        Session session = null;

        try {
            session = openSession();

            if (relationshipBuilder.isNew()) {
                session.save(relationshipBuilder);

                relationshipBuilder.setNew(false);
            } else {
                session.merge(relationshipBuilder);
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

        EntityCacheUtil.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey(),
            relationshipBuilder);

        return relationshipBuilder;
    }

    protected RelationshipBuilder toUnwrappedModel(
        RelationshipBuilder relationshipBuilder) {
        if (relationshipBuilder instanceof RelationshipBuilderImpl) {
            return relationshipBuilder;
        }

        RelationshipBuilderImpl relationshipBuilderImpl = new RelationshipBuilderImpl();

        relationshipBuilderImpl.setNew(relationshipBuilder.isNew());
        relationshipBuilderImpl.setPrimaryKey(relationshipBuilder.getPrimaryKey());

        relationshipBuilderImpl.setStartDate(relationshipBuilder.getStartDate());
        relationshipBuilderImpl.setCreatedDate(relationshipBuilder.getCreatedDate());
        relationshipBuilderImpl.setCreatedBy(relationshipBuilder.getCreatedBy());
        relationshipBuilderImpl.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
        relationshipBuilderImpl.setHierarchyDefinitionSid(relationshipBuilder.getHierarchyDefinitionSid());
        relationshipBuilderImpl.setVersionNo(relationshipBuilder.getVersionNo());
        relationshipBuilderImpl.setRelationshipName(relationshipBuilder.getRelationshipName());
        relationshipBuilderImpl.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
        relationshipBuilderImpl.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
        relationshipBuilderImpl.setModifiedBy(relationshipBuilder.getModifiedBy());
        relationshipBuilderImpl.setModifiedDate(relationshipBuilder.getModifiedDate());
        relationshipBuilderImpl.setRelationshipType(relationshipBuilder.getRelationshipType());
        relationshipBuilderImpl.setBuildType(relationshipBuilder.getBuildType());

        return relationshipBuilderImpl;
    }

    /**
     * Returns the relationship builder with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the relationship builder
     * @return the relationship builder
     * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRelationshipBuilderException, SystemException {
        RelationshipBuilder relationshipBuilder = fetchByPrimaryKey(primaryKey);

        if (relationshipBuilder == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return relationshipBuilder;
    }

    /**
     * Returns the relationship builder with the primary key or throws a {@link com.stpl.app.NoSuchRelationshipBuilderException} if it could not be found.
     *
     * @param relationshipBuilderSid the primary key of the relationship builder
     * @return the relationship builder
     * @throws com.stpl.app.NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder findByPrimaryKey(int relationshipBuilderSid)
        throws NoSuchRelationshipBuilderException, SystemException {
        return findByPrimaryKey((Serializable) relationshipBuilderSid);
    }

    /**
     * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the relationship builder
     * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RelationshipBuilder relationshipBuilder = (RelationshipBuilder) EntityCacheUtil.getResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                RelationshipBuilderImpl.class, primaryKey);

        if (relationshipBuilder == _nullRelationshipBuilder) {
            return null;
        }

        if (relationshipBuilder == null) {
            Session session = null;

            try {
                session = openSession();

                relationshipBuilder = (RelationshipBuilder) session.get(RelationshipBuilderImpl.class,
                        primaryKey);

                if (relationshipBuilder != null) {
                    cacheResult(relationshipBuilder);
                } else {
                    EntityCacheUtil.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                        RelationshipBuilderImpl.class, primaryKey,
                        _nullRelationshipBuilder);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                    RelationshipBuilderImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return relationshipBuilder;
    }

    /**
     * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param relationshipBuilderSid the primary key of the relationship builder
     * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RelationshipBuilder fetchByPrimaryKey(int relationshipBuilderSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) relationshipBuilderSid);
    }

    /**
     * Returns all the relationship builders.
     *
     * @return the relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipBuilder> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the relationship builders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of relationship builders
     * @param end the upper bound of the range of relationship builders (not inclusive)
     * @return the range of relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipBuilder> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the relationship builders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of relationship builders
     * @param end the upper bound of the range of relationship builders (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RelationshipBuilder> findAll(int start, int end,
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

        List<RelationshipBuilder> list = (List<RelationshipBuilder>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_RELATIONSHIPBUILDER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_RELATIONSHIPBUILDER;

                if (pagination) {
                    sql = sql.concat(RelationshipBuilderModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RelationshipBuilder>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RelationshipBuilder>(list);
                } else {
                    list = (List<RelationshipBuilder>) QueryUtil.list(q,
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
     * Removes all the relationship builders from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RelationshipBuilder relationshipBuilder : findAll()) {
            remove(relationshipBuilder);
        }
    }

    /**
     * Returns the number of relationship builders.
     *
     * @return the number of relationship builders
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

                Query q = session.createQuery(_SQL_COUNT_RELATIONSHIPBUILDER);

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
     * Initializes the relationship builder persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RelationshipBuilder")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RelationshipBuilder>> listenersList = new ArrayList<ModelListener<RelationshipBuilder>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RelationshipBuilder>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RelationshipBuilderImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
