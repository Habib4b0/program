package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;
import com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionImpl;
import com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl;
import com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionPersistence;

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
 * The persistence implementation for the ac fd adjustment selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelectionPersistence
 * @see AcFdAdjustmentSelectionUtil
 * @generated
 */
public class AcFdAdjustmentSelectionPersistenceImpl extends BasePersistenceImpl<AcFdAdjustmentSelection>
    implements AcFdAdjustmentSelectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AcFdAdjustmentSelectionUtil} to access the ac fd adjustment selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AcFdAdjustmentSelectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED,
            AcFdAdjustmentSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED,
            AcFdAdjustmentSelectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACFDADJUSTMENTSELECTION = "SELECT acFdAdjustmentSelection FROM AcFdAdjustmentSelection acFdAdjustmentSelection";
    private static final String _SQL_COUNT_ACFDADJUSTMENTSELECTION = "SELECT COUNT(acFdAdjustmentSelection) FROM AcFdAdjustmentSelection acFdAdjustmentSelection";
    private static final String _ORDER_BY_ENTITY_ALIAS = "acFdAdjustmentSelection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcFdAdjustmentSelection exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AcFdAdjustmentSelectionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "methodologyStartDate", "allocationMethod", "startDate",
                "totalFixedDollarAdj", "calculationFlag", "rateCorrection",
                "businessDays", "glImpactDate", "salesBasis", "releaseType",
                "accClosureMasterSid", "releaseAmount", "suggestedAdj",
                "methodologyEndDate"
            });
    private static AcFdAdjustmentSelection _nullAcFdAdjustmentSelection = new AcFdAdjustmentSelectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AcFdAdjustmentSelection> toCacheModel() {
                return _nullAcFdAdjustmentSelectionCacheModel;
            }
        };

    private static CacheModel<AcFdAdjustmentSelection> _nullAcFdAdjustmentSelectionCacheModel =
        new CacheModel<AcFdAdjustmentSelection>() {
            @Override
            public AcFdAdjustmentSelection toEntityModel() {
                return _nullAcFdAdjustmentSelection;
            }
        };

    public AcFdAdjustmentSelectionPersistenceImpl() {
        setModelClass(AcFdAdjustmentSelection.class);
    }

    /**
     * Caches the ac fd adjustment selection in the entity cache if it is enabled.
     *
     * @param acFdAdjustmentSelection the ac fd adjustment selection
     */
    @Override
    public void cacheResult(AcFdAdjustmentSelection acFdAdjustmentSelection) {
        EntityCacheUtil.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionImpl.class,
            acFdAdjustmentSelection.getPrimaryKey(), acFdAdjustmentSelection);

        acFdAdjustmentSelection.resetOriginalValues();
    }

    /**
     * Caches the ac fd adjustment selections in the entity cache if it is enabled.
     *
     * @param acFdAdjustmentSelections the ac fd adjustment selections
     */
    @Override
    public void cacheResult(
        List<AcFdAdjustmentSelection> acFdAdjustmentSelections) {
        for (AcFdAdjustmentSelection acFdAdjustmentSelection : acFdAdjustmentSelections) {
            if (EntityCacheUtil.getResult(
                        AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        AcFdAdjustmentSelectionImpl.class,
                        acFdAdjustmentSelection.getPrimaryKey()) == null) {
                cacheResult(acFdAdjustmentSelection);
            } else {
                acFdAdjustmentSelection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ac fd adjustment selections.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AcFdAdjustmentSelectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AcFdAdjustmentSelectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ac fd adjustment selection.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AcFdAdjustmentSelection acFdAdjustmentSelection) {
        EntityCacheUtil.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionImpl.class,
            acFdAdjustmentSelection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<AcFdAdjustmentSelection> acFdAdjustmentSelections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AcFdAdjustmentSelection acFdAdjustmentSelection : acFdAdjustmentSelections) {
            EntityCacheUtil.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
                AcFdAdjustmentSelectionImpl.class,
                acFdAdjustmentSelection.getPrimaryKey());
        }
    }

    /**
     * Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
     *
     * @param accClosureMasterSid the primary key for the new ac fd adjustment selection
     * @return the new ac fd adjustment selection
     */
    @Override
    public AcFdAdjustmentSelection create(int accClosureMasterSid) {
        AcFdAdjustmentSelection acFdAdjustmentSelection = new AcFdAdjustmentSelectionImpl();

        acFdAdjustmentSelection.setNew(true);
        acFdAdjustmentSelection.setPrimaryKey(accClosureMasterSid);

        return acFdAdjustmentSelection;
    }

    /**
     * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param accClosureMasterSid the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection remove(int accClosureMasterSid)
        throws NoSuchAcFdAdjustmentSelectionException, SystemException {
        return remove((Serializable) accClosureMasterSid);
    }

    /**
     * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection remove(Serializable primaryKey)
        throws NoSuchAcFdAdjustmentSelectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AcFdAdjustmentSelection acFdAdjustmentSelection = (AcFdAdjustmentSelection) session.get(AcFdAdjustmentSelectionImpl.class,
                    primaryKey);

            if (acFdAdjustmentSelection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAcFdAdjustmentSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(acFdAdjustmentSelection);
        } catch (NoSuchAcFdAdjustmentSelectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AcFdAdjustmentSelection removeImpl(
        AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws SystemException {
        acFdAdjustmentSelection = toUnwrappedModel(acFdAdjustmentSelection);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(acFdAdjustmentSelection)) {
                acFdAdjustmentSelection = (AcFdAdjustmentSelection) session.get(AcFdAdjustmentSelectionImpl.class,
                        acFdAdjustmentSelection.getPrimaryKeyObj());
            }

            if (acFdAdjustmentSelection != null) {
                session.delete(acFdAdjustmentSelection);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (acFdAdjustmentSelection != null) {
            clearCache(acFdAdjustmentSelection);
        }

        return acFdAdjustmentSelection;
    }

    @Override
    public AcFdAdjustmentSelection updateImpl(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws SystemException {
        acFdAdjustmentSelection = toUnwrappedModel(acFdAdjustmentSelection);

        boolean isNew = acFdAdjustmentSelection.isNew();

        Session session = null;

        try {
            session = openSession();

            if (acFdAdjustmentSelection.isNew()) {
                session.save(acFdAdjustmentSelection);

                acFdAdjustmentSelection.setNew(false);
            } else {
                session.merge(acFdAdjustmentSelection);
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

        EntityCacheUtil.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
            AcFdAdjustmentSelectionImpl.class,
            acFdAdjustmentSelection.getPrimaryKey(), acFdAdjustmentSelection);

        return acFdAdjustmentSelection;
    }

    protected AcFdAdjustmentSelection toUnwrappedModel(
        AcFdAdjustmentSelection acFdAdjustmentSelection) {
        if (acFdAdjustmentSelection instanceof AcFdAdjustmentSelectionImpl) {
            return acFdAdjustmentSelection;
        }

        AcFdAdjustmentSelectionImpl acFdAdjustmentSelectionImpl = new AcFdAdjustmentSelectionImpl();

        acFdAdjustmentSelectionImpl.setNew(acFdAdjustmentSelection.isNew());
        acFdAdjustmentSelectionImpl.setPrimaryKey(acFdAdjustmentSelection.getPrimaryKey());

        acFdAdjustmentSelectionImpl.setMethodologyStartDate(acFdAdjustmentSelection.getMethodologyStartDate());
        acFdAdjustmentSelectionImpl.setAllocationMethod(acFdAdjustmentSelection.getAllocationMethod());
        acFdAdjustmentSelectionImpl.setStartDate(acFdAdjustmentSelection.getStartDate());
        acFdAdjustmentSelectionImpl.setTotalFixedDollarAdj(acFdAdjustmentSelection.getTotalFixedDollarAdj());
        acFdAdjustmentSelectionImpl.setCalculationFlag(acFdAdjustmentSelection.isCalculationFlag());
        acFdAdjustmentSelectionImpl.setRateCorrection(acFdAdjustmentSelection.getRateCorrection());
        acFdAdjustmentSelectionImpl.setBusinessDays(acFdAdjustmentSelection.getBusinessDays());
        acFdAdjustmentSelectionImpl.setGlImpactDate(acFdAdjustmentSelection.getGlImpactDate());
        acFdAdjustmentSelectionImpl.setSalesBasis(acFdAdjustmentSelection.getSalesBasis());
        acFdAdjustmentSelectionImpl.setReleaseType(acFdAdjustmentSelection.isReleaseType());
        acFdAdjustmentSelectionImpl.setAccClosureMasterSid(acFdAdjustmentSelection.getAccClosureMasterSid());
        acFdAdjustmentSelectionImpl.setReleaseAmount(acFdAdjustmentSelection.getReleaseAmount());
        acFdAdjustmentSelectionImpl.setSuggestedAdj(acFdAdjustmentSelection.getSuggestedAdj());
        acFdAdjustmentSelectionImpl.setMethodologyEndDate(acFdAdjustmentSelection.getMethodologyEndDate());

        return acFdAdjustmentSelectionImpl;
    }

    /**
     * Returns the ac fd adjustment selection with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection
     * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAcFdAdjustmentSelectionException, SystemException {
        AcFdAdjustmentSelection acFdAdjustmentSelection = fetchByPrimaryKey(primaryKey);

        if (acFdAdjustmentSelection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAcFdAdjustmentSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return acFdAdjustmentSelection;
    }

    /**
     * Returns the ac fd adjustment selection with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException} if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection
     * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection findByPrimaryKey(int accClosureMasterSid)
        throws NoSuchAcFdAdjustmentSelectionException, SystemException {
        return findByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AcFdAdjustmentSelection acFdAdjustmentSelection = (AcFdAdjustmentSelection) EntityCacheUtil.getResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
                AcFdAdjustmentSelectionImpl.class, primaryKey);

        if (acFdAdjustmentSelection == _nullAcFdAdjustmentSelection) {
            return null;
        }

        if (acFdAdjustmentSelection == null) {
            Session session = null;

            try {
                session = openSession();

                acFdAdjustmentSelection = (AcFdAdjustmentSelection) session.get(AcFdAdjustmentSelectionImpl.class,
                        primaryKey);

                if (acFdAdjustmentSelection != null) {
                    cacheResult(acFdAdjustmentSelection);
                } else {
                    EntityCacheUtil.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
                        AcFdAdjustmentSelectionImpl.class, primaryKey,
                        _nullAcFdAdjustmentSelection);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
                    AcFdAdjustmentSelectionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return acFdAdjustmentSelection;
    }

    /**
     * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param accClosureMasterSid the primary key of the ac fd adjustment selection
     * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcFdAdjustmentSelection fetchByPrimaryKey(int accClosureMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) accClosureMasterSid);
    }

    /**
     * Returns all the ac fd adjustment selections.
     *
     * @return the ac fd adjustment selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcFdAdjustmentSelection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ac fd adjustment selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac fd adjustment selections
     * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
     * @return the range of ac fd adjustment selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcFdAdjustmentSelection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ac fd adjustment selections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac fd adjustment selections
     * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ac fd adjustment selections
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcFdAdjustmentSelection> findAll(int start, int end,
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

        List<AcFdAdjustmentSelection> list = (List<AcFdAdjustmentSelection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACFDADJUSTMENTSELECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACFDADJUSTMENTSELECTION;

                if (pagination) {
                    sql = sql.concat(AcFdAdjustmentSelectionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AcFdAdjustmentSelection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AcFdAdjustmentSelection>(list);
                } else {
                    list = (List<AcFdAdjustmentSelection>) QueryUtil.list(q,
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
     * Removes all the ac fd adjustment selections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AcFdAdjustmentSelection acFdAdjustmentSelection : findAll()) {
            remove(acFdAdjustmentSelection);
        }
    }

    /**
     * Returns the number of ac fd adjustment selections.
     *
     * @return the number of ac fd adjustment selections
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

                Query q = session.createQuery(_SQL_COUNT_ACFDADJUSTMENTSELECTION);

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
     * Initializes the ac fd adjustment selection persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AcFdAdjustmentSelection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AcFdAdjustmentSelection>> listenersList = new ArrayList<ModelListener<AcFdAdjustmentSelection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AcFdAdjustmentSelection>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AcFdAdjustmentSelectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
