package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldDemandActualException;
import com.stpl.app.model.IvldDemandActual;
import com.stpl.app.model.impl.IvldDemandActualImpl;
import com.stpl.app.model.impl.IvldDemandActualModelImpl;
import com.stpl.app.service.persistence.IvldDemandActualPersistence;

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
 * The persistence implementation for the ivld demand actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandActualPersistence
 * @see IvldDemandActualUtil
 * @generated
 */
public class IvldDemandActualPersistenceImpl extends BasePersistenceImpl<IvldDemandActual>
    implements IvldDemandActualPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldDemandActualUtil} to access the ivld demand actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldDemandActualImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualModelImpl.FINDER_CACHE_ENABLED,
            IvldDemandActualImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualModelImpl.FINDER_CACHE_ENABLED,
            IvldDemandActualImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDDEMANDACTUAL = "SELECT ivldDemandActual FROM IvldDemandActual ivldDemandActual";
    private static final String _SQL_COUNT_IVLDDEMANDACTUAL = "SELECT COUNT(ivldDemandActual) FROM IvldDemandActual ivldDemandActual";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldDemandActual.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldDemandActual exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldDemandActualPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "forecastYear", "grossUnits", "totalDemandUnits", "itemId",
                "modifiedDate", "organizationKey", "source", "marketShareRatio",
                "createdBy", "createdDate", "demandActualInterfaceId",
                "addChgDelIndicator", "itemIdentifier", "errorCode",
                "intfInsertedDate", "marketShareUnits", "modifiedBy",
                "reprocessedFlag", "reasonForFailure", "country", "forecastType",
                "brandId", "grossPrice", "ivldDemandActualSid", "grossAmount",
                "itemIdentifierCodeQualifier", "batchId", "forecastMonth",
                "errorField", "netSalesPrice", "netSalesAmount", "segment",
                "totalDemandAmount", "marketSizeUnits", "checkRecord"
            });
    private static IvldDemandActual _nullIvldDemandActual = new IvldDemandActualImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldDemandActual> toCacheModel() {
                return _nullIvldDemandActualCacheModel;
            }
        };

    private static CacheModel<IvldDemandActual> _nullIvldDemandActualCacheModel = new CacheModel<IvldDemandActual>() {
            @Override
            public IvldDemandActual toEntityModel() {
                return _nullIvldDemandActual;
            }
        };

    public IvldDemandActualPersistenceImpl() {
        setModelClass(IvldDemandActual.class);
    }

    /**
     * Caches the ivld demand actual in the entity cache if it is enabled.
     *
     * @param ivldDemandActual the ivld demand actual
     */
    @Override
    public void cacheResult(IvldDemandActual ivldDemandActual) {
        EntityCacheUtil.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey(),
            ivldDemandActual);

        ivldDemandActual.resetOriginalValues();
    }

    /**
     * Caches the ivld demand actuals in the entity cache if it is enabled.
     *
     * @param ivldDemandActuals the ivld demand actuals
     */
    @Override
    public void cacheResult(List<IvldDemandActual> ivldDemandActuals) {
        for (IvldDemandActual ivldDemandActual : ivldDemandActuals) {
            if (EntityCacheUtil.getResult(
                        IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
                        IvldDemandActualImpl.class,
                        ivldDemandActual.getPrimaryKey()) == null) {
                cacheResult(ivldDemandActual);
            } else {
                ivldDemandActual.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld demand actuals.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldDemandActualImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldDemandActualImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld demand actual.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldDemandActual ivldDemandActual) {
        EntityCacheUtil.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldDemandActual> ivldDemandActuals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldDemandActual ivldDemandActual : ivldDemandActuals) {
            EntityCacheUtil.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
                IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
     *
     * @param ivldDemandActualSid the primary key for the new ivld demand actual
     * @return the new ivld demand actual
     */
    @Override
    public IvldDemandActual create(int ivldDemandActualSid) {
        IvldDemandActual ivldDemandActual = new IvldDemandActualImpl();

        ivldDemandActual.setNew(true);
        ivldDemandActual.setPrimaryKey(ivldDemandActualSid);

        return ivldDemandActual;
    }

    /**
     * Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldDemandActualSid the primary key of the ivld demand actual
     * @return the ivld demand actual that was removed
     * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual remove(int ivldDemandActualSid)
        throws NoSuchIvldDemandActualException, SystemException {
        return remove((Serializable) ivldDemandActualSid);
    }

    /**
     * Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld demand actual
     * @return the ivld demand actual that was removed
     * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual remove(Serializable primaryKey)
        throws NoSuchIvldDemandActualException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldDemandActual ivldDemandActual = (IvldDemandActual) session.get(IvldDemandActualImpl.class,
                    primaryKey);

            if (ivldDemandActual == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldDemandActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldDemandActual);
        } catch (NoSuchIvldDemandActualException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldDemandActual removeImpl(IvldDemandActual ivldDemandActual)
        throws SystemException {
        ivldDemandActual = toUnwrappedModel(ivldDemandActual);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldDemandActual)) {
                ivldDemandActual = (IvldDemandActual) session.get(IvldDemandActualImpl.class,
                        ivldDemandActual.getPrimaryKeyObj());
            }

            if (ivldDemandActual != null) {
                session.delete(ivldDemandActual);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldDemandActual != null) {
            clearCache(ivldDemandActual);
        }

        return ivldDemandActual;
    }

    @Override
    public IvldDemandActual updateImpl(
        com.stpl.app.model.IvldDemandActual ivldDemandActual)
        throws SystemException {
        ivldDemandActual = toUnwrappedModel(ivldDemandActual);

        boolean isNew = ivldDemandActual.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldDemandActual.isNew()) {
                session.save(ivldDemandActual);

                ivldDemandActual.setNew(false);
            } else {
                session.merge(ivldDemandActual);
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

        EntityCacheUtil.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
            IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey(),
            ivldDemandActual);

        return ivldDemandActual;
    }

    protected IvldDemandActual toUnwrappedModel(
        IvldDemandActual ivldDemandActual) {
        if (ivldDemandActual instanceof IvldDemandActualImpl) {
            return ivldDemandActual;
        }

        IvldDemandActualImpl ivldDemandActualImpl = new IvldDemandActualImpl();

        ivldDemandActualImpl.setNew(ivldDemandActual.isNew());
        ivldDemandActualImpl.setPrimaryKey(ivldDemandActual.getPrimaryKey());

        ivldDemandActualImpl.setForecastYear(ivldDemandActual.getForecastYear());
        ivldDemandActualImpl.setGrossUnits(ivldDemandActual.getGrossUnits());
        ivldDemandActualImpl.setTotalDemandUnits(ivldDemandActual.getTotalDemandUnits());
        ivldDemandActualImpl.setItemId(ivldDemandActual.getItemId());
        ivldDemandActualImpl.setModifiedDate(ivldDemandActual.getModifiedDate());
        ivldDemandActualImpl.setOrganizationKey(ivldDemandActual.getOrganizationKey());
        ivldDemandActualImpl.setSource(ivldDemandActual.getSource());
        ivldDemandActualImpl.setMarketShareRatio(ivldDemandActual.getMarketShareRatio());
        ivldDemandActualImpl.setCreatedBy(ivldDemandActual.getCreatedBy());
        ivldDemandActualImpl.setCreatedDate(ivldDemandActual.getCreatedDate());
        ivldDemandActualImpl.setDemandActualInterfaceId(ivldDemandActual.getDemandActualInterfaceId());
        ivldDemandActualImpl.setAddChgDelIndicator(ivldDemandActual.getAddChgDelIndicator());
        ivldDemandActualImpl.setItemIdentifier(ivldDemandActual.getItemIdentifier());
        ivldDemandActualImpl.setErrorCode(ivldDemandActual.getErrorCode());
        ivldDemandActualImpl.setIntfInsertedDate(ivldDemandActual.getIntfInsertedDate());
        ivldDemandActualImpl.setMarketShareUnits(ivldDemandActual.getMarketShareUnits());
        ivldDemandActualImpl.setModifiedBy(ivldDemandActual.getModifiedBy());
        ivldDemandActualImpl.setReprocessedFlag(ivldDemandActual.getReprocessedFlag());
        ivldDemandActualImpl.setReasonForFailure(ivldDemandActual.getReasonForFailure());
        ivldDemandActualImpl.setCountry(ivldDemandActual.getCountry());
        ivldDemandActualImpl.setForecastType(ivldDemandActual.getForecastType());
        ivldDemandActualImpl.setBrandId(ivldDemandActual.getBrandId());
        ivldDemandActualImpl.setGrossPrice(ivldDemandActual.getGrossPrice());
        ivldDemandActualImpl.setIvldDemandActualSid(ivldDemandActual.getIvldDemandActualSid());
        ivldDemandActualImpl.setGrossAmount(ivldDemandActual.getGrossAmount());
        ivldDemandActualImpl.setItemIdentifierCodeQualifier(ivldDemandActual.getItemIdentifierCodeQualifier());
        ivldDemandActualImpl.setBatchId(ivldDemandActual.getBatchId());
        ivldDemandActualImpl.setForecastMonth(ivldDemandActual.getForecastMonth());
        ivldDemandActualImpl.setErrorField(ivldDemandActual.getErrorField());
        ivldDemandActualImpl.setNetSalesPrice(ivldDemandActual.getNetSalesPrice());
        ivldDemandActualImpl.setNetSalesAmount(ivldDemandActual.getNetSalesAmount());
        ivldDemandActualImpl.setSegment(ivldDemandActual.getSegment());
        ivldDemandActualImpl.setTotalDemandAmount(ivldDemandActual.getTotalDemandAmount());
        ivldDemandActualImpl.setMarketSizeUnits(ivldDemandActual.getMarketSizeUnits());
        ivldDemandActualImpl.setCheckRecord(ivldDemandActual.isCheckRecord());

        return ivldDemandActualImpl;
    }

    /**
     * Returns the ivld demand actual with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld demand actual
     * @return the ivld demand actual
     * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldDemandActualException, SystemException {
        IvldDemandActual ivldDemandActual = fetchByPrimaryKey(primaryKey);

        if (ivldDemandActual == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldDemandActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldDemandActual;
    }

    /**
     * Returns the ivld demand actual with the primary key or throws a {@link com.stpl.app.NoSuchIvldDemandActualException} if it could not be found.
     *
     * @param ivldDemandActualSid the primary key of the ivld demand actual
     * @return the ivld demand actual
     * @throws com.stpl.app.NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual findByPrimaryKey(int ivldDemandActualSid)
        throws NoSuchIvldDemandActualException, SystemException {
        return findByPrimaryKey((Serializable) ivldDemandActualSid);
    }

    /**
     * Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld demand actual
     * @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldDemandActual ivldDemandActual = (IvldDemandActual) EntityCacheUtil.getResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
                IvldDemandActualImpl.class, primaryKey);

        if (ivldDemandActual == _nullIvldDemandActual) {
            return null;
        }

        if (ivldDemandActual == null) {
            Session session = null;

            try {
                session = openSession();

                ivldDemandActual = (IvldDemandActual) session.get(IvldDemandActualImpl.class,
                        primaryKey);

                if (ivldDemandActual != null) {
                    cacheResult(ivldDemandActual);
                } else {
                    EntityCacheUtil.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
                        IvldDemandActualImpl.class, primaryKey,
                        _nullIvldDemandActual);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
                    IvldDemandActualImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldDemandActual;
    }

    /**
     * Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldDemandActualSid the primary key of the ivld demand actual
     * @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldDemandActual fetchByPrimaryKey(int ivldDemandActualSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldDemandActualSid);
    }

    /**
     * Returns all the ivld demand actuals.
     *
     * @return the ivld demand actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandActual> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld demand actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld demand actuals
     * @param end the upper bound of the range of ivld demand actuals (not inclusive)
     * @return the range of ivld demand actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandActual> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld demand actuals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld demand actuals
     * @param end the upper bound of the range of ivld demand actuals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld demand actuals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldDemandActual> findAll(int start, int end,
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

        List<IvldDemandActual> list = (List<IvldDemandActual>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDDEMANDACTUAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDDEMANDACTUAL;

                if (pagination) {
                    sql = sql.concat(IvldDemandActualModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldDemandActual>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldDemandActual>(list);
                } else {
                    list = (List<IvldDemandActual>) QueryUtil.list(q,
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
     * Removes all the ivld demand actuals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldDemandActual ivldDemandActual : findAll()) {
            remove(ivldDemandActual);
        }
    }

    /**
     * Returns the number of ivld demand actuals.
     *
     * @return the number of ivld demand actuals
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

                Query q = session.createQuery(_SQL_COUNT_IVLDDEMANDACTUAL);

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
     * Initializes the ivld demand actual persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldDemandActual")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldDemandActual>> listenersList = new ArrayList<ModelListener<IvldDemandActual>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldDemandActual>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldDemandActualImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
