package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchMedicaidNewNdcException;
import com.stpl.app.model.MedicaidNewNdc;
import com.stpl.app.model.impl.MedicaidNewNdcImpl;
import com.stpl.app.model.impl.MedicaidNewNdcModelImpl;
import com.stpl.app.service.persistence.MedicaidNewNdcPersistence;

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
 * The persistence implementation for the medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidNewNdcPersistence
 * @see MedicaidNewNdcUtil
 * @generated
 */
public class MedicaidNewNdcPersistenceImpl extends BasePersistenceImpl<MedicaidNewNdc>
    implements MedicaidNewNdcPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MedicaidNewNdcUtil} to access the medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MedicaidNewNdcImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
            MedicaidNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
            MedicaidNewNdcImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MEDICAIDNEWNDC = "SELECT medicaidNewNdc FROM MedicaidNewNdc medicaidNewNdc";
    private static final String _SQL_COUNT_MEDICAIDNEWNDC = "SELECT COUNT(medicaidNewNdc) FROM MedicaidNewNdc medicaidNewNdc";
    private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidNewNdc.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidNewNdc exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MedicaidNewNdcPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastAmp", "forecastBestprice", "baseYearCpi", "ndc9",
                "wacPrice", "baseYearAmp"
            });
    private static MedicaidNewNdc _nullMedicaidNewNdc = new MedicaidNewNdcImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MedicaidNewNdc> toCacheModel() {
                return _nullMedicaidNewNdcCacheModel;
            }
        };

    private static CacheModel<MedicaidNewNdc> _nullMedicaidNewNdcCacheModel = new CacheModel<MedicaidNewNdc>() {
            @Override
            public MedicaidNewNdc toEntityModel() {
                return _nullMedicaidNewNdc;
            }
        };

    public MedicaidNewNdcPersistenceImpl() {
        setModelClass(MedicaidNewNdc.class);
    }

    /**
     * Caches the medicaid new ndc in the entity cache if it is enabled.
     *
     * @param medicaidNewNdc the medicaid new ndc
     */
    @Override
    public void cacheResult(MedicaidNewNdc medicaidNewNdc) {
        EntityCacheUtil.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey(),
            medicaidNewNdc);

        medicaidNewNdc.resetOriginalValues();
    }

    /**
     * Caches the medicaid new ndcs in the entity cache if it is enabled.
     *
     * @param medicaidNewNdcs the medicaid new ndcs
     */
    @Override
    public void cacheResult(List<MedicaidNewNdc> medicaidNewNdcs) {
        for (MedicaidNewNdc medicaidNewNdc : medicaidNewNdcs) {
            if (EntityCacheUtil.getResult(
                        MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey()) == null) {
                cacheResult(medicaidNewNdc);
            } else {
                medicaidNewNdc.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all medicaid new ndcs.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MedicaidNewNdcImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MedicaidNewNdcImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the medicaid new ndc.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MedicaidNewNdc medicaidNewNdc) {
        EntityCacheUtil.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MedicaidNewNdc> medicaidNewNdcs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MedicaidNewNdc medicaidNewNdc : medicaidNewNdcs) {
            EntityCacheUtil.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey());
        }
    }

    /**
     * Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
     *
     * @param ndc9 the primary key for the new medicaid new ndc
     * @return the new medicaid new ndc
     */
    @Override
    public MedicaidNewNdc create(String ndc9) {
        MedicaidNewNdc medicaidNewNdc = new MedicaidNewNdcImpl();

        medicaidNewNdc.setNew(true);
        medicaidNewNdc.setPrimaryKey(ndc9);

        return medicaidNewNdc;
    }

    /**
     * Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ndc9 the primary key of the medicaid new ndc
     * @return the medicaid new ndc that was removed
     * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc remove(String ndc9)
        throws NoSuchMedicaidNewNdcException, SystemException {
        return remove((Serializable) ndc9);
    }

    /**
     * Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the medicaid new ndc
     * @return the medicaid new ndc that was removed
     * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc remove(Serializable primaryKey)
        throws NoSuchMedicaidNewNdcException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MedicaidNewNdc medicaidNewNdc = (MedicaidNewNdc) session.get(MedicaidNewNdcImpl.class,
                    primaryKey);

            if (medicaidNewNdc == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(medicaidNewNdc);
        } catch (NoSuchMedicaidNewNdcException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MedicaidNewNdc removeImpl(MedicaidNewNdc medicaidNewNdc)
        throws SystemException {
        medicaidNewNdc = toUnwrappedModel(medicaidNewNdc);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(medicaidNewNdc)) {
                medicaidNewNdc = (MedicaidNewNdc) session.get(MedicaidNewNdcImpl.class,
                        medicaidNewNdc.getPrimaryKeyObj());
            }

            if (medicaidNewNdc != null) {
                session.delete(medicaidNewNdc);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (medicaidNewNdc != null) {
            clearCache(medicaidNewNdc);
        }

        return medicaidNewNdc;
    }

    @Override
    public MedicaidNewNdc updateImpl(
        com.stpl.app.model.MedicaidNewNdc medicaidNewNdc)
        throws SystemException {
        medicaidNewNdc = toUnwrappedModel(medicaidNewNdc);

        boolean isNew = medicaidNewNdc.isNew();

        Session session = null;

        try {
            session = openSession();

            if (medicaidNewNdc.isNew()) {
                session.save(medicaidNewNdc);

                medicaidNewNdc.setNew(false);
            } else {
                session.merge(medicaidNewNdc);
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

        EntityCacheUtil.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
            MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey(),
            medicaidNewNdc);

        return medicaidNewNdc;
    }

    protected MedicaidNewNdc toUnwrappedModel(MedicaidNewNdc medicaidNewNdc) {
        if (medicaidNewNdc instanceof MedicaidNewNdcImpl) {
            return medicaidNewNdc;
        }

        MedicaidNewNdcImpl medicaidNewNdcImpl = new MedicaidNewNdcImpl();

        medicaidNewNdcImpl.setNew(medicaidNewNdc.isNew());
        medicaidNewNdcImpl.setPrimaryKey(medicaidNewNdc.getPrimaryKey());

        medicaidNewNdcImpl.setForecastAmp(medicaidNewNdc.getForecastAmp());
        medicaidNewNdcImpl.setForecastBestprice(medicaidNewNdc.getForecastBestprice());
        medicaidNewNdcImpl.setBaseYearCpi(medicaidNewNdc.getBaseYearCpi());
        medicaidNewNdcImpl.setNdc9(medicaidNewNdc.getNdc9());
        medicaidNewNdcImpl.setWacPrice(medicaidNewNdc.getWacPrice());
        medicaidNewNdcImpl.setBaseYearAmp(medicaidNewNdc.getBaseYearAmp());

        return medicaidNewNdcImpl;
    }

    /**
     * Returns the medicaid new ndc with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid new ndc
     * @return the medicaid new ndc
     * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMedicaidNewNdcException, SystemException {
        MedicaidNewNdc medicaidNewNdc = fetchByPrimaryKey(primaryKey);

        if (medicaidNewNdc == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return medicaidNewNdc;
    }

    /**
     * Returns the medicaid new ndc with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidNewNdcException} if it could not be found.
     *
     * @param ndc9 the primary key of the medicaid new ndc
     * @return the medicaid new ndc
     * @throws com.stpl.app.NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc findByPrimaryKey(String ndc9)
        throws NoSuchMedicaidNewNdcException, SystemException {
        return findByPrimaryKey((Serializable) ndc9);
    }

    /**
     * Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the medicaid new ndc
     * @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MedicaidNewNdc medicaidNewNdc = (MedicaidNewNdc) EntityCacheUtil.getResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                MedicaidNewNdcImpl.class, primaryKey);

        if (medicaidNewNdc == _nullMedicaidNewNdc) {
            return null;
        }

        if (medicaidNewNdc == null) {
            Session session = null;

            try {
                session = openSession();

                medicaidNewNdc = (MedicaidNewNdc) session.get(MedicaidNewNdcImpl.class,
                        primaryKey);

                if (medicaidNewNdc != null) {
                    cacheResult(medicaidNewNdc);
                } else {
                    EntityCacheUtil.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                        MedicaidNewNdcImpl.class, primaryKey,
                        _nullMedicaidNewNdc);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
                    MedicaidNewNdcImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return medicaidNewNdc;
    }

    /**
     * Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ndc9 the primary key of the medicaid new ndc
     * @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MedicaidNewNdc fetchByPrimaryKey(String ndc9)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ndc9);
    }

    /**
     * Returns all the medicaid new ndcs.
     *
     * @return the medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidNewNdc> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the medicaid new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid new ndcs
     * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
     * @return the range of medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidNewNdc> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the medicaid new ndcs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of medicaid new ndcs
     * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of medicaid new ndcs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MedicaidNewNdc> findAll(int start, int end,
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

        List<MedicaidNewNdc> list = (List<MedicaidNewNdc>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MEDICAIDNEWNDC);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MEDICAIDNEWNDC;

                if (pagination) {
                    sql = sql.concat(MedicaidNewNdcModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MedicaidNewNdc>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MedicaidNewNdc>(list);
                } else {
                    list = (List<MedicaidNewNdc>) QueryUtil.list(q,
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
     * Removes all the medicaid new ndcs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MedicaidNewNdc medicaidNewNdc : findAll()) {
            remove(medicaidNewNdc);
        }
    }

    /**
     * Returns the number of medicaid new ndcs.
     *
     * @return the number of medicaid new ndcs
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

                Query q = session.createQuery(_SQL_COUNT_MEDICAIDNEWNDC);

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
     * Initializes the medicaid new ndc persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.MedicaidNewNdc")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MedicaidNewNdc>> listenersList = new ArrayList<ModelListener<MedicaidNewNdc>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MedicaidNewNdc>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MedicaidNewNdcImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
