package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistHierarchyDefinitionException;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.model.impl.HistHierarchyDefinitionImpl;
import com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPersistence;

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
 * The persistence implementation for the hist hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyDefinitionPersistence
 * @see HistHierarchyDefinitionUtil
 * @generated
 */
public class HistHierarchyDefinitionPersistenceImpl extends BasePersistenceImpl<HistHierarchyDefinition>
    implements HistHierarchyDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistHierarchyDefinitionUtil} to access the hist hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTHIERARCHYDEFINITION = "SELECT histHierarchyDefinition FROM HistHierarchyDefinition histHierarchyDefinition";
    private static final String _SQL_COUNT_HISTHIERARCHYDEFINITION = "SELECT COUNT(histHierarchyDefinition) FROM HistHierarchyDefinition histHierarchyDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistHierarchyDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "noOfLevels", "createdBy", "actionDate", "actionFlag",
                "modifiedBy", "hierarchyDefinitionSid", "createdDate",
                "hierarchyType", "hierarchyCategory", "hierarchyName",
                "versionNo", "modifiedDate"
            });
    private static HistHierarchyDefinition _nullHistHierarchyDefinition = new HistHierarchyDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistHierarchyDefinition> toCacheModel() {
                return _nullHistHierarchyDefinitionCacheModel;
            }
        };

    private static CacheModel<HistHierarchyDefinition> _nullHistHierarchyDefinitionCacheModel =
        new CacheModel<HistHierarchyDefinition>() {
            @Override
            public HistHierarchyDefinition toEntityModel() {
                return _nullHistHierarchyDefinition;
            }
        };

    public HistHierarchyDefinitionPersistenceImpl() {
        setModelClass(HistHierarchyDefinition.class);
    }

    /**
     * Caches the hist hierarchy definition in the entity cache if it is enabled.
     *
     * @param histHierarchyDefinition the hist hierarchy definition
     */
    @Override
    public void cacheResult(HistHierarchyDefinition histHierarchyDefinition) {
        EntityCacheUtil.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionImpl.class,
            histHierarchyDefinition.getPrimaryKey(), histHierarchyDefinition);

        histHierarchyDefinition.resetOriginalValues();
    }

    /**
     * Caches the hist hierarchy definitions in the entity cache if it is enabled.
     *
     * @param histHierarchyDefinitions the hist hierarchy definitions
     */
    @Override
    public void cacheResult(
        List<HistHierarchyDefinition> histHierarchyDefinitions) {
        for (HistHierarchyDefinition histHierarchyDefinition : histHierarchyDefinitions) {
            if (EntityCacheUtil.getResult(
                        HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyDefinitionImpl.class,
                        histHierarchyDefinition.getPrimaryKey()) == null) {
                cacheResult(histHierarchyDefinition);
            } else {
                histHierarchyDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist hierarchy definitions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistHierarchyDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistHierarchyDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist hierarchy definition.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistHierarchyDefinition histHierarchyDefinition) {
        EntityCacheUtil.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionImpl.class,
            histHierarchyDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HistHierarchyDefinition> histHierarchyDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistHierarchyDefinition histHierarchyDefinition : histHierarchyDefinitions) {
            EntityCacheUtil.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyDefinitionImpl.class,
                histHierarchyDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
     *
     * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
     * @return the new hist hierarchy definition
     */
    @Override
    public HistHierarchyDefinition create(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
        HistHierarchyDefinition histHierarchyDefinition = new HistHierarchyDefinitionImpl();

        histHierarchyDefinition.setNew(true);
        histHierarchyDefinition.setPrimaryKey(histHierarchyDefinitionPK);

        return histHierarchyDefinition;
    }

    /**
     * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition remove(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws NoSuchHistHierarchyDefinitionException, SystemException {
        return remove((Serializable) histHierarchyDefinitionPK);
    }

    /**
     * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition remove(Serializable primaryKey)
        throws NoSuchHistHierarchyDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistHierarchyDefinition histHierarchyDefinition = (HistHierarchyDefinition) session.get(HistHierarchyDefinitionImpl.class,
                    primaryKey);

            if (histHierarchyDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histHierarchyDefinition);
        } catch (NoSuchHistHierarchyDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistHierarchyDefinition removeImpl(
        HistHierarchyDefinition histHierarchyDefinition)
        throws SystemException {
        histHierarchyDefinition = toUnwrappedModel(histHierarchyDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histHierarchyDefinition)) {
                histHierarchyDefinition = (HistHierarchyDefinition) session.get(HistHierarchyDefinitionImpl.class,
                        histHierarchyDefinition.getPrimaryKeyObj());
            }

            if (histHierarchyDefinition != null) {
                session.delete(histHierarchyDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histHierarchyDefinition != null) {
            clearCache(histHierarchyDefinition);
        }

        return histHierarchyDefinition;
    }

    @Override
    public HistHierarchyDefinition updateImpl(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws SystemException {
        histHierarchyDefinition = toUnwrappedModel(histHierarchyDefinition);

        boolean isNew = histHierarchyDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histHierarchyDefinition.isNew()) {
                session.save(histHierarchyDefinition);

                histHierarchyDefinition.setNew(false);
            } else {
                session.merge(histHierarchyDefinition);
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

        EntityCacheUtil.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyDefinitionImpl.class,
            histHierarchyDefinition.getPrimaryKey(), histHierarchyDefinition);

        return histHierarchyDefinition;
    }

    protected HistHierarchyDefinition toUnwrappedModel(
        HistHierarchyDefinition histHierarchyDefinition) {
        if (histHierarchyDefinition instanceof HistHierarchyDefinitionImpl) {
            return histHierarchyDefinition;
        }

        HistHierarchyDefinitionImpl histHierarchyDefinitionImpl = new HistHierarchyDefinitionImpl();

        histHierarchyDefinitionImpl.setNew(histHierarchyDefinition.isNew());
        histHierarchyDefinitionImpl.setPrimaryKey(histHierarchyDefinition.getPrimaryKey());

        histHierarchyDefinitionImpl.setNoOfLevels(histHierarchyDefinition.getNoOfLevels());
        histHierarchyDefinitionImpl.setCreatedBy(histHierarchyDefinition.getCreatedBy());
        histHierarchyDefinitionImpl.setActionDate(histHierarchyDefinition.getActionDate());
        histHierarchyDefinitionImpl.setActionFlag(histHierarchyDefinition.getActionFlag());
        histHierarchyDefinitionImpl.setModifiedBy(histHierarchyDefinition.getModifiedBy());
        histHierarchyDefinitionImpl.setHierarchyDefinitionSid(histHierarchyDefinition.getHierarchyDefinitionSid());
        histHierarchyDefinitionImpl.setCreatedDate(histHierarchyDefinition.getCreatedDate());
        histHierarchyDefinitionImpl.setHierarchyType(histHierarchyDefinition.getHierarchyType());
        histHierarchyDefinitionImpl.setHierarchyCategory(histHierarchyDefinition.getHierarchyCategory());
        histHierarchyDefinitionImpl.setHierarchyName(histHierarchyDefinition.getHierarchyName());
        histHierarchyDefinitionImpl.setVersionNo(histHierarchyDefinition.getVersionNo());
        histHierarchyDefinitionImpl.setModifiedDate(histHierarchyDefinition.getModifiedDate());

        return histHierarchyDefinitionImpl;
    }

    /**
     * Returns the hist hierarchy definition with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition
     * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistHierarchyDefinitionException, SystemException {
        HistHierarchyDefinition histHierarchyDefinition = fetchByPrimaryKey(primaryKey);

        if (histHierarchyDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histHierarchyDefinition;
    }

    /**
     * Returns the hist hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyDefinitionException} if it could not be found.
     *
     * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition
     * @throws com.stpl.app.NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition findByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws NoSuchHistHierarchyDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) histHierarchyDefinitionPK);
    }

    /**
     * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistHierarchyDefinition histHierarchyDefinition = (HistHierarchyDefinition) EntityCacheUtil.getResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyDefinitionImpl.class, primaryKey);

        if (histHierarchyDefinition == _nullHistHierarchyDefinition) {
            return null;
        }

        if (histHierarchyDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                histHierarchyDefinition = (HistHierarchyDefinition) session.get(HistHierarchyDefinitionImpl.class,
                        primaryKey);

                if (histHierarchyDefinition != null) {
                    cacheResult(histHierarchyDefinition);
                } else {
                    EntityCacheUtil.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyDefinitionImpl.class, primaryKey,
                        _nullHistHierarchyDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    HistHierarchyDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histHierarchyDefinition;
    }

    /**
     * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
     * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyDefinition fetchByPrimaryKey(
        HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histHierarchyDefinitionPK);
    }

    /**
     * Returns all the hist hierarchy definitions.
     *
     * @return the hist hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy definitions
     * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
     * @return the range of hist hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy definitions
     * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyDefinition> findAll(int start, int end,
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

        List<HistHierarchyDefinition> list = (List<HistHierarchyDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTHIERARCHYDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTHIERARCHYDEFINITION;

                if (pagination) {
                    sql = sql.concat(HistHierarchyDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistHierarchyDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistHierarchyDefinition>(list);
                } else {
                    list = (List<HistHierarchyDefinition>) QueryUtil.list(q,
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
     * Removes all the hist hierarchy definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistHierarchyDefinition histHierarchyDefinition : findAll()) {
            remove(histHierarchyDefinition);
        }
    }

    /**
     * Returns the number of hist hierarchy definitions.
     *
     * @return the number of hist hierarchy definitions
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

                Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYDEFINITION);

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
     * Initializes the hist hierarchy definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistHierarchyDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistHierarchyDefinition>> listenersList = new ArrayList<ModelListener<HistHierarchyDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistHierarchyDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistHierarchyDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
