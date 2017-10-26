package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwCompanyMasterException;
import com.stpl.app.parttwo.model.VwCompanyMaster;
import com.stpl.app.parttwo.model.impl.VwCompanyMasterImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyMasterPersistence;

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
 * The persistence implementation for the vw company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyMasterPersistence
 * @see VwCompanyMasterUtil
 * @generated
 */
public class VwCompanyMasterPersistenceImpl extends BasePersistenceImpl<VwCompanyMaster>
    implements VwCompanyMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwCompanyMasterUtil} to access the vw company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWCOMPANYMASTER = "SELECT vwCompanyMaster FROM VwCompanyMaster vwCompanyMaster";
    private static final String _SQL_COUNT_VWCOMPANYMASTER = "SELECT COUNT(vwCompanyMaster) FROM VwCompanyMaster vwCompanyMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwCompanyMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "state", "financialSystem", "companyGroup", "companyName",
                "lastUpdatedDate", "companyCategory", "modifiedDate", "lives",
                "organizationKey", "address2", "createdDate", "createdBy",
                "source", "address1", "addChgDelIndicator", "modifiedBy", "udc6",
                "udc5", "companyMasterSid", "udc4", "udc1", "zipCode", "udc2",
                "udc3", "companyId", "country", "companyType",
                "companyStartDate", "companyNo", "batchId", "companyStatus",
                "companyEndDate", "city", "regionCode"
            });
    private static VwCompanyMaster _nullVwCompanyMaster = new VwCompanyMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwCompanyMaster> toCacheModel() {
                return _nullVwCompanyMasterCacheModel;
            }
        };

    private static CacheModel<VwCompanyMaster> _nullVwCompanyMasterCacheModel = new CacheModel<VwCompanyMaster>() {
            @Override
            public VwCompanyMaster toEntityModel() {
                return _nullVwCompanyMaster;
            }
        };

    public VwCompanyMasterPersistenceImpl() {
        setModelClass(VwCompanyMaster.class);
    }

    /**
     * Caches the vw company master in the entity cache if it is enabled.
     *
     * @param vwCompanyMaster the vw company master
     */
    @Override
    public void cacheResult(VwCompanyMaster vwCompanyMaster) {
        EntityCacheUtil.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey(),
            vwCompanyMaster);

        vwCompanyMaster.resetOriginalValues();
    }

    /**
     * Caches the vw company masters in the entity cache if it is enabled.
     *
     * @param vwCompanyMasters the vw company masters
     */
    @Override
    public void cacheResult(List<VwCompanyMaster> vwCompanyMasters) {
        for (VwCompanyMaster vwCompanyMaster : vwCompanyMasters) {
            if (EntityCacheUtil.getResult(
                        VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyMasterImpl.class,
                        vwCompanyMaster.getPrimaryKey()) == null) {
                cacheResult(vwCompanyMaster);
            } else {
                vwCompanyMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw company masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwCompanyMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwCompanyMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw company master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwCompanyMaster vwCompanyMaster) {
        EntityCacheUtil.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwCompanyMaster> vwCompanyMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwCompanyMaster vwCompanyMaster : vwCompanyMasters) {
            EntityCacheUtil.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw company master with the primary key. Does not add the vw company master to the database.
     *
     * @param companyMasterSid the primary key for the new vw company master
     * @return the new vw company master
     */
    @Override
    public VwCompanyMaster create(int companyMasterSid) {
        VwCompanyMaster vwCompanyMaster = new VwCompanyMasterImpl();

        vwCompanyMaster.setNew(true);
        vwCompanyMaster.setPrimaryKey(companyMasterSid);

        return vwCompanyMaster;
    }

    /**
     * Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyMasterSid the primary key of the vw company master
     * @return the vw company master that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster remove(int companyMasterSid)
        throws NoSuchVwCompanyMasterException, SystemException {
        return remove((Serializable) companyMasterSid);
    }

    /**
     * Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw company master
     * @return the vw company master that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster remove(Serializable primaryKey)
        throws NoSuchVwCompanyMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwCompanyMaster vwCompanyMaster = (VwCompanyMaster) session.get(VwCompanyMasterImpl.class,
                    primaryKey);

            if (vwCompanyMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwCompanyMaster);
        } catch (NoSuchVwCompanyMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwCompanyMaster removeImpl(VwCompanyMaster vwCompanyMaster)
        throws SystemException {
        vwCompanyMaster = toUnwrappedModel(vwCompanyMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwCompanyMaster)) {
                vwCompanyMaster = (VwCompanyMaster) session.get(VwCompanyMasterImpl.class,
                        vwCompanyMaster.getPrimaryKeyObj());
            }

            if (vwCompanyMaster != null) {
                session.delete(vwCompanyMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwCompanyMaster != null) {
            clearCache(vwCompanyMaster);
        }

        return vwCompanyMaster;
    }

    @Override
    public VwCompanyMaster updateImpl(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster)
        throws SystemException {
        vwCompanyMaster = toUnwrappedModel(vwCompanyMaster);

        boolean isNew = vwCompanyMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwCompanyMaster.isNew()) {
                session.save(vwCompanyMaster);

                vwCompanyMaster.setNew(false);
            } else {
                session.merge(vwCompanyMaster);
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

        EntityCacheUtil.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyMasterImpl.class, vwCompanyMaster.getPrimaryKey(),
            vwCompanyMaster);

        return vwCompanyMaster;
    }

    protected VwCompanyMaster toUnwrappedModel(VwCompanyMaster vwCompanyMaster) {
        if (vwCompanyMaster instanceof VwCompanyMasterImpl) {
            return vwCompanyMaster;
        }

        VwCompanyMasterImpl vwCompanyMasterImpl = new VwCompanyMasterImpl();

        vwCompanyMasterImpl.setNew(vwCompanyMaster.isNew());
        vwCompanyMasterImpl.setPrimaryKey(vwCompanyMaster.getPrimaryKey());

        vwCompanyMasterImpl.setState(vwCompanyMaster.getState());
        vwCompanyMasterImpl.setFinancialSystem(vwCompanyMaster.getFinancialSystem());
        vwCompanyMasterImpl.setCompanyGroup(vwCompanyMaster.getCompanyGroup());
        vwCompanyMasterImpl.setCompanyName(vwCompanyMaster.getCompanyName());
        vwCompanyMasterImpl.setLastUpdatedDate(vwCompanyMaster.getLastUpdatedDate());
        vwCompanyMasterImpl.setCompanyCategory(vwCompanyMaster.getCompanyCategory());
        vwCompanyMasterImpl.setModifiedDate(vwCompanyMaster.getModifiedDate());
        vwCompanyMasterImpl.setLives(vwCompanyMaster.getLives());
        vwCompanyMasterImpl.setOrganizationKey(vwCompanyMaster.getOrganizationKey());
        vwCompanyMasterImpl.setAddress2(vwCompanyMaster.getAddress2());
        vwCompanyMasterImpl.setCreatedDate(vwCompanyMaster.getCreatedDate());
        vwCompanyMasterImpl.setCreatedBy(vwCompanyMaster.getCreatedBy());
        vwCompanyMasterImpl.setSource(vwCompanyMaster.getSource());
        vwCompanyMasterImpl.setAddress1(vwCompanyMaster.getAddress1());
        vwCompanyMasterImpl.setAddChgDelIndicator(vwCompanyMaster.getAddChgDelIndicator());
        vwCompanyMasterImpl.setModifiedBy(vwCompanyMaster.getModifiedBy());
        vwCompanyMasterImpl.setUdc6(vwCompanyMaster.getUdc6());
        vwCompanyMasterImpl.setUdc5(vwCompanyMaster.getUdc5());
        vwCompanyMasterImpl.setCompanyMasterSid(vwCompanyMaster.getCompanyMasterSid());
        vwCompanyMasterImpl.setUdc4(vwCompanyMaster.getUdc4());
        vwCompanyMasterImpl.setUdc1(vwCompanyMaster.getUdc1());
        vwCompanyMasterImpl.setZipCode(vwCompanyMaster.getZipCode());
        vwCompanyMasterImpl.setUdc2(vwCompanyMaster.getUdc2());
        vwCompanyMasterImpl.setUdc3(vwCompanyMaster.getUdc3());
        vwCompanyMasterImpl.setCompanyId(vwCompanyMaster.getCompanyId());
        vwCompanyMasterImpl.setCountry(vwCompanyMaster.getCountry());
        vwCompanyMasterImpl.setCompanyType(vwCompanyMaster.getCompanyType());
        vwCompanyMasterImpl.setCompanyStartDate(vwCompanyMaster.getCompanyStartDate());
        vwCompanyMasterImpl.setCompanyNo(vwCompanyMaster.getCompanyNo());
        vwCompanyMasterImpl.setBatchId(vwCompanyMaster.getBatchId());
        vwCompanyMasterImpl.setCompanyStatus(vwCompanyMaster.getCompanyStatus());
        vwCompanyMasterImpl.setCompanyEndDate(vwCompanyMaster.getCompanyEndDate());
        vwCompanyMasterImpl.setCity(vwCompanyMaster.getCity());
        vwCompanyMasterImpl.setRegionCode(vwCompanyMaster.getRegionCode());

        return vwCompanyMasterImpl;
    }

    /**
     * Returns the vw company master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw company master
     * @return the vw company master
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwCompanyMasterException, SystemException {
        VwCompanyMaster vwCompanyMaster = fetchByPrimaryKey(primaryKey);

        if (vwCompanyMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwCompanyMaster;
    }

    /**
     * Returns the vw company master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyMasterException} if it could not be found.
     *
     * @param companyMasterSid the primary key of the vw company master
     * @return the vw company master
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster findByPrimaryKey(int companyMasterSid)
        throws NoSuchVwCompanyMasterException, SystemException {
        return findByPrimaryKey((Serializable) companyMasterSid);
    }

    /**
     * Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw company master
     * @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwCompanyMaster vwCompanyMaster = (VwCompanyMaster) EntityCacheUtil.getResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyMasterImpl.class, primaryKey);

        if (vwCompanyMaster == _nullVwCompanyMaster) {
            return null;
        }

        if (vwCompanyMaster == null) {
            Session session = null;

            try {
                session = openSession();

                vwCompanyMaster = (VwCompanyMaster) session.get(VwCompanyMasterImpl.class,
                        primaryKey);

                if (vwCompanyMaster != null) {
                    cacheResult(vwCompanyMaster);
                } else {
                    EntityCacheUtil.putResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyMasterImpl.class, primaryKey,
                        _nullVwCompanyMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwCompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
                    VwCompanyMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwCompanyMaster;
    }

    /**
     * Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyMasterSid the primary key of the vw company master
     * @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyMaster fetchByPrimaryKey(int companyMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyMasterSid);
    }

    /**
     * Returns all the vw company masters.
     *
     * @return the vw company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company masters
     * @param end the upper bound of the range of vw company masters (not inclusive)
     * @return the range of vw company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw company masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company masters
     * @param end the upper bound of the range of vw company masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw company masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyMaster> findAll(int start, int end,
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

        List<VwCompanyMaster> list = (List<VwCompanyMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWCOMPANYMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWCOMPANYMASTER;

                if (pagination) {
                    sql = sql.concat(VwCompanyMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwCompanyMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwCompanyMaster>(list);
                } else {
                    list = (List<VwCompanyMaster>) QueryUtil.list(q,
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
     * Removes all the vw company masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwCompanyMaster vwCompanyMaster : findAll()) {
            remove(vwCompanyMaster);
        }
    }

    /**
     * Returns the number of vw company masters.
     *
     * @return the number of vw company masters
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

                Query q = session.createQuery(_SQL_COUNT_VWCOMPANYMASTER);

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
     * Initializes the vw company master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwCompanyMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwCompanyMaster>> listenersList = new ArrayList<ModelListener<VwCompanyMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwCompanyMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwCompanyMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
