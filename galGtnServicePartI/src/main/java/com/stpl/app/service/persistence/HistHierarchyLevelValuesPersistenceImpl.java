package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchHistHierarchyLevelValuesException;
import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.model.impl.HistHierarchyLevelValuesImpl;
import com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPersistence;

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
 * The persistence implementation for the hist hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValuesPersistence
 * @see HistHierarchyLevelValuesUtil
 * @generated
 */
public class HistHierarchyLevelValuesPersistenceImpl extends BasePersistenceImpl<HistHierarchyLevelValues>
    implements HistHierarchyLevelValuesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link HistHierarchyLevelValuesUtil} to access the hist hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyLevelValuesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyLevelValuesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            HistHierarchyLevelValuesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_HISTHIERARCHYLEVELVALUES = "SELECT histHierarchyLevelValues FROM HistHierarchyLevelValues histHierarchyLevelValues";
    private static final String _SQL_COUNT_HISTHIERARCHYLEVELVALUES = "SELECT COUNT(histHierarchyLevelValues) FROM HistHierarchyLevelValues histHierarchyLevelValues";
    private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyLevelValues.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyLevelValues exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(HistHierarchyLevelValuesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "levelValues", "hierarchyLevelValuesSid", "createdDate",
                "createdBy", "actionDate", "actionFlag",
                "hierarchyLevelDefinitionSid", "versionNo", "modifiedBy",
                "modifiedDate"
            });
    private static HistHierarchyLevelValues _nullHistHierarchyLevelValues = new HistHierarchyLevelValuesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<HistHierarchyLevelValues> toCacheModel() {
                return _nullHistHierarchyLevelValuesCacheModel;
            }
        };

    private static CacheModel<HistHierarchyLevelValues> _nullHistHierarchyLevelValuesCacheModel =
        new CacheModel<HistHierarchyLevelValues>() {
            @Override
            public HistHierarchyLevelValues toEntityModel() {
                return _nullHistHierarchyLevelValues;
            }
        };

    public HistHierarchyLevelValuesPersistenceImpl() {
        setModelClass(HistHierarchyLevelValues.class);
    }

    /**
     * Caches the hist hierarchy level values in the entity cache if it is enabled.
     *
     * @param histHierarchyLevelValues the hist hierarchy level values
     */
    @Override
    public void cacheResult(HistHierarchyLevelValues histHierarchyLevelValues) {
        EntityCacheUtil.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesImpl.class,
            histHierarchyLevelValues.getPrimaryKey(), histHierarchyLevelValues);

        histHierarchyLevelValues.resetOriginalValues();
    }

    /**
     * Caches the hist hierarchy level valueses in the entity cache if it is enabled.
     *
     * @param histHierarchyLevelValueses the hist hierarchy level valueses
     */
    @Override
    public void cacheResult(
        List<HistHierarchyLevelValues> histHierarchyLevelValueses) {
        for (HistHierarchyLevelValues histHierarchyLevelValues : histHierarchyLevelValueses) {
            if (EntityCacheUtil.getResult(
                        HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyLevelValuesImpl.class,
                        histHierarchyLevelValues.getPrimaryKey()) == null) {
                cacheResult(histHierarchyLevelValues);
            } else {
                histHierarchyLevelValues.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all hist hierarchy level valueses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(HistHierarchyLevelValuesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(HistHierarchyLevelValuesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the hist hierarchy level values.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(HistHierarchyLevelValues histHierarchyLevelValues) {
        EntityCacheUtil.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesImpl.class,
            histHierarchyLevelValues.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<HistHierarchyLevelValues> histHierarchyLevelValueses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (HistHierarchyLevelValues histHierarchyLevelValues : histHierarchyLevelValueses) {
            EntityCacheUtil.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyLevelValuesImpl.class,
                histHierarchyLevelValues.getPrimaryKey());
        }
    }

    /**
     * Creates a new hist hierarchy level values with the primary key. Does not add the hist hierarchy level values to the database.
     *
     * @param histHierarchyLevelValuesPK the primary key for the new hist hierarchy level values
     * @return the new hist hierarchy level values
     */
    @Override
    public HistHierarchyLevelValues create(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK) {
        HistHierarchyLevelValues histHierarchyLevelValues = new HistHierarchyLevelValuesImpl();

        histHierarchyLevelValues.setNew(true);
        histHierarchyLevelValues.setPrimaryKey(histHierarchyLevelValuesPK);

        return histHierarchyLevelValues;
    }

    /**
     * Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues remove(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws NoSuchHistHierarchyLevelValuesException, SystemException {
        return remove((Serializable) histHierarchyLevelValuesPK);
    }

    /**
     * Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values that was removed
     * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues remove(Serializable primaryKey)
        throws NoSuchHistHierarchyLevelValuesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            HistHierarchyLevelValues histHierarchyLevelValues = (HistHierarchyLevelValues) session.get(HistHierarchyLevelValuesImpl.class,
                    primaryKey);

            if (histHierarchyLevelValues == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchHistHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(histHierarchyLevelValues);
        } catch (NoSuchHistHierarchyLevelValuesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected HistHierarchyLevelValues removeImpl(
        HistHierarchyLevelValues histHierarchyLevelValues)
        throws SystemException {
        histHierarchyLevelValues = toUnwrappedModel(histHierarchyLevelValues);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(histHierarchyLevelValues)) {
                histHierarchyLevelValues = (HistHierarchyLevelValues) session.get(HistHierarchyLevelValuesImpl.class,
                        histHierarchyLevelValues.getPrimaryKeyObj());
            }

            if (histHierarchyLevelValues != null) {
                session.delete(histHierarchyLevelValues);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (histHierarchyLevelValues != null) {
            clearCache(histHierarchyLevelValues);
        }

        return histHierarchyLevelValues;
    }

    @Override
    public HistHierarchyLevelValues updateImpl(
        com.stpl.app.model.HistHierarchyLevelValues histHierarchyLevelValues)
        throws SystemException {
        histHierarchyLevelValues = toUnwrappedModel(histHierarchyLevelValues);

        boolean isNew = histHierarchyLevelValues.isNew();

        Session session = null;

        try {
            session = openSession();

            if (histHierarchyLevelValues.isNew()) {
                session.save(histHierarchyLevelValues);

                histHierarchyLevelValues.setNew(false);
            } else {
                session.merge(histHierarchyLevelValues);
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

        EntityCacheUtil.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            HistHierarchyLevelValuesImpl.class,
            histHierarchyLevelValues.getPrimaryKey(), histHierarchyLevelValues);

        return histHierarchyLevelValues;
    }

    protected HistHierarchyLevelValues toUnwrappedModel(
        HistHierarchyLevelValues histHierarchyLevelValues) {
        if (histHierarchyLevelValues instanceof HistHierarchyLevelValuesImpl) {
            return histHierarchyLevelValues;
        }

        HistHierarchyLevelValuesImpl histHierarchyLevelValuesImpl = new HistHierarchyLevelValuesImpl();

        histHierarchyLevelValuesImpl.setNew(histHierarchyLevelValues.isNew());
        histHierarchyLevelValuesImpl.setPrimaryKey(histHierarchyLevelValues.getPrimaryKey());

        histHierarchyLevelValuesImpl.setLevelValues(histHierarchyLevelValues.getLevelValues());
        histHierarchyLevelValuesImpl.setHierarchyLevelValuesSid(histHierarchyLevelValues.getHierarchyLevelValuesSid());
        histHierarchyLevelValuesImpl.setCreatedDate(histHierarchyLevelValues.getCreatedDate());
        histHierarchyLevelValuesImpl.setCreatedBy(histHierarchyLevelValues.getCreatedBy());
        histHierarchyLevelValuesImpl.setActionDate(histHierarchyLevelValues.getActionDate());
        histHierarchyLevelValuesImpl.setActionFlag(histHierarchyLevelValues.getActionFlag());
        histHierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(histHierarchyLevelValues.getHierarchyLevelDefinitionSid());
        histHierarchyLevelValuesImpl.setVersionNo(histHierarchyLevelValues.getVersionNo());
        histHierarchyLevelValuesImpl.setModifiedBy(histHierarchyLevelValues.getModifiedBy());
        histHierarchyLevelValuesImpl.setModifiedDate(histHierarchyLevelValues.getModifiedDate());

        return histHierarchyLevelValuesImpl;
    }

    /**
     * Returns the hist hierarchy level values with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values
     * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues findByPrimaryKey(Serializable primaryKey)
        throws NoSuchHistHierarchyLevelValuesException, SystemException {
        HistHierarchyLevelValues histHierarchyLevelValues = fetchByPrimaryKey(primaryKey);

        if (histHierarchyLevelValues == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchHistHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return histHierarchyLevelValues;
    }

    /**
     * Returns the hist hierarchy level values with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyLevelValuesException} if it could not be found.
     *
     * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values
     * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues findByPrimaryKey(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws NoSuchHistHierarchyLevelValuesException, SystemException {
        return findByPrimaryKey((Serializable) histHierarchyLevelValuesPK);
    }

    /**
     * Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        HistHierarchyLevelValues histHierarchyLevelValues = (HistHierarchyLevelValues) EntityCacheUtil.getResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                HistHierarchyLevelValuesImpl.class, primaryKey);

        if (histHierarchyLevelValues == _nullHistHierarchyLevelValues) {
            return null;
        }

        if (histHierarchyLevelValues == null) {
            Session session = null;

            try {
                session = openSession();

                histHierarchyLevelValues = (HistHierarchyLevelValues) session.get(HistHierarchyLevelValuesImpl.class,
                        primaryKey);

                if (histHierarchyLevelValues != null) {
                    cacheResult(histHierarchyLevelValues);
                } else {
                    EntityCacheUtil.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        HistHierarchyLevelValuesImpl.class, primaryKey,
                        _nullHistHierarchyLevelValues);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                    HistHierarchyLevelValuesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return histHierarchyLevelValues;
    }

    /**
     * Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
     * @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public HistHierarchyLevelValues fetchByPrimaryKey(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) histHierarchyLevelValuesPK);
    }

    /**
     * Returns all the hist hierarchy level valueses.
     *
     * @return the hist hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelValues> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the hist hierarchy level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy level valueses
     * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
     * @return the range of hist hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelValues> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the hist hierarchy level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of hist hierarchy level valueses
     * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of hist hierarchy level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<HistHierarchyLevelValues> findAll(int start, int end,
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

        List<HistHierarchyLevelValues> list = (List<HistHierarchyLevelValues>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_HISTHIERARCHYLEVELVALUES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_HISTHIERARCHYLEVELVALUES;

                if (pagination) {
                    sql = sql.concat(HistHierarchyLevelValuesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<HistHierarchyLevelValues>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<HistHierarchyLevelValues>(list);
                } else {
                    list = (List<HistHierarchyLevelValues>) QueryUtil.list(q,
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
     * Removes all the hist hierarchy level valueses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (HistHierarchyLevelValues histHierarchyLevelValues : findAll()) {
            remove(histHierarchyLevelValues);
        }
    }

    /**
     * Returns the number of hist hierarchy level valueses.
     *
     * @return the number of hist hierarchy level valueses
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

                Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYLEVELVALUES);

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
     * Initializes the hist hierarchy level values persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.HistHierarchyLevelValues")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<HistHierarchyLevelValues>> listenersList = new ArrayList<ModelListener<HistHierarchyLevelValues>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<HistHierarchyLevelValues>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(HistHierarchyLevelValuesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
