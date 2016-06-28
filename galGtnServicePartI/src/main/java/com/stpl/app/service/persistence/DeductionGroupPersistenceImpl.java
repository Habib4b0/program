package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDeductionGroupException;
import com.stpl.app.model.DeductionGroup;
import com.stpl.app.model.impl.DeductionGroupImpl;
import com.stpl.app.model.impl.DeductionGroupModelImpl;
import com.stpl.app.service.persistence.DeductionGroupPersistence;

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
 * The persistence implementation for the deduction group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionGroupPersistence
 * @see DeductionGroupUtil
 * @generated
 */
public class DeductionGroupPersistenceImpl extends BasePersistenceImpl<DeductionGroup>
    implements DeductionGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DeductionGroupUtil} to access the deduction group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DeductionGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupModelImpl.FINDER_CACHE_ENABLED,
            DeductionGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupModelImpl.FINDER_CACHE_ENABLED,
            DeductionGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEDUCTIONGROUP = "SELECT deductionGroup FROM DeductionGroup deductionGroup";
    private static final String _SQL_COUNT_DEDUCTIONGROUP = "SELECT COUNT(deductionGroup) FROM DeductionGroup deductionGroup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "deductionGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionGroup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DeductionGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "deductionFilter", "createdDate", "createdBy",
                "deductionGroupSid", "deductionGroupName", "versionNo",
                "deductionGroupDescription", "modifiedBy", "deductionGroupNo",
                "modifiedDate"
            });
    private static DeductionGroup _nullDeductionGroup = new DeductionGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DeductionGroup> toCacheModel() {
                return _nullDeductionGroupCacheModel;
            }
        };

    private static CacheModel<DeductionGroup> _nullDeductionGroupCacheModel = new CacheModel<DeductionGroup>() {
            @Override
            public DeductionGroup toEntityModel() {
                return _nullDeductionGroup;
            }
        };

    public DeductionGroupPersistenceImpl() {
        setModelClass(DeductionGroup.class);
    }

    /**
     * Caches the deduction group in the entity cache if it is enabled.
     *
     * @param deductionGroup the deduction group
     */
    @Override
    public void cacheResult(DeductionGroup deductionGroup) {
        EntityCacheUtil.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupImpl.class, deductionGroup.getPrimaryKey(),
            deductionGroup);

        deductionGroup.resetOriginalValues();
    }

    /**
     * Caches the deduction groups in the entity cache if it is enabled.
     *
     * @param deductionGroups the deduction groups
     */
    @Override
    public void cacheResult(List<DeductionGroup> deductionGroups) {
        for (DeductionGroup deductionGroup : deductionGroups) {
            if (EntityCacheUtil.getResult(
                        DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionGroupImpl.class, deductionGroup.getPrimaryKey()) == null) {
                cacheResult(deductionGroup);
            } else {
                deductionGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all deduction groups.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DeductionGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DeductionGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the deduction group.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DeductionGroup deductionGroup) {
        EntityCacheUtil.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupImpl.class, deductionGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<DeductionGroup> deductionGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DeductionGroup deductionGroup : deductionGroups) {
            EntityCacheUtil.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
                DeductionGroupImpl.class, deductionGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new deduction group with the primary key. Does not add the deduction group to the database.
     *
     * @param deductionGroupSid the primary key for the new deduction group
     * @return the new deduction group
     */
    @Override
    public DeductionGroup create(int deductionGroupSid) {
        DeductionGroup deductionGroup = new DeductionGroupImpl();

        deductionGroup.setNew(true);
        deductionGroup.setPrimaryKey(deductionGroupSid);

        return deductionGroup;
    }

    /**
     * Removes the deduction group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param deductionGroupSid the primary key of the deduction group
     * @return the deduction group that was removed
     * @throws com.stpl.app.NoSuchDeductionGroupException if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup remove(int deductionGroupSid)
        throws NoSuchDeductionGroupException, SystemException {
        return remove((Serializable) deductionGroupSid);
    }

    /**
     * Removes the deduction group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the deduction group
     * @return the deduction group that was removed
     * @throws com.stpl.app.NoSuchDeductionGroupException if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup remove(Serializable primaryKey)
        throws NoSuchDeductionGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DeductionGroup deductionGroup = (DeductionGroup) session.get(DeductionGroupImpl.class,
                    primaryKey);

            if (deductionGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDeductionGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(deductionGroup);
        } catch (NoSuchDeductionGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DeductionGroup removeImpl(DeductionGroup deductionGroup)
        throws SystemException {
        deductionGroup = toUnwrappedModel(deductionGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(deductionGroup)) {
                deductionGroup = (DeductionGroup) session.get(DeductionGroupImpl.class,
                        deductionGroup.getPrimaryKeyObj());
            }

            if (deductionGroup != null) {
                session.delete(deductionGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (deductionGroup != null) {
            clearCache(deductionGroup);
        }

        return deductionGroup;
    }

    @Override
    public DeductionGroup updateImpl(
        com.stpl.app.model.DeductionGroup deductionGroup)
        throws SystemException {
        deductionGroup = toUnwrappedModel(deductionGroup);

        boolean isNew = deductionGroup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (deductionGroup.isNew()) {
                session.save(deductionGroup);

                deductionGroup.setNew(false);
            } else {
                session.merge(deductionGroup);
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

        EntityCacheUtil.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupImpl.class, deductionGroup.getPrimaryKey(),
            deductionGroup);

        return deductionGroup;
    }

    protected DeductionGroup toUnwrappedModel(DeductionGroup deductionGroup) {
        if (deductionGroup instanceof DeductionGroupImpl) {
            return deductionGroup;
        }

        DeductionGroupImpl deductionGroupImpl = new DeductionGroupImpl();

        deductionGroupImpl.setNew(deductionGroup.isNew());
        deductionGroupImpl.setPrimaryKey(deductionGroup.getPrimaryKey());

        deductionGroupImpl.setDeductionFilter(deductionGroup.getDeductionFilter());
        deductionGroupImpl.setCreatedDate(deductionGroup.getCreatedDate());
        deductionGroupImpl.setCreatedBy(deductionGroup.getCreatedBy());
        deductionGroupImpl.setDeductionGroupSid(deductionGroup.getDeductionGroupSid());
        deductionGroupImpl.setDeductionGroupName(deductionGroup.getDeductionGroupName());
        deductionGroupImpl.setVersionNo(deductionGroup.getVersionNo());
        deductionGroupImpl.setDeductionGroupDescription(deductionGroup.getDeductionGroupDescription());
        deductionGroupImpl.setModifiedBy(deductionGroup.getModifiedBy());
        deductionGroupImpl.setDeductionGroupNo(deductionGroup.getDeductionGroupNo());
        deductionGroupImpl.setModifiedDate(deductionGroup.getModifiedDate());

        return deductionGroupImpl;
    }

    /**
     * Returns the deduction group with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the deduction group
     * @return the deduction group
     * @throws com.stpl.app.NoSuchDeductionGroupException if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDeductionGroupException, SystemException {
        DeductionGroup deductionGroup = fetchByPrimaryKey(primaryKey);

        if (deductionGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDeductionGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return deductionGroup;
    }

    /**
     * Returns the deduction group with the primary key or throws a {@link com.stpl.app.NoSuchDeductionGroupException} if it could not be found.
     *
     * @param deductionGroupSid the primary key of the deduction group
     * @return the deduction group
     * @throws com.stpl.app.NoSuchDeductionGroupException if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup findByPrimaryKey(int deductionGroupSid)
        throws NoSuchDeductionGroupException, SystemException {
        return findByPrimaryKey((Serializable) deductionGroupSid);
    }

    /**
     * Returns the deduction group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the deduction group
     * @return the deduction group, or <code>null</code> if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DeductionGroup deductionGroup = (DeductionGroup) EntityCacheUtil.getResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
                DeductionGroupImpl.class, primaryKey);

        if (deductionGroup == _nullDeductionGroup) {
            return null;
        }

        if (deductionGroup == null) {
            Session session = null;

            try {
                session = openSession();

                deductionGroup = (DeductionGroup) session.get(DeductionGroupImpl.class,
                        primaryKey);

                if (deductionGroup != null) {
                    cacheResult(deductionGroup);
                } else {
                    EntityCacheUtil.putResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionGroupImpl.class, primaryKey,
                        _nullDeductionGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DeductionGroupModelImpl.ENTITY_CACHE_ENABLED,
                    DeductionGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return deductionGroup;
    }

    /**
     * Returns the deduction group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param deductionGroupSid the primary key of the deduction group
     * @return the deduction group, or <code>null</code> if a deduction group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroup fetchByPrimaryKey(int deductionGroupSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) deductionGroupSid);
    }

    /**
     * Returns all the deduction groups.
     *
     * @return the deduction groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the deduction groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction groups
     * @param end the upper bound of the range of deduction groups (not inclusive)
     * @return the range of deduction groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the deduction groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction groups
     * @param end the upper bound of the range of deduction groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of deduction groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroup> findAll(int start, int end,
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

        List<DeductionGroup> list = (List<DeductionGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEDUCTIONGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEDUCTIONGROUP;

                if (pagination) {
                    sql = sql.concat(DeductionGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DeductionGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DeductionGroup>(list);
                } else {
                    list = (List<DeductionGroup>) QueryUtil.list(q,
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
     * Removes all the deduction groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DeductionGroup deductionGroup : findAll()) {
            remove(deductionGroup);
        }
    }

    /**
     * Returns the number of deduction groups.
     *
     * @return the number of deduction groups
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

                Query q = session.createQuery(_SQL_COUNT_DEDUCTIONGROUP);

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
     * Initializes the deduction group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DeductionGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DeductionGroup>> listenersList = new ArrayList<ModelListener<DeductionGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DeductionGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DeductionGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
