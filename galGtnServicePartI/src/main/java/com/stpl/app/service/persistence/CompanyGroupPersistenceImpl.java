package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchCompanyGroupException;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.model.impl.CompanyGroupImpl;
import com.stpl.app.model.impl.CompanyGroupModelImpl;
import com.stpl.app.service.persistence.CompanyGroupPersistence;

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
 * The persistence implementation for the company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupPersistence
 * @see CompanyGroupUtil
 * @generated
 */
public class CompanyGroupPersistenceImpl extends BasePersistenceImpl<CompanyGroup>
    implements CompanyGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CompanyGroupUtil} to access the company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CompanyGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupModelImpl.FINDER_CACHE_ENABLED, CompanyGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupModelImpl.FINDER_CACHE_ENABLED, CompanyGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_COMPANYGROUP = "SELECT companyGroup FROM CompanyGroup companyGroup";
    private static final String _SQL_COUNT_COMPANYGROUP = "SELECT COUNT(companyGroup) FROM CompanyGroup companyGroup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "companyGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyGroup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CompanyGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "companyGroupNo", "createdDate", "createdBy", "companyFilter",
                "companyGroupSid", "companyGroupDescription", "versionNo",
                "modifiedBy", "modifiedDate", "companyGroupName"
            });
    private static CompanyGroup _nullCompanyGroup = new CompanyGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CompanyGroup> toCacheModel() {
                return _nullCompanyGroupCacheModel;
            }
        };

    private static CacheModel<CompanyGroup> _nullCompanyGroupCacheModel = new CacheModel<CompanyGroup>() {
            @Override
            public CompanyGroup toEntityModel() {
                return _nullCompanyGroup;
            }
        };

    public CompanyGroupPersistenceImpl() {
        setModelClass(CompanyGroup.class);
    }

    /**
     * Caches the company group in the entity cache if it is enabled.
     *
     * @param companyGroup the company group
     */
    @Override
    public void cacheResult(CompanyGroup companyGroup) {
        EntityCacheUtil.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupImpl.class, companyGroup.getPrimaryKey(), companyGroup);

        companyGroup.resetOriginalValues();
    }

    /**
     * Caches the company groups in the entity cache if it is enabled.
     *
     * @param companyGroups the company groups
     */
    @Override
    public void cacheResult(List<CompanyGroup> companyGroups) {
        for (CompanyGroup companyGroup : companyGroups) {
            if (EntityCacheUtil.getResult(
                        CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyGroupImpl.class, companyGroup.getPrimaryKey()) == null) {
                cacheResult(companyGroup);
            } else {
                companyGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all company groups.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CompanyGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CompanyGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the company group.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CompanyGroup companyGroup) {
        EntityCacheUtil.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupImpl.class, companyGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CompanyGroup> companyGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CompanyGroup companyGroup : companyGroups) {
            EntityCacheUtil.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                CompanyGroupImpl.class, companyGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new company group with the primary key. Does not add the company group to the database.
     *
     * @param companyGroupSid the primary key for the new company group
     * @return the new company group
     */
    @Override
    public CompanyGroup create(int companyGroupSid) {
        CompanyGroup companyGroup = new CompanyGroupImpl();

        companyGroup.setNew(true);
        companyGroup.setPrimaryKey(companyGroupSid);

        return companyGroup;
    }

    /**
     * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyGroupSid the primary key of the company group
     * @return the company group that was removed
     * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup remove(int companyGroupSid)
        throws NoSuchCompanyGroupException, SystemException {
        return remove((Serializable) companyGroupSid);
    }

    /**
     * Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the company group
     * @return the company group that was removed
     * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup remove(Serializable primaryKey)
        throws NoSuchCompanyGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CompanyGroup companyGroup = (CompanyGroup) session.get(CompanyGroupImpl.class,
                    primaryKey);

            if (companyGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(companyGroup);
        } catch (NoSuchCompanyGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CompanyGroup removeImpl(CompanyGroup companyGroup)
        throws SystemException {
        companyGroup = toUnwrappedModel(companyGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(companyGroup)) {
                companyGroup = (CompanyGroup) session.get(CompanyGroupImpl.class,
                        companyGroup.getPrimaryKeyObj());
            }

            if (companyGroup != null) {
                session.delete(companyGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (companyGroup != null) {
            clearCache(companyGroup);
        }

        return companyGroup;
    }

    @Override
    public CompanyGroup updateImpl(com.stpl.app.model.CompanyGroup companyGroup)
        throws SystemException {
        companyGroup = toUnwrappedModel(companyGroup);

        boolean isNew = companyGroup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (companyGroup.isNew()) {
                session.save(companyGroup);

                companyGroup.setNew(false);
            } else {
                session.merge(companyGroup);
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

        EntityCacheUtil.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
            CompanyGroupImpl.class, companyGroup.getPrimaryKey(), companyGroup);

        return companyGroup;
    }

    protected CompanyGroup toUnwrappedModel(CompanyGroup companyGroup) {
        if (companyGroup instanceof CompanyGroupImpl) {
            return companyGroup;
        }

        CompanyGroupImpl companyGroupImpl = new CompanyGroupImpl();

        companyGroupImpl.setNew(companyGroup.isNew());
        companyGroupImpl.setPrimaryKey(companyGroup.getPrimaryKey());

        companyGroupImpl.setCompanyGroupNo(companyGroup.getCompanyGroupNo());
        companyGroupImpl.setCreatedDate(companyGroup.getCreatedDate());
        companyGroupImpl.setCreatedBy(companyGroup.getCreatedBy());
        companyGroupImpl.setCompanyFilter(companyGroup.getCompanyFilter());
        companyGroupImpl.setCompanyGroupSid(companyGroup.getCompanyGroupSid());
        companyGroupImpl.setCompanyGroupDescription(companyGroup.getCompanyGroupDescription());
        companyGroupImpl.setVersionNo(companyGroup.getVersionNo());
        companyGroupImpl.setModifiedBy(companyGroup.getModifiedBy());
        companyGroupImpl.setModifiedDate(companyGroup.getModifiedDate());
        companyGroupImpl.setCompanyGroupName(companyGroup.getCompanyGroupName());

        return companyGroupImpl;
    }

    /**
     * Returns the company group with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the company group
     * @return the company group
     * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCompanyGroupException, SystemException {
        CompanyGroup companyGroup = fetchByPrimaryKey(primaryKey);

        if (companyGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCompanyGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return companyGroup;
    }

    /**
     * Returns the company group with the primary key or throws a {@link com.stpl.app.NoSuchCompanyGroupException} if it could not be found.
     *
     * @param companyGroupSid the primary key of the company group
     * @return the company group
     * @throws com.stpl.app.NoSuchCompanyGroupException if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup findByPrimaryKey(int companyGroupSid)
        throws NoSuchCompanyGroupException, SystemException {
        return findByPrimaryKey((Serializable) companyGroupSid);
    }

    /**
     * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the company group
     * @return the company group, or <code>null</code> if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CompanyGroup companyGroup = (CompanyGroup) EntityCacheUtil.getResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                CompanyGroupImpl.class, primaryKey);

        if (companyGroup == _nullCompanyGroup) {
            return null;
        }

        if (companyGroup == null) {
            Session session = null;

            try {
                session = openSession();

                companyGroup = (CompanyGroup) session.get(CompanyGroupImpl.class,
                        primaryKey);

                if (companyGroup != null) {
                    cacheResult(companyGroup);
                } else {
                    EntityCacheUtil.putResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                        CompanyGroupImpl.class, primaryKey, _nullCompanyGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CompanyGroupModelImpl.ENTITY_CACHE_ENABLED,
                    CompanyGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return companyGroup;
    }

    /**
     * Returns the company group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyGroupSid the primary key of the company group
     * @return the company group, or <code>null</code> if a company group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CompanyGroup fetchByPrimaryKey(int companyGroupSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyGroupSid);
    }

    /**
     * Returns all the company groups.
     *
     * @return the company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the company groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company groups
     * @param end the upper bound of the range of company groups (not inclusive)
     * @return the range of company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the company groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of company groups
     * @param end the upper bound of the range of company groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of company groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CompanyGroup> findAll(int start, int end,
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

        List<CompanyGroup> list = (List<CompanyGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_COMPANYGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_COMPANYGROUP;

                if (pagination) {
                    sql = sql.concat(CompanyGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CompanyGroup>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CompanyGroup>(list);
                } else {
                    list = (List<CompanyGroup>) QueryUtil.list(q, getDialect(),
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
     * Removes all the company groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CompanyGroup companyGroup : findAll()) {
            remove(companyGroup);
        }
    }

    /**
     * Returns the number of company groups.
     *
     * @return the number of company groups
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

                Query q = session.createQuery(_SQL_COUNT_COMPANYGROUP);

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
     * Initializes the company group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.CompanyGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CompanyGroup>> listenersList = new ArrayList<ModelListener<CompanyGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CompanyGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CompanyGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
