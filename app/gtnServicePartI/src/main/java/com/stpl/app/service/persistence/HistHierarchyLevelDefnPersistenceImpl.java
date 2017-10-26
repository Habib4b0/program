package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistHierarchyLevelDefnException;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.model.impl.HistHierarchyLevelDefnImpl;
import com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPersistence;

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
 * The persistence implementation for the hist hierarchy level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefnPersistence
 * @see HistHierarchyLevelDefnUtil
 * @generated
 */
public class HistHierarchyLevelDefnPersistenceImpl extends BasePersistenceImpl<HistHierarchyLevelDefn>
    implements HistHierarchyLevelDefnPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistHierarchyLevelDefnUtil} to access the hist hierarchy level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyLevelDefnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyLevelDefnImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyLevelDefnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTHIERARCHYLEVELDEFN = "SELECT histHierarchyLevelDefn FROM HistHierarchyLevelDefn histHierarchyLevelDefn";
    private static final String _SQL_COUNT_HISTHIERARCHYLEVELDEFN = "SELECT COUNT(histHierarchyLevelDefn) FROM HistHierarchyLevelDefn histHierarchyLevelDefn";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyLevelDefn.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyLevelDefn exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistHierarchyLevelDefnPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "tableName", "actionDate", "fieldName",
                "hierarchyLevelDefinitionSid", "versionNo", "modifiedDate",
                "createdBy", "createdDate", "levelValueReference", "levelNo",
                "actionFlag", "hierarchyDefinitionSid", "modifiedBy",
                "levelName"
            });
    private static HistHierarchyLevelDefn _nullHistHierarchyLevelDefn = new HistHierarchyLevelDefnImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistHierarchyLevelDefn> toCacheModel() {
                return _nullHistHierarchyLevelDefnCacheModel;
            }
        };

    private static CacheModel<HistHierarchyLevelDefn> _nullHistHierarchyLevelDefnCacheModel =
        new CacheModel<HistHierarchyLevelDefn>() {
            @Override
            public HistHierarchyLevelDefn toEntityModel() {
                return _nullHistHierarchyLevelDefn;
            }
        };

    public HistHierarchyLevelDefnPersistenceImpl() {
        setModelClass(HistHierarchyLevelDefn.class);
    }

    /**
     * Caches the hist hierarchy level defn in the entity cache if it is enabled.
     *
     * @param histHierarchyLevelDefn the hist hierarchy level defn
     */
    @Override
    public void cacheResult(HistHierarchyLevelDefn histHierarchyLevelDefn) {
        EntityCacheUtil.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnImpl.class,
            histHierarchyLevelDefn.getPrimaryKey(), histHierarchyLevelDefn);

        histHierarchyLevelDefn.resetOriginalValues();
    }

    /**
     * Caches the hist hierarchy level defns in the entity cache if it is enabled.
     *
     * @param histHierarchyLevelDefns the hist hierarchy level defns
     */
    @Override
    public void cacheResult(
        List<HistHierarchyLevelDefn> histHierarchyLevelDefns) {
        for (HistHierarchyLevelDefn histHierarchyLevelDefn : histHierarchyLevelDefns) {
            if (EntityCacheUtil.getResult(
                        HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyLevelDefnImpl.class,
                        histHierarchyLevelDefn.getPrimaryKey()) == null) {
                cacheResult(histHierarchyLevelDefn);
            } else {
                histHierarchyLevelDefn.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist hierarchy level defns.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistHierarchyLevelDefnImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistHierarchyLevelDefnImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist hierarchy level defn.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistHierarchyLevelDefn histHierarchyLevelDefn) {
        EntityCacheUtil.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnImpl.class,
            histHierarchyLevelDefn.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<HistHierarchyLevelDefn> histHierarchyLevelDefns) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistHierarchyLevelDefn histHierarchyLevelDefn : histHierarchyLevelDefns) {
            EntityCacheUtil.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyLevelDefnImpl.class,
                histHierarchyLevelDefn.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
     *
     * @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
     * @return the new hist hierarchy level defn
     */
    @Override
    public HistHierarchyLevelDefn create(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
        HistHierarchyLevelDefn histHierarchyLevelDefn = new HistHierarchyLevelDefnImpl();

        histHierarchyLevelDefn.setNew(true);
        histHierarchyLevelDefn.setPrimaryKey(histHierarchyLevelDefnPK);

        return histHierarchyLevelDefn;
    }

    /**
     * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn remove(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws NoSuchHistHierarchyLevelDefnException, SystemException {
        return remove((Serializable) histHierarchyLevelDefnPK);
    }

    /**
     * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn remove(Serializable primaryKey)
        throws NoSuchHistHierarchyLevelDefnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistHierarchyLevelDefn histHierarchyLevelDefn = (HistHierarchyLevelDefn) session.get(HistHierarchyLevelDefnImpl.class,
                    primaryKey);

            if (histHierarchyLevelDefn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistHierarchyLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histHierarchyLevelDefn);
        } catch (NoSuchHistHierarchyLevelDefnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistHierarchyLevelDefn removeImpl(
        HistHierarchyLevelDefn histHierarchyLevelDefn)
        throws SystemException {
        histHierarchyLevelDefn = toUnwrappedModel(histHierarchyLevelDefn);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histHierarchyLevelDefn)) {
                histHierarchyLevelDefn = (HistHierarchyLevelDefn) session.get(HistHierarchyLevelDefnImpl.class,
                        histHierarchyLevelDefn.getPrimaryKeyObj());
            }

            if (histHierarchyLevelDefn != null) {
                session.delete(histHierarchyLevelDefn);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histHierarchyLevelDefn != null) {
            clearCache(histHierarchyLevelDefn);
        }

        return histHierarchyLevelDefn;
    }

    @Override
    public HistHierarchyLevelDefn updateImpl(
        com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn)
        throws SystemException {
        histHierarchyLevelDefn = toUnwrappedModel(histHierarchyLevelDefn);

        boolean isNew = histHierarchyLevelDefn.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histHierarchyLevelDefn.isNew()) {
                session.save(histHierarchyLevelDefn);

                histHierarchyLevelDefn.setNew(false);
            } else {
                session.merge(histHierarchyLevelDefn);
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

        EntityCacheUtil.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelDefnImpl.class,
            histHierarchyLevelDefn.getPrimaryKey(), histHierarchyLevelDefn);

        return histHierarchyLevelDefn;
    }

    protected HistHierarchyLevelDefn toUnwrappedModel(
        HistHierarchyLevelDefn histHierarchyLevelDefn) {
        if (histHierarchyLevelDefn instanceof HistHierarchyLevelDefnImpl) {
            return histHierarchyLevelDefn;
        }

        HistHierarchyLevelDefnImpl histHierarchyLevelDefnImpl = new HistHierarchyLevelDefnImpl();

        histHierarchyLevelDefnImpl.setNew(histHierarchyLevelDefn.isNew());
        histHierarchyLevelDefnImpl.setPrimaryKey(histHierarchyLevelDefn.getPrimaryKey());

        histHierarchyLevelDefnImpl.setTableName(histHierarchyLevelDefn.getTableName());
        histHierarchyLevelDefnImpl.setActionDate(histHierarchyLevelDefn.getActionDate());
        histHierarchyLevelDefnImpl.setFieldName(histHierarchyLevelDefn.getFieldName());
        histHierarchyLevelDefnImpl.setHierarchyLevelDefinitionSid(histHierarchyLevelDefn.getHierarchyLevelDefinitionSid());
        histHierarchyLevelDefnImpl.setVersionNo(histHierarchyLevelDefn.getVersionNo());
        histHierarchyLevelDefnImpl.setModifiedDate(histHierarchyLevelDefn.getModifiedDate());
        histHierarchyLevelDefnImpl.setCreatedBy(histHierarchyLevelDefn.getCreatedBy());
        histHierarchyLevelDefnImpl.setCreatedDate(histHierarchyLevelDefn.getCreatedDate());
        histHierarchyLevelDefnImpl.setLevelValueReference(histHierarchyLevelDefn.getLevelValueReference());
        histHierarchyLevelDefnImpl.setLevelNo(histHierarchyLevelDefn.getLevelNo());
        histHierarchyLevelDefnImpl.setActionFlag(histHierarchyLevelDefn.getActionFlag());
        histHierarchyLevelDefnImpl.setHierarchyDefinitionSid(histHierarchyLevelDefn.getHierarchyDefinitionSid());
        histHierarchyLevelDefnImpl.setModifiedBy(histHierarchyLevelDefn.getModifiedBy());
        histHierarchyLevelDefnImpl.setLevelName(histHierarchyLevelDefn.getLevelName());

        return histHierarchyLevelDefnImpl;
    }

    /**
     * Returns the hist hierarchy level defn with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn
     * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistHierarchyLevelDefnException, SystemException {
        HistHierarchyLevelDefn histHierarchyLevelDefn = fetchByPrimaryKey(primaryKey);

        if (histHierarchyLevelDefn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistHierarchyLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histHierarchyLevelDefn;
    }

    /**
     * Returns the hist hierarchy level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyLevelDefnException} if it could not be found.
     *
     * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn
     * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn findByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws NoSuchHistHierarchyLevelDefnException, SystemException {
        return findByPrimaryKey((Serializable) histHierarchyLevelDefnPK);
    }

    /**
     * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistHierarchyLevelDefn histHierarchyLevelDefn = (HistHierarchyLevelDefn) EntityCacheUtil.getResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyLevelDefnImpl.class, primaryKey);

        if (histHierarchyLevelDefn == _nullHistHierarchyLevelDefn) {
            return null;
        }

        if (histHierarchyLevelDefn == null) {
            Session session = null;

            try {
                session = openSession();

                histHierarchyLevelDefn = (HistHierarchyLevelDefn) session.get(HistHierarchyLevelDefnImpl.class,
                        primaryKey);

                if (histHierarchyLevelDefn != null) {
                    cacheResult(histHierarchyLevelDefn);
                } else {
                    EntityCacheUtil.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyLevelDefnImpl.class, primaryKey,
                        _nullHistHierarchyLevelDefn);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
                    HistHierarchyLevelDefnImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histHierarchyLevelDefn;
    }

    /**
     * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
     * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelDefn fetchByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histHierarchyLevelDefnPK);
    }

    /**
     * Returns all the hist hierarchy level defns.
     *
     * @return the hist hierarchy level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelDefn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist hierarchy level defns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy level defns
     * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
     * @return the range of hist hierarchy level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelDefn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist hierarchy level defns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy level defns
     * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist hierarchy level defns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelDefn> findAll(int start, int end,
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

        List<HistHierarchyLevelDefn> list = (List<HistHierarchyLevelDefn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTHIERARCHYLEVELDEFN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTHIERARCHYLEVELDEFN;

                if (pagination) {
                    sql = sql.concat(HistHierarchyLevelDefnModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistHierarchyLevelDefn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistHierarchyLevelDefn>(list);
                } else {
                    list = (List<HistHierarchyLevelDefn>) QueryUtil.list(q,
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
     * Removes all the hist hierarchy level defns from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistHierarchyLevelDefn histHierarchyLevelDefn : findAll()) {
            remove(histHierarchyLevelDefn);
        }
    }

    /**
     * Returns the number of hist hierarchy level defns.
     *
     * @return the number of hist hierarchy level defns
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

                Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYLEVELDEFN);

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
     * Initializes the hist hierarchy level defn persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistHierarchyLevelDefn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistHierarchyLevelDefn>> listenersList = new ArrayList<ModelListener<HistHierarchyLevelDefn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistHierarchyLevelDefn>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistHierarchyLevelDefnImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
