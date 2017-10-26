package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldCompanyIdentifier;
import com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierPersistence;

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
 * The persistence implementation for the ivld company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyIdentifierPersistence
 * @see IvldCompanyIdentifierUtil
 * @generated
 */
public class IvldCompanyIdentifierPersistenceImpl extends BasePersistenceImpl<IvldCompanyIdentifier>
    implements IvldCompanyIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldCompanyIdentifierUtil} to access the ivld company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            IvldCompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDCOMPANYIDENTIFIER = "SELECT ivldCompanyIdentifier FROM IvldCompanyIdentifier ivldCompanyIdentifier";
    private static final String _SQL_COUNT_IVLDCOMPANYIDENTIFIER = "SELECT COUNT(ivldCompanyIdentifier) FROM IvldCompanyIdentifier ivldCompanyIdentifier";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyIdentifier exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldCompanyIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "reasonForFailure", "companyId", "companyName", "endDate",
                "modifiedDate", "identifierStatus", "companyIdentifier",
                "entityCode", "companyIdentifierIntfid", "startDate", "source",
                "createdDate", "createdBy", "companyNo", "addChgDelIndicator",
                "batchId", "errorField", "errorCode",
                "identifierCodeQualifierName", "intfInsertedDate", "modifiedBy",
                "ivldCompanyIdentifierSid", "reprocessedFlag",
                "identifierCodeQualifier", "checkRecord"
            });
    private static IvldCompanyIdentifier _nullIvldCompanyIdentifier = new IvldCompanyIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldCompanyIdentifier> toCacheModel() {
                return _nullIvldCompanyIdentifierCacheModel;
            }
        };

    private static CacheModel<IvldCompanyIdentifier> _nullIvldCompanyIdentifierCacheModel =
        new CacheModel<IvldCompanyIdentifier>() {
            @Override
            public IvldCompanyIdentifier toEntityModel() {
                return _nullIvldCompanyIdentifier;
            }
        };

    public IvldCompanyIdentifierPersistenceImpl() {
        setModelClass(IvldCompanyIdentifier.class);
    }

    /**
     * Caches the ivld company identifier in the entity cache if it is enabled.
     *
     * @param ivldCompanyIdentifier the ivld company identifier
     */
    @Override
    public void cacheResult(IvldCompanyIdentifier ivldCompanyIdentifier) {
        EntityCacheUtil.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierImpl.class,
            ivldCompanyIdentifier.getPrimaryKey(), ivldCompanyIdentifier);

        ivldCompanyIdentifier.resetOriginalValues();
    }

    /**
     * Caches the ivld company identifiers in the entity cache if it is enabled.
     *
     * @param ivldCompanyIdentifiers the ivld company identifiers
     */
    @Override
    public void cacheResult(List<IvldCompanyIdentifier> ivldCompanyIdentifiers) {
        for (IvldCompanyIdentifier ivldCompanyIdentifier : ivldCompanyIdentifiers) {
            if (EntityCacheUtil.getResult(
                        IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyIdentifierImpl.class,
                        ivldCompanyIdentifier.getPrimaryKey()) == null) {
                cacheResult(ivldCompanyIdentifier);
            } else {
                ivldCompanyIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld company identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldCompanyIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldCompanyIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld company identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldCompanyIdentifier ivldCompanyIdentifier) {
        EntityCacheUtil.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierImpl.class,
            ivldCompanyIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldCompanyIdentifier> ivldCompanyIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldCompanyIdentifier ivldCompanyIdentifier : ivldCompanyIdentifiers) {
            EntityCacheUtil.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyIdentifierImpl.class,
                ivldCompanyIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
     *
     * @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
     * @return the new ivld company identifier
     */
    @Override
    public IvldCompanyIdentifier create(int ivldCompanyIdentifierSid) {
        IvldCompanyIdentifier ivldCompanyIdentifier = new IvldCompanyIdentifierImpl();

        ivldCompanyIdentifier.setNew(true);
        ivldCompanyIdentifier.setPrimaryKey(ivldCompanyIdentifierSid);

        return ivldCompanyIdentifier;
    }

    /**
     * Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
     * @return the ivld company identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier remove(int ivldCompanyIdentifierSid)
        throws NoSuchIvldCompanyIdentifierException, SystemException {
        return remove((Serializable) ivldCompanyIdentifierSid);
    }

    /**
     * Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld company identifier
     * @return the ivld company identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier remove(Serializable primaryKey)
        throws NoSuchIvldCompanyIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldCompanyIdentifier ivldCompanyIdentifier = (IvldCompanyIdentifier) session.get(IvldCompanyIdentifierImpl.class,
                    primaryKey);

            if (ivldCompanyIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldCompanyIdentifier);
        } catch (NoSuchIvldCompanyIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldCompanyIdentifier removeImpl(
        IvldCompanyIdentifier ivldCompanyIdentifier) throws SystemException {
        ivldCompanyIdentifier = toUnwrappedModel(ivldCompanyIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldCompanyIdentifier)) {
                ivldCompanyIdentifier = (IvldCompanyIdentifier) session.get(IvldCompanyIdentifierImpl.class,
                        ivldCompanyIdentifier.getPrimaryKeyObj());
            }

            if (ivldCompanyIdentifier != null) {
                session.delete(ivldCompanyIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldCompanyIdentifier != null) {
            clearCache(ivldCompanyIdentifier);
        }

        return ivldCompanyIdentifier;
    }

    @Override
    public IvldCompanyIdentifier updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier)
        throws SystemException {
        ivldCompanyIdentifier = toUnwrappedModel(ivldCompanyIdentifier);

        boolean isNew = ivldCompanyIdentifier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldCompanyIdentifier.isNew()) {
                session.save(ivldCompanyIdentifier);

                ivldCompanyIdentifier.setNew(false);
            } else {
                session.merge(ivldCompanyIdentifier);
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

        EntityCacheUtil.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldCompanyIdentifierImpl.class,
            ivldCompanyIdentifier.getPrimaryKey(), ivldCompanyIdentifier);

        return ivldCompanyIdentifier;
    }

    protected IvldCompanyIdentifier toUnwrappedModel(
        IvldCompanyIdentifier ivldCompanyIdentifier) {
        if (ivldCompanyIdentifier instanceof IvldCompanyIdentifierImpl) {
            return ivldCompanyIdentifier;
        }

        IvldCompanyIdentifierImpl ivldCompanyIdentifierImpl = new IvldCompanyIdentifierImpl();

        ivldCompanyIdentifierImpl.setNew(ivldCompanyIdentifier.isNew());
        ivldCompanyIdentifierImpl.setPrimaryKey(ivldCompanyIdentifier.getPrimaryKey());

        ivldCompanyIdentifierImpl.setReasonForFailure(ivldCompanyIdentifier.getReasonForFailure());
        ivldCompanyIdentifierImpl.setCompanyId(ivldCompanyIdentifier.getCompanyId());
        ivldCompanyIdentifierImpl.setCompanyName(ivldCompanyIdentifier.getCompanyName());
        ivldCompanyIdentifierImpl.setEndDate(ivldCompanyIdentifier.getEndDate());
        ivldCompanyIdentifierImpl.setModifiedDate(ivldCompanyIdentifier.getModifiedDate());
        ivldCompanyIdentifierImpl.setIdentifierStatus(ivldCompanyIdentifier.getIdentifierStatus());
        ivldCompanyIdentifierImpl.setCompanyIdentifier(ivldCompanyIdentifier.getCompanyIdentifier());
        ivldCompanyIdentifierImpl.setEntityCode(ivldCompanyIdentifier.getEntityCode());
        ivldCompanyIdentifierImpl.setCompanyIdentifierIntfid(ivldCompanyIdentifier.getCompanyIdentifierIntfid());
        ivldCompanyIdentifierImpl.setStartDate(ivldCompanyIdentifier.getStartDate());
        ivldCompanyIdentifierImpl.setSource(ivldCompanyIdentifier.getSource());
        ivldCompanyIdentifierImpl.setCreatedDate(ivldCompanyIdentifier.getCreatedDate());
        ivldCompanyIdentifierImpl.setCreatedBy(ivldCompanyIdentifier.getCreatedBy());
        ivldCompanyIdentifierImpl.setCompanyNo(ivldCompanyIdentifier.getCompanyNo());
        ivldCompanyIdentifierImpl.setAddChgDelIndicator(ivldCompanyIdentifier.getAddChgDelIndicator());
        ivldCompanyIdentifierImpl.setBatchId(ivldCompanyIdentifier.getBatchId());
        ivldCompanyIdentifierImpl.setErrorField(ivldCompanyIdentifier.getErrorField());
        ivldCompanyIdentifierImpl.setErrorCode(ivldCompanyIdentifier.getErrorCode());
        ivldCompanyIdentifierImpl.setIdentifierCodeQualifierName(ivldCompanyIdentifier.getIdentifierCodeQualifierName());
        ivldCompanyIdentifierImpl.setIntfInsertedDate(ivldCompanyIdentifier.getIntfInsertedDate());
        ivldCompanyIdentifierImpl.setModifiedBy(ivldCompanyIdentifier.getModifiedBy());
        ivldCompanyIdentifierImpl.setIvldCompanyIdentifierSid(ivldCompanyIdentifier.getIvldCompanyIdentifierSid());
        ivldCompanyIdentifierImpl.setReprocessedFlag(ivldCompanyIdentifier.getReprocessedFlag());
        ivldCompanyIdentifierImpl.setIdentifierCodeQualifier(ivldCompanyIdentifier.getIdentifierCodeQualifier());
        ivldCompanyIdentifierImpl.setCheckRecord(ivldCompanyIdentifier.isCheckRecord());

        return ivldCompanyIdentifierImpl;
    }

    /**
     * Returns the ivld company identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company identifier
     * @return the ivld company identifier
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldCompanyIdentifierException, SystemException {
        IvldCompanyIdentifier ivldCompanyIdentifier = fetchByPrimaryKey(primaryKey);

        if (ivldCompanyIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldCompanyIdentifier;
    }

    /**
     * Returns the ivld company identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException} if it could not be found.
     *
     * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
     * @return the ivld company identifier
     * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier findByPrimaryKey(int ivldCompanyIdentifierSid)
        throws NoSuchIvldCompanyIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) ivldCompanyIdentifierSid);
    }

    /**
     * Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld company identifier
     * @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldCompanyIdentifier ivldCompanyIdentifier = (IvldCompanyIdentifier) EntityCacheUtil.getResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                IvldCompanyIdentifierImpl.class, primaryKey);

        if (ivldCompanyIdentifier == _nullIvldCompanyIdentifier) {
            return null;
        }

        if (ivldCompanyIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                ivldCompanyIdentifier = (IvldCompanyIdentifier) session.get(IvldCompanyIdentifierImpl.class,
                        primaryKey);

                if (ivldCompanyIdentifier != null) {
                    cacheResult(ivldCompanyIdentifier);
                } else {
                    EntityCacheUtil.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        IvldCompanyIdentifierImpl.class, primaryKey,
                        _nullIvldCompanyIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    IvldCompanyIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldCompanyIdentifier;
    }

    /**
     * Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
     * @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldCompanyIdentifier fetchByPrimaryKey(int ivldCompanyIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldCompanyIdentifierSid);
    }

    /**
     * Returns all the ivld company identifiers.
     *
     * @return the ivld company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld company identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company identifiers
     * @param end the upper bound of the range of ivld company identifiers (not inclusive)
     * @return the range of ivld company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld company identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld company identifiers
     * @param end the upper bound of the range of ivld company identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldCompanyIdentifier> findAll(int start, int end,
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

        List<IvldCompanyIdentifier> list = (List<IvldCompanyIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDCOMPANYIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDCOMPANYIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(IvldCompanyIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldCompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldCompanyIdentifier>(list);
                } else {
                    list = (List<IvldCompanyIdentifier>) QueryUtil.list(q,
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
     * Removes all the ivld company identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldCompanyIdentifier ivldCompanyIdentifier : findAll()) {
            remove(ivldCompanyIdentifier);
        }
    }

    /**
     * Returns the number of ivld company identifiers.
     *
     * @return the number of ivld company identifiers
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

                Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYIDENTIFIER);

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
     * Initializes the ivld company identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldCompanyIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldCompanyIdentifier>> listenersList = new ArrayList<ModelListener<IvldCompanyIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldCompanyIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldCompanyIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
