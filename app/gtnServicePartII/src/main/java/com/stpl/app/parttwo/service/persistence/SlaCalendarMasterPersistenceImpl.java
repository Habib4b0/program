package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchSlaCalendarMasterException;
import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.model.impl.SlaCalendarMasterImpl;
import com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.SlaCalendarMasterPersistence;

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
 * The persistence implementation for the sla calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarMasterPersistence
 * @see SlaCalendarMasterUtil
 * @generated
 */
public class SlaCalendarMasterPersistenceImpl extends BasePersistenceImpl<SlaCalendarMaster>
    implements SlaCalendarMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SlaCalendarMasterUtil} to access the sla calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SlaCalendarMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
            SlaCalendarMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
            SlaCalendarMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_SLACALENDARMASTER = "SELECT slaCalendarMaster FROM SlaCalendarMaster slaCalendarMaster";
    private static final String _SQL_COUNT_SLACALENDARMASTER = "SELECT COUNT(slaCalendarMaster) FROM SlaCalendarMaster slaCalendarMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "slaCalendarMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SlaCalendarMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SlaCalendarMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "modifiedBy", "slaCalendarMasterSid", "createdDate",
                "defaultHolidays", "calendarName", "modifiedDate",
                "inboundStatus"
            });
    private static SlaCalendarMaster _nullSlaCalendarMaster = new SlaCalendarMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SlaCalendarMaster> toCacheModel() {
                return _nullSlaCalendarMasterCacheModel;
            }
        };

    private static CacheModel<SlaCalendarMaster> _nullSlaCalendarMasterCacheModel =
        new CacheModel<SlaCalendarMaster>() {
            @Override
            public SlaCalendarMaster toEntityModel() {
                return _nullSlaCalendarMaster;
            }
        };

    public SlaCalendarMasterPersistenceImpl() {
        setModelClass(SlaCalendarMaster.class);
    }

    /**
     * Caches the sla calendar master in the entity cache if it is enabled.
     *
     * @param slaCalendarMaster the sla calendar master
     */
    @Override
    public void cacheResult(SlaCalendarMaster slaCalendarMaster) {
        EntityCacheUtil.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey(),
            slaCalendarMaster);

        slaCalendarMaster.resetOriginalValues();
    }

    /**
     * Caches the sla calendar masters in the entity cache if it is enabled.
     *
     * @param slaCalendarMasters the sla calendar masters
     */
    @Override
    public void cacheResult(List<SlaCalendarMaster> slaCalendarMasters) {
        for (SlaCalendarMaster slaCalendarMaster : slaCalendarMasters) {
            if (EntityCacheUtil.getResult(
                        SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                        SlaCalendarMasterImpl.class,
                        slaCalendarMaster.getPrimaryKey()) == null) {
                cacheResult(slaCalendarMaster);
            } else {
                slaCalendarMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all sla calendar masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SlaCalendarMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SlaCalendarMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the sla calendar master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SlaCalendarMaster slaCalendarMaster) {
        EntityCacheUtil.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SlaCalendarMaster> slaCalendarMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SlaCalendarMaster slaCalendarMaster : slaCalendarMasters) {
            EntityCacheUtil.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
     *
     * @param slaCalendarMasterSid the primary key for the new sla calendar master
     * @return the new sla calendar master
     */
    @Override
    public SlaCalendarMaster create(int slaCalendarMasterSid) {
        SlaCalendarMaster slaCalendarMaster = new SlaCalendarMasterImpl();

        slaCalendarMaster.setNew(true);
        slaCalendarMaster.setPrimaryKey(slaCalendarMasterSid);

        return slaCalendarMaster;
    }

    /**
     * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param slaCalendarMasterSid the primary key of the sla calendar master
     * @return the sla calendar master that was removed
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster remove(int slaCalendarMasterSid)
        throws NoSuchSlaCalendarMasterException, SystemException {
        return remove((Serializable) slaCalendarMasterSid);
    }

    /**
     * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the sla calendar master
     * @return the sla calendar master that was removed
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster remove(Serializable primaryKey)
        throws NoSuchSlaCalendarMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SlaCalendarMaster slaCalendarMaster = (SlaCalendarMaster) session.get(SlaCalendarMasterImpl.class,
                    primaryKey);

            if (slaCalendarMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSlaCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(slaCalendarMaster);
        } catch (NoSuchSlaCalendarMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SlaCalendarMaster removeImpl(SlaCalendarMaster slaCalendarMaster)
        throws SystemException {
        slaCalendarMaster = toUnwrappedModel(slaCalendarMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(slaCalendarMaster)) {
                slaCalendarMaster = (SlaCalendarMaster) session.get(SlaCalendarMasterImpl.class,
                        slaCalendarMaster.getPrimaryKeyObj());
            }

            if (slaCalendarMaster != null) {
                session.delete(slaCalendarMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (slaCalendarMaster != null) {
            clearCache(slaCalendarMaster);
        }

        return slaCalendarMaster;
    }

    @Override
    public SlaCalendarMaster updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster)
        throws SystemException {
        slaCalendarMaster = toUnwrappedModel(slaCalendarMaster);

        boolean isNew = slaCalendarMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (slaCalendarMaster.isNew()) {
                session.save(slaCalendarMaster);

                slaCalendarMaster.setNew(false);
            } else {
                session.merge(slaCalendarMaster);
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

        EntityCacheUtil.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey(),
            slaCalendarMaster);

        return slaCalendarMaster;
    }

    protected SlaCalendarMaster toUnwrappedModel(
        SlaCalendarMaster slaCalendarMaster) {
        if (slaCalendarMaster instanceof SlaCalendarMasterImpl) {
            return slaCalendarMaster;
        }

        SlaCalendarMasterImpl slaCalendarMasterImpl = new SlaCalendarMasterImpl();

        slaCalendarMasterImpl.setNew(slaCalendarMaster.isNew());
        slaCalendarMasterImpl.setPrimaryKey(slaCalendarMaster.getPrimaryKey());

        slaCalendarMasterImpl.setCreatedBy(slaCalendarMaster.getCreatedBy());
        slaCalendarMasterImpl.setModifiedBy(slaCalendarMaster.getModifiedBy());
        slaCalendarMasterImpl.setSlaCalendarMasterSid(slaCalendarMaster.getSlaCalendarMasterSid());
        slaCalendarMasterImpl.setCreatedDate(slaCalendarMaster.getCreatedDate());
        slaCalendarMasterImpl.setDefaultHolidays(slaCalendarMaster.isDefaultHolidays());
        slaCalendarMasterImpl.setCalendarName(slaCalendarMaster.getCalendarName());
        slaCalendarMasterImpl.setModifiedDate(slaCalendarMaster.getModifiedDate());
        slaCalendarMasterImpl.setInboundStatus(slaCalendarMaster.getInboundStatus());

        return slaCalendarMasterImpl;
    }

    /**
     * Returns the sla calendar master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the sla calendar master
     * @return the sla calendar master
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSlaCalendarMasterException, SystemException {
        SlaCalendarMaster slaCalendarMaster = fetchByPrimaryKey(primaryKey);

        if (slaCalendarMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSlaCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return slaCalendarMaster;
    }

    /**
     * Returns the sla calendar master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarMasterException} if it could not be found.
     *
     * @param slaCalendarMasterSid the primary key of the sla calendar master
     * @return the sla calendar master
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster findByPrimaryKey(int slaCalendarMasterSid)
        throws NoSuchSlaCalendarMasterException, SystemException {
        return findByPrimaryKey((Serializable) slaCalendarMasterSid);
    }

    /**
     * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the sla calendar master
     * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SlaCalendarMaster slaCalendarMaster = (SlaCalendarMaster) EntityCacheUtil.getResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                SlaCalendarMasterImpl.class, primaryKey);

        if (slaCalendarMaster == _nullSlaCalendarMaster) {
            return null;
        }

        if (slaCalendarMaster == null) {
            Session session = null;

            try {
                session = openSession();

                slaCalendarMaster = (SlaCalendarMaster) session.get(SlaCalendarMasterImpl.class,
                        primaryKey);

                if (slaCalendarMaster != null) {
                    cacheResult(slaCalendarMaster);
                } else {
                    EntityCacheUtil.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                        SlaCalendarMasterImpl.class, primaryKey,
                        _nullSlaCalendarMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
                    SlaCalendarMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return slaCalendarMaster;
    }

    /**
     * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param slaCalendarMasterSid the primary key of the sla calendar master
     * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarMaster fetchByPrimaryKey(int slaCalendarMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) slaCalendarMasterSid);
    }

    /**
     * Returns all the sla calendar masters.
     *
     * @return the sla calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sla calendar masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sla calendar masters
     * @param end the upper bound of the range of sla calendar masters (not inclusive)
     * @return the range of sla calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the sla calendar masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sla calendar masters
     * @param end the upper bound of the range of sla calendar masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of sla calendar masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarMaster> findAll(int start, int end,
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

        List<SlaCalendarMaster> list = (List<SlaCalendarMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SLACALENDARMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SLACALENDARMASTER;

                if (pagination) {
                    sql = sql.concat(SlaCalendarMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SlaCalendarMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SlaCalendarMaster>(list);
                } else {
                    list = (List<SlaCalendarMaster>) QueryUtil.list(q,
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
     * Removes all the sla calendar masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SlaCalendarMaster slaCalendarMaster : findAll()) {
            remove(slaCalendarMaster);
        }
    }

    /**
     * Returns the number of sla calendar masters.
     *
     * @return the number of sla calendar masters
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

                Query q = session.createQuery(_SQL_COUNT_SLACALENDARMASTER);

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
     * Initializes the sla calendar master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.SlaCalendarMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SlaCalendarMaster>> listenersList = new ArrayList<ModelListener<SlaCalendarMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SlaCalendarMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SlaCalendarMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
