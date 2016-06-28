package com.stpl.app.service.persistence;

import com.stpl.app.NoSuchNaProjMasterException;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.model.impl.NaProjMasterImpl;
import com.stpl.app.model.impl.NaProjMasterModelImpl;
import com.stpl.app.service.persistence.NaProjMasterPersistence;

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
 * The persistence implementation for the na proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjMasterPersistence
 * @see NaProjMasterUtil
 * @generated
 */
public class NaProjMasterPersistenceImpl extends BasePersistenceImpl<NaProjMaster>
    implements NaProjMasterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link NaProjMasterUtil} to access the na proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = NaProjMasterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterModelImpl.FINDER_CACHE_ENABLED, NaProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterModelImpl.FINDER_CACHE_ENABLED, NaProjMasterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_NAPROJMASTER = "SELECT naProjMaster FROM NaProjMaster naProjMaster";
    private static final String _SQL_COUNT_NAPROJMASTER = "SELECT COUNT(naProjMaster) FROM NaProjMaster naProjMaster";
    private static final String _ORDER_BY_ENTITY_ALIAS = "naProjMaster.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjMaster exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(NaProjMasterPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "naProjName", "createdDate", "createdBy", "saveFlag",
                "modifiedBy", "modifiedDate", "naProjMasterSid", "itemGroupSid",
                "therapeuticClass", "companyMasterSid"
            });
    private static NaProjMaster _nullNaProjMaster = new NaProjMasterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<NaProjMaster> toCacheModel() {
                return _nullNaProjMasterCacheModel;
            }
        };

    private static CacheModel<NaProjMaster> _nullNaProjMasterCacheModel = new CacheModel<NaProjMaster>() {
            @Override
            public NaProjMaster toEntityModel() {
                return _nullNaProjMaster;
            }
        };

    public NaProjMasterPersistenceImpl() {
        setModelClass(NaProjMaster.class);
    }

    /**
     * Caches the na proj master in the entity cache if it is enabled.
     *
     * @param naProjMaster the na proj master
     */
    @Override
    public void cacheResult(NaProjMaster naProjMaster) {
        EntityCacheUtil.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterImpl.class, naProjMaster.getPrimaryKey(), naProjMaster);

        naProjMaster.resetOriginalValues();
    }

    /**
     * Caches the na proj masters in the entity cache if it is enabled.
     *
     * @param naProjMasters the na proj masters
     */
    @Override
    public void cacheResult(List<NaProjMaster> naProjMasters) {
        for (NaProjMaster naProjMaster : naProjMasters) {
            if (EntityCacheUtil.getResult(
                        NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjMasterImpl.class, naProjMaster.getPrimaryKey()) == null) {
                cacheResult(naProjMaster);
            } else {
                naProjMaster.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all na proj masters.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(NaProjMasterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(NaProjMasterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the na proj master.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(NaProjMaster naProjMaster) {
        EntityCacheUtil.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterImpl.class, naProjMaster.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<NaProjMaster> naProjMasters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (NaProjMaster naProjMaster : naProjMasters) {
            EntityCacheUtil.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                NaProjMasterImpl.class, naProjMaster.getPrimaryKey());
        }
    }

    /**
     * Creates a new na proj master with the primary key. Does not add the na proj master to the database.
     *
     * @param naProjMasterSid the primary key for the new na proj master
     * @return the new na proj master
     */
    @Override
    public NaProjMaster create(int naProjMasterSid) {
        NaProjMaster naProjMaster = new NaProjMasterImpl();

        naProjMaster.setNew(true);
        naProjMaster.setPrimaryKey(naProjMasterSid);

        return naProjMaster;
    }

    /**
     * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param naProjMasterSid the primary key of the na proj master
     * @return the na proj master that was removed
     * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster remove(int naProjMasterSid)
        throws NoSuchNaProjMasterException, SystemException {
        return remove((Serializable) naProjMasterSid);
    }

    /**
     * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the na proj master
     * @return the na proj master that was removed
     * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster remove(Serializable primaryKey)
        throws NoSuchNaProjMasterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            NaProjMaster naProjMaster = (NaProjMaster) session.get(NaProjMasterImpl.class,
                    primaryKey);

            if (naProjMaster == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchNaProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(naProjMaster);
        } catch (NoSuchNaProjMasterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected NaProjMaster removeImpl(NaProjMaster naProjMaster)
        throws SystemException {
        naProjMaster = toUnwrappedModel(naProjMaster);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(naProjMaster)) {
                naProjMaster = (NaProjMaster) session.get(NaProjMasterImpl.class,
                        naProjMaster.getPrimaryKeyObj());
            }

            if (naProjMaster != null) {
                session.delete(naProjMaster);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (naProjMaster != null) {
            clearCache(naProjMaster);
        }

        return naProjMaster;
    }

    @Override
    public NaProjMaster updateImpl(com.stpl.app.model.NaProjMaster naProjMaster)
        throws SystemException {
        naProjMaster = toUnwrappedModel(naProjMaster);

        boolean isNew = naProjMaster.isNew();

        Session session = null;

        try {
            session = openSession();

            if (naProjMaster.isNew()) {
                session.save(naProjMaster);

                naProjMaster.setNew(false);
            } else {
                session.merge(naProjMaster);
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

        EntityCacheUtil.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
            NaProjMasterImpl.class, naProjMaster.getPrimaryKey(), naProjMaster);

        return naProjMaster;
    }

    protected NaProjMaster toUnwrappedModel(NaProjMaster naProjMaster) {
        if (naProjMaster instanceof NaProjMasterImpl) {
            return naProjMaster;
        }

        NaProjMasterImpl naProjMasterImpl = new NaProjMasterImpl();

        naProjMasterImpl.setNew(naProjMaster.isNew());
        naProjMasterImpl.setPrimaryKey(naProjMaster.getPrimaryKey());

        naProjMasterImpl.setNaProjName(naProjMaster.getNaProjName());
        naProjMasterImpl.setCreatedDate(naProjMaster.getCreatedDate());
        naProjMasterImpl.setCreatedBy(naProjMaster.getCreatedBy());
        naProjMasterImpl.setSaveFlag(naProjMaster.isSaveFlag());
        naProjMasterImpl.setModifiedBy(naProjMaster.getModifiedBy());
        naProjMasterImpl.setModifiedDate(naProjMaster.getModifiedDate());
        naProjMasterImpl.setNaProjMasterSid(naProjMaster.getNaProjMasterSid());
        naProjMasterImpl.setItemGroupSid(naProjMaster.getItemGroupSid());
        naProjMasterImpl.setTherapeuticClass(naProjMaster.getTherapeuticClass());
        naProjMasterImpl.setCompanyMasterSid(naProjMaster.getCompanyMasterSid());

        return naProjMasterImpl;
    }

    /**
     * Returns the na proj master with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the na proj master
     * @return the na proj master
     * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster findByPrimaryKey(Serializable primaryKey)
        throws NoSuchNaProjMasterException, SystemException {
        NaProjMaster naProjMaster = fetchByPrimaryKey(primaryKey);

        if (naProjMaster == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchNaProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return naProjMaster;
    }

    /**
     * Returns the na proj master with the primary key or throws a {@link com.stpl.app.NoSuchNaProjMasterException} if it could not be found.
     *
     * @param naProjMasterSid the primary key of the na proj master
     * @return the na proj master
     * @throws com.stpl.app.NoSuchNaProjMasterException if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster findByPrimaryKey(int naProjMasterSid)
        throws NoSuchNaProjMasterException, SystemException {
        return findByPrimaryKey((Serializable) naProjMasterSid);
    }

    /**
     * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the na proj master
     * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        NaProjMaster naProjMaster = (NaProjMaster) EntityCacheUtil.getResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                NaProjMasterImpl.class, primaryKey);

        if (naProjMaster == _nullNaProjMaster) {
            return null;
        }

        if (naProjMaster == null) {
            Session session = null;

            try {
                session = openSession();

                naProjMaster = (NaProjMaster) session.get(NaProjMasterImpl.class,
                        primaryKey);

                if (naProjMaster != null) {
                    cacheResult(naProjMaster);
                } else {
                    EntityCacheUtil.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                        NaProjMasterImpl.class, primaryKey, _nullNaProjMaster);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
                    NaProjMasterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return naProjMaster;
    }

    /**
     * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param naProjMasterSid the primary key of the na proj master
     * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public NaProjMaster fetchByPrimaryKey(int naProjMasterSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) naProjMasterSid);
    }

    /**
     * Returns all the na proj masters.
     *
     * @return the na proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjMaster> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the na proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na proj masters
     * @param end the upper bound of the range of na proj masters (not inclusive)
     * @return the range of na proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjMaster> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the na proj masters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of na proj masters
     * @param end the upper bound of the range of na proj masters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of na proj masters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<NaProjMaster> findAll(int start, int end,
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

        List<NaProjMaster> list = (List<NaProjMaster>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_NAPROJMASTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_NAPROJMASTER;

                if (pagination) {
                    sql = sql.concat(NaProjMasterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<NaProjMaster>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<NaProjMaster>(list);
                } else {
                    list = (List<NaProjMaster>) QueryUtil.list(q, getDialect(),
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
     * Removes all the na proj masters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (NaProjMaster naProjMaster : findAll()) {
            remove(naProjMaster);
        }
    }

    /**
     * Returns the number of na proj masters.
     *
     * @return the number of na proj masters
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

                Query q = session.createQuery(_SQL_COUNT_NAPROJMASTER);

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
     * Initializes the na proj master persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.model.NaProjMaster")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<NaProjMaster>> listenersList = new ArrayList<ModelListener<NaProjMaster>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<NaProjMaster>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(NaProjMasterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
