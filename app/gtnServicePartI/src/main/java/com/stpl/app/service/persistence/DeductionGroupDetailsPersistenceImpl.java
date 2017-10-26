package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchDeductionGroupDetailsException;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.model.impl.DeductionGroupDetailsImpl;
import com.stpl.app.model.impl.DeductionGroupDetailsModelImpl;
import com.stpl.app.service.persistence.DeductionGroupDetailsPersistence;

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
 * The persistence implementation for the deduction group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionGroupDetailsPersistence
 * @see DeductionGroupDetailsUtil
 * @generated
 */
public class DeductionGroupDetailsPersistenceImpl extends BasePersistenceImpl<DeductionGroupDetails>
    implements DeductionGroupDetailsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DeductionGroupDetailsUtil} to access the deduction group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DeductionGroupDetailsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
            DeductionGroupDetailsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DEDUCTIONGROUPDETAILS = "SELECT deductionGroupDetails FROM DeductionGroupDetails deductionGroupDetails";
    private static final String _SQL_COUNT_DEDUCTIONGROUPDETAILS = "SELECT COUNT(deductionGroupDetails) FROM DeductionGroupDetails deductionGroupDetails";
    private static final String _ORDER_BY_ENTITY_ALIAS = "deductionGroupDetails.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionGroupDetails exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DeductionGroupDetailsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "deductionGroupDetailsSid", "createdDate", "createdBy",
                "deductionGroupSid", "versionNo", "modifiedBy", "rsModelSid",
                "modifiedDate"
            });
    private static DeductionGroupDetails _nullDeductionGroupDetails = new DeductionGroupDetailsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DeductionGroupDetails> toCacheModel() {
                return _nullDeductionGroupDetailsCacheModel;
            }
        };

    private static CacheModel<DeductionGroupDetails> _nullDeductionGroupDetailsCacheModel =
        new CacheModel<DeductionGroupDetails>() {
            @Override
            public DeductionGroupDetails toEntityModel() {
                return _nullDeductionGroupDetails;
            }
        };

    public DeductionGroupDetailsPersistenceImpl() {
        setModelClass(DeductionGroupDetails.class);
    }

    /**
     * Caches the deduction group details in the entity cache if it is enabled.
     *
     * @param deductionGroupDetails the deduction group details
     */
    @Override
    public void cacheResult(DeductionGroupDetails deductionGroupDetails) {
        EntityCacheUtil.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsImpl.class,
            deductionGroupDetails.getPrimaryKey(), deductionGroupDetails);

        deductionGroupDetails.resetOriginalValues();
    }

    /**
     * Caches the deduction group detailses in the entity cache if it is enabled.
     *
     * @param deductionGroupDetailses the deduction group detailses
     */
    @Override
    public void cacheResult(List<DeductionGroupDetails> deductionGroupDetailses) {
        for (DeductionGroupDetails deductionGroupDetails : deductionGroupDetailses) {
            if (EntityCacheUtil.getResult(
                        DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionGroupDetailsImpl.class,
                        deductionGroupDetails.getPrimaryKey()) == null) {
                cacheResult(deductionGroupDetails);
            } else {
                deductionGroupDetails.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all deduction group detailses.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DeductionGroupDetailsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DeductionGroupDetailsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the deduction group details.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DeductionGroupDetails deductionGroupDetails) {
        EntityCacheUtil.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsImpl.class,
            deductionGroupDetails.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<DeductionGroupDetails> deductionGroupDetailses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DeductionGroupDetails deductionGroupDetails : deductionGroupDetailses) {
            EntityCacheUtil.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionGroupDetailsImpl.class,
                deductionGroupDetails.getPrimaryKey());
        }
    }

    /**
     * Creates a new deduction group details with the primary key. Does not add the deduction group details to the database.
     *
     * @param deductionGroupDetailsSid the primary key for the new deduction group details
     * @return the new deduction group details
     */
    @Override
    public DeductionGroupDetails create(int deductionGroupDetailsSid) {
        DeductionGroupDetails deductionGroupDetails = new DeductionGroupDetailsImpl();

        deductionGroupDetails.setNew(true);
        deductionGroupDetails.setPrimaryKey(deductionGroupDetailsSid);

        return deductionGroupDetails;
    }

    /**
     * Removes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param deductionGroupDetailsSid the primary key of the deduction group details
     * @return the deduction group details that was removed
     * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails remove(int deductionGroupDetailsSid)
        throws NoSuchDeductionGroupDetailsException, SystemException {
        return remove((Serializable) deductionGroupDetailsSid);
    }

    /**
     * Removes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the deduction group details
     * @return the deduction group details that was removed
     * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails remove(Serializable primaryKey)
        throws NoSuchDeductionGroupDetailsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DeductionGroupDetails deductionGroupDetails = (DeductionGroupDetails) session.get(DeductionGroupDetailsImpl.class,
                    primaryKey);

            if (deductionGroupDetails == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDeductionGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(deductionGroupDetails);
        } catch (NoSuchDeductionGroupDetailsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DeductionGroupDetails removeImpl(
        DeductionGroupDetails deductionGroupDetails) throws SystemException {
        deductionGroupDetails = toUnwrappedModel(deductionGroupDetails);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(deductionGroupDetails)) {
                deductionGroupDetails = (DeductionGroupDetails) session.get(DeductionGroupDetailsImpl.class,
                        deductionGroupDetails.getPrimaryKeyObj());
            }

            if (deductionGroupDetails != null) {
                session.delete(deductionGroupDetails);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (deductionGroupDetails != null) {
            clearCache(deductionGroupDetails);
        }

        return deductionGroupDetails;
    }

    @Override
    public DeductionGroupDetails updateImpl(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails)
        throws SystemException {
        deductionGroupDetails = toUnwrappedModel(deductionGroupDetails);

        boolean isNew = deductionGroupDetails.isNew();

        Session session = null;

        try {
            session = openSession();

            if (deductionGroupDetails.isNew()) {
                session.save(deductionGroupDetails);

                deductionGroupDetails.setNew(false);
            } else {
                session.merge(deductionGroupDetails);
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

        EntityCacheUtil.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
            DeductionGroupDetailsImpl.class,
            deductionGroupDetails.getPrimaryKey(), deductionGroupDetails);

        return deductionGroupDetails;
    }

    protected DeductionGroupDetails toUnwrappedModel(
        DeductionGroupDetails deductionGroupDetails) {
        if (deductionGroupDetails instanceof DeductionGroupDetailsImpl) {
            return deductionGroupDetails;
        }

        DeductionGroupDetailsImpl deductionGroupDetailsImpl = new DeductionGroupDetailsImpl();

        deductionGroupDetailsImpl.setNew(deductionGroupDetails.isNew());
        deductionGroupDetailsImpl.setPrimaryKey(deductionGroupDetails.getPrimaryKey());

        deductionGroupDetailsImpl.setDeductionGroupDetailsSid(deductionGroupDetails.getDeductionGroupDetailsSid());
        deductionGroupDetailsImpl.setCreatedDate(deductionGroupDetails.getCreatedDate());
        deductionGroupDetailsImpl.setCreatedBy(deductionGroupDetails.getCreatedBy());
        deductionGroupDetailsImpl.setDeductionGroupSid(deductionGroupDetails.getDeductionGroupSid());
        deductionGroupDetailsImpl.setVersionNo(deductionGroupDetails.getVersionNo());
        deductionGroupDetailsImpl.setModifiedBy(deductionGroupDetails.getModifiedBy());
        deductionGroupDetailsImpl.setRsModelSid(deductionGroupDetails.getRsModelSid());
        deductionGroupDetailsImpl.setModifiedDate(deductionGroupDetails.getModifiedDate());

        return deductionGroupDetailsImpl;
    }

    /**
     * Returns the deduction group details with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the deduction group details
     * @return the deduction group details
     * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDeductionGroupDetailsException, SystemException {
        DeductionGroupDetails deductionGroupDetails = fetchByPrimaryKey(primaryKey);

        if (deductionGroupDetails == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDeductionGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return deductionGroupDetails;
    }

    /**
     * Returns the deduction group details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionGroupDetailsException} if it could not be found.
     *
     * @param deductionGroupDetailsSid the primary key of the deduction group details
     * @return the deduction group details
     * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails findByPrimaryKey(int deductionGroupDetailsSid)
        throws NoSuchDeductionGroupDetailsException, SystemException {
        return findByPrimaryKey((Serializable) deductionGroupDetailsSid);
    }

    /**
     * Returns the deduction group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the deduction group details
     * @return the deduction group details, or <code>null</code> if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DeductionGroupDetails deductionGroupDetails = (DeductionGroupDetails) EntityCacheUtil.getResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                DeductionGroupDetailsImpl.class, primaryKey);

        if (deductionGroupDetails == _nullDeductionGroupDetails) {
            return null;
        }

        if (deductionGroupDetails == null) {
            Session session = null;

            try {
                session = openSession();

                deductionGroupDetails = (DeductionGroupDetails) session.get(DeductionGroupDetailsImpl.class,
                        primaryKey);

                if (deductionGroupDetails != null) {
                    cacheResult(deductionGroupDetails);
                } else {
                    EntityCacheUtil.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                        DeductionGroupDetailsImpl.class, primaryKey,
                        _nullDeductionGroupDetails);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
                    DeductionGroupDetailsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return deductionGroupDetails;
    }

    /**
     * Returns the deduction group details with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param deductionGroupDetailsSid the primary key of the deduction group details
     * @return the deduction group details, or <code>null</code> if a deduction group details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DeductionGroupDetails fetchByPrimaryKey(int deductionGroupDetailsSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) deductionGroupDetailsSid);
    }

    /**
     * Returns all the deduction group detailses.
     *
     * @return the deduction group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroupDetails> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the deduction group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction group detailses
     * @param end the upper bound of the range of deduction group detailses (not inclusive)
     * @return the range of deduction group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroupDetails> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the deduction group detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of deduction group detailses
     * @param end the upper bound of the range of deduction group detailses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of deduction group detailses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DeductionGroupDetails> findAll(int start, int end,
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

        List<DeductionGroupDetails> list = (List<DeductionGroupDetails>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DEDUCTIONGROUPDETAILS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DEDUCTIONGROUPDETAILS;

                if (pagination) {
                    sql = sql.concat(DeductionGroupDetailsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DeductionGroupDetails>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DeductionGroupDetails>(list);
                } else {
                    list = (List<DeductionGroupDetails>) QueryUtil.list(q,
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
     * Removes all the deduction group detailses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DeductionGroupDetails deductionGroupDetails : findAll()) {
            remove(deductionGroupDetails);
        }
    }

    /**
     * Returns the number of deduction group detailses.
     *
     * @return the number of deduction group detailses
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

                Query q = session.createQuery(_SQL_COUNT_DEDUCTIONGROUPDETAILS);

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
     * Initializes the deduction group details persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.DeductionGroupDetails")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DeductionGroupDetails>> listenersList = new ArrayList<ModelListener<DeductionGroupDetails>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DeductionGroupDetails>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DeductionGroupDetailsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
