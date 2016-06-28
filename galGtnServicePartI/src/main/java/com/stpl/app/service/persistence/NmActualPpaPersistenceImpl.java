package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNmActualPpaException;
import com.stpl.app.model.NmActualPpa;
import com.stpl.app.model.impl.NmActualPpaImpl;
import com.stpl.app.model.impl.NmActualPpaModelImpl;
import com.stpl.app.service.persistence.NmActualPpaPersistence;

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
 * The persistence implementation for the nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualPpaPersistence
 * @see NmActualPpaUtil
 * @generated
 */
public class NmActualPpaPersistenceImpl extends BasePersistenceImpl<NmActualPpa>
    implements NmActualPpaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NmActualPpaUtil} to access the nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NmActualPpaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaModelImpl.FINDER_CACHE_ENABLED, NmActualPpaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaModelImpl.FINDER_CACHE_ENABLED, NmActualPpaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NMACTUALPPA = "SELECT nmActualPpa FROM NmActualPpa nmActualPpa";
    private static final String _SQL_COUNT_NMACTUALPPA = "SELECT COUNT(nmActualPpa) FROM NmActualPpa nmActualPpa";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nmActualPpa.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmActualPpa exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NmActualPpaPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "actualRate", "periodSid", "actualProjDiscountDollar",
                "actualProjectionSales", "projectionDetailsSid",
                "actualProjectionRate", "actualProjDiscountUnits",
                "actualDiscountDollar", "actualDiscountUnits", "actualSales"
            });
    private static NmActualPpa _nullNmActualPpa = new NmActualPpaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NmActualPpa> toCacheModel() {
                return _nullNmActualPpaCacheModel;
            }
        };

    private static CacheModel<NmActualPpa> _nullNmActualPpaCacheModel = new CacheModel<NmActualPpa>() {
            @Override
            public NmActualPpa toEntityModel() {
                return _nullNmActualPpa;
            }
        };

    public NmActualPpaPersistenceImpl() {
        setModelClass(NmActualPpa.class);
    }

    /**
     * Caches the nm actual ppa in the entity cache if it is enabled.
     *
     * @param nmActualPpa the nm actual ppa
     */
    @Override
    public void cacheResult(NmActualPpa nmActualPpa) {
        EntityCacheUtil.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaImpl.class, nmActualPpa.getPrimaryKey(), nmActualPpa);

        nmActualPpa.resetOriginalValues();
    }

    /**
     * Caches the nm actual ppas in the entity cache if it is enabled.
     *
     * @param nmActualPpas the nm actual ppas
     */
    @Override
    public void cacheResult(List<NmActualPpa> nmActualPpas) {
        for (NmActualPpa nmActualPpa : nmActualPpas) {
            if (EntityCacheUtil.getResult(
                        NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                        NmActualPpaImpl.class, nmActualPpa.getPrimaryKey()) == null) {
                cacheResult(nmActualPpa);
            } else {
                nmActualPpa.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all nm actual ppas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NmActualPpaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NmActualPpaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the nm actual ppa.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NmActualPpa nmActualPpa) {
        EntityCacheUtil.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaImpl.class, nmActualPpa.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NmActualPpa> nmActualPpas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NmActualPpa nmActualPpa : nmActualPpas) {
            EntityCacheUtil.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                NmActualPpaImpl.class, nmActualPpa.getPrimaryKey());
        }
    }

    /**
     * Creates a new nm actual ppa with the primary key. Does not add the nm actual ppa to the database.
     *
     * @param nmActualPpaPK the primary key for the new nm actual ppa
     * @return the new nm actual ppa
     */
    @Override
    public NmActualPpa create(NmActualPpaPK nmActualPpaPK) {
        NmActualPpa nmActualPpa = new NmActualPpaImpl();

        nmActualPpa.setNew(true);
        nmActualPpa.setPrimaryKey(nmActualPpaPK);

        return nmActualPpa;
    }

    /**
     * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nmActualPpaPK the primary key of the nm actual ppa
     * @return the nm actual ppa that was removed
     * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa remove(NmActualPpaPK nmActualPpaPK)
        throws NoSuchNmActualPpaException, SystemException {
        return remove((Serializable) nmActualPpaPK);
    }

    /**
     * Removes the nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the nm actual ppa
     * @return the nm actual ppa that was removed
     * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa remove(Serializable primaryKey)
        throws NoSuchNmActualPpaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NmActualPpa nmActualPpa = (NmActualPpa) session.get(NmActualPpaImpl.class,
                    primaryKey);

            if (nmActualPpa == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nmActualPpa);
        } catch (NoSuchNmActualPpaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NmActualPpa removeImpl(NmActualPpa nmActualPpa)
        throws SystemException {
        nmActualPpa = toUnwrappedModel(nmActualPpa);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nmActualPpa)) {
                nmActualPpa = (NmActualPpa) session.get(NmActualPpaImpl.class,
                        nmActualPpa.getPrimaryKeyObj());
            }

            if (nmActualPpa != null) {
                session.delete(nmActualPpa);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nmActualPpa != null) {
            clearCache(nmActualPpa);
        }

        return nmActualPpa;
    }

    @Override
    public NmActualPpa updateImpl(com.stpl.app.model.NmActualPpa nmActualPpa)
        throws SystemException {
        nmActualPpa = toUnwrappedModel(nmActualPpa);

        boolean isNew = nmActualPpa.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nmActualPpa.isNew()) {
                session.save(nmActualPpa);

                nmActualPpa.setNew(false);
            } else {
                session.merge(nmActualPpa);
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

        EntityCacheUtil.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
            NmActualPpaImpl.class, nmActualPpa.getPrimaryKey(), nmActualPpa);

        return nmActualPpa;
    }

    protected NmActualPpa toUnwrappedModel(NmActualPpa nmActualPpa) {
        if (nmActualPpa instanceof NmActualPpaImpl) {
            return nmActualPpa;
        }

        NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

        nmActualPpaImpl.setNew(nmActualPpa.isNew());
        nmActualPpaImpl.setPrimaryKey(nmActualPpa.getPrimaryKey());

        nmActualPpaImpl.setActualRate(nmActualPpa.getActualRate());
        nmActualPpaImpl.setPeriodSid(nmActualPpa.getPeriodSid());
        nmActualPpaImpl.setActualProjDiscountDollar(nmActualPpa.getActualProjDiscountDollar());
        nmActualPpaImpl.setActualProjectionSales(nmActualPpa.getActualProjectionSales());
        nmActualPpaImpl.setProjectionDetailsSid(nmActualPpa.getProjectionDetailsSid());
        nmActualPpaImpl.setActualProjectionRate(nmActualPpa.getActualProjectionRate());
        nmActualPpaImpl.setActualProjDiscountUnits(nmActualPpa.getActualProjDiscountUnits());
        nmActualPpaImpl.setActualDiscountDollar(nmActualPpa.getActualDiscountDollar());
        nmActualPpaImpl.setActualDiscountUnits(nmActualPpa.getActualDiscountUnits());
        nmActualPpaImpl.setActualSales(nmActualPpa.getActualSales());

        return nmActualPpaImpl;
    }

    /**
     * Returns the nm actual ppa with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the nm actual ppa
     * @return the nm actual ppa
     * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNmActualPpaException, SystemException {
        NmActualPpa nmActualPpa = fetchByPrimaryKey(primaryKey);

        if (nmActualPpa == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nmActualPpa;
    }

    /**
     * Returns the nm actual ppa with the primary key or throws a {@link com.stpl.app.NoSuchNmActualPpaException} if it could not be found.
     *
     * @param nmActualPpaPK the primary key of the nm actual ppa
     * @return the nm actual ppa
     * @throws com.stpl.app.NoSuchNmActualPpaException if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa findByPrimaryKey(NmActualPpaPK nmActualPpaPK)
        throws NoSuchNmActualPpaException, SystemException {
        return findByPrimaryKey((Serializable) nmActualPpaPK);
    }

    /**
     * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the nm actual ppa
     * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NmActualPpa nmActualPpa = (NmActualPpa) EntityCacheUtil.getResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                NmActualPpaImpl.class, primaryKey);

        if (nmActualPpa == _nullNmActualPpa) {
            return null;
        }

        if (nmActualPpa == null) {
            Session session = null;

            try {
                session = openSession();

                nmActualPpa = (NmActualPpa) session.get(NmActualPpaImpl.class,
                        primaryKey);

                if (nmActualPpa != null) {
                    cacheResult(nmActualPpa);
                } else {
                    EntityCacheUtil.putResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                        NmActualPpaImpl.class, primaryKey, _nullNmActualPpa);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
                    NmActualPpaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nmActualPpa;
    }

    /**
     * Returns the nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nmActualPpaPK the primary key of the nm actual ppa
     * @return the nm actual ppa, or <code>null</code> if a nm actual ppa with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NmActualPpa fetchByPrimaryKey(NmActualPpaPK nmActualPpaPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) nmActualPpaPK);
    }

    /**
     * Returns all the nm actual ppas.
     *
     * @return the nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualPpa> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the nm actual ppas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm actual ppas
     * @param end the upper bound of the range of nm actual ppas (not inclusive)
     * @return the range of nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualPpa> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the nm actual ppas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of nm actual ppas
     * @param end the upper bound of the range of nm actual ppas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of nm actual ppas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NmActualPpa> findAll(int start, int end,
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

        List<NmActualPpa> list = (List<NmActualPpa>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NMACTUALPPA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NMACTUALPPA;

                if (pagination) {
                    sql = sql.concat(NmActualPpaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NmActualPpa>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NmActualPpa>(list);
                } else {
                    list = (List<NmActualPpa>) QueryUtil.list(q, getDialect(),
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
     * Removes all the nm actual ppas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NmActualPpa nmActualPpa : findAll()) {
            remove(nmActualPpa);
        }
    }

    /**
     * Returns the number of nm actual ppas.
     *
     * @return the number of nm actual ppas
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

                Query q = session.createQuery(_SQL_COUNT_NMACTUALPPA);

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
     * Initializes the nm actual ppa persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NmActualPpa")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NmActualPpa>> listenersList = new ArrayList<ModelListener<NmActualPpa>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NmActualPpa>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NmActualPpaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
