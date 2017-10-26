package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.NoSuchCffAdditionalInfoException;
import com.stpl.app.parttwo.model.CffAdditionalInfo;
import com.stpl.app.parttwo.model.impl.CffAdditionalInfoImpl;
import com.stpl.app.parttwo.model.impl.CffAdditionalInfoModelImpl;
import com.stpl.app.parttwo.service.persistence.CffAdditionalInfoPersistence;

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
 * The persistence implementation for the cff additional info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffAdditionalInfoPersistence
 * @see CffAdditionalInfoUtil
 * @generated
 */
public class CffAdditionalInfoPersistenceImpl extends BasePersistenceImpl<CffAdditionalInfo>
    implements CffAdditionalInfoPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link CffAdditionalInfoUtil} to access the cff additional info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = CffAdditionalInfoImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoModelImpl.FINDER_CACHE_ENABLED,
            CffAdditionalInfoImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoModelImpl.FINDER_CACHE_ENABLED,
            CffAdditionalInfoImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CFFADDITIONALINFO = "SELECT cffAdditionalInfo FROM CffAdditionalInfo cffAdditionalInfo";
    private static final String _SQL_COUNT_CFFADDITIONALINFO = "SELECT COUNT(cffAdditionalInfo) FROM CffAdditionalInfo cffAdditionalInfo";
    private static final String _ORDER_BY_ENTITY_ALIAS = "cffAdditionalInfo.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffAdditionalInfo exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(CffAdditionalInfoPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "createdDate", "createdBy", "cffMasterSid",
                "cffAdditionalInfoSid", "notes"
            });
    private static CffAdditionalInfo _nullCffAdditionalInfo = new CffAdditionalInfoImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<CffAdditionalInfo> toCacheModel() {
                return _nullCffAdditionalInfoCacheModel;
            }
        };

    private static CacheModel<CffAdditionalInfo> _nullCffAdditionalInfoCacheModel =
        new CacheModel<CffAdditionalInfo>() {
            @Override
            public CffAdditionalInfo toEntityModel() {
                return _nullCffAdditionalInfo;
            }
        };

    public CffAdditionalInfoPersistenceImpl() {
        setModelClass(CffAdditionalInfo.class);
    }

    /**
     * Caches the cff additional info in the entity cache if it is enabled.
     *
     * @param cffAdditionalInfo the cff additional info
     */
    @Override
    public void cacheResult(CffAdditionalInfo cffAdditionalInfo) {
        EntityCacheUtil.putResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoImpl.class, cffAdditionalInfo.getPrimaryKey(),
            cffAdditionalInfo);

        cffAdditionalInfo.resetOriginalValues();
    }

    /**
     * Caches the cff additional infos in the entity cache if it is enabled.
     *
     * @param cffAdditionalInfos the cff additional infos
     */
    @Override
    public void cacheResult(List<CffAdditionalInfo> cffAdditionalInfos) {
        for (CffAdditionalInfo cffAdditionalInfo : cffAdditionalInfos) {
            if (EntityCacheUtil.getResult(
                        CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
                        CffAdditionalInfoImpl.class,
                        cffAdditionalInfo.getPrimaryKey()) == null) {
                cacheResult(cffAdditionalInfo);
            } else {
                cffAdditionalInfo.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all cff additional infos.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(CffAdditionalInfoImpl.class.getName());
        }

        EntityCacheUtil.clearCache(CffAdditionalInfoImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the cff additional info.
     *
     * <p>
     * The {@link com.stpl.portal.kernel.dao.orm.EntityCache} and {@link com.stpl.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(CffAdditionalInfo cffAdditionalInfo) {
        EntityCacheUtil.removeResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoImpl.class, cffAdditionalInfo.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<CffAdditionalInfo> cffAdditionalInfos) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (CffAdditionalInfo cffAdditionalInfo : cffAdditionalInfos) {
            EntityCacheUtil.removeResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
                CffAdditionalInfoImpl.class, cffAdditionalInfo.getPrimaryKey());
        }
    }

    /**
     * Creates a new cff additional info with the primary key. Does not add the cff additional info to the database.
     *
     * @param cffAdditionalInfoSid the primary key for the new cff additional info
     * @return the new cff additional info
     */
    @Override
    public CffAdditionalInfo create(int cffAdditionalInfoSid) {
        CffAdditionalInfo cffAdditionalInfo = new CffAdditionalInfoImpl();

        cffAdditionalInfo.setNew(true);
        cffAdditionalInfo.setPrimaryKey(cffAdditionalInfoSid);

        return cffAdditionalInfo;
    }

    /**
     * Removes the cff additional info with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param cffAdditionalInfoSid the primary key of the cff additional info
     * @return the cff additional info that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo remove(int cffAdditionalInfoSid)
        throws NoSuchCffAdditionalInfoException, SystemException {
        return remove((Serializable) cffAdditionalInfoSid);
    }

    /**
     * Removes the cff additional info with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the cff additional info
     * @return the cff additional info that was removed
     * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo remove(Serializable primaryKey)
        throws NoSuchCffAdditionalInfoException, SystemException {
        Session session = null;

        try {
            session = openSession();

            CffAdditionalInfo cffAdditionalInfo = (CffAdditionalInfo) session.get(CffAdditionalInfoImpl.class,
                    primaryKey);

            if (cffAdditionalInfo == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchCffAdditionalInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(cffAdditionalInfo);
        } catch (NoSuchCffAdditionalInfoException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected CffAdditionalInfo removeImpl(CffAdditionalInfo cffAdditionalInfo)
        throws SystemException {
        cffAdditionalInfo = toUnwrappedModel(cffAdditionalInfo);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(cffAdditionalInfo)) {
                cffAdditionalInfo = (CffAdditionalInfo) session.get(CffAdditionalInfoImpl.class,
                        cffAdditionalInfo.getPrimaryKeyObj());
            }

            if (cffAdditionalInfo != null) {
                session.delete(cffAdditionalInfo);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (cffAdditionalInfo != null) {
            clearCache(cffAdditionalInfo);
        }

        return cffAdditionalInfo;
    }

    @Override
    public CffAdditionalInfo updateImpl(
        com.stpl.app.parttwo.model.CffAdditionalInfo cffAdditionalInfo)
        throws SystemException {
        cffAdditionalInfo = toUnwrappedModel(cffAdditionalInfo);

        boolean isNew = cffAdditionalInfo.isNew();

        Session session = null;

        try {
            session = openSession();

            if (cffAdditionalInfo.isNew()) {
                session.save(cffAdditionalInfo);

                cffAdditionalInfo.setNew(false);
            } else {
                session.merge(cffAdditionalInfo);
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

        EntityCacheUtil.putResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
            CffAdditionalInfoImpl.class, cffAdditionalInfo.getPrimaryKey(),
            cffAdditionalInfo);

        return cffAdditionalInfo;
    }

    protected CffAdditionalInfo toUnwrappedModel(
        CffAdditionalInfo cffAdditionalInfo) {
        if (cffAdditionalInfo instanceof CffAdditionalInfoImpl) {
            return cffAdditionalInfo;
        }

        CffAdditionalInfoImpl cffAdditionalInfoImpl = new CffAdditionalInfoImpl();

        cffAdditionalInfoImpl.setNew(cffAdditionalInfo.isNew());
        cffAdditionalInfoImpl.setPrimaryKey(cffAdditionalInfo.getPrimaryKey());

        cffAdditionalInfoImpl.setCreatedDate(cffAdditionalInfo.getCreatedDate());
        cffAdditionalInfoImpl.setCreatedBy(cffAdditionalInfo.getCreatedBy());
        cffAdditionalInfoImpl.setCffMasterSid(cffAdditionalInfo.getCffMasterSid());
        cffAdditionalInfoImpl.setCffAdditionalInfoSid(cffAdditionalInfo.getCffAdditionalInfoSid());
        cffAdditionalInfoImpl.setNotes(cffAdditionalInfo.getNotes());

        return cffAdditionalInfoImpl;
    }

    /**
     * Returns the cff additional info with the primary key or throws a {@link com.stpl.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the cff additional info
     * @return the cff additional info
     * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo findByPrimaryKey(Serializable primaryKey)
        throws NoSuchCffAdditionalInfoException, SystemException {
        CffAdditionalInfo cffAdditionalInfo = fetchByPrimaryKey(primaryKey);

        if (cffAdditionalInfo == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchCffAdditionalInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return cffAdditionalInfo;
    }

    /**
     * Returns the cff additional info with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffAdditionalInfoException} if it could not be found.
     *
     * @param cffAdditionalInfoSid the primary key of the cff additional info
     * @return the cff additional info
     * @throws com.stpl.app.parttwo.NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo findByPrimaryKey(int cffAdditionalInfoSid)
        throws NoSuchCffAdditionalInfoException, SystemException {
        return findByPrimaryKey((Serializable) cffAdditionalInfoSid);
    }

    /**
     * Returns the cff additional info with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the cff additional info
     * @return the cff additional info, or <code>null</code> if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        CffAdditionalInfo cffAdditionalInfo = (CffAdditionalInfo) EntityCacheUtil.getResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
                CffAdditionalInfoImpl.class, primaryKey);

        if (cffAdditionalInfo == _nullCffAdditionalInfo) {
            return null;
        }

        if (cffAdditionalInfo == null) {
            Session session = null;

            try {
                session = openSession();

                cffAdditionalInfo = (CffAdditionalInfo) session.get(CffAdditionalInfoImpl.class,
                        primaryKey);

                if (cffAdditionalInfo != null) {
                    cacheResult(cffAdditionalInfo);
                } else {
                    EntityCacheUtil.putResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
                        CffAdditionalInfoImpl.class, primaryKey,
                        _nullCffAdditionalInfo);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(CffAdditionalInfoModelImpl.ENTITY_CACHE_ENABLED,
                    CffAdditionalInfoImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return cffAdditionalInfo;
    }

    /**
     * Returns the cff additional info with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param cffAdditionalInfoSid the primary key of the cff additional info
     * @return the cff additional info, or <code>null</code> if a cff additional info with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public CffAdditionalInfo fetchByPrimaryKey(int cffAdditionalInfoSid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) cffAdditionalInfoSid);
    }

    /**
     * Returns all the cff additional infos.
     *
     * @return the cff additional infos
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffAdditionalInfo> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the cff additional infos.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff additional infos
     * @param end the upper bound of the range of cff additional infos (not inclusive)
     * @return the range of cff additional infos
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffAdditionalInfo> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the cff additional infos.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of cff additional infos
     * @param end the upper bound of the range of cff additional infos (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of cff additional infos
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<CffAdditionalInfo> findAll(int start, int end,
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

        List<CffAdditionalInfo> list = (List<CffAdditionalInfo>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CFFADDITIONALINFO);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CFFADDITIONALINFO;

                if (pagination) {
                    sql = sql.concat(CffAdditionalInfoModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<CffAdditionalInfo>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<CffAdditionalInfo>(list);
                } else {
                    list = (List<CffAdditionalInfo>) QueryUtil.list(q,
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
     * Removes all the cff additional infos from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (CffAdditionalInfo cffAdditionalInfo : findAll()) {
            remove(cffAdditionalInfo);
        }
    }

    /**
     * Returns the number of cff additional infos.
     *
     * @return the number of cff additional infos
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

                Query q = session.createQuery(_SQL_COUNT_CFFADDITIONALINFO);

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
     * Initializes the cff additional info persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.stpl.util.service.ServiceProps.get(
                        "value.object.listener.com.stpl.app.parttwo.model.CffAdditionalInfo")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<CffAdditionalInfo>> listenersList = new ArrayList<ModelListener<CffAdditionalInfo>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<CffAdditionalInfo>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(CffAdditionalInfoImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
