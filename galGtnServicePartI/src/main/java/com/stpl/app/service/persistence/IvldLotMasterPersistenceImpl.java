package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldLotMasterException;
import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.model.impl.IvldLotMasterImpl;
import com.stpl.app.model.impl.IvldLotMasterModelImpl;
import com.stpl.app.service.persistence.IvldLotMasterPersistence;

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
 * The persistence implementation for the ivld lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldLotMasterPersistence
 * @see IvldLotMasterUtil
 * @generated
 */
public class IvldLotMasterPersistenceImpl extends BasePersistenceImpl<IvldLotMaster>
    implements IvldLotMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldLotMasterUtil} to access the ivld lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldLotMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldLotMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterModelImpl.FINDER_CACHE_ENABLED,
            IvldLotMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDLOTMASTER = "SELECT ivldLotMaster FROM IvldLotMaster ivldLotMaster";
    private static final String _SQL_COUNT_IVLDLOTMASTER = "SELECT COUNT(ivldLotMaster) FROM IvldLotMaster ivldLotMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldLotMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldLotMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldLotMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "reasonForFailure", "itemId", "ivldLotMasterSid", "modifiedDate",
                "createdBy", "createdDate", "source", "lastLotSoldDate",
                "lotExpirationDate", "batchId", "addChgDelIndicator",
                "errorField", "errorCode", "intfInsertedDate", "modifiedBy",
                "lotNo", "reprocessedFlag", "lotMasterIntfid"
            });
    private static IvldLotMaster _nullIvldLotMaster = new IvldLotMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldLotMaster> toCacheModel() {
                return _nullIvldLotMasterCacheModel;
            }
        };

    private static CacheModel<IvldLotMaster> _nullIvldLotMasterCacheModel = new CacheModel<IvldLotMaster>() {
            @Override
            public IvldLotMaster toEntityModel() {
                return _nullIvldLotMaster;
            }
        };

    public IvldLotMasterPersistenceImpl() {
        setModelClass(IvldLotMaster.class);
    }

    /**
     * Caches the ivld lot master in the entity cache if it is enabled.
     *
     * @param ivldLotMaster the ivld lot master
     */
    @Override
    public void cacheResult(IvldLotMaster ivldLotMaster) {
        EntityCacheUtil.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey(),
            ivldLotMaster);

        ivldLotMaster.resetOriginalValues();
    }

    /**
     * Caches the ivld lot masters in the entity cache if it is enabled.
     *
     * @param ivldLotMasters the ivld lot masters
     */
    @Override
    public void cacheResult(List<IvldLotMaster> ivldLotMasters) {
        for (IvldLotMaster ivldLotMaster : ivldLotMasters) {
            if (EntityCacheUtil.getResult(
                        IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey()) == null) {
                cacheResult(ivldLotMaster);
            } else {
                ivldLotMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld lot masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldLotMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldLotMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld lot master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldLotMaster ivldLotMaster) {
        EntityCacheUtil.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldLotMaster> ivldLotMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldLotMaster ivldLotMaster : ivldLotMasters) {
            EntityCacheUtil.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
     *
     * @param ivldLotMasterSid the primary key for the new ivld lot master
     * @return the new ivld lot master
     */
    @Override
    public IvldLotMaster create(int ivldLotMasterSid) {
        IvldLotMaster ivldLotMaster = new IvldLotMasterImpl();

        ivldLotMaster.setNew(true);
        ivldLotMaster.setPrimaryKey(ivldLotMasterSid);

        return ivldLotMaster;
    }

    /**
     * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldLotMasterSid the primary key of the ivld lot master
     * @return the ivld lot master that was removed
     * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster remove(int ivldLotMasterSid)
        throws NoSuchIvldLotMasterException, SystemException {
        return remove((Serializable) ivldLotMasterSid);
    }

    /**
     * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld lot master
     * @return the ivld lot master that was removed
     * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster remove(Serializable primaryKey)
        throws NoSuchIvldLotMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldLotMaster ivldLotMaster = (IvldLotMaster) session.get(IvldLotMasterImpl.class,
                    primaryKey);

            if (ivldLotMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldLotMaster);
        } catch (NoSuchIvldLotMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldLotMaster removeImpl(IvldLotMaster ivldLotMaster)
        throws SystemException {
        ivldLotMaster = toUnwrappedModel(ivldLotMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldLotMaster)) {
                ivldLotMaster = (IvldLotMaster) session.get(IvldLotMasterImpl.class,
                        ivldLotMaster.getPrimaryKeyObj());
            }

            if (ivldLotMaster != null) {
                session.delete(ivldLotMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldLotMaster != null) {
            clearCache(ivldLotMaster);
        }

        return ivldLotMaster;
    }

    @Override
    public IvldLotMaster updateImpl(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws SystemException {
        ivldLotMaster = toUnwrappedModel(ivldLotMaster);

        boolean isNew = ivldLotMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldLotMaster.isNew()) {
                session.save(ivldLotMaster);

                ivldLotMaster.setNew(false);
            } else {
                session.merge(ivldLotMaster);
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

        EntityCacheUtil.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
            IvldLotMasterImpl.class, ivldLotMaster.getPrimaryKey(),
            ivldLotMaster);

        return ivldLotMaster;
    }

    protected IvldLotMaster toUnwrappedModel(IvldLotMaster ivldLotMaster) {
        if (ivldLotMaster instanceof IvldLotMasterImpl) {
            return ivldLotMaster;
        }

        IvldLotMasterImpl ivldLotMasterImpl = new IvldLotMasterImpl();

        ivldLotMasterImpl.setNew(ivldLotMaster.isNew());
        ivldLotMasterImpl.setPrimaryKey(ivldLotMaster.getPrimaryKey());

        ivldLotMasterImpl.setReasonForFailure(ivldLotMaster.getReasonForFailure());
        ivldLotMasterImpl.setItemId(ivldLotMaster.getItemId());
        ivldLotMasterImpl.setIvldLotMasterSid(ivldLotMaster.getIvldLotMasterSid());
        ivldLotMasterImpl.setModifiedDate(ivldLotMaster.getModifiedDate());
        ivldLotMasterImpl.setCreatedBy(ivldLotMaster.getCreatedBy());
        ivldLotMasterImpl.setCreatedDate(ivldLotMaster.getCreatedDate());
        ivldLotMasterImpl.setSource(ivldLotMaster.getSource());
        ivldLotMasterImpl.setLastLotSoldDate(ivldLotMaster.getLastLotSoldDate());
        ivldLotMasterImpl.setLotExpirationDate(ivldLotMaster.getLotExpirationDate());
        ivldLotMasterImpl.setBatchId(ivldLotMaster.getBatchId());
        ivldLotMasterImpl.setAddChgDelIndicator(ivldLotMaster.getAddChgDelIndicator());
        ivldLotMasterImpl.setErrorField(ivldLotMaster.getErrorField());
        ivldLotMasterImpl.setErrorCode(ivldLotMaster.getErrorCode());
        ivldLotMasterImpl.setIntfInsertedDate(ivldLotMaster.getIntfInsertedDate());
        ivldLotMasterImpl.setModifiedBy(ivldLotMaster.getModifiedBy());
        ivldLotMasterImpl.setLotNo(ivldLotMaster.getLotNo());
        ivldLotMasterImpl.setReprocessedFlag(ivldLotMaster.getReprocessedFlag());
        ivldLotMasterImpl.setLotMasterIntfid(ivldLotMaster.getLotMasterIntfid());

        return ivldLotMasterImpl;
    }

    /**
     * Returns the ivld lot master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld lot master
     * @return the ivld lot master
     * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldLotMasterException, SystemException {
        IvldLotMaster ivldLotMaster = fetchByPrimaryKey(primaryKey);

        if (ivldLotMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldLotMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldLotMaster;
    }

    /**
     * Returns the ivld lot master with the primary key or throws a {@link com.stpl.app.NoSuchIvldLotMasterException} if it could not be found.
     *
     * @param ivldLotMasterSid the primary key of the ivld lot master
     * @return the ivld lot master
     * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster findByPrimaryKey(int ivldLotMasterSid)
        throws NoSuchIvldLotMasterException, SystemException {
        return findByPrimaryKey((Serializable) ivldLotMasterSid);
    }

    /**
     * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld lot master
     * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldLotMaster ivldLotMaster = (IvldLotMaster) EntityCacheUtil.getResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
                IvldLotMasterImpl.class, primaryKey);

        if (ivldLotMaster == _nullIvldLotMaster) {
            return null;
        }

        if (ivldLotMaster == null) {
            Session session = null;

            try {
                session = openSession();

                ivldLotMaster = (IvldLotMaster) session.get(IvldLotMasterImpl.class,
                        primaryKey);

                if (ivldLotMaster != null) {
                    cacheResult(ivldLotMaster);
                } else {
                    EntityCacheUtil.putResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
                        IvldLotMasterImpl.class, primaryKey, _nullIvldLotMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldLotMasterModelImpl.ENTITY_CACHE_ENABLED,
                    IvldLotMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldLotMaster;
    }

    /**
     * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldLotMasterSid the primary key of the ivld lot master
     * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldLotMaster fetchByPrimaryKey(int ivldLotMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldLotMasterSid);
    }

    /**
     * Returns all the ivld lot masters.
     *
     * @return the ivld lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldLotMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld lot masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld lot masters
     * @param end the upper bound of the range of ivld lot masters (not inclusive)
     * @return the range of ivld lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldLotMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld lot masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld lot masters
     * @param end the upper bound of the range of ivld lot masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld lot masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldLotMaster> findAll(int start, int end,
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

        List<IvldLotMaster> list = (List<IvldLotMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDLOTMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDLOTMASTER;

                if (pagination) {
                    sql = sql.concat(IvldLotMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldLotMaster>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldLotMaster>(list);
                } else {
                    list = (List<IvldLotMaster>) QueryUtil.list(q,
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
     * Removes all the ivld lot masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldLotMaster ivldLotMaster : findAll()) {
            remove(ivldLotMaster);
        }
    }

    /**
     * Returns the number of ivld lot masters.
     *
     * @return the number of ivld lot masters
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

                Query q = session.createQuery(_SQL_COUNT_IVLDLOTMASTER);

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
     * Initializes the ivld lot master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldLotMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldLotMaster>> listenersList = new ArrayList<ModelListener<IvldLotMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldLotMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldLotMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
