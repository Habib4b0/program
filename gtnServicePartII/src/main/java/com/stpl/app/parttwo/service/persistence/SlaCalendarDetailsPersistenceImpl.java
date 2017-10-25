package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException;
import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl;
import com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsPersistence;

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
 * The persistence implementation for the sla calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsPersistence
 * @see SlaCalendarDetailsUtil
 * @generated
 */
public class SlaCalendarDetailsPersistenceImpl extends BasePersistenceImpl<SlaCalendarDetails>
    implements SlaCalendarDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SlaCalendarDetailsUtil} to access the sla calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SlaCalendarDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            SlaCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
            SlaCalendarDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_SLACALENDARDETAILS = "SELECT slaCalendarDetails FROM SlaCalendarDetails slaCalendarDetails";
    private static final String _SQL_COUNT_SLACALENDARDETAILS = "SELECT COUNT(slaCalendarDetails) FROM SlaCalendarDetails slaCalendarDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "slaCalendarDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SlaCalendarDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SlaCalendarDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "slaCalendarMasterSid",
                "holidayYear", "slaCalendarDetailsSid", "modifiedBy",
                "inboundStatus", "holidayDay", "modifiedDate", "holidayCombined",
                "holidayMonth"
            });
    private static SlaCalendarDetails _nullSlaCalendarDetails = new SlaCalendarDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SlaCalendarDetails> toCacheModel() {
                return _nullSlaCalendarDetailsCacheModel;
            }
        };

    private static CacheModel<SlaCalendarDetails> _nullSlaCalendarDetailsCacheModel =
        new CacheModel<SlaCalendarDetails>() {
            @Override
            public SlaCalendarDetails toEntityModel() {
                return _nullSlaCalendarDetails;
            }
        };

    public SlaCalendarDetailsPersistenceImpl() {
        setModelClass(SlaCalendarDetails.class);
    }

    /**
     * Caches the sla calendar details in the entity cache if it is enabled.
     *
     * @param slaCalendarDetails the sla calendar details
     */
    @Override
    public void cacheResult(SlaCalendarDetails slaCalendarDetails) {
        EntityCacheUtil.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey(),
            slaCalendarDetails);

        slaCalendarDetails.resetOriginalValues();
    }

    /**
     * Caches the sla calendar detailses in the entity cache if it is enabled.
     *
     * @param slaCalendarDetailses the sla calendar detailses
     */
    @Override
    public void cacheResult(List<SlaCalendarDetails> slaCalendarDetailses) {
        for (SlaCalendarDetails slaCalendarDetails : slaCalendarDetailses) {
            if (EntityCacheUtil.getResult(
                        SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        SlaCalendarDetailsImpl.class,
                        slaCalendarDetails.getPrimaryKey()) == null) {
                cacheResult(slaCalendarDetails);
            } else {
                slaCalendarDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all sla calendar detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SlaCalendarDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SlaCalendarDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the sla calendar details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SlaCalendarDetails slaCalendarDetails) {
        EntityCacheUtil.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SlaCalendarDetails> slaCalendarDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SlaCalendarDetails slaCalendarDetails : slaCalendarDetailses) {
            EntityCacheUtil.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
     *
     * @param slaCalendarDetailsSid the primary key for the new sla calendar details
     * @return the new sla calendar details
     */
    @Override
    public SlaCalendarDetails create(int slaCalendarDetailsSid) {
        SlaCalendarDetails slaCalendarDetails = new SlaCalendarDetailsImpl();

        slaCalendarDetails.setNew(true);
        slaCalendarDetails.setPrimaryKey(slaCalendarDetailsSid);

        return slaCalendarDetails;
    }

    /**
     * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param slaCalendarDetailsSid the primary key of the sla calendar details
     * @return the sla calendar details that was removed
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails remove(int slaCalendarDetailsSid)
        throws NoSuchSlaCalendarDetailsException, SystemException {
        return remove((Serializable) slaCalendarDetailsSid);
    }

    /**
     * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the sla calendar details
     * @return the sla calendar details that was removed
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails remove(Serializable primaryKey)
        throws NoSuchSlaCalendarDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SlaCalendarDetails slaCalendarDetails = (SlaCalendarDetails) session.get(SlaCalendarDetailsImpl.class,
                    primaryKey);

            if (slaCalendarDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSlaCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(slaCalendarDetails);
        } catch (NoSuchSlaCalendarDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SlaCalendarDetails removeImpl(
        SlaCalendarDetails slaCalendarDetails) throws SystemException {
        slaCalendarDetails = toUnwrappedModel(slaCalendarDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(slaCalendarDetails)) {
                slaCalendarDetails = (SlaCalendarDetails) session.get(SlaCalendarDetailsImpl.class,
                        slaCalendarDetails.getPrimaryKeyObj());
            }

            if (slaCalendarDetails != null) {
                session.delete(slaCalendarDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (slaCalendarDetails != null) {
            clearCache(slaCalendarDetails);
        }

        return slaCalendarDetails;
    }

    @Override
    public SlaCalendarDetails updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws SystemException {
        slaCalendarDetails = toUnwrappedModel(slaCalendarDetails);

        boolean isNew = slaCalendarDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (slaCalendarDetails.isNew()) {
                session.save(slaCalendarDetails);

                slaCalendarDetails.setNew(false);
            } else {
                session.merge(slaCalendarDetails);
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

        EntityCacheUtil.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
            SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey(),
            slaCalendarDetails);

        return slaCalendarDetails;
    }

    protected SlaCalendarDetails toUnwrappedModel(
        SlaCalendarDetails slaCalendarDetails) {
        if (slaCalendarDetails instanceof SlaCalendarDetailsImpl) {
            return slaCalendarDetails;
        }

        SlaCalendarDetailsImpl slaCalendarDetailsImpl = new SlaCalendarDetailsImpl();

        slaCalendarDetailsImpl.setNew(slaCalendarDetails.isNew());
        slaCalendarDetailsImpl.setPrimaryKey(slaCalendarDetails.getPrimaryKey());

        slaCalendarDetailsImpl.setCreatedDate(slaCalendarDetails.getCreatedDate());
        slaCalendarDetailsImpl.setCreatedBy(slaCalendarDetails.getCreatedBy());
        slaCalendarDetailsImpl.setSlaCalendarMasterSid(slaCalendarDetails.getSlaCalendarMasterSid());
        slaCalendarDetailsImpl.setHolidayYear(slaCalendarDetails.getHolidayYear());
        slaCalendarDetailsImpl.setSlaCalendarDetailsSid(slaCalendarDetails.getSlaCalendarDetailsSid());
        slaCalendarDetailsImpl.setModifiedBy(slaCalendarDetails.getModifiedBy());
        slaCalendarDetailsImpl.setInboundStatus(slaCalendarDetails.getInboundStatus());
        slaCalendarDetailsImpl.setHolidayDay(slaCalendarDetails.getHolidayDay());
        slaCalendarDetailsImpl.setModifiedDate(slaCalendarDetails.getModifiedDate());
        slaCalendarDetailsImpl.setHolidayCombined(slaCalendarDetails.getHolidayCombined());
        slaCalendarDetailsImpl.setHolidayMonth(slaCalendarDetails.getHolidayMonth());

        return slaCalendarDetailsImpl;
    }

    /**
     * Returns the sla calendar details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the sla calendar details
     * @return the sla calendar details
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSlaCalendarDetailsException, SystemException {
        SlaCalendarDetails slaCalendarDetails = fetchByPrimaryKey(primaryKey);

        if (slaCalendarDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSlaCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return slaCalendarDetails;
    }

    /**
     * Returns the sla calendar details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException} if it could not be found.
     *
     * @param slaCalendarDetailsSid the primary key of the sla calendar details
     * @return the sla calendar details
     * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails findByPrimaryKey(int slaCalendarDetailsSid)
        throws NoSuchSlaCalendarDetailsException, SystemException {
        return findByPrimaryKey((Serializable) slaCalendarDetailsSid);
    }

    /**
     * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the sla calendar details
     * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SlaCalendarDetails slaCalendarDetails = (SlaCalendarDetails) EntityCacheUtil.getResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                SlaCalendarDetailsImpl.class, primaryKey);

        if (slaCalendarDetails == _nullSlaCalendarDetails) {
            return null;
        }

        if (slaCalendarDetails == null) {
            Session session = null;

            try {
                session = openSession();

                slaCalendarDetails = (SlaCalendarDetails) session.get(SlaCalendarDetailsImpl.class,
                        primaryKey);

                if (slaCalendarDetails != null) {
                    cacheResult(slaCalendarDetails);
                } else {
                    EntityCacheUtil.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        SlaCalendarDetailsImpl.class, primaryKey,
                        _nullSlaCalendarDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    SlaCalendarDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return slaCalendarDetails;
    }

    /**
     * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param slaCalendarDetailsSid the primary key of the sla calendar details
     * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SlaCalendarDetails fetchByPrimaryKey(int slaCalendarDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) slaCalendarDetailsSid);
    }

    /**
     * Returns all the sla calendar detailses.
     *
     * @return the sla calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sla calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sla calendar detailses
     * @param end the upper bound of the range of sla calendar detailses (not inclusive)
     * @return the range of sla calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the sla calendar detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of sla calendar detailses
     * @param end the upper bound of the range of sla calendar detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of sla calendar detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SlaCalendarDetails> findAll(int start, int end,
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

        List<SlaCalendarDetails> list = (List<SlaCalendarDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SLACALENDARDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SLACALENDARDETAILS;

                if (pagination) {
                    sql = sql.concat(SlaCalendarDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SlaCalendarDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SlaCalendarDetails>(list);
                } else {
                    list = (List<SlaCalendarDetails>) QueryUtil.list(q,
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
     * Removes all the sla calendar detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SlaCalendarDetails slaCalendarDetails : findAll()) {
            remove(slaCalendarDetails);
        }
    }

    /**
     * Returns the number of sla calendar detailses.
     *
     * @return the number of sla calendar detailses
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

                Query q = session.createQuery(_SQL_COUNT_SLACALENDARDETAILS);

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
     * Initializes the sla calendar details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.SlaCalendarDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SlaCalendarDetails>> listenersList = new ArrayList<ModelListener<SlaCalendarDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SlaCalendarDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SlaCalendarDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
