package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException;
import com.stpl.app.parttwo.model.VwCompanyIdentifier;
import com.stpl.app.parttwo.model.impl.VwCompanyIdentifierImpl;
import com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCompanyIdentifierPersistence;

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
 * The persistence implementation for the vw company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyIdentifierPersistence
 * @see VwCompanyIdentifierUtil
 * @generated
 */
public class VwCompanyIdentifierPersistenceImpl extends BasePersistenceImpl<VwCompanyIdentifier>
    implements VwCompanyIdentifierPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link VwCompanyIdentifierUtil} to access the vw company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = VwCompanyIdentifierImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
            VwCompanyIdentifierImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_VWCOMPANYIDENTIFIER = "SELECT vwCompanyIdentifier FROM VwCompanyIdentifier vwCompanyIdentifier";
    private static final String _SQL_COUNT_VWCOMPANYIDENTIFIER = "SELECT COUNT(vwCompanyIdentifier) FROM VwCompanyIdentifier vwCompanyIdentifier";
    private static final String _ORDER_BY_ENTITY_ALIAS = "vwCompanyIdentifier.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCompanyIdentifier exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(VwCompanyIdentifierPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "companyId", "companyName", "endDate", "companyIdentifierSid",
                "modifiedDate", "identifierStatus", "companyIdentifier",
                "entityCode", "startDate", "createdDate", "createdBy", "source",
                "companyNo", "batchId", "addChgDelIndicator",
                "identifierCodeQualifierName", "modifiedBy",
                "identifierCodeQualifier"
            });
    private static VwCompanyIdentifier _nullVwCompanyIdentifier = new VwCompanyIdentifierImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<VwCompanyIdentifier> toCacheModel() {
                return _nullVwCompanyIdentifierCacheModel;
            }
        };

    private static CacheModel<VwCompanyIdentifier> _nullVwCompanyIdentifierCacheModel =
        new CacheModel<VwCompanyIdentifier>() {
            @Override
            public VwCompanyIdentifier toEntityModel() {
                return _nullVwCompanyIdentifier;
            }
        };

    public VwCompanyIdentifierPersistenceImpl() {
        setModelClass(VwCompanyIdentifier.class);
    }

    /**
     * Caches the vw company identifier in the entity cache if it is enabled.
     *
     * @param vwCompanyIdentifier the vw company identifier
     */
    @Override
    public void cacheResult(VwCompanyIdentifier vwCompanyIdentifier) {
        EntityCacheUtil.putResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierImpl.class, vwCompanyIdentifier.getPrimaryKey(),
            vwCompanyIdentifier);

        vwCompanyIdentifier.resetOriginalValues();
    }

    /**
     * Caches the vw company identifiers in the entity cache if it is enabled.
     *
     * @param vwCompanyIdentifiers the vw company identifiers
     */
    @Override
    public void cacheResult(List<VwCompanyIdentifier> vwCompanyIdentifiers) {
        for (VwCompanyIdentifier vwCompanyIdentifier : vwCompanyIdentifiers) {
            if (EntityCacheUtil.getResult(
                        VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyIdentifierImpl.class,
                        vwCompanyIdentifier.getPrimaryKey()) == null) {
                cacheResult(vwCompanyIdentifier);
            } else {
                vwCompanyIdentifier.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all vw company identifiers.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(VwCompanyIdentifierImpl.class.getName());
        }

        EntityCacheUtil.clearCache(VwCompanyIdentifierImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the vw company identifier.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(VwCompanyIdentifier vwCompanyIdentifier) {
        EntityCacheUtil.removeResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierImpl.class, vwCompanyIdentifier.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<VwCompanyIdentifier> vwCompanyIdentifiers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (VwCompanyIdentifier vwCompanyIdentifier : vwCompanyIdentifiers) {
            EntityCacheUtil.removeResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyIdentifierImpl.class,
                vwCompanyIdentifier.getPrimaryKey());
        }
    }

    /**
     * Creates a new vw company identifier with the primary key. Does not add the vw company identifier to the database.
     *
     * @param companyIdentifierSid the primary key for the new vw company identifier
     * @return the new vw company identifier
     */
    @Override
    public VwCompanyIdentifier create(int companyIdentifierSid) {
        VwCompanyIdentifier vwCompanyIdentifier = new VwCompanyIdentifierImpl();

        vwCompanyIdentifier.setNew(true);
        vwCompanyIdentifier.setPrimaryKey(companyIdentifierSid);

        return vwCompanyIdentifier;
    }

    /**
     * Removes the vw company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param companyIdentifierSid the primary key of the vw company identifier
     * @return the vw company identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier remove(int companyIdentifierSid)
        throws NoSuchVwCompanyIdentifierException, SystemException {
        return remove((Serializable) companyIdentifierSid);
    }

    /**
     * Removes the vw company identifier with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the vw company identifier
     * @return the vw company identifier that was removed
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier remove(Serializable primaryKey)
        throws NoSuchVwCompanyIdentifierException, SystemException {
        Session session = null;

        try {
            session = openSession();

            VwCompanyIdentifier vwCompanyIdentifier = (VwCompanyIdentifier) session.get(VwCompanyIdentifierImpl.class,
                    primaryKey);

            if (vwCompanyIdentifier == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchVwCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(vwCompanyIdentifier);
        } catch (NoSuchVwCompanyIdentifierException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected VwCompanyIdentifier removeImpl(
        VwCompanyIdentifier vwCompanyIdentifier) throws SystemException {
        vwCompanyIdentifier = toUnwrappedModel(vwCompanyIdentifier);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(vwCompanyIdentifier)) {
                vwCompanyIdentifier = (VwCompanyIdentifier) session.get(VwCompanyIdentifierImpl.class,
                        vwCompanyIdentifier.getPrimaryKeyObj());
            }

            if (vwCompanyIdentifier != null) {
                session.delete(vwCompanyIdentifier);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (vwCompanyIdentifier != null) {
            clearCache(vwCompanyIdentifier);
        }

        return vwCompanyIdentifier;
    }

    @Override
    public VwCompanyIdentifier updateImpl(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws SystemException {
        vwCompanyIdentifier = toUnwrappedModel(vwCompanyIdentifier);

        boolean isNew = vwCompanyIdentifier.isNew();

        Session session = null;

        try {
            session = openSession();

            if (vwCompanyIdentifier.isNew()) {
                session.save(vwCompanyIdentifier);

                vwCompanyIdentifier.setNew(false);
            } else {
                session.merge(vwCompanyIdentifier);
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

        EntityCacheUtil.putResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
            VwCompanyIdentifierImpl.class, vwCompanyIdentifier.getPrimaryKey(),
            vwCompanyIdentifier);

        return vwCompanyIdentifier;
    }

    protected VwCompanyIdentifier toUnwrappedModel(
        VwCompanyIdentifier vwCompanyIdentifier) {
        if (vwCompanyIdentifier instanceof VwCompanyIdentifierImpl) {
            return vwCompanyIdentifier;
        }

        VwCompanyIdentifierImpl vwCompanyIdentifierImpl = new VwCompanyIdentifierImpl();

        vwCompanyIdentifierImpl.setNew(vwCompanyIdentifier.isNew());
        vwCompanyIdentifierImpl.setPrimaryKey(vwCompanyIdentifier.getPrimaryKey());

        vwCompanyIdentifierImpl.setCompanyId(vwCompanyIdentifier.getCompanyId());
        vwCompanyIdentifierImpl.setCompanyName(vwCompanyIdentifier.getCompanyName());
        vwCompanyIdentifierImpl.setEndDate(vwCompanyIdentifier.getEndDate());
        vwCompanyIdentifierImpl.setCompanyIdentifierSid(vwCompanyIdentifier.getCompanyIdentifierSid());
        vwCompanyIdentifierImpl.setModifiedDate(vwCompanyIdentifier.getModifiedDate());
        vwCompanyIdentifierImpl.setIdentifierStatus(vwCompanyIdentifier.getIdentifierStatus());
        vwCompanyIdentifierImpl.setCompanyIdentifier(vwCompanyIdentifier.getCompanyIdentifier());
        vwCompanyIdentifierImpl.setEntityCode(vwCompanyIdentifier.getEntityCode());
        vwCompanyIdentifierImpl.setStartDate(vwCompanyIdentifier.getStartDate());
        vwCompanyIdentifierImpl.setCreatedDate(vwCompanyIdentifier.getCreatedDate());
        vwCompanyIdentifierImpl.setCreatedBy(vwCompanyIdentifier.getCreatedBy());
        vwCompanyIdentifierImpl.setSource(vwCompanyIdentifier.getSource());
        vwCompanyIdentifierImpl.setCompanyNo(vwCompanyIdentifier.getCompanyNo());
        vwCompanyIdentifierImpl.setBatchId(vwCompanyIdentifier.getBatchId());
        vwCompanyIdentifierImpl.setAddChgDelIndicator(vwCompanyIdentifier.getAddChgDelIndicator());
        vwCompanyIdentifierImpl.setIdentifierCodeQualifierName(vwCompanyIdentifier.getIdentifierCodeQualifierName());
        vwCompanyIdentifierImpl.setModifiedBy(vwCompanyIdentifier.getModifiedBy());
        vwCompanyIdentifierImpl.setIdentifierCodeQualifier(vwCompanyIdentifier.getIdentifierCodeQualifier());

        return vwCompanyIdentifierImpl;
    }

    /**
     * Returns the vw company identifier with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the vw company identifier
     * @return the vw company identifier
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier findByPrimaryKey(Serializable primaryKey)
        throws NoSuchVwCompanyIdentifierException, SystemException {
        VwCompanyIdentifier vwCompanyIdentifier = fetchByPrimaryKey(primaryKey);

        if (vwCompanyIdentifier == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchVwCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return vwCompanyIdentifier;
    }

    /**
     * Returns the vw company identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException} if it could not be found.
     *
     * @param companyIdentifierSid the primary key of the vw company identifier
     * @return the vw company identifier
     * @throws com.stpl.app.parttwo.NoSuchVwCompanyIdentifierException if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier findByPrimaryKey(int companyIdentifierSid)
        throws NoSuchVwCompanyIdentifierException, SystemException {
        return findByPrimaryKey((Serializable) companyIdentifierSid);
    }

    /**
     * Returns the vw company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the vw company identifier
     * @return the vw company identifier, or <code>null</code> if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        VwCompanyIdentifier vwCompanyIdentifier = (VwCompanyIdentifier) EntityCacheUtil.getResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                VwCompanyIdentifierImpl.class, primaryKey);

        if (vwCompanyIdentifier == _nullVwCompanyIdentifier) {
            return null;
        }

        if (vwCompanyIdentifier == null) {
            Session session = null;

            try {
                session = openSession();

                vwCompanyIdentifier = (VwCompanyIdentifier) session.get(VwCompanyIdentifierImpl.class,
                        primaryKey);

                if (vwCompanyIdentifier != null) {
                    cacheResult(vwCompanyIdentifier);
                } else {
                    EntityCacheUtil.putResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                        VwCompanyIdentifierImpl.class, primaryKey,
                        _nullVwCompanyIdentifier);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(VwCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
                    VwCompanyIdentifierImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return vwCompanyIdentifier;
    }

    /**
     * Returns the vw company identifier with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param companyIdentifierSid the primary key of the vw company identifier
     * @return the vw company identifier, or <code>null</code> if a vw company identifier with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public VwCompanyIdentifier fetchByPrimaryKey(int companyIdentifierSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) companyIdentifierSid);
    }

    /**
     * Returns all the vw company identifiers.
     *
     * @return the vw company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyIdentifier> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the vw company identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company identifiers
     * @param end the upper bound of the range of vw company identifiers (not inclusive)
     * @return the range of vw company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyIdentifier> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the vw company identifiers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of vw company identifiers
     * @param end the upper bound of the range of vw company identifiers (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of vw company identifiers
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<VwCompanyIdentifier> findAll(int start, int end,
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

        List<VwCompanyIdentifier> list = (List<VwCompanyIdentifier>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_VWCOMPANYIDENTIFIER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_VWCOMPANYIDENTIFIER;

                if (pagination) {
                    sql = sql.concat(VwCompanyIdentifierModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<VwCompanyIdentifier>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<VwCompanyIdentifier>(list);
                } else {
                    list = (List<VwCompanyIdentifier>) QueryUtil.list(q,
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
     * Removes all the vw company identifiers from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (VwCompanyIdentifier vwCompanyIdentifier : findAll()) {
            remove(vwCompanyIdentifier);
        }
    }

    /**
     * Returns the number of vw company identifiers.
     *
     * @return the number of vw company identifiers
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

                Query q = session.createQuery(_SQL_COUNT_VWCOMPANYIDENTIFIER);

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
     * Initializes the vw company identifier persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.VwCompanyIdentifier")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<VwCompanyIdentifier>> listenersList = new ArrayList<ModelListener<VwCompanyIdentifier>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<VwCompanyIdentifier>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(VwCompanyIdentifierImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
