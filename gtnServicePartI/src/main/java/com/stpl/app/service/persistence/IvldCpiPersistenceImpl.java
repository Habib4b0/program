package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldCpiException;
import com.stpl.app.model.IvldCpi;
import com.stpl.app.model.impl.IvldCpiImpl;
import com.stpl.app.model.impl.IvldCpiModelImpl;
import com.stpl.app.service.persistence.IvldCpiPersistence;

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
 * The persistence implementation for the ivld cpi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCpiPersistence
 * @see IvldCpiUtil
 * @generated
 */
public class IvldCpiPersistenceImpl extends BasePersistenceImpl<IvldCpi>
    implements IvldCpiPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCpiUtil} to access the ivld cpi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCpiImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiModelImpl.FINDER_CACHE_ENABLED, IvldCpiImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiModelImpl.FINDER_CACHE_ENABLED, IvldCpiImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCPI = "SELECT ivldCpi FROM IvldCpi ivldCpi";
    private static final String _SQL_COUNT_IVLDCPI = "SELECT COUNT(ivldCpi) FROM IvldCpi ivldCpi";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCpi.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCpi exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCpiPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "effectiveDate", "reasonForFailure", "indexType", "status",
                "modifiedDate", "cpiIntfid", "createdBy", "createdDate",
                "source", "indexValue", "addChgDelIndicator", "batchId",
                "ivldCpiSid", "errorField", "errorCode", "intfInsertedDate",
                "modifiedBy", "reprocessedFlag", "indexId", "checkRecord"
            });
    private static IvldCpi _nullIvldCpi = new IvldCpiImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCpi> toCacheModel() {
                return _nullIvldCpiCacheModel;
            }
        };

    private static CacheModel<IvldCpi> _nullIvldCpiCacheModel = new CacheModel<IvldCpi>() {
            @Override
            public IvldCpi toEntityModel() {
                return _nullIvldCpi;
            }
        };

    public IvldCpiPersistenceImpl() {
        setModelClass(IvldCpi.class);
    }

    /**
     * Caches the ivld cpi in the entity cache if it is enabled.
     *
     * @param ivldCpi the ivld cpi
     */
    @Override
    public void cacheResult(IvldCpi ivldCpi) {
        EntityCacheUtil.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiImpl.class, ivldCpi.getPrimaryKey(), ivldCpi);

        ivldCpi.resetOriginalValues();
    }

    /**
     * Caches the ivld cpis in the entity cache if it is enabled.
     *
     * @param ivldCpis the ivld cpis
     */
    @Override
    public void cacheResult(List<IvldCpi> ivldCpis) {
        for (IvldCpi ivldCpi : ivldCpis) {
            if (EntityCacheUtil.getResult(
                        IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCpiImpl.class, ivldCpi.getPrimaryKey()) == null) {
                cacheResult(ivldCpi);
            } else {
                ivldCpi.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld cpis.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCpiImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCpiImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld cpi.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCpi ivldCpi) {
        EntityCacheUtil.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiImpl.class, ivldCpi.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCpi> ivldCpis) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCpi ivldCpi : ivldCpis) {
            EntityCacheUtil.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
                IvldCpiImpl.class, ivldCpi.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld cpi with the primary key. Does not add the ivld cpi to the database.
     *
     * @param ivldCpiSid the primary key for the new ivld cpi
     * @return the new ivld cpi
     */
    @Override
    public IvldCpi create(int ivldCpiSid) {
        IvldCpi ivldCpi = new IvldCpiImpl();

        ivldCpi.setNew(true);
        ivldCpi.setPrimaryKey(ivldCpiSid);

        return ivldCpi;
    }

    /**
     * Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCpiSid the primary key of the ivld cpi
     * @return the ivld cpi that was removed
     * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi remove(int ivldCpiSid)
        throws NoSuchIvldCpiException, SystemException {
        return remove((Serializable) ivldCpiSid);
    }

    /**
     * Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld cpi
     * @return the ivld cpi that was removed
     * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi remove(Serializable primaryKey)
        throws NoSuchIvldCpiException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCpi ivldCpi = (IvldCpi) session.get(IvldCpiImpl.class,
                    primaryKey);

            if (ivldCpi == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCpiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCpi);
        } catch (NoSuchIvldCpiException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCpi removeImpl(IvldCpi ivldCpi) throws SystemException {
        ivldCpi = toUnwrappedModel(ivldCpi);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCpi)) {
                ivldCpi = (IvldCpi) session.get(IvldCpiImpl.class,
                        ivldCpi.getPrimaryKeyObj());
            }

            if (ivldCpi != null) {
                session.delete(ivldCpi);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCpi != null) {
            clearCache(ivldCpi);
        }

        return ivldCpi;
    }

    @Override
    public IvldCpi updateImpl(com.stpl.app.model.IvldCpi ivldCpi)
        throws SystemException {
        ivldCpi = toUnwrappedModel(ivldCpi);

        boolean isNew = ivldCpi.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCpi.isNew()) {
                session.save(ivldCpi);

                ivldCpi.setNew(false);
            } else {
                session.merge(ivldCpi);
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

        EntityCacheUtil.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
            IvldCpiImpl.class, ivldCpi.getPrimaryKey(), ivldCpi);

        return ivldCpi;
    }

    protected IvldCpi toUnwrappedModel(IvldCpi ivldCpi) {
        if (ivldCpi instanceof IvldCpiImpl) {
            return ivldCpi;
        }

        IvldCpiImpl ivldCpiImpl = new IvldCpiImpl();

        ivldCpiImpl.setNew(ivldCpi.isNew());
        ivldCpiImpl.setPrimaryKey(ivldCpi.getPrimaryKey());

        ivldCpiImpl.setEffectiveDate(ivldCpi.getEffectiveDate());
        ivldCpiImpl.setReasonForFailure(ivldCpi.getReasonForFailure());
        ivldCpiImpl.setIndexType(ivldCpi.getIndexType());
        ivldCpiImpl.setStatus(ivldCpi.getStatus());
        ivldCpiImpl.setModifiedDate(ivldCpi.getModifiedDate());
        ivldCpiImpl.setCpiIntfid(ivldCpi.getCpiIntfid());
        ivldCpiImpl.setCreatedBy(ivldCpi.getCreatedBy());
        ivldCpiImpl.setCreatedDate(ivldCpi.getCreatedDate());
        ivldCpiImpl.setSource(ivldCpi.getSource());
        ivldCpiImpl.setIndexValue(ivldCpi.getIndexValue());
        ivldCpiImpl.setAddChgDelIndicator(ivldCpi.getAddChgDelIndicator());
        ivldCpiImpl.setBatchId(ivldCpi.getBatchId());
        ivldCpiImpl.setIvldCpiSid(ivldCpi.getIvldCpiSid());
        ivldCpiImpl.setErrorField(ivldCpi.getErrorField());
        ivldCpiImpl.setErrorCode(ivldCpi.getErrorCode());
        ivldCpiImpl.setIntfInsertedDate(ivldCpi.getIntfInsertedDate());
        ivldCpiImpl.setModifiedBy(ivldCpi.getModifiedBy());
        ivldCpiImpl.setReprocessedFlag(ivldCpi.getReprocessedFlag());
        ivldCpiImpl.setIndexId(ivldCpi.getIndexId());
        ivldCpiImpl.setCheckRecord(ivldCpi.isCheckRecord());

        return ivldCpiImpl;
    }

    /**
     * Returns the ivld cpi with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld cpi
     * @return the ivld cpi
     * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCpiException, SystemException {
        IvldCpi ivldCpi = fetchByPrimaryKey(primaryKey);

        if (ivldCpi == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCpiException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCpi;
    }

    /**
     * Returns the ivld cpi with the primary key or throws a {@link com.stpl.app.NoSuchIvldCpiException} if it could not be found.
     *
     * @param ivldCpiSid the primary key of the ivld cpi
     * @return the ivld cpi
     * @throws com.stpl.app.NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi findByPrimaryKey(int ivldCpiSid)
        throws NoSuchIvldCpiException, SystemException {
        return findByPrimaryKey((Serializable) ivldCpiSid);
    }

    /**
     * Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld cpi
     * @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCpi ivldCpi = (IvldCpi) EntityCacheUtil.getResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
                IvldCpiImpl.class, primaryKey);

        if (ivldCpi == _nullIvldCpi) {
            return null;
        }

        if (ivldCpi == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCpi = (IvldCpi) session.get(IvldCpiImpl.class, primaryKey);

                if (ivldCpi != null) {
                    cacheResult(ivldCpi);
                } else {
                    EntityCacheUtil.putResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCpiImpl.class, primaryKey, _nullIvldCpi);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCpiModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCpiImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCpi;
    }

    /**
     * Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCpiSid the primary key of the ivld cpi
     * @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCpi fetchByPrimaryKey(int ivldCpiSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCpiSid);
    }

    /**
     * Returns all the ivld cpis.
     *
     * @return the ivld cpis
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCpi> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld cpis.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld cpis
     * @param end the upper bound of the range of ivld cpis (not inclusive)
     * @return the range of ivld cpis
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCpi> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld cpis.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld cpis
     * @param end the upper bound of the range of ivld cpis (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld cpis
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCpi> findAll(int start, int end,
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

        List<IvldCpi> list = (List<IvldCpi>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCPI);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCPI;

                if (pagination) {
                    sql = sql.concat(IvldCpiModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCpi>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCpi>(list);
                } else {
                    list = (List<IvldCpi>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ivld cpis from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCpi ivldCpi : findAll()) {
            remove(ivldCpi);
        }
    }

    /**
     * Returns the number of ivld cpis.
     *
     * @return the number of ivld cpis
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCPI);

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
     * Initializes the ivld cpi persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldCpi")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCpi>> listenersList = new ArrayList<ModelListener<IvldCpi>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCpi>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCpiImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
