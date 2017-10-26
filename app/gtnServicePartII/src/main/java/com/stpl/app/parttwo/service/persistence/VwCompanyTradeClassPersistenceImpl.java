package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException;
import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.model.impl.VwCompanyTradeClassImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyTradeClassPersistence;

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
 * The persistence implementation for the vw company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassPersistence
 * @see VwCompanyTradeClassUtil
 * @generated
 */
public class VwCompanyTradeClassPersistenceImpl extends BasePersistenceImpl<VwCompanyTradeClass>
    implements VwCompanyTradeClassPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwCompanyTradeClassUtil} to access the vw company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyTradeClassImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWCOMPANYTRADECLASS = "SELECT vwCompanyTradeClass FROM VwCompanyTradeClass vwCompanyTradeClass";
    private static final String _SQL_COUNT_VWCOMPANYTRADECLASS = "SELECT COUNT(vwCompanyTradeClass) FROM VwCompanyTradeClass vwCompanyTradeClass";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyTradeClass.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyTradeClass exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwCompanyTradeClassPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "priorTradeClass", "companyTradeClassSid", "companyId",
                "lastUpdatedDate", "priorTradeClassStartDate", "modifiedDate",
                "tradeClassEndDate", "tradeClassStartDate", "source",
                "createdBy", "createdDate", "companyTradeClass", "batchId",
                "addChgDelIndicator", "modifiedBy"
            });
    private static VwCompanyTradeClass _nullVwCompanyTradeClass = new VwCompanyTradeClassImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwCompanyTradeClass> toCacheModel() {
                return _nullVwCompanyTradeClassCacheModel;
            }
        };

    private static CacheModel<VwCompanyTradeClass> _nullVwCompanyTradeClassCacheModel =
        new CacheModel<VwCompanyTradeClass>() {
            @Override
            public VwCompanyTradeClass toEntityModel() {
                return _nullVwCompanyTradeClass;
            }
        };

    public VwCompanyTradeClassPersistenceImpl() {
        setModelClass(VwCompanyTradeClass.class);
    }

    /**
     * Caches the vw company trade class in the entity cache if it is enabled.
     *
     * @param vwCompanyTradeClass the vw company trade class
     */
    @Override
    public void cacheResult(VwCompanyTradeClass vwCompanyTradeClass) {
        EntityCacheUtil.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey(),
            vwCompanyTradeClass);

        vwCompanyTradeClass.resetOriginalValues();
    }

    /**
     * Caches the vw company trade classes in the entity cache if it is enabled.
     *
     * @param vwCompanyTradeClasses the vw company trade classes
     */
    @Override
    public void cacheResult(List<VwCompanyTradeClass> vwCompanyTradeClasses) {
        for (VwCompanyTradeClass vwCompanyTradeClass : vwCompanyTradeClasses) {
            if (EntityCacheUtil.getResult(
                        VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyTradeClassImpl.class,
                        vwCompanyTradeClass.getPrimaryKey()) == null) {
                cacheResult(vwCompanyTradeClass);
            } else {
                vwCompanyTradeClass.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw company trade classes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwCompanyTradeClassImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwCompanyTradeClassImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw company trade class.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwCompanyTradeClass vwCompanyTradeClass) {
        EntityCacheUtil.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwCompanyTradeClass> vwCompanyTradeClasses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwCompanyTradeClass vwCompanyTradeClass : vwCompanyTradeClasses) {
            EntityCacheUtil.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyTradeClassImpl.class,
                vwCompanyTradeClass.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
     *
     * @param companyTradeClassSid the primary key for the new vw company trade class
     * @return the new vw company trade class
     */
    @Override
    public VwCompanyTradeClass create(int companyTradeClassSid) {
        VwCompanyTradeClass vwCompanyTradeClass = new VwCompanyTradeClassImpl();

        vwCompanyTradeClass.setNew(true);
        vwCompanyTradeClass.setPrimaryKey(companyTradeClassSid);

        return vwCompanyTradeClass;
    }

    /**
     * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyTradeClassSid the primary key of the vw company trade class
     * @return the vw company trade class that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass remove(int companyTradeClassSid)
        throws NoSuchVwCompanyTradeClassException, SystemException {
        return remove((Serializable) companyTradeClassSid);
    }

    /**
     * Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw company trade class
     * @return the vw company trade class that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass remove(Serializable primaryKey)
        throws NoSuchVwCompanyTradeClassException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwCompanyTradeClass vwCompanyTradeClass = (VwCompanyTradeClass) session.get(VwCompanyTradeClassImpl.class,
                    primaryKey);

            if (vwCompanyTradeClass == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwCompanyTradeClass);
        } catch (NoSuchVwCompanyTradeClassException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwCompanyTradeClass removeImpl(
        VwCompanyTradeClass vwCompanyTradeClass) throws SystemException {
        vwCompanyTradeClass = toUnwrappedModel(vwCompanyTradeClass);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwCompanyTradeClass)) {
                vwCompanyTradeClass = (VwCompanyTradeClass) session.get(VwCompanyTradeClassImpl.class,
                        vwCompanyTradeClass.getPrimaryKeyObj());
            }

            if (vwCompanyTradeClass != null) {
                session.delete(vwCompanyTradeClass);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwCompanyTradeClass != null) {
            clearCache(vwCompanyTradeClass);
        }

        return vwCompanyTradeClass;
    }

    @Override
    public VwCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass)
        throws SystemException {
        vwCompanyTradeClass = toUnwrappedModel(vwCompanyTradeClass);

        boolean isNew = vwCompanyTradeClass.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwCompanyTradeClass.isNew()) {
                session.save(vwCompanyTradeClass);

                vwCompanyTradeClass.setNew(false);
            } else {
                session.merge(vwCompanyTradeClass);
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

        EntityCacheUtil.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyTradeClassImpl.class, vwCompanyTradeClass.getPrimaryKey(),
            vwCompanyTradeClass);

        return vwCompanyTradeClass;
    }

    protected VwCompanyTradeClass toUnwrappedModel(
        VwCompanyTradeClass vwCompanyTradeClass) {
        if (vwCompanyTradeClass instanceof VwCompanyTradeClassImpl) {
            return vwCompanyTradeClass;
        }

        VwCompanyTradeClassImpl vwCompanyTradeClassImpl = new VwCompanyTradeClassImpl();

        vwCompanyTradeClassImpl.setNew(vwCompanyTradeClass.isNew());
        vwCompanyTradeClassImpl.setPrimaryKey(vwCompanyTradeClass.getPrimaryKey());

        vwCompanyTradeClassImpl.setPriorTradeClass(vwCompanyTradeClass.getPriorTradeClass());
        vwCompanyTradeClassImpl.setCompanyTradeClassSid(vwCompanyTradeClass.getCompanyTradeClassSid());
        vwCompanyTradeClassImpl.setCompanyId(vwCompanyTradeClass.getCompanyId());
        vwCompanyTradeClassImpl.setLastUpdatedDate(vwCompanyTradeClass.getLastUpdatedDate());
        vwCompanyTradeClassImpl.setPriorTradeClassStartDate(vwCompanyTradeClass.getPriorTradeClassStartDate());
        vwCompanyTradeClassImpl.setModifiedDate(vwCompanyTradeClass.getModifiedDate());
        vwCompanyTradeClassImpl.setTradeClassEndDate(vwCompanyTradeClass.getTradeClassEndDate());
        vwCompanyTradeClassImpl.setTradeClassStartDate(vwCompanyTradeClass.getTradeClassStartDate());
        vwCompanyTradeClassImpl.setSource(vwCompanyTradeClass.getSource());
        vwCompanyTradeClassImpl.setCreatedBy(vwCompanyTradeClass.getCreatedBy());
        vwCompanyTradeClassImpl.setCreatedDate(vwCompanyTradeClass.getCreatedDate());
        vwCompanyTradeClassImpl.setCompanyTradeClass(vwCompanyTradeClass.getCompanyTradeClass());
        vwCompanyTradeClassImpl.setBatchId(vwCompanyTradeClass.getBatchId());
        vwCompanyTradeClassImpl.setAddChgDelIndicator(vwCompanyTradeClass.getAddChgDelIndicator());
        vwCompanyTradeClassImpl.setModifiedBy(vwCompanyTradeClass.getModifiedBy());

        return vwCompanyTradeClassImpl;
    }

    /**
     * Returns the vw company trade class with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw company trade class
     * @return the vw company trade class
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwCompanyTradeClassException, SystemException {
        VwCompanyTradeClass vwCompanyTradeClass = fetchByPrimaryKey(primaryKey);

        if (vwCompanyTradeClass == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwCompanyTradeClass;
    }

    /**
     * Returns the vw company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException} if it could not be found.
     *
     * @param companyTradeClassSid the primary key of the vw company trade class
     * @return the vw company trade class
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
        throws NoSuchVwCompanyTradeClassException, SystemException {
        return findByPrimaryKey((Serializable) companyTradeClassSid);
    }

    /**
     * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw company trade class
     * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwCompanyTradeClass vwCompanyTradeClass = (VwCompanyTradeClass) EntityCacheUtil.getResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyTradeClassImpl.class, primaryKey);

        if (vwCompanyTradeClass == _nullVwCompanyTradeClass) {
            return null;
        }

        if (vwCompanyTradeClass == null) {
            Session session = null;

            try {
                session = openSession();

                vwCompanyTradeClass = (VwCompanyTradeClass) session.get(VwCompanyTradeClassImpl.class,
                        primaryKey);

                if (vwCompanyTradeClass != null) {
                    cacheResult(vwCompanyTradeClass);
                } else {
                    EntityCacheUtil.putResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyTradeClassImpl.class, primaryKey,
                        _nullVwCompanyTradeClass);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                    VwCompanyTradeClassImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwCompanyTradeClass;
    }

    /**
     * Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyTradeClassSid the primary key of the vw company trade class
     * @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyTradeClassSid);
    }

    /**
     * Returns all the vw company trade classes.
     *
     * @return the vw company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyTradeClass> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company trade classes
     * @param end the upper bound of the range of vw company trade classes (not inclusive)
     * @return the range of vw company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyTradeClass> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company trade classes
     * @param end the upper bound of the range of vw company trade classes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyTradeClass> findAll(int start, int end,
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

        List<VwCompanyTradeClass> list = (List<VwCompanyTradeClass>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWCOMPANYTRADECLASS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWCOMPANYTRADECLASS;

                if (pagination) {
                    sql = sql.concat(VwCompanyTradeClassModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwCompanyTradeClass>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwCompanyTradeClass>(list);
                } else {
                    list = (List<VwCompanyTradeClass>) QueryUtil.list(q,
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
     * Removes all the vw company trade classes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwCompanyTradeClass vwCompanyTradeClass : findAll()) {
            remove(vwCompanyTradeClass);
        }
    }

    /**
     * Returns the number of vw company trade classes.
     *
     * @return the number of vw company trade classes
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

                Query q = session.createQuery(_SQL_COUNT_VWCOMPANYTRADECLASS);

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
     * Initializes the vw company trade class persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwCompanyTradeClass")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwCompanyTradeClass>> listenersList = new ArrayList<ModelListener<VwCompanyTradeClass>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwCompanyTradeClass>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwCompanyTradeClassImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
