package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistRelationshipBuilderException;
import com.stpl.app.model.HistRelationshipBuilder;
import com.stpl.app.model.impl.HistRelationshipBuilderImpl;
import com.stpl.app.model.impl.HistRelationshipBuilderModelImpl;
import com.stpl.app.service.persistence.HistRelationshipBuilderPersistence;

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
 * The persistence implementation for the hist relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipBuilderPersistence
 * @see HistRelationshipBuilderUtil
 * @generated
 */
public class HistRelationshipBuilderPersistenceImpl extends BasePersistenceImpl<HistRelationshipBuilder>
    implements HistRelationshipBuilderPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistRelationshipBuilderUtil} to access the hist relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistRelationshipBuilderImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
            HistRelationshipBuilderImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
            HistRelationshipBuilderImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTRELATIONSHIPBUILDER = "SELECT histRelationshipBuilder FROM HistRelationshipBuilder histRelationshipBuilder";
    private static final String _SQL_COUNT_HISTRELATIONSHIPBUILDER = "SELECT COUNT(histRelationshipBuilder) FROM HistRelationshipBuilder histRelationshipBuilder";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histRelationshipBuilder.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistRelationshipBuilder exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistRelationshipBuilderPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "startDate", "createdDate", "createdBy",
                "relationshipDescription", "actionDate", "actionFlag",
                "hierarchyDefinitionSid", "versionNo", "relationshipName",
                "relationshipBuilderSid", "hierarchyVersion", "modifiedBy",
                "modifiedDate", "relationshipType", "buildType"
            });
    private static HistRelationshipBuilder _nullHistRelationshipBuilder = new HistRelationshipBuilderImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistRelationshipBuilder> toCacheModel() {
                return _nullHistRelationshipBuilderCacheModel;
            }
        };

    private static CacheModel<HistRelationshipBuilder> _nullHistRelationshipBuilderCacheModel =
        new CacheModel<HistRelationshipBuilder>() {
            @Override
            public HistRelationshipBuilder toEntityModel() {
                return _nullHistRelationshipBuilder;
            }
        };

    public HistRelationshipBuilderPersistenceImpl() {
        setModelClass(HistRelationshipBuilder.class);
    }

    /**
     * Caches the hist relationship builder in the entity cache if it is enabled.
     *
     * @param histRelationshipBuilder the hist relationship builder
     */
    @Override
    public void cacheResult(HistRelationshipBuilder histRelationshipBuilder) {
        EntityCacheUtil.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderImpl.class,
            histRelationshipBuilder.getPrimaryKey(), histRelationshipBuilder);

        histRelationshipBuilder.resetOriginalValues();
    }

    /**
     * Caches the hist relationship builders in the entity cache if it is enabled.
     *
     * @param histRelationshipBuilders the hist relationship builders
     */
    @Override
    public void cacheResult(
        List<HistRelationshipBuilder> histRelationshipBuilders) {
        for (HistRelationshipBuilder histRelationshipBuilder : histRelationshipBuilders) {
            if (EntityCacheUtil.getResult(
                        HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                        HistRelationshipBuilderImpl.class,
                        histRelationshipBuilder.getPrimaryKey()) == null) {
                cacheResult(histRelationshipBuilder);
            } else {
                histRelationshipBuilder.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist relationship builders.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistRelationshipBuilderImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistRelationshipBuilderImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist relationship builder.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistRelationshipBuilder histRelationshipBuilder) {
        EntityCacheUtil.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderImpl.class,
            histRelationshipBuilder.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HistRelationshipBuilder> histRelationshipBuilders) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistRelationshipBuilder histRelationshipBuilder : histRelationshipBuilders) {
            EntityCacheUtil.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                HistRelationshipBuilderImpl.class,
                histRelationshipBuilder.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist relationship builder with the primary key. Does not add the hist relationship builder to the database.
     *
     * @param histRelationshipBuilderPK the primary key for the new hist relationship builder
     * @return the new hist relationship builder
     */
    @Override
    public HistRelationshipBuilder create(
        HistRelationshipBuilderPK histRelationshipBuilderPK) {
        HistRelationshipBuilder histRelationshipBuilder = new HistRelationshipBuilderImpl();

        histRelationshipBuilder.setNew(true);
        histRelationshipBuilder.setPrimaryKey(histRelationshipBuilderPK);

        return histRelationshipBuilder;
    }

    /**
     * Removes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histRelationshipBuilderPK the primary key of the hist relationship builder
     * @return the hist relationship builder that was removed
     * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder remove(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws NoSuchHistRelationshipBuilderException, SystemException {
        return remove((Serializable) histRelationshipBuilderPK);
    }

    /**
     * Removes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist relationship builder
     * @return the hist relationship builder that was removed
     * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder remove(Serializable primaryKey)
        throws NoSuchHistRelationshipBuilderException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistRelationshipBuilder histRelationshipBuilder = (HistRelationshipBuilder) session.get(HistRelationshipBuilderImpl.class,
                    primaryKey);

            if (histRelationshipBuilder == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histRelationshipBuilder);
        } catch (NoSuchHistRelationshipBuilderException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistRelationshipBuilder removeImpl(
        HistRelationshipBuilder histRelationshipBuilder)
        throws SystemException {
        histRelationshipBuilder = toUnwrappedModel(histRelationshipBuilder);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histRelationshipBuilder)) {
                histRelationshipBuilder = (HistRelationshipBuilder) session.get(HistRelationshipBuilderImpl.class,
                        histRelationshipBuilder.getPrimaryKeyObj());
            }

            if (histRelationshipBuilder != null) {
                session.delete(histRelationshipBuilder);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histRelationshipBuilder != null) {
            clearCache(histRelationshipBuilder);
        }

        return histRelationshipBuilder;
    }

    @Override
    public HistRelationshipBuilder updateImpl(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder)
        throws SystemException {
        histRelationshipBuilder = toUnwrappedModel(histRelationshipBuilder);

        boolean isNew = histRelationshipBuilder.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histRelationshipBuilder.isNew()) {
                session.save(histRelationshipBuilder);

                histRelationshipBuilder.setNew(false);
            } else {
                session.merge(histRelationshipBuilder);
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

        EntityCacheUtil.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipBuilderImpl.class,
            histRelationshipBuilder.getPrimaryKey(), histRelationshipBuilder);

        return histRelationshipBuilder;
    }

    protected HistRelationshipBuilder toUnwrappedModel(
        HistRelationshipBuilder histRelationshipBuilder) {
        if (histRelationshipBuilder instanceof HistRelationshipBuilderImpl) {
            return histRelationshipBuilder;
        }

        HistRelationshipBuilderImpl histRelationshipBuilderImpl = new HistRelationshipBuilderImpl();

        histRelationshipBuilderImpl.setNew(histRelationshipBuilder.isNew());
        histRelationshipBuilderImpl.setPrimaryKey(histRelationshipBuilder.getPrimaryKey());

        histRelationshipBuilderImpl.setStartDate(histRelationshipBuilder.getStartDate());
        histRelationshipBuilderImpl.setCreatedDate(histRelationshipBuilder.getCreatedDate());
        histRelationshipBuilderImpl.setCreatedBy(histRelationshipBuilder.getCreatedBy());
        histRelationshipBuilderImpl.setRelationshipDescription(histRelationshipBuilder.getRelationshipDescription());
        histRelationshipBuilderImpl.setActionDate(histRelationshipBuilder.getActionDate());
        histRelationshipBuilderImpl.setActionFlag(histRelationshipBuilder.getActionFlag());
        histRelationshipBuilderImpl.setHierarchyDefinitionSid(histRelationshipBuilder.getHierarchyDefinitionSid());
        histRelationshipBuilderImpl.setVersionNo(histRelationshipBuilder.getVersionNo());
        histRelationshipBuilderImpl.setRelationshipName(histRelationshipBuilder.getRelationshipName());
        histRelationshipBuilderImpl.setRelationshipBuilderSid(histRelationshipBuilder.getRelationshipBuilderSid());
        histRelationshipBuilderImpl.setHierarchyVersion(histRelationshipBuilder.getHierarchyVersion());
        histRelationshipBuilderImpl.setModifiedBy(histRelationshipBuilder.getModifiedBy());
        histRelationshipBuilderImpl.setModifiedDate(histRelationshipBuilder.getModifiedDate());
        histRelationshipBuilderImpl.setRelationshipType(histRelationshipBuilder.getRelationshipType());
        histRelationshipBuilderImpl.setBuildType(histRelationshipBuilder.getBuildType());

        return histRelationshipBuilderImpl;
    }

    /**
     * Returns the hist relationship builder with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist relationship builder
     * @return the hist relationship builder
     * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistRelationshipBuilderException, SystemException {
        HistRelationshipBuilder histRelationshipBuilder = fetchByPrimaryKey(primaryKey);

        if (histRelationshipBuilder == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histRelationshipBuilder;
    }

    /**
     * Returns the hist relationship builder with the primary key or throws a {@link com.stpl.app.NoSuchHistRelationshipBuilderException} if it could not be found.
     *
     * @param histRelationshipBuilderPK the primary key of the hist relationship builder
     * @return the hist relationship builder
     * @throws com.stpl.app.NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder findByPrimaryKey(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws NoSuchHistRelationshipBuilderException, SystemException {
        return findByPrimaryKey((Serializable) histRelationshipBuilderPK);
    }

    /**
     * Returns the hist relationship builder with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist relationship builder
     * @return the hist relationship builder, or <code>null</code> if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistRelationshipBuilder histRelationshipBuilder = (HistRelationshipBuilder) EntityCacheUtil.getResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                HistRelationshipBuilderImpl.class, primaryKey);

        if (histRelationshipBuilder == _nullHistRelationshipBuilder) {
            return null;
        }

        if (histRelationshipBuilder == null) {
            Session session = null;

            try {
                session = openSession();

                histRelationshipBuilder = (HistRelationshipBuilder) session.get(HistRelationshipBuilderImpl.class,
                        primaryKey);

                if (histRelationshipBuilder != null) {
                    cacheResult(histRelationshipBuilder);
                } else {
                    EntityCacheUtil.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                        HistRelationshipBuilderImpl.class, primaryKey,
                        _nullHistRelationshipBuilder);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
                    HistRelationshipBuilderImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histRelationshipBuilder;
    }

    /**
     * Returns the hist relationship builder with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histRelationshipBuilderPK the primary key of the hist relationship builder
     * @return the hist relationship builder, or <code>null</code> if a hist relationship builder with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipBuilder fetchByPrimaryKey(
        HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histRelationshipBuilderPK);
    }

    /**
     * Returns all the hist relationship builders.
     *
     * @return the hist relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipBuilder> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist relationship builders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist relationship builders
     * @param end the upper bound of the range of hist relationship builders (not inclusive)
     * @return the range of hist relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipBuilder> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist relationship builders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist relationship builders
     * @param end the upper bound of the range of hist relationship builders (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist relationship builders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipBuilder> findAll(int start, int end,
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

        List<HistRelationshipBuilder> list = (List<HistRelationshipBuilder>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTRELATIONSHIPBUILDER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTRELATIONSHIPBUILDER;

                if (pagination) {
                    sql = sql.concat(HistRelationshipBuilderModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistRelationshipBuilder>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistRelationshipBuilder>(list);
                } else {
                    list = (List<HistRelationshipBuilder>) QueryUtil.list(q,
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
     * Removes all the hist relationship builders from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistRelationshipBuilder histRelationshipBuilder : findAll()) {
            remove(histRelationshipBuilder);
        }
    }

    /**
     * Returns the number of hist relationship builders.
     *
     * @return the number of hist relationship builders
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

                Query q = session.createQuery(_SQL_COUNT_HISTRELATIONSHIPBUILDER);

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
     * Initializes the hist relationship builder persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistRelationshipBuilder")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistRelationshipBuilder>> listenersList = new ArrayList<ModelListener<HistRelationshipBuilder>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistRelationshipBuilder>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistRelationshipBuilderImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
