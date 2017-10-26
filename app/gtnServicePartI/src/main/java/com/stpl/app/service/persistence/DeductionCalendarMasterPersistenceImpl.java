package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDeductionCalendarMasterException;
import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.model.impl.DeductionCalendarMasterImpl;
import com.stpl.app.model.impl.DeductionCalendarMasterModelImpl;
import com.stpl.app.service.persistence.DeductionCalendarMasterPersistence;

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
 * The persistence implementation for the deduction calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterPersistence
 * @see DeductionCalendarMasterUtil
 * @generated
 */
public class DeductionCalendarMasterPersistenceImpl extends BasePersistenceImpl<DeductionCalendarMaster>
    implements DeductionCalendarMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DeductionCalendarMasterUtil} to access the deduction calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DeductionCalendarMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
            DeductionCalendarMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
            DeductionCalendarMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEDUCTIONCALENDARMASTER = "SELECT deductionCalendarMaster FROM DeductionCalendarMaster deductionCalendarMaster";
    private static final String _SQL_COUNT_DEDUCTIONCALENDARMASTER = "SELECT COUNT(deductionCalendarMaster) FROM DeductionCalendarMaster deductionCalendarMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "deductionCalendarMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionCalendarMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DeductionCalendarMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "deductionCalendarMasterSid", "deductionCalendarNo",
                "modifiedBy", "createdDate", "category", "additionalNotes",
                "userId", "deductionCalendarName", "deductionCalendarDesc",
                "modifiedDate", "inboundStatus"
            });
    private static DeductionCalendarMaster _nullDeductionCalendarMaster = new DeductionCalendarMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DeductionCalendarMaster> toCacheModel() {
                return _nullDeductionCalendarMasterCacheModel;
            }
        };

    private static CacheModel<DeductionCalendarMaster> _nullDeductionCalendarMasterCacheModel =
        new CacheModel<DeductionCalendarMaster>() {
            @Override
            public DeductionCalendarMaster toEntityModel() {
                return _nullDeductionCalendarMaster;
            }
        };

    public DeductionCalendarMasterPersistenceImpl() {
        setModelClass(DeductionCalendarMaster.class);
    }

    /**
     * Caches the deduction calendar master in the entity cache if it is enabled.
     *
     * @param deductionCalendarMaster the deduction calendar master
     */
    @Override
    public void cacheResult(DeductionCalendarMaster deductionCalendarMaster) {
        EntityCacheUtil.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterImpl.class,
            deductionCalendarMaster.getPrimaryKey(), deductionCalendarMaster);

        deductionCalendarMaster.resetOriginalValues();
    }

    /**
     * Caches the deduction calendar masters in the entity cache if it is enabled.
     *
     * @param deductionCalendarMasters the deduction calendar masters
     */
    @Override
    public void cacheResult(
        List<DeductionCalendarMaster> deductionCalendarMasters) {
        for (DeductionCalendarMaster deductionCalendarMaster : deductionCalendarMasters) {
            if (EntityCacheUtil.getResult(
                        DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionCalendarMasterImpl.class,
                        deductionCalendarMaster.getPrimaryKey()) == null) {
                cacheResult(deductionCalendarMaster);
            } else {
                deductionCalendarMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all deduction calendar masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DeductionCalendarMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DeductionCalendarMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the deduction calendar master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DeductionCalendarMaster deductionCalendarMaster) {
        EntityCacheUtil.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterImpl.class,
            deductionCalendarMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<DeductionCalendarMaster> deductionCalendarMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DeductionCalendarMaster deductionCalendarMaster : deductionCalendarMasters) {
            EntityCacheUtil.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                DeductionCalendarMasterImpl.class,
                deductionCalendarMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
     *
     * @param deductionCalendarMasterSid the primary key for the new deduction calendar master
     * @return the new deduction calendar master
     */
    @Override
    public DeductionCalendarMaster create(int deductionCalendarMasterSid) {
        DeductionCalendarMaster deductionCalendarMaster = new DeductionCalendarMasterImpl();

        deductionCalendarMaster.setNew(true);
        deductionCalendarMaster.setPrimaryKey(deductionCalendarMasterSid);

        return deductionCalendarMaster;
    }

    /**
     * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param deductionCalendarMasterSid the primary key of the deduction calendar master
     * @return the deduction calendar master that was removed
     * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster remove(int deductionCalendarMasterSid)
        throws NoSuchDeductionCalendarMasterException, SystemException {
        return remove((Serializable) deductionCalendarMasterSid);
    }

    /**
     * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the deduction calendar master
     * @return the deduction calendar master that was removed
     * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster remove(Serializable primaryKey)
        throws NoSuchDeductionCalendarMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DeductionCalendarMaster deductionCalendarMaster = (DeductionCalendarMaster) session.get(DeductionCalendarMasterImpl.class,
                    primaryKey);

            if (deductionCalendarMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDeductionCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(deductionCalendarMaster);
        } catch (NoSuchDeductionCalendarMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DeductionCalendarMaster removeImpl(
        DeductionCalendarMaster deductionCalendarMaster)
        throws SystemException {
        deductionCalendarMaster = toUnwrappedModel(deductionCalendarMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(deductionCalendarMaster)) {
                deductionCalendarMaster = (DeductionCalendarMaster) session.get(DeductionCalendarMasterImpl.class,
                        deductionCalendarMaster.getPrimaryKeyObj());
            }

            if (deductionCalendarMaster != null) {
                session.delete(deductionCalendarMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (deductionCalendarMaster != null) {
            clearCache(deductionCalendarMaster);
        }

        return deductionCalendarMaster;
    }

    @Override
    public DeductionCalendarMaster updateImpl(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws SystemException {
        deductionCalendarMaster = toUnwrappedModel(deductionCalendarMaster);

        boolean isNew = deductionCalendarMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (deductionCalendarMaster.isNew()) {
                session.save(deductionCalendarMaster);

                deductionCalendarMaster.setNew(false);
            } else {
                session.merge(deductionCalendarMaster);
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

        EntityCacheUtil.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            DeductionCalendarMasterImpl.class,
            deductionCalendarMaster.getPrimaryKey(), deductionCalendarMaster);

        return deductionCalendarMaster;
    }

    protected DeductionCalendarMaster toUnwrappedModel(
        DeductionCalendarMaster deductionCalendarMaster) {
        if (deductionCalendarMaster instanceof DeductionCalendarMasterImpl) {
            return deductionCalendarMaster;
        }

        DeductionCalendarMasterImpl deductionCalendarMasterImpl = new DeductionCalendarMasterImpl();

        deductionCalendarMasterImpl.setNew(deductionCalendarMaster.isNew());
        deductionCalendarMasterImpl.setPrimaryKey(deductionCalendarMaster.getPrimaryKey());

        deductionCalendarMasterImpl.setCreatedBy(deductionCalendarMaster.getCreatedBy());
        deductionCalendarMasterImpl.setDeductionCalendarMasterSid(deductionCalendarMaster.getDeductionCalendarMasterSid());
        deductionCalendarMasterImpl.setDeductionCalendarNo(deductionCalendarMaster.getDeductionCalendarNo());
        deductionCalendarMasterImpl.setModifiedBy(deductionCalendarMaster.getModifiedBy());
        deductionCalendarMasterImpl.setCreatedDate(deductionCalendarMaster.getCreatedDate());
        deductionCalendarMasterImpl.setCategory(deductionCalendarMaster.getCategory());
        deductionCalendarMasterImpl.setAdditionalNotes(deductionCalendarMaster.getAdditionalNotes());
        deductionCalendarMasterImpl.setUserId(deductionCalendarMaster.getUserId());
        deductionCalendarMasterImpl.setDeductionCalendarName(deductionCalendarMaster.getDeductionCalendarName());
        deductionCalendarMasterImpl.setDeductionCalendarDesc(deductionCalendarMaster.getDeductionCalendarDesc());
        deductionCalendarMasterImpl.setModifiedDate(deductionCalendarMaster.getModifiedDate());
        deductionCalendarMasterImpl.setInboundStatus(deductionCalendarMaster.getInboundStatus());

        return deductionCalendarMasterImpl;
    }

    /**
     * Returns the deduction calendar master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the deduction calendar master
     * @return the deduction calendar master
     * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDeductionCalendarMasterException, SystemException {
        DeductionCalendarMaster deductionCalendarMaster = fetchByPrimaryKey(primaryKey);

        if (deductionCalendarMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDeductionCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return deductionCalendarMaster;
    }

    /**
     * Returns the deduction calendar master with the primary key or throws a {@link com.stpl.app.NoSuchDeductionCalendarMasterException} if it could not be found.
     *
     * @param deductionCalendarMasterSid the primary key of the deduction calendar master
     * @return the deduction calendar master
     * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster findByPrimaryKey(
        int deductionCalendarMasterSid)
        throws NoSuchDeductionCalendarMasterException, SystemException {
        return findByPrimaryKey((Serializable) deductionCalendarMasterSid);
    }

    /**
     * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the deduction calendar master
     * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DeductionCalendarMaster deductionCalendarMaster = (DeductionCalendarMaster) EntityCacheUtil.getResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                DeductionCalendarMasterImpl.class, primaryKey);

        if (deductionCalendarMaster == _nullDeductionCalendarMaster) {
            return null;
        }

        if (deductionCalendarMaster == null) {
            Session session = null;

            try {
                session = openSession();

                deductionCalendarMaster = (DeductionCalendarMaster) session.get(DeductionCalendarMasterImpl.class,
                        primaryKey);

                if (deductionCalendarMaster != null) {
                    cacheResult(deductionCalendarMaster);
                } else {
                    EntityCacheUtil.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionCalendarMasterImpl.class, primaryKey,
                        _nullDeductionCalendarMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                    DeductionCalendarMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return deductionCalendarMaster;
    }

    /**
     * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param deductionCalendarMasterSid the primary key of the deduction calendar master
     * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionCalendarMaster fetchByPrimaryKey(
        int deductionCalendarMasterSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) deductionCalendarMasterSid);
    }

    /**
     * Returns all the deduction calendar masters.
     *
     * @return the deduction calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the deduction calendar masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction calendar masters
     * @param end the upper bound of the range of deduction calendar masters (not inclusive)
     * @return the range of deduction calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the deduction calendar masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction calendar masters
     * @param end the upper bound of the range of deduction calendar masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of deduction calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionCalendarMaster> findAll(int start, int end,
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

        List<DeductionCalendarMaster> list = (List<DeductionCalendarMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEDUCTIONCALENDARMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEDUCTIONCALENDARMASTER;

                if (pagination) {
                    sql = sql.concat(DeductionCalendarMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DeductionCalendarMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DeductionCalendarMaster>(list);
                } else {
                    list = (List<DeductionCalendarMaster>) QueryUtil.list(q,
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
     * Removes all the deduction calendar masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DeductionCalendarMaster deductionCalendarMaster : findAll()) {
            remove(deductionCalendarMaster);
        }
    }

    /**
     * Returns the number of deduction calendar masters.
     *
     * @return the number of deduction calendar masters
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

                Query q = session.createQuery(_SQL_COUNT_DEDUCTIONCALENDARMASTER);

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
     * Initializes the deduction calendar master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DeductionCalendarMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DeductionCalendarMaster>> listenersList = new ArrayList<ModelListener<DeductionCalendarMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DeductionCalendarMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DeductionCalendarMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
