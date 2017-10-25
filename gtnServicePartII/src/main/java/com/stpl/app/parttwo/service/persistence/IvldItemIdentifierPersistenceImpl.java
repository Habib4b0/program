package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchIvldItemIdentifierException;
import com.stpl.app.parttwo.model.IvldItemIdentifier;
import com.stpl.app.parttwo.model.impl.IvldItemIdentifierImpl;
import com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemIdentifierPersistence;

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
 * The persistence implementation for the ivld item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemIdentifierPersistence
 * @see IvldItemIdentifierUtil
 * @generated
 */
public class IvldItemIdentifierPersistenceImpl extends BasePersistenceImpl<IvldItemIdentifier>
    implements IvldItemIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldItemIdentifierUtil} to access the ivld item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldItemIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            IvldItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            IvldItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IVLDITEMIDENTIFIER = "SELECT ivldItemIdentifier FROM IvldItemIdentifier ivldItemIdentifier";
    private static final String _SQL_COUNT_IVLDITEMIDENTIFIER = "SELECT COUNT(ivldItemIdentifier) FROM IvldItemIdentifier ivldItemIdentifier";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemIdentifier exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldItemIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdBy", "identifierCodeQualifierName",
                "ivldItemIdentifierSid", "itemNo", "modifiedBy", "createdDate",
                "identifierCodeQualifier", "itemId", "endDate", "errorField",
                "startDate", "batchId", "modifiedDate", "itemName", "errorCode",
                "reprocessedFlag", "itemIdentifier", "itemStatus",
                "reasonForFailure", "source", "addChgDelIndicator", "entityCode",
                "itemIdentifierIntfid", "intfInsertedDate", "checkRecord"
            });
    private static IvldItemIdentifier _nullIvldItemIdentifier = new IvldItemIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldItemIdentifier> toCacheModel() {
                return _nullIvldItemIdentifierCacheModel;
            }
        };

    private static CacheModel<IvldItemIdentifier> _nullIvldItemIdentifierCacheModel =
        new CacheModel<IvldItemIdentifier>() {
            @Override
            public IvldItemIdentifier toEntityModel() {
                return _nullIvldItemIdentifier;
            }
        };

    public IvldItemIdentifierPersistenceImpl() {
        setModelClass(IvldItemIdentifier.class);
    }

    /**
     * Caches the ivld item identifier in the entity cache if it is enabled.
     *
     * @param ivldItemIdentifier the ivld item identifier
     */
    @Override
    public void cacheResult(IvldItemIdentifier ivldItemIdentifier) {
        EntityCacheUtil.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey(),
            ivldItemIdentifier);

        ivldItemIdentifier.resetOriginalValues();
    }

    /**
     * Caches the ivld item identifiers in the entity cache if it is enabled.
     *
     * @param ivldItemIdentifiers the ivld item identifiers
     */
    @Override
    public void cacheResult(List<IvldItemIdentifier> ivldItemIdentifiers) {
        for (IvldItemIdentifier ivldItemIdentifier : ivldItemIdentifiers) {
            if (EntityCacheUtil.getResult(
                        IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemIdentifierImpl.class,
                        ivldItemIdentifier.getPrimaryKey()) == null) {
                cacheResult(ivldItemIdentifier);
            } else {
                ivldItemIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld item identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldItemIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldItemIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld item identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IvldItemIdentifier ivldItemIdentifier) {
        EntityCacheUtil.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IvldItemIdentifier> ivldItemIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldItemIdentifier ivldItemIdentifier : ivldItemIdentifiers) {
            EntityCacheUtil.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
     *
     * @param ivldItemIdentifierSid the primary key for the new ivld item identifier
     * @return the new ivld item identifier
     */
    @Override
    public IvldItemIdentifier create(int ivldItemIdentifierSid) {
        IvldItemIdentifier ivldItemIdentifier = new IvldItemIdentifierImpl();

        ivldItemIdentifier.setNew(true);
        ivldItemIdentifier.setPrimaryKey(ivldItemIdentifierSid);

        return ivldItemIdentifier;
    }

    /**
     * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldItemIdentifierSid the primary key of the ivld item identifier
     * @return the ivld item identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier remove(int ivldItemIdentifierSid)
        throws NoSuchIvldItemIdentifierException, SystemException {
        return remove((Serializable) ivldItemIdentifierSid);
    }

    /**
     * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld item identifier
     * @return the ivld item identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier remove(Serializable primaryKey)
        throws NoSuchIvldItemIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldItemIdentifier ivldItemIdentifier = (IvldItemIdentifier) session.get(IvldItemIdentifierImpl.class,
                    primaryKey);

            if (ivldItemIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldItemIdentifier);
        } catch (NoSuchIvldItemIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldItemIdentifier removeImpl(
        IvldItemIdentifier ivldItemIdentifier) throws SystemException {
        ivldItemIdentifier = toUnwrappedModel(ivldItemIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldItemIdentifier)) {
                ivldItemIdentifier = (IvldItemIdentifier) session.get(IvldItemIdentifierImpl.class,
                        ivldItemIdentifier.getPrimaryKeyObj());
            }

            if (ivldItemIdentifier != null) {
                session.delete(ivldItemIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldItemIdentifier != null) {
            clearCache(ivldItemIdentifier);
        }

        return ivldItemIdentifier;
    }

    @Override
    public IvldItemIdentifier updateImpl(
        com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier)
        throws SystemException {
        ivldItemIdentifier = toUnwrappedModel(ivldItemIdentifier);

        boolean isNew = ivldItemIdentifier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldItemIdentifier.isNew()) {
                session.save(ivldItemIdentifier);

                ivldItemIdentifier.setNew(false);
            } else {
                session.merge(ivldItemIdentifier);
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

        EntityCacheUtil.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey(),
            ivldItemIdentifier);

        return ivldItemIdentifier;
    }

    protected IvldItemIdentifier toUnwrappedModel(
        IvldItemIdentifier ivldItemIdentifier) {
        if (ivldItemIdentifier instanceof IvldItemIdentifierImpl) {
            return ivldItemIdentifier;
        }

        IvldItemIdentifierImpl ivldItemIdentifierImpl = new IvldItemIdentifierImpl();

        ivldItemIdentifierImpl.setNew(ivldItemIdentifier.isNew());
        ivldItemIdentifierImpl.setPrimaryKey(ivldItemIdentifier.getPrimaryKey());

        ivldItemIdentifierImpl.setCreatedBy(ivldItemIdentifier.getCreatedBy());
        ivldItemIdentifierImpl.setIdentifierCodeQualifierName(ivldItemIdentifier.getIdentifierCodeQualifierName());
        ivldItemIdentifierImpl.setIvldItemIdentifierSid(ivldItemIdentifier.getIvldItemIdentifierSid());
        ivldItemIdentifierImpl.setItemNo(ivldItemIdentifier.getItemNo());
        ivldItemIdentifierImpl.setModifiedBy(ivldItemIdentifier.getModifiedBy());
        ivldItemIdentifierImpl.setCreatedDate(ivldItemIdentifier.getCreatedDate());
        ivldItemIdentifierImpl.setIdentifierCodeQualifier(ivldItemIdentifier.getIdentifierCodeQualifier());
        ivldItemIdentifierImpl.setItemId(ivldItemIdentifier.getItemId());
        ivldItemIdentifierImpl.setEndDate(ivldItemIdentifier.getEndDate());
        ivldItemIdentifierImpl.setErrorField(ivldItemIdentifier.getErrorField());
        ivldItemIdentifierImpl.setStartDate(ivldItemIdentifier.getStartDate());
        ivldItemIdentifierImpl.setBatchId(ivldItemIdentifier.getBatchId());
        ivldItemIdentifierImpl.setModifiedDate(ivldItemIdentifier.getModifiedDate());
        ivldItemIdentifierImpl.setItemName(ivldItemIdentifier.getItemName());
        ivldItemIdentifierImpl.setErrorCode(ivldItemIdentifier.getErrorCode());
        ivldItemIdentifierImpl.setReprocessedFlag(ivldItemIdentifier.getReprocessedFlag());
        ivldItemIdentifierImpl.setItemIdentifier(ivldItemIdentifier.getItemIdentifier());
        ivldItemIdentifierImpl.setItemStatus(ivldItemIdentifier.getItemStatus());
        ivldItemIdentifierImpl.setReasonForFailure(ivldItemIdentifier.getReasonForFailure());
        ivldItemIdentifierImpl.setSource(ivldItemIdentifier.getSource());
        ivldItemIdentifierImpl.setAddChgDelIndicator(ivldItemIdentifier.getAddChgDelIndicator());
        ivldItemIdentifierImpl.setEntityCode(ivldItemIdentifier.getEntityCode());
        ivldItemIdentifierImpl.setItemIdentifierIntfid(ivldItemIdentifier.getItemIdentifierIntfid());
        ivldItemIdentifierImpl.setIntfInsertedDate(ivldItemIdentifier.getIntfInsertedDate());
        ivldItemIdentifierImpl.setCheckRecord(ivldItemIdentifier.isCheckRecord());

        return ivldItemIdentifierImpl;
    }

    /**
     * Returns the ivld item identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item identifier
     * @return the ivld item identifier
     * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldItemIdentifierException, SystemException {
        IvldItemIdentifier ivldItemIdentifier = fetchByPrimaryKey(primaryKey);

        if (ivldItemIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldItemIdentifier;
    }

    /**
     * Returns the ivld item identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemIdentifierException} if it could not be found.
     *
     * @param ivldItemIdentifierSid the primary key of the ivld item identifier
     * @return the ivld item identifier
     * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier findByPrimaryKey(int ivldItemIdentifierSid)
        throws NoSuchIvldItemIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) ivldItemIdentifierSid);
    }

    /**
     * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item identifier
     * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IvldItemIdentifier ivldItemIdentifier = (IvldItemIdentifier) EntityCacheUtil.getResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemIdentifierImpl.class, primaryKey);

        if (ivldItemIdentifier == _nullIvldItemIdentifier) {
            return null;
        }

        if (ivldItemIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                ivldItemIdentifier = (IvldItemIdentifier) session.get(IvldItemIdentifierImpl.class,
                        primaryKey);

                if (ivldItemIdentifier != null) {
                    cacheResult(ivldItemIdentifier);
                } else {
                    EntityCacheUtil.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemIdentifierImpl.class, primaryKey,
                        _nullIvldItemIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    IvldItemIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldItemIdentifier;
    }

    /**
     * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldItemIdentifierSid the primary key of the ivld item identifier
     * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemIdentifier fetchByPrimaryKey(int ivldItemIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldItemIdentifierSid);
    }

    /**
     * Returns all the ivld item identifiers.
     *
     * @return the ivld item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item identifiers
     * @param end the upper bound of the range of ivld item identifiers (not inclusive)
     * @return the range of ivld item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item identifiers
     * @param end the upper bound of the range of ivld item identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemIdentifier> findAll(int start, int end,
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

        List<IvldItemIdentifier> list = (List<IvldItemIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDITEMIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDITEMIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(IvldItemIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldItemIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldItemIdentifier>(list);
                } else {
                    list = (List<IvldItemIdentifier>) QueryUtil.list(q,
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
     * Removes all the ivld item identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldItemIdentifier ivldItemIdentifier : findAll()) {
            remove(ivldItemIdentifier);
        }
    }

    /**
     * Returns the number of ivld item identifiers.
     *
     * @return the number of ivld item identifiers
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

                Query q = session.createQuery(_SQL_COUNT_IVLDITEMIDENTIFIER);

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
     * Initializes the ivld item identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.IvldItemIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldItemIdentifier>> listenersList = new ArrayList<ModelListener<IvldItemIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldItemIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldItemIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
