package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMedicaidUraActualsException;
import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.model.impl.MedicaidUraActualsImpl;
import com.stpl.app.model.impl.MedicaidUraActualsModelImpl;
import com.stpl.app.service.persistence.MedicaidUraActualsPersistence;

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
 * The persistence implementation for the medicaid ura actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraActualsPersistence
 * @see MedicaidUraActualsUtil
 * @generated
 */
public class MedicaidUraActualsPersistenceImpl extends BasePersistenceImpl<MedicaidUraActuals>
    implements MedicaidUraActualsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MedicaidUraActualsUtil} to access the medicaid ura actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MedicaidUraActualsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED,
            MedicaidUraActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED,
            MedicaidUraActualsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MEDICAIDURAACTUALS = "SELECT medicaidUraActuals FROM MedicaidUraActuals medicaidUraActuals";
    private static final String _SQL_COUNT_MEDICAIDURAACTUALS = "SELECT COUNT(medicaidUraActuals) FROM MedicaidUraActuals medicaidUraActuals";
    private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidUraActuals.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidUraActuals exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MedicaidUraActualsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodSid", "priceType", "actualPrice", "notes",
                "naProjDetailsSid", "baseYear"
            });
    private static MedicaidUraActuals _nullMedicaidUraActuals = new MedicaidUraActualsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MedicaidUraActuals> toCacheModel() {
                return _nullMedicaidUraActualsCacheModel;
            }
        };

    private static CacheModel<MedicaidUraActuals> _nullMedicaidUraActualsCacheModel =
        new CacheModel<MedicaidUraActuals>() {
            @Override
            public MedicaidUraActuals toEntityModel() {
                return _nullMedicaidUraActuals;
            }
        };

    public MedicaidUraActualsPersistenceImpl() {
        setModelClass(MedicaidUraActuals.class);
    }

    /**
     * Caches the medicaid ura actuals in the entity cache if it is enabled.
     *
     * @param medicaidUraActuals the medicaid ura actuals
     */
    @Override
    public void cacheResult(MedicaidUraActuals medicaidUraActuals) {
        EntityCacheUtil.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey(),
            medicaidUraActuals);

        medicaidUraActuals.resetOriginalValues();
    }

    /**
     * Caches the medicaid ura actualses in the entity cache if it is enabled.
     *
     * @param medicaidUraActualses the medicaid ura actualses
     */
    @Override
    public void cacheResult(List<MedicaidUraActuals> medicaidUraActualses) {
        for (MedicaidUraActuals medicaidUraActuals : medicaidUraActualses) {
            if (EntityCacheUtil.getResult(
                        MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidUraActualsImpl.class,
                        medicaidUraActuals.getPrimaryKey()) == null) {
                cacheResult(medicaidUraActuals);
            } else {
                medicaidUraActuals.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all medicaid ura actualses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MedicaidUraActualsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MedicaidUraActualsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the medicaid ura actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MedicaidUraActuals medicaidUraActuals) {
        EntityCacheUtil.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MedicaidUraActuals> medicaidUraActualses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MedicaidUraActuals medicaidUraActuals : medicaidUraActualses) {
            EntityCacheUtil.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey());
        }
    }

    /**
     * Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
     *
     * @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
     * @return the new medicaid ura actuals
     */
    @Override
    public MedicaidUraActuals create(MedicaidUraActualsPK medicaidUraActualsPK) {
        MedicaidUraActuals medicaidUraActuals = new MedicaidUraActualsImpl();

        medicaidUraActuals.setNew(true);
        medicaidUraActuals.setPrimaryKey(medicaidUraActualsPK);

        return medicaidUraActuals;
    }

    /**
     * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals that was removed
     * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals remove(MedicaidUraActualsPK medicaidUraActualsPK)
        throws NoSuchMedicaidUraActualsException, SystemException {
        return remove((Serializable) medicaidUraActualsPK);
    }

    /**
     * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals that was removed
     * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals remove(Serializable primaryKey)
        throws NoSuchMedicaidUraActualsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MedicaidUraActuals medicaidUraActuals = (MedicaidUraActuals) session.get(MedicaidUraActualsImpl.class,
                    primaryKey);

            if (medicaidUraActuals == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMedicaidUraActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(medicaidUraActuals);
        } catch (NoSuchMedicaidUraActualsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MedicaidUraActuals removeImpl(
        MedicaidUraActuals medicaidUraActuals) throws SystemException {
        medicaidUraActuals = toUnwrappedModel(medicaidUraActuals);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(medicaidUraActuals)) {
                medicaidUraActuals = (MedicaidUraActuals) session.get(MedicaidUraActualsImpl.class,
                        medicaidUraActuals.getPrimaryKeyObj());
            }

            if (medicaidUraActuals != null) {
                session.delete(medicaidUraActuals);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (medicaidUraActuals != null) {
            clearCache(medicaidUraActuals);
        }

        return medicaidUraActuals;
    }

    @Override
    public MedicaidUraActuals updateImpl(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws SystemException {
        medicaidUraActuals = toUnwrappedModel(medicaidUraActuals);

        boolean isNew = medicaidUraActuals.isNew();

        Session session = null;

        try {
            session = openSession();

            if (medicaidUraActuals.isNew()) {
                session.save(medicaidUraActuals);

                medicaidUraActuals.setNew(false);
            } else {
                session.merge(medicaidUraActuals);
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

        EntityCacheUtil.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey(),
            medicaidUraActuals);

        return medicaidUraActuals;
    }

    protected MedicaidUraActuals toUnwrappedModel(
        MedicaidUraActuals medicaidUraActuals) {
        if (medicaidUraActuals instanceof MedicaidUraActualsImpl) {
            return medicaidUraActuals;
        }

        MedicaidUraActualsImpl medicaidUraActualsImpl = new MedicaidUraActualsImpl();

        medicaidUraActualsImpl.setNew(medicaidUraActuals.isNew());
        medicaidUraActualsImpl.setPrimaryKey(medicaidUraActuals.getPrimaryKey());

        medicaidUraActualsImpl.setPeriodSid(medicaidUraActuals.getPeriodSid());
        medicaidUraActualsImpl.setPriceType(medicaidUraActuals.getPriceType());
        medicaidUraActualsImpl.setActualPrice(medicaidUraActuals.getActualPrice());
        medicaidUraActualsImpl.setNotes(medicaidUraActuals.getNotes());
        medicaidUraActualsImpl.setNaProjDetailsSid(medicaidUraActuals.getNaProjDetailsSid());
        medicaidUraActualsImpl.setBaseYear(medicaidUraActuals.getBaseYear());

        return medicaidUraActualsImpl;
    }

    /**
     * Returns the medicaid ura actuals with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals
     * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMedicaidUraActualsException, SystemException {
        MedicaidUraActuals medicaidUraActuals = fetchByPrimaryKey(primaryKey);

        if (medicaidUraActuals == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMedicaidUraActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return medicaidUraActuals;
    }

    /**
     * Returns the medicaid ura actuals with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidUraActualsException} if it could not be found.
     *
     * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals
     * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals findByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws NoSuchMedicaidUraActualsException, SystemException {
        return findByPrimaryKey((Serializable) medicaidUraActualsPK);
    }

    /**
     * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MedicaidUraActuals medicaidUraActuals = (MedicaidUraActuals) EntityCacheUtil.getResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidUraActualsImpl.class, primaryKey);

        if (medicaidUraActuals == _nullMedicaidUraActuals) {
            return null;
        }

        if (medicaidUraActuals == null) {
            Session session = null;

            try {
                session = openSession();

                medicaidUraActuals = (MedicaidUraActuals) session.get(MedicaidUraActualsImpl.class,
                        primaryKey);

                if (medicaidUraActuals != null) {
                    cacheResult(medicaidUraActuals);
                } else {
                    EntityCacheUtil.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidUraActualsImpl.class, primaryKey,
                        _nullMedicaidUraActuals);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
                    MedicaidUraActualsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return medicaidUraActuals;
    }

    /**
     * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
     * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidUraActuals fetchByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) medicaidUraActualsPK);
    }

    /**
     * Returns all the medicaid ura actualses.
     *
     * @return the medicaid ura actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraActuals> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the medicaid ura actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid ura actualses
     * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
     * @return the range of medicaid ura actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraActuals> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the medicaid ura actualses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid ura actualses
     * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of medicaid ura actualses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidUraActuals> findAll(int start, int end,
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

        List<MedicaidUraActuals> list = (List<MedicaidUraActuals>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MEDICAIDURAACTUALS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MEDICAIDURAACTUALS;

                if (pagination) {
                    sql = sql.concat(MedicaidUraActualsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MedicaidUraActuals>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MedicaidUraActuals>(list);
                } else {
                    list = (List<MedicaidUraActuals>) QueryUtil.list(q,
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
     * Removes all the medicaid ura actualses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MedicaidUraActuals medicaidUraActuals : findAll()) {
            remove(medicaidUraActuals);
        }
    }

    /**
     * Returns the number of medicaid ura actualses.
     *
     * @return the number of medicaid ura actualses
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

                Query q = session.createQuery(_SQL_COUNT_MEDICAIDURAACTUALS);

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
     * Initializes the medicaid ura actuals persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MedicaidUraActuals")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MedicaidUraActuals>> listenersList = new ArrayList<ModelListener<MedicaidUraActuals>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MedicaidUraActuals>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MedicaidUraActualsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
