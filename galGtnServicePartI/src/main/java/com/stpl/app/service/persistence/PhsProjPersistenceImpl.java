package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchPhsProjException;
import com.stpl.app.model.PhsProj;
import com.stpl.app.model.impl.PhsProjImpl;
import com.stpl.app.model.impl.PhsProjModelImpl;
import com.stpl.app.service.persistence.PhsProjPersistence;

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
 * The persistence implementation for the phs proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsProjPersistence
 * @see PhsProjUtil
 * @generated
 */
public class PhsProjPersistenceImpl extends BasePersistenceImpl<PhsProj>
    implements PhsProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PhsProjUtil} to access the phs proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PhsProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjModelImpl.FINDER_CACHE_ENABLED, PhsProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjModelImpl.FINDER_CACHE_ENABLED, PhsProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PHSPROJ = "SELECT phsProj FROM PhsProj phsProj";
    private static final String _SQL_COUNT_PHSPROJ = "SELECT COUNT(phsProj) FROM PhsProj phsProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "phsProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PhsProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PhsProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "adjustment", "periodSid", "priceType", "projectionPrice",
                "notes", "naProjDetailsSid"
            });
    private static PhsProj _nullPhsProj = new PhsProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PhsProj> toCacheModel() {
                return _nullPhsProjCacheModel;
            }
        };

    private static CacheModel<PhsProj> _nullPhsProjCacheModel = new CacheModel<PhsProj>() {
            @Override
            public PhsProj toEntityModel() {
                return _nullPhsProj;
            }
        };

    public PhsProjPersistenceImpl() {
        setModelClass(PhsProj.class);
    }

    /**
     * Caches the phs proj in the entity cache if it is enabled.
     *
     * @param phsProj the phs proj
     */
    @Override
    public void cacheResult(PhsProj phsProj) {
        EntityCacheUtil.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjImpl.class, phsProj.getPrimaryKey(), phsProj);

        phsProj.resetOriginalValues();
    }

    /**
     * Caches the phs projs in the entity cache if it is enabled.
     *
     * @param phsProjs the phs projs
     */
    @Override
    public void cacheResult(List<PhsProj> phsProjs) {
        for (PhsProj phsProj : phsProjs) {
            if (EntityCacheUtil.getResult(
                        PhsProjModelImpl.ENTITY_CACHE_ENABLED,
                        PhsProjImpl.class, phsProj.getPrimaryKey()) == null) {
                cacheResult(phsProj);
            } else {
                phsProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all phs projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PhsProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PhsProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the phs proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PhsProj phsProj) {
        EntityCacheUtil.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjImpl.class, phsProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PhsProj> phsProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PhsProj phsProj : phsProjs) {
            EntityCacheUtil.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
                PhsProjImpl.class, phsProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new phs proj with the primary key. Does not add the phs proj to the database.
     *
     * @param phsProjPK the primary key for the new phs proj
     * @return the new phs proj
     */
    @Override
    public PhsProj create(PhsProjPK phsProjPK) {
        PhsProj phsProj = new PhsProjImpl();

        phsProj.setNew(true);
        phsProj.setPrimaryKey(phsProjPK);

        return phsProj;
    }

    /**
     * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param phsProjPK the primary key of the phs proj
     * @return the phs proj that was removed
     * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj remove(PhsProjPK phsProjPK)
        throws NoSuchPhsProjException, SystemException {
        return remove((Serializable) phsProjPK);
    }

    /**
     * Removes the phs proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the phs proj
     * @return the phs proj that was removed
     * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj remove(Serializable primaryKey)
        throws NoSuchPhsProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PhsProj phsProj = (PhsProj) session.get(PhsProjImpl.class,
                    primaryKey);

            if (phsProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPhsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(phsProj);
        } catch (NoSuchPhsProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PhsProj removeImpl(PhsProj phsProj) throws SystemException {
        phsProj = toUnwrappedModel(phsProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(phsProj)) {
                phsProj = (PhsProj) session.get(PhsProjImpl.class,
                        phsProj.getPrimaryKeyObj());
            }

            if (phsProj != null) {
                session.delete(phsProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (phsProj != null) {
            clearCache(phsProj);
        }

        return phsProj;
    }

    @Override
    public PhsProj updateImpl(com.stpl.app.model.PhsProj phsProj)
        throws SystemException {
        phsProj = toUnwrappedModel(phsProj);

        boolean isNew = phsProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (phsProj.isNew()) {
                session.save(phsProj);

                phsProj.setNew(false);
            } else {
                session.merge(phsProj);
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

        EntityCacheUtil.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
            PhsProjImpl.class, phsProj.getPrimaryKey(), phsProj);

        return phsProj;
    }

    protected PhsProj toUnwrappedModel(PhsProj phsProj) {
        if (phsProj instanceof PhsProjImpl) {
            return phsProj;
        }

        PhsProjImpl phsProjImpl = new PhsProjImpl();

        phsProjImpl.setNew(phsProj.isNew());
        phsProjImpl.setPrimaryKey(phsProj.getPrimaryKey());

        phsProjImpl.setAdjustment(phsProj.getAdjustment());
        phsProjImpl.setPeriodSid(phsProj.getPeriodSid());
        phsProjImpl.setPriceType(phsProj.getPriceType());
        phsProjImpl.setProjectionPrice(phsProj.getProjectionPrice());
        phsProjImpl.setNotes(phsProj.getNotes());
        phsProjImpl.setNaProjDetailsSid(phsProj.getNaProjDetailsSid());

        return phsProjImpl;
    }

    /**
     * Returns the phs proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the phs proj
     * @return the phs proj
     * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPhsProjException, SystemException {
        PhsProj phsProj = fetchByPrimaryKey(primaryKey);

        if (phsProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPhsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return phsProj;
    }

    /**
     * Returns the phs proj with the primary key or throws a {@link com.stpl.app.NoSuchPhsProjException} if it could not be found.
     *
     * @param phsProjPK the primary key of the phs proj
     * @return the phs proj
     * @throws com.stpl.app.NoSuchPhsProjException if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj findByPrimaryKey(PhsProjPK phsProjPK)
        throws NoSuchPhsProjException, SystemException {
        return findByPrimaryKey((Serializable) phsProjPK);
    }

    /**
     * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the phs proj
     * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PhsProj phsProj = (PhsProj) EntityCacheUtil.getResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
                PhsProjImpl.class, primaryKey);

        if (phsProj == _nullPhsProj) {
            return null;
        }

        if (phsProj == null) {
            Session session = null;

            try {
                session = openSession();

                phsProj = (PhsProj) session.get(PhsProjImpl.class, primaryKey);

                if (phsProj != null) {
                    cacheResult(phsProj);
                } else {
                    EntityCacheUtil.putResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
                        PhsProjImpl.class, primaryKey, _nullPhsProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PhsProjModelImpl.ENTITY_CACHE_ENABLED,
                    PhsProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return phsProj;
    }

    /**
     * Returns the phs proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param phsProjPK the primary key of the phs proj
     * @return the phs proj, or <code>null</code> if a phs proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PhsProj fetchByPrimaryKey(PhsProjPK phsProjPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) phsProjPK);
    }

    /**
     * Returns all the phs projs.
     *
     * @return the phs projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PhsProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the phs projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of phs projs
     * @param end the upper bound of the range of phs projs (not inclusive)
     * @return the range of phs projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PhsProj> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the phs projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PhsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of phs projs
     * @param end the upper bound of the range of phs projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of phs projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PhsProj> findAll(int start, int end,
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

        List<PhsProj> list = (List<PhsProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PHSPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PHSPROJ;

                if (pagination) {
                    sql = sql.concat(PhsProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PhsProj>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PhsProj>(list);
                } else {
                    list = (List<PhsProj>) QueryUtil.list(q, getDialect(),
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
     * Removes all the phs projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PhsProj phsProj : findAll()) {
            remove(phsProj);
        }
    }

    /**
     * Returns the number of phs projs.
     *
     * @return the number of phs projs
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

                Query q = session.createQuery(_SQL_COUNT_PHSPROJ);

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
     * Initializes the phs proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.PhsProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PhsProj>> listenersList = new ArrayList<ModelListener<PhsProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PhsProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PhsProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
