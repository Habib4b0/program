package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCompanyMasterException;
import com.stpl.app.parttwo.model.IvldCompanyMaster;
import com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyMasterPersistence;

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
 * The persistence implementation for the ivld company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyMasterPersistence
 * @see IvldCompanyMasterUtil
 * @generated
 */
public class IvldCompanyMasterPersistenceImpl extends BasePersistenceImpl<IvldCompanyMaster>
    implements IvldCompanyMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCompanyMasterUtil} to access the ivld company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCOMPANYMASTER = "SELECT ivldCompanyMaster FROM IvldCompanyMaster ivldCompanyMaster";
    private static final String _SQL_COUNT_IVLDCOMPANYMASTER = "SELECT COUNT(ivldCompanyMaster) FROM IvldCompanyMaster ivldCompanyMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCompanyMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "state", "financialSystem", "companyName", "companyGroup",
                "companyCategory", "lastUpdatedDate", "modifiedDate", "status",
                "ivldCompanyMasterSid", "lives", "organizationKey", "source",
                "createdDate", "createdBy", "addChgDelIndicator", "errorCode",
                "intfInsertedDate", "modifiedBy", "companyMasterIntfid",
                "reprocessedFlag", "udc6", "udc5", "udc4", "udc1", "udc2",
                "zipCode", "udc3", "reasonForFailure", "companyId", "address1",
                "country", "address2", "companyType", "companyStartDate",
                "companyNo", "batchId", "companyStatus", "companyEndDate",
                "errorField", "city", "regionCode", "checkRecord"
            });
    private static IvldCompanyMaster _nullIvldCompanyMaster = new IvldCompanyMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCompanyMaster> toCacheModel() {
                return _nullIvldCompanyMasterCacheModel;
            }
        };

    private static CacheModel<IvldCompanyMaster> _nullIvldCompanyMasterCacheModel =
        new CacheModel<IvldCompanyMaster>() {
            @Override
            public IvldCompanyMaster toEntityModel() {
                return _nullIvldCompanyMaster;
            }
        };

    public IvldCompanyMasterPersistenceImpl() {
        setModelClass(IvldCompanyMaster.class);
    }

    /**
     * Caches the ivld company master in the entity cache if it is enabled.
     *
     * @param ivldCompanyMaster the ivld company master
     */
    @Override
    public void cacheResult(IvldCompanyMaster ivldCompanyMaster) {
        EntityCacheUtil.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey(),
            ivldCompanyMaster);

        ivldCompanyMaster.resetOriginalValues();
    }

    /**
     * Caches the ivld company masters in the entity cache if it is enabled.
     *
     * @param ivldCompanyMasters the ivld company masters
     */
    @Override
    public void cacheResult(List<IvldCompanyMaster> ivldCompanyMasters) {
        for (IvldCompanyMaster ivldCompanyMaster : ivldCompanyMasters) {
            if (EntityCacheUtil.getResult(
                        IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyMasterImpl.class,
                        ivldCompanyMaster.getPrimaryKey()) == null) {
                cacheResult(ivldCompanyMaster);
            } else {
                ivldCompanyMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld company masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCompanyMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCompanyMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld company master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCompanyMaster ivldCompanyMaster) {
        EntityCacheUtil.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCompanyMaster> ivldCompanyMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCompanyMaster ivldCompanyMaster : ivldCompanyMasters) {
            EntityCacheUtil.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
     *
     * @param ivldCompanyMasterSid the primary key for the new ivld company master
     * @return the new ivld company master
     */
    @Override
    public IvldCompanyMaster create(int ivldCompanyMasterSid) {
        IvldCompanyMaster ivldCompanyMaster = new IvldCompanyMasterImpl();

        ivldCompanyMaster.setNew(true);
        ivldCompanyMaster.setPrimaryKey(ivldCompanyMasterSid);

        return ivldCompanyMaster;
    }

    /**
     * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCompanyMasterSid the primary key of the ivld company master
     * @return the ivld company master that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster remove(int ivldCompanyMasterSid)
        throws NoSuchIvldCompanyMasterException, SystemException {
        return remove((Serializable) ivldCompanyMasterSid);
    }

    /**
     * Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld company master
     * @return the ivld company master that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster remove(Serializable primaryKey)
        throws NoSuchIvldCompanyMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCompanyMaster ivldCompanyMaster = (IvldCompanyMaster) session.get(IvldCompanyMasterImpl.class,
                    primaryKey);

            if (ivldCompanyMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCompanyMaster);
        } catch (NoSuchIvldCompanyMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCompanyMaster removeImpl(IvldCompanyMaster ivldCompanyMaster)
        throws SystemException {
        ivldCompanyMaster = toUnwrappedModel(ivldCompanyMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCompanyMaster)) {
                ivldCompanyMaster = (IvldCompanyMaster) session.get(IvldCompanyMasterImpl.class,
                        ivldCompanyMaster.getPrimaryKeyObj());
            }

            if (ivldCompanyMaster != null) {
                session.delete(ivldCompanyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCompanyMaster != null) {
            clearCache(ivldCompanyMaster);
        }

        return ivldCompanyMaster;
    }

    @Override
    public IvldCompanyMaster updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster)
        throws SystemException {
        ivldCompanyMaster = toUnwrappedModel(ivldCompanyMaster);

        boolean isNew = ivldCompanyMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCompanyMaster.isNew()) {
                session.save(ivldCompanyMaster);

                ivldCompanyMaster.setNew(false);
            } else {
                session.merge(ivldCompanyMaster);
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

        EntityCacheUtil.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyMasterImpl.class, ivldCompanyMaster.getPrimaryKey(),
            ivldCompanyMaster);

        return ivldCompanyMaster;
    }

    protected IvldCompanyMaster toUnwrappedModel(
        IvldCompanyMaster ivldCompanyMaster) {
        if (ivldCompanyMaster instanceof IvldCompanyMasterImpl) {
            return ivldCompanyMaster;
        }

        IvldCompanyMasterImpl ivldCompanyMasterImpl = new IvldCompanyMasterImpl();

        ivldCompanyMasterImpl.setNew(ivldCompanyMaster.isNew());
        ivldCompanyMasterImpl.setPrimaryKey(ivldCompanyMaster.getPrimaryKey());

        ivldCompanyMasterImpl.setState(ivldCompanyMaster.getState());
        ivldCompanyMasterImpl.setFinancialSystem(ivldCompanyMaster.getFinancialSystem());
        ivldCompanyMasterImpl.setCompanyName(ivldCompanyMaster.getCompanyName());
        ivldCompanyMasterImpl.setCompanyGroup(ivldCompanyMaster.getCompanyGroup());
        ivldCompanyMasterImpl.setCompanyCategory(ivldCompanyMaster.getCompanyCategory());
        ivldCompanyMasterImpl.setLastUpdatedDate(ivldCompanyMaster.getLastUpdatedDate());
        ivldCompanyMasterImpl.setModifiedDate(ivldCompanyMaster.getModifiedDate());
        ivldCompanyMasterImpl.setStatus(ivldCompanyMaster.getStatus());
        ivldCompanyMasterImpl.setIvldCompanyMasterSid(ivldCompanyMaster.getIvldCompanyMasterSid());
        ivldCompanyMasterImpl.setLives(ivldCompanyMaster.getLives());
        ivldCompanyMasterImpl.setOrganizationKey(ivldCompanyMaster.getOrganizationKey());
        ivldCompanyMasterImpl.setSource(ivldCompanyMaster.getSource());
        ivldCompanyMasterImpl.setCreatedDate(ivldCompanyMaster.getCreatedDate());
        ivldCompanyMasterImpl.setCreatedBy(ivldCompanyMaster.getCreatedBy());
        ivldCompanyMasterImpl.setAddChgDelIndicator(ivldCompanyMaster.getAddChgDelIndicator());
        ivldCompanyMasterImpl.setErrorCode(ivldCompanyMaster.getErrorCode());
        ivldCompanyMasterImpl.setIntfInsertedDate(ivldCompanyMaster.getIntfInsertedDate());
        ivldCompanyMasterImpl.setModifiedBy(ivldCompanyMaster.getModifiedBy());
        ivldCompanyMasterImpl.setCompanyMasterIntfid(ivldCompanyMaster.getCompanyMasterIntfid());
        ivldCompanyMasterImpl.setReprocessedFlag(ivldCompanyMaster.getReprocessedFlag());
        ivldCompanyMasterImpl.setUdc6(ivldCompanyMaster.getUdc6());
        ivldCompanyMasterImpl.setUdc5(ivldCompanyMaster.getUdc5());
        ivldCompanyMasterImpl.setUdc4(ivldCompanyMaster.getUdc4());
        ivldCompanyMasterImpl.setUdc1(ivldCompanyMaster.getUdc1());
        ivldCompanyMasterImpl.setUdc2(ivldCompanyMaster.getUdc2());
        ivldCompanyMasterImpl.setZipCode(ivldCompanyMaster.getZipCode());
        ivldCompanyMasterImpl.setUdc3(ivldCompanyMaster.getUdc3());
        ivldCompanyMasterImpl.setReasonForFailure(ivldCompanyMaster.getReasonForFailure());
        ivldCompanyMasterImpl.setCompanyId(ivldCompanyMaster.getCompanyId());
        ivldCompanyMasterImpl.setAddress1(ivldCompanyMaster.getAddress1());
        ivldCompanyMasterImpl.setCountry(ivldCompanyMaster.getCountry());
        ivldCompanyMasterImpl.setAddress2(ivldCompanyMaster.getAddress2());
        ivldCompanyMasterImpl.setCompanyType(ivldCompanyMaster.getCompanyType());
        ivldCompanyMasterImpl.setCompanyStartDate(ivldCompanyMaster.getCompanyStartDate());
        ivldCompanyMasterImpl.setCompanyNo(ivldCompanyMaster.getCompanyNo());
        ivldCompanyMasterImpl.setBatchId(ivldCompanyMaster.getBatchId());
        ivldCompanyMasterImpl.setCompanyStatus(ivldCompanyMaster.getCompanyStatus());
        ivldCompanyMasterImpl.setCompanyEndDate(ivldCompanyMaster.getCompanyEndDate());
        ivldCompanyMasterImpl.setErrorField(ivldCompanyMaster.getErrorField());
        ivldCompanyMasterImpl.setCity(ivldCompanyMaster.getCity());
        ivldCompanyMasterImpl.setRegionCode(ivldCompanyMaster.getRegionCode());
        ivldCompanyMasterImpl.setCheckRecord(ivldCompanyMaster.isCheckRecord());

        return ivldCompanyMasterImpl;
    }

    /**
     * Returns the ivld company master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company master
     * @return the ivld company master
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCompanyMasterException, SystemException {
        IvldCompanyMaster ivldCompanyMaster = fetchByPrimaryKey(primaryKey);

        if (ivldCompanyMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCompanyMaster;
    }

    /**
     * Returns the ivld company master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyMasterException} if it could not be found.
     *
     * @param ivldCompanyMasterSid the primary key of the ivld company master
     * @return the ivld company master
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster findByPrimaryKey(int ivldCompanyMasterSid)
        throws NoSuchIvldCompanyMasterException, SystemException {
        return findByPrimaryKey((Serializable) ivldCompanyMasterSid);
    }

    /**
     * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company master
     * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCompanyMaster ivldCompanyMaster = (IvldCompanyMaster) EntityCacheUtil.getResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyMasterImpl.class, primaryKey);

        if (ivldCompanyMaster == _nullIvldCompanyMaster) {
            return null;
        }

        if (ivldCompanyMaster == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCompanyMaster = (IvldCompanyMaster) session.get(IvldCompanyMasterImpl.class,
                        primaryKey);

                if (ivldCompanyMaster != null) {
                    cacheResult(ivldCompanyMaster);
                } else {
                    EntityCacheUtil.putResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyMasterImpl.class, primaryKey,
                        _nullIvldCompanyMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCompanyMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCompanyMaster;
    }

    /**
     * Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCompanyMasterSid the primary key of the ivld company master
     * @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyMaster fetchByPrimaryKey(int ivldCompanyMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCompanyMasterSid);
    }

    /**
     * Returns all the ivld company masters.
     *
     * @return the ivld company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company masters
     * @param end the upper bound of the range of ivld company masters (not inclusive)
     * @return the range of ivld company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company masters
     * @param end the upper bound of the range of ivld company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyMaster> findAll(int start, int end,
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

        List<IvldCompanyMaster> list = (List<IvldCompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCOMPANYMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCOMPANYMASTER;

                if (pagination) {
                    sql = sql.concat(IvldCompanyMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCompanyMaster>(list);
                } else {
                    list = (List<IvldCompanyMaster>) QueryUtil.list(q,
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
     * Removes all the ivld company masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCompanyMaster ivldCompanyMaster : findAll()) {
            remove(ivldCompanyMaster);
        }
    }

    /**
     * Returns the number of ivld company masters.
     *
     * @return the number of ivld company masters
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYMASTER);

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
     * Initializes the ivld company master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCompanyMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCompanyMaster>> listenersList = new ArrayList<ModelListener<IvldCompanyMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCompanyMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCompanyMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
