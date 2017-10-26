package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwItemIdentifierException;
import com.stpl.app.parttwo.model.VwItemIdentifier;
import com.stpl.app.parttwo.model.impl.VwItemIdentifierImpl;
import com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.VwItemIdentifierPersistence;

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
 * The persistence implementation for the vw item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemIdentifierPersistence
 * @see VwItemIdentifierUtil
 * @generated
 */
public class VwItemIdentifierPersistenceImpl extends BasePersistenceImpl<VwItemIdentifier>
    implements VwItemIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwItemIdentifierUtil} to access the vw item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwItemIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            VwItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
            VwItemIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWITEMIDENTIFIER = "SELECT vwItemIdentifier FROM VwItemIdentifier vwItemIdentifier";
    private static final String _SQL_COUNT_VWITEMIDENTIFIER = "SELECT COUNT(vwItemIdentifier) FROM VwItemIdentifier vwItemIdentifier";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwItemIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwItemIdentifier exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwItemIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "itemStatus", "itemIdentifierSid", "endDate", "itemId",
                "modifiedDate", "entityCode", "startDate", "createdDate",
                "createdBy", "source", "batchId", "addChgDelIndicator",
                "itemName", "itemIdentifier", "identifierCodeQualifierName",
                "modifiedBy", "itemNo", "identifierCodeQualifier"
            });
    private static VwItemIdentifier _nullVwItemIdentifier = new VwItemIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwItemIdentifier> toCacheModel() {
                return _nullVwItemIdentifierCacheModel;
            }
        };

    private static CacheModel<VwItemIdentifier> _nullVwItemIdentifierCacheModel = new CacheModel<VwItemIdentifier>() {
            @Override
            public VwItemIdentifier toEntityModel() {
                return _nullVwItemIdentifier;
            }
        };

    public VwItemIdentifierPersistenceImpl() {
        setModelClass(VwItemIdentifier.class);
    }

    /**
     * Caches the vw item identifier in the entity cache if it is enabled.
     *
     * @param vwItemIdentifier the vw item identifier
     */
    @Override
    public void cacheResult(VwItemIdentifier vwItemIdentifier) {
        EntityCacheUtil.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey(),
            vwItemIdentifier);

        vwItemIdentifier.resetOriginalValues();
    }

    /**
     * Caches the vw item identifiers in the entity cache if it is enabled.
     *
     * @param vwItemIdentifiers the vw item identifiers
     */
    @Override
    public void cacheResult(List<VwItemIdentifier> vwItemIdentifiers) {
        for (VwItemIdentifier vwItemIdentifier : vwItemIdentifiers) {
            if (EntityCacheUtil.getResult(
                        VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemIdentifierImpl.class,
                        vwItemIdentifier.getPrimaryKey()) == null) {
                cacheResult(vwItemIdentifier);
            } else {
                vwItemIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw item identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwItemIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwItemIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw item identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwItemIdentifier vwItemIdentifier) {
        EntityCacheUtil.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwItemIdentifier> vwItemIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwItemIdentifier vwItemIdentifier : vwItemIdentifiers) {
            EntityCacheUtil.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
     *
     * @param itemIdentifierSid the primary key for the new vw item identifier
     * @return the new vw item identifier
     */
    @Override
    public VwItemIdentifier create(int itemIdentifierSid) {
        VwItemIdentifier vwItemIdentifier = new VwItemIdentifierImpl();

        vwItemIdentifier.setNew(true);
        vwItemIdentifier.setPrimaryKey(itemIdentifierSid);

        return vwItemIdentifier;
    }

    /**
     * Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param itemIdentifierSid the primary key of the vw item identifier
     * @return the vw item identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier remove(int itemIdentifierSid)
        throws NoSuchVwItemIdentifierException, SystemException {
        return remove((Serializable) itemIdentifierSid);
    }

    /**
     * Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw item identifier
     * @return the vw item identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier remove(Serializable primaryKey)
        throws NoSuchVwItemIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwItemIdentifier vwItemIdentifier = (VwItemIdentifier) session.get(VwItemIdentifierImpl.class,
                    primaryKey);

            if (vwItemIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwItemIdentifier);
        } catch (NoSuchVwItemIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwItemIdentifier removeImpl(VwItemIdentifier vwItemIdentifier)
        throws SystemException {
        vwItemIdentifier = toUnwrappedModel(vwItemIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwItemIdentifier)) {
                vwItemIdentifier = (VwItemIdentifier) session.get(VwItemIdentifierImpl.class,
                        vwItemIdentifier.getPrimaryKeyObj());
            }

            if (vwItemIdentifier != null) {
                session.delete(vwItemIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwItemIdentifier != null) {
            clearCache(vwItemIdentifier);
        }

        return vwItemIdentifier;
    }

    @Override
    public VwItemIdentifier updateImpl(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier)
        throws SystemException {
        vwItemIdentifier = toUnwrappedModel(vwItemIdentifier);

        boolean isNew = vwItemIdentifier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwItemIdentifier.isNew()) {
                session.save(vwItemIdentifier);

                vwItemIdentifier.setNew(false);
            } else {
                session.merge(vwItemIdentifier);
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

        EntityCacheUtil.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey(),
            vwItemIdentifier);

        return vwItemIdentifier;
    }

    protected VwItemIdentifier toUnwrappedModel(
        VwItemIdentifier vwItemIdentifier) {
        if (vwItemIdentifier instanceof VwItemIdentifierImpl) {
            return vwItemIdentifier;
        }

        VwItemIdentifierImpl vwItemIdentifierImpl = new VwItemIdentifierImpl();

        vwItemIdentifierImpl.setNew(vwItemIdentifier.isNew());
        vwItemIdentifierImpl.setPrimaryKey(vwItemIdentifier.getPrimaryKey());

        vwItemIdentifierImpl.setItemStatus(vwItemIdentifier.getItemStatus());
        vwItemIdentifierImpl.setItemIdentifierSid(vwItemIdentifier.getItemIdentifierSid());
        vwItemIdentifierImpl.setEndDate(vwItemIdentifier.getEndDate());
        vwItemIdentifierImpl.setItemId(vwItemIdentifier.getItemId());
        vwItemIdentifierImpl.setModifiedDate(vwItemIdentifier.getModifiedDate());
        vwItemIdentifierImpl.setEntityCode(vwItemIdentifier.getEntityCode());
        vwItemIdentifierImpl.setStartDate(vwItemIdentifier.getStartDate());
        vwItemIdentifierImpl.setCreatedDate(vwItemIdentifier.getCreatedDate());
        vwItemIdentifierImpl.setCreatedBy(vwItemIdentifier.getCreatedBy());
        vwItemIdentifierImpl.setSource(vwItemIdentifier.getSource());
        vwItemIdentifierImpl.setBatchId(vwItemIdentifier.getBatchId());
        vwItemIdentifierImpl.setAddChgDelIndicator(vwItemIdentifier.getAddChgDelIndicator());
        vwItemIdentifierImpl.setItemName(vwItemIdentifier.getItemName());
        vwItemIdentifierImpl.setItemIdentifier(vwItemIdentifier.getItemIdentifier());
        vwItemIdentifierImpl.setIdentifierCodeQualifierName(vwItemIdentifier.getIdentifierCodeQualifierName());
        vwItemIdentifierImpl.setModifiedBy(vwItemIdentifier.getModifiedBy());
        vwItemIdentifierImpl.setItemNo(vwItemIdentifier.getItemNo());
        vwItemIdentifierImpl.setIdentifierCodeQualifier(vwItemIdentifier.getIdentifierCodeQualifier());

        return vwItemIdentifierImpl;
    }

    /**
     * Returns the vw item identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw item identifier
     * @return the vw item identifier
     * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwItemIdentifierException, SystemException {
        VwItemIdentifier vwItemIdentifier = fetchByPrimaryKey(primaryKey);

        if (vwItemIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwItemIdentifier;
    }

    /**
     * Returns the vw item identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemIdentifierException} if it could not be found.
     *
     * @param itemIdentifierSid the primary key of the vw item identifier
     * @return the vw item identifier
     * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier findByPrimaryKey(int itemIdentifierSid)
        throws NoSuchVwItemIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) itemIdentifierSid);
    }

    /**
     * Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw item identifier
     * @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwItemIdentifier vwItemIdentifier = (VwItemIdentifier) EntityCacheUtil.getResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                VwItemIdentifierImpl.class, primaryKey);

        if (vwItemIdentifier == _nullVwItemIdentifier) {
            return null;
        }

        if (vwItemIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                vwItemIdentifier = (VwItemIdentifier) session.get(VwItemIdentifierImpl.class,
                        primaryKey);

                if (vwItemIdentifier != null) {
                    cacheResult(vwItemIdentifier);
                } else {
                    EntityCacheUtil.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        VwItemIdentifierImpl.class, primaryKey,
                        _nullVwItemIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    VwItemIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwItemIdentifier;
    }

    /**
     * Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param itemIdentifierSid the primary key of the vw item identifier
     * @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwItemIdentifier fetchByPrimaryKey(int itemIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) itemIdentifierSid);
    }

    /**
     * Returns all the vw item identifiers.
     *
     * @return the vw item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item identifiers
     * @param end the upper bound of the range of vw item identifiers (not inclusive)
     * @return the range of vw item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw item identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw item identifiers
     * @param end the upper bound of the range of vw item identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw item identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwItemIdentifier> findAll(int start, int end,
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

        List<VwItemIdentifier> list = (List<VwItemIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWITEMIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWITEMIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(VwItemIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwItemIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwItemIdentifier>(list);
                } else {
                    list = (List<VwItemIdentifier>) QueryUtil.list(q,
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
     * Removes all the vw item identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwItemIdentifier vwItemIdentifier : findAll()) {
            remove(vwItemIdentifier);
        }
    }

    /**
     * Returns the number of vw item identifiers.
     *
     * @return the number of vw item identifiers
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

                Query q = session.createQuery(_SQL_COUNT_VWITEMIDENTIFIER);

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
     * Initializes the vw item identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwItemIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwItemIdentifier>> listenersList = new ArrayList<ModelListener<VwItemIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwItemIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwItemIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
