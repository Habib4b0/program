package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchIvldItemHierarchyDefinitionException;
import com.stpl.app.model.IvldItemHierarchyDefinition;
import com.stpl.app.model.impl.IvldItemHierarchyDefinitionImpl;
import com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.IvldItemHierarchyDefinitionPersistence;

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
 * The persistence implementation for the ivld item hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionPersistence
 * @see IvldItemHierarchyDefinitionUtil
 * @generated
 */
public class IvldItemHierarchyDefinitionPersistenceImpl
    extends BasePersistenceImpl<IvldItemHierarchyDefinition>
    implements IvldItemHierarchyDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IvldItemHierarchyDefinitionUtil} to access the ivld item hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IvldItemHierarchyDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            IvldItemHierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            IvldItemHierarchyDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_IVLDITEMHIERARCHYDEFINITION = "SELECT ivldItemHierarchyDefinition FROM IvldItemHierarchyDefinition ivldItemHierarchyDefinition";
    private static final String _SQL_COUNT_IVLDITEMHIERARCHYDEFINITION = "SELECT COUNT(ivldItemHierarchyDefinition) FROM IvldItemHierarchyDefinition ivldItemHierarchyDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemHierarchyDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemHierarchyDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IvldItemHierarchyDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "member", "reasonForFailure", "itemHierarchyDefnIntfid",
                "bpiLvl", "modifiedDate", "alias", "createdBy", "createdDate",
                "source", "batchId", "addChgDelIndicator",
                "ivldItemHierarchyDefinitionSid", "errorField", "errorCode",
                "intfInsertedDate", "modifiedBy", "reprocessedFlag"
            });
    private static IvldItemHierarchyDefinition _nullIvldItemHierarchyDefinition = new IvldItemHierarchyDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IvldItemHierarchyDefinition> toCacheModel() {
                return _nullIvldItemHierarchyDefinitionCacheModel;
            }
        };

    private static CacheModel<IvldItemHierarchyDefinition> _nullIvldItemHierarchyDefinitionCacheModel =
        new CacheModel<IvldItemHierarchyDefinition>() {
            @Override
            public IvldItemHierarchyDefinition toEntityModel() {
                return _nullIvldItemHierarchyDefinition;
            }
        };

    public IvldItemHierarchyDefinitionPersistenceImpl() {
        setModelClass(IvldItemHierarchyDefinition.class);
    }

    /**
     * Caches the ivld item hierarchy definition in the entity cache if it is enabled.
     *
     * @param ivldItemHierarchyDefinition the ivld item hierarchy definition
     */
    @Override
    public void cacheResult(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        EntityCacheUtil.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionImpl.class,
            ivldItemHierarchyDefinition.getPrimaryKey(),
            ivldItemHierarchyDefinition);

        ivldItemHierarchyDefinition.resetOriginalValues();
    }

    /**
     * Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
     *
     * @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
     */
    @Override
    public void cacheResult(
        List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
        for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : ivldItemHierarchyDefinitions) {
            if (EntityCacheUtil.getResult(
                        IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemHierarchyDefinitionImpl.class,
                        ivldItemHierarchyDefinition.getPrimaryKey()) == null) {
                cacheResult(ivldItemHierarchyDefinition);
            } else {
                ivldItemHierarchyDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ivld item hierarchy definitions.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IvldItemHierarchyDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IvldItemHierarchyDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ivld item hierarchy definition.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        EntityCacheUtil.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionImpl.class,
            ivldItemHierarchyDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : ivldItemHierarchyDefinitions) {
            EntityCacheUtil.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemHierarchyDefinitionImpl.class,
                ivldItemHierarchyDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
     *
     * @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
     * @return the new ivld item hierarchy definition
     */
    @Override
    public IvldItemHierarchyDefinition create(
        int ivldItemHierarchyDefinitionSid) {
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition = new IvldItemHierarchyDefinitionImpl();

        ivldItemHierarchyDefinition.setNew(true);
        ivldItemHierarchyDefinition.setPrimaryKey(ivldItemHierarchyDefinitionSid);

        return ivldItemHierarchyDefinition;
    }

    /**
     * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition remove(
        int ivldItemHierarchyDefinitionSid)
        throws NoSuchIvldItemHierarchyDefinitionException, SystemException {
        return remove((Serializable) ivldItemHierarchyDefinitionSid);
    }

    /**
     * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition that was removed
     * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition remove(Serializable primaryKey)
        throws NoSuchIvldItemHierarchyDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IvldItemHierarchyDefinition ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition) session.get(IvldItemHierarchyDefinitionImpl.class,
                    primaryKey);

            if (ivldItemHierarchyDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIvldItemHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ivldItemHierarchyDefinition);
        } catch (NoSuchIvldItemHierarchyDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IvldItemHierarchyDefinition removeImpl(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition)
        throws SystemException {
        ivldItemHierarchyDefinition = toUnwrappedModel(ivldItemHierarchyDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ivldItemHierarchyDefinition)) {
                ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition) session.get(IvldItemHierarchyDefinitionImpl.class,
                        ivldItemHierarchyDefinition.getPrimaryKeyObj());
            }

            if (ivldItemHierarchyDefinition != null) {
                session.delete(ivldItemHierarchyDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ivldItemHierarchyDefinition != null) {
            clearCache(ivldItemHierarchyDefinition);
        }

        return ivldItemHierarchyDefinition;
    }

    @Override
    public IvldItemHierarchyDefinition updateImpl(
        com.stpl.app.model.IvldItemHierarchyDefinition ivldItemHierarchyDefinition)
        throws SystemException {
        ivldItemHierarchyDefinition = toUnwrappedModel(ivldItemHierarchyDefinition);

        boolean isNew = ivldItemHierarchyDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (ivldItemHierarchyDefinition.isNew()) {
                session.save(ivldItemHierarchyDefinition);

                ivldItemHierarchyDefinition.setNew(false);
            } else {
                session.merge(ivldItemHierarchyDefinition);
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

        EntityCacheUtil.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            IvldItemHierarchyDefinitionImpl.class,
            ivldItemHierarchyDefinition.getPrimaryKey(),
            ivldItemHierarchyDefinition);

        return ivldItemHierarchyDefinition;
    }

    protected IvldItemHierarchyDefinition toUnwrappedModel(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        if (ivldItemHierarchyDefinition instanceof IvldItemHierarchyDefinitionImpl) {
            return ivldItemHierarchyDefinition;
        }

        IvldItemHierarchyDefinitionImpl ivldItemHierarchyDefinitionImpl = new IvldItemHierarchyDefinitionImpl();

        ivldItemHierarchyDefinitionImpl.setNew(ivldItemHierarchyDefinition.isNew());
        ivldItemHierarchyDefinitionImpl.setPrimaryKey(ivldItemHierarchyDefinition.getPrimaryKey());

        ivldItemHierarchyDefinitionImpl.setMember(ivldItemHierarchyDefinition.getMember());
        ivldItemHierarchyDefinitionImpl.setReasonForFailure(ivldItemHierarchyDefinition.getReasonForFailure());
        ivldItemHierarchyDefinitionImpl.setItemHierarchyDefnIntfid(ivldItemHierarchyDefinition.getItemHierarchyDefnIntfid());
        ivldItemHierarchyDefinitionImpl.setBpiLvl(ivldItemHierarchyDefinition.getBpiLvl());
        ivldItemHierarchyDefinitionImpl.setModifiedDate(ivldItemHierarchyDefinition.getModifiedDate());
        ivldItemHierarchyDefinitionImpl.setAlias(ivldItemHierarchyDefinition.getAlias());
        ivldItemHierarchyDefinitionImpl.setCreatedBy(ivldItemHierarchyDefinition.getCreatedBy());
        ivldItemHierarchyDefinitionImpl.setCreatedDate(ivldItemHierarchyDefinition.getCreatedDate());
        ivldItemHierarchyDefinitionImpl.setSource(ivldItemHierarchyDefinition.getSource());
        ivldItemHierarchyDefinitionImpl.setBatchId(ivldItemHierarchyDefinition.getBatchId());
        ivldItemHierarchyDefinitionImpl.setAddChgDelIndicator(ivldItemHierarchyDefinition.getAddChgDelIndicator());
        ivldItemHierarchyDefinitionImpl.setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid());
        ivldItemHierarchyDefinitionImpl.setErrorField(ivldItemHierarchyDefinition.getErrorField());
        ivldItemHierarchyDefinitionImpl.setErrorCode(ivldItemHierarchyDefinition.getErrorCode());
        ivldItemHierarchyDefinitionImpl.setIntfInsertedDate(ivldItemHierarchyDefinition.getIntfInsertedDate());
        ivldItemHierarchyDefinitionImpl.setModifiedBy(ivldItemHierarchyDefinition.getModifiedBy());
        ivldItemHierarchyDefinitionImpl.setReprocessedFlag(ivldItemHierarchyDefinition.getReprocessedFlag());

        return ivldItemHierarchyDefinitionImpl;
    }

    /**
     * Returns the ivld item hierarchy definition with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition
     * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIvldItemHierarchyDefinitionException, SystemException {
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition = fetchByPrimaryKey(primaryKey);

        if (ivldItemHierarchyDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIvldItemHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ivldItemHierarchyDefinition;
    }

    /**
     * Returns the ivld item hierarchy definition with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
     *
     * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition
     * @throws com.stpl.app.NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition findByPrimaryKey(
        int ivldItemHierarchyDefinitionSid)
        throws NoSuchIvldItemHierarchyDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) ivldItemHierarchyDefinitionSid);
    }

    /**
     * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition) EntityCacheUtil.getResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                IvldItemHierarchyDefinitionImpl.class, primaryKey);

        if (ivldItemHierarchyDefinition == _nullIvldItemHierarchyDefinition) {
            return null;
        }

        if (ivldItemHierarchyDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition) session.get(IvldItemHierarchyDefinitionImpl.class,
                        primaryKey);

                if (ivldItemHierarchyDefinition != null) {
                    cacheResult(ivldItemHierarchyDefinition);
                } else {
                    EntityCacheUtil.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        IvldItemHierarchyDefinitionImpl.class, primaryKey,
                        _nullIvldItemHierarchyDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    IvldItemHierarchyDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ivldItemHierarchyDefinition;
    }

    /**
     * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
     * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IvldItemHierarchyDefinition fetchByPrimaryKey(
        int ivldItemHierarchyDefinitionSid) throws SystemException {
        return fetchByPrimaryKey((Serializable) ivldItemHierarchyDefinitionSid);
    }

    /**
     * Returns all the ivld item hierarchy definitions.
     *
     * @return the ivld item hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchyDefinition> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ivld item hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item hierarchy definitions
     * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
     * @return the range of ivld item hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchyDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ivld item hierarchy definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of ivld item hierarchy definitions
     * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ivld item hierarchy definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IvldItemHierarchyDefinition> findAll(int start, int end,
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

        List<IvldItemHierarchyDefinition> list = (List<IvldItemHierarchyDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IVLDITEMHIERARCHYDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IVLDITEMHIERARCHYDEFINITION;

                if (pagination) {
                    sql = sql.concat(IvldItemHierarchyDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IvldItemHierarchyDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IvldItemHierarchyDefinition>(list);
                } else {
                    list = (List<IvldItemHierarchyDefinition>) QueryUtil.list(q,
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
     * Removes all the ivld item hierarchy definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : findAll()) {
            remove(ivldItemHierarchyDefinition);
        }
    }

    /**
     * Returns the number of ivld item hierarchy definitions.
     *
     * @return the number of ivld item hierarchy definitions
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

                Query q = session.createQuery(_SQL_COUNT_IVLDITEMHIERARCHYDEFINITION);

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
     * Initializes the ivld item hierarchy definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.IvldItemHierarchyDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IvldItemHierarchyDefinition>> listenersList = new ArrayList<ModelListener<IvldItemHierarchyDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IvldItemHierarchyDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IvldItemHierarchyDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
