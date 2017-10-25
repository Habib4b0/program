package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException;
import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyTradeClassPersistence;

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
 * The persistence implementation for the ivld company trade class service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassPersistence
 * @see IvldCompanyTradeClassUtil
 * @generated
 */
public class IvldCompanyTradeClassPersistenceImpl extends BasePersistenceImpl<IvldCompanyTradeClass>
    implements IvldCompanyTradeClassPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCompanyTradeClassUtil} to access the ivld company trade class persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyTradeClassImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyTradeClassImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCOMPANYTRADECLASS = "SELECT ivldCompanyTradeClass FROM IvldCompanyTradeClass ivldCompanyTradeClass";
    private static final String _SQL_COUNT_IVLDCOMPANYTRADECLASS = "SELECT COUNT(ivldCompanyTradeClass) FROM IvldCompanyTradeClass ivldCompanyTradeClass";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyTradeClass.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyTradeClass exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCompanyTradeClassPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "ivldCompanyTradeClassSid", "priorTradeClass",
                "reasonForFailure", "companyId", "lastUpdatedDate",
                "priorTradeClassStartDate", "modifiedDate", "tradeClassEndDate",
                "tradeClassIntfid", "tradeClassStartDate", "source", "createdBy",
                "createdDate", "addChgDelIndicator", "batchId", "errorField",
                "errorCode", "tradeClass", "intfInsertedDate", "modifiedBy",
                "reprocessedFlag", "checkRecord"
            });
    private static IvldCompanyTradeClass _nullIvldCompanyTradeClass = new IvldCompanyTradeClassImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCompanyTradeClass> toCacheModel() {
                return _nullIvldCompanyTradeClassCacheModel;
            }
        };

    private static CacheModel<IvldCompanyTradeClass> _nullIvldCompanyTradeClassCacheModel =
        new CacheModel<IvldCompanyTradeClass>() {
            @Override
            public IvldCompanyTradeClass toEntityModel() {
                return _nullIvldCompanyTradeClass;
            }
        };

    public IvldCompanyTradeClassPersistenceImpl() {
        setModelClass(IvldCompanyTradeClass.class);
    }

    /**
     * Caches the ivld company trade class in the entity cache if it is enabled.
     *
     * @param ivldCompanyTradeClass the ivld company trade class
     */
    @Override
    public void cacheResult(IvldCompanyTradeClass ivldCompanyTradeClass) {
        EntityCacheUtil.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassImpl.class,
            ivldCompanyTradeClass.getPrimaryKey(), ivldCompanyTradeClass);

        ivldCompanyTradeClass.resetOriginalValues();
    }

    /**
     * Caches the ivld company trade classes in the entity cache if it is enabled.
     *
     * @param ivldCompanyTradeClasses the ivld company trade classes
     */
    @Override
    public void cacheResult(List<IvldCompanyTradeClass> ivldCompanyTradeClasses) {
        for (IvldCompanyTradeClass ivldCompanyTradeClass : ivldCompanyTradeClasses) {
            if (EntityCacheUtil.getResult(
                        IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyTradeClassImpl.class,
                        ivldCompanyTradeClass.getPrimaryKey()) == null) {
                cacheResult(ivldCompanyTradeClass);
            } else {
                ivldCompanyTradeClass.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld company trade classes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCompanyTradeClassImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCompanyTradeClassImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld company trade class.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCompanyTradeClass ivldCompanyTradeClass) {
        EntityCacheUtil.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassImpl.class,
            ivldCompanyTradeClass.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCompanyTradeClass> ivldCompanyTradeClasses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCompanyTradeClass ivldCompanyTradeClass : ivldCompanyTradeClasses) {
            EntityCacheUtil.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyTradeClassImpl.class,
                ivldCompanyTradeClass.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
     *
     * @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
     * @return the new ivld company trade class
     */
    @Override
    public IvldCompanyTradeClass create(int ivldCompanyTradeClassSid) {
        IvldCompanyTradeClass ivldCompanyTradeClass = new IvldCompanyTradeClassImpl();

        ivldCompanyTradeClass.setNew(true);
        ivldCompanyTradeClass.setPrimaryKey(ivldCompanyTradeClassSid);

        return ivldCompanyTradeClass;
    }

    /**
     * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
     * @return the ivld company trade class that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass remove(int ivldCompanyTradeClassSid)
        throws NoSuchIvldCompanyTradeClassException, SystemException {
        return remove((Serializable) ivldCompanyTradeClassSid);
    }

    /**
     * Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld company trade class
     * @return the ivld company trade class that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass remove(Serializable primaryKey)
        throws NoSuchIvldCompanyTradeClassException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCompanyTradeClass ivldCompanyTradeClass = (IvldCompanyTradeClass) session.get(IvldCompanyTradeClassImpl.class,
                    primaryKey);

            if (ivldCompanyTradeClass == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCompanyTradeClass);
        } catch (NoSuchIvldCompanyTradeClassException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCompanyTradeClass removeImpl(
        IvldCompanyTradeClass ivldCompanyTradeClass) throws SystemException {
        ivldCompanyTradeClass = toUnwrappedModel(ivldCompanyTradeClass);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCompanyTradeClass)) {
                ivldCompanyTradeClass = (IvldCompanyTradeClass) session.get(IvldCompanyTradeClassImpl.class,
                        ivldCompanyTradeClass.getPrimaryKeyObj());
            }

            if (ivldCompanyTradeClass != null) {
                session.delete(ivldCompanyTradeClass);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCompanyTradeClass != null) {
            clearCache(ivldCompanyTradeClass);
        }

        return ivldCompanyTradeClass;
    }

    @Override
    public IvldCompanyTradeClass updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass)
        throws SystemException {
        ivldCompanyTradeClass = toUnwrappedModel(ivldCompanyTradeClass);

        boolean isNew = ivldCompanyTradeClass.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCompanyTradeClass.isNew()) {
                session.save(ivldCompanyTradeClass);

                ivldCompanyTradeClass.setNew(false);
            } else {
                session.merge(ivldCompanyTradeClass);
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

        EntityCacheUtil.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyTradeClassImpl.class,
            ivldCompanyTradeClass.getPrimaryKey(), ivldCompanyTradeClass);

        return ivldCompanyTradeClass;
    }

    protected IvldCompanyTradeClass toUnwrappedModel(
        IvldCompanyTradeClass ivldCompanyTradeClass) {
        if (ivldCompanyTradeClass instanceof IvldCompanyTradeClassImpl) {
            return ivldCompanyTradeClass;
        }

        IvldCompanyTradeClassImpl ivldCompanyTradeClassImpl = new IvldCompanyTradeClassImpl();

        ivldCompanyTradeClassImpl.setNew(ivldCompanyTradeClass.isNew());
        ivldCompanyTradeClassImpl.setPrimaryKey(ivldCompanyTradeClass.getPrimaryKey());

        ivldCompanyTradeClassImpl.setIvldCompanyTradeClassSid(ivldCompanyTradeClass.getIvldCompanyTradeClassSid());
        ivldCompanyTradeClassImpl.setPriorTradeClass(ivldCompanyTradeClass.getPriorTradeClass());
        ivldCompanyTradeClassImpl.setReasonForFailure(ivldCompanyTradeClass.getReasonForFailure());
        ivldCompanyTradeClassImpl.setCompanyId(ivldCompanyTradeClass.getCompanyId());
        ivldCompanyTradeClassImpl.setLastUpdatedDate(ivldCompanyTradeClass.getLastUpdatedDate());
        ivldCompanyTradeClassImpl.setPriorTradeClassStartDate(ivldCompanyTradeClass.getPriorTradeClassStartDate());
        ivldCompanyTradeClassImpl.setModifiedDate(ivldCompanyTradeClass.getModifiedDate());
        ivldCompanyTradeClassImpl.setTradeClassEndDate(ivldCompanyTradeClass.getTradeClassEndDate());
        ivldCompanyTradeClassImpl.setTradeClassIntfid(ivldCompanyTradeClass.getTradeClassIntfid());
        ivldCompanyTradeClassImpl.setTradeClassStartDate(ivldCompanyTradeClass.getTradeClassStartDate());
        ivldCompanyTradeClassImpl.setSource(ivldCompanyTradeClass.getSource());
        ivldCompanyTradeClassImpl.setCreatedBy(ivldCompanyTradeClass.getCreatedBy());
        ivldCompanyTradeClassImpl.setCreatedDate(ivldCompanyTradeClass.getCreatedDate());
        ivldCompanyTradeClassImpl.setAddChgDelIndicator(ivldCompanyTradeClass.getAddChgDelIndicator());
        ivldCompanyTradeClassImpl.setBatchId(ivldCompanyTradeClass.getBatchId());
        ivldCompanyTradeClassImpl.setErrorField(ivldCompanyTradeClass.getErrorField());
        ivldCompanyTradeClassImpl.setErrorCode(ivldCompanyTradeClass.getErrorCode());
        ivldCompanyTradeClassImpl.setTradeClass(ivldCompanyTradeClass.getTradeClass());
        ivldCompanyTradeClassImpl.setIntfInsertedDate(ivldCompanyTradeClass.getIntfInsertedDate());
        ivldCompanyTradeClassImpl.setModifiedBy(ivldCompanyTradeClass.getModifiedBy());
        ivldCompanyTradeClassImpl.setReprocessedFlag(ivldCompanyTradeClass.getReprocessedFlag());
        ivldCompanyTradeClassImpl.setCheckRecord(ivldCompanyTradeClass.isCheckRecord());

        return ivldCompanyTradeClassImpl;
    }

    /**
     * Returns the ivld company trade class with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company trade class
     * @return the ivld company trade class
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCompanyTradeClassException, SystemException {
        IvldCompanyTradeClass ivldCompanyTradeClass = fetchByPrimaryKey(primaryKey);

        if (ivldCompanyTradeClass == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCompanyTradeClassException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCompanyTradeClass;
    }

    /**
     * Returns the ivld company trade class with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException} if it could not be found.
     *
     * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
     * @return the ivld company trade class
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass findByPrimaryKey(int ivldCompanyTradeClassSid)
        throws NoSuchIvldCompanyTradeClassException, SystemException {
        return findByPrimaryKey((Serializable) ivldCompanyTradeClassSid);
    }

    /**
     * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company trade class
     * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCompanyTradeClass ivldCompanyTradeClass = (IvldCompanyTradeClass) EntityCacheUtil.getResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyTradeClassImpl.class, primaryKey);

        if (ivldCompanyTradeClass == _nullIvldCompanyTradeClass) {
            return null;
        }

        if (ivldCompanyTradeClass == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCompanyTradeClass = (IvldCompanyTradeClass) session.get(IvldCompanyTradeClassImpl.class,
                        primaryKey);

                if (ivldCompanyTradeClass != null) {
                    cacheResult(ivldCompanyTradeClass);
                } else {
                    EntityCacheUtil.putResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyTradeClassImpl.class, primaryKey,
                        _nullIvldCompanyTradeClass);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCompanyTradeClassModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCompanyTradeClassImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCompanyTradeClass;
    }

    /**
     * Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
     * @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyTradeClass fetchByPrimaryKey(int ivldCompanyTradeClassSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCompanyTradeClassSid);
    }

    /**
     * Returns all the ivld company trade classes.
     *
     * @return the ivld company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyTradeClass> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company trade classes
     * @param end the upper bound of the range of ivld company trade classes (not inclusive)
     * @return the range of ivld company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyTradeClass> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld company trade classes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company trade classes
     * @param end the upper bound of the range of ivld company trade classes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld company trade classes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyTradeClass> findAll(int start, int end,
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

        List<IvldCompanyTradeClass> list = (List<IvldCompanyTradeClass>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCOMPANYTRADECLASS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCOMPANYTRADECLASS;

                if (pagination) {
                    sql = sql.concat(IvldCompanyTradeClassModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCompanyTradeClass>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCompanyTradeClass>(list);
                } else {
                    list = (List<IvldCompanyTradeClass>) QueryUtil.list(q,
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
     * Removes all the ivld company trade classes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCompanyTradeClass ivldCompanyTradeClass : findAll()) {
            remove(ivldCompanyTradeClass);
        }
    }

    /**
     * Returns the number of ivld company trade classes.
     *
     * @return the number of ivld company trade classes
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYTRADECLASS);

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
     * Initializes the ivld company trade class persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCompanyTradeClass")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCompanyTradeClass>> listenersList = new ArrayList<ModelListener<IvldCompanyTradeClass>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCompanyTradeClass>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCompanyTradeClassImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
