package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistRelationshipLevelDefnException;
import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.model.impl.HistRelationshipLevelDefnImpl;
import com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPersistence;

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
 * The persistence implementation for the hist relationship level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefnPersistence
 * @see HistRelationshipLevelDefnUtil
 * @generated
 */
public class HistRelationshipLevelDefnPersistenceImpl
    extends BasePersistenceImpl<HistRelationshipLevelDefn>
    implements HistRelationshipLevelDefnPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistRelationshipLevelDefnUtil} to access the hist relationship level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistRelationshipLevelDefnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
            HistRelationshipLevelDefnImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
            HistRelationshipLevelDefnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_HISTRELATIONSHIPLEVELDEFN = "SELECT histRelationshipLevelDefn FROM HistRelationshipLevelDefn histRelationshipLevelDefn";
    private static final String _SQL_COUNT_HISTRELATIONSHIPLEVELDEFN = "SELECT COUNT(histRelationshipLevelDefn) FROM HistRelationshipLevelDefn histRelationshipLevelDefn";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histRelationshipLevelDefn.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistRelationshipLevelDefn exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistRelationshipLevelDefnPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "relationshipLevelValues", "actionDate",
                "hierarchyLevelDefinitionSid", "parentNode", "versionNo",
                "relationshipBuilderSid", "modifiedDate", "createdBy",
                "createdDate", "levelNo", "actionFlag", "hierarchyNo",
                "modifiedBy", "relationshipLevelSid", "flag", "levelName"
            });
    private static HistRelationshipLevelDefn _nullHistRelationshipLevelDefn = new HistRelationshipLevelDefnImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistRelationshipLevelDefn> toCacheModel() {
                return _nullHistRelationshipLevelDefnCacheModel;
            }
        };

    private static CacheModel<HistRelationshipLevelDefn> _nullHistRelationshipLevelDefnCacheModel =
        new CacheModel<HistRelationshipLevelDefn>() {
            @Override
            public HistRelationshipLevelDefn toEntityModel() {
                return _nullHistRelationshipLevelDefn;
            }
        };

    public HistRelationshipLevelDefnPersistenceImpl() {
        setModelClass(HistRelationshipLevelDefn.class);
    }

    /**
     * Caches the hist relationship level defn in the entity cache if it is enabled.
     *
     * @param histRelationshipLevelDefn the hist relationship level defn
     */
    @Override
    public void cacheResult(HistRelationshipLevelDefn histRelationshipLevelDefn) {
        EntityCacheUtil.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnImpl.class,
            histRelationshipLevelDefn.getPrimaryKey(), histRelationshipLevelDefn);

        histRelationshipLevelDefn.resetOriginalValues();
    }

    /**
     * Caches the hist relationship level defns in the entity cache if it is enabled.
     *
     * @param histRelationshipLevelDefns the hist relationship level defns
     */
    @Override
    public void cacheResult(
        List<HistRelationshipLevelDefn> histRelationshipLevelDefns) {
        for (HistRelationshipLevelDefn histRelationshipLevelDefn : histRelationshipLevelDefns) {
            if (EntityCacheUtil.getResult(
                        HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                        HistRelationshipLevelDefnImpl.class,
                        histRelationshipLevelDefn.getPrimaryKey()) == null) {
                cacheResult(histRelationshipLevelDefn);
            } else {
                histRelationshipLevelDefn.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist relationship level defns.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistRelationshipLevelDefnImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistRelationshipLevelDefnImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist relationship level defn.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistRelationshipLevelDefn histRelationshipLevelDefn) {
        EntityCacheUtil.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnImpl.class,
            histRelationshipLevelDefn.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HistRelationshipLevelDefn> histRelationshipLevelDefns) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistRelationshipLevelDefn histRelationshipLevelDefn : histRelationshipLevelDefns) {
            EntityCacheUtil.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                HistRelationshipLevelDefnImpl.class,
                histRelationshipLevelDefn.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
     *
     * @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
     * @return the new hist relationship level defn
     */
    @Override
    public HistRelationshipLevelDefn create(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
        HistRelationshipLevelDefn histRelationshipLevelDefn = new HistRelationshipLevelDefnImpl();

        histRelationshipLevelDefn.setNew(true);
        histRelationshipLevelDefn.setPrimaryKey(histRelationshipLevelDefnPK);

        return histRelationshipLevelDefn;
    }

    /**
     * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
     * @return the hist relationship level defn that was removed
     * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn remove(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws NoSuchHistRelationshipLevelDefnException, SystemException {
        return remove((Serializable) histRelationshipLevelDefnPK);
    }

    /**
     * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist relationship level defn
     * @return the hist relationship level defn that was removed
     * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn remove(Serializable primaryKey)
        throws NoSuchHistRelationshipLevelDefnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistRelationshipLevelDefn histRelationshipLevelDefn = (HistRelationshipLevelDefn) session.get(HistRelationshipLevelDefnImpl.class,
                    primaryKey);

            if (histRelationshipLevelDefn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistRelationshipLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histRelationshipLevelDefn);
        } catch (NoSuchHistRelationshipLevelDefnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistRelationshipLevelDefn removeImpl(
        HistRelationshipLevelDefn histRelationshipLevelDefn)
        throws SystemException {
        histRelationshipLevelDefn = toUnwrappedModel(histRelationshipLevelDefn);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histRelationshipLevelDefn)) {
                histRelationshipLevelDefn = (HistRelationshipLevelDefn) session.get(HistRelationshipLevelDefnImpl.class,
                        histRelationshipLevelDefn.getPrimaryKeyObj());
            }

            if (histRelationshipLevelDefn != null) {
                session.delete(histRelationshipLevelDefn);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histRelationshipLevelDefn != null) {
            clearCache(histRelationshipLevelDefn);
        }

        return histRelationshipLevelDefn;
    }

    @Override
    public HistRelationshipLevelDefn updateImpl(
        com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn)
        throws SystemException {
        histRelationshipLevelDefn = toUnwrappedModel(histRelationshipLevelDefn);

        boolean isNew = histRelationshipLevelDefn.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histRelationshipLevelDefn.isNew()) {
                session.save(histRelationshipLevelDefn);

                histRelationshipLevelDefn.setNew(false);
            } else {
                session.merge(histRelationshipLevelDefn);
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

        EntityCacheUtil.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistRelationshipLevelDefnImpl.class,
            histRelationshipLevelDefn.getPrimaryKey(), histRelationshipLevelDefn);

        return histRelationshipLevelDefn;
    }

    protected HistRelationshipLevelDefn toUnwrappedModel(
        HistRelationshipLevelDefn histRelationshipLevelDefn) {
        if (histRelationshipLevelDefn instanceof HistRelationshipLevelDefnImpl) {
            return histRelationshipLevelDefn;
        }

        HistRelationshipLevelDefnImpl histRelationshipLevelDefnImpl = new HistRelationshipLevelDefnImpl();

        histRelationshipLevelDefnImpl.setNew(histRelationshipLevelDefn.isNew());
        histRelationshipLevelDefnImpl.setPrimaryKey(histRelationshipLevelDefn.getPrimaryKey());

        histRelationshipLevelDefnImpl.setRelationshipLevelValues(histRelationshipLevelDefn.getRelationshipLevelValues());
        histRelationshipLevelDefnImpl.setActionDate(histRelationshipLevelDefn.getActionDate());
        histRelationshipLevelDefnImpl.setHierarchyLevelDefinitionSid(histRelationshipLevelDefn.getHierarchyLevelDefinitionSid());
        histRelationshipLevelDefnImpl.setParentNode(histRelationshipLevelDefn.getParentNode());
        histRelationshipLevelDefnImpl.setVersionNo(histRelationshipLevelDefn.getVersionNo());
        histRelationshipLevelDefnImpl.setRelationshipBuilderSid(histRelationshipLevelDefn.getRelationshipBuilderSid());
        histRelationshipLevelDefnImpl.setModifiedDate(histRelationshipLevelDefn.getModifiedDate());
        histRelationshipLevelDefnImpl.setCreatedBy(histRelationshipLevelDefn.getCreatedBy());
        histRelationshipLevelDefnImpl.setCreatedDate(histRelationshipLevelDefn.getCreatedDate());
        histRelationshipLevelDefnImpl.setLevelNo(histRelationshipLevelDefn.getLevelNo());
        histRelationshipLevelDefnImpl.setActionFlag(histRelationshipLevelDefn.getActionFlag());
        histRelationshipLevelDefnImpl.setHierarchyNo(histRelationshipLevelDefn.getHierarchyNo());
        histRelationshipLevelDefnImpl.setModifiedBy(histRelationshipLevelDefn.getModifiedBy());
        histRelationshipLevelDefnImpl.setRelationshipLevelSid(histRelationshipLevelDefn.getRelationshipLevelSid());
        histRelationshipLevelDefnImpl.setFlag(histRelationshipLevelDefn.getFlag());
        histRelationshipLevelDefnImpl.setLevelName(histRelationshipLevelDefn.getLevelName());

        return histRelationshipLevelDefnImpl;
    }

    /**
     * Returns the hist relationship level defn with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist relationship level defn
     * @return the hist relationship level defn
     * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistRelationshipLevelDefnException, SystemException {
        HistRelationshipLevelDefn histRelationshipLevelDefn = fetchByPrimaryKey(primaryKey);

        if (histRelationshipLevelDefn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistRelationshipLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histRelationshipLevelDefn;
    }

    /**
     * Returns the hist relationship level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistRelationshipLevelDefnException} if it could not be found.
     *
     * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
     * @return the hist relationship level defn
     * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn findByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws NoSuchHistRelationshipLevelDefnException, SystemException {
        return findByPrimaryKey((Serializable) histRelationshipLevelDefnPK);
    }

    /**
     * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist relationship level defn
     * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistRelationshipLevelDefn histRelationshipLevelDefn = (HistRelationshipLevelDefn) EntityCacheUtil.getResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                HistRelationshipLevelDefnImpl.class, primaryKey);

        if (histRelationshipLevelDefn == _nullHistRelationshipLevelDefn) {
            return null;
        }

        if (histRelationshipLevelDefn == null) {
            Session session = null;

            try {
                session = openSession();

                histRelationshipLevelDefn = (HistRelationshipLevelDefn) session.get(HistRelationshipLevelDefnImpl.class,
                        primaryKey);

                if (histRelationshipLevelDefn != null) {
                    cacheResult(histRelationshipLevelDefn);
                } else {
                    EntityCacheUtil.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                        HistRelationshipLevelDefnImpl.class, primaryKey,
                        _nullHistRelationshipLevelDefn);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                    HistRelationshipLevelDefnImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histRelationshipLevelDefn;
    }

    /**
     * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
     * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistRelationshipLevelDefn fetchByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histRelationshipLevelDefnPK);
    }

    /**
     * Returns all the hist relationship level defns.
     *
     * @return the hist relationship level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipLevelDefn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist relationship level defns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist relationship level defns
     * @param end the upper bound of the range of hist relationship level defns (not inclusive)
     * @return the range of hist relationship level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipLevelDefn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist relationship level defns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist relationship level defns
     * @param end the upper bound of the range of hist relationship level defns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist relationship level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistRelationshipLevelDefn> findAll(int start, int end,
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

        List<HistRelationshipLevelDefn> list = (List<HistRelationshipLevelDefn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTRELATIONSHIPLEVELDEFN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTRELATIONSHIPLEVELDEFN;

                if (pagination) {
                    sql = sql.concat(HistRelationshipLevelDefnModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistRelationshipLevelDefn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistRelationshipLevelDefn>(list);
                } else {
                    list = (List<HistRelationshipLevelDefn>) QueryUtil.list(q,
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
     * Removes all the hist relationship level defns from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistRelationshipLevelDefn histRelationshipLevelDefn : findAll()) {
            remove(histRelationshipLevelDefn);
        }
    }

    /**
     * Returns the number of hist relationship level defns.
     *
     * @return the number of hist relationship level defns
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

                Query q = session.createQuery(_SQL_COUNT_HISTRELATIONSHIPLEVELDEFN);

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
     * Initializes the hist relationship level defn persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistRelationshipLevelDefn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistRelationshipLevelDefn>> listenersList = new ArrayList<ModelListener<HistRelationshipLevelDefn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistRelationshipLevelDefn>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistRelationshipLevelDefnImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
