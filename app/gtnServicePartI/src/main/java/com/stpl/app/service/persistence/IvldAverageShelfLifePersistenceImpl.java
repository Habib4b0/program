package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldAverageShelfLifeException;
import com.stpl.app.model.IvldAverageShelfLife;
import com.stpl.app.model.impl.IvldAverageShelfLifeImpl;
import com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl;
import com.stpl.app.service.persistence.IvldAverageShelfLifePersistence;

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
 * The persistence implementation for the ivld average shelf life service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAverageShelfLifePersistence
 * @see IvldAverageShelfLifeUtil
 * @generated
 */
public class IvldAverageShelfLifePersistenceImpl extends BasePersistenceImpl<IvldAverageShelfLife>
    implements IvldAverageShelfLifePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldAverageShelfLifeUtil} to access the ivld average shelf life persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldAverageShelfLifeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED,
            IvldAverageShelfLifeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED,
            IvldAverageShelfLifeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDAVERAGESHELFLIFE = "SELECT ivldAverageShelfLife FROM IvldAverageShelfLife ivldAverageShelfLife";
    private static final String _SQL_COUNT_IVLDAVERAGESHELFLIFE = "SELECT COUNT(ivldAverageShelfLife) FROM IvldAverageShelfLife ivldAverageShelfLife";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldAverageShelfLife.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldAverageShelfLife exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldAverageShelfLifePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "reasonForFailure", "ivldAverageShelfLifeSid", "itemId",
                "avgShelfLife", "modifiedDate", "createdBy", "createdDate",
                "source", "itemIdType", "batchId", "addChgDelIndicator",
                "averageShelfLifeIntfid", "errorField", "errorCode",
                "intfInsertedDate", "modifiedBy", "reprocessedFlag",
                "checkRecord"
            });
    private static IvldAverageShelfLife _nullIvldAverageShelfLife = new IvldAverageShelfLifeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldAverageShelfLife> toCacheModel() {
                return _nullIvldAverageShelfLifeCacheModel;
            }
        };

    private static CacheModel<IvldAverageShelfLife> _nullIvldAverageShelfLifeCacheModel =
        new CacheModel<IvldAverageShelfLife>() {
            @Override
            public IvldAverageShelfLife toEntityModel() {
                return _nullIvldAverageShelfLife;
            }
        };

    public IvldAverageShelfLifePersistenceImpl() {
        setModelClass(IvldAverageShelfLife.class);
    }

    /**
     * Caches the ivld average shelf life in the entity cache if it is enabled.
     *
     * @param ivldAverageShelfLife the ivld average shelf life
     */
    @Override
    public void cacheResult(IvldAverageShelfLife ivldAverageShelfLife) {
        EntityCacheUtil.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeImpl.class,
            ivldAverageShelfLife.getPrimaryKey(), ivldAverageShelfLife);

        ivldAverageShelfLife.resetOriginalValues();
    }

    /**
     * Caches the ivld average shelf lifes in the entity cache if it is enabled.
     *
     * @param ivldAverageShelfLifes the ivld average shelf lifes
     */
    @Override
    public void cacheResult(List<IvldAverageShelfLife> ivldAverageShelfLifes) {
        for (IvldAverageShelfLife ivldAverageShelfLife : ivldAverageShelfLifes) {
            if (EntityCacheUtil.getResult(
                        IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
                        IvldAverageShelfLifeImpl.class,
                        ivldAverageShelfLife.getPrimaryKey()) == null) {
                cacheResult(ivldAverageShelfLife);
            } else {
                ivldAverageShelfLife.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld average shelf lifes.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldAverageShelfLifeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldAverageShelfLifeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld average shelf life.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldAverageShelfLife ivldAverageShelfLife) {
        EntityCacheUtil.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeImpl.class, ivldAverageShelfLife.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldAverageShelfLife> ivldAverageShelfLifes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldAverageShelfLife ivldAverageShelfLife : ivldAverageShelfLifes) {
            EntityCacheUtil.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
                IvldAverageShelfLifeImpl.class,
                ivldAverageShelfLife.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
     *
     * @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
     * @return the new ivld average shelf life
     */
    @Override
    public IvldAverageShelfLife create(int ivldAverageShelfLifeSid) {
        IvldAverageShelfLife ivldAverageShelfLife = new IvldAverageShelfLifeImpl();

        ivldAverageShelfLife.setNew(true);
        ivldAverageShelfLife.setPrimaryKey(ivldAverageShelfLifeSid);

        return ivldAverageShelfLife;
    }

    /**
     * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
     * @return the ivld average shelf life that was removed
     * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife remove(int ivldAverageShelfLifeSid)
        throws NoSuchIvldAverageShelfLifeException, SystemException {
        return remove((Serializable) ivldAverageShelfLifeSid);
    }

    /**
     * Removes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld average shelf life
     * @return the ivld average shelf life that was removed
     * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife remove(Serializable primaryKey)
        throws NoSuchIvldAverageShelfLifeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldAverageShelfLife ivldAverageShelfLife = (IvldAverageShelfLife) session.get(IvldAverageShelfLifeImpl.class,
                    primaryKey);

            if (ivldAverageShelfLife == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldAverageShelfLifeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldAverageShelfLife);
        } catch (NoSuchIvldAverageShelfLifeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldAverageShelfLife removeImpl(
        IvldAverageShelfLife ivldAverageShelfLife) throws SystemException {
        ivldAverageShelfLife = toUnwrappedModel(ivldAverageShelfLife);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldAverageShelfLife)) {
                ivldAverageShelfLife = (IvldAverageShelfLife) session.get(IvldAverageShelfLifeImpl.class,
                        ivldAverageShelfLife.getPrimaryKeyObj());
            }

            if (ivldAverageShelfLife != null) {
                session.delete(ivldAverageShelfLife);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldAverageShelfLife != null) {
            clearCache(ivldAverageShelfLife);
        }

        return ivldAverageShelfLife;
    }

    @Override
    public IvldAverageShelfLife updateImpl(
        com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife)
        throws SystemException {
        ivldAverageShelfLife = toUnwrappedModel(ivldAverageShelfLife);

        boolean isNew = ivldAverageShelfLife.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldAverageShelfLife.isNew()) {
                session.save(ivldAverageShelfLife);

                ivldAverageShelfLife.setNew(false);
            } else {
                session.merge(ivldAverageShelfLife);
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

        EntityCacheUtil.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
            IvldAverageShelfLifeImpl.class,
            ivldAverageShelfLife.getPrimaryKey(), ivldAverageShelfLife);

        return ivldAverageShelfLife;
    }

    protected IvldAverageShelfLife toUnwrappedModel(
        IvldAverageShelfLife ivldAverageShelfLife) {
        if (ivldAverageShelfLife instanceof IvldAverageShelfLifeImpl) {
            return ivldAverageShelfLife;
        }

        IvldAverageShelfLifeImpl ivldAverageShelfLifeImpl = new IvldAverageShelfLifeImpl();

        ivldAverageShelfLifeImpl.setNew(ivldAverageShelfLife.isNew());
        ivldAverageShelfLifeImpl.setPrimaryKey(ivldAverageShelfLife.getPrimaryKey());

        ivldAverageShelfLifeImpl.setReasonForFailure(ivldAverageShelfLife.getReasonForFailure());
        ivldAverageShelfLifeImpl.setIvldAverageShelfLifeSid(ivldAverageShelfLife.getIvldAverageShelfLifeSid());
        ivldAverageShelfLifeImpl.setItemId(ivldAverageShelfLife.getItemId());
        ivldAverageShelfLifeImpl.setAvgShelfLife(ivldAverageShelfLife.getAvgShelfLife());
        ivldAverageShelfLifeImpl.setModifiedDate(ivldAverageShelfLife.getModifiedDate());
        ivldAverageShelfLifeImpl.setCreatedBy(ivldAverageShelfLife.getCreatedBy());
        ivldAverageShelfLifeImpl.setCreatedDate(ivldAverageShelfLife.getCreatedDate());
        ivldAverageShelfLifeImpl.setSource(ivldAverageShelfLife.getSource());
        ivldAverageShelfLifeImpl.setItemIdType(ivldAverageShelfLife.getItemIdType());
        ivldAverageShelfLifeImpl.setBatchId(ivldAverageShelfLife.getBatchId());
        ivldAverageShelfLifeImpl.setAddChgDelIndicator(ivldAverageShelfLife.getAddChgDelIndicator());
        ivldAverageShelfLifeImpl.setAverageShelfLifeIntfid(ivldAverageShelfLife.getAverageShelfLifeIntfid());
        ivldAverageShelfLifeImpl.setErrorField(ivldAverageShelfLife.getErrorField());
        ivldAverageShelfLifeImpl.setErrorCode(ivldAverageShelfLife.getErrorCode());
        ivldAverageShelfLifeImpl.setIntfInsertedDate(ivldAverageShelfLife.getIntfInsertedDate());
        ivldAverageShelfLifeImpl.setModifiedBy(ivldAverageShelfLife.getModifiedBy());
        ivldAverageShelfLifeImpl.setReprocessedFlag(ivldAverageShelfLife.getReprocessedFlag());
        ivldAverageShelfLifeImpl.setCheckRecord(ivldAverageShelfLife.isCheckRecord());

        return ivldAverageShelfLifeImpl;
    }

    /**
     * Returns the ivld average shelf life with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld average shelf life
     * @return the ivld average shelf life
     * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldAverageShelfLifeException, SystemException {
        IvldAverageShelfLife ivldAverageShelfLife = fetchByPrimaryKey(primaryKey);

        if (ivldAverageShelfLife == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldAverageShelfLifeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldAverageShelfLife;
    }

    /**
     * Returns the ivld average shelf life with the primary key or throws a {@link com.stpl.app.NoSuchIvldAverageShelfLifeException} if it could not be found.
     *
     * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
     * @return the ivld average shelf life
     * @throws com.stpl.app.NoSuchIvldAverageShelfLifeException if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife findByPrimaryKey(int ivldAverageShelfLifeSid)
        throws NoSuchIvldAverageShelfLifeException, SystemException {
        return findByPrimaryKey((Serializable) ivldAverageShelfLifeSid);
    }

    /**
     * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld average shelf life
     * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldAverageShelfLife ivldAverageShelfLife = (IvldAverageShelfLife) EntityCacheUtil.getResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
                IvldAverageShelfLifeImpl.class, primaryKey);

        if (ivldAverageShelfLife == _nullIvldAverageShelfLife) {
            return null;
        }

        if (ivldAverageShelfLife == null) {
            Session session = null;

            try {
                session = openSession();

                ivldAverageShelfLife = (IvldAverageShelfLife) session.get(IvldAverageShelfLifeImpl.class,
                        primaryKey);

                if (ivldAverageShelfLife != null) {
                    cacheResult(ivldAverageShelfLife);
                } else {
                    EntityCacheUtil.putResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
                        IvldAverageShelfLifeImpl.class, primaryKey,
                        _nullIvldAverageShelfLife);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldAverageShelfLifeModelImpl.ENTITY_CACHE_ENABLED,
                    IvldAverageShelfLifeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldAverageShelfLife;
    }

    /**
     * Returns the ivld average shelf life with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
     * @return the ivld average shelf life, or <code>null</code> if a ivld average shelf life with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldAverageShelfLife fetchByPrimaryKey(int ivldAverageShelfLifeSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldAverageShelfLifeSid);
    }

    /**
     * Returns all the ivld average shelf lifes.
     *
     * @return the ivld average shelf lifes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAverageShelfLife> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld average shelf lifes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld average shelf lifes
     * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
     * @return the range of ivld average shelf lifes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAverageShelfLife> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld average shelf lifes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld average shelf lifes
     * @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld average shelf lifes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldAverageShelfLife> findAll(int start, int end,
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

        List<IvldAverageShelfLife> list = (List<IvldAverageShelfLife>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDAVERAGESHELFLIFE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDAVERAGESHELFLIFE;

                if (pagination) {
                    sql = sql.concat(IvldAverageShelfLifeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldAverageShelfLife>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldAverageShelfLife>(list);
                } else {
                    list = (List<IvldAverageShelfLife>) QueryUtil.list(q,
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
     * Removes all the ivld average shelf lifes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldAverageShelfLife ivldAverageShelfLife : findAll()) {
            remove(ivldAverageShelfLife);
        }
    }

    /**
     * Returns the number of ivld average shelf lifes.
     *
     * @return the number of ivld average shelf lifes
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

                Query q = session.createQuery(_SQL_COUNT_IVLDAVERAGESHELFLIFE);

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
     * Initializes the ivld average shelf life persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldAverageShelfLife")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldAverageShelfLife>> listenersList = new ArrayList<ModelListener<IvldAverageShelfLife>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldAverageShelfLife>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldAverageShelfLifeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
