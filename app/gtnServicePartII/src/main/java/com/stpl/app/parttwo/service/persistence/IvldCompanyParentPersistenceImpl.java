package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCompanyParentException;
import com.stpl.app.parttwo.model.IvldCompanyParent;
import com.stpl.app.parttwo.model.impl.IvldCompanyParentImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyParentPersistence;

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
 * The persistence implementation for the ivld company parent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyParentPersistence
 * @see IvldCompanyParentUtil
 * @generated
 */
public class IvldCompanyParentPersistenceImpl extends BasePersistenceImpl<IvldCompanyParent>
    implements IvldCompanyParentPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCompanyParentUtil} to access the ivld company parent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyParentImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyParentImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyParentImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCOMPANYPARENT = "SELECT ivldCompanyParent FROM IvldCompanyParent ivldCompanyParent";
    private static final String _SQL_COUNT_IVLDCOMPANYPARENT = "SELECT COUNT(ivldCompanyParent) FROM IvldCompanyParent ivldCompanyParent";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyParent.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyParent exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCompanyParentPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "parentCompanyId", "priorParentCompanyId", "reasonForFailure",
                "companyId", "lastUpdatedDate", "parentEndDate", "modifiedDate",
                "parentDetailsIntfid", "priorParentStartDate", "source",
                "createdBy", "createdDate", "addChgDelIndicator", "batchId",
                "ivldCompanyParentSid", "errorField", "errorCode",
                "intfInsertedDate", "modifiedBy", "reprocessedFlag",
                "parentStartDate", "checkRecord"
            });
    private static IvldCompanyParent _nullIvldCompanyParent = new IvldCompanyParentImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCompanyParent> toCacheModel() {
                return _nullIvldCompanyParentCacheModel;
            }
        };

    private static CacheModel<IvldCompanyParent> _nullIvldCompanyParentCacheModel =
        new CacheModel<IvldCompanyParent>() {
            @Override
            public IvldCompanyParent toEntityModel() {
                return _nullIvldCompanyParent;
            }
        };

    public IvldCompanyParentPersistenceImpl() {
        setModelClass(IvldCompanyParent.class);
    }

    /**
     * Caches the ivld company parent in the entity cache if it is enabled.
     *
     * @param ivldCompanyParent the ivld company parent
     */
    @Override
    public void cacheResult(IvldCompanyParent ivldCompanyParent) {
        EntityCacheUtil.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey(),
            ivldCompanyParent);

        ivldCompanyParent.resetOriginalValues();
    }

    /**
     * Caches the ivld company parents in the entity cache if it is enabled.
     *
     * @param ivldCompanyParents the ivld company parents
     */
    @Override
    public void cacheResult(List<IvldCompanyParent> ivldCompanyParents) {
        for (IvldCompanyParent ivldCompanyParent : ivldCompanyParents) {
            if (EntityCacheUtil.getResult(
                        IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyParentImpl.class,
                        ivldCompanyParent.getPrimaryKey()) == null) {
                cacheResult(ivldCompanyParent);
            } else {
                ivldCompanyParent.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld company parents.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCompanyParentImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCompanyParentImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld company parent.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCompanyParent ivldCompanyParent) {
        EntityCacheUtil.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCompanyParent> ivldCompanyParents) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCompanyParent ivldCompanyParent : ivldCompanyParents) {
            EntityCacheUtil.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
     *
     * @param ivldCompanyParentSid the primary key for the new ivld company parent
     * @return the new ivld company parent
     */
    @Override
    public IvldCompanyParent create(int ivldCompanyParentSid) {
        IvldCompanyParent ivldCompanyParent = new IvldCompanyParentImpl();

        ivldCompanyParent.setNew(true);
        ivldCompanyParent.setPrimaryKey(ivldCompanyParentSid);

        return ivldCompanyParent;
    }

    /**
     * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCompanyParentSid the primary key of the ivld company parent
     * @return the ivld company parent that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent remove(int ivldCompanyParentSid)
        throws NoSuchIvldCompanyParentException, SystemException {
        return remove((Serializable) ivldCompanyParentSid);
    }

    /**
     * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld company parent
     * @return the ivld company parent that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent remove(Serializable primaryKey)
        throws NoSuchIvldCompanyParentException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCompanyParent ivldCompanyParent = (IvldCompanyParent) session.get(IvldCompanyParentImpl.class,
                    primaryKey);

            if (ivldCompanyParent == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCompanyParentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCompanyParent);
        } catch (NoSuchIvldCompanyParentException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCompanyParent removeImpl(IvldCompanyParent ivldCompanyParent)
        throws SystemException {
        ivldCompanyParent = toUnwrappedModel(ivldCompanyParent);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCompanyParent)) {
                ivldCompanyParent = (IvldCompanyParent) session.get(IvldCompanyParentImpl.class,
                        ivldCompanyParent.getPrimaryKeyObj());
            }

            if (ivldCompanyParent != null) {
                session.delete(ivldCompanyParent);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCompanyParent != null) {
            clearCache(ivldCompanyParent);
        }

        return ivldCompanyParent;
    }

    @Override
    public IvldCompanyParent updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyParent ivldCompanyParent)
        throws SystemException {
        ivldCompanyParent = toUnwrappedModel(ivldCompanyParent);

        boolean isNew = ivldCompanyParent.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCompanyParent.isNew()) {
                session.save(ivldCompanyParent);

                ivldCompanyParent.setNew(false);
            } else {
                session.merge(ivldCompanyParent);
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

        EntityCacheUtil.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey(),
            ivldCompanyParent);

        return ivldCompanyParent;
    }

    protected IvldCompanyParent toUnwrappedModel(
        IvldCompanyParent ivldCompanyParent) {
        if (ivldCompanyParent instanceof IvldCompanyParentImpl) {
            return ivldCompanyParent;
        }

        IvldCompanyParentImpl ivldCompanyParentImpl = new IvldCompanyParentImpl();

        ivldCompanyParentImpl.setNew(ivldCompanyParent.isNew());
        ivldCompanyParentImpl.setPrimaryKey(ivldCompanyParent.getPrimaryKey());

        ivldCompanyParentImpl.setParentCompanyId(ivldCompanyParent.getParentCompanyId());
        ivldCompanyParentImpl.setPriorParentCompanyId(ivldCompanyParent.getPriorParentCompanyId());
        ivldCompanyParentImpl.setReasonForFailure(ivldCompanyParent.getReasonForFailure());
        ivldCompanyParentImpl.setCompanyId(ivldCompanyParent.getCompanyId());
        ivldCompanyParentImpl.setLastUpdatedDate(ivldCompanyParent.getLastUpdatedDate());
        ivldCompanyParentImpl.setParentEndDate(ivldCompanyParent.getParentEndDate());
        ivldCompanyParentImpl.setModifiedDate(ivldCompanyParent.getModifiedDate());
        ivldCompanyParentImpl.setParentDetailsIntfid(ivldCompanyParent.getParentDetailsIntfid());
        ivldCompanyParentImpl.setPriorParentStartDate(ivldCompanyParent.getPriorParentStartDate());
        ivldCompanyParentImpl.setSource(ivldCompanyParent.getSource());
        ivldCompanyParentImpl.setCreatedBy(ivldCompanyParent.getCreatedBy());
        ivldCompanyParentImpl.setCreatedDate(ivldCompanyParent.getCreatedDate());
        ivldCompanyParentImpl.setAddChgDelIndicator(ivldCompanyParent.getAddChgDelIndicator());
        ivldCompanyParentImpl.setBatchId(ivldCompanyParent.getBatchId());
        ivldCompanyParentImpl.setIvldCompanyParentSid(ivldCompanyParent.getIvldCompanyParentSid());
        ivldCompanyParentImpl.setErrorField(ivldCompanyParent.getErrorField());
        ivldCompanyParentImpl.setErrorCode(ivldCompanyParent.getErrorCode());
        ivldCompanyParentImpl.setIntfInsertedDate(ivldCompanyParent.getIntfInsertedDate());
        ivldCompanyParentImpl.setModifiedBy(ivldCompanyParent.getModifiedBy());
        ivldCompanyParentImpl.setReprocessedFlag(ivldCompanyParent.getReprocessedFlag());
        ivldCompanyParentImpl.setParentStartDate(ivldCompanyParent.getParentStartDate());
        ivldCompanyParentImpl.setCheckRecord(ivldCompanyParent.isCheckRecord());

        return ivldCompanyParentImpl;
    }

    /**
     * Returns the ivld company parent with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company parent
     * @return the ivld company parent
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCompanyParentException, SystemException {
        IvldCompanyParent ivldCompanyParent = fetchByPrimaryKey(primaryKey);

        if (ivldCompanyParent == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCompanyParentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCompanyParent;
    }

    /**
     * Returns the ivld company parent with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyParentException} if it could not be found.
     *
     * @param ivldCompanyParentSid the primary key of the ivld company parent
     * @return the ivld company parent
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent findByPrimaryKey(int ivldCompanyParentSid)
        throws NoSuchIvldCompanyParentException, SystemException {
        return findByPrimaryKey((Serializable) ivldCompanyParentSid);
    }

    /**
     * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company parent
     * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCompanyParent ivldCompanyParent = (IvldCompanyParent) EntityCacheUtil.getResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyParentImpl.class, primaryKey);

        if (ivldCompanyParent == _nullIvldCompanyParent) {
            return null;
        }

        if (ivldCompanyParent == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCompanyParent = (IvldCompanyParent) session.get(IvldCompanyParentImpl.class,
                        primaryKey);

                if (ivldCompanyParent != null) {
                    cacheResult(ivldCompanyParent);
                } else {
                    EntityCacheUtil.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyParentImpl.class, primaryKey,
                        _nullIvldCompanyParent);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCompanyParentImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCompanyParent;
    }

    /**
     * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCompanyParentSid the primary key of the ivld company parent
     * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyParent fetchByPrimaryKey(int ivldCompanyParentSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCompanyParentSid);
    }

    /**
     * Returns all the ivld company parents.
     *
     * @return the ivld company parents
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyParent> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld company parents.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company parents
     * @param end the upper bound of the range of ivld company parents (not inclusive)
     * @return the range of ivld company parents
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyParent> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld company parents.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company parents
     * @param end the upper bound of the range of ivld company parents (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld company parents
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyParent> findAll(int start, int end,
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

        List<IvldCompanyParent> list = (List<IvldCompanyParent>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCOMPANYPARENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCOMPANYPARENT;

                if (pagination) {
                    sql = sql.concat(IvldCompanyParentModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCompanyParent>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCompanyParent>(list);
                } else {
                    list = (List<IvldCompanyParent>) QueryUtil.list(q,
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
     * Removes all the ivld company parents from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCompanyParent ivldCompanyParent : findAll()) {
            remove(ivldCompanyParent);
        }
    }

    /**
     * Returns the number of ivld company parents.
     *
     * @return the number of ivld company parents
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYPARENT);

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
     * Initializes the ivld company parent persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCompanyParent")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCompanyParent>> listenersList = new ArrayList<ModelListener<IvldCompanyParent>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCompanyParent>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCompanyParentImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
