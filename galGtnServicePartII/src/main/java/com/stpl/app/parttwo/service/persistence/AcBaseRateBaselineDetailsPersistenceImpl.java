package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException;
import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;
import com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl;
import com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsPersistence;

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
 * The persistence implementation for the ac base rate baseline details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetailsPersistence
 * @see AcBaseRateBaselineDetailsUtil
 * @generated
 */
public class AcBaseRateBaselineDetailsPersistenceImpl
    extends BasePersistenceImpl<AcBaseRateBaselineDetails>
    implements AcBaseRateBaselineDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AcBaseRateBaselineDetailsUtil} to access the ac base rate baseline details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AcBaseRateBaselineDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
            AcBaseRateBaselineDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
            AcBaseRateBaselineDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_ACBASERATEBASELINEDETAILS = "SELECT acBaseRateBaselineDetails FROM AcBaseRateBaselineDetails acBaseRateBaselineDetails";
    private static final String _SQL_COUNT_ACBASERATEBASELINEDETAILS = "SELECT COUNT(acBaseRateBaselineDetails) FROM AcBaseRateBaselineDetails acBaseRateBaselineDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "acBaseRateBaselineDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcBaseRateBaselineDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AcBaseRateBaselineDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "periodValue", "periodSid", "paymentsPeriod",
                "acBrMethodologyDetailsSid", "salesPeriod"
            });
    private static AcBaseRateBaselineDetails _nullAcBaseRateBaselineDetails = new AcBaseRateBaselineDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AcBaseRateBaselineDetails> toCacheModel() {
                return _nullAcBaseRateBaselineDetailsCacheModel;
            }
        };

    private static CacheModel<AcBaseRateBaselineDetails> _nullAcBaseRateBaselineDetailsCacheModel =
        new CacheModel<AcBaseRateBaselineDetails>() {
            @Override
            public AcBaseRateBaselineDetails toEntityModel() {
                return _nullAcBaseRateBaselineDetails;
            }
        };

    public AcBaseRateBaselineDetailsPersistenceImpl() {
        setModelClass(AcBaseRateBaselineDetails.class);
    }

    /**
     * Caches the ac base rate baseline details in the entity cache if it is enabled.
     *
     * @param acBaseRateBaselineDetails the ac base rate baseline details
     */
    @Override
    public void cacheResult(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        EntityCacheUtil.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsImpl.class,
            acBaseRateBaselineDetails.getPrimaryKey(), acBaseRateBaselineDetails);

        acBaseRateBaselineDetails.resetOriginalValues();
    }

    /**
     * Caches the ac base rate baseline detailses in the entity cache if it is enabled.
     *
     * @param acBaseRateBaselineDetailses the ac base rate baseline detailses
     */
    @Override
    public void cacheResult(
        List<AcBaseRateBaselineDetails> acBaseRateBaselineDetailses) {
        for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : acBaseRateBaselineDetailses) {
            if (EntityCacheUtil.getResult(
                        AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AcBaseRateBaselineDetailsImpl.class,
                        acBaseRateBaselineDetails.getPrimaryKey()) == null) {
                cacheResult(acBaseRateBaselineDetails);
            } else {
                acBaseRateBaselineDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ac base rate baseline detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AcBaseRateBaselineDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AcBaseRateBaselineDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ac base rate baseline details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        EntityCacheUtil.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsImpl.class,
            acBaseRateBaselineDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<AcBaseRateBaselineDetails> acBaseRateBaselineDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : acBaseRateBaselineDetailses) {
            EntityCacheUtil.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AcBaseRateBaselineDetailsImpl.class,
                acBaseRateBaselineDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new ac base rate baseline details with the primary key. Does not add the ac base rate baseline details to the database.
     *
     * @param acBrMethodologyDetailsSid the primary key for the new ac base rate baseline details
     * @return the new ac base rate baseline details
     */
    @Override
    public AcBaseRateBaselineDetails create(int acBrMethodologyDetailsSid) {
        AcBaseRateBaselineDetails acBaseRateBaselineDetails = new AcBaseRateBaselineDetailsImpl();

        acBaseRateBaselineDetails.setNew(true);
        acBaseRateBaselineDetails.setPrimaryKey(acBrMethodologyDetailsSid);

        return acBaseRateBaselineDetails;
    }

    /**
     * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails remove(int acBrMethodologyDetailsSid)
        throws NoSuchAcBaseRateBaselineDetailsException, SystemException {
        return remove((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details that was removed
     * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails remove(Serializable primaryKey)
        throws NoSuchAcBaseRateBaselineDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AcBaseRateBaselineDetails acBaseRateBaselineDetails = (AcBaseRateBaselineDetails) session.get(AcBaseRateBaselineDetailsImpl.class,
                    primaryKey);

            if (acBaseRateBaselineDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAcBaseRateBaselineDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(acBaseRateBaselineDetails);
        } catch (NoSuchAcBaseRateBaselineDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AcBaseRateBaselineDetails removeImpl(
        AcBaseRateBaselineDetails acBaseRateBaselineDetails)
        throws SystemException {
        acBaseRateBaselineDetails = toUnwrappedModel(acBaseRateBaselineDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(acBaseRateBaselineDetails)) {
                acBaseRateBaselineDetails = (AcBaseRateBaselineDetails) session.get(AcBaseRateBaselineDetailsImpl.class,
                        acBaseRateBaselineDetails.getPrimaryKeyObj());
            }

            if (acBaseRateBaselineDetails != null) {
                session.delete(acBaseRateBaselineDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (acBaseRateBaselineDetails != null) {
            clearCache(acBaseRateBaselineDetails);
        }

        return acBaseRateBaselineDetails;
    }

    @Override
    public AcBaseRateBaselineDetails updateImpl(
        com.stpl.app.parttwo.model.AcBaseRateBaselineDetails acBaseRateBaselineDetails)
        throws SystemException {
        acBaseRateBaselineDetails = toUnwrappedModel(acBaseRateBaselineDetails);

        boolean isNew = acBaseRateBaselineDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (acBaseRateBaselineDetails.isNew()) {
                session.save(acBaseRateBaselineDetails);

                acBaseRateBaselineDetails.setNew(false);
            } else {
                session.merge(acBaseRateBaselineDetails);
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

        EntityCacheUtil.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
            AcBaseRateBaselineDetailsImpl.class,
            acBaseRateBaselineDetails.getPrimaryKey(), acBaseRateBaselineDetails);

        return acBaseRateBaselineDetails;
    }

    protected AcBaseRateBaselineDetails toUnwrappedModel(
        AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
        if (acBaseRateBaselineDetails instanceof AcBaseRateBaselineDetailsImpl) {
            return acBaseRateBaselineDetails;
        }

        AcBaseRateBaselineDetailsImpl acBaseRateBaselineDetailsImpl = new AcBaseRateBaselineDetailsImpl();

        acBaseRateBaselineDetailsImpl.setNew(acBaseRateBaselineDetails.isNew());
        acBaseRateBaselineDetailsImpl.setPrimaryKey(acBaseRateBaselineDetails.getPrimaryKey());

        acBaseRateBaselineDetailsImpl.setPeriodValue(acBaseRateBaselineDetails.getPeriodValue());
        acBaseRateBaselineDetailsImpl.setPeriodSid(acBaseRateBaselineDetails.getPeriodSid());
        acBaseRateBaselineDetailsImpl.setPaymentsPeriod(acBaseRateBaselineDetails.isPaymentsPeriod());
        acBaseRateBaselineDetailsImpl.setAcBrMethodologyDetailsSid(acBaseRateBaselineDetails.getAcBrMethodologyDetailsSid());
        acBaseRateBaselineDetailsImpl.setSalesPeriod(acBaseRateBaselineDetails.isSalesPeriod());

        return acBaseRateBaselineDetailsImpl;
    }

    /**
     * Returns the ac base rate baseline details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details
     * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAcBaseRateBaselineDetailsException, SystemException {
        AcBaseRateBaselineDetails acBaseRateBaselineDetails = fetchByPrimaryKey(primaryKey);

        if (acBaseRateBaselineDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAcBaseRateBaselineDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return acBaseRateBaselineDetails;
    }

    /**
     * Returns the ac base rate baseline details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException} if it could not be found.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details
     * @throws com.stpl.app.parttwo.NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws NoSuchAcBaseRateBaselineDetailsException, SystemException {
        return findByPrimaryKey((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AcBaseRateBaselineDetails acBaseRateBaselineDetails = (AcBaseRateBaselineDetails) EntityCacheUtil.getResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
                AcBaseRateBaselineDetailsImpl.class, primaryKey);

        if (acBaseRateBaselineDetails == _nullAcBaseRateBaselineDetails) {
            return null;
        }

        if (acBaseRateBaselineDetails == null) {
            Session session = null;

            try {
                session = openSession();

                acBaseRateBaselineDetails = (AcBaseRateBaselineDetails) session.get(AcBaseRateBaselineDetailsImpl.class,
                        primaryKey);

                if (acBaseRateBaselineDetails != null) {
                    cacheResult(acBaseRateBaselineDetails);
                } else {
                    EntityCacheUtil.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        AcBaseRateBaselineDetailsImpl.class, primaryKey,
                        _nullAcBaseRateBaselineDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    AcBaseRateBaselineDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return acBaseRateBaselineDetails;
    }

    /**
     * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
     * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AcBaseRateBaselineDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) acBrMethodologyDetailsSid);
    }

    /**
     * Returns all the ac base rate baseline detailses.
     *
     * @return the ac base rate baseline detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBaseRateBaselineDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ac base rate baseline detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac base rate baseline detailses
     * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
     * @return the range of ac base rate baseline detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBaseRateBaselineDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ac base rate baseline detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ac base rate baseline detailses
     * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ac base rate baseline detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AcBaseRateBaselineDetails> findAll(int start, int end,
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

        List<AcBaseRateBaselineDetails> list = (List<AcBaseRateBaselineDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ACBASERATEBASELINEDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ACBASERATEBASELINEDETAILS;

                if (pagination) {
                    sql = sql.concat(AcBaseRateBaselineDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AcBaseRateBaselineDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AcBaseRateBaselineDetails>(list);
                } else {
                    list = (List<AcBaseRateBaselineDetails>) QueryUtil.list(q,
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
     * Removes all the ac base rate baseline detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : findAll()) {
            remove(acBaseRateBaselineDetails);
        }
    }

    /**
     * Returns the number of ac base rate baseline detailses.
     *
     * @return the number of ac base rate baseline detailses
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

                Query q = session.createQuery(_SQL_COUNT_ACBASERATEBASELINEDETAILS);

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
     * Initializes the ac base rate baseline details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.AcBaseRateBaselineDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AcBaseRateBaselineDetails>> listenersList = new ArrayList<ModelListener<AcBaseRateBaselineDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AcBaseRateBaselineDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AcBaseRateBaselineDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
