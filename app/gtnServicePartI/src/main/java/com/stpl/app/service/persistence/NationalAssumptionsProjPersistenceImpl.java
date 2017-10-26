package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNationalAssumptionsProjException;
import com.stpl.app.model.NationalAssumptionsProj;
import com.stpl.app.model.impl.NationalAssumptionsProjImpl;
import com.stpl.app.model.impl.NationalAssumptionsProjModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsProjPersistence;

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
 * The persistence implementation for the national assumptions proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsProjPersistence
 * @see NationalAssumptionsProjUtil
 * @generated
 */
public class NationalAssumptionsProjPersistenceImpl extends BasePersistenceImpl<NationalAssumptionsProj>
    implements NationalAssumptionsProjPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NationalAssumptionsProjUtil} to access the national assumptions proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsProjImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED,
            NationalAssumptionsProjImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NATIONALASSUMPTIONSPROJ = "SELECT nationalAssumptionsProj FROM NationalAssumptionsProj nationalAssumptionsProj";
    private static final String _SQL_COUNT_NATIONALASSUMPTIONSPROJ = "SELECT COUNT(nationalAssumptionsProj) FROM NationalAssumptionsProj nationalAssumptionsProj";
    private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptionsProj.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptionsProj exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NationalAssumptionsProjPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodSid", "itemMasterSid", "priceType", "projectionPrice"
            });
    private static NationalAssumptionsProj _nullNationalAssumptionsProj = new NationalAssumptionsProjImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NationalAssumptionsProj> toCacheModel() {
                return _nullNationalAssumptionsProjCacheModel;
            }
        };

    private static CacheModel<NationalAssumptionsProj> _nullNationalAssumptionsProjCacheModel =
        new CacheModel<NationalAssumptionsProj>() {
            @Override
            public NationalAssumptionsProj toEntityModel() {
                return _nullNationalAssumptionsProj;
            }
        };

    public NationalAssumptionsProjPersistenceImpl() {
        setModelClass(NationalAssumptionsProj.class);
    }

    /**
     * Caches the national assumptions proj in the entity cache if it is enabled.
     *
     * @param nationalAssumptionsProj the national assumptions proj
     */
    @Override
    public void cacheResult(NationalAssumptionsProj nationalAssumptionsProj) {
        EntityCacheUtil.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjImpl.class,
            nationalAssumptionsProj.getPrimaryKey(), nationalAssumptionsProj);

        nationalAssumptionsProj.resetOriginalValues();
    }

    /**
     * Caches the national assumptions projs in the entity cache if it is enabled.
     *
     * @param nationalAssumptionsProjs the national assumptions projs
     */
    @Override
    public void cacheResult(
        List<NationalAssumptionsProj> nationalAssumptionsProjs) {
        for (NationalAssumptionsProj nationalAssumptionsProj : nationalAssumptionsProjs) {
            if (EntityCacheUtil.getResult(
                        NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsProjImpl.class,
                        nationalAssumptionsProj.getPrimaryKey()) == null) {
                cacheResult(nationalAssumptionsProj);
            } else {
                nationalAssumptionsProj.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all national assumptions projs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NationalAssumptionsProjImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NationalAssumptionsProjImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the national assumptions proj.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NationalAssumptionsProj nationalAssumptionsProj) {
        EntityCacheUtil.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjImpl.class,
            nationalAssumptionsProj.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<NationalAssumptionsProj> nationalAssumptionsProjs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NationalAssumptionsProj nationalAssumptionsProj : nationalAssumptionsProjs) {
            EntityCacheUtil.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsProjImpl.class,
                nationalAssumptionsProj.getPrimaryKey());
        }
    }

    /**
     * Creates a new national assumptions proj with the primary key. Does not add the national assumptions proj to the database.
     *
     * @param nationalAssumptionsProjPK the primary key for the new national assumptions proj
     * @return the new national assumptions proj
     */
    @Override
    public NationalAssumptionsProj create(
        NationalAssumptionsProjPK nationalAssumptionsProjPK) {
        NationalAssumptionsProj nationalAssumptionsProj = new NationalAssumptionsProjImpl();

        nationalAssumptionsProj.setNew(true);
        nationalAssumptionsProj.setPrimaryKey(nationalAssumptionsProjPK);

        return nationalAssumptionsProj;
    }

    /**
     * Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
     * @return the national assumptions proj that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj remove(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws NoSuchNationalAssumptionsProjException, SystemException {
        return remove((Serializable) nationalAssumptionsProjPK);
    }

    /**
     * Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the national assumptions proj
     * @return the national assumptions proj that was removed
     * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj remove(Serializable primaryKey)
        throws NoSuchNationalAssumptionsProjException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NationalAssumptionsProj nationalAssumptionsProj = (NationalAssumptionsProj) session.get(NationalAssumptionsProjImpl.class,
                    primaryKey);

            if (nationalAssumptionsProj == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNationalAssumptionsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(nationalAssumptionsProj);
        } catch (NoSuchNationalAssumptionsProjException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NationalAssumptionsProj removeImpl(
        NationalAssumptionsProj nationalAssumptionsProj)
        throws SystemException {
        nationalAssumptionsProj = toUnwrappedModel(nationalAssumptionsProj);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(nationalAssumptionsProj)) {
                nationalAssumptionsProj = (NationalAssumptionsProj) session.get(NationalAssumptionsProjImpl.class,
                        nationalAssumptionsProj.getPrimaryKeyObj());
            }

            if (nationalAssumptionsProj != null) {
                session.delete(nationalAssumptionsProj);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (nationalAssumptionsProj != null) {
            clearCache(nationalAssumptionsProj);
        }

        return nationalAssumptionsProj;
    }

    @Override
    public NationalAssumptionsProj updateImpl(
        com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj)
        throws SystemException {
        nationalAssumptionsProj = toUnwrappedModel(nationalAssumptionsProj);

        boolean isNew = nationalAssumptionsProj.isNew();

        Session session = null;

        try {
            session = openSession();

            if (nationalAssumptionsProj.isNew()) {
                session.save(nationalAssumptionsProj);

                nationalAssumptionsProj.setNew(false);
            } else {
                session.merge(nationalAssumptionsProj);
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

        EntityCacheUtil.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
            NationalAssumptionsProjImpl.class,
            nationalAssumptionsProj.getPrimaryKey(), nationalAssumptionsProj);

        return nationalAssumptionsProj;
    }

    protected NationalAssumptionsProj toUnwrappedModel(
        NationalAssumptionsProj nationalAssumptionsProj) {
        if (nationalAssumptionsProj instanceof NationalAssumptionsProjImpl) {
            return nationalAssumptionsProj;
        }

        NationalAssumptionsProjImpl nationalAssumptionsProjImpl = new NationalAssumptionsProjImpl();

        nationalAssumptionsProjImpl.setNew(nationalAssumptionsProj.isNew());
        nationalAssumptionsProjImpl.setPrimaryKey(nationalAssumptionsProj.getPrimaryKey());

        nationalAssumptionsProjImpl.setPeriodSid(nationalAssumptionsProj.getPeriodSid());
        nationalAssumptionsProjImpl.setItemMasterSid(nationalAssumptionsProj.getItemMasterSid());
        nationalAssumptionsProjImpl.setPriceType(nationalAssumptionsProj.getPriceType());
        nationalAssumptionsProjImpl.setProjectionPrice(nationalAssumptionsProj.getProjectionPrice());

        return nationalAssumptionsProjImpl;
    }

    /**
     * Returns the national assumptions proj with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions proj
     * @return the national assumptions proj
     * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNationalAssumptionsProjException, SystemException {
        NationalAssumptionsProj nationalAssumptionsProj = fetchByPrimaryKey(primaryKey);

        if (nationalAssumptionsProj == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNationalAssumptionsProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return nationalAssumptionsProj;
    }

    /**
     * Returns the national assumptions proj with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsProjException} if it could not be found.
     *
     * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
     * @return the national assumptions proj
     * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj findByPrimaryKey(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws NoSuchNationalAssumptionsProjException, SystemException {
        return findByPrimaryKey((Serializable) nationalAssumptionsProjPK);
    }

    /**
     * Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the national assumptions proj
     * @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NationalAssumptionsProj nationalAssumptionsProj = (NationalAssumptionsProj) EntityCacheUtil.getResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
                NationalAssumptionsProjImpl.class, primaryKey);

        if (nationalAssumptionsProj == _nullNationalAssumptionsProj) {
            return null;
        }

        if (nationalAssumptionsProj == null) {
            Session session = null;

            try {
                session = openSession();

                nationalAssumptionsProj = (NationalAssumptionsProj) session.get(NationalAssumptionsProjImpl.class,
                        primaryKey);

                if (nationalAssumptionsProj != null) {
                    cacheResult(nationalAssumptionsProj);
                } else {
                    EntityCacheUtil.putResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
                        NationalAssumptionsProjImpl.class, primaryKey,
                        _nullNationalAssumptionsProj);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NationalAssumptionsProjModelImpl.ENTITY_CACHE_ENABLED,
                    NationalAssumptionsProjImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return nationalAssumptionsProj;
    }

    /**
     * Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
     * @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NationalAssumptionsProj fetchByPrimaryKey(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) nationalAssumptionsProjPK);
    }

    /**
     * Returns all the national assumptions projs.
     *
     * @return the national assumptions projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsProj> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the national assumptions projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptions projs
     * @param end the upper bound of the range of national assumptions projs (not inclusive)
     * @return the range of national assumptions projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsProj> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the national assumptions projs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of national assumptions projs
     * @param end the upper bound of the range of national assumptions projs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of national assumptions projs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NationalAssumptionsProj> findAll(int start, int end,
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

        List<NationalAssumptionsProj> list = (List<NationalAssumptionsProj>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NATIONALASSUMPTIONSPROJ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NATIONALASSUMPTIONSPROJ;

                if (pagination) {
                    sql = sql.concat(NationalAssumptionsProjModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NationalAssumptionsProj>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NationalAssumptionsProj>(list);
                } else {
                    list = (List<NationalAssumptionsProj>) QueryUtil.list(q,
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
     * Removes all the national assumptions projs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NationalAssumptionsProj nationalAssumptionsProj : findAll()) {
            remove(nationalAssumptionsProj);
        }
    }

    /**
     * Returns the number of national assumptions projs.
     *
     * @return the number of national assumptions projs
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

                Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONSPROJ);

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
     * Initializes the national assumptions proj persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NationalAssumptionsProj")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NationalAssumptionsProj>> listenersList = new ArrayList<ModelListener<NationalAssumptionsProj>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NationalAssumptionsProj>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NationalAssumptionsProjImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
