package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldGlCostCenterException;
import com.stpl.app.model.IvldGlCostCenter;
import com.stpl.app.model.impl.IvldGlCostCenterImpl;
import com.stpl.app.model.impl.IvldGlCostCenterModelImpl;
import com.stpl.app.service.persistence.IvldGlCostCenterPersistence;

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
 * The persistence implementation for the ivld gl cost center service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlCostCenterPersistence
 * @see IvldGlCostCenterUtil
 * @generated
 */
public class IvldGlCostCenterPersistenceImpl extends BasePersistenceImpl<IvldGlCostCenter>
    implements IvldGlCostCenterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldGlCostCenterUtil} to access the ivld gl cost center persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldGlCostCenterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED,
            IvldGlCostCenterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED,
            IvldGlCostCenterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDGLCOSTCENTER = "SELECT ivldGlCostCenter FROM IvldGlCostCenter ivldGlCostCenter";
    private static final String _SQL_COUNT_IVLDGLCOSTCENTER = "SELECT COUNT(ivldGlCostCenter) FROM IvldGlCostCenter ivldGlCostCenter";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldGlCostCenter.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldGlCostCenter exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldGlCostCenterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "reasonForFailure", "glCostCenterIntfid", "modifiedDate",
                "companyCostCenter", "uploadDate", "createdBy", "createdDate",
                "source", "batchId", "addChgDelIndicator", "ivldGlCostCenterSid",
                "errorField", "errorCode", "intfInsertedDate", "companyCode",
                "modifiedBy", "reprocessedFlag", "ndc8", "checkRecord"
            });
    private static IvldGlCostCenter _nullIvldGlCostCenter = new IvldGlCostCenterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldGlCostCenter> toCacheModel() {
                return _nullIvldGlCostCenterCacheModel;
            }
        };

    private static CacheModel<IvldGlCostCenter> _nullIvldGlCostCenterCacheModel = new CacheModel<IvldGlCostCenter>() {
            @Override
            public IvldGlCostCenter toEntityModel() {
                return _nullIvldGlCostCenter;
            }
        };

    public IvldGlCostCenterPersistenceImpl() {
        setModelClass(IvldGlCostCenter.class);
    }

    /**
     * Caches the ivld gl cost center in the entity cache if it is enabled.
     *
     * @param ivldGlCostCenter the ivld gl cost center
     */
    @Override
    public void cacheResult(IvldGlCostCenter ivldGlCostCenter) {
        EntityCacheUtil.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey(),
            ivldGlCostCenter);

        ivldGlCostCenter.resetOriginalValues();
    }

    /**
     * Caches the ivld gl cost centers in the entity cache if it is enabled.
     *
     * @param ivldGlCostCenters the ivld gl cost centers
     */
    @Override
    public void cacheResult(List<IvldGlCostCenter> ivldGlCostCenters) {
        for (IvldGlCostCenter ivldGlCostCenter : ivldGlCostCenters) {
            if (EntityCacheUtil.getResult(
                        IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldGlCostCenterImpl.class,
                        ivldGlCostCenter.getPrimaryKey()) == null) {
                cacheResult(ivldGlCostCenter);
            } else {
                ivldGlCostCenter.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld gl cost centers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldGlCostCenterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldGlCostCenterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld gl cost center.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldGlCostCenter ivldGlCostCenter) {
        EntityCacheUtil.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldGlCostCenter> ivldGlCostCenters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldGlCostCenter ivldGlCostCenter : ivldGlCostCenters) {
            EntityCacheUtil.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
                IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
     *
     * @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
     * @return the new ivld gl cost center
     */
    @Override
    public IvldGlCostCenter create(int ivldGlCostCenterSid) {
        IvldGlCostCenter ivldGlCostCenter = new IvldGlCostCenterImpl();

        ivldGlCostCenter.setNew(true);
        ivldGlCostCenter.setPrimaryKey(ivldGlCostCenterSid);

        return ivldGlCostCenter;
    }

    /**
     * Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
     * @return the ivld gl cost center that was removed
     * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter remove(int ivldGlCostCenterSid)
        throws NoSuchIvldGlCostCenterException, SystemException {
        return remove((Serializable) ivldGlCostCenterSid);
    }

    /**
     * Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld gl cost center
     * @return the ivld gl cost center that was removed
     * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter remove(Serializable primaryKey)
        throws NoSuchIvldGlCostCenterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldGlCostCenter ivldGlCostCenter = (IvldGlCostCenter) session.get(IvldGlCostCenterImpl.class,
                    primaryKey);

            if (ivldGlCostCenter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldGlCostCenterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldGlCostCenter);
        } catch (NoSuchIvldGlCostCenterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldGlCostCenter removeImpl(IvldGlCostCenter ivldGlCostCenter)
        throws SystemException {
        ivldGlCostCenter = toUnwrappedModel(ivldGlCostCenter);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldGlCostCenter)) {
                ivldGlCostCenter = (IvldGlCostCenter) session.get(IvldGlCostCenterImpl.class,
                        ivldGlCostCenter.getPrimaryKeyObj());
            }

            if (ivldGlCostCenter != null) {
                session.delete(ivldGlCostCenter);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldGlCostCenter != null) {
            clearCache(ivldGlCostCenter);
        }

        return ivldGlCostCenter;
    }

    @Override
    public IvldGlCostCenter updateImpl(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter)
        throws SystemException {
        ivldGlCostCenter = toUnwrappedModel(ivldGlCostCenter);

        boolean isNew = ivldGlCostCenter.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldGlCostCenter.isNew()) {
                session.save(ivldGlCostCenter);

                ivldGlCostCenter.setNew(false);
            } else {
                session.merge(ivldGlCostCenter);
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

        EntityCacheUtil.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
            IvldGlCostCenterImpl.class, ivldGlCostCenter.getPrimaryKey(),
            ivldGlCostCenter);

        return ivldGlCostCenter;
    }

    protected IvldGlCostCenter toUnwrappedModel(
        IvldGlCostCenter ivldGlCostCenter) {
        if (ivldGlCostCenter instanceof IvldGlCostCenterImpl) {
            return ivldGlCostCenter;
        }

        IvldGlCostCenterImpl ivldGlCostCenterImpl = new IvldGlCostCenterImpl();

        ivldGlCostCenterImpl.setNew(ivldGlCostCenter.isNew());
        ivldGlCostCenterImpl.setPrimaryKey(ivldGlCostCenter.getPrimaryKey());

        ivldGlCostCenterImpl.setReasonForFailure(ivldGlCostCenter.getReasonForFailure());
        ivldGlCostCenterImpl.setGlCostCenterIntfid(ivldGlCostCenter.getGlCostCenterIntfid());
        ivldGlCostCenterImpl.setModifiedDate(ivldGlCostCenter.getModifiedDate());
        ivldGlCostCenterImpl.setCompanyCostCenter(ivldGlCostCenter.getCompanyCostCenter());
        ivldGlCostCenterImpl.setUploadDate(ivldGlCostCenter.getUploadDate());
        ivldGlCostCenterImpl.setCreatedBy(ivldGlCostCenter.getCreatedBy());
        ivldGlCostCenterImpl.setCreatedDate(ivldGlCostCenter.getCreatedDate());
        ivldGlCostCenterImpl.setSource(ivldGlCostCenter.getSource());
        ivldGlCostCenterImpl.setBatchId(ivldGlCostCenter.getBatchId());
        ivldGlCostCenterImpl.setAddChgDelIndicator(ivldGlCostCenter.getAddChgDelIndicator());
        ivldGlCostCenterImpl.setIvldGlCostCenterSid(ivldGlCostCenter.getIvldGlCostCenterSid());
        ivldGlCostCenterImpl.setErrorField(ivldGlCostCenter.getErrorField());
        ivldGlCostCenterImpl.setErrorCode(ivldGlCostCenter.getErrorCode());
        ivldGlCostCenterImpl.setIntfInsertedDate(ivldGlCostCenter.getIntfInsertedDate());
        ivldGlCostCenterImpl.setCompanyCode(ivldGlCostCenter.getCompanyCode());
        ivldGlCostCenterImpl.setModifiedBy(ivldGlCostCenter.getModifiedBy());
        ivldGlCostCenterImpl.setReprocessedFlag(ivldGlCostCenter.getReprocessedFlag());
        ivldGlCostCenterImpl.setNdc8(ivldGlCostCenter.getNdc8());
        ivldGlCostCenterImpl.setCheckRecord(ivldGlCostCenter.isCheckRecord());

        return ivldGlCostCenterImpl;
    }

    /**
     * Returns the ivld gl cost center with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld gl cost center
     * @return the ivld gl cost center
     * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldGlCostCenterException, SystemException {
        IvldGlCostCenter ivldGlCostCenter = fetchByPrimaryKey(primaryKey);

        if (ivldGlCostCenter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldGlCostCenterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldGlCostCenter;
    }

    /**
     * Returns the ivld gl cost center with the primary key or throws a {@link com.stpl.app.NoSuchIvldGlCostCenterException} if it could not be found.
     *
     * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
     * @return the ivld gl cost center
     * @throws com.stpl.app.NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter findByPrimaryKey(int ivldGlCostCenterSid)
        throws NoSuchIvldGlCostCenterException, SystemException {
        return findByPrimaryKey((Serializable) ivldGlCostCenterSid);
    }

    /**
     * Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld gl cost center
     * @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldGlCostCenter ivldGlCostCenter = (IvldGlCostCenter) EntityCacheUtil.getResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
                IvldGlCostCenterImpl.class, primaryKey);

        if (ivldGlCostCenter == _nullIvldGlCostCenter) {
            return null;
        }

        if (ivldGlCostCenter == null) {
            Session session = null;

            try {
                session = openSession();

                ivldGlCostCenter = (IvldGlCostCenter) session.get(IvldGlCostCenterImpl.class,
                        primaryKey);

                if (ivldGlCostCenter != null) {
                    cacheResult(ivldGlCostCenter);
                } else {
                    EntityCacheUtil.putResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldGlCostCenterImpl.class, primaryKey,
                        _nullIvldGlCostCenter);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldGlCostCenterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldGlCostCenterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldGlCostCenter;
    }

    /**
     * Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
     * @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldGlCostCenter fetchByPrimaryKey(int ivldGlCostCenterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldGlCostCenterSid);
    }

    /**
     * Returns all the ivld gl cost centers.
     *
     * @return the ivld gl cost centers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlCostCenter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld gl cost centers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld gl cost centers
     * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
     * @return the range of ivld gl cost centers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlCostCenter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld gl cost centers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld gl cost centers
     * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld gl cost centers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldGlCostCenter> findAll(int start, int end,
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

        List<IvldGlCostCenter> list = (List<IvldGlCostCenter>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDGLCOSTCENTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDGLCOSTCENTER;

                if (pagination) {
                    sql = sql.concat(IvldGlCostCenterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldGlCostCenter>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldGlCostCenter>(list);
                } else {
                    list = (List<IvldGlCostCenter>) QueryUtil.list(q,
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
     * Removes all the ivld gl cost centers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldGlCostCenter ivldGlCostCenter : findAll()) {
            remove(ivldGlCostCenter);
        }
    }

    /**
     * Returns the number of ivld gl cost centers.
     *
     * @return the number of ivld gl cost centers
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

                Query q = session.createQuery(_SQL_COUNT_IVLDGLCOSTCENTER);

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
     * Initializes the ivld gl cost center persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldGlCostCenter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldGlCostCenter>> listenersList = new ArrayList<ModelListener<IvldGlCostCenter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldGlCostCenter>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldGlCostCenterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
