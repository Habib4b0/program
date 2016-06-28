package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchFcpProjException;
import com.stpl.app.model.FcpProj;
import com.stpl.app.model.impl.FcpProjImpl;
import com.stpl.app.model.impl.FcpProjModelImpl;
import com.stpl.app.service.persistence.FcpProjPersistence;

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
 * The persistence implementation for the fcp proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpProjPersistence
 * @see FcpProjUtil
 * @generated
 */
public class FcpProjPersistenceImpl extends BasePersistenceImpl<FcpProj>
    implements FcpProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FcpProjUtil} to access the fcp proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FcpProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjModelImpl.FINDER_CACHE_ENABLED, FcpProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjModelImpl.FINDER_CACHE_ENABLED, FcpProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FCPPROJ = "SELECT fcpProj FROM FcpProj fcpProj";
    private static final String _SQL_COUNT_FCPPROJ = "SELECT COUNT(fcpProj) FROM FcpProj fcpProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "fcpProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FcpProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FcpProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustment", "periodSid", "priceType", "projectionPrice",
                "notes", "naProjDetailsSid"
            });
    private static FcpProj _nullFcpProj = new FcpProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FcpProj> toCacheModel() {
                return _nullFcpProjCacheModel;
            }
        };

    private static CacheModel<FcpProj> _nullFcpProjCacheModel = new CacheModel<FcpProj>() {
            @Override
            public FcpProj toEntityModel() {
                return _nullFcpProj;
            }
        };

    public FcpProjPersistenceImpl() {
        setModelClass(FcpProj.class);
    }

    /**
     * Caches the fcp proj in the entity cache if it is enabled.
     *
     * @param fcpProj the fcp proj
     */
    @Override
    public void cacheResult(FcpProj fcpProj) {
        EntityCacheUtil.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjImpl.class, fcpProj.getPrimaryKey(), fcpProj);

        fcpProj.resetOriginalValues();
    }

    /**
     * Caches the fcp projs in the entity cache if it is enabled.
     *
     * @param fcpProjs the fcp projs
     */
    @Override
    public void cacheResult(List<FcpProj> fcpProjs) {
        for (FcpProj fcpProj : fcpProjs) {
            if (EntityCacheUtil.getResult(
                        FcpProjModelImpl.ENTITY_CACHE_ENABLED,
                        FcpProjImpl.class, fcpProj.getPrimaryKey()) == null) {
                cacheResult(fcpProj);
            } else {
                fcpProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all fcp projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FcpProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FcpProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the fcp proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FcpProj fcpProj) {
        EntityCacheUtil.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjImpl.class, fcpProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FcpProj> fcpProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FcpProj fcpProj : fcpProjs) {
            EntityCacheUtil.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
                FcpProjImpl.class, fcpProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
     *
     * @param fcpProjPK the primary key for the new fcp proj
     * @return the new fcp proj
     */
    @Override
    public FcpProj create(FcpProjPK fcpProjPK) {
        FcpProj fcpProj = new FcpProjImpl();

        fcpProj.setNew(true);
        fcpProj.setPrimaryKey(fcpProjPK);

        return fcpProj;
    }

    /**
     * Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param fcpProjPK the primary key of the fcp proj
     * @return the fcp proj that was removed
     * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj remove(FcpProjPK fcpProjPK)
        throws NoSuchFcpProjException, SystemException {
        return remove((Serializable) fcpProjPK);
    }

    /**
     * Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the fcp proj
     * @return the fcp proj that was removed
     * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj remove(Serializable primaryKey)
        throws NoSuchFcpProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FcpProj fcpProj = (FcpProj) session.get(FcpProjImpl.class,
                    primaryKey);

            if (fcpProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFcpProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(fcpProj);
        } catch (NoSuchFcpProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FcpProj removeImpl(FcpProj fcpProj) throws SystemException {
        fcpProj = toUnwrappedModel(fcpProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(fcpProj)) {
                fcpProj = (FcpProj) session.get(FcpProjImpl.class,
                        fcpProj.getPrimaryKeyObj());
            }

            if (fcpProj != null) {
                session.delete(fcpProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (fcpProj != null) {
            clearCache(fcpProj);
        }

        return fcpProj;
    }

    @Override
    public FcpProj updateImpl(com.stpl.app.model.FcpProj fcpProj)
        throws SystemException {
        fcpProj = toUnwrappedModel(fcpProj);

        boolean isNew = fcpProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (fcpProj.isNew()) {
                session.save(fcpProj);

                fcpProj.setNew(false);
            } else {
                session.merge(fcpProj);
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

        EntityCacheUtil.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
            FcpProjImpl.class, fcpProj.getPrimaryKey(), fcpProj);

        return fcpProj;
    }

    protected FcpProj toUnwrappedModel(FcpProj fcpProj) {
        if (fcpProj instanceof FcpProjImpl) {
            return fcpProj;
        }

        FcpProjImpl fcpProjImpl = new FcpProjImpl();

        fcpProjImpl.setNew(fcpProj.isNew());
        fcpProjImpl.setPrimaryKey(fcpProj.getPrimaryKey());

        fcpProjImpl.setAdjustment(fcpProj.getAdjustment());
        fcpProjImpl.setPeriodSid(fcpProj.getPeriodSid());
        fcpProjImpl.setPriceType(fcpProj.getPriceType());
        fcpProjImpl.setProjectionPrice(fcpProj.getProjectionPrice());
        fcpProjImpl.setNotes(fcpProj.getNotes());
        fcpProjImpl.setNaProjDetailsSid(fcpProj.getNaProjDetailsSid());

        return fcpProjImpl;
    }

    /**
     * Returns the fcp proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the fcp proj
     * @return the fcp proj
     * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchFcpProjException, SystemException {
        FcpProj fcpProj = fetchByPrimaryKey(primaryKey);

        if (fcpProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchFcpProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return fcpProj;
    }

    /**
     * Returns the fcp proj with the primary key or throws a {@link com.stpl.app.NoSuchFcpProjException} if it could not be found.
     *
     * @param fcpProjPK the primary key of the fcp proj
     * @return the fcp proj
     * @throws com.stpl.app.NoSuchFcpProjException if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj findByPrimaryKey(FcpProjPK fcpProjPK)
        throws NoSuchFcpProjException, SystemException {
        return findByPrimaryKey((Serializable) fcpProjPK);
    }

    /**
     * Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the fcp proj
     * @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        FcpProj fcpProj = (FcpProj) EntityCacheUtil.getResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
                FcpProjImpl.class, primaryKey);

        if (fcpProj == _nullFcpProj) {
            return null;
        }

        if (fcpProj == null) {
            Session session = null;

            try {
                session = openSession();

                fcpProj = (FcpProj) session.get(FcpProjImpl.class, primaryKey);

                if (fcpProj != null) {
                    cacheResult(fcpProj);
                } else {
                    EntityCacheUtil.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
                        FcpProjImpl.class, primaryKey, _nullFcpProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
                    FcpProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return fcpProj;
    }

    /**
     * Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param fcpProjPK the primary key of the fcp proj
     * @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FcpProj fetchByPrimaryKey(FcpProjPK fcpProjPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) fcpProjPK);
    }

    /**
     * Returns all the fcp projs.
     *
     * @return the fcp projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the fcp projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of fcp projs
     * @param end the upper bound of the range of fcp projs (not inclusive)
     * @return the range of fcp projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpProj> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the fcp projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of fcp projs
     * @param end the upper bound of the range of fcp projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of fcp projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<FcpProj> findAll(int start, int end,
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

        List<FcpProj> list = (List<FcpProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FCPPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FCPPROJ;

                if (pagination) {
                    sql = sql.concat(FcpProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<FcpProj>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<FcpProj>(list);
                } else {
                    list = (List<FcpProj>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the fcp projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (FcpProj fcpProj : findAll()) {
            remove(fcpProj);
        }
    }

    /**
     * Returns the number of fcp projs.
     *
     * @return the number of fcp projs
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

                Query q = session.createQuery(_SQL_COUNT_FCPPROJ);

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
     * Initializes the fcp proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.FcpProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FcpProj>> listenersList = new ArrayList<ModelListener<FcpProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FcpProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FcpProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
