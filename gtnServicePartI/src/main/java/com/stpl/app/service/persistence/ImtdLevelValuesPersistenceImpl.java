package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchImtdLevelValuesException;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.model.impl.ImtdLevelValuesImpl;
import com.stpl.app.model.impl.ImtdLevelValuesModelImpl;
import com.stpl.app.service.persistence.ImtdLevelValuesPersistence;

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
 * The persistence implementation for the imtd level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdLevelValuesPersistence
 * @see ImtdLevelValuesUtil
 * @generated
 */
public class ImtdLevelValuesPersistenceImpl extends BasePersistenceImpl<ImtdLevelValues>
    implements ImtdLevelValuesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImtdLevelValuesUtil} to access the imtd level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImtdLevelValuesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            ImtdLevelValuesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED,
            ImtdLevelValuesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMTDLEVELVALUES = "SELECT imtdLevelValues FROM ImtdLevelValues imtdLevelValues";
    private static final String _SQL_COUNT_IMTDLEVELVALUES = "SELECT COUNT(imtdLevelValues) FROM ImtdLevelValues imtdLevelValues";
    private static final String _ORDER_BY_ENTITY_ALIAS = "imtdLevelValues.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdLevelValues exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImtdLevelValuesPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "levelValues", "createdDate", "createdBy", "imtdLevelValuesSid",
                "levelNo", "versionNo", "modifiedBy", "modifiedDate",
                "levelName"
            });
    private static ImtdLevelValues _nullImtdLevelValues = new ImtdLevelValuesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImtdLevelValues> toCacheModel() {
                return _nullImtdLevelValuesCacheModel;
            }
        };

    private static CacheModel<ImtdLevelValues> _nullImtdLevelValuesCacheModel = new CacheModel<ImtdLevelValues>() {
            @Override
            public ImtdLevelValues toEntityModel() {
                return _nullImtdLevelValues;
            }
        };

    public ImtdLevelValuesPersistenceImpl() {
        setModelClass(ImtdLevelValues.class);
    }

    /**
     * Caches the imtd level values in the entity cache if it is enabled.
     *
     * @param imtdLevelValues the imtd level values
     */
    @Override
    public void cacheResult(ImtdLevelValues imtdLevelValues) {
        EntityCacheUtil.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey(),
            imtdLevelValues);

        imtdLevelValues.resetOriginalValues();
    }

    /**
     * Caches the imtd level valueses in the entity cache if it is enabled.
     *
     * @param imtdLevelValueses the imtd level valueses
     */
    @Override
    public void cacheResult(List<ImtdLevelValues> imtdLevelValueses) {
        for (ImtdLevelValues imtdLevelValues : imtdLevelValueses) {
            if (EntityCacheUtil.getResult(
                        ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdLevelValuesImpl.class,
                        imtdLevelValues.getPrimaryKey()) == null) {
                cacheResult(imtdLevelValues);
            } else {
                imtdLevelValues.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all imtd level valueses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImtdLevelValuesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImtdLevelValuesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the imtd level values.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImtdLevelValues imtdLevelValues) {
        EntityCacheUtil.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImtdLevelValues> imtdLevelValueses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImtdLevelValues imtdLevelValues : imtdLevelValueses) {
            EntityCacheUtil.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey());
        }
    }

    /**
     * Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
     *
     * @param imtdLevelValuesSid the primary key for the new imtd level values
     * @return the new imtd level values
     */
    @Override
    public ImtdLevelValues create(int imtdLevelValuesSid) {
        ImtdLevelValues imtdLevelValues = new ImtdLevelValuesImpl();

        imtdLevelValues.setNew(true);
        imtdLevelValues.setPrimaryKey(imtdLevelValuesSid);

        return imtdLevelValues;
    }

    /**
     * Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param imtdLevelValuesSid the primary key of the imtd level values
     * @return the imtd level values that was removed
     * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues remove(int imtdLevelValuesSid)
        throws NoSuchImtdLevelValuesException, SystemException {
        return remove((Serializable) imtdLevelValuesSid);
    }

    /**
     * Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the imtd level values
     * @return the imtd level values that was removed
     * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues remove(Serializable primaryKey)
        throws NoSuchImtdLevelValuesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImtdLevelValues imtdLevelValues = (ImtdLevelValues) session.get(ImtdLevelValuesImpl.class,
                    primaryKey);

            if (imtdLevelValues == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImtdLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(imtdLevelValues);
        } catch (NoSuchImtdLevelValuesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImtdLevelValues removeImpl(ImtdLevelValues imtdLevelValues)
        throws SystemException {
        imtdLevelValues = toUnwrappedModel(imtdLevelValues);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(imtdLevelValues)) {
                imtdLevelValues = (ImtdLevelValues) session.get(ImtdLevelValuesImpl.class,
                        imtdLevelValues.getPrimaryKeyObj());
            }

            if (imtdLevelValues != null) {
                session.delete(imtdLevelValues);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (imtdLevelValues != null) {
            clearCache(imtdLevelValues);
        }

        return imtdLevelValues;
    }

    @Override
    public ImtdLevelValues updateImpl(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues)
        throws SystemException {
        imtdLevelValues = toUnwrappedModel(imtdLevelValues);

        boolean isNew = imtdLevelValues.isNew();

        Session session = null;

        try {
            session = openSession();

            if (imtdLevelValues.isNew()) {
                session.save(imtdLevelValues);

                imtdLevelValues.setNew(false);
            } else {
                session.merge(imtdLevelValues);
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

        EntityCacheUtil.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
            ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey(),
            imtdLevelValues);

        return imtdLevelValues;
    }

    protected ImtdLevelValues toUnwrappedModel(ImtdLevelValues imtdLevelValues) {
        if (imtdLevelValues instanceof ImtdLevelValuesImpl) {
            return imtdLevelValues;
        }

        ImtdLevelValuesImpl imtdLevelValuesImpl = new ImtdLevelValuesImpl();

        imtdLevelValuesImpl.setNew(imtdLevelValues.isNew());
        imtdLevelValuesImpl.setPrimaryKey(imtdLevelValues.getPrimaryKey());

        imtdLevelValuesImpl.setLevelValues(imtdLevelValues.getLevelValues());
        imtdLevelValuesImpl.setCreatedDate(imtdLevelValues.getCreatedDate());
        imtdLevelValuesImpl.setCreatedBy(imtdLevelValues.getCreatedBy());
        imtdLevelValuesImpl.setImtdLevelValuesSid(imtdLevelValues.getImtdLevelValuesSid());
        imtdLevelValuesImpl.setLevelNo(imtdLevelValues.getLevelNo());
        imtdLevelValuesImpl.setVersionNo(imtdLevelValues.getVersionNo());
        imtdLevelValuesImpl.setModifiedBy(imtdLevelValues.getModifiedBy());
        imtdLevelValuesImpl.setModifiedDate(imtdLevelValues.getModifiedDate());
        imtdLevelValuesImpl.setLevelName(imtdLevelValues.getLevelName());

        return imtdLevelValuesImpl;
    }

    /**
     * Returns the imtd level values with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the imtd level values
     * @return the imtd level values
     * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImtdLevelValuesException, SystemException {
        ImtdLevelValues imtdLevelValues = fetchByPrimaryKey(primaryKey);

        if (imtdLevelValues == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImtdLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return imtdLevelValues;
    }

    /**
     * Returns the imtd level values with the primary key or throws a {@link com.stpl.app.NoSuchImtdLevelValuesException} if it could not be found.
     *
     * @param imtdLevelValuesSid the primary key of the imtd level values
     * @return the imtd level values
     * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues findByPrimaryKey(int imtdLevelValuesSid)
        throws NoSuchImtdLevelValuesException, SystemException {
        return findByPrimaryKey((Serializable) imtdLevelValuesSid);
    }

    /**
     * Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the imtd level values
     * @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImtdLevelValues imtdLevelValues = (ImtdLevelValues) EntityCacheUtil.getResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                ImtdLevelValuesImpl.class, primaryKey);

        if (imtdLevelValues == _nullImtdLevelValues) {
            return null;
        }

        if (imtdLevelValues == null) {
            Session session = null;

            try {
                session = openSession();

                imtdLevelValues = (ImtdLevelValues) session.get(ImtdLevelValuesImpl.class,
                        primaryKey);

                if (imtdLevelValues != null) {
                    cacheResult(imtdLevelValues);
                } else {
                    EntityCacheUtil.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                        ImtdLevelValuesImpl.class, primaryKey,
                        _nullImtdLevelValues);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
                    ImtdLevelValuesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return imtdLevelValues;
    }

    /**
     * Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param imtdLevelValuesSid the primary key of the imtd level values
     * @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImtdLevelValues fetchByPrimaryKey(int imtdLevelValuesSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) imtdLevelValuesSid);
    }

    /**
     * Returns all the imtd level valueses.
     *
     * @return the imtd level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdLevelValues> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the imtd level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd level valueses
     * @param end the upper bound of the range of imtd level valueses (not inclusive)
     * @return the range of imtd level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdLevelValues> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the imtd level valueses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of imtd level valueses
     * @param end the upper bound of the range of imtd level valueses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of imtd level valueses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImtdLevelValues> findAll(int start, int end,
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

        List<ImtdLevelValues> list = (List<ImtdLevelValues>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMTDLEVELVALUES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMTDLEVELVALUES;

                if (pagination) {
                    sql = sql.concat(ImtdLevelValuesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImtdLevelValues>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImtdLevelValues>(list);
                } else {
                    list = (List<ImtdLevelValues>) QueryUtil.list(q,
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
     * Removes all the imtd level valueses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImtdLevelValues imtdLevelValues : findAll()) {
            remove(imtdLevelValues);
        }
    }

    /**
     * Returns the number of imtd level valueses.
     *
     * @return the number of imtd level valueses
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

                Query q = session.createQuery(_SQL_COUNT_IMTDLEVELVALUES);

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
     * Initializes the imtd level values persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ImtdLevelValues")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImtdLevelValues>> listenersList = new ArrayList<ModelListener<ImtdLevelValues>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImtdLevelValues>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImtdLevelValuesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
