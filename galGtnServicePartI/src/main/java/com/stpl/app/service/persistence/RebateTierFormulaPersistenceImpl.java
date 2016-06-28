package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchRebateTierFormulaException;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.model.impl.RebateTierFormulaImpl;
import com.stpl.app.model.impl.RebateTierFormulaModelImpl;
import com.stpl.app.service.persistence.RebateTierFormulaPersistence;

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
 * The persistence implementation for the rebate tier formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebateTierFormulaPersistence
 * @see RebateTierFormulaUtil
 * @generated
 */
public class RebateTierFormulaPersistenceImpl extends BasePersistenceImpl<RebateTierFormula>
    implements RebateTierFormulaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RebateTierFormulaUtil} to access the rebate tier formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RebateTierFormulaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED,
            RebateTierFormulaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED,
            RebateTierFormulaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_REBATETIERFORMULA = "SELECT rebateTierFormula FROM RebateTierFormula rebateTierFormula";
    private static final String _SQL_COUNT_REBATETIERFORMULA = "SELECT COUNT(rebateTierFormula) FROM RebateTierFormula rebateTierFormula";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rebateTierFormula.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RebateTierFormula exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RebateTierFormulaPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "rebateTierFormulaNo", "rebateTierFormulaName",
                "rebatePlanMasterSid", "modifiedDate", "recordLockStatus",
                "source", "createdBy", "createdDate", "batchId",
                "rebateTierFormulaId", "inboundStatus", "modifiedBy",
                "rebateTierFormulaSid"
            });
    private static RebateTierFormula _nullRebateTierFormula = new RebateTierFormulaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RebateTierFormula> toCacheModel() {
                return _nullRebateTierFormulaCacheModel;
            }
        };

    private static CacheModel<RebateTierFormula> _nullRebateTierFormulaCacheModel =
        new CacheModel<RebateTierFormula>() {
            @Override
            public RebateTierFormula toEntityModel() {
                return _nullRebateTierFormula;
            }
        };

    public RebateTierFormulaPersistenceImpl() {
        setModelClass(RebateTierFormula.class);
    }

    /**
     * Caches the rebate tier formula in the entity cache if it is enabled.
     *
     * @param rebateTierFormula the rebate tier formula
     */
    @Override
    public void cacheResult(RebateTierFormula rebateTierFormula) {
        EntityCacheUtil.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey(),
            rebateTierFormula);

        rebateTierFormula.resetOriginalValues();
    }

    /**
     * Caches the rebate tier formulas in the entity cache if it is enabled.
     *
     * @param rebateTierFormulas the rebate tier formulas
     */
    @Override
    public void cacheResult(List<RebateTierFormula> rebateTierFormulas) {
        for (RebateTierFormula rebateTierFormula : rebateTierFormulas) {
            if (EntityCacheUtil.getResult(
                        RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
                        RebateTierFormulaImpl.class,
                        rebateTierFormula.getPrimaryKey()) == null) {
                cacheResult(rebateTierFormula);
            } else {
                rebateTierFormula.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all rebate tier formulas.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RebateTierFormulaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RebateTierFormulaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the rebate tier formula.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RebateTierFormula rebateTierFormula) {
        EntityCacheUtil.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RebateTierFormula> rebateTierFormulas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RebateTierFormula rebateTierFormula : rebateTierFormulas) {
            EntityCacheUtil.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
                RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey());
        }
    }

    /**
     * Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
     *
     * @param rebateTierFormulaSid the primary key for the new rebate tier formula
     * @return the new rebate tier formula
     */
    @Override
    public RebateTierFormula create(int rebateTierFormulaSid) {
        RebateTierFormula rebateTierFormula = new RebateTierFormulaImpl();

        rebateTierFormula.setNew(true);
        rebateTierFormula.setPrimaryKey(rebateTierFormulaSid);

        return rebateTierFormula;
    }

    /**
     * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param rebateTierFormulaSid the primary key of the rebate tier formula
     * @return the rebate tier formula that was removed
     * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula remove(int rebateTierFormulaSid)
        throws NoSuchRebateTierFormulaException, SystemException {
        return remove((Serializable) rebateTierFormulaSid);
    }

    /**
     * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the rebate tier formula
     * @return the rebate tier formula that was removed
     * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula remove(Serializable primaryKey)
        throws NoSuchRebateTierFormulaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RebateTierFormula rebateTierFormula = (RebateTierFormula) session.get(RebateTierFormulaImpl.class,
                    primaryKey);

            if (rebateTierFormula == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRebateTierFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rebateTierFormula);
        } catch (NoSuchRebateTierFormulaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RebateTierFormula removeImpl(RebateTierFormula rebateTierFormula)
        throws SystemException {
        rebateTierFormula = toUnwrappedModel(rebateTierFormula);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rebateTierFormula)) {
                rebateTierFormula = (RebateTierFormula) session.get(RebateTierFormulaImpl.class,
                        rebateTierFormula.getPrimaryKeyObj());
            }

            if (rebateTierFormula != null) {
                session.delete(rebateTierFormula);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rebateTierFormula != null) {
            clearCache(rebateTierFormula);
        }

        return rebateTierFormula;
    }

    @Override
    public RebateTierFormula updateImpl(
        com.stpl.app.model.RebateTierFormula rebateTierFormula)
        throws SystemException {
        rebateTierFormula = toUnwrappedModel(rebateTierFormula);

        boolean isNew = rebateTierFormula.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rebateTierFormula.isNew()) {
                session.save(rebateTierFormula);

                rebateTierFormula.setNew(false);
            } else {
                session.merge(rebateTierFormula);
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

        EntityCacheUtil.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
            RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey(),
            rebateTierFormula);

        return rebateTierFormula;
    }

    protected RebateTierFormula toUnwrappedModel(
        RebateTierFormula rebateTierFormula) {
        if (rebateTierFormula instanceof RebateTierFormulaImpl) {
            return rebateTierFormula;
        }

        RebateTierFormulaImpl rebateTierFormulaImpl = new RebateTierFormulaImpl();

        rebateTierFormulaImpl.setNew(rebateTierFormula.isNew());
        rebateTierFormulaImpl.setPrimaryKey(rebateTierFormula.getPrimaryKey());

        rebateTierFormulaImpl.setRebateTierFormulaNo(rebateTierFormula.getRebateTierFormulaNo());
        rebateTierFormulaImpl.setRebateTierFormulaName(rebateTierFormula.getRebateTierFormulaName());
        rebateTierFormulaImpl.setRebatePlanMasterSid(rebateTierFormula.getRebatePlanMasterSid());
        rebateTierFormulaImpl.setModifiedDate(rebateTierFormula.getModifiedDate());
        rebateTierFormulaImpl.setRecordLockStatus(rebateTierFormula.isRecordLockStatus());
        rebateTierFormulaImpl.setSource(rebateTierFormula.getSource());
        rebateTierFormulaImpl.setCreatedBy(rebateTierFormula.getCreatedBy());
        rebateTierFormulaImpl.setCreatedDate(rebateTierFormula.getCreatedDate());
        rebateTierFormulaImpl.setBatchId(rebateTierFormula.getBatchId());
        rebateTierFormulaImpl.setRebateTierFormulaId(rebateTierFormula.getRebateTierFormulaId());
        rebateTierFormulaImpl.setInboundStatus(rebateTierFormula.getInboundStatus());
        rebateTierFormulaImpl.setModifiedBy(rebateTierFormula.getModifiedBy());
        rebateTierFormulaImpl.setRebateTierFormulaSid(rebateTierFormula.getRebateTierFormulaSid());

        return rebateTierFormulaImpl;
    }

    /**
     * Returns the rebate tier formula with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the rebate tier formula
     * @return the rebate tier formula
     * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRebateTierFormulaException, SystemException {
        RebateTierFormula rebateTierFormula = fetchByPrimaryKey(primaryKey);

        if (rebateTierFormula == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRebateTierFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rebateTierFormula;
    }

    /**
     * Returns the rebate tier formula with the primary key or throws a {@link com.stpl.app.NoSuchRebateTierFormulaException} if it could not be found.
     *
     * @param rebateTierFormulaSid the primary key of the rebate tier formula
     * @return the rebate tier formula
     * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula findByPrimaryKey(int rebateTierFormulaSid)
        throws NoSuchRebateTierFormulaException, SystemException {
        return findByPrimaryKey((Serializable) rebateTierFormulaSid);
    }

    /**
     * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the rebate tier formula
     * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RebateTierFormula rebateTierFormula = (RebateTierFormula) EntityCacheUtil.getResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
                RebateTierFormulaImpl.class, primaryKey);

        if (rebateTierFormula == _nullRebateTierFormula) {
            return null;
        }

        if (rebateTierFormula == null) {
            Session session = null;

            try {
                session = openSession();

                rebateTierFormula = (RebateTierFormula) session.get(RebateTierFormulaImpl.class,
                        primaryKey);

                if (rebateTierFormula != null) {
                    cacheResult(rebateTierFormula);
                } else {
                    EntityCacheUtil.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
                        RebateTierFormulaImpl.class, primaryKey,
                        _nullRebateTierFormula);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
                    RebateTierFormulaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rebateTierFormula;
    }

    /**
     * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param rebateTierFormulaSid the primary key of the rebate tier formula
     * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RebateTierFormula fetchByPrimaryKey(int rebateTierFormulaSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) rebateTierFormulaSid);
    }

    /**
     * Returns all the rebate tier formulas.
     *
     * @return the rebate tier formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebateTierFormula> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the rebate tier formulas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate tier formulas
     * @param end the upper bound of the range of rebate tier formulas (not inclusive)
     * @return the range of rebate tier formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebateTierFormula> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the rebate tier formulas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of rebate tier formulas
     * @param end the upper bound of the range of rebate tier formulas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of rebate tier formulas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RebateTierFormula> findAll(int start, int end,
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

        List<RebateTierFormula> list = (List<RebateTierFormula>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_REBATETIERFORMULA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_REBATETIERFORMULA;

                if (pagination) {
                    sql = sql.concat(RebateTierFormulaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RebateTierFormula>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RebateTierFormula>(list);
                } else {
                    list = (List<RebateTierFormula>) QueryUtil.list(q,
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
     * Removes all the rebate tier formulas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RebateTierFormula rebateTierFormula : findAll()) {
            remove(rebateTierFormula);
        }
    }

    /**
     * Returns the number of rebate tier formulas.
     *
     * @return the number of rebate tier formulas
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

                Query q = session.createQuery(_SQL_COUNT_REBATETIERFORMULA);

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
     * Initializes the rebate tier formula persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.RebateTierFormula")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RebateTierFormula>> listenersList = new ArrayList<ModelListener<RebateTierFormula>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RebateTierFormula>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RebateTierFormulaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
