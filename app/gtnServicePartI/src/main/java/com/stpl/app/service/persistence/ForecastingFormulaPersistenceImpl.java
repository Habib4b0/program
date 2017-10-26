package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchForecastingFormulaException;
import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.model.impl.ForecastingFormulaImpl;
import com.stpl.app.model.impl.ForecastingFormulaModelImpl;
import com.stpl.app.service.persistence.ForecastingFormulaPersistence;

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
 * The persistence implementation for the forecasting formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingFormulaPersistence
 * @see ForecastingFormulaUtil
 * @generated
 */
public class ForecastingFormulaPersistenceImpl extends BasePersistenceImpl<ForecastingFormula>
    implements ForecastingFormulaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ForecastingFormulaUtil} to access the forecasting formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ForecastingFormulaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED,
            ForecastingFormulaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED,
            ForecastingFormulaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FORECASTINGFORMULA = "SELECT forecastingFormula FROM ForecastingFormula forecastingFormula";
    private static final String _SQL_COUNT_FORECASTINGFORMULA = "SELECT COUNT(forecastingFormula) FROM ForecastingFormula forecastingFormula";
    private static final String _ORDER_BY_ENTITY_ALIAS = "forecastingFormula.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastingFormula exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ForecastingFormulaPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "formulaType", "forecastingFormulaSid", "formula",
                "formulaNo", "modifiedDate", "isActive", "formulaName"
            });
    private static ForecastingFormula _nullForecastingFormula = new ForecastingFormulaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ForecastingFormula> toCacheModel() {
                return _nullForecastingFormulaCacheModel;
            }
        };

    private static CacheModel<ForecastingFormula> _nullForecastingFormulaCacheModel =
        new CacheModel<ForecastingFormula>() {
            @Override
            public ForecastingFormula toEntityModel() {
                return _nullForecastingFormula;
            }
        };

    public ForecastingFormulaPersistenceImpl() {
        setModelClass(ForecastingFormula.class);
    }

    /**
     * Caches the forecasting formula in the entity cache if it is enabled.
     *
     * @param forecastingFormula the forecasting formula
     */
    @Override
    public void cacheResult(ForecastingFormula forecastingFormula) {
        EntityCacheUtil.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey(),
            forecastingFormula);

        forecastingFormula.resetOriginalValues();
    }

    /**
     * Caches the forecasting formulas in the entity cache if it is enabled.
     *
     * @param forecastingFormulas the forecasting formulas
     */
    @Override
    public void cacheResult(List<ForecastingFormula> forecastingFormulas) {
        for (ForecastingFormula forecastingFormula : forecastingFormulas) {
            if (EntityCacheUtil.getResult(
                        ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingFormulaImpl.class,
                        forecastingFormula.getPrimaryKey()) == null) {
                cacheResult(forecastingFormula);
            } else {
                forecastingFormula.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all forecasting formulas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ForecastingFormulaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ForecastingFormulaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the forecasting formula.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ForecastingFormula forecastingFormula) {
        EntityCacheUtil.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ForecastingFormula> forecastingFormulas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ForecastingFormula forecastingFormula : forecastingFormulas) {
            EntityCacheUtil.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey());
        }
    }

    /**
     * Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
     *
     * @param forecastingFormulaSid the primary key for the new forecasting formula
     * @return the new forecasting formula
     */
    @Override
    public ForecastingFormula create(int forecastingFormulaSid) {
        ForecastingFormula forecastingFormula = new ForecastingFormulaImpl();

        forecastingFormula.setNew(true);
        forecastingFormula.setPrimaryKey(forecastingFormulaSid);

        return forecastingFormula;
    }

    /**
     * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param forecastingFormulaSid the primary key of the forecasting formula
     * @return the forecasting formula that was removed
     * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula remove(int forecastingFormulaSid)
        throws NoSuchForecastingFormulaException, SystemException {
        return remove((Serializable) forecastingFormulaSid);
    }

    /**
     * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the forecasting formula
     * @return the forecasting formula that was removed
     * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula remove(Serializable primaryKey)
        throws NoSuchForecastingFormulaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ForecastingFormula forecastingFormula = (ForecastingFormula) session.get(ForecastingFormulaImpl.class,
                    primaryKey);

            if (forecastingFormula == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchForecastingFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(forecastingFormula);
        } catch (NoSuchForecastingFormulaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ForecastingFormula removeImpl(
        ForecastingFormula forecastingFormula) throws SystemException {
        forecastingFormula = toUnwrappedModel(forecastingFormula);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(forecastingFormula)) {
                forecastingFormula = (ForecastingFormula) session.get(ForecastingFormulaImpl.class,
                        forecastingFormula.getPrimaryKeyObj());
            }

            if (forecastingFormula != null) {
                session.delete(forecastingFormula);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (forecastingFormula != null) {
            clearCache(forecastingFormula);
        }

        return forecastingFormula;
    }

    @Override
    public ForecastingFormula updateImpl(
        com.stpl.app.model.ForecastingFormula forecastingFormula)
        throws SystemException {
        forecastingFormula = toUnwrappedModel(forecastingFormula);

        boolean isNew = forecastingFormula.isNew();

        Session session = null;

        try {
            session = openSession();

            if (forecastingFormula.isNew()) {
                session.save(forecastingFormula);

                forecastingFormula.setNew(false);
            } else {
                session.merge(forecastingFormula);
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

        EntityCacheUtil.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
            ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey(),
            forecastingFormula);

        return forecastingFormula;
    }

    protected ForecastingFormula toUnwrappedModel(
        ForecastingFormula forecastingFormula) {
        if (forecastingFormula instanceof ForecastingFormulaImpl) {
            return forecastingFormula;
        }

        ForecastingFormulaImpl forecastingFormulaImpl = new ForecastingFormulaImpl();

        forecastingFormulaImpl.setNew(forecastingFormula.isNew());
        forecastingFormulaImpl.setPrimaryKey(forecastingFormula.getPrimaryKey());

        forecastingFormulaImpl.setCreatedDate(forecastingFormula.getCreatedDate());
        forecastingFormulaImpl.setFormulaType(forecastingFormula.getFormulaType());
        forecastingFormulaImpl.setForecastingFormulaSid(forecastingFormula.getForecastingFormulaSid());
        forecastingFormulaImpl.setFormula(forecastingFormula.getFormula());
        forecastingFormulaImpl.setFormulaNo(forecastingFormula.getFormulaNo());
        forecastingFormulaImpl.setModifiedDate(forecastingFormula.getModifiedDate());
        forecastingFormulaImpl.setIsActive(forecastingFormula.isIsActive());
        forecastingFormulaImpl.setFormulaName(forecastingFormula.getFormulaName());

        return forecastingFormulaImpl;
    }

    /**
     * Returns the forecasting formula with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting formula
     * @return the forecasting formula
     * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula findByPrimaryKey(Serializable primaryKey)
        throws NoSuchForecastingFormulaException, SystemException {
        ForecastingFormula forecastingFormula = fetchByPrimaryKey(primaryKey);

        if (forecastingFormula == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchForecastingFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return forecastingFormula;
    }

    /**
     * Returns the forecasting formula with the primary key or throws a {@link com.stpl.app.NoSuchForecastingFormulaException} if it could not be found.
     *
     * @param forecastingFormulaSid the primary key of the forecasting formula
     * @return the forecasting formula
     * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula findByPrimaryKey(int forecastingFormulaSid)
        throws NoSuchForecastingFormulaException, SystemException {
        return findByPrimaryKey((Serializable) forecastingFormulaSid);
    }

    /**
     * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the forecasting formula
     * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ForecastingFormula forecastingFormula = (ForecastingFormula) EntityCacheUtil.getResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
                ForecastingFormulaImpl.class, primaryKey);

        if (forecastingFormula == _nullForecastingFormula) {
            return null;
        }

        if (forecastingFormula == null) {
            Session session = null;

            try {
                session = openSession();

                forecastingFormula = (ForecastingFormula) session.get(ForecastingFormulaImpl.class,
                        primaryKey);

                if (forecastingFormula != null) {
                    cacheResult(forecastingFormula);
                } else {
                    EntityCacheUtil.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
                        ForecastingFormulaImpl.class, primaryKey,
                        _nullForecastingFormula);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
                    ForecastingFormulaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return forecastingFormula;
    }

    /**
     * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param forecastingFormulaSid the primary key of the forecasting formula
     * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ForecastingFormula fetchByPrimaryKey(int forecastingFormulaSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) forecastingFormulaSid);
    }

    /**
     * Returns all the forecasting formulas.
     *
     * @return the forecasting formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingFormula> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the forecasting formulas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting formulas
     * @param end the upper bound of the range of forecasting formulas (not inclusive)
     * @return the range of forecasting formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingFormula> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the forecasting formulas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of forecasting formulas
     * @param end the upper bound of the range of forecasting formulas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of forecasting formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ForecastingFormula> findAll(int start, int end,
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

        List<ForecastingFormula> list = (List<ForecastingFormula>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FORECASTINGFORMULA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FORECASTINGFORMULA;

                if (pagination) {
                    sql = sql.concat(ForecastingFormulaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ForecastingFormula>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ForecastingFormula>(list);
                } else {
                    list = (List<ForecastingFormula>) QueryUtil.list(q,
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
     * Removes all the forecasting formulas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ForecastingFormula forecastingFormula : findAll()) {
            remove(forecastingFormula);
        }
    }

    /**
     * Returns the number of forecasting formulas.
     *
     * @return the number of forecasting formulas
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

                Query q = session.createQuery(_SQL_COUNT_FORECASTINGFORMULA);

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
     * Initializes the forecasting formula persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.ForecastingFormula")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ForecastingFormula>> listenersList = new ArrayList<ModelListener<ForecastingFormula>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ForecastingFormula>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ForecastingFormulaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
