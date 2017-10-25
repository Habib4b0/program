package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException;
import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsImpl;
import com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsPersistence;

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
 * The persistence implementation for the ac br methodology details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsPersistence
 * @see AcBrMethodologyDetailsUtil
 * @generated
 */
public class AcBrMethodologyDetailsPersistenceImpl extends BasePersistenceImpl<AcBrMethodologyDetails>
    implements AcBrMethodologyDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AcBrMethodologyDetailsUtil} to access the ac br methodology details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AcBrMethodologyDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED,
            AcBrMethodologyDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED,
            AcBrMethodologyDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ACBRMETHODOLOGYDETAILS = "SELECT acBrMethodologyDetails FROM AcBrMethodologyDetails acBrMethodologyDetails";
    private static final String _SQL_COUNT_ACBRMETHODOLOGYDETAILS = "SELECT COUNT(acBrMethodologyDetails) FROM AcBrMethodologyDetails acBrMethodologyDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "acBrMethodologyDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcBrMethodologyDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AcBrMethodologyDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "salesGrowthRate", "methodologyStartDate", "frequency",
                "calculationFlag", "provisionGrowthRate", "salesBasis",
                "acBrMethodologyDetailsSid", "accClosureMasterSid",
                "methodologyEndDate", "methodologyValue", "dampingFactor",
                "methodologyName"
            });
    private static AcBrMethodologyDetails _nullAcBrMethodologyDetails = new AcBrMethodologyDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AcBrMethodologyDetails> toCacheModel() {
                return _nullAcBrMethodologyDetailsCacheModel;
            }
        };

    private static CacheModel<AcBrMethodologyDetails> _nullAcBrMethodologyDetailsCacheModel =
        new CacheModel<AcBrMethodologyDetails>() {
            @Override
            public AcBrMethodologyDetails toEntityModel() {
                return _nullAcBrMethodologyDetails;
            }
        };

    public AcBrMethodologyDetailsPersistenceImpl() {
        setModelClass(AcBrMethodologyDetails.class);
    }

    /**
     * Caches the ac br methodology details in the entity cache if it is enabled.
     *
     * @param acBrMethodologyDetails the ac br methodology details
     */
    @Override
    public void cacheResult(AcBrMethodologyDetails acBrMethodologyDetails) {
        EntityCacheUtil.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsImpl.class,
            acBrMethodologyDetails.getPrimaryKey(), acBrMethodologyDetails);

        acBrMethodologyDetails.resetOriginalValues();
    }

    /**
     * Caches the ac br methodology detailses in the entity cache if it is enabled.
     *
     * @param acBrMethodologyDetailses the ac br methodology detailses
     */
    @Override
    public void cacheResult(
        List<AcBrMethodologyDetails> acBrMethodologyDetailses) {
        for (AcBrMethodologyDetails acBrMethodologyDetails : acBrMethodologyDetailses) {
            if (EntityCacheUtil.getResult(
                        AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AcBrMethodologyDetailsImpl.class,
                        acBrMethodologyDetails.getPrimaryKey()) == null) {
                cacheResult(acBrMethodologyDetails);
            } else {
                acBrMethodologyDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ac br methodology detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AcBrMethodologyDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AcBrMethodologyDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ac br methodology details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AcBrMethodologyDetails acBrMethodologyDetails) {
        EntityCacheUtil.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsImpl.class,
            acBrMethodologyDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<AcBrMethodologyDetails> acBrMethodologyDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AcBrMethodologyDetails acBrMethodologyDetails : acBrMethodologyDetailses) {
            EntityCacheUtil.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AcBrMethodologyDetailsImpl.class,
                acBrMethodologyDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
     *
     * @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
     * @return the new ac br methodology details
     */
    @Override
    public AcBrMethodologyDetails create(int acBrMethodologyDetailsSid) {
        AcBrMethodologyDetails acBrMethodologyDetails = new AcBrMethodologyDetailsImpl();

        acBrMethodologyDetails.setNew(true);
        acBrMethodologyDetails.setPrimaryKey(acBrMethodologyDetailsSid);

        return acBrMethodologyDetails;
    }

    /**
     * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
     * @return the ac br methodology details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails remove(int acBrMethodologyDetailsSid)
        throws NoSuchAcBrMethodologyDetailsException, SystemException {
        return remove((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ac br methodology details
     * @return the ac br methodology details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails remove(Serializable primaryKey)
        throws NoSuchAcBrMethodologyDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AcBrMethodologyDetails acBrMethodologyDetails = (AcBrMethodologyDetails) session.get(AcBrMethodologyDetailsImpl.class,
                    primaryKey);

            if (acBrMethodologyDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAcBrMethodologyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(acBrMethodologyDetails);
        } catch (NoSuchAcBrMethodologyDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AcBrMethodologyDetails removeImpl(
        AcBrMethodologyDetails acBrMethodologyDetails)
        throws SystemException {
        acBrMethodologyDetails = toUnwrappedModel(acBrMethodologyDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(acBrMethodologyDetails)) {
                acBrMethodologyDetails = (AcBrMethodologyDetails) session.get(AcBrMethodologyDetailsImpl.class,
                        acBrMethodologyDetails.getPrimaryKeyObj());
            }

            if (acBrMethodologyDetails != null) {
                session.delete(acBrMethodologyDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (acBrMethodologyDetails != null) {
            clearCache(acBrMethodologyDetails);
        }

        return acBrMethodologyDetails;
    }

    @Override
    public AcBrMethodologyDetails updateImpl(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws SystemException {
        acBrMethodologyDetails = toUnwrappedModel(acBrMethodologyDetails);

        boolean isNew = acBrMethodologyDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (acBrMethodologyDetails.isNew()) {
                session.save(acBrMethodologyDetails);

                acBrMethodologyDetails.setNew(false);
            } else {
                session.merge(acBrMethodologyDetails);
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

        EntityCacheUtil.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBrMethodologyDetailsImpl.class,
            acBrMethodologyDetails.getPrimaryKey(), acBrMethodologyDetails);

        return acBrMethodologyDetails;
    }

    protected AcBrMethodologyDetails toUnwrappedModel(
        AcBrMethodologyDetails acBrMethodologyDetails) {
        if (acBrMethodologyDetails instanceof AcBrMethodologyDetailsImpl) {
            return acBrMethodologyDetails;
        }

        AcBrMethodologyDetailsImpl acBrMethodologyDetailsImpl = new AcBrMethodologyDetailsImpl();

        acBrMethodologyDetailsImpl.setNew(acBrMethodologyDetails.isNew());
        acBrMethodologyDetailsImpl.setPrimaryKey(acBrMethodologyDetails.getPrimaryKey());

        acBrMethodologyDetailsImpl.setSalesGrowthRate(acBrMethodologyDetails.getSalesGrowthRate());
        acBrMethodologyDetailsImpl.setMethodologyStartDate(acBrMethodologyDetails.getMethodologyStartDate());
        acBrMethodologyDetailsImpl.setFrequency(acBrMethodologyDetails.getFrequency());
        acBrMethodologyDetailsImpl.setCalculationFlag(acBrMethodologyDetails.isCalculationFlag());
        acBrMethodologyDetailsImpl.setProvisionGrowthRate(acBrMethodologyDetails.getProvisionGrowthRate());
        acBrMethodologyDetailsImpl.setSalesBasis(acBrMethodologyDetails.getSalesBasis());
        acBrMethodologyDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetails.getAcBrMethodologyDetailsSid());
        acBrMethodologyDetailsImpl.setAccClosureMasterSid(acBrMethodologyDetails.getAccClosureMasterSid());
        acBrMethodologyDetailsImpl.setMethodologyEndDate(acBrMethodologyDetails.getMethodologyEndDate());
        acBrMethodologyDetailsImpl.setMethodologyValue(acBrMethodologyDetails.getMethodologyValue());
        acBrMethodologyDetailsImpl.setDampingFactor(acBrMethodologyDetails.getDampingFactor());
        acBrMethodologyDetailsImpl.setMethodologyName(acBrMethodologyDetails.getMethodologyName());

        return acBrMethodologyDetailsImpl;
    }

    /**
     * Returns the ac br methodology details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ac br methodology details
     * @return the ac br methodology details
     * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAcBrMethodologyDetailsException, SystemException {
        AcBrMethodologyDetails acBrMethodologyDetails = fetchByPrimaryKey(primaryKey);

        if (acBrMethodologyDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAcBrMethodologyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return acBrMethodologyDetails;
    }

    /**
     * Returns the ac br methodology details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException} if it could not be found.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
     * @return the ac br methodology details
     * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws NoSuchAcBrMethodologyDetailsException, SystemException {
        return findByPrimaryKey((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ac br methodology details
     * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AcBrMethodologyDetails acBrMethodologyDetails = (AcBrMethodologyDetails) EntityCacheUtil.getResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AcBrMethodologyDetailsImpl.class, primaryKey);

        if (acBrMethodologyDetails == _nullAcBrMethodologyDetails) {
            return null;
        }

        if (acBrMethodologyDetails == null) {
            Session session = null;

            try {
                session = openSession();

                acBrMethodologyDetails = (AcBrMethodologyDetails) session.get(AcBrMethodologyDetailsImpl.class,
                        primaryKey);

                if (acBrMethodologyDetails != null) {
                    cacheResult(acBrMethodologyDetails);
                } else {
                    EntityCacheUtil.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AcBrMethodologyDetailsImpl.class, primaryKey,
                        _nullAcBrMethodologyDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    AcBrMethodologyDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return acBrMethodologyDetails;
    }

    /**
     * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
     * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBrMethodologyDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Returns all the ac br methodology detailses.
     *
     * @return the ac br methodology detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBrMethodologyDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ac br methodology detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac br methodology detailses
     * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
     * @return the range of ac br methodology detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBrMethodologyDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ac br methodology detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac br methodology detailses
     * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ac br methodology detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBrMethodologyDetails> findAll(int start, int end,
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

        List<AcBrMethodologyDetails> list = (List<AcBrMethodologyDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACBRMETHODOLOGYDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACBRMETHODOLOGYDETAILS;

                if (pagination) {
                    sql = sql.concat(AcBrMethodologyDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AcBrMethodologyDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AcBrMethodologyDetails>(list);
                } else {
                    list = (List<AcBrMethodologyDetails>) QueryUtil.list(q,
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
     * Removes all the ac br methodology detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AcBrMethodologyDetails acBrMethodologyDetails : findAll()) {
            remove(acBrMethodologyDetails);
        }
    }

    /**
     * Returns the number of ac br methodology detailses.
     *
     * @return the number of ac br methodology detailses
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

                Query q = session.createQuery(_SQL_COUNT_ACBRMETHODOLOGYDETAILS);

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
     * Initializes the ac br methodology details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AcBrMethodologyDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AcBrMethodologyDetails>> listenersList = new ArrayList<ModelListener<AcBrMethodologyDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AcBrMethodologyDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AcBrMethodologyDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
