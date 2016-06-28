package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffMasterException;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.model.impl.CffMasterImpl;
import com.stpl.app.parttwo.model.impl.CffMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffMasterPersistence;

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
 * The persistence implementation for the cff master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffMasterPersistence
 * @see CffMasterUtil
 * @generated
 */
public class CffMasterPersistenceImpl extends BasePersistenceImpl<CffMaster>
    implements CffMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffMasterUtil} to access the cff master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterModelImpl.FINDER_CACHE_ENABLED, CffMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterModelImpl.FINDER_CACHE_ENABLED, CffMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFMASTER = "SELECT cffMaster FROM CffMaster cffMaster";
    private static final String _SQL_COUNT_CFFMASTER = "SELECT COUNT(cffMaster) FROM CffMaster cffMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "productHierarchyLevel", "activeFromDate", "cffType",
                "cffOfficial", "cffMasterSid", "productHierVersionNo",
                "activeToDate", "customerHierVersionNo", "modifiedDate",
                "customerHierarchyLevel", "productHierarchySid", "cffName",
                "customerHierarchyInnerLevel", "createdDate", "createdBy",
                "customerHierarchySid", "companyGroupSid",
                "prodRelationshipBuilderSid", "modifiedBy", "inboundStatus",
                "productHierarchyInnerLevel", "itemGroupSid",
                "custRelationshipBuilderSid", "companyMasterSid"
            });
    private static CffMaster _nullCffMaster = new CffMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffMaster> toCacheModel() {
                return _nullCffMasterCacheModel;
            }
        };

    private static CacheModel<CffMaster> _nullCffMasterCacheModel = new CacheModel<CffMaster>() {
            @Override
            public CffMaster toEntityModel() {
                return _nullCffMaster;
            }
        };

    public CffMasterPersistenceImpl() {
        setModelClass(CffMaster.class);
    }

    /**
     * Caches the cff master in the entity cache if it is enabled.
     *
     * @param cffMaster the cff master
     */
    @Override
    public void cacheResult(CffMaster cffMaster) {
        EntityCacheUtil.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterImpl.class, cffMaster.getPrimaryKey(), cffMaster);

        cffMaster.resetOriginalValues();
    }

    /**
     * Caches the cff masters in the entity cache if it is enabled.
     *
     * @param cffMasters the cff masters
     */
    @Override
    public void cacheResult(List<CffMaster> cffMasters) {
        for (CffMaster cffMaster : cffMasters) {
            if (EntityCacheUtil.getResult(
                        CffMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffMasterImpl.class, cffMaster.getPrimaryKey()) == null) {
                cacheResult(cffMaster);
            } else {
                cffMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffMaster cffMaster) {
        EntityCacheUtil.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterImpl.class, cffMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffMaster> cffMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffMaster cffMaster : cffMasters) {
            EntityCacheUtil.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffMasterImpl.class, cffMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff master with the primary key. Does not add the cff master to the database.
     *
     * @param cffMasterSid the primary key for the new cff master
     * @return the new cff master
     */
    @Override
    public CffMaster create(int cffMasterSid) {
        CffMaster cffMaster = new CffMasterImpl();

        cffMaster.setNew(true);
        cffMaster.setPrimaryKey(cffMasterSid);

        return cffMaster;
    }

    /**
     * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffMasterSid the primary key of the cff master
     * @return the cff master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster remove(int cffMasterSid)
        throws NoSuchCffMasterException, SystemException {
        return remove((Serializable) cffMasterSid);
    }

    /**
     * Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff master
     * @return the cff master that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster remove(Serializable primaryKey)
        throws NoSuchCffMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffMaster cffMaster = (CffMaster) session.get(CffMasterImpl.class,
                    primaryKey);

            if (cffMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffMaster);
        } catch (NoSuchCffMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffMaster removeImpl(CffMaster cffMaster)
        throws SystemException {
        cffMaster = toUnwrappedModel(cffMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffMaster)) {
                cffMaster = (CffMaster) session.get(CffMasterImpl.class,
                        cffMaster.getPrimaryKeyObj());
            }

            if (cffMaster != null) {
                session.delete(cffMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffMaster != null) {
            clearCache(cffMaster);
        }

        return cffMaster;
    }

    @Override
    public CffMaster updateImpl(com.stpl.app.parttwo.model.CffMaster cffMaster)
        throws SystemException {
        cffMaster = toUnwrappedModel(cffMaster);

        boolean isNew = cffMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffMaster.isNew()) {
                session.save(cffMaster);

                cffMaster.setNew(false);
            } else {
                session.merge(cffMaster);
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

        EntityCacheUtil.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
            CffMasterImpl.class, cffMaster.getPrimaryKey(), cffMaster);

        return cffMaster;
    }

    protected CffMaster toUnwrappedModel(CffMaster cffMaster) {
        if (cffMaster instanceof CffMasterImpl) {
            return cffMaster;
        }

        CffMasterImpl cffMasterImpl = new CffMasterImpl();

        cffMasterImpl.setNew(cffMaster.isNew());
        cffMasterImpl.setPrimaryKey(cffMaster.getPrimaryKey());

        cffMasterImpl.setProductHierarchyLevel(cffMaster.getProductHierarchyLevel());
        cffMasterImpl.setActiveFromDate(cffMaster.getActiveFromDate());
        cffMasterImpl.setCffType(cffMaster.getCffType());
        cffMasterImpl.setCffOfficial(cffMaster.isCffOfficial());
        cffMasterImpl.setCffMasterSid(cffMaster.getCffMasterSid());
        cffMasterImpl.setProductHierVersionNo(cffMaster.getProductHierVersionNo());
        cffMasterImpl.setActiveToDate(cffMaster.getActiveToDate());
        cffMasterImpl.setCustomerHierVersionNo(cffMaster.getCustomerHierVersionNo());
        cffMasterImpl.setModifiedDate(cffMaster.getModifiedDate());
        cffMasterImpl.setCustomerHierarchyLevel(cffMaster.getCustomerHierarchyLevel());
        cffMasterImpl.setProductHierarchySid(cffMaster.getProductHierarchySid());
        cffMasterImpl.setCffName(cffMaster.getCffName());
        cffMasterImpl.setCustomerHierarchyInnerLevel(cffMaster.getCustomerHierarchyInnerLevel());
        cffMasterImpl.setCreatedDate(cffMaster.getCreatedDate());
        cffMasterImpl.setCreatedBy(cffMaster.getCreatedBy());
        cffMasterImpl.setCustomerHierarchySid(cffMaster.getCustomerHierarchySid());
        cffMasterImpl.setCompanyGroupSid(cffMaster.getCompanyGroupSid());
        cffMasterImpl.setProdRelationshipBuilderSid(cffMaster.getProdRelationshipBuilderSid());
        cffMasterImpl.setModifiedBy(cffMaster.getModifiedBy());
        cffMasterImpl.setInboundStatus(cffMaster.getInboundStatus());
        cffMasterImpl.setProductHierarchyInnerLevel(cffMaster.getProductHierarchyInnerLevel());
        cffMasterImpl.setItemGroupSid(cffMaster.getItemGroupSid());
        cffMasterImpl.setCustRelationshipBuilderSid(cffMaster.getCustRelationshipBuilderSid());
        cffMasterImpl.setCompanyMasterSid(cffMaster.getCompanyMasterSid());

        return cffMasterImpl;
    }

    /**
     * Returns the cff master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff master
     * @return the cff master
     * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffMasterException, SystemException {
        CffMaster cffMaster = fetchByPrimaryKey(primaryKey);

        if (cffMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffMaster;
    }

    /**
     * Returns the cff master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffMasterException} if it could not be found.
     *
     * @param cffMasterSid the primary key of the cff master
     * @return the cff master
     * @throws com.stpl.app.parttwo.NoSuchCffMasterException if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster findByPrimaryKey(int cffMasterSid)
        throws NoSuchCffMasterException, SystemException {
        return findByPrimaryKey((Serializable) cffMasterSid);
    }

    /**
     * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff master
     * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffMaster cffMaster = (CffMaster) EntityCacheUtil.getResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
                CffMasterImpl.class, primaryKey);

        if (cffMaster == _nullCffMaster) {
            return null;
        }

        if (cffMaster == null) {
            Session session = null;

            try {
                session = openSession();

                cffMaster = (CffMaster) session.get(CffMasterImpl.class,
                        primaryKey);

                if (cffMaster != null) {
                    cacheResult(cffMaster);
                } else {
                    EntityCacheUtil.putResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
                        CffMasterImpl.class, primaryKey, _nullCffMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffMasterModelImpl.ENTITY_CACHE_ENABLED,
                    CffMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffMaster;
    }

    /**
     * Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffMasterSid the primary key of the cff master
     * @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffMaster fetchByPrimaryKey(int cffMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffMasterSid);
    }

    /**
     * Returns all the cff masters.
     *
     * @return the cff masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff masters
     * @param end the upper bound of the range of cff masters (not inclusive)
     * @return the range of cff masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff masters
     * @param end the upper bound of the range of cff masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffMaster> findAll(int start, int end,
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

        List<CffMaster> list = (List<CffMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFMASTER;

                if (pagination) {
                    sql = sql.concat(CffMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffMaster>(list);
                } else {
                    list = (List<CffMaster>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the cff masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffMaster cffMaster : findAll()) {
            remove(cffMaster);
        }
    }

    /**
     * Returns the number of cff masters.
     *
     * @return the number of cff masters
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

                Query q = session.createQuery(_SQL_COUNT_CFFMASTER);

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
     * Initializes the cff master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffMaster>> listenersList = new ArrayList<ModelListener<CffMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
