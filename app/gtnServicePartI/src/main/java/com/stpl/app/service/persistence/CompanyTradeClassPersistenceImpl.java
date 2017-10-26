package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyTradeClassException;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.impl.CompanyTradeClassImpl;
import com.stpl.app.model.impl.CompanyTradeClassModelImpl;
import com.stpl.app.service.persistence.CompanyTradeClassPersistence;

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
 * The persistence implementation for the company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyTradeClassPersistence
 * @see CompanyTradeClassUtil
 * @generated
 */
public class CompanyTradeClassPersistenceImpl extends BasePersistenceImpl<CompanyTradeClass>
    implements CompanyTradeClassPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyTradeClassUtil} to access the company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyTradeClassImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            CompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            CompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COMPANYTRADECLASS = "SELECT companyTradeClass FROM CompanyTradeClass companyTradeClass";
    private static final String _SQL_COUNT_COMPANYTRADECLASS = "SELECT COUNT(companyTradeClass) FROM CompanyTradeClass companyTradeClass";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyTradeClass.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyTradeClass exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyTradeClassPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "priorTradeClass", "companyTradeClassSid", "lastUpdatedDate",
                "priorTradeClassStartDate", "modifiedDate", "tradeClassEndDate",
                "tradeClassStartDate", "recordLockStatus", "createdDate",
                "source", "createdBy", "batchId", "companyTradeClass",
                "modifiedBy", "inboundStatus", "companyMasterSid"
            });
    private static CompanyTradeClass _nullCompanyTradeClass = new CompanyTradeClassImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyTradeClass> toCacheModel() {
                return _nullCompanyTradeClassCacheModel;
            }
        };

    private static CacheModel<CompanyTradeClass> _nullCompanyTradeClassCacheModel =
        new CacheModel<CompanyTradeClass>() {
            @Override
            public CompanyTradeClass toEntityModel() {
                return _nullCompanyTradeClass;
            }
        };

    public CompanyTradeClassPersistenceImpl() {
        setModelClass(CompanyTradeClass.class);
    }

    /**
     * Caches the company trade class in the entity cache if it is enabled.
     *
     * @param companyTradeClass the company trade class
     */
    @Override
    public void cacheResult(CompanyTradeClass companyTradeClass) {
        EntityCacheUtil.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey(),
            companyTradeClass);

        companyTradeClass.resetOriginalValues();
    }

    /**
     * Caches the company trade classes in the entity cache if it is enabled.
     *
     * @param companyTradeClasses the company trade classes
     */
    @Override
    public void cacheResult(List<CompanyTradeClass> companyTradeClasses) {
        for (CompanyTradeClass companyTradeClass : companyTradeClasses) {
            if (EntityCacheUtil.getResult(
                        CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyTradeClassImpl.class,
                        companyTradeClass.getPrimaryKey()) == null) {
                cacheResult(companyTradeClass);
            } else {
                companyTradeClass.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company trade classes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyTradeClassImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyTradeClassImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company trade class.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyTradeClass companyTradeClass) {
        EntityCacheUtil.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyTradeClass> companyTradeClasses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyTradeClass companyTradeClass : companyTradeClasses) {
            EntityCacheUtil.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey());
        }
    }

    /**
     * Creates a new company trade class with the primary key. Does not add the company trade class to the database.
     *
     * @param companyTradeClassSid the primary key for the new company trade class
     * @return the new company trade class
     */
    @Override
    public CompanyTradeClass create(int companyTradeClassSid) {
        CompanyTradeClass companyTradeClass = new CompanyTradeClassImpl();

        companyTradeClass.setNew(true);
        companyTradeClass.setPrimaryKey(companyTradeClassSid);

        return companyTradeClass;
    }

    /**
     * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyTradeClassSid the primary key of the company trade class
     * @return the company trade class that was removed
     * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass remove(int companyTradeClassSid)
        throws NoSuchCompanyTradeClassException, SystemException {
        return remove((Serializable) companyTradeClassSid);
    }

    /**
     * Removes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company trade class
     * @return the company trade class that was removed
     * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass remove(Serializable primaryKey)
        throws NoSuchCompanyTradeClassException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyTradeClass companyTradeClass = (CompanyTradeClass) session.get(CompanyTradeClassImpl.class,
                    primaryKey);

            if (companyTradeClass == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyTradeClass);
        } catch (NoSuchCompanyTradeClassException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyTradeClass removeImpl(CompanyTradeClass companyTradeClass)
        throws SystemException {
        companyTradeClass = toUnwrappedModel(companyTradeClass);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyTradeClass)) {
                companyTradeClass = (CompanyTradeClass) session.get(CompanyTradeClassImpl.class,
                        companyTradeClass.getPrimaryKeyObj());
            }

            if (companyTradeClass != null) {
                session.delete(companyTradeClass);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyTradeClass != null) {
            clearCache(companyTradeClass);
        }

        return companyTradeClass;
    }

    @Override
    public CompanyTradeClass updateImpl(
        com.stpl.app.model.CompanyTradeClass companyTradeClass)
        throws SystemException {
        companyTradeClass = toUnwrappedModel(companyTradeClass);

        boolean isNew = companyTradeClass.isNew();

        Session session = null;

        try {
            session = openSession();

            if (companyTradeClass.isNew()) {
                session.save(companyTradeClass);

                companyTradeClass.setNew(false);
            } else {
                session.merge(companyTradeClass);
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

        EntityCacheUtil.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            CompanyTradeClassImpl.class, companyTradeClass.getPrimaryKey(),
            companyTradeClass);

        return companyTradeClass;
    }

    protected CompanyTradeClass toUnwrappedModel(
        CompanyTradeClass companyTradeClass) {
        if (companyTradeClass instanceof CompanyTradeClassImpl) {
            return companyTradeClass;
        }

        CompanyTradeClassImpl companyTradeClassImpl = new CompanyTradeClassImpl();

        companyTradeClassImpl.setNew(companyTradeClass.isNew());
        companyTradeClassImpl.setPrimaryKey(companyTradeClass.getPrimaryKey());

        companyTradeClassImpl.setPriorTradeClass(companyTradeClass.getPriorTradeClass());
        companyTradeClassImpl.setCompanyTradeClassSid(companyTradeClass.getCompanyTradeClassSid());
        companyTradeClassImpl.setLastUpdatedDate(companyTradeClass.getLastUpdatedDate());
        companyTradeClassImpl.setPriorTradeClassStartDate(companyTradeClass.getPriorTradeClassStartDate());
        companyTradeClassImpl.setModifiedDate(companyTradeClass.getModifiedDate());
        companyTradeClassImpl.setTradeClassEndDate(companyTradeClass.getTradeClassEndDate());
        companyTradeClassImpl.setTradeClassStartDate(companyTradeClass.getTradeClassStartDate());
        companyTradeClassImpl.setRecordLockStatus(companyTradeClass.isRecordLockStatus());
        companyTradeClassImpl.setCreatedDate(companyTradeClass.getCreatedDate());
        companyTradeClassImpl.setSource(companyTradeClass.getSource());
        companyTradeClassImpl.setCreatedBy(companyTradeClass.getCreatedBy());
        companyTradeClassImpl.setBatchId(companyTradeClass.getBatchId());
        companyTradeClassImpl.setCompanyTradeClass(companyTradeClass.getCompanyTradeClass());
        companyTradeClassImpl.setModifiedBy(companyTradeClass.getModifiedBy());
        companyTradeClassImpl.setInboundStatus(companyTradeClass.getInboundStatus());
        companyTradeClassImpl.setCompanyMasterSid(companyTradeClass.getCompanyMasterSid());

        return companyTradeClassImpl;
    }

    /**
     * Returns the company trade class with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company trade class
     * @return the company trade class
     * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyTradeClassException, SystemException {
        CompanyTradeClass companyTradeClass = fetchByPrimaryKey(primaryKey);

        if (companyTradeClass == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyTradeClass;
    }

    /**
     * Returns the company trade class with the primary key or throws a {@link com.stpl.app.NoSuchCompanyTradeClassException} if it could not be found.
     *
     * @param companyTradeClassSid the primary key of the company trade class
     * @return the company trade class
     * @throws com.stpl.app.NoSuchCompanyTradeClassException if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
        throws NoSuchCompanyTradeClassException, SystemException {
        return findByPrimaryKey((Serializable) companyTradeClassSid);
    }

    /**
     * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company trade class
     * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyTradeClass companyTradeClass = (CompanyTradeClass) EntityCacheUtil.getResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                CompanyTradeClassImpl.class, primaryKey);

        if (companyTradeClass == _nullCompanyTradeClass) {
            return null;
        }

        if (companyTradeClass == null) {
            Session session = null;

            try {
                session = openSession();

                companyTradeClass = (CompanyTradeClass) session.get(CompanyTradeClassImpl.class,
                        primaryKey);

                if (companyTradeClass != null) {
                    cacheResult(companyTradeClass);
                } else {
                    EntityCacheUtil.putResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyTradeClassImpl.class, primaryKey,
                        _nullCompanyTradeClass);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyTradeClassImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyTradeClass;
    }

    /**
     * Returns the company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyTradeClassSid the primary key of the company trade class
     * @return the company trade class, or <code>null</code> if a company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyTradeClass fetchByPrimaryKey(int companyTradeClassSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyTradeClassSid);
    }

    /**
     * Returns all the company trade classes.
     *
     * @return the company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyTradeClass> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company trade classes
     * @param end the upper bound of the range of company trade classes (not inclusive)
     * @return the range of company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyTradeClass> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company trade classes
     * @param end the upper bound of the range of company trade classes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyTradeClass> findAll(int start, int end,
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

        List<CompanyTradeClass> list = (List<CompanyTradeClass>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYTRADECLASS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYTRADECLASS;

                if (pagination) {
                    sql = sql.concat(CompanyTradeClassModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyTradeClass>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyTradeClass>(list);
                } else {
                    list = (List<CompanyTradeClass>) QueryUtil.list(q,
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
     * Removes all the company trade classes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyTradeClass companyTradeClass : findAll()) {
            remove(companyTradeClass);
        }
    }

    /**
     * Returns the number of company trade classes.
     *
     * @return the number of company trade classes
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYTRADECLASS);

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
     * Initializes the company trade class persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyTradeClass")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyTradeClass>> listenersList = new ArrayList<ModelListener<CompanyTradeClass>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyTradeClass>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyTradeClassImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
